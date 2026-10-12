package kr.co.nexon.npaccount.terms.impl;

import android.app.Activity;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToyTerm;
import java.util.ArrayList;
import java.util.List;
import kr.co.nexon.npaccount.auth.request.NXPAgreeTermsWithoutUpdateToyTokenRequest;
import kr.co.nexon.npaccount.auth.request.NXToyAgreeTermsRequest;
import kr.co.nexon.npaccount.auth.request.NXToyGetTermsListRequest;
import kr.co.nexon.npaccount.auth.result.NXPAgreeTermsWithoutUpdateToyTokenResult;
import kr.co.nexon.npaccount.auth.result.NXToyAgreeTermResult;
import kr.co.nexon.npaccount.auth.result.NXToyTermResult;
import kr.co.nexon.npaccount.auth.result.NXToyTermsListResult;
import kr.co.nexon.npaccount.terms.interfaces.NXPTerms;
import kr.co.nexon.toy.android.ui.auth.NXPTermsDialog;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPTermsV2 implements NXPTerms {
    @Override // kr.co.nexon.npaccount.terms.interfaces.NXPTerms
    public void agree(Activity activity, final List<NXToyTerm> list, final NPListener nPListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyAgreeTermsRequest(list), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.terms.impl.NXPTermsV2.3
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NXToySessionManager nXToySessionManager = NXToySessionManager.getInstance();
                    nXToySessionManager.getSession().setLoginTermsList(list);
                    nXToySessionManager.getSession().setNPToken(((NXToyAgreeTermResult) nXToyResult).result.npToken);
                    nXToySessionManager.saveSession();
                    NXToyCommonPreferenceController.getInstance().setHasPhoneNumberFlag(NXToySessionManager.getInstance().getSession().getNpsn(), true);
                }
                if (nPListener != null) {
                    nPListener.onResult(nXToyResult);
                }
            }
        });
    }

    @Override // kr.co.nexon.npaccount.terms.interfaces.NXPTerms
    public void agreeTermsWithoutUpdateToyToken(final List<NXToyTerm> list, final NPListener nPListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXPAgreeTermsWithoutUpdateToyTokenRequest(list), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.terms.impl.NXPTermsV2.4
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NXToySessionManager nXToySessionManager = NXToySessionManager.getInstance();
                    nXToySessionManager.getSession().setLoginTermsList(list);
                    nXToySessionManager.getSession().setNPToken(((NXPAgreeTermsWithoutUpdateToyTokenResult) nXToyResult).result.npToken);
                    nXToySessionManager.saveSession();
                }
                if (nPListener != null) {
                    nPListener.onResult(nXToyResult);
                }
            }
        });
    }

    @Override // kr.co.nexon.npaccount.terms.interfaces.NXPTerms
    public void getTermsList(final NPListener nPListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetTermsListRequest("app"), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.terms.impl.NXPTermsV2.1
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
        NXPTermsDialog nXPTermsDialogNewInstance = NXPTermsDialog.newInstance(activity, false, z);
        nXPTermsDialogNewInstance.setAgreeTermsList(list);
        nXPTermsDialogNewInstance.setResultListener(nPListener);
        nXPTermsDialogNewInstance.showDialog(activity, NXPTermsDialog.TAG);
    }

    @Override // kr.co.nexon.npaccount.terms.interfaces.NXPTerms
    public void showPreAgreement(final Activity activity, final NPListener nPListener) {
        getTermsList(new NPListener() { // from class: kr.co.nexon.npaccount.terms.impl.NXPTermsV2.2
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode() && nPListener != null) {
                    nPListener.onResult(nXToyResult);
                    return;
                }
                final NXToyTermsListResult nXToyTermsListResult = (NXToyTermsListResult) nXToyResult;
                if (nXToyTermsListResult.result.terms != null && !nXToyTermsListResult.result.terms.isEmpty()) {
                    activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.terms.impl.NXPTermsV2.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            NXPTermsDialog nXPTermsDialogNewInstance = NXPTermsDialog.newInstance(activity, true, false);
                            nXPTermsDialogNewInstance.setAgreeTermsList(nXToyTermsListResult.result.terms);
                            nXPTermsDialogNewInstance.setResultListener(nPListener);
                            nXPTermsDialogNewInstance.showDialog(activity, NXPTermsDialog.TAG);
                        }
                    });
                } else if (nPListener != null) {
                    NXToyTermResult nXToyTermResult = new NXToyTermResult();
                    nXToyTermResult.result.terms = new ArrayList();
                    nXToyTermsListResult.requestTag = nXToyResult.requestTag;
                    nPListener.onResult(nXToyTermResult);
                }
            }
        });
    }
}
