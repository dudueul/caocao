package kr.co.nexon.npaccount.terms.interfaces;

import android.app.Activity;
import com.nexon.core.session.model.NXToyTerm;
import java.util.List;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXPTerms {
    void agree(Activity activity, List<NXToyTerm> list, NPListener nPListener);

    void agreeTermsWithoutUpdateToyToken(List<NXToyTerm> list, NPListener nPListener);

    void getTermsList(NPListener nPListener);

    void show(Activity activity, List<NXToyTerm> list, boolean z, NPListener nPListener);

    void showPreAgreement(Activity activity, NPListener nPListener);
}
