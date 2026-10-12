package com.naver.glink.android.sdk.ui.viewer.a;

import com.naver.glink.android.sdk.api.response.ArticleMedia;
import com.naver.plug.android.core.api.PlugError;

/* JADX INFO: compiled from: AllMediaLoaderDataSource.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a implements c {
    private final com.naver.glink.android.sdk.ui.media.b a;
    private com.naver.glink.android.sdk.ui.media.b.a b;

    public a(com.naver.glink.android.sdk.ui.media.b bVar) {
        this.a = bVar;
    }

    @Override // com.naver.glink.android.sdk.ui.viewer.a.c
    public int a() {
        return this.a.b();
    }

    @Override // com.naver.glink.android.sdk.ui.viewer.a.c
    public ArticleMedia a(int i) {
        return this.a.a(i);
    }

    @Override // com.naver.glink.android.sdk.ui.viewer.a.c
    public void a(final c.a aVar) {
        if (this.b != null) {
            this.a.b(this.b);
            this.b = null;
        }
        if (aVar != null) {
            this.b = new com.naver.glink.android.sdk.ui.media.b.a() { // from class: com.naver.glink.android.sdk.ui.viewer.a.a.1
                @Override // com.naver.glink.android.sdk.ui.media.b.a
                public void a(com.naver.glink.android.sdk.api.response.a aVar2, PlugError plugError) {
                    if (aVar2 != null) {
                        aVar.a();
                    }
                }
            };
            this.a.a(this.b);
        }
    }
}
