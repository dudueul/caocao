package com.naver.glink.android.sdk.ui.write.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.GsonBuilder;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.v;
import com.naver.glink.android.sdk.a.w;
import com.naver.glink.android.sdk.a.y;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.model.Menu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class WritingArticle extends com.naver.plug.android.core.a.a implements Parcelable {
    public static final Parcelable.Creator<WritingArticle> CREATOR = new Parcelable.Creator<WritingArticle>() { // from class: com.naver.glink.android.sdk.ui.write.model.WritingArticle.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public WritingArticle createFromParcel(Parcel parcel) {
            WritingArticle writingArticle = new WritingArticle();
            writingArticle.b = parcel.readInt();
            writingArticle.c = parcel.readInt();
            writingArticle.d = parcel.readString();
            ArrayList arrayList = new ArrayList();
            for (Parcelable parcelable : parcel.readParcelableArray(getClass().getClassLoader())) {
                arrayList.add((Content) parcelable);
            }
            writingArticle.e = arrayList;
            return writingArticle;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public WritingArticle[] newArray(int i) {
            return new WritingArticle[i];
        }
    };
    public static final int a = 4;
    private int b;
    private int c;
    private String d;
    private List<Content> e;
    private int f;

    public static class a {
        private final int a;
        private final int b;
        private String c;
        private List<Content> d = new ArrayList();
        private int e;

        a(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.e = i3;
            this.d.add(new Text(""));
        }

        public a a(String str) {
            this.c = str;
            return this;
        }

        public WritingArticle a() {
            WritingArticle writingArticle = new WritingArticle();
            writingArticle.b = this.a;
            writingArticle.c = this.b;
            writingArticle.d = this.c;
            writingArticle.e = this.d;
            writingArticle.f = this.e;
            return writingArticle;
        }

        public a b(String str) {
            ((Text) this.d.get(0)).a(str);
            return this;
        }

        public a c(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.d.add(new ImageAttachment(y.a(str)));
            }
            return this;
        }

        public a d(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.d.add(new VideoAttachment(y.a(str)));
            }
            return this;
        }
    }

    private WritingArticle() {
        this.b = -1;
        this.c = -1;
        this.e = new ArrayList();
    }

    public static a a() {
        return a(-1, -1);
    }

    public static a a(int i, int i2) {
        return new a(i, i2, 4);
    }

    public void a(int i) {
        this.b = i;
    }

    public void a(Responses.y yVar) {
        if (!g()) {
            this.d = yVar.property.subject;
            this.b = yVar.a().getMenuId();
            this.e = yVar.b();
        } else {
            Menu menuA = yVar.a(this.b);
            if (menuA == null) {
                this.b = -1;
            } else {
                this.b = menuA.getMenuId();
            }
        }
    }

    public void a(Content content) {
        this.e.add(content);
    }

    public void a(Content content, Content content2) {
        int iIndexOf = this.e.indexOf(content);
        if (iIndexOf != -1) {
            this.e.set(iIndexOf, content2);
        }
    }

    public void a(String str) {
        this.d = str;
    }

    public int b() {
        return this.c;
    }

    public void b(int i) {
        this.f = i;
    }

    public void b(Content content) {
        this.e.remove(content);
    }

    public int c() {
        return this.b;
    }

    public List<Attachment> c(int i) {
        if (i < 0 || i >= o()) {
            return Collections.emptyList();
        }
        List<Attachment> listK = k();
        int i2 = i * this.f;
        int size = this.f + i2;
        if (size >= listK.size()) {
            size = listK.size();
        }
        return new ArrayList(listK.subList(i2, size));
    }

    public String d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<Content> e() {
        return this.e;
    }

    public int f() {
        return this.f;
    }

    public boolean g() {
        return this.c == -1;
    }

    public String h() {
        if (this.b == -1) {
            return d.a(R.string.save_error_menu);
        }
        if (v.a(d())) {
            return d.a(R.string.save_error_subject);
        }
        if (v.a(j())) {
            return d.a(R.string.save_error_contents);
        }
        return null;
    }

    public boolean i() {
        if (TextUtils.isEmpty(d()) && TextUtils.isEmpty(j())) {
            return k().isEmpty();
        }
        return false;
    }

    String j() {
        for (Content content : this.e) {
            if (content instanceof Text) {
                return ((Text) content).a();
            }
        }
        return null;
    }

    public List<Attachment> k() {
        return com.naver.glink.android.sdk.ui.write.model.a.b(this.e);
    }

    public List<Attachment> l() {
        ArrayList arrayList = new ArrayList();
        for (Attachment attachment : k()) {
            if (attachment.a()) {
                arrayList.add(attachment);
            }
        }
        return arrayList;
    }

    public List<ImageAttachment> m() {
        return com.naver.glink.android.sdk.ui.write.model.a.c(this.e);
    }

    public List<VideoAttachment> n() {
        return com.naver.glink.android.sdk.ui.write.model.a.d(this.e);
    }

    public int o() {
        int size = k().size();
        return (size % this.f > 0 ? 1 : 0) + (size / this.f);
    }

    public String p() {
        return w.a(q());
    }

    public String q() {
        ArrayList arrayList = new ArrayList();
        Iterator<Content> it = this.e.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().b());
        }
        return new GsonBuilder().create().toJson(arrayList);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeParcelableArray((Parcelable[]) this.e.toArray(new Content[this.e.size()]), i);
    }
}
