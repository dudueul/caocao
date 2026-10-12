package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzd implements Parcelable.Creator<zze> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zze createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Long longObject = null;
        Uri uri = null;
        BitmapTeleporter bitmapTeleporter = null;
        Long longObject2 = null;
        String strCreateString = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 2:
                    longObject2 = SafeParcelReader.readLongObject(parcel, header);
                    break;
                case 3:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 4:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                case 5:
                    bitmapTeleporter = (BitmapTeleporter) SafeParcelReader.createParcelable(parcel, header, BitmapTeleporter.CREATOR);
                    break;
                case 6:
                    longObject = SafeParcelReader.readLongObject(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zze(strCreateString, longObject2, bitmapTeleporter, uri, longObject);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zze[] newArray(int i) {
        return new zze[i];
    }
}
