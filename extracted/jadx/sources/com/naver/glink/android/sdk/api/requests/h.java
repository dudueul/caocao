package com.naver.glink.android.sdk.api.requests;

import com.facebook.appevents.UserDataStore;
import com.naver.plug.android.core.api.Response;
import com.naver.plug.android.core.api.request.BytePart;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestBuilder;
import com.naver.plug.android.core.api.request.RequestHelper;
import com.navercorp.volleyextensions.volleyer.http.ContentType;
import com.navercorp.volleyextensions.volleyer.multipart.AbstractPart;
import com.navercorp.volleyextensions.volleyer.multipart.FilePart;
import com.navercorp.volleyextensions.volleyer.multipart.StringPart;
import com.tapjoy.TJAdUnitConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: VideoUploadRequests.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class h {
    public static final int CHUNK_SIZE = 5242880;
    public static final int DEFAULT_ATTACHMENT_TIMEOUT_MS = 60000;
    public static final int DEFAULT_SEND_LOGO_TIMEOUT_MS = 300000;
    private static final String ENCODE_TYPE = "normal";
    public static final int MINUTES = 60000;
    private static final String PREFIX_USER_ID = "plug";
    public static final int SECONDS = 1000;
    static final int SERVICE_ID = 2027;
    static final int SERVICE_ID_DEV = 22027;
    private static String host;

    /* JADX INFO: compiled from: VideoUploadRequests.java */
    public static class a extends Response {
        public boolean resultCode;
    }

    /* JADX INFO: compiled from: VideoUploadRequests.java */
    public static class b extends Response {
        public boolean resultCode;
        public int uploadedChunkNumber;
    }

    /* JADX INFO: compiled from: VideoUploadRequests.java */
    public static class c extends Response {
        public String key;
        public boolean resultCode;
    }

    /* JADX INFO: compiled from: VideoUploadRequests.java */
    public static class d extends Response {
        public a data;
        public String key;
        public boolean resultCode;

        /* JADX INFO: compiled from: VideoUploadRequests.java */
        public class a {
            public String chunkNum;
            public String chunkSize;

            public a() {
            }
        }
    }

    /* JADX INFO: compiled from: VideoUploadRequests.java */
    public static class e extends Response {
        public a data;
        public boolean resultCode;

        /* JADX INFO: compiled from: VideoUploadRequests.java */
        public static class a {
            public String audioBitrate;
            public String fileSize;
            public String fps;
            public String inKey;
            public String logoImage;
            public String playTime;
            public String sourceHeight;
            public String sourceWidth;
            public String videoBitrate;
            public String videoId;
        }
    }

    /* JADX INFO: compiled from: VideoUploadRequests.java */
    public static class f extends Response {
        public a data;
        public boolean resultCode;

        /* JADX INFO: compiled from: VideoUploadRequests.java */
        public static class a {
            public List<String> thumbnailList;
        }
    }

    public static Request<d> a(int i, long j, String str, File file) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new StringPart("key", str));
        arrayList.add(new StringPart("sid", String.valueOf(d())));
        arrayList.add(new StringPart("fs", String.valueOf(file.length())));
        arrayList.add(new StringPart(UserDataStore.FIRST_NAME, file.getName()));
        arrayList.add(new StringPart("cn", String.valueOf(i)));
        arrayList.add(new StringPart("cs", String.valueOf(CHUNK_SIZE)));
        arrayList.add(new StringPart("ccs", String.valueOf(j)));
        arrayList.add(new StringPart("userId", "plug"));
        arrayList.add(new FilePart("file", file, ContentType.createContentType(com.naver.glink.android.sdk.a.f.b(file))));
        return a("multipartChunk.nhn", (Map<String, String>) null, arrayList, d.class);
    }

    public static Request<d> a(int i, long j, String str, File file, int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new StringPart("key", str));
        arrayList.add(new StringPart("sid", String.valueOf(d())));
        arrayList.add(new StringPart("fs", String.valueOf(file.length())));
        arrayList.add(new StringPart(UserDataStore.FIRST_NAME, file.getName()));
        arrayList.add(new StringPart("cn", String.valueOf(i)));
        arrayList.add(new StringPart("cs", String.valueOf(CHUNK_SIZE)));
        arrayList.add(new StringPart("ccs", String.valueOf(j)));
        arrayList.add(new StringPart("userId", "plug"));
        arrayList.add(new BytePart("file", ContentType.createContentType(com.naver.glink.android.sdk.a.f.b(file)), file, i2, (int) j));
        return a("multipartChunk.nhn", (Map<String, String>) null, arrayList, d.class);
    }

    private static <T extends Response> Request<T> a(int i, String str, Map<String, String> map, List<AbstractPart> list, Class<T> cls, int i2) {
        return new RequestBuilder().method(i).host(host).path("/upload/" + str).headers(b()).parameters(map).parts(list).responseClass(cls).timeoutMs(i2).toRequest();
    }

    public static Request<c> a(long j, String str) {
        Map<String, String> mapC = c();
        mapC.put("cs", String.valueOf(CHUNK_SIZE));
        mapC.put("fs", String.valueOf(j));
        mapC.put(UserDataStore.FIRST_NAME, str);
        mapC.put("userId", "plug");
        return a("getUploadKey.nhn", mapC, c.class);
    }

    public static Request<b> a(c cVar, int i, String str) {
        Map<String, String> mapC = c();
        mapC.put("key", cVar.key);
        mapC.put("fs", String.valueOf(i));
        mapC.put(UserDataStore.FIRST_NAME, str);
        mapC.put("userId", "plug");
        return a("getUploadInfo.nhn", mapC, b.class);
    }

    public static Request<f> a(String str, String str2) {
        Map<String, String> mapC = c();
        mapC.put("key", str);
        mapC.put(UserDataStore.FIRST_NAME, str2);
        mapC.put("encodeType", "normal");
        mapC.put("userId", "plug");
        return a("getVideoInfo.nhn", mapC, f.class);
    }

    public static Request<a> a(String str, String str2, String str3) {
        Map<String, String> mapC = c();
        mapC.put("key", str);
        mapC.put(UserDataStore.FIRST_NAME, str2);
        mapC.put("userId", "plug");
        mapC.put("chunkList", str3);
        return a("completeUploadFile.nhn", mapC, a.class);
    }

    private static <T extends Response> Request<T> a(String str, Map<String, String> map, Class<T> cls) {
        return a(0, str, map, null, cls, 10000);
    }

    private static <T extends Response> Request<T> a(String str, Map<String, String> map, List<AbstractPart> list, Class<T> cls) {
        return a(1, str, map, list, cls, 60000);
    }

    public static void a(String str) {
        host = str;
    }

    public static boolean a() {
        return host == null;
    }

    public static Request<e> b(String str, String str2, String str3) {
        Map<String, String> mapC = c();
        mapC.put("key", str);
        mapC.put(UserDataStore.FIRST_NAME, str2);
        mapC.put("logoImage", str3);
        mapC.put("userId", "plug");
        return b("sendLogoImage.nhn", mapC, e.class);
    }

    private static <T extends Response> Request<T> b(String str, Map<String, String> map, Class<T> cls) {
        return a(1, str, map, null, cls, DEFAULT_SEND_LOGO_TIMEOUT_MS);
    }

    private static Map<String, String> b() {
        HashMap map = new HashMap();
        map.put("User-Agent", RequestHelper.getUserAgent());
        map.put("Connection", TJAdUnitConstants.String.CLOSE);
        return map;
    }

    private static Map<String, String> c() {
        HashMap map = new HashMap();
        map.put("sid", String.valueOf(d()));
        return map;
    }

    private static int d() {
        return com.naver.glink.android.sdk.d.m() ? SERVICE_ID_DEV : SERVICE_ID;
    }
}
