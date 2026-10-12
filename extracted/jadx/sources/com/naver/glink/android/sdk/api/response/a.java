package com.naver.glink.android.sdk.api.response;

import com.naver.plug.android.core.api.Response;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: AllMediaResponse.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a extends Response {
    private List<ArticleMedia> items;
    public C0030a metadata;
    private List<ArticleMedia> videos = Collections.emptyList();
    private List<ArticleMedia> photos = Collections.emptyList();

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.api.response.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AllMediaResponse.java */
    public static class C0030a extends com.naver.plug.android.core.a.a {
        public boolean isLast;
        public int lastArticleId;
        public int lastAttachId;
    }

    public List<ArticleMedia> a() {
        if (this.items == null) {
            Iterator<ArticleMedia> it = this.videos.iterator();
            while (it.hasNext()) {
                it.next().type = ArticleMedia.Type.VIDEO;
            }
            Iterator<ArticleMedia> it2 = this.photos.iterator();
            while (it2.hasNext()) {
                it2.next().type = ArticleMedia.Type.PHOTO;
            }
            this.items = this.videos.isEmpty() ? this.photos : this.videos;
        }
        return this.items;
    }
}
