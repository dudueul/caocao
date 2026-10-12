package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;
import com.tapjoy.internal.fj;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TapjoyCache {
    public static final String CACHE_DIRECTORY_NAME = "Tapjoy/Cache/";
    public static final int CACHE_LIMIT = -1;
    private Context b;
    private TapjoyCacheMap c;
    private Vector d;
    private ExecutorService e;
    private File f;
    public static boolean unit_test_mode = false;
    private static TapjoyCache a = null;

    public class CacheAssetThread implements Callable {
        private URL b;
        private String c;
        private long d;

        public CacheAssetThread(URL url, String str, long j) {
            this.b = url;
            this.c = str;
            this.d = j;
            if (this.d <= 0) {
                this.d = 86400L;
            }
            TapjoyCache.this.d.add(TapjoyCache.b(this.b.toString()));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v10 */
        /* JADX WARN: Type inference failed for: r3v11 */
        /* JADX WARN: Type inference failed for: r3v12 */
        /* JADX WARN: Type inference failed for: r3v13, types: [java.io.BufferedOutputStream] */
        /* JADX WARN: Type inference failed for: r3v15, types: [java.io.BufferedOutputStream] */
        /* JADX WARN: Type inference failed for: r3v16, types: [java.io.IOException] */
        /* JADX WARN: Type inference failed for: r3v17, types: [java.io.BufferedOutputStream] */
        /* JADX WARN: Type inference failed for: r3v18, types: [java.io.IOException] */
        /* JADX WARN: Type inference failed for: r3v19 */
        /* JADX WARN: Type inference failed for: r3v21 */
        /* JADX WARN: Type inference failed for: r3v22 */
        /* JADX WARN: Type inference failed for: r3v23 */
        /* JADX WARN: Type inference failed for: r3v24, types: [java.io.BufferedOutputStream, java.io.OutputStream] */
        /* JADX WARN: Type inference failed for: r3v32, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r3v9 */
        /* JADX WARN: Type inference failed for: r5v10, types: [java.io.BufferedInputStream] */
        /* JADX WARN: Type inference failed for: r5v11 */
        /* JADX WARN: Type inference failed for: r5v12, types: [java.io.BufferedInputStream] */
        /* JADX WARN: Type inference failed for: r5v13, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r5v3, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r5v4 */
        /* JADX WARN: Type inference failed for: r5v5 */
        /* JADX WARN: Type inference failed for: r5v6 */
        /* JADX WARN: Type inference failed for: r5v7 */
        /* JADX WARN: Type inference failed for: r5v8, types: [java.io.BufferedInputStream] */
        /* JADX WARN: Type inference failed for: r5v9, types: [java.io.BufferedInputStream] */
        @Override // java.util.concurrent.Callable
        public Boolean call() throws Throwable {
            boolean z;
            int responseCode;
            String strB = TapjoyCache.b(this.b.toString());
            if (TapjoyCache.this.c.containsKey(strB)) {
                if (new File(((TapjoyCachedAssetData) TapjoyCache.this.c.get(strB)).getLocalFilePath()).exists()) {
                    if (this.d != 0) {
                        ((TapjoyCachedAssetData) TapjoyCache.this.c.get(strB)).resetTimeToLive(this.d);
                    } else {
                        ((TapjoyCachedAssetData) TapjoyCache.this.c.get(strB)).resetTimeToLive(86400L);
                    }
                    TapjoyLog.d("TapjoyCache", "Reseting time to live for " + this.b.toString());
                    TapjoyCache.this.d.remove(strB);
                    return true;
                }
                TapjoyCache.getInstance().removeAssetFromCache(strB);
            }
            System.currentTimeMillis();
            try {
                File file = new File(TapjoyCache.this.f + "/" + TapjoyUtil.SHA256(strB));
                ?? bufferedInputStream = " to ";
                ?? e = "Downloading and caching asset from: " + this.b + " to " + file;
                TapjoyLog.d("TapjoyCache", e);
                try {
                    try {
                        URLConnection uRLConnectionA = fj.a(this.b);
                        uRLConnectionA.setConnectTimeout(15000);
                        uRLConnectionA.setReadTimeout(30000);
                        uRLConnectionA.connect();
                        if ((uRLConnectionA instanceof HttpURLConnection) && (responseCode = ((HttpURLConnection) uRLConnectionA).getResponseCode()) != 200) {
                            throw new IOException("Unexpected response code: " + responseCode);
                        }
                        bufferedInputStream = new BufferedInputStream(uRLConnectionA.getInputStream());
                        try {
                            e = new BufferedOutputStream(new FileOutputStream(file));
                            try {
                                TapjoyUtil.writeFileToDevice(bufferedInputStream, e);
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e2) {
                                }
                                try {
                                    e.close();
                                } catch (IOException e3) {
                                }
                                TapjoyCachedAssetData tapjoyCachedAssetData = new TapjoyCachedAssetData(this.b.toString(), file.getAbsolutePath(), this.d);
                                if (this.c != null) {
                                    tapjoyCachedAssetData.setOfferID(this.c);
                                }
                                TapjoyCache.this.c.put(strB, tapjoyCachedAssetData);
                                TapjoyCache.this.d.remove(strB);
                                e = "TapjoyCache";
                                bufferedInputStream = "----- Download complete -----";
                                TapjoyLog.d("TapjoyCache", "----- Download complete -----" + tapjoyCachedAssetData.toString());
                                z = true;
                            } catch (SocketTimeoutException e4) {
                                e = e4;
                                TapjoyLog.e("TapjoyCache", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.NETWORK_ERROR, "Network timeout during caching: " + e.toString()));
                                TapjoyCache.this.d.remove(strB);
                                TapjoyUtil.deleteFileOrDirectory(file);
                                z = false;
                                if (bufferedInputStream != 0) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e5) {
                                    }
                                }
                                if (e != 0) {
                                    try {
                                        e.close();
                                    } catch (IOException e6) {
                                        e = e6;
                                    }
                                }
                            } catch (Exception e7) {
                                e = e7;
                                TapjoyLog.e("TapjoyCache", "Error caching asset: " + e.toString());
                                TapjoyCache.this.d.remove(strB);
                                TapjoyUtil.deleteFileOrDirectory(file);
                                z = false;
                                if (bufferedInputStream != 0) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e8) {
                                    }
                                }
                                if (e != 0) {
                                    try {
                                        e.close();
                                    } catch (IOException e9) {
                                        e = e9;
                                    }
                                }
                            }
                        } catch (SocketTimeoutException e10) {
                            e = e10;
                            e = 0;
                        } catch (Exception e11) {
                            e = e11;
                            e = 0;
                        } catch (Throwable th) {
                            th = th;
                            e = 0;
                            if (bufferedInputStream != 0) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e12) {
                                }
                            }
                            if (e == 0) {
                                throw th;
                            }
                            try {
                                e.close();
                                throw th;
                            } catch (IOException e13) {
                                throw th;
                            }
                        }
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (SocketTimeoutException e14) {
                    e = e14;
                    e = 0;
                    bufferedInputStream = 0;
                } catch (Exception e15) {
                    e = e15;
                    e = 0;
                    bufferedInputStream = 0;
                } catch (Throwable th3) {
                    th = th3;
                    e = 0;
                    bufferedInputStream = 0;
                }
            } catch (Exception e16) {
                TapjoyCache.this.d.remove(strB);
                return false;
            }
        }
    }

    public TapjoyCache(Context context) {
        if (a == null || unit_test_mode) {
            a = this;
            this.b = context;
            this.c = new TapjoyCacheMap(context, -1);
            this.d = new Vector();
            this.e = Executors.newFixedThreadPool(5);
            if (Environment.getExternalStorageDirectory() != null) {
                TapjoyUtil.deleteFileOrDirectory(new File(Environment.getExternalStorageDirectory(), "tapjoy"));
                TapjoyUtil.deleteFileOrDirectory(new File(Environment.getExternalStorageDirectory(), "tjcache/tmp/"));
            }
            this.f = new File(this.b.getFilesDir() + "/Tapjoy/Cache/");
            if (!this.f.exists()) {
                if (this.f.mkdirs()) {
                    TapjoyLog.d("TapjoyCache", "Created directory at: " + this.f.getPath());
                } else {
                    TapjoyLog.e("TapjoyCache", "Error initalizing cache");
                    a = null;
                }
            }
            a();
        }
    }

    private void a() {
        SharedPreferences sharedPreferences = this.b.getSharedPreferences(TapjoyConstants.PREF_TAPJOY_CACHE, 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            File file = new File(entry.getKey());
            if (file.exists() && file.isFile()) {
                TapjoyCachedAssetData tapjoyCachedAssetDataFromRawJSONString = TapjoyCachedAssetData.fromRawJSONString(entry.getValue().toString());
                if (tapjoyCachedAssetDataFromRawJSONString != null) {
                    TapjoyLog.d("TapjoyCache", "Loaded Asset: " + tapjoyCachedAssetDataFromRawJSONString.getAssetURL());
                    String strB = b(tapjoyCachedAssetDataFromRawJSONString.getAssetURL());
                    if (strB == null || "".equals(strB) || strB.length() <= 0) {
                        TapjoyLog.e("TapjoyCache", "Removing asset because deserialization failed.");
                        editorEdit.remove(entry.getKey()).apply();
                    } else if (tapjoyCachedAssetDataFromRawJSONString.getTimeOfDeathInSeconds() < System.currentTimeMillis() / 1000) {
                        TapjoyLog.d("TapjoyCache", "Asset expired, removing from cache: " + tapjoyCachedAssetDataFromRawJSONString.getAssetURL());
                        if (tapjoyCachedAssetDataFromRawJSONString.getLocalFilePath() != null && tapjoyCachedAssetDataFromRawJSONString.getLocalFilePath().length() > 0) {
                            TapjoyUtil.deleteFileOrDirectory(new File(tapjoyCachedAssetDataFromRawJSONString.getLocalFilePath()));
                        }
                    } else {
                        this.c.put(strB, tapjoyCachedAssetDataFromRawJSONString);
                    }
                } else {
                    TapjoyLog.e("TapjoyCache", "Removing asset because deserialization failed.");
                    editorEdit.remove(entry.getKey()).apply();
                }
            } else {
                TapjoyLog.d("TapjoyCache", "Removing reference to missing asset: " + entry.getKey());
                editorEdit.remove(entry.getKey()).apply();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str) {
        if (str.startsWith("//")) {
            str = "http:" + str;
        }
        try {
            return new URL(str).getFile();
        } catch (MalformedURLException e) {
            TapjoyLog.e("TapjoyCache", "Invalid URL " + str);
            return "";
        }
    }

    public static TapjoyCache getInstance() {
        return a;
    }

    public static void setInstance(TapjoyCache tapjoyCache) {
        a = tapjoyCache;
    }

    public Future cacheAssetFromJSONObject(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("url");
            Long.valueOf(86400L);
            return cacheAssetFromURL(string, jSONObject.optString(TapjoyConstants.TJC_PLACEMENT_OFFER_ID), Long.valueOf(jSONObject.optLong(TapjoyConstants.TJC_TIME_TO_LIVE)).longValue());
        } catch (JSONException e) {
            TapjoyLog.e("TapjoyCache", "Required parameters to cache an asset from JSON is not present");
            return null;
        }
    }

    public Future cacheAssetFromURL(String str, String str2, long j) {
        try {
            URL url = new URL(str);
            if (!this.d.contains(b(str))) {
                return startCachingThread(url, str2, j);
            }
            TapjoyLog.d("TapjoyCache", "URL is already in the process of being cached: " + str);
            return null;
        } catch (MalformedURLException e) {
            TapjoyLog.d("TapjoyCache", "Invalid cache assetURL");
            return null;
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.tapjoy.TapjoyCache$1] */
    public void cacheAssetGroup(final JSONArray jSONArray, final TJCacheListener tJCacheListener) {
        if (jSONArray != null && jSONArray.length() > 0) {
            new Thread() { // from class: com.tapjoy.TapjoyCache.1
                @Override // java.lang.Thread, java.lang.Runnable
                public final void run() {
                    TapjoyLog.d("TapjoyCache", "Starting to cache asset group size of " + jSONArray.length());
                    ArrayList arrayList = new ArrayList();
                    int i = 1;
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        try {
                            Future futureCacheAssetFromJSONObject = TapjoyCache.this.cacheAssetFromJSONObject(jSONArray.getJSONObject(i2));
                            if (futureCacheAssetFromJSONObject != null) {
                                arrayList.add(futureCacheAssetFromJSONObject);
                            }
                        } catch (JSONException e) {
                            TapjoyLog.e("TapjoyCache", "Failed to load JSON object from JSONArray");
                        }
                    }
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        try {
                            i = !((Boolean) ((Future) it.next()).get()).booleanValue() ? 2 : i;
                        } catch (InterruptedException e2) {
                            TapjoyLog.e("TapjoyCache", "Caching thread failed: " + e2.toString());
                            i = 2;
                        } catch (ExecutionException e3) {
                            TapjoyLog.e("TapjoyCache", "Caching thread failed: " + e3.toString());
                            i = 2;
                        }
                    }
                    TapjoyLog.d("TapjoyCache", "Finished caching group");
                    if (tJCacheListener != null) {
                        tJCacheListener.onCachingComplete(i);
                    }
                }
            }.start();
        } else if (tJCacheListener != null) {
            tJCacheListener.onCachingComplete(1);
        }
    }

    public String cachedAssetsToJSON() {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : this.c.entrySet()) {
            try {
                jSONObject.put(((String) entry.getKey()).toString(), ((TapjoyCachedAssetData) entry.getValue()).toRawJSONString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    public void clearTapjoyCache() {
        TapjoyLog.d("TapjoyCache", "Cleaning Tapjoy cache!");
        TapjoyUtil.deleteFileOrDirectory(this.f);
        if (this.f.mkdirs()) {
            TapjoyLog.d("TapjoyCache", "Created new cache directory at: " + this.f.getPath());
        }
        this.c = new TapjoyCacheMap(this.b, -1);
    }

    public TapjoyCacheMap getCachedData() {
        return this.c;
    }

    public TapjoyCachedAssetData getCachedDataForURL(String str) {
        String strB = b(str);
        if (strB != "") {
            return (TapjoyCachedAssetData) this.c.get(strB);
        }
        return null;
    }

    public String getCachedOfferIDs() {
        ArrayList arrayList = new ArrayList();
        if (this.c == null) {
            return "";
        }
        Iterator it = this.c.entrySet().iterator();
        while (it.hasNext()) {
            String offerId = ((TapjoyCachedAssetData) ((Map.Entry) it.next()).getValue()).getOfferId();
            if (offerId != null && offerId.length() != 0 && !arrayList.contains(offerId)) {
                arrayList.add(offerId);
            }
        }
        return TextUtils.join(",", arrayList);
    }

    public String getPathOfCachedURL(String str) {
        String strB = b(str);
        if (strB == "" || !this.c.containsKey(strB)) {
            return str;
        }
        TapjoyCachedAssetData tapjoyCachedAssetData = (TapjoyCachedAssetData) this.c.get(strB);
        if (new File(tapjoyCachedAssetData.getLocalFilePath()).exists()) {
            return tapjoyCachedAssetData.getLocalURL();
        }
        getInstance().removeAssetFromCache(str);
        return str;
    }

    public boolean isURLCached(String str) {
        return this.c.get(b(str)) != null;
    }

    public boolean isURLDownloading(String str) {
        String strB;
        return (this.d == null || (strB = b(str)) == "" || !this.d.contains(strB)) ? false : true;
    }

    public void printCacheInformation() {
        TapjoyLog.d("TapjoyCache", "------------- Cache Data -------------");
        TapjoyLog.d("TapjoyCache", "Number of files in cache: " + this.c.size());
        TapjoyLog.d("TapjoyCache", "Cache Size: " + TapjoyUtil.fileOrDirectorySize(this.f));
        TapjoyLog.d("TapjoyCache", "--------------------------------------");
    }

    public boolean removeAssetFromCache(String str) {
        String strB = b(str);
        return (strB == "" || this.c.remove((Object) strB) == null) ? false : true;
    }

    public Future startCachingThread(URL url, String str, long j) {
        if (url != null) {
            return this.e.submit(new CacheAssetThread(url, str, j));
        }
        return null;
    }
}
