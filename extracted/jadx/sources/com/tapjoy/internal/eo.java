package com.tapjoy.internal;

import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class eo {
    public static IllegalStateException a(Object... objArr) {
        StringBuilder sb = new StringBuilder();
        String str = "";
        int length = objArr.length;
        for (int i = 0; i < length; i += 2) {
            if (objArr[i] == null) {
                if (sb.length() > 0) {
                    str = "s";
                }
                sb.append("\n  ");
                sb.append(objArr[i + 1]);
            }
        }
        throw new IllegalStateException("Required field" + str + " not set:" + ((Object) sb));
    }

    public static List a() {
        return new ep(Collections.emptyList());
    }

    public static List a(String str, List list) {
        if (list == null) {
            throw new NullPointerException(str + " == null");
        }
        if (list instanceof ep) {
            list = ((ep) list).a;
        }
        if (list == Collections.emptyList() || (list instanceof en)) {
            return list;
        }
        en enVar = new en(list);
        if (enVar.contains(null)) {
            throw new IllegalArgumentException(str + ".contains(null)");
        }
        return enVar;
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }
}
