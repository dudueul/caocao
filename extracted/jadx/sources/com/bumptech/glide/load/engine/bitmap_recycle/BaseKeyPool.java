package com.bumptech.glide.load.engine.bitmap_recycle;

import com.bumptech.glide.load.engine.bitmap_recycle.Poolable;
import com.bumptech.glide.util.Util;
import java.util.Queue;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
abstract class BaseKeyPool<T extends Poolable> {
    private static final int MAX_SIZE = 20;
    private final Queue<T> keyPool = Util.createQueue(20);

    BaseKeyPool() {
    }

    protected abstract T create();

    protected T get() {
        T tPoll = this.keyPool.poll();
        return tPoll == null ? (T) create() : tPoll;
    }

    public void offer(T t) {
        if (this.keyPool.size() < 20) {
            this.keyPool.offer(t);
        }
    }
}
