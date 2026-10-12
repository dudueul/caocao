package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzp implements Parcelable.Creator<zzo> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzo createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        zzr zzrVar = null;
        int i = 0;
        ArrayList arrayListCreateTypedList = null;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    hashSet.add(1);
                    break;
                case 2:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, zzu.CREATOR);
                    hashSet.add(2);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, header);
                    hashSet.add(3);
                    break;
                case 4:
                    zzr zzrVar2 = (zzr) SafeParcelReader.createParcelable(parcel, header, zzr.CREATOR);
                    hashSet.add(4);
                    zzrVar = zzrVar2;
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() != iValidateObjectHeader) {
            throw new SafeParcelReader.ParseException(new StringBuilder(37).append("Overread allowed size end=").append(iValidateObjectHeader).toString(), parcel);
        }
        return new zzo(hashSet, i2, arrayListCreateTypedList, i, zzrVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzo[] newArray(int i) {
        return new zzo[i];
    }
}
