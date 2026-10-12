package com.naver.glink.android.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.naver.glink.android.sdk.a.q;
import com.naver.plug.android.core.a.a;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Article extends a implements Parcelable {
    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() { // from class: com.naver.glink.android.sdk.model.Article.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Article createFromParcel(Parcel parcel) {
            Article article = new Article();
            article.articleId = parcel.readInt();
            article.writerId = parcel.readString();
            article.movie = parcel.readByte() != 0;
            article.commentCount = parcel.readInt();
            article.writeDateTime = parcel.readString();
            article.readCount = parcel.readInt();
            article.subject = parcel.readString();
            article.likeCount = parcel.readInt();
            article.writerNickname = parcel.readString();
            article.thumbnailImage = parcel.readString();
            article.likedItDateTime = parcel.readString();
            return article;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Article[] newArray(int i) {
            return new Article[i];
        }
    };
    public static final int INVALID_ARTICLE_ID = -1;
    public int articleId;
    public int commentCount;
    public String formattedWriteDate;
    public boolean gif;
    public int likeCount;
    public boolean likeIt;
    public String likedItDateTime;
    public boolean movie;
    public int readCount;
    public String subject;
    public String thumbnailImage;
    public String writeDate;
    public String writeDateTime;
    public String writerId;
    public String writerNickname;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getFormattedWriteDate() {
        if (TextUtils.isEmpty(this.writeDateTime)) {
            return this.writeDate;
        }
        if (this.formattedWriteDate == null) {
            this.formattedWriteDate = q.a(this.writeDateTime);
        }
        return this.formattedWriteDate;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.articleId);
        parcel.writeString(this.writerId);
        parcel.writeByte((byte) (this.movie ? 1 : 0));
        parcel.writeInt(this.commentCount);
        parcel.writeString(this.writeDateTime);
        parcel.writeInt(this.readCount);
        parcel.writeString(this.subject);
        parcel.writeInt(this.likeCount);
        parcel.writeString(this.writerNickname);
        parcel.writeString(this.thumbnailImage);
        parcel.writeString(this.likedItDateTime);
    }
}
