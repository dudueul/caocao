package com.naver.glink.android.sdk.ui.widget.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class PhotoView extends ImageView implements c {
    private d e;
    private ImageView.ScaleType f;

    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setScaleType(ImageView.ScaleType.MATRIX);
        b();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public boolean a() {
        return this.e.a();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public boolean a(Matrix matrix) {
        return this.e.a(matrix);
    }

    protected void b() {
        if (this.e == null || this.e.c() == null) {
            this.e = new d(this);
        }
        if (this.f != null) {
            setScaleType(this.f);
            this.f = null;
        }
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public Matrix getDisplayMatrix() {
        return this.e.getDisplayMatrix();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public RectF getDisplayRect() {
        return this.e.getDisplayRect();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public c getIPhotoViewImplementation() {
        return this.e;
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    @Deprecated
    public float getMaxScale() {
        return getMaximumScale();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public float getMaximumScale() {
        return this.e.getMaximumScale();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public float getMediumScale() {
        return this.e.getMediumScale();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    @Deprecated
    public float getMidScale() {
        return getMediumScale();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    @Deprecated
    public float getMinScale() {
        return getMinimumScale();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public float getMinimumScale() {
        return this.e.getMinimumScale();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public d.InterfaceC0065d getOnPhotoTapListener() {
        return this.e.getOnPhotoTapListener();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public d.f getOnViewTapListener() {
        return this.e.getOnViewTapListener();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public float getScale() {
        return this.e.getScale();
    }

    @Override // android.widget.ImageView, com.naver.glink.android.sdk.ui.widget.photoview.c
    public ImageView.ScaleType getScaleType() {
        return this.e.getScaleType();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public Bitmap getVisibleRectangleBitmap() {
        return this.e.getVisibleRectangleBitmap();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        b();
        super.onAttachedToWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        this.e.b();
        super.onDetachedFromWindow();
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setAllowParentInterceptOnEdge(boolean z) {
        this.e.setAllowParentInterceptOnEdge(z);
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (this.e != null) {
            this.e.d();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        if (this.e != null) {
            this.e.d();
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (this.e != null) {
            this.e.d();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    @Deprecated
    public void setMaxScale(float f) {
        setMaximumScale(f);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setMaximumScale(float f) {
        this.e.setMaximumScale(f);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setMediumScale(float f) {
        this.e.setMediumScale(f);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    @Deprecated
    public void setMidScale(float f) {
        setMediumScale(f);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    @Deprecated
    public void setMinScale(float f) {
        setMinimumScale(f);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setMinimumScale(float f) {
        this.e.setMinimumScale(f);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.e.setOnDoubleTapListener(onDoubleTapListener);
    }

    @Override // android.view.View, com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.e.setOnLongClickListener(onLongClickListener);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setOnMatrixChangeListener(d.c cVar) {
        this.e.setOnMatrixChangeListener(cVar);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setOnPhotoTapListener(d.InterfaceC0065d interfaceC0065d) {
        this.e.setOnPhotoTapListener(interfaceC0065d);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setOnScaleChangeListener(d.e eVar) {
        this.e.setOnScaleChangeListener(eVar);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setOnViewTapListener(d.f fVar) {
        this.e.setOnViewTapListener(fVar);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setPhotoViewRotation(float f) {
        this.e.setRotationTo(f);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setRotationBy(float f) {
        this.e.setRotationBy(f);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setRotationTo(float f) {
        this.e.setRotationTo(f);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setScale(float f) {
        this.e.setScale(f);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setScale(float f, float f2, float f3, boolean z) {
        this.e.setScale(f, f2, f3, z);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setScale(float f, boolean z) {
        this.e.setScale(f, z);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setScaleLevels(float f, float f2, float f3) {
        this.e.setScaleLevels(f, f2, f3);
    }

    @Override // android.widget.ImageView, com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (this.e != null) {
            this.e.setScaleType(scaleType);
        } else {
            this.f = scaleType;
        }
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setZoomTransitionDuration(int i) {
        this.e.setZoomTransitionDuration(i);
    }

    @Override // com.naver.glink.android.sdk.ui.widget.photoview.c
    public void setZoomable(boolean z) {
        this.e.setZoomable(z);
    }
}
