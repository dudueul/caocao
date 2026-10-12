package kr.co.nexon.toy.android.ui.etc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.etc.view.NXPSettlementFundConfirmView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPSettlementFundConfirmDialog extends NPDialogBase {
    public static final String KEY_ITEM_NAME = "itemName";
    public static final String KEY_ITEM_PRICE = "itemPrice";
    public static final String TAG = "NPSettlementFundConfirmDialog";
    private String itemName;
    private String itemPrice;
    private List<NXToyTerm> policyTerms;
    private NPSettlementFundConfirmListener resultListener;

    public interface NPSettlementFundConfirmListener {
        void onCancel();

        void onClose();

        void onComplete(NXToyResult nXToyResult);
    }

    public static NPSettlementFundConfirmDialog newInstance(Activity activity, String str, String str2) {
        NPSettlementFundConfirmDialog nPSettlementFundConfirmDialog = new NPSettlementFundConfirmDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("itemName", str);
        bundle.putString("itemPrice", str2);
        nPSettlementFundConfirmDialog.setArguments(bundle);
        return nPSettlementFundConfirmDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSettlementFundItemConfirm() {
        NPFundsSettlementItemConfirmDialog nPFundsSettlementItemConfirmDialogNewInstance = NPFundsSettlementItemConfirmDialog.newInstance(getActivity(), this.itemName, this.itemPrice, true);
        nPFundsSettlementItemConfirmDialogNewInstance.setResultListener(new NPFundsSettlementItemConfirmDialog.NPSettlementFundItemConfirmListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPSettlementFundConfirmDialog.5
            @Override // kr.co.nexon.toy.android.ui.etc.NPFundsSettlementItemConfirmDialog.NPSettlementFundItemConfirmListener
            public void onCancel() {
                ToyLog.d("On click back button - NPFundsSettlementItemConfirmDialog");
            }

            @Override // kr.co.nexon.toy.android.ui.etc.NPFundsSettlementItemConfirmDialog.NPSettlementFundItemConfirmListener
            public void onClose(NXToyResult nXToyResult) {
                NPSettlementFundConfirmDialog.this.resultListener.onClose();
                NPSettlementFundConfirmDialog.this.dismiss();
            }

            @Override // kr.co.nexon.toy.android.ui.etc.NPFundsSettlementItemConfirmDialog.NPSettlementFundItemConfirmListener
            public void onConfirm(NXToyResult nXToyResult) {
                NPSettlementFundConfirmDialog.this.resultListener.onComplete(nXToyResult);
                NPSettlementFundConfirmDialog.this.dismiss();
            }
        });
        nPFundsSettlementItemConfirmDialogNewInstance.setPolicyTerms(this.policyTerms);
        nPFundsSettlementItemConfirmDialogNewInstance.showDialog(getActivity(), NPFundsSettlementItemConfirmDialog.TAG);
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        NXPSettlementFundConfirmView nXPSettlementFundConfirmView = (NXPSettlementFundConfirmView) View.inflate(this.applicationContext, R.layout.nxp_settlement_fund_confirm_view, null);
        this.itemName = getArguments().getString("itemName");
        this.itemPrice = getArguments().getString("itemPrice");
        String localeCode = NXLocale.LOCALE.JA_JP.getLocaleCode();
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(this.applicationContext);
        nXPSettlementFundConfirmView.setTitle(nXToyLocaleManager.getString(R.string.npsettlementfund_minor_agree_title, localeCode));
        nXPSettlementFundConfirmView.setDescriptionText(String.format(nXToyLocaleManager.getString(R.string.npsettlementfund_minor_agree_desc, localeCode), this.itemName, this.itemName));
        nXPSettlementFundConfirmView.setYesButton(nXToyLocaleManager.getString(R.string.yes, localeCode), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPSettlementFundConfirmDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPSettlementFundConfirmDialog.this.showSettlementFundItemConfirm();
            }
        });
        nXPSettlementFundConfirmView.setNoButton(nXToyLocaleManager.getString(R.string.no, localeCode), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPSettlementFundConfirmDialog.2
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NXToyResult nXToyResult = new NXToyResult(NXToyErrorCode.SETTLEMENT_FUND_DISAGREE.getCode(), "Not agree", "Not agree");
                nXToyResult.requestTag = NXToyRequestTag.GetPolicy.getValue();
                NPSettlementFundConfirmDialog.this.resultListener.onComplete(nXToyResult);
                NPSettlementFundConfirmDialog.this.dismiss();
            }
        });
        nXPSettlementFundConfirmView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPSettlementFundConfirmDialog.3
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPSettlementFundConfirmDialog.this.onBackPressed();
            }
        });
        nXPSettlementFundConfirmView.setOnBackButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPSettlementFundConfirmDialog.4
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPSettlementFundConfirmDialog.this.resultListener.onCancel();
                NPSettlementFundConfirmDialog.this.dismiss();
            }
        });
        return nXPSettlementFundConfirmView;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        super.onBackPressed();
        this.resultListener.onClose();
    }

    public void onCloseBtnClick() {
        onBackPressed();
    }

    public void setPolicyTerms(List<NXToyTerm> list) {
        if (list != null) {
            this.policyTerms = list;
        } else {
            this.policyTerms = new ArrayList();
        }
    }

    public void setResultListener(NPSettlementFundConfirmListener nPSettlementFundConfirmListener) {
        this.resultListener = nPSettlementFundConfirmListener;
    }
}
