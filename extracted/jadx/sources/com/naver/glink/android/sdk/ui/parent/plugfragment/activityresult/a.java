package com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import com.naver.glink.android.sdk.d;

/* JADX INFO: compiled from: ActivityResultHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityResultHelper.java */
    public static class C0048a {
        public int a;
        public int b;
        public Intent c;
        public String d;

        public C0048a(int i, int i2, Intent intent, String str) {
            this.a = i;
            this.b = i2;
            this.c = intent;
            this.d = str;
        }
    }

    /* JADX INFO: compiled from: ActivityResultHelper.java */
    public static class b {
        public int a;
        public String[] b;
        public int[] c;

        public b(int i, String[] strArr, int[] iArr) {
            this.a = i;
            this.b = strArr;
            this.c = iArr;
        }
    }

    public static Fragment a(Activity activity) {
        return activity.getFragmentManager().findFragmentByTag(d.a);
    }

    public static void a(Context context, Intent intent, int i) {
        if (d.h(context)) {
            com.naver.glink.android.sdk.ui.parent.plugfragment.a.d.a(true);
            context.startActivity(b(context, intent, i));
            com.naver.glink.android.sdk.ui.main.b.a();
        } else {
            Fragment fragmentA = a((Activity) context);
            if (fragmentA != null) {
                fragmentA.startActivityForResult(intent, i);
            }
        }
    }

    private static Intent b(Context context, Intent intent, int i) {
        if (intent == null) {
            return null;
        }
        Intent intent2 = new Intent(context, (Class<?>) RequestActivity.class);
        intent2.setAction(intent.getAction());
        intent2.putExtra(RequestActivity.f, intent);
        intent2.putExtra(RequestActivity.e, i);
        intent2.addFlags(268435456);
        intent2.addFlags(8388608);
        intent2.addFlags(524288);
        intent2.addFlags(2097152);
        return intent2;
    }
}
