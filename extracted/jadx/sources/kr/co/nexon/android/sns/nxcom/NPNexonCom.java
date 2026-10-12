package kr.co.nexon.android.sns.nxcom;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.gson.Gson;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.platform.auth.model.NXPProviderAuthenticationInfo;
import java.util.Arrays;
import java.util.HashMap;
import kr.co.nexon.android.sns.NPAuthFriendsListener;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.google.NPGoogleSignIn;
import kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog;
import kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog;
import kr.co.nexon.android.sns.nxcom.ui.NPNXComSearchIDPWDialog;
import kr.co.nexon.android.sns.nxcom.ui.NPNXComSignUpSelectDialog;
import kr.co.nexon.android.sns.nxcom.ui.NPNXComUserAuthWebDialog;
import kr.co.nexon.npaccount.auth.request.NXToyEncryptForNXKRealNameAuthenticationRequest;
import kr.co.nexon.npaccount.auth.request.NXToySignInRequest;
import kr.co.nexon.npaccount.auth.request.model.NXToySignInRequestOptionalBody;
import kr.co.nexon.npaccount.auth.result.NXToyLoginResult;
import kr.co.nexon.npaccount.auth.result.NXToyNXKEncryptResult;
import kr.co.nexon.npaccount.auth.result.NXToyNexonSNResult;
import kr.co.nexon.toy.android.ui.baseplate.NPUserInfoDialog;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNexonCom extends NPAuthPlugin implements NPNXComLoginDialog.NPNexonComDialogEvent {
    public static final String KEY_NEXON_ACCOUNT_ID = "key_nexon_id";
    public static final String KEY_TOY_RESULT = "toyresult";
    public static final String KEY_TOY_SESSION = "toysession";
    private static final String NEXON_ACCOUNT_ID_SHARED_KEY = "nxid";
    private static final String NEXON_COM_SHARED_PREFERENCE_NAME = "nxinfo";
    private static final String NPSNS_PREF_NXCOM = "nxcom";
    public static String SERVICE_NAME = "nexon";
    public NPGoogleSignIn googleSignIn;
    private SharedPreferences sharedPreferences;

    public NPNexonCom(Context context) {
        super(context);
        this.sharedPreferences = context.getSharedPreferences(NEXON_COM_SHARED_PREFERENCE_NAME, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cacheNexonAccountId(String str) {
        this.sharedPreferences.edit().putString(NEXON_ACCOUNT_ID_SHARED_KEY, str).commit();
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getAccessToken(Context context, NPAuthListener nPAuthListener) {
        Bundle bundle = new Bundle();
        bundle.putString(NPAuthPlugin.KEY_ACCESSTOKEN, "");
        bundle.putString(NPAuthPlugin.KEY_SECRETTOKEN, "");
        nPAuthListener.onResult(0, null, bundle);
    }

    public String getCachedNexonAccountId() {
        return this.sharedPreferences.getString(NEXON_ACCOUNT_ID_SHARED_KEY, "");
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getFriends(Context context, boolean z, NPAuthFriendsListener nPAuthFriendsListener) {
        nPAuthFriendsListener.onResult(NPAuthPlugin.CODE_NOT_SUPPORT, null, false, null);
    }

    public void getNexonSN(Activity activity, final NPListener nPListener) {
        NPGetNexonSNDialog nPGetNexonSNDialogNewInstance = NPGetNexonSNDialog.newInstance(activity, this.extraData, getCachedNexonAccountId(), true);
        nPGetNexonSNDialogNewInstance.setResultListener(new NPGetNexonSNDialog.NXPGetNexonSNListener() { // from class: kr.co.nexon.android.sns.nxcom.NPNexonCom.5
            @Override // kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.NXPGetNexonSNListener
            public void onFailure(int i, @NonNull String str) {
                if (nPListener != null) {
                    nPListener.onResult(new NXToyNexonSNResult(i, str, str, NXToyRequestTag.GetNexonSN.getValue()));
                }
            }

            @Override // kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.NXPGetNexonSNListener
            public void onNeedToCache(@NonNull String str) {
                NPNexonCom.this.cacheNexonAccountId(str);
            }

            @Override // kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.NXPGetNexonSNListener
            public void onSuccess(@NonNull NXToyNexonSNResult nXToyNexonSNResult, @Nullable NXPProviderAuthenticationInfo nXPProviderAuthenticationInfo) {
                if (nXPProviderAuthenticationInfo != null) {
                    NPNexonCom.this.cacheAuthenticationInfo(nXPProviderAuthenticationInfo);
                }
                if (nPListener != null) {
                    nPListener.onResult(nXToyNexonSNResult);
                }
            }
        });
        nPGetNexonSNDialogNewInstance.showDialog(activity, NPGetNexonSNDialog.TAG);
    }

    public void getNexonSNFromWeb(Activity activity, final NPListener nPListener) {
        NPGetNexonSNDialog nPGetNexonSNDialogNewInstance = NPGetNexonSNDialog.newInstance(activity, this.extraData, getCachedNexonAccountId(), false);
        nPGetNexonSNDialogNewInstance.setResultListener(new NPGetNexonSNDialog.NXPGetNexonSNListener() { // from class: kr.co.nexon.android.sns.nxcom.NPNexonCom.4
            @Override // kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.NXPGetNexonSNListener
            public void onFailure(int i, @NonNull String str) {
                if (nPListener != null) {
                    nPListener.onResult(new NXToyNexonSNResult(i, str, str, NXToyRequestTag.GetNexonSN.getValue()));
                }
            }

            @Override // kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.NXPGetNexonSNListener
            public void onNeedToCache(@NonNull String str) {
                NPNexonCom.this.cacheNexonAccountId(str);
            }

            @Override // kr.co.nexon.android.sns.nxcom.ui.NPGetNexonSNDialog.NXPGetNexonSNListener
            public void onSuccess(@NonNull NXToyNexonSNResult nXToyNexonSNResult, @Nullable NXPProviderAuthenticationInfo nXPProviderAuthenticationInfo) {
                if (nPListener != null) {
                    nPListener.onResult(nXToyNexonSNResult);
                }
            }
        });
        nPGetNexonSNDialogNewInstance.showDialog(activity, NPGetNexonSNDialog.TAG);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getUserInfo(Context context, final NPAuthListener nPAuthListener) {
        isConnect(context, new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.NPNexonCom.2
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str, Bundle bundle) {
                nPAuthListener.onResult(i, str, bundle);
            }
        });
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void isConnect(Context context, NPAuthListener nPAuthListener) {
        if (NXStringUtil.isNull(getPref().getString(NPSNS_PREF_NXCOM, ""))) {
            nPAuthListener.onResult(NPAuthPlugin.CODE_NOT_SUPPORT, null, null);
        } else {
            nPAuthListener.onResult(0, null, null);
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public boolean isConnected() {
        return false;
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void login(Activity activity, final NPAuthListener nPAuthListener) {
        if (this.googleSignIn == null) {
            this.googleSignIn = new NPGoogleSignIn(activity);
        }
        NPNXComLoginDialog nPNXComLoginDialogNewInstance = NPNXComLoginDialog.newInstance(activity, 0, getCachedNexonAccountId(), this.extraData);
        nPNXComLoginDialogNewInstance.setEventHandler(this);
        nPNXComLoginDialogNewInstance.setResultListener(new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.NPNexonCom.1
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str, Bundle bundle) {
                String string = bundle.getString("toyresult");
                if (((NXToyLoginResult) NXJsonUtil.fromObject(string, NXToyLoginResult.class)).errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NPNexonCom.this.getEditor().putString(NPNexonCom.NPSNS_PREF_NXCOM, string).commit();
                    String string2 = bundle.getString("key_nexon_id", null);
                    if (NXStringUtil.isNotNull(string2)) {
                        NPNexonCom.this.cacheNexonAccountId(string2);
                    }
                }
                if (nPAuthListener != null) {
                    nPAuthListener.onResult(i, str, bundle);
                }
            }
        });
        nPNXComLoginDialogNewInstance.setGoogleSignIn(this.googleSignIn);
        nPNXComLoginDialogNewInstance.showDialog(activity, NPNXComLoginDialog.TAG);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void logout(Context context, NPAuthListener nPAuthListener) {
        getEditor().putString(NPSNS_PREF_NXCOM, "").commit();
        clearLastAuthenticationInfo();
        if (nPAuthListener != null) {
            nPAuthListener.onResult(0, null, null);
        }
    }

    public void nexonComLogin(Activity activity, final String str, final byte[] bArr, final NXToySession nXToySession, final NPAuthListener nPAuthListener) {
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        String uuid = nXToyCommonPreferenceController.getUUID();
        String uuid2 = nXToyCommonPreferenceController.getUUID2();
        if (NXStringUtil.isNull(uuid) || NXStringUtil.isNull(uuid2)) {
            nPAuthListener.onResult(NXToyErrorCode.INVALID_ARGS.getCode(), "UUID is null", null);
        } else {
            NXToyRequestPostman.getInstance().postRequest(new NXToySignInRequest(nXToySession.getType() == NXToyLoginType.LoginTypeGuest.getValue() ? String.valueOf(nXToySession.getNpsn()) : "0", str, bArr, uuid, uuid2, NXToyLoginType.LoginTypeNXCom.getValue(), new NXToySignInRequestOptionalBody(activity, str, null)), new NXToyRequestListener() { // from class: kr.co.nexon.android.sns.nxcom.NPNexonCom.6
                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                public void onComplete(NXToyResult nXToyResult) {
                    Bundle bundle = new Bundle();
                    NXToyLoginResult nXToyLoginResult = (NXToyLoginResult) nXToyResult;
                    if (nXToyResult.errorCode == 0) {
                        NXToyLoginResult.ResultSet resultSet = nXToyLoginResult.result;
                        nXToySession.setNpsn(resultSet.npSN);
                        nXToySession.setNPToken(resultSet.npToken);
                        nXToySession.setUMKey(resultSet.umKey);
                        nXToySession.setType(NXToyLoginType.LoginTypeNXCom.getValue());
                        nXToySession.setAccountId(str);
                        if (NXStringUtil.isValidEmail(str)) {
                            nXToySession.setEmailId(str);
                        }
                    }
                    switch (NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(nXToyResult.errorCode)) {
                        case WITHDRAWAL_PROCESSING_BY_THE_USER:
                        case USING_NPSN_USER_NEED_RESOLVE:
                            HashMap map = new HashMap();
                            map.put("id", str);
                            map.put("pw", new String(bArr));
                            map.put(NPUserInfoDialog.KEY_LOGIN_TYPE, Integer.valueOf(NXToyLoginType.LoginTypeNXCom.getValue()));
                            map.put("email", str);
                            nXToySession.setTempUserLoginInfo(map);
                            break;
                    }
                    Arrays.fill(bArr, (byte) 0);
                    bundle.putString("toyresult", NXJsonUtil.toJsonString(nXToyResult));
                    bundle.putString("toysession", NXJsonUtil.toJsonString(nXToySession));
                    nPAuthListener.onResult(nXToyResult.errorCode, nXToyResult.errorText, bundle);
                }
            });
        }
    }

    @Override // kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.NPNexonComDialogEvent
    public void onLogin(Activity activity, String str, byte[] bArr, NXToySession nXToySession, NPAuthListener nPAuthListener) {
        nexonComLogin(activity, str, bArr, nXToySession, nPAuthListener);
    }

    @Override // kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.NPNexonComDialogEvent
    public void onNeedShowNXAuth(Activity activity, NXToySession nXToySession, String str, NPAuthListener nPAuthListener) {
        showNXAuth(activity, nXToySession, str, nPAuthListener);
    }

    @Override // kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.NPNexonComDialogEvent
    public void onPrepareNXAuth(Activity activity, NXToySession nXToySession, NPAuthListener nPAuthListener) {
        prepareNexonAuth(activity, nXToySession, nPAuthListener);
    }

    @Override // kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.NPNexonComDialogEvent
    public void onSearchID(Activity activity) {
        showSearchIDDialog(activity);
    }

    @Override // kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.NPNexonComDialogEvent
    public void onSearchPassword(Activity activity) {
        showSearchPasswordDialog(activity);
    }

    @Override // kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.NPNexonComDialogEvent
    public void onSignUpNXCom(Activity activity) {
        showSignUpDialog(activity);
    }

    public void prepareNexonAuth(Activity activity, NXToySession nXToySession, final NPAuthListener nPAuthListener) {
        NXToyRequestListener nXToyRequestListener = new NXToyRequestListener() { // from class: kr.co.nexon.android.sns.nxcom.NPNexonCom.10
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                Bundle bundle = new Bundle();
                bundle.putString("toyresult", NXJsonUtil.toJsonString((NXToyNXKEncryptResult) nXToyResult));
                nPAuthListener.onResult(nXToyResult.errorCode, nXToyResult.errorText, bundle);
            }
        };
        NXToyRequestPostman.getInstance().postRequest(new NXToyEncryptForNXKRealNameAuthenticationRequest(nXToySession), nXToyRequestListener);
    }

    public void queryNpsn(Activity activity, final NPAuthListener nPAuthListener) {
        NPNXComLoginDialog nPNXComLoginDialogNewInstance = NPNXComLoginDialog.newInstance(activity, 1, getCachedNexonAccountId(), this.extraData);
        nPNXComLoginDialogNewInstance.setEventHandler(this);
        nPNXComLoginDialogNewInstance.setResultListener(new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.NPNexonCom.3
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str, Bundle bundle) {
                if (i == NXToyErrorCode.SUCCESS.getCode()) {
                    String string = bundle.getString("key_nexon_id", null);
                    if (NXStringUtil.isNotNull(string)) {
                        NPNexonCom.this.cacheNexonAccountId(string);
                    }
                }
                if (nPAuthListener != null) {
                    nPAuthListener.onResult(i, str, bundle);
                }
            }
        });
        nPNXComLoginDialogNewInstance.showDialog(activity, NPNXComLoginDialog.TAG);
    }

    public void showNXAuth(Activity activity, NXToySession nXToySession, String str, NPAuthListener nPAuthListener) {
        NPNXComUserAuthWebDialog nPNXComUserAuthWebDialogNewInstance = NPNXComUserAuthWebDialog.newInstance(activity, str, new Gson().toJson(nXToySession));
        nPNXComUserAuthWebDialogNewInstance.setResultListener(new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.NPNexonCom.11
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str2, Bundle bundle) {
                if (i == 0) {
                }
            }
        });
        nPNXComUserAuthWebDialogNewInstance.showDialog(activity, NPNXComUserAuthWebDialog.TAG);
    }

    public void showSearchIDDialog(Activity activity) {
        ToyLog.d("Search ID");
        NPNXComSearchIDPWDialog nPNXComSearchIDPWDialogNewInstance = NPNXComSearchIDPWDialog.newInstance(activity, this.extraData, 0);
        nPNXComSearchIDPWDialogNewInstance.setResultListener(new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.NPNexonCom.8
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str, Bundle bundle) {
            }
        });
        nPNXComSearchIDPWDialogNewInstance.showDialog(activity, NPNXComSearchIDPWDialog.TAG);
    }

    public void showSearchPasswordDialog(Activity activity) {
        ToyLog.d("Search PW");
        NPNXComSearchIDPWDialog nPNXComSearchIDPWDialogNewInstance = NPNXComSearchIDPWDialog.newInstance(activity, this.extraData, 1);
        nPNXComSearchIDPWDialogNewInstance.setResultListener(new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.NPNexonCom.9
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str, Bundle bundle) {
            }
        });
        nPNXComSearchIDPWDialogNewInstance.showDialog(activity, NPNXComSearchIDPWDialog.TAG);
    }

    public void showSignUpDialog(Activity activity) {
        if (this.googleSignIn == null) {
            this.googleSignIn = new NPGoogleSignIn(activity);
        }
        NPNXComSignUpSelectDialog nPNXComSignUpSelectDialogNewInstance = NPNXComSignUpSelectDialog.newInstance(activity, this.extraData);
        nPNXComSignUpSelectDialogNewInstance.setResultListener(new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.NPNexonCom.7
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str, Bundle bundle) {
            }
        });
        nPNXComSignUpSelectDialogNewInstance.setGoogleSignIn(this.googleSignIn);
        nPNXComSignUpSelectDialogNewInstance.showDialog(activity, NPNXComSignUpSelectDialog.TAG);
    }
}
