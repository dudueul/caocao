package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class AuthAccountRequestCreator implements Parcelable.Creator<AuthAccountRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public AuthAccountRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        Account account = null;
        Integer integerObject = null;
        Integer integerObject2 = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 2:
                    iBinder = SafeParcelReader.readIBinder(parcel, header);
                    break;
                case 3:
                    scopeArr = (Scope[]) SafeParcelReader.createTypedArray(parcel, header, Scope.CREATOR);
                    break;
                case 4:
                    integerObject2 = SafeParcelReader.readIntegerObject(parcel, header);
                    break;
                case 5:
                    integerObject = SafeParcelReader.readIntegerObject(parcel, header);
                    break;
                case 6:
                    account = (Account) SafeParcelReader.createParcelable(parcel, header, Account.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new AuthAccountRequest(i, iBinder, scopeArr, integerObject2, integerObject, account);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public AuthAccountRequest[] newArray(int i) {
        return new AuthAccountRequest[i];
    }
}
