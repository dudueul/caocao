package com.naver.glink.android.sdk.ui.article;

import android.util.Log;
import com.naver.glink.android.sdk.a.q;
import com.naver.glink.android.sdk.ui.write.model.Attachment;
import com.naver.glink.android.sdk.ui.write.model.Content;
import com.naver.glink.android.sdk.ui.write.model.ImageAttachment;
import com.naver.glink.android.sdk.ui.write.model.Text;
import com.naver.glink.android.sdk.ui.write.model.VideoAttachment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ViewArticle.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c {
    private static final String a = "ViewArticle";
    private final com.naver.glink.android.sdk.api.a.C0019a b;
    private final List<a.b> c;
    private com.naver.glink.android.sdk.api.a.h d;
    private List<a.b> e;
    private String f;
    private boolean g;

    c(com.naver.glink.android.sdk.api.a.C0019a c0019a) {
        this.b = c0019a;
        this.c = a(c0019a.a());
    }

    private List<a.b> a(List<Content> list) {
        ArrayList arrayList = new ArrayList();
        for (Content content : list) {
            if (content instanceof Text) {
                arrayList.add(new a.b(1, (Text) content));
            } else if (content instanceof ImageAttachment) {
                arrayList.add(new a.b(2, (ImageAttachment) content));
            } else if (content instanceof VideoAttachment) {
                arrayList.add(new a.b(3, (VideoAttachment) content));
            } else {
                Log.d(a, "지원하지 않는 content 입니다.\n" + content);
            }
        }
        return arrayList;
    }

    void a(com.naver.glink.android.sdk.api.a.h hVar) {
        this.d = hVar;
        this.e = a(hVar.a());
    }

    void a(List<a.b> list, boolean z) {
        int iIndexOf;
        Iterator<a.b> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                iIndexOf = -1;
                break;
            }
            a.b next = it.next();
            if (next.a == 0) {
                iIndexOf = list.indexOf(next);
                break;
            }
        }
        if (iIndexOf != -1) {
            if (z) {
                list.addAll(iIndexOf + 1, this.e);
                list.removeAll(this.c);
            } else {
                list.addAll(iIndexOf + 1, this.c);
                list.removeAll(this.e);
            }
            this.g = z;
        }
    }

    boolean a() {
        return this.d != null;
    }

    public boolean b() {
        return this.g;
    }

    public String c() {
        return this.g ? this.d.subject : this.b.subject;
    }

    List<a.b> d() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new a.b(0, (Object) null));
        arrayList.addAll(this.c);
        arrayList.add(new a.b(4, (Object) null));
        return arrayList;
    }

    public String e() {
        return this.b.menuName;
    }

    public int f() {
        return this.b.menuId;
    }

    public String g() {
        return this.b.writerProfileImageUrl;
    }

    public String h() {
        return this.b.writerId;
    }

    public boolean i() {
        return this.b.isWriter;
    }

    public int j() {
        return this.b.writerMemberLevel;
    }

    public String k() {
        return this.b.writerNickname;
    }

    public String l() {
        return this.b.writerMemberTypeName;
    }

    public String m() {
        if (this.f == null) {
            this.f = q.a(this.b.writeDateTime);
        }
        return this.f;
    }

    public int n() {
        return this.b.readCount;
    }

    public String o() {
        return this.b.linkUrl;
    }

    public List<Attachment> p() {
        return com.naver.glink.android.sdk.ui.write.model.a.b(this.g ? this.d.a() : this.b.a());
    }
}
