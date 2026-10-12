package com.google.android.gms.common.util;

import android.os.Binder;
import android.os.Process;
import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ProcessUtils {
    private static String zzaai = null;
    private static int zzaaj = 0;

    public static class SystemGroupsNotAvailableException extends Exception {
        SystemGroupsNotAvailableException(String str) {
            super(str);
        }

        SystemGroupsNotAvailableException(String str, Throwable th) {
            super(str, th);
        }
    }

    private ProcessUtils() {
    }

    @Nullable
    public static String getCallingProcessName() {
        int callingPid = Binder.getCallingPid();
        return callingPid == zzde() ? getMyProcessName() : zzl(callingPid);
    }

    @Nullable
    public static String getMyProcessName() {
        if (zzaai == null) {
            zzaai = zzl(zzde());
        }
        return zzaai;
    }

    public static boolean hasSystemGroups() throws SystemGroupsNotAvailableException {
        BufferedReader bufferedReaderZzm = null;
        try {
            try {
                bufferedReaderZzm = zzm(new StringBuilder(24).append("/proc/").append(zzde()).append("/status").toString());
                boolean zZzk = zzk(bufferedReaderZzm);
                IOUtils.closeQuietly(bufferedReaderZzm);
                return zZzk;
            } catch (IOException e) {
                throw new SystemGroupsNotAvailableException("Unable to access /proc/pid/status.", e);
            }
        } catch (Throwable th) {
            IOUtils.closeQuietly(bufferedReaderZzm);
            throw th;
        }
    }

    private static int zzde() {
        if (zzaaj == 0) {
            zzaaj = Process.myPid();
        }
        return zzaaj;
    }

    private static boolean zzk(BufferedReader bufferedReader) throws IOException, SystemGroupsNotAvailableException {
        String line = bufferedReader.readLine();
        while (line != null) {
            String strTrim = line.trim();
            if (strTrim.startsWith("Groups:")) {
                String[] strArrSplit = strTrim.substring(7).trim().split("\\s", -1);
                for (String str : strArrSplit) {
                    try {
                        long j = Long.parseLong(str);
                        if (j >= 1000 && j < 2000) {
                            return true;
                        }
                    } catch (NumberFormatException e) {
                    }
                }
                return false;
            }
            line = bufferedReader.readLine();
        }
        throw new SystemGroupsNotAvailableException("Missing Groups entry from proc/pid/status.");
    }

    @Nullable
    private static String zzl(int i) throws Throwable {
        Throwable th;
        BufferedReader bufferedReader;
        BufferedReader bufferedReaderZzm;
        String strTrim = null;
        if (i > 0) {
            try {
                bufferedReaderZzm = zzm(new StringBuilder(25).append("/proc/").append(i).append("/cmdline").toString());
                try {
                    strTrim = bufferedReaderZzm.readLine().trim();
                    IOUtils.closeQuietly(bufferedReaderZzm);
                } catch (IOException e) {
                    IOUtils.closeQuietly(bufferedReaderZzm);
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReaderZzm;
                    IOUtils.closeQuietly(bufferedReader);
                    throw th;
                }
            } catch (IOException e2) {
                bufferedReaderZzm = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
        }
        return strTrim;
    }

    private static BufferedReader zzm(String str) throws IOException {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return new BufferedReader(new FileReader(str));
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }
}
