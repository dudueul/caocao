package com.naver.glink.android.sdk.ui.viewer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.j;
import com.naver.glink.android.sdk.api.response.ArticleMedia;
import com.naver.glink.android.sdk.statistics.jackpot.JackpotEvent;
import com.naver.glink.android.sdk.ui.media.AllMediaFragmentView;
import com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView;
import com.naver.glink.android.sdk.ui.widget.dragviewer.DragView;
import com.squareup.otto.Subscribe;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class MediaViewerDialog extends DialogFragmentView implements ViewPager.OnPageChangeListener {
    private From a;
    private com.naver.glink.android.sdk.ui.viewer.a.c b;
    private int c;
    private boolean d;
    private ViewPager i;
    private d j;
    private View k;
    private c l;

    public enum From {
        ARTICLE,
        PROFILE,
        ALL_MEDIA,
        BANNER
    }

    public static class a {
    }

    public static class b {
        final int a;

        public b(int i) {
            this.a = i;
        }
    }

    private class c {
        final TextView a;
        final View b;
        final TextView c;
        final TextView d;
        final TextView e;
        final TextView f;
        final View g;

        c(View view) {
            view.findViewById(R.id.details).setVisibility(0);
            this.a = (TextView) view.findViewById(R.id.viewer_page);
            this.b = view.findViewById(R.id.viewer_page_divider);
            this.c = (TextView) view.findViewById(R.id.viewer_total_page);
            this.d = (TextView) view.findViewById(R.id.viewer_subject);
            this.e = (TextView) view.findViewById(R.id.viewer_nickname);
            this.f = (TextView) view.findViewById(R.id.viewer_date);
            this.g = view.findViewById(R.id.viewer_view_post);
            switch (mediaViewerDialog.a) {
                case BANNER:
                case PROFILE:
                    this.a.setVisibility(8);
                    this.b.setVisibility(8);
                    this.c.setVisibility(8);
                    this.d.setVisibility(8);
                    this.e.setVisibility(8);
                    this.f.setVisibility(8);
                    this.g.setVisibility(8);
                    break;
                case ARTICLE:
                    this.d.setVisibility(8);
                    this.e.setVisibility(8);
                    this.f.setVisibility(8);
                    this.g.setVisibility(8);
                    break;
            }
        }

        void a(int i, final ArticleMedia articleMedia) {
            this.a.setText(String.valueOf(i + 1));
            this.c.setText(String.valueOf(MediaViewerDialog.this.b.a()));
            if (MediaViewerDialog.this.a == From.ARTICLE || MediaViewerDialog.this.a == From.ALL_MEDIA) {
                this.d.setText(articleMedia.subject);
                this.e.setText(articleMedia.writerNickname);
                this.f.setText(articleMedia.d());
                this.g.setVisibility((MediaViewerDialog.this.a != From.ALL_MEDIA || articleMedia.a(true)) ? 8 : 0);
                this.g.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.viewer.MediaViewerDialog.c.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        com.naver.glink.android.sdk.ui.tabs.b.a(articleMedia.articleId);
                        MediaViewerDialog.this.f();
                    }
                });
            }
        }
    }

    private class d extends PagerAdapter {
        private final HashMap<Integer, com.naver.glink.android.sdk.ui.viewer.b.a> b;

        private d() {
            this.b = new HashMap<>();
        }

        private void a(com.naver.glink.android.sdk.ui.viewer.b.a aVar) {
            if (MediaViewerDialog.this.d && (aVar instanceof com.naver.glink.android.sdk.ui.viewer.b.d)) {
                ((com.naver.glink.android.sdk.ui.viewer.b.d) aVar).a(true);
            }
            MediaViewerDialog.this.d = false;
        }

        void a() {
            Iterator<Integer> it = this.b.keySet().iterator();
            while (it.hasNext()) {
                com.naver.glink.android.sdk.ui.viewer.b.a aVar = this.b.get(Integer.valueOf(it.next().intValue()));
                if (aVar != null) {
                    aVar.b();
                }
            }
            this.b.clear();
        }

        void a(int i) {
            Iterator<Integer> it = this.b.keySet().iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                com.naver.glink.android.sdk.ui.viewer.b.a aVar = this.b.get(Integer.valueOf(iIntValue));
                if (aVar != null) {
                    aVar.a(MediaViewerDialog.this.getContext(), MediaViewerDialog.this.a, MediaViewerDialog.this.b.a(iIntValue), iIntValue == i);
                }
            }
        }

        boolean b() {
            com.naver.glink.android.sdk.ui.viewer.b.a aVar = this.b.get(Integer.valueOf(MediaViewerDialog.this.i.getCurrentItem()));
            return aVar != null && aVar.c();
        }

        @Override // android.support.v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (obj instanceof com.naver.glink.android.sdk.ui.viewer.b.a) {
                com.naver.glink.android.sdk.ui.viewer.b.a aVar = (com.naver.glink.android.sdk.ui.viewer.b.a) obj;
                aVar.b();
                viewGroup.removeView(aVar.a());
                this.b.remove(Integer.valueOf(i));
            }
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            return MediaViewerDialog.this.b.a();
        }

        @Override // android.support.v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            ArticleMedia articleMediaA = MediaViewerDialog.this.b.a(i);
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(MediaViewerDialog.this.getContext());
            View viewInflate = articleMediaA.a() ? layoutInflaterFrom.inflate(R.layout.item_photo_media_viewer, viewGroup, false) : layoutInflaterFrom.inflate(R.layout.item_video_media_viewer, viewGroup, false);
            viewGroup.addView(viewInflate, -1, -1);
            com.naver.glink.android.sdk.ui.viewer.b.a bVar = articleMediaA.a() ? new com.naver.glink.android.sdk.ui.viewer.b.b(viewInflate) : new com.naver.glink.android.sdk.ui.viewer.b.d(viewInflate);
            this.b.put(Integer.valueOf(i), bVar);
            if (i == MediaViewerDialog.this.c) {
                a(bVar);
                bVar.a(MediaViewerDialog.this.getContext(), MediaViewerDialog.this.a, MediaViewerDialog.this.b.a(i), true);
            }
            return bVar;
        }

        @Override // android.support.v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return (obj instanceof com.naver.glink.android.sdk.ui.viewer.b.a) && view == ((com.naver.glink.android.sdk.ui.viewer.b.a) obj).a();
        }
    }

    public MediaViewerDialog(Context context) {
        super(context);
    }

    public static MediaViewerDialog a(Context context, From from, com.naver.glink.android.sdk.ui.viewer.a.c cVar, int i) {
        MediaViewerDialog mediaViewerDialog = new MediaViewerDialog(context);
        mediaViewerDialog.a = from;
        mediaViewerDialog.b = cVar;
        mediaViewerDialog.c = i;
        return mediaViewerDialog;
    }

    private void a(int i) {
        ArticleMedia articleMediaA = this.b.a(i);
        this.l.a(i, articleMediaA);
        if (articleMediaA.type == ArticleMedia.Type.VIDEO) {
            com.naver.glink.android.sdk.a.a.b.c(new b(0));
        }
        this.j.a(i);
    }

    private void a(View view) {
        this.i = (ViewPager) view.findViewById(R.id.viewer_view_pager);
        this.j = new d();
        this.i.setAdapter(this.j);
        this.i.addOnPageChangeListener(this);
        int i = this.c < this.b.a() ? this.c : 0;
        this.i.setCurrentItem(i);
        a(i);
    }

    private void b(View view) {
        this.k = view.findViewById(R.id.overlay_layout);
        this.l = new c(this.k);
        view.findViewById(R.id.viewer_close).setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.viewer.MediaViewerDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                MediaViewerDialog.this.f();
            }
        });
    }

    private boolean c() {
        return this.b == null || this.b.a() == 0;
    }

    private boolean e() {
        this.j.b();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        g();
        dismiss();
    }

    private void g() {
        if (this.a == From.ALL_MEDIA) {
            com.naver.glink.android.sdk.a.a.b.c(new AllMediaFragmentView.a(getCurrentItem()));
        }
    }

    private ArticleMedia getCurrentItem() {
        return this.b.a(this.i.getCurrentItem());
    }

    private void h() {
        int currentItem = this.i.getCurrentItem();
        if (currentItem < this.b.a()) {
            com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.ARTICLE_DETAIL, this.b.a(currentItem).articleId);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_media_viewer, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        com.naver.glink.android.sdk.ui.main.b.g();
        com.naver.glink.android.sdk.a.a.b.a(this);
        this.b.a(new com.naver.glink.android.sdk.ui.viewer.a.c.a() { // from class: com.naver.glink.android.sdk.ui.viewer.MediaViewerDialog.2
            @Override // com.naver.glink.android.sdk.ui.viewer.a.c.a
            public void a() {
                MediaViewerDialog.this.j.notifyDataSetChanged();
            }
        });
        h();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Context context) {
        super.a(context);
        setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    @SuppressLint({"NewApi"})
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        if (c()) {
            return;
        }
        b(view);
        a(view);
        if (-1 >= this.c || this.c >= this.b.a()) {
            return;
        }
        this.d = this.b.a(this.c).b() && (this.a == From.BANNER || this.a == From.ARTICLE);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView
    protected void a(WindowManager.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return;
        }
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.gravity = 17;
        layoutParams.dimAmount = 0.6f;
        layoutParams.format = -2;
        layoutParams.windowAnimations = android.R.style.Animation.Dialog;
        layoutParams.flags |= 16778240;
    }

    @Subscribe
    public void a(a aVar) {
        f();
    }

    @Subscribe
    public void a(b bVar) {
        if (this.k != null) {
            if (bVar.a == -1) {
                this.k.setVisibility(this.k.getVisibility() == 8 ? 0 : 8);
            } else {
                this.k.setVisibility(bVar.a);
            }
        }
    }

    @Subscribe
    public void a(DragView.c cVar) {
        switch (cVar.a) {
            case NONE:
                com.naver.glink.android.sdk.ui.main.b.g();
                if (!e()) {
                    this.k.setVisibility(0);
                }
                break;
            case DRAGGING:
                com.naver.glink.android.sdk.ui.main.b.h();
                this.k.setVisibility(8);
                g();
                break;
            case END:
                f();
                break;
        }
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.widget.dragviewer.a.C0064a c0064a) {
        this.i.setVisibility(0);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a_() {
        super.a_();
        this.j.a();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void b() {
        super.b();
        com.naver.glink.android.sdk.ui.main.b.h();
        com.naver.glink.android.sdk.a.a.b.b(this);
        this.b.a((com.naver.glink.android.sdk.ui.viewer.a.c.a) null);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (j.a(keyEvent)) {
            f();
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        a(i);
        h();
    }
}
