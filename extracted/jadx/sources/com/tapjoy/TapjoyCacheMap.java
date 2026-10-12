package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TapjoyCacheMap extends ConcurrentHashMap {
    private Context a;
    private int b;

    public TapjoyCacheMap(Context context, int i) {
        this.b = -1;
        this.a = context;
        this.b = i;
    }

    private String a() {
        String str;
        long j = -1;
        String str2 = "";
        for (Map.Entry entry : entrySet()) {
            long timestampInSeconds = ((TapjoyCachedAssetData) entry.getValue()).getTimestampInSeconds();
            if (j == 0 || timestampInSeconds < j) {
                str = (String) entry.getKey();
                j = timestampInSeconds;
            } else {
                str = str2;
            }
            str2 = str;
        }
        return str2;
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public TapjoyCachedAssetData put(String str, TapjoyCachedAssetData tapjoyCachedAssetData) {
        TapjoyLog.d("TapjoyCacheMap", "TapjoyCacheMap::put() -- key: " + str + " assetURL: " + tapjoyCachedAssetData.getAssetURL());
        if (tapjoyCachedAssetData == null || tapjoyCachedAssetData.getTimeOfDeathInSeconds() <= System.currentTimeMillis() / 1000) {
            return null;
        }
        if (size() == this.b) {
            remove((Object) a());
        }
        SharedPreferences.Editor editorEdit = this.a.getSharedPreferences(TapjoyConstants.PREF_TAPJOY_CACHE, 0).edit();
        editorEdit.putString(tapjoyCachedAssetData.getLocalFilePath(), tapjoyCachedAssetData.toRawJSONString());
        editorEdit.apply();
        return (TapjoyCachedAssetData) super.put(str, tapjoyCachedAssetData);
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public TapjoyCachedAssetData remove(Object obj) {
        if (!containsKey(obj)) {
            return null;
        }
        SharedPreferences.Editor editorEdit = this.a.getSharedPreferences(TapjoyConstants.PREF_TAPJOY_CACHE, 0).edit();
        editorEdit.remove(((TapjoyCachedAssetData) get(obj)).getLocalFilePath());
        editorEdit.apply();
        String localFilePath = ((TapjoyCachedAssetData) get(obj)).getLocalFilePath();
        if (localFilePath != null && localFilePath.length() > 0) {
            TapjoyUtil.deleteFileOrDirectory(new File(localFilePath));
        }
        TapjoyLog.d("TapjoyCacheMap", "TapjoyCacheMap::remove() -- key: " + obj);
        return (TapjoyCachedAssetData) super.remove(obj);
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.Map, java.util.concurrent.ConcurrentMap
    public TapjoyCachedAssetData replace(String str, TapjoyCachedAssetData tapjoyCachedAssetData) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(String str, TapjoyCachedAssetData tapjoyCachedAssetData, TapjoyCachedAssetData tapjoyCachedAssetData2) {
        throw new UnsupportedOperationException();
    }
}
