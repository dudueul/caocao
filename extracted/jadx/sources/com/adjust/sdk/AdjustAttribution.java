package com.adjust.sdk;

import com.nexon.nexonanalyticssdk.NxLogInfo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class AdjustAttribution implements Serializable {
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("trackerToken", String.class), new ObjectStreamField("trackerName", String.class), new ObjectStreamField("network", String.class), new ObjectStreamField("campaign", String.class), new ObjectStreamField("adgroup", String.class), new ObjectStreamField("creative", String.class), new ObjectStreamField("clickLabel", String.class), new ObjectStreamField(NxLogInfo.KEY_ADID, String.class)};
    private static final long serialVersionUID = 1;
    public String adgroup;
    public String adid;
    public String campaign;
    public String clickLabel;
    public String creative;
    public String network;
    public String trackerName;
    public String trackerToken;

    public static AdjustAttribution fromJson(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return null;
        }
        AdjustAttribution adjustAttribution = new AdjustAttribution();
        adjustAttribution.trackerToken = jSONObject.optString("tracker_token", null);
        adjustAttribution.trackerName = jSONObject.optString("tracker_name", null);
        adjustAttribution.network = jSONObject.optString("network", null);
        adjustAttribution.campaign = jSONObject.optString("campaign", null);
        adjustAttribution.adgroup = jSONObject.optString("adgroup", null);
        adjustAttribution.creative = jSONObject.optString("creative", null);
        adjustAttribution.clickLabel = jSONObject.optString("click_label", null);
        adjustAttribution.adid = str;
        return adjustAttribution;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            AdjustAttribution adjustAttribution = (AdjustAttribution) obj;
            return Util.equalString(this.trackerToken, adjustAttribution.trackerToken) && Util.equalString(this.trackerName, adjustAttribution.trackerName) && Util.equalString(this.network, adjustAttribution.network) && Util.equalString(this.campaign, adjustAttribution.campaign) && Util.equalString(this.adgroup, adjustAttribution.adgroup) && Util.equalString(this.creative, adjustAttribution.creative) && Util.equalString(this.clickLabel, adjustAttribution.clickLabel) && Util.equalString(this.adid, adjustAttribution.adid);
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((((((Util.hashString(this.trackerToken) + 629) * 37) + Util.hashString(this.trackerName)) * 37) + Util.hashString(this.network)) * 37) + Util.hashString(this.campaign)) * 37) + Util.hashString(this.adgroup)) * 37) + Util.hashString(this.creative)) * 37) + Util.hashString(this.clickLabel)) * 37) + Util.hashString(this.adid);
    }

    public String toString() {
        return Util.formatString("tt:%s tn:%s net:%s cam:%s adg:%s cre:%s cl:%s adid:%s", this.trackerToken, this.trackerName, this.network, this.campaign, this.adgroup, this.creative, this.clickLabel, this.adid);
    }
}
