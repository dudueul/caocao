package kr.co.nexon.mdev.android.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.util.NXStringUtil;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Locale;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXLocalizedString {
    public static String getText(Context context, String str, int i) {
        ToyLog.v("base Locale setting: " + context.getResources().getConfiguration().locale);
        if (NXStringUtil.isNull(str)) {
            str = Locale.getDefault().toString();
        }
        Configuration configuration = new Configuration();
        if (str.contains(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR)) {
            String[] strArrSplit = str.split(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            configuration.locale = new Locale(strArrSplit[0], strArrSplit[1]);
        } else {
            configuration.locale = new Locale(str);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        Resources resources = new Resources(context.getAssets(), displayMetrics, configuration);
        ToyLog.v("temp Locale setting: " + configuration.locale);
        String string = i == 0 ? "" : resources.getString(i);
        new Resources(context.getAssets(), displayMetrics, context.getResources().getConfiguration());
        return string;
    }
}
