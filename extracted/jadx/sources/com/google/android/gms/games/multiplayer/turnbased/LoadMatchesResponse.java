package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.zzh;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class LoadMatchesResponse {
    private final InvitationBuffer zzpg;
    private final TurnBasedMatchBuffer zzph;
    private final TurnBasedMatchBuffer zzpi;
    private final TurnBasedMatchBuffer zzpj;

    public LoadMatchesResponse(Bundle bundle) {
        DataHolder dataHolderZza = zza(bundle, 0);
        if (dataHolderZza != null) {
            this.zzpg = new InvitationBuffer(dataHolderZza);
        } else {
            this.zzpg = null;
        }
        DataHolder dataHolderZza2 = zza(bundle, 1);
        if (dataHolderZza2 != null) {
            this.zzph = new TurnBasedMatchBuffer(dataHolderZza2);
        } else {
            this.zzph = null;
        }
        DataHolder dataHolderZza3 = zza(bundle, 2);
        if (dataHolderZza3 != null) {
            this.zzpi = new TurnBasedMatchBuffer(dataHolderZza3);
        } else {
            this.zzpi = null;
        }
        DataHolder dataHolderZza4 = zza(bundle, 3);
        if (dataHolderZza4 != null) {
            this.zzpj = new TurnBasedMatchBuffer(dataHolderZza4);
        } else {
            this.zzpj = null;
        }
    }

    private static DataHolder zza(Bundle bundle, int i) {
        String str;
        switch (i) {
            case 0:
                str = "TURN_STATUS_INVITED";
                break;
            case 1:
                str = "TURN_STATUS_MY_TURN";
                break;
            case 2:
                str = "TURN_STATUS_THEIR_TURN";
                break;
            case 3:
                str = "TURN_STATUS_COMPLETE";
                break;
            default:
                zzh.e("MatchTurnStatus", new StringBuilder(38).append("Unknown match turn status: ").append(i).toString());
                str = "TURN_STATUS_UNKNOWN";
                break;
        }
        if (bundle.containsKey(str)) {
            return (DataHolder) bundle.getParcelable(str);
        }
        return null;
    }

    @Deprecated
    public final void close() {
        release();
    }

    public final TurnBasedMatchBuffer getCompletedMatches() {
        return this.zzpj;
    }

    public final InvitationBuffer getInvitations() {
        return this.zzpg;
    }

    public final TurnBasedMatchBuffer getMyTurnMatches() {
        return this.zzph;
    }

    public final TurnBasedMatchBuffer getTheirTurnMatches() {
        return this.zzpi;
    }

    public final boolean hasData() {
        if (this.zzpg != null && this.zzpg.getCount() > 0) {
            return true;
        }
        if (this.zzph != null && this.zzph.getCount() > 0) {
            return true;
        }
        if (this.zzpi == null || this.zzpi.getCount() <= 0) {
            return this.zzpj != null && this.zzpj.getCount() > 0;
        }
        return true;
    }

    public final void release() {
        if (this.zzpg != null) {
            this.zzpg.release();
        }
        if (this.zzph != null) {
            this.zzph.release();
        }
        if (this.zzpi != null) {
            this.zzpi.release();
        }
        if (this.zzpj != null) {
            this.zzpj.release();
        }
    }
}
