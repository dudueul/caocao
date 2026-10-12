package kr.co.nexon.npaccount.jnisupport;

import android.app.Activity;
import com.nexon.core.locale.NXLocale;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.util.NXJsonUtil;
import java.util.List;
import java.util.Map;
import kr.co.nexon.mdev.android.permission.NXPRequestPermissionInfo;
import kr.co.nexon.mdev.android.push.local.NPNotificationData;
import kr.co.nexon.npaccount.NPAccount;
import kr.co.nexon.npaccount.auth.result.NXToyRuntimePermissionResultExt;
import kr.co.nexon.npaccount.billing.NXPPaymentInfo;
import kr.co.nexon.npaccount.listener.NPBannerListener;
import kr.co.nexon.npaccount.listener.NPCloseListener;
import kr.co.nexon.npaccount.listener.NPEndingBannerListener;
import kr.co.nexon.npaccount.listener.NPGCMListener;
import kr.co.nexon.npaccount.listener.NPPlateListener;
import kr.co.nexon.npaccount.listener.NPRuntimePermissionListener;
import kr.co.nexon.npaccount.push.NXPPushPolicies;
import kr.co.nexon.npaccount.push.result.v1.NXToyOffPushResult;
import kr.co.nexon.npaccount.push.result.v1.NXToyOnPushResult;
import kr.co.nexon.npaccount.push.result.v1.NXToyPushStatusResult;
import kr.co.nexon.npaccount.setting.NPOptions;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPAccountJNISupport {
    public static void NXLogin(final NPAccount nPAccount, final Activity activity, final String str, final String str2, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.8
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.NXLogin(activity, str, str2, nPListener);
            }
        });
    }

    public static void billingPayment(final NPAccount nPAccount, final Activity activity, final String str, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.62
            @Override // java.lang.Runnable
            public void run() {
                try {
                    nPAccount.billingPayment((NXPPaymentInfo) NXJsonUtil.fromObject(str, NXPPaymentInfo.class), activity, nPListener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void billingRequestProducts(NPAccount nPAccount, String str, NPListener nPListener) {
        List<String> list = null;
        try {
            list = (List) NXJsonUtil.fromObject(str, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        nPAccount.billingRequestProducts(list, nPListener);
    }

    public static void billingRestore(final NPAccount nPAccount, final Activity activity, final boolean z, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.63
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.billingRestore(activity, z, nPListener);
            }
        });
    }

    public static void billingRestore(NPAccount nPAccount, NPListener nPListener) {
        nPAccount.billingRestore(nPListener);
    }

    public static void cancelAllLocalPush(final NPAccount nPAccount, final Activity activity) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.26
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.cancelAllLocalPush(activity);
            }
        });
    }

    public static void cancelLocalPush(final NPAccount nPAccount, final Activity activity, final int i) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.25
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.cancelLocalPush(activity, i);
            }
        });
    }

    public static void connectGamePlatform(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.40
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.connectGamePlatform(activity, nPListener);
            }
        });
    }

    public static void disconnectGamePlatform(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.41
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.disconnectGamePlatform(activity, nPListener);
            }
        });
    }

    public static void dispatchLocalPush(final NPAccount nPAccount, final Activity activity, final String str) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.24
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.dispatchLocalPush(activity, (NPNotificationData) NXJsonUtil.fromObject(str, NPNotificationData.class));
            }
        });
    }

    public static void enterToy(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.57
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.enterToy(activity, nPListener);
            }
        });
    }

    public static void fbActivateApp(NPAccount nPAccount) {
        nPAccount.fbActivateApp();
    }

    public static void fbAddPermission(final NPAccount nPAccount, final Activity activity, final String str, final int i, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.74
            @Override // java.lang.Runnable
            public void run() {
                List<String> list = null;
                try {
                    list = (List) NXJsonUtil.fromObject(str, List.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                nPAccount.fbAddPermission(activity, list, i, nPListener);
            }
        });
    }

    public static void fbDeactivateApp(NPAccount nPAccount) {
        nPAccount.fbDeactivateApp();
    }

    public static void fbFetchDeferredAppLink(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.64
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.fbFetchDeferredAppLink(activity, nPListener);
            }
        });
    }

    public static void fbGetFriends(NPAccount nPAccount, int i, NPListener nPListener) {
        nPAccount.fbGetFriends(i, nPListener);
    }

    public static void fbLogEvent(NPAccount nPAccount, String str) {
        nPAccount.fbLogEvent(str);
    }

    public static void fbLogEvent(NPAccount nPAccount, String str, double d) {
        nPAccount.fbLogEvent(str, d);
    }

    public static void fbLogEvent(NPAccount nPAccount, String str, double d, String str2) {
        nPAccount.fbLogEvent(str, d, str2);
    }

    public static void fbLogEvent(NPAccount nPAccount, String str, String str2) {
        nPAccount.fbLogEvent(str, str2);
    }

    public static void fbLogPurchase(NPAccount nPAccount, double d, String str, String str2) {
        nPAccount.fbLogPurchase(Double.valueOf(d), str, str2);
    }

    public static void fbSetIsDebugEnabled(NPAccount nPAccount, boolean z) {
        nPAccount.fbSetIsDebugEnabled(z);
    }

    public static void fbShare(NPAccount nPAccount, Activity activity, String str, String str2, String str3, String str4, NPListener nPListener) {
        nPAccount.fbShare(activity, str, str2, str3, str4, nPListener);
    }

    public static void getAdvertisingId(NPAccount nPAccount, NPListener nPListener) {
        nPAccount.getAdvertisingId(nPListener);
    }

    public static void getBanners(NPAccount nPAccount, int i, NPListener nPListener) {
        nPAccount.getBanners(i, nPListener);
    }

    public static int getCountry(NPAccount nPAccount) {
        return nPAccount.getCountry().getCountryCodeNumber();
    }

    public static String getCountryLetterCode(NPAccount nPAccount) {
        return nPAccount.getCountryLetterCode();
    }

    public static void getFriends(NPAccount nPAccount, int i, int i2, String str, NPListener nPListener) {
        nPAccount.getFriends(i, i2, str, nPListener);
    }

    public static void getFriends(NPAccount nPAccount, int i, String str, NPListener nPListener) {
        nPAccount.getFriends(i, str, nPListener);
    }

    public static int getLocale(NPAccount nPAccount) {
        return nPAccount.getLocale().getLocaleCodeNumber();
    }

    public static int getLoginType(NPAccount nPAccount) {
        return nPAccount.getLoginType();
    }

    public static void getNexonSN(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.9
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.getNexonSN(activity, nPListener);
            }
        });
    }

    public static void getNexonSN(final NPAccount nPAccount, final Activity activity, final boolean z, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.10
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.getNexonSN(activity, z, nPListener);
            }
        });
    }

    public static void getNexonSNByNaverChannel(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.11
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.getNexonSNByNaverChannel(activity, nPListener);
            }
        });
    }

    public static String getOptions(NPAccount nPAccount) {
        return NXJsonUtil.toJsonString(nPAccount.getOptions());
    }

    public static void getPlayerStats(NPAccount nPAccount, boolean z, NPListener nPListener) {
        nPAccount.getPlayerStats(z, nPListener);
    }

    public static void getPromotion(final NPAccount nPAccount, final Activity activity, final String str, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.67
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.getPromotion(activity, str, nPListener);
            }
        });
    }

    public static void getPromotion(final NPAccount nPAccount, final Activity activity, final String str, final boolean z, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.68
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.getPromotion(activity, str, z, nPListener);
            }
        });
    }

    public static void getPushPolicy(NPAccount nPAccount, NPListener nPListener) {
        nPAccount.getPushPolicy(nPListener);
    }

    public static void getServiceInfo(NPAccount nPAccount, NPListener nPListener) {
        nPAccount.getServiceInfo(nPListener);
    }

    public static void getSmsEnabled(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.75
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.getSmsEnabled(activity, nPListener);
            }
        });
    }

    public static void getSnsConnectionStatus(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.49
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.getSnsConnectionStatus(activity, nPListener);
            }
        });
    }

    public static void getSnsTokenList(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.51
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.getSnsTokenList(activity, nPListener);
            }
        });
    }

    public static void getSnsUserInfo(final NPAccount nPAccount, final Activity activity, final int i, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.50
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.getSnsUserInfo(i, activity, nPListener);
            }
        });
    }

    public static String getUUID(NPAccount nPAccount) {
        return nPAccount.getUUID();
    }

    public static void getUserInfo(NPAccount nPAccount, NPListener nPListener) {
        nPAccount.getUserInfo(nPListener);
    }

    public static void incrementAchievement(final NPAccount nPAccount, final Activity activity, final String str, final int i) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.32
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.incrementAchievement(activity, str, i);
            }
        });
    }

    public static void incrementAchievementImmediate(final NPAccount nPAccount, final Activity activity, final String str, final int i, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.33
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.incrementAchievementImmediate(activity, str, i, nPListener);
            }
        });
    }

    public static boolean isAuthCrashError(NPAccount nPAccount, int i) {
        return nPAccount.isAuthCrashError(i);
    }

    public static boolean isAvailableOfferwall(NPAccount nPAccount) {
        return nPAccount.isAvailableOfferwall();
    }

    public static boolean isEnableGamePlatform(NPAccount nPAccount) {
        return nPAccount.isEnableGamePlatform();
    }

    public static void loadAchievementData(final NPAccount nPAccount, final Activity activity, final boolean z, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.34
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.loadAchievementData(activity, z, nPListener);
            }
        });
    }

    public static void loadCurrentPlayerLeaderboardScore(final NPAccount nPAccount, final Activity activity, final String str, final int i, final int i2, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.39
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.loadCurrentPlayerLeaderboardScore(activity, str, i, i2, nPListener);
            }
        });
    }

    public static void loadLocatedCountry(NPAccount nPAccount, NPListener nPListener) {
        nPAccount.loadLocatedCountry(nPListener);
    }

    public static void login(final NPAccount nPAccount, final Activity activity, final int i, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.3
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.login(activity, i, nPListener);
            }
        });
    }

    public static void login(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.2
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.login(activity, nPListener);
            }
        });
    }

    public static void loginByLastNexonSN(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.12
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.loginByLastNexonSN(activity, nPListener);
            }
        });
    }

    public static void loginForKakao(final NPAccount nPAccount, final Activity activity, final String str, final String str2, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.4
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.loginForKakao(activity, str, str2, nPListener);
            }
        });
    }

    public static void logout(NPAccount nPAccount, NPListener nPListener) {
        nPAccount.logout(nPListener);
    }

    public static void logoutGamePlatform(final NPAccount nPAccount, Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.42
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.logoutGamePlatform(nPListener);
            }
        });
    }

    public static void pushInit(final NPAccount nPAccount, final Activity activity, final NPGCMListener nPGCMListener, final boolean z, final String str) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.1
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.gcmInit(activity, nPGCMListener, z, str);
            }
        });
    }

    public static void recoverUser(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.5
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.recoverUser(activity, nPListener);
            }
        });
    }

    public static void registerPush(NPAccount nPAccount, NPListener nPListener) {
        nPAccount.registerPush(nPListener);
    }

    public static void requestPermissions(final NPAccount nPAccount, final Activity activity, final String str, final int i, final String str2, final NPRuntimePermissionListenerJNISupport nPRuntimePermissionListenerJNISupport) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.65
            @Override // java.lang.Runnable
            public void run() {
                String[] strArr = null;
                try {
                    List list = (List) NXJsonUtil.fromObject(str, List.class);
                    strArr = (String[]) list.toArray(new String[list.size()]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                nPAccount.requestPermissions(activity, strArr, i, str2, new NPRuntimePermissionListener() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.65.1
                    @Override // kr.co.nexon.npaccount.listener.NPRuntimePermissionListener
                    public void onResult(int i2, String[] strArr2, int[] iArr) {
                        NXToyRuntimePermissionResultExt nXToyRuntimePermissionResultExt = new NXToyRuntimePermissionResultExt();
                        nXToyRuntimePermissionResultExt.result.grantResults = iArr;
                        nXToyRuntimePermissionResultExt.result.permissions = strArr2;
                        nXToyRuntimePermissionResultExt.result.requestCode = i2;
                        nPRuntimePermissionListenerJNISupport.onResult(nXToyRuntimePermissionResultExt);
                    }
                });
            }
        });
    }

    public static void requestPermissions(final NPAccount nPAccount, final Activity activity, final String str, final int i, final NPRuntimePermissionListenerJNISupport nPRuntimePermissionListenerJNISupport) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.66
            @Override // java.lang.Runnable
            public void run() {
                NXPRequestPermissionInfo nXPRequestPermissionInfo = null;
                try {
                    nXPRequestPermissionInfo = (NXPRequestPermissionInfo) NXJsonUtil.fromObject(str, NXPRequestPermissionInfo.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                nPAccount.requestPermissions(activity, nXPRequestPermissionInfo, i, new NPRuntimePermissionListener() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.66.1
                    @Override // kr.co.nexon.npaccount.listener.NPRuntimePermissionListener
                    public void onResult(int i2, String[] strArr, int[] iArr) {
                        NXToyRuntimePermissionResultExt nXToyRuntimePermissionResultExt = new NXToyRuntimePermissionResultExt();
                        nXToyRuntimePermissionResultExt.result.grantResults = iArr;
                        nXToyRuntimePermissionResultExt.result.permissions = strArr;
                        nXToyRuntimePermissionResultExt.result.requestCode = i2;
                        nPRuntimePermissionListenerJNISupport.onResult(nXToyRuntimePermissionResultExt);
                    }
                });
            }
        });
    }

    public static void resolveAlreadyLoginedUser(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.56
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.resolveAlreadyLoginedUser(activity, nPListener);
            }
        });
    }

    public static void screenCapture(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.43
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.screenCapture(activity, nPListener);
            }
        });
    }

    public static boolean sendErrorLog(NPAccount nPAccount, int i, String str) {
        return nPAccount.sendErrorLog(i, str);
    }

    public static boolean sendFunnel(NPAccount nPAccount, String str) {
        return nPAccount.sendFunnel(str);
    }

    public static boolean sendFunnel(NPAccount nPAccount, String str, String str2) {
        return nPAccount.sendFunnel(str, str2);
    }

    public static boolean sendLog(NPAccount nPAccount, String str, String str2) {
        return nPAccount.sendLog(str, str2);
    }

    public static boolean sendNXLog(NPAccount nPAccount, String str, String str2) {
        return nPAccount.sendNXLog(str, str2);
    }

    public static boolean sendOnlyOnceLog(NPAccount nPAccount, String str, String str2) {
        return nPAccount.sendOnlyOnceLog(str, str2);
    }

    public static boolean sendStageLog(NPAccount nPAccount, int i, String str) {
        return nPAccount.sendStageLog(i, str);
    }

    public static void setCountry(NPAccount nPAccount, int i) {
        nPAccount.setCountry(NXLocale.getCountryFromCodeNumber(i));
    }

    public static void setDisableLoginTypes(NPAccount nPAccount, int[] iArr) {
    }

    public static void setLocale(NPAccount nPAccount, int i) {
        nPAccount.setLocale(NXLocale.getLocaleFromCodeNumber(i));
    }

    public static void setNexonAnalyticsUserInfo(NPAccount nPAccount, String str, String str2) {
        nPAccount.setNexonAnalyticsUserInfo(str, str2);
    }

    public static void setOptions(NPAccount nPAccount, String str) {
        NPOptions nPOptions = null;
        try {
            nPOptions = (NPOptions) NXJsonUtil.fromObject(str, NPOptions.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        nPAccount.setOptions(nPOptions);
    }

    public static void setPushAgreement(NPAccount nPAccount, int i, NPListener nPListener) {
        nPAccount.setPushAgreement(i, nPListener);
    }

    public static void setPushPolicy(NPAccount nPAccount, String str, NPListener nPListener) {
        nPAccount.setPushPolicy((NXPPushPolicies) NXJsonUtil.fromObject(str, NXPPushPolicies.class), nPListener);
    }

    public static void setStepsAchievement(final NPAccount nPAccount, final Activity activity, final String str, final int i) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.28
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.setStepsAchievement(activity, str, i);
            }
        });
    }

    public static void setStepsAchievementImmediate(final NPAccount nPAccount, final Activity activity, final String str, final int i, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.29
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.setStepsAchievementImmediate(activity, str, i, nPListener);
            }
        });
    }

    public static void setupSecondPassword(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.78
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.setupSecondPassword(activity, nPListener);
            }
        });
    }

    public static void share(final NPAccount nPAccount, final Activity activity, final String str, final String str2, final String str3) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.6
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.commonShare(activity, str, str2, str3);
            }
        });
    }

    public static void showAccountMenu(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.70
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showAccountMenu(activity, nPListener);
            }
        });
    }

    public static void showAchievement(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.27
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showAchievement(activity, nPListener);
            }
        });
    }

    public static void showAllLeaderBoard(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.35
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showAllLeaderBoard(activity, nPListener);
            }
        });
    }

    public static void showBanner(final NPAccount nPAccount, final Activity activity, final int i, final NPBannerListener nPBannerListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.7
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showBanner(activity, i, nPBannerListener);
            }
        });
    }

    public static void showCustomerService(final NPAccount nPAccount, final Activity activity, String str) {
        Map map = null;
        try {
            map = (Map) NXJsonUtil.fromObject(str, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Map map2 = map;
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.21
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showCustomerService(activity, map2);
            }
        });
    }

    public static void showCustomerService(final NPAccount nPAccount, final Activity activity, String str, final NPPlateListener nPPlateListener) {
        Map map = null;
        try {
            map = (Map) NXJsonUtil.fromObject(str, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Map map2 = map;
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.22
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showCustomerService(activity, map2, nPPlateListener);
            }
        });
    }

    public static void showDataBackup(final NPAccount nPAccount, final Activity activity, final String str, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.53
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showDataBackup(activity, str, nPListener);
            }
        });
    }

    public static void showDataBackup(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.52
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showDataBackup(activity, nPListener);
            }
        });
    }

    public static void showDataRestore(final NPAccount nPAccount, final Activity activity, final String str, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.55
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showDataRestore(activity, str, nPListener);
            }
        });
    }

    public static void showDataRestore(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.54
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showDataRestore(activity, nPListener);
            }
        });
    }

    public static void showEndingBanner(final NPAccount nPAccount, final Activity activity, final NPEndingBannerListener nPEndingBannerListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.20
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showEndingBanner(activity, nPEndingBannerListener);
            }
        });
    }

    public static void showEventWeb(final NPAccount nPAccount, final Activity activity, final String str) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.19
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showEventWeb(activity, str);
            }
        });
    }

    public static void showFAQ(final NPAccount nPAccount, final Activity activity) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.15
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showFAQ(activity);
            }
        });
    }

    public static void showForum(final NPAccount nPAccount, final Activity activity, final int i) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.16
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showForum(activity, i);
            }
        });
    }

    public static void showHelpCenter(final NPAccount nPAccount, final Activity activity, String str) {
        Map map = null;
        try {
            map = (Map) NXJsonUtil.fromObject(str, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Map map2 = map;
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.23
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showHelpCenter(activity, map2);
            }
        });
    }

    public static void showLeaderBoard(final NPAccount nPAccount, final Activity activity, final String str, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.36
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showLeaderBoard(activity, str, nPListener);
            }
        });
    }

    public static void showNotice(final NPAccount nPAccount, final Activity activity) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.13
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showNotice(activity);
            }
        });
    }

    public static void showNotice(final NPAccount nPAccount, final Activity activity, final NPCloseListener nPCloseListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.14
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showNotice(activity, nPCloseListener);
            }
        });
    }

    public static void showOfferwall(final NPAccount nPAccount, final Activity activity, final NPCloseListener nPCloseListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.76
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showOfferwall(activity, nPCloseListener);
            }
        });
    }

    public static void showPlate(final NPAccount nPAccount, final Activity activity, final int i, String str, final NPCloseListener nPCloseListener, final NPListener nPListener, final NPListener nPListener2, final NPPlateListener nPPlateListener) {
        Map map = null;
        try {
            map = (Map) NXJsonUtil.fromObject(str, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Map map2 = map;
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.45
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showPlate(activity, i, map2, nPCloseListener, nPListener, nPListener2, nPPlateListener);
            }
        });
    }

    public static void showPlate(final NPAccount nPAccount, final Activity activity, final int i, String str, final NPListener nPListener) {
        Map map = null;
        try {
            map = (Map) NXJsonUtil.fromObject(str, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Map map2 = map;
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.44
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showPlate(activity, i, map2, new NPPlateListener() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.44.1
                    @Override // kr.co.nexon.npaccount.listener.NPPlateListener
                    public void onActionPerformedResult(NXToyResult nXToyResult) {
                        nPListener.onResult(nXToyResult);
                    }
                });
            }
        });
    }

    public static void showPromotion(final NPAccount nPAccount, final Activity activity, final String str, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.69
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showPromotion(activity, str, nPListener);
            }
        });
    }

    public static void showPushMenu(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.61
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showPushMenu(activity, nPListener);
            }
        });
    }

    public static void showPushNSmsSetting(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.60
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showPushNSmsSetting(activity, new NPListener() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.60.1
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult) {
                        NXToyPushStatusResult nXToyPushStatusResult = new NXToyPushStatusResult();
                        nXToyPushStatusResult.errorCode = nXToyResult.errorCode;
                        nXToyPushStatusResult.errorText = nXToyResult.errorText;
                        nXToyPushStatusResult.errorDetail = nXToyResult.errorDetail;
                        nXToyPushStatusResult.requestTag = nXToyResult.requestTag;
                        nXToyPushStatusResult.result.pushStatus = -1;
                        if (nXToyResult instanceof NXToyOnPushResult) {
                            NXToyOnPushResult nXToyOnPushResult = (NXToyOnPushResult) nXToyResult;
                            nXToyPushStatusResult.result.createdAt = nXToyOnPushResult.result.createdAt;
                            nXToyPushStatusResult.result.status = nXToyOnPushResult.result.status;
                            nXToyPushStatusResult.result.pushStatus = 1;
                        } else if (nXToyResult instanceof NXToyOffPushResult) {
                            NXToyOffPushResult nXToyOffPushResult = (NXToyOffPushResult) nXToyResult;
                            nXToyPushStatusResult.result.createdAt = nXToyOffPushResult.result.createdAt;
                            nXToyPushStatusResult.result.status = nXToyOffPushResult.result.status;
                            nXToyPushStatusResult.result.pushStatus = 0;
                        }
                        nPListener.onResult(nXToyPushStatusResult);
                    }
                });
            }
        });
    }

    public static void showSettlementFund(final NPAccount nPAccount, final Activity activity, final String str, final String str2, final String str3, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.59
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showSettlementFund(activity, str, str2, str3, nPListener);
            }
        });
    }

    public static void showSettlementFund(final NPAccount nPAccount, final Activity activity, final String str, final String str2, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.58
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showSettlementFund(activity, str, str2, nPListener);
            }
        });
    }

    public static void showTermsOfAgree(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.46
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showTermsOfAgree(activity, nPListener);
            }
        });
    }

    public static void showToday(final NPAccount nPAccount, final Activity activity, final int i) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.71
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showToday(activity, i);
            }
        });
    }

    public static void showToday(final NPAccount nPAccount, final Activity activity, final int i, final boolean z, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.72
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showToday(activity, i, z, nPListener);
            }
        });
    }

    public static void showWeb(final NPAccount nPAccount, final Activity activity, final String str, final String str2) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.17
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showWeb(activity, str, str2);
            }
        });
    }

    public static void showWeb(final NPAccount nPAccount, final Activity activity, final String str, final String str2, final String str3) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.18
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.showWeb(activity, str, str2, str3);
            }
        });
    }

    public static void snsConnect(final NPAccount nPAccount, final Activity activity, final int i, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.47
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.snsConnect(activity, i, nPListener);
            }
        });
    }

    public static void snsDisconnect(final NPAccount nPAccount, final Activity activity, final int i, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.48
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.snsDisconnect(activity, i, nPListener);
            }
        });
    }

    public static void submitScore(final NPAccount nPAccount, final Activity activity, final String str, final long j) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.37
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.submitScore(activity, str, j);
            }
        });
    }

    public static void submitScoreImmediate(final NPAccount nPAccount, final Activity activity, final String str, final long j, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.38
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.submitScoreImmediate(activity, str, j, nPListener);
            }
        });
    }

    public static void unlockAchievement(final NPAccount nPAccount, final Activity activity, final String str) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.30
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.unlockAchievement(activity, str);
            }
        });
    }

    public static void unlockAchievementImmediate(final NPAccount nPAccount, final Activity activity, final String str, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.31
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.unlockAchievementImmediate(activity, str, nPListener);
            }
        });
    }

    public static void unregisterPush(NPAccount nPAccount, NPListener nPListener) {
        nPAccount.unregisterPush(nPListener);
    }

    public static void unregisterService(NPAccount nPAccount, NPListener nPListener) {
        nPAccount.unregisterService(nPListener);
    }

    public static void verifySecondPassword(final NPAccount nPAccount, final Activity activity, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.77
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.verifySecondPassword(activity, nPListener);
            }
        });
    }

    public static void webShop(final NPAccount nPAccount, final Activity activity, final int i, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.jnisupport.NPAccountJNISupport.73
            @Override // java.lang.Runnable
            public void run() {
                nPAccount.webShop(activity, i, nPListener);
            }
        });
    }
}
