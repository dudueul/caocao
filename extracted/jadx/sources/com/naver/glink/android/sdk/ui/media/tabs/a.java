package com.naver.glink.android.sdk.ui.media.tabs;

import android.view.ViewGroup;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.model.Menu;
import com.naver.glink.android.sdk.ui.media.AllMediaFragmentView;
import com.naver.glink.android.sdk.ui.parent.PlugFragmentView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MediaTabsHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    private static C0046a a = new C0046a();

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.media.tabs.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MediaTabsHelper.java */
    public static class C0046a {
        public static List<MediaTab.Type> b() {
            return a.a.c();
        }

        public MediaTab a(MediaTab.Type type) {
            switch (type) {
                case PHOTOS:
                    return new MediaTab(type, R.id.media_menu_image, AllMediaFragmentView.a(d.r(), Menu.allImages(null)));
                case VIDEOS:
                    return new MediaTab(type, R.id.media_menu_video, AllMediaFragmentView.a(d.r(), Menu.allVideos(null)));
                default:
                    throw new IllegalArgumentException("지원하지 않는 type 입니다.");
            }
        }

        List<MediaTab> a() {
            return a(b());
        }

        List<MediaTab> a(List<MediaTab.Type> list) {
            ArrayList arrayList = new ArrayList();
            Iterator<MediaTab.Type> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(a(it.next()));
            }
            return Collections.unmodifiableList(arrayList);
        }

        public List<MediaTab.Type> c() {
            return Arrays.asList(MediaTab.Type.PHOTOS, MediaTab.Type.VIDEOS);
        }
    }

    static List<MediaTab> a() {
        return a.a();
    }

    static void a(List<MediaTab> list, int i) {
        for (MediaTab mediaTab : list) {
            if (mediaTab.b != null && mediaTab.c == i) {
                PlugFragmentView plugFragmentView = (PlugFragmentView) com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().a(mediaTab.a.fragmentTag);
                if (mediaTab.e && plugFragmentView != null && plugFragmentView.isAttachedToWindow()) {
                    plugFragmentView.c();
                    return;
                }
            }
        }
    }

    static void a(List<MediaTab> list, ViewGroup viewGroup, int i) {
        com.naver.glink.android.sdk.ui.parent.plugfragment.a.b bVarB = com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b();
        for (MediaTab mediaTab : list) {
            if (mediaTab.b != null) {
                if (mediaTab.c == i) {
                    if (mediaTab.e || com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().a(mediaTab.a.fragmentTag) != null) {
                        bVarB.b(mediaTab.b);
                    } else {
                        mediaTab.e = true;
                        bVarB.a(mediaTab.b, mediaTab.a.fragmentTag, viewGroup, R.id.media_content);
                    }
                    mediaTab.b.b_();
                } else if (com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().a(mediaTab.a.fragmentTag) != null) {
                    bVarB.c(mediaTab.b);
                }
            }
        }
        bVarB.a();
    }
}
