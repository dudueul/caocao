package bolts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import com.facebook.GraphResponse;
import com.facebook.applinks.AppLinkData;
import com.facebook.internal.AnalyticsEvents;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class AppLinkNavigation {
    private static final String KEY_NAME_REFERER_APP_LINK = "referer_app_link";
    private static final String KEY_NAME_REFERER_APP_LINK_APP_NAME = "app_name";
    private static final String KEY_NAME_REFERER_APP_LINK_PACKAGE = "package";
    private static final String KEY_NAME_USER_AGENT = "user_agent";
    private static final String KEY_NAME_VERSION = "version";
    private static final String VERSION = "1.0";
    private static AppLinkResolver defaultResolver;
    private final AppLink appLink;
    private final Bundle appLinkData;
    private final Bundle extras;

    public enum NavigationResult {
        FAILED("failed", false),
        WEB(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB, true),
        APP("app", true);

        private String code;
        private boolean succeeded;

        NavigationResult(String str, boolean z) {
            this.code = str;
            this.succeeded = z;
        }

        public String getCode() {
            return this.code;
        }

        public boolean isSucceeded() {
            return this.succeeded;
        }
    }

    public AppLinkNavigation(AppLink appLink, Bundle bundle, Bundle bundle2) {
        if (appLink == null) {
            throw new IllegalArgumentException("appLink must not be null.");
        }
        bundle = bundle == null ? new Bundle() : bundle;
        bundle2 = bundle2 == null ? new Bundle() : bundle2;
        this.appLink = appLink;
        this.extras = bundle;
        this.appLinkData = bundle2;
    }

    private Bundle buildAppLinkDataForNavigation(Context context) {
        String string;
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        if (context != null) {
            String packageName = context.getPackageName();
            if (packageName != null) {
                bundle2.putString("package", packageName);
            }
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo != null && (string = context.getString(applicationInfo.labelRes)) != null) {
                bundle2.putString("app_name", string);
            }
        }
        bundle.putAll(getAppLinkData());
        bundle.putString("target_url", getAppLink().getSourceUrl().toString());
        bundle.putString("version", "1.0");
        bundle.putString(KEY_NAME_USER_AGENT, "Bolts Android 1.4.0");
        bundle.putBundle(KEY_NAME_REFERER_APP_LINK, bundle2);
        bundle.putBundle(AppLinkData.ARGUMENTS_EXTRAS_KEY, getExtras());
        return bundle;
    }

    public static AppLinkResolver getDefaultResolver() {
        return defaultResolver;
    }

    private JSONObject getJSONForBundle(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            jSONObject.put(str, getJSONValue(bundle.get(str)));
        }
        return jSONObject;
    }

    private Object getJSONValue(Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            return getJSONForBundle((Bundle) obj);
        }
        if (obj instanceof CharSequence) {
            return obj.toString();
        }
        if (obj instanceof List) {
            JSONArray jSONArray = new JSONArray();
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                jSONArray.put(getJSONValue(it.next()));
            }
            return jSONArray;
        }
        if (obj instanceof SparseArray) {
            JSONArray jSONArray2 = new JSONArray();
            SparseArray sparseArray = (SparseArray) obj;
            for (int i = 0; i < sparseArray.size(); i++) {
                jSONArray2.put(sparseArray.keyAt(i), getJSONValue(sparseArray.valueAt(i)));
            }
            return jSONArray2;
        }
        if (obj instanceof Character) {
            return obj.toString();
        }
        if (obj instanceof Boolean) {
            return obj;
        }
        if (obj instanceof Number) {
            return ((obj instanceof Double) || (obj instanceof Float)) ? Double.valueOf(((Number) obj).doubleValue()) : Long.valueOf(((Number) obj).longValue());
        }
        if (obj instanceof boolean[]) {
            JSONArray jSONArray3 = new JSONArray();
            for (boolean z : (boolean[]) obj) {
                jSONArray3.put(getJSONValue(Boolean.valueOf(z)));
            }
            return jSONArray3;
        }
        if (obj instanceof char[]) {
            JSONArray jSONArray4 = new JSONArray();
            for (char c : (char[]) obj) {
                jSONArray4.put(getJSONValue(Character.valueOf(c)));
            }
            return jSONArray4;
        }
        if (obj instanceof CharSequence[]) {
            JSONArray jSONArray5 = new JSONArray();
            for (CharSequence charSequence : (CharSequence[]) obj) {
                jSONArray5.put(getJSONValue(charSequence));
            }
            return jSONArray5;
        }
        if (obj instanceof double[]) {
            JSONArray jSONArray6 = new JSONArray();
            for (double d : (double[]) obj) {
                jSONArray6.put(getJSONValue(Double.valueOf(d)));
            }
            return jSONArray6;
        }
        if (obj instanceof float[]) {
            JSONArray jSONArray7 = new JSONArray();
            for (float f : (float[]) obj) {
                jSONArray7.put(getJSONValue(Float.valueOf(f)));
            }
            return jSONArray7;
        }
        if (obj instanceof int[]) {
            JSONArray jSONArray8 = new JSONArray();
            for (int i2 : (int[]) obj) {
                jSONArray8.put(getJSONValue(Integer.valueOf(i2)));
            }
            return jSONArray8;
        }
        if (obj instanceof long[]) {
            JSONArray jSONArray9 = new JSONArray();
            for (long j : (long[]) obj) {
                jSONArray9.put(getJSONValue(Long.valueOf(j)));
            }
            return jSONArray9;
        }
        if (obj instanceof short[]) {
            JSONArray jSONArray10 = new JSONArray();
            for (short s : (short[]) obj) {
                jSONArray10.put(getJSONValue(Short.valueOf(s)));
            }
            return jSONArray10;
        }
        if (!(obj instanceof String[])) {
            return null;
        }
        JSONArray jSONArray11 = new JSONArray();
        for (String str : (String[]) obj) {
            jSONArray11.put(getJSONValue(str));
        }
        return jSONArray11;
    }

    private static AppLinkResolver getResolver(Context context) {
        return getDefaultResolver() != null ? getDefaultResolver() : new WebViewAppLinkResolver(context);
    }

    public static NavigationResult navigate(Context context, AppLink appLink) {
        return new AppLinkNavigation(appLink, null, null).navigate(context);
    }

    public static Task<NavigationResult> navigateInBackground(Context context, Uri uri) {
        return navigateInBackground(context, uri, getResolver(context));
    }

    public static Task<NavigationResult> navigateInBackground(final Context context, Uri uri, AppLinkResolver appLinkResolver) {
        return appLinkResolver.getAppLinkFromUrlInBackground(uri).onSuccess((Continuation<AppLink, TContinuationResult>) new Continuation<AppLink, NavigationResult>() { // from class: bolts.AppLinkNavigation.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public NavigationResult then(Task<AppLink> task) throws Exception {
                return AppLinkNavigation.navigate(context, task.getResult());
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    public static Task<NavigationResult> navigateInBackground(Context context, String str) {
        return navigateInBackground(context, str, getResolver(context));
    }

    public static Task<NavigationResult> navigateInBackground(Context context, String str, AppLinkResolver appLinkResolver) {
        return navigateInBackground(context, Uri.parse(str), appLinkResolver);
    }

    public static Task<NavigationResult> navigateInBackground(Context context, URL url) {
        return navigateInBackground(context, url, getResolver(context));
    }

    public static Task<NavigationResult> navigateInBackground(Context context, URL url, AppLinkResolver appLinkResolver) {
        return navigateInBackground(context, Uri.parse(url.toString()), appLinkResolver);
    }

    private void sendAppLinkNavigateEventBroadcast(Context context, Intent intent, NavigationResult navigationResult, JSONException jSONException) {
        HashMap map = new HashMap();
        if (jSONException != null) {
            map.put("error", jSONException.getLocalizedMessage());
        }
        map.put(GraphResponse.SUCCESS_KEY, navigationResult.isSucceeded() ? "1" : "0");
        map.put("type", navigationResult.getCode());
        MeasurementEvent.sendBroadcastEvent(context, MeasurementEvent.APP_LINK_NAVIGATE_OUT_EVENT_NAME, intent, map);
    }

    public static void setDefaultResolver(AppLinkResolver appLinkResolver) {
        defaultResolver = appLinkResolver;
    }

    public AppLink getAppLink() {
        return this.appLink;
    }

    public Bundle getAppLinkData() {
        return this.appLinkData;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public NavigationResult navigate(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Bundle bundleBuildAppLinkDataForNavigation = buildAppLinkDataForNavigation(context);
        Intent intent = null;
        for (AppLink.Target target : getAppLink().getTargets()) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            if (target.getUrl() != null) {
                intent2.setData(target.getUrl());
            } else {
                intent2.setData(this.appLink.getSourceUrl());
            }
            intent2.setPackage(target.getPackageName());
            if (target.getClassName() != null) {
                intent2.setClassName(target.getPackageName(), target.getClassName());
            }
            intent2.putExtra("al_applink_data", bundleBuildAppLinkDataForNavigation);
            if (packageManager.resolveActivity(intent2, 65536) != null) {
                intent = intent2;
                break;
            }
        }
        Intent intent3 = null;
        NavigationResult navigationResult = NavigationResult.FAILED;
        if (intent != null) {
            intent3 = intent;
            navigationResult = NavigationResult.APP;
        } else {
            Uri webUrl = getAppLink().getWebUrl();
            if (webUrl != null) {
                try {
                    intent3 = new Intent("android.intent.action.VIEW", webUrl.buildUpon().appendQueryParameter("al_applink_data", getJSONForBundle(bundleBuildAppLinkDataForNavigation).toString()).build());
                    navigationResult = NavigationResult.WEB;
                } catch (JSONException e) {
                    sendAppLinkNavigateEventBroadcast(context, intent, NavigationResult.FAILED, e);
                    throw new RuntimeException(e);
                }
            }
        }
        sendAppLinkNavigateEventBroadcast(context, intent3, navigationResult, null);
        if (intent3 != null) {
            context.startActivity(intent3);
        }
        return navigationResult;
    }
}
