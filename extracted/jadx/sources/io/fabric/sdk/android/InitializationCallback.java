package io.fabric.sdk.android;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface InitializationCallback<T> {
    public static final InitializationCallback EMPTY = new Empty();

    public static class Empty implements InitializationCallback<Object> {
        private Empty() {
        }

        @Override // io.fabric.sdk.android.InitializationCallback
        public void failure(Exception exc) {
        }

        @Override // io.fabric.sdk.android.InitializationCallback
        public void success(Object obj) {
        }
    }

    void failure(Exception exc);

    void success(T t);
}
