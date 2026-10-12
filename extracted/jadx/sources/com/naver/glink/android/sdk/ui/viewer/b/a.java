package com.naver.glink.android.sdk.ui.viewer.b;

import android.content.Context;
import android.view.View;
import com.naver.glink.android.sdk.api.response.ArticleMedia;
import com.naver.glink.android.sdk.ui.viewer.MediaViewerDialog;

/* JADX INFO: compiled from: MediaViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface a {
    View a();

    void a(Context context, MediaViewerDialog.From from, ArticleMedia articleMedia, boolean z);

    void b();

    boolean c();
}
