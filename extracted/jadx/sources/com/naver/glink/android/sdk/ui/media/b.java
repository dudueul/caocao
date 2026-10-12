package com.naver.glink.android.sdk.ui.media;

import android.content.Context;
import android.support.annotation.NonNull;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.api.response.ArticleMedia;
import com.naver.glink.android.sdk.model.Menu;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: AllMediaLoader.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    private static final int b = 20;
    private static final int c = 10;
    final Menu.Type a;
    private final Context d;
    private List<ArticleMedia> e = new ArrayList();
    private List<a> f = new ArrayList();
    private Request<com.naver.glink.android.sdk.api.response.a> g;
    private com.naver.glink.android.sdk.api.response.a h;

    /* JADX INFO: compiled from: AllMediaLoader.java */
    public interface a {
        void a(com.naver.glink.android.sdk.api.response.a aVar, PlugError plugError);
    }

    b(Context context, Menu.Type type) {
        this.d = context;
        this.a = type;
    }

    private Request<com.naver.glink.android.sdk.api.response.a> a(com.naver.glink.android.sdk.api.response.a.C0030a c0030a) {
        int i = c0030a != null ? c0030a.lastArticleId : -1;
        int i2 = c0030a != null ? c0030a.lastAttachId : -1;
        switch (this.a) {
            case VIDEOS:
                return f.c(20, i, i2);
            case IMAGES:
                return f.b(20, i, i2);
            default:
                throw new IllegalStateException("지원하지 않는 메뉴 타입입니다.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.naver.glink.android.sdk.api.response.a aVar, PlugError plugError) {
        Iterator<a> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().a(aVar, plugError);
        }
    }

    private void e() {
        if (this.h == null || this.h.metadata == null || this.g != null || this.h.metadata.isLast) {
            return;
        }
        this.g = a(this.h.metadata);
        this.g.execute(this.d, new RequestListener<com.naver.glink.android.sdk.api.response.a>() { // from class: com.naver.glink.android.sdk.ui.media.b.2
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull com.naver.glink.android.sdk.api.response.a aVar) {
                b.this.h = aVar;
                b.this.e.addAll(aVar.a());
                b.this.a(aVar, (PlugError) null);
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onFinally(com.naver.glink.android.sdk.api.response.a aVar, PlugError plugError) {
                b.this.g = null;
            }
        });
    }

    int a(ArticleMedia articleMedia) {
        return this.e.indexOf(articleMedia);
    }

    public ArticleMedia a(int i) {
        if (b() < i + 10) {
            e();
        }
        return this.e.get(i);
    }

    public void a(a aVar) {
        if (this.f.contains(aVar)) {
            return;
        }
        this.f.add(aVar);
    }

    public boolean a() {
        return this.a == Menu.Type.VIDEOS;
    }

    public int b() {
        return this.e.size();
    }

    public void b(a aVar) {
        this.f.remove(aVar);
    }

    boolean b(ArticleMedia articleMedia) {
        return this.e.contains(articleMedia);
    }

    public boolean c() {
        return this.e.isEmpty();
    }

    void d() {
        if (this.g != null) {
            this.g.cancel();
            this.g = null;
        }
        this.h = null;
        this.e.clear();
        this.g = a((com.naver.glink.android.sdk.api.response.a.C0030a) null);
        this.g.execute(this.d, new RequestListener<com.naver.glink.android.sdk.api.response.a>() { // from class: com.naver.glink.android.sdk.ui.media.b.1
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull com.naver.glink.android.sdk.api.response.a aVar) {
                b.this.h = aVar;
                b.this.e.addAll(aVar.a());
                b.this.a(aVar, (PlugError) null);
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onFinally(com.naver.glink.android.sdk.api.response.a aVar, PlugError plugError) {
                b.this.g = null;
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                b.this.a((com.naver.glink.android.sdk.api.response.a) null, plugError);
            }
        });
    }
}
