package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzk implements Parcelable.Creator<DriveId> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DriveId createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        int i = -1;
        long j = 0;
        long j2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    j2 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 4:
                    j = SafeParcelReader.readLong(parcel, header);
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new DriveId(strCreateString, j2, j, i);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DriveId[] newArray(int i) {
        return new DriveId[i];
    }
}
