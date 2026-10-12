package com.naver.glink.android.sdk.api.requests;

import android.content.Context;
import android.text.TextUtils;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.facebook.appevents.UserDataStore;
import com.google.gson.JsonSyntaxException;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.plug.android.core.api.request.VolleyQueue;
import com.nexon.nexonanalyticssdk.NxSystemInfo;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: JackpotRequests.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class d {
    private static final l logger = l.a(d.class.getSimpleName());

    /* JADX INFO: compiled from: JackpotRequests.java */
    private static class a {
        Map<String, Object> client;
        List<Map<String, Object>> events;

        public a(Map<String, Object> map, List<Map<String, Object>> list) {
            this.client = map;
            this.events = list;
        }

        public a(Map<String, Object> map, Map<String, Object> map2) {
            this.client = map;
            if (this.events == null) {
                this.events = new ArrayList();
            }
            this.events.add(map2);
        }

        public void a(Map<String, Object> map) {
            this.client = map;
        }

        public boolean a() {
            return this.client == null || this.events == null || this.events.size() == 0;
        }

        public void b(Map<String, Object> map) {
            if (this.events == null) {
                this.events = new ArrayList();
            }
            this.events.add(map);
        }
    }

    /* JADX INFO: compiled from: JackpotRequests.java */
    public static class b {
        int result_code;
        a result_data;

        /* JADX INFO: compiled from: JackpotRequests.java */
        public class a {
            String message;

            public a() {
            }
        }

        public int a() {
            return this.result_code;
        }

        public String b() {
            return this.result_data.message;
        }
    }

    private static HashMap<String, Object> a(Context context) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("service_id", "plugkr");
        map.put("user_key", b(context));
        map.put("product", "app");
        map.put(NxSystemInfo.KEY_OS_NAME, "android");
        map.put("os_ver", com.naver.glink.android.sdk.a.e.d());
        map.put("app_ver", context.getString(R.string.plug_sdk_version));
        map.put(UserDataStore.COUNTRY, com.naver.glink.android.sdk.a.e.b(context));
        map.put("language", com.naver.glink.android.sdk.a.e.a(context));
        map.put("device_id", c(context));
        map.put("device_model", com.naver.glink.android.sdk.a.e.c());
        return map;
    }

    private static void a(Context context, String str, String str2) {
        VolleyQueue.getInstance(context).getRequestQueue().add(new JsonRequest<b>(1, str, str2, new Response.Listener<b>() { // from class: com.naver.glink.android.sdk.api.requests.d.1
            @Override // com.android.volley.Response.Listener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResponse(b bVar) {
                d.logger.a("stan++ response : " + bVar.result_code + " " + bVar.result_data.message, new Object[0]);
            }
        }, new Response.ErrorListener() { // from class: com.naver.glink.android.sdk.api.requests.d.2
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
            }
        }) { // from class: com.naver.glink.android.sdk.api.requests.d.3
            @Override // com.android.volley.toolbox.JsonRequest, com.android.volley.Request
            public String getBodyContentType() {
                return "application/json; charset=UTF-8";
            }

            @Override // com.android.volley.toolbox.JsonRequest, com.android.volley.Request
            protected Response<b> parseNetworkResponse(NetworkResponse networkResponse) {
                try {
                    return Response.success(com.naver.glink.android.sdk.a.b.a.a().fromJson(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers)), b.class), HttpHeaderParser.parseCacheHeaders(networkResponse));
                } catch (JsonSyntaxException e) {
                    return Response.error(new ParseError(e));
                } catch (UnsupportedEncodingException e2) {
                    return Response.error(new ParseError(e2));
                } catch (Exception e3) {
                    return Response.error(new ParseError());
                }
            }
        });
    }

    public static void a(Map<String, Object> map) {
        Context contextR = com.naver.glink.android.sdk.d.r();
        String str = com.naver.glink.android.sdk.d.b().c.h;
        if (contextR == null || TextUtils.isEmpty(str)) {
            return;
        }
        a(contextR, str + b(), com.naver.glink.android.sdk.a.b.a.a().toJson(new a(a(contextR), map)));
    }

    private static String b() {
        if (com.naver.glink.android.sdk.d.m()) {
            return "/jackpotlog/test/v1/logs";
        }
        return com.naver.glink.android.sdk.d.n() ? "/jackpotlog/stage/v1/logs" : "/jackpotlog/v1/logs";
    }

    private static String b(Context context) {
        String strD = LoginHelper.a().isLogin(context) ? p.d(context) : "";
        return TextUtils.isEmpty(strD) ? "__UNKNOWN__" : strD;
    }

    private static String c(Context context) {
        String strE = p.e(context);
        if (!TextUtils.isEmpty(strE)) {
            return strE;
        }
        String strC = com.naver.glink.android.sdk.a.e.c(context);
        p.e(context, strC);
        return strC;
    }
}
