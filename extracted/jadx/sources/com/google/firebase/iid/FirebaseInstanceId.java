package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FirebaseInstanceId {
    private static final long zzaf = TimeUnit.HOURS.toSeconds(8);
    private static zzau zzag;

    @VisibleForTesting
    @GuardedBy("FirebaseInstanceId.class")
    private static ScheduledThreadPoolExecutor zzah;
    private final Executor zzai;
    private final FirebaseApp zzaj;
    private final zzal zzak;
    private MessagingChannel zzal;
    private final zzao zzam;
    private final zzay zzan;

    @GuardedBy("this")
    private boolean zzao;

    @GuardedBy("this")
    private boolean zzap;

    FirebaseInstanceId(FirebaseApp firebaseApp) {
        this(firebaseApp, new zzal(firebaseApp.getApplicationContext()), zzi.zze(), zzi.zze());
    }

    private FirebaseInstanceId(FirebaseApp firebaseApp, zzal zzalVar, Executor executor, Executor executor2) {
        this.zzam = new zzao();
        this.zzao = false;
        if (zzal.zza(firebaseApp) == null) {
            throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
        }
        synchronized (FirebaseInstanceId.class) {
            if (zzag == null) {
                zzag = new zzau(firebaseApp.getApplicationContext());
            }
        }
        this.zzaj = firebaseApp;
        this.zzak = zzalVar;
        if (this.zzal == null) {
            MessagingChannel messagingChannel = (MessagingChannel) firebaseApp.get(MessagingChannel.class);
            if (messagingChannel == null || !messagingChannel.isAvailable()) {
                this.zzal = new zzp(firebaseApp, zzalVar, executor);
            } else {
                this.zzal = messagingChannel;
            }
        }
        this.zzal = this.zzal;
        this.zzai = executor2;
        this.zzan = new zzay(zzag);
        this.zzap = zzq();
        if (zzs()) {
            zzf();
        }
    }

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @Keep
    public static synchronized FirebaseInstanceId getInstance(@NonNull FirebaseApp firebaseApp) {
        return (FirebaseInstanceId) firebaseApp.get(FirebaseInstanceId.class);
    }

    private final synchronized void startSync() {
        if (!this.zzao) {
            zza(0L);
        }
    }

    private final Task<InstanceIdResult> zza(final String str, final String str2) {
        final String strZzd = zzd(str2);
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzai.execute(new Runnable(this, str, str2, taskCompletionSource, strZzd) { // from class: com.google.firebase.iid.zzm
            private final FirebaseInstanceId zzaq;
            private final String zzar;
            private final String zzas;
            private final TaskCompletionSource zzat;
            private final String zzau;

            {
                this.zzaq = this;
                this.zzar = str;
                this.zzas = str2;
                this.zzat = taskCompletionSource;
                this.zzau = strZzd;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzaq.zza(this.zzar, this.zzas, this.zzat, this.zzau);
            }
        });
        return taskCompletionSource.getTask();
    }

    private final <T> T zza(Task<T> task) throws IOException {
        try {
            return (T) Tasks.await(task, 30000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof IOException) {
                if ("INSTANCE_ID_RESET".equals(cause.getMessage())) {
                    zzl();
                }
                throw ((IOException) cause);
            }
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new IOException(e2);
        } catch (TimeoutException e3) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
    }

    static void zza(Runnable runnable, long j) {
        synchronized (FirebaseInstanceId.class) {
            if (zzah == null) {
                zzah = new ScheduledThreadPoolExecutor(1);
            }
            zzah.schedule(runnable, j, TimeUnit.SECONDS);
        }
    }

    private static String zzd(String str) {
        return (str.isEmpty() || str.equalsIgnoreCase("fcm") || str.equalsIgnoreCase("gcm")) ? "*" : str;
    }

    private final void zzf() {
        zzav zzavVarZzi = zzi();
        if (!zzn() || zzavVarZzi == null || zzavVarZzi.zzj(this.zzak.zzac()) || this.zzan.zzap()) {
            startSync();
        }
    }

    private static String zzh() {
        return zzal.zza(zzag.zzg("").getKeyPair());
    }

    static boolean zzk() {
        return Log.isLoggable("FirebaseInstanceId", 3) || (Build.VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseInstanceId", 3));
    }

    private final boolean zzq() {
        ApplicationInfo applicationInfo;
        Context applicationContext = this.zzaj.getApplicationContext();
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
        if (sharedPreferences.contains("auto_init")) {
            return sharedPreferences.getBoolean("auto_init", true);
        }
        try {
            PackageManager packageManager = applicationContext.getPackageManager();
            if (packageManager != null && (applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128)) != null && applicationInfo.metaData != null && applicationInfo.metaData.containsKey("firebase_messaging_auto_init_enabled")) {
                return applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled");
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return zzr();
    }

    private final boolean zzr() {
        try {
            Class.forName("com.google.firebase.messaging.FirebaseMessaging");
            return true;
        } catch (ClassNotFoundException e) {
            Context applicationContext = this.zzaj.getApplicationContext();
            Intent intent = new Intent("com.google.firebase.MESSAGING_EVENT");
            intent.setPackage(applicationContext.getPackageName());
            ResolveInfo resolveInfoResolveService = applicationContext.getPackageManager().resolveService(intent, 0);
            return (resolveInfoResolveService == null || resolveInfoResolveService.serviceInfo == null) ? false : true;
        }
    }

    @WorkerThread
    public void deleteInstanceId() throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        zza(this.zzal.deleteInstanceId(zzh()));
        zzl();
    }

    @WorkerThread
    public void deleteToken(String str, String str2) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        String strZzd = zzd(str2);
        zza(this.zzal.deleteToken(zzh(), str, strZzd));
        zzag.zzd("", str, strZzd);
    }

    public long getCreationTime() {
        return zzag.zzg("").getCreationTime();
    }

    @WorkerThread
    public String getId() {
        zzf();
        return zzh();
    }

    @NonNull
    public Task<InstanceIdResult> getInstanceId() {
        return zza(zzal.zza(this.zzaj), "*");
    }

    @Nullable
    @Deprecated
    public String getToken() {
        zzav zzavVarZzi = zzi();
        if (zzavVarZzi == null || zzavVarZzi.zzj(this.zzak.zzac())) {
            startSync();
        }
        if (zzavVarZzi != null) {
            return zzavVarZzi.zzbh;
        }
        return null;
    }

    @WorkerThread
    public String getToken(String str, String str2) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        return ((InstanceIdResult) zza(zza(str, str2))).getToken();
    }

    public final synchronized Task<Void> zza(String str) {
        Task<Void> taskZza;
        taskZza = this.zzan.zza(str);
        startSync();
        return taskZza;
    }

    final /* synthetic */ Task zza(String str, String str2, String str3) {
        return this.zzal.getToken(str, str2, str3);
    }

    final synchronized void zza(long j) {
        zza(new zzaw(this, this.zzak, this.zzan, Math.min(Math.max(30L, j << 1), zzaf)), j);
        this.zzao = true;
    }

    final /* synthetic */ void zza(final String str, String str2, final TaskCompletionSource taskCompletionSource, final String str3) {
        final String strZzh = zzh();
        zzav zzavVarZzc = zzag.zzc("", str, str2);
        if (zzavVarZzc == null || zzavVarZzc.zzj(this.zzak.zzac())) {
            this.zzam.zza(str, str3, new zzaq(this, strZzh, str, str3) { // from class: com.google.firebase.iid.zzn
                private final FirebaseInstanceId zzaq;
                private final String zzar;
                private final String zzas;
                private final String zzav;

                {
                    this.zzaq = this;
                    this.zzar = strZzh;
                    this.zzas = str;
                    this.zzav = str3;
                }

                @Override // com.google.firebase.iid.zzaq
                public final Task zzt() {
                    return this.zzaq.zza(this.zzar, this.zzas, this.zzav);
                }
            }).addOnCompleteListener(this.zzai, new OnCompleteListener(this, str, str3, taskCompletionSource, strZzh) { // from class: com.google.firebase.iid.zzo
                private final FirebaseInstanceId zzaq;
                private final String zzar;
                private final String zzas;
                private final TaskCompletionSource zzat;
                private final String zzau;

                {
                    this.zzaq = this;
                    this.zzar = str;
                    this.zzas = str3;
                    this.zzat = taskCompletionSource;
                    this.zzau = strZzh;
                }

                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    this.zzaq.zza(this.zzar, this.zzas, this.zzat, this.zzau, task);
                }
            });
        } else {
            taskCompletionSource.setResult(new zzv(strZzh, zzavVarZzc.zzbh));
        }
    }

    final /* synthetic */ void zza(String str, String str2, TaskCompletionSource taskCompletionSource, String str3, Task task) {
        if (!task.isSuccessful()) {
            taskCompletionSource.setException(task.getException());
            return;
        }
        String str4 = (String) task.getResult();
        zzag.zza("", str, str2, str4, this.zzak.zzac());
        taskCompletionSource.setResult(new zzv(str3, str4));
    }

    final synchronized void zza(boolean z) {
        this.zzao = z;
    }

    final void zzb(String str) throws IOException {
        zzav zzavVarZzi = zzi();
        if (zzavVarZzi == null || zzavVarZzi.zzj(this.zzak.zzac())) {
            throw new IOException("token not available");
        }
        zza(this.zzal.subscribeToTopic(zzh(), zzavVarZzi.zzbh, str));
    }

    @VisibleForTesting
    public final synchronized void zzb(boolean z) {
        SharedPreferences.Editor editorEdit = this.zzaj.getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit();
        editorEdit.putBoolean("auto_init", z);
        editorEdit.apply();
        if (!this.zzap && z) {
            zzf();
        }
        this.zzap = z;
    }

    final void zzc(String str) throws IOException {
        zzav zzavVarZzi = zzi();
        if (zzavVarZzi == null || zzavVarZzi.zzj(this.zzak.zzac())) {
            throw new IOException("token not available");
        }
        zza(this.zzal.unsubscribeFromTopic(zzh(), zzavVarZzi.zzbh, str));
    }

    final FirebaseApp zzg() {
        return this.zzaj;
    }

    @Nullable
    final zzav zzi() {
        return zzag.zzc("", zzal.zza(this.zzaj), "*");
    }

    final String zzj() throws IOException {
        return getToken(zzal.zza(this.zzaj), "*");
    }

    final synchronized void zzl() {
        zzag.zzak();
        if (zzs()) {
            startSync();
        }
    }

    final boolean zzm() {
        return this.zzal.isAvailable();
    }

    final boolean zzn() {
        return this.zzal.isChannelBuilt();
    }

    final void zzo() throws IOException {
        String strZzh = zzh();
        zzav zzavVarZzi = zzi();
        zza(this.zzal.buildChannel(strZzh, zzavVarZzi == null ? null : zzavVarZzi.zzbh));
    }

    final void zzp() {
        zzag.zzh("");
        startSync();
    }

    @VisibleForTesting
    public final synchronized boolean zzs() {
        return this.zzap;
    }
}
