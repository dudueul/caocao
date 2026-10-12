package com.crashlytics.android.core;

import java.io.File;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
interface Report {
    Map<String, String> getCustomHeaders();

    File getFile();

    String getFileName();

    File[] getFiles();

    String getIdentifier();

    void remove();
}
