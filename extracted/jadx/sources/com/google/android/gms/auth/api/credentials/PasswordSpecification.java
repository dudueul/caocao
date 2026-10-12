package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@SafeParcelable.Class(creator = "PasswordSpecificationCreator")
@SafeParcelable.Reserved({1000})
public final class PasswordSpecification extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<PasswordSpecification> CREATOR = new zzm();
    public static final PasswordSpecification zzdg = new zzd().zzd(12, 16).zze("abcdefghijkmnopqrstxyzABCDEFGHJKLMNPQRSTXY3456789").zzd("abcdefghijkmnopqrstxyz", 1).zzd("ABCDEFGHJKLMNPQRSTXY", 1).zzd("3456789", 1).zzj();
    private static final PasswordSpecification zzdh = new zzd().zzd(12, 16).zze("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890").zzd("abcdefghijklmnopqrstuvwxyz", 1).zzd("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1).zzd("1234567890", 1).zzj();

    @VisibleForTesting
    @SafeParcelable.Field(id = 1)
    private final String zzdi;

    @VisibleForTesting
    @SafeParcelable.Field(id = 2)
    private final List<String> zzdj;

    @VisibleForTesting
    @SafeParcelable.Field(id = 3)
    private final List<Integer> zzdk;

    @VisibleForTesting
    @SafeParcelable.Field(id = 4)
    private final int zzdl;

    @VisibleForTesting
    @SafeParcelable.Field(id = 5)
    private final int zzdm;
    private final int[] zzdn;
    private final Random zzdo;

    public static class zzd {
        private final TreeSet<Character> zzdp = new TreeSet<>();
        private final List<String> zzdj = new ArrayList();
        private final List<Integer> zzdk = new ArrayList();
        private int zzdl = 12;
        private int zzdm = 16;

        private static TreeSet<Character> zzd(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                throw new zze(String.valueOf(str2).concat(" cannot be null or empty"));
            }
            TreeSet<Character> treeSet = new TreeSet<>();
            for (char c : str.toCharArray()) {
                if (PasswordSpecification.zze(c, 32, 126)) {
                    throw new zze(String.valueOf(str2).concat(" must only contain ASCII printable characters"));
                }
                treeSet.add(Character.valueOf(c));
            }
            return treeSet;
        }

        public final zzd zzd(int i, int i2) {
            this.zzdl = 12;
            this.zzdm = 16;
            return this;
        }

        public final zzd zzd(@NonNull String str, int i) {
            this.zzdj.add(PasswordSpecification.zzd(zzd(str, "requiredChars")));
            this.zzdk.add(1);
            return this;
        }

        public final zzd zze(@NonNull String str) {
            this.zzdp.addAll(zzd(str, "allowedChars"));
            return this;
        }

        public final PasswordSpecification zzj() {
            if (this.zzdp.isEmpty()) {
                throw new zze("no allowed characters specified");
            }
            Iterator<Integer> it = this.zzdk.iterator();
            int iIntValue = 0;
            while (it.hasNext()) {
                iIntValue = it.next().intValue() + iIntValue;
            }
            if (iIntValue > this.zzdm) {
                throw new zze("required character count cannot be greater than the max password size");
            }
            boolean[] zArr = new boolean[95];
            Iterator<String> it2 = this.zzdj.iterator();
            while (it2.hasNext()) {
                for (char c : it2.next().toCharArray()) {
                    if (zArr[c - ' ']) {
                        throw new zze(new StringBuilder(58).append("character ").append(c).append(" occurs in more than one required character set").toString());
                    }
                    zArr[c - ' '] = true;
                }
            }
            return new PasswordSpecification(PasswordSpecification.zzd(this.zzdp), this.zzdj, this.zzdk, this.zzdl, this.zzdm);
        }
    }

    public static class zze extends Error {
        public zze(String str) {
            super(str);
        }
    }

    @SafeParcelable.Constructor
    PasswordSpecification(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) List<String> list, @SafeParcelable.Param(id = 3) List<Integer> list2, @SafeParcelable.Param(id = 4) int i, @SafeParcelable.Param(id = 5) int i2) {
        this.zzdi = str;
        this.zzdj = Collections.unmodifiableList(list);
        this.zzdk = Collections.unmodifiableList(list2);
        this.zzdl = i;
        this.zzdm = i2;
        int[] iArr = new int[95];
        Arrays.fill(iArr, -1);
        Iterator<String> it = this.zzdj.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            for (char c : it.next().toCharArray()) {
                iArr[c - ' '] = i3;
            }
            i3++;
        }
        this.zzdn = iArr;
        this.zzdo = new SecureRandom();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String zzd(Collection<Character> collection) {
        char[] cArr = new char[collection.size()];
        int i = 0;
        Iterator<Character> it = collection.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return new String(cArr);
            }
            i = i2 + 1;
            cArr[i2] = it.next().charValue();
        }
    }

    private static boolean zzd(int i, int i2, int i3) {
        return i < 32 || i > 126;
    }

    static /* synthetic */ boolean zze(int i, int i2, int i3) {
        return zzd(i, 32, 126);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzdi, false);
        SafeParcelWriter.writeStringList(parcel, 2, this.zzdj, false);
        SafeParcelWriter.writeIntegerList(parcel, 3, this.zzdk, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzdl);
        SafeParcelWriter.writeInt(parcel, 5, this.zzdm);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
