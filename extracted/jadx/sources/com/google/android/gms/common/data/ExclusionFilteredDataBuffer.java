package com.google.android.gms.common.data;

import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@VisibleForTesting
public final class ExclusionFilteredDataBuffer<T> extends FilteredDataBuffer<T> implements DataBufferObserver.Observable, ExclusionFilterable {
    private AbstractDataBuffer<T> zzoc;
    private final String zzod;
    private final HashSet<Integer> zzoe;
    private DataBufferObserverSet zzof;

    public ExclusionFilteredDataBuffer(AbstractDataBuffer<T> abstractDataBuffer, String str) {
        super(abstractDataBuffer);
        this.zzoe = new HashSet<>();
        this.zzoc = abstractDataBuffer;
        this.zzod = str;
        this.zzof = new DataBufferObserverSet();
    }

    private final ArrayList<Integer> zza(String str, ArrayList<Integer> arrayList) {
        int i;
        int i2;
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        if (arrayList != null) {
            arrayList.clear();
        }
        DataHolder dataHolder = this.zzoc.mDataHolder;
        String str2 = this.zzod;
        boolean z = this.zzoc instanceof EntityBuffer;
        int count = this.zzoc.getCount();
        int i3 = 0;
        int i4 = 0;
        while (i3 < count) {
            int iZzi = z ? ((EntityBuffer) this.zzoc).zzi(i3) : i3;
            String string = dataHolder.getString(str2, iZzi, dataHolder.getWindowIndex(iZzi));
            if (arrayList == null) {
                i = i4;
                i2 = i4;
            } else if (this.zzoe.contains(Integer.valueOf(i3))) {
                i = (-i4) - 1;
                i2 = i4;
            } else {
                i2 = i4 + 1;
                i = i4;
            }
            if (!TextUtils.isEmpty(string) && string.equals(str)) {
                arrayList2.add(Integer.valueOf(i3));
                if (arrayList != null) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            i3++;
            i4 = i2;
        }
        return arrayList2;
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver.Observable
    public final void addObserver(DataBufferObserver dataBufferObserver) {
        this.zzof.addObserver(dataBufferObserver);
    }

    @Override // com.google.android.gms.common.data.ExclusionFilterable
    public final void clearFilters() {
        int i;
        if (!this.zzof.hasObservers()) {
            this.zzoe.clear();
            return;
        }
        int size = this.zzoe.size();
        Integer[] numArr = (Integer[]) this.zzoe.toArray(new Integer[size]);
        Arrays.sort(numArr);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < size) {
            int iIntValue = numArr[i2].intValue();
            this.zzoe.remove(Integer.valueOf(iIntValue));
            if (i3 == 0) {
                i = 1;
            } else if (iIntValue == i4 + i3) {
                i = i3 + 1;
                iIntValue = i4;
            } else {
                this.zzof.onDataRangeRemoved(i4, i3);
                i = 1;
            }
            i2++;
            i3 = i;
            i4 = iIntValue;
        }
        if (i3 > 0) {
            this.zzof.onDataRangeRemoved(i4, i3);
        }
    }

    @Override // com.google.android.gms.common.data.FilteredDataBuffer
    public final int computeRealPosition(int i) {
        if (this.zzoe.isEmpty()) {
            return i;
        }
        if (i < 0 || i >= getCount()) {
            throw new IllegalArgumentException(new StringBuilder(53).append("Position ").append(i).append(" is out of bounds for this buffer").toString());
        }
        int count = this.mDataBuffer.getCount();
        int i2 = 0;
        for (int i3 = 0; i3 < count; i3++) {
            if (!this.zzoe.contains(Integer.valueOf(i3))) {
                if (i2 == i) {
                    return i3;
                }
                i2++;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.common.data.ExclusionFilterable
    public final void filterOut(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList<Integer> arrayList = this.zzof.hasObservers() ? new ArrayList<>() : null;
        ArrayList<Integer> arrayListZza = zza(str, arrayList);
        if (!this.zzof.hasObservers()) {
            this.zzoe.addAll(arrayListZza);
            return;
        }
        int size = arrayListZza.size() - 1;
        int i2 = 0;
        int i3 = 0;
        while (size >= 0) {
            int iIntValue = arrayList.get(size).intValue();
            if (iIntValue < 0) {
                i = i2;
                iIntValue = i3;
            } else {
                this.zzoe.add(Integer.valueOf(arrayListZza.get(size).intValue()));
                if (i2 == 0) {
                    i = 1;
                } else if (iIntValue == i3 - 1) {
                    iIntValue = i3 - 1;
                    i = i2 + 1;
                } else {
                    this.zzof.onDataRangeRemoved(i3, i2);
                    i = 1;
                }
            }
            size--;
            i2 = i;
            i3 = iIntValue;
        }
        if (i2 > 0) {
            this.zzof.onDataRangeRemoved(i3, i2);
        }
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public final int getCount() {
        return this.mDataBuffer.getCount() - this.zzoe.size();
    }

    @Override // com.google.android.gms.common.data.FilteredDataBuffer, com.google.android.gms.common.data.DataBuffer, com.google.android.gms.common.api.Releasable
    public final void release() {
        super.release();
        this.zzof.clear();
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver.Observable
    public final void removeObserver(DataBufferObserver dataBufferObserver) {
        this.zzof.removeObserver(dataBufferObserver);
    }

    @Override // com.google.android.gms.common.data.ExclusionFilterable
    public final void unfilter(String str) {
        int i;
        int i2;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList<Integer> arrayList = this.zzof.hasObservers() ? new ArrayList<>() : null;
        ArrayList<Integer> arrayListZza = zza(str, arrayList);
        if (!this.zzof.hasObservers()) {
            this.zzoe.removeAll(arrayListZza);
            return;
        }
        int size = arrayListZza.size() - 1;
        int i3 = 0;
        int i4 = 0;
        while (size >= 0) {
            int iIntValue = arrayList.get(size).intValue();
            if (iIntValue < 0) {
                this.zzoe.remove(Integer.valueOf(arrayListZza.get(size).intValue()));
                i2 = (-iIntValue) - 1;
                if (i3 == 0) {
                    i = 1;
                } else if (i2 == i4) {
                    i = i3 + 1;
                    i2 = i4;
                } else {
                    this.zzof.onDataRangeInserted(i4, i3);
                    i = 1;
                }
            } else {
                i = i3;
                i2 = i4;
            }
            size--;
            i3 = i;
            i4 = i2;
        }
        if (i3 > 0) {
            this.zzof.onDataRangeInserted(i4, i3);
        }
    }
}
