package kr.co.nexon.mdev.android.push.local;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import com.google.gson.Gson;
import com.nexon.core.log.ToyLog;
import java.util.Calendar;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNotificationRestore extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("NotificationData", 0);
        Set<String> setKeySet = sharedPreferences.getAll().keySet();
        ToyLog.d("NPNotificationRestore");
        for (String str : setKeySet) {
            String string = sharedPreferences.getString(str, "{}");
            try {
                NPNotificationData nPNotificationData = (NPNotificationData) new Gson().fromJson(string, NPNotificationData.class);
                ToyLog.d("Restore local notification " + string);
                if (nPNotificationData.confirmedNotification) {
                    ToyLog.d("already confirmed alarm : " + str + " / Remove data.");
                    sharedPreferences.edit().remove(str).commit();
                } else if (nPNotificationData.time == null) {
                    ToyLog.d("time dosen't exist : " + str + " / Remove data.");
                    sharedPreferences.edit().remove(str).commit();
                } else {
                    Calendar calendar = Calendar.getInstance();
                    if (nPNotificationData.pushType == 1) {
                        calendar.add(1, nPNotificationData.time.year);
                        calendar.add(2, nPNotificationData.time.month);
                        calendar.add(5, nPNotificationData.time.day);
                        calendar.add(11, nPNotificationData.time.hour);
                        calendar.add(12, nPNotificationData.time.minute);
                        calendar.add(13, nPNotificationData.time.sec);
                    } else if (nPNotificationData.pushType == 0) {
                        calendar.set(1, nPNotificationData.time.year);
                        calendar.set(2, nPNotificationData.time.month - 1);
                        calendar.set(5, nPNotificationData.time.day);
                        calendar.set(11, nPNotificationData.time.hour);
                        calendar.set(12, nPNotificationData.time.minute);
                        calendar.set(13, nPNotificationData.time.sec);
                    }
                    ToyLog.d("dispatch alarm regist calendar = " + calendar);
                    Intent intent2 = new Intent(context, (Class<?>) NPNotification.class);
                    intent2.setAction(str);
                    intent2.putExtra("id", nPNotificationData.notificationID);
                    PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent2, 134217728);
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
                    try {
                        if (Build.VERSION.SDK_INT >= 23) {
                            alarmManager.setExactAndAllowWhileIdle(0, calendar.getTimeInMillis(), broadcast);
                        } else if (Build.VERSION.SDK_INT >= 19) {
                            alarmManager.setExact(0, calendar.getTimeInMillis(), broadcast);
                        } else {
                            alarmManager.set(0, calendar.getTimeInMillis(), broadcast);
                        }
                    } catch (Exception e) {
                        ToyLog.d("AlarmManager.set() failed. error message : " + e.getMessage());
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
