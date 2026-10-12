package com.nexon.wander.ccclipboard;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import com.unity3d.player.UnityPlayer;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CCClipBoard {
    public static String getSampleText() {
        return "testest hello";
    }

    public static String getText() {
        ClipboardManager clipboardManager = (ClipboardManager) UnityPlayer.currentActivity.getSystemService("clipboard");
        return (clipboardManager != null && clipboardManager.hasPrimaryClip() && clipboardManager.getPrimaryClipDescription().hasMimeType("text/plain")) ? clipboardManager.getPrimaryClip().getItemAt(0).getText().toString() : "null";
    }

    public static void setText(String str) {
        Activity activity = UnityPlayer.currentActivity;
        ((ClipboardManager) activity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("data", str));
    }
}
