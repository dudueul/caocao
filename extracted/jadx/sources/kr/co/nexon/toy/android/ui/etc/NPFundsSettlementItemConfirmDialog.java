package kr.co.nexon.toy.android.ui.etc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.nexon.core.locale.NXLocale;
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
import kr.co.nexon.npaccount.terms.NXPTermsManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.android.ui.etc.view.NXPSettlementFundItemConfirmView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPFundsSettlementItemConfirmDialog extends NPDialogBase {
    public static final String KEY_BACK_BUTTON_VISIBILITY = "backButtonVisibility";
    public static final String KEY_ITEM_NAME = "itemName";
    public static final String KEY_ITEM_PRICE = "itemPrice";
    public static final String TAG = "NPFundsSettlementItemConfirmDialog";
    private String itemName;
    private String itemPrice;
    private String localeCode;
    private NXToyLocaleManager localeManager;
    private List<NXToyTerm> policyTerms;
    private NPSettlementFundItemConfirmListener resultListener;

    public interface NPSettlementFundItemConfirmListener {
        void onCancel();

        void onClose(NXToyResult nXToyResult);

        void onConfirm(NXToyResult nXToyResult);
    }

    public static NPFundsSettlementItemConfirmDialog newInstance(Activity activity, String str, String str2, boolean z) {
        NPFundsSettlementItemConfirmDialog nPFundsSettlementItemConfirmDialog = new NPFundsSettlementItemConfirmDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("itemName", str);
        bundle.putString("itemPrice", str2);
        bundle.putBoolean(KEY_BACK_BUTTON_VISIBILITY, z);
        nPFundsSettlementItemConfirmDialog.setArguments(bundle);
        return nPFundsSettlementItemConfirmDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConfirm() {
        NXPAlertDialog.Builder builder = new NXPAlertDialog.Builder(getActivity());
        builder.setMessage(String.format(this.localeManager.getString(R.string.npsettlementfund_item_confirm_desc, this.localeCode), this.itemName, this.itemPrice)).setPositiveButton(this.localeManager.getString(R.string.yes, this.localeCode), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPFundsSettlementItemConfirmDialog.5
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NXToyResult nXToyResult = new NXToyResult(0, "", "");
                nXToyResult.requestTag = NXToyRequestTag.GetPolicy.getValue();
                NPFundsSettlementItemConfirmDialog.this.resultListener.onConfirm(nXToyResult);
                NPFundsSettlementItemConfirmDialog.this.dismiss();
            }
        }).setNegativeButton(this.localeManager.getString(R.string.no, this.localeCode), null);
        builder.show();
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        NXPSettlementFundItemConfirmView nXPSettlementFundItemConfirmView = (NXPSettlementFundItemConfirmView) View.inflate(this.applicationContext, R.layout.nxp_settlement_fund_item_confirm_view, null);
        this.itemName = getArguments().getString("itemName");
        this.itemPrice = getArguments().getString("itemPrice");
        this.localeCode = NXLocale.LOCALE.JA_JP.getLocaleCode();
        this.localeManager = NXToyLocaleManager.getInstance(this.applicationContext);
        nXPSettlementFundItemConfirmView.setTitle(this.localeManager.getString(R.string.npsettlementfund_item_confirm_title, this.localeCode));
        nXPSettlementFundItemConfirmView.setItemNameText(String.format(this.localeManager.getString(R.string.npsettlementfund_item_confirm_name, this.localeCode), this.itemName));
        nXPSettlementFundItemConfirmView.setItemPriceText(String.format(this.localeManager.getString(R.string.npsettlementfund_item_confirm_price, this.localeCode), this.itemPrice));
        if (this.policyTerms != null) {
            for (int i = 0; i < this.policyTerms.size(); i++) {
                final NXToyTerm nXToyTerm = this.policyTerms.get(i);
                if (nXToyTerm != null) {
                    nXPSettlementFundItemConfirmView.addTerm(nXToyTerm.title, new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPFundsSettlementItemConfirmDialog.1
                        @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                        protected void onSwallowClick(View view) {
                            NXPTermsManager.getInstance().showTermsDetail(NPFundsSettlementItemConfirmDialog.this.getActivity(), nXToyTerm.termID);
                        }
                    });
                }
            }
        }
        nXPSettlementFundItemConfirmView.setOnConfirmButton(this.localeManager.getString(R.string.npsettlementfund_item_purchase, this.localeCode), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPFundsSettlementItemConfirmDialog.2
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPFundsSettlementItemConfirmDialog.this.onConfirm();
            }
        });
        nXPSettlementFundItemConfirmView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPFundsSettlementItemConfirmDialog.3
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPFundsSettlementItemConfirmDialog.this.onBackPressed();
            }
        });
        if (getArguments().getBoolean(KEY_BACK_BUTTON_VISIBILITY, false)) {
            nXPSettlementFundItemConfirmView.setBackButtonVisibility(0);
            nXPSettlementFundItemConfirmView.setOnBackButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPFundsSettlementItemConfirmDialog.4
                @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                protected void onSwallowClick(View view) {
                    NPFundsSettlementItemConfirmDialog.this.resultListener.onCancel();
                    NPFundsSettlementItemConfirmDialog.this.dismiss();
                }
            });
        }
        return nXPSettlementFundItemConfirmView;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        NXToyResult nXToyResult = new NXToyResult(NXToyErrorCode.SETTLEMENT_FUND_USER_CANCELED.getCode(), "user cancel", "user cancel");
        nXToyResult.requestTag = NXToyRequestTag.GetPolicy.getValue();
        this.resultListener.onClose(nXToyResult);
        super.onBackPressed();
    }

    public void setPolicyTerms(List<NXToyTerm> list) {
        this.policyTerms = new ArrayList();
        if (list != null) {
            this.policyTerms = list;
        }
    }

    public void setResultListener(NPSettlementFundItemConfirmListener nPSettlementFundItemConfirmListener) {
        this.resultListener = nPSettlementFundItemConfirmListener;
    }
}
