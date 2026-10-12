package com.google.android.gms.auth.api.accounttransfer;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.HashSet;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzx implements Parcelable.Creator<zzw> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzw createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        DeviceMetaData deviceMetaData = null;
        PendingIntent pendingIntent = null;
        byte[] bArrCreateByteArray = null;
        int i = 0;
        String strCreateString = null;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    hashSet.add(1);
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    hashSet.add(2);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, header);
                    hashSet.add(3);
                    break;
                case 4:
                    bArrCreateByteArray = SafeParcelReader.createByteArray(parcel, header);
                    hashSet.add(4);
                    break;
                case 5:
                    PendingIntent pendingIntent2 = (PendingIntent) SafeParcelReader.createParcelable(parcel, header, PendingIntent.CREATOR);
                    hashSet.add(5);
                    pendingIntent = pendingIntent2;
                    break;
                case 6:
                    DeviceMetaData deviceMetaData2 = (DeviceMetaData) SafeParcelReader.createParcelable(parcel, header, DeviceMetaData.CREATOR);
                    hashSet.add(6);
                    deviceMetaData = deviceMetaData2;
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() != iValidateObjectHeader) {
            throw new SafeParcelReader.ParseException(new StringBuilder(37).append("Overread allowed size end=").append(iValidateObjectHeader).toString(), parcel);
        }
        return new zzw(hashSet, i2, strCreateString, i, bArrCreateByteArray, pendingIntent, deviceMetaData);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzw[] newArray(int i) {
        return new zzw[i];
    }
}
