package com.naver.glink.android.sdk.model;

import com.naver.plug.android.core.a.a;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Comment extends a {
    public static final int INVALID_COMMENT_ID = -1;
    public final int articleId;
    public String content = "";
    public final int id;
    public final String mentionId;
    public final String mentionNickname;
    public final int refCommentId;
    public final String targetMemberId;

    Comment(int i, int i2, int i3, String str, String str2, String str3) {
        this.id = i;
        this.articleId = i2;
        this.refCommentId = i3;
        this.targetMemberId = str;
        this.mentionId = str2;
        this.mentionNickname = str3;
    }

    public static Comment newComment(int i, int i2) {
        return new Comment(i, i2, -1, null, null, null);
    }

    public static Comment newComment(int i, int i2, int i3, String str) {
        return new Comment(i, i2, i3, str, null, null);
    }

    public static Comment newComment(int i, int i2, int i3, String str, String str2) {
        return new Comment(i, i2, i3, null, str, str2);
    }

    public boolean isNewComment() {
        return this.id == -1;
    }
}
