package com.google.firebase.messaging;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SafeParcelable.Class(creator = "RemoteMessageCreator")
@SafeParcelable.Reserved({1})
public final class RemoteMessage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RemoteMessage> CREATOR = new zzd();
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NORMAL = 2;
    public static final int PRIORITY_UNKNOWN = 0;

    @SafeParcelable.Field(id = 2)
    Bundle zzdm;
    private Map<String, String> zzdn;
    private Notification zzdo;

    public static class Builder {
        private final Bundle zzdm = new Bundle();
        private final Map<String, String> zzdn = new ArrayMap();

        public Builder(String str) {
            if (TextUtils.isEmpty(str)) {
                String strValueOf = String.valueOf(str);
                throw new IllegalArgumentException(strValueOf.length() != 0 ? "Invalid to: ".concat(strValueOf) : new String("Invalid to: "));
            }
            this.zzdm.putString("google.to", str);
        }

        public Builder addData(String str, String str2) {
            this.zzdn.put(str, str2);
            return this;
        }

        public RemoteMessage build() {
            Bundle bundle = new Bundle();
            for (Map.Entry<String, String> entry : this.zzdn.entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }
            bundle.putAll(this.zzdm);
            this.zzdm.remove("from");
            return new RemoteMessage(bundle);
        }

        public Builder clearData() {
            this.zzdn.clear();
            return this;
        }

        public Builder setCollapseKey(String str) {
            this.zzdm.putString("collapse_key", str);
            return this;
        }

        public Builder setData(Map<String, String> map) {
            this.zzdn.clear();
            this.zzdn.putAll(map);
            return this;
        }

        public Builder setMessageId(String str) {
            this.zzdm.putString("google.message_id", str);
            return this;
        }

        public Builder setMessageType(String str) {
            this.zzdm.putString("message_type", str);
            return this;
        }

        public Builder setTtl(@IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED, to = 86400) int i) {
            this.zzdm.putString("google.ttl", String.valueOf(i));
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MessagePriority {
    }

    public static class Notification {
        private final String tag;
        private final String zzdp;
        private final String zzdq;
        private final String[] zzdr;
        private final String zzds;
        private final String zzdt;
        private final String[] zzdu;
        private final String zzdv;
        private final String zzdw;
        private final String zzdx;
        private final String zzdy;
        private final Uri zzdz;

        private Notification(Bundle bundle) {
            this.zzdp = zza.zza(bundle, "gcm.n.title");
            this.zzdq = zza.zzb(bundle, "gcm.n.title");
            this.zzdr = zze(bundle, "gcm.n.title");
            this.zzds = zza.zza(bundle, "gcm.n.body");
            this.zzdt = zza.zzb(bundle, "gcm.n.body");
            this.zzdu = zze(bundle, "gcm.n.body");
            this.zzdv = zza.zza(bundle, "gcm.n.icon");
            this.zzdw = zza.zzi(bundle);
            this.tag = zza.zza(bundle, "gcm.n.tag");
            this.zzdx = zza.zza(bundle, "gcm.n.color");
            this.zzdy = zza.zza(bundle, "gcm.n.click_action");
            this.zzdz = zza.zzg(bundle);
        }

        private static String[] zze(Bundle bundle, String str) {
            Object[] objArrZzc = zza.zzc(bundle, str);
            if (objArrZzc == null) {
                return null;
            }
            String[] strArr = new String[objArrZzc.length];
            for (int i = 0; i < objArrZzc.length; i++) {
                strArr[i] = String.valueOf(objArrZzc[i]);
            }
            return strArr;
        }

        @Nullable
        public String getBody() {
            return this.zzds;
        }

        @Nullable
        public String[] getBodyLocalizationArgs() {
            return this.zzdu;
        }

        @Nullable
        public String getBodyLocalizationKey() {
            return this.zzdt;
        }

        @Nullable
        public String getClickAction() {
            return this.zzdy;
        }

        @Nullable
        public String getColor() {
            return this.zzdx;
        }

        @Nullable
        public String getIcon() {
            return this.zzdv;
        }

        @Nullable
        public Uri getLink() {
            return this.zzdz;
        }

        @Nullable
        public String getSound() {
            return this.zzdw;
        }

        @Nullable
        public String getTag() {
            return this.tag;
        }

        @Nullable
        public String getTitle() {
            return this.zzdp;
        }

        @Nullable
        public String[] getTitleLocalizationArgs() {
            return this.zzdr;
        }

        @Nullable
        public String getTitleLocalizationKey() {
            return this.zzdq;
        }
    }

    @SafeParcelable.Constructor
    public RemoteMessage(@SafeParcelable.Param(id = 2) Bundle bundle) {
        this.zzdm = bundle;
    }

    private static int zzo(String str) {
        if (Constants.HIGH.equals(str)) {
            return 1;
        }
        return "normal".equals(str) ? 2 : 0;
    }

    @Nullable
    public final String getCollapseKey() {
        return this.zzdm.getString("collapse_key");
    }

    public final Map<String, String> getData() {
        if (this.zzdn == null) {
            this.zzdn = new ArrayMap();
            for (String str : this.zzdm.keySet()) {
                Object obj = this.zzdm.get(str);
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (!str.startsWith("google.") && !str.startsWith("gcm.") && !str.equals("from") && !str.equals("message_type") && !str.equals("collapse_key")) {
                        this.zzdn.put(str, str2);
                    }
                }
            }
        }
        return this.zzdn;
    }

    @Nullable
    public final String getFrom() {
        return this.zzdm.getString("from");
    }

    @Nullable
    public final String getMessageId() {
        String string = this.zzdm.getString("google.message_id");
        return string == null ? this.zzdm.getString("message_id") : string;
    }

    @Nullable
    public final String getMessageType() {
        return this.zzdm.getString("message_type");
    }

    @Nullable
    public final Notification getNotification() {
        if (this.zzdo == null && zza.zzf(this.zzdm)) {
            this.zzdo = new Notification(this.zzdm);
        }
        return this.zzdo;
    }

    public final int getOriginalPriority() {
        String string = this.zzdm.getString("google.original_priority");
        if (string == null) {
            string = this.zzdm.getString("google.priority");
        }
        return zzo(string);
    }

    public final int getPriority() {
        String string = this.zzdm.getString("google.delivered_priority");
        if (string == null) {
            if ("1".equals(this.zzdm.getString("google.priority_reduced"))) {
                return 2;
            }
            string = this.zzdm.getString("google.priority");
        }
        return zzo(string);
    }

    public final long getSentTime() {
        Object obj = this.zzdm.get("google.sent_time");
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        if (obj instanceof String) {
            try {
                return Long.parseLong((String) obj);
            } catch (NumberFormatException e) {
                String strValueOf = String.valueOf(obj);
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(strValueOf).length() + 19).append("Invalid sent time: ").append(strValueOf).toString());
            }
        }
        return 0L;
    }

    @Nullable
    public final String getTo() {
        return this.zzdm.getString("google.to");
    }

    public final int getTtl() {
        Object obj = this.zzdm.get("google.ttl");
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
                String strValueOf = String.valueOf(obj);
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(strValueOf).length() + 13).append("Invalid TTL: ").append(strValueOf).toString());
            }
        }
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzdm, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
