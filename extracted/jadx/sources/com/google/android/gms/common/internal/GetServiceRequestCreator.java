package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class GetServiceRequestCreator implements Parcelable.Creator<GetServiceRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public GetServiceRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        Feature[] featureArr = null;
        Feature[] featureArr2 = null;
        Account account = null;
        Bundle bundleCreateBundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        String strCreateString = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i3 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 2:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 4:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 5:
                    iBinder = SafeParcelReader.readIBinder(parcel, header);
                    break;
                case 6:
                    scopeArr = (Scope[]) SafeParcelReader.createTypedArray(parcel, header, Scope.CREATOR);
                    break;
                case 7:
                    bundleCreateBundle = SafeParcelReader.createBundle(parcel, header);
                    break;
                case 8:
                    account = (Account) SafeParcelReader.createParcelable(parcel, header, Account.CREATOR);
                    break;
                case 9:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 10:
                    featureArr2 = (Feature[]) SafeParcelReader.createTypedArray(parcel, header, Feature.CREATOR);
                    break;
                case 11:
                    featureArr = (Feature[]) SafeParcelReader.createTypedArray(parcel, header, Feature.CREATOR);
                    break;
                case 12:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new GetServiceRequest(i3, i2, i, strCreateString, iBinder, scopeArr, bundleCreateBundle, account, featureArr2, featureArr, z);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public GetServiceRequest[] newArray(int i) {
        return new GetServiceRequest[i];
    }
}
