package com.facebook.internal;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import com.facebook.FacebookSdk;
import com.google.android.gms.common.util.CrashUtils;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CustomTab {
    private Uri uri;

    public CustomTab(String str, Bundle bundle) {
        this.uri = Utility.buildUri(ServerProtocol.getDialogAuthority(), FacebookSdk.getGraphApiVersion() + "/" + ServerProtocol.DIALOG_PATH + str, bundle == null ? new Bundle() : bundle);
    }

    public void openCustomTab(Activity activity, String str) {
        CustomTabsIntent customTabsIntentBuild = new CustomTabsIntent.Builder().build();
        customTabsIntentBuild.intent.setPackage(str);
        customTabsIntentBuild.intent.addFlags(CrashUtils.ErrorDialogData.SUPPRESSED);
        customTabsIntentBuild.launchUrl(activity, this.uri);
    }
}
