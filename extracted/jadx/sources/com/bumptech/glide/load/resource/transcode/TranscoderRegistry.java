package com.bumptech.glide.load.resource.transcode;

import com.bumptech.glide.util.MultiClassKey;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TranscoderRegistry {
    private static final MultiClassKey GET_KEY = new MultiClassKey();
    private final Map<MultiClassKey, ResourceTranscoder<?, ?>> factories = new HashMap();

    public <Z, R> ResourceTranscoder<Z, R> get(Class<Z> cls, Class<R> cls2) {
        ResourceTranscoder<Z, R> resourceTranscoder;
        if (cls.equals(cls2)) {
            return UnitTranscoder.get();
        }
        synchronized (GET_KEY) {
            GET_KEY.set(cls, cls2);
            resourceTranscoder = (ResourceTranscoder) this.factories.get(GET_KEY);
        }
        if (resourceTranscoder == null) {
            throw new IllegalArgumentException("No transcoder registered for " + cls + " and " + cls2);
        }
        return resourceTranscoder;
    }

    public <Z, R> void register(Class<Z> cls, Class<R> cls2, ResourceTranscoder<Z, R> resourceTranscoder) {
        this.factories.put(new MultiClassKey(cls, cls2), resourceTranscoder);
    }
}
