package com.google.android.gms.common.data;

import java.util.Comparator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzb implements Comparator<Integer> {
    private final /* synthetic */ Comparator zzom;
    private final /* synthetic */ SortedDataBuffer zzon;

    zzb(SortedDataBuffer sortedDataBuffer, Comparator comparator) {
        this.zzon = sortedDataBuffer;
        this.zzom = comparator;
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Integer num, Integer num2) {
        return this.zzom.compare(this.zzon.zzok.get(num.intValue()), this.zzon.zzok.get(num2.intValue()));
    }
}
