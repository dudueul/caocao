package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzd implements Parcelable.Creator<AccountChangeEvent> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ AccountChangeEvent createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        String strCreateString = null;
        int i = 0;
        int i2 = 0;
        String strCreateString2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i3 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel, header);
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 6:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new AccountChangeEvent(i3, j, strCreateString2, i2, i, strCreateString);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ AccountChangeEvent[] newArray(int i) {
        return new AccountChangeEvent[i];
    }
}
