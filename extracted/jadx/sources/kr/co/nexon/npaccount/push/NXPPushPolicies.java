package kr.co.nexon.npaccount.push;

import com.nexon.core.util.NXJsonUtil;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPushPolicies {
    private static final String AD_PUSH_KEY = "1";
    private static final String NIGHT_PUSH_KEY = "2";
    private NXPPushPolicy adPolicy;
    private Map<String, NXPPushPolicy> gamePolicies = new HashMap();
    private NXPPushPolicy nightPolicy;

    public NXPPushPolicy getAdPolicy() {
        return this.adPolicy;
    }

    public NXPPushPolicy getGamePolicy(String str) {
        return this.gamePolicies.get(str);
    }

    public List<NXPPushPolicy> getGamePolicyList() {
        Collection<NXPPushPolicy> collectionValues = this.gamePolicies.values();
        return collectionValues.size() == 0 ? Arrays.asList(new NXPPushPolicy[0]) : Arrays.asList(collectionValues.toArray(new NXPPushPolicy[collectionValues.size()]));
    }

    public NXPPushPolicy getNightPolicy() {
        return this.nightPolicy;
    }

    public void putGamePolicy(NXPPushPolicy nXPPushPolicy) {
        if (nXPPushPolicy == null) {
            return;
        }
        this.gamePolicies.put(nXPPushPolicy.getKey(), nXPPushPolicy);
    }

    public void setAdPolicy(NXPPushPolicy nXPPushPolicy) {
        this.adPolicy = nXPPushPolicy;
    }

    public void setEnableAdPolicy(boolean z) {
        if (this.adPolicy != null) {
            this.adPolicy.setEnabled(z);
        } else {
            this.adPolicy = new NXPPushPolicy("1", z);
        }
    }

    public void setEnableNightPolicy(boolean z) {
        if (this.nightPolicy != null) {
            this.nightPolicy.setEnabled(z);
        } else {
            this.nightPolicy = new NXPPushPolicy("2", z);
        }
    }

    public void setGamePolicyEnable(String str, boolean z) {
        NXPPushPolicy nXPPushPolicy = this.gamePolicies.get(str);
        if (nXPPushPolicy != null) {
            nXPPushPolicy.setEnabled(z);
        } else {
            this.gamePolicies.put(str, new NXPPushPolicy(str, z));
        }
    }

    public void setNightPolicy(NXPPushPolicy nXPPushPolicy) {
        this.nightPolicy = nXPPushPolicy;
    }

    public String toString() {
        return NXJsonUtil.toJsonString(this);
    }
}
