package com.naver.glink.android.sdk;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Keep;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.ui.tabs.Tab;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Keep
public class Glink {

    public interface OnClickAppSchemeBannerListener {
        void onClickAppSchemeBanner(String str);
    }

    public interface OnEndStreamingLiveListener {
        void onEndStreamingLive(int i, int i2);
    }

    public interface OnEndWatchingLiveListener {
        void onEndWatchingLive(int i);
    }

    public interface OnJoinedListener {
        void onJoined();
    }

    public interface OnLoggedInListener {
        void onLoggedIn(boolean z);
    }

    public interface OnPostedArticleListener {
        void onPostedArticle(int i, int i2, int i3);
    }

    public interface OnPostedCommentListener {
        void onPostedComment(int i);
    }

    public interface OnRecordFinishListener {
        void onRecordFinished(String str);
    }

    public interface OnSdkStartedListener {
        void onSdkStarted();
    }

    public interface OnSdkStoppedListener {
        void onSdkStopped();
    }

    public interface OnVotedListener {
        void onVoted(int i);
    }

    public interface OnWidgetScreenshotClickListener {
        void onScreenshotClick();
    }

    public static boolean checkPermission(Context context, String str) {
        return context != null && context.checkCallingOrSelfPermission(str) == 0;
    }

    public static int getAndroidVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static String getChannelCode() {
        return d.b().b();
    }

    public static void init(Context context, String str, String str2, int i) {
        d.a(context, str, str2, i);
        d.a(StartFrom.GAME);
        d.a(new com.naver.glink.android.sdk.ui.tabs.a.C0063a());
    }

    public static void initGlobal(Context context, String str, int i) {
        d.a(context, str, i);
        d.a(StartFrom.GAME);
        d.a(new com.naver.glink.android.sdk.ui.tabs.a.C0063a());
    }

    public static void initGlobal(Context context, String str, int i, int i2) {
        d.a(context, str, i, i2);
        d.a(StartFrom.GAME);
        d.a(new com.naver.glink.android.sdk.ui.tabs.a.C0063a());
    }

    public static boolean isShowGlink(Context context) {
        return d.f();
    }

    public static boolean isSupportAndroidVersion() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static void setChannelChangeable(Context context, boolean z) {
        p.b(context, z);
    }

    public static void setChannelCode(String str) {
        if (isShowGlink(d.r())) {
            return;
        }
        d.a();
        d.b().a(str);
    }

    public static void setOnClickAppSchemeBannerListener(OnClickAppSchemeBannerListener onClickAppSchemeBannerListener) {
        b.a(onClickAppSchemeBannerListener);
    }

    public static void setOnEndStreamingLiveListener(OnEndStreamingLiveListener onEndStreamingLiveListener) {
        b.a(onEndStreamingLiveListener);
    }

    public static void setOnEndWatchingLiveListener(OnEndWatchingLiveListener onEndWatchingLiveListener) {
        b.a(onEndWatchingLiveListener);
    }

    public static void setOnJoinedListener(OnJoinedListener onJoinedListener) {
        b.a(onJoinedListener);
    }

    public static void setOnPostedArticleListener(OnPostedArticleListener onPostedArticleListener) {
        b.a(onPostedArticleListener);
    }

    public static void setOnPostedCommentListener(OnPostedCommentListener onPostedCommentListener) {
        b.a(onPostedCommentListener);
    }

    public static void setOnRecordFinishListener(OnRecordFinishListener onRecordFinishListener) {
        b.a(onRecordFinishListener);
    }

    public static void setOnSdkStartedListener(OnSdkStartedListener onSdkStartedListener) {
        b.a(onSdkStartedListener);
    }

    public static void setOnSdkStoppedListener(OnSdkStoppedListener onSdkStoppedListener) {
        b.a(onSdkStoppedListener);
    }

    public static void setOnVotedListener(OnVotedListener onVotedListener) {
        b.a(onVotedListener);
    }

    public static void setOnWidgetScreenshotClickListener(OnWidgetScreenshotClickListener onWidgetScreenshotClickListener) {
        b.a(onWidgetScreenshotClickListener);
    }

    public static void setThemeColor(String str) {
        d.a(new com.naver.glink.android.sdk.configure.d(str, "#44484e"));
    }

    public static void setThemeColor(String str, String str2) {
        d.a(new com.naver.glink.android.sdk.configure.d(str, str2));
    }

    public static void setTransparentable(Context context, boolean z) {
        p.a(context, z);
    }

    public static void setUseScreenshot(Context context, boolean z) {
        p.e(context, z);
    }

    public static void setUseVideoRecord(Context context, boolean z) {
        p.d(context, z);
    }

    public static void setWidgetStartPosition(Context context, boolean z, int i) {
        p.a(context, z, i);
    }

    public static void showWidgetWhenUnloadSdk(Context context, boolean z) {
        p.c(context, z);
    }

    public static void startAllImages(Context context) {
        d.b(context);
    }

    public static void startAllVideos(Context context) {
        d.a(context);
    }

    public static void startArticle(Context context, int i) {
        d.a(context, i);
    }

    public static void startHome(Context context) {
        d.a(context, Tab.Type.BANNERS);
    }

    public static void startImageWrite(Context context, String str) {
        d.a(context, -1, null, null, str);
    }

    public static void startMenu(Context context, int i) {
        d.b(context, i);
    }

    public static void startVideoWrite(Context context, String str) {
        d.b(context, -1, null, null, str);
    }

    public static void startWidget(Context context) {
        d.f(context);
    }

    public static void startWrite(Context context) {
        d.a(context, -1, (String) null, (String) null);
    }

    public static void stop(Context context) {
        d.c(context);
    }

    public static void stopWidget(Context context) {
        d.g(context);
    }

    public static void syncGameUserId(Context context, String str) {
        f.a(context, str);
    }
}
