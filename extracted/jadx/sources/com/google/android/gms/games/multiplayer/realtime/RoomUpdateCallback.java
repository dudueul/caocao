package com.google.android.gms.games.multiplayer.realtime;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class RoomUpdateCallback implements RoomUpdateListener {
    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public abstract void onJoinedRoom(int i, @Nullable Room room);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public abstract void onLeftRoom(int i, @NonNull String str);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public abstract void onRoomConnected(int i, @Nullable Room room);

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public abstract void onRoomCreated(int i, @Nullable Room room);
}
