package com.nexon.platform;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import com.nexon.core.log.ToyLog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPInstallReferrerReceiver extends BroadcastReceiver {
    private void dispatchEvent(Context context, Intent intent) {
        if (intent == null) {
            ToyLog.d("intent is null in dispatchEvent");
            return;
        }
        try {
            ToyLog.dd("componentName:" + intent.getComponent());
            ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(intent.getComponent(), 128);
            for (String str : receiverInfo.metaData.keySet()) {
                String string = receiverInfo.metaData.getString(str);
                ToyLog.dd("meta key:" + str + " , meta value:" + string);
                try {
                    BroadcastReceiver broadcastReceiver = (BroadcastReceiver) Class.forName(string).newInstance();
                    if (broadcastReceiver != null) {
                        broadcastReceiver.onReceive(context, intent);
                    }
                } catch (Exception e) {
                    ToyLog.dd("BroadcastReceiver class not found. name:" + string);
                }
            }
        } catch (PackageManager.NameNotFoundException e2) {
            ToyLog.dd("Package name not found. error:" + e2.toString());
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("referrer");
        ToyLog.dd("isOrderedBroadCast:" + isOrderedBroadcast() + ", resultCode:" + getResultCode() + ", resultData:" + getResultData());
        ToyLog.dd("onReceive Context:" + context + " intent:" + intent);
        ToyLog.dd("referrer:" + stringExtra);
        if (stringExtra != null) {
        }
        dispatchEvent(context, intent);
    }
}
