package kr.co.nexon.mdev.android.push.local;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.SparseArray;
import com.nexon.core.log.ToyLog;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Iterator;
import kr.co.nexon.npaccount.gcm.NXPBaseFcmMessagingService;
import kr.co.nexon.npaccount.gcm.NXPFcmMessagingService;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNotification extends BroadcastReceiver {
    private static final String NOTIFICATION_DATA_PREF_NAME = "NotificationData";

    public static void cancel(Context context, int i) {
        internalCancel(context, i);
    }

    public static void cancelAll(Context context) {
        SparseArray<NPNotificationData> localNotifications = getLocalNotifications(context);
        for (int i = 0; i < localNotifications.size(); i++) {
            int iKeyAt = localNotifications.keyAt(i);
            ToyLog.d("KeyAt " + iKeyAt);
            internalCancel(context, iKeyAt);
        }
    }

    @Deprecated
    public static void clearLocalNotifications(Context context) {
        GNPersistent.deleteAll(context, NOTIFICATION_DATA_PREF_NAME);
    }

    public static void dispatch(Context context, NPNotificationData nPNotificationData) {
        if (nPNotificationData.pushType == 2) {
            dispatchNow(context, nPNotificationData);
            return;
        }
        Calendar calendar = Calendar.getInstance();
        if (nPNotificationData.pushType == 1) {
            calendar.add(1, nPNotificationData.time.year);
            calendar.add(2, nPNotificationData.time.month);
            calendar.add(5, nPNotificationData.time.day);
            calendar.add(11, nPNotificationData.time.hour);
            calendar.add(12, nPNotificationData.time.minute);
            calendar.add(13, nPNotificationData.time.sec);
            nPNotificationData.pushType = 0;
            nPNotificationData.time.year = calendar.get(1);
            nPNotificationData.time.month = calendar.get(2) + 1;
            nPNotificationData.time.day = calendar.get(5);
            nPNotificationData.time.hour = calendar.get(11);
            nPNotificationData.time.minute = calendar.get(12);
            nPNotificationData.time.sec = calendar.get(13);
        } else if (nPNotificationData.pushType == 0) {
            calendar.set(1, nPNotificationData.time.year);
            calendar.set(2, nPNotificationData.time.month - 1);
            calendar.set(5, nPNotificationData.time.day);
            calendar.set(11, nPNotificationData.time.hour);
            calendar.set(12, nPNotificationData.time.minute);
            calendar.set(13, nPNotificationData.time.sec);
        }
        ToyLog.d("dispatch alarm regist calendar = " + calendar);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, prepare(context, nPNotificationData), 134217728);
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

    @Deprecated
    private static void dispatchEvents(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        Iterator<String> it = sharedPreferences.getAll().keySet().iterator();
        while (it.hasNext()) {
            try {
                JSONObject jSONObject = new JSONObject(sharedPreferences.getString(it.next(), ""));
                jSONObject.getInt("id");
                jSONObject.getString("title");
                jSONObject.getString("message");
                jSONObject.getInt("number");
                jSONObject.getString("sound");
                jSONObject.getString("custom");
                jSONObject.getBoolean("didOpen");
                if (str.equals("NotificationLocalEvent")) {
                }
            } catch (JSONException e) {
            }
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        editorEdit.clear();
        editorEdit.commit();
    }

    private static void dispatchNow(Context context, NPNotificationData nPNotificationData) {
        sendReceiveClass(context, nPNotificationData);
    }

    public static SparseArray<NPNotificationData> getLocalNotifications(Context context) {
        return GNPersistent.getAll(context, NOTIFICATION_DATA_PREF_NAME);
    }

    public static void initLocalPush(Context context, String str) {
        GNPersistent.saveReceiveClassName(context, str);
    }

    private static void internalCancel(Context context, int i) {
        NPNotificationData nPNotificationData = GNPersistent.get(context, "" + i, NOTIFICATION_DATA_PREF_NAME);
        if (nPNotificationData != null) {
            nPNotificationData.time = null;
            GNPersistent.safe(context, nPNotificationData, NOTIFICATION_DATA_PREF_NAME);
        }
        Intent intent = new Intent(context, (Class<?>) NPNotification.class);
        intent.setAction("" + i);
        intent.putExtra("id", i);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 134217728);
        try {
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(broadcast);
            broadcast.cancel();
        } catch (Exception e) {
            ToyLog.d("AlarmManager.cancel() or PendingIntent.cancel() error message : " + e.getMessage());
        }
    }

    private static Intent prepare(Context context, NPNotificationData nPNotificationData) {
        GNPersistent.safe(context, nPNotificationData, NOTIFICATION_DATA_PREF_NAME);
        Intent intent = new Intent(context, (Class<?>) NPNotification.class);
        intent.setAction("" + nPNotificationData.notificationID);
        intent.putExtra("id", nPNotificationData.notificationID);
        return intent;
    }

    @Deprecated
    public static void removeAllNotificationBarData(Context context) {
        ((NotificationManager) context.getSystemService("notification")).cancelAll();
    }

    private static void sendReceiveClass(Context context, NPNotificationData nPNotificationData) {
        Bundle bundle = new Bundle();
        bundle.putString("message", nPNotificationData.message);
        bundle.putString(NXPBaseFcmMessagingService.KEY_META, nPNotificationData.meta);
        bundle.putString("notificationID", String.valueOf(nPNotificationData.notificationID));
        bundle.putString("isLocalPush", "true");
        try {
            String strLoadReceiveClassName = GNPersistent.loadReceiveClassName(context);
            if (strLoadReceiveClassName.equals(NXPFcmMessagingService.class.getName())) {
                new NXPFcmMessagingService().onLocalPush(context, bundle);
            } else if (strLoadReceiveClassName.equals(NXPBaseFcmMessagingService.class.getName())) {
                new NXPBaseFcmMessagingService().onLocalPush(context, bundle);
            } else {
                Class<?> cls = Class.forName(strLoadReceiveClassName);
                cls.getMethod("onLocalPush", Context.class, Bundle.class).invoke(cls.newInstance(), context, bundle);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        } catch (InstantiationException e4) {
            e4.printStackTrace();
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ToyLog.d("onReceive ");
        Bundle extras = intent.getExtras();
        if (!extras.containsKey("id")) {
            ToyLog.d("containsKey \"id\" not found.");
            return;
        }
        NPNotificationData nPNotificationData = GNPersistent.get(context, "" + extras.getInt("id"), NOTIFICATION_DATA_PREF_NAME);
        if (nPNotificationData != null) {
            ToyLog.d("NotificationData found " + nPNotificationData.message);
            nPNotificationData.confirmedNotification = true;
            GNPersistent.safe(context, nPNotificationData, NOTIFICATION_DATA_PREF_NAME);
            sendReceiveClass(context, nPNotificationData);
        }
    }
}
