package com.naver.glink.android.sdk.alarm;

import android.support.annotation.Keep;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Keep
public class LiveAlarmMessage {
    public boolean isFirst;
    public String nickName;
    public String timestamp;
    public String title;

    public LiveAlarmMessage(String str, String str2, boolean z, String str3) {
        this.nickName = str;
        this.title = str2;
        this.isFirst = z;
        this.timestamp = str3;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public LiveAlarmMessage m9clone() {
        LiveAlarmMessage liveAlarmMessage = new LiveAlarmMessage(this.nickName, this.title, this.isFirst, String.valueOf(System.currentTimeMillis()));
        this.isFirst = false;
        return liveAlarmMessage;
    }
}
