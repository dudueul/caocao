package io.fabric.sdk.android.services.events;

import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface EventTransform<T> {
    byte[] toBytes(T t) throws IOException;
}
