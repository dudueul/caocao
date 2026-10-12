package com.naver.glink.android.sdk.a;

import android.view.KeyEvent;

/* JADX INFO: compiled from: KeyEventHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class j {
    public static boolean a(KeyEvent keyEvent) {
        return keyEvent.getAction() == 1 && keyEvent.getKeyCode() == 4;
    }
}
