package com.naver.glink.android.sdk.ui.tabs;

import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.articles.ArticlesMainFragmentView;
import com.naver.glink.android.sdk.ui.articles.SearchArticlesFragmentView;
import com.naver.glink.android.sdk.ui.banner.BannersFragmentView;
import com.naver.glink.android.sdk.ui.media.tabs.MediaTab;
import com.naver.glink.android.sdk.ui.media.tabs.MediaTabFragmentView;
import com.naver.glink.android.sdk.ui.profile.ProfileMainFragmentView;
import com.naver.glink.android.sdk.ui.streaming.streamings.StreamingsFragmentView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: TabsCreator.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.tabs.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TabsCreator.java */
    public static class C0063a extends a {
        @Override // com.naver.glink.android.sdk.ui.tabs.a
        public Tab a(Tab.Type type) {
            switch (type) {
                case BANNERS:
                    return new Tab(type, R.drawable.cf_btn_navi1, BannersFragmentView.b(d.r()));
                case NOTICE:
                    return new Tab(type, R.drawable.cf_btn_navi2, ArticlesMainFragmentView.a(d.r(), 0));
                case STREAMING:
                    return new Tab(type, R.drawable.cf_btn_navi6, StreamingsFragmentView.b(d.r()));
                case ALL_MEDIA:
                    return new Tab(type, R.drawable.cf_btn_navi3, MediaTabFragmentView.a(d.r(), MediaTab.Type.PHOTOS));
                case SEARCH:
                    return new Tab(type, R.drawable.cf_btn_navi4, SearchArticlesFragmentView.b(d.r()));
                case NONE:
                    return new Tab(type, 0, null);
                case PROFILE:
                    return new Tab(type, R.drawable.cf_btn_navi5, ProfileMainFragmentView.b(d.r()));
                default:
                    throw new IllegalArgumentException("지원하지 않는 type 입니다.");
            }
        }

        @Override // com.naver.glink.android.sdk.ui.tabs.a
        public List<Tab.Type> a(boolean z) {
            if (z) {
                return Arrays.asList(Tab.Type.BANNERS, Tab.Type.NOTICE, Tab.Type.STREAMING, Tab.Type.ALL_MEDIA, Tab.Type.SEARCH, Tab.Type.PROFILE);
            }
            return d.i() ? Arrays.asList(Tab.Type.BANNERS, Tab.Type.NOTICE, Tab.Type.ALL_MEDIA, Tab.Type.SEARCH, Tab.Type.NONE, Tab.Type.PROFILE) : Arrays.asList(Tab.Type.BANNERS, Tab.Type.NOTICE, Tab.Type.ALL_MEDIA, Tab.Type.SEARCH, Tab.Type.PROFILE);
        }
    }

    public abstract Tab a(Tab.Type type);

    List<Tab> a() {
        return a(a(false));
    }

    List<Tab> a(List<Tab.Type> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<Tab.Type> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public abstract List<Tab.Type> a(boolean z);
}
