package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.zzat;
import com.google.firebase.iid.zzz;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FirebaseMessagingService extends com.google.firebase.iid.zzb {
    private static final Queue<String> zzdl = new ArrayDeque(10);

    static void zzj(Bundle bundle) {
        Iterator<String> it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next != null && next.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    static boolean zzk(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        return "1".equals(bundle.getString("google.c.a.e"));
    }

    @WorkerThread
    public void onDeletedMessages() {
    }

    @WorkerThread
    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    @WorkerThread
    public void onMessageSent(String str) {
    }

    @WorkerThread
    public void onNewToken(String str) {
    }

    @WorkerThread
    public void onSendError(String str, Exception exc) {
    }

    @Override // com.google.firebase.iid.zzb
    protected final Intent zzb(Intent intent) {
        return zzat.zzah().zzai();
    }

    @Override // com.google.firebase.iid.zzb
    public final boolean zzc(Intent intent) {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                Log.e("FirebaseMessaging", "Notification pending intent canceled");
            }
        }
        if (zzk(intent.getExtras())) {
            zzb.zzf(this, intent);
        }
        return true;
    }

    @Override // com.google.firebase.iid.zzb
    public final void zzd(Intent intent) {
        Task<Void> taskZza;
        boolean z;
        byte b = 0;
        String action = intent.getAction();
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(action)) {
            if ("com.google.firebase.messaging.NOTIFICATION_DISMISS".equals(action)) {
                if (zzk(intent.getExtras())) {
                    zzb.zzg(this, intent);
                    return;
                }
                return;
            } else if ("com.google.firebase.messaging.NEW_TOKEN".equals(action)) {
                onNewToken(intent.getStringExtra("token"));
                return;
            } else {
                String strValueOf = String.valueOf(intent.getAction());
                Log.d("FirebaseMessaging", strValueOf.length() != 0 ? "Unknown intent action: ".concat(strValueOf) : new String("Unknown intent action: "));
                return;
            }
        }
        String stringExtra = intent.getStringExtra("google.message_id");
        if (TextUtils.isEmpty(stringExtra)) {
            taskZza = Tasks.forResult(null);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("google.message_id", stringExtra);
            taskZza = zzz.zzc(this).zza(2, bundle);
        }
        if (TextUtils.isEmpty(stringExtra)) {
            z = false;
        } else if (zzdl.contains(stringExtra)) {
            if (Log.isLoggable("FirebaseMessaging", 3)) {
                String strValueOf2 = String.valueOf(stringExtra);
                Log.d("FirebaseMessaging", strValueOf2.length() != 0 ? "Received duplicate message: ".concat(strValueOf2) : new String("Received duplicate message: "));
            }
            z = true;
        } else {
            if (zzdl.size() >= 10) {
                zzdl.remove();
            }
            zzdl.add(stringExtra);
            z = false;
        }
        if (!z) {
            String stringExtra2 = intent.getStringExtra("message_type");
            if (stringExtra2 == null) {
                stringExtra2 = "gcm";
            }
            switch (stringExtra2.hashCode()) {
                case -2062414158:
                    b = !stringExtra2.equals("deleted_messages") ? (byte) -1 : (byte) 1;
                    break;
                case 102161:
                    if (!stringExtra2.equals("gcm")) {
                        b = -1;
                    }
                    break;
                case 814694033:
                    b = !stringExtra2.equals("send_error") ? (byte) -1 : (byte) 3;
                    break;
                case 814800675:
                    b = !stringExtra2.equals("send_event") ? (byte) -1 : (byte) 2;
                    break;
                default:
                    b = -1;
                    break;
            }
            switch (b) {
                case 0:
                    if (zzk(intent.getExtras())) {
                        zzb.zze(this, intent);
                    }
                    Bundle extras = intent.getExtras();
                    if (extras == null) {
                        extras = new Bundle();
                    }
                    extras.remove("android.support.content.wakelockid");
                    if (!zza.zzf(extras)) {
                        onMessageReceived(new RemoteMessage(extras));
                    } else if (!zza.zzd(this).zzh(extras)) {
                        if (zzk(extras)) {
                            zzb.zzh(this, intent);
                        }
                        onMessageReceived(new RemoteMessage(extras));
                    }
                    break;
                case 1:
                    onDeletedMessages();
                    break;
                case 2:
                    onMessageSent(intent.getStringExtra("google.message_id"));
                    break;
                case 3:
                    String stringExtra3 = intent.getStringExtra("google.message_id");
                    if (stringExtra3 == null) {
                        stringExtra3 = intent.getStringExtra("message_id");
                    }
                    onSendError(stringExtra3, new SendException(intent.getStringExtra("error")));
                    break;
                default:
                    String strValueOf3 = String.valueOf(stringExtra2);
                    Log.w("FirebaseMessaging", strValueOf3.length() != 0 ? "Received message with unknown type: ".concat(strValueOf3) : new String("Received message with unknown type: "));
                    break;
            }
        }
        try {
            Tasks.await(taskZza, 1L, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            String strValueOf4 = String.valueOf(e);
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(strValueOf4).length() + 20).append("Message ack failed: ").append(strValueOf4).toString());
        }
    }
}
