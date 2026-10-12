package com.tapjoy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.tapjoy.internal.ev;
import com.tapjoy.internal.ey;
import com.tapjoy.internal.fc;
import com.tapjoy.internal.gw;
import com.tapjoy.internal.gy;
import com.tapjoy.internal.gz;
import com.tapjoy.internal.hb;
import com.tapjoy.internal.hk;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TapjoyReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intent intent2;
        hb.b(context);
        if ("com.tapjoy.PUSH_CLICK".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("com.tapjoy.PUSH_ID");
            String stringExtra2 = intent.getStringExtra(Tapjoy.INTENT_EXTRA_PUSH_PAYLOAD);
            String stringExtra3 = intent.getStringExtra("com.tapjoy.PUSH_PLACEMENT");
            if (stringExtra != null && stringExtra.length() != 0) {
                PackageManager packageManager = context.getPackageManager();
                String packageName = context.getPackageName();
                Intent intent3 = new Intent("android.intent.action.MAIN");
                intent3.setPackage(packageName);
                intent3.addCategory("android.intent.category.LAUNCHER");
                List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent3, 0);
                if (listQueryIntentActivities == null || listQueryIntentActivities.size() <= 0) {
                    intent2 = null;
                } else {
                    Intent intent4 = new Intent(intent3);
                    intent4.setFlags(268435456);
                    intent4.setClassName(listQueryIntentActivities.get(0).activityInfo.packageName, listQueryIntentActivities.get(0).activityInfo.name);
                    intent2 = intent4;
                }
                if (intent2 != null) {
                    if (stringExtra2 != null) {
                        intent2.putExtra(Tapjoy.INTENT_EXTRA_PUSH_PAYLOAD, stringExtra2);
                    }
                    gz gzVarA = gz.a(context);
                    if (gzVarA.f.c(stringExtra)) {
                        gy gyVar = gzVarA.g;
                        ev.a aVarA = gyVar.a(ey.APP, "push_click");
                        aVarA.s = new fc(null, null, stringExtra);
                        gyVar.a(aVarA);
                    }
                    if (stringExtra3 != null) {
                        hk.a(stringExtra, stringExtra3);
                    }
                    context.startActivity(intent2);
                } else {
                    gw.b("No intent that can be used to launch the main activity.");
                }
            }
        }
        if (isOrderedBroadcast()) {
            setResult(-1, null, null);
        }
    }
}
