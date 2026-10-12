package com.naver.glink.android.sdk.ui.parent.plugfragment.a;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: LifecycleFragment.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c extends Fragment {
    private b a;

    public static c a() {
        Bundle bundle = new Bundle();
        c cVar = new c();
        cVar.setArguments(bundle);
        return cVar;
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.a != null) {
            this.a.a(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = new a(getActivity());
        if (this.a != null) {
            this.a.a();
        }
    }

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = new View(getActivity());
        view.setVisibility(8);
        return view;
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        if (this.a != null) {
            this.a.d();
        }
        super.onDestroy();
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        if (this.a != null) {
            this.a.e();
        }
        super.onLowMemory();
    }

    @Override // android.app.Fragment
    public void onPause() {
        if (this.a != null) {
            this.a.c();
        }
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (this.a != null) {
            this.a.a(i, strArr, iArr);
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Fragment
    public void onResume() {
        if (this.a != null) {
            this.a.b();
        }
        super.onResume();
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        if (this.a != null) {
            this.a.a(i);
        }
        super.onTrimMemory(i);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }
}
