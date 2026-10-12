package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@KeepForSdk
public final class Dependency {
    private final Class<?> zza;
    private final int zzb;
    private final int zzc;

    private Dependency(Class<?> cls, int i, int i2) {
        this.zza = (Class) Preconditions.checkNotNull(cls, "Null dependency anInterface.");
        this.zzb = i;
        this.zzc = i2;
    }

    @KeepForSdk
    public static Dependency optional(Class<?> cls) {
        return new Dependency(cls, 0, 0);
    }

    @KeepForSdk
    public static Dependency optionalProvider(Class<?> cls) {
        return new Dependency(cls, 0, 1);
    }

    @KeepForSdk
    public static Dependency required(Class<?> cls) {
        return new Dependency(cls, 1, 0);
    }

    @KeepForSdk
    public static Dependency requiredProvider(Class<?> cls) {
        return new Dependency(cls, 1, 1);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Dependency)) {
            return false;
        }
        Dependency dependency = (Dependency) obj;
        return this.zza == dependency.zza && this.zzb == dependency.zzb && this.zzc == dependency.zzc;
    }

    public final int hashCode() {
        return ((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb) * 1000003) ^ this.zzc;
    }

    public final String toString() {
        return "Dependency{anInterface=" + this.zza + ", required=" + (this.zzb == 1) + ", direct=" + (this.zzc == 0) + "}";
    }

    public final Class<?> zza() {
        return this.zza;
    }

    public final boolean zzb() {
        return this.zzb == 1;
    }

    public final boolean zzc() {
        return this.zzc == 0;
    }
}
