package com.google.firebase.iid;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzp implements MessagingChannel {
    private final FirebaseApp zzaj;
    private final zzal zzak;

    @VisibleForTesting
    private final zzar zzaz;
    private final Executor zzba;

    zzp(FirebaseApp firebaseApp, zzal zzalVar, Executor executor) {
        this(firebaseApp, zzalVar, executor, new zzar(firebaseApp.getApplicationContext(), zzalVar));
    }

    @VisibleForTesting
    private zzp(FirebaseApp firebaseApp, zzal zzalVar, Executor executor, zzar zzarVar) {
        this.zzaj = firebaseApp;
        this.zzak = zzalVar;
        this.zzaz = zzarVar;
        this.zzba = executor;
    }

    private final Task<Bundle> zza(String str, String str2, String str3, final Bundle bundle) {
        bundle.putString("scope", str3);
        bundle.putString("sender", str2);
        bundle.putString("subtype", str2);
        bundle.putString("appid", str);
        bundle.putString("gmp_app_id", this.zzaj.getOptions().getApplicationId());
        bundle.putString("gmsv", Integer.toString(this.zzak.zzae()));
        bundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
        bundle.putString("app_ver", this.zzak.zzac());
        bundle.putString("app_ver_name", this.zzak.zzad());
        bundle.putString("cliv", "fiid-12451000");
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzba.execute(new Runnable(this, bundle, taskCompletionSource) { // from class: com.google.firebase.iid.zzq
            private final zzp zzbb;
            private final Bundle zzbc;
            private final TaskCompletionSource zzbd;

            {
                this.zzbb = this;
                this.zzbc = bundle;
                this.zzbd = taskCompletionSource;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzbb.zza(this.zzbc, this.zzbd);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String zza(Bundle bundle) throws IOException {
        if (bundle == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String string = bundle.getString("registration_id");
        if (string != null || (string = bundle.getString("unregistered")) != null) {
            return string;
        }
        String string2 = bundle.getString("error");
        if ("RST".equals(string2)) {
            throw new IOException("INSTANCE_ID_RESET");
        }
        if (string2 != null) {
            throw new IOException(string2);
        }
        String strValueOf = String.valueOf(bundle);
        Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(strValueOf).length() + 21).append("Unexpected response: ").append(strValueOf).toString(), new Throwable());
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    private final <T> Task<Void> zzb(Task<T> task) {
        return task.continueWith(zzi.zzd(), new zzr(this));
    }

    private final Task<String> zzc(Task<Bundle> task) {
        return task.continueWith(this.zzba, new zzs(this));
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final Task<Void> ackMessage(String str) {
        return null;
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final Task<Void> buildChannel(String str, String str2) {
        return Tasks.forResult(null);
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final Task<Void> deleteInstanceId(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("iid-operation", "delete");
        bundle.putString("delete", "1");
        return zzb(zzc(zza(str, "*", "*", bundle)));
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final Task<Void> deleteToken(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("delete", "1");
        return zzb(zzc(zza(str, str2, str3, bundle)));
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final Task<String> getToken(String str, String str2, String str3) {
        return zzc(zza(str, str2, str3, new Bundle()));
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final boolean isAvailable() {
        return this.zzak.zzab() != 0;
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final boolean isChannelBuilt() {
        return true;
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final Task<Void> subscribeToTopic(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String strValueOf = String.valueOf("/topics/");
        String strValueOf2 = String.valueOf(str3);
        bundle.putString("gcm.topic", strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
        String strValueOf3 = String.valueOf("/topics/");
        String strValueOf4 = String.valueOf(str3);
        return zzb(zzc(zza(str, str2, strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3), bundle)));
    }

    @Override // com.google.firebase.iid.MessagingChannel
    public final Task<Void> unsubscribeFromTopic(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String strValueOf = String.valueOf("/topics/");
        String strValueOf2 = String.valueOf(str3);
        bundle.putString("gcm.topic", strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
        bundle.putString("delete", "1");
        String strValueOf3 = String.valueOf("/topics/");
        String strValueOf4 = String.valueOf(str3);
        return zzb(zzc(zza(str, str2, strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3), bundle)));
    }

    final /* synthetic */ void zza(Bundle bundle, TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(this.zzaz.zzc(bundle));
        } catch (IOException e) {
            taskCompletionSource.setException(e);
        }
    }
}
