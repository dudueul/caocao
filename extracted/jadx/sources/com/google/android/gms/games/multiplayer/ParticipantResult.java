package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SafeParcelable.Class(creator = "ParticipantResultCreator")
@SafeParcelable.Reserved({1000})
public final class ParticipantResult extends com.google.android.gms.games.internal.zzd {
    public static final Parcelable.Creator<ParticipantResult> CREATOR = new zzd();
    public static final int MATCH_RESULT_DISAGREED = 5;
    public static final int MATCH_RESULT_DISCONNECT = 4;
    public static final int MATCH_RESULT_LOSS = 1;
    public static final int MATCH_RESULT_NONE = 3;
    public static final int MATCH_RESULT_TIE = 2;
    public static final int MATCH_RESULT_UNINITIALIZED = -1;
    public static final int MATCH_RESULT_WIN = 0;
    public static final int PLACING_UNINITIALIZED = -1;

    @SafeParcelable.Field(getter = "getParticipantId", id = 1)
    private final String zzhl;

    @SafeParcelable.Field(getter = "getResult", id = 2)
    private final int zzom;

    @SafeParcelable.Field(getter = "getPlacing", id = 3)
    private final int zzon;

    @SafeParcelable.Constructor
    public ParticipantResult(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) int i2) {
        boolean z;
        this.zzhl = (String) Preconditions.checkNotNull(str);
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                z = true;
                break;
            default:
                z = false;
                break;
        }
        Preconditions.checkState(z);
        this.zzom = i;
        this.zzon = i2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ParticipantResult)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ParticipantResult participantResult = (ParticipantResult) obj;
        return participantResult.getPlacing() == getPlacing() && participantResult.getResult() == getResult() && Objects.equal(participantResult.getParticipantId(), getParticipantId());
    }

    public final String getParticipantId() {
        return this.zzhl;
    }

    public final int getPlacing() {
        return this.zzon;
    }

    public final int getResult() {
        return this.zzom;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(getPlacing()), Integer.valueOf(getResult()), getParticipantId());
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getParticipantId(), false);
        SafeParcelWriter.writeInt(parcel, 2, getResult());
        SafeParcelWriter.writeInt(parcel, 3, getPlacing());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
