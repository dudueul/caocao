package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzu implements zzt {
    private final IBinder zzbf;

    zzu(IBinder iBinder) {
        this.zzbf = iBinder;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.zzbf;
    }

    @Override // com.google.firebase.iid.zzt
    public final void send(Message message) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
        parcelObtain.writeInt(1);
        message.writeToParcel(parcelObtain, 0);
        try {
            this.zzbf.transact(1, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }
}
