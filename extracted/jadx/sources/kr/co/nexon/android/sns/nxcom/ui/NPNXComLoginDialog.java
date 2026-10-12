package kr.co.nexon.android.sns.nxcom.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import java.util.Arrays;
import java.util.HashMap;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.google.NPGoogleSignIn;
import kr.co.nexon.android.sns.nxcom.ui.view.NXPNexonLoginView;
import kr.co.nexon.mdev.android.util.NXDeviceUtil;
import kr.co.nexon.mdev.android.util.NXLocalizedString;
import kr.co.nexon.mdev.android.view.NXInputEditView;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.auth.request.NXToyGetNPSNRequest;
import kr.co.nexon.npaccount.auth.result.NXToyLoginResult;
import kr.co.nexon.npaccount.auth.result.NXToyNXKEncryptResult;
import kr.co.nexon.npaccount.auth.result.NXToyNpsnResult;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.baseplate.NPUserInfoDialog;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXComLoginDialog extends NPDialogBase {
    public static final int CODE_USING_TYPE_GET_NPSN = 1;
    public static final int CODE_USING_TYPE_LOGIN = 0;
    public static final int CODE_USING_TYPE_RECOVERY = 2;
    public static final String KEY_EXTRA_DATA = "extraData";
    public static final String KEY_MODE = "mode";
    public static final String KEY_NEXON_ACCOUNT_ID = "key_nexon_id";
    public static final String KEY_TOY_RESULT = "toyresult";
    public static final String KEY_TOY_SESSION = "toysession";
    public static final String TAG = "NPNXComLoginDialog";
    private NPNexonComDialogEvent eventHandler;
    private NPGoogleSignIn googleSignIn;
    private String localeCode;
    private int mode = 0;
    private NXPNexonLoginView nexonLoginView;
    private NPAuthListener resultListener;
    private NXToySession session;

    /* JADX INFO: renamed from: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog$8, reason: invalid class name */
    class AnonymousClass8 implements NPAuthListener {
        final /* synthetic */ String val$id;
        final /* synthetic */ byte[] val$pw;

        AnonymousClass8(byte[] bArr, String str) {
            this.val$pw = bArr;
            this.val$id = str;
        }

        @Override // kr.co.nexon.android.sns.NPAuthListener
        public void onResult(final int i, String str, final Bundle bundle) {
            NPNXComLoginDialog.this.runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.8.1
                @Override // java.lang.Runnable
                public void run() {
                    NPNXComLoginDialog.this.dismissProgressDialog();
                    Arrays.fill(AnonymousClass8.this.val$pw, (byte) 0);
                    final NXToyLoginResult nXToyLoginResult = (NXToyLoginResult) NXJsonUtil.fromObject(bundle.getString("toyresult"), NXToyLoginResult.class);
                    bundle.getString("toysession");
                    if (i == 0) {
                        bundle.putString("key_nexon_id", AnonymousClass8.this.val$id);
                        NPNXComLoginDialog.this.resultListener.onResult(0, "", bundle);
                        NPNXComLoginDialog.this.dismiss();
                    } else {
                        if (i == NXToyErrorCode.NEED_AUTH.getCode()) {
                            String text = NXLocalizedString.getText(NPNXComLoginDialog.this.applicationContext, NPNXComLoginDialog.this.localeCode, R.string.nxauth2_text);
                            String text2 = NXLocalizedString.getText(NPNXComLoginDialog.this.applicationContext, NPNXComLoginDialog.this.localeCode, R.string.no);
                            new NXPAlertDialog.Builder(NPNXComLoginDialog.this.getActivity()).setMessage(text).setNegativeButton(text2, null).setPositiveButton(NXLocalizedString.getText(NPNXComLoginDialog.this.applicationContext, NPNXComLoginDialog.this.localeCode, R.string.yes), new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.8.1.1
                                @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                                public void onSwallowClick(View view) {
                                    NPNXComLoginDialog.this.processGoNXAuth(nXToyLoginResult);
                                }
                            }).create().show();
                            return;
                        }
                        if (i != NXToyErrorCode.WITHDRAWAL_PROCESSING_BY_THE_USER.getCode() && i != NXToyErrorCode.USING_NPSN_USER_NEED_RESOLVE.getCode() && i != NXToyErrorCode.USING_NPSN_USER.getCode()) {
                            NPNXComLoginDialog.this.nexonLoginView.setErrorMessage(nXToyLoginResult.errorText);
                        } else {
                            NPNXComLoginDialog.this.resultListener.onResult(i, nXToyLoginResult.errorText, bundle);
                            NPNXComLoginDialog.this.dismiss();
                        }
                    }
                }
            });
        }
    }

    public interface NPNexonComDialogEvent {
        void onLogin(Activity activity, String str, byte[] bArr, NXToySession nXToySession, NPAuthListener nPAuthListener);

        void onNeedShowNXAuth(Activity activity, NXToySession nXToySession, String str, NPAuthListener nPAuthListener);

        void onPrepareNXAuth(Activity activity, NXToySession nXToySession, NPAuthListener nPAuthListener);

        void onSearchID(Activity activity);

        void onSearchPassword(Activity activity);

        void onSignUpNXCom(Activity activity);
    }

    private void getNXUserNPSN(final String str, final byte[] bArr) {
        showProgressDialog();
        final NXToyRequestListener nXToyRequestListener = new NXToyRequestListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.10
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(final NXToyResult nXToyResult) {
                NPNXComLoginDialog.this.dismissProgressDialog();
                Bundle bundle = new Bundle();
                bundle.putString("toyresult", NXJsonUtil.toJsonString(nXToyResult));
                bundle.putString("toysession", NXJsonUtil.toJsonString(NPNXComLoginDialog.this.session));
                if (nXToyResult.errorCode == 0) {
                    NPNXComLoginDialog.this.resultListener.onResult(0, nXToyResult.errorText, bundle);
                    NPNXComLoginDialog.this.dismiss();
                } else if (nXToyResult.errorCode != NXToyErrorCode.NPSN_NOT_FOUND.getCode()) {
                    NPNXComLoginDialog.this.runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.10.1
                        @Override // java.lang.Runnable
                        public void run() {
                            NPNXComLoginDialog.this.nexonLoginView.setErrorMessage(nXToyResult.errorText);
                        }
                    });
                } else {
                    NPNXComLoginDialog.this.resultListener.onResult(nXToyResult.errorCode, nXToyResult.errorText, bundle);
                    NPNXComLoginDialog.this.dismiss();
                }
            }
        };
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetNPSNRequest(str, new String(bArr), NXToyLoginType.LoginTypeNXCom.getValue()), new NXToyRequestListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.11
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode == 0) {
                    HashMap map = new HashMap();
                    map.put("id", str);
                    map.put("pw", new String(bArr));
                    map.put(NPUserInfoDialog.KEY_LOGIN_TYPE, Integer.valueOf(NXToyLoginType.LoginTypeNXCom.getValue()));
                    NPNXComLoginDialog.this.session.setTempUserLoginInfoForNpsn(((NXToyNpsnResult) nXToyResult).result.npsn, map);
                }
                Arrays.fill(bArr, (byte) 0);
                nXToyRequestListener.onComplete(nXToyResult);
            }
        });
    }

    public static NPNXComLoginDialog newInstance(Activity activity, int i, String str, String str2) {
        NPNXComLoginDialog nPNXComLoginDialog = new NPNXComLoginDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putInt("mode", i);
        bundle.putString(KEY_EXTRA_DATA, str2);
        bundle.putString("key_nexon_id", str);
        nPNXComLoginDialog.setArguments(bundle);
        return nPNXComLoginDialog;
    }

    private void nxLogin(String str, byte[] bArr) {
        if (this.eventHandler == null) {
            return;
        }
        showProgressDialog();
        this.eventHandler.onLogin(getActivity(), str, bArr, this.session, new AnonymousClass8(bArr, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLogin(String str, byte[] bArr) {
        this.nexonLoginView.setErrorMessage("");
        String strTrim = str.trim();
        if (this.mode == 1) {
            getNXUserNPSN(strTrim, bArr);
        } else {
            nxLogin(strTrim, bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processGoNXAuth(NXToyResult nXToyResult) {
        if (this.eventHandler == null) {
            return;
        }
        NXToyLoginResult nXToyLoginResult = (NXToyLoginResult) nXToyResult;
        NXToySession nXToySession = new NXToySession();
        nXToySession.setNpsn(nXToyLoginResult.result.npSN);
        nXToySession.setNPToken(nXToyLoginResult.result.npToken);
        nXToySession.setUMKey(nXToyLoginResult.result.umKey);
        nXToySession.setType(NXToyLoginType.LoginTypeNXCom.getValue());
        nXToySession.setServiceId(this.session.getServiceId());
        showProgressDialog();
        this.eventHandler.onPrepareNXAuth(getActivity(), nXToySession, new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.9
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(final int i, String str, final Bundle bundle) {
                NPNXComLoginDialog.this.runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        NPNXComLoginDialog.this.dismissProgressDialog();
                        NXToyNXKEncryptResult nXToyNXKEncryptResult = (NXToyNXKEncryptResult) NXJsonUtil.fromObject(bundle.getString("toyresult"), NXToyNXKEncryptResult.class);
                        if (i == 0) {
                            NPNXComLoginDialog.this.eventHandler.onNeedShowNXAuth(NPNXComLoginDialog.this.getActivity(), NPNXComLoginDialog.this.session, nXToyNXKEncryptResult.result.encrypt, new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.9.1.1
                                @Override // kr.co.nexon.android.sns.NPAuthListener
                                public void onResult(int i2, String str2, Bundle bundle2) {
                                }
                            });
                        } else {
                            NPNXComLoginDialog.this.nexonLoginView.setErrorMessage(nXToyNXKEncryptResult.errorText);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchID() {
        if (this.eventHandler == null) {
            return;
        }
        this.eventHandler.onSearchID(getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchPassword() {
        if (this.eventHandler == null) {
            return;
        }
        this.eventHandler.onSearchPassword(getActivity());
    }

    private void setSession(String str) {
        if (NXStringUtil.isNotNull(str)) {
            this.session = (NXToySession) NXJsonUtil.fromObject(str, NXToySession.class);
        } else {
            this.session = new NXToySession();
        }
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.nexonLoginView = (NXPNexonLoginView) View.inflate(this.applicationContext, R.layout.nxp_nexon_login_view, null);
        setSession(getArguments().getString(KEY_EXTRA_DATA, ""));
        this.mode = getArguments().getInt("mode", 0);
        this.localeCode = NXToyCommonPreferenceController.getInstance().getLocale();
        this.nexonLoginView.setIdEditHint(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_id_hint));
        this.nexonLoginView.setIdTextChangeListener(new NXInputEditView.NPTextChangedListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.1
            @Override // kr.co.nexon.mdev.android.view.NXInputEditView.NPTextChangedListener
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() == 0) {
                    NPNXComLoginDialog.this.nexonLoginView.setLoginButtonEnabled(false);
                } else {
                    NPNXComLoginDialog.this.nexonLoginView.setLoginButtonEnabled(NPNXComLoginDialog.this.nexonLoginView.getPasswordText().isEmpty() ? false : true);
                }
            }
        });
        this.nexonLoginView.setPasswordEditHint(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_pw_hint));
        this.nexonLoginView.setPasswordTextChangeListener(new NXInputEditView.NPTextChangedListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.2
            @Override // kr.co.nexon.mdev.android.view.NXInputEditView.NPTextChangedListener
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                NPNXComLoginDialog.this.nexonLoginView.setErrorMessage("");
                if (charSequence.length() == 0) {
                    NPNXComLoginDialog.this.nexonLoginView.setLoginButtonEnabled(false);
                } else {
                    NPNXComLoginDialog.this.nexonLoginView.setLoginButtonEnabled(NPNXComLoginDialog.this.nexonLoginView.getIdText().isEmpty() ? false : true);
                }
            }
        });
        String string = getArguments().getString("key_nexon_id", "");
        if (NXStringUtil.isNotNull(string)) {
            this.nexonLoginView.setIdText(string);
        }
        if (this.mode == 1) {
            this.nexonLoginView.setSignUpVisibility(8);
        }
        this.nexonLoginView.setLoginButton(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_login), new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.3
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                Context context = view.getContext();
                if (!NXDeviceUtil.isAvailableNetwork(context)) {
                    Toast.makeText(context, NXLocalizedString.getText(context, NPNXComLoginDialog.this.localeCode, R.string.npres_check_network), 0).show();
                    return;
                }
                byte[] passwordTextToBytes = NPNXComLoginDialog.this.nexonLoginView.getPasswordTextToBytes();
                NPNXComLoginDialog.this.nexonLoginView.clearPasswordText();
                NPNXComLoginDialog.this.onLogin(NPNXComLoginDialog.this.nexonLoginView.getIdText(), passwordTextToBytes);
            }
        });
        this.nexonLoginView.setSignUpButton(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nxjoin_description), new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.4
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPNXComLoginDialog.this.signUpNXCom();
            }
        });
        this.nexonLoginView.setSearchIdButton(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_searchid_btn), new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.5
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPNXComLoginDialog.this.searchID();
            }
        });
        this.nexonLoginView.setSearchPwButton(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nplogin_searchpw_btn), new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.6
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPNXComLoginDialog.this.searchPassword();
            }
        });
        this.nexonLoginView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComLoginDialog.7
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPNXComLoginDialog.this.onBackPressed();
            }
        });
        this.nexonLoginView.setLoginButtonEnabled(false);
        return this.nexonLoginView;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase, android.app.DialogFragment
    public void dismiss() {
        if (this.nexonLoginView != null) {
            this.nexonLoginView.setIdText("");
            this.nexonLoginView.clearPasswordText();
            this.nexonLoginView = null;
        }
        super.dismiss();
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (isShowingProgressDialog()) {
            return;
        }
        if (this.resultListener != null) {
            Bundle bundle = new Bundle();
            bundle.putString("toyresult", NXJsonUtil.toJsonString(new NXToyResult(NPAuthPlugin.CODE_USER_CANCEL, "user canceled", "")));
            this.resultListener.onResult(NPAuthPlugin.CODE_USER_CANCEL, "user cancel", bundle);
        }
        super.onBackPressed();
    }

    public void setEventHandler(NPNexonComDialogEvent nPNexonComDialogEvent) {
        this.eventHandler = nPNexonComDialogEvent;
    }

    public void setGoogleSignIn(NPGoogleSignIn nPGoogleSignIn) {
        this.googleSignIn = nPGoogleSignIn;
    }

    public void setResultListener(NPAuthListener nPAuthListener) {
        this.resultListener = nPAuthListener;
    }

    public void signUpNXCom() {
        if (this.eventHandler == null) {
            return;
        }
        this.eventHandler.onSignUpNXCom(getActivity());
    }
}
