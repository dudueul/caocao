package kr.co.nexon.mdev.android.push.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseArray;
import com.nexon.core.log.ToyLog;
import com.nexon.core.util.NXJsonUtil;

/* JADX INFO: compiled from: NPNotification.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class GNPersistent {
    GNPersistent() {
    }

    public static void delete(Context context, int i, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        editorEdit.remove("" + i);
        editorEdit.commit();
    }

    public static void deleteAll(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        editorEdit.clear();
        editorEdit.commit();
    }

    public static NPNotificationData get(Context context, String str, String str2) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(str2, 0);
        try {
            ToyLog.d("ID:" + str + " " + sharedPreferences.getString(str, "{}"));
            return (NPNotificationData) NXJsonUtil.fromObject(sharedPreferences.getString(str, "{}"), NPNotificationData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SparseArray<NPNotificationData> getAll(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        SparseArray<NPNotificationData> sparseArray = new SparseArray<>();
        for (String str2 : sharedPreferences.getAll().keySet()) {
            NPNotificationData nPNotificationData = null;
            ToyLog.d("AlramID " + str2);
            try {
                nPNotificationData = (NPNotificationData) NXJsonUtil.fromObject(sharedPreferences.getString(str2, "{}"), NPNotificationData.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sparseArray.put(Integer.parseInt(str2), nPNotificationData);
        }
        return sparseArray;
    }

    public static String loadReceiveClassName(Context context) {
        return context.getSharedPreferences("RECV_CLASS_NAME", 0).getString("RECV_CLASS_NAME", "");
    }

    public static void safe(Context context, NPNotificationData nPNotificationData, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        editorEdit.putString("" + nPNotificationData.notificationID, NXJsonUtil.toJsonString(nPNotificationData));
        editorEdit.commit();
    }

    public static void saveReceiveClassName(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("RECV_CLASS_NAME", 0).edit();
        editorEdit.putString("RECV_CLASS_NAME", str);
        editorEdit.commit();
    }
}
