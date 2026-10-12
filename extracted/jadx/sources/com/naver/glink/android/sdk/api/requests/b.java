package com.naver.glink.android.sdk.api.requests;

import android.text.Html;
import android.text.TextUtils;
import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.ShareConstants;
import com.google.gson.GsonBuilder;
import com.naver.glink.android.sdk.a.q;
import com.naver.glink.android.sdk.a.v;
import com.naver.glink.android.sdk.model.Comment;
import com.naver.plug.android.core.api.Response;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestHelper;
import com.nexon.nexonanalyticssdk.NxLogInfo;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import io.fabric.sdk.android.services.settings.AppSettingsData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: CommentRequests.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    public static final int PAGE_SIZE = 20;

    /* JADX INFO: compiled from: CommentRequests.java */
    public static class a extends C0026b<C0025a> {

        /* JADX INFO: renamed from: com.naver.glink.android.sdk.api.requests.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: CommentRequests.java */
        public static class C0025a {
            public c.a comment;
            public c.C0027b count = c.C0027b.a();
            public c.a parent;
        }
    }

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.api.requests.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CommentRequests.java */
    public static class C0026b<T> extends Response {
        public int code;
        public String country;
        public String date;
        public String lang;
        public String message;
        public T result;
        public boolean success;

        public String a() {
            return this.message;
        }
    }

    /* JADX INFO: compiled from: CommentRequests.java */
    public static class c extends C0026b<e> {

        /* JADX INFO: compiled from: CommentRequests.java */
        public static class a extends com.naver.plug.android.core.a.a {
            public static final int INVALID_CAFE_MEMBER_LEVEL = -1;
            public int commentNo;
            private String contents;
            public boolean deleted;
            public String extension;
            public String formattedDate;
            public String lang;
            public boolean manager;
            public Map<String, String> mentions = Collections.emptyMap();
            public boolean mine;
            public boolean open;
            public int parentCommentNo;
            public String regTimeGmt;
            public int replyCount;
            public int replyLevel;
            private List<a> replyList;
            public int status;
            private String unescapeContents;
            public String userIdNo;
            public String userName;
            public String userProfileImage;
            public boolean visible;

            public String a() {
                if (this.formattedDate == null) {
                    this.formattedDate = q.a(this.regTimeGmt);
                }
                return this.formattedDate;
            }

            public boolean b() {
                return !c();
            }

            public boolean c() {
                return this.replyLevel > 1;
            }

            public int d() {
                int i = this.replyCount / 20;
                return this.replyCount % 20 > 0 ? i + 1 : i;
            }

            public String e() {
                if (this.unescapeContents == null) {
                    this.unescapeContents = v.b(this.contents);
                }
                return this.unescapeContents;
            }

            public String f() {
                String strE = e();
                Iterator<String> it = g().iterator();
                while (true) {
                    String str = strE;
                    if (!it.hasNext()) {
                        return str;
                    }
                    strE = str.replace(it.next(), "");
                }
            }

            public List<String> g() {
                ArrayList arrayList = new ArrayList();
                if (h()) {
                    Iterator<String> it = this.mentions.keySet().iterator();
                    while (it.hasNext()) {
                        arrayList.add("@" + it.next() + " ");
                    }
                }
                return arrayList;
            }

            public boolean h() {
                return (this.mentions == null || this.mentions.isEmpty()) ? false : true;
            }

            public String i() {
                if (h()) {
                    Iterator<String> it = this.mentions.keySet().iterator();
                    if (it.hasNext()) {
                        return it.next();
                    }
                }
                return null;
            }

            public String j() {
                if (h()) {
                    return this.mentions.get(i());
                }
                return null;
            }

            public int k() {
                try {
                    String string = TextUtils.isEmpty(this.extension) ? null : Html.fromHtml(this.extension).toString();
                    if (TextUtils.isEmpty(string)) {
                        return -1;
                    }
                    return ((C0028c) new GsonBuilder().create().fromJson(string, C0028c.class)).cafeMemberLevel;
                } catch (Exception e) {
                    return -1;
                }
            }

            public List<a> l() {
                return this.replyList != null ? this.replyList : Collections.emptyList();
            }
        }

        /* JADX INFO: renamed from: com.naver.glink.android.sdk.api.requests.b$c$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: CommentRequests.java */
        public static class C0027b {
            public int comment;
            public int exposeCount;
            public int reply;
            public int total;

            static C0027b a() {
                return new C0027b();
            }
        }

        /* JADX INFO: renamed from: com.naver.glink.android.sdk.api.requests.b$c$c, reason: collision with other inner class name */
        /* JADX INFO: compiled from: CommentRequests.java */
        public static class C0028c extends com.naver.plug.android.core.a.a {
            public final int cafeMemberLevel;

            C0028c(int i) {
                this.cafeMemberLevel = i;
            }
        }

        /* JADX INFO: compiled from: CommentRequests.java */
        public static class d {
            public int endRow;
            public int firstPage;
            public int indexSize;
            public int lastPage;
            public int nextPage;
            public int page;
            public int pageSize;
            public int prevPage;
            public int startIndex;
            public int startRow;
            public int totalPages;
            public int totalRows;
        }

        /* JADX INFO: compiled from: CommentRequests.java */
        public static class e {
            public d pageModel;
            public a parent;
            public C0027b count = C0027b.a();
            public List<a> commentList = Collections.emptyList();

            public int a() {
                return this.commentList.get(this.commentList.size() - 1).commentNo;
            }

            public boolean b() {
                if (this.pageModel == null) {
                    return true;
                }
                if (this.parent == null) {
                    return this.commentList.size() < this.pageModel.pageSize;
                }
                return this.pageModel.prevPage == 0;
            }

            public List<a> c() {
                ArrayList arrayList = new ArrayList(this.commentList);
                Collections.reverse(arrayList);
                return arrayList;
            }
        }
    }

    public static Request<c> a(int i, int i2) {
        Map<String, String> mapA = a(i);
        mapA.put("sort", AppSettingsData.STATUS_NEW);
        mapA.put("pageSize", String.valueOf(20));
        if (i2 != -1) {
            mapA.put("current", String.valueOf(i2));
        }
        return RequestBuilders.PLUG_API_GW.get("/cbox/v2_neo_list_json.json", mapA, c.class);
    }

    public static Request<c> a(int i, int i2, int i3) {
        Map<String, String> mapA = a(i);
        mapA.put("sort", "old");
        mapA.put(PlaceFields.PAGE, String.valueOf(i3));
        mapA.put("pageSize", String.valueOf(20));
        if (i2 != -1) {
            mapA.put("parentCommentNo", String.valueOf(i2));
        }
        return RequestBuilders.PLUG_API_GW.get("/cbox/v2_neo_list_json.json", mapA, c.class);
    }

    public static Request<a> a(int i, c.a aVar) {
        Map<String, String> mapA = a(i);
        mapA.put("commentNo", String.valueOf(aVar.commentNo));
        mapA.put("resultType", NxLogInfo.KEY_COMMENT);
        return RequestBuilders.PLUG_API_GW.post("/cbox/v2_neo_delete_json.json", mapA, a.class);
    }

    public static Request<a> a(Comment comment, int i) {
        String str;
        Map<String, String> mapA = a(comment.articleId);
        mapA.put("objectUrl", "http://www.naver.com");
        mapA.put("commentType", "txt");
        mapA.put("resultType", NxLogInfo.KEY_COMMENT);
        if (i != -1) {
            mapA.put(ShareConstants.MEDIA_EXTENSION, new c.C0028c(i).toJsonString());
        }
        if (TextUtils.isEmpty(comment.mentionId)) {
            mapA.put("contents", comment.content);
        } else {
            mapA.put("contents", String.format("@%s %s", comment.mentionId, comment.content));
        }
        if (comment.refCommentId != -1) {
            mapA.put("parentCommentNo", String.valueOf(comment.refCommentId));
        }
        if (TextUtils.isEmpty(comment.mentionId)) {
            str = "";
        } else {
            HashMap map = new HashMap();
            map.put("mentions[" + comment.mentionId + "]", comment.mentionNickname);
            str = "?" + RequestHelper.makeQueryString(map);
        }
        if (comment.id == -1) {
            return RequestBuilders.PLUG_API_GW.post("/cbox/v2_neo_create_json.json" + str, mapA, a.class);
        }
        mapA.put("commentNo", String.valueOf(comment.id));
        return RequestBuilders.PLUG_API_GW.post("/cbox/v2_neo_update_json.json" + str, mapA, a.class);
    }

    private static Map<String, String> a(int i) {
        HashMap map = new HashMap();
        map.put("lang", RequestHelper.getSystemLangCode());
        map.put("pool", "cbox2");
        map.put("clientType", "app-android");
        map.put("ticket", "glink");
        map.put("snsCode", com.naver.glink.android.sdk.login.neoid.c.b(com.naver.glink.android.sdk.d.r()));
        int i2 = com.naver.glink.android.sdk.d.b().e.a;
        int iB = com.naver.glink.android.sdk.api.requests.a.b();
        map.put("groupId", String.valueOf(i2));
        map.put("objectId", i2 + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + iB);
        map.put("categoryId", String.valueOf(i));
        return map;
    }
}
