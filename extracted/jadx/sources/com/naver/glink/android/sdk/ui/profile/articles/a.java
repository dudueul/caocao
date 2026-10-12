package com.naver.glink.android.sdk.ui.profile.articles;

import android.support.annotation.IdRes;
import android.view.ViewGroup;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.profile.ProfileArticlesFragmentView;
import com.naver.glink.android.sdk.ui.streaming.streamings.StreamingsFragmentView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ProfileTabsHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    private static C0052a a = new C0052a();

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.profile.articles.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ProfileTabsHelper.java */
    public static class C0052a {
        public static List<ProfileTab.Type> a() {
            return a.a.b();
        }

        public ProfileTab a(ProfileTab.Type type, String str) {
            switch (type) {
                case WRITE:
                    return new ProfileTab(type, R.id.profile_write_tab, ProfileArticlesFragmentView.a(d.r(), str, type));
                case COMMENT:
                    return new ProfileTab(type, R.id.profile_comment_tab, ProfileArticlesFragmentView.a(d.r(), str, type));
                case LIKE:
                    return new ProfileTab(type, R.id.profile_like_tab, ProfileArticlesFragmentView.a(d.r(), str, type));
                case STREAMING:
                    return new ProfileTab(type, R.id.profile_streaming_tab, StreamingsFragmentView.a(d.r(), str));
                default:
                    throw new IllegalArgumentException("지원하지 않는 type 입니다.");
            }
        }

        List<ProfileTab> a(String str) {
            return a(a(), str);
        }

        List<ProfileTab> a(List<ProfileTab.Type> list, String str) {
            ArrayList arrayList = new ArrayList();
            Iterator<ProfileTab.Type> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(a(it.next(), str));
            }
            return Collections.unmodifiableList(arrayList);
        }

        public List<ProfileTab.Type> b() {
            return Arrays.asList(ProfileTab.Type.WRITE, ProfileTab.Type.COMMENT, ProfileTab.Type.LIKE, ProfileTab.Type.STREAMING);
        }
    }

    @IdRes
    static int a(List<ProfileTab> list, ProfileTab.Type type) {
        if (type == null) {
            return 0;
        }
        for (ProfileTab profileTab : list) {
            if (type.equals(profileTab.a)) {
                return profileTab.c;
            }
        }
        return 0;
    }

    static List<ProfileTab> a(String str) {
        return a.a(str);
    }

    static void a(List<ProfileTab> list, ViewGroup viewGroup, int i) {
        com.naver.glink.android.sdk.ui.parent.plugfragment.a.b bVarB = com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b();
        for (ProfileTab profileTab : list) {
            if (profileTab.b != null) {
                if (profileTab.c == i) {
                    if (profileTab.d || com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().a(profileTab.a.fragmentTag) != null) {
                        bVarB.b(profileTab.b);
                    } else {
                        profileTab.d = true;
                        bVarB.a(profileTab.b, profileTab.a.fragmentTag, viewGroup, R.id.profile_article_content);
                    }
                    profileTab.b.b_();
                } else if (com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().a(profileTab.a.fragmentTag) != null) {
                    bVarB.c(profileTab.b);
                }
            }
        }
        bVarB.a();
    }
}
