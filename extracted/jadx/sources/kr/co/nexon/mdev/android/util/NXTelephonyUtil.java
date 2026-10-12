package kr.co.nexon.mdev.android.util;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.util.NXStringUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXTelephonyUtil {
    public static String getDeviceId(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
    }

    public static String getMcc(Context context) {
        TelephonyManager telephonyManager;
        if (context != null && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null) {
            String simOperator = telephonyManager.getSimOperator();
            if (simOperator == null || simOperator.length() < 3) {
                return null;
            }
            return simOperator.length() != 3 ? simOperator.substring(0, 3) : simOperator;
        }
        return null;
    }

    public static String getMnc(Context context) {
        TelephonyManager telephonyManager;
        String simOperator;
        if (context == null || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null || (simOperator = telephonyManager.getSimOperator()) == null || simOperator.length() < 5) {
            return null;
        }
        return simOperator.substring(3);
    }

    public static String getNetworkOperatorName(Context context) {
        String networkOperatorName = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        if (NXStringUtil.isNull(networkOperatorName)) {
            networkOperatorName = "";
        }
        ToyLog.d("getNetworkOperatorName : " + networkOperatorName);
        return networkOperatorName;
    }

    public static String getPhoneNumber(Context context) {
        String line1Number = ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
        if (NXStringUtil.isNull(line1Number)) {
            line1Number = "";
        }
        ToyLog.d("getLineNumber", "phoneNumber", line1Number);
        return line1Number;
    }

    public static boolean isExistUsim(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimState() != 1;
    }

    public static boolean isPossibleUsim(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimState() == 5;
    }
}
