package io.fabric.sdk.android.services.persistence;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.io.File;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FileStoreImpl implements FileStore {
    private final String contentPath;
    private final Context context;
    private final String legacySupport;

    public FileStoreImpl(Kit kit) {
        if (kit.getContext() == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
        }
        this.context = kit.getContext();
        this.contentPath = kit.getPath();
        this.legacySupport = "Android/" + this.context.getPackageName();
    }

    @Override // io.fabric.sdk.android.services.persistence.FileStore
    public File getCacheDir() {
        return prepare(this.context.getCacheDir());
    }

    @Override // io.fabric.sdk.android.services.persistence.FileStore
    public File getExternalCacheDir() {
        File externalCacheDir = null;
        if (isExternalStorageAvailable()) {
            externalCacheDir = Build.VERSION.SDK_INT >= 8 ? this.context.getExternalCacheDir() : new File(Environment.getExternalStorageDirectory(), this.legacySupport + "/cache/" + this.contentPath);
        }
        return prepare(externalCacheDir);
    }

    @Override // io.fabric.sdk.android.services.persistence.FileStore
    @TargetApi(8)
    public File getExternalFilesDir() {
        File externalFilesDir = null;
        if (isExternalStorageAvailable()) {
            externalFilesDir = Build.VERSION.SDK_INT >= 8 ? this.context.getExternalFilesDir(null) : new File(Environment.getExternalStorageDirectory(), this.legacySupport + "/files/" + this.contentPath);
        }
        return prepare(externalFilesDir);
    }

    @Override // io.fabric.sdk.android.services.persistence.FileStore
    public File getFilesDir() {
        return prepare(this.context.getFilesDir());
    }

    boolean isExternalStorageAvailable() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            return true;
        }
        Fabric.getLogger().w(Fabric.TAG, "External Storage is not mounted and/or writable\nHave you declared android.permission.WRITE_EXTERNAL_STORAGE in the manifest?");
        return false;
    }

    File prepare(File file) {
        if (file == null) {
            Fabric.getLogger().d(Fabric.TAG, "Null File");
        } else {
            if (file.exists() || file.mkdirs()) {
                return file;
            }
            Fabric.getLogger().w(Fabric.TAG, "Couldn't create file");
        }
        return null;
    }
}
