package kr.co.nexon.toy.android.ui.etc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.nexon.core.locale.NXLocale;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import java.util.List;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.result.NXToyFundsSettlementPolicyResult;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.android.ui.etc.view.NXPDatePickerView;
import kr.co.nexon.toy.android.ui.etc.view.NXPSettlementFundView;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPFundsSettlementDialog extends NPDialogBase {
    public static final String KEY_ITEM_NAME = "itemName";
    public static final String KEY_ITEM_PRICE = "itemPrice";
    public static final String TAG = "NPFundsSettlementDialog";
    private String itemName;
    private String itemPrice;
    private String localeCode;
    private NXToyLocaleManager localeManager;
    private List<NXToyTerm> policyTerms;
    private NPListener resultListener;
    private int currentYear = -1;
    private int currentMonth = -1;

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(NXToyResult nXToyResult) {
        if (this.resultListener != null) {
            this.resultListener.onResult(nXToyResult);
        }
        dismiss();
    }

    public static NPFundsSettlementDialog newInsatnce(Activity activity, String str, String str2) {
        NPFundsSettlementDialog nPFundsSettlementDialog = new NPFundsSettlementDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("itemName", str);
        bundle.putString("itemPrice", str2);
        nPFundsSettlementDialog.setArguments(bundle);
        return nPFundsSettlementDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClose() {
        NXToyResult nXToyResult = new NXToyResult();
        nXToyResult.errorCode = NXToyErrorCode.SETTLEMENT_FUND_USER_CANCELED.getCode();
        nXToyResult.errorText = "user cancel";
        nXToyResult.errorDetail = "user cancel";
        nXToyResult.requestTag = NXToyRequestTag.GetPolicy.getValue();
        handleResult(nXToyResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConfirm() {
        if (this.currentYear < 1 || this.currentMonth < 1) {
            return;
        }
        String str = String.format(this.localeManager.getString(R.string.npsettlementfund_birthday_confirm_desc, this.localeCode), Integer.valueOf(this.currentYear), Integer.valueOf(this.currentMonth));
        NXPAlertDialog.Builder builder = new NXPAlertDialog.Builder(getActivity());
        builder.setMessage(str).setPositiveButton(this.localeManager.getString(R.string.yes, this.localeCode), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPFundsSettlementDialog.5
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPFundsSettlementDialog.this.validatePolicy();
            }
        }).setNegativeButton(this.localeManager.getString(R.string.no, this.localeCode), null);
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSettlementFundItemConfirm() {
        NPFundsSettlementItemConfirmDialog nPFundsSettlementItemConfirmDialogNewInstance = NPFundsSettlementItemConfirmDialog.newInstance(getActivity(), this.itemName, this.itemPrice, true);
        nPFundsSettlementItemConfirmDialogNewInstance.setResultListener(new NPFundsSettlementItemConfirmDialog.NPSettlementFundItemConfirmListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPFundsSettlementDialog.7
            @Override // kr.co.nexon.toy.android.ui.etc.NPFundsSettlementItemConfirmDialog.NPSettlementFundItemConfirmListener
            public void onCancel() {
            }

            @Override // kr.co.nexon.toy.android.ui.etc.NPFundsSettlementItemConfirmDialog.NPSettlementFundItemConfirmListener
            public void onClose(NXToyResult nXToyResult) {
                NPFundsSettlementDialog.this.handleResult(nXToyResult);
            }

            @Override // kr.co.nexon.toy.android.ui.etc.NPFundsSettlementItemConfirmDialog.NPSettlementFundItemConfirmListener
            public void onConfirm(NXToyResult nXToyResult) {
                NPFundsSettlementDialog.this.handleResult(nXToyResult);
            }
        });
        nPFundsSettlementItemConfirmDialogNewInstance.setPolicyTerms(this.policyTerms);
        nPFundsSettlementItemConfirmDialogNewInstance.showDialog(getActivity(), NPFundsSettlementItemConfirmDialog.TAG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void validatePolicy() {
        String str = String.format("%s%s", Integer.valueOf(this.currentYear), Integer.valueOf(this.currentMonth));
        showProgressDialog();
        NXPPolicyManager.getInstance().putFundsSettlementPolicy(getActivity(), str, new NPListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPFundsSettlementDialog.6
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(final NXToyResult nXToyResult) {
                NPFundsSettlementDialog.this.dismissProgressDialog();
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    NPFundsSettlementDialog.this.runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.etc.NPFundsSettlementDialog.6.2
                        @Override // java.lang.Runnable
                        public void run() {
                            Toast.makeText(NPFundsSettlementDialog.this.applicationContext, nXToyResult.errorText, 0).show();
                        }
                    });
                    return;
                }
                int i = ((NXToyFundsSettlementPolicyResult) nXToyResult).result.isSubscription;
                if (i != 0 && i != 2) {
                    if (i == 1) {
                        NPFundsSettlementDialog.this.showSettlementFundItemConfirm();
                    }
                } else {
                    NPSettlementFundConfirmDialog nPSettlementFundConfirmDialogNewInstance = NPSettlementFundConfirmDialog.newInstance(NPFundsSettlementDialog.this.getActivity(), NPFundsSettlementDialog.this.itemName, NPFundsSettlementDialog.this.itemPrice);
                    nPSettlementFundConfirmDialogNewInstance.setPolicyTerms(NPFundsSettlementDialog.this.policyTerms);
                    nPSettlementFundConfirmDialogNewInstance.setResultListener(new NPSettlementFundConfirmDialog.NPSettlementFundConfirmListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPFundsSettlementDialog.6.1
                        @Override // kr.co.nexon.toy.android.ui.etc.NPSettlementFundConfirmDialog.NPSettlementFundConfirmListener
                        public void onCancel() {
                            ToyLog.d("On click back button - NPSettlementFundConfirmDialog");
                        }

                        @Override // kr.co.nexon.toy.android.ui.etc.NPSettlementFundConfirmDialog.NPSettlementFundConfirmListener
                        public void onClose() {
                            NPFundsSettlementDialog.this.onClose();
                        }

                        @Override // kr.co.nexon.toy.android.ui.etc.NPSettlementFundConfirmDialog.NPSettlementFundConfirmListener
                        public void onComplete(NXToyResult nXToyResult2) {
                            NPFundsSettlementDialog.this.handleResult(nXToyResult2);
                        }
                    });
                    nPSettlementFundConfirmDialogNewInstance.showDialog(NPFundsSettlementDialog.this.getActivity(), NPSettlementFundConfirmDialog.TAG);
                }
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        final NXPSettlementFundView nXPSettlementFundView = (NXPSettlementFundView) View.inflate(this.applicationContext, R.layout.nxp_settlement_fund_view, null);
        this.itemName = getArguments().getString("itemName");
        this.itemPrice = getArguments().getString("itemPrice");
        this.localeCode = NXLocale.LOCALE.JA_JP.getLocaleCode();
        this.localeManager = NXToyLocaleManager.getInstance(this.applicationContext);
        nXPSettlementFundView.setTitle(this.localeManager.getString(R.string.npsettlementfund_title, this.localeCode));
        nXPSettlementFundView.setDescriptionText(String.format(this.localeManager.getString(R.string.npsettlementfund_desc, this.localeCode), this.itemName));
        final NXPDatePickerView.NXPDatePcikerListener nXPDatePcikerListener = new NXPDatePickerView.NXPDatePcikerListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPFundsSettlementDialog.1
            @Override // kr.co.nexon.toy.android.ui.etc.view.NXPDatePickerView.NXPDatePcikerListener
            public void onCancel() {
            }

            @Override // kr.co.nexon.toy.android.ui.etc.view.NXPDatePickerView.NXPDatePcikerListener
            public void onConfirm(int i, int i2) {
                NPFundsSettlementDialog.this.currentYear = i;
                NPFundsSettlementDialog.this.currentMonth = i2;
                nXPSettlementFundView.setBirthDay(String.format(NPFundsSettlementDialog.this.localeManager.getString(R.string.npsettlementfund_birthday, NPFundsSettlementDialog.this.localeCode), Integer.valueOf(NPFundsSettlementDialog.this.currentYear), Integer.valueOf(NPFundsSettlementDialog.this.currentMonth)));
                nXPSettlementFundView.setConfirmButtonEnabled(true);
            }
        };
        nXPSettlementFundView.setConfirmButton(this.localeManager.getString(R.string.confirm, this.localeCode), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPFundsSettlementDialog.2
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPFundsSettlementDialog.this.onConfirm();
            }
        });
        nXPSettlementFundView.setOnBirthDayEditTextClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPFundsSettlementDialog.3
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                Activity activity = NPFundsSettlementDialog.this.getActivity();
                if (activity == null) {
                    return;
                }
                NXPDatePickerDialog nXPDatePickerDialogNewInstance = NXPDatePickerDialog.newInstance(activity);
                nXPDatePickerDialogNewInstance.setDatePickerListener(nXPDatePcikerListener);
                nXPDatePickerDialogNewInstance.showDialog(activity, NXPDatePickerDialog.TAG);
            }
        });
        nXPSettlementFundView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPFundsSettlementDialog.4
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPFundsSettlementDialog.this.onClose();
            }
        });
        return nXPSettlementFundView;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        onClose();
    }

    public void setPolicyTerms(List<NXToyTerm> list) {
        if (list != null) {
            this.policyTerms = list;
        } else {
            this.policyTerms = new ArrayList();
        }
    }

    public void setResultListener(NPListener nPListener) {
        this.resultListener = nPListener;
    }
}
