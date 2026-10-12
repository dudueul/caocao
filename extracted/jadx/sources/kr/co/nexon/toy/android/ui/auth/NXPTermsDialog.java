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
import java.util.HashMap;
import java.util.List;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.result.NXToyTermResult;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.npaccount.terms.NXPTermsManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.auth.view.NXPTermsItem;
import kr.co.nexon.toy.android.ui.auth.view.NXPTermsView;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPTermsDialog extends NPDialogBase implements NXPTermsView.NXPTermsAgreementStateChangeListener, NXPTermsView.NXPTermsAgreementButtonClickListener, NXPTermsView.NXPTermsViewContentsButtonClickListener {
    private static final String KEY_IS_NEED_REQUIRED_TERMS = "isNeedRequiredTerms";
    private static final String KEY_IS_PRE_AGREEMENT = "isPreAgreement";
    public static final String TAG = "NXPTermsDialog";
    private List<NXToyTerm> agreeTermsList;
    private List<NXToyTerm> displayedTermsList;
    private boolean isNeedRequiredTerms;
    private boolean isPreAgreement;
    private NXToyLocaleManager localeManager;
    private NPListener resultListener;
    private NXPTermsManager termsManager;
    private NXPTermsView termsView;

    public static NXPTermsDialog newInstance(Activity activity, boolean z, boolean z2) {
        NXPTermsDialog nXPTermsDialog = new NXPTermsDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putBoolean(KEY_IS_PRE_AGREEMENT, z);
        bundle.putBoolean(KEY_IS_NEED_REQUIRED_TERMS, z2);
        nXPTermsDialog.setArguments(bundle);
        return nXPTermsDialog;
    }

    private void onAgree() {
        showProgressDialog();
        if (!this.termsManager.isAgreeAllRequiredTerms(this.agreeTermsList)) {
            ToyLog.d("Not Agree to all the required terms");
            Toast.makeText(this.applicationContext, this.localeManager.getString(R.string.need_terms_agree), 1).show();
            return;
        }
        for (NXToyTerm nXToyTerm : this.agreeTermsList) {
            if (nXToyTerm.optional == 1 && nXToyTerm.isAgree == 0) {
                nXToyTerm.isAgree = 2;
            }
        }
        if (!this.isPreAgreement) {
            this.termsManager.agree(getActivity(), this.agreeTermsList, new NPListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPTermsDialog.3
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(final NXToyResult nXToyResult) {
                    if (nXToyResult.errorCode == 0) {
                        NXPTermsDialog.this.termsManager.showToastWithTerms(NXPTermsDialog.this.getActivity(), NXPTermsDialog.this.displayedTermsList);
                        NXPPolicyManager.getInstance().setAllEnabledPolicy(NXPTermsDialog.this.getActivity(), NXPTermsDialog.this.agreeTermsList, NXPTermsDialog.this.displayedTermsList, new NPListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPTermsDialog.3.1
                            @Override // kr.co.nexon.toy.listener.NPListener
                            public void onResult(NXToyResult nXToyResult2) {
                                if (NXPTermsDialog.this.resultListener != null) {
                                    NXPTermsDialog.this.resultListener.onResult(nXToyResult);
                                }
                                NXPTermsDialog.this.dismissProgressDialog();
                                NXPTermsDialog.this.dismiss();
                            }
                        });
                    } else {
                        if (NXPTermsDialog.this.resultListener != null) {
                            NXPTermsDialog.this.resultListener.onResult(nXToyResult);
                        }
                        NXPTermsDialog.this.dismissProgressDialog();
                        NXPTermsDialog.this.dismiss();
                    }
                }
            });
            return;
        }
        if (this.resultListener != null) {
            NXToyTermResult nXToyTermResult = new NXToyTermResult();
            nXToyTermResult.result.terms = this.agreeTermsList;
            nXToyTermResult.requestTag = NXToyRequestTag.SaveTermsOfAgree.getValue();
            this.resultListener.onResult(nXToyTermResult);
        }
        dismissProgressDialog();
        dismiss();
    }

    private void showCancelConfirmAlert() {
        NXPAlertDialog.Builder builder = new NXPAlertDialog.Builder(getActivity());
        builder.setMessage(this.localeManager.getString(R.string.npres_term_cancel_confirm_msg)).setPositiveButton(this.localeManager.getString(R.string.confirm), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPTermsDialog.4
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NXPTermsDialog.this.isNeedRequiredTerms = false;
                NXPTermsDialog.this.onBackPressed();
            }
        }).setNegativeButton(this.localeManager.getString(R.string.npres_cancel), null);
        builder.create().show();
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    protected void activityCreated() {
        if (this.agreeTermsList == null || this.agreeTermsList.isEmpty()) {
            ToyLog.d("Terms list is null or empty");
            getMainLooperHandler().postDelayed(new Runnable() { // from class: kr.co.nexon.toy.android.ui.auth.NXPTermsDialog.2
                @Override // java.lang.Runnable
                public void run() {
                    NXToyTermResult nXToyTermResult = new NXToyTermResult();
                    nXToyTermResult.result.terms = new ArrayList();
                    nXToyTermResult.requestTag = NXToyRequestTag.SaveTermsOfAgree.getValue();
                    if (NXPTermsDialog.this.resultListener != null) {
                        NXPTermsDialog.this.resultListener.onResult(nXToyTermResult);
                    }
                    NXPTermsDialog.this.dismiss();
                }
            }, 100L);
        }
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.isNeedRequiredTerms = getArguments().getBoolean(KEY_IS_NEED_REQUIRED_TERMS, false);
        this.isPreAgreement = getArguments().getBoolean(KEY_IS_PRE_AGREEMENT, false);
        this.localeManager = NXToyLocaleManager.getInstance(this.applicationContext);
        this.termsManager = NXPTermsManager.getInstance();
        this.displayedTermsList = new ArrayList();
        for (NXToyTerm nXToyTerm : this.agreeTermsList) {
            if (nXToyTerm.isAgree == 0) {
                this.displayedTermsList.add(nXToyTerm);
            }
        }
        this.termsView = (NXPTermsView) View.inflate(this.applicationContext, R.layout.nxp_terms_view, null);
        this.termsView.setTermsAgreementStateChangeListener(this);
        this.termsView.setTermsAgreementButtonClickListener(this);
        this.termsView.setTermsViewContentButtonClickListener(this);
        this.termsView.setTermsList(this.displayedTermsList, NXToyLocaleManager.getInstance().getCountry());
        this.termsView.setTitleText(this.localeManager.getString(R.string.npterms_terms_header));
        this.termsView.setDescriptionTextView(this.localeManager.getString(R.string.npres_terms_description_text));
        this.termsView.setLeftButtonText(this.localeManager.getString(R.string.npterms_start_btn));
        this.termsView.setLeftButtonEnabled(this.termsManager.isAgreeAllRequiredTerms(this.agreeTermsList));
        this.termsView.setRightButtonText(this.localeManager.getString(R.string.npterms_agree_all_and_start_btn));
        this.termsView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPTermsDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NXPTermsDialog.this.onBackPressed();
            }
        });
        return this.termsView;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (isShowingProgressDialog()) {
            return;
        }
        if (this.isNeedRequiredTerms) {
            showCancelConfirmAlert();
            return;
        }
        String string = this.localeManager.getString(R.string.npres_logincancel);
        NXToyResult nXToyResult = new NXToyResult(NXToyErrorCode.LOGIN_USER_CANCELED.getCode(), string, string);
        nXToyResult.requestTag = NXToyRequestTag.AgreeTerms.getValue();
        if (this.resultListener != null) {
            this.resultListener.onResult(nXToyResult);
        }
        super.onBackPressed();
    }

    @Override // kr.co.nexon.toy.android.ui.auth.view.NXPTermsView.NXPTermsAgreementStateChangeListener
    public void onCheckBoxClick(NXToyTerm nXToyTerm, NXPTermsItem nXPTermsItem) {
        boolean z;
        int i;
        for (NXToyTerm nXToyTerm2 : this.agreeTermsList) {
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
                break;
            }
        }
        if (this.termsManager.isAgreeAllRequiredTerms(this.agreeTermsList)) {
            this.termsView.setLeftButtonEnabled(true);
        } else {
            this.termsView.setLeftButtonEnabled(false);
        }
    }

    @Override // kr.co.nexon.toy.android.ui.auth.view.NXPTermsView.NXPTermsAgreementButtonClickListener
    public void onLeftButtonClick() {
        onAgree();
    }

    @Override // kr.co.nexon.toy.android.ui.auth.view.NXPTermsView.NXPTermsAgreementButtonClickListener
    public void onRightButtonClick() {
        this.termsView.setAllCheckBoxBackgroundResource(R.drawable.terms_agreement);
        HashMap<Integer, NXToyTerm> mapTermsListToTermsMap = this.termsManager.termsListToTermsMap(this.displayedTermsList);
        for (NXToyTerm nXToyTerm : this.agreeTermsList) {
            if (mapTermsListToTermsMap.get(Integer.valueOf(nXToyTerm.termID)) != null) {
                nXToyTerm.isAgree = 1;
            }
        }
        onAgree();
    }

    @Override // kr.co.nexon.toy.android.ui.auth.view.NXPTermsView.NXPTermsViewContentsButtonClickListener
    public void onShowTerms(String str, int i) {
        this.termsManager.showTermsDetail(getActivity(), i);
    }

    public void setAgreeTermsList(List<NXToyTerm> list) {
        this.agreeTermsList = list;
    }

    public void setResultListener(NPListener nPListener) {
        this.resultListener = nPListener;
    }
}
