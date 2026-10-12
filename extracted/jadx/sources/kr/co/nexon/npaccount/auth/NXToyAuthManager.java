package kr.co.nexon.npaccount.auth;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import com.nexon.core.android.NXPApplicationConfigManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.core.session.model.NXToyUserInfo;
import com.nexon.core.util.NXCrypto.NXCrypto;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.nexonanalyticssdk.NxLogInfo;
import com.nexon.ngsm.Ngsm;
import com.nexon.ngsm.NgsmResult;
import com.nexon.npaccount.R;
import com.nexon.platform.auth.model.NXPProviderAuthenticationInfo;
import com.tapjoy.TJAdUnitConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.email.NPEmail;
import kr.co.nexon.android.sns.facebook.NPFacebook;
import kr.co.nexon.android.sns.google.NPGoogleGame;
import kr.co.nexon.android.sns.google.NPGoogleSignIn;
import kr.co.nexon.android.sns.naver.NPNaverChannel;
import kr.co.nexon.android.sns.nxcom.NPNexonCom;
import kr.co.nexon.android.sns.nxnet.NPNexonNet;
import kr.co.nexon.android.sns.twitter.NPTwitter;
import kr.co.nexon.mdev.android.view.NXToast;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.request.NXPRegisterFbBizTokenRequest;
import kr.co.nexon.npaccount.auth.request.NXToyCreateMGTokenRequest;
import kr.co.nexon.npaccount.auth.request.NXToyGetNPSNRequest;
import kr.co.nexon.npaccount.auth.request.NXToyGetNexonSNByNaverChannelRequest;
import kr.co.nexon.npaccount.auth.request.NXToyGetUserInfoRequest;
import kr.co.nexon.npaccount.auth.request.NXToyIsNewGCIDRequest;
import kr.co.nexon.npaccount.auth.request.NXToyLinkGCIDToNPSNRequest;
import kr.co.nexon.npaccount.auth.request.NXToyLogoutSVCRequest;
import kr.co.nexon.npaccount.auth.request.NXToyRecoverUserRequest;
import kr.co.nexon.npaccount.auth.request.NXToyRecoverUserWithGcidRequest;
import kr.co.nexon.npaccount.auth.request.NXToySignInRequest;
import kr.co.nexon.npaccount.auth.request.NXToySignInWithGCIDRequest;
import kr.co.nexon.npaccount.auth.request.NXToySignUpNPAARequest;
import kr.co.nexon.npaccount.auth.request.NXToyUnregisterSVCRequest;
import kr.co.nexon.npaccount.auth.request.model.NXToySignInRequestOptionalBody;
import kr.co.nexon.npaccount.auth.result.NXToyCreateMGTokenResult;
import kr.co.nexon.npaccount.auth.result.NXToyGetNexonSNByNaverChannelResult;
import kr.co.nexon.npaccount.auth.result.NXToyIsNewGCIDResult;
import kr.co.nexon.npaccount.auth.result.NXToyLoginResult;
import kr.co.nexon.npaccount.auth.result.NXToyLoginWithGCIDResult;
import kr.co.nexon.npaccount.auth.result.NXToyNpsnResult;
import kr.co.nexon.npaccount.auth.result.NXToyPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyTermResult;
import kr.co.nexon.npaccount.auth.result.NXToyTermsListResult;
import kr.co.nexon.npaccount.auth.result.NXToyUserInfoResult;
import kr.co.nexon.npaccount.auth.result.model.NXToyNexonUserInfo;
import kr.co.nexon.npaccount.auth.result.model.NXToyPolicy;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.npaccount.promotion.NPPromotionPrefCtl;
import kr.co.nexon.npaccount.setting.NPOptionManager;
import kr.co.nexon.npaccount.sns.NXPSNSManager;
import kr.co.nexon.npaccount.terms.NXPTermsManager;
import kr.co.nexon.toy.android.ui.auth.NXPLoginSelectDialog;
import kr.co.nexon.toy.android.ui.auth.accountmenu.NXPAccountMenuDialog;
import kr.co.nexon.toy.android.ui.backup.NPDataBackupDialog;
import kr.co.nexon.toy.android.ui.backup.NPDataRestoreDialog;
import kr.co.nexon.toy.android.ui.baseplate.NPUserInfoDialog;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.android.ui.common.NXPFullAlertDialog;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyAuthManager {
    private final Context applicationContext;
    private boolean isGcidSignup = false;
    private NXToyLocaleManager localeManager;
    private NXToySession prevSession;

    /* JADX INFO: renamed from: kr.co.nexon.npaccount.auth.NXToyAuthManager$20, reason: invalid class name */
    class AnonymousClass20 implements NPAuthListener {
        final /* synthetic */ NPListener val$listener;
        final /* synthetic */ NXToySession val$session;
        final /* synthetic */ NPTwitter val$twitterAuthPlugin;
        final /* synthetic */ int val$twitterLoginType;

        AnonymousClass20(NPListener nPListener, NPTwitter nPTwitter, int i, NXToySession nXToySession) {
            this.val$listener = nPListener;
            this.val$twitterAuthPlugin = nPTwitter;
            this.val$twitterLoginType = i;
            this.val$session = nXToySession;
        }

        @Override // kr.co.nexon.android.sns.NPAuthListener
        public void onResult(int i, String str, Bundle bundle) {
            if (i == NXToyErrorCode.SUCCESS.getCode()) {
                this.val$twitterAuthPlugin.getAccessToken(NXToyAuthManager.this.applicationContext, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.20.1
                    @Override // kr.co.nexon.android.sns.NPAuthListener
                    public void onResult(int i2, String str2, Bundle bundle2) {
                        final String id = AnonymousClass20.this.val$twitterAuthPlugin.getID();
                        final String str3 = bundle2.getString(NPAuthPlugin.KEY_ACCESSTOKEN) + "," + bundle2.getString(NPAuthPlugin.KEY_SECRETTOKEN);
                        if (id == null || str3 == null) {
                            AnonymousClass20.this.val$listener.onResult(new NXToyNpsnResult(NXToyErrorCode.INVALID_ARGS.getCode(), "ID or password error", ""));
                        } else {
                            NXToyRequestPostman.getInstance().postRequest(new NXToyGetNPSNRequest(id, str3, AnonymousClass20.this.val$twitterLoginType), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.20.1.1
                                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                                public void onComplete(NXToyResult nXToyResult) {
                                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                                        AnonymousClass20.this.val$listener.onResult(nXToyResult);
                                        return;
                                    }
                                    HashMap map = new HashMap();
                                    map.put("id", id);
                                    map.put("pw", str3);
                                    map.put(NPUserInfoDialog.KEY_LOGIN_TYPE, Integer.valueOf(AnonymousClass20.this.val$twitterLoginType));
                                    AnonymousClass20.this.val$session.setTempUserLoginInfoForNpsn(((NXToyNpsnResult) nXToyResult).result.npsn, map);
                                    AnonymousClass20.this.val$listener.onResult(nXToyResult);
                                }
                            });
                        }
                    }
                });
                return;
            }
            ToyLog.d("" + str);
            NXToyResult nXToyResult = new NXToyResult(i, NXToyAuthManager.this.localeManager.getString(R.string.npres_loginfailed), str);
            nXToyResult.requestTag = NXToyRequestTag.GetNpsnWithTwitter.getValue();
            this.val$listener.onResult(nXToyResult);
        }
    }

    /* JADX INFO: renamed from: kr.co.nexon.npaccount.auth.NXToyAuthManager$3, reason: invalid class name */
    class AnonymousClass3 implements NXToyRequestListener {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ int val$currentLoginType;
        final /* synthetic */ NPListener val$listener;
        final /* synthetic */ NPListener val$listenerWrapperForNgsm;
        final /* synthetic */ NXToySession val$session;
        final /* synthetic */ NXToySessionManager val$sessionManager;

        /* JADX INFO: renamed from: kr.co.nexon.npaccount.auth.NXToyAuthManager$3$4, reason: invalid class name */
        class AnonymousClass4 implements NPAuthListener {
            final /* synthetic */ NPAuthPlugin val$googleGameAuth;
            final /* synthetic */ NXToyUserInfoResult val$userInfoResult;
            final /* synthetic */ NXToyUserInfoResult.ResultSet val$userInfoResultSet;

            AnonymousClass4(NPAuthPlugin nPAuthPlugin, NXToyUserInfoResult nXToyUserInfoResult, NXToyUserInfoResult.ResultSet resultSet) {
                this.val$googleGameAuth = nPAuthPlugin;
                this.val$userInfoResult = nXToyUserInfoResult;
                this.val$userInfoResultSet = resultSet;
            }

            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str, final Bundle bundle) {
                String gcId = ((NPGoogleGame) this.val$googleGameAuth).getGcId();
                NXToyUserInfo nXToyUserInfo = this.val$userInfoResult.result.npsnUserInfo;
                if (i != NXToyErrorCode.SUCCESS.getCode() && NXStringUtil.isNull(gcId)) {
                    this.val$userInfoResult.errorCode = 0;
                    this.val$userInfoResult.errorText = "Success";
                    this.val$userInfoResult.errorDetail = "";
                } else if (!AnonymousClass3.this.val$session.getGcId().equals(gcId)) {
                    AnonymousClass3.this.val$activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.3.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            new NXPAlertDialog.Builder(AnonymousClass3.this.val$activity).setMessage(String.format(NXToyAuthManager.this.localeManager.getString(R.string.npres_gcid_change_player_message), bundle.getString(NPAuthPlugin.KEY_NAME, ""))).setPositiveButton(NXToyAuthManager.this.localeManager.getString(R.string.yes), new NXClickListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.3.4.1.3
                                @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                                protected void onSwallowClick(View view) {
                                    AnonymousClass3.this.val$sessionManager.removeSession();
                                    NXPSNSManager.getInstance().disconnectAll(NXToyAuthManager.this.applicationContext);
                                    NXToyUserInfoResult nXToyUserInfoResult = new NXToyUserInfoResult(NXToyErrorCode.TOYSERVER_AUTH_NOT_VALID.getCode(), "User attempt to login with another GooglePlayGame ID.", NXToyAuthManager.this.localeManager.getString(R.string.npres_get_userinfo_fail));
                                    nXToyUserInfoResult.requestTag = NXToyRequestTag.GetUserInfo.getValue();
                                    if (AnonymousClass3.this.val$listener != null) {
                                        AnonymousClass3.this.val$listener.onResult(nXToyUserInfoResult);
                                    }
                                }
                            }).setNegativeButton(NXToyAuthManager.this.localeManager.getString(R.string.no), new NXClickListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.3.4.1.2
                                @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                                protected void onSwallowClick(View view) {
                                    AnonymousClass4.this.val$userInfoResultSet.npsnUserInfo.agcId = AnonymousClass4.this.val$userInfoResult.result.npsnUserInfo.agcId;
                                    AnonymousClass4.this.val$userInfoResultSet.npsnUserInfo.gcid = AnonymousClass3.this.val$session.getGpgId();
                                    AnonymousClass4.this.val$userInfoResultSet.npsnUserInfo.name = AnonymousClass3.this.val$session.getName();
                                    AnonymousClass4.this.val$userInfoResultSet.npsnUserInfo.npsn = AnonymousClass3.this.val$session.getNpsn();
                                    if (AnonymousClass3.this.val$listener != null) {
                                        AnonymousClass3.this.val$listener.onResult(AnonymousClass4.this.val$userInfoResult);
                                    }
                                    if (AnonymousClass3.this.val$currentLoginType == NXToyLoginType.LoginTypeGameCenter.getValue() && AnonymousClass4.this.val$userInfoResult.result.doToast) {
                                        NXToyAuthManager.this.showUserInfoToast(NXToyAuthManager.this.applicationContext, NXToyLoginType.LoginTypeGameCenter.getValue(), "");
                                    }
                                }
                            }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.3.4.1.1
                                @Override // android.content.DialogInterface.OnCancelListener
                                public void onCancel(DialogInterface dialogInterface) {
                                    AnonymousClass4.this.val$userInfoResultSet.npsnUserInfo.agcId = AnonymousClass4.this.val$userInfoResult.result.npsnUserInfo.agcId;
                                    AnonymousClass4.this.val$userInfoResultSet.npsnUserInfo.gcid = AnonymousClass3.this.val$session.getGpgId();
                                    AnonymousClass4.this.val$userInfoResultSet.npsnUserInfo.name = AnonymousClass3.this.val$session.getName();
                                    AnonymousClass4.this.val$userInfoResultSet.npsnUserInfo.npsn = AnonymousClass3.this.val$session.getNpsn();
                                    if (AnonymousClass3.this.val$listener != null) {
                                        AnonymousClass3.this.val$listener.onResult(AnonymousClass4.this.val$userInfoResult);
                                    }
                                    if (AnonymousClass3.this.val$currentLoginType == NXToyLoginType.LoginTypeGameCenter.getValue() && AnonymousClass4.this.val$userInfoResult.result.doToast) {
                                        NXToyAuthManager.this.showUserInfoToast(NXToyAuthManager.this.applicationContext, NXToyLoginType.LoginTypeGameCenter.getValue(), "");
                                    }
                                }
                            }).create().show();
                        }
                    });
                    return;
                }
                AnonymousClass3.this.val$session.setAgcId(nXToyUserInfo.agcId);
                this.val$userInfoResultSet.npsnUserInfo.npsn = AnonymousClass3.this.val$session.getNpsn();
                this.val$userInfoResultSet.npsnUserInfo.memType = AnonymousClass3.this.val$currentLoginType;
                this.val$userInfoResultSet.npsnUserInfo.gpgId = AnonymousClass3.this.val$session.getGpgId();
                this.val$userInfoResultSet.npsnUserInfo.agcId = this.val$userInfoResult.result.npsnUserInfo.agcId;
                this.val$userInfoResultSet.npsnUserInfo.gcid = AnonymousClass3.this.val$session.getGpgId();
                this.val$userInfoResultSet.npsnUserInfo.name = AnonymousClass3.this.val$session.getName();
                if (AnonymousClass3.this.val$listener != null) {
                    AnonymousClass3.this.val$listener.onResult(this.val$userInfoResult);
                }
                if (AnonymousClass3.this.val$currentLoginType == NXToyLoginType.LoginTypeGameCenter.getValue() && this.val$userInfoResult.result.doToast) {
                    NXToyAuthManager.this.showUserInfoToast(NXToyAuthManager.this.applicationContext, NXToyLoginType.LoginTypeGameCenter.getValue(), "");
                }
            }
        }

        AnonymousClass3(NXToySession nXToySession, NPListener nPListener, int i, Activity activity, NXToySessionManager nXToySessionManager, NPListener nPListener2) {
            this.val$session = nXToySession;
            this.val$listener = nPListener;
            this.val$currentLoginType = i;
            this.val$activity = activity;
            this.val$sessionManager = nXToySessionManager;
            this.val$listenerWrapperForNgsm = nPListener2;
        }

        @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
        public void onComplete(NXToyResult nXToyResult) {
            final NXToyUserInfoResult nXToyUserInfoResult = (NXToyUserInfoResult) nXToyResult;
            nXToyUserInfoResult.result.npsn = this.val$session.getNpsn();
            nXToyUserInfoResult.result.npToken = this.val$session.getNptoken();
            nXToyUserInfoResult.result.npsnString = String.valueOf(this.val$session.getNpsn());
            if (nXToyUserInfoResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                if (NXToyAuthManager.this.isAuthCrashError(nXToyUserInfoResult.errorCode)) {
                    NXPSNSManager.getInstance().disconnectAll(NXToyAuthManager.this.applicationContext);
                }
                if (this.val$listener != null) {
                    this.val$listener.onResult(nXToyUserInfoResult);
                }
                return;
            }
            NXToyCommonPreferenceController.getInstance().setPushAgree(nXToyUserInfoResult.result.pushAgree);
            final NXToyUserInfoResult.ResultSet resultSet = nXToyUserInfoResult.result;
            this.val$session.setNpaCode(resultSet.npaCode);
            NXToyNexonUserInfo nXToyNexonUserInfo = resultSet.nkUserInfo;
            if (nXToyNexonUserInfo != null) {
                nXToyNexonUserInfo.NexonSNString = String.valueOf(nXToyNexonUserInfo.NexonSN);
            }
            switch (AnonymousClass48.$SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.convertIntLoginTypeToEnumLoginType(this.val$currentLoginType).ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                    NXPSNSManager.getInstance().getUserInfo(this.val$currentLoginType, this.val$activity, new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.3.1
                        @Override // kr.co.nexon.toy.listener.NPListener
                        public void onResult(NXToyResult nXToyResult2) {
                            switch (AnonymousClass48.$SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(nXToyResult2.errorCode).ordinal()]) {
                                case 4:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                    AnonymousClass3.this.val$sessionManager.removeSession();
                                    NXPSNSManager.getInstance().disconnectAll(NXToyAuthManager.this.applicationContext);
                                    if (AnonymousClass3.this.val$listener != null) {
                                        AnonymousClass3.this.val$listener.onResult(new NXToyUserInfoResult(NXToyErrorCode.TOYSERVER_AUTH_NOT_VALID.getCode(), NXToyAuthManager.this.localeManager.getString(R.string.npres_get_userinfo_fail), "snstype:" + AnonymousClass3.this.val$currentLoginType + " error:" + nXToyResult2.errorDetail));
                                    }
                                    break;
                                case 5:
                                default:
                                    if (nXToyResult2.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                                        AnonymousClass3.this.val$sessionManager.setSnsUserinfo(((NXToyUserInfoResult) nXToyResult2).result.npsnUserInfo);
                                    }
                                    nXToyUserInfoResult.result.npsnUserInfo = AnonymousClass3.this.val$sessionManager.getSnsUserinfo();
                                    final NXToyUserInfo nXToyUserInfo = nXToyUserInfoResult.result.npsnUserInfo;
                                    if (nXToyUserInfo.memType == 0 || nXToyUserInfo.npsn == 0) {
                                        nXToyUserInfoResult.errorCode = NXToyErrorCode.TOYSERVER_AUTH_NOT_VALID.getCode();
                                        nXToyUserInfoResult.errorText = NXToyAuthManager.this.localeManager.getString(R.string.npres_get_userinfo_fail);
                                        nXToyUserInfoResult.errorDetail = "stored npsnUserInfo empty.";
                                        NXPSNSManager.getInstance().disconnectAll(NXToyAuthManager.this.applicationContext);
                                        if (AnonymousClass3.this.val$listener != null) {
                                            AnonymousClass3.this.val$listener.onResult(nXToyUserInfoResult);
                                        }
                                        break;
                                    } else if (NXToyLoginType.convertIntLoginTypeToEnumLoginType(AnonymousClass3.this.val$currentLoginType) == NXToyLoginType.LoginTypeFaceBook) {
                                        ((NPFacebook) NXToyAuthManager.this.findAuthPlugin(AnonymousClass3.this.val$currentLoginType)).getBusinessToken(NXToyAuthManager.this.applicationContext, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.3.1.1
                                            @Override // kr.co.nexon.android.sns.NPAuthListener
                                            public void onResult(int i, String str, Bundle bundle) {
                                                switch (AnonymousClass48.$SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(i).ordinal()]) {
                                                    case 4:
                                                    case 6:
                                                        AnonymousClass3.this.val$sessionManager.removeSession();
                                                        NXPSNSManager.getInstance().disconnectAll(NXToyAuthManager.this.applicationContext);
                                                        if (AnonymousClass3.this.val$listener != null) {
                                                            AnonymousClass3.this.val$listener.onResult(new NXToyUserInfoResult(NXToyErrorCode.TOYSERVER_AUTH_NOT_VALID.getCode(), NXToyAuthManager.this.localeManager.getString(R.string.npres_get_userinfo_fail), "snstype:" + AnonymousClass3.this.val$currentLoginType + " error:" + str));
                                                        }
                                                        break;
                                                    case 10:
                                                        AnonymousClass3.this.val$sessionManager.removeSession();
                                                        NXPSNSManager.getInstance().disconnectAll(NXToyAuthManager.this.applicationContext);
                                                        if (AnonymousClass3.this.val$listener != null) {
                                                            AnonymousClass3.this.val$listener.onResult(new NXToyUserInfoResult(i, NXToyAuthManager.this.localeManager.getString(R.string.npres_fbnot_associated_business), "snstype:" + AnonymousClass3.this.val$currentLoginType + " error:" + str));
                                                        }
                                                        break;
                                                    default:
                                                        if (bundle != null && bundle.containsKey(NPAuthPlugin.KEY_FB_BIZ_TOKEN)) {
                                                            nXToyUserInfo.fbBizToken = bundle.getString(NPAuthPlugin.KEY_FB_BIZ_TOKEN, "");
                                                            NXToyRequestPostman.getInstance().postRequest(new NXPRegisterFbBizTokenRequest(nXToyUserInfo.subID, nXToyUserInfo.fbBizToken), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.3.1.1.1
                                                                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                                                                public void onComplete(NXToyResult nXToyResult3) {
                                                                    ToyLog.d("Register facebook business token result : " + nXToyResult3.toString());
                                                                }
                                                            });
                                                        }
                                                        AnonymousClass3.this.val$listenerWrapperForNgsm.onResult(nXToyUserInfoResult);
                                                        if (nXToyUserInfoResult.errorCode == NXToyErrorCode.SUCCESS.getCode() && nXToyUserInfoResult.result.doToast) {
                                                            NXToyAuthManager.this.showUserInfoToast(NXToyAuthManager.this.applicationContext, AnonymousClass3.this.val$currentLoginType, nXToyUserInfoResult.result.npsnUserInfo.name);
                                                            break;
                                                        }
                                                        break;
                                                }
                                            }
                                        });
                                        break;
                                    } else {
                                        AnonymousClass3.this.val$listenerWrapperForNgsm.onResult(nXToyUserInfoResult);
                                        if (nXToyUserInfoResult.errorCode == NXToyErrorCode.SUCCESS.getCode() && nXToyUserInfoResult.result.doToast) {
                                            NXToyAuthManager.this.showUserInfoToast(NXToyAuthManager.this.applicationContext, AnonymousClass3.this.val$currentLoginType, nXToyUserInfoResult.result.npsnUserInfo.name);
                                            break;
                                        }
                                    }
                                    break;
                            }
                        }
                    });
                    break;
                case 5:
                    ToyLog.d("NexonCom");
                    NXToyNexonUserInfo nXToyNexonUserInfo2 = nXToyUserInfoResult.result.nkUserInfo;
                    resultSet.npsnUserInfo = new NXToyUserInfo();
                    resultSet.npsnUserInfo.memType = this.val$currentLoginType;
                    resultSet.npsnUserInfo.subID = "" + nXToyNexonUserInfo2.NexonSN;
                    resultSet.npsnUserInfo.name = nXToyNexonUserInfo2.NickName;
                    resultSet.npsnUserInfo.pictureUrl = nXToyNexonUserInfo2.NexonProfileThumbnailImageUrl_130;
                    resultSet.npsnUserInfo.npsn = this.val$session.getNpsn();
                    this.val$listenerWrapperForNgsm.onResult(nXToyUserInfoResult);
                    if (nXToyUserInfoResult.result.doToast) {
                        NXToyAuthManager.this.showUserInfoToast(NXToyAuthManager.this.applicationContext, NXToyLoginType.LoginTypeNXCom.getValue(), resultSet.npsnUserInfo.name);
                    }
                    break;
                case 6:
                case 7:
                    ToyLog.d("NaverChannel");
                    this.val$session.setAccountId(resultSet.npsnUserInfo.memID);
                    this.val$sessionManager.saveSession();
                    this.val$listenerWrapperForNgsm.onResult(nXToyUserInfoResult);
                    break;
                case 8:
                    this.val$session.setAccountId(resultSet.nkUserInfo.NickName);
                    this.val$sessionManager.saveSession();
                    this.val$listenerWrapperForNgsm.onResult(nXToyUserInfoResult);
                    break;
                case 9:
                    ToyLog.d("Email");
                    NPAuthPlugin nPAuthPluginFindAuthPlugin = NXToyAuthManager.this.findAuthPlugin(NXToyLoginType.LoginTypeEmail.getValue());
                    nPAuthPluginFindAuthPlugin.setExtraData(NXJsonUtil.toJsonString(this.val$session));
                    nPAuthPluginFindAuthPlugin.getUserInfo(NXToyAuthManager.this.applicationContext, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.3.2
                        @Override // kr.co.nexon.android.sns.NPAuthListener
                        public void onResult(int i, String str, Bundle bundle) {
                            if (i != NXToyErrorCode.SUCCESS.getCode()) {
                                AnonymousClass3.this.val$sessionManager.removeSession();
                                NXPSNSManager.getInstance().disconnectAll(NXToyAuthManager.this.applicationContext);
                                NXToyUserInfoResult nXToyUserInfoResult2 = new NXToyUserInfoResult(NXToyErrorCode.TOYSERVER_AUTH_NOT_VALID.getCode(), NXToyAuthManager.this.localeManager.getString(R.string.npres_get_userinfo_fail), "email:" + str);
                                if (AnonymousClass3.this.val$listener != null) {
                                    AnonymousClass3.this.val$listener.onResult(nXToyUserInfoResult2);
                                    return;
                                }
                                return;
                            }
                            resultSet.npsnUserInfo.email = bundle.getString(NPAuthPlugin.KEY_EMAIL);
                            resultSet.npsnUserInfo.subID = bundle.getString(NPAuthPlugin.KEY_ID);
                            AnonymousClass3.this.val$listenerWrapperForNgsm.onResult(nXToyUserInfoResult);
                            if (nXToyUserInfoResult.result.doToast) {
                                NXToyAuthManager.this.showUserInfoToast(NXToyAuthManager.this.applicationContext, NXToyLoginType.LoginTypeEmail.getValue(), resultSet.npsnUserInfo.email);
                            }
                        }
                    });
                    break;
                case 10:
                    ((NPNexonNet) NXToyAuthManager.this.findAuthPlugin(NXToyLoginType.LoginTypeNXNet.getValue())).getUserInfo(NXToyAuthManager.this.applicationContext, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.3.3
                        @Override // kr.co.nexon.android.sns.NPAuthListener
                        public void onResult(int i, String str, Bundle bundle) {
                            ToyLog.d("NXMP SDK : " + i + " / " + str);
                            if (i == NXToyErrorCode.NXNET_INVALID_ACCESS_TOKEN.getCode() || i == NXToyErrorCode.NXNET_INVALID_REFRESH_TOKEN.getCode() || i == NXToyErrorCode.NXNET_EXPIRE_ACCESS_TOKEN.getCode()) {
                                AnonymousClass3.this.val$sessionManager.removeSession();
                                NXToyUserInfoResult nXToyUserInfoResult2 = new NXToyUserInfoResult(NXToyErrorCode.TOYSERVER_AUTH_NOT_VALID.getCode(), NXToyAuthManager.this.localeManager.getString(R.string.npres_get_userinfo_fail), "NXNet:" + str);
                                if (AnonymousClass3.this.val$listener != null) {
                                    AnonymousClass3.this.val$listener.onResult(nXToyUserInfoResult2);
                                    return;
                                }
                                return;
                            }
                            if (i != NXToyErrorCode.SUCCESS.getCode()) {
                                if (AnonymousClass3.this.val$listener != null) {
                                    AnonymousClass3.this.val$listener.onResult(new NXToyUserInfoResult(i, NXToyAuthManager.this.localeManager.getString(R.string.npres_get_userinfo_fail), "NXNet:" + str));
                                    return;
                                }
                                return;
                            }
                            resultSet.npsnUserInfo.name = bundle.getString(NPAuthPlugin.KEY_NAME);
                            resultSet.npsnUserInfo.subID = bundle.getString(NPAuthPlugin.KEY_ID);
                            AnonymousClass3.this.val$listenerWrapperForNgsm.onResult(nXToyUserInfoResult);
                            if (nXToyUserInfoResult.result.doToast) {
                                NXToyAuthManager.this.showUserInfoToast(NXToyAuthManager.this.applicationContext, NXToyLoginType.LoginTypeNXNet.getValue(), resultSet.npsnUserInfo.name);
                            }
                        }
                    });
                    break;
                case 11:
                    ToyLog.d("ETC " + this.val$currentLoginType);
                    NPAuthPlugin nPAuthPluginFindAuthPlugin2 = NXToyAuthManager.this.findAuthPlugin(this.val$currentLoginType);
                    nPAuthPluginFindAuthPlugin2.getUserInfo(NXToyAuthManager.this.applicationContext, new AnonymousClass4(nPAuthPluginFindAuthPlugin2, nXToyUserInfoResult, resultSet));
                    break;
                default:
                    ToyLog.d("ETC " + this.val$currentLoginType);
                    resultSet.npsnUserInfo = new NXToyUserInfo();
                    resultSet.npsnUserInfo.memType = this.val$currentLoginType;
                    resultSet.npsnUserInfo.subID = "";
                    resultSet.npsnUserInfo.name = "";
                    resultSet.npsnUserInfo.pictureUrl = "";
                    resultSet.npsnUserInfo.npsn = this.val$session.getNpsn();
                    this.val$listenerWrapperForNgsm.onResult(nXToyUserInfoResult);
                    if (this.val$currentLoginType == NXToyLoginType.LoginTypeGuest.getValue() && nXToyUserInfoResult.result.doToast) {
                        NXToyAuthManager.this.showUserInfoToast(NXToyAuthManager.this.applicationContext, NXToyLoginType.LoginTypeGuest.getValue(), "");
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: kr.co.nexon.npaccount.auth.NXToyAuthManager$36, reason: invalid class name */
    class AnonymousClass36 implements Runnable {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ NPListener val$listener;
        final /* synthetic */ int val$loginType;
        final /* synthetic */ NXToySession val$session;

        /* JADX INFO: renamed from: kr.co.nexon.npaccount.auth.NXToyAuthManager$36$1, reason: invalid class name */
        class AnonymousClass1 implements NPAuthListener {
            final /* synthetic */ NPAuthPlugin val$authPlugin;

            AnonymousClass1(NPAuthPlugin nPAuthPlugin) {
                this.val$authPlugin = nPAuthPlugin;
            }

            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str, Bundle bundle) {
                ToyLog.d("authPlugin.login result", NxLogInfo.KEY_ERROR_CODE, Integer.valueOf(i), "errorDetail", str, TJAdUnitConstants.String.BUNDLE, bundle);
                final NXToyRequestTag typeFromLoginType = NXToyRequestTag.getTypeFromLoginType(AnonymousClass36.this.val$loginType);
                if (AnonymousClass36.this.val$loginType == NXToyLoginType.LoginTypeNXCom.getValue() || AnonymousClass36.this.val$loginType == NXToyLoginType.LoginTypeEmail.getValue()) {
                    if (i == 90201 || i == 90201) {
                        NXToyLoginResult nXToyLoginResult = new NXToyLoginResult(NXToyErrorCode.LOGIN_USER_CANCELED.getCode(), NXToyAuthManager.this.localeManager.getString(R.string.npres_logincancel), NXToyAuthManager.this.localeManager.getString(R.string.npres_logincancel));
                        nXToyLoginResult.requestTag = typeFromLoginType.getValue();
                        AnonymousClass36.this.val$listener.onResult(nXToyLoginResult);
                        return;
                    }
                    NXToyLoginResult nXToyLoginResult2 = (NXToyLoginResult) NXJsonUtil.fromObject(bundle.getString("toyresult"), NXToyLoginResult.class);
                    nXToyLoginResult2.requestTag = typeFromLoginType.getValue();
                    if (i != NXToyErrorCode.SUCCESS.getCode()) {
                        NXToySession nXToySession = (NXToySession) NXJsonUtil.fromObject(bundle.getString("toysession"), NXToySession.class);
                        if (nXToySession != null) {
                            if (nXToySession.getTempUserLoginInfo() != null) {
                                nXToySession.getTempUserLoginInfo().put("withdrawExpiresIn", Integer.valueOf(nXToyLoginResult2.result.withdrawExpiresIn));
                            }
                            AnonymousClass36.this.val$session.setTempUserLoginInfo(nXToySession.getTempUserLoginInfo());
                            NXToySessionManager.getInstance().saveSession();
                        }
                        AnonymousClass36.this.val$listener.onResult(nXToyLoginResult2);
                        return;
                    }
                    if (NXToySessionManager.getInstance().getSession().getType() == NXToyLoginType.LoginTypeGuest.getValue() && (nXToyLoginResult2.result.loginResultType == 0 || nXToyLoginResult2.result.loginResultType == 1)) {
                        nXToyLoginResult2.result.loginResultType = 1;
                    }
                    NXToySession nXToySession2 = (NXToySession) NXJsonUtil.fromObject(bundle.getString("toysession"), NXToySession.class);
                    NXToyAuthManager.this.prevSession = nXToySession2;
                    NXToySessionManager.getInstance().setSession(nXToySession2);
                    NXToyAuthManager.this.showTerms(AnonymousClass36.this.val$activity, nXToyLoginResult2, AnonymousClass36.this.val$listener);
                    return;
                }
                if (i == NXToyErrorCode.SUCCESS.getCode()) {
                    AnonymousClass36.this.val$session.setExternalAuthId(bundle.getString(NPAuthPlugin.KEY_ID));
                    AnonymousClass36.this.val$session.setExternalAuthToken(bundle.getString(NPAuthPlugin.KEY_ACCESSTOKEN));
                    NXToySessionManager.getInstance().saveSession();
                    final String string = bundle.getString(NPAuthPlugin.KEY_EMAIL, null);
                    if (AnonymousClass36.this.val$loginType != NXToyLoginType.LoginTypeFaceBook.getValue()) {
                        NXToyAuthManager.this.postSignInRequest(AnonymousClass36.this.val$activity, AnonymousClass36.this.val$session.getExternalAuthId(), AnonymousClass36.this.val$session.getExternalAuthToken(), AnonymousClass36.this.val$loginType, string, bundle.getString(NPAuthPlugin.KEY_FB_BIZ_TOKEN, null), AnonymousClass36.this.val$listener);
                        return;
                    } else {
                        ((NPFacebook) this.val$authPlugin).getBusinessToken(NXToyAuthManager.this.applicationContext, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.36.1.2
                            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                            @Override // kr.co.nexon.android.sns.NPAuthListener
                            public void onResult(int i2, String str2, Bundle bundle2) {
                                if (i2 == NXToyErrorCode.SUCCESS.getCode()) {
                                    String string2 = bundle2.getString(NPAuthPlugin.KEY_FB_BIZ_TOKEN, null);
                                    if (bundle2 != null && bundle2.containsKey(NPAuthPlugin.KEY_FB_BIZ_TOKEN)) {
                                        string2 = bundle2.getString(NPAuthPlugin.KEY_FB_BIZ_TOKEN, "");
                                    }
                                    NXToyAuthManager.this.postSignInRequest(AnonymousClass36.this.val$activity, AnonymousClass36.this.val$session.getExternalAuthId(), AnonymousClass36.this.val$session.getExternalAuthToken(), AnonymousClass36.this.val$loginType, string, string2, AnonymousClass36.this.val$listener);
                                    return;
                                }
                                final NXToyLoginResult nXToyLoginResult3 = new NXToyLoginResult();
                                nXToyLoginResult3.requestTag = typeFromLoginType.getValue();
                                switch (AnonymousClass48.$SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(i2).ordinal()]) {
                                    case 4:
                                    case 6:
                                        NXToySessionManager.getInstance().removeSession();
                                        NXPSNSManager.getInstance().disconnectAll(NXToyAuthManager.this.applicationContext);
                                        nXToyLoginResult3.errorCode = i2;
                                        nXToyLoginResult3.errorText = NXToyAuthManager.this.localeManager.getString(R.string.npres_loginfailed);
                                        nXToyLoginResult3.errorDetail = str2;
                                        break;
                                    case 10:
                                        NXToySessionManager.getInstance().removeSession();
                                        NXPSNSManager.getInstance().disconnectAll(NXToyAuthManager.this.applicationContext);
                                        nXToyLoginResult3.errorCode = i2;
                                        nXToyLoginResult3.errorText = NXToyAuthManager.this.localeManager.getString(R.string.npres_fbnot_associated_business);
                                        nXToyLoginResult3.errorDetail = str2;
                                        break;
                                    default:
                                        nXToyLoginResult3.errorCode = i2;
                                        nXToyLoginResult3.errorText = NXToyAuthManager.this.localeManager.getString(R.string.npres_loginfailed);
                                        nXToyLoginResult3.errorDetail = str2;
                                        break;
                                }
                                AnonymousClass1.this.val$authPlugin.logout(NXToyAuthManager.this.applicationContext, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.36.1.2.1
                                    @Override // kr.co.nexon.android.sns.NPAuthListener
                                    public void onResult(int i3, String str3, Bundle bundle3) {
                                        AnonymousClass36.this.val$listener.onResult(nXToyLoginResult3);
                                    }
                                });
                            }
                        });
                        return;
                    }
                }
                if (i == NXToyErrorCode.FACEBOOK_INVALID_APPID_OR_HASHKEY.getCode()) {
                    ToyLog.d("errorCode : " + NXToyErrorCode.FACEBOOK_INVALID_APPID_OR_HASHKEY.getCode());
                    ToyLog.d("errorText : " + NXToyAuthManager.this.localeManager.getString(R.string.npres_fbappid_or_hashkey_invalid));
                }
                final NXToyLoginResult nXToyLoginResult3 = new NXToyLoginResult();
                nXToyLoginResult3.requestTag = typeFromLoginType.getValue();
                switch (AnonymousClass48.$SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(i).ordinal()]) {
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                        nXToyLoginResult3.errorCode = NXToyErrorCode.LOGIN_USER_CANCELED.getCode();
                        nXToyLoginResult3.errorText = NXToyAuthManager.this.localeManager.getString(R.string.npres_logincancel);
                        nXToyLoginResult3.errorDetail = str;
                        break;
                    default:
                        nXToyLoginResult3.errorCode = i;
                        nXToyLoginResult3.errorText = NXToyAuthManager.this.localeManager.getString(R.string.npres_loginfailed);
                        nXToyLoginResult3.errorDetail = str;
                        break;
                }
                this.val$authPlugin.logout(NXToyAuthManager.this.applicationContext, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.36.1.1
                    @Override // kr.co.nexon.android.sns.NPAuthListener
                    public void onResult(int i2, String str2, Bundle bundle2) {
                        AnonymousClass36.this.val$listener.onResult(nXToyLoginResult3);
                    }
                });
            }
        }

        AnonymousClass36(int i, NPListener nPListener, NXToySession nXToySession, Activity activity) {
            this.val$loginType = i;
            this.val$listener = nPListener;
            this.val$session = nXToySession;
            this.val$activity = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            NPAuthPlugin nPAuthPluginFindAuthPlugin = NXToyAuthManager.this.findAuthPlugin(this.val$loginType);
            if (nPAuthPluginFindAuthPlugin != null) {
                nPAuthPluginFindAuthPlugin.setExtraData(NXJsonUtil.toJsonString(this.val$session));
                nPAuthPluginFindAuthPlugin.login(this.val$activity, new AnonymousClass1(nPAuthPluginFindAuthPlugin));
            } else if (this.val$listener != null) {
                this.val$listener.onResult(new NXToyLoginResult(NXToyErrorCode.UNSUPPORTED.getCode(), NXToyAuthManager.this.localeManager.getString(R.string.npres_loginfailed), NXToyAuthManager.this.localeManager.getString(R.string.npres_daumchn_unsupported_response_type), NXToyRequestTag.getTypeFromLoginType(this.val$loginType).getValue()));
            }
        }
    }

    /* JADX INFO: renamed from: kr.co.nexon.npaccount.auth.NXToyAuthManager$40, reason: invalid class name */
    class AnonymousClass40 implements NPListener {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ NPListener val$agreeTermsListener;
        final /* synthetic */ NPListener val$listener;
        final /* synthetic */ NXToyLoginResult val$loginResult;
        final /* synthetic */ NXPPolicyManager val$policyManager;
        final /* synthetic */ NXToySession val$session;

        AnonymousClass40(NXToyLoginResult nXToyLoginResult, NXToySession nXToySession, Activity activity, NXPPolicyManager nXPPolicyManager, NPListener nPListener, NPListener nPListener2) {
            this.val$loginResult = nXToyLoginResult;
            this.val$session = nXToySession;
            this.val$activity = activity;
            this.val$policyManager = nXPPolicyManager;
            this.val$listener = nPListener;
            this.val$agreeTermsListener = nPListener2;
        }

        @Override // kr.co.nexon.toy.listener.NPListener
        public void onResult(NXToyResult nXToyResult) {
            NXPTermsManager nXPTermsManager = NXPTermsManager.getInstance();
            int i = -1;
            List<NXToyPolicy> list = ((NXToyPolicyResult) nXToyResult).result.policy;
            if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode() && list != null) {
                for (NXToyPolicy nXToyPolicy : list) {
                    if (nXToyPolicy.code == 2505) {
                        i = nXToyPolicy.isTermsAgree;
                    }
                }
            }
            List<NXToyTerm> list2 = this.val$loginResult.result.termsAgree;
            List<NXToyTerm> additionalTermsList = nXPTermsManager.getAdditionalTermsList(list2);
            if (additionalTermsList.size() == 0 && i != 0) {
                this.val$loginResult.requestTag = NXToyRequestTag.convertLoginTypeToRequestTag(this.val$session.getType());
                if (this.val$session.getType() == NXToyLoginType.LoginTypeGameCenter.getValue() && NXToyAuthManager.this.isGcidSignup) {
                    NXToyAuthManager.this.postLinkGCIDToNPSNRequest(this.val$activity, new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.40.1
                        @Override // kr.co.nexon.toy.listener.NPListener
                        public void onResult(NXToyResult nXToyResult2) {
                            AnonymousClass40.this.val$policyManager.setDefaultPushPolicyIfNotRegistered(AnonymousClass40.this.val$activity, new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.40.1.1
                                @Override // kr.co.nexon.toy.listener.NPListener
                                public void onResult(NXToyResult nXToyResult3) {
                                    NXToyAuthManager.this.setSnsEnable(NXToyAuthManager.this.applicationContext, AnonymousClass40.this.val$session.getType());
                                    NXToyAuthManager.this.handleToyResult(AnonymousClass40.this.val$listener, AnonymousClass40.this.val$loginResult);
                                }
                            });
                        }
                    });
                    return;
                } else {
                    this.val$policyManager.setDefaultPushPolicyIfNotRegistered(this.val$activity, new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.40.2
                        @Override // kr.co.nexon.toy.listener.NPListener
                        public void onResult(NXToyResult nXToyResult2) {
                            NXToyAuthManager.this.setSnsEnable(NXToyAuthManager.this.applicationContext, AnonymousClass40.this.val$session.getType());
                            NXToyAuthManager.this.handleToyResult(AnonymousClass40.this.val$listener, AnonymousClass40.this.val$loginResult);
                        }
                    });
                    return;
                }
            }
            NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
            if (nXToyCommonPreferenceController != null ? nXToyCommonPreferenceController.getIsTermsOfAgree() : false) {
                NXToyTermResult.ResultSet resultSet = (NXToyTermResult.ResultSet) NXJsonUtil.fromObject(nXToyCommonPreferenceController.getSaveTerms(), NXToyTermResult.ResultSet.class);
                additionalTermsList = nXPTermsManager.getAdditionalTermsList(list2, resultSet.terms);
                if (additionalTermsList.size() == 0) {
                    ToyLog.d(resultSet.terms.toString());
                    nXPTermsManager.agree(this.val$activity, resultSet.terms, new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.40.3
                        @Override // kr.co.nexon.toy.listener.NPListener
                        public void onResult(NXToyResult nXToyResult2) {
                            if (nXToyResult2.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                                Iterator<NXToyTerm> it = AnonymousClass40.this.val$loginResult.result.termsAgree.iterator();
                                while (it.hasNext()) {
                                    it.next().isAgree = 1;
                                }
                                NXToyAuthManager.this.setSnsEnable(NXToyAuthManager.this.applicationContext, AnonymousClass40.this.val$session.getType());
                                NXToyAuthManager.this.handleToyResult(AnonymousClass40.this.val$listener, AnonymousClass40.this.val$loginResult);
                                return;
                            }
                            NXToySessionManager.getInstance().setSession(NXToyAuthManager.this.prevSession);
                            AnonymousClass40.this.val$loginResult.errorCode = nXToyResult2.errorCode;
                            AnonymousClass40.this.val$loginResult.errorText = nXToyResult2.errorText;
                            AnonymousClass40.this.val$loginResult.errorDetail = nXToyResult2.errorDetail;
                            AnonymousClass40.this.val$agreeTermsListener.onResult(AnonymousClass40.this.val$loginResult);
                        }
                    });
                    return;
                }
            }
            nXPTermsManager.goTermsAgree(this.val$activity, additionalTermsList, false, this.val$agreeTermsListener);
        }
    }

    /* JADX INFO: renamed from: kr.co.nexon.npaccount.auth.NXToyAuthManager$43, reason: invalid class name */
    class AnonymousClass43 implements NPListener {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ NPListener val$agreeTermsListener;
        final /* synthetic */ NXToyCommonPreferenceController val$commonPrefCtl;
        final /* synthetic */ NPListener val$listener;
        final /* synthetic */ NXToyLoginResult val$loginResult;
        final /* synthetic */ List val$loginTermsList;
        final /* synthetic */ NXPPolicyManager val$policyManager;
        final /* synthetic */ NXToySession val$session;
        final /* synthetic */ NXPTermsManager val$termsManager;

        AnonymousClass43(NXPTermsManager nXPTermsManager, Activity activity, List list, NPListener nPListener, NXToyCommonPreferenceController nXToyCommonPreferenceController, NXPPolicyManager nXPPolicyManager, NXToySession nXToySession, NPListener nPListener2, NXToyLoginResult nXToyLoginResult) {
            this.val$termsManager = nXPTermsManager;
            this.val$activity = activity;
            this.val$loginTermsList = list;
            this.val$agreeTermsListener = nPListener;
            this.val$commonPrefCtl = nXToyCommonPreferenceController;
            this.val$policyManager = nXPPolicyManager;
            this.val$session = nXToySession;
            this.val$listener = nPListener2;
            this.val$loginResult = nXToyLoginResult;
        }

        @Override // kr.co.nexon.toy.listener.NPListener
        public void onResult(NXToyResult nXToyResult) {
            NXToyTermsListResult nXToyTermsListResult = (NXToyTermsListResult) nXToyResult;
            if (nXToyTermsListResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                this.val$termsManager.goTermsAgree(this.val$activity, this.val$loginTermsList, false, this.val$agreeTermsListener);
                return;
            }
            final List<NXToyTerm> list = nXToyTermsListResult.result.terms;
            if (this.val$commonPrefCtl.getIsTermsOfAgree() && this.val$termsManager.didCheckAllTerms(list)) {
                this.val$termsManager.agree(this.val$activity, list, new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.43.1
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult2) {
                        if (nXToyResult2.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                            AnonymousClass43.this.val$policyManager.setAllEnabledPolicy(AnonymousClass43.this.val$activity, list, new ArrayList(), new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.43.1.1
                                @Override // kr.co.nexon.toy.listener.NPListener
                                public void onResult(NXToyResult nXToyResult3) {
                                    NXToyAuthManager.this.setSnsEnable(NXToyAuthManager.this.applicationContext, AnonymousClass43.this.val$session.getType());
                                    NXToyAuthManager.this.handleToyResult(AnonymousClass43.this.val$listener, AnonymousClass43.this.val$loginResult);
                                }
                            });
                            return;
                        }
                        NXToySessionManager.getInstance().setSession(NXToyAuthManager.this.prevSession);
                        AnonymousClass43.this.val$loginResult.errorCode = nXToyResult2.errorCode;
                        AnonymousClass43.this.val$loginResult.errorText = nXToyResult2.errorText;
                        AnonymousClass43.this.val$loginResult.errorDetail = nXToyResult2.errorDetail;
                        AnonymousClass43.this.val$agreeTermsListener.onResult(AnonymousClass43.this.val$loginResult);
                    }
                });
            } else {
                this.val$termsManager.goTermsAgree(this.val$activity, list, false, this.val$agreeTermsListener);
            }
        }
    }

    public NXToyAuthManager(Context context) {
        this.applicationContext = context;
        this.localeManager = NXToyLocaleManager.getInstance(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelRecoverUser(final NPListener nPListener) {
        int[] iArr = {NXToyLoginType.LoginTypeGoogle.getValue(), NXToyLoginType.LoginTypeFaceBook.getValue(), NXToyLoginType.LoginTypeTwitter.getValue(), NXToyLoginType.LoginTypeVKontakte.getValue()};
        Map<String, Object> tempUserLoginInfo = NXToySessionManager.getInstance().getSession().getTempUserLoginInfo();
        if (tempUserLoginInfo == null) {
            ToyLog.d("sessionManager.session.tempUserLoginInfo is null");
            nPListener.onResult(new NXToyResult(NXToyErrorCode.RECOVERY_USER_CANCELED.getCode(), this.localeManager.getString(R.string.npres_withdrawal_recover_cancel_message), "", NXToyRequestTag.RecoverUser.getValue()));
            return;
        }
        Object obj = tempUserLoginInfo.get(NPUserInfoDialog.KEY_LOGIN_TYPE);
        int iIntValue = obj.getClass().getName().contains("Double") ? ((Double) obj).intValue() : ((Integer) obj).intValue();
        NPAuthPlugin nPAuthPluginFindAuthPlugin = null;
        for (int i : iArr) {
            if (i == iIntValue) {
                nPAuthPluginFindAuthPlugin = findAuthPlugin(i);
                ToyLog.d("found auth. loginType:" + i);
                break;
            }
        }
        if (nPAuthPluginFindAuthPlugin != null) {
            nPAuthPluginFindAuthPlugin.logout(this.applicationContext, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.45
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i2, String str, Bundle bundle) {
                    nPListener.onResult(new NXToyResult(NXToyErrorCode.RECOVERY_USER_CANCELED.getCode(), NXToyAuthManager.this.localeManager.getString(R.string.npres_withdrawal_recover_cancel_message), "", NXToyRequestTag.RecoverUser.getValue()));
                }
            });
        } else {
            ToyLog.d("auth not found.");
            nPListener.onResult(new NXToyResult(NXToyErrorCode.RECOVERY_USER_CANCELED.getCode(), this.localeManager.getString(R.string.npres_withdrawal_recover_cancel_message), "", NXToyRequestTag.RecoverUser.getValue()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAuthInfo(NPListener nPListener) {
        NXPSNSManager.getInstance().disconnectAll(this.applicationContext);
        NXToySessionManager.getInstance().removeSession();
        NPPromotionPrefCtl.getInstance(this.applicationContext).removeAll();
        if (nPListener != null) {
            nPListener.onResult(new NXToyResult(0, ""));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNameFromLoginType(int i) {
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeVKontakte:
                return this.localeManager.getString(R.string.nplogin_type_vkontakte_btn);
            case LoginTypeFaceBook:
                return this.localeManager.getString(R.string.nplogin_type_facebook_btn);
            case LoginTypeTwitter:
                return this.localeManager.getString(R.string.nplogin_type_twitter_btn);
            case LoginTypeGoogle:
                return this.localeManager.getString(R.string.nplogin_type_gplus_btn);
            case LoginTypeNXCom:
                return this.localeManager.getString(R.string.nplogin_type_nexon_btn);
            case LoginTypeNaverChannel:
            case LoginTypeNaver:
                return this.localeManager.getString(R.string.nplogin_type_naver_btn);
            case LoginTypeDaumChannel:
                return this.localeManager.getString(R.string.nplogin_type_daum_btn);
            case LoginTypeEmail:
                return this.localeManager.getString(R.string.nplogin_type_email_btn);
            case LoginTypeNXNet:
            default:
                return "";
            case LoginTypeGameCenter:
                return this.localeManager.getString(R.string.nplogin_type_gamecenter_btn);
            case LoginTypeGuest:
                return this.localeManager.getString(R.string.nplogin_type_guest_btn);
            case LoginTypeNotLogined:
                return this.localeManager.getString(R.string.nplogin_type_notlogined_btn);
            case LoginTypeDefault:
                return this.localeManager.getString(R.string.nplogin_type_default_btn);
            case LoginTypeKakao:
                return this.localeManager.getString(R.string.nplogin_type_kakao_btn);
            case LoginTypeMapleId:
                return this.localeManager.getString(R.string.nplogin_type_mapleid_btn);
            case LoginTypePlayPark:
                return this.localeManager.getString(R.string.nplogin_type_playpark_btn);
        }
    }

    private void getNexonSNFromWeb(Activity activity, final NPListener nPListener) {
        NPNexonCom nPNexonCom = (NPNexonCom) findAuthPlugin(NXToyLoginType.LoginTypeNXCom.getValue());
        nPNexonCom.setExtraData(NXJsonUtil.toJsonString(NXToySessionManager.getInstance().getSession()));
        nPNexonCom.getNexonSNFromWeb(activity, new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.25
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (nPListener != null) {
                    nPListener.onResult(nXToyResult);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleToyResult(NPListener nPListener, NXToyResult nXToyResult) {
        if (nPListener != null) {
            nPListener.onResult(nXToyResult);
        }
    }

    private boolean isLoginTPAMode(List<Integer> list) {
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        boolean z = false;
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().intValue() == NXToyLoginType.LoginTypeNXCom.getValue()) {
                z = true;
                break;
            }
        }
        return nXToyCommonPreferenceController.useTPA() && z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void login(final Activity activity, int i, final NPListener nPListener) {
        NXToySessionManager.getInstance().backupToLastSession();
        final NXToySession session = NXToySessionManager.getInstance().getSession();
        if (i == NXToyLoginType.LoginTypeGuest.getValue()) {
            if (NXPApplicationConfigManager.getInstance().isEnabledGuestLoginAlert()) {
                new NXPAlertDialog.Builder(activity).setMessage(this.localeManager.getString(R.string.npres_guest_login_alert)).setPositiveButton(this.localeManager.getString(R.string.confirm), new View.OnClickListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.33
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NXToyAuthManager.this.postSignInRequest(activity, "", "", NXToyLoginType.LoginTypeGuest.getValue(), null, null, nPListener);
                    }
                }).setNegativeButton(this.localeManager.getString(R.string.npres_cancel), new View.OnClickListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.32
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NXToyLoginResult nXToyLoginResult = new NXToyLoginResult(NXToyErrorCode.LOGIN_USER_CANCELED.getCode(), NXToyAuthManager.this.localeManager.getString(R.string.npres_logincancel), NXToyAuthManager.this.localeManager.getString(R.string.npres_logincancel));
                        nXToyLoginResult.requestTag = NXToyRequestTag.LoginWithGuest.getValue();
                        nPListener.onResult(nXToyLoginResult);
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.31
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        NXToyLoginResult nXToyLoginResult = new NXToyLoginResult(NXToyErrorCode.LOGIN_USER_CANCELED.getCode(), NXToyAuthManager.this.localeManager.getString(R.string.npres_logincancel), NXToyAuthManager.this.localeManager.getString(R.string.npres_logincancel));
                        nXToyLoginResult.requestTag = NXToyRequestTag.LoginWithGuest.getValue();
                        nPListener.onResult(nXToyLoginResult);
                    }
                }).create().show();
                return;
            } else {
                postSignInRequest(activity, "", "", NXToyLoginType.LoginTypeGuest.getValue(), null, null, nPListener);
                return;
            }
        }
        if (i != NXToyLoginType.LoginTypeGameCenter.getValue()) {
            ToyLog.d("login : NXLOG");
            activity.runOnUiThread(new AnonymousClass36(i, nPListener, session, activity));
            return;
        }
        final NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (googleGameProvider == null) {
            if (nPListener != null) {
                nPListener.onResult(new NXToyLoginResult(NPAuthPlugin.CODE_NOT_SUPPORT, NPGoogleGame.STR_GOOGLEGAME_NOT_SUPPORTED, NPGoogleGame.STR_GOOGLEGAME_NOT_SUPPORTED, NXToyRequestTag.LoginWithGameCenter.getValue()));
            }
        } else if (!NPOptionManager.getInstance().getOptions().isGoogleSignInInsteadOfGcidLogin()) {
            activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.35
                @Override // java.lang.Runnable
                public void run() {
                    if (session.getType() == NXToyLoginType.LoginTypeGameCenter.getValue()) {
                        if (nPListener != null) {
                            nPListener.onResult(new NXToyLoginResult(NXToyErrorCode.ALREADY_LOGIN.getCode(), NXToyAuthManager.this.localeManager.getString(R.string.npres_already_login), "", NXToyRequestTag.LoginWithGameCenter.getValue()));
                        }
                    } else if (googleGameProvider.isConnected()) {
                        NXToyAuthManager.this.loginWithGameCenter(activity, googleGameProvider.getGcId(), nPListener);
                    } else {
                        googleGameProvider.login(activity, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.35.1
                            @Override // kr.co.nexon.android.sns.NPAuthListener
                            public void onResult(int i2, String str, Bundle bundle) {
                                if (i2 == NXToyErrorCode.SUCCESS.getCode()) {
                                    NXToyAuthManager.this.loginWithGameCenter(activity, googleGameProvider.getGcId(), nPListener);
                                } else if (nPListener != null) {
                                    nPListener.onResult(new NXToyLoginResult(i2, str, "", NXToyRequestTag.LoginWithGameCenter.getValue()));
                                }
                            }
                        });
                    }
                }
            });
        } else if (googleGameProvider.isConnected()) {
            login(activity, NXToyLoginType.LoginTypeGoogle.getValue(), nPListener);
        } else {
            googleGameProvider.login(activity, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.34
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i2, String str, Bundle bundle) {
                    if (i2 == NXToyErrorCode.SUCCESS.getCode()) {
                        NXToyAuthManager.this.login(activity, NXToyLoginType.LoginTypeGoogle.getValue(), nPListener);
                    } else if (nPListener != null) {
                        nPListener.onResult(new NXToyLoginResult(i2, str, "", NXToyRequestTag.LoginWithGameCenter.getValue()));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginWithGameCenter(final Activity activity, final String str, final NPListener nPListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyIsNewGCIDRequest(str), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.37
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                NXToyIsNewGCIDResult nXToyIsNewGCIDResult = (NXToyIsNewGCIDResult) nXToyResult;
                if (nXToyIsNewGCIDResult.errorCode == NXToyErrorCode.SUCCESS.getCode() && nXToyIsNewGCIDResult.result.npSN == 0) {
                    NXToySession session = NXToySessionManager.getInstance().getSession();
                    session.setGcId(str);
                    session.setGpgId(str);
                    NXToyAuthManager.this.isGcidSignup = true;
                    NXToyAuthManager.this.postSignInRequest(activity, "", "", NXToyLoginType.LoginTypeGuest.getValue(), null, null, nPListener);
                    return;
                }
                NXToyAuthManager.this.prevSession = NXToySessionManager.getInstance().getSession().copy();
                NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
                String uuid = nXToyCommonPreferenceController.getUUID();
                String uuid2 = nXToyCommonPreferenceController.getUUID2();
                if (!NXStringUtil.isNull(uuid) && !NXStringUtil.isNull(uuid2)) {
                    final NXToySession session2 = NXToySessionManager.getInstance().getSession();
                    NXToyRequestPostman.getInstance().postRequest(new NXToySignInWithGCIDRequest(session2.getType() == NXToyLoginType.LoginTypeGuest.getValue() ? String.valueOf(session2.getNpsn()) : "0", str, uuid, uuid2, NXToyLoginType.LoginTypeGameCenter.getValue(), new NXToySignInRequestOptionalBody(activity, null, null)), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.37.1
                        @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                        public void onComplete(NXToyResult nXToyResult2) {
                            NXToyLoginWithGCIDResult nXToyLoginWithGCIDResult = (NXToyLoginWithGCIDResult) nXToyResult2;
                            NXToyLoginResult nXToyLoginResult = new NXToyLoginResult(nXToyResult2.errorCode, nXToyResult2.errorText, nXToyResult2.errorDetail);
                            nXToyLoginResult.requestTag = NXToyRequestTag.LoginWithGameCenter.getValue();
                            nXToyLoginResult.result.withdrawExpiresIn = nXToyLoginWithGCIDResult.result.withdrawExpiresIn;
                            switch (AnonymousClass48.$SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(nXToyLoginResult.errorCode).ordinal()]) {
                                case 11:
                                case 12:
                                    HashMap map = new HashMap();
                                    map.put("gcId", str);
                                    map.put(NPUserInfoDialog.KEY_LOGIN_TYPE, Integer.valueOf(NXToyLoginType.LoginTypeGameCenter.getValue()));
                                    map.put("withdrawExpiresIn", Integer.valueOf(nXToyLoginWithGCIDResult.result.withdrawExpiresIn));
                                    session2.setTempUserLoginInfo(map);
                                    NXToySessionManager.getInstance().setSession(NXToyAuthManager.this.prevSession);
                                    nPListener.onResult(nXToyLoginResult);
                                    break;
                                default:
                                    if (nXToyLoginResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                                        Log.d(NxLogInfo.VALUE_DEFAULT_TYPE, "loginWithGcidRequest result:" + nXToyResult2.toString());
                                        NXToyLoginWithGCIDResult.ResultSet resultSet = ((NXToyLoginWithGCIDResult) nXToyResult2).result;
                                        session2.setNpsn(resultSet.npSN);
                                        session2.setNPToken(resultSet.npToken);
                                        session2.setUMKey("");
                                        session2.setType(NXToyLoginType.LoginTypeGameCenter.getValue());
                                        nXToyLoginResult.result.npSN = nXToyLoginWithGCIDResult.result.npSN;
                                        nXToyLoginResult.result.npToken = nXToyLoginWithGCIDResult.result.npToken;
                                        nXToyLoginResult.result.termsAgree = nXToyLoginWithGCIDResult.result.termsAgree;
                                        NXToySession session3 = NXToySessionManager.getInstance().getSession();
                                        session3.setType(NXToyLoginType.LoginTypeGameCenter.getValue());
                                        session3.setGcId(str);
                                        session3.setGpgId(str);
                                        session3.setName(((NPGoogleGame) NXToyAuthManager.this.findAuthPlugin(NXToyLoginType.LoginTypeGameCenter.getValue())).getName());
                                        NXToyAuthManager.this.showTerms(activity, nXToyLoginResult, nPListener);
                                    } else {
                                        NXToyAuthManager.this.restoreLastSession();
                                        if (nPListener != null) {
                                            nPListener.onResult(nXToyLoginResult);
                                        }
                                    }
                                    break;
                            }
                        }
                    });
                } else {
                    NXToyAuthManager.this.restoreLastSession();
                    if (nPListener != null) {
                        nPListener.onResult(new NXToyLoginResult(NXToyErrorCode.INVALID_ARGS.getCode(), "UUID is null", "", NXToyRequestTag.LoginWithGameCenter.getValue()));
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postLinkGCIDToNPSNRequest(final Activity activity, final NPListener nPListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyLinkGCIDToNPSNRequest(NXToySessionManager.getInstance().getSession().getGcId()), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.44
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(final NXToyResult nXToyResult) {
                NXToyAuthManager.this.isGcidSignup = false;
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NXToyAuthManager.this.handleToyResult(nPListener, nXToyResult);
                    return;
                }
                NXToySessionManager.getInstance().restoreLastSession();
                NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
                if (googleGameProvider != null) {
                    googleGameProvider.logout(activity, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.44.1
                        @Override // kr.co.nexon.android.sns.NPAuthListener
                        public void onResult(int i, String str, Bundle bundle) {
                            NXToyAuthManager.this.handleToyResult(nPListener, nXToyResult);
                        }
                    });
                } else {
                    NXToyAuthManager.this.handleToyResult(nPListener, nXToyResult);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postSignInRequest(Activity activity, String str, String str2, int i, String str3, String str4, NPListener nPListener) {
        postSignInRequest(activity, str, str2, null, i, str3, str4, nPListener);
    }

    private void postSignInRequest(final Activity activity, final String str, final String str2, final byte[] bArr, final int i, final String str3, final String str4, final NPListener nPListener) {
        this.prevSession = NXToySessionManager.getInstance().getSession().copy();
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        String uuid = nXToyCommonPreferenceController.getUUID();
        String uuid2 = nXToyCommonPreferenceController.getUUID2();
        if (!NXStringUtil.isNull(uuid) && !NXStringUtil.isNull(uuid2)) {
            final NXToySession session = NXToySessionManager.getInstance().getSession();
            String strValueOf = session.getType() == NXToyLoginType.LoginTypeGuest.getValue() ? String.valueOf(session.getNpsn()) : "0";
            NXToyRequestPostman.getInstance().postRequest(str2 == null ? new NXToySignInRequest(strValueOf, str, bArr, uuid, uuid2, i, new NXToySignInRequestOptionalBody(activity, str3, str4)) : new NXToySignInRequest(strValueOf, str, str2, uuid, uuid2, i, new NXToySignInRequestOptionalBody(activity, str3, str4)), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.38
                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                public void onComplete(final NXToyResult nXToyResult) {
                    nXToyResult.requestTag = NXToyRequestTag.getTypeFromLoginType(i).getValue();
                    if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                        NXToyLoginResult nXToyLoginResult = (NXToyLoginResult) nXToyResult;
                        if (NXToySessionManager.getInstance().getSession().getType() == NXToyLoginType.LoginTypeGuest.getValue() && nXToyLoginResult.result.loginResultType == 0) {
                            nXToyLoginResult.result.loginResultType = 1;
                        }
                        NXToyLoginResult.ResultSet resultSet = nXToyLoginResult.result;
                        session.setNpsn(resultSet.npSN);
                        session.setNPToken(resultSet.npToken);
                        session.setUMKey(resultSet.umKey);
                        session.setType(i);
                        session.setEmailId(str3);
                        switch (AnonymousClass48.$SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.convertIntLoginTypeToEnumLoginType(i).ordinal()]) {
                            case 5:
                            case 9:
                            case 18:
                                session.setAccountId(str);
                                break;
                        }
                        if (NXToyAuthManager.this.isGcidSignup) {
                            nXToyLoginResult.requestTag = NXToyRequestTag.LoginWithGameCenter.getValue();
                            session.setType(NXToyLoginType.LoginTypeGameCenter.getValue());
                            session.setName(((NPGoogleGame) NXToyAuthManager.this.findAuthPlugin(NXToyLoginType.LoginTypeGameCenter.getValue())).getName());
                            session.setNpsn(resultSet.npSN);
                            session.setNPToken(resultSet.npToken);
                        }
                        ToyLog.d(nXToyLoginResult.toString());
                        ToyLog.d("loginType = " + NXToySessionManager.getInstance().getSession().getType());
                        NXToyAuthManager.this.showTerms(activity, nXToyLoginResult, nPListener);
                        return;
                    }
                    if (NXToyAuthManager.this.isGcidSignup) {
                        session.setGcId("");
                        session.setGpgId("");
                    }
                    ToyLog.d(nXToyResult.toString());
                    ToyLog.d("loginType = " + NXToySessionManager.getInstance().getSession().getType());
                    switch (AnonymousClass48.$SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(nXToyResult.errorCode).ordinal()]) {
                        case 11:
                        case 12:
                            HashMap map = new HashMap();
                            map.put("id", str);
                            if (str2 != null) {
                                map.put("pw", str2);
                            } else {
                                map.put("pw", new String(bArr));
                            }
                            map.put(NPUserInfoDialog.KEY_LOGIN_TYPE, Integer.valueOf(i));
                            map.put("withdrawExpiresIn", Integer.valueOf(((NXToyLoginResult) nXToyResult).result.withdrawExpiresIn));
                            if (NXStringUtil.isNotNull(str3)) {
                                map.put("email", str3);
                            }
                            if (NXStringUtil.isNotNull(str4)) {
                                map.put("fbBizToken", str4);
                            }
                            session.setTempUserLoginInfo(map);
                            NXToySessionManager.getInstance().setSession(NXToyAuthManager.this.prevSession);
                            NXToyAuthManager.this.handleToyResult(nPListener, nXToyResult);
                            break;
                        default:
                            NPAuthPlugin nPAuthPluginFindAuthPlugin = NXToyAuthManager.this.findAuthPlugin(i);
                            if (nPAuthPluginFindAuthPlugin == null) {
                                NXToyAuthManager.this.handleToyResult(nPListener, nXToyResult);
                            } else {
                                nPAuthPluginFindAuthPlugin.logout(NXToyAuthManager.this.applicationContext, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.38.1
                                    @Override // kr.co.nexon.android.sns.NPAuthListener
                                    public void onResult(int i2, String str5, Bundle bundle) {
                                        NXToyAuthManager.this.restoreLastSession();
                                        NXToyAuthManager.this.handleToyResult(nPListener, nXToyResult);
                                    }
                                });
                            }
                            break;
                    }
                }
            });
        } else {
            restoreLastSession();
            if (nPListener != null) {
                nPListener.onResult(new NXToyLoginResult(NXToyErrorCode.INVALID_ARGS.getCode(), "UUID is null", ""));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeAuthInfo(final NPListener nPListener) {
        final int type = NXToySessionManager.getInstance().getSession().getType();
        NPAuthPlugin nPAuthPluginFindAuthPlugin = findAuthPlugin(type);
        if (nPAuthPluginFindAuthPlugin == null) {
            clearAuthInfo(nPListener);
        } else {
            nPAuthPluginFindAuthPlugin.logout(this.applicationContext, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.46
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str, Bundle bundle) {
                    if (!NXToyAuthManager.this.isFastLogin(type)) {
                        NXToyAuthManager.this.clearAuthInfo(nPListener);
                        return;
                    }
                    NPAuthPlugin nPAuthPluginFindAuthPlugin2 = NXToyAuthManager.this.findAuthPlugin(NXToyLoginType.LoginTypeGameCenter.getValue());
                    if (nPAuthPluginFindAuthPlugin2 == null) {
                        NXToyAuthManager.this.clearAuthInfo(nPListener);
                    } else {
                        ToyLog.d("NPGoogleGame logout called");
                        nPAuthPluginFindAuthPlugin2.logout(NXToyAuthManager.this.applicationContext, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.46.1
                            @Override // kr.co.nexon.android.sns.NPAuthListener
                            public void onResult(int i2, String str2, Bundle bundle2) {
                                NXToyAuthManager.this.clearAuthInfo(nPListener);
                            }
                        });
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeLoginInfo() {
        NXToySession session = NXToySessionManager.getInstance().getSession();
        session.setNpsn(0L);
        session.setUMKey("");
        session.setNPToken("");
        session.setType(NXToyLoginType.LoginTypeNotLogined.getValue());
        NXToySessionManager.getInstance().saveSession();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restoreLastSession() {
        NXToySessionManager.getInstance().setSession(this.prevSession);
        NXToySessionManager.getInstance().restoreLastSession();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSnsEnable(Context context, int i) {
        for (int i2 : new int[]{NXToyLoginType.LoginTypeFaceBook.getValue(), NXToyLoginType.LoginTypeTwitter.getValue()}) {
            if (i == i2) {
                NXToySessionManager.getInstance().setSnsEnable(i, true);
            } else {
                NXToySessionManager.getInstance().setSnsEnable(i2, false);
                NPAuthPlugin nPAuthPluginFindAuthPlugin = findAuthPlugin(i2);
                if (nPAuthPluginFindAuthPlugin != null) {
                    nPAuthPluginFindAuthPlugin.logout(context, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.47
                        @Override // kr.co.nexon.android.sns.NPAuthListener
                        public void onResult(int i3, String str, Bundle bundle) {
                            ToyLog.d("logout errorCode : " + i3 + " / errorDetail : " + str);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTerms(final Activity activity, final NXToyLoginResult nXToyLoginResult, final NPListener nPListener) {
        final NXToySession session = NXToySessionManager.getInstance().getSession();
        NPListener nPListener2 = new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.39
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NXToyAuthManager.this.setSnsEnable(NXToyAuthManager.this.applicationContext, session.getType());
                    final NXToyLoginResult nXToyLoginResult2 = new NXToyLoginResult(0, NXToyAuthManager.this.localeManager.getString(R.string.npres_loginsuccess));
                    nXToyLoginResult2.result.npSN = session.getNpsn();
                    nXToyLoginResult2.requestTag = NXToyRequestTag.convertLoginTypeToRequestTag(session.getType());
                    if (session.getType() == NXToyLoginType.LoginTypeGameCenter.getValue() && NXToyAuthManager.this.isGcidSignup) {
                        NXToyAuthManager.this.postLinkGCIDToNPSNRequest(activity, new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.39.2
                            @Override // kr.co.nexon.toy.listener.NPListener
                            public void onResult(NXToyResult nXToyResult2) {
                                NXToyAuthManager.this.setSnsEnable(NXToyAuthManager.this.applicationContext, session.getType());
                                NXToyAuthManager.this.handleToyResult(nPListener, nXToyLoginResult2);
                            }
                        });
                        return;
                    } else {
                        NXToyAuthManager.this.handleToyResult(nPListener, nXToyLoginResult2);
                        return;
                    }
                }
                final NXToyLoginResult nXToyLoginResult3 = new NXToyLoginResult(nXToyResult.errorCode, nXToyResult.errorText);
                nXToyLoginResult3.requestTag = NXToyRequestTag.convertLoginTypeToRequestTag(session.getType());
                int type = session.getType();
                switch (AnonymousClass48.$SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.convertIntLoginTypeToEnumLoginType(type).ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 11:
                        final NPAuthPlugin nPAuthPluginFindAuthPlugin = NXToyAuthManager.this.findAuthPlugin(type);
                        if (nPAuthPluginFindAuthPlugin == null) {
                            NXToyAuthManager.this.restoreLastSession();
                            NXToyAuthManager.this.handleToyResult(nPListener, nXToyLoginResult3);
                        } else {
                            nPAuthPluginFindAuthPlugin.logout(NXToyAuthManager.this.applicationContext, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.39.1
                                @Override // kr.co.nexon.android.sns.NPAuthListener
                                public void onResult(int i, String str, Bundle bundle) {
                                    ToyLog.d(nPAuthPluginFindAuthPlugin.getClass().getSimpleName() + "logout. errorCode:" + i + " loginResult:" + nXToyLoginResult3);
                                    NXToyAuthManager.this.restoreLastSession();
                                    NXToyAuthManager.this.handleToyResult(nPListener, nXToyLoginResult3);
                                }
                            });
                        }
                        break;
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    default:
                        NXToyAuthManager.this.restoreLastSession();
                        NXToyAuthManager.this.handleToyResult(nPListener, nXToyLoginResult3);
                        break;
                }
            }
        };
        if (NXToyCommonPreferenceController.getInstance().getTermsApiVer() == 1) {
            NXPPolicyManager nXPPolicyManager = NXPPolicyManager.getInstance();
            nXPPolicyManager.getPolicyListV1(activity, "terms", new AnonymousClass40(nXToyLoginResult, session, activity, nXPPolicyManager, nPListener, nPListener2));
            return;
        }
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        final NXPPolicyManager nXPPolicyManager2 = NXPPolicyManager.getInstance();
        NXPTermsManager nXPTermsManager = NXPTermsManager.getInstance();
        final List<NXToyTerm> list = nXToyLoginResult.result.termsAgree;
        if (!nXPTermsManager.didCheckAllTerms(list)) {
            nXPTermsManager.getTermsList(list, new AnonymousClass43(nXPTermsManager, activity, list, nPListener2, nXToyCommonPreferenceController, nXPPolicyManager2, session, nPListener, nXToyLoginResult));
            return;
        }
        nXToyLoginResult.requestTag = NXToyRequestTag.convertLoginTypeToRequestTag(session.getType());
        session.setLoginTermsList(list);
        NXToySessionManager.getInstance().saveSession();
        if (session.getType() == NXToyLoginType.LoginTypeGameCenter.getValue() && this.isGcidSignup) {
            postLinkGCIDToNPSNRequest(activity, new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.41
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    nXPPolicyManager2.setAllEnabledPolicy(activity, list, new ArrayList(), new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.41.1
                        @Override // kr.co.nexon.toy.listener.NPListener
                        public void onResult(NXToyResult nXToyResult2) {
                            NXToyAuthManager.this.setSnsEnable(NXToyAuthManager.this.applicationContext, session.getType());
                            NXToyAuthManager.this.handleToyResult(nPListener, nXToyLoginResult);
                        }
                    });
                }
            });
        } else {
            nXPPolicyManager2.setAllEnabledPolicy(activity, list, new ArrayList(), new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.42
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    NXToyAuthManager.this.setSnsEnable(NXToyAuthManager.this.applicationContext, session.getType());
                    NXToyAuthManager.this.handleToyResult(nPListener, nXToyLoginResult);
                }
            });
        }
    }

    public void changeAccount(Activity activity, int i, NPListener nPListener) {
        login(activity, i, nPListener);
    }

    public NPAuthPlugin findAuthPlugin(int i) {
        return NXPProviderManager.getInstance().getProvider(i);
    }

    public void getNexonSN(Activity activity, boolean z, final NPListener nPListener) {
        if (!z) {
            getNexonSNFromWeb(activity, nPListener);
            return;
        }
        NPNexonCom nPNexonCom = (NPNexonCom) findAuthPlugin(NXToyLoginType.LoginTypeNXCom.getValue());
        nPNexonCom.setExtraData(NXJsonUtil.toJsonString(NXToySessionManager.getInstance().getSession()));
        nPNexonCom.getNexonSN(activity, new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.24
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (nPListener != null) {
                    nPListener.onResult(nXToyResult);
                }
            }
        });
    }

    public void getNexonSNByNaverChannel(Activity activity, final NPListener nPListener) {
        NPAuthListener nPAuthListener = new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.26
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str, Bundle bundle) {
                if (bundle == null || i != NXToyErrorCode.SUCCESS.getCode()) {
                    if (nPListener != null) {
                        nPListener.onResult(new NXToyGetNexonSNByNaverChannelResult(i, str, str));
                        return;
                    }
                    return;
                }
                String string = bundle.getString(NPAuthPlugin.KEY_ACCESSTOKEN);
                if (string == null || string.length() < 1) {
                    NXToyAuthManager.this.handleToyResult(nPListener, new NXToyGetNexonSNByNaverChannelResult(NXToyErrorCode.INVALID_ARGS.getCode(), "ID or password error"));
                } else {
                    NXToyRequestPostman.getInstance().postRequest(new NXToyGetNexonSNByNaverChannelRequest(string), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.26.1
                        @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                        public void onComplete(NXToyResult nXToyResult) {
                            NXToyGetNexonSNByNaverChannelResult nXToyGetNexonSNByNaverChannelResult = (NXToyGetNexonSNByNaverChannelResult) nXToyResult;
                            nXToyGetNexonSNByNaverChannelResult.result.nexonSNString = Long.toString(nXToyGetNexonSNByNaverChannelResult.result.nexonSN);
                            NXToyAuthManager.this.handleToyResult(nPListener, nXToyGetNexonSNByNaverChannelResult);
                        }
                    });
                }
            }
        };
        NPNaverChannel nPNaverChannel = (NPNaverChannel) findAuthPlugin(NXToyLoginType.LoginTypeNaverChannel.getValue());
        if (NXToySessionManager.getInstance().getSession().getType() == NXToyLoginType.LoginTypeNaverChannel.getValue()) {
            nPNaverChannel.getUserInfo(activity.getApplicationContext(), nPAuthListener);
        } else {
            nPNaverChannel.getNexonSN(activity, nPAuthListener);
        }
    }

    public void getUserInfo(Activity activity, final NPListener nPListener) {
        NPListener nPListener2 = new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.2
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(final NXToyResult nXToyResult) {
                if (nPListener == null) {
                    return;
                }
                if (!NPOptionManager.getInstance().getOptions().isNgsmEnabled()) {
                    nPListener.onResult(nXToyResult);
                    return;
                }
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    nPListener.onResult(nXToyResult);
                    return;
                }
                NXToySession session = NXToySessionManager.getInstance().getSession();
                Ngsm.getInst().ngsmRun(session.getNpaCode(), String.valueOf(session.getNpsn()), new Ngsm.OnRunListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.2.1
                    @Override // com.nexon.ngsm.Ngsm.OnRunListener
                    public void onRun(NgsmResult ngsmResult) {
                        nPListener.onResult(nXToyResult);
                    }
                });
            }
        };
        ToyLog.d("[getUserInfo]");
        final NXToySessionManager nXToySessionManager = NXToySessionManager.getInstance();
        NXToySession session = nXToySessionManager.getSession();
        final int type = session.getType();
        final AnonymousClass3 anonymousClass3 = new AnonymousClass3(session, nPListener, type, activity, nXToySessionManager, nPListener2);
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(type)) {
            case LoginTypeNaverChannel:
            case LoginTypeNaver:
            case LoginTypeDaumChannel:
                findAuthPlugin(type).getUserInfo(this.applicationContext, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.4
                    @Override // kr.co.nexon.android.sns.NPAuthListener
                    public void onResult(int i, String str, Bundle bundle) {
                        if (i == NXToyErrorCode.NAVERCHN_TOKEN_EXPIRED.getCode() || i == NXToyErrorCode.DAUM_TOKEN_EXPIRED.getCode()) {
                            nXToySessionManager.removeSession();
                            NXPSNSManager.getInstance().disconnectAll(NXToyAuthManager.this.applicationContext);
                            String str2 = String.format(NXToyAuthManager.this.localeManager.getString(R.string.npres_refresh_token_failed), NXToyAuthManager.this.getNameFromLoginType(type));
                            if (nPListener != null) {
                                nPListener.onResult(new NXToyUserInfoResult(NXToyErrorCode.TOYSERVER_AUTH_NOT_VALID.getCode(), str2, str));
                                return;
                            }
                            return;
                        }
                        if (i == NXToyErrorCode.SUCCESS.getCode()) {
                            NXToyRequestPostman.getInstance().postRequest(new NXToyGetUserInfoRequest(bundle.getString(NPAuthPlugin.KEY_ACCESSTOKEN)), anonymousClass3);
                            return;
                        }
                        String str3 = String.format(NXToyAuthManager.this.localeManager.getString(R.string.npres_refresh_token_failed), NXToyAuthManager.this.getNameFromLoginType(type));
                        if (nPListener != null) {
                            nPListener.onResult(new NXToyUserInfoResult(i, str3, str3));
                        }
                    }
                });
                break;
            default:
                NXToyRequestPostman.getInstance().postRequest(new NXToyGetUserInfoRequest(session.getExternalAuthToken()), anonymousClass3);
                break;
        }
    }

    public boolean isAuthCrashError(int i) {
        switch (NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(i)) {
            case TOYSERVER_AUTH_NOT_VALID:
            case TOYSERVER_TOKEN_EXPIRED:
            case TOYSERVER_EXTERNAL_TOKEN_EXPIRED:
            case FACEBOOK_GRAPHAPI_FAIL:
            case DAUM_TOKEN_EXPIRED:
                NXToySessionManager.getInstance().removeSession();
                return true;
            default:
                return false;
        }
    }

    public boolean isFastLogin(int i) {
        boolean zIsGoogleSignInInsteadOfGcidLogin = NPOptionManager.getInstance().getOptions().isGoogleSignInInsteadOfGcidLogin();
        ToyLog.d("currentLoginType:" + i + ",   isGoogleSignInInsteadOfGcidLogin:" + zIsGoogleSignInInsteadOfGcidLogin);
        return i == NXToyLoginType.LoginTypeGoogle.getValue() && zIsGoogleSignInInsteadOfGcidLogin;
    }

    public void loginByLastNexonSN(Activity activity, final NPListener nPListener) {
        final NPNexonCom nPNexonCom = (NPNexonCom) NXPProviderManager.getInstance().getProvider(NXToyLoginType.LoginTypeNXCom.getValue());
        NXPProviderAuthenticationInfo lastAuthenticationInfo = nPNexonCom.getLastAuthenticationInfo();
        if (lastAuthenticationInfo == null) {
            NXToyLoginResult nXToyLoginResult = new NXToyLoginResult(NXToyErrorCode.LAST_NEXONSN_NOT_FOUND.getCode(), "Last NexonSN not found.");
            if (nPListener != null) {
                nPListener.onResult(nXToyLoginResult);
                return;
            }
            return;
        }
        NXToySessionManager.getInstance().backupToLastSession();
        final NXToySession session = NXToySessionManager.getInstance().getSession();
        final int type = session.getType();
        final long npsn = session.getNpsn();
        session.setExternalAuthId(lastAuthenticationInfo.getId());
        session.setExternalAuthToken(lastAuthenticationInfo.getPassword());
        NXToySessionManager.getInstance().saveSession();
        ToyLog.d("cached NXPAuthenticationInfo:" + lastAuthenticationInfo);
        postSignInRequest(activity, lastAuthenticationInfo.getId(), lastAuthenticationInfo.getPassword(), lastAuthenticationInfo.getLoginType(), lastAuthenticationInfo.getEmail(), lastAuthenticationInfo.getFbBizToken(), new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.23
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NXToyAuthManager.this.handleToyResult(nPListener, nXToyResult);
                    return;
                }
                nPNexonCom.clearLastAuthenticationInfo();
                if (type == session.getType()) {
                    NXToyAuthManager.this.handleToyResult(nPListener, nXToyResult);
                    return;
                }
                if (!(nXToyResult instanceof NXToyLoginResult)) {
                    NXToyAuthManager.this.handleToyResult(nPListener, nXToyResult);
                    return;
                }
                NXToyLoginResult nXToyLoginResult2 = (NXToyLoginResult) nXToyResult;
                if (npsn == 0) {
                    nXToyLoginResult2.result.loginResultType = 0;
                } else if (npsn == session.getNpsn()) {
                    nXToyLoginResult2.result.loginResultType = 1;
                } else {
                    nXToyLoginResult2.result.loginResultType = 2;
                }
                ToyLog.d("prevLoginType:" + type + " , prvNpsn: " + npsn);
                ToyLog.d("currentLoginType:" + session.getType() + " , currentNpsn:" + session.getNpsn());
                NXToyAuthManager.this.handleToyResult(nPListener, nXToyLoginResult2);
            }
        });
    }

    public void loginByQueriedNpsn(final Activity activity, Long l, final NPListener nPListener) {
        final NXToySession session = NXToySessionManager.getInstance().getSession();
        Map<String, Object> tempUserLoginInfoForNpsn = session.getTempUserLoginInfoForNpsn(l.longValue());
        if (tempUserLoginInfoForNpsn == null) {
            if (nPListener != null) {
                nPListener.onResult(new NXToyResult(NXToyErrorCode.NOT_FOUND_LOGIN_INFO.getCode(), "Missing data mapped with NPSN", "Missing data mapped with NPSN."));
                return;
            }
            return;
        }
        Object obj = tempUserLoginInfoForNpsn.get(NPUserInfoDialog.KEY_LOGIN_TYPE);
        int iIntValue = obj.getClass().getName().contains("Double") ? ((Double) obj).intValue() : ((Integer) obj).intValue();
        final String str = (String) tempUserLoginInfoForNpsn.get("id");
        final String str2 = (String) tempUserLoginInfoForNpsn.get("pw");
        if (session.getType() == iIntValue) {
            if (nPListener != null) {
                nPListener.onResult(new NXToyResult(NXToyErrorCode.UNAUTHORIZED_LOGIN_TYPE.getCode(), "Unauthorized login type", "Unauthorized login type"));
                return;
            }
            return;
        }
        NPAuthPlugin snsProvider = NXPSNSManager.getInstance().getSnsProvider(iIntValue);
        if (snsProvider != null && !snsProvider.isConnected()) {
            if (nPListener != null) {
                nPListener.onResult(new NXToyResult(NXToyErrorCode.TEMP_SNS_NOT_CONNECTED.getCode(), "SNS account unlinked.", "SNS account unlinked."));
            }
        } else if (session.getType() == NXToyLoginType.LoginTypeNotLogined.getValue()) {
            postSignInRequest(activity, str, str2, iIntValue, null, null, nPListener);
        } else {
            final int i = iIntValue;
            NXToyRequestPostman.getInstance().postRequest(new NXToyLogoutSVCRequest(), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.17
                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                public void onComplete(NXToyResult nXToyResult) {
                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                        if (nPListener != null) {
                            nPListener.onResult(nXToyResult);
                            return;
                        }
                        return;
                    }
                    NPNaverChannel nPNaverChannel = (NPNaverChannel) NXToyAuthManager.this.findAuthPlugin(NXToyLoginType.LoginTypeNaverChannel.getValue());
                    NPGoogleSignIn nPGoogleSignIn = (NPGoogleSignIn) NXToyAuthManager.this.findAuthPlugin(NXToyLoginType.LoginTypeGoogle.getValue());
                    NPTwitter nPTwitter = (NPTwitter) NXToyAuthManager.this.findAuthPlugin(NXToyLoginType.LoginTypeTwitter.getValue());
                    NPFacebook nPFacebook = (NPFacebook) NXToyAuthManager.this.findAuthPlugin(NXToyLoginType.LoginTypeFaceBook.getValue());
                    NXToyLoginType nXToyLoginTypeConvertIntLoginTypeToEnumLoginType = NXToyLoginType.convertIntLoginTypeToEnumLoginType(session.getType());
                    if (nXToyLoginTypeConvertIntLoginTypeToEnumLoginType == NXToyLoginType.LoginTypeNaverChannel || nXToyLoginTypeConvertIntLoginTypeToEnumLoginType == NXToyLoginType.LoginTypeNaver) {
                        nPNaverChannel.logout(NXToyAuthManager.this.applicationContext, null);
                    }
                    if (nPFacebook != null && i != NXToyLoginType.LoginTypeFaceBook.getValue()) {
                        nPFacebook.logout(NXToyAuthManager.this.applicationContext, null);
                    }
                    if (nPGoogleSignIn != null && i != NXToyLoginType.LoginTypeGoogle.getValue()) {
                        nPGoogleSignIn.logout(NXToyAuthManager.this.applicationContext, null);
                    }
                    if (nPTwitter != null && i != NXToyLoginType.LoginTypeTwitter.getValue()) {
                        nPTwitter.logout(NXToyAuthManager.this.applicationContext, null);
                    }
                    NXToyAuthManager.this.removeLoginInfo();
                    NXToyAuthManager.this.postSignInRequest(activity, str, str2, i, null, null, nPListener);
                }
            });
        }
    }

    public void loginWithEmail(Activity activity, String str, String str2, NPListener nPListener) {
        NXToySessionManager.getInstance().backupToLastSession();
        postSignInRequest(activity, str, str2, NXToyLoginType.LoginTypeEmail.getValue(), str, null, nPListener);
    }

    public void loginWithExternalAuthType(Activity activity, int i, String str, String str2, final NPListener nPListener) {
        NXToySessionManager.getInstance().backupToLastSession();
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeKakao:
            case LoginTypeMapleId:
            case LoginTypePlayPark:
                postSignInRequest(activity, str, str2, i, null, null, new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.15
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult) {
                        if (!nXToyResult.getClass().toString().contains("NXToyLoginResult")) {
                            nPListener.onResult(nXToyResult);
                            return;
                        }
                        if (nXToyResult.errorCode == NXToyErrorCode.WITHDRAWAL_PROCESSING_BY_THE_USER.getCode()) {
                            NXToyLoginResult nXToyLoginResult = (NXToyLoginResult) nXToyResult;
                            NXToySession session = NXToySessionManager.getInstance().getSession();
                            if (session.getTempUserLoginInfo() != null) {
                                session.getTempUserLoginInfo().put("withdrawExpiresIn", Integer.valueOf(nXToyLoginResult.result.withdrawExpiresIn));
                            }
                        }
                        nPListener.onResult(nXToyResult);
                    }
                });
                break;
            default:
                if (nPListener != null) {
                    nPListener.onResult(new NXToyResult(NXToyErrorCode.UNSUPPORTED.getCode(), "Unsupported type", "Unsupported type"));
                }
                break;
        }
    }

    public void loginWithNX(Activity activity, String str, String str2, NPListener nPListener) {
        NXToySessionManager.getInstance().backupToLastSession();
        postSignInRequest(activity, str, str2, NXToyLoginType.LoginTypeNXCom.getValue(), null, null, nPListener);
    }

    public void loginWithNX(Activity activity, String str, byte[] bArr, NPListener nPListener) {
        NXToySessionManager.getInstance().backupToLastSession();
        postSignInRequest(activity, str, null, bArr, NXToyLoginType.LoginTypeNXCom.getValue(), null, null, nPListener);
    }

    public void loginWithType(Activity activity, int i, NPListener nPListener) {
        NXToyLoginType nXToyLoginTypeConvertIntLoginTypeToEnumLoginType = NXToyLoginType.convertIntLoginTypeToEnumLoginType(NXToySessionManager.getInstance().getSession().getType());
        if (i == NXToyLoginType.LoginTypeNotLogined.getValue()) {
            if (nPListener != null) {
                nPListener.onResult(new NXToyLoginResult(NXToyErrorCode.UNSUPPORTED.getCode(), this.localeManager.getString(R.string.npres_loginfailed), this.localeManager.getString(R.string.npres_daumchn_unsupported_response_type), NXToyRequestTag.getTypeFromLoginType(i).getValue()));
            }
        } else {
            if (i == NXToyLoginType.LoginTypeDefault.getValue()) {
                showLoginSelector(activity, nPListener);
                return;
            }
            if ((nXToyLoginTypeConvertIntLoginTypeToEnumLoginType != NXToyLoginType.LoginTypeGuest || i != NXToyLoginType.LoginTypeGuest.getValue()) && (nXToyLoginTypeConvertIntLoginTypeToEnumLoginType.getValue() == NXToyLoginType.LoginTypeGuest.getValue() || !NXToyLoginType.isValidLoginType(nXToyLoginTypeConvertIntLoginTypeToEnumLoginType.getValue()) || !NXToyLoginType.isValidLoginType(i))) {
                login(activity, i, nPListener);
            } else if (nPListener != null) {
                String string = this.localeManager.getString(R.string.npres_already_login);
                nPListener.onResult(new NXToyLoginResult(NXToyErrorCode.ALREADY_LOGIN.getCode(), string, string, NXToyRequestTag.getTypeFromLoginType(i).getValue()));
            }
        }
    }

    public void logout(final NPListener nPListener) {
        ToyLog.d("[logout]");
        NXToyRequestPostman.getInstance().postRequest(new NXToyLogoutSVCRequest(), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.1
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(final NXToyResult nXToyResult) {
                NXToyAuthManager.this.removeAuthInfo(new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.1.1
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult2) {
                        if (nPListener != null) {
                            nPListener.onResult(nXToyResult);
                        }
                    }
                });
            }
        });
    }

    public void queryNpsnByLoginType(Activity activity, int i, final NPListener nPListener) {
        final NXToySession session = NXToySessionManager.getInstance().getSession();
        if (session.getType() == i) {
            nPListener.onResult(new NXToyNpsnResult(NXToyErrorCode.UNAUTHORIZED_LOGIN_TYPE.getCode(), "This account type is not available", "This account type is not available"));
        }
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
                ((NPFacebook) findAuthPlugin(NXToyLoginType.LoginTypeFaceBook.getValue())).login(activity, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.18
                    @Override // kr.co.nexon.android.sns.NPAuthListener
                    public void onResult(int i2, String str, Bundle bundle) {
                        if (i2 != NXToyErrorCode.SUCCESS.getCode()) {
                            if (i2 == NXToyErrorCode.FACEBOOK_INVALID_APPID_OR_HASHKEY.getCode()) {
                                ToyLog.d("errorCode : " + NXToyErrorCode.FACEBOOK_INVALID_APPID_OR_HASHKEY.getCode());
                                ToyLog.d("errorText : " + NXToyAuthManager.this.localeManager.getString(R.string.npres_fbappid_or_hashkey_invalid));
                            }
                            nPListener.onResult(new NXToyNpsnResult(i2, NXToyAuthManager.this.localeManager.getString(R.string.npres_loginfailed), str, NXToyRequestTag.GetNpsnWithFB.getValue()));
                            return;
                        }
                        final String string = bundle.getString(NPAuthPlugin.KEY_ID);
                        final String string2 = bundle.getString(NPAuthPlugin.KEY_ACCESSTOKEN);
                        final int value = NXToyLoginType.LoginTypeFaceBook.getValue();
                        if (string == null || string2 == null || value < 0) {
                            nPListener.onResult(new NXToyNpsnResult(NXToyErrorCode.INVALID_ARGS.getCode(), "ID or password error", ""));
                        } else {
                            NXToyRequestPostman.getInstance().postRequest(new NXToyGetNPSNRequest(string, string2, value), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.18.1
                                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                                public void onComplete(NXToyResult nXToyResult) {
                                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                                        nPListener.onResult(nXToyResult);
                                    }
                                    NXToyNpsnResult nXToyNpsnResult = (NXToyNpsnResult) nXToyResult;
                                    HashMap map = new HashMap();
                                    map.put("id", string);
                                    map.put("pw", string2);
                                    map.put(NPUserInfoDialog.KEY_LOGIN_TYPE, Integer.valueOf(value));
                                    session.setTempUserLoginInfoForNpsn(nXToyNpsnResult.result.npsn, map);
                                    nPListener.onResult(nXToyNpsnResult);
                                }
                            });
                        }
                    }
                });
                break;
            case LoginTypeTwitter:
                int value = NXToyLoginType.LoginTypeTwitter.getValue();
                NPTwitter nPTwitter = (NPTwitter) findAuthPlugin(value);
                nPTwitter.initInstance();
                nPTwitter.login(activity, new AnonymousClass20(nPListener, nPTwitter, value, session));
                break;
            case LoginTypeGoogle:
                if (NPGoogleSignIn.isAvailable(activity, true) == 0) {
                    NPGoogleSignIn nPGoogleSignIn = (NPGoogleSignIn) findAuthPlugin(NXToyLoginType.LoginTypeGoogle.getValue());
                    if (nPGoogleSignIn != null) {
                        nPGoogleSignIn.login(activity, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.19
                            @Override // kr.co.nexon.android.sns.NPAuthListener
                            public void onResult(int i2, String str, Bundle bundle) {
                                if (i2 != NXToyErrorCode.SUCCESS.getCode()) {
                                    NXToyResult nXToyResult = new NXToyResult(NXToyErrorCode.GOOGLE_LOGIN_FAIL.getCode(), NXToyAuthManager.this.localeManager.getString(R.string.npres_loginfailed), str);
                                    nXToyResult.requestTag = NXToyRequestTag.GetNpsnWithGPlus.getValue();
                                    nPListener.onResult(nXToyResult);
                                    return;
                                }
                                final String string = bundle.getString(NPAuthPlugin.KEY_ID);
                                final String string2 = bundle.getString(NPAuthPlugin.KEY_ACCESSTOKEN);
                                final int value2 = NXToyLoginType.LoginTypeGoogle.getValue();
                                if (string == null || string2 == null || value2 < 0) {
                                    nPListener.onResult(new NXToyNpsnResult(NXToyErrorCode.INVALID_ARGS.getCode(), "ID or password error", ""));
                                } else {
                                    NXToyRequestPostman.getInstance().postRequest(new NXToyGetNPSNRequest(string, string2, value2), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.19.1
                                        @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                                        public void onComplete(NXToyResult nXToyResult2) {
                                            if (nXToyResult2.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                                                nPListener.onResult(nXToyResult2);
                                                return;
                                            }
                                            HashMap map = new HashMap();
                                            map.put("id", string);
                                            map.put("pw", string2);
                                            map.put(NPUserInfoDialog.KEY_LOGIN_TYPE, Integer.valueOf(value2));
                                            NXToySessionManager.getInstance().getSession().setTempUserLoginInfoForNpsn(((NXToyNpsnResult) nXToyResult2).result.npsn, map);
                                            nPListener.onResult(nXToyResult2);
                                        }
                                    });
                                }
                            }
                        });
                    }
                } else if (nPListener != null) {
                    nPListener.onResult(new NXToyResult(NXToyErrorCode.GOOGLE_PLAY_SERVICE_UNAVAILABLE.getCode(), this.localeManager.getString(R.string.npres_loginfailed), "google play service not available", NXToyRequestTag.LoginWithGoogleSignIn.getValue()));
                }
                break;
            case LoginTypeNXCom:
                NPNexonCom nPNexonCom = (NPNexonCom) findAuthPlugin(NXToyLoginType.LoginTypeNXCom.getValue());
                nPNexonCom.setExtraData(NXJsonUtil.toJsonString(session));
                nPNexonCom.queryNpsn(activity, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.22
                    @Override // kr.co.nexon.android.sns.NPAuthListener
                    public void onResult(int i2, String str, Bundle bundle) {
                        NXToyNpsnResult nXToyNpsnResult = (NXToyNpsnResult) NXJsonUtil.fromObject(bundle.getString("toyresult"), NXToyNpsnResult.class);
                        nXToyNpsnResult.requestTag = NXToyRequestTag.GetNPSN.getValue();
                        if (i2 == 90201) {
                            nXToyNpsnResult.errorCode = NXToyErrorCode.LOGIN_USER_CANCELED.getCode();
                            nXToyNpsnResult.errorDetail = NXToyAuthManager.this.localeManager.getString(R.string.npres_logincancel);
                            nXToyNpsnResult.errorText = NXToyAuthManager.this.localeManager.getString(R.string.npres_logincancel);
                            nPListener.onResult(nXToyNpsnResult);
                            return;
                        }
                        if (i2 == NXToyErrorCode.SUCCESS.getCode()) {
                            NXToySessionManager.getInstance().setSession((NXToySession) NXJsonUtil.fromObject(bundle.getString("toysession"), NXToySession.class));
                            nPListener.onResult(nXToyNpsnResult);
                        } else {
                            nXToyNpsnResult.errorCode = NXToyErrorCode.NPSN_NOT_FOUND.getCode();
                            nXToyNpsnResult.errorDetail = "Can not find NPSN";
                            nXToyNpsnResult.errorText = "Can not find NPSN";
                            nPListener.onResult(nXToyNpsnResult);
                        }
                    }
                });
                break;
            case LoginTypeNaverChannel:
            case LoginTypeNaver:
            case LoginTypeDaumChannel:
            default:
                nPListener.onResult(new NXToyNpsnResult(NXToyErrorCode.UNAUTHORIZED_LOGIN_TYPE.getCode(), "Unauthorized login type", "Unauthorized login type"));
                break;
            case LoginTypeEmail:
                NPEmail nPEmail = (NPEmail) findAuthPlugin(NXToyLoginType.LoginTypeEmail.getValue());
                nPEmail.setExtraData(NXJsonUtil.toJsonString(session));
                nPEmail.queryNpsn(activity, new NPAuthListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.21
                    @Override // kr.co.nexon.android.sns.NPAuthListener
                    public void onResult(int i2, String str, Bundle bundle) {
                        NXToyNpsnResult nXToyNpsnResult = (NXToyNpsnResult) NXJsonUtil.fromObject(bundle.getString("toyresult"), NXToyNpsnResult.class);
                        nXToyNpsnResult.requestTag = NXToyRequestTag.GetNPSN.getValue();
                        if (i2 == 90201) {
                            nXToyNpsnResult.errorCode = NXToyErrorCode.LOGIN_USER_CANCELED.getCode();
                            nXToyNpsnResult.errorDetail = NXToyAuthManager.this.localeManager.getString(R.string.npres_logincancel);
                            nXToyNpsnResult.errorText = NXToyAuthManager.this.localeManager.getString(R.string.npres_logincancel);
                            nPListener.onResult(nXToyNpsnResult);
                            return;
                        }
                        if (i2 == NXToyErrorCode.SUCCESS.getCode()) {
                            NXToySessionManager.getInstance().setSession((NXToySession) NXJsonUtil.fromObject(bundle.getString("toysession"), NXToySession.class));
                            nPListener.onResult(nXToyNpsnResult);
                        } else {
                            nXToyNpsnResult.errorCode = NXToyErrorCode.NPSN_NOT_FOUND.getCode();
                            nXToyNpsnResult.errorDetail = "Can not find NPSN";
                            nXToyNpsnResult.errorText = "Can not find NPSN";
                            nPListener.onResult(nXToyNpsnResult);
                        }
                    }
                });
                break;
        }
    }

    public void recoverUser(final Activity activity, final NPListener nPListener) {
        final NXToySession session = NXToySessionManager.getInstance().getSession();
        final int type = session.getType();
        final NPListener nPListener2 = new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.7
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (nPListener == null) {
                    return;
                }
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    nPListener.onResult(nXToyResult);
                    return;
                }
                if (type != NXToyLoginType.LoginTypeNotLogined.getValue()) {
                    ((NXToyLoginResult) nXToyResult).result.loginResultType = 2;
                }
                nPListener.onResult(nXToyResult);
            }
        };
        int i = 0;
        Map<String, Object> tempUserLoginInfo = session.getTempUserLoginInfo();
        if (tempUserLoginInfo != null && tempUserLoginInfo.get("withdrawExpiresIn") != null) {
            i = Integer.parseInt(tempUserLoginInfo.get("withdrawExpiresIn").toString());
        }
        if (i < 60) {
            i = 60;
        }
        int i2 = i / 86400;
        int i3 = i % 86400;
        int i4 = i3 / 3600;
        int i5 = (i3 % 3600) / 60;
        String str = type == NXToyLoginType.LoginTypeGuest.getValue() ? String.format(this.localeManager.getString(R.string.npres_withdrawal_recover_message_then_guest_logout), Integer.valueOf(i2), Integer.valueOf(i4), Integer.valueOf(i5)) : String.format(this.localeManager.getString(R.string.npres_withdrawal_recover_message), Integer.valueOf(i2), Integer.valueOf(i4), Integer.valueOf(i5));
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        int value = 0;
        int iIntValue = 0;
        try {
            Map<String, Object> tempUserLoginInfo2 = session.getTempUserLoginInfo();
            if (tempUserLoginInfo2 == null) {
                return;
            }
            Object obj = tempUserLoginInfo2.get(NPUserInfoDialog.KEY_LOGIN_TYPE);
            if (obj.getClass().getName().contains("Double")) {
                iIntValue = ((Double) obj).intValue();
                value = NXToyRequestTag.getCodeFromLoginType(iIntValue);
            } else {
                iIntValue = ((Integer) obj).intValue();
                value = NXToyRequestTag.Login.getValue();
            }
            if (iIntValue == NXToyLoginType.LoginTypeGameCenter.getValue()) {
                str4 = (String) tempUserLoginInfo2.get("gcId");
                str5 = null;
            } else {
                str3 = (String) tempUserLoginInfo2.get("id");
                str2 = (String) tempUserLoginInfo2.get("pw");
                Object obj2 = tempUserLoginInfo2.get("email");
                Object obj3 = tempUserLoginInfo2.get("fbBizToken");
                str5 = obj2 instanceof String ? (String) obj2 : null;
                if (obj3 instanceof String) {
                    str6 = (String) obj3;
                }
            }
            String str7 = String.format(str, getNameFromLoginType(iIntValue));
            final String str8 = str3;
            final String str9 = str2;
            final String str10 = str4;
            final String str11 = str5;
            final String str12 = str6;
            final int i6 = iIntValue;
            final int i7 = value;
            final NXPAlertDialog.Builder builder = new NXPAlertDialog.Builder(activity);
            builder.setMessage(str7).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.10
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    NXToyAuthManager.this.cancelRecoverUser(nPListener);
                }
            }).setNegativeButton(this.localeManager.getString(R.string.npres_withdrawal_cancel), new View.OnClickListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NXToyAuthManager.this.cancelRecoverUser(nPListener);
                }
            }).setPositiveButton(this.localeManager.getString(R.string.npres_withdrawal_recover), new View.OnClickListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NXToyAuthManager.this.removeLoginInfo();
                    if (i6 == NXToyLoginType.LoginTypeGameCenter.getValue()) {
                        NXToyRequestPostman.getInstance().postRequest(new NXToyRecoverUserWithGcidRequest(str10, i6, new NXToySignInRequestOptionalBody(activity, null, null)), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.8.1
                            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                            public void onComplete(NXToyResult nXToyResult) {
                                NXToyLoginResult nXToyLoginResult = (NXToyLoginResult) nXToyResult;
                                nXToyLoginResult.requestTag = NXToyRequestTag.LoginWithGameCenter.getValue();
                                NXToyLoginResult.ResultSet resultSet = nXToyLoginResult.result;
                                session.setNpsn(resultSet.npSN);
                                session.setNPToken(resultSet.npToken);
                                session.setUMKey("");
                                session.setType(i6);
                                session.setGcId(str10);
                                session.setGpgId(str10);
                                ToyLog.d(nXToyLoginResult.toString());
                                ToyLog.d("loginType = " + NXToySessionManager.getInstance().getSession().getType());
                                if (nXToyLoginResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                                    NXToyAuthManager.this.showTerms(activity, nXToyLoginResult, nPListener2);
                                    return;
                                }
                                if (nXToyResult.errorCode == NXToyErrorCode.WITHDRAWAL_PROCESSING_FAILED.getCode()) {
                                    nXToyLoginResult.requestTag = NXToyRequestTag.RecoverUser.getValue();
                                }
                                NXToyAuthManager.this.restoreLastSession();
                                if (nPListener != null) {
                                    nPListener.onResult(nXToyLoginResult);
                                }
                            }
                        });
                        return;
                    }
                    if (str8 == null || str9 == null || i6 < 0) {
                        ToyLog.d("Recovery was failed. ID or password error");
                        ToyLog.d("loginType = " + NXToySessionManager.getInstance().getSession().getType());
                        NXToyAuthManager.this.restoreLastSession();
                        if (nPListener != null) {
                            nPListener.onResult(new NXToyLoginResult(NXToyErrorCode.INVALID_ARGS.getCode(), "ID or password error", "", i7));
                            return;
                        }
                        return;
                    }
                    String strEncodeHmacSha256ToHexString = str9;
                    if (i6 == NXToyLoginType.LoginTypeNXCom.getValue() && str8.contains("@")) {
                        try {
                            strEncodeHmacSha256ToHexString = NXCrypto.encodeHmacSha256ToHexString("NexonUser", str9);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    NXToyRequestPostman.getInstance().postRequest(new NXToyRecoverUserRequest(str8, strEncodeHmacSha256ToHexString, i6, new NXToySignInRequestOptionalBody(activity, str11, str12)), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.8.2
                        @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                        public void onComplete(NXToyResult nXToyResult) {
                            NXToyLoginResult nXToyLoginResult = (NXToyLoginResult) nXToyResult;
                            nXToyLoginResult.requestTag = i7;
                            ToyLog.d(nXToyLoginResult.toString());
                            ToyLog.d("loginType = " + NXToySessionManager.getInstance().getSession().getType());
                            if (nXToyLoginResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                                if (nXToyResult.errorCode == NXToyErrorCode.WITHDRAWAL_PROCESSING_FAILED.getCode()) {
                                    nXToyLoginResult.requestTag = NXToyRequestTag.RecoverUser.getValue();
                                }
                                NXToyAuthManager.this.restoreLastSession();
                                if (nPListener != null) {
                                    nPListener.onResult(nXToyLoginResult);
                                    return;
                                }
                                return;
                            }
                            NXToyLoginResult.ResultSet resultSet = nXToyLoginResult.result;
                            session.setNpsn(resultSet.npSN);
                            session.setNPToken(resultSet.npToken);
                            session.setUMKey(resultSet.umKey);
                            session.setType(i6);
                            session.setEmailId(str11);
                            NXToyAuthManager.this.showTerms(activity, nXToyLoginResult, nPListener2);
                        }
                    });
                }
            });
            activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.11
                @Override // java.lang.Runnable
                public void run() {
                    builder.create().show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resolveAlreadyLoginedUser(final Activity activity, boolean z, final NPListener nPListener) {
        boolean z2;
        final NXToySession session = NXToySessionManager.getInstance().getSession();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        int iIntValue = 0;
        try {
            Map<String, Object> tempUserLoginInfo = session.getTempUserLoginInfo();
            if (tempUserLoginInfo == null) {
                return;
            }
            Object obj = tempUserLoginInfo.get(NPUserInfoDialog.KEY_LOGIN_TYPE);
            iIntValue = obj.getClass().getName().contains("Double") ? ((Double) obj).intValue() : ((Integer) obj).intValue();
            if (iIntValue == NXToyLoginType.LoginTypeGameCenter.getValue()) {
                str3 = (String) tempUserLoginInfo.get("gcId");
            } else {
                str2 = (String) tempUserLoginInfo.get("id");
                str = (String) tempUserLoginInfo.get("pw");
                Object obj2 = tempUserLoginInfo.get("email");
                str4 = obj2 instanceof String ? (String) obj2 : null;
                Object obj3 = tempUserLoginInfo.get("fbBizToken");
                if (obj3 instanceof String) {
                    str5 = (String) obj3;
                }
            }
            try {
                z2 = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 128).metaData.getBoolean("changeUserDataAlertSkip");
            } catch (PackageManager.NameNotFoundException e) {
                z2 = false;
            }
            final NPListener nPListener2 = new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.27
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    if (nPListener == null) {
                        return;
                    }
                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode() || !(nXToyResult instanceof NXToyLoginResult)) {
                        nPListener.onResult(nXToyResult);
                        return;
                    }
                    NXToyLoginResult nXToyLoginResult = (NXToyLoginResult) nXToyResult;
                    nXToyLoginResult.result.loginResultType = 2;
                    nPListener.onResult(nXToyLoginResult);
                }
            };
            if (z2 || z) {
                removeLoginInfo();
                if (iIntValue == NXToyLoginType.LoginTypeGameCenter.getValue()) {
                    loginWithGameCenter(activity, str3, nPListener2);
                    return;
                } else {
                    postSignInRequest(activity, str2, str, iIntValue, str4, str5, nPListener2);
                    return;
                }
            }
            final int i = iIntValue;
            final String str6 = str3;
            final String str7 = str;
            final String str8 = str2;
            final String str9 = str4;
            final String str10 = str5;
            new NXPAlertDialog.Builder(activity).setMessage(String.format(this.localeManager.getString(R.string.npres_resolve_using_npsn), getNameFromLoginType(iIntValue))).setPositiveButton(this.localeManager.getString(R.string.yes), new View.OnClickListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.30
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NXToyAuthManager.this.removeLoginInfo();
                    if (i == NXToyLoginType.LoginTypeGameCenter.getValue()) {
                        NXToyAuthManager.this.loginWithGameCenter(activity, str6, nPListener2);
                    } else {
                        NXToyAuthManager.this.postSignInRequest(activity, str8, str7, i, str9, str10, nPListener2);
                    }
                }
            }).setNegativeButton(this.localeManager.getString(R.string.no), new View.OnClickListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.29
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    session.setTempUserLoginInfo(null);
                    NPAuthPlugin nPAuthPluginFindAuthPlugin = NXToyAuthManager.this.findAuthPlugin(i);
                    boolean z3 = !NXPApplicationConfigManager.getInstance().isSupportSnsConnectWithGuest();
                    if (nPAuthPluginFindAuthPlugin != null && z3) {
                        nPAuthPluginFindAuthPlugin.logout(activity, null);
                    }
                    NXToyLoginResult nXToyLoginResult = new NXToyLoginResult(NXToyErrorCode.LOGIN_USER_CANCELED.getCode(), NXToyAuthManager.this.localeManager.getString(R.string.npres_logincancel), NXToyAuthManager.this.localeManager.getString(R.string.npres_logincancel));
                    nXToyLoginResult.requestTag = NXToyRequestTag.getCodeFromLoginType(i);
                    NXToyAuthManager.this.handleToyResult(nPListener, nXToyLoginResult);
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.28
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    session.setTempUserLoginInfo(null);
                    NPAuthPlugin nPAuthPluginFindAuthPlugin = NXToyAuthManager.this.findAuthPlugin(i);
                    boolean z3 = !NXPApplicationConfigManager.getInstance().isSupportSnsConnectWithGuest();
                    if (nPAuthPluginFindAuthPlugin != null && z3) {
                        nPAuthPluginFindAuthPlugin.logout(activity, null);
                    }
                    NXToyLoginResult nXToyLoginResult = new NXToyLoginResult(NXToyErrorCode.LOGIN_USER_CANCELED.getCode(), NXToyAuthManager.this.localeManager.getString(R.string.npres_logincancel), NXToyAuthManager.this.localeManager.getString(R.string.npres_logincancel));
                    nXToyLoginResult.requestTag = NXToyRequestTag.getCodeFromLoginType(i);
                    NXToyAuthManager.this.handleToyResult(nPListener, nXToyLoginResult);
                }
            }).create().show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void showAccountMenu(final Activity activity, final NPListener nPListener) {
        NXToyLoginType nXToyLoginTypeConvertIntLoginTypeToEnumLoginType = NXToyLoginType.convertIntLoginTypeToEnumLoginType(NXToySessionManager.getInstance().getSession().getType());
        if (nXToyLoginTypeConvertIntLoginTypeToEnumLoginType != NXToyLoginType.LoginTypeDefault && nXToyLoginTypeConvertIntLoginTypeToEnumLoginType != NXToyLoginType.LoginTypeNotLogined) {
            activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.5
                @Override // java.lang.Runnable
                public void run() {
                    NXPAccountMenuDialog.newInstance(activity, new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.5.1
                        @Override // kr.co.nexon.toy.listener.NPListener
                        public void onResult(NXToyResult nXToyResult) {
                            if (nPListener == null) {
                                return;
                            }
                            if (nXToyResult instanceof NXToyLoginResult) {
                                nPListener.onResult(nXToyResult);
                            } else {
                                nPListener.onResult(nXToyResult);
                            }
                        }
                    }).showDialog(activity, NXPAccountMenuDialog.TAG);
                }
            });
        } else if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NXToyErrorCode.UNSUPPORTED.getCode(), "Cannot open account menu when you are not signed in.", "", NXToyRequestTag.ShowAccountMenu.getValue()));
        }
    }

    public void showDataBackup(final Activity activity, String str, final NPListener nPListener) {
        final String string = NXStringUtil.isNull(str) ? this.localeManager.getString(R.string.npres_data_backup_title) : str;
        int type = NXToySessionManager.getInstance().getSession().getType();
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(type)) {
            case LoginTypeGuest:
                NXToyRequestPostman.getInstance().postRequest(new NXToyCreateMGTokenRequest(), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.12
                    @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                    public void onComplete(NXToyResult nXToyResult) {
                        if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                            if (nPListener != null) {
                                nPListener.onResult(nXToyResult);
                            }
                        } else {
                            NPDataBackupDialog nPDataBackupDialogNewInstance = NPDataBackupDialog.newInstance(activity, string, ((NXToyCreateMGTokenResult) nXToyResult).result.mgToken);
                            String string2 = NXToyLocaleManager.getInstance().getString(R.string.npres_cancel);
                            NXToyResult nXToyResult2 = new NXToyResult(NXToyErrorCode.BACKUP_CANCEL.getCode(), string2, string2);
                            nXToyResult2.requestTag = NXToyRequestTag.DataBackup.getValue();
                            nPDataBackupDialogNewInstance.setResultInfo(nPListener, nXToyResult2);
                            nPDataBackupDialogNewInstance.showDialog(activity, NPDataBackupDialog.TAG);
                        }
                    }
                });
                break;
            case LoginTypeNotLogined:
            case LoginTypeDefault:
                String string2 = this.localeManager.getString(R.string.npres_backup_fail_not_guest);
                NPDataBackupDialog nPDataBackupDialogNewInstanceForDescription = NPDataBackupDialog.newInstanceForDescription(activity, string, string2);
                NXToyResult nXToyResult = new NXToyResult(NXToyErrorCode.BACKUP_NOT_GUEST_USER.getCode(), string2, string2);
                nXToyResult.requestTag = NXToyRequestTag.DataBackup.getValue();
                nPDataBackupDialogNewInstanceForDescription.setResultInfo(nPListener, nXToyResult);
                nPDataBackupDialogNewInstanceForDescription.showDialog(activity, NPDataBackupDialog.TAG);
                break;
            default:
                NPDataBackupDialog nPDataBackupDialogNewInstanceForDescription2 = NPDataBackupDialog.newInstanceForDescription(activity, string, String.format(this.localeManager.getString(R.string.npres_backup_fail_login_user), getNameFromLoginType(type)));
                String string3 = this.localeManager.getString(R.string.npres_already_login);
                NXToyResult nXToyResult2 = new NXToyResult(NXToyErrorCode.ALREADY_LOGIN.getCode(), string3, string3);
                nXToyResult2.requestTag = NXToyRequestTag.DataBackup.getValue();
                nPDataBackupDialogNewInstanceForDescription2.setResultInfo(nPListener, nXToyResult2);
                nPDataBackupDialogNewInstanceForDescription2.showDialog(activity, NPDataBackupDialog.TAG);
                break;
        }
    }

    public void showDataRestore(Activity activity, String str, final NPListener nPListener) {
        String string = NXStringUtil.isNull(str) ? this.localeManager.getString(R.string.npres_data_restore_title) : str;
        int type = NXToySessionManager.getInstance().getSession().getType();
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(type)) {
            case LoginTypeGuest:
            case LoginTypeNotLogined:
            case LoginTypeDefault:
                NPDataRestoreDialog nPDataRestoreDialogNewInstance = NPDataRestoreDialog.newInstance(activity, string);
                nPDataRestoreDialogNewInstance.setResultListener(nPListener);
                nPDataRestoreDialogNewInstance.showDialog(activity, NPDataRestoreDialog.TAG);
                break;
            default:
                new NXPFullAlertDialog.FullBuilder(activity).setTitle(string).setMessage(String.format(this.localeManager.getString(R.string.npres_backup_fail_login_user), getNameFromLoginType(type))).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.13
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        if (nPListener != null) {
                            String string2 = NXToyAuthManager.this.localeManager.getString(R.string.npres_already_login);
                            nPListener.onResult(new NXToyResult(NXToyErrorCode.ALREADY_LOGIN.getCode(), string2, string2, NXToyRequestTag.DataRestore.getValue()));
                        }
                    }
                }).show();
                break;
        }
    }

    public void showLoginSelector(Activity activity, NPListener nPListener) {
        NXToyLoginType nXToyLoginTypeConvertIntLoginTypeToEnumLoginType = NXToyLoginType.convertIntLoginTypeToEnumLoginType(NXToySessionManager.getInstance().getSession().getType());
        if (NXToyLoginType.isValidLoginType(nXToyLoginTypeConvertIntLoginTypeToEnumLoginType.getValue()) && nXToyLoginTypeConvertIntLoginTypeToEnumLoginType != NXToyLoginType.LoginTypeGuest) {
            if (nPListener != null) {
                String string = this.localeManager.getString(R.string.npres_already_login);
                nPListener.onResult(new NXToyLoginResult(NXToyErrorCode.ALREADY_LOGIN.getCode(), string, string, NXToyRequestTag.Login.getValue()));
                return;
            }
            return;
        }
        List<Integer> defaultLoginTypeList = NXToyCommonPreferenceController.getInstance().getDefaultLoginTypeList();
        if (defaultLoginTypeList == null || defaultLoginTypeList.size() == 0) {
            if (nPListener != null) {
                NXToyResult nXToyResult = new NXToyResult(NXToyErrorCode.MEMBERSHIP_INFO_NOT_FOUND.getCode(), "Membership information not found. Please try enterToy API.", "Membership information not found. Please try enterToy API.");
                nXToyResult.requestTag = NXToyRequestTag.Login.getValue();
                nPListener.onResult(nXToyResult);
                return;
            }
            return;
        }
        if (defaultLoginTypeList.size() == 1) {
            login(activity, defaultLoginTypeList.get(0).intValue(), nPListener);
            return;
        }
        int value = NXToyLoginType.LoginTypeGuest.getValue();
        if (NXToySessionManager.getInstance().getSession().getType() == value && defaultLoginTypeList.contains(Integer.valueOf(value))) {
            defaultLoginTypeList.remove(Integer.valueOf(value));
        }
        NXToyCommonPreferenceController.getInstance();
        NXPLoginSelectDialog nXPLoginSelectDialogNewInstance = NXPLoginSelectDialog.newInstance(activity, defaultLoginTypeList, isLoginTPAMode(defaultLoginTypeList));
        nXPLoginSelectDialogNewInstance.setResultListener(nPListener);
        nXPLoginSelectDialogNewInstance.showDialog(activity, NXPLoginSelectDialog.TAG);
    }

    public void showUserInfoToast(final Context context, final int i, final String str) {
        HandlerThread handlerThread = new HandlerThread("userInfoToast");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(new Runnable() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.14
            @Override // java.lang.Runnable
            public void run() {
                int i2 = 0;
                String string = "";
                String str2 = str;
                if (NXStringUtil.isNull(str2)) {
                    str2 = "";
                }
                switch (AnonymousClass48.$SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.convertIntLoginTypeToEnumLoginType(i).ordinal()]) {
                    case 1:
                        i2 = R.drawable.btn_account_vkontakte;
                        string = String.format(NXToyAuthManager.this.localeManager.getString(R.string.nplogin_login_text_msg_vkontakte), str2);
                        break;
                    case 2:
                        i2 = R.drawable.btn_account_facebook;
                        string = String.format(NXToyAuthManager.this.localeManager.getString(R.string.nplogin_login_text_msg_facebook), str2);
                        break;
                    case 3:
                        i2 = R.drawable.btn_account_twitter;
                        string = String.format(NXToyAuthManager.this.localeManager.getString(R.string.nplogin_login_text_msg_twitter), str2);
                        break;
                    case 4:
                        i2 = R.drawable.btn_account_google;
                        string = String.format(NXToyAuthManager.this.localeManager.getString(R.string.nplogin_login_text_msg_google), str2);
                        break;
                    case 5:
                    case 10:
                        i2 = R.drawable.btn_account_nexon;
                        string = String.format(NXToyAuthManager.this.localeManager.getString(R.string.nplogin_login_text_msg_nxcom), str2);
                        break;
                    case 6:
                    case 7:
                        i2 = R.drawable.btn_account_naver;
                        string = String.format(NXToyAuthManager.this.localeManager.getString(R.string.nplogin_login_text_msg_naver), str2);
                        break;
                    case 8:
                        i2 = R.drawable.btn_account_daum;
                        string = String.format(NXToyAuthManager.this.localeManager.getString(R.string.nplogin_login_text_msg_daum), str2);
                        break;
                    case 9:
                        i2 = R.drawable.btn_account_email;
                        string = String.format(NXToyAuthManager.this.localeManager.getString(R.string.nplogin_login_text_msg_email), str2);
                        break;
                    case 11:
                        i2 = R.drawable.btn_account_playgame_white;
                        string = String.format(NXToyAuthManager.this.localeManager.getString(R.string.nplogin_login_text_msg_gamecenter), str2);
                        break;
                    case 12:
                        i2 = R.drawable.btn_account_guest;
                        string = NXToyAuthManager.this.localeManager.getString(R.string.nplogin_login_text_msg_guest);
                        break;
                }
                NXToast.make(context, i2, string.trim(), 2, 0).show();
            }
        });
    }

    public void signUpWithEmail(final Activity activity, final String str, final String str2, final NPListener nPListener) {
        if (str != null && str2 != null) {
            NXToyRequestPostman.getInstance().postRequest(new NXToySignUpNPAARequest(str, str2, new NXToySignInRequestOptionalBody(activity, str, null)), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.16
                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                public void onComplete(NXToyResult nXToyResult) {
                    NXToySession session = NXToySessionManager.getInstance().getSession();
                    ToyLog.d(nXToyResult.toString());
                    ToyLog.d("loginType = " + NXToySessionManager.getInstance().getSession().getType());
                    if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                        NXToyLoginResult.ResultSet resultSet = ((NXToyLoginResult) nXToyResult).result;
                        session.setNpsn(resultSet.npSN);
                        session.setNPToken(resultSet.npToken);
                        session.setUMKey(resultSet.umKey);
                        session.setType(NXToyLoginType.LoginTypeEmail.getValue());
                        session.setAccountId(str);
                        session.setEmailId(str);
                        NXToyAuthManager.this.showTerms(activity, (NXToyLoginResult) nXToyResult, nPListener);
                        return;
                    }
                    switch (AnonymousClass48.$SwitchMap$com$nexon$core$requestpostman$constants$NXToyErrorCode[NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(nXToyResult.errorCode).ordinal()]) {
                        case 11:
                        case 12:
                            HashMap map = new HashMap();
                            map.put("id", str);
                            map.put("pw", str2);
                            map.put(NPUserInfoDialog.KEY_LOGIN_TYPE, Integer.valueOf(NXToyLoginType.LoginTypeEmail.getValue()));
                            map.put("email", str);
                            session.setTempUserLoginInfo(map);
                            NXToySessionManager.getInstance().setSession(NXToyAuthManager.this.prevSession);
                            nPListener.onResult(nXToyResult);
                            break;
                        default:
                            NXToyAuthManager.this.restoreLastSession();
                            if (nPListener != null) {
                                nPListener.onResult(nXToyResult);
                            }
                            break;
                    }
                }
            });
            return;
        }
        ToyLog.d("Email SignUp was failed, Invalid args . ID or password error");
        ToyLog.d("loginType = " + NXToySessionManager.getInstance().getSession().getType());
        restoreLastSession();
        if (nPListener != null) {
            nPListener.onResult(new NXToyLoginResult(NXToyErrorCode.INVALID_ARGS.getCode(), "ID or password error", ""));
        }
    }

    public void unregisterService(final NPListener nPListener) {
        ToyLog.d("[unregisterService]");
        NXToyRequestPostman.getInstance().postRequest(new NXToyUnregisterSVCRequest(), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.6
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(final NXToyResult nXToyResult) {
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NXToyAuthManager.this.removeAuthInfo(new NPListener() { // from class: kr.co.nexon.npaccount.auth.NXToyAuthManager.6.1
                        @Override // kr.co.nexon.toy.listener.NPListener
                        public void onResult(NXToyResult nXToyResult2) {
                            if (nPListener != null) {
                                nPListener.onResult(nXToyResult);
                            }
                        }
                    });
                } else if (nPListener != null) {
                    nPListener.onResult(nXToyResult);
                }
            }
        });
    }
}
