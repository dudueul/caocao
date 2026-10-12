package com.nexon.caocao.iab;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import com.android.iab.util.IabResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CCPurchaseProxyActivity extends Activity {
    public static final String ACTION_FINISH = "com.nexon.caocao.iab.ACTION_FINISH";
    private BroadcastReceiver broadcastReceiver;

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        Log.d(CaoCaoIABActivity.mLogTag, "onActivityResult(" + i + ", " + i2 + ", " + intent);
        if (CaoCaoIABActivity.instance().getHelper().handleActivityResult(i, i2, intent)) {
            Log.d(CaoCaoIABActivity.mLogTag, "onActivityResult handled by IABUtil.");
        } else {
            super.onActivityResult(i, i2, intent);
        }
        finish();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.broadcastReceiver = new BroadcastReceiver() { // from class: com.nexon.caocao.iab.CCPurchaseProxyActivity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Log.d(CaoCaoIABActivity.mLogTag, "PurchaseProxyActivity - broadcast was received");
                if (CCPurchaseProxyActivity.this.isFinishing()) {
                    return;
                }
                CCPurchaseProxyActivity.this.finish();
            }
        };
        registerReceiver(this.broadcastReceiver, new IntentFilter(ACTION_FINISH));
        if (CaoCaoIABActivity.sendRequest) {
            CaoCaoIABActivity.sendRequest = false;
            Intent intent = getIntent();
            String stringExtra = intent.getStringExtra("sku");
            String stringExtra2 = intent.getStringExtra("developerPayload");
            try {
                if (intent.getBooleanExtra("inapp", true)) {
                    CaoCaoIABActivity.instance().getHelper().launchPurchaseFlow(this, stringExtra, 10001, CaoCaoIABActivity.instance().getPurchaseFinishedListener(), stringExtra2);
                } else {
                    CaoCaoIABActivity.instance().getHelper().launchSubscriptionPurchaseFlow(this, stringExtra, 10001, CaoCaoIABActivity.instance().getPurchaseFinishedListener(), stringExtra2);
                }
            } catch (IllegalStateException e) {
                CaoCaoIABActivity.instance().getPurchaseFinishedListener().onIabPurchaseFinished(new IabResult(3, "Cannot start purchase process. Billing unavailable."), null);
            } catch (NullPointerException e2) {
                CaoCaoIABActivity.instance().getPurchaseFinishedListener().onIabPurchaseFinished(new IabResult(3, "Cannot start purchase process. Billing unavailable."), null);
            }
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.broadcastReceiver);
    }
}
