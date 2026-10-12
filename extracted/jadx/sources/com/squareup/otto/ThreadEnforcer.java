package com.squareup.otto;

import android.os.Looper;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface ThreadEnforcer {
    public static final ThreadEnforcer ANY = new ThreadEnforcer() { // from class: com.squareup.otto.ThreadEnforcer.1
        @Override // com.squareup.otto.ThreadEnforcer
        public void enforce(Bus bus) {
        }
    };
    public static final ThreadEnforcer MAIN = new ThreadEnforcer() { // from class: com.squareup.otto.ThreadEnforcer.2
        @Override // com.squareup.otto.ThreadEnforcer
        public void enforce(Bus bus) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw new IllegalStateException("Event bus " + bus + " accessed from non-main thread " + Looper.myLooper());
            }
        }
    };

    void enforce(Bus bus);
}
