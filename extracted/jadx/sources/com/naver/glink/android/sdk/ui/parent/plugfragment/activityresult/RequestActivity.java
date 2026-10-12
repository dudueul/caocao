package com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;
import com.naver.glink.android.sdk.R;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class RequestActivity extends Activity {
    public static final int a = 10001;
    public static final String b = "OVERLAY_PERMISSION";
    public static final String c = "REQ_PERMISSON";
    public static final String d = "BUNDLE_REQ_PERMISSION";
    public static final String e = "BUNDLE_REQ_CODE";
    public static final String f = "BUNDLE_INTENT";
    private com.naver.glink.android.sdk.ui.parent.plugfragment.a.b g;

    private void a() {
        if (Build.VERSION.SDK_INT < 23) {
            finish();
        } else {
            if (Settings.canDrawOverlays(this)) {
                return;
            }
            startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())), 10001);
        }
    }

    private void a(int i, int i2, Intent intent) {
        if (Build.VERSION.SDK_INT >= 23) {
            Log.d("yang", "yang PERMISSIONS_REQ_NUM " + Settings.canDrawOverlays(this));
            if (!Settings.canDrawOverlays(this)) {
            }
        }
    }

    private void a(Intent intent) {
        if (Build.VERSION.SDK_INT < 23) {
            finish();
        } else {
            if (intent == null) {
                return;
            }
            Bundle bundleExtra = intent.getBundleExtra(c);
            requestPermissions(bundleExtra.getStringArray(d), bundleExtra.getInt(e));
        }
    }

    private void a(Intent intent, int i) {
        if (intent == null) {
            return;
        }
        intent.addFlags(8388608);
        startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        Log.d("yang", "yang RequestActivity onActivityResult " + i2);
        if (this.g != null) {
            this.g.a(i, i2, intent);
        }
        if (i2 == -1) {
        }
        super.onActivityResult(i, i2, intent);
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        Log.d("yang", "yang RequestActivity onConfigurationChanged");
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.g = new com.naver.glink.android.sdk.ui.parent.plugfragment.a.a(this);
        Intent intent = getIntent();
        if (intent == null) {
        }
        setContentView(R.layout.activity_request);
        String action = intent.getAction();
        if (action == null) {
            finish();
            return;
        }
        switch (action) {
            case "OVERLAY_PERMISSION":
                a();
                break;
            case "android.intent.action.PICK":
                a((Intent) intent.getParcelableExtra(f), intent.getIntExtra(e, 0));
                break;
            case "REQ_PERMISSON":
                a(intent);
                break;
            default:
                finish();
                break;
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        Log.d("yang", "RequestActivity onDestroy");
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        Log.d("yang", "RequestActivity onPause");
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (this.g != null) {
            this.g.a(i, strArr, iArr);
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onResume() {
        Log.d("yang", "RequestActivity onResume");
        super.onResume();
    }
}
