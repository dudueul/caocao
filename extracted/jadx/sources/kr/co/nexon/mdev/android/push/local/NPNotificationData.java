package kr.co.nexon.mdev.android.push.local;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNotificationData {
    public static final int LOCAL_PUSH_TYPE_AFTER = 1;
    public static final int LOCAL_PUSH_TYPE_NOW = 2;
    public static final int LOCAL_PUSH_TYPE_ON = 0;
    public int notificationID;
    public String message = "";
    public String meta = "";
    public NPNotificationTime time = null;
    public boolean confirmedNotification = false;
    public int pushType = 2;
}
