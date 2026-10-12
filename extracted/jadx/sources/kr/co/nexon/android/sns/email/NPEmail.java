package kr.co.nexon.android.sns.email;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import kr.co.nexon.android.sns.NPAuthFriendsListener;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.email.ui.NPEmailLoginCheckDialog;
import kr.co.nexon.android.sns.email.ui.NPEmailLoginDialog;
import kr.co.nexon.android.sns.email.ui.NPEmailSignUpDialog;
import kr.co.nexon.npaccount.auth.request.NXToyGetEmailUserInfoRequest;
import kr.co.nexon.npaccount.auth.result.NXToyEmailUserInfoResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPEmail extends NPAuthPlugin {
    public static final int CODE_BACK_CLICK = 90204;
    public static final int CODE_USING_TYPE_GET_NPSN = 1;
    public static final int CODE_USING_TYPE_LOGIN = 0;
    public static final String KEY_TOY_RESULT = "toyresult";
    public static final String KEY_TOY_SESSION = "toysession";
    private static final String NPSNS_PREF_EMAIL = "email";
    public static String SERVICE_NAME = "email";

    public NPEmail(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkEmailId(final Activity activity, String str, final String str2, final NPAuthListener nPAuthListener) {
        NPEmailLoginCheckDialog nPEmailLoginCheckDialogNewInstance = NPEmailLoginCheckDialog.newInstance(activity, str2);
        nPEmailLoginCheckDialogNewInstance.setResultListener(new NPEmailLoginCheckDialog.NXPEmailLoginCheckListener() { // from class: kr.co.nexon.android.sns.email.NPEmail.3
            @Override // kr.co.nexon.android.sns.email.ui.NPEmailLoginCheckDialog.NXPEmailLoginCheckListener
            public void onComplete(int i, String str3, final boolean z, final String str4) {
                Bundle bundle = new Bundle();
                if (i == NXToyErrorCode.SUCCESS.getCode()) {
                    final NPAuthListener nPAuthListener2 = new NPAuthListener() { // from class: kr.co.nexon.android.sns.email.NPEmail.3.1
                        @Override // kr.co.nexon.android.sns.NPAuthListener
                        public void onResult(int i2, String str5, Bundle bundle2) {
                            if (i2 == NXToyErrorCode.SUCCESS.getCode()) {
                                NPEmail.this.saveLastEmailId(activity, str4);
                            }
                            nPAuthListener.onResult(i2, str5, bundle2);
                        }
                    };
                    activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.email.NPEmail.3.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (z) {
                                NPEmail.this.signIn(activity, 0, str4, str2, nPAuthListener2);
                            } else {
                                NPEmail.this.signUp(activity, 0, str4, str2, nPAuthListener2);
                            }
                        }
                    });
                } else {
                    bundle.putString("toyresult", NXJsonUtil.toJsonString(new NXToyResult(i, str3, str3, NXToyLoginType.LoginTypeEmail.getValue())));
                    nPAuthListener.onResult(i, str3, bundle);
                }
            }
        });
        if (NXStringUtil.isNotNull(str)) {
            nPEmailLoginCheckDialogNewInstance.setEmailId(str);
        }
        nPEmailLoginCheckDialogNewInstance.showDialog(activity, NPEmailLoginCheckDialog.TAG);
    }

    private String getLastEmailId(Activity activity) {
        return activity.getSharedPreferences("nxpEmailUserInfo", 0).getString("lastLoginedEmailId", "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveLastEmailId(Activity activity, String str) {
        activity.getSharedPreferences("nxpEmailUserInfo", 0).edit().putString("lastLoginedEmailId", str).commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void signIn(final Activity activity, int i, final String str, final String str2, final NPAuthListener nPAuthListener) {
        NPEmailLoginDialog nPEmailLoginDialogNewInstance = NPEmailLoginDialog.newInstance(activity, i, str, str2);
        nPEmailLoginDialogNewInstance.setResultListener(new NPEmailListener() { // from class: kr.co.nexon.android.sns.email.NPEmail.5
            @Override // kr.co.nexon.android.sns.email.NPEmailListener
            public void onComplete(int i2, String str3, Bundle bundle) {
                if (i2 == 90204) {
                    NPEmail.this.checkEmailId(activity, str, str2, nPAuthListener);
                    return;
                }
                if (bundle == null) {
                    NXToyResult nXToyResult = new NXToyResult(i2, str3, str3, NXToyLoginType.LoginTypeEmail.getValue());
                    bundle = new Bundle();
                    bundle.putString("toyresult", NXJsonUtil.toJsonString(nXToyResult));
                }
                if (nPAuthListener != null) {
                    nPAuthListener.onResult(i2, str3, bundle);
                }
            }
        });
        nPEmailLoginDialogNewInstance.showDialog(activity, NPEmailLoginDialog.TAG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void signUp(final Activity activity, int i, final String str, final String str2, final NPAuthListener nPAuthListener) {
        if (i != 1) {
            NPEmailSignUpDialog nPEmailSignUpDialogNewInstance = NPEmailSignUpDialog.newInstance(activity, str, str2);
            nPEmailSignUpDialogNewInstance.setResultListener(new NPEmailListener() { // from class: kr.co.nexon.android.sns.email.NPEmail.4
                @Override // kr.co.nexon.android.sns.email.NPEmailListener
                public void onComplete(int i2, String str3, Bundle bundle) {
                    if (i2 == 90204) {
                        NPEmail.this.checkEmailId(activity, str, str2, nPAuthListener);
                        return;
                    }
                    if (bundle == null) {
                        NXToyResult nXToyResult = new NXToyResult(i2, str3, str3, NXToyLoginType.LoginTypeEmail.getValue());
                        bundle = new Bundle();
                        bundle.putString("toyresult", NXJsonUtil.toJsonString(nXToyResult));
                    }
                    if (nPAuthListener != null) {
                        nPAuthListener.onResult(i2, str3, bundle);
                    }
                }
            });
            nPEmailSignUpDialogNewInstance.showDialog(activity, NPEmailLoginDialog.TAG);
        } else {
            new NXToyResult(NXToyErrorCode.NPSN_NOT_FOUND.getCode(), "npsn not found", "npsn not found").requestTag = NXToyRequestTag.GetNPSN.getValue();
            if (nPAuthListener != null) {
                nPAuthListener.onResult(NXToyErrorCode.NPSN_NOT_FOUND.getCode(), "npsn not found", null);
            }
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getAccessToken(Context context, NPAuthListener nPAuthListener) {
        Bundle bundle = new Bundle();
        bundle.putString(NPAuthPlugin.KEY_ACCESSTOKEN, "");
        bundle.putString(NPAuthPlugin.KEY_SECRETTOKEN, "");
        nPAuthListener.onResult(0, null, bundle);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getFriends(Context context, boolean z, NPAuthFriendsListener nPAuthFriendsListener) {
        nPAuthFriendsListener.onResult(NPAuthPlugin.CODE_NOT_SUPPORT, null, false, null);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getUserInfo(Context context, final NPAuthListener nPAuthListener) {
        if (NXStringUtil.isNotNull(this.extraData)) {
        } else {
            new NXToySession();
        }
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetEmailUserInfoRequest(NXToySessionManager.getInstance().getSession().getNptoken()), new NXToyRequestListener() { // from class: kr.co.nexon.android.sns.email.NPEmail.1
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                Bundle bundle = new Bundle();
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NXToyEmailUserInfoResult nXToyEmailUserInfoResult = (NXToyEmailUserInfoResult) nXToyResult;
                    bundle.putString(NPAuthPlugin.KEY_ID, nXToyEmailUserInfoResult.extend.id);
                    bundle.putString(NPAuthPlugin.KEY_EMAIL, nXToyEmailUserInfoResult.email);
                }
                nPAuthListener.onResult(nXToyResult.errorCode, nXToyResult.errorText, bundle);
            }
        });
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void isConnect(Context context, NPAuthListener nPAuthListener) {
        if (NXStringUtil.isNull(getPref().getString("email", ""))) {
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
    public void login(Activity activity, NPAuthListener nPAuthListener) {
        checkEmailId(activity, getLastEmailId(activity), this.extraData, nPAuthListener);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void logout(Context context, NPAuthListener nPAuthListener) {
        getEditor().putString("email", "").commit();
        if (nPAuthListener != null) {
            nPAuthListener.onResult(0, null, null);
        }
    }

    public void queryNpsn(final Activity activity, final NPAuthListener nPAuthListener) {
        NPEmailLoginCheckDialog nPEmailLoginCheckDialogNewInstance = NPEmailLoginCheckDialog.newInstance(activity, this.extraData);
        nPEmailLoginCheckDialogNewInstance.setResultListener(new NPEmailLoginCheckDialog.NXPEmailLoginCheckListener() { // from class: kr.co.nexon.android.sns.email.NPEmail.2
            @Override // kr.co.nexon.android.sns.email.ui.NPEmailLoginCheckDialog.NXPEmailLoginCheckListener
            public void onComplete(int i, String str, final boolean z, final String str2) {
                Bundle bundle = new Bundle();
                if (i == NXToyErrorCode.SUCCESS.getCode()) {
                    activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.email.NPEmail.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (z) {
                                NPEmail.this.signIn(activity, 1, str2, NPEmail.this.extraData, nPAuthListener);
                            } else {
                                NPEmail.this.signUp(activity, 1, str2, NPEmail.this.extraData, nPAuthListener);
                            }
                        }
                    });
                } else {
                    bundle.putString("toyresult", NXJsonUtil.toJsonString(new NXToyResult(i, str, str, NXToyLoginType.LoginTypeEmail.getValue())));
                    nPAuthListener.onResult(i, str, bundle);
                }
            }
        });
        nPEmailLoginCheckDialogNewInstance.showDialog(activity, NPEmailLoginCheckDialog.TAG);
    }
}
