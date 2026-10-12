package com.naver.glink.android.sdk.ui.parent.a;

import android.app.Fragment;

/* JADX INFO: compiled from: PlugFragment.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b extends Fragment {
    @Override // android.app.Fragment
    public void onPause() {
        com.naver.glink.android.sdk.a.a.b.b(this);
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        com.naver.glink.android.sdk.a.a.b.a(this);
    }
}
