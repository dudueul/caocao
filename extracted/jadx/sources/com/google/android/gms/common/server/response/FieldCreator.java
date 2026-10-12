package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.server.converter.ConverterWrapper;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FieldCreator implements Parcelable.Creator<FastJsonResponse.Field> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public FastJsonResponse.Field createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ConverterWrapper converterWrapper = null;
        String strCreateString = null;
        int i = 0;
        String strCreateString2 = null;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i4 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 2:
                    i3 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 3:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 4:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 5:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 6:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 7:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 8:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 9:
                    converterWrapper = (ConverterWrapper) SafeParcelReader.createParcelable(parcel, header, ConverterWrapper.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new FastJsonResponse.Field(i4, i3, z2, i2, z, strCreateString2, i, strCreateString, converterWrapper);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public FastJsonResponse.Field[] newArray(int i) {
        return new FastJsonResponse.Field[i];
    }
}
