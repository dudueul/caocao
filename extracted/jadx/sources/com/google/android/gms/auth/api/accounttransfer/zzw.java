package com.google.android.gms.auth.api.accounttransfer;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.internal.auth.zzbs;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SafeParcelable.Class(creator = "AuthenticatorTransferInfoCreator")
public class zzw extends zzbs {
    public static final Parcelable.Creator<zzw> CREATOR = new zzx();
    private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzbk;

    @SafeParcelable.Indicator
    private final Set<Integer> zzbl;

    @SafeParcelable.Field(getter = "getAccountType", id = 2)
    private String zzbx;

    @SafeParcelable.Field(getter = "getStatus", id = 3)
    private int zzby;

    @SafeParcelable.Field(getter = "getTransferBytes", id = 4)
    private byte[] zzbz;

    @SafeParcelable.Field(getter = "getPendingIntent", id = 5)
    private PendingIntent zzca;

    @SafeParcelable.Field(getter = "getDeviceMetaData", id = 6)
    private DeviceMetaData zzcb;

    @SafeParcelable.VersionField(id = 1)
    private final int zzy;

    static {
        HashMap<String, FastJsonResponse.Field<?, ?>> map = new HashMap<>();
        zzbk = map;
        map.put("accountType", FastJsonResponse.Field.forString("accountType", 2));
        zzbk.put("status", FastJsonResponse.Field.forInteger("status", 3));
        zzbk.put("transferBytes", (FastJsonResponse.Field<?, ?>) FastJsonResponse.Field.forBase64("transferBytes", 4));
    }

    public zzw() {
        this.zzbl = new ArraySet(3);
        this.zzy = 1;
    }

    @SafeParcelable.Constructor
    zzw(@SafeParcelable.Indicator Set<Integer> set, @SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) byte[] bArr, @SafeParcelable.Param(id = 5) PendingIntent pendingIntent, @SafeParcelable.Param(id = 6) DeviceMetaData deviceMetaData) {
        this.zzbl = set;
        this.zzy = i;
        this.zzbx = str;
        this.zzby = i2;
        this.zzbz = bArr;
        this.zzca = pendingIntent;
        this.zzcb = deviceMetaData;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public /* synthetic */ Map getFieldMappings() {
        return zzbk;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected Object getFieldValue(FastJsonResponse.Field field) {
        switch (field.getSafeParcelableFieldId()) {
            case 1:
                return Integer.valueOf(this.zzy);
            case 2:
                return this.zzbx;
            case 3:
                return Integer.valueOf(this.zzby);
            case 4:
                return this.zzbz;
            default:
                throw new IllegalStateException(new StringBuilder(37).append("Unknown SafeParcelable id=").append(field.getSafeParcelableFieldId()).toString());
        }
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected boolean isFieldSet(FastJsonResponse.Field field) {
        return this.zzbl.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected void setDecodedBytesInternal(FastJsonResponse.Field<?, ?> field, String str, byte[] bArr) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case 4:
                this.zzbz = bArr;
                this.zzbl.add(Integer.valueOf(safeParcelableFieldId));
                return;
            default:
                throw new IllegalArgumentException(new StringBuilder(59).append("Field with id=").append(safeParcelableFieldId).append(" is not known to be an byte array.").toString());
        }
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case 3:
                this.zzby = i;
                this.zzbl.add(Integer.valueOf(safeParcelableFieldId));
                return;
            default:
                throw new IllegalArgumentException(new StringBuilder(52).append("Field with id=").append(safeParcelableFieldId).append(" is not known to be an int.").toString());
        }
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case 2:
                this.zzbx = str2;
                this.zzbl.add(Integer.valueOf(safeParcelableFieldId));
                return;
            default:
                throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string.", Integer.valueOf(safeParcelableFieldId)));
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> set = this.zzbl;
        if (set.contains(1)) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzy);
        }
        if (set.contains(2)) {
            SafeParcelWriter.writeString(parcel, 2, this.zzbx, true);
        }
        if (set.contains(3)) {
            SafeParcelWriter.writeInt(parcel, 3, this.zzby);
        }
        if (set.contains(4)) {
            SafeParcelWriter.writeByteArray(parcel, 4, this.zzbz, true);
        }
        if (set.contains(5)) {
            SafeParcelWriter.writeParcelable(parcel, 5, this.zzca, i, true);
        }
        if (set.contains(6)) {
            SafeParcelWriter.writeParcelable(parcel, 6, this.zzcb, i, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
