package com.naver.glink.android.sdk.login.neoid;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.GsonBuilder;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.d;
import com.naver.plug.android.core.api.request.RequestHelper;
import com.naver.plug.android.core.api.request.RequestListener;
import com.naver.plug.android.core.api.request.VolleyQueue;
import java.util.HashMap;
import kr.co.nexon.android.sns.daum.NPDaumLoginWebDialog;
import kr.co.nexon.android.sns.google.NPGoogleSignIn;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NeoIdSdkManager {
    private static String a = NeoIdSdkManager.class.getSimpleName();
    private static Context b;
    private static Glink.OnLoggedInListener c;

    public enum NeoIdTokenState {
        NEED_INIT,
        NEED_LOGIN,
        OK
    }

    public static String a() {
        return "0.1.11";
    }

    public static void a(Context context) {
        b = context.getApplicationContext();
    }

    public static void a(final Context context, final Glink.OnLoggedInListener onLoggedInListener) {
        if (com.naver.plug.android.core.a.b.a(context)) {
            com.naver.glink.android.sdk.api.requests.a.connectionsRequest.execute(context, new RequestListener<com.naver.glink.android.sdk.api.a.c>() { // from class: com.naver.glink.android.sdk.login.neoid.NeoIdSdkManager.1
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull com.naver.glink.android.sdk.api.a.c cVar) {
                    NeoIdSdkManager.b(context, onLoggedInListener, cVar);
                }
            });
        } else {
            Toast.makeText(context, R.string.network_error, 1).show();
        }
    }

    static void a(String str) {
        VolleyQueue.getInstance(b).getRequestQueue().add(new StringRequest(str, new Response.Listener<String>() { // from class: com.naver.glink.android.sdk.login.neoid.NeoIdSdkManager.2
            @Override // com.android.volley.Response.Listener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResponse(String str2) {
                NeoIdSdkManager.b(str2);
            }
        }, null));
    }

    static void a(boolean z) {
        if (c != null) {
            c.onLoggedIn(z);
            c = null;
        }
    }

    public static void b() {
        c = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, Glink.OnLoggedInListener onLoggedInListener, com.naver.glink.android.sdk.api.a.c cVar) {
        c = onLoggedInListener;
        HashMap map = new HashMap();
        map.put("cafeId", String.valueOf(d.b().e.a));
        if (com.naver.glink.android.sdk.api.requests.a.b() != -1) {
            map.put("channelId", String.valueOf(com.naver.glink.android.sdk.api.requests.a.b()));
        }
        map.put(NPDaumLoginWebDialog.KEY_CLIENT_ID, d.b().e.b);
        map.put("timeZoneId", RequestHelper.getTimeZoneId());
        map.put("langCode", RequestHelper.getSystemLangCode());
        map.put("androidPackage", context.getPackageName());
        String str = cVar.optional.apis.a() + "?" + RequestHelper.makeQueryString(map);
        String str2 = cVar.optional.apis.finish_path;
        Intent intent = new Intent(context, (Class<?>) NeoIdInAppBrowserActivity.class);
        intent.putExtra("url", str);
        intent.putExtra(NeoIdInAppBrowserActivity.c.c, str2);
        context.startActivity(intent);
    }

    static void b(String str) {
        try {
            com.naver.glink.android.sdk.api.a.f fVar = (com.naver.glink.android.sdk.api.a.f) new GsonBuilder().create().fromJson(str, com.naver.glink.android.sdk.api.a.f.class);
            if (fVar.a()) {
                String str2 = fVar.rtn_data.token;
                if (!TextUtils.isEmpty(str2)) {
                    c.a(b, str2);
                    c.b(b, fVar.rtn_data.sns_cd);
                    if (!c.c(b) && TextUtils.equals(fVar.rtn_data.sns_cd, NPGoogleSignIn.SERVICE_NAME)) {
                        com.naver.glink.android.sdk.b.c();
                        c.a(b, true);
                    }
                }
                a(fVar.a());
            }
        } catch (Exception e) {
            Log.e(a, "exception: " + e);
        }
    }

    public static NeoIdTokenState c() {
        if (b == null) {
            return NeoIdTokenState.NEED_INIT;
        }
        return TextUtils.isEmpty(c.a(b)) ? NeoIdTokenState.NEED_LOGIN : NeoIdTokenState.OK;
    }

    public static String d() {
        return c.a(b);
    }

    public static void e() {
        c.a(b, "");
        c.b(b, "");
    }
}
