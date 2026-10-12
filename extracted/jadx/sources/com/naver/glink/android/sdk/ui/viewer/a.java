package com.naver.glink.android.sdk.ui.viewer;

import com.naver.glink.android.sdk.api.response.ArticleMedia;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.article.c;
import com.naver.glink.android.sdk.ui.viewer.a.b;
import com.naver.glink.android.sdk.ui.write.model.Attachment;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: MediaViewerHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    private static final String a = "com.naver.glink.image-viewer";

    public static void a(c cVar, Attachment attachment) {
        List<Attachment> listP = cVar.p();
        List<ArticleMedia> listB = ArticleMedia.b(listP);
        if (listB.isEmpty()) {
            return;
        }
        MediaViewerDialog.From from = MediaViewerDialog.From.ARTICLE;
        int iIndexOf = listP.indexOf(attachment);
        if (iIndexOf == -1) {
            iIndexOf = 0;
        }
        a(from, listB, iIndexOf);
    }

    public static void a(MediaViewerDialog.From from, List<ArticleMedia> list, int i) {
        a(MediaViewerDialog.a(d.r(), from, new b(list), i));
    }

    public static void a(MediaViewerDialog mediaViewerDialog) {
        mediaViewerDialog.a(a);
    }

    public static void a(String str) {
        a(MediaViewerDialog.From.PROFILE, ArticleMedia.a((List<String>) Arrays.asList(str)), 0);
    }
}
