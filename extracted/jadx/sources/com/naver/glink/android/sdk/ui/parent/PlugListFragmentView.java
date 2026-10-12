package com.naver.glink.android.sdk.ui.parent;

import android.R;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class PlugListFragmentView extends PlugFragmentView {
    private ListView a;
    private View b;
    private ListAdapter c;
    private final Handler d;
    private final AdapterView.OnItemClickListener e;
    private final Runnable f;

    public PlugListFragmentView(Context context) {
        super(context);
        this.d = new Handler(Looper.getMainLooper());
        this.e = new AdapterView.OnItemClickListener() { // from class: com.naver.glink.android.sdk.ui.parent.PlugListFragmentView.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                PlugListFragmentView.this.a((ListView) adapterView, view, i, j);
            }
        };
        this.f = new Runnable() { // from class: com.naver.glink.android.sdk.ui.parent.PlugListFragmentView.2
            @Override // java.lang.Runnable
            public void run() {
                PlugListFragmentView.this.a.focusableViewAvailable(PlugListFragmentView.this.a);
            }
        };
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        this.a = (ListView) view.findViewById(R.id.list);
        this.a.setOnItemClickListener(this.e);
        this.b = view.findViewById(R.id.empty);
        this.a.setEmptyView(this.b);
        this.d.post(this.f);
        super.a(view, bundle);
    }

    public void a(ListView listView, View view, int i, long j) {
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a_() {
        this.d.removeCallbacks(this.f);
        super.a_();
    }

    protected void b(int i, @StringRes int i2) {
        if (this.b != null) {
            ((ImageView) this.b.findViewById(com.naver.glink.android.sdk.R.id.empty_image)).setImageResource(i);
            ((TextView) this.b.findViewById(com.naver.glink.android.sdk.R.id.empty_message)).setText(getContext().getString(i2));
        }
    }

    protected View getEmptyContentsLayout() {
        return getListView().getEmptyView().findViewById(com.naver.glink.android.sdk.R.id.empty_contents_layout);
    }

    public ListAdapter getListAdapter() {
        return this.c;
    }

    public ListView getListView() {
        return this.a;
    }

    public void setListAdapter(ListAdapter listAdapter) {
        this.c = listAdapter;
        if (this.a != null) {
            this.a.setAdapter(listAdapter);
        }
    }
}
