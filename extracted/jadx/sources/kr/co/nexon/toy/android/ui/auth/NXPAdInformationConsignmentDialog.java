package kr.co.nexon.toy.android.ui.auth;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.terms.NXPTermsManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.auth.view.NXPTermsItem;
import kr.co.nexon.toy.android.ui.auth.view.NXPTermsView;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAdInformationConsignmentDialog extends NPDialogBase implements NXPTermsView.NXPTermsAgreementStateChangeListener, NXPTermsView.NXPTermsAgreementButtonClickListener, NXPTermsView.NXPTermsViewContentsButtonClickListener {
    public static final String TAG = "NXPAdInformationConsignmentDialog";
    private List<NXToyTerm> agreeTermsList;
    private List<NXToyTerm> displayedTermsList;
    private NXToyLocaleManager localeManager;
    private List<NXToyTerm> policyTermsList;
    private NPListener resultListener;
    private NXPTermsManager termsManager;

    public static NXPAdInformationConsignmentDialog newInstance(Activity activity) {
        NXPAdInformationConsignmentDialog nXPAdInformationConsignmentDialog = new NXPAdInformationConsignmentDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        nXPAdInformationConsignmentDialog.setArguments(bundle);
        return nXPAdInformationConsignmentDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.localeManager = NXToyLocaleManager.getInstance(this.applicationContext);
        this.termsManager = NXPTermsManager.getInstance();
        this.displayedTermsList = new ArrayList();
        Iterator<NXToyTerm> it = this.policyTermsList.iterator();
        while (it.hasNext()) {
            this.displayedTermsList.add(it.next());
        }
        NXPTermsView nXPTermsView = (NXPTermsView) View.inflate(this.applicationContext, R.layout.nxp_terms_view, null);
        nXPTermsView.setCloseButtonVisibility(4);
        nXPTermsView.setTermsAgreementStateChangeListener(this);
        nXPTermsView.setTermsAgreementButtonClickListener(this);
        nXPTermsView.setTermsViewContentButtonClickListener(this);
        nXPTermsView.setTitleText(this.localeManager.getString(R.string.npterms_terms_header));
        nXPTermsView.setDescriptionTextView(this.localeManager.getString(R.string.npres_terms_description_text));
        nXPTermsView.setRightButtonText(this.localeManager.getString(R.string.npterms_agree_btn));
        nXPTermsView.setLeftButtonText(this.localeManager.getString(R.string.npterms_disagree_btn));
        nXPTermsView.setLeftButtonEnabled(true);
        nXPTermsView.setTermsList(this.displayedTermsList, NXToyLocaleManager.getInstance().getCountry());
        return nXPTermsView;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (this.progressDialog == null || !this.progressDialog.isShowing()) {
            NXToyResult nXToyResult = new NXToyResult();
            nXToyResult.errorCode = NXToyErrorCode.LOGIN_USER_CANCELED.getCode();
            nXToyResult.errorText = this.localeManager.getString(R.string.npres_logincancel);
            nXToyResult.errorDetail = this.localeManager.getString(R.string.npres_logincancel);
            nXToyResult.requestTag = NXToyRequestTag.AgreeTerms.getValue();
            if (this.resultListener != null) {
                this.resultListener.onResult(nXToyResult);
            }
            super.onBackPressed();
        }
    }

    @Override // kr.co.nexon.toy.android.ui.auth.view.NXPTermsView.NXPTermsAgreementStateChangeListener
    public void onCheckBoxClick(NXToyTerm nXToyTerm, NXPTermsItem nXPTermsItem) {
        boolean z;
        int i;
        for (NXToyTerm nXToyTerm2 : this.policyTermsList) {
            if (nXToyTerm.termID == nXToyTerm2.termID) {
                if (nXToyTerm.isAgree == 1) {
                    z = false;
                    i = R.drawable.terms_disagreement;
                } else {
                    z = true;
                    i = R.drawable.terms_agreement;
                }
                nXToyTerm2.isAgree = z ? 1 : 2;
                nXPTermsItem.setCheckBoxBackgroundResource(i);
                return;
            }
        }
    }

    @Override // kr.co.nexon.toy.android.ui.auth.view.NXPTermsView.NXPTermsAgreementButtonClickListener
    public void onLeftButtonClick() {
        onBackPressed();
    }

    @Override // kr.co.nexon.toy.android.ui.auth.view.NXPTermsView.NXPTermsAgreementButtonClickListener
    public void onRightButtonClick() {
        Iterator<NXToyTerm> it = this.policyTermsList.iterator();
        while (it.hasNext()) {
            if (it.next().isAgree != 1) {
                ToyLog.d("Not Agree to all terms");
                Toast.makeText(this.applicationContext, this.localeManager.getString(R.string.need_terms_agree), 1).show();
                return;
            }
        }
        this.progressDialog.show();
        this.termsManager.agreeTermsWithoutUpdateToyToken(this.termsManager.changeTermsAgreementStatus(this.agreeTermsList, this.policyTermsList), new NPListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPAdInformationConsignmentDialog.1
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (NXPAdInformationConsignmentDialog.this.progressDialog != null && NXPAdInformationConsignmentDialog.this.progressDialog.isShowing()) {
                    NXPAdInformationConsignmentDialog.this.progressDialog.dismiss();
                }
                if (NXPAdInformationConsignmentDialog.this.resultListener != null) {
                    NXPAdInformationConsignmentDialog.this.resultListener.onResult(nXToyResult);
                }
                NXPAdInformationConsignmentDialog.this.dismiss();
            }
        });
    }

    @Override // kr.co.nexon.toy.android.ui.auth.view.NXPTermsView.NXPTermsViewContentsButtonClickListener
    public void onShowTerms(String str, int i) {
        this.termsManager.showTermsDetail(getActivity(), i);
    }

    public void setResultListener(NPListener nPListener) {
        this.resultListener = nPListener;
    }

    public void setTermsList(List<NXToyTerm> list, List<NXToyTerm> list2) {
        this.agreeTermsList = list;
        this.policyTermsList = list2;
    }
}
