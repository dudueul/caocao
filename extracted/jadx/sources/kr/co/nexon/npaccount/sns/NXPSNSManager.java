package kr.co.nexon.npaccount.sns;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.nexon.core.android.NXPApplicationConfigManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToyUserInfo;
import com.nexon.nexonanalyticssdk.NxLogInfo;
import com.nexon.npaccount.R;
import com.tapjoy.TJAdUnitConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kr.co.nexon.android.sns.NPAuthFriendsListener;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.NPAuthUser;
import kr.co.nexon.android.sns.facebook.NPFacebook;
import kr.co.nexon.android.sns.twitter.NPTwitter;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.NXPProviderManager;
import kr.co.nexon.npaccount.auth.request.NXToyGetNPSNsRequest;
import kr.co.nexon.npaccount.auth.request.NXToyLinkMemIDtoNPSNRequest;
import kr.co.nexon.npaccount.auth.request.NXToyUnlinkMemIDtoNPSNRequest;
import kr.co.nexon.npaccount.auth.result.NXAuthConnectionStatusListResult;
import kr.co.nexon.npaccount.auth.result.NXAuthResult;
import kr.co.nexon.npaccount.auth.result.NXAuthTokenResult;
import kr.co.nexon.npaccount.auth.result.NXToyAppLinkDataResult;
import kr.co.nexon.npaccount.auth.result.NXToyFriendsResult;
import kr.co.nexon.npaccount.auth.result.NXToyMapResult;
import kr.co.nexon.npaccount.auth.result.NXToyNPSNsResult;
import kr.co.nexon.npaccount.auth.result.NXToyUserInfoResult;
import kr.co.nexon.npaccount.auth.result.model.NXAuthConnectionStatus;
import kr.co.nexon.npaccount.auth.result.model.NXAuthToken;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPSNSManager {
    public static final String FRIEND_FILTER_TYPE_FRIENDS = "friends";
    public static final String FRIEND_FILTER_TYPE_INVITES = "invites";

    /* JADX INFO: renamed from: kr.co.nexon.npaccount.sns.NXPSNSManager$1, reason: invalid class name */
    /* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ NPListener val$listener;
        final /* synthetic */ NXToyLocaleManager val$localeManager;
        final /* synthetic */ NPAuthPlugin val$snsPlugin;
        final /* synthetic */ NXAuthResult val$snsResult;
        final /* synthetic */ int val$snsType;

        AnonymousClass1(NPAuthPlugin nPAuthPlugin, Activity activity, NXAuthResult nXAuthResult, NXToyLocaleManager nXToyLocaleManager, NPListener nPListener, int i) {
            this.val$snsPlugin = nPAuthPlugin;
            this.val$activity = activity;
            this.val$snsResult = nXAuthResult;
            this.val$localeManager = nXToyLocaleManager;
            this.val$listener = nPListener;
            this.val$snsType = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$snsPlugin.login(this.val$activity, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.1.1
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str, Bundle bundle) {
                    if (i == NXToyErrorCode.SUCCESS.getCode()) {
                        NXToyRequestPostman.getInstance().postRequest(new NXToyLinkMemIDtoNPSNRequest(bundle.getString(NPAuthPlugin.KEY_ID), AnonymousClass1.this.val$snsType), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.1.1.1
                            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                            public void onComplete(NXToyResult nXToyResult) {
                                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                                    AnonymousClass1.this.val$snsPlugin.logout(AnonymousClass1.this.val$activity, null);
                                    AnonymousClass1.this.val$snsResult.errorCode = nXToyResult.errorCode;
                                    AnonymousClass1.this.val$snsResult.errorText = nXToyResult.errorText;
                                    AnonymousClass1.this.val$snsResult.errorDetail = nXToyResult.errorDetail;
                                    NXToySessionManager.getInstance().setSnsEnable(AnonymousClass1.this.val$snsType, false);
                                } else {
                                    NXToySessionManager.getInstance().setSnsEnable(AnonymousClass1.this.val$snsType, true);
                                }
                                AnonymousClass1.this.val$listener.onResult(AnonymousClass1.this.val$snsResult);
                            }
                        });
                        return;
                    }
                    AnonymousClass1.this.val$snsResult.errorCode = i;
                    AnonymousClass1.this.val$snsResult.errorText = AnonymousClass1.this.val$localeManager.getString(R.string.npres_loginfailed);
                    AnonymousClass1.this.val$snsResult.errorDetail = str;
                    AnonymousClass1.this.val$listener.onResult(AnonymousClass1.this.val$snsResult);
                }
            });
        }
    }

    /* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
    private static class Singleton {
        private static final NXPSNSManager instance = new NXPSNSManager();

        private Singleton() {
        }
    }

    public static NXPSNSManager getInstance() {
        return Singleton.instance;
    }

    @Nullable
    private NPAuthPlugin getLegacySnsProvider(int i) {
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
            case LoginTypeTwitter:
            case LoginTypeGoogle:
            case LoginTypeVKontakte:
                return NXPProviderManager.getInstance().getProvider(i);
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NXToyUserInfo makeFriendUserInfo(NPAuthUser nPAuthUser, long j, int i) {
        NXToyUserInfo nXToyUserInfo = new NXToyUserInfo();
        nXToyUserInfo.npsn = j;
        nXToyUserInfo.subID = nPAuthUser.memID;
        nXToyUserInfo.memID = nPAuthUser.memID;
        nXToyUserInfo.name = nPAuthUser.name;
        nXToyUserInfo.gender = nPAuthUser.gender;
        nXToyUserInfo.pictureUrl = nPAuthUser.pictureURL;
        nXToyUserInfo.age_range_max = nPAuthUser.agerangeMax;
        nXToyUserInfo.age_range_min = nPAuthUser.agerangeMin;
        nXToyUserInfo.birthDay = nPAuthUser.birthDay;
        nXToyUserInfo.memType = i;
        nXToyUserInfo.firstName = nPAuthUser.firstName;
        nXToyUserInfo.lastName = nPAuthUser.lastName;
        return nXToyUserInfo;
    }

    public void connectToNpsn(@NonNull Activity activity, int i, NPListener nPListener) {
        NPAuthPlugin snsProvider = getSnsProvider(i);
        if (snsProvider == null) {
            if (nPListener != null) {
                nPListener.onResult(new NXToyResult(NXToyErrorCode.UNSUPPORTED.getCode(), "Unsupported type", "Unsupported type"));
                return;
            }
            return;
        }
        Context applicationContext = activity.getApplicationContext();
        NXAuthResult nXAuthResult = new NXAuthResult();
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(applicationContext);
        nXAuthResult.result.type = i;
        nXAuthResult.result.name = snsProvider.getServiceName();
        nXAuthResult.requestTag = NXToyRequestTag.getSnsConnectCodeFromSnsType(i);
        boolean z = !NXPApplicationConfigManager.getInstance().isSupportSnsConnectWithGuest();
        int type = NXToySessionManager.getInstance().getSession().getType();
        if (type == NXToyLoginType.LoginTypeGuest.getValue() && z) {
            nXAuthResult.errorCode = NXToyErrorCode.SNS_DONT_CONNECT_WITH_GUEST.getCode();
            nXAuthResult.errorText = nXToyLocaleManager.getString(R.string.npres_dont_connect_with_guest);
            nXAuthResult.errorDetail = "The guest account cannot use the SNS connection.";
            if (nPListener != null) {
                nPListener.onResult(nXAuthResult);
                return;
            }
            return;
        }
        if (type != i) {
            activity.runOnUiThread(new AnonymousClass1(snsProvider, activity, nXAuthResult, nXToyLocaleManager, nPListener, i));
            return;
        }
        nXAuthResult.errorCode = NXToyErrorCode.SNS_ALREADY_LOGIN.getCode();
        nXAuthResult.errorText = nXToyLocaleManager.getString(R.string.npres_already_login);
        nXAuthResult.errorDetail = "already login";
        if (nPListener != null) {
            nPListener.onResult(nXAuthResult);
        }
    }

    public void disconnect(Context context, int i, NPAuthListener nPAuthListener) {
        NPAuthPlugin snsProvider = getSnsProvider(i);
        if (snsProvider != null) {
            snsProvider.logout(context, nPAuthListener);
        } else if (nPAuthListener != null) {
            nPAuthListener.onResult(NXToyErrorCode.SNS_DONT_DISCONNECT_LOGINED_SNS.getCode(), NXToyLocaleManager.getInstance(context).getStringEx(R.string.npres_dont_disconnect_logined_sns, ""), null);
        }
    }

    public void disconnectAll(@NonNull Context context) {
        Iterator<NPAuthPlugin> it = NXPProviderManager.getInstance().getAllSNSList().iterator();
        while (it.hasNext()) {
            it.next().logout(context, null);
        }
    }

    public void disconnectFromNpsn(Context context, final int i, final NPListener nPListener) {
        NPAuthPlugin snsProvider = getSnsProvider(i);
        if (snsProvider == null) {
            if (nPListener != null) {
                nPListener.onResult(new NXToyResult(NXToyErrorCode.UNSUPPORTED.getCode(), "Unsupported type", "Unsupported type"));
                return;
            }
            return;
        }
        final NXAuthResult nXAuthResult = new NXAuthResult();
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(context);
        nXAuthResult.result.type = i;
        nXAuthResult.result.name = snsProvider.getServiceName();
        nXAuthResult.requestTag = NXToyRequestTag.getSNSDisconnectCodeFromSnsType(i);
        int type = NXToySessionManager.getInstance().getSession().getType();
        if (i != type) {
            snsProvider.logout(context, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.2
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i2, String str, Bundle bundle) {
                    if (i2 == NXToyErrorCode.SUCCESS.getCode()) {
                        NXToyRequestPostman.getInstance().postRequest(new NXToyUnlinkMemIDtoNPSNRequest(i), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.2.1
                            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                            public void onComplete(NXToyResult nXToyResult) {
                                ToyLog.d("unlink result:" + nXToyResult);
                                if (nPListener != null) {
                                    nPListener.onResult(nXAuthResult);
                                }
                            }
                        });
                        NXToySessionManager.getInstance().setSnsEnable(i, false);
                    } else {
                        nXAuthResult.errorCode = i2;
                        nXAuthResult.errorText = str;
                        nXAuthResult.errorDetail = str;
                        if (nPListener != null) {
                            nPListener.onResult(nXAuthResult);
                        }
                    }
                }
            });
            return;
        }
        ToyLog.d("currentLoginType :" + type + ", snsType :" + i);
        if (nPListener != null) {
            nXAuthResult.errorCode = NXToyErrorCode.SNS_DONT_DISCONNECT_LOGINED_SNS.getCode();
            nXAuthResult.errorText = nXToyLocaleManager.getStringEx(R.string.npres_dont_disconnect_logined_sns, "");
            nXAuthResult.errorDetail = "Do not logout logined sns";
            nPListener.onResult(nXAuthResult);
        }
    }

    @Deprecated
    public void fbActivateApp() {
        NPFacebook facebookProvider = NXPProviderManager.getInstance().getFacebookProvider();
        if (facebookProvider == null) {
            ToyLog.d("Unsupported type. facebook provider not found.");
        } else {
            facebookProvider.activateApp();
        }
    }

    public void fbActivateApp(Application application) {
        NPFacebook facebookProvider = NXPProviderManager.getInstance().getFacebookProvider();
        if (facebookProvider == null) {
            ToyLog.d("Unsupported type. facebook provider not found.");
        } else {
            facebookProvider.activateApp(application);
        }
    }

    public void fbAddPermission(Activity activity, List<String> list, int i, final NPListener nPListener) {
        NPFacebook facebookProvider = NXPProviderManager.getInstance().getFacebookProvider();
        if (facebookProvider != null) {
            facebookProvider.addPermission(activity, list, i, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.10
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i2, String str, Bundle bundle) {
                    if (nPListener == null) {
                        ToyLog.d("fbAddPermission listener param is null");
                        return;
                    }
                    NXToyResult nXToyResult = new NXToyResult(i2, i2 != NXToyErrorCode.SUCCESS.getCode() ? "Please agree permission" : "", str);
                    nXToyResult.requestTag = NXToyRequestTag.RequestPermissions.getValue();
                    nPListener.onResult(nXToyResult);
                }
            });
        } else if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NXToyErrorCode.UNSUPPORTED.getCode(), "Unsupported type", "Unsupported type", NXToyRequestTag.RequestPermissions.getValue()));
        }
    }

    @Deprecated
    public void fbDeactivateApp() {
        NPFacebook facebookProvider = NXPProviderManager.getInstance().getFacebookProvider();
        if (facebookProvider == null) {
            ToyLog.d("Unsupported type. facebook provider not found.");
        } else {
            facebookProvider.deactivateApp();
        }
    }

    public void fbFetchDeferredAppLink(@NonNull Context context, final NPListener nPListener) {
        NPFacebook facebookProvider = NXPProviderManager.getInstance().getFacebookProvider();
        if (facebookProvider != null) {
            facebookProvider.fetchDeferredAppLinkData(context, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.11
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str, Bundle bundle) {
                    NXToyAppLinkDataResult nXToyAppLinkDataResult = new NXToyAppLinkDataResult(i, str, str);
                    nXToyAppLinkDataResult.requestTag = NXToyRequestTag.FetchDeferredAppLink.getValue();
                    if (i == NXToyErrorCode.SUCCESS.getCode()) {
                        nXToyAppLinkDataResult.result.url = bundle.getString(NPAuthPlugin.KEY_URL);
                    }
                    if (nPListener != null) {
                        nPListener.onResult(nXToyAppLinkDataResult);
                    }
                }
            });
        } else if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NXToyErrorCode.UNSUPPORTED.getCode(), "Unsupported type", "Unsupported type", NXToyRequestTag.FetchDeferredAppLink.getValue()));
        }
    }

    public void fbGetFriends(@NonNull Context context, int i, final NPListener nPListener) {
        NPFacebook facebookProvider = NXPProviderManager.getInstance().getFacebookProvider();
        if (facebookProvider == null) {
            if (nPListener != null) {
                nPListener.onResult(new NXToyResult(NXToyErrorCode.UNSUPPORTED.getCode(), "Unsupported type", "Unsupported type", NXToyRequestTag.GetFriends.getValue()));
            }
        } else {
            final Context applicationContext = context.getApplicationContext();
            facebookProvider.getFriends(applicationContext, i > 0, new NPAuthFriendsListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.14
                @Override // kr.co.nexon.android.sns.NPAuthFriendsListener
                public void onResult(int i2, String str, boolean z, List<NPAuthUser> list) {
                    if (nPListener == null) {
                        ToyLog.d("fbGetFriends listener param is null");
                        return;
                    }
                    if (i2 != NXToyErrorCode.SUCCESS.getCode()) {
                        NXToyMapResult nXToyMapResult = new NXToyMapResult(i2, NXToyLocaleManager.getInstance(applicationContext).getStringEx(R.string.npres_get_friend_failed, ""), str);
                        nXToyMapResult.requestTag = NXToyRequestTag.GetFriends.getValue();
                        nPListener.onResult(nXToyMapResult);
                    } else {
                        NXToyMapResult nXToyMapResult2 = new NXToyMapResult();
                        nXToyMapResult2.requestTag = NXToyRequestTag.GetFriends.getValue();
                        nXToyMapResult2.result.put("friends", list);
                        nXToyMapResult2.result.put("hasNext", Integer.valueOf(z ? 1 : 0));
                        nPListener.onResult(nXToyMapResult2);
                    }
                }
            });
        }
    }

    public void fbLogEvent(String str) {
        NPFacebook facebookProvider = NXPProviderManager.getInstance().getFacebookProvider();
        if (facebookProvider == null) {
            ToyLog.d("Unsupported type. facebook provider not found.");
        } else {
            facebookProvider.logEvent(str);
        }
    }

    public void fbLogEvent(String str, double d) {
        NPFacebook facebookProvider = NXPProviderManager.getInstance().getFacebookProvider();
        if (facebookProvider == null) {
            ToyLog.d("Unsupported type. facebook provider not found.");
        } else {
            facebookProvider.logEvent(str, d);
        }
    }

    public void fbLogEvent(String str, double d, String str2) {
        NPFacebook facebookProvider = NXPProviderManager.getInstance().getFacebookProvider();
        if (facebookProvider == null) {
            ToyLog.d("Unsupported type. facebook provider not found.");
        } else {
            facebookProvider.logEvent(str, d, str2);
        }
    }

    public void fbLogEvent(String str, String str2) {
        NPFacebook facebookProvider = NXPProviderManager.getInstance().getFacebookProvider();
        if (facebookProvider == null) {
            ToyLog.d("Unsupported type. facebook provider not found.");
        } else {
            facebookProvider.logEvent(str, str2);
        }
    }

    public void fbLogPurchase(Double d, String str, String str2) {
        NPFacebook facebookProvider = NXPProviderManager.getInstance().getFacebookProvider();
        if (facebookProvider == null) {
            ToyLog.d("Unsupported type. facebook provider not found.");
        } else {
            facebookProvider.logPurchase(d, str, str2);
        }
    }

    public void fbSetIsDebugEnabled(boolean z) {
        NPFacebook facebookProvider = NXPProviderManager.getInstance().getFacebookProvider();
        if (facebookProvider == null) {
            ToyLog.d("Unsupported type. Facebook provider not found.");
        } else {
            facebookProvider.setIsDebugEnabled(z);
        }
    }

    public void fbShare(Activity activity, String str, String str2, String str3, String str4, final NPListener nPListener) {
        NPFacebook facebookProvider = NXPProviderManager.getInstance().getFacebookProvider();
        if (facebookProvider != null) {
            facebookProvider.share(activity, null, str, str2, str3, str4, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.12
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str5, Bundle bundle) {
                    if (nPListener != null) {
                        NXToyResult nXToyResult = new NXToyResult(i, str5, str5);
                        nXToyResult.requestTag = NXToyRequestTag.ShareFacebook.getValue();
                        nPListener.onResult(nXToyResult);
                    }
                }
            });
        } else if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NXToyErrorCode.UNSUPPORTED.getCode(), "Unsupported type", "Unsupported type", NXToyRequestTag.ShareFacebook.getValue()));
        }
    }

    public void fbShareImage(Activity activity, Bitmap bitmap, final NPListener nPListener) {
        NPFacebook facebookProvider = NXPProviderManager.getInstance().getFacebookProvider();
        if (facebookProvider != null) {
            facebookProvider.shareImage(activity, bitmap, new NPListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.13
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    if (nPListener != null) {
                        nXToyResult.requestTag = NXToyRequestTag.ShareImageFacebook.getValue();
                        nPListener.onResult(nXToyResult);
                    }
                }
            });
        } else if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NXToyErrorCode.UNSUPPORTED.getCode(), "Unsupported type", "Unsupported type", NXToyRequestTag.ShareImageFacebook.getValue()));
        }
    }

    public void getFriends(@NonNull Context context, final int i, int i2, String str, final NPListener nPListener) {
        if (nPListener == null) {
            ToyLog.d("getFriends listener param is null!!");
            return;
        }
        NPAuthPlugin snsProvider = getSnsProvider(i);
        Context applicationContext = context.getApplicationContext();
        final NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(applicationContext);
        if (snsProvider == null) {
            nPListener.onResult(new NXToyFriendsResult(NXToyErrorCode.GET_FRIENDS_FAILED.getCode(), nXToyLocaleManager.getString(R.string.npres_get_friend_failed), "guest", NXToyRequestTag.GetFriends.getValue()));
            return;
        }
        if ("invites".equals(str)) {
            if (i == NXToyLoginType.LoginTypeFaceBook.getValue()) {
                nPListener.onResult(new NXToyFriendsResult(NXToyErrorCode.FACEBOOK_NOT_SUPPORT.getCode(), nXToyLocaleManager.getString(R.string.npres_get_friend_failed)));
                return;
            } else {
                snsProvider.getFriends(applicationContext, i2 > 0, new NPAuthFriendsListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.7
                    @Override // kr.co.nexon.android.sns.NPAuthFriendsListener
                    public void onResult(int i3, String str2, final boolean z, final List<NPAuthUser> list) {
                        if (i3 == NXToyErrorCode.TWITTER_GET_REQUEST_TOKEN_FAIL.getCode()) {
                            nPListener.onResult(new NXToyFriendsResult(i3, nXToyLocaleManager.getString(R.string.npres_twitter_getting_access_token_failed), str2));
                        } else {
                            if (i3 != NXToyErrorCode.SUCCESS.getCode()) {
                                nPListener.onResult(new NXToyFriendsResult(NXToyErrorCode.GET_FRIENDS_FAILED.getCode(), nXToyLocaleManager.getString(R.string.npres_get_friend_failed), str2));
                                return;
                            }
                            ArrayList arrayList = new ArrayList();
                            Iterator<NPAuthUser> it = list.iterator();
                            while (it.hasNext()) {
                                arrayList.add(it.next().memID);
                            }
                            NXToyRequestPostman.getInstance().postRequest(new NXToyGetNPSNsRequest(i, arrayList), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.7.1
                                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                                public void onComplete(NXToyResult nXToyResult) {
                                    ToyLog.d(nXToyResult.toString());
                                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                                        nPListener.onResult(new NXToyFriendsResult(nXToyResult.errorCode, nXToyResult.errorText, nXToyResult.errorDetail));
                                        return;
                                    }
                                    NXToyNPSNsResult nXToyNPSNsResult = (NXToyNPSNsResult) nXToyResult;
                                    Map<String, List<Long>> map = nXToyNPSNsResult.result.linkWithSNS;
                                    List<Long> list2 = nXToyNPSNsResult.result.npSNs;
                                    NXToyFriendsResult nXToyFriendsResult = new NXToyFriendsResult();
                                    nXToyFriendsResult.requestTag = NXToyRequestTag.GetFriends.getValue();
                                    nXToyFriendsResult.result.friends = new ArrayList();
                                    nXToyFriendsResult.result.invites = new ArrayList();
                                    if (list2.size() != list.size()) {
                                        ToyLog.e("mismatch friend size from server");
                                        nPListener.onResult(new NXToyNPSNsResult(NXToyErrorCode.GET_FRIENDS_FAILED.getCode(), nXToyLocaleManager.getString(R.string.npres_mismatch_friend_size), ""));
                                        return;
                                    }
                                    for (int i4 = 0; i4 < list2.size(); i4++) {
                                        long jLongValue = list2.get(i4).longValue();
                                        if (jLongValue <= 0) {
                                            NPAuthUser nPAuthUser = (NPAuthUser) list.get(i4);
                                            if (map == null || map.get(nPAuthUser.memID) == null) {
                                                nXToyFriendsResult.result.invites.add(NXPSNSManager.this.makeFriendUserInfo(nPAuthUser, jLongValue, i));
                                            }
                                        }
                                    }
                                    nXToyFriendsResult.result.hasNext = z ? 1 : 0;
                                    nPListener.onResult(nXToyFriendsResult);
                                }
                            });
                        }
                    }
                });
                return;
            }
        }
        if (!"friends".equals(str)) {
            nPListener.onResult(new NXToyFriendsResult(NXToyErrorCode.GET_FRIENDS_FAILED.getCode(), nXToyLocaleManager.getString(R.string.npres_get_friend_failed), "not supported filter type", NXToyRequestTag.GetFriends.getValue()));
        } else {
            snsProvider.getFriends(applicationContext, i2 > 0, new NPAuthFriendsListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.8
                @Override // kr.co.nexon.android.sns.NPAuthFriendsListener
                public void onResult(int i3, String str2, final boolean z, final List<NPAuthUser> list) {
                    if (i3 == NXToyErrorCode.FACEBOOK_GET_ACCESSTOKEN_FAILED.getCode()) {
                        nPListener.onResult(new NXToyFriendsResult(i3, nXToyLocaleManager.getString(R.string.npres_fbgetting_access_token_failed), str2));
                        return;
                    }
                    if (i3 == NXToyErrorCode.TWITTER_GET_REQUEST_TOKEN_FAIL.getCode()) {
                        nPListener.onResult(new NXToyFriendsResult(i3, nXToyLocaleManager.getString(R.string.npres_twitter_getting_access_token_failed), str2));
                    } else {
                        if (i3 != NXToyErrorCode.SUCCESS.getCode()) {
                            nPListener.onResult(new NXToyFriendsResult(NXToyErrorCode.GET_FRIENDS_FAILED.getCode(), nXToyLocaleManager.getString(R.string.npres_get_friend_failed), str2));
                            return;
                        }
                        ArrayList arrayList = new ArrayList();
                        Iterator<NPAuthUser> it = list.iterator();
                        while (it.hasNext()) {
                            arrayList.add(it.next().memID);
                        }
                        NXToyRequestPostman.getInstance().postRequest(new NXToyGetNPSNsRequest(i, arrayList), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.8.1
                            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                            public void onComplete(NXToyResult nXToyResult) {
                                ToyLog.d(nXToyResult.toString());
                                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                                    nPListener.onResult(new NXToyFriendsResult(nXToyResult.errorCode, nXToyResult.errorText, nXToyResult.errorDetail, NXToyRequestTag.GetFriends.getValue()));
                                    return;
                                }
                                NXToyNPSNsResult nXToyNPSNsResult = (NXToyNPSNsResult) nXToyResult;
                                Map<String, List<Long>> map = nXToyNPSNsResult.result.linkWithSNS;
                                ArrayList arrayList2 = (ArrayList) nXToyNPSNsResult.result.npSNs;
                                NXToyFriendsResult nXToyFriendsResult = new NXToyFriendsResult();
                                nXToyFriendsResult.requestTag = NXToyRequestTag.GetFriends.getValue();
                                nXToyFriendsResult.result.friends = new ArrayList();
                                nXToyFriendsResult.result.invites = new ArrayList();
                                if (arrayList2.size() != list.size()) {
                                    ToyLog.e("mismatch friend size from server");
                                    nPListener.onResult(new NXToyFriendsResult(NXToyErrorCode.GET_FRIENDS_FAILED.getCode(), nXToyLocaleManager.getString(R.string.npres_mismatch_friend_size), ""));
                                    return;
                                }
                                for (int i4 = 0; i4 < arrayList2.size(); i4++) {
                                    long jLongValue = ((Long) arrayList2.get(i4)).longValue();
                                    NPAuthUser nPAuthUser = (NPAuthUser) list.get(i4);
                                    NXToyUserInfo nXToyUserInfoMakeFriendUserInfo = NXPSNSManager.this.makeFriendUserInfo(nPAuthUser, jLongValue, i);
                                    if (jLongValue != 0) {
                                        nXToyFriendsResult.result.friends.add(nXToyUserInfoMakeFriendUserInfo);
                                    }
                                    if (map != null && map.get(nPAuthUser.memID) != null) {
                                        for (Long l : map.get(nPAuthUser.memID)) {
                                            NXToyUserInfo nXToyUserInfo = new NXToyUserInfo();
                                            nXToyUserInfo.copyFrom(nXToyUserInfoMakeFriendUserInfo);
                                            nXToyUserInfo.npsn = l.longValue();
                                            nXToyFriendsResult.result.friends.add(nXToyUserInfo);
                                        }
                                    }
                                }
                                nXToyFriendsResult.result.hasNext = z ? 1 : 0;
                                nPListener.onResult(nXToyFriendsResult);
                            }
                        });
                    }
                }
            });
        }
    }

    public void getFriends(@NonNull Context context, final String str, final NPListener nPListener) {
        if (nPListener == null) {
            ToyLog.d("getFriends(Context, String, NPListener) listener param is null!!");
            return;
        }
        Context applicationContext = context.getApplicationContext();
        final NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(applicationContext);
        final int type = NXToySessionManager.getInstance().getSession().getType();
        NPAuthPlugin snsProvider = getSnsProvider(type);
        if (snsProvider != null) {
            snsProvider.getFriendsAll(applicationContext, new NPAuthFriendsListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.6
                @Override // kr.co.nexon.android.sns.NPAuthFriendsListener
                public void onResult(int i, String str2, boolean z, final List<NPAuthUser> list) {
                    if (i != NXToyErrorCode.SUCCESS.getCode()) {
                        nPListener.onResult(new NXToyFriendsResult(NXToyErrorCode.GET_FRIENDS_FAILED.getCode(), nXToyLocaleManager.getString(R.string.npres_get_friend_failed), str2, NXToyRequestTag.GetFriends.getValue()));
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    Iterator<NPAuthUser> it = list.iterator();
                    while (it.hasNext()) {
                        arrayList.add(it.next().memID);
                    }
                    NXToyRequestPostman.getInstance().postRequest(new NXToyGetNPSNsRequest(type, arrayList), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.6.1
                        @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                        public void onComplete(NXToyResult nXToyResult) {
                            ToyLog.d(nXToyResult.toString());
                            if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                                nPListener.onResult(new NXToyFriendsResult(nXToyResult.errorCode, nXToyResult.errorText, nXToyResult.errorDetail));
                                return;
                            }
                            NXToyFriendsResult nXToyFriendsResult = new NXToyFriendsResult();
                            nXToyFriendsResult.result.friends = new ArrayList();
                            nXToyFriendsResult.result.invites = new ArrayList();
                            ArrayList arrayList2 = (ArrayList) ((NXToyNPSNsResult) nXToyResult).result.npSNs;
                            if (arrayList2.size() != list.size()) {
                                ToyLog.e("mismatch friend size from server");
                                nPListener.onResult(new NXToyNPSNsResult(NXToyErrorCode.GET_FRIENDS_FAILED.getCode(), nXToyLocaleManager.getString(R.string.npres_mismatch_friend_size), ""));
                                return;
                            }
                            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                                long jLongValue = ((Long) arrayList2.get(i2)).longValue();
                                if ((!"friends".equalsIgnoreCase(str) || jLongValue != 0) && (!"invites".equalsIgnoreCase(str) || jLongValue <= 0)) {
                                    NXToyUserInfo nXToyUserInfoMakeFriendUserInfo = NXPSNSManager.this.makeFriendUserInfo((NPAuthUser) list.get(i2), jLongValue, type);
                                    if (jLongValue == 0) {
                                        nXToyFriendsResult.result.invites.add(nXToyUserInfoMakeFriendUserInfo);
                                    } else {
                                        nXToyFriendsResult.result.friends.add(nXToyUserInfoMakeFriendUserInfo);
                                    }
                                }
                            }
                            nPListener.onResult(nXToyFriendsResult);
                        }
                    });
                }
            });
            return;
        }
        NXToyFriendsResult nXToyFriendsResult = new NXToyFriendsResult(NXToyErrorCode.GET_FRIENDS_FAILED.getCode(), nXToyLocaleManager.getString(R.string.npres_get_friend_failed), "guest");
        nXToyFriendsResult.requestTag = NXToyRequestTag.GetFriends.getValue();
        nPListener.onResult(nXToyFriendsResult);
    }

    @Nullable
    public NPAuthPlugin getSnsProvider(int i) {
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
            case LoginTypeTwitter:
                return NXPProviderManager.getInstance().getProvider(i);
            default:
                return null;
        }
    }

    public void getTokenList(@NonNull final Activity activity, final NPListener nPListener) {
        if (nPListener == null) {
            ToyLog.d("getTokenList listener param is null.");
            return;
        }
        final List<NPAuthPlugin> allSNSList = NXPProviderManager.getInstance().getAllSNSList();
        final NXAuthTokenResult nXAuthTokenResult = new NXAuthTokenResult();
        nXAuthTokenResult.requestTag = NXToyRequestTag.GetTokenList.getValue();
        nXAuthTokenResult.result.list = new ArrayList();
        if (allSNSList.isEmpty()) {
            nPListener.onResult(nXAuthTokenResult);
        } else {
            allSNSList.get(0).getAccessToken(activity, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.4
                private int index = 0;

                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str, Bundle bundle) {
                    if (i == NXToyErrorCode.SUCCESS.getCode()) {
                        NXAuthToken nXAuthToken = new NXAuthToken();
                        nXAuthToken.sns = bundle.getString(NPAuthPlugin.KEY_SNS_NAME);
                        nXAuthToken.accessToken = bundle.getString(NPAuthPlugin.KEY_ACCESSTOKEN);
                        nXAuthToken.secretToken = bundle.getString(NPAuthPlugin.KEY_SECRETTOKEN);
                        nXAuthTokenResult.result.list.add(nXAuthToken);
                    }
                    int i2 = this.index + 1;
                    this.index = i2;
                    if (i2 != allSNSList.size()) {
                        ((NPAuthPlugin) allSNSList.get(this.index)).getAccessToken(activity, this);
                    } else {
                        nPListener.onResult(nXAuthTokenResult);
                    }
                }
            });
        }
    }

    public void getUserInfo(final int i, Activity activity, final NPListener nPListener) {
        if (nPListener == null) {
            ToyLog.d("getUserInfo listener param is null.");
            return;
        }
        Context applicationContext = activity.getApplicationContext();
        final NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(applicationContext);
        final NXToyUserInfoResult nXToyUserInfoResult = new NXToyUserInfoResult();
        nXToyUserInfoResult.result.npsnUserInfo = new NXToyUserInfo();
        nXToyUserInfoResult.requestTag = NXToyRequestTag.getSnsUserInfoCodeFromSnsType(i);
        NPAuthPlugin legacySnsProvider = getLegacySnsProvider(i);
        if (legacySnsProvider != null) {
            legacySnsProvider.getUserInfo(applicationContext, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.3
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i2, String str, Bundle bundle) {
                    ToyLog.d("getUserInfo(sns) result.", NxLogInfo.KEY_ERROR_CODE, Integer.valueOf(i2), "errorDetail", str, TJAdUnitConstants.String.BUNDLE, bundle);
                    if (i2 == NXToyErrorCode.FACEBOOK_GET_ACCESSTOKEN_FAILED.getCode()) {
                        nXToyUserInfoResult.errorCode = i2;
                        nXToyUserInfoResult.errorText = nXToyLocaleManager.getString(R.string.npres_fbgetting_access_token_failed);
                        nXToyUserInfoResult.errorDetail = str;
                        nPListener.onResult(nXToyUserInfoResult);
                        return;
                    }
                    if (i2 == NXToyErrorCode.TWITTER_GET_REQUEST_TOKEN_FAIL.getCode()) {
                        nXToyUserInfoResult.errorCode = i2;
                        nXToyUserInfoResult.errorText = nXToyLocaleManager.getString(R.string.npres_twitter_getting_access_token_failed);
                        nXToyUserInfoResult.errorDetail = str;
                        nPListener.onResult(nXToyUserInfoResult);
                        return;
                    }
                    if (i2 == NXToyErrorCode.VKONTAKTE_GET_ACCESSTOKEN_FAILED.getCode()) {
                        nXToyUserInfoResult.errorCode = i2;
                        nXToyUserInfoResult.errorText = nXToyLocaleManager.getString(R.string.npres_vkontakte_getting_access_token_failed);
                        nXToyUserInfoResult.errorDetail = str;
                        nPListener.onResult(nXToyUserInfoResult);
                        return;
                    }
                    if (i2 != NXToyErrorCode.SUCCESS.getCode()) {
                        nXToyUserInfoResult.errorCode = i2;
                        nXToyUserInfoResult.errorText = str;
                        nXToyUserInfoResult.errorDetail = str;
                        nPListener.onResult(nXToyUserInfoResult);
                        return;
                    }
                    NXToyUserInfo nXToyUserInfo = nXToyUserInfoResult.result.npsnUserInfo;
                    nXToyUserInfo.memType = i;
                    nXToyUserInfo.subID = bundle.getString(NPAuthPlugin.KEY_ID);
                    nXToyUserInfo.name = bundle.getString(NPAuthPlugin.KEY_NAME);
                    nXToyUserInfo.pictureUrl = bundle.getString(NPAuthPlugin.KEY_PICTURE_URL);
                    nXToyUserInfo.npsn = NXToySessionManager.getInstance().getSession().getNpsn();
                    nXToyUserInfo.email = bundle.getString(NPAuthPlugin.KEY_EMAIL);
                    nXToyUserInfo.gender = bundle.getInt(NPAuthPlugin.KEY_GENDER);
                    nXToyUserInfo.age_range_max = bundle.getInt(NPAuthPlugin.KEY_AGERANGE_MAX);
                    nXToyUserInfo.age_range_min = bundle.getInt(NPAuthPlugin.KEY_AGERANGE_MIN);
                    nXToyUserInfo.birthDay = bundle.getString(NPAuthPlugin.KEY_BIRTHDAY);
                    nXToyUserInfo.firstName = bundle.getString(NPAuthPlugin.KEY_FIRSTNAME);
                    nXToyUserInfo.middleName = bundle.getString(NPAuthPlugin.KEY_MIDDLENAME);
                    nXToyUserInfo.lastName = bundle.getString(NPAuthPlugin.KEY_LASTNAME);
                    nXToyUserInfo.thirdPartyToken = bundle.getString(NPAuthPlugin.KEY_ACCESSTOKEN, "");
                    nXToyUserInfo.fbBizToken = bundle.getString(NPAuthPlugin.KEY_FB_BIZ_TOKEN, "");
                    nPListener.onResult(nXToyUserInfoResult);
                }
            });
            return;
        }
        nXToyUserInfoResult.errorCode = NXToyErrorCode.SNS_GET_USERINFO_FAILED.getCode();
        nXToyUserInfoResult.errorText = nXToyLocaleManager.getStringEx(R.string.npres_get_userinfo_fail, "");
        nXToyUserInfoResult.errorDetail = nXToyLocaleManager.getStringEx(R.string.npres_get_userinfo_fail, "");
        nPListener.onResult(nXToyUserInfoResult);
    }

    public void invite(@NonNull Activity activity, int i, List<String> list, String str, String str2, final NPListener nPListener) {
        NPFacebook facebookProvider = NXPProviderManager.getInstance().getFacebookProvider();
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(activity.getApplicationContext());
        NXToyResult nXToyResult = new NXToyResult();
        if (facebookProvider != null) {
            facebookProvider.invite(activity, list, str, str2, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.9
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i2, String str3, Bundle bundle) {
                    if (nPListener == null) {
                        ToyLog.d("sns invite listener param is null");
                        return;
                    }
                    NXToyResult nXToyResult2 = new NXToyResult();
                    nXToyResult2.errorCode = i2;
                    nXToyResult2.errorText = str3;
                    nXToyResult2.errorDetail = str3;
                    nPListener.onResult(nXToyResult2);
                }
            });
        } else if (nPListener != null) {
            nXToyResult.errorCode = NXToyErrorCode.SNS_INVITE_FAILED.getCode();
            nXToyResult.errorText = nXToyLocaleManager.getString(R.string.npres_get_userinfo_fail);
            nXToyResult.errorDetail = nXToyLocaleManager.getString(R.string.npres_get_userinfo_fail);
            nPListener.onResult(nXToyResult);
        }
    }

    public void queryAllConnectionStatus(@NonNull final Activity activity, final NPListener nPListener) {
        if (nPListener == null) {
            ToyLog.d("getTokenList listener param is null.");
            return;
        }
        final List<NPAuthPlugin> allSNSList = NXPProviderManager.getInstance().getAllSNSList();
        final NXAuthConnectionStatusListResult nXAuthConnectionStatusListResult = new NXAuthConnectionStatusListResult();
        nXAuthConnectionStatusListResult.requestTag = NXToyRequestTag.GetSnsConnectionStatus.getValue();
        nXAuthConnectionStatusListResult.result.list = new ArrayList();
        if (allSNSList.isEmpty()) {
            nPListener.onResult(nXAuthConnectionStatusListResult);
        } else {
            allSNSList.get(0).isConnect(activity, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPSNSManager.5
                private int index = 0;

                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str, Bundle bundle) {
                    NXAuthConnectionStatus nXAuthConnectionStatus = new NXAuthConnectionStatus();
                    nXAuthConnectionStatus.name = ((NPAuthPlugin) allSNSList.get(this.index)).getServiceName();
                    if (nXAuthConnectionStatus.name.equals(NPFacebook.SERVICE_NAME)) {
                        nXAuthConnectionStatus.type = NXToyLoginType.LoginTypeFaceBook.getValue();
                        nXAuthConnectionStatus.isConnect = (i == NXToyErrorCode.SUCCESS.getCode() && NXToySessionManager.getInstance().getSnsEnable(NXToyLoginType.LoginTypeFaceBook.getValue())) ? 1 : 0;
                    } else if (nXAuthConnectionStatus.name.equals(NPTwitter.SERVICE_NAME)) {
                        nXAuthConnectionStatus.type = NXToyLoginType.LoginTypeTwitter.getValue();
                        nXAuthConnectionStatus.isConnect = (i == NXToyErrorCode.SUCCESS.getCode() && NXToySessionManager.getInstance().getSnsEnable(NXToyLoginType.LoginTypeTwitter.getValue())) ? 1 : 0;
                    }
                    nXAuthConnectionStatusListResult.result.list.add(nXAuthConnectionStatus);
                    int i2 = this.index + 1;
                    this.index = i2;
                    if (i2 != allSNSList.size()) {
                        ((NPAuthPlugin) allSNSList.get(this.index)).isConnect(activity, this);
                    } else {
                        nPListener.onResult(nXAuthConnectionStatusListResult);
                    }
                }
            });
        }
    }
}
