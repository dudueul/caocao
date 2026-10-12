package com.unity3d.player;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class GoogleVrApi {
    private static AtomicReference a = new AtomicReference();

    private GoogleVrApi() {
    }

    static void a() {
        a.set(null);
    }

    static void a(f fVar) {
        a.compareAndSet(null, new GoogleVrProxy(fVar));
    }

    static GoogleVrProxy b() {
        return (GoogleVrProxy) a.get();
    }

    public static GoogleVrVideo getGoogleVrVideo() {
        return (GoogleVrVideo) a.get();
    }
}
