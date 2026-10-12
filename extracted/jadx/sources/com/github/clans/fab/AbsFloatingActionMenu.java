package com.github.clans.fab;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class AbsFloatingActionMenu extends ViewGroup {
    public static final int a = 200;
    public static final float b = 0.0f;
    public static final float c = -135.0f;
    public static final float d = 135.0f;
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 3;
    public static final int i = 0;
    public static final int j = 1;
    protected a k;

    public interface a {
        void a(boolean z);
    }

    public AbsFloatingActionMenu(Context context) {
        super(context);
    }

    public AbsFloatingActionMenu(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AbsFloatingActionMenu(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    @TargetApi(21)
    public AbsFloatingActionMenu(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    public abstract void a(boolean z);

    public abstract boolean a();

    public abstract void b(boolean z);

    public abstract void c(boolean z);

    public void setOnMenuToggleListener(a aVar) {
        this.k = aVar;
    }

    public abstract void setOpenDirection(int i2);

    public abstract void setOpenLabel(int i2);
}
