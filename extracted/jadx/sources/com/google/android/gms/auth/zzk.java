package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzk implements zzm<Bundle> {
    private final /* synthetic */ Account zzr;

    zzk(Account account) {
        this.zzr = account;
    }

    @Override // com.google.android.gms.auth.zzm
    public final /* synthetic */ Bundle zze(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        return (Bundle) zzg.zzd(com.google.android.gms.internal.auth.zzi.zzd(iBinder).zzd(this.zzr));
    }
}
