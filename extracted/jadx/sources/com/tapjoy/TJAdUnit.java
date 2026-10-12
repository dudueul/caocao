package com.tapjoy;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;
import com.naver.glink.android.sdk.c;
import com.tapjoy.internal.fm;
import com.tapjoy.internal.gf;
import com.tapjoy.internal.hn;
import com.tapjoy.internal.jq;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import net.daum.mf.oauth.impl.Constant;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TJAdUnit implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener {
    public static TJVideoListener a;
    private boolean A;
    private boolean B;
    private int D;
    private int E;
    private boolean F;
    private boolean G;
    private fm H;
    TJAdUnitJSBridge b;
    TJWebView c;
    TJWebView d;
    VideoView e;
    volatile boolean f;
    private TJAdUnitWebViewListener j;
    private TJAdUnitVideoListener k;
    private TJAdUnitActivity l;
    private MediaPlayer m;
    private int n;
    private boolean o;
    private boolean p;
    private boolean q;

    @Nullable
    private ScheduledFuture r;
    private AudioManager s;
    private int u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;
    private final Handler i = new Handler(Looper.getMainLooper());
    private int t = 0;
    private int C = -1;
    private final Runnable I = new Runnable() { // from class: com.tapjoy.TJAdUnit.1
        @Override // java.lang.Runnable
        public final void run() {
            int streamVolume = TJAdUnit.this.s.getStreamVolume(3);
            if (TJAdUnit.this.t != streamVolume) {
                TJAdUnit.this.t = streamVolume;
                TJAdUnit.c(TJAdUnit.this);
            }
        }
    };
    private final Runnable J = new Runnable() { // from class: com.tapjoy.TJAdUnit.8
        @Override // java.lang.Runnable
        public final void run() {
            if (TJAdUnit.this.e.getCurrentPosition() != 0) {
                if (!TJAdUnit.this.p) {
                    TJAdUnit.this.p = true;
                }
                TJAdUnit.this.b.onVideoStarted(TJAdUnit.this.n);
                TJAdUnit.this.K.run();
                return;
            }
            if (TJAdUnit.this.F) {
                TJAdUnit.n(TJAdUnit.this);
            } else {
                TJAdUnit.this.i.postDelayed(TJAdUnit.this.J, 200L);
            }
        }
    };
    private final Runnable K = new Runnable() { // from class: com.tapjoy.TJAdUnit.9
        @Override // java.lang.Runnable
        public final void run() {
            TJAdUnit.this.b.onVideoProgress(TJAdUnit.this.e.getCurrentPosition());
            TJAdUnit.this.i.postDelayed(TJAdUnit.this.K, 500L);
        }
    };
    WebViewClient g = new WebViewClient() { // from class: com.tapjoy.TJAdUnit.2
        private static WebResourceResponse a(TapjoyCachedAssetData tapjoyCachedAssetData) {
            if (tapjoyCachedAssetData == null) {
                return null;
            }
            try {
                return new WebResourceResponse(tapjoyCachedAssetData.getMimeType(), "UTF-8", new FileInputStream(tapjoyCachedAssetData.getLocalFilePath()));
            } catch (Exception e) {
                return null;
            }
        }

        private boolean a(String str) {
            if (!TJAdUnit.this.e() || !URLUtil.isValidUrl(str)) {
                if (TJAdUnit.this.l != null) {
                    TJAdUnit.this.l.showErrorDialog();
                }
                return true;
            }
            if (TJAdUnit.b(str)) {
                return false;
            }
            if (!TJAdUnit.this.b.allowRedirect) {
                if (!str.startsWith("javascript:") || Build.VERSION.SDK_INT < 19) {
                    return false;
                }
                try {
                    TJAdUnit.this.d.evaluateJavascript(str.replaceFirst("javascript:", ""), null);
                    return true;
                } catch (Exception e) {
                    TapjoyLog.e("TJAdUnit", "Exception in evaluateJavascript. Device not supported. " + e.toString());
                    return false;
                }
            }
            Uri uri = Uri.parse(str);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(uri);
            intent.addFlags(268435456);
            if (TJAdUnit.this.d.getContext() == null) {
                return false;
            }
            try {
                TJAdUnit.this.d.getContext().startActivity(intent);
                return true;
            } catch (Exception e2) {
                TapjoyLog.e("TJAdUnit", "Exception in loading URL. " + e2.getMessage());
                return false;
            }
        }

        @Override // android.webkit.WebViewClient
        public final void onLoadResource(WebView webView, String str) {
        }

        @Override // android.webkit.WebViewClient
        public final void onPageFinished(WebView webView, String str) {
            TapjoyLog.d("TJAdUnit", "onPageFinished: " + str);
            if (TJAdUnit.this.l != null) {
                TJAdUnit.this.l.setProgressSpinnerVisibility(false);
            }
            TJAdUnit.s(TJAdUnit.this);
            if (TJAdUnit.this.y) {
                TJAdUnit.u(TJAdUnit.this);
            }
            TJAdUnit.this.b.flushMessageQueue();
        }

        @Override // android.webkit.WebViewClient
        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            TapjoyLog.d("TJAdUnit", "onPageStarted: " + str);
            if (TJAdUnit.this.b != null) {
                TJAdUnit.this.b.allowRedirect = true;
                TJAdUnit.this.b.customClose = false;
                TJAdUnit.this.b.closeRequested = false;
                TJAdUnit.this.a();
            }
        }

        @Override // android.webkit.WebViewClient
        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            TapjoyLog.d("TJAdUnit", "error:" + str);
            if (TJAdUnit.this.l != null) {
                TJAdUnit.this.l.showErrorDialog();
            }
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            TapjoyCachedAssetData cachedDataForURL;
            WebResourceResponse webResourceResponseA;
            if (TapjoyCache.getInstance() == null || (cachedDataForURL = TapjoyCache.getInstance().getCachedDataForURL(str)) == null || (webResourceResponseA = a(cachedDataForURL)) == null) {
                return super.shouldInterceptRequest(webView, str);
            }
            TapjoyLog.d("TJAdUnit", "Reading request for " + str + " from cache -- localPath: " + cachedDataForURL.getLocalFilePath());
            return webResourceResponseA;
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(24)
        public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            return a(webResourceRequest.getUrl().toString());
        }

        @Override // android.webkit.WebViewClient
        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return a(str);
        }
    };
    WebChromeClient h = new WebChromeClient() { // from class: com.tapjoy.TJAdUnit.3
        @Override // android.webkit.WebChromeClient
        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            if (TJAdUnit.this.b.closeRequested) {
                String[] strArr = {"Uncaught", "uncaught", "Error", "error", "not defined"};
                if (TJAdUnit.this.l != null) {
                    for (int i = 0; i < 5; i++) {
                        if (consoleMessage.message().contains(strArr[i])) {
                            TJAdUnit.this.l.handleClose();
                            break;
                        }
                    }
                }
            }
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            TapjoyLog.d("TJAdUnit", str2);
            return false;
        }
    };

    public interface TJAdUnitVideoListener {
        void onVideoCompleted();

        void onVideoError(String str);

        void onVideoStart();
    }

    public interface TJAdUnitWebViewListener {
        void onClick();

        void onClosed();

        void onContentReady();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        TapjoyLog.d("TJAdUnit", "detachVolumeListener");
        if (this.r != null) {
            this.r.cancel(false);
            this.r = null;
        }
        this.s = null;
    }

    private static boolean a(int i) {
        return i == 0 || i == 8 || i == 6 || i == 11;
    }

    private int b() {
        TJAdUnitActivity tJAdUnitActivity = this.l;
        if (tJAdUnitActivity == null) {
            return -1;
        }
        int rotation = tJAdUnitActivity.getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        tJAdUnitActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.D = displayMetrics.widthPixels;
        this.E = displayMetrics.heightPixels;
        if (((rotation == 0 || rotation == 2) && this.E > this.D) || ((rotation == 1 || rotation == 3) && this.D > this.E)) {
            switch (rotation) {
                case 0:
                default:
                    return 1;
                case 1:
                    return 0;
                case 2:
                    return 9;
                case 3:
                    return 8;
            }
        }
        switch (rotation) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 8;
            case 3:
                return 9;
            default:
                TapjoyLog.w("TJAdUnit", "Unknown screen orientation. Defaulting to landscape.");
                return 0;
        }
    }

    private static boolean b(int i) {
        return i == 1 || i == 9 || i == 7 || i == 12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(String str) {
        try {
            String host = new URL(TapjoyConfig.TJC_SERVICE_URL).getHost();
            return (host != null && str.contains(host)) || str.contains(TapjoyConnectCore.getRedirectDomain()) || str.contains(TapjoyUtil.getRedirectDomain(TapjoyConnectCore.getPlacementURL()));
        } catch (MalformedURLException e) {
            return false;
        }
    }

    private void c() {
        this.i.removeCallbacks(this.J);
        this.i.removeCallbacks(this.K);
    }

    static /* synthetic */ void c(TJAdUnit tJAdUnit) {
        tJAdUnit.b.onVolumeChanged();
    }

    private void d() {
        if (this.H != null) {
            this.H.a(SettingsJsonConstants.ICON_PRERENDERED_KEY, Boolean.valueOf(this.A));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        try {
            if (this.d.getContext() != null) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.d.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
            }
        } catch (Exception e) {
            TapjoyLog.d("TJAdUnit", "Exception getting NetworkInfo: " + e.getLocalizedMessage());
        }
        return false;
    }

    static /* synthetic */ boolean n(TJAdUnit tJAdUnit) {
        tJAdUnit.G = true;
        return true;
    }

    static /* synthetic */ boolean o(TJAdUnit tJAdUnit) {
        tJAdUnit.o = false;
        return false;
    }

    static /* synthetic */ int p(TJAdUnit tJAdUnit) {
        tJAdUnit.n = 0;
        return 0;
    }

    static /* synthetic */ boolean s(TJAdUnit tJAdUnit) {
        tJAdUnit.B = true;
        return true;
    }

    static /* synthetic */ void u(TJAdUnit tJAdUnit) {
        tJAdUnit.b.display();
    }

    final void a(boolean z) {
        if (this.m == null) {
            this.v = z;
            return;
        }
        if (z) {
            this.m.setVolume(0.0f, 0.0f);
        } else {
            this.m.setVolume(1.0f, 1.0f);
        }
        if (this.w != z) {
            this.w = z;
            this.b.onVolumeChanged();
        }
    }

    public void attachVolumeListener(boolean z, int i) {
        TJAdUnitActivity tJAdUnitActivity;
        TapjoyLog.d("TJAdUnit", "attachVolumeListener: isAttached=" + z + "; interval=" + i);
        a();
        if (z && (tJAdUnitActivity = this.l) != null) {
            this.s = (AudioManager) tJAdUnitActivity.getSystemService("audio");
            this.t = this.s.getStreamVolume(3);
            this.u = this.s.getStreamMaxVolume(3);
            this.r = hn.a.scheduleWithFixedDelay(this.I, i, i, TimeUnit.MILLISECONDS);
        }
    }

    public void clearVideo(final TJAdUnitJSBridge.AdUnitAsyncTaskListner adUnitAsyncTaskListner, final boolean z) {
        if (this.e == null) {
            adUnitAsyncTaskListner.onComplete(false);
        } else {
            c();
            TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.TJAdUnit.10
                @Override // java.lang.Runnable
                public final void run() {
                    TJAdUnit.this.e.setVisibility(z ? 0 : 4);
                    TJAdUnit.this.e.stopPlayback();
                    TJAdUnit.this.p = false;
                    TJAdUnit.o(TJAdUnit.this);
                    TJAdUnit.p(TJAdUnit.this);
                    adUnitAsyncTaskListner.onComplete(true);
                }
            });
        }
    }

    public void closeRequested(boolean z) {
        this.b.closeRequested(Boolean.valueOf(z));
    }

    public void destroy() {
        this.b.destroy();
        c();
        if (this.c != null) {
            this.c.removeAllViews();
            this.c = null;
        }
        if (this.d != null) {
            this.d.removeAllViews();
            this.d = null;
        }
        this.f = false;
        this.y = false;
        setAdUnitActivity(null);
        a();
        this.m = null;
        if (this.j != null) {
            this.j.onClosed();
        }
        resetContentLoadState();
    }

    public void endAdContentTracking(String str, JSONObject jSONObject) {
        if (this.H != null) {
            d();
            this.H.b(str, jSONObject);
        }
    }

    public void fireContentReady() {
        if (this.j != null) {
            this.j.onContentReady();
        }
    }

    public void fireOnClick() {
        if (this.j != null) {
            this.j.onClick();
        }
    }

    public void fireOnVideoComplete() {
        TapjoyLog.v("TJAdUnit", "Firing onVideoComplete");
        if (getPublisherVideoListener() != null) {
            getPublisherVideoListener().onVideoComplete();
        }
        if (this.k != null) {
            this.k.onVideoCompleted();
        }
    }

    public void fireOnVideoError(String str) {
        TapjoyLog.e("TJAdUnit", "Firing onVideoError with error: " + str);
        if (getPublisherVideoListener() != null) {
            getPublisherVideoListener().onVideoError(3);
        }
        if (this.k != null) {
            this.k.onVideoError(str);
        }
    }

    public void fireOnVideoStart() {
        TapjoyLog.v("TJAdUnit", "Firing onVideoStart");
        if (getPublisherVideoListener() != null) {
            getPublisherVideoListener().onVideoStart();
        }
        if (this.k != null) {
            this.k.onVideoStart();
        }
    }

    public TJWebView getBackgroundWebView() {
        return this.c;
    }

    public boolean getCloseRequested() {
        return this.b.closeRequested;
    }

    public int getLockedOrientation() {
        return this.C;
    }

    public TJVideoListener getPublisherVideoListener() {
        return a;
    }

    public int getScreenHeight() {
        return this.E;
    }

    public String getScreenOrientationString() {
        return a(b()) ? TJAdUnitConstants.String.LANDSCAPE : TJAdUnitConstants.String.PORTRAIT;
    }

    public int getScreenWidth() {
        return this.D;
    }

    public int getVideoSeekTime() {
        return this.n;
    }

    public VideoView getVideoView() {
        return this.e;
    }

    public float getVolume() {
        return this.t / this.u;
    }

    public TJWebView getWebView() {
        return this.d;
    }

    public boolean hasCalledLoad() {
        return this.z;
    }

    public void invokeBridgeCallback(String str, Object... objArr) {
        if (this.b == null || str == null) {
            return;
        }
        this.b.invokeJSCallback(str, objArr);
    }

    public boolean isLockedOrientation() {
        return this.x;
    }

    public boolean isMuted() {
        return this.w;
    }

    public boolean isPrerendered() {
        return this.A;
    }

    public boolean isVideoComplete() {
        return this.q;
    }

    public void load(final TJPlacementData tJPlacementData, final boolean z, final Context context) {
        this.z = false;
        TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.TJAdUnit.4
            @Override // java.lang.Runnable
            public final void run() {
                TJAdUnit tJAdUnit = TJAdUnit.this;
                Context context2 = context;
                if (Looper.myLooper() == Looper.getMainLooper() && !tJAdUnit.f && context2 != null) {
                    TapjoyLog.d("TJAdUnit", "Constructing ad unit");
                    tJAdUnit.f = true;
                    tJAdUnit.c = new TJWebView(context2);
                    tJAdUnit.c.loadDataWithBaseURL(null, "<!DOCTYPE html><html><head><title>Tapjoy Background Webview</title></head></html>", "text/html", Constant.DEFAULT_ENCODING, null);
                    tJAdUnit.d = new TJWebView(context2);
                    tJAdUnit.d.setWebViewClient(tJAdUnit.g);
                    tJAdUnit.d.setWebChromeClient(tJAdUnit.h);
                    tJAdUnit.e = new VideoView(context2);
                    tJAdUnit.e.setOnCompletionListener(tJAdUnit);
                    tJAdUnit.e.setOnErrorListener(tJAdUnit);
                    tJAdUnit.e.setOnPreparedListener(tJAdUnit);
                    tJAdUnit.e.setVisibility(4);
                    tJAdUnit.b = new TJAdUnitJSBridge(context2, tJAdUnit);
                    if (context2 instanceof TJAdUnitActivity) {
                        tJAdUnit.setAdUnitActivity((TJAdUnitActivity) context2);
                    }
                }
                if (tJAdUnit.f) {
                    TapjoyLog.i("TJAdUnit", "Loading ad unit content");
                    TJAdUnit.this.z = true;
                    if (jq.c(tJPlacementData.getRedirectURL())) {
                        if (tJPlacementData.getBaseURL() == null || tJPlacementData.getHttpResponse() == null) {
                            TapjoyLog.e("TJAdUnit", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error loading ad unit content"));
                            TJAdUnit.this.z = false;
                        } else {
                            TJAdUnit.this.d.loadDataWithBaseURL(tJPlacementData.getBaseURL(), tJPlacementData.getHttpResponse(), "text/html", Constant.DEFAULT_ENCODING, null);
                        }
                    } else if (tJPlacementData.isPreloadDisabled()) {
                        TJAdUnit.this.d.postUrl(tJPlacementData.getRedirectURL(), null);
                    } else {
                        TJAdUnit.this.d.loadUrl(tJPlacementData.getRedirectURL());
                    }
                    TJAdUnit.this.A = TJAdUnit.this.z && z;
                }
            }
        });
    }

    public void loadVideoUrl(final String str, final TJAdUnitJSBridge.AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.TJAdUnit.7
            @Override // java.lang.Runnable
            public final void run() {
                if (TJAdUnit.this.e == null) {
                    adUnitAsyncTaskListner.onComplete(false);
                    return;
                }
                TapjoyLog.i("TJAdUnit", "loadVideoUrl: " + str);
                TJAdUnit.this.e.setVideoPath(str);
                TJAdUnit.this.e.setVisibility(0);
                TJAdUnit.this.e.seekTo(0);
                adUnitAsyncTaskListner.onComplete(true);
            }
        });
    }

    public void notifyOrientationChanged() {
        this.b.notifyOrientationChanged(getScreenOrientationString(), this.D, this.E);
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        TapjoyLog.i("TJAdUnit", "video -- onCompletion");
        c();
        this.q = true;
        if (!this.o) {
            this.b.onVideoCompletion();
        }
        this.o = false;
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str;
        String str2;
        TapjoyLog.e("TJAdUnit", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error encountered when instantiating the VideoView: " + i + " - " + i2));
        this.o = true;
        c();
        switch (i) {
            case 100:
                str = "MEDIA_ERROR_SERVER_DIED";
                break;
            default:
                str = "MEDIA_ERROR_UNKNOWN";
                break;
        }
        String str3 = str + " -- ";
        switch (i2) {
            case -1010:
                str2 = str3 + "MEDIA_ERROR_UNSUPPORTED";
                break;
            case -1007:
                str2 = str3 + "MEDIA_ERROR_MALFORMED";
                break;
            case -1004:
                str2 = str3 + "MEDIA_ERROR_IO";
                break;
            case -110:
                str2 = str3 + "MEDIA_ERROR_TIMED_OUT";
                break;
            default:
                str2 = str3 + "MEDIA_ERROR_EXTRA_UNKNOWN";
                break;
        }
        this.b.onVideoError(str2);
        return i == 1 || i2 == -1004;
    }

    @Override // android.media.MediaPlayer.OnInfoListener
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = "";
        switch (i) {
            case 3:
                str = "MEDIA_INFO_VIDEO_RENDERING_START";
                break;
            case c.c /* 700 */:
                str = "MEDIA_INFO_VIDEO_TRACK_LAGGING";
                break;
            case 701:
                str = "MEDIA_INFO_BUFFERING_START";
                break;
            case 702:
                str = "MEDIA_INFO_BUFFERING_END";
                break;
            case 801:
                str = "MEDIA_INFO_NOT_SEEKABLE";
                break;
        }
        this.b.onVideoInfo(str);
        return false;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        TapjoyLog.i("TJAdUnit", "video -- onPrepared");
        final int duration = this.e.getDuration();
        final int measuredWidth = this.e.getMeasuredWidth();
        final int measuredHeight = this.e.getMeasuredHeight();
        this.m = mediaPlayer;
        if (this.v) {
            a(this.v);
        }
        if (this.n <= 0 || this.e.getCurrentPosition() == this.n) {
            this.b.onVideoReady(duration, measuredWidth, measuredHeight);
        } else {
            this.m.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() { // from class: com.tapjoy.TJAdUnit.11
                @Override // android.media.MediaPlayer.OnSeekCompleteListener
                public final void onSeekComplete(MediaPlayer mediaPlayer2) {
                    TJAdUnit.this.b.onVideoReady(duration, measuredWidth, measuredHeight);
                }
            });
        }
        this.m.setOnInfoListener(this);
    }

    public void pause() {
        this.F = true;
        this.b.setEnabled(false);
        pauseVideo();
    }

    public boolean pauseVideo() {
        c();
        if (this.e == null || !this.e.isPlaying()) {
            return false;
        }
        this.e.pause();
        this.n = this.e.getCurrentPosition();
        TapjoyLog.i("TJAdUnit", "Video paused at: " + this.n);
        this.b.onVideoPaused(this.n);
        return true;
    }

    public boolean playVideo() {
        TapjoyLog.i("TJAdUnit", "playVideo");
        if (this.e == null) {
            return false;
        }
        this.e.start();
        this.q = false;
        this.i.postDelayed(this.J, 200L);
        return true;
    }

    public boolean preload(TJPlacementData tJPlacementData, Context context) {
        if (this.z || !tJPlacementData.isPrerenderingRequested() || !TJPlacementManager.canPreRenderPlacement() || TapjoyConnectCore.isViewOpen()) {
            fireContentReady();
            return false;
        }
        TapjoyLog.i("TJAdUnit", "Pre-rendering ad unit for placement: " + tJPlacementData.getPlacementName());
        TJPlacementManager.incrementPlacementPreRenderCount();
        load(tJPlacementData, true, context);
        return true;
    }

    public void resetContentLoadState() {
        this.z = false;
        this.B = false;
        this.A = false;
        this.C = -1;
        this.x = false;
        this.v = false;
    }

    public void resume(TJAdUnitSaveStateData tJAdUnitSaveStateData) {
        if (this.b.didLaunchOtherActivity) {
            TapjoyLog.d("TJAdUnit", "onResume bridge.didLaunchOtherActivity callbackID: " + this.b.otherActivityCallbackID);
            this.b.invokeJSCallback(this.b.otherActivityCallbackID, Boolean.TRUE);
            this.b.didLaunchOtherActivity = false;
        }
        this.F = false;
        this.b.setEnabled(true);
        if (tJAdUnitSaveStateData != null) {
            this.n = tJAdUnitSaveStateData.seekTime;
            this.e.seekTo(this.n);
            if (this.m != null) {
                this.v = tJAdUnitSaveStateData.isVideoMuted;
            }
        }
        if (this.G) {
            this.G = false;
            this.i.postDelayed(this.J, 200L);
        }
    }

    public void sendAdContentTracking(String str, JSONObject jSONObject) {
        if (this.H != null) {
            d();
            fm fmVar = this.H;
            Map mapA = fm.a(jSONObject);
            gf.e(str).a(fmVar.a).a(mapA).b(fm.b(jSONObject)).c();
        }
    }

    public void setAdContentTracker(fm fmVar) {
        this.H = fmVar;
    }

    public void setAdUnitActivity(TJAdUnitActivity tJAdUnitActivity) {
        this.l = tJAdUnitActivity;
        if (this.b != null) {
            this.b.setAdUnitActivity(this.l);
        }
    }

    public void setBackgroundColor(final String str, final TJAdUnitJSBridge.AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.TJAdUnit.5
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    TapjoyLog.d("TJAdUnit", "setBackgroundColor: " + str);
                    TJAdUnit.this.c.setBackgroundColor(Color.parseColor(str));
                    adUnitAsyncTaskListner.onComplete(true);
                } catch (Exception e) {
                    TapjoyLog.d("TJAdUnit", "Error setting background color. backgroundWebView: " + TJAdUnit.this.c + ", hexColor: " + str);
                    adUnitAsyncTaskListner.onComplete(false);
                }
            }
        });
    }

    public void setBackgroundContent(final String str, final TJAdUnitJSBridge.AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        TapjoyUtil.runOnMainThread(new Runnable() { // from class: com.tapjoy.TJAdUnit.6
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    TapjoyLog.d("TJAdUnit", "setBackgroundContent: " + str);
                    TJAdUnit.this.c.loadDataWithBaseURL(null, str, "text/html", Constant.DEFAULT_ENCODING, null);
                    adUnitAsyncTaskListner.onComplete(true);
                } catch (Exception e) {
                    TapjoyLog.d("TJAdUnit", "Error setting background content. backgroundWebView: " + TJAdUnit.this.c + ", content: " + str);
                    adUnitAsyncTaskListner.onComplete(false);
                }
            }
        });
    }

    public void setOrientation(int i) {
        TJAdUnitActivity tJAdUnitActivity = this.l;
        if (tJAdUnitActivity != null) {
            int iB = b();
            if (this.C != -1) {
                iB = this.C;
            }
            if ((a(iB) && a(i)) || (b(iB) && b(i))) {
                i = iB;
            }
            tJAdUnitActivity.setRequestedOrientation(i);
            this.C = i;
            this.x = true;
        }
    }

    public void setVideoListener(TJAdUnitVideoListener tJAdUnitVideoListener) {
        this.k = tJAdUnitVideoListener;
    }

    public void setVisible(boolean z) {
        this.b.notifyOrientationChanged(getScreenOrientationString(), this.D, this.E);
        this.y = z;
        if (this.y && this.B) {
            this.b.display();
        }
    }

    public void setWebViewListener(TJAdUnitWebViewListener tJAdUnitWebViewListener) {
        this.j = tJAdUnitWebViewListener;
    }

    public void startAdContentTracking(String str, JSONObject jSONObject) {
        if (this.H != null) {
            this.H.a(str, jSONObject);
        }
    }

    public void unsetOrientation() {
        TJAdUnitActivity tJAdUnitActivity = this.l;
        if (tJAdUnitActivity != null) {
            tJAdUnitActivity.setRequestedOrientation(-1);
        }
        this.C = -1;
        this.x = false;
    }
}
