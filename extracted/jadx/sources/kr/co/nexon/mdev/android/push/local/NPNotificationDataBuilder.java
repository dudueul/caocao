package kr.co.nexon.mdev.android.push.local;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNotificationDataBuilder {
    NPNotificationData instance = new NPNotificationData();

    public NPNotificationDataBuilder(int i) {
        this.instance.notificationID = i;
    }

    public NPNotificationData build() {
        return this.instance;
    }

    public NPNotificationDataBuilder setMessage(String str) {
        this.instance.message = str;
        return this;
    }

    public NPNotificationDataBuilder setMeta(String str) {
        this.instance.meta = str;
        return this;
    }

    public NPNotificationDataBuilder setPushType(int i) {
        this.instance.pushType = i;
        return this;
    }

    public NPNotificationDataBuilder setTime(int i, int i2, int i3, int i4, int i5, int i6) {
        this.instance.time = new NPNotificationTime();
        this.instance.time.year = i;
        this.instance.time.month = i2;
        this.instance.time.day = i3;
        this.instance.time.hour = i4;
        this.instance.time.minute = i5;
        this.instance.time.sec = i6;
        return this;
    }
}
