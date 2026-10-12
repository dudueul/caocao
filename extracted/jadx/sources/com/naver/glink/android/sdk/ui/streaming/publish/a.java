package com.naver.glink.android.sdk.ui.streaming.publish;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import com.naver.glink.android.sdk.ui.streaming.publish.publisher.PublisherConfigure;

/* JADX INFO: compiled from: PublishFragment.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(21)
public class a extends Fragment {
    private static final int a = 1;
    private int b;
    private PublisherConfigure c;
    private MediaProjectionManager d;
    private InterfaceC0057a e;

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.streaming.publish.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PublishFragment.java */
    public interface InterfaceC0057a {
        void a();

        void b();
    }

    public static a a() {
        return new a();
    }

    private void e() {
        if (this.e != null) {
            this.e.a();
            this.e = null;
        }
    }

    private void f() {
        if (this.e != null) {
            this.e.b();
            this.e = null;
        }
    }

    public void a(PublisherConfigure publisherConfigure, InterfaceC0057a interfaceC0057a) {
        this.c = publisherConfigure;
        this.e = interfaceC0057a;
        startActivityForResult(this.d.createScreenCaptureIntent(), 1);
    }

    public void b() {
        PublishService.c(getActivity());
    }

    public void c() {
        PublishService.b(getActivity());
    }

    public void d() {
        PublishService.a(getActivity());
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.b = displayMetrics.densityDpi;
        this.d = (MediaProjectionManager) getActivity().getSystemService("media_projection");
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (getActivity() != null && i == 1) {
            if (i2 != -1) {
                f();
            } else {
                PublishService.a(getActivity(), this.c, this.b, i2, intent);
                e();
            }
        }
    }
}
