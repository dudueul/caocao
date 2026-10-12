package kr.co.nexon.mdev.android.activity;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPActivity extends Activity {
    private void hideSystemUI(boolean z) {
        try {
            if (getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getBoolean("immersiveMode") && z && Build.VERSION.SDK_INT >= 19) {
                findViewById(R.id.content).getRootView().setSystemUiVisibility(5894);
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        hideSystemUI(true);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    @SuppressLint({"NewApi"})
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        hideSystemUI(z);
    }
}
