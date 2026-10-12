package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzv implements Parcelable.Creator<zzu> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzu createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        Integer integerObject = null;
        DriveId driveId = null;
        String strCreateString = null;
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    metadataBundle = (MetadataBundle) SafeParcelReader.createParcelable(parcel, header, MetadataBundle.CREATOR);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 4:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 5:
                    driveId = (DriveId) SafeParcelReader.createParcelable(parcel, header, DriveId.CREATOR);
                    break;
                case 6:
                    integerObject = SafeParcelReader.readIntegerObject(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzu(metadataBundle, i, strCreateString, driveId, integerObject);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzu[] newArray(int i) {
        return new zzu[i];
    }
}
