package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzcx extends TaskApiCall<zzaw, DriveContents> {
    private final /* synthetic */ DriveContents zzfv;

    zzcx(zzch zzchVar, DriveContents driveContents) {
        this.zzfv = driveContents;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<DriveContents> taskCompletionSource) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgd(this.zzfv.getDriveId(), 536870912, this.zzfv.zzh().getRequestId()), new zzhc(taskCompletionSource));
    }
}
