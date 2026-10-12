package kr.co.nexon.mdev.android.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.nexon.core.log.ToyLog;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXActivityUtil {
    public static String getLuancherActivityName(Context context) {
        try {
            return context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName();
        } catch (Exception e) {
            ToyLog.d("In getLauncherActivityName, exception:" + e.toString());
            return null;
        }
    }

    public static String getTopActivity(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getClassName();
    }

    public static String getTopActivityPackageName(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getPackageName();
    }

    public static boolean isIntentAvailable(Context context, Intent intent) {
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
        return listQueryIntentActivities != null && listQueryIntentActivities.size() > 0;
    }
}
