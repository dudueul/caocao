package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Debug;
import android.os.DropBoxManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class CrashUtils {
    private static boolean zzzf;
    private static boolean zzzg;
    private static final String[] zzzc = {"android.", "com.android.", "dalvik.", "java.", "javax."};
    private static DropBoxManager zzzd = null;
    private static boolean zzze = false;
    private static int zzzh = -1;

    @GuardedBy("CrashUtils.class")
    private static int zzzi = 0;

    @GuardedBy("CrashUtils.class")
    private static int zzzj = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorDialogData {
        public static final int AVG_CRASH_FREQ = 2;
        public static final int BINDER_CRASH = 268435456;
        public static final int DYNAMITE_CRASH = 536870912;
        public static final int FORCED_SHUSHED_BY_WRAPPER = 4;
        public static final int NONE = 0;
        public static final int POPUP_FREQ = 1;
        public static final int SUPPRESSED = 1073741824;
    }

    public static boolean addDynamiteErrorToDropBox(Context context, Throwable th) {
        return addErrorToDropBoxInternal(context, th, 536870912);
    }

    @Deprecated
    public static boolean addErrorToDropBox(Context context, Throwable th) {
        return addDynamiteErrorToDropBox(context, th);
    }

    public static boolean addErrorToDropBoxInternal(Context context, String str, String str2, int i) {
        return zza(context, str, str2, i, null);
    }

    public static boolean addErrorToDropBoxInternal(Context context, Throwable th, int i) throws Exception {
        boolean zZzdb;
        try {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(th);
            if (!isPackageSide()) {
                return false;
            }
            if (zzdb() || (th = zza(th)) != null) {
                return zza(context, Log.getStackTraceString(th), ProcessUtils.getMyProcessName(), i, th);
            }
            return false;
        } catch (Exception e) {
            try {
                zZzdb = zzdb();
            } catch (Exception e2) {
                Log.e("CrashUtils", "Error determining which process we're running in!", e2);
                zZzdb = false;
            }
            if (zZzdb) {
                throw e;
            }
            Log.e("CrashUtils", "Error adding exception to DropBox!", e);
            return false;
        }
    }

    private static boolean isPackageSide() {
        if (zzze) {
            return zzzf;
        }
        return false;
    }

    public static boolean isSystemClassPrefixInternal(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str2 : zzzc) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    public static synchronized void setTestVariables(DropBoxManager dropBoxManager, boolean z, boolean z2, int i) {
        zzze = true;
        zzzd = dropBoxManager;
        zzzg = z;
        zzzf = z2;
        zzzh = i;
        zzzi = 0;
        zzzj = 0;
    }

    /* JADX WARN: Code duplicated, block: B:95:0x0199 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    @VisibleForTesting
    private static synchronized String zza(Context context, String str, String str2, int i) {
        StringBuilder sb;
        int i2;
        InputStreamReader inputStreamReader;
        sb = new StringBuilder(1024);
        sb.append("Process: ").append(Strings.nullToEmpty(str2)).append("\n");
        sb.append("Package: com.google.android.gms");
        int i3 = 12451009;
        String strConcat = "12.4.51 (020308-{{cl}})";
        if (zzdb()) {
            try {
                PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(context.getPackageName(), 0);
                i3 = packageInfo.versionCode;
                if (packageInfo.versionName != null) {
                    strConcat = packageInfo.versionName;
                }
            } catch (Exception e) {
                Log.w("CrashUtils", "Error while trying to get the package information! Using static version.", e);
            }
        }
        sb.append(" v").append(i3);
        if (!TextUtils.isEmpty(strConcat)) {
            if (strConcat.contains("(") && !strConcat.contains(")")) {
                if (strConcat.endsWith("-")) {
                    strConcat = String.valueOf(strConcat).concat("111111111");
                }
                strConcat = String.valueOf(strConcat).concat(")");
            }
            sb.append(" (").append(strConcat).append(")");
        }
        sb.append("\n");
        sb.append("Build: ").append(Build.FINGERPRINT).append("\n");
        if (Debug.isDebuggerConnected()) {
            sb.append("Debugger: Connected\n");
        }
        if (i != 0) {
            sb.append("DD-EDD: ").append(i).append("\n");
        }
        sb.append("\n");
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        if (zzdb()) {
            i2 = zzzh >= 0 ? zzzh : Settings.Secure.getInt(context.getContentResolver(), "logcat_for_system_app_crash", 0);
        } else {
            i2 = 0;
        }
        if (i2 > 0) {
            sb.append("\n");
            FileReader fileReader = 0;
            try {
                try {
                    try {
                        Process processStart = new ProcessBuilder("/system/bin/logcat", "-v", "time", "-b", "events", "-b", "system", "-b", "main", "-b", "crash", "-t", String.valueOf(i2)).redirectErrorStream(true).start();
                        try {
                            processStart.getOutputStream().close();
                        } catch (IOException e2) {
                        }
                        try {
                            processStart.getErrorStream().close();
                        } catch (IOException e3) {
                        }
                        inputStreamReader = new InputStreamReader(processStart.getInputStream());
                        try {
                            char[] cArr = new char[8192];
                            while (true) {
                                int i4 = inputStreamReader.read(cArr);
                                if (i4 > 0) {
                                    sb.append(cArr, 0, i4);
                                } else {
                                    try {
                                        break;
                                    } catch (IOException e4) {
                                    }
                                }
                            }
                            inputStreamReader.close();
                        } catch (IOException e5) {
                            e = e5;
                            Log.e("CrashUtils", "Error running logcat", e);
                            if (inputStreamReader != null) {
                                try {
                                    inputStreamReader.close();
                                } catch (IOException e6) {
                                }
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileReader = "\n";
                        if (fileReader != 0) {
                            try {
                                fileReader.close();
                            } catch (IOException e7) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileReader != 0) {
                        fileReader.close();
                    }
                    throw th;
                }
            } catch (IOException e8) {
                e = e8;
                inputStreamReader = null;
            }
        }
        return sb.toString();
    }

    @VisibleForTesting
    private static synchronized Throwable zza(Throwable th) {
        Throwable th2;
        boolean z;
        LinkedList linkedList = new LinkedList();
        while (th != null) {
            linkedList.push(th);
            th = th.getCause();
        }
        th2 = null;
        z = false;
        while (!linkedList.isEmpty()) {
            Throwable th3 = (Throwable) linkedList.pop();
            StackTraceElement[] stackTrace = th3.getStackTrace();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new StackTraceElement(th3.getClass().getName(), "<filtered>", "<filtered>", 1));
            boolean z2 = z;
            for (StackTraceElement stackTraceElement : stackTrace) {
                String className = stackTraceElement.getClassName();
                String fileName = stackTraceElement.getFileName();
                boolean z3 = !TextUtils.isEmpty(fileName) && fileName.startsWith(":com.google.android.gms");
                z2 |= z3;
                if (!z3 && !isSystemClassPrefixInternal(className)) {
                    stackTraceElement = new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1);
                }
                arrayList.add(stackTraceElement);
            }
            th2 = th2 == null ? new Throwable("<filtered>") : new Throwable("<filtered>", th2);
            th2.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
            z = z2;
        }
        return !z ? null : th2;
    }

    private static synchronized boolean zza(Context context, String str, String str2, int i, Throwable th) {
        boolean z;
        Preconditions.checkNotNull(context);
        if (!isPackageSide() || Strings.isEmptyOrWhitespace(str)) {
            z = false;
        } else {
            int iHashCode = str.hashCode();
            int iHashCode2 = th == null ? zzzj : th.hashCode();
            if (zzzi == iHashCode && zzzj == iHashCode2) {
                z = false;
            } else {
                zzzi = iHashCode;
                zzzj = iHashCode2;
                DropBoxManager dropBoxManager = zzzd != null ? zzzd : (DropBoxManager) context.getSystemService("dropbox");
                if (dropBoxManager == null || !dropBoxManager.isTagEnabled("system_app_crash")) {
                    z = false;
                } else {
                    dropBoxManager.addText("system_app_crash", zza(context, str, str2, i));
                    z = true;
                }
            }
        }
        return z;
    }

    private static boolean zzdb() {
        if (zzze) {
            return zzzg;
        }
        return false;
    }
}
