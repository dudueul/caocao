package com.tapjoy.internal;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class n {
    public static void a(SharedPreferences sharedPreferences, String str, int i) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(str, i);
        editorEdit.apply();
    }

    public static void a(SharedPreferences sharedPreferences, String str, String str2) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    public static void a(SharedPreferences sharedPreferences, String str, boolean z) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putBoolean(str, z);
        editorEdit.apply();
    }
}
