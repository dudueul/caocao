package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
interface EngineJobListener {
    void onEngineJobCancelled(EngineJob engineJob, Key key);

    void onEngineJobComplete(Key key, EngineResource<?> engineResource);
}
