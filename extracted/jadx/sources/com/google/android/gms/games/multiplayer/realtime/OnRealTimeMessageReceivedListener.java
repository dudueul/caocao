package com.google.android.gms.games.multiplayer.realtime;

import android.support.annotation.NonNull;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface OnRealTimeMessageReceivedListener extends RealTimeMessageReceivedListener {
    @Override // com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener
    void onRealTimeMessageReceived(@NonNull RealTimeMessage realTimeMessage);
}
