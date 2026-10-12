package kr.co.nexon.mdev.android.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXAccountUtil {
    public static String TYPE_GOOGLE = "com.google";

    public static Account[] getAccounts(Context context) {
        return AccountManager.get(context).getAccounts();
    }

    public static Account[] getAccounts(Context context, String str) {
        return AccountManager.get(context).getAccountsByType(str);
    }
}
