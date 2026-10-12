package com.adjust.sdk;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tapjoy.TapjoyConstants;
import io.fabric.sdk.android.services.common.IdManager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Util {
    private static final String fieldReadErrorMessage = "Unable to read '%s' field in migration device with message (%s)";
    public static final DecimalFormat SecondsDisplayFormat = newLocalDecimalFormat();
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'Z";
    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.US);

    public static boolean checkPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static String convertToHex(byte[] bArr) {
        return formatString("%0" + (bArr.length << 1) + "x", new BigInteger(1, bArr));
    }

    protected static String createUuid() {
        return UUID.randomUUID().toString();
    }

    public static boolean equalBoolean(Boolean bool, Boolean bool2) {
        return equalObject(bool, bool2);
    }

    public static boolean equalEnum(Enum r1, Enum r2) {
        return equalObject(r1, r2);
    }

    public static boolean equalInt(Integer num, Integer num2) {
        return equalObject(num, num2);
    }

    public static boolean equalLong(Long l, Long l2) {
        return equalObject(l, l2);
    }

    public static boolean equalObject(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return obj == null && obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static boolean equalString(String str, String str2) {
        return equalObject(str, str2);
    }

    public static boolean equalsDouble(Double d, Double d2) {
        if (d == null || d2 == null) {
            return d == null && d2 == null;
        }
        return Double.doubleToLongBits(d.doubleValue()) == Double.doubleToLongBits(d2.doubleValue());
    }

    public static String formatString(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static String getAndroidId(Context context) {
        return Reflection.getAndroidId(context);
    }

    public static int getConnectivityType(Context context) {
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo().getType();
        } catch (Exception e) {
            getLogger().warn("Couldn't read connectivity type (%s)", e.getMessage());
            return -1;
        }
    }

    public static String getCpuAbi() {
        return Reflection.getCpuAbi();
    }

    public static String getFireAdvertisingId(ContentResolver contentResolver) {
        if (contentResolver == null) {
            return null;
        }
        try {
            return Settings.Secure.getString(contentResolver, TapjoyConstants.TJC_ADVERTISING_ID);
        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean getFireTrackingEnabled(ContentResolver contentResolver) {
        try {
            return Boolean.valueOf(Settings.Secure.getInt(contentResolver, "limit_ad_tracking") == 0);
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.adjust.sdk.Util$2] */
    public static void getGoogleAdId(Context context, final OnDeviceIdsRead onDeviceIdsRead) {
        ILogger logger = AdjustFactory.getLogger();
        if (Looper.myLooper() == Looper.getMainLooper()) {
            logger.debug("GoogleAdId being read in the foreground", new Object[0]);
            new AsyncTask<Context, Void, String>() { // from class: com.adjust.sdk.Util.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public String doInBackground(Context... contextArr) {
                    ILogger logger2 = AdjustFactory.getLogger();
                    String playAdId = Util.getPlayAdId(contextArr[0]);
                    logger2.debug("GoogleAdId read " + playAdId, new Object[0]);
                    return playAdId;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(String str) {
                    AdjustFactory.getLogger();
                    onDeviceIdsRead.onGoogleAdIdRead(str);
                }
            }.execute(context);
        } else {
            logger.debug("GoogleAdId being read in the background", new Object[0]);
            String playAdId = getPlayAdId(context);
            logger.debug("GoogleAdId read " + playAdId, new Object[0]);
            onDeviceIdsRead.onGoogleAdIdRead(playAdId);
        }
    }

    public static String getIMEI(TelephonyManager telephonyManager) {
        return Reflection.getImei(telephonyManager);
    }

    public static String getIMEI(TelephonyManager telephonyManager, int i) {
        return Reflection.getImei(telephonyManager, i);
    }

    public static String getIMEIs(TelephonyManager telephonyManager) {
        ArrayList arrayList = new ArrayList();
        tryAddToStringList(arrayList, getIMEI(telephonyManager, 0));
        for (int i = 1; i < 10 && tryAddToStringList(arrayList, getIMEI(telephonyManager, i)); i++) {
        }
        tryAddToStringList(arrayList, getIMEI(telephonyManager, Integer.MAX_VALUE));
        return TextUtils.join(",", arrayList);
    }

    public static Locale getLocale(Configuration configuration) {
        Locale localeFromLocaleList = Reflection.getLocaleFromLocaleList(configuration);
        return localeFromLocaleList != null ? localeFromLocaleList : Reflection.getLocaleFromField(configuration);
    }

    private static ILogger getLogger() {
        return AdjustFactory.getLogger();
    }

    public static String getMEID(TelephonyManager telephonyManager) {
        return Reflection.getMeid(telephonyManager);
    }

    public static String getMEID(TelephonyManager telephonyManager, int i) {
        return Reflection.getMeid(telephonyManager, i);
    }

    public static String getMEIDs(TelephonyManager telephonyManager) {
        ArrayList arrayList = new ArrayList();
        tryAddToStringList(arrayList, getMEID(telephonyManager, 0));
        for (int i = 1; i < 10 && tryAddToStringList(arrayList, getMEID(telephonyManager, i)); i++) {
        }
        tryAddToStringList(arrayList, getMEID(telephonyManager, Integer.MAX_VALUE));
        return TextUtils.join(",", arrayList);
    }

    public static String getMacAddress(Context context) {
        return Reflection.getMacAddress(context);
    }

    public static String getMcc(Context context) {
        String strSubstring = null;
        try {
            String networkOperator = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
            if (TextUtils.isEmpty(networkOperator)) {
                AdjustFactory.getLogger().warn("Couldn't receive networkOperator string to read MCC", new Object[0]);
            } else {
                strSubstring = networkOperator.substring(0, 3);
            }
        } catch (Exception e) {
            AdjustFactory.getLogger().warn("Couldn't return mcc", new Object[0]);
        }
        return strSubstring;
    }

    public static String getMnc(Context context) {
        String strSubstring = null;
        try {
            String networkOperator = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
            if (TextUtils.isEmpty(networkOperator)) {
                AdjustFactory.getLogger().warn("Couldn't receive networkOperator string to read MNC", new Object[0]);
            } else {
                strSubstring = networkOperator.substring(3);
            }
        } catch (Exception e) {
            AdjustFactory.getLogger().warn("Couldn't return mnc", new Object[0]);
        }
        return strSubstring;
    }

    public static int getNetworkType(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
        } catch (Exception e) {
            getLogger().warn("Couldn't read network type (%s)", e.getMessage());
            return -1;
        }
    }

    public static String getPlayAdId(Context context) {
        return Reflection.getPlayAdId(context);
    }

    public static Map<String, String> getPluginKeys(Context context) {
        return Reflection.getPluginKeys(context);
    }

    public static String getReasonString(String str, Throwable th) {
        return th != null ? formatString("%s: %s", str, th) : formatString("%s", str);
    }

    public static String getRootCause(Exception exc) {
        if (!hasRootCause(exc)) {
            return null;
        }
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        String string = stringWriter.toString();
        int iIndexOf = string.indexOf("Caused by:");
        return string.substring(iIndexOf, string.indexOf("\n", iIndexOf));
    }

    public static String[] getSupportedAbis() {
        return Reflection.getSupportedAbis();
    }

    public static String getTelephonyId(TelephonyManager telephonyManager) {
        return Reflection.getTelephonyId(telephonyManager);
    }

    public static String getTelephonyId(TelephonyManager telephonyManager, int i) {
        return Reflection.getTelephonyId(telephonyManager, i);
    }

    public static String getTelephonyIds(TelephonyManager telephonyManager) {
        ArrayList arrayList = new ArrayList();
        tryAddToStringList(arrayList, getTelephonyId(telephonyManager, 0));
        for (int i = 1; i < 10 && tryAddToStringList(arrayList, getTelephonyId(telephonyManager, i)); i++) {
        }
        tryAddToStringList(arrayList, getTelephonyId(telephonyManager, Integer.MAX_VALUE));
        return TextUtils.join(",", arrayList);
    }

    public static String getVmInstructionSet() {
        return Reflection.getVmInstructionSet();
    }

    public static long getWaitingTime(int i, BackoffStrategy backoffStrategy) {
        if (i < backoffStrategy.minRetries) {
            return 0L;
        }
        return (long) (Math.min(((long) Math.pow(2.0d, i - backoffStrategy.minRetries)) * backoffStrategy.milliSecondMultiplier, backoffStrategy.maxWait) * randomInRange(backoffStrategy.minRange, backoffStrategy.maxRange));
    }

    public static boolean hasRootCause(Exception exc) {
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString().contains("Caused by:");
    }

    public static String hash(String str, String str2) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(bytes, 0, bytes.length);
            return convertToHex(messageDigest.digest());
        } catch (Exception e) {
            return null;
        }
    }

    public static int hashBoolean(Boolean bool) {
        if (bool == null) {
            return 0;
        }
        return bool.hashCode();
    }

    public static int hashEnum(Enum r1) {
        if (r1 == null) {
            return 0;
        }
        return r1.hashCode();
    }

    public static int hashLong(Long l) {
        if (l == null) {
            return 0;
        }
        return l.hashCode();
    }

    public static int hashObject(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public static int hashString(String str) {
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public static Boolean isPlayTrackingEnabled(Context context) {
        return Reflection.isPlayTrackingEnabled(context);
    }

    public static boolean isValidParameter(String str, String str2, String str3) {
        if (str == null) {
            getLogger().error("%s parameter %s is missing", str3, str2);
            return false;
        }
        if (!str.equals("")) {
            return true;
        }
        getLogger().error("%s parameter %s is empty", str3, str2);
        return false;
    }

    public static String md5(String str) {
        return hash(str, "MD5");
    }

    public static Map<String, String> mergeParameters(Map<String, String> map, Map<String, String> map2, String str) {
        if (map == null) {
            return map2;
        }
        if (map2 == null) {
            return map;
        }
        HashMap map3 = new HashMap(map);
        ILogger logger = getLogger();
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            String str2 = (String) map3.put(entry.getKey(), entry.getValue());
            if (str2 != null) {
                logger.warn("Key %s with value %s from %s parameter was replaced by value %s", entry.getKey(), str2, str, entry.getValue());
            }
        }
        return map3;
    }

    private static DecimalFormat newLocalDecimalFormat() {
        return new DecimalFormat(IdManager.DEFAULT_VERSION_NAME, new DecimalFormatSymbols(Locale.US));
    }

    public static String quote(String str) {
        if (str == null) {
            return null;
        }
        return Pattern.compile("\\s").matcher(str).find() ? formatString("'%s'", str) : str;
    }

    private static double randomInRange(double d, double d2) {
        return (new Random().nextDouble() * (d2 - d)) + d;
    }

    public static boolean readBooleanField(ObjectInputStream.GetField getField, String str, boolean z) {
        try {
            return getField.get(str, z);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, str, e.getMessage());
            return z;
        }
    }

    public static int readIntField(ObjectInputStream.GetField getField, String str, int i) {
        try {
            return getField.get(str, i);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, str, e.getMessage());
            return i;
        }
    }

    public static long readLongField(ObjectInputStream.GetField getField, String str, long j) {
        try {
            return getField.get(str, j);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, str, e.getMessage());
            return j;
        }
    }

    public static <T> T readObject(Context context, String str, String str2, Class<T> cls) {
        ObjectInputStream objectInputStream = null;
        T tCast = null;
        try {
            try {
                ObjectInputStream objectInputStream2 = new ObjectInputStream(new BufferedInputStream(context.openFileInput(str)));
                objectInputStream = objectInputStream2;
                try {
                    tCast = cls.cast(objectInputStream2.readObject());
                    getLogger().debug("Read %s: %s", str2, tCast);
                } catch (ClassCastException e) {
                    getLogger().error("Failed to cast %s object (%s)", str2, e.getMessage());
                } catch (ClassNotFoundException e2) {
                    getLogger().error("Failed to find %s class (%s)", str2, e2.getMessage());
                } catch (Exception e3) {
                    getLogger().error("Failed to read %s object (%s)", str2, e3.getMessage());
                }
            } catch (Exception e4) {
                getLogger().error("Failed to open %s file for reading (%s)", str2, e4);
            }
        } catch (FileNotFoundException e5) {
            getLogger().debug("%s file not found", str2);
        }
        if (objectInputStream != null) {
            try {
                objectInputStream.close();
            } catch (Exception e6) {
                getLogger().error("Failed to close %s file for reading (%s)", str2, e6);
            }
        }
        return tCast;
    }

    public static <T> T readObjectField(ObjectInputStream.GetField getField, String str, T t) {
        try {
            return (T) getField.get(str, t);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, str, e.getMessage());
            return t;
        }
    }

    public static String readStringField(ObjectInputStream.GetField getField, String str, String str2) {
        return (String) readObjectField(getField, str, str2);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.adjust.sdk.Util$1] */
    public static void runInBackground(Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            runnable.run();
        } else {
            new AsyncTask<Object, Void, Void>() { // from class: com.adjust.sdk.Util.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Void doInBackground(Object... objArr) {
                    ((Runnable) objArr[0]).run();
                    return null;
                }
            }.execute(runnable);
        }
    }

    public static String sha1(String str) {
        return hash(str, "SHA-1");
    }

    public static String sha256(String str) {
        return hash(str, Constants.SHA256);
    }

    public static boolean tryAddToStringList(List<String> list, String str) {
        if (str == null || list.contains(str)) {
            return false;
        }
        return list.add(str);
    }

    public static <T> void writeObject(T t, Context context, String str, String str2) {
        ObjectOutputStream objectOutputStream = null;
        try {
            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new BufferedOutputStream(context.openFileOutput(str, 0)));
            objectOutputStream = objectOutputStream2;
            try {
                objectOutputStream2.writeObject(t);
                getLogger().debug("Wrote %s: %s", str2, t);
            } catch (NotSerializableException e) {
                getLogger().error("Failed to serialize %s", str2);
            }
        } catch (Exception e2) {
            getLogger().error("Failed to open %s for writing (%s)", str2, e2);
        }
        if (objectOutputStream != null) {
            try {
                objectOutputStream.close();
            } catch (Exception e3) {
                getLogger().error("Failed to close %s file for writing (%s)", str2, e3);
            }
        }
    }
}
