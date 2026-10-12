package com.google.android.gms.common.util;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class MapUtils {
    public static <K, V> K getKeyFromMap(Map<K, V> map, K k) {
        if (map.containsKey(k)) {
            for (K k2 : map.keySet()) {
                if (k2.equals(k)) {
                    return k2;
                }
            }
        }
        return null;
    }

    public static void writeStringMapToJson(StringBuilder sb, HashMap<String, String> map) {
        sb.append("{");
        boolean z = true;
        for (String str : map.keySet()) {
            if (z) {
                z = false;
            } else {
                sb.append(",");
                z = z;
            }
            String str2 = map.get(str);
            sb.append("\"").append(str).append("\":");
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append("\"").append(str2).append("\"");
            }
        }
        sb.append("}");
    }
}
