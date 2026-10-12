package com.facebook;

import android.content.Intent;
import com.facebook.internal.CallbackManagerImpl;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface CallbackManager {

    public static class Factory {
        public static CallbackManager create() {
            return new CallbackManagerImpl();
        }
    }

    boolean onActivityResult(int i, int i2, Intent intent);
}
