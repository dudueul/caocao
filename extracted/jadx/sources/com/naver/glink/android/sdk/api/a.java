package com.naver.glink.android.sdk.api;

import android.text.TextUtils;
import com.facebook.GraphResponse;
import com.google.gson.annotations.Expose;
import com.naver.glink.android.sdk.ui.write.model.Content;
import com.naver.plug.android.core.api.Response;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: GResponses.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.api.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GResponses.java */
    public static class C0019a extends Response {
        public int articleId;
        public int cafeMemberLevel;
        public int channelId;
        public String commentAuthDesc;
        public String contents;
        public boolean isCommentWritable;
        public boolean isLikeable;
        public boolean isWriter;

        @Expose(deserialize = false, serialize = false)
        private List<Content> items;
        public String likeAuthDesc;
        public String linkUrl;
        public int menuId;
        public String menuName;
        public int readCount;
        public String subject;
        public String writeDateTime;
        public String writerId;
        public int writerMemberLevel;
        public String writerMemberTypeName;
        public String writerNickname;
        public String writerProfileImageUrl;

        public List<Content> a() {
            if (this.items == null) {
                this.items = com.naver.glink.android.sdk.ui.write.model.a.a(this.contents);
            }
            return this.items;
        }
    }

    /* JADX INFO: compiled from: GResponses.java */
    public static class b extends Response {
        public int animatedCnt;
        public String colorSpace;
        public String fileName;
        public int fileSize;
        public int height;
        public int index;
        public String path;
        public String thumbnail;
        public String url;
        public int width;
    }

    /* JADX INFO: compiled from: GResponses.java */
    public static class c extends Response {
        public long buildDate;
        public b connection;
        public e optional;

        /* JADX INFO: renamed from: com.naver.glink.android.sdk.api.a$c$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GResponses.java */
        public static class C0020a extends f {
            public f fallback;
        }

        /* JADX INFO: compiled from: GResponses.java */
        public static class b {
            public C0021c apis;
        }

        /* JADX INFO: renamed from: com.naver.glink.android.sdk.api.a$c$c, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GResponses.java */
        public static class C0021c extends d {
            public d fallback;
        }

        /* JADX INFO: compiled from: GResponses.java */
        public static class d {
            private String ssl;
            private String url;

            public String a() {
                return com.naver.glink.android.sdk.d.o() ? this.ssl : this.url;
            }
        }

        /* JADX INFO: compiled from: GResponses.java */
        public static class e {
            public C0020a apis;
        }

        /* JADX INFO: compiled from: GResponses.java */
        public static class f {
            public String finish_path;
            private String login;
            private String login_stg;
            public List<String> video;

            public String a() {
                return com.naver.glink.android.sdk.d.n() ? this.login_stg : this.login;
            }
        }
    }

    /* JADX INFO: compiled from: GResponses.java */
    public static class d extends Response {
        public String contentsId;
        public String countType;
        public String guestToken;
        public boolean isDisplay;
        public List<C0022a> reactions = Collections.emptyList();
        public String serviceId;
        public String timestamp;

        /* JADX INFO: renamed from: com.naver.glink.android.sdk.api.a$d$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GResponses.java */
        public static class C0022a extends com.naver.plug.android.core.a.a {
            public int count;
            public boolean isReacted;
            public String reactionType;
        }

        private C0022a c() {
            for (C0022a c0022a : this.reactions) {
                if (TextUtils.equals(c0022a.reactionType, com.naver.glink.android.sdk.api.requests.e.LIKE_REACTION_TYPE)) {
                    return c0022a;
                }
            }
            return null;
        }

        public boolean a() {
            C0022a c0022aC = c();
            return c0022aC != null && c0022aC.isReacted;
        }

        public int b() {
            C0022a c0022aC = c();
            if (c0022aC != null) {
                return c0022aC.count;
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: GResponses.java */
    public static class e extends Response {
        public String categoryId;
        public String contentsId;
        public int count;
        public String countType;
        public String displayId;
        public boolean isReacted;
        public String reactionType;
        public String serviceId;
    }

    /* JADX INFO: compiled from: GResponses.java */
    public static class f extends com.naver.plug.android.core.a.a {
        public int rtn_cd;
        public C0023a rtn_data;
        public String rtn_msg;

        /* JADX INFO: renamed from: com.naver.glink.android.sdk.api.a$f$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GResponses.java */
        public static class C0023a {
            public String id;
            public String id_no;
            public int login_status;
            public int memberLevel;
            public String mode;
            public String sns_cd;
            public String sns_id;
            public String sns_nick;
            public String sns_token;
            public String sns_token_secret;
            public String stat_cd;
            public String svc_cd;
            public String svc_url;
            public String token;
            public String token_secret;
            public String token_type;
        }

        public boolean a() {
            return GraphResponse.SUCCESS_KEY.equalsIgnoreCase(this.rtn_msg);
        }
    }

    /* JADX INFO: compiled from: GResponses.java */
    public static class g extends Response {
        public int index = 0;
        public String key;
        public String uploaderIp;
    }

    /* JADX INFO: compiled from: GResponses.java */
    public static class h extends Response {
        public String contents;

        @Expose(deserialize = false, serialize = false)
        private List<Content> items;
        public String subject;

        public List<Content> a() {
            if (this.items == null) {
                this.items = com.naver.glink.android.sdk.ui.write.model.a.a(this.contents);
            }
            return this.items;
        }
    }
}
