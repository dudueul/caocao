package com.naver.glink.android.sdk;

import android.content.Context;
import android.support.annotation.Keep;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.plug.android.core.api.request.VolleyQueue;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Keep
public class NaverIdLogin {

    @Keep
    public interface OnGetProfileListener {
        void onResult(String str);
    }

    public static void getProfile(Context context, final OnGetProfileListener onGetProfileListener) {
        if (isLogin(context)) {
            VolleyQueue.getInstance(context).getRequestQueue().add(new StringRequest("https://openapi.naver.com/v1/nid/me", new Response.Listener<String>() { // from class: com.naver.glink.android.sdk.NaverIdLogin.3
                @Override // com.android.volley.Response.Listener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onResponse(String str) {
                    if (onGetProfileListener != null) {
                        onGetProfileListener.onResult(str);
                    }
                }
            }, new Response.ErrorListener() { // from class: com.naver.glink.android.sdk.NaverIdLogin.4
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    if (onGetProfileListener != null) {
                        onGetProfileListener.onResult(null);
                    }
                }
            }) { // from class: com.naver.glink.android.sdk.NaverIdLogin.5
                @Override // com.android.volley.Request
                public Map<String, String> getHeaders() {
                    HashMap map = new HashMap();
                    map.put(HttpRequest.HEADER_AUTHORIZATION, "Bearer " + LoginHelper.LoginType.NAVER.getAccessToken());
                    return map;
                }
            });
        } else if (onGetProfileListener != null) {
            onGetProfileListener.onResult(null);
        }
    }

    public static boolean isLogin(Context context) {
        return d.b().e() && LoginHelper.LoginType.NAVER.isLogin(context);
    }

    public static void login(final Context context, final Glink.OnLoggedInListener onLoggedInListener) {
        if (d.b().e()) {
            d.a(new Runnable() { // from class: com.naver.glink.android.sdk.NaverIdLogin.1
                @Override // java.lang.Runnable
                public void run() {
                    LoginHelper.LoginType.NAVER.login(context, onLoggedInListener);
                }
            });
        }
    }

    public static void logout(final Context context) {
        if (d.b().e()) {
            d.a(new Runnable() { // from class: com.naver.glink.android.sdk.NaverIdLogin.2
                @Override // java.lang.Runnable
                public void run() {
                    LoginHelper.LoginType.NAVER.logout(context);
                }
            });
        }
    }
}
