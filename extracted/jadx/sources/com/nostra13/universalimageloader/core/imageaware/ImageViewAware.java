package com.nostra13.universalimageloader.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.utils.L;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ImageViewAware implements ImageAware {
    protected boolean checkActualViewSize;
    protected Reference<ImageView> imageViewRef;

    public ImageViewAware(ImageView imageView) {
        this(imageView, true);
    }

    public ImageViewAware(ImageView imageView, boolean z) {
        this.imageViewRef = new WeakReference(imageView);
        this.checkActualViewSize = z;
    }

    private static int getImageViewFieldValue(Object obj, String str) {
        try {
            Field declaredField = ImageView.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            int iIntValue = ((Integer) declaredField.get(obj)).intValue();
            if (iIntValue <= 0 || iIntValue >= Integer.MAX_VALUE) {
                return 0;
            }
            return iIntValue;
        } catch (Exception e) {
            L.e(e);
            return 0;
        }
    }

    @Override // com.nostra13.universalimageloader.core.imageaware.ImageAware
    public int getHeight() {
        ImageView imageView = this.imageViewRef.get();
        if (imageView == null) {
            return 0;
        }
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        int height = 0;
        if (this.checkActualViewSize && layoutParams != null && layoutParams.height != -2) {
            height = imageView.getHeight();
        }
        if (height <= 0 && layoutParams != null) {
            height = layoutParams.height;
        }
        return height <= 0 ? getImageViewFieldValue(imageView, "mMaxHeight") : height;
    }

    @Override // com.nostra13.universalimageloader.core.imageaware.ImageAware
    public int getId() {
        ImageView imageView = this.imageViewRef.get();
        return imageView == null ? super.hashCode() : imageView.hashCode();
    }

    @Override // com.nostra13.universalimageloader.core.imageaware.ImageAware
    public ViewScaleType getScaleType() {
        ImageView imageView = this.imageViewRef.get();
        if (imageView != null) {
            return ViewScaleType.fromImageView(imageView);
        }
        return null;
    }

    @Override // com.nostra13.universalimageloader.core.imageaware.ImageAware
    public int getWidth() {
        ImageView imageView = this.imageViewRef.get();
        if (imageView == null) {
            return 0;
        }
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        int width = 0;
        if (this.checkActualViewSize && layoutParams != null && layoutParams.width != -2) {
            width = imageView.getWidth();
        }
        if (width <= 0 && layoutParams != null) {
            width = layoutParams.width;
        }
        return width <= 0 ? getImageViewFieldValue(imageView, "mMaxWidth") : width;
    }

    @Override // com.nostra13.universalimageloader.core.imageaware.ImageAware
    public ImageView getWrappedView() {
        return this.imageViewRef.get();
    }

    @Override // com.nostra13.universalimageloader.core.imageaware.ImageAware
    public boolean isCollected() {
        return this.imageViewRef.get() == null;
    }

    @Override // com.nostra13.universalimageloader.core.imageaware.ImageAware
    public boolean setImageBitmap(Bitmap bitmap) {
        ImageView imageView = this.imageViewRef.get();
        if (imageView == null) {
            return false;
        }
        imageView.setImageBitmap(bitmap);
        return true;
    }

    @Override // com.nostra13.universalimageloader.core.imageaware.ImageAware
    public boolean setImageDrawable(Drawable drawable) {
        ImageView imageView = this.imageViewRef.get();
        if (imageView == null) {
            return false;
        }
        imageView.setImageDrawable(drawable);
        return true;
    }
}
