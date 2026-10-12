package kr.co.nexon.npaccount.auth.result.model;

import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.session.model.NXToyTerm;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyPolicy extends NXClassInfo {
    public int code;
    public int isTermsAgree = -1;
    public List<NXToyTerm> terms;
}
