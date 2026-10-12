package com.tapjoy.internal;

import android.util.Log;
import java.util.HashMap;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class x {
    public static void a(int i, String str, @Nullable String str2, @Nullable Throwable th) {
        if (str2 != null) {
            Log.println(i, str, str2);
        }
        if (th != null) {
            Log.println(i, str, Log.getStackTraceString(th));
        }
    }

    public static void a(int i, String str, String str2, Object... objArr) {
        Throwable th;
        jf jfVar;
        if (objArr == null || objArr.length == 0) {
            th = null;
        } else {
            Object obj = objArr[objArr.length - 1];
            th = obj instanceof Throwable ? (Throwable) obj : null;
        }
        if (str2 == null) {
            jfVar = new jf(null, objArr, th);
        } else if (objArr == null) {
            jfVar = new jf(str2);
        } else {
            StringBuffer stringBuffer = new StringBuffer(str2.length() + 50);
            int i2 = 0;
            int i3 = 0;
            while (i2 < objArr.length) {
                int iIndexOf = str2.indexOf("{}", i3);
                if (iIndexOf != -1) {
                    if (iIndexOf != 0 && str2.charAt(iIndexOf + (-1)) == '\\') {
                        if (iIndexOf >= 2 && str2.charAt(iIndexOf + (-2)) == '\\') {
                            stringBuffer.append(str2.substring(i3, iIndexOf - 1));
                            jg.a(stringBuffer, objArr[i2], new HashMap());
                            i3 = iIndexOf + 2;
                        } else {
                            i2--;
                            stringBuffer.append(str2.substring(i3, iIndexOf - 1));
                            stringBuffer.append('{');
                            i3 = iIndexOf + 1;
                        }
                    } else {
                        stringBuffer.append(str2.substring(i3, iIndexOf));
                        jg.a(stringBuffer, objArr[i2], new HashMap());
                        i3 = iIndexOf + 2;
                    }
                    i2++;
                } else if (i3 == 0) {
                    jfVar = new jf(str2, objArr, th);
                } else {
                    stringBuffer.append(str2.substring(i3, str2.length()));
                    jfVar = new jf(stringBuffer.toString(), objArr, th);
                }
            }
            stringBuffer.append(str2.substring(i3, str2.length()));
            jfVar = i2 < objArr.length + (-1) ? new jf(stringBuffer.toString(), objArr, th) : new jf(stringBuffer.toString(), objArr, null);
        }
        a(i, str, jfVar.b, jfVar.c);
    }

    public static void a(String str, String str2, Object... objArr) {
        a(6, str, str2, objArr);
    }
}
