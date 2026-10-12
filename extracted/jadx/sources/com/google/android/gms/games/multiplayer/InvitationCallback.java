package com.google.android.gms.games.multiplayer;

import android.support.annotation.NonNull;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class InvitationCallback implements OnInvitationReceivedListener {
    @Override // com.google.android.gms.games.multiplayer.OnInvitationReceivedListener
    public abstract void onInvitationReceived(@NonNull Invitation invitation);

    @Override // com.google.android.gms.games.multiplayer.OnInvitationReceivedListener
    public abstract void onInvitationRemoved(@NonNull String str);
}
