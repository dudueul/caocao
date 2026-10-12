package com.google.android.gms.games.multiplayer.realtime;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzg implements zzh {
    private final RoomUpdateCallback zzou;
    private final RoomStatusUpdateCallback zzov;
    private final OnRealTimeMessageReceivedListener zzpf;

    public zzg(@NonNull RoomUpdateCallback roomUpdateCallback, @Nullable RoomStatusUpdateCallback roomStatusUpdateCallback, @Nullable OnRealTimeMessageReceivedListener onRealTimeMessageReceivedListener) {
        this.zzou = roomUpdateCallback;
        this.zzov = roomStatusUpdateCallback;
        this.zzpf = onRealTimeMessageReceivedListener;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onConnectedToRoom(@Nullable Room room) {
        if (this.zzov != null) {
            this.zzov.onConnectedToRoom(room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onDisconnectedFromRoom(@Nullable Room room) {
        if (this.zzov != null) {
            this.zzov.onDisconnectedFromRoom(room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public final void onJoinedRoom(int i, @Nullable Room room) {
        if (this.zzou != null) {
            this.zzou.onJoinedRoom(i, room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public final void onLeftRoom(int i, @NonNull String str) {
        if (this.zzou != null) {
            this.zzou.onLeftRoom(i, str);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onP2PConnected(@NonNull String str) {
        if (this.zzov != null) {
            this.zzov.onP2PConnected(str);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onP2PDisconnected(@NonNull String str) {
        if (this.zzov != null) {
            this.zzov.onP2PDisconnected(str);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeerDeclined(@Nullable Room room, @NonNull List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeerDeclined(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeerInvitedToRoom(@Nullable Room room, @NonNull List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeerInvitedToRoom(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeerJoined(@Nullable Room room, @NonNull List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeerJoined(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeerLeft(@Nullable Room room, @NonNull List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeerLeft(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeersConnected(@Nullable Room room, @NonNull List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeersConnected(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeersDisconnected(@Nullable Room room, @NonNull List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeersDisconnected(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.OnRealTimeMessageReceivedListener, com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener
    public final void onRealTimeMessageReceived(@NonNull RealTimeMessage realTimeMessage) {
        if (this.zzpf != null) {
            this.zzpf.onRealTimeMessageReceived(realTimeMessage);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onRoomAutoMatching(@Nullable Room room) {
        if (this.zzov != null) {
            this.zzov.onRoomAutoMatching(room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public final void onRoomConnected(int i, @Nullable Room room) {
        if (this.zzou != null) {
            this.zzou.onRoomConnected(i, room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onRoomConnecting(@Nullable Room room) {
        if (this.zzov != null) {
            this.zzov.onRoomConnecting(room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public final void onRoomCreated(int i, @Nullable Room room) {
        if (this.zzou != null) {
            this.zzou.onRoomCreated(i, room);
        }
    }
}
