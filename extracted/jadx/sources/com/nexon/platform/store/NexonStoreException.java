package com.nexon.platform.store;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NexonStoreException extends RuntimeException {
    static final long serialVersionUID = 1;

    public NexonStoreException() {
    }

    public NexonStoreException(String str) {
        super(str);
    }

    public NexonStoreException(String str, Throwable th) {
        super(str, th);
    }

    public NexonStoreException(String str, Object... objArr) {
        this(String.format(str, objArr));
    }

    public NexonStoreException(Throwable th) {
        super(th);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return getMessage();
    }
}
