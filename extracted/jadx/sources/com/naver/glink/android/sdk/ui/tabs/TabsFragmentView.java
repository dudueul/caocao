package com.naver.glink.android.sdk.ui.tabs;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.StartTo;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.alarm.LiveAlarmMessage;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.model.Menu;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.parent.PlugFragmentView;
import com.naver.glink.android.sdk.ui.parent.plugfragment.ItemsDialogFragmentView;
import com.naver.glink.android.sdk.ui.widget.progress.PlugProgressbar;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TabsFragmentView extends PlugFragmentView {
    public static final String a = "com.naver.glink.ARG_START_TO";
    private StartTo b;
    private Tab.Type c;
    private List<Tab> d;
    private ViewGroup e;
    private PlugProgressbar f;
    private boolean i;

    private class a {
        final Tab.Type a;
        final View b;
        final ImageView c;
        final ImageView d;

        a(Tab.Type type, View view) {
            this.a = type;
            this.b = view;
            this.c = (ImageView) view.findViewById(R.id.image_view);
            d.p().a(this.c, 19, 19);
            int i = d.p().f;
            int i2 = d.p().g;
            this.c.getLayoutParams().width = Math.min(i, i2);
            this.c.getLayoutParams().height = Math.min(i, i2);
            this.d = (ImageView) view.findViewById(R.id.new_icon);
            if (Tab.Type.STREAMING.equals(type)) {
                this.d.setImageResource(R.drawable.cf_blt_tag_live);
                d.p().a(this.d, 24, 14);
                if (d.i()) {
                    x.a(this.d, (List<Integer>) Arrays.asList(10, 11));
                    x.a(this.d, 0, x.a(6.0f), x.a(4.0f), 0);
                    return;
                } else {
                    x.a(this.d, (List<Integer>) Arrays.asList(11, 11));
                    x.a(this.d, 0, x.a(6.0f), x.a(6.0f), 0);
                    return;
                }
            }
            this.d.setImageResource(R.drawable.cf_blt_tag_new);
            d.p().a(this.d, 14, 14);
            if (d.i()) {
                x.a(this.d, (List<Integer>) Arrays.asList(10, 11));
                x.a(this.d, 0, x.a(6.0f), x.a(4.0f), 0);
            } else {
                x.a(this.d, (List<Integer>) Arrays.asList(11, 11));
                x.a(this.d, 0, x.a(6.0f), x.a(16.0f), 0);
            }
        }

        void a(final Tab tab) {
            if (tab.a != this.a) {
                return;
            }
            if (tab.a == Tab.Type.NONE) {
                this.c.setVisibility(8);
                this.d.setVisibility(8);
            } else {
                this.b.setActivated(this.a == TabsFragmentView.this.c);
                this.b.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.tabs.TabsFragmentView.a.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        TabsFragmentView.this.a(tab.a);
                    }
                });
                this.c.setImageResource(tab.b);
                this.d.setVisibility(tab.d ? 0 : 8);
            }
        }
    }

    public TabsFragmentView(Context context) {
        super(context);
    }

    public static TabsFragmentView a(Context context, StartTo startTo) {
        TabsFragmentView tabsFragmentView = new TabsFragmentView(context);
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.naver.glink.ARG_START_TO", startTo);
        tabsFragmentView.setArguments(bundle);
        return tabsFragmentView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(StartTo startTo) {
        a(startTo.b);
        switch (startTo.a) {
            case ARTICLE:
                b.a(startTo.d);
                break;
            case WRITE:
                b.a(startTo.e, true, false);
                break;
            case MENU:
                b.a(startTo.c, false, true);
                break;
            case ALL_VIDEOS:
                b.a(Menu.allVideos(null));
                break;
            case ALL_IMAGES:
                b.a(Menu.allImages(null));
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Responses.j jVar) {
        com.naver.glink.android.sdk.api.requests.a.a(getContext(), jVar.a(), jVar.b(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.tabs.TabsFragmentView.4
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                boolean z = iVar.live && com.naver.glink.android.sdk.ui.streaming.b.b();
                TabsFragmentView.this.a(b.a(new ArrayList(b.a(z))), Tab.Type.BANNERS);
                for (Tab tab : TabsFragmentView.this.d) {
                    if (tab.c != null && tab.c.isAttachedToWindow() && tab.a == Tab.Type.BANNERS) {
                        tab.c.c();
                    }
                }
                d.b(TabsFragmentView.this.getContext(), z);
                TabsFragmentView.this.e.removeAllViews();
                TabsFragmentView.this.setupTabs(TabsFragmentView.this.e);
                com.naver.glink.android.sdk.api.requests.a.d();
                com.naver.glink.android.sdk.alarm.b.a(TabsFragmentView.this.getContext(), iVar.live);
                TabsFragmentView.this.f();
                TabsFragmentView.this.a(Tab.Type.BANNERS);
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                Toast.makeText(TabsFragmentView.this.getContext(), plugError.errorMessage, 1).show();
            }
        });
    }

    private void a(Tab tab) {
        p();
        h();
        b.e();
        if (tab == null || tab.c == null) {
            return;
        }
        com.naver.glink.android.sdk.ui.parent.plugfragment.a.b bVarB = com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b();
        for (Tab tab2 : this.d) {
            if (tab2.c != null) {
                if (tab2.equals(tab)) {
                    if (tab2.e || com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().a(tab2.a.fragmentTag) != null) {
                        bVarB.b(tab2.c);
                    } else {
                        tab2.e = true;
                        bVarB.a(tab2.c, tab2.a.fragmentTag, this, R.id.tab_contents);
                    }
                } else if (com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().a(tab2.a.fragmentTag) != null) {
                    bVarB.c(tab2.c);
                }
            }
        }
        bVarB.a();
        Tab tabB = b(this.c);
        if (tabB != null) {
            tabB.c.i();
        }
        tab.c.b_();
        if (Tab.Type.NOTICE.equals(tab.a)) {
            p.a(getContext(), System.currentTimeMillis());
            tab.d = false;
        }
        this.c = tab.a;
        r();
    }

    private void q() {
        m();
        if (com.naver.glink.android.sdk.ui.streaming.b.b()) {
            com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.tabs.TabsFragmentView.2
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.i iVar) {
                    TabsFragmentView.this.p();
                    TabsFragmentView.this.i = true;
                    boolean z = iVar.live && com.naver.glink.android.sdk.ui.streaming.b.b();
                    d.b(TabsFragmentView.this.getContext(), z);
                    TabsFragmentView.this.d = b.a(b.a(z));
                    TabsFragmentView.this.setupTabs(TabsFragmentView.this.e);
                    TabsFragmentView.this.a(TabsFragmentView.this.b);
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    TabsFragmentView.this.p();
                    TabsFragmentView.this.i = false;
                    TabsFragmentView.this.setupTabs(TabsFragmentView.this.e);
                    TabsFragmentView.this.a(TabsFragmentView.this.b);
                }
            });
            return;
        }
        p();
        this.i = false;
        setupTabs(this.e);
        a(this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.e.getChildCount()) {
                return;
            }
            View childAt = this.e.getChildAt(i2);
            if (childAt.getTag() instanceof a) {
                a aVar = (a) childAt.getTag();
                aVar.a(b(aVar.a));
            }
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupTabs(ViewGroup viewGroup) {
        viewGroup.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.black2));
        if (this.d == null) {
            this.d = b.b();
        }
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        if (d.i()) {
            layoutParams.width = d.p().f;
        } else {
            layoutParams.height = d.p().g;
        }
        viewGroup.setLayoutParams(layoutParams);
        for (Tab tab : this.d) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.item_tab, viewGroup, false);
            View viewFindViewById = viewInflate.findViewById(R.id.image_view);
            viewInflate.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.black2));
            d.e().a(viewFindViewById);
            a aVar = new a(tab.a, viewInflate);
            aVar.a(tab);
            viewInflate.setTag(aVar);
            viewGroup.addView(viewInflate, new LinearLayout.LayoutParams(d.p().f, d.p().g));
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_tabs, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        f();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Bundle bundle) {
        super.a(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.b = (StartTo) arguments.getParcelable("com.naver.glink.ARG_START_TO");
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        this.e = (ViewGroup) view.findViewById(R.id.tabs_view);
        this.f = (PlugProgressbar) view.findViewById(R.id.tab_progress);
        if (bundle != null) {
            d.c(getContext());
        } else {
            q();
        }
    }

    @Subscribe
    public void a(LiveAlarmMessage liveAlarmMessage) {
        if (liveAlarmMessage == null) {
            return;
        }
        b(Tab.Type.STREAMING).d = !com.naver.glink.android.sdk.alarm.b.b(liveAlarmMessage);
        r();
    }

    void a(Responses.i iVar) {
        if (this.i || iVar == null || this.d == null) {
            return;
        }
        this.i = true;
        boolean z = iVar.live && com.naver.glink.android.sdk.ui.streaming.b.b();
        a(b.a(new ArrayList(b.a(z))), Tab.Type.BANNERS);
        for (Tab tab : this.d) {
            if (tab.c != null && tab.c.isAttachedToWindow() && tab.a == Tab.Type.BANNERS) {
                tab.c.c();
            }
        }
        d.b(getContext(), z);
        this.e.removeAllViews();
        setupTabs(this.e);
        a(this.b);
    }

    @Subscribe
    public void a(final ItemsDialogFragmentView.c cVar) {
        if (TextUtils.equals(cVar.a, ItemsDialogFragmentView.b)) {
            com.naver.glink.android.sdk.api.requests.a.forChannelsRequest.execute(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.tabs.TabsFragmentView.3
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull final Responses.i iVar) {
                    AlertDialogFragmentView.a(TabsFragmentView.this.getContext(), TabsFragmentView.this.c(R.string.menu_change_channel)).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.tabs.TabsFragmentView.3.1
                        @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                        public void a(DialogInterface dialogInterface, int i) {
                            Responses.j jVar = iVar.a().get(cVar.b);
                            if (jVar.channelId != d.b().a()) {
                                TabsFragmentView.this.a(jVar);
                            }
                        }
                    }).a();
                }
            });
        }
    }

    public void a(Tab.Type type) {
        a(b(type));
    }

    void a(List<Tab> list, Tab.Type type) {
        ArrayList arrayList = new ArrayList();
        com.naver.glink.android.sdk.ui.parent.plugfragment.a.b bVarB = com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b();
        Tab tab = null;
        for (Tab tab2 : this.d) {
            if (!tab2.e || tab2.a != type) {
                bVarB.a(tab2.c);
                tab2 = tab;
            }
            tab = tab2;
        }
        for (Tab tab3 : list) {
            if (tab != null && tab.a == tab3.a) {
                tab3 = tab;
            }
            arrayList.add(tab3);
        }
        bVarB.a();
        this.d = Collections.unmodifiableList(arrayList);
    }

    Tab b(Tab.Type type) {
        for (Tab tab : this.d) {
            if (tab.a == type) {
                return tab;
            }
        }
        return null;
    }

    void f() {
        com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.tabs.TabsFragmentView.1
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                Tab tabB = TabsFragmentView.this.b(Tab.Type.NOTICE);
                if (tabB != null) {
                    if (p.k(TabsFragmentView.this.getContext()) < Math.max(iVar.eventUpdateTimestamp, iVar.noticeUpdateTimestamp)) {
                        tabB.d = true;
                    } else {
                        tabB.d = false;
                    }
                }
                Tab tabB2 = TabsFragmentView.this.b(Tab.Type.STREAMING);
                if (tabB2 != null) {
                    tabB2.d = com.naver.glink.android.sdk.alarm.b.c() ? false : true;
                }
                TabsFragmentView.this.r();
            }
        });
    }

    public Tab.Type getSelectedTabType() {
        return this.c;
    }

    void m() {
        if (this.f != null) {
            this.f.setVisibility(0);
        }
    }

    void p() {
        if (this.f != null) {
            this.f.setVisibility(8);
        }
    }
}
