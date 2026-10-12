package kr.co.nexon.npaccount.terms.impl;

import android.app.Activity;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToyTerm;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kr.co.nexon.npaccount.auth.request.NXToyAgreeTermsRequest;
import kr.co.nexon.npaccount.auth.request.NXToyGetTermsListRequest;
import kr.co.nexon.npaccount.auth.result.NXToyAgreeTermResult;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.npaccount.terms.interfaces.NXPTerms;
import kr.co.nexon.toy.android.ui.auth.NXPAgreeTermsDialog;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPTermsV1 implements NXPTerms {
    @Override // kr.co.nexon.npaccount.terms.interfaces.NXPTerms
    public void agree(final Activity activity, List<NXToyTerm> list, final NPListener nPListener) {
        int i = -1;
        ArrayList arrayList = new ArrayList();
        for (NXToyTerm nXToyTerm : list) {
            if (nXToyTerm.isOptional != -1) {
                ToyLog.d("optional terms:" + nXToyTerm);
                arrayList.add(nXToyTerm);
                i = nXToyTerm.isOptional;
            }
        }
        if (arrayList.size() > 0) {
            ToyLog.d("before remove : " + list);
            list.removeAll(arrayList);
            ToyLog.d("after remove : " + list);
        }
        final int i2 = i;
        NXToyRequestPostman.getInstance().postRequest(new NXToyAgreeTermsRequest(list), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.terms.impl.NXPTermsV1.2
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(final NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    nPListener.onResult(nXToyResult);
                    return;
                }
                NXToySessionManager.getInstance().getSession().setNPToken(((NXToyAgreeTermResult) nXToyResult).result.npToken);
                NXPPolicyManager nXPPolicyManager = NXPPolicyManager.getInstance();
                if (i2 != -1) {
                    nXPPolicyManager.setPersonalInformationConsingenmentState(activity, i2, new NPListener() { // from class: kr.co.nexon.npaccount.terms.impl.NXPTermsV1.2.1
                        @Override // kr.co.nexon.toy.listener.NPListener
                        public void onResult(NXToyResult nXToyResult2) {
                            nPListener.onResult(nXToyResult);
                        }
                    });
                } else {
                    nPListener.onResult(nXToyResult);
                }
            }
        });
    }

    @Override // kr.co.nexon.npaccount.terms.interfaces.NXPTerms
    public void agreeTermsWithoutUpdateToyToken(List<NXToyTerm> list, NPListener nPListener) {
        ToyLog.d("Not using this API. AgreeTermsWithoutUpdateToyToken API is only used terms version 2");
    }

    @Override // kr.co.nexon.npaccount.terms.interfaces.NXPTerms
    public void getTermsList(final NPListener nPListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetTermsListRequest("app"), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.terms.impl.NXPTermsV1.1
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nPListener != null) {
                    nPListener.onResult(nXToyResult);
                }
            }
        });
    }

    @Override // kr.co.nexon.npaccount.terms.interfaces.NXPTerms
    public void show(Activity activity, List<NXToyTerm> list, boolean z, NPListener nPListener) {
        ArrayList arrayList = new ArrayList();
        Iterator<NXToyTerm> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(it.next().termID));
        }
        NXPAgreeTermsDialog nXPAgreeTermsDialogNewInstance = NXPAgreeTermsDialog.newInstance(activity, arrayList);
        nXPAgreeTermsDialogNewInstance.setResultListener(nPListener);
        nXPAgreeTermsDialogNewInstance.setIsCancelConfirmFlag(z);
        nXPAgreeTermsDialogNewInstance.showDialog(activity, NXPAgreeTermsDialog.TAG);
    }

    @Override // kr.co.nexon.npaccount.terms.interfaces.NXPTerms
    public void showPreAgreement(Activity activity, NPListener nPListener) {
        NXPAgreeTermsDialog nXPAgreeTermsDialogNewInstance = NXPAgreeTermsDialog.newInstance(activity, null);
        nXPAgreeTermsDialogNewInstance.setResultListener(nPListener);
        nXPAgreeTermsDialogNewInstance.setSaveTermsListFlag(true);
        nXPAgreeTermsDialogNewInstance.showDialog(activity, NXPAgreeTermsDialog.TAG);
    }
}
