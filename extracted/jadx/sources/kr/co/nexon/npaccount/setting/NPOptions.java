package kr.co.nexon.npaccount.setting;

import com.nexon.core.requestpostman.result.NXClassInfo;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPOptions {
    private List<Integer> defaultLoginTypeList;
    private int googleSignInInsteadOfGcidLogin;
    private int useNgsm;

    public enum GoogleSignInInsteadOfGcidLoginOption {
        ;

        public static final int Disabled = 0;
        public static final int Enabled = 1;
        public static final int NotInitialized = -1;
    }

    public class NPOptionsResult extends NXClassInfo {
        public List<Integer> defaultLoginTypeList;
        public int googleSignInInsteadOfGcidLogin;
        public int useNgsm;

        public NPOptionsResult() {
            this.googleSignInInsteadOfGcidLogin = -1;
            this.useNgsm = -1;
            this.defaultLoginTypeList = null;
        }

        public NPOptionsResult(NPOptions nPOptions) {
            if (nPOptions != null) {
                this.defaultLoginTypeList = nPOptions.defaultLoginTypeList;
                this.googleSignInInsteadOfGcidLogin = nPOptions.googleSignInInsteadOfGcidLogin;
                this.useNgsm = nPOptions.useNgsm;
            }
        }

        public NPOptions toNPOptions() {
            return new NPOptions(this.defaultLoginTypeList, this.googleSignInInsteadOfGcidLogin, this.useNgsm);
        }
    }

    public enum NgsmOption {
        ;

        public static final int Disabled = 0;
        public static final int Enabled = 1;
        public static final int NotInitialized = -1;
    }

    public NPOptions() {
        this(null);
    }

    public NPOptions(List<Integer> list) {
        this.defaultLoginTypeList = list;
        this.googleSignInInsteadOfGcidLogin = -1;
        this.useNgsm = -1;
    }

    public NPOptions(List<Integer> list, int i, int i2) {
        this.defaultLoginTypeList = list;
        this.googleSignInInsteadOfGcidLogin = i;
        this.useNgsm = i2;
    }

    public int getGoogleSignInInsteadOfGcidLogin() {
        return this.googleSignInInsteadOfGcidLogin;
    }

    public List<Integer> getLoginTypeList() {
        return this.defaultLoginTypeList;
    }

    int getNgsmEnabled() {
        return this.useNgsm;
    }

    public boolean isGoogleSignInInsteadOfGcidLogin() {
        return this.googleSignInInsteadOfGcidLogin == 1;
    }

    public boolean isNgsmEnabled() {
        return this.useNgsm == 1;
    }

    public void setGoogleSignInInsteadOfGcidLogin(boolean z) {
        if (z) {
            this.googleSignInInsteadOfGcidLogin = 1;
        } else {
            this.googleSignInInsteadOfGcidLogin = 0;
        }
    }

    public void setLoginTypeList(List<Integer> list) {
        this.defaultLoginTypeList = list;
    }

    public void setNgsmEnabled(boolean z) {
        if (z) {
            this.useNgsm = 1;
        } else {
            this.useNgsm = 0;
        }
    }

    public String toString() {
        return new NPOptionsResult(this).toString();
    }
}
