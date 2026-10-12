package kr.co.nexon.npaccount.terms;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.nexon.core.android.NXPApplicationConfigInterface;
import com.nexon.core.android.NXPApplicationConfigManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.core.util.NXDateUtil;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.result.NXToyTermResult;
import kr.co.nexon.npaccount.auth.result.NXToyTermsListResult;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.npaccount.terms.impl.NXPTermsV1;
import kr.co.nexon.npaccount.terms.impl.NXPTermsV2;
import kr.co.nexon.npaccount.terms.interfaces.NXPTerms;
import kr.co.nexon.toy.android.ui.auth.NXPAdInformationConsignmentDialog;
import kr.co.nexon.toy.android.ui.board.NPWebDialogFullScreen;
import kr.co.nexon.toy.android.ui.board.NPWebDialogTitleBar;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPTermsManager {
    public static final int TERMS_AGREE = 1;
    public static final int TERMS_DISAGREE = 2;
    public static final int TERMS_NONE = 0;
    public static final int TERMS_TYPE_OPTIONAL = 1;
    public static final int TERMS_TYPE_REQUIRED = 0;
    private static volatile NXPTermsManager instance;

    private NXPTermsManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<NXToyTerm> compareCurrentTermsListToLoginTermsList(List<NXToyTerm> list, List<NXToyTerm> list2) {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (list2 == null) {
            list2 = new ArrayList<>();
        }
        HashMap map = new HashMap();
        for (NXToyTerm nXToyTerm : list) {
            map.put(Integer.valueOf(nXToyTerm.termID), nXToyTerm);
        }
        for (NXToyTerm nXToyTerm2 : list2) {
            NXToyTerm nXToyTerm3 = (NXToyTerm) map.get(nXToyTerm2);
            if (nXToyTerm3 != null) {
                nXToyTerm2.title = nXToyTerm3.title;
            }
        }
        return list2;
    }

    private NXPTerms createNXPTerms() {
        return getTermsVersion() == 1 ? new NXPTermsV1() : new NXPTermsV2();
    }

    public static NXPTermsManager getInstance() {
        if (instance == null) {
            synchronized (NXPTermsManager.class) {
                if (instance == null) {
                    instance = new NXPTermsManager();
                }
            }
        }
        return instance;
    }

    private String getPageServerURL() {
        return NXPApplicationConfigManager.getInstance().getServerURL(NXPApplicationConfigInterface.ServerType.Page).getUrl();
    }

    private int getTermsVersion() {
        return NXToyCommonPreferenceController.getInstance().getTermsApiVer();
    }

    public void agree(Activity activity, List<NXToyTerm> list, NPListener nPListener) {
        createNXPTerms().agree(activity, list, nPListener);
    }

    public void agreeTermsWithoutUpdateToyToken(List<NXToyTerm> list, NPListener nPListener) {
        createNXPTerms().agreeTermsWithoutUpdateToyToken(list, nPListener);
    }

    public List<NXToyTerm> changeTermsAgreementStatus(List<NXToyTerm> list, List<NXToyTerm> list2) {
        HashMap<Integer, NXToyTerm> mapTermsListToTermsMap = termsListToTermsMap(list2);
        for (NXToyTerm nXToyTerm : list) {
            if (mapTermsListToTermsMap.containsKey(Integer.valueOf(nXToyTerm.termID))) {
                nXToyTerm.isAgree = 1;
            }
        }
        return list;
    }

    public void checkAdditionalTermsFromEnterToy(Activity activity, List<NXToyTerm> list, int i, final NPListener nPListener) {
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        if (nXToyCommonPreferenceController.getTermsApiVer() == 1) {
            List<NXToyTerm> additionalTermsList = getAdditionalTermsList(list);
            if (additionalTermsList.size() > 0 || i == 0) {
                nXToyCommonPreferenceController.setAdditionalTermsAgree(NXJsonUtil.toJsonString(additionalTermsList));
                goTermsAgree(activity, additionalTermsList, true, nPListener);
                return;
            } else {
                NXToyResult nXToyResult = new NXToyResult();
                nXToyResult.requestTag = NXToyRequestTag.EnterToy.getValue();
                nPListener.onResult(nXToyResult);
                return;
            }
        }
        if (!didCheckAllTerms(list)) {
            goTermsAgree(activity, list, true, nPListener);
            return;
        }
        NXToySessionManager nXToySessionManager = NXToySessionManager.getInstance();
        nXToySessionManager.getSession().setLoginTermsList(list);
        nXToySessionManager.saveSession();
        final NXToyResult nXToyResult2 = new NXToyResult();
        nXToyResult2.requestTag = NXToyRequestTag.EnterToy.getValue();
        NXPPolicyManager.getInstance().setAllEnabledPolicy(activity, list, new ArrayList(), new NPListener() { // from class: kr.co.nexon.npaccount.terms.NXPTermsManager.3
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult3) {
                nPListener.onResult(nXToyResult2);
            }
        });
    }

    public boolean didCheckAllTerms(List<NXToyTerm> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        for (NXToyTerm nXToyTerm : list) {
            if (nXToyTerm.optional == 0 && nXToyTerm.isAgree != 1) {
                return false;
            }
            if (nXToyTerm.optional == 1 && nXToyTerm.isAgree == 0) {
                return false;
            }
        }
        return true;
    }

    public List<NXToyTerm> getAdditionalTermsList(List<NXToyTerm> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() != 0) {
            for (NXToyTerm nXToyTerm : list) {
                if (nXToyTerm.isAgree == 0) {
                    arrayList.add(nXToyTerm);
                }
            }
        }
        return arrayList;
    }

    public List<NXToyTerm> getAdditionalTermsList(List<NXToyTerm> list, List<NXToyTerm> list2) {
        ArrayList arrayList = new ArrayList();
        HashMap<Integer, NXToyTerm> mapTermsListToTermsMap = termsListToTermsMap(list2);
        for (NXToyTerm nXToyTerm : list) {
            NXToyTerm nXToyTerm2 = mapTermsListToTermsMap.get(Integer.valueOf(nXToyTerm.termID));
            if (nXToyTerm2 == null || nXToyTerm2.isAgree == 2) {
                arrayList.add(nXToyTerm);
            }
        }
        return arrayList;
    }

    public List<NXToyTerm> getLoginTermsList() {
        NXToySession session = NXToySessionManager.getInstance().getSession();
        return session != null ? session.getLoginTermsList() : new ArrayList();
    }

    public void getTermsList(final List<NXToyTerm> list, final NPListener nPListener) {
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        if (!nXToyCommonPreferenceController.getIsTermsOfAgree()) {
            getTermsList(new NPListener() { // from class: kr.co.nexon.npaccount.terms.NXPTermsManager.2
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    if (nPListener == null) {
                        ToyLog.d("NPListener is null");
                        return;
                    }
                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                        ToyLog.d("Get terms list errorCode : " + nXToyResult.errorCode + " / errorText : " + nXToyResult.errorText);
                        nPListener.onResult(nXToyResult);
                    } else {
                        NXToyTermsListResult nXToyTermsListResult = (NXToyTermsListResult) nXToyResult;
                        nXToyTermsListResult.result.terms = NXPTermsManager.this.compareCurrentTermsListToLoginTermsList(nXToyTermsListResult.result.terms, list);
                        nPListener.onResult(nXToyTermsListResult);
                    }
                }
            });
            return;
        }
        NXToyTermResult.ResultSet resultSet = (NXToyTermResult.ResultSet) NXJsonUtil.fromObject(nXToyCommonPreferenceController.getSaveTerms(), NXToyTermResult.ResultSet.class);
        if (nPListener != null) {
            NXToyTermsListResult nXToyTermsListResult = new NXToyTermsListResult(0, "", "");
            nXToyTermsListResult.requestTag = NXToyRequestTag.GetTermsList.getValue();
            nXToyTermsListResult.result.terms = compareCurrentTermsListToLoginTermsList(list, resultSet.terms);
            nPListener.onResult(nXToyTermsListResult);
        }
    }

    public void getTermsList(NPListener nPListener) {
        createNXPTerms().getTermsList(nPListener);
    }

    public void goTermsAgree(Activity activity, List<NXToyTerm> list, boolean z, NPListener nPListener) {
        createNXPTerms().show(activity, list, z, nPListener);
    }

    public boolean isAgreeAllRequiredTerms(List<NXToyTerm> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        for (NXToyTerm nXToyTerm : list) {
            if (nXToyTerm.optional == 0 && nXToyTerm.isAgree != 1) {
                return false;
            }
        }
        return true;
    }

    public void showPersonalInformationConsignment(final Activity activity, final String str, final int i, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.terms.NXPTermsManager.5
            @Override // java.lang.Runnable
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                View viewInflate = activity.getLayoutInflater().inflate(R.layout.nxp_personal_info_consignment_alert_view, (ViewGroup) null);
                TextView textView = (TextView) viewInflate.findViewById(R.id.personal_info_consignment_title);
                Button button = (Button) viewInflate.findViewById(R.id.personal_info_consignment_agree);
                Button button2 = (Button) viewInflate.findViewById(R.id.personal_info_consignment_disagree);
                TextView textView2 = (TextView) viewInflate.findViewById(R.id.personal_info_consignment_contents);
                textView.setText(str);
                SpannableString spannableString = new SpannableString(activity.getResources().getString(R.string.npres_privacy_information_consigment_contents_view_btn));
                spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
                textView2.setText(spannableString);
                builder.setView(viewInflate).create();
                final AlertDialog alertDialogShow = builder.show();
                alertDialogShow.setCanceledOnTouchOutside(false);
                button.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.npaccount.terms.NXPTermsManager.5.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        nPListener.onResult(new NXToyResult(NXToyErrorCode.SUCCESS.getCode(), "agree", ""));
                        alertDialogShow.dismiss();
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.npaccount.terms.NXPTermsManager.5.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        nPListener.onResult(new NXToyResult(NXToyErrorCode.USER_CANCEL.getCode(), "disagree", ""));
                        alertDialogShow.dismiss();
                    }
                });
                alertDialogShow.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.npaccount.terms.NXPTermsManager.5.3
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        nPListener.onResult(new NXToyResult(NXToyErrorCode.USER_CANCEL.getCode(), "disagree", ""));
                    }
                });
                textView2.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.npaccount.terms.NXPTermsManager.5.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NXPTermsManager.getInstance().showTermsDetail(activity, i);
                    }
                });
            }
        });
    }

    public void showTermsAgreementFromPolicyDialog(final Activity activity, final List<NXToyTerm> list, final List<NXToyTerm> list2, final NPListener nPListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.terms.NXPTermsManager.4
            @Override // java.lang.Runnable
            public void run() {
                NXPAdInformationConsignmentDialog nXPAdInformationConsignmentDialogNewInstance = NXPAdInformationConsignmentDialog.newInstance(activity);
                nXPAdInformationConsignmentDialogNewInstance.setTermsList(list2, list);
                nXPAdInformationConsignmentDialogNewInstance.setResultListener(nPListener);
                nXPAdInformationConsignmentDialogNewInstance.showDialog(activity, NXPAdInformationConsignmentDialog.TAG);
            }
        });
    }

    public void showTermsDetail(Activity activity, int i) {
        NPWebDialogFullScreen.newInstance(activity, getPageServerURL() + "/term/" + i).showDialog(activity, NPWebDialogFullScreen.TAG);
    }

    public void showTermsDetail(Activity activity, String str, int i) {
        NPWebDialogTitleBar.newInstance(activity, str, getPageServerURL() + "/term/text/" + i).showDialog(activity, NPWebDialogTitleBar.TAG);
    }

    public void showTermsOfAgree(Activity activity, final NPListener nPListener) {
        createNXPTerms().showPreAgreement(activity, new NPListener() { // from class: kr.co.nexon.npaccount.terms.NXPTermsManager.1
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                nXToyResult.requestTag = NXToyRequestTag.SaveTermsOfAgree.getValue();
                NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NXToyTermResult nXToyTermResult = (NXToyTermResult) nXToyResult;
                    ToyLog.d("showTermsOfAgree : " + new Gson().toJson(nXToyTermResult.result.terms));
                    nXToyCommonPreferenceController.setIsTermsOfAgree(true);
                    nXToyCommonPreferenceController.setSaveTerms(new Gson().toJson(nXToyTermResult.result));
                } else {
                    nXToyCommonPreferenceController.setIsTermsOfAgree(false);
                    nXToyResult.errorCode = NXToyErrorCode.SAVE_TERMS_OF_AGREE_FAIL.getCode();
                    nXToyResult.errorText = "";
                    nXToyResult.errorDetail = "";
                }
                if (nPListener != null) {
                    nPListener.onResult(nXToyResult);
                }
            }
        });
    }

    public void showToastWithTerms(Activity activity, List<NXToyTerm> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        String stringEx = null;
        String stringEx2 = null;
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(activity);
        for (NXToyTerm nXToyTerm : list) {
            if (nXToyTerm.type != null && !nXToyTerm.type.isEmpty()) {
                for (String str : nXToyTerm.type) {
                    if (str.equals("adToast")) {
                        if (stringEx == null && nXToyTerm.isAgree == 1) {
                            stringEx = nXToyLocaleManager.getStringEx(R.string.npres_ad_information_toast_on, "");
                        }
                    } else if (str.equals("nightToast") && stringEx2 == null && nXToyTerm.isAgree == 1) {
                        stringEx2 = nXToyLocaleManager.getStringEx(R.string.npres_night_push_receive_on, "");
                    }
                }
                if (stringEx != null && stringEx2 != null) {
                    break;
                }
            }
        }
        String currentTimeFormat = NXDateUtil.getCurrentTimeFormat("yyyy-MM-dd");
        if (NXStringUtil.isNotNull(stringEx)) {
            Toast.makeText(activity, stringEx.replace(NXDateUtil.DATE_RESOURCES_FORMAT, currentTimeFormat), 0).show();
        }
        if (NXStringUtil.isNotNull(stringEx2)) {
            Toast.makeText(activity, stringEx2.replace(NXDateUtil.DATE_RESOURCES_FORMAT, currentTimeFormat), 0).show();
        }
    }

    public HashMap<Integer, NXToyTerm> termsListToTermsMap(List<NXToyTerm> list) {
        HashMap<Integer, NXToyTerm> map = new HashMap<>();
        if (list != null && !list.isEmpty()) {
            for (NXToyTerm nXToyTerm : list) {
                map.put(Integer.valueOf(nXToyTerm.termID), nXToyTerm);
            }
        }
        return map;
    }
}
