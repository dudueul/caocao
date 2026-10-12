package com.naver.glink.android.sdk.ui.widget.photoview;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;

/* JADX INFO: compiled from: IPhotoView.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface c {
    public static final float a = 3.0f;
    public static final float b = 1.75f;
    public static final float c = 1.0f;
    public static final int d = 200;

    boolean a();

    boolean a(Matrix matrix);

    Matrix getDisplayMatrix();

    RectF getDisplayRect();

    c getIPhotoViewImplementation();

    @Deprecated
    float getMaxScale();

    float getMaximumScale();

    float getMediumScale();

    @Deprecated
    float getMidScale();

    @Deprecated
    float getMinScale();

    float getMinimumScale();

    d.InterfaceC0065d getOnPhotoTapListener();

    d.f getOnViewTapListener();

    float getScale();

    ImageView.ScaleType getScaleType();

    Bitmap getVisibleRectangleBitmap();

    void setAllowParentInterceptOnEdge(boolean z);

    @Deprecated
    void setMaxScale(float f);

    void setMaximumScale(float f);

    void setMediumScale(float f);

    @Deprecated
    void setMidScale(float f);

    @Deprecated
    void setMinScale(float f);

    void setMinimumScale(float f);

    void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener);

    void setOnLongClickListener(View.OnLongClickListener onLongClickListener);

    void setOnMatrixChangeListener(d.c cVar);

    void setOnPhotoTapListener(d.InterfaceC0065d interfaceC0065d);

    void setOnScaleChangeListener(d.e eVar);

    void setOnViewTapListener(d.f fVar);

    void setPhotoViewRotation(float f);

    void setRotationBy(float f);

    void setRotationTo(float f);

    void setScale(float f);

    void setScale(float f, float f2, float f3, boolean z);

    void setScale(float f, boolean z);

    void setScaleLevels(float f, float f2, float f3);

    void setScaleType(ImageView.ScaleType scaleType);

    void setZoomTransitionDuration(int i);

    void setZoomable(boolean z);
}
