package com.naver.glink.android.sdk.configure;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.util.SparseIntArray;
import android.util.StateSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.aa;
import com.naver.glink.android.sdk.a.x;

/* JADX INFO: compiled from: SdkTheme.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class d {

    @ColorInt
    public final int a;

    @ColorInt
    public final int b;

    @ColorInt
    private final int c;
    private SparseIntArray d;

    /* JADX INFO: compiled from: SdkTheme.java */
    private static class a implements Transformation<Bitmap> {
        private final BitmapPool a;

        @ColorInt
        private final int b;

        private a(Context context, @ColorInt int i) {
            this(Glide.get(context).getBitmapPool(), i);
        }

        private a(BitmapPool bitmapPool, @ColorInt int i) {
            this.a = bitmapPool;
            this.b = i;
        }

        @Override // com.bumptech.glide.load.Transformation
        public String getId() {
            return "WidgetTransformation(" + this.b + ")";
        }

        @Override // com.bumptech.glide.load.Transformation
        public Resource<Bitmap> transform(Resource<Bitmap> resource, int i, int i2) {
            Bitmap bitmap = resource.get();
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Bitmap.Config config = bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888;
            Bitmap bitmap2 = this.a.get(width, height, config);
            Bitmap bitmapCreateBitmap = bitmap2 == null ? Bitmap.createBitmap(width, height, config) : bitmap2;
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            Paint paint = new Paint();
            paint.setColor(this.b);
            paint.setAntiAlias(true);
            canvas.drawCircle(width / 2, height / 2, (width / 2) - 10, paint);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
            return BitmapResource.obtain(bitmapCreateBitmap, this.a);
        }
    }

    public d() {
        this("#19d3c6", "#44484e");
    }

    public d(String str, String str2) {
        this.a = Color.parseColor(str);
        this.c = a(91);
        this.b = Color.parseColor(str2);
    }

    private Context a() {
        return com.naver.glink.android.sdk.d.r();
    }

    private Drawable a(@ColorInt int i, int i2) {
        new ColorDrawable(i).setAlpha(i2);
        return new ColorDrawable(i);
    }

    private Drawable a(Context context, @ColorInt int i) {
        Drawable drawable = context.getResources().getDrawable(R.drawable.floating_button_background);
        if (drawable instanceof GradientDrawable) {
            ((GradientDrawable) drawable).setColor(this.a);
        }
        drawable.setAlpha(i);
        return drawable;
    }

    private ColorStateList b() {
        return d(ViewCompat.MEASURED_STATE_MASK);
    }

    private Drawable b(@ColorInt int i) {
        return a(i, 255);
    }

    private Drawable c(@DrawableRes int i) {
        Context contextR = com.naver.glink.android.sdk.d.r();
        if (contextR == null) {
            return null;
        }
        return ContextCompat.getDrawable(contextR, i);
    }

    private ColorStateList d(@ColorInt int i) {
        return new ColorStateList(new int[][]{new int[]{android.R.attr.state_activated}, new int[]{android.R.attr.state_pressed}, StateSet.WILD_CARD}, new int[]{this.a, this.a, i});
    }

    private boolean g(View view) {
        return view == null || com.naver.glink.android.sdk.d.r() == null;
    }

    @ColorInt
    public int a(int i) {
        return Color.argb(i, Color.red(this.a), Color.green(this.a), Color.blue(this.a));
    }

    public void a(final Resources resources, int i, ImageView imageView) {
        if (this.d == null) {
            this.d = new SparseIntArray();
            this.d.put(R.id.float_menu, R.drawable.cf_btn_wdclose_normal);
            this.d.put(R.id.float_item_cafe, R.drawable.cf_btn_wdhome_normal);
            this.d.put(R.id.float_item_capture, R.drawable.cf_btn_wdcapture_normal);
            this.d.put(R.id.float_item_video, R.drawable.cf_btn_wdvideo_normal);
            this.d.put(R.id.float_item_live, R.drawable.cf_btn_wdlive_normal);
        }
        int i2 = this.d.get(i, -1);
        if (g(imageView) || resources == null || i2 == -1) {
            return;
        }
        Glide.with(com.naver.glink.android.sdk.d.r()).load(Integer.valueOf(i2)).asBitmap().transform(new a(com.naver.glink.android.sdk.d.r(), this.a)).into(new BitmapImageViewTarget(imageView) { // from class: com.naver.glink.android.sdk.configure.d.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.bumptech.glide.request.target.BitmapImageViewTarget, com.bumptech.glide.request.target.ImageViewTarget
            public void setResource(Bitmap bitmap) {
                StateListDrawable stateListDrawable = new StateListDrawable();
                BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, bitmap);
                bitmapDrawable.setAlpha(217);
                stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, bitmapDrawable);
                stateListDrawable.addState(StateSet.WILD_CARD, new BitmapDrawable(resources, bitmap));
                getView().setImageDrawable(stateListDrawable);
            }
        });
    }

    public void a(View view) {
        if (g(view)) {
            return;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (com.naver.glink.android.sdk.d.g()) {
            stateListDrawable.addState(new int[]{android.R.attr.state_activated}, b(this.a));
        }
        stateListDrawable.addState(StateSet.WILD_CARD, b(this.b));
        aa.a(view, stateListDrawable);
    }

    public void a(View view, @DrawableRes int i) {
        if (g(view)) {
            return;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, a(a(), 153));
        stateListDrawable.addState(StateSet.WILD_CARD, a(a(), 230));
        aa.a(view.findViewById(R.id.floating_button_background), stateListDrawable);
        view.findViewById(R.id.floating_button_image).setBackgroundResource(i);
    }

    public void a(View view, int i, int i2) {
        ImageView imageView = (ImageView) view.findViewById(i);
        if (imageView != null) {
            Drawable drawableMutate = DrawableCompat.wrap(imageView.getDrawable()).mutate();
            DrawableCompat.setTintList(drawableMutate, d(view.getContext().getResources().getColor(R.color.black4)));
            imageView.setImageDrawable(drawableMutate);
        }
        TextView textView = (TextView) view.findViewById(i2);
        if (textView != null) {
            textView.setTextColor(d(view.getContext().getResources().getColor(R.color.black4)));
        }
    }

    public void a(View view, boolean z) {
        if (g(view)) {
            return;
        }
        Drawable background = view.getBackground();
        if (background instanceof LayerDrawable) {
            ((GradientDrawable) ((LayerDrawable) background).getDrawable(0)).setStroke(x.a(2.0f), z ? this.a : -16777216);
        }
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            int[][] iArr = {new int[]{android.R.attr.state_checked}, StateSet.WILD_CARD};
            int[] iArr2 = new int[2];
            iArr2[0] = z ? this.a : -16777216;
            iArr2[1] = -16777216;
            textView.setTextColor(new ColorStateList(iArr, iArr2));
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, background);
        stateListDrawable.addState(StateSet.WILD_CARD, new ColorDrawable(view.getResources().getColor(android.R.color.transparent)));
        aa.a(view, stateListDrawable);
    }

    public void a(View view, boolean z, int i) {
        if (g(view)) {
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(x.a(i));
        if (z) {
            gradientDrawable.setColor(this.a);
        } else {
            gradientDrawable.setColor(view.getResources().getColor(R.color.white1));
            gradientDrawable.setStroke(x.a(1.0f), this.a);
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(0);
        gradientDrawable2.setCornerRadius(x.a(i));
        if (z) {
            gradientDrawable2.setColor(this.c);
        } else {
            gradientDrawable2.setColor(view.getResources().getColor(R.color.white1));
            gradientDrawable2.setStroke(x.a(1.0f), this.c);
        }
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setShape(0);
        gradientDrawable3.setCornerRadius(x.a(i));
        if (z) {
            gradientDrawable3.setColor(view.getResources().getColor(R.color.gray3));
        } else {
            gradientDrawable3.setColor(view.getResources().getColor(R.color.white1));
            gradientDrawable3.setStroke(x.a(1.0f), view.getResources().getColor(R.color.gray3));
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, gradientDrawable2);
        stateListDrawable.addState(new int[]{-16842910}, gradientDrawable3);
        stateListDrawable.addState(StateSet.WILD_CARD, gradientDrawable);
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            int[][] iArr = {new int[]{android.R.attr.state_pressed}, new int[]{-16842910}, StateSet.WILD_CARD};
            int[] iArr2 = new int[3];
            iArr2[0] = z ? -1 : this.c;
            iArr2[1] = z ? -1 : view.getResources().getColor(R.color.gray3);
            iArr2[2] = z ? -1 : this.a;
            textView.setTextColor(new ColorStateList(iArr, iArr2));
        }
        aa.a(view, stateListDrawable);
    }

    public void a(RadioButton radioButton) {
        Drawable drawable = radioButton.getResources().getDrawable(R.drawable.cf_icon_radio_button_off);
        Drawable drawable2 = radioButton.getResources().getDrawable(R.drawable.cf_icon_radio_button_on);
        drawable2.setColorFilter(this.a, PorterDuff.Mode.SRC_IN);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, drawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, drawable);
        radioButton.setButtonDrawable(stateListDrawable);
        radioButton.setTextColor(new ColorStateList(new int[][]{new int[]{android.R.attr.state_checked}, StateSet.WILD_CARD}, new int[]{radioButton.getResources().getColor(R.color.black4), radioButton.getResources().getColor(R.color.gray2)}));
    }

    public void a(SeekBar seekBar) {
        if (g(seekBar)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            seekBar.setProgressTintList(ColorStateList.valueOf(this.a));
        } else {
            ((LayerDrawable) seekBar.getProgressDrawable()).findDrawableByLayerId(android.R.id.progress).setColorFilter(this.a, PorterDuff.Mode.SRC_IN);
        }
    }

    public void b(View view) {
        Drawable drawable;
        if (g(view) || !(view instanceof ImageView) || (drawable = ((ImageView) view).getDrawable()) == null) {
            return;
        }
        Drawable drawableMutate = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTintList(drawableMutate, d(view.getContext().getResources().getColor(R.color.black4)));
        ((ImageView) view).setImageDrawable(drawableMutate);
    }

    public void b(View view, boolean z) {
        a(view, z, 22);
    }

    public void c(View view) {
        Drawable drawableC;
        if (g(view) || (drawableC = c(R.drawable.cf_blt_go)) == null) {
            return;
        }
        Drawable drawableMutate = DrawableCompat.wrap(drawableC).mutate();
        DrawableCompat.setTintList(drawableMutate, b());
        aa.a(view, drawableMutate);
    }

    public void d(View view) {
        if (g(view)) {
            return;
        }
        ((TextView) view.findViewById(R.id.name)).setTextColor(b());
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_activated}, a(a(), 255));
        aa.a(view.findViewById(R.id.check_icon), stateListDrawable);
    }

    public void e(View view) {
        if (g(view)) {
            return;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, b(this.c));
        stateListDrawable.addState(new int[]{-16842910}, b(Color.parseColor("#f2f2f2")));
        stateListDrawable.addState(StateSet.WILD_CARD, b(this.a));
        aa.a(view, stateListDrawable);
    }

    public void f(View view) {
        int color;
        if (g(view)) {
            return;
        }
        int color2 = this.a;
        if (this.a == Color.parseColor("#19d3c6")) {
            color = Color.parseColor("#1199b3");
        } else if (this.a == Color.parseColor("#00c73c")) {
            color2 = Color.parseColor("#00de74");
            color = Color.parseColor("#00bbd3");
        } else {
            color = this.a;
        }
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{color2, color});
        gradientDrawable.setGradientCenter(0.7f, 0.5f);
        aa.a(view, gradientDrawable);
    }
}
