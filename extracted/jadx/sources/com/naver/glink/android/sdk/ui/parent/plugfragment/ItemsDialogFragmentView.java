package com.naver.glink.android.sdk.ui.parent.plugfragment;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.t;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ItemsDialogFragmentView extends DialogFragmentView {
    public static final int a = -1;
    public static final String b = "CHANNELS_DIALOG_TAG";
    public static final String c = "PROFILE_DIALOG_TAG";
    private a d;

    public static class a {
        private final String a;
        private final List<b> b;
        private int c;
        private Drawable d;

        private a(String str) {
            this.a = str;
            this.b = new ArrayList();
            this.c = -1;
        }

        private void a(int i, String str, @DrawableRes int i2, Runnable runnable) {
            this.b.add(new b(i, str, i2, runnable));
        }

        public int a() {
            return this.b.size();
        }

        public a a(int i) {
            this.c = i;
            return this;
        }

        public a a(Drawable drawable) {
            this.d = drawable;
            return this;
        }

        public a a(Pair<String, Runnable> pair) {
            a(pair.first, pair.second);
            return this;
        }

        public a a(String str) {
            a(str, -1);
            return this;
        }

        public a a(String str, @DrawableRes int i) {
            a(0, str, i, null);
            return this;
        }

        public a a(String str, @DrawableRes int i, Runnable runnable) {
            a(2, str, i, runnable);
            return this;
        }

        public a a(String str, Runnable runnable) {
            a(1, str, -1, runnable);
            return this;
        }

        public a a(List<Pair<String, Runnable>> list) {
            Iterator<Pair<String, Runnable>> it = list.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            return this;
        }

        public void a(Context context) {
            a(context, (String) null);
        }

        public void a(Context context, String str) {
            ItemsDialogFragmentView itemsDialogFragmentView = new ItemsDialogFragmentView(context);
            itemsDialogFragmentView.d = this;
            itemsDialogFragmentView.a(str);
        }

        public a b(String str, Runnable runnable) {
            a(str, -1, runnable);
            return this;
        }
    }

    public static class b {
        final int a;
        final String b;

        @DrawableRes
        final int c;
        final Runnable d;

        b(int i, String str, @DrawableRes int i2, Runnable runnable) {
            this.a = i;
            this.b = str;
            this.c = i2;
            this.d = runnable;
        }
    }

    public static class c extends com.naver.plug.android.core.a.a {
        public final String a;
        public final int b;
        public final String c;

        c(String str, int i, String str2) {
            this.a = str;
            this.b = i;
            this.c = str2;
        }
    }

    private class d extends BaseAdapter {
        private static final int b = 0;
        private static final int c = 1;
        private static final int d = 2;

        private d() {
        }

        private View a(int i, View view, ViewGroup viewGroup) {
            switch (getItemViewType(i)) {
                case 0:
                    if (view == null) {
                        view = LayoutInflater.from(ItemsDialogFragmentView.this.getContext()).inflate(R.layout.item_items_dialog_section, viewGroup, false);
                    }
                    a(view, getItem(i));
                    return view;
                case 1:
                    if (view == null) {
                        view = LayoutInflater.from(ItemsDialogFragmentView.this.getContext()).inflate(R.layout.item_items_dialog_item, viewGroup, false);
                        com.naver.glink.android.sdk.d.e().d(view);
                    }
                    a(view, getItem(i));
                    view.setActivated(ItemsDialogFragmentView.this.d.c == i);
                    return view;
                case 2:
                    if (view == null) {
                        view = LayoutInflater.from(ItemsDialogFragmentView.this.getContext()).inflate(R.layout.item_items_dialog_more, viewGroup, false);
                    }
                    a(view, getItem(i));
                    return view;
                default:
                    throw new IllegalStateException("지원하지 않는 view type 입니다.");
            }
        }

        private void a(View view, b bVar) {
            if (bVar.c != -1) {
                ((ImageView) view.findViewById(R.id.icon)).setImageDrawable(ItemsDialogFragmentView.this.getResources().getDrawable(bVar.c));
            }
            ((TextView) view.findViewById(R.id.name)).setText(bVar.b);
        }

        @Override // android.widget.Adapter
        @NonNull
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public b getItem(int i) {
            return (b) ItemsDialogFragmentView.this.d.b.get(i);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return ItemsDialogFragmentView.this.d.b.size();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i) {
            return getItem(i).a;
        }

        @Override // android.widget.Adapter
        @NonNull
        public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
            return a(i, view, viewGroup);
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 3;
        }
    }

    public ItemsDialogFragmentView(Context context) {
        super(context);
    }

    public static void a(Context context, String str, String str2, List<String> list) {
        a(context, str, str2, list, -1, null);
    }

    public static void a(Context context, String str, String str2, List<String> list, int i, Drawable drawable) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Pair.create(it.next(), null));
        }
        new a(str2).a(i).a(drawable).a(arrayList).a(context, str);
    }

    public static void a(Context context, String str, List<Pair<String, Runnable>> list, int i) {
        new a(str).a(i).a(list).a(context, (String) null);
    }

    private void a(View view) {
        t tVar = new t() { // from class: com.naver.glink.android.sdk.ui.parent.plugfragment.ItemsDialogFragmentView.1
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                ItemsDialogFragmentView.this.dismiss();
            }
        };
        if (TextUtils.isEmpty(this.d.a)) {
            view.findViewById(R.id.title_layout).setVisibility(8);
            view.findViewById(R.id.dialog_close).setOnClickListener(tVar);
        } else {
            ((TextView) view.findViewById(R.id.title)).setText(this.d.a);
            if (this.d.d != null) {
                ImageView imageView = (ImageView) view.findViewById(R.id.title_drawable);
                imageView.setImageDrawable(this.d.d);
                imageView.setVisibility(0);
            }
            view.findViewById(R.id.close).setOnClickListener(tVar);
            view.findViewById(R.id.dialog_close).setVisibility(8);
        }
        ListView listView = (ListView) view.findViewById(R.id.item_dialog_list);
        final d dVar = new d();
        listView.setAdapter((ListAdapter) dVar);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.naver.glink.android.sdk.ui.parent.plugfragment.ItemsDialogFragmentView.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                b item = dVar.getItem(i);
                if (item.d != null) {
                    item.d.run();
                } else {
                    com.naver.glink.android.sdk.a.a.b.c(new c(ItemsDialogFragmentView.this.getTag(), i, item.b));
                }
                ItemsDialogFragmentView.this.dismiss();
            }
        });
    }

    public static a b(String str) {
        return new a(str);
    }

    public static a c() {
        return b(null);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_items_dialog, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        setCanceledOnTouchOutside(true, view);
        Point pointB = com.naver.glink.android.sdk.d.p().b();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = (int) (com.naver.glink.android.sdk.d.i() ? ((double) pointB.x) * 0.6d : com.naver.glink.android.sdk.d.p().i);
        layoutParams.height = -1;
        layoutParams.topMargin = (int) (com.naver.glink.android.sdk.d.i() ? ((double) pointB.x) * 0.03d : ((double) pointB.y) * 0.15d);
        layoutParams.bottomMargin = (int) (com.naver.glink.android.sdk.d.i() ? ((double) pointB.x) * 0.03d : ((double) pointB.y) * 0.15d);
        view.requestLayout();
        a(view);
    }
}
