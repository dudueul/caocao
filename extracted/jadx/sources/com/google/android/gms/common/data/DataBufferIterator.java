package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DataBufferIterator<T> implements Iterator<T> {
    protected final DataBuffer<T> mDataBuffer;
    protected int mPosition = -1;

    public DataBufferIterator(DataBuffer<T> dataBuffer) {
        this.mDataBuffer = (DataBuffer) Preconditions.checkNotNull(dataBuffer);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.mPosition < this.mDataBuffer.getCount() + (-1);
    }

    @Override // java.util.Iterator
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException(new StringBuilder(46).append("Cannot advance the iterator beyond ").append(this.mPosition).toString());
        }
        DataBuffer<T> dataBuffer = this.mDataBuffer;
        int i = this.mPosition + 1;
        this.mPosition = i;
        return dataBuffer.get(i);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
