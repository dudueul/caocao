package com.tapjoy.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.adjust.sdk.Constants;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class d extends BroadcastReceiver {
    @Nullable
    public static String a(Intent intent) {
        if ("com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            return intent.getStringExtra("referrer");
        }
        return null;
    }

    private static boolean a(Context context, String str, String str2) {
        FileOutputStream fileOutputStreamOpenFileOutput = null;
        try {
            fileOutputStreamOpenFileOutput = context.openFileOutput(str, 0);
            bg.a(fileOutputStreamOpenFileOutput, str2);
            fileOutputStreamOpenFileOutput.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e2) {
            jz.a(fileOutputStreamOpenFileOutput);
            context.deleteFile(Constants.INSTALL_REFERRER);
            return false;
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String strA = a(intent);
        if (strA != null) {
            a(context, Constants.INSTALL_REFERRER, strA);
        }
    }
}
