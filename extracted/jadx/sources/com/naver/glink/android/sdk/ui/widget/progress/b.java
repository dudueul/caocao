package com.naver.glink.android.sdk.ui.widget.progress;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import com.google.android.gms.games.GamesStatusCodes;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.d;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: ProgressDialogHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    private static final String a = "ProgressDialogHelper";
    private static ProgressDialog b;
    private static Set<String> c = new HashSet();

    public static void a() {
        try {
            if (b != null) {
                b.dismiss();
                b = null;
            }
        } catch (Exception e) {
            if (Log.isLoggable(a, 3)) {
                Log.d(a, "exception: " + e);
            }
        }
    }

    public static void a(int i) {
        if (b != null) {
            b.setProgress(b.getProgress() + i);
        }
    }

    public static void a(Context context, CharSequence charSequence, boolean z) {
        a();
        try {
            if (!z) {
                b = new a(context);
                if (d.h(context)) {
                    b.getWindow().getAttributes().type = GamesStatusCodes.STATUS_REQUEST_TOO_MANY_RECIPIENTS;
                }
                b.show();
                return;
            }
            b = new ProgressDialog(context, R.style.notitle_defaultdialog_msg);
            b.setProgressStyle(1);
            b.setMessage(charSequence);
            b.setCancelable(false);
            if (d.h(context)) {
                b.getWindow().getAttributes().type = GamesStatusCodes.STATUS_REQUEST_TOO_MANY_RECIPIENTS;
            }
            b.show();
        } catch (Exception e) {
            if (Log.isLoggable(a, 3)) {
                Log.d(a, "exception: " + e);
            }
        }
    }

    public static void a(String str) {
        c.add(str);
        com.naver.glink.android.sdk.ui.tabs.b.h();
    }

    public static void b(String str) {
        c.remove(str);
        if (c.isEmpty()) {
            com.naver.glink.android.sdk.ui.tabs.b.i();
        }
    }

    public static boolean b() {
        return b != null && b.isShowing();
    }

    public static void c() {
        a();
        c.clear();
        com.naver.glink.android.sdk.ui.tabs.b.i();
    }
}
