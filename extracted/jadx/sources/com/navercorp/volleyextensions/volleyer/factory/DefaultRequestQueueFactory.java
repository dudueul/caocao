package com.navercorp.volleyextensions.volleyer.factory;

import android.content.Context;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.NoCache;
import com.navercorp.volleyextensions.volleyer.util.Assert;
import java.io.File;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DefaultRequestQueueFactory {
    public static RequestQueue create(Context context) {
        Assert.notNull(context, "Context");
        return new RequestQueue(getDefaultDiskCache(context), getDefaultNetwork(HttpStackFactory.createDefaultHttpStack()));
    }

    public static File getCacheDir(Context context) {
        return new File(context.getCacheDir().getPath() + "/volleyer-cache");
    }

    public static Cache getDefaultDiskCache(Context context) {
        File cacheDir = getCacheDir(context);
        return cacheDir == null ? new NoCache() : new DiskBasedCache(cacheDir);
    }

    public static Network getDefaultNetwork(HttpStack httpStack) {
        Assert.notNull(httpStack, "HttpStack");
        return new BasicNetwork(httpStack);
    }
}
