package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DataHolderCreator implements Parcelable.Creator<DataHolder> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public DataHolder createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Bundle bundleCreateBundle = null;
        int i = 0;
        CursorWindow[] cursorWindowArr = null;
        String[] strArrCreateStringArray = null;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    strArrCreateStringArray = SafeParcelReader.createStringArray(parcel, header);
                    break;
                case 2:
                    cursorWindowArr = (CursorWindow[]) SafeParcelReader.createTypedArray(parcel, header, CursorWindow.CREATOR);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 4:
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
        DataHolder dataHolder = new DataHolder(i2, strArrCreateStringArray, cursorWindowArr, i, bundleCreateBundle);
        dataHolder.validateContents();
        return dataHolder;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public DataHolder[] newArray(int i) {
        return new DataHolder[i];
    }
}
