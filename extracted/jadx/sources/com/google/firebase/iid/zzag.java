package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzag {
    private final Messenger zzad;
    private final zzk zzbv;

    zzag(IBinder iBinder) throws RemoteException {
        String interfaceDescriptor = iBinder.getInterfaceDescriptor();
        if ("android.os.IMessenger".equals(interfaceDescriptor)) {
            this.zzad = new Messenger(iBinder);
            this.zzbv = null;
        } else if ("com.google.android.gms.iid.IMessengerCompat".equals(interfaceDescriptor)) {
            this.zzbv = new zzk(iBinder);
            this.zzad = null;
        } else {
            String strValueOf = String.valueOf(interfaceDescriptor);
            Log.w("MessengerIpcClient", strValueOf.length() != 0 ? "Invalid interface descriptor: ".concat(strValueOf) : new String("Invalid interface descriptor: "));
            throw new RemoteException();
        }
    }

    final void send(Message message) throws RemoteException {
        if (this.zzad != null) {
            this.zzad.send(message);
        } else {
            if (this.zzbv == null) {
                throw new IllegalStateException("Both messengers are null");
            }
            this.zzbv.send(message);
        }
    }
}
