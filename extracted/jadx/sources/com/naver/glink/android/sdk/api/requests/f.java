package com.naver.glink.android.sdk.api.requests;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.facebook.places.model.PlaceFields;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.a.w;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.glink.android.sdk.model.Comment;
import com.naver.glink.android.sdk.ui.write.model.WritingArticle;
import com.naver.plug.android.core.api.Response;
import com.naver.plug.android.core.api.request.RenameFilePart;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;
import com.navercorp.volleyextensions.volleyer.http.ContentType;
import com.navercorp.volleyextensions.volleyer.multipart.AbstractPart;
import com.navercorp.volleyextensions.volleyer.multipart.FilePart;
import com.navercorp.volleyextensions.volleyer.multipart.StringPart;
import com.nexon.nexonanalyticssdk.NxLogSummaryInfos;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.tapjoy.TJAdUnitConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog;

/* JADX INFO: compiled from: Requests.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class f {
    static final int DEFAULT_ATTACHMENT_TIMEOUT_MS = 60000;
    public static final int DEFAULT_TIMEOUT_MS = 10000;
    static final int MINUTES = 60000;
    static final int SECONDS = 1000;

    /* JADX INFO: compiled from: Requests.java */
    public static class a extends Response {
        public String userId;
    }

    public static Request<Responses.i> a() {
        return RequestBuilders.CAFE.get("/cafe", Responses.i.class);
    }

    public static Request<Responses.x> a(int i) {
        return RequestBuilders.COMMON.delete("/articles/" + i, Responses.x.class);
    }

    public static Request<Responses.m> a(int i, int i2) {
        HashMap map = new HashMap();
        map.put(PlaceFields.PAGE, String.valueOf(i2));
        return RequestBuilders.CAFE.get("/articles/" + i + "/comments", map, Responses.m.class);
    }

    public static Request<Responses.c> a(int i, int i2, int i3) {
        HashMap map = new HashMap();
        if (i != 0) {
            map.put("menuId", String.valueOf(i));
        }
        if (i2 > -1) {
            map.put("lastArticleId", String.valueOf(i2));
        }
        map.put(NxLogSummaryInfos.KEY_COUNT, String.valueOf(i3));
        return RequestBuilders.COMMON.get("/articles", map, Responses.c.class);
    }

    public static Request<Responses.c> a(int i, String str, int i2, int i3, int i4) {
        HashMap map = new HashMap();
        if (i == 0) {
            map.put("menuId", String.valueOf(0));
        } else {
            map.put("menuId", String.valueOf(i));
            map.put("searchScope", "MENU");
        }
        map.put("query", str);
        map.put(PlaceFields.PAGE, String.valueOf(i2));
        map.put(NxLogSummaryInfos.KEY_COUNT, String.valueOf(i3));
        map.put("sortBy", String.valueOf(i4));
        return RequestBuilders.COMMON.get("/search", map, Responses.c.class);
    }

    public static Request<Response> a(int i, boolean z) {
        String str = "/articles/" + i;
        HashMap map = new HashMap();
        map.put(TJAdUnitConstants.String.PORTRAIT, String.valueOf(z));
        return RequestBuilders.CAFE.builder(str, map, Response.class).method(0).host(com.naver.glink.android.sdk.d.b().c.c).toRequest();
    }

    public static Request<Responses.e> a(Responses.d dVar, File file) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new StringPart("cafeId", String.valueOf(com.naver.glink.android.sdk.d.b().d.a)));
        arrayList.add(new StringPart("width", String.valueOf(com.naver.glink.android.sdk.ui.write.a.a)));
        arrayList.add(new StringPart("key", dVar.key));
        arrayList.add(new RenameFilePart("file", file, ContentType.createContentType(com.naver.glink.android.sdk.a.f.b(file))));
        return a("/AttachImage.nhn", (Map<String, String>) null, arrayList, Responses.e.class);
    }

    public static Request<Responses.l> a(Comment comment) {
        HashMap map = new HashMap();
        map.put("articleId", String.valueOf(comment.articleId));
        map.put("content", w.a(comment.content));
        if (comment.refCommentId != -1) {
            map.put("refCommentId", String.valueOf(comment.refCommentId));
        }
        if (!TextUtils.isEmpty(comment.targetMemberId)) {
            map.put("targetMemberId", comment.targetMemberId);
        }
        if (comment.id == -1) {
            return RequestBuilders.CAFE.post("/articles/" + comment.articleId + "/comments", map, Responses.l.class);
        }
        map.put("commentId", String.valueOf(comment.id));
        return RequestBuilders.CAFE.put("/articles/" + comment.articleId + "/comments/" + comment.id, map, Responses.l.class);
    }

    public static Request<Responses.w> a(WritingArticle writingArticle) {
        if (com.naver.glink.android.sdk.d.k()) {
            String str = writingArticle.g() ? "/articles" : "/articles/" + writingArticle.b();
            HashMap map = new HashMap();
            map.put("menuId", String.valueOf(writingArticle.c()));
            map.put("subject", w.a(writingArticle.d()));
            map.put("jSonResolvedArticle", writingArticle.p());
            return RequestBuilders.CAFE.builder(str, map, Responses.w.class).method(writingArticle.g() ? 1 : 2).timeoutMs(BaseImageDownloader.DEFAULT_HTTP_READ_TIMEOUT).toRequest();
        }
        String str2 = writingArticle.g() ? "/articles" : "/articles/" + writingArticle.b();
        HashMap map2 = new HashMap();
        map2.put("menuId", String.valueOf(writingArticle.c()));
        map2.put("subject", writingArticle.d());
        map2.put("contents", writingArticle.q());
        return RequestBuilders.PLUG.builder(str2, map2, Responses.w.class).method(writingArticle.g() ? 1 : 2).timeoutMs(BaseImageDownloader.DEFAULT_HTTP_READ_TIMEOUT).toRequest();
    }

    public static Request<Responses.s> a(String str) {
        return RequestBuilders.AD.get(str, Responses.s.class);
    }

    private static Request<com.naver.glink.android.sdk.api.response.a> a(String str, int i, int i2, int i3) {
        HashMap map = new HashMap();
        map.put(NxLogSummaryInfos.KEY_COUNT, String.valueOf(i));
        if (i2 != -1) {
            map.put("lastArticleId", String.valueOf(i2));
        }
        if (i3 != -1) {
            map.put("lastAttachId", String.valueOf(i3));
        }
        return RequestBuilders.COMMON.get(str, map, com.naver.glink.android.sdk.api.response.a.class);
    }

    public static Request<Responses.q> a(String str, String str2) {
        HashMap map = new HashMap();
        map.put("articleType", w.a(str));
        return RequestBuilders.CAFE.get("/members/" + str2, map, Responses.q.class);
    }

    public static Request<Response> a(String str, String str2, String str3, String str4) {
        HashMap map = new HashMap();
        map.put(NPNXComWebDialog.NICKNAME, w.a(str2));
        map.put("profileImageStatus", str4);
        if (str3 != null) {
            map.put("profileImageUrl", str3);
        }
        return RequestBuilders.COMMON.put("/members/" + str, map, Response.class);
    }

    public static Request<Responses.n> a(String str, String str2, boolean z) {
        HashMap map = new HashMap();
        map.put(NPNXComWebDialog.NICKNAME, w.a(str));
        map.put("gameUserId", w.a(str2));
        map.put("agreeyn", String.valueOf(z));
        return RequestBuilders.CAFE.post("/members", map, Responses.n.class);
    }

    private static <T extends Response> Request<T> a(String str, Map<String, String> map, List<AbstractPart> list, Class<T> cls) {
        return RequestBuilders.CAFE.builder(cls).method(1).host(com.naver.glink.android.sdk.d.b().c.b).path("/glink" + str).parameters(map).parts(list).timeoutMs(60000).toRequest();
    }

    public static Request<Responses.x> a(Map<String, String> map) {
        return RequestBuilders.CAFE.builder(map, Responses.x.class).method(1).host(com.naver.glink.android.sdk.d.b().c.a).path(g() + "/plug_proxy/v1/statistics/game").toRequest();
    }

    public static Request<Responses.r> a(boolean z) {
        HashMap map = new HashMap();
        map.put("search", String.valueOf(z));
        return RequestBuilders.COMMON.get("/menus", map, Responses.r.class);
    }

    public static void a(final Context context) {
        if (LoginHelper.a().isLogin(context)) {
            final p.a aVarC = p.c(context);
            if (aVarC.a(context)) {
                p.a(context, (p.a) null);
            } else {
                d(aVarC.a).execute(context, new RequestListener<Responses.x>() { // from class: com.naver.glink.android.sdk.api.requests.f.1
                    @Override // com.naver.plug.android.core.api.request.RequestListener
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(@NonNull Responses.x xVar) {
                        p.c(context, aVarC.a);
                        p.a(context, (p.a) null);
                    }
                });
            }
        }
    }

    public static void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            p.a(context, (p.a) null);
        } else {
            p.a(context, new p.a(str));
            a(context);
        }
    }

    public static Request<Responses.h> b() {
        return RequestBuilders.COMMON.get("/channel", Responses.h.class);
    }

    public static Request<Responses.y> b(int i) {
        HashMap map = new HashMap();
        map.put("articleId", String.valueOf(i));
        return RequestBuilders.COMMON.get("/modify/article", map, Responses.y.class);
    }

    public static Request<com.naver.glink.android.sdk.api.response.a> b(int i, int i2, int i3) {
        return a("/photos", i, i2, i3);
    }

    public static Request<Responses.f> b(Responses.d dVar, File file) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new StringPart("cafeId", String.valueOf(com.naver.glink.android.sdk.d.b().d.a)));
        arrayList.add(new StringPart("key", dVar.key));
        arrayList.add(new FilePart("file", file, ContentType.createContentType(com.naver.glink.android.sdk.a.f.b(file))));
        return a("/AttachMovie.nhn", (Map<String, String>) null, arrayList, Responses.f.class);
    }

    public static Request<Responses.k> b(Comment comment) {
        HashMap map = new HashMap();
        map.put("articleId", String.valueOf(comment.articleId));
        map.put("commentId", String.valueOf(comment.id));
        return RequestBuilders.CAFE.delete("/articles/" + comment.articleId + "/comments/" + comment.id, map, Responses.k.class);
    }

    public static Request<Responses.g> b(String str) {
        HashMap map = new HashMap();
        map.put(NPNXComWebDialog.NICKNAME, str);
        return RequestBuilders.COMMON.get("/check/nickname", map, Responses.g.class);
    }

    public static Request<Responses.p> b(String str, String str2, String str3, String str4) {
        HashMap map = new HashMap();
        map.put("articleType", w.a(str));
        if (!String.valueOf(-1).equals(str2)) {
            map.put(str3, str2);
        }
        map.put(NxLogSummaryInfos.KEY_COUNT, String.valueOf(10));
        return RequestBuilders.CAFE.get("/members/" + str4 + "/articles", map, Responses.p.class);
    }

    public static void b(Context context, String str) {
        h(str).execute(context);
    }

    public static Request<Responses.BannersResponse> c() {
        return RequestBuilders.COMMON.get("/banners", Responses.BannersResponse.class);
    }

    public static Request<Responses.b> c(int i) {
        HashMap map = new HashMap();
        map.put("articleId", String.valueOf(i));
        return RequestBuilders.CAFE.get("/articles/" + i + "/metadata", map, Responses.b.class);
    }

    public static Request<com.naver.glink.android.sdk.api.response.a> c(int i, int i2, int i3) {
        return a("/videos", i, i2, i3);
    }

    public static Request<Responses.q> c(String str) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            map.put("articleType", w.a(str));
        }
        return RequestBuilders.CAFE.get("/member", map, Responses.q.class);
    }

    public static Request<Responses.y> d() {
        return RequestBuilders.COMMON.get("/write/article", Responses.y.class);
    }

    public static Request<Responses.a> d(int i) {
        return RequestBuilders.CAFE.post("/articles/" + i + "/likes", Responses.a.class);
    }

    public static Request<Responses.x> d(String str) {
        HashMap map = new HashMap();
        map.put("gameUserId", str);
        Request<Responses.x> requestPost = RequestBuilders.COMMON.post("/glinkUsers", map, Responses.x.class);
        requestPost.setRetryLoginError(false);
        return requestPost;
    }

    public static Request<Responses.d> e() {
        return RequestBuilders.CAFE.get("/attach/auth", Responses.d.class);
    }

    public static Request<Responses.t> e(String str) {
        HashMap map = new HashMap();
        map.put("videoId", str);
        return RequestBuilders.COMMON.get("/playurl/video", map, Responses.t.class);
    }

    public static Request<a> f() {
        return RequestBuilders.COMMON.get("/user", a.class);
    }

    public static Request<Response> f(String str) {
        return RequestBuilders.COMMON.builder(str, null, Response.class).method(0).host(com.naver.glink.android.sdk.d.b().c.c).toRequest();
    }

    public static Request<Responses.c> g(String str) {
        HashMap map = new HashMap();
        map.put("listType", str);
        return RequestBuilders.COMMON.get("/popularArticles", map, Responses.c.class);
    }

    private static String g() {
        return LoginHelper.LoginType.NAVER.isLogin(com.naver.glink.android.sdk.d.r()) ? "/glink" : "/glink_open";
    }

    private static Request<Responses.x> h(String str) {
        HashMap map = new HashMap();
        map.put("type", str);
        return RequestBuilders.COMMON.post("/statistics", map, Responses.x.class);
    }
}
