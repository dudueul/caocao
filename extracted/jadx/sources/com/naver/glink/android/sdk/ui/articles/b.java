package com.naver.glink.android.sdk.ui.articles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.model.Menu;
import com.naver.glink.android.sdk.ui.parent.plugfragment.ItemsDialogFragmentView;
import com.naver.plug.android.core.api.request.RequestListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MenusDialogHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    private static final int a = 3;

    /* JADX INFO: compiled from: MenusDialogHelper.java */
    public interface a {
        void a(Menu menu);
    }

    private static Pair<String, Runnable> a(final Context context, final Menu menu, final a aVar) {
        return Pair.create(menu.getMenuName(), new Runnable() { // from class: com.naver.glink.android.sdk.ui.articles.b.2
            @Override // java.lang.Runnable
            public void run() {
                if (aVar != null) {
                    aVar.a(menu);
                }
                p.b(context, menu.getMenuId());
            }
        });
    }

    public static void a(final Context context, final int i, final a aVar) {
        com.naver.glink.android.sdk.api.requests.a.menusRequest.execute(context, new RequestListener<Responses.r>() { // from class: com.naver.glink.android.sdk.ui.articles.b.1
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.r rVar) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(Menu.allArticles(rVar.metadata.allPosts));
                arrayList.addAll(rVar.menus);
                b.c(context, arrayList);
                b.a(context, arrayList, i, aVar);
            }
        });
    }

    public static void a(Context context, List<Menu> list, int i, a aVar) {
        ItemsDialogFragmentView.a aVarC = ItemsDialogFragmentView.c();
        List<Menu> listB = b(context, list);
        if (listB.size() > 0) {
            aVarC.a(d.a(R.string.recent_board), R.drawable.cf_icon_bdrecent_s);
            Iterator<Menu> it = listB.iterator();
            while (it.hasNext()) {
                aVarC.a(a(context, it.next(), aVar));
            }
        }
        aVarC.a(d.a(R.string.all_board), R.drawable.cf_icon_bdall_s);
        for (Menu menu : list) {
            aVarC.a(a(context, menu, aVar));
            if (menu.getMenuId() == i) {
                aVarC.a(aVarC.a() - 1);
            }
        }
        aVarC.a(context, (String) null);
    }

    private static List<Menu> b(Context context, List<Menu> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = p.o(context).iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            for (Menu menu : list) {
                if (iIntValue == menu.getMenuId()) {
                    arrayList.add(menu);
                    break;
                }
            }
            if (arrayList.size() >= 3) {
                break;
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, List<Menu> list) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = p.o(context).iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            Iterator<Menu> it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = true;
                    break;
                } else if (iIntValue == it2.next().getMenuId()) {
                    z = false;
                    break;
                }
            }
            if (z) {
                arrayList.add(Integer.valueOf(iIntValue));
            }
        }
        p.a(context, arrayList);
    }
}
