package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzs implements Parcelable.Creator<zzr> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        ArrayList<String> arrayListCreateStringList = null;
        ArrayList<String> arrayListCreateStringList2 = null;
        ArrayList<String> arrayListCreateStringList3 = null;
        ArrayList<String> arrayListCreateStringList4 = null;
        ArrayList<String> arrayListCreateStringList5 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 2:
                    arrayListCreateStringList5 = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 3:
                    arrayListCreateStringList4 = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 4:
                    arrayListCreateStringList3 = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 5:
                    arrayListCreateStringList2 = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 6:
                    arrayListCreateStringList = SafeParcelReader.createStringList(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzr(i, arrayListCreateStringList5, arrayListCreateStringList4, arrayListCreateStringList3, arrayListCreateStringList2, arrayListCreateStringList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr[] newArray(int i) {
        return new zzr[i];
    }
}
