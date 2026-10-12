package com.crashlytics.android.core;

import java.io.InputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface PinningInfoProvider {
    String getKeyStorePassword();

    InputStream getKeyStoreStream();

    String[] getPins();
}
