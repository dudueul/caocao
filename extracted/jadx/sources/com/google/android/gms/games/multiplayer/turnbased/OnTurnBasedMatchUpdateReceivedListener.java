package com.google.android.gms.games.multiplayer.turnbased;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Deprecated
public interface OnTurnBasedMatchUpdateReceivedListener {
    void onTurnBasedMatchReceived(TurnBasedMatch turnBasedMatch);

    void onTurnBasedMatchRemoved(String str);
}
