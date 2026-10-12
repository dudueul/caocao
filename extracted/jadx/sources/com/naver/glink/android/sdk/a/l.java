package com.naver.glink.android.sdk.a;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: compiled from: Logger.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class l {
    private String a;

    private l(String str) {
        this.a = str;
    }

    public static l a(Class cls) {
        return a(cls.getSimpleName());
    }

    public static l a(String str) {
        return new l(str);
    }

    public Throwable a() {
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            return e;
        }
    }

    public void a(Intent intent) {
        Bundle extras;
        if (intent == null || (extras = intent.getExtras()) == null) {
            return;
        }
        for (String str : extras.keySet()) {
            if (str != null) {
                a("Dump: " + str + " = " + extras.get(str) + "\n", new Object[0]);
            }
        }
    }

    public void a(String str, Throwable th) {
        Log.e(this.a, str, th);
    }

    public void a(String str, Object... objArr) {
        if (com.naver.glink.android.sdk.d.m()) {
            String strSubstring = str == null ? "" : str;
            if (objArr != null && objArr.length > 0) {
                strSubstring = d(strSubstring, objArr);
            }
            if (strSubstring.length() <= 3000) {
                Log.d(this.a, strSubstring);
                return;
            }
            Log.d(this.a, "--------------------------------------------------------------");
            while (strSubstring.length() > 3000) {
                Log.d(this.a, strSubstring.substring(0, 3000));
                strSubstring = strSubstring.substring(3000);
            }
            Log.d(this.a, strSubstring);
            Log.d(this.a, "--------------------------------------------------------------");
        }
    }

    public void a(Throwable th) {
        a(th.getMessage(), th);
    }

    public void a(Throwable th, String str, Object... objArr) {
        if (str != null && objArr != null && objArr.length > 0) {
            str = d(str, objArr);
        }
        Log.w(this.a, d("[WARN] %s", str), th);
    }

    public String b(Intent intent) {
        Bundle extras;
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (intent != null && (extras = intent.getExtras()) != null) {
            for (String str : extras.keySet()) {
                if (str != null) {
                    sb.append(str + " = " + extras.get(str) + ",");
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public void b(String str, Object... objArr) {
        if (com.naver.glink.android.sdk.d.m()) {
            String strD = str == null ? "" : str;
            if (objArr != null && objArr.length > 0) {
                strD = d(strD, objArr);
            }
            Log.i(this.a, strD);
        }
    }

    public void c(String str, Object... objArr) {
        if (str != null && objArr != null && objArr.length > 0) {
            str = d(str, objArr);
        }
        Log.w(this.a, d("[WARN] %s", str));
    }

    public String d(String str, Object... objArr) {
        if (!com.naver.glink.android.sdk.d.m()) {
            return String.format(str, objArr);
        }
        try {
            return String.format(str, objArr);
        } catch (Exception e) {
            return str;
        }
    }
}
