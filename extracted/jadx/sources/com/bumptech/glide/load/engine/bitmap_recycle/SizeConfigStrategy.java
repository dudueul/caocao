package com.bumptech.glide.load.engine.bitmap_recycle;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.bumptech.glide.util.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(19)
public class SizeConfigStrategy implements LruPoolStrategy {
    private static final int MAX_SIZE_MULTIPLE = 8;
    private static final Bitmap.Config[] ARGB_8888_IN_CONFIGS = {Bitmap.Config.ARGB_8888, null};
    private static final Bitmap.Config[] RGB_565_IN_CONFIGS = {Bitmap.Config.RGB_565};
    private static final Bitmap.Config[] ARGB_4444_IN_CONFIGS = {Bitmap.Config.ARGB_4444};
    private static final Bitmap.Config[] ALPHA_8_IN_CONFIGS = {Bitmap.Config.ALPHA_8};
    private final KeyPool keyPool = new KeyPool();
    private final GroupedLinkedMap<Key, Bitmap> groupedMap = new GroupedLinkedMap<>();
    private final Map<Bitmap.Config, NavigableMap<Integer, Integer>> sortedSizes = new HashMap();

    /* JADX INFO: renamed from: com.bumptech.glide.load.engine.bitmap_recycle.SizeConfigStrategy$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config = new int[Bitmap.Config.values().length];

        static {
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ALPHA_8.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    static final class Key implements Poolable {
        private Bitmap.Config config;
        private final KeyPool pool;
        private int size;

        public Key(KeyPool keyPool) {
            this.pool = keyPool;
        }

        Key(KeyPool keyPool, int i, Bitmap.Config config) {
            this(keyPool);
            init(i, config);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Key)) {
                return false;
            }
            Key key = (Key) obj;
            if (this.size != key.size) {
                return false;
            }
            if (this.config == null) {
                if (key.config != null) {
                    return false;
                }
            } else if (!this.config.equals(key.config)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.size * 31) + (this.config != null ? this.config.hashCode() : 0);
        }

        public void init(int i, Bitmap.Config config) {
            this.size = i;
            this.config = config;
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.Poolable
        public void offer() {
            this.pool.offer(this);
        }

        public String toString() {
            return SizeConfigStrategy.getBitmapString(this.size, this.config);
        }
    }

    static class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bumptech.glide.load.engine.bitmap_recycle.BaseKeyPool
        public Key create() {
            return new Key(this);
        }

        public Key get(int i, Bitmap.Config config) {
            Key key = get();
            key.init(i, config);
            return key;
        }
    }

    private void decrementBitmapOfSize(Integer num, Bitmap.Config config) {
        NavigableMap<Integer, Integer> sizesForConfig = getSizesForConfig(config);
        Integer num2 = (Integer) sizesForConfig.get(num);
        if (num2.intValue() == 1) {
            sizesForConfig.remove(num);
        } else {
            sizesForConfig.put(num, Integer.valueOf(num2.intValue() - 1));
        }
    }

    private Key findBestKey(Key key, int i, Bitmap.Config config) {
        for (Bitmap.Config config2 : getInConfigs(config)) {
            Integer numCeilingKey = getSizesForConfig(config2).ceilingKey(Integer.valueOf(i));
            if (numCeilingKey != null && numCeilingKey.intValue() <= i * 8) {
                if (numCeilingKey.intValue() == i) {
                    if (config2 == null) {
                        if (config == null) {
                            return key;
                        }
                    } else if (config2.equals(config)) {
                        return key;
                    }
                }
                this.keyPool.offer(key);
                return this.keyPool.get(numCeilingKey.intValue(), config2);
            }
        }
        return key;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getBitmapString(int i, Bitmap.Config config) {
        return "[" + i + "](" + config + ")";
    }

    private static Bitmap.Config[] getInConfigs(Bitmap.Config config) {
        switch (AnonymousClass1.$SwitchMap$android$graphics$Bitmap$Config[config.ordinal()]) {
            case 1:
                return ARGB_8888_IN_CONFIGS;
            case 2:
                return RGB_565_IN_CONFIGS;
            case 3:
                return ARGB_4444_IN_CONFIGS;
            case 4:
                return ALPHA_8_IN_CONFIGS;
            default:
                return new Bitmap.Config[]{config};
        }
    }

    private NavigableMap<Integer, Integer> getSizesForConfig(Bitmap.Config config) {
        NavigableMap<Integer, Integer> navigableMap = this.sortedSizes.get(config);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.sortedSizes.put(config, treeMap);
        return treeMap;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public Bitmap get(int i, int i2, Bitmap.Config config) {
        int bitmapByteSize = Util.getBitmapByteSize(i, i2, config);
        Bitmap bitmap = this.groupedMap.get(findBestKey(this.keyPool.get(bitmapByteSize, config), bitmapByteSize, config));
        if (bitmap != null) {
            decrementBitmapOfSize(Integer.valueOf(Util.getBitmapByteSize(bitmap)), bitmap.getConfig());
            bitmap.reconfigure(i, i2, bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888);
        }
        return bitmap;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public String logBitmap(int i, int i2, Bitmap.Config config) {
        return getBitmapString(Util.getBitmapByteSize(i, i2, config), config);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public String logBitmap(Bitmap bitmap) {
        return getBitmapString(Util.getBitmapByteSize(bitmap), bitmap.getConfig());
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public void put(Bitmap bitmap) {
        Key key = this.keyPool.get(Util.getBitmapByteSize(bitmap), bitmap.getConfig());
        this.groupedMap.put(key, bitmap);
        NavigableMap<Integer, Integer> sizesForConfig = getSizesForConfig(bitmap.getConfig());
        Integer num = (Integer) sizesForConfig.get(Integer.valueOf(key.size));
        sizesForConfig.put(Integer.valueOf(key.size), Integer.valueOf(num == null ? 1 : num.intValue() + 1));
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruPoolStrategy
    public Bitmap removeLast() {
        Bitmap bitmapRemoveLast = this.groupedMap.removeLast();
        if (bitmapRemoveLast != null) {
            decrementBitmapOfSize(Integer.valueOf(Util.getBitmapByteSize(bitmapRemoveLast)), bitmapRemoveLast.getConfig());
        }
        return bitmapRemoveLast;
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append("SizeConfigStrategy{groupedMap=").append(this.groupedMap).append(", sortedSizes=(");
        for (Map.Entry<Bitmap.Config, NavigableMap<Integer, Integer>> entry : this.sortedSizes.entrySet()) {
            sbAppend.append(entry.getKey()).append('[').append(entry.getValue()).append("], ");
        }
        if (!this.sortedSizes.isEmpty()) {
            sbAppend.replace(sbAppend.length() - 2, sbAppend.length(), "");
        }
        return sbAppend.append(")}").toString();
    }
}
