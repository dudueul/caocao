package com.naver.glink.android.sdk.ui.widget.photoview.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;

/* JADX INFO: compiled from: EclairGestureDetector.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(5)
public class b extends a {
    private static final int f = -1;
    private int g;
    private int h;

    public b(Context context) {
        super(context);
        this.g = -1;
        this.h = 0;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.a.a
    float a(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.h);
        } catch (Exception e) {
            return motionEvent.getX();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.a.a
    float b(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.h);
        } catch (Exception e) {
            return motionEvent.getY();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.a.a, com.naver.glink.android.sdk.ui.widget.photoview.a.d
    public boolean c(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.g = motionEvent.getPointerId(0);
                break;
            case 1:
            case 3:
                this.g = -1;
                break;
            case 6:
                int iA = com.naver.glink.android.sdk.ui.widget.photoview.a.a(motionEvent.getAction());
                if (motionEvent.getPointerId(iA) == this.g) {
                    int i = iA == 0 ? 1 : 0;
                    this.g = motionEvent.getPointerId(i);
                    this.d = motionEvent.getX(i);
                    this.e = motionEvent.getY(i);
                }
                break;
        }
        this.h = motionEvent.findPointerIndex(this.g != -1 ? this.g : 0);
        return super.c(motionEvent);
    }
}
