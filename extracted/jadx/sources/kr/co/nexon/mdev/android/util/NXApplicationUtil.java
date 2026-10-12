package kr.co.nexon.mdev.android.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.os.PowerManager;
import com.nexon.core.log.ToyLog;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXApplicationUtil {
    private static final int APP_INFO_META_TYPE_BOOLEAN = 2;
    private static final int APP_INFO_META_TYPE_DOUBLE = 3;
    private static final int APP_INFO_META_TYPE_FLOAT = 4;
    private static final int APP_INFO_META_TYPE_INT = 0;
    private static final int APP_INFO_META_TYPE_STRING = 1;

    public static String getAndroidManifest(Activity activity) {
        try {
            XmlResourceParser xmlResourceParserOpenXmlResourceParser = activity.getApplicationContext().createPackageContext(activity.getPackageName(), 0).getAssets().openXmlResourceParser("AndroidManifest.xml");
            String strSubstring = "";
            StringBuilder sb = new StringBuilder();
            boolean z = true;
            while (true) {
                int iNextToken = xmlResourceParserOpenXmlResourceParser.nextToken();
                if (iNextToken == 1) {
                    xmlResourceParserOpenXmlResourceParser.close();
                    return sb.toString();
                }
                if (iNextToken == 2) {
                    if (z) {
                        z = false;
                    } else {
                        strSubstring = strSubstring + "    ";
                    }
                }
                if (iNextToken == 3) {
                    int length = strSubstring.length();
                    strSubstring = strSubstring.substring(0, length > 3 ? length - 4 : 0);
                }
                for (int i = 0; i < xmlResourceParserOpenXmlResourceParser.getAttributeCount(); i++) {
                    sb.append(strSubstring + xmlResourceParserOpenXmlResourceParser.getName() + " " + xmlResourceParserOpenXmlResourceParser.getAttributeName(i) + "=\"" + xmlResourceParserOpenXmlResourceParser.getAttributeValue(i) + "\"\n");
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "Manifest Read fail." + e.toString();
        } catch (IOException e2) {
            e2.printStackTrace();
            return "Manifest Read fail." + e2.toString();
        } catch (RuntimeException e3) {
            e3.printStackTrace();
            return "Manifest Read fail." + e3.toString();
        } catch (XmlPullParserException e4) {
            e4.printStackTrace();
            return "Manifest Read fail." + e4.toString();
        } catch (Exception e5) {
            e5.printStackTrace();
            return "Manifest Read fail." + e5.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return "Manifest Read fail. " + th.toString();
        }
    }

    private static Object getAppMetaData(Context context, int i, String str) {
        Object objValueOf = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            switch (i) {
                case 0:
                    objValueOf = Integer.valueOf(applicationInfo.metaData.getInt(str));
                    break;
                case 1:
                    objValueOf = applicationInfo.metaData.getString(str);
                    break;
                case 2:
                    objValueOf = Boolean.valueOf(applicationInfo.metaData.getBoolean(str));
                    break;
                case 3:
                    objValueOf = Double.valueOf(applicationInfo.metaData.getDouble(str));
                    break;
                case 4:
                    objValueOf = Float.valueOf(applicationInfo.metaData.getFloat(str));
                    break;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return objValueOf;
    }

    public static boolean getAppMetaDataTypeBoolean(Context context, String str) {
        try {
            return ((Boolean) getAppMetaData(context, 2, str)).booleanValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static double getAppMetaDataTypeDouble(Context context, String str) {
        try {
            return ((Double) getAppMetaData(context, 0, str)).doubleValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1.0d;
        }
    }

    public static float getAppMetaDataTypeFloat(Context context, String str) {
        try {
            return ((Float) getAppMetaData(context, 0, str)).floatValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1.0f;
        }
    }

    public static int getAppMetaDataTypeInteger(Context context, String str) {
        try {
            return ((Integer) getAppMetaData(context, 0, str)).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String getAppMetaDataTypeString(Context context, String str) {
        Object appMetaData = getAppMetaData(context, 1, str);
        if (appMetaData instanceof String) {
            return (String) appMetaData;
        }
        return null;
    }

    public static boolean isRunningForeground(Context context) {
        ToyLog.d("build version:" + Build.VERSION.SDK_INT);
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (!(Build.VERSION.SDK_INT >= 20 ? powerManager.isInteractive() : powerManager.isScreenOn())) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 21) {
            String className = "";
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
            if (runningTasks != null && runningTasks.size() > 0) {
                className = runningTasks.get(0).topActivity.getClassName();
            }
            return context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName().equals(className);
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null || runningAppProcesses.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100) {
                for (String str : runningAppProcessInfo.pkgList) {
                    if (str.equals(context.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
