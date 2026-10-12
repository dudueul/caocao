package com.naver.glink.android.sdk.ui.widget.photoview.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

/* JADX INFO: compiled from: FroyoGestureDetector.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(8)
public class c extends b {
    protected final ScaleGestureDetector f;

    public c(Context context) {
        super(context);
        this.f = new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() { // from class: com.naver.glink.android.sdk.ui.widget.photoview.a.c.1
            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                float scaleFactor = scaleGestureDetector.getScaleFactor();
                if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor)) {
                    return false;
                }
                c.this.c.a(scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                return true;
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return true;
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            }
        });
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.a.a, com.naver.glink.android.sdk.ui.widget.photoview.a.d
    public boolean a() {
        return this.f.isInProgress();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.a.b, com.naver.glink.android.sdk.ui.widget.photoview.a.a, com.naver.glink.android.sdk.ui.widget.photoview.a.d
    public boolean c(MotionEvent motionEvent) {
        this.f.onTouchEvent(motionEvent);
        return super.c(motionEvent);
    }
}
