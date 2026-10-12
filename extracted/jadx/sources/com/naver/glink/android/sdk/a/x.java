package com.naver.glink.android.sdk.a;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.naver.glink.android.sdk.R;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: UnitUtils.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class x {
    public static int a(float f) {
        Context contextR = com.naver.glink.android.sdk.d.r();
        if (contextR == null) {
            return 0;
        }
        return (int) TypedValue.applyDimension(1, f, contextR.getResources().getDisplayMetrics());
    }

    public static int a(float f, float f2) {
        Context contextR = com.naver.glink.android.sdk.d.r();
        if (contextR == null) {
            return 0;
        }
        if (!com.naver.glink.android.sdk.d.i()) {
            f = f2;
        }
        return (int) TypedValue.applyDimension(1, f, contextR.getResources().getDisplayMetrics());
    }

    static int a(int i, int i2) {
        return (i == -1 || i == -2) ? i2 : i;
    }

    public static View a(Context context, ViewGroup viewGroup, int i, @DrawableRes int i2) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.item_empty_space, viewGroup, false);
        a(viewInflate, -1, i);
        viewInflate.setBackgroundResource(i2);
        return viewInflate;
    }

    public static void a(View view, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = a(i, layoutParams.width);
        layoutParams.height = a(i2, layoutParams.height);
        view.setLayoutParams(layoutParams);
    }

    public static void a(View view, int i, int i2, int i3, int i4) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.setMargins(a(i, layoutParams2.leftMargin), a(i2, layoutParams2.topMargin), a(i3, layoutParams2.rightMargin), a(i4, layoutParams2.bottomMargin));
            view.setLayoutParams(layoutParams2);
        } else if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams3.setMargins(a(i, layoutParams3.leftMargin), a(i2, layoutParams3.topMargin), a(i3, layoutParams3.rightMargin), a(i4, layoutParams3.bottomMargin));
            view.setLayoutParams(layoutParams3);
        }
    }

    public static void a(View view, List<Integer> list) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            layoutParams.addRule(it.next().intValue(), -1);
        }
        view.setLayoutParams(layoutParams);
    }
}
