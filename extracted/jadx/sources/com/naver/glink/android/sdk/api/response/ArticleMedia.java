package com.naver.glink.android.sdk.api.response;

import android.net.Uri;
import android.text.TextUtils;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.q;
import com.naver.glink.android.sdk.a.y;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.write.model.Attachment;
import com.naver.glink.android.sdk.ui.write.model.ImageAttachment;
import com.naver.glink.android.sdk.ui.write.model.VideoAttachment;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ArticleMedia {
    private boolean deleted;
    private String formattedWriteDate;
    public boolean gif;
    public int height;
    public String logoImage;
    public float playTime;
    private boolean scraped;
    public String subject;
    private String thumbnailImage;
    public Type type;
    public String url;
    public String vid;
    public int width;
    private String writeDateTime;
    public String writerNickname;
    public int articleId = -1;
    private boolean hasAuthority = true;

    public enum Type {
        PHOTO,
        VIDEO,
        NONE
    }

    public static ArticleMedia a(Responses.BannersResponse.HomeBanner homeBanner) {
        if (!homeBanner.a()) {
            return null;
        }
        ArticleMedia articleMedia = new ArticleMedia();
        articleMedia.type = Type.VIDEO;
        articleMedia.vid = homeBanner.videoId;
        articleMedia.logoImage = homeBanner.imageUrl;
        return articleMedia;
    }

    public static List<ArticleMedia> a(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            ArticleMedia articleMedia = new ArticleMedia();
            articleMedia.type = Type.PHOTO;
            articleMedia.url = str;
            arrayList.add(articleMedia);
        }
        return arrayList;
    }

    public static List<ArticleMedia> b(List<Attachment> list) {
        ArrayList arrayList = new ArrayList();
        for (Attachment attachment : list) {
            if (attachment instanceof ImageAttachment) {
                String string = ((ImageAttachment) attachment).g().toString();
                if (!TextUtils.isEmpty(string)) {
                    ArticleMedia articleMedia = new ArticleMedia();
                    articleMedia.type = Type.PHOTO;
                    articleMedia.url = string;
                    arrayList.add(articleMedia);
                }
            } else if (attachment instanceof VideoAttachment) {
                VideoAttachment videoAttachment = (VideoAttachment) attachment;
                String strC = videoAttachment.c();
                String strD = videoAttachment.d();
                if (!TextUtils.isEmpty(strC) && !TextUtils.isEmpty(strD)) {
                    ArticleMedia articleMedia2 = new ArticleMedia();
                    articleMedia2.type = Type.VIDEO;
                    articleMedia2.vid = videoAttachment.c();
                    articleMedia2.logoImage = videoAttachment.d();
                    arrayList.add(articleMedia2);
                }
            }
        }
        return arrayList;
    }

    public boolean a() {
        return this.type == Type.PHOTO;
    }

    public boolean a(boolean z) {
        return this.scraped || this.deleted || (!this.hasAuthority && z);
    }

    public String b(boolean z) {
        if (!this.hasAuthority && z) {
            return d.a(R.string.all_media_no_permissions);
        }
        if (this.scraped) {
            return z ? "목록에서 재생할 수 없는 동영상입니다." : "Scrapped";
        }
        return this.deleted ? "Deleted" : "";
    }

    public boolean b() {
        return this.type == Type.VIDEO;
    }

    public boolean c() {
        return this.hasAuthority;
    }

    public String d() {
        if (this.formattedWriteDate == null) {
            this.formattedWriteDate = q.a(this.writeDateTime);
        }
        return this.formattedWriteDate;
    }

    public Uri e() {
        return a() ? y.a(this.url).buildUpon().appendQueryParameter("type", "wa1280").build() : y.a(this.logoImage);
    }

    public Uri f() {
        return a() ? y.a(this.url).buildUpon().appendQueryParameter("type", "ff159_159").build() : y.a(this.thumbnailImage);
    }
}
