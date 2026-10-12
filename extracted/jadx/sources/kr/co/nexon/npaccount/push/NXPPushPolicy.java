package kr.co.nexon.npaccount.push;

import com.nexon.core.util.NXJsonUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPushPolicy {
    private boolean enable;
    private String key;
    private String name;

    NXPPushPolicy(String str, boolean z) {
        this.enable = z;
        this.key = str;
        this.name = "";
    }

    public NXPPushPolicy(String str, boolean z, String str2) {
        this.key = str;
        if (this.key == null) {
            this.key = "";
        }
        this.enable = z;
        this.name = str2;
        if (this.name == null) {
            this.name = "";
        }
    }

    public String getKey() {
        return this.key;
    }

    public String getName() {
        return this.name;
    }

    public boolean isEnabled() {
        return this.enable;
    }

    public void setEnabled(boolean z) {
        this.enable = z;
    }

    public String toString() {
        return NXJsonUtil.toJsonString(this);
    }
}
