package com.tapjoy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tapjoy.internal.hb;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class GCMReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        boolean zA = hb.b(context).a(intent);
        if (isOrderedBroadcast()) {
            setResult(-1, null, null);
            if (zA) {
                abortBroadcast();
            }
        }
    }
}
