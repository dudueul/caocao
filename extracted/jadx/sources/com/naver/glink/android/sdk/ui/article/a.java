package com.naver.glink.android.sdk.ui.article;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.e;
import com.naver.glink.android.sdk.ui.article.a.d;
import com.naver.glink.android.sdk.ui.write.model.ImageAttachment;
import com.naver.glink.android.sdk.ui.write.model.Text;
import com.naver.glink.android.sdk.ui.write.model.VideoAttachment;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ArticleAdapter.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a extends BaseAdapter {
    public static final int a = 3;
    static final int b = 0;
    static final int c = 1;
    static final int d = 2;
    static final int e = 3;
    static final int f = 4;
    private static final int g = 5;
    private static final int h = 6;
    private static final int i = 7;
    private static final int j = 8;
    private static final int k = 9;
    private final Context l;
    private final WeakReference<ArticleFragmentView> m;
    private final boolean n;
    private final boolean o;
    private List<b> p;
    private com.naver.glink.android.sdk.api.requests.b.c.C0027b q;
    private int r;
    private int s = -1;
    private boolean t;
    private com.naver.glink.android.sdk.api.requests.b.c.e u;
    private c v;
    private String w;
    private Request<com.naver.glink.android.sdk.api.a.h> x;

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.article.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArticleAdapter.java */
    public static class C0038a {
        public boolean a;
        public String b;
    }

    /* JADX INFO: compiled from: ArticleAdapter.java */
    static class b {
        final int a;
        final Object b;
        final Object c;

        b(int i, Object obj) {
            this.a = i;
            this.b = obj;
            this.c = null;
        }

        b(com.naver.glink.android.sdk.api.requests.b.c.a aVar) {
            this.a = aVar.c() ? 7 : 6;
            this.b = aVar;
            this.c = new C0038a();
        }

        b(com.naver.glink.android.sdk.api.requests.b.c.a aVar, C0038a c0038a) {
            this.a = aVar.c() ? 7 : 6;
            this.b = aVar;
            this.c = c0038a == null ? new C0038a() : c0038a;
        }
    }

    a(Context context, ArticleFragmentView articleFragmentView, boolean z) {
        this.l = context;
        this.m = new WeakReference<>(articleFragmentView);
        this.o = z;
        this.n = !z;
    }

    private int b(int i2) {
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= this.p.size()) {
                return -1;
            }
            if (this.p.get(i4).a == i2) {
                return i4;
            }
            i3 = i4 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArticleFragmentView b() {
        return this.m.get();
    }

    private List<b> b(com.naver.glink.android.sdk.api.requests.b.c.e eVar) {
        List<com.naver.glink.android.sdk.api.requests.b.c.a> listC = this.o ? eVar.c() : eVar.commentList;
        ArrayList arrayList = new ArrayList();
        for (com.naver.glink.android.sdk.api.requests.b.c.a aVar : listC) {
            arrayList.add(new b(aVar));
            if (this.o) {
                Iterator<com.naver.glink.android.sdk.api.requests.b.c.a> it = aVar.l().iterator();
                while (it.hasNext()) {
                    arrayList.add(new b(it.next()));
                }
            }
        }
        return arrayList;
    }

    private com.naver.glink.android.sdk.api.requests.b.c.a c(int i2) {
        int iD = d(i2);
        if (iD == -1 || iD >= this.p.size() - 1) {
            return null;
        }
        b bVar = this.p.get(iD + 1);
        if (bVar.a == 6 || bVar.a == 7) {
            return (com.naver.glink.android.sdk.api.requests.b.c.a) bVar.b;
        }
        return null;
    }

    private C0038a c(com.naver.glink.android.sdk.api.requests.b.c.a aVar) {
        com.naver.glink.android.sdk.api.requests.b.c.a aVarA = a(aVar.commentNo);
        if (aVarA == null) {
            return null;
        }
        if (!TextUtils.equals(aVar.e(), aVarA.e())) {
            return null;
        }
        return (C0038a) f(d(aVar.commentNo));
    }

    private boolean c() {
        if (this.u == null) {
            return false;
        }
        if (this.o) {
            return d() <= 0;
        }
        return this.u.pageModel == null || this.u.pageModel.prevPage == 0;
    }

    private int d() {
        int i2;
        if (!this.o || (i2 = this.u.count.exposeCount - this.r) > 20) {
            return 20;
        }
        return i2;
    }

    private int d(int i2) {
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= this.p.size()) {
                return -1;
            }
            b bVar = this.p.get(i4);
            if ((bVar.a == 6 || bVar.a == 7) && i2 == ((com.naver.glink.android.sdk.api.requests.b.c.a) bVar.b).commentNo) {
                return i4;
            }
            i3 = i4 + 1;
        }
    }

    private b e(int i2) {
        if (this.p.size() == i2) {
            return new b((e() && this.o) ? 8 : 9, (Object) null);
        }
        return this.p.get(i2);
    }

    private boolean e() {
        return this.q != null && this.q.exposeCount == 0;
    }

    private Object f(int i2) {
        return e(i2).c;
    }

    com.naver.glink.android.sdk.api.requests.b.c.a a(int i2) {
        int iD = d(i2);
        if (iD == -1) {
            return null;
        }
        return (com.naver.glink.android.sdk.api.requests.b.c.a) this.p.get(iD).b;
    }

    List<com.naver.glink.android.sdk.api.requests.b.c.a> a() {
        if (!this.n || !c()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (b bVar : this.p) {
            if (bVar.a == 7) {
                arrayList.add((com.naver.glink.android.sdk.api.requests.b.c.a) bVar.b);
            }
            if (arrayList.size() >= 3) {
                break;
            }
        }
        return arrayList;
    }

    void a(Context context, int i2) {
        if (this.x != null) {
            return;
        }
        if (this.v.a()) {
            this.v.a(this.p, !this.v.b());
            notifyDataSetChanged();
        } else {
            this.x = com.naver.glink.android.sdk.api.requests.c.c(i2);
            this.x.execute(context, new RequestListener<com.naver.glink.android.sdk.api.a.h>() { // from class: com.naver.glink.android.sdk.ui.article.a.1
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull com.naver.glink.android.sdk.api.a.h hVar) {
                    a.this.v.a(hVar);
                    a.this.v.a(a.this.p, true);
                    a.this.notifyDataSetChanged();
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onFinally(com.naver.glink.android.sdk.api.a.h hVar, PlugError plugError) {
                    a.this.x = null;
                }
            });
        }
    }

    void a(Context context, final com.naver.glink.android.sdk.api.requests.b.c.a aVar) {
        int iD = d(aVar.commentNo);
        if (iD == -1) {
            return;
        }
        final C0038a c0038a = (C0038a) f(iD);
        if (TextUtils.isEmpty(c0038a.b) && !TextUtils.isEmpty(aVar.f())) {
            com.naver.glink.android.sdk.api.requests.c.b(aVar.f()).execute(context, new RequestListener<Responses.v>() { // from class: com.naver.glink.android.sdk.ui.article.a.2
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.v vVar) {
                    c0038a.a = true;
                    List<String> listG = aVar.g();
                    String str = vVar.result == null ? "" : vVar.result;
                    C0038a c0038a2 = c0038a;
                    if (!listG.isEmpty()) {
                        str = listG.get(0) + str;
                    }
                    c0038a2.b = str;
                    a.this.notifyDataSetChanged();
                }
            });
        } else {
            c0038a.a = !c0038a.a;
            notifyDataSetChanged();
        }
    }

    void a(ViewGroup viewGroup, int i2, int i3) {
        if (viewGroup == null) {
            return;
        }
        int i4 = 0;
        while (i2 <= i3) {
            if (getItem(i2) instanceof ImageAttachment) {
                View childAt = viewGroup.getChildAt(i4);
                if (childAt == null) {
                    return;
                }
                d dVar = (d) childAt.getTag();
                if (dVar != null) {
                    dVar.a();
                }
            }
            i2++;
            i4++;
        }
    }

    void a(com.naver.glink.android.sdk.api.a.C0019a c0019a, Responses.i iVar) {
        this.v = new c(c0019a);
        this.p = this.v.d();
        this.q = null;
        this.r = 0;
        notifyDataSetChanged();
        this.w = iVar.cafeType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void a(com.naver.glink.android.sdk.api.requests.b.a aVar) {
        this.q = ((com.naver.glink.android.sdk.api.requests.b.a.C0025a) aVar.result).count;
        this.p.add(new b(((com.naver.glink.android.sdk.api.requests.b.a.C0025a) aVar.result).comment));
        notifyDataSetChanged();
    }

    void a(com.naver.glink.android.sdk.api.requests.b.c.a aVar) {
        this.q = null;
        this.r = 0;
        if (this.p == null) {
            this.p = new ArrayList();
            if (aVar != null) {
                this.p.add(new b(aVar));
            }
        }
    }

    void a(com.naver.glink.android.sdk.api.requests.b.c.e eVar) {
        int iB;
        if (eVar == null) {
            return;
        }
        this.u = eVar;
        if (this.n && this.q == null) {
            this.p = new ArrayList();
            if (eVar.parent != null) {
                this.p.add(new b(eVar.parent));
            }
        }
        this.q = eVar.count;
        this.r += eVar.commentList.size();
        int iB2 = b(5);
        if (iB2 == -1) {
            this.p.add(new b(5, (Object) null));
            this.p.addAll(b(eVar));
        } else {
            this.p.addAll(iB2 + 1, b(eVar));
        }
        if (this.n && c() && (iB = b(5)) != -1) {
            this.p.remove(iB);
        }
        notifyDataSetChanged();
    }

    void a(e.b bVar) {
        this.s = bVar.count;
        this.t = bVar.isReacted;
        notifyDataSetChanged();
    }

    void a(ArticleFragmentView.a aVar) {
        int i2;
        this.q = aVar.d;
        if (aVar.c == null) {
            b(aVar.b);
        } else {
            ArrayList arrayList = new ArrayList();
            if (!aVar.b.deleted || !aVar.c.isEmpty()) {
                arrayList.add(new b(aVar.b, c(aVar.b)));
                for (com.naver.glink.android.sdk.api.requests.b.c.a aVar2 : aVar.c) {
                    arrayList.add(new b(aVar2, c(aVar2)));
                }
            }
            int iD = d(aVar.b.commentNo);
            if (iD == -1) {
                return;
            }
            int i3 = iD + 1;
            while (true) {
                i2 = i3;
                if (i2 >= this.p.size() || this.p.get(i2).a != 7) {
                    break;
                } else {
                    i3 = i2 + 1;
                }
            }
            this.p.removeAll(new ArrayList(this.p.subList(iD, i2)));
            this.p.addAll(iD, arrayList);
        }
        notifyDataSetChanged();
    }

    /* JADX WARN: Multi-variable type inference failed */
    void b(com.naver.glink.android.sdk.api.requests.b.a aVar) {
        this.q = ((com.naver.glink.android.sdk.api.requests.b.a.C0025a) aVar.result).count;
        com.naver.glink.android.sdk.api.requests.b.c.a aVar2 = ((com.naver.glink.android.sdk.api.requests.b.a.C0025a) aVar.result).comment;
        int iD = d(aVar2.commentNo);
        if (iD != -1) {
            this.p.remove(iD);
            if (aVar2.b() && ((this.o && aVar2.replyCount > 0) || this.n)) {
                this.p.add(iD, new b(aVar2));
            }
            notifyDataSetChanged();
        }
    }

    void b(com.naver.glink.android.sdk.api.requests.b.c.a aVar) {
        int iD = d(aVar.commentNo);
        if (iD != -1) {
            C0038a c0038aC = c(aVar);
            this.p.remove(iD);
            this.p.add(iD, new b(aVar, c0038aC));
            notifyDataSetChanged();
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.p == null) {
            return 0;
        }
        return this.p.size() + 1;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return e(i2).b;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i2) {
        return e(i2).a;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        d dVar;
        int itemViewType = getItemViewType(i2);
        switch (itemViewType) {
            case 0:
                if (view == null) {
                    view = LayoutInflater.from(this.l).inflate(R.layout.item_article_header, viewGroup, false);
                }
                com.naver.glink.android.sdk.ui.article.a.c cVar = (com.naver.glink.android.sdk.ui.article.a.c) view.getTag();
                if (cVar == null) {
                    cVar = new com.naver.glink.android.sdk.ui.article.a.c(view);
                    view.setTag(cVar);
                }
                cVar.a(b(), this.v);
                return view;
            case 1:
                if (view == null) {
                    view = LayoutInflater.from(this.l).inflate(R.layout.item_article_text, viewGroup, false);
                }
                ((TextView) view.findViewById(R.id.text)).setText(((Text) getItem(i2)).a());
                return view;
            case 2:
                if (view == null) {
                    view = LayoutInflater.from(this.l).inflate(R.layout.item_article_image, viewGroup, false);
                }
                d dVar2 = (d) view.getTag();
                if (dVar2 == null) {
                    d dVar3 = new d(view);
                    view.setTag(dVar3);
                    dVar = dVar3;
                } else {
                    dVar = dVar2;
                }
                dVar.a(b(), (ImageAttachment) getItem(i2), this.v);
                return view;
            case 3:
                if (view == null) {
                    view = LayoutInflater.from(this.l).inflate(R.layout.item_article_video, viewGroup, false);
                }
                VideoAttachment videoAttachment = (VideoAttachment) getItem(i2);
                com.naver.glink.android.sdk.ui.article.a.e eVar = (com.naver.glink.android.sdk.ui.article.a.e) view.getTag();
                if (eVar == null) {
                    eVar = new com.naver.glink.android.sdk.ui.article.a.e(view);
                    view.setTag(eVar);
                }
                eVar.a(b(), videoAttachment, this.v);
                return view;
            case 4:
                if (view == null) {
                    view = LayoutInflater.from(this.l).inflate(R.layout.item_article_contents_footer, viewGroup, false);
                }
                com.naver.glink.android.sdk.ui.article.a.b bVar = (com.naver.glink.android.sdk.ui.article.a.b) view.getTag();
                if (bVar == null) {
                    bVar = new com.naver.glink.android.sdk.ui.article.a.b(view);
                    view.setTag(bVar);
                }
                bVar.a(b(), this.v, this.q, this.t, this.s, this.w);
                return view;
            case 5:
                if (view == null) {
                    view = LayoutInflater.from(this.l).inflate(R.layout.item_article_comments_header, viewGroup, false);
                }
                ((TextView) view.findViewById(R.id.comments_text)).setVisibility(this.o ? 0 : 8);
                TextView textView = (TextView) view.findViewById(R.id.comments_more);
                textView.setVisibility(c() ? 8 : 0);
                textView.setText(com.naver.glink.android.sdk.d.a(R.string.comments_more_view, Integer.valueOf(d())));
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.article.a.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        if (a.this.b() == null) {
                            return;
                        }
                        if (a.this.o) {
                            a.this.b().a(a.this.u.a(), -1);
                        } else {
                            a.this.b().a(-1, a.this.u.pageModel.prevPage);
                        }
                    }
                });
                view.findViewById(R.id.comments_header_divider).setVisibility((this.o && c()) ? 0 : 8);
                return view;
            case 6:
            case 7:
                if (view == null) {
                    view = LayoutInflater.from(this.l).inflate(R.layout.item_article_comment, viewGroup, false);
                    view.findViewById(R.id.etc_items).setVisibility(0);
                    view.setBackgroundColor(ContextCompat.getColor(this.l, (this.n && itemViewType == 6) ? R.color.white1 : R.color.white2));
                    if (itemViewType == 7) {
                        x.a(view.findViewById(R.id.writer_profile_image), x.a(30.0f), x.a(30.0f));
                        x.a(view.findViewById(R.id.writer_profile_image_stroke), x.a(30.0f), x.a(30.0f));
                        if (this.n) {
                            x.a(view.findViewById(R.id.comment_divider), x.a(48.0f), x.a(12.0f), -1, -1);
                        }
                    }
                }
                com.naver.glink.android.sdk.ui.article.a.a aVar = (com.naver.glink.android.sdk.ui.article.a.a) view.getTag();
                if (aVar == null) {
                    aVar = new com.naver.glink.android.sdk.ui.article.a.a(view);
                    view.setTag(aVar);
                }
                com.naver.glink.android.sdk.api.requests.b.c.a aVar2 = (com.naver.glink.android.sdk.api.requests.b.c.a) getItem(i2);
                aVar.a(b(), aVar2, c(aVar2.commentNo), this.o, c(aVar2));
                return view;
            case 8:
                return view == null ? LayoutInflater.from(this.l).inflate(R.layout.item_article_empty_comments, viewGroup, false) : view;
            case 9:
                return view == null ? LayoutInflater.from(this.l).inflate(R.layout.item_empty_space, viewGroup, false) : view;
            default:
                throw new IllegalStateException("지원하지 않는 view type 입니다.");
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 10;
    }
}
