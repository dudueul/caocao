package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class SingleRefDataBufferIterator<T> extends DataBufferIterator<T> {
    private T zzoj;

    public SingleRefDataBufferIterator(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    @Override // com.google.android.gms.common.data.DataBufferIterator, java.util.Iterator
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException(new StringBuilder(46).append("Cannot advance the iterator beyond ").append(this.mPosition).toString());
        }
        this.mPosition++;
        if (this.mPosition == 0) {
            this.zzoj = this.mDataBuffer.get(0);
            if (!(this.zzoj instanceof DataBufferRef)) {
                String strValueOf = String.valueOf(this.zzoj.getClass());
                throw new IllegalStateException(new StringBuilder(String.valueOf(strValueOf).length() + 44).append("DataBuffer reference of type ").append(strValueOf).append(" is not movable").toString());
            }
        } else {
            ((DataBufferRef) this.zzoj).setDataRow(this.mPosition);
        }
        return this.zzoj;
    }
}
