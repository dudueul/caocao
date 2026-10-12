package kr.co.nexon.toy.android.ui.auth.accountmenu.implement;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import java.util.List;
import kr.co.nexon.npaccount.NPAccount;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.result.NXToyLoginResult;
import kr.co.nexon.toy.android.ui.auth.accountmenu.NXPAccountMenuDialog;
import kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState;
import kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuChangeView;
import kr.co.nexon.toy.android.ui.auth.util.NPLoginUIUtil;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.android.ui.dialog.NPLoadingDialog;
import kr.co.nexon.toy.listener.NPListener;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAccountMenuChangeState implements NXPAccountMenuState {
    private NPAccount account;
    private NXPAccountMenuDialog accountMenuDialog;
    private Activity activity;
    private NXPAccountMenuChangeView changeView;
    private NPListener listener;
    private NPLoadingDialog loadingDialog;
    private NXToyLocaleManager localeManager;
    private int loginType;
    List<Integer> membershipType = new ArrayList();
    private NPListener loginListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuChangeState.1
        @Override // kr.co.nexon.toy.listener.NPListener
        public void onResult(final NXToyResult nXToyResult) {
            NXPAccountMenuChangeState.this.activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuChangeState.1.1
                @Override // java.lang.Runnable
                public void run() {
                    if (nXToyResult.errorCode == NXToyErrorCode.WITHDRAWAL_PROCESSING_BY_THE_USER.getCode()) {
                        if (!(nXToyResult instanceof NXToyLoginResult)) {
                            NXPAccountMenuChangeState.this.listener.onResult(nXToyResult);
                            NXPAccountMenuChangeState.this.accountMenuDialog.dismiss();
                            return;
                        }
                        NXToyLoginResult nXToyLoginResult = (NXToyLoginResult) nXToyResult;
                        NXToySession session = NXToySessionManager.getInstance().getSession();
                        if (session.getTempUserLoginInfo() != null) {
                            session.getTempUserLoginInfo().put("withdrawExpiresIn", Integer.valueOf(nXToyLoginResult.result.withdrawExpiresIn));
                        }
                        NXPAccountMenuChangeState.this.account.recoverUser(NXPAccountMenuChangeState.this.activity, NXPAccountMenuChangeState.this.recoverUserListener);
                        return;
                    }
                    if (nXToyResult.errorCode == NXToyErrorCode.USING_NPSN_USER_NEED_RESOLVE.getCode()) {
                        NXPAccountMenuChangeState.this.resolveAlreadyLoginUser();
                        return;
                    }
                    if (nXToyResult.errorCode == NXToyErrorCode.NEED_CHANNELING_AGREE.getCode()) {
                        NXPAccountMenuChangeState.this.showChannelingGuideAlert(nXToyResult);
                        return;
                    }
                    if (nXToyResult.errorCode != 0) {
                        NXPAccountMenuChangeState.this.loadingDialog.dismiss();
                        Toast.makeText(NXPAccountMenuChangeState.this.activity, String.format(nXToyResult.errorText + "(%d)", Integer.valueOf(nXToyResult.errorCode)), 0).show();
                        return;
                    }
                    NXPAccountMenuChangeState.this.loadingDialog.dismiss();
                    if (!(nXToyResult instanceof NXToyLoginResult)) {
                        NXPAccountMenuChangeState.this.listener.onResult(nXToyResult);
                        NXPAccountMenuChangeState.this.accountMenuDialog.dismiss();
                        return;
                    }
                    NXToyLoginResult nXToyLoginResult2 = (NXToyLoginResult) nXToyResult;
                    if (NXPAccountMenuChangeState.this.loginType != NXToyLoginType.LoginTypeGuest.getValue() || (nXToyLoginResult2.result.loginResultType != 0 && nXToyLoginResult2.result.loginResultType != 1)) {
                        nXToyLoginResult2.result.loginResultType = 2;
                        NXPAccountMenuChangeState.this.loginSuccessAlertDialog(nXToyResult);
                    } else {
                        nXToyLoginResult2.result.loginResultType = 1;
                        NXPAccountMenuChangeState.this.listener.onResult(nXToyResult);
                        NXPAccountMenuChangeState.this.accountMenuDialog.dismiss();
                    }
                }
            });
        }
    };
    private NPListener recoverUserListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuChangeState.2
        @Override // kr.co.nexon.toy.listener.NPListener
        public void onResult(final NXToyResult nXToyResult) {
            NXPAccountMenuChangeState.this.loadingDialog.dismiss();
            NXPAccountMenuChangeState.this.activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuChangeState.2.1
                @Override // java.lang.Runnable
                public void run() {
                    if (nXToyResult.errorCode == 0) {
                        NXPAccountMenuChangeState.this.loginSuccessAlertDialog(nXToyResult);
                    } else {
                        Toast.makeText(NXPAccountMenuChangeState.this.activity, String.format(nXToyResult.errorText + "(%d)", Integer.valueOf(nXToyResult.errorCode)), 0).show();
                    }
                }
            });
        }
    };

    /* JADX INFO: renamed from: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuChangeState$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType = new int[NXToyLoginType.values().length];

        static {
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.LoginTypeFaceBook.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.LoginTypeGoogle.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.LoginTypeTwitter.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.LoginTypeGuest.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.LoginTypeNaver.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.LoginTypeNaverChannel.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.LoginTypeNXNet.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.LoginTypeGameCenter.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.LoginTypeVKontakte.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginSuccessAlertDialog(final NXToyResult nXToyResult) {
        NXPAlertDialog.Builder builder = new NXPAlertDialog.Builder(this.activity);
        String string = this.localeManager.getString(R.string.confirm);
        builder.setMessage(this.localeManager.getString(R.string.npres_account_menu_desc_login_success));
        builder.setPositiveButton(string, new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuChangeState.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NXPAccountMenuChangeState.this.listener.onResult(nXToyResult);
                NXPAccountMenuChangeState.this.accountMenuDialog.dismiss();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuChangeState.7
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                NXPAccountMenuChangeState.this.listener.onResult(nXToyResult);
                NXPAccountMenuChangeState.this.accountMenuDialog.dismiss();
            }
        });
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBackButtonPressed(NXPAccountMenuDialog nXPAccountMenuDialog) {
        nXPAccountMenuDialog.changeState(new NXPAccountMenuInitialState());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveAlreadyLoginUser() {
        if (this.account.getLoginType() == NPAccount.LoginTypeGuest) {
            this.account.resolveAlreadyLoginedUser(this.activity, false, this.loginListener);
        } else {
            this.account.resolveAlreadyLoginedUser(this.activity, true, this.loginListener);
        }
    }

    private void setAccountInfo(int i) {
        this.membershipType.clear();
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        List<Integer> defaultLoginTypeList = nXToyCommonPreferenceController != null ? nXToyCommonPreferenceController.getDefaultLoginTypeList() : null;
        if (defaultLoginTypeList == null) {
            defaultLoginTypeList = new ArrayList<>();
        }
        for (Integer num : defaultLoginTypeList) {
            if (num.intValue() != i && num.intValue() != NPAccount.LoginTypeGuest && (!this.account.getOptions().isGoogleSignInInsteadOfGcidLogin() || i != NPAccount.LoginTypeGoogle || num.intValue() != NPAccount.LoginTypeGameCenter)) {
                this.membershipType.add(num);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showChannelingGuideAlert(NXToyResult nXToyResult) {
        try {
            ToyLog.d("showChannelingGuideAlert :" + nXToyResult.toString());
            JSONObject jSONObject = new JSONObject(nXToyResult.errorDetail);
            String string = jSONObject.getString("channelingErrorMessage");
            final String string2 = jSONObject.getString("channelingUrl");
            if (NXStringUtil.isNotNull(string) && NXStringUtil.isNotNull(string2)) {
                ToyLog.d("channelingErrorMessage & channelingUrl not null");
                new NXPAlertDialog.Builder(this.activity).setMessage(string).setPositiveButton(this.localeManager.getString(R.string.npres_channeling_agree_btn), new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuChangeState.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NXPAccountMenuChangeState.this.activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(string2)));
                        NXPAccountMenuChangeState.this.accountMenuDialog.dismiss();
                    }
                }).setNegativeButton(this.localeManager.getString(R.string.npres_cancel), new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuChangeState.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                    }
                }).create().show();
            } else {
                ToyLog.d("channelingErrorMessage or channelingUrl is null");
                Toast.makeText(this.activity, nXToyResult.errorText, 0).show();
            }
        } catch (JSONException e) {
            ToyLog.d("exception e:" + e.toString());
            Toast.makeText(this.activity, nXToyResult.errorText, 0).show();
        }
        this.loadingDialog.dismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public View createView(final NXPAccountMenuDialog nXPAccountMenuDialog) {
        this.accountMenuDialog = nXPAccountMenuDialog;
        this.activity = this.accountMenuDialog.getActivity();
        this.listener = this.accountMenuDialog.getToyResultListener();
        this.account = NPAccount.getInstance(this.activity);
        this.loginType = this.account.getLoginType();
        this.loadingDialog = new NPLoadingDialog(this.activity);
        Context applicationContext = this.activity.getApplicationContext();
        this.localeManager = NXToyLocaleManager.getInstance(applicationContext);
        this.changeView = (NXPAccountMenuChangeView) ((LayoutInflater) applicationContext.getSystemService("layout_inflater")).inflate(R.layout.nxp_account_menu_change, (ViewGroup) null);
        int loginType = this.account.getLoginType();
        setAccountInfo(loginType);
        NPLoginUIUtil.sortDisplayPriority(loginType, this.membershipType);
        this.changeView.setLoginType(loginType);
        this.changeView.setMembershipList(this.membershipType);
        this.changeView.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.implement.NXPAccountMenuChangeState.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view.getId() == R.id.backBtn) {
                    NXPAccountMenuChangeState.this.onBackButtonPressed(nXPAccountMenuDialog);
                    return;
                }
                Object tag = view.getTag();
                if (tag == null) {
                    ToyLog.e("loginType Tag is null");
                    return;
                }
                Integer num = (Integer) tag;
                switch (AnonymousClass8.$SwitchMap$com$nexon$core$requestpostman$constants$NXToyLoginType[NXToyLoginType.convertIntLoginTypeToEnumLoginType(num.intValue()).ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        NXPAccountMenuChangeState.this.loadingDialog.show();
                        break;
                }
                if (NXPAccountMenuChangeState.this.activity != null) {
                    NXPAccountMenuChangeState.this.account.changeAccount(NXPAccountMenuChangeState.this.activity, num.intValue(), NXPAccountMenuChangeState.this.loginListener);
                }
            }
        });
        return this.changeView;
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public void onBackPressed(NXPAccountMenuDialog nXPAccountMenuDialog) {
        onBackButtonPressed(nXPAccountMenuDialog);
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuState
    public void setCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.changeView.setCloseButtonClickListener(onClickListener);
    }
}
