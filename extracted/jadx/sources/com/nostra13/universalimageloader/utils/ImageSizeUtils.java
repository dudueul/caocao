package com.nostra13.universalimageloader.utils;

import android.opengl.GLES10;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ImageSizeUtils {
    private static final int DEFAULT_MAX_BITMAP_DIMENSION = 2048;
    private static ImageSize maxBitmapSize;

    static {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        int iMax = Math.max(iArr[0], 2048);
        maxBitmapSize = new ImageSize(iMax, iMax);
    }

    private ImageSizeUtils() {
    }

    public static int computeImageSampleSize(ImageSize imageSize, ImageSize imageSize2, ViewScaleType viewScaleType, boolean z) {
        int width = imageSize.getWidth();
        int height = imageSize.getHeight();
        int width2 = imageSize2.getWidth();
        int height2 = imageSize2.getHeight();
        int iMin = 1;
        int i = width / width2;
        int i2 = height / height2;
        switch (viewScaleType) {
            case FIT_INSIDE:
                if (!z) {
                    iMin = Math.max(i, i2);
                } else {
                    while (true) {
                        if (width / 2 >= width2 || height / 2 >= height2) {
                            width /= 2;
                            height /= 2;
                            iMin *= 2;
                        }
                    }
                }
                break;
            case CROP:
                if (!z) {
                    iMin = Math.min(i, i2);
                } else {
                    while (width / 2 >= width2 && height / 2 >= height2) {
                        width /= 2;
                        height /= 2;
                        iMin *= 2;
                    }
                }
                break;
        }
        if (iMin < 1) {
            return 1;
        }
        return iMin;
    }

    public static float computeImageScale(ImageSize imageSize, ImageSize imageSize2, ViewScaleType viewScaleType, boolean z) {
        int i;
        int i2;
        int width = imageSize.getWidth();
        int height = imageSize.getHeight();
        int width2 = imageSize2.getWidth();
        int height2 = imageSize2.getHeight();
        float f = width / width2;
        float f2 = height / height2;
        if ((viewScaleType != ViewScaleType.FIT_INSIDE || f < f2) && (viewScaleType != ViewScaleType.CROP || f >= f2)) {
            i = (int) (width / f2);
            i2 = height2;
        } else {
            i = width2;
            i2 = (int) (height / f);
        }
        if ((z || i >= width || i2 >= height) && (!z || i == width || i2 == height)) {
            return 1.0f;
        }
        return i / width;
    }

    public static int computeMinImageSampleSize(ImageSize imageSize) {
        return Math.max((int) Math.ceil(imageSize.getWidth() / maxBitmapSize.getWidth()), (int) Math.ceil(imageSize.getHeight() / maxBitmapSize.getHeight()));
    }

    public static ImageSize defineTargetSizeForView(ImageAware imageAware, ImageSize imageSize) {
        int width = imageAware.getWidth();
        if (width <= 0) {
            width = imageSize.getWidth();
        }
        int height = imageAware.getHeight();
        if (height <= 0) {
            height = imageSize.getHeight();
        }
        return new ImageSize(width, height);
    }
}
