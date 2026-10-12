package kr.co.nexon.android.sns.nxcom.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.gson.Gson;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.google.NPGoogleSignIn;
import kr.co.nexon.android.sns.nxcom.ui.view.NXPNexonSignUpView;
import kr.co.nexon.mdev.android.util.NXLocalizedString;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.auth.request.NXToyGetSvcInfoRequest;
import kr.co.nexon.npaccount.auth.result.NXToyServiceInfoResult;
import kr.co.nexon.toy.android.ui.NPDialogBase;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXComSignUpSelectDialog extends NPDialogBase {
    public static final String KEY_SESSION = "session";
    public static final String TAG = "NPNXComSignUpSelectDialog";
    private NPGoogleSignIn googleSignIn;
    private String localeCode;
    private NPAuthListener resultListener;
    private NXToySession session;

    /* JADX INFO: renamed from: kr.co.nexon.android.sns.nxcom.ui.NPNXComSignUpSelectDialog$6, reason: invalid class name */
    class AnonymousClass6 implements NPAuthListener {
        AnonymousClass6() {
        }

        @Override // kr.co.nexon.android.sns.NPAuthListener
        public void onResult(int i, String str, Bundle bundle) {
            ToyLog.d("getUserInfo errorCode = " + i);
            if (i == NXToyErrorCode.SUCCESS.getCode()) {
                NPNXComSignUpSelectDialog.this.nxsignup(bundle.getString(NPAuthPlugin.KEY_NAME), bundle.getString(NPAuthPlugin.KEY_EMAIL));
            } else {
                NPNXComSignUpSelectDialog.this.googleSignIn.login(NPNXComSignUpSelectDialog.this.getActivity(), new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComSignUpSelectDialog.6.1
                    @Override // kr.co.nexon.android.sns.NPAuthListener
                    public void onResult(int i2, String str2, Bundle bundle2) {
                        final NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
                        if (i2 == NXToyErrorCode.SUCCESS.getCode()) {
                            NPNXComSignUpSelectDialog.this.googleSignIn.getUserInfo(NPNXComSignUpSelectDialog.this.getActivity(), new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComSignUpSelectDialog.6.1.1
                                @Override // kr.co.nexon.android.sns.NPAuthListener
                                public void onResult(int i3, String str3, Bundle bundle3) {
                                    if (i3 == NXToyErrorCode.SUCCESS.getCode()) {
                                        NPNXComSignUpSelectDialog.this.nxsignup(bundle3.getString(NPAuthPlugin.KEY_NAME), bundle3.getString(NPAuthPlugin.KEY_EMAIL));
                                    } else {
                                        Toast.makeText(NPNXComSignUpSelectDialog.this.applicationContext, NXLocalizedString.getText(NPNXComSignUpSelectDialog.this.applicationContext, nXToyCommonPreferenceController.getLocale(), R.string.nxjoin_gplus_read_userinfo_fail), 0).show();
                                    }
                                    NPNXComSignUpSelectDialog.this.googleSignIn.logout(NPNXComSignUpSelectDialog.this.getActivity(), null);
                                }
                            });
                        } else {
                            Toast.makeText(NPNXComSignUpSelectDialog.this.applicationContext, NXLocalizedString.getText(NPNXComSignUpSelectDialog.this.applicationContext, nXToyCommonPreferenceController.getLocale(), R.string.nxjoin_gplus_read_userinfo_fail), 0).show();
                        }
                    }
                });
            }
        }
    }

    public static NPNXComSignUpSelectDialog newInstance(Activity activity, String str) {
        NPNXComSignUpSelectDialog nPNXComSignUpSelectDialog = new NPNXComSignUpSelectDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("session", str);
        nPNXComSignUpSelectDialog.setArguments(bundle);
        return nPNXComSignUpSelectDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nxComSignUpByEmail() {
        if (this.progressDialog != null) {
            this.progressDialog.show();
        }
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetSvcInfoRequest(), new NXToyRequestListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComSignUpSelectDialog.5
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(final NXToyResult nXToyResult) {
                if (NPNXComSignUpSelectDialog.this.progressDialog != null && NPNXComSignUpSelectDialog.this.progressDialog.isShowing()) {
                    NPNXComSignUpSelectDialog.this.progressDialog.dismiss();
                }
                NPNXComSignUpSelectDialog.this.getActivity().runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComSignUpSelectDialog.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        NXToyServiceInfoResult.ResultSet resultSet = ((NXToyServiceInfoResult) nXToyResult).result;
                        if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://member.nexon.com/mobile/join/mobilejoin.aspx?accesscode=218&pagetype=1&needverify=" + (resultSet.nxkATL == 2 ? "1" : "0")));
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("create_new_tab", true);
                            intent.putExtras(bundle);
                            NPNXComSignUpSelectDialog.this.startActivity(intent);
                            return;
                        }
                        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("https://member.nexon.com/mobile/join/mobilejoin.aspx?accesscode=218&pagetype=1&needverify=0"));
                        Bundle bundle2 = new Bundle();
                        bundle2.putBoolean("create_new_tab", true);
                        intent2.putExtras(bundle2);
                        NPNXComSignUpSelectDialog.this.startActivity(intent2);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nxsignup(String str, String str2) {
        Log.d("PLUSInfo", str + " " + str2);
        if (str == null || str2 == null) {
            Toast.makeText(this.applicationContext, NXLocalizedString.getText(this.applicationContext, NXToyCommonPreferenceController.getInstance().getLocale(), R.string.nxjoin_gplus_read_userinfo_fail), 0).show();
            return;
        }
        NPNXComSignUpWebDialog nPNXComSignUpWebDialogNewInstance = NPNXComSignUpWebDialog.newInstance(getActivity(), new Gson().toJson(this.session), str2, str);
        nPNXComSignUpWebDialogNewInstance.setResultListener(new NPAuthListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComSignUpSelectDialog.7
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str3, Bundle bundle) {
                if (i != NXToyErrorCode.SUCCESS.getCode()) {
                    NPNXComSignUpSelectDialog.this.googleSignIn.logout(NPNXComSignUpSelectDialog.this.getActivity(), null);
                }
            }
        });
        nPNXComSignUpWebDialogNewInstance.showDialog(getActivity(), NPNXComSignUpWebDialog.TAG);
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
        NXPNexonSignUpView nXPNexonSignUpView = (NXPNexonSignUpView) View.inflate(this.applicationContext, R.layout.nxp_nexon_sign_up_view, null);
        setSession(getArguments().getString("session"));
        this.localeCode = NXToyCommonPreferenceController.getInstance().getLocale();
        nXPNexonSignUpView.setDescriptionText(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nxjoin_gplus_description));
        nXPNexonSignUpView.setEmailSignUpButton(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nxjoin_email_btn), new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComSignUpSelectDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPNXComSignUpSelectDialog.this.nxComSignUpByEmail();
            }
        });
        nXPNexonSignUpView.setGoogleSignUpButton(NXLocalizedString.getText(this.applicationContext, this.localeCode, R.string.nxjoin_gplus_btn), new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComSignUpSelectDialog.2
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPNXComSignUpSelectDialog.this.nxComSignUpByGoogle();
            }
        });
        nXPNexonSignUpView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComSignUpSelectDialog.3
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPNXComSignUpSelectDialog.this.onBackPressed();
            }
        });
        nXPNexonSignUpView.setOnBackButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.NPNXComSignUpSelectDialog.4
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPNXComSignUpSelectDialog.this.onBackPressed();
            }
        });
        return nXPNexonSignUpView;
    }

    public void nxComSignUpByGoogle() {
        int iIsGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getActivity());
        if (iIsGooglePlayServicesAvailable == 0) {
            if (this.googleSignIn != null) {
                this.googleSignIn.getUserInfo(getActivity(), new AnonymousClass6());
            }
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(iIsGooglePlayServicesAvailable)) {
            GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), iIsGooglePlayServicesAvailable, 9001).show();
        } else {
            Log.i("GooglePlusLogin", "This device is not supported.");
        }
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void setGoogleSignIn(NPGoogleSignIn nPGoogleSignIn) {
        this.googleSignIn = nPGoogleSignIn;
    }

    public void setResultListener(NPAuthListener nPAuthListener) {
        this.resultListener = nPAuthListener;
    }
}
