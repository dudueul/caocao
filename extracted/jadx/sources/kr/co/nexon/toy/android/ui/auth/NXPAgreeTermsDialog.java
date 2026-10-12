package kr.co.nexon.toy.android.ui.auth;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.nexon.core.locale.NXLocale;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.core.util.NXDateUtil;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kr.co.nexon.mdev.android.view.NXToast;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.request.NXToyGetTermsListRequest;
import kr.co.nexon.npaccount.auth.result.NXToyAgreeTermResult;
import kr.co.nexon.npaccount.auth.result.NXToyPolicyResult;
import kr.co.nexon.npaccount.auth.result.NXToyTermResult;
import kr.co.nexon.npaccount.auth.result.NXToyTermsListResult;
import kr.co.nexon.npaccount.auth.result.model.NXToyPolicy;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.npaccount.push.NXPPush;
import kr.co.nexon.npaccount.terms.NXPTermsManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.auth.view.NXPTermsItem;
import kr.co.nexon.toy.android.ui.auth.view.NXPTermsView;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAgreeTermsDialog extends NPDialogBase implements NXToyRequestListener, NXPTermsView.NXPTermsAgreementStateChangeListener, NXPTermsView.NXPTermsAgreementButtonClickListener, NXPTermsView.NXPTermsViewContentsButtonClickListener {
    private static final int CODE_POLICY_KOREA_COLLECTION_PHONE_NUMBER = 2505;
    public static final String DATE_FORMAT_TYPE_A = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_TYPE_B = "yyyy-MM-dd";
    private static final int FAKE_TERM_ID = -1;
    private static final String KEY_ADDITIONAL_TERMS_LIST = "additionalTermsList";
    public static final String TAG = "NXPAgreeTermsDialog";
    public static final int UTC_KOREA = 9;
    private List<NXToyTerm> agreeTermsList;
    private List<NXToyTerm> displayedTermsList;
    private NXToyLocaleManager localeManager;
    private NXPPolicyManager policyManager;
    private NPListener resultListener;
    private NXPTermsManager termsManager;
    private NXPTermsView termsView;
    private boolean cancelConfirmFlag = false;
    private boolean viewToastFlag = false;
    private boolean saveTermsListFlag = false;

    private boolean isAgreeAllTerms() {
        if (this.agreeTermsList.isEmpty()) {
            return true;
        }
        for (NXToyTerm nXToyTerm : this.agreeTermsList) {
            if (nXToyTerm.isOptional == -1 && nXToyTerm.isAgree == 0) {
                return false;
            }
        }
        return true;
    }

    public static NXPAgreeTermsDialog newInstance(Activity activity, List<Integer> list) {
        NXPAgreeTermsDialog nXPAgreeTermsDialog = new NXPAgreeTermsDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putIntegerArrayList(KEY_ADDITIONAL_TERMS_LIST, (ArrayList) list);
        nXPAgreeTermsDialog.setArguments(bundle);
        return nXPAgreeTermsDialog;
    }

    private void onAgree() {
        ArrayList arrayList = new ArrayList();
        for (NXToyTerm nXToyTerm : this.agreeTermsList) {
            if (nXToyTerm.isOptional == 0) {
                if (nXToyTerm.isAgree == 1) {
                    nXToyTerm.isOptional = 1;
                } else {
                    nXToyTerm.isOptional = 2;
                    if (nXToyTerm.type != null && nXToyTerm.type.contains(NXToyTerm.TYPE_NIGHT_PUSH)) {
                        arrayList.add(nXToyTerm);
                    }
                }
            }
        }
        this.agreeTermsList.removeAll(arrayList);
        ToyLog.d("currentTermList : " + this.agreeTermsList);
        if (this.agreeTermsList == null) {
            return;
        }
        if (!isAgreeAllTerms()) {
            Toast.makeText(this.applicationContext, this.localeManager.getString(R.string.need_terms_agree), 1).show();
            return;
        }
        if (!this.saveTermsListFlag) {
            NPListener nPListener = new NPListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPAgreeTermsDialog.5
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(final NXToyResult nXToyResult) {
                    NXPAgreeTermsDialog.this.runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.auth.NXPAgreeTermsDialog.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            NXPAgreeTermsDialog.this.dismissProgressDialog();
                            if (nXToyResult.errorCode != 0) {
                                ToyLog.d("NXToyAgreeTermResult :" + nXToyResult.toString());
                                Toast.makeText(NXPAgreeTermsDialog.this.applicationContext, String.format(nXToyResult.errorText + "(%d)", Integer.valueOf(nXToyResult.errorCode)), 1).show();
                                return;
                            }
                            NXToyAgreeTermResult nXToyAgreeTermResult = (NXToyAgreeTermResult) nXToyResult;
                            if (NXPAgreeTermsDialog.this.viewToastFlag) {
                                NXPAgreeTermsDialog.this.showToastOfPushAgreement(NXDateUtil.formattedDate(NXDateUtil.changeHour(nXToyAgreeTermResult.result.agreeAt, NXPAgreeTermsDialog.DATE_FORMAT_TYPE_A, 9), NXPAgreeTermsDialog.DATE_FORMAT_TYPE_A, "yyyy-MM-dd"));
                            }
                            NXPAgreeTermsDialog.this.resultListener.onResult(nXToyAgreeTermResult);
                            NXPAgreeTermsDialog.this.dismiss();
                        }
                    });
                }
            };
            showProgressDialog();
            this.termsManager.agree(getActivity(), this.agreeTermsList, nPListener);
            return;
        }
        NXToyTermResult nXToyTermResult = new NXToyTermResult();
        nXToyTermResult.errorCode = 0;
        nXToyTermResult.errorText = "";
        nXToyTermResult.errorDetail = "";
        nXToyTermResult.result.terms = this.agreeTermsList;
        for (NXToyTerm nXToyTerm2 : this.agreeTermsList) {
            nXToyTerm2.contents = "";
            nXToyTerm2.title = "";
        }
        if (this.viewToastFlag) {
            showToastOfPushAgreement("");
        }
        this.resultListener.onResult(nXToyTermResult);
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onShowTermsView() {
        if (this.agreeTermsList.isEmpty()) {
            if (this.saveTermsListFlag) {
                NXToyTermResult nXToyTermResult = new NXToyTermResult();
                nXToyTermResult.requestTag = NXToyRequestTag.AgreeTerms.getValue();
                nXToyTermResult.result.terms = this.agreeTermsList;
                if (this.resultListener != null) {
                    this.resultListener.onResult(nXToyTermResult);
                }
            } else {
                this.termsManager.agree(getActivity(), this.agreeTermsList, new NPListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPAgreeTermsDialog.6
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult) {
                        if (NXPAgreeTermsDialog.this.resultListener != null) {
                            NXPAgreeTermsDialog.this.resultListener.onResult(new NXToyResult(0, "", "", NXToyRequestTag.AgreeTerms.getValue()));
                        }
                    }
                });
            }
            dismiss();
            return;
        }
        updateAgreementOfTermsState();
        for (NXToyTerm nXToyTerm : this.agreeTermsList) {
            if (nXToyTerm.isAgree == 0) {
                this.displayedTermsList.add(nXToyTerm);
                if (nXToyTerm.type.contains(NXToyTerm.TYPE_TOAST)) {
                    this.viewToastFlag = true;
                }
            }
        }
        if (this.displayedTermsList.isEmpty()) {
            this.termsManager.agree(getActivity(), this.agreeTermsList, new NPListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPAgreeTermsDialog.7
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    NXPAgreeTermsDialog.this.resultListener.onResult(new NXToyResult(0, "", "", NXToyRequestTag.AgreeTerms.getValue()));
                }
            });
            dismiss();
        } else {
            this.termsView.setVisibility(0);
            this.termsView.setTermsList(this.displayedTermsList, NXToyLocaleManager.getInstance().getCountry());
            this.termsView.setLeftButtonEnabled(this.termsManager.isAgreeAllRequiredTerms(this.agreeTermsList));
            this.termsView.setRightButtonEnabled(true);
        }
    }

    private void showCancelConfirmAlert() {
        NXPAlertDialog.Builder builder = new NXPAlertDialog.Builder(getActivity());
        builder.setMessage(this.localeManager.getString(R.string.npres_term_cancel_confirm_msg)).setPositiveButton(this.localeManager.getString(R.string.confirm), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPAgreeTermsDialog.2
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NXPAgreeTermsDialog.this.cancelConfirmFlag = false;
                NXPAgreeTermsDialog.this.onBackPressed();
            }
        }).setNegativeButton(this.localeManager.getString(R.string.npres_cancel), null);
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToastOfPushAgreement(String str) {
        NXToast.makeText(this.applicationContext, this.localeManager.getString(R.string.npres_ad_information_toast_on).replace(NXDateUtil.DATE_RESOURCES_FORMAT, str.equals("") ? NXDateUtil.getTodayDateString("yyyy-MM-dd") : str), 0, 0).show();
    }

    private void updateAgreementOfTermsState() {
        ArrayList<Integer> integerArrayList = getArguments().getIntegerArrayList(KEY_ADDITIONAL_TERMS_LIST);
        if (integerArrayList == null) {
            return;
        }
        for (NXToyTerm nXToyTerm : this.agreeTermsList) {
            boolean z = true;
            for (int i = 0; i < integerArrayList.size(); i++) {
                if (integerArrayList.get(i).intValue() == nXToyTerm.termID) {
                    z = false;
                    break;
                }
            }
            if (nXToyTerm.isOptional != 0 && z) {
                nXToyTerm.isAgree = 1;
            }
        }
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    protected void activityCreated() {
        showProgressDialog();
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetTermsListRequest("app"), this);
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        this.localeManager = NXToyLocaleManager.getInstance(this.applicationContext);
        this.termsManager = NXPTermsManager.getInstance();
        this.policyManager = NXPPolicyManager.getInstance();
        this.displayedTermsList = new ArrayList();
        this.termsView = (NXPTermsView) View.inflate(this.applicationContext, R.layout.nxp_terms_view, null);
        this.termsView.setVisibility(8);
        this.termsView.setTermsAgreementButtonClickListener(this);
        this.termsView.setTermsAgreementStateChangeListener(this);
        this.termsView.setTermsViewContentButtonClickListener(this);
        this.termsView.setTitleText(this.localeManager.getString(R.string.npterms_terms_header));
        this.termsView.setDescriptionTextView(this.localeManager.getString(R.string.npres_terms_description_text));
        this.termsView.setLeftButtonText(this.localeManager.getString(R.string.npterms_start_btn));
        this.termsView.setLeftButtonEnabled(false);
        this.termsView.setRightButtonText(this.localeManager.getString(R.string.npterms_agree_all_and_start_btn));
        this.termsView.setRightButtonEnabled(false);
        this.termsView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPAgreeTermsDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NXPAgreeTermsDialog.this.onBackPressed();
            }
        });
        return this.termsView;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (isShowingProgressDialog()) {
            return;
        }
        if (this.cancelConfirmFlag) {
            showCancelConfirmAlert();
            return;
        }
        if (this.resultListener != null) {
            String string = this.localeManager.getString(R.string.npres_logincancel);
            this.resultListener.onResult(new NXToyResult(NXToyErrorCode.LOGIN_USER_CANCELED.getCode(), string, string, NXToyRequestTag.AgreeTerms.getValue()));
        }
        super.onBackPressed();
    }

    @Override // kr.co.nexon.toy.android.ui.auth.view.NXPTermsView.NXPTermsAgreementStateChangeListener
    public void onCheckBoxClick(NXToyTerm nXToyTerm, NXPTermsItem nXPTermsItem) {
        boolean z;
        int i;
        for (NXToyTerm nXToyTerm2 : this.agreeTermsList) {
            if (nXToyTerm.termID == nXToyTerm2.termID && nXToyTerm.isOptional == nXToyTerm2.isOptional) {
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

    @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
    public void onComplete(NXToyResult nXToyResult) {
        if (nXToyResult.errorCode != 0) {
            dismissProgressDialog();
            if (this.resultListener != null) {
                this.resultListener.onResult(nXToyResult);
            }
            dismiss();
            return;
        }
        this.agreeTermsList = new ArrayList();
        this.agreeTermsList.addAll(((NXToyTermsListResult) nXToyResult).result.terms);
        if (this.localeManager.getCountry() != NXLocale.COUNTRY.Korea) {
            runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.auth.NXPAgreeTermsDialog.4
                @Override // java.lang.Runnable
                public void run() {
                    NXPAgreeTermsDialog.this.onShowTermsView();
                    NXPAgreeTermsDialog.this.dismissProgressDialog();
                }
            });
            return;
        }
        if (NXPPush.getPushServiceVersion() == 2) {
            NXToyTerm nXToyTerm = new NXToyTerm();
            nXToyTerm.isOptional = 0;
            nXToyTerm.termID = -1;
            nXToyTerm.title = this.localeManager.getString(R.string.npres_night_push_terms_message);
            nXToyTerm.type = new ArrayList();
            nXToyTerm.type.add(NXToyTerm.TYPE_NIGHT_PUSH);
            nXToyTerm.optional = 1;
            this.agreeTermsList.add(nXToyTerm);
        }
        this.policyManager.getPolicyListV1(this.applicationContext, "terms", new NPListener() { // from class: kr.co.nexon.toy.android.ui.auth.NXPAgreeTermsDialog.3
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult2) {
                List<NXToyPolicy> list;
                if (nXToyResult2.errorCode == 0 && (list = ((NXToyPolicyResult) nXToyResult2).result.policy) != null && list.size() != 0) {
                    for (NXToyPolicy nXToyPolicy : list) {
                        if (nXToyPolicy.terms != null && nXToyPolicy.code == 2505 && nXToyPolicy.isTermsAgree == 0) {
                            for (NXToyTerm nXToyTerm2 : nXToyPolicy.terms) {
                                nXToyTerm2.isOptional = 0;
                                nXToyTerm2.optional = 1;
                            }
                            NXPAgreeTermsDialog.this.agreeTermsList.addAll(nXToyPolicy.terms);
                        }
                    }
                }
                NXPAgreeTermsDialog.this.runOnUiThread(new Runnable() { // from class: kr.co.nexon.toy.android.ui.auth.NXPAgreeTermsDialog.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        NXPAgreeTermsDialog.this.onShowTermsView();
                        NXPAgreeTermsDialog.this.dismissProgressDialog();
                    }
                });
            }
        });
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

    public void setIsCancelConfirmFlag(boolean z) {
        this.cancelConfirmFlag = z;
    }

    public void setResultListener(NPListener nPListener) {
        this.resultListener = nPListener;
    }

    public void setSaveTermsListFlag(boolean z) {
        this.saveTermsListFlag = z;
    }
}
