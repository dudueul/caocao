package com.nexon.nexonanalyticssdk;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxSharedPreference {
    public static final String IS_NOT_EXIST = "isNotExist";
    public static final String NX_PREFERENCE_NAME = "NxAnalyticsPreference";

    NxSharedPreference() {
    }

    public String getDataInNxPreference(Context context, String str, String str2) {
        return context.getSharedPreferences(NX_PREFERENCE_NAME, 0).getString(str, str2);
    }

    public String getUuidInNxPreference(Context context) {
        return getDataInNxPreference(context, NxLogInfo.KEY_MID, IS_NOT_EXIST);
    }

    public void setDataInNxPreference(Context context, String str, String str2) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(NX_PREFERENCE_NAME, 0).edit();
        editorEdit.putString(str, str2);
        editorEdit.commit();
    }
}
