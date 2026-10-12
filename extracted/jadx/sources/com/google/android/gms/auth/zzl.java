package com.google.android.gms.auth;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.auth.zzbr;
import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzl implements zzm<Boolean> {
    private final /* synthetic */ String zzw;

    zzl(String str) {
        this.zzw = str;
    }

    @Override // com.google.android.gms.auth.zzm
    public final /* synthetic */ Boolean zze(IBinder iBinder) throws GoogleAuthException, RemoteException, IOException {
        Bundle bundle = (Bundle) zzg.zzd(com.google.android.gms.internal.auth.zzi.zzd(iBinder).zzd(this.zzw));
        String string = bundle.getString("Error");
        Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
        zzbr zzbrVarZzh = zzbr.zzh(string);
        if (zzbr.SUCCESS.equals(zzbrVarZzh)) {
            return true;
        }
        if (!zzbr.zzd(zzbrVarZzh)) {
            throw new GoogleAuthException(string);
        }
        Logger logger = zzg.zzq;
        String strValueOf = String.valueOf(zzbrVarZzh);
        logger.w("GoogleAuthUtil", new StringBuilder(String.valueOf(strValueOf).length() + 31).append("isUserRecoverableError status: ").append(strValueOf).toString());
        throw new UserRecoverableAuthException(string, intent);
    }
}
