package com.naver.glink.android.sdk.api.requests;

import android.text.TextUtils;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.login.neoid.NeoIdSdkManager;
import com.naver.plug.android.core.api.request.RenameFilePart;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestBuilder;
import com.naver.plug.android.core.api.request.RequestHelper;
import com.navercorp.volleyextensions.volleyer.http.ContentType;
import com.navercorp.volleyextensions.volleyer.multipart.StringPart;
import com.nexon.nexonanalyticssdk.NxLogSummaryInfos;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: GRequests.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c {
    public static Request<Responses.q> a() {
        return RequestBuilders.PLUG.get("/member/", Responses.q.class);
    }

    public static Request<com.naver.glink.android.sdk.api.a.C0019a> a(int i) {
        return RequestBuilders.PLUG.get("/articles/" + i, com.naver.glink.android.sdk.api.a.C0019a.class);
    }

    public static Request<com.naver.glink.android.sdk.api.a.b> a(Responses.d dVar, File file) {
        StringBuilder sbAppend = new StringBuilder().append("/").append(dVar.key).append("/simpleUpload/");
        int i = dVar.index;
        dVar.index = i + 1;
        String str = sbAppend.append(i).toString() + "?extractAnimatedCnt=true";
        HashMap map = new HashMap();
        map.put("User-Agent", RequestHelper.getUserAgent());
        map.put("consumerKey", com.naver.glink.android.sdk.d.b().e.b);
        if (NeoIdSdkManager.NeoIdTokenState.OK == NeoIdSdkManager.c()) {
            String strD = NeoIdSdkManager.d();
            if (!TextUtils.isEmpty(strD)) {
                map.put(HttpRequest.HEADER_AUTHORIZATION, "Bearer " + strD);
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new StringPart("serviceName", com.naver.glink.android.sdk.ui.profile.image.a.b));
        arrayList.add(new RenameFilePart(MessengerShareContentUtility.MEDIA_IMAGE, file, ContentType.createContentType(com.naver.glink.android.sdk.a.f.b(file))));
        return new RequestBuilder().method(1).host(com.naver.glink.android.sdk.d.b().c.e).path(str).headers(map).parts(arrayList).responseClass(com.naver.glink.android.sdk.api.a.b.class).responseParser(new i()).timeoutMs(60000).toRequest();
    }

    public static Request<Responses.q> a(String str) {
        return RequestBuilders.PLUG.get("/members/" + str, Responses.q.class);
    }

    public static Request<Responses.i> a(String str, int i) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            map.put("cafeLangCode", str);
        }
        if (i != -1) {
            map.put("channelId", String.valueOf(i));
        }
        RequestBuilder requestBuilderMethod = RequestBuilders.PLUG.builder("/cafe", map, Responses.i.class).method(0);
        if (i == -1) {
            requestBuilderMethod.parameters.remove("channelId");
        }
        return requestBuilderMethod.toRequest();
    }

    public static Request<Responses.p> a(String str, String str2, String str3, String str4) {
        HashMap map = new HashMap();
        map.put("articleType", str);
        if (!String.valueOf(-1).equals(str2)) {
            map.put(str3, str2);
        }
        map.put(NxLogSummaryInfos.KEY_COUNT, String.valueOf(10));
        return RequestBuilders.PLUG.get("/members/" + str4 + "/articles", map, Responses.p.class);
    }

    public static Request<Responses.d> b() {
        return RequestBuilders.PLUG.get("/uploader/photo/key", Responses.d.class);
    }

    public static Request<Responses.x> b(int i) {
        return RequestBuilders.PLUG.post("/report/articles/" + i, Responses.x.class);
    }

    public static Request<Responses.v> b(String str) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            map.put("text", str);
        }
        return RequestBuilders.PLUG.get("/translate", map, Responses.v.class);
    }

    public static Request<Responses.x> c() {
        return RequestBuilders.PLUG.get("/logout", Responses.x.class);
    }

    public static Request<com.naver.glink.android.sdk.api.a.h> c(int i) {
        HashMap map = new HashMap();
        map.put("articleId", String.valueOf(i));
        return RequestBuilders.PLUG.get("/translateArticle", map, com.naver.glink.android.sdk.api.a.h.class);
    }

    public static Request<com.naver.glink.android.sdk.api.a.c> d() {
        HashMap map = new HashMap();
        map.put("serviceId", com.naver.glink.android.sdk.ui.profile.image.a.b);
        return RequestBuilders.PLUG.builder(map, com.naver.glink.android.sdk.api.a.c.class).method(0).path("/gpop/v1/connections.json").toRequest();
    }
}
