package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.query.Query;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzgl implements Parcelable.Creator<zzgk> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgk createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Query query = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    query = (Query) SafeParcelReader.createParcelable(parcel, header, Query.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzgk(query);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgk[] newArray(int i) {
        return new zzgk[i];
    }
}
