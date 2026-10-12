package kr.co.nexon.mdev.android.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXResourcesUtil {
    public static int getColorByResId(Context context, @ColorRes int i) {
        return Build.VERSION.SDK_INT >= 23 ? context.getResources().getColor(i, context.getTheme()) : context.getResources().getColor(i);
    }

    public static ColorStateList getColorStateListByResId(Context context, @ColorRes int i) {
        return Build.VERSION.SDK_INT >= 23 ? context.getResources().getColorStateList(i, context.getTheme()) : context.getResources().getColorStateList(i);
    }

    public static Drawable getDrawableByResId(Context context, @DrawableRes int i) {
        return Build.VERSION.SDK_INT >= 23 ? context.getResources().getDrawable(i, context.getTheme()) : context.getResources().getDrawable(i);
    }
}
