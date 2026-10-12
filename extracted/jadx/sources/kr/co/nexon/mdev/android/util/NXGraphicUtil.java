package kr.co.nexon.mdev.android.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import com.nexon.core.log.ToyLog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXGraphicUtil {
    public static int dipToPix(Context context, double d) {
        return (int) (((double) context.getResources().getDisplayMetrics().density) * d);
    }

    public static Drawable getImageByFile(Activity activity, String str) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDensity = 240;
            return Drawable.createFromResourceStream(activity.getResources(), null, activity.getAssets().open(str), null, options);
        } catch (Exception e) {
            ToyLog.d("load file error : " + e.getMessage());
            return null;
        }
    }
}
