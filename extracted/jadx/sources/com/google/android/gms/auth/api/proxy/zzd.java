package com.google.android.gms.auth.api.proxy;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzd implements Parcelable.Creator<ProxyRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ProxyRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        Bundle bundleCreateBundle = null;
        byte[] bArrCreateByteArray = null;
        int i = 0;
        String strCreateString = null;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 2:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 3:
                    j = SafeParcelReader.readLong(parcel, header);
                    break;
                case 4:
                    bArrCreateByteArray = SafeParcelReader.createByteArray(parcel, header);
                    break;
                case 5:
                    bundleCreateBundle = SafeParcelReader.createBundle(parcel, header);
                    break;
                case 1000:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new ProxyRequest(i2, strCreateString, i, j, bArrCreateByteArray, bundleCreateBundle);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ProxyRequest[] newArray(int i) {
        return new ProxyRequest[i];
    }
}
