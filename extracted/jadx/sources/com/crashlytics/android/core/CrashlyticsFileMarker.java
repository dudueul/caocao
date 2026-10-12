package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.persistence.FileStore;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class CrashlyticsFileMarker {
    private final FileStore fileStore;
    private final String markerName;

    public CrashlyticsFileMarker(String str, FileStore fileStore) {
        this.markerName = str;
        this.fileStore = fileStore;
    }

    private File getMarkerFile() {
        return new File(this.fileStore.getFilesDir(), this.markerName);
    }

    public boolean create() {
        try {
            return getMarkerFile().createNewFile();
        } catch (IOException e) {
            Fabric.getLogger().e(CrashlyticsCore.TAG, "Error creating marker: " + this.markerName, e);
            return false;
        }
    }

    public boolean isPresent() {
        return getMarkerFile().exists();
    }

    public boolean remove() {
        return getMarkerFile().delete();
    }
}
