package com.facebook.marketing;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.marketing.internal.Constants;
import com.facebook.marketing.internal.MarketingLogger;
import com.facebook.marketing.internal.MarketingUtils;
import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ViewIndexer {
    private static final String APP_VERSION_PARAM = "app_version";
    private static final String PLATFORM_PARAM = "platform";
    private static final String SUCCESS = "success";
    private static final String TAG = ViewIndexer.class.getCanonicalName();
    private static final String TREE_PARAM = "tree";
    private WeakReference<Activity> activityReference;
    private Timer indexingTimer;
    private String previousDigest = null;
    private final Handler uiThreadHandler = new Handler(Looper.getMainLooper());
    private final MarketingLogger logger = new MarketingLogger(FacebookSdk.getApplicationContext(), FacebookSdk.getApplicationId());

    private static class ScreenshotTaker implements Callable<String> {
        private WeakReference<View> rootView;

        public ScreenshotTaker(View view) {
            this.rootView = new WeakReference<>(view);
        }

        @Override // java.util.concurrent.Callable
        public String call() throws Exception {
            View view = this.rootView.get();
            if (view == null || view.getWidth() == 0 || view.getHeight() == 0) {
                return "";
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
            view.draw(new Canvas(bitmapCreateBitmap));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        }
    }

    public ViewIndexer(Activity activity) {
        this.activityReference = new WeakReference<>(activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static GraphRequest buildAppIndexingRequest(String str, AccessToken accessToken, String str2) {
        GraphRequest graphRequestNewPostRequest = null;
        if (str != null) {
            graphRequestNewPostRequest = GraphRequest.newPostRequest(accessToken, String.format(Locale.US, "%s/app_indexing", str2), null, null);
            Bundle parameters = graphRequestNewPostRequest.getParameters();
            if (parameters == null) {
                parameters = new Bundle();
            }
            parameters.putString(TREE_PARAM, str);
            parameters.putString("app_version", MarketingUtils.getAppVersion());
            parameters.putString("platform", "android");
            parameters.putString(Constants.DEVICE_SESSION_ID, CodelessActivityLifecycleTracker.getCurrentDeviceSessionID());
            graphRequestNewPostRequest.setParameters(parameters);
            graphRequestNewPostRequest.setCallback(new GraphRequest.Callback() { // from class: com.facebook.marketing.ViewIndexer.4
                @Override // com.facebook.GraphRequest.Callback
                public void onCompleted(GraphResponse graphResponse) {
                    Logger.log(LoggingBehavior.APP_EVENTS, ViewIndexer.TAG, "App index sent to FB!");
                }
            });
        }
        return graphRequestNewPostRequest;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendToServer(final String str, final String str2) {
        FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.marketing.ViewIndexer.3
            @Override // java.lang.Runnable
            public void run() {
                String applicationId = FacebookSdk.getApplicationId();
                String strMd5hash = Utility.md5hash(str);
                AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
                if (strMd5hash == null || !strMd5hash.equals(ViewIndexer.this.previousDigest)) {
                    ViewIndexer.this.logger.logIndexingStart(str2);
                    GraphRequest graphRequestBuildAppIndexingRequest = ViewIndexer.buildAppIndexingRequest(str, currentAccessToken, applicationId);
                    if (graphRequestBuildAppIndexingRequest != null) {
                        GraphResponse graphResponseExecuteAndWait = graphRequestBuildAppIndexingRequest.executeAndWait();
                        try {
                            JSONObject jSONObject = graphResponseExecuteAndWait.getJSONObject();
                            if (jSONObject == null) {
                                Log.e(ViewIndexer.TAG, "Error sending UI component tree to Facebook: " + graphResponseExecuteAndWait.getError());
                                return;
                            }
                            if (jSONObject.has("success") && jSONObject.getString("success") == "true") {
                                Logger.log(LoggingBehavior.APP_EVENTS, ViewIndexer.TAG, "Successfully send UI component tree to server");
                                ViewIndexer.this.previousDigest = strMd5hash;
                                ViewIndexer.this.logger.logIndexingComplete(str2);
                            }
                            if (jSONObject.has(Constants.APP_INDEXING_ENABLED)) {
                                CodelessActivityLifecycleTracker.updateAppIndexing(Boolean.valueOf(jSONObject.getBoolean(Constants.APP_INDEXING_ENABLED)));
                            }
                        } catch (JSONException e) {
                            Log.e(ViewIndexer.TAG, "Error decoding server response.", e);
                        }
                    }
                }
            }
        });
    }

    public void schedule() {
        final Activity activity = this.activityReference.get();
        if (activity == null) {
            return;
        }
        final String simpleName = activity.getClass().getSimpleName();
        FacebookSdk.getApplicationId();
        final TimerTask timerTask = new TimerTask() { // from class: com.facebook.marketing.ViewIndexer.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                try {
                    View rootView = activity.getWindow().getDecorView().getRootView();
                    if (CodelessActivityLifecycleTracker.getIsAppIndexingEnabled()) {
                        FutureTask futureTask = new FutureTask(new ScreenshotTaker(rootView));
                        ViewIndexer.this.uiThreadHandler.post(futureTask);
                        String str = "";
                        try {
                            str = (String) futureTask.get(1L, TimeUnit.SECONDS);
                        } catch (Exception e) {
                            Log.e(ViewIndexer.TAG, "Failed to take screenshot.", e);
                        }
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("screenname", simpleName);
                            jSONObject.put("screenshot", str);
                            JSONArray jSONArray = new JSONArray();
                            jSONArray.put(ViewHierarchy.getDictionaryOfView(rootView));
                            jSONObject.put("view", jSONArray);
                        } catch (JSONException e2) {
                            Log.e(ViewIndexer.TAG, "Failed to create JSONObject");
                        }
                        ViewIndexer.this.sendToServer(jSONObject.toString(), simpleName);
                    }
                } catch (Exception e3) {
                    Log.e(ViewIndexer.TAG, "UI Component tree indexing failure!", e3);
                }
            }
        };
        FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.marketing.ViewIndexer.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (ViewIndexer.this.indexingTimer != null) {
                        ViewIndexer.this.indexingTimer.cancel();
                    }
                    ViewIndexer.this.previousDigest = null;
                    ViewIndexer.this.indexingTimer = new Timer();
                    ViewIndexer.this.indexingTimer.scheduleAtFixedRate(timerTask, 0L, 1000L);
                } catch (Exception e) {
                    Log.e(ViewIndexer.TAG, "Error scheduling indexing job", e);
                }
            }
        });
    }

    public void unschedule() {
        Activity activity = this.activityReference.get();
        if (activity == null || this.indexingTimer == null) {
            return;
        }
        try {
            this.indexingTimer.cancel();
            this.indexingTimer = null;
            if (CodelessActivityLifecycleTracker.getIsAppIndexingEnabled()) {
                this.logger.logIndexingCancelled(activity.getClass().getCanonicalName());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error unscheduling indexing job", e);
        }
    }
}
