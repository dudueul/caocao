package com.naver.glink.android.sdk.ui.banner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.c;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.api.response.ArticleMedia;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.statistics.jackpot.JackpotEvent;
import com.naver.glink.android.sdk.ui.parent.PlugFragmentView;
import com.naver.glink.android.sdk.ui.viewer.MediaViewerDialog;
import com.naver.glink.android.sdk.ui.widget.viewpagerindicator.CirclePageIndicator;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class BannersFragmentView extends PlugFragmentView {
    private static final l a = l.a(BannersFragmentView.class.getSimpleName());
    private Responses.BannersResponse b;
    private ViewGroup c;
    private ViewGroup d;
    private boolean e;

    private class a extends ArrayAdapter<C0041a> {
        static final int a = 0;
        static final int b = 1;

        /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.banner.BannersFragmentView$a$a, reason: collision with other inner class name */
        class C0041a {
            final int a;
            final Responses.BannersResponse.HomeBanner b;

            C0041a(int i, Responses.BannersResponse.HomeBanner homeBanner) {
                this.a = i;
                this.b = homeBanner;
            }
        }

        public a(Context context) {
            super(context, 0, new ArrayList());
        }

        private View a(int i, View view, ViewGroup viewGroup) {
            View view2;
            if (view == null) {
                BannerMainView bannerMainView = new BannerMainView(getContext());
                bannerMainView.a(LayoutInflater.from(getContext()), getContext());
                view2 = bannerMainView;
            } else {
                view2 = view;
            }
            ((BannerMainView) view2).a(getItem(i).b, getContext());
            return view2;
        }

        private View b(int i, View view, ViewGroup viewGroup) {
            View view2;
            if (view == null) {
                BannerSmallView bannerSmallView = new BannerSmallView(getContext());
                bannerSmallView.a(LayoutInflater.from(getContext()), getContext());
                view2 = bannerSmallView;
            } else {
                view2 = view;
            }
            ((BannerSmallView) view2).a(getItem(i).b, getContext());
            return view2;
        }

        public void a(List<Responses.BannersResponse.HomeBanner> list, List<Responses.BannersResponse.HomeBanner> list2) {
            ArrayList arrayList = new ArrayList();
            Iterator<Responses.BannersResponse.HomeBanner> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new C0041a(0, it.next()));
            }
            Iterator<Responses.BannersResponse.HomeBanner> it2 = list2.iterator();
            while (it2.hasNext()) {
                arrayList.add(new C0041a(1, it2.next()));
            }
            clear();
            addAll(arrayList);
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i) {
            return getItem(i).a;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        @NonNull
        public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
            switch (getItemViewType(i)) {
                case 0:
                    return a(i, view, viewGroup);
                case 1:
                    return b(i, view, viewGroup);
                default:
                    throw new IllegalStateException("지원하지 않는 viewType 입니다.");
            }
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 2;
        }
    }

    private class b extends PagerAdapter {
        private b() {
        }

        @Override // android.support.v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            return BannersFragmentView.this.b.mains.size();
        }

        @Override // android.support.v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            BannerFullView bannerFullView = new BannerFullView(BannersFragmentView.this.getContext());
            bannerFullView.a(LayoutInflater.from(BannersFragmentView.this.getContext()), BannersFragmentView.this.getContext());
            bannerFullView.a(BannersFragmentView.this.b.mains.get(i), BannersFragmentView.this.getContext());
            viewGroup.addView(bannerFullView);
            return bannerFullView;
        }

        @Override // android.support.v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    public BannersFragmentView(Context context) {
        super(context);
        this.e = true;
    }

    private void a(final Uri uri) {
        new Handler().postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.ui.banner.BannersFragmentView.6
            @Override // java.lang.Runnable
            public void run() {
                if (uri.getQuery() != null) {
                    BannersFragmentView.this.d(uri.getPath() + "?" + uri.getQuery());
                } else {
                    BannersFragmentView.this.d(uri.getPath());
                }
            }
        }, 1000L);
    }

    private void a(View view, Responses.BannersResponse.HomeBanner homeBanner) {
        BannerMainView bannerMainView = new BannerMainView(getContext());
        bannerMainView.a(LayoutInflater.from(getContext()), getContext());
        bannerMainView.a(homeBanner, getContext());
        ((ViewGroup) view).addView(bannerMainView);
    }

    private void a(ViewGroup viewGroup) {
        ViewPager viewPager = (ViewPager) viewGroup.findViewById(R.id.pager);
        viewPager.setAdapter(new b());
        if (viewPager.getAdapter().getCount() > 1) {
            CirclePageIndicator circlePageIndicator = (CirclePageIndicator) viewGroup.findViewById(R.id.indicator);
            x.a(circlePageIndicator, 0, 0, 0, x.a(8.0f, 18.0f));
            circlePageIndicator.setFillColor(d.e().a);
            circlePageIndicator.setViewPager(viewPager);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void a(Responses.BannersResponse.HomeBanner homeBanner) {
        if (homeBanner.mediaType == null) {
            return;
        }
        String str = homeBanner.mediaType;
        byte b2 = -1;
        switch (str.hashCode()) {
            case 65:
                if (str.equals("A")) {
                    b2 = 1;
                }
                break;
            case 69:
                if (str.equals("E")) {
                    b2 = 5;
                }
                break;
            case 73:
                if (str.equals("I")) {
                    b2 = 0;
                }
                break;
            case 77:
                if (str.equals("M")) {
                    b2 = 2;
                }
                break;
            case 83:
                if (str.equals("S")) {
                    b2 = 3;
                }
                break;
            case 2083:
                if (str.equals("AD")) {
                    b2 = 4;
                }
                break;
        }
        switch (b2) {
            case 0:
            default:
                return;
            case 1:
                com.naver.glink.android.sdk.ui.tabs.b.a(homeBanner.articleId);
                return;
            case 2:
                ArticleMedia articleMediaA = ArticleMedia.a(homeBanner);
                if (articleMediaA != null) {
                    com.naver.glink.android.sdk.ui.viewer.a.a(MediaViewerDialog.From.BANNER, Arrays.asList(articleMediaA), 0);
                    return;
                }
                return;
            case 3:
                com.naver.glink.android.sdk.b.a(homeBanner.appScheme);
                return;
            case 4:
                if (homeBanner.aC != null) {
                    Uri uri = Uri.parse(homeBanner.aC);
                    try {
                        c.a().a(new URL(homeBanner.aC).getProtocol() + "://" + uri.getHost());
                    } catch (MalformedURLException e) {
                        c.a().a("https://" + uri.getHost());
                        e.printStackTrace();
                    }
                    d(uri.getPath());
                }
                break;
            case 5:
                break;
        }
        if (homeBanner.androidLink != null) {
            String str2 = homeBanner.androidLink;
            if (str2.startsWith("http")) {
                getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str2)));
            } else {
                if (!str2.startsWith("market")) {
                    Toast.makeText(d.r(), "Not supported url", 1).show();
                    return;
                }
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str2));
                d.r().startActivity(intent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Responses.BannersResponse bannersResponse) {
        this.b = bannersResponse;
        switch (bannersResponse.layout) {
            case 1:
            case 101:
                this.c.setVisibility(0);
                this.d.setVisibility(8);
                a(this.c);
                break;
            case 2:
            case 102:
            case 103:
                this.c.setVisibility(8);
                this.d.setVisibility(0);
                b(this.d);
                break;
        }
        p();
    }

    public static BannersFragmentView b(Context context) {
        BannersFragmentView bannersFragmentView = new BannersFragmentView(context);
        bannersFragmentView.setArguments(new Bundle());
        return bannersFragmentView;
    }

    private void b(ViewGroup viewGroup) {
        ListView listView = (ListView) viewGroup.findViewById(R.id.list_view);
        a aVar = new a(getContext());
        listView.setAdapter((ListAdapter) aVar);
        View viewFindViewById = viewGroup.findViewById(R.id.main_banner);
        if (this.e) {
            new Handler().postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.ui.banner.BannersFragmentView.3
                @Override // java.lang.Runnable
                public void run() {
                    c.a().b(getClass().getSimpleName() + ": ADEvent");
                    BannersFragmentView.this.m();
                }
            }, 1000L);
            this.e = false;
        }
        if (!d.i()) {
            viewFindViewById.setVisibility(8);
            aVar.a(this.b.mains, this.b.banners);
            return;
        }
        viewFindViewById.setVisibility(0);
        if (this.b.mains != null && this.b.mains.size() > 0) {
            a(viewFindViewById, this.b.mains.get(0));
        }
        aVar.a(Collections.emptyList(), this.b.banners);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        f.a(str).execute(getContext(), new RequestListener<Responses.s>() { // from class: com.naver.glink.android.sdk.ui.banner.BannersFragmentView.5
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.s sVar) {
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        f.c().execute(getContext(), new RequestListener<Responses.BannersResponse>() { // from class: com.naver.glink.android.sdk.ui.banner.BannersFragmentView.2
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.BannersResponse bannersResponse) {
                BannersFragmentView.this.g();
                BannersFragmentView.this.a(bannersResponse);
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                BannersFragmentView.this.a(plugError);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.b != null) {
            if (this.b.mains != null && this.b.mains.size() > 0) {
                for (Responses.BannersResponse.HomeBanner homeBanner : this.b.mains) {
                    if (homeBanner.mediaType.equals("AD") && homeBanner.aP != null) {
                        Uri uri = Uri.parse(homeBanner.aP);
                        try {
                            c.a().a(new URL(homeBanner.aP).getProtocol() + "://" + uri.getHost());
                        } catch (MalformedURLException e) {
                            c.a().a("https://" + uri.getHost());
                            e.printStackTrace();
                        }
                        a(uri);
                    }
                }
            }
            if (this.b.banners == null || this.b.banners.size() <= 0) {
                return;
            }
            for (Responses.BannersResponse.HomeBanner homeBanner2 : this.b.banners) {
                if (homeBanner2.mediaType.equals("AD") && homeBanner2.aP != null) {
                    Uri uri2 = Uri.parse(homeBanner2.aP);
                    try {
                        c.a().a(new URL(homeBanner2.aP).getProtocol() + "://" + uri2.getHost());
                    } catch (MalformedURLException e2) {
                        c.a().a("https://" + uri2.getHost());
                        e2.printStackTrace();
                    }
                    a(uri2);
                }
            }
        }
    }

    private void p() {
        if (this.b != null) {
            if (this.b.layout == 1) {
                com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.HOME_LAND_TYPE1);
                return;
            }
            if (this.b.layout == 2) {
                com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.HOME_LAND_TYPE2);
                return;
            }
            if (this.b.layout == 101) {
                com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.HOME_PORTRAIT_TYPE1);
            } else if (this.b.layout == 102) {
                com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.HOME_PORTRAIT_TYPE2);
            } else if (this.b.layout == 103) {
                com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.HOME_PORTRAIT_TYPE3);
            }
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_banners, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        c();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        this.c = (ViewGroup) view.findViewById(R.id.full_banners_layout);
        this.d = (ViewGroup) view.findViewById(R.id.main_and_banners_layout);
    }

    @Subscribe
    public void a(AbsBanner.a aVar) {
        a(aVar.a);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void b_() {
        super.b_();
        p();
        if (this.e) {
            return;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.ui.banner.BannersFragmentView.4
            @Override // java.lang.Runnable
            public void run() {
                c.a().b(getClass().getSimpleName() + ": ADEvent");
                BannersFragmentView.this.m();
            }
        }, 1000L);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void c() {
        this.c.setVisibility(8);
        this.d.setVisibility(8);
        com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.banner.BannersFragmentView.1
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                BannersFragmentView.this.f();
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                BannersFragmentView.this.a(plugError);
            }
        });
    }
}
