package io.fabric.sdk.android.services.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.tapjoy.TapjoyConstants;
import io.fabric.sdk.android.Fabric;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CommonUtils {
    static final int BYTES_IN_A_GIGABYTE = 1073741824;
    static final int BYTES_IN_A_KILOBYTE = 1024;
    static final int BYTES_IN_A_MEGABYTE = 1048576;
    private static final String CLS_SHARED_PREFERENCES_NAME = "com.crashlytics.prefs";
    static final boolean CLS_TRACE_DEFAULT = false;
    static final String CLS_TRACE_PREFERENCE_NAME = "com.crashlytics.Trace";
    static final String CRASHLYTICS_BUILD_ID = "com.crashlytics.android.build_id";
    public static final int DEVICE_STATE_BETAOS = 8;
    public static final int DEVICE_STATE_COMPROMISEDLIBRARIES = 32;
    public static final int DEVICE_STATE_DEBUGGERATTACHED = 4;
    public static final int DEVICE_STATE_ISSIMULATOR = 1;
    public static final int DEVICE_STATE_JAILBROKEN = 2;
    public static final int DEVICE_STATE_VENDORINTERNAL = 16;
    static final String FABRIC_BUILD_ID = "io.fabric.android.build_id";
    public static final String GOOGLE_SDK = "google_sdk";
    private static final String LOG_PRIORITY_NAME_ASSERT = "A";
    private static final String LOG_PRIORITY_NAME_DEBUG = "D";
    private static final String LOG_PRIORITY_NAME_ERROR = "E";
    private static final String LOG_PRIORITY_NAME_INFO = "I";
    private static final String LOG_PRIORITY_NAME_UNKNOWN = "?";
    private static final String LOG_PRIORITY_NAME_VERBOSE = "V";
    private static final String LOG_PRIORITY_NAME_WARN = "W";
    public static final String MD5_INSTANCE = "MD5";
    public static final String SDK = "sdk";
    public static final String SHA1_INSTANCE = "SHA-1";
    private static final long UNCALCULATED_TOTAL_RAM = -1;
    private static Boolean clsTrace = null;
    private static final char[] HEX_VALUES = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static long totalRamInBytes = -1;
    public static final Comparator<File> FILE_MODIFIED_COMPARATOR = new Comparator<File>() { // from class: io.fabric.sdk.android.services.common.CommonUtils.1
        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            return (int) (file.lastModified() - file2.lastModified());
        }
    };

    enum Architecture {
        X86_32,
        X86_64,
        ARM_UNKNOWN,
        PPC,
        PPC64,
        ARMV6,
        ARMV7,
        UNKNOWN,
        ARMV7S,
        ARM64;

        private static final Map<String, Architecture> matcher = new HashMap(4);

        static {
            matcher.put("armeabi-v7a", ARMV7);
            matcher.put("armeabi", ARMV6);
            matcher.put("arm64-v8a", ARM64);
            matcher.put("x86", X86_32);
        }

        static Architecture getValue() {
            String str = Build.CPU_ABI;
            if (TextUtils.isEmpty(str)) {
                Fabric.getLogger().d(Fabric.TAG, "Architecture#getValue()::Build.CPU_ABI returned null or empty");
                return UNKNOWN;
            }
            Architecture architecture = matcher.get(str.toLowerCase(Locale.US));
            return architecture == null ? UNKNOWN : architecture;
        }
    }

    public static long calculateFreeRamInBytes(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static long calculateUsedDiskSpaceInBytes(String str) {
        StatFs statFs = new StatFs(str);
        long blockSize = statFs.getBlockSize();
        return (blockSize * ((long) statFs.getBlockCount())) - (blockSize * ((long) statFs.getAvailableBlocks()));
    }

    public static boolean canTryConnection(Context context) {
        if (!checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static boolean checkPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static void closeOrLog(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Fabric.getLogger().e(Fabric.TAG, str, e);
            }
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    static long convertMemInfoToBytes(String str, String str2, int i) {
        return Long.parseLong(str.split(str2)[0].trim()) * ((long) i);
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    @Deprecated
    public static Cipher createCipher(int i, String str) throws InvalidKeyException {
        throw new InvalidKeyException("This method is deprecated");
    }

    public static String createInstanceIdFrom(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (str != null) {
                arrayList.add(str.replace("-", "").toLowerCase(Locale.US));
            }
        }
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
        }
        String string = sb.toString();
        if (string.length() > 0) {
            return sha1(string);
        }
        return null;
    }

    public static byte[] dehexify(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static String extractFieldFromSystemFile(File file, String str) throws Throwable {
        String str2 = null;
        if (file.exists()) {
            BufferedReader bufferedReader = null;
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file), 1024);
                    while (true) {
                        try {
                            String line = bufferedReader2.readLine();
                            if (line == null) {
                                break;
                            }
                            String[] strArrSplit = Pattern.compile("\\s*:\\s*").split(line, 2);
                            if (strArrSplit.length > 1 && strArrSplit[0].equals(str)) {
                                str2 = strArrSplit[1];
                                break;
                            }
                        } catch (Exception e) {
                            e = e;
                            bufferedReader = bufferedReader2;
                            Fabric.getLogger().e(Fabric.TAG, "Error parsing " + file, e);
                            closeOrLog(bufferedReader, "Failed to close system file reader.");
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            closeOrLog(bufferedReader, "Failed to close system file reader.");
                            throw th;
                        }
                    }
                    closeOrLog(bufferedReader2, "Failed to close system file reader.");
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return str2;
    }

    @TargetApi(16)
    public static void finishAffinity(Activity activity, int i) {
        if (activity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            activity.finishAffinity();
        } else {
            activity.setResult(i);
            activity.finish();
        }
    }

    @TargetApi(16)
    public static void finishAffinity(Context context, int i) {
        if (context instanceof Activity) {
            finishAffinity((Activity) context, i);
        }
    }

    public static void flushOrLog(Flushable flushable, String str) {
        if (flushable != null) {
            try {
                flushable.flush();
            } catch (IOException e) {
                Fabric.getLogger().e(Fabric.TAG, str, e);
            }
        }
    }

    public static String getAppIconHashOrNull(Context context) {
        InputStream inputStreamOpenRawResource = null;
        try {
            inputStreamOpenRawResource = context.getResources().openRawResource(getAppIconResourceId(context));
            String strSha1 = sha1(inputStreamOpenRawResource);
            if (isNullOrEmpty(strSha1)) {
                strSha1 = null;
            }
            return strSha1;
        } catch (Exception e) {
            Fabric.getLogger().e(Fabric.TAG, "Could not calculate hash for app icon.", e);
            return null;
        } finally {
            closeOrLog(inputStreamOpenRawResource, "Failed to close icon input stream.");
        }
    }

    public static int getAppIconResourceId(Context context) {
        return context.getApplicationContext().getApplicationInfo().icon;
    }

    public static ActivityManager.RunningAppProcessInfo getAppProcessInfo(String str, Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(str)) {
                return runningAppProcessInfo;
            }
        }
        return null;
    }

    public static Float getBatteryLevel(Context context) {
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver == null) {
            return null;
        }
        return Float.valueOf(intentRegisterReceiver.getIntExtra("level", -1) / intentRegisterReceiver.getIntExtra("scale", -1));
    }

    public static int getBatteryVelocity(Context context, boolean z) {
        Float batteryLevel = getBatteryLevel(context);
        if (!z || batteryLevel == null) {
            return 1;
        }
        if (batteryLevel.floatValue() >= 99.0d) {
            return 3;
        }
        return ((double) batteryLevel.floatValue()) < 99.0d ? 2 : 0;
    }

    public static boolean getBooleanResourceValue(Context context, String str, boolean z) {
        Resources resources;
        if (context == null || (resources = context.getResources()) == null) {
            return z;
        }
        int resourcesIdentifier = getResourcesIdentifier(context, str, "bool");
        if (resourcesIdentifier > 0) {
            return resources.getBoolean(resourcesIdentifier);
        }
        int resourcesIdentifier2 = getResourcesIdentifier(context, str, "string");
        return resourcesIdentifier2 > 0 ? Boolean.parseBoolean(context.getString(resourcesIdentifier2)) : z;
    }

    public static int getCpuArchitectureInt() {
        return Architecture.getValue().ordinal();
    }

    public static int getDeviceState(Context context) {
        int i = isEmulator(context) ? 0 | 1 : 0;
        if (isRooted(context)) {
            i |= 2;
        }
        return isDebuggerAttached() ? i | 4 : i;
    }

    public static boolean getProximitySensorEnabled(Context context) {
        return (isEmulator(context) || ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(8) == null) ? false : true;
    }

    public static String getResourcePackageName(Context context) {
        int i = context.getApplicationContext().getApplicationInfo().icon;
        return i > 0 ? context.getResources().getResourcePackageName(i) : context.getPackageName();
    }

    public static int getResourcesIdentifier(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, getResourcePackageName(context));
    }

    public static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences(CLS_SHARED_PREFERENCES_NAME, 0);
    }

    public static String getStringsFileValue(Context context, String str) {
        int resourcesIdentifier = getResourcesIdentifier(context, str, "string");
        return resourcesIdentifier > 0 ? context.getString(resourcesIdentifier) : "";
    }

    public static synchronized long getTotalRamInBytes() {
        if (totalRamInBytes == -1) {
            long jConvertMemInfoToBytes = 0;
            String strExtractFieldFromSystemFile = extractFieldFromSystemFile(new File("/proc/meminfo"), "MemTotal");
            if (!TextUtils.isEmpty(strExtractFieldFromSystemFile)) {
                String upperCase = strExtractFieldFromSystemFile.toUpperCase(Locale.US);
                try {
                    if (upperCase.endsWith("KB")) {
                        jConvertMemInfoToBytes = convertMemInfoToBytes(upperCase, "KB", 1024);
                    } else if (upperCase.endsWith("MB")) {
                        jConvertMemInfoToBytes = convertMemInfoToBytes(upperCase, "MB", 1048576);
                    } else if (upperCase.endsWith("GB")) {
                        jConvertMemInfoToBytes = convertMemInfoToBytes(upperCase, "GB", 1073741824);
                    } else {
                        Fabric.getLogger().d(Fabric.TAG, "Unexpected meminfo format while computing RAM: " + upperCase);
                    }
                } catch (NumberFormatException e) {
                    Fabric.getLogger().e(Fabric.TAG, "Unexpected meminfo format while computing RAM: " + upperCase, e);
                }
            }
            totalRamInBytes = jConvertMemInfoToBytes;
        }
        return totalRamInBytes;
    }

    private static String hash(InputStream inputStream, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    return hexify(messageDigest.digest());
                }
                messageDigest.update(bArr, 0, i);
            }
        } catch (Exception e) {
            Fabric.getLogger().e(Fabric.TAG, "Could not calculate hash for app icon.", e);
            return "";
        }
    }

    private static String hash(String str, String str2) {
        return hash(str.getBytes(), str2);
    }

    private static String hash(byte[] bArr, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            return hexify(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            Fabric.getLogger().e(Fabric.TAG, "Could not create hashing algorithm: " + str + ", returning empty string.", e);
            return "";
        }
    }

    public static String hexify(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            cArr[i * 2] = HEX_VALUES[i2 >>> 4];
            cArr[(i * 2) + 1] = HEX_VALUES[i2 & 15];
        }
        return new String(cArr);
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static boolean isAppDebuggable(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static boolean isClsTrace(Context context) {
        if (clsTrace == null) {
            clsTrace = Boolean.valueOf(getBooleanResourceValue(context, CLS_TRACE_PREFERENCE_NAME, false));
        }
        return clsTrace.booleanValue();
    }

    public static boolean isDebuggerAttached() {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger();
    }

    public static boolean isEmulator(Context context) {
        return "sdk".equals(Build.PRODUCT) || GOOGLE_SDK.equals(Build.PRODUCT) || Settings.Secure.getString(context.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID) == null;
    }

    @Deprecated
    public static boolean isLoggingEnabled(Context context) {
        return false;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isRooted(Context context) {
        boolean zIsEmulator = isEmulator(context);
        String str = Build.TAGS;
        if ((zIsEmulator || str == null || !str.contains("test-keys")) && !new File("/system/app/Superuser.apk").exists()) {
            return !zIsEmulator && new File("/system/xbin/su").exists();
        }
        return true;
    }

    public static void logControlled(Context context, int i, String str, String str2) {
        if (isClsTrace(context)) {
            Fabric.getLogger().log(i, Fabric.TAG, str2);
        }
    }

    public static void logControlled(Context context, String str) {
        if (isClsTrace(context)) {
            Fabric.getLogger().d(Fabric.TAG, str);
        }
    }

    public static void logControlledError(Context context, String str, Throwable th) {
        if (isClsTrace(context)) {
            Fabric.getLogger().e(Fabric.TAG, str);
        }
    }

    public static void logOrThrowIllegalArgumentException(String str, String str2) {
        if (Fabric.isDebuggable()) {
            throw new IllegalArgumentException(str2);
        }
        Fabric.getLogger().w(str, str2);
    }

    public static void logOrThrowIllegalStateException(String str, String str2) {
        if (Fabric.isDebuggable()) {
            throw new IllegalStateException(str2);
        }
        Fabric.getLogger().w(str, str2);
    }

    public static String logPriorityToString(int i) {
        switch (i) {
            case 2:
                return LOG_PRIORITY_NAME_VERBOSE;
            case 3:
                return LOG_PRIORITY_NAME_DEBUG;
            case 4:
                return LOG_PRIORITY_NAME_INFO;
            case 5:
                return LOG_PRIORITY_NAME_WARN;
            case 6:
                return LOG_PRIORITY_NAME_ERROR;
            case 7:
                return LOG_PRIORITY_NAME_ASSERT;
            default:
                return LOG_PRIORITY_NAME_UNKNOWN;
        }
    }

    public static String md5(String str) {
        return hash(str, "MD5");
    }

    public static String md5(byte[] bArr) {
        return hash(bArr, "MD5");
    }

    public static void openKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInputFromInputMethod(view.getWindowToken(), 0);
        }
    }

    public static String padWithZerosToMaxIntWidth(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("value must be zero or greater");
        }
        return String.format(Locale.US, "%1$10s", Integer.valueOf(i)).replace(' ', '0');
    }

    public static String resolveBuildId(Context context) {
        int resourcesIdentifier = getResourcesIdentifier(context, FABRIC_BUILD_ID, "string");
        if (resourcesIdentifier == 0) {
            resourcesIdentifier = getResourcesIdentifier(context, CRASHLYTICS_BUILD_ID, "string");
        }
        if (resourcesIdentifier == 0) {
            return null;
        }
        String string = context.getResources().getString(resourcesIdentifier);
        Fabric.getLogger().d(Fabric.TAG, "Build ID is: " + string);
        return string;
    }

    public static String sha1(InputStream inputStream) {
        return hash(inputStream, "SHA-1");
    }

    public static String sha1(String str) {
        return hash(str, "SHA-1");
    }

    public static String sha1(byte[] bArr) {
        return hash(bArr, "SHA-1");
    }

    public static String streamToString(InputStream inputStream) throws IOException {
        Scanner scannerUseDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return scannerUseDelimiter.hasNext() ? scannerUseDelimiter.next() : "";
    }

    public static boolean stringsEqualIncludingNull(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str != null) {
            return str.equals(str2);
        }
        return false;
    }
}
