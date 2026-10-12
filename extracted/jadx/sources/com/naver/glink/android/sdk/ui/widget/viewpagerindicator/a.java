package com.naver.glink.android.sdk.ui.widget.viewpagerindicator;

import android.support.v4.view.ViewPager;

/* JADX INFO: compiled from: PageIndicator.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface a extends ViewPager.OnPageChangeListener {
    void c();

    void setCurrentItem(int i);

    void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener);

    void setViewPager(ViewPager viewPager);

    void setViewPager(ViewPager viewPager, int i);
}
