package com.naver.glink.android.sdk.ui.parent.plugfragment;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/* JADX INFO: compiled from: FragmentViewManager.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    private static a b = new a();
    private Handler a = new Handler(Looper.getMainLooper());
    private List<c> c = new ArrayList();
    private HashMap<String, FragmentView> d = new HashMap<>();
    private Vector<C0047a> e = new Vector<>();

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.parent.plugfragment.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FragmentViewManager.java */
    private class C0047a {
        String a;
        Vector<FragmentView> b;

        C0047a(String str, Vector<FragmentView> vector) {
            this.a = str;
            this.b = vector;
        }

        void a() {
            this.a = null;
            this.b.clear();
            this.b = null;
        }

        public String toString() {
            return "BackStackRecord " + this.a;
        }
    }

    /* JADX INFO: compiled from: FragmentViewManager.java */
    public class b {
        Vector<FragmentView> a = new Vector<>();
        ArrayList<Runnable> b = new ArrayList<>();
        boolean c = false;

        public b() {
        }

        public b a(final FragmentView fragmentView) {
            this.b.add(new Runnable() { // from class: com.naver.glink.android.sdk.ui.parent.plugfragment.a.b.2
                @Override // java.lang.Runnable
                public void run() {
                    if (b.this.c) {
                        return;
                    }
                    a.this.c(fragmentView);
                    a.this.b(fragmentView);
                }
            });
            return this;
        }

        public b a(FragmentView fragmentView, String str) {
            a(fragmentView, str, null, 0);
            return this;
        }

        public b a(final FragmentView fragmentView, final String str, final ViewGroup viewGroup, final int i) {
            this.b.add(new Runnable() { // from class: com.naver.glink.android.sdk.ui.parent.plugfragment.a.b.1
                @Override // java.lang.Runnable
                public void run() {
                    View viewFindViewById;
                    String str2 = str;
                    if (b.this.c) {
                        return;
                    }
                    if (fragmentView == null) {
                        b.this.c = true;
                        return;
                    }
                    if (a.this.d.get(str2) != null && (fragmentView instanceof DialogFragmentView)) {
                        ((FragmentView) a.this.d.get(str2)).l();
                    } else if (a.this.d.get(str2) != null) {
                        b.this.c = true;
                        return;
                    }
                    if (viewGroup != null && Build.VERSION.SDK_INT >= 19 && !viewGroup.isAttachedToWindow()) {
                        b.this.c = true;
                        return;
                    }
                    b.this.a.add(fragmentView);
                    a.this.d.put(str2, fragmentView);
                    fragmentView.k();
                    if (viewGroup == null || (viewFindViewById = viewGroup.findViewById(i)) == null || !(viewFindViewById instanceof ViewGroup)) {
                        return;
                    }
                    ((ViewGroup) viewFindViewById).addView(fragmentView, new ViewGroup.LayoutParams(-1, -1));
                    fragmentView.setParentView((ViewGroup) viewFindViewById);
                    fragmentView.setVisibility(0);
                }
            });
            return this;
        }

        public b a(final String str) {
            this.b.add(new Runnable() { // from class: com.naver.glink.android.sdk.ui.parent.plugfragment.a.b.5
                @Override // java.lang.Runnable
                public void run() {
                    if (b.this.c) {
                        return;
                    }
                    a.this.e.add(a.this.new C0047a(str, (Vector) b.this.a.clone()));
                    if (a.this.c.size() > 0) {
                        Iterator it = a.this.c.iterator();
                        while (it.hasNext()) {
                            ((c) it.next()).a();
                        }
                    }
                }
            });
            return this;
        }

        public void a() {
            this.b.add(new Runnable() { // from class: com.naver.glink.android.sdk.ui.parent.plugfragment.a.b.6
                @Override // java.lang.Runnable
                public void run() {
                    if (b.this.c) {
                    }
                    b.this.a.clear();
                    b.this.a = null;
                }
            });
            Iterator<Runnable> it = this.b.iterator();
            while (it.hasNext()) {
                a.this.a.post(it.next());
            }
            this.b.clear();
        }

        public b b(final FragmentView fragmentView) {
            this.b.add(new Runnable() { // from class: com.naver.glink.android.sdk.ui.parent.plugfragment.a.b.3
                @Override // java.lang.Runnable
                public void run() {
                    if (b.this.c) {
                        return;
                    }
                    fragmentView.setVisibility(0);
                }
            });
            return this;
        }

        public b c(final FragmentView fragmentView) {
            this.b.add(new Runnable() { // from class: com.naver.glink.android.sdk.ui.parent.plugfragment.a.b.4
                @Override // java.lang.Runnable
                public void run() {
                    if (b.this.c) {
                        return;
                    }
                    fragmentView.setVisibility(8);
                }
            });
            return this;
        }
    }

    /* JADX INFO: compiled from: FragmentViewManager.java */
    public interface c {
        void a();
    }

    public static a a() {
        if (b == null) {
            b = new a();
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(int i, FragmentView fragmentView) {
        for (int size = this.e.size() - 1; size >= i; size--) {
            boolean z = false;
            Vector<FragmentView> vector = this.e.get(size).b;
            if (vector != null) {
                for (FragmentView fragmentView2 : vector) {
                    if (fragmentView == null) {
                        c(fragmentView2);
                    } else if (fragmentView.getWindowToken() == fragmentView2.getWindowToken()) {
                        c(fragmentView2);
                    } else {
                        z = true;
                    }
                }
            }
            if (!z) {
                this.e.get(size).a();
                this.e.remove(size);
                if (this.c.size() > 0) {
                    Iterator<c> it = this.c.iterator();
                    while (it.hasNext()) {
                        it.next().a();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(FragmentView fragmentView) {
        if (a(fragmentView) != null) {
            fragmentView.l();
        }
    }

    private String d(FragmentView fragmentView) {
        for (String str : this.d.keySet()) {
            if (this.d.get(str) == fragmentView) {
                return str;
            }
        }
        return null;
    }

    public FragmentView a(String str) {
        return this.d.get(str);
    }

    public String a(FragmentView fragmentView) {
        String strD = d(fragmentView);
        if (strD != null) {
            this.d.remove(strD);
        }
        return strD;
    }

    public void a(c cVar) {
        if (this.c.contains(cVar)) {
            this.c.remove(cVar);
        }
        this.c.add(cVar);
    }

    public b b() {
        return new b();
    }

    public String b(String str) {
        if (str == null) {
            return null;
        }
        for (C0047a c0047a : this.e) {
            if (str.equals(c0047a.a)) {
                return c0047a.a;
            }
        }
        return null;
    }

    public void b(final FragmentView fragmentView) {
        this.a.post(new Runnable() { // from class: com.naver.glink.android.sdk.ui.parent.plugfragment.a.3
            @Override // java.lang.Runnable
            public void run() {
                if (a.this.e.size() == 0) {
                    return;
                }
                int size = a.this.e.size() - 1;
                while (true) {
                    int i = size;
                    if (a.this.e.size() <= 0 || i < 0) {
                        return;
                    }
                    Vector<FragmentView> vector = ((C0047a) a.this.e.get(i)).b;
                    if (vector != null) {
                        for (int i2 = 0; i2 < vector.size(); i2++) {
                            if (vector.get(i2) == fragmentView) {
                                if (i >= 0) {
                                    a.this.a(i, fragmentView);
                                    return;
                                }
                                return;
                            }
                        }
                    }
                    size = i - 1;
                }
            }
        });
    }

    public void b(c cVar) {
        if (this.c.contains(cVar)) {
            this.c.remove(cVar);
        }
    }

    public List<String> c() {
        ArrayList arrayList = new ArrayList();
        Iterator<C0047a> it = this.e.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a);
        }
        return arrayList;
    }

    public void c(final String str) {
        this.a.post(new Runnable() { // from class: com.naver.glink.android.sdk.ui.parent.plugfragment.a.2
            @Override // java.lang.Runnable
            public void run() {
                int i;
                if (a.this.e.size() == 0) {
                    return;
                }
                int size = a.this.e.size() - 1;
                while (true) {
                    i = size;
                    if (a.this.e.size() <= 0 || i < 0 || (str != null && str.equals(((C0047a) a.this.e.get(i)).a))) {
                        break;
                    } else {
                        size = i - 1;
                    }
                }
                if (i >= 0) {
                    a.this.a(i, (FragmentView) null);
                }
            }
        });
    }

    public int d() {
        return this.e.size();
    }

    public void e() {
        this.a.post(new Runnable() { // from class: com.naver.glink.android.sdk.ui.parent.plugfragment.a.1
            @Override // java.lang.Runnable
            public void run() {
                if (a.this.e.size() == 0) {
                    return;
                }
                a.this.a(a.this.e.size() - 1, (FragmentView) null);
            }
        });
    }

    public void f() {
        Iterator<String> it = this.d.keySet().iterator();
        while (it.hasNext()) {
            FragmentView fragmentView = this.d.get(it.next());
            if (fragmentView != null && fragmentView.isAttachedToWindow() && !fragmentView.o()) {
                fragmentView.a();
            }
        }
    }

    public void g() {
        Iterator<String> it = this.d.keySet().iterator();
        while (it.hasNext()) {
            FragmentView fragmentView = this.d.get(it.next());
            if (fragmentView != null && fragmentView.isAttachedToWindow() && fragmentView.o()) {
                fragmentView.b();
            }
        }
    }

    public void h() {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.d.keySet().iterator();
        while (it.hasNext()) {
            FragmentView fragmentView = this.d.get(it.next());
            if (fragmentView != null && (fragmentView instanceof DialogFragmentView)) {
                arrayList.add(fragmentView);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((FragmentView) it2.next()).l();
        }
        arrayList.clear();
    }
}
