package com.nhn.android.naverlogin.util;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class OAuthLoginUiUtil {
    public static boolean isFixActivityPortrait(String str) {
        if (str != null) {
            try {
                if (str.length() > 4) {
                    String[] strArrSplit = str.split("\\.");
                    if (2 < strArrSplit.length) {
                        int iIntValue = Integer.valueOf(strArrSplit[0]).intValue();
                        int iIntValue2 = Integer.valueOf(strArrSplit[1]).intValue();
                        int iIntValue3 = Integer.valueOf(strArrSplit[2]).intValue();
                        if (4 < iIntValue) {
                            return false;
                        }
                        if (4 == iIntValue) {
                            if (1 < iIntValue2) {
                                return false;
                            }
                            if (1 == iIntValue2 && 2 < iIntValue3) {
                                return false;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
