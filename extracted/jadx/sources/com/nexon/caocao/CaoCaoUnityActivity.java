package com.nexon.caocao;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import com.adjust.sdk.Adjust;
import com.nexon.caocao.iab.CaoCaoIABActivity;
import com.nexon.platform.store.vendor.util.IabBroadcastReceiver;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;
import kr.co.nexon.npaccount.NPAccount;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CaoCaoUnityActivity extends UnityPlayerActivity implements PromoBroadcastReceiver.PromoBroadcastListener {
    public PromoBroadcastReceiver promoBroadcastReceiver = new PromoBroadcastReceiver(this);

    public int ShouldShowRequestPermissionRationale(final Activity activity, String str, String str2, String str3) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, str)) {
            return 0;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(str2);
        builder.setPositiveButton(str3, new DialogInterface.OnClickListener() { // from class: com.nexon.caocao.CaoCaoUnityActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                UnityPlayer.UnitySendMessage("(singleton) NPAController", "SetPermissionFlow", "APP_SETTING");
                activity.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.parse("package:" + activity.getApplicationContext().getPackageName())));
            }
        });
        builder.show();
        return 1;
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        NPAccount.getInstance(this).onActivityResult(this, i, i2, intent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
    }

    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Adjust.appWillOpenUrl(getIntent().getData());
        NPAccount.getInstance(this).onActivityCreated(this, bundle);
    }

    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.promoBroadcastReceiver);
    }

    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Adjust.appWillOpenUrl(intent.getData());
        NPAccount.getInstance(this).onNewIntent(intent);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        NPAccount.getInstance(this).onRequestPermissionsResult(this, i, strArr, iArr);
    }

    @Override // com.unity3d.player.UnityPlayerActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        registerReceiver(this.promoBroadcastReceiver, new IntentFilter(IabBroadcastReceiver.ACTION));
    }

    @Override // com.nexon.caocao.PromoBroadcastReceiver.PromoBroadcastListener
    public void receivedBroadcast() {
        if (CaoCaoIABActivity.instance().CheckInitIabHelper()) {
            CaoCaoIABActivity.instance().QueryInventory();
        }
    }
}
