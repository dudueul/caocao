package com.naver.glink.android.sdk.login.neoid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class PlugSchemeActivity extends Activity {
    public static final String a = "PlugSchemeActivity.ACTION_LOGIN_FINISH";
    public static final String b = "result";

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        switch (getIntent().getData().getHost()) {
            case "googleLogin":
                Intent intent = new Intent(this, (Class<?>) NeoIdInAppBrowserActivity.class);
                intent.setAction(a);
                intent.addFlags(603979776);
                intent.putExtra(b, getIntent().getStringExtra(b));
                startActivity(intent);
                break;
        }
        finish();
    }
}
