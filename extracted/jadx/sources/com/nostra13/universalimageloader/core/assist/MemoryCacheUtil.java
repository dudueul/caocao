package com.nostra13.universalimageloader.core.assist;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.MemoryCacheAware;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class MemoryCacheUtil {
    private static final String URI_AND_SIZE_SEPARATOR = "_";
    private static final String WIDTH_AND_HEIGHT_SEPARATOR = "x";

    private MemoryCacheUtil() {
    }

    public static Comparator<String> createFuzzyKeyComparator() {
        return new Comparator<String>() { // from class: com.nostra13.universalimageloader.core.assist.MemoryCacheUtil.1
            @Override // java.util.Comparator
            public int compare(String str, String str2) {
                return str.substring(0, str.lastIndexOf("_")).compareTo(str2.substring(0, str2.lastIndexOf("_")));
            }
        };
    }

    public static List<String> findCacheKeysForImageUri(String str, MemoryCacheAware<String, Bitmap> memoryCacheAware) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : memoryCacheAware.keys()) {
            if (str2.startsWith(str)) {
                arrayList.add(str2);
            }
        }
        return arrayList;
    }

    public static List<Bitmap> findCachedBitmapsForImageUri(String str, MemoryCacheAware<String, Bitmap> memoryCacheAware) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : memoryCacheAware.keys()) {
            if (str2.startsWith(str)) {
                arrayList.add(memoryCacheAware.get(str2));
            }
        }
        return arrayList;
    }

    public static String generateKey(String str, ImageSize imageSize) {
        return str + "_" + imageSize.getWidth() + WIDTH_AND_HEIGHT_SEPARATOR + imageSize.getHeight();
    }

    public static void removeFromCache(String str, MemoryCacheAware<String, Bitmap> memoryCacheAware) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : memoryCacheAware.keys()) {
            if (str2.startsWith(str)) {
                arrayList.add(str2);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            memoryCacheAware.remove((String) it.next());
        }
    }
}
