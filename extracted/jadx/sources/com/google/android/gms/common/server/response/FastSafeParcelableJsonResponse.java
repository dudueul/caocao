package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class FastSafeParcelableJsonResponse extends FastJsonResponse implements SafeParcelable {
    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!getClass().isInstance(obj)) {
            return false;
        }
        FastJsonResponse fastJsonResponse = (FastJsonResponse) obj;
        for (FastJsonResponse.Field<?, ?> field : getFieldMappings().values()) {
            if (isFieldSet(field)) {
                if (fastJsonResponse.isFieldSet(field) && getFieldValue(field).equals(fastJsonResponse.getFieldValue(field))) {
                }
                return false;
            }
            if (fastJsonResponse.isFieldSet(field)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    @VisibleForTesting
    public Object getValueObject(String str) {
        return null;
    }

    public int hashCode() {
        int iHashCode = 0;
        Iterator<FastJsonResponse.Field<?, ?>> it = getFieldMappings().values().iterator();
        while (true) {
            int i = iHashCode;
            if (!it.hasNext()) {
                return i;
            }
            FastJsonResponse.Field<?, ?> next = it.next();
            if (isFieldSet(next)) {
                iHashCode = getFieldValue(next).hashCode() + (i * 31);
            } else {
                iHashCode = i;
            }
        }
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    @VisibleForTesting
    public boolean isPrimitiveFieldSet(String str) {
        return false;
    }

    public byte[] toByteArray() {
        Parcel parcelObtain = Parcel.obtain();
        writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        return bArrMarshall;
    }
}
