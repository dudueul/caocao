package com.navercorp.volleyextensions.volleyer.util;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ClassUtils {
    public static boolean isPresent(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
