package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.HashSet;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzv implements Parcelable.Creator<zzu> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzu createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        String strCreateString = null;
        String strCreateString2 = null;
        zzw zzwVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, header);
                    hashSet.add(1);
                    break;
                case 2:
                    zzw zzwVar2 = (zzw) SafeParcelReader.createParcelable(parcel, header, zzw.CREATOR);
                    hashSet.add(2);
                    zzwVar = zzwVar2;
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    hashSet.add(3);
                    break;
                case 4:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    hashSet.add(4);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() != iValidateObjectHeader) {
            throw new SafeParcelReader.ParseException(new StringBuilder(37).append("Overread allowed size end=").append(iValidateObjectHeader).toString(), parcel);
        }
        return new zzu(hashSet, i, zzwVar, strCreateString2, strCreateString);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzu[] newArray(int i) {
        return new zzu[i];
    }
}
