package com.nexon.platform.store.vendor.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class IabBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION = "com.android.vending.billing.PURCHASES_UPDATED";
    private final IabBroadcastListener mListener;

    public interface IabBroadcastListener {
        void receivedBroadcast();
    }

    public IabBroadcastReceiver(IabBroadcastListener iabBroadcastListener) {
        this.mListener = iabBroadcastListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (this.mListener != null) {
            this.mListener.receivedBroadcast();
        }
    }
}
