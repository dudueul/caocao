package io.fabric.sdk.android.services.common;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class AdvertisingInfo {
    public final String advertisingId;
    public final boolean limitAdTrackingEnabled;

    AdvertisingInfo(String str, boolean z) {
        this.advertisingId = str;
        this.limitAdTrackingEnabled = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdvertisingInfo advertisingInfo = (AdvertisingInfo) obj;
        if (this.limitAdTrackingEnabled != advertisingInfo.limitAdTrackingEnabled) {
            return false;
        }
        if (this.advertisingId != null) {
            if (this.advertisingId.equals(advertisingInfo.advertisingId)) {
                return true;
            }
        } else if (advertisingInfo.advertisingId == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((this.advertisingId != null ? this.advertisingId.hashCode() : 0) * 31) + (this.limitAdTrackingEnabled ? 1 : 0);
    }
}
