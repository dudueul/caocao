package kr.co.nexon.mdev.android.activity;

import android.content.Intent;
import com.nexon.core.log.ToyLog;
import com.nexon.platform.NXPFinalizer;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPActivityResultManager {
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: kr.co.nexon.mdev.android.activity.NPActivityResultManager.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            NPActivityResultManager unused = NPActivityResultManager.instance = null;
        }
    };
    private static volatile NPActivityResultManager instance;
    private Map<Integer, ActivityResultCallback> activityResultCallbacks = new HashMap();

    public interface ActivityResultCallback {
        void onActivityResult(int i, int i2, Intent intent);
    }

    private NPActivityResultManager() {
    }

    public static NPActivityResultManager getInstance() {
        if (instance == null) {
            synchronized (NPActivityResultManager.class) {
                if (instance == null) {
                    instance = new NPActivityResultManager();
                }
            }
        }
        return instance;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        ActivityResultCallback activityResultCallback = this.activityResultCallbacks.get(Integer.valueOf(i));
        ToyLog.d("onActivityResult requestCode :" + i + "  callback :" + activityResultCallback);
        if (activityResultCallback != null) {
            activityResultCallback.onActivityResult(i, i2, intent);
            this.activityResultCallbacks.remove(Integer.valueOf(i));
        }
    }

    public void registerActivityResultCallback(int i, ActivityResultCallback activityResultCallback) {
        this.activityResultCallbacks.put(Integer.valueOf(i), activityResultCallback);
    }
}
