package io.fabric.sdk.android.services.persistence;

import java.io.File;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface FileStore {
    File getCacheDir();

    File getExternalCacheDir();

    File getExternalFilesDir();

    File getFilesDir();
}
