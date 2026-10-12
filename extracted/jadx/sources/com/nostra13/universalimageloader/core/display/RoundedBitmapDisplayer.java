package com.nostra13.universalimageloader.core.display;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class RoundedBitmapDisplayer implements BitmapDisplayer {
    protected final int cornerRadius;
    protected final int margin;

    protected static class RoundedDrawable extends Drawable {
        protected final BitmapShader bitmapShader;
        protected final float cornerRadius;
        protected final int margin;
        protected final RectF mRect = new RectF();
        protected final Paint paint = new Paint();

        RoundedDrawable(Bitmap bitmap, int i, int i2) {
            this.cornerRadius = i;
            this.margin = i2;
            this.bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            this.paint.setAntiAlias(true);
            this.paint.setShader(this.bitmapShader);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            canvas.drawRoundRect(this.mRect, this.cornerRadius, this.cornerRadius, this.paint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        protected void onBoundsChange(Rect rect) {
            super.onBoundsChange(rect);
            this.mRect.set(this.margin, this.margin, rect.width() - this.margin, rect.height() - this.margin);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            this.paint.setAlpha(i);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.paint.setColorFilter(colorFilter);
        }
    }

    public RoundedBitmapDisplayer(int i) {
        this(i, 0);
    }

    public RoundedBitmapDisplayer(int i, int i2) {
        this.cornerRadius = i;
        this.margin = i2;
    }

    @Override // com.nostra13.universalimageloader.core.display.BitmapDisplayer
    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
        if (!(imageAware instanceof ImageViewAware)) {
            throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
        }
        imageAware.setImageDrawable(new RoundedDrawable(bitmap, this.cornerRadius, this.margin));
    }
}
