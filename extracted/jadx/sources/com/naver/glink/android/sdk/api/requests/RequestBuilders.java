package com.naver.glink.android.sdk.api.requests;

import android.text.TextUtils;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.glink.android.sdk.login.neoid.NeoIdSdkManager;
import com.naver.plug.android.core.api.Response;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestBuilder;
import com.naver.plug.android.core.api.request.RequestHelper;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum RequestBuilders {
    COMMON { // from class: com.naver.glink.android.sdk.api.requests.RequestBuilders.1
        @Override // com.naver.glink.android.sdk.api.requests.RequestBuilders
        public <T extends Response> RequestBuilder<T> builder(String str, Map<String, String> map, Class<T> cls) {
            return (com.naver.glink.android.sdk.d.k() ? CAFE : PLUG).builder(str, map, cls);
        }
    },
    CAFE { // from class: com.naver.glink.android.sdk.api.requests.RequestBuilders.2
        private String firstPath() {
            return LoginHelper.LoginType.NAVER.isLogin(com.naver.glink.android.sdk.d.r()) ? "/glink/glink" : "/glink/glink_open";
        }

        private Map<String, String> headers() {
            HashMap map = new HashMap();
            map.put("User-Agent", RequestHelper.getUserAgent());
            map.put("X-Naver-Client-Id", com.naver.glink.android.sdk.d.b().d.b);
            map.put("X-Naver-Client-Secret", com.naver.glink.android.sdk.d.b().d.c);
            String accessToken = LoginHelper.LoginType.NAVER.getAccessToken();
            if (!TextUtils.isEmpty(accessToken)) {
                map.put(HttpRequest.HEADER_AUTHORIZATION, "bearer " + accessToken);
            }
            return map;
        }

        private Map<String, String> makeParameters(Map<String, String> map) {
            HashMap map2 = new HashMap();
            map2.put("cafeId", String.valueOf(com.naver.glink.android.sdk.d.b().d.a));
            map2.put("puuid", p.a(com.naver.glink.android.sdk.d.r()));
            if (com.naver.glink.android.sdk.d.h()) {
                map2.put("timeZoneId", RequestHelper.getTimeZoneId());
                map2.put("langCode", RequestHelper.getSystemLangCode());
            }
            if (map != null) {
                map2.putAll(map);
            }
            return map2;
        }

        @Override // com.naver.glink.android.sdk.api.requests.RequestBuilders
        public <T extends Response> RequestBuilder<T> builder(String str, Map<String, String> map, Class<T> cls) {
            return new RequestBuilder().host(com.naver.glink.android.sdk.d.b().c.a).path(firstPath() + (com.naver.glink.android.sdk.d.h() ? "/s1" : "/v2") + str).headers(headers()).parameters(makeParameters(map)).responseClass(cls).timeoutMs(10000);
        }
    },
    PLUG { // from class: com.naver.glink.android.sdk.api.requests.RequestBuilders.3
        private Map<String, String> headers() {
            HashMap map = new HashMap();
            map.put("User-Agent", RequestHelper.getUserAgent());
            map.put("X-NEOID-consumer-key", com.naver.glink.android.sdk.d.b().e.b);
            map.put("X-NEOID-service-id", LoginHelper.b());
            map.put("X-NEOID-service-key", LoginHelper.c());
            String accessToken = LoginHelper.LoginType.NEO_ID.getAccessToken();
            if (!TextUtils.isEmpty(accessToken)) {
                map.put("X-NEOID-access-token", accessToken);
            }
            return map;
        }

        private Map<String, String> makeParameters(Map<String, String> map) {
            HashMap map2 = new HashMap();
            map2.put("cafeId", String.valueOf(com.naver.glink.android.sdk.d.b().e.a));
            map2.put("timeZoneId", RequestHelper.getTimeZoneId());
            map2.put("langCode", RequestHelper.getSystemLangCode());
            map2.put("puuid", p.a(com.naver.glink.android.sdk.d.r()));
            if (a.b() != -1) {
                map2.put("channelId", String.valueOf(a.b()));
            }
            if (map != null) {
                map2.putAll(map);
            }
            return map2;
        }

        @Override // com.naver.glink.android.sdk.api.requests.RequestBuilders
        public <T extends Response> RequestBuilder<T> builder(String str, Map<String, String> map, Class<T> cls) {
            return new RequestBuilder().host(com.naver.glink.android.sdk.d.b().c.d).path("/plug/plug/v2" + str).headers(headers()).parameters(makeParameters(map)).responseClass(cls).timeoutMs(10000);
        }
    },
    AD { // from class: com.naver.glink.android.sdk.api.requests.RequestBuilders.4
        private Map<String, String> headers() {
            HashMap map = new HashMap();
            map.put("User-Agent", RequestHelper.getUserAgent());
            map.put("X-Naver-Client-Id", com.naver.glink.android.sdk.d.b().d.b);
            map.put("X-Naver-Client-Secret", com.naver.glink.android.sdk.d.b().d.c);
            String accessToken = LoginHelper.LoginType.NAVER.getAccessToken();
            if (!TextUtils.isEmpty(accessToken)) {
                map.put(HttpRequest.HEADER_AUTHORIZATION, "bearer " + accessToken);
            }
            return map;
        }

        @Override // com.naver.glink.android.sdk.api.requests.RequestBuilders
        public <T extends Response> RequestBuilder<T> builder(String str, Map<String, String> map, Class<T> cls) {
            return new RequestBuilder().host(com.naver.glink.android.sdk.a.c.a().b()).path(str).headers(headers()).responseClass(cls).timeoutMs(10000);
        }
    },
    PLUG_API_GW { // from class: com.naver.glink.android.sdk.api.requests.RequestBuilders.5
        private Map<String, String> headers() {
            HashMap map = new HashMap();
            map.put("User-Agent", RequestHelper.getUserAgent());
            map.put("consumerKey", com.naver.glink.android.sdk.d.b().e.b);
            if (NeoIdSdkManager.NeoIdTokenState.OK == NeoIdSdkManager.c()) {
                String strD = NeoIdSdkManager.d();
                if (!TextUtils.isEmpty(strD)) {
                    map.put(HttpRequest.HEADER_AUTHORIZATION, "Bearer " + strD);
                }
            }
            return map;
        }

        @Override // com.naver.glink.android.sdk.api.requests.RequestBuilders
        public <T extends Response> RequestBuilder<T> builder(String str, Map<String, String> map, Class<T> cls) {
            return new RequestBuilder().host(com.naver.glink.android.sdk.d.b().c.d).path("/plug" + str).headers(headers()).parameters(map).responseClass(cls).useHmac(true).timeoutMs(10000);
        }
    },
    LIKE { // from class: com.naver.glink.android.sdk.api.requests.RequestBuilders.6
        private Map<String, String> headers() {
            HashMap map = new HashMap();
            map.put(HttpRequest.HEADER_REFERER, "http://cafe.naver.com");
            return map;
        }

        @Override // com.naver.glink.android.sdk.api.requests.RequestBuilders
        public <T extends Response> RequestBuilder<T> builder(String str, Map<String, String> map, Class<T> cls) {
            return new RequestBuilder().host(com.naver.glink.android.sdk.d.b().c.d).path("/plug" + str).headers(headers()).parameters(map).responseClass(cls).timeoutMs(10000);
        }
    },
    LOCAL_LIKE { // from class: com.naver.glink.android.sdk.api.requests.RequestBuilders.7
        private Map<String, String> headers() {
            HashMap map = new HashMap();
            map.put("User-Agent", RequestHelper.getUserAgent());
            map.put(HttpRequest.HEADER_REFERER, "http://m.cafe.naver.com/andriod");
            map.put("X-Naver-Client-Id", com.naver.glink.android.sdk.d.b().d.b);
            map.put("X-Naver-Client-Secret", com.naver.glink.android.sdk.d.b().d.c);
            String accessToken = LoginHelper.LoginType.NAVER.getAccessToken();
            if (!TextUtils.isEmpty(accessToken)) {
                map.put(HttpRequest.HEADER_AUTHORIZATION, "bearer " + accessToken);
            }
            return map;
        }

        @Override // com.naver.glink.android.sdk.api.requests.RequestBuilders
        public <T extends Response> RequestBuilder<T> builder(String str, Map<String, String> map, Class<T> cls) {
            return new RequestBuilder().host(com.naver.glink.android.sdk.d.b().c.a).path("/glink" + str).headers(headers()).parameters(map).responseClass(cls).timeoutMs(10000);
        }
    };

    public <T extends Response> RequestBuilder<T> builder(Class<T> cls) {
        return builder(null, cls);
    }

    public abstract <T extends Response> RequestBuilder<T> builder(String str, Map<String, String> map, Class<T> cls);

    public <T extends Response> RequestBuilder<T> builder(Map<String, String> map, Class<T> cls) {
        return builder(null, map, cls);
    }

    public <T extends Response> Request<T> delete(String str, Class<T> cls) {
        return delete(str, null, cls);
    }

    public <T extends Response> Request<T> delete(String str, Map<String, String> map, Class<T> cls) {
        return builder(str, map, cls).method(3).toRequest();
    }

    public <T extends Response> Request<T> get(String str, Class<T> cls) {
        return get(str, null, cls);
    }

    public <T extends Response> Request<T> get(String str, Map<String, String> map, Class<T> cls) {
        return builder(str, map, cls).method(0).toRequest();
    }

    public <T extends Response> Request<T> post(String str, Class<T> cls) {
        return post(str, null, cls);
    }

    public <T extends Response> Request<T> post(String str, Map<String, String> map, Class<T> cls) {
        return builder(str, map, cls).method(1).toRequest();
    }

    public <T extends Response> Request<T> put(String str, Class<T> cls) {
        return put(str, null, cls);
    }

    public <T extends Response> Request<T> put(String str, Map<String, String> map, Class<T> cls) {
        return builder(str, map, cls).method(2).toRequest();
    }
}
