package com.facebook.internal;

import android.net.Uri;
import com.facebook.LoggingBehavior;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class UrlRedirectCache {
    private static FileLruCache urlRedirectCache;
    static final String TAG = UrlRedirectCache.class.getSimpleName();
    private static final String REDIRECT_CONTENT_TAG = TAG + "_Redirect";

    UrlRedirectCache() {
    }

    static void cacheUriRedirect(Uri uri, Uri uri2) {
        if (uri == null || uri2 == null) {
            return;
        }
        OutputStream outputStreamOpenPutStream = null;
        try {
            outputStreamOpenPutStream = getCache().openPutStream(uri.toString(), REDIRECT_CONTENT_TAG);
            outputStreamOpenPutStream.write(uri2.toString().getBytes());
        } catch (IOException e) {
        } finally {
            Utility.closeQuietly(outputStreamOpenPutStream);
        }
    }

    static void clearCache() {
        try {
            getCache().clearCache();
        } catch (IOException e) {
            Logger.log(LoggingBehavior.CACHE, 5, TAG, "clearCache failed " + e.getMessage());
        }
    }

    static synchronized FileLruCache getCache() throws IOException {
        if (urlRedirectCache == null) {
            urlRedirectCache = new FileLruCache(TAG, new FileLruCache.Limits());
        }
        return urlRedirectCache;
    }

    static Uri getRedirectedUri(Uri uri) throws Throwable {
        InputStreamReader inputStreamReader;
        Uri uri2 = null;
        if (uri != null) {
            String string = uri.toString();
            InputStreamReader inputStreamReader2 = null;
            try {
                FileLruCache cache = getCache();
                boolean z = false;
                while (true) {
                    try {
                        inputStreamReader = inputStreamReader2;
                        InputStream inputStream = cache.get(string, REDIRECT_CONTENT_TAG);
                        if (inputStream == null) {
                            break;
                        }
                        z = true;
                        inputStreamReader2 = new InputStreamReader(inputStream);
                        char[] cArr = new char[128];
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            int i = inputStreamReader2.read(cArr, 0, cArr.length);
                            if (i <= 0) {
                                break;
                            }
                            sb.append(cArr, 0, i);
                        }
                        Utility.closeQuietly(inputStreamReader2);
                        string = sb.toString();
                    } catch (IOException e) {
                        inputStreamReader2 = inputStreamReader;
                        Utility.closeQuietly(inputStreamReader2);
                    } catch (Throwable th) {
                        th = th;
                        inputStreamReader2 = inputStreamReader;
                        Utility.closeQuietly(inputStreamReader2);
                        throw th;
                    }
                    Utility.closeQuietly(inputStreamReader2);
                }
                if (z) {
                    uri2 = Uri.parse(string);
                    Utility.closeQuietly(inputStreamReader);
                } else {
                    Utility.closeQuietly(inputStreamReader);
                }
            } catch (IOException e2) {
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return uri2;
    }
}
