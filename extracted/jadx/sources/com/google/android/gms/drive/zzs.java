package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzs implements Parcelable.Creator<zzr> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        int i = 0;
        String strCreateString = null;
        String strCreateString2 = null;
        int i2 = 0;
        String strCreateString3 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 4:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 5:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 6:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 7:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzr(strCreateString3, i2, strCreateString2, strCreateString, i, z);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr[] newArray(int i) {
        return new zzr[i];
    }
}
