package com.nhn.android.naverlogin.util;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DeviceDisplayInfo {
    public static boolean isHdpi(int i) {
        return !isMdpi(i) && i <= 240;
    }

    public static boolean isMdpi(int i) {
        return i <= 160;
    }

    public static boolean isXhdpi(int i) {
        return (isMdpi(i) || isHdpi(i)) ? false : true;
    }
}
