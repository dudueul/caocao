package com.naver.glink.android.sdk.ui.parent.plugfragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FragmentView extends FrameLayout {
    private static final String a = "PlugView";
    private static String b = "bundle_super";
    private ViewGroup c;
    private Bundle d;
    private boolean e;
    protected Bundle g;
    boolean h;

    public FragmentView(Context context) {
        super(context);
        a(context);
    }

    public View a(LayoutInflater layoutInflater) {
        return null;
    }

    public void a() {
        this.h = true;
    }

    protected void a(Context context) {
    }

    public void a(Bundle bundle) {
    }

    public void a(View view, Bundle bundle) {
    }

    protected void a(String str, String str2, List<String> list) {
        if (isAttachedToWindow()) {
            ItemsDialogFragmentView.a(getContext(), str, str2, list);
        }
    }

    protected void a(String str, List<Pair<String, Runnable>> list) {
        a(str, list, -1);
    }

    protected void a(String str, List<Pair<String, Runnable>> list, int i) {
        if (isAttachedToWindow()) {
            ItemsDialogFragmentView.a(getContext(), str, list, i);
        }
    }

    public void a_() {
    }

    public void b() {
        this.h = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchWindowFocusChanged(boolean z) {
        super.dispatchWindowFocusChanged(z);
    }

    public Bundle getArguments() {
        return this.d;
    }

    public View getDeepestFocusedChild() {
        View focusedChild = this;
        while (focusedChild != null) {
            if (focusedChild.isFocused()) {
                return focusedChild;
            }
            focusedChild = focusedChild instanceof ViewGroup ? ((ViewGroup) focusedChild).getFocusedChild() : null;
        }
        return null;
    }

    public ViewGroup getParentView() {
        return this.c;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.e;
    }

    public void j() {
        a(this.g);
        View viewA = a((LayoutInflater) getContext().getSystemService("layout_inflater"));
        if (viewA != null) {
            FrameLayout.LayoutParams layoutParamsN = n();
            layoutParamsN.gravity = 17;
            addView(viewA, layoutParamsN);
        }
        a(viewA, this.g);
        if (o()) {
            return;
        }
        a();
    }

    public void k() {
    }

    public void l() {
        if (this.c != null) {
            this.c.removeView(this);
        }
    }

    protected FrameLayout.LayoutParams n() {
        return new FrameLayout.LayoutParams(-1, -1);
    }

    public boolean o() {
        return this.h;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.e = true;
        j();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.e = false;
        if (o()) {
            b();
        }
        a_();
        a.a().a(this);
        a.a().b(this);
        this.c = null;
        this.d = null;
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (isAttachedToWindow()) {
            super.onMeasure(i, i2);
        }
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        this.g = (Bundle) parcelable;
        Parcelable parcelable2 = this.g.getParcelable(b);
        if (parcelable2 != null) {
            super.onRestoreInstanceState(parcelable2);
        } else {
            super.onRestoreInstanceState(parcelable);
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        this.g = new Bundle();
        this.g.putParcelable(b, super.onSaveInstanceState());
        return this.g;
    }

    public void setArguments(Bundle bundle) {
        this.d = bundle;
    }

    public void setParentView(ViewGroup viewGroup) {
        this.c = viewGroup;
    }
}
