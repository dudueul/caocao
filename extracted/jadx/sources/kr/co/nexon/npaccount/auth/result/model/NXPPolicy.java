package kr.co.nexon.npaccount.auth.result.model;

import com.nexon.core.session.model.NXToyTerm;
import com.nexon.core.util.NXJsonUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPolicy {
    private String agreeToast;
    private String disagreeToast;
    private int isAgreeToast;
    private int isDisagreeToast;
    private int status;
    private List<NXToyTerm> termsList;
    private String title;
    private int type;

    public String getAgreeToast() {
        return this.agreeToast;
    }

    public String getDisagreeToast() {
        return this.disagreeToast;
    }

    public boolean getIsAgreeToast() {
        return this.isAgreeToast == 1;
    }

    public boolean getIsDisagreeToast() {
        return this.isDisagreeToast == 1;
    }

    public int getStatus() {
        return this.status;
    }

    public List<NXToyTerm> getTermsList() {
        return this.termsList == null ? new ArrayList() : this.termsList;
    }

    public String getTitle() {
        return this.title;
    }

    public int getType() {
        return this.type;
    }

    public void setAgreeToast(String str) {
        this.agreeToast = str;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setTermsList(List<NXToyTerm> list) {
        this.termsList = list;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String toString() {
        return NXJsonUtil.toJsonString(this);
    }
}
