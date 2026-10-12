package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.internal.zzd;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SafeParcelable.Class(creator = "VideoCapabilitiesCreator")
@SafeParcelable.Reserved({1000})
public final class VideoCapabilities extends zzd {
    public static final Parcelable.Creator<VideoCapabilities> CREATOR = new zza();

    @SafeParcelable.Field(getter = "isCameraSupported", id = 1)
    private final boolean zzru;

    @SafeParcelable.Field(getter = "isMicSupported", id = 2)
    private final boolean zzrv;

    @SafeParcelable.Field(getter = "isWriteStorageSupported", id = 3)
    private final boolean zzrw;

    @SafeParcelable.Field(getter = "getSupportedCaptureModes", id = 4)
    private final boolean[] zzrx;

    @SafeParcelable.Field(getter = "getSupportedQualityLevels", id = 5)
    private final boolean[] zzry;

    @SafeParcelable.Constructor
    public VideoCapabilities(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) boolean z2, @SafeParcelable.Param(id = 3) boolean z3, @SafeParcelable.Param(id = 4) boolean[] zArr, @SafeParcelable.Param(id = 5) boolean[] zArr2) {
        this.zzru = z;
        this.zzrv = z2;
        this.zzrw = z3;
        this.zzrx = zArr;
        this.zzry = zArr2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof VideoCapabilities)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        VideoCapabilities videoCapabilities = (VideoCapabilities) obj;
        return Objects.equal(videoCapabilities.getSupportedCaptureModes(), getSupportedCaptureModes()) && Objects.equal(videoCapabilities.getSupportedQualityLevels(), getSupportedQualityLevels()) && Objects.equal(Boolean.valueOf(videoCapabilities.isCameraSupported()), Boolean.valueOf(isCameraSupported())) && Objects.equal(Boolean.valueOf(videoCapabilities.isMicSupported()), Boolean.valueOf(isMicSupported())) && Objects.equal(Boolean.valueOf(videoCapabilities.isWriteStorageSupported()), Boolean.valueOf(isWriteStorageSupported()));
    }

    public final boolean[] getSupportedCaptureModes() {
        return this.zzrx;
    }

    public final boolean[] getSupportedQualityLevels() {
        return this.zzry;
    }

    public final int hashCode() {
        return Objects.hashCode(getSupportedCaptureModes(), getSupportedQualityLevels(), Boolean.valueOf(isCameraSupported()), Boolean.valueOf(isMicSupported()), Boolean.valueOf(isWriteStorageSupported()));
    }

    public final boolean isCameraSupported() {
        return this.zzru;
    }

    public final boolean isFullySupported(int i, int i2) {
        return this.zzru && this.zzrv && this.zzrw && supportsCaptureMode(i) && supportsQualityLevel(i2);
    }

    public final boolean isMicSupported() {
        return this.zzrv;
    }

    public final boolean isWriteStorageSupported() {
        return this.zzrw;
    }

    public final boolean supportsCaptureMode(int i) {
        Preconditions.checkState(VideoConfiguration.isValidCaptureMode(i, false));
        return this.zzrx[i];
    }

    public final boolean supportsQualityLevel(int i) {
        Preconditions.checkState(VideoConfiguration.isValidQualityLevel(i, false));
        return this.zzry[i];
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("SupportedCaptureModes", getSupportedCaptureModes()).add("SupportedQualityLevels", getSupportedQualityLevels()).add("CameraSupported", Boolean.valueOf(isCameraSupported())).add("MicSupported", Boolean.valueOf(isMicSupported())).add("StorageWriteSupported", Boolean.valueOf(isWriteStorageSupported())).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, isCameraSupported());
        SafeParcelWriter.writeBoolean(parcel, 2, isMicSupported());
        SafeParcelWriter.writeBoolean(parcel, 3, isWriteStorageSupported());
        SafeParcelWriter.writeBooleanArray(parcel, 4, getSupportedCaptureModes(), false);
        SafeParcelWriter.writeBooleanArray(parcel, 5, getSupportedQualityLevels(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
