package com.nexon.caocao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class PromoBroadcastReceiver extends BroadcastReceiver {
    private final PromoBroadcastListener mListener;

    public interface PromoBroadcastListener {
        void receivedBroadcast();
    }

    public PromoBroadcastReceiver(PromoBroadcastListener promoBroadcastListener) {
        this.mListener = promoBroadcastListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (this.mListener != null) {
            this.mListener.receivedBroadcast();
        }
    }
}
