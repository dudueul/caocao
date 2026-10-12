package com.naver.glink.android.sdk.ui.tabs;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.Scopes;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.StartTo;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.model.Menu;
import com.naver.glink.android.sdk.ui.CafeInfoFragmentView;
import com.naver.glink.android.sdk.ui.article.ArticleFragmentView;
import com.naver.glink.android.sdk.ui.article.WebViewArticleFragmentView;
import com.naver.glink.android.sdk.ui.articles.ArticlesFragmentView;
import com.naver.glink.android.sdk.ui.articles.PopularArticlesFragment;
import com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView;
import com.naver.glink.android.sdk.ui.media.AllMediaFragmentView;
import com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView;
import com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView;
import com.naver.glink.android.sdk.ui.profile.articles.ProfileArticlesTabFragmentView;
import com.naver.glink.android.sdk.ui.profile.articles.ProfileTab;
import com.naver.glink.android.sdk.ui.write.WriteFragmentView;
import com.naver.glink.android.sdk.ui.write.model.WritingArticle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: TabsHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    private static final String a = "TabsHelper";
    private static final String b = "plug.sdk.tabs";
    private static final String c = "plug.sdk.child.";
    private static a d;

    public static TabsFragmentView a() {
        return (TabsFragmentView) k().a(b);
    }

    private static String a(String str) {
        return c + str;
    }

    private static List<String> a(com.naver.glink.android.sdk.ui.parent.plugfragment.a aVar) {
        return aVar == null ? Collections.emptyList() : aVar.c();
    }

    public static List<Tab> a(List<Tab.Type> list) {
        return d.a(list);
    }

    public static List<Tab.Type> a(boolean z) {
        return d.a(z);
    }

    public static void a(int i) {
        a(d.k() ? WebViewArticleFragmentView.a(d.r(), i) : ArticleFragmentView.a(d.r(), i), a("article"), true);
    }

    public static void a(int i, com.naver.glink.android.sdk.api.a.C0019a c0019a, com.naver.glink.android.sdk.api.requests.b.c.a aVar, boolean z) {
        a((FragmentView) ArticleFragmentView.a(d.r(), i, c0019a, aVar, z), a("replies"), true);
    }

    public static void a(int i, boolean z, boolean z2) {
        a(ArticlesFragmentView.a(d.r(), i, z), a("articles"), z2);
    }

    public static void a(Context context, View view, StartTo startTo) {
        com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b().a(TabsFragmentView.a(context, startTo), b, (ViewGroup) view, R.id.glink).a();
    }

    public static void a(Responses.i iVar) {
        TabsFragmentView tabsFragmentViewA = a();
        if (tabsFragmentViewA == null) {
            return;
        }
        tabsFragmentViewA.a(iVar);
    }

    public static void a(Menu menu) {
        a((FragmentView) AllMediaFragmentView.a(d.r(), menu), a("allMedia"), false);
    }

    private static void a(FragmentView fragmentView, String str, boolean z) {
        if (z) {
            str = d(str);
        } else {
            c(str);
        }
        k().b().a(fragmentView, str, a(), R.id.tab_contents).a(str).a();
    }

    public static void a(Tab.Type type) {
        TabsFragmentView tabsFragmentViewA = a();
        if (tabsFragmentViewA == null) {
            return;
        }
        tabsFragmentViewA.a(type);
    }

    public static void a(a aVar) {
        d = aVar;
    }

    public static void a(WritingArticle writingArticle) {
        a(writingArticle, false, false);
    }

    public static void a(WritingArticle writingArticle, boolean z, boolean z2) {
        if (writingArticle == null) {
            return;
        }
        String strA = a("write");
        k().b().a(WriteFragmentView.a(d.r(), writingArticle, z, z2), strA, a(), R.id.glink_contents).a(strA).a();
    }

    public static void a(String str, boolean z) {
        a((FragmentView) ProfileMainFragmentView.a(d.r(), true, str, z), a(Scopes.PROFILE), false);
    }

    public static void a(String str, boolean z, ProfileTab.Type type) {
        a((FragmentView) ProfileArticlesTabFragmentView.a(d.r(), z, type, str), a("profileArticles"), false);
    }

    static List<Tab> b() {
        return d.a();
    }

    public static void b(int i) {
        a((FragmentView) WebViewArticleFragmentView.a(d.r(), i), a("link.article"), false);
    }

    public static void b(Menu menu) {
        a((FragmentView) SearchArticlesFragmentView.a(d.r(), menu), a("search"), true);
    }

    public static void b(boolean z) {
        a(PopularArticlesFragment.b(d.r()), a("articles"), z);
    }

    private static boolean b(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith(c);
    }

    public static void c() {
        String strA = a("cafeInfo");
        k().b().a(CafeInfoFragmentView.b(d.r()), strA, a(), R.id.glink_contents).a(strA).a();
    }

    private static void c(String str) {
        k().c(str);
    }

    public static WriteFragmentView d() {
        return (WriteFragmentView) k().a(a("write"));
    }

    private static String d(String str) {
        return str + System.currentTimeMillis();
    }

    static void e() {
        List<String> listL = l();
        if (listL.isEmpty()) {
            return;
        }
        c(listL.get(0));
    }

    public static void f() {
        List<String> listL = l();
        if (listL.isEmpty()) {
            return;
        }
        c(listL.get(listL.size() - 1));
    }

    public static Tab.Type g() {
        TabsFragmentView tabsFragmentViewA = a();
        if (tabsFragmentViewA == null) {
            return null;
        }
        return tabsFragmentViewA.getSelectedTabType();
    }

    public static void h() {
        TabsFragmentView tabsFragmentViewA = a();
        if (tabsFragmentViewA == null) {
            return;
        }
        tabsFragmentViewA.m();
    }

    public static void i() {
        TabsFragmentView tabsFragmentViewA = a();
        if (tabsFragmentViewA == null) {
            return;
        }
        tabsFragmentViewA.p();
    }

    public static void j() {
        Log.d(a, "getAllChildBackStackNames: " + l());
    }

    private static com.naver.glink.android.sdk.ui.parent.plugfragment.a k() {
        return com.naver.glink.android.sdk.ui.parent.plugfragment.a.a();
    }

    private static List<String> l() {
        ArrayList arrayList = new ArrayList();
        for (String str : a(com.naver.glink.android.sdk.ui.parent.plugfragment.a.a())) {
            if (b(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
