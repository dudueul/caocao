package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzfe implements Parcelable.Creator<zzfd> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfd createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzei zzeiVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    zzeiVar = (zzei) SafeParcelReader.createParcelable(parcel, header, zzei.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzfd(zzeiVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfd[] newArray(int i) {
        return new zzfd[i];
    }
}
