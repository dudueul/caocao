package com.naver.glink.android.sdk.a;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.R;

/* JADX INFO: compiled from: Validate.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class z {
    private static final String a = "Validate";
    private static Boolean b;
    private static final String[][] c = {new String[]{"com.google.gson.Gson", "gson"}, new String[]{"com.bumptech.glide.Glide", "glide"}, new String[]{"com.android.volley.Network", "volly"}, new String[]{"com.squareup.otto.Bus", "otto"}, new String[]{"com.navercorp.volleyextensions.volleyer.Volleyer", "Naver volleyer"}, new String[]{"com.nhn.android.naverlogin.OAuthLogin", "Naver Id Login"}};

    private static void a() {
        for (int i = 0; i < c.length; i++) {
            try {
                Class.forName(c[i][0]);
            } catch (ClassNotFoundException e) {
                String str = c[i][1];
                throw new IllegalStateException(String.format("library '%s' is not found. you must add library '%s' for use NAVER CAFE SDK", str, str));
            }
        }
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        if (!Glink.isSupportAndroidVersion()) {
            Toast.makeText(context, context.getString(R.string.not_supported_os_version), 1).show();
            return false;
        }
        if (b == null) {
            try {
                a();
                b();
                b = true;
            } catch (Exception e) {
                b = false;
                Log.e(a, e.toString() + "\nusages guide links\n  > android: https://www.gitbook.com/book/plug/plug-sdk-android/details\n  > unity: https://www.gitbook.com/book/plug/plug-sdk-unity/details\n  > cocos2dx: https://www.gitbook.com/book/plug/plug-sdk-cocos2dx/details\n  > unreal: https://www.gitbook.com/book/plug/plug-sdk-unreal/details");
            }
        }
        return b.booleanValue();
    }

    private static void b() {
        if (com.naver.glink.android.sdk.d.b().e()) {
            com.naver.glink.android.sdk.configure.a.b bVar = com.naver.glink.android.sdk.d.b().d;
            if (TextUtils.isEmpty(bVar.b)) {
                throw new IllegalStateException("Not Exist ClientId.");
            }
            if (TextUtils.isEmpty(bVar.c)) {
                throw new IllegalStateException("Not Exist ClientSecret.");
            }
            if (bVar.a == 0) {
                throw new IllegalStateException("Not Exist CafeId.");
            }
            return;
        }
        if (!com.naver.glink.android.sdk.d.b().f()) {
            throw new IllegalStateException("The SDK has not been initialized.");
        }
        com.naver.glink.android.sdk.configure.a.b bVar2 = com.naver.glink.android.sdk.d.b().e;
        if (TextUtils.isEmpty(bVar2.b)) {
            throw new IllegalStateException("Not Exist NeoIdConsumerKey.");
        }
        if (bVar2.a == 0) {
            throw new IllegalStateException("Not Exist CommunityId.");
        }
    }
}
