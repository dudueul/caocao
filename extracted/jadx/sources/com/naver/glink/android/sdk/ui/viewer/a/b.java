package com.naver.glink.android.sdk.ui.viewer.a;

import com.naver.glink.android.sdk.api.response.ArticleMedia;
import java.util.List;

/* JADX INFO: compiled from: FixedItemsDataSource.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b implements c {
    private final List<ArticleMedia> a;

    public b(List<ArticleMedia> list) {
        this.a = list;
    }

    @Override // com.naver.glink.android.sdk.ui.viewer.a.c
    public int a() {
        return this.a.size();
    }

    @Override // com.naver.glink.android.sdk.ui.viewer.a.c
    public ArticleMedia a(int i) {
        if (i < 0 || i >= this.a.size()) {
            return null;
        }
        return this.a.get(i);
    }

    @Override // com.naver.glink.android.sdk.ui.viewer.a.c
    public void a(c.a aVar) {
    }
}
