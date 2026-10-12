package com.naver.glink.android.sdk.ui.widget.photoview;

import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

/* JADX INFO: compiled from: DefaultOnDoubleTapListener.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b implements GestureDetector.OnDoubleTapListener {
    private d a;

    public b(d dVar) {
        a(dVar);
    }

    public void a(d dVar) {
        this.a = dVar;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (this.a == null) {
            return false;
        }
        try {
            float scale = this.a.getScale();
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (scale < this.a.getMediumScale()) {
                this.a.setScale(this.a.getMediumScale(), x, y, true);
            } else if (scale < this.a.getMediumScale() || scale >= this.a.getMaximumScale()) {
                this.a.setScale(this.a.getMinimumScale(), x, y, true);
            } else {
                this.a.setScale(this.a.getMaximumScale(), x, y, true);
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        RectF displayRect;
        if (this.a == null) {
            return false;
        }
        ImageView imageViewC = this.a.c();
        if (this.a.getOnPhotoTapListener() != null && (displayRect = this.a.getDisplayRect()) != null) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (displayRect.contains(x, y)) {
                this.a.getOnPhotoTapListener().a(imageViewC, (x - displayRect.left) / displayRect.width(), (y - displayRect.top) / displayRect.height());
                return true;
            }
        }
        if (this.a.getOnViewTapListener() == null) {
            return false;
        }
        this.a.getOnViewTapListener().a(imageViewC, motionEvent.getX(), motionEvent.getY());
        return false;
    }
}
