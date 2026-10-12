package com.nexon.core.log.util;

import android.os.Bundle;
import com.nexon.core.log.model.NXToyStackTraceInfo;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.util.NXStringUtil;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyLogUtil {
    public static NXToyStackTraceInfo extractStackTrace() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[5];
        int lineNumber = stackTraceElement.getLineNumber();
        String className = stackTraceElement.getClassName();
        return new NXToyStackTraceInfo(className.substring(className.lastIndexOf(".") + 1), stackTraceElement.getMethodName(), lineNumber + "");
    }

    private static Map<String, Object> maskPersonalDataInMap(Map<String, Object> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj == null) {
                linkedHashMap.put(str, null);
            } else if (str.equalsIgnoreCase("pw") || str.equalsIgnoreCase("password") || str.equalsIgnoreCase("pass_word") || str.equalsIgnoreCase("passwd") || str.equalsIgnoreCase("token") || str.equalsIgnoreCase("accessToken") || str.equalsIgnoreCase("access_Token") || str.equalsIgnoreCase("key_accessToken") || str.equalsIgnoreCase("access_Token")) {
                linkedHashMap.put(str, "hidden");
            } else if (str.equalsIgnoreCase("PhoneNumber") || str.equalsIgnoreCase("Phone_Number")) {
                if (obj instanceof String) {
                    linkedHashMap.put(str, NXStringUtil.getMaskedPhoneNumber((String) obj));
                } else {
                    linkedHashMap.put(str, "hidden");
                }
            } else if (obj.getClass().isPrimitive() || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Character) || (obj instanceof Boolean) || (obj instanceof Void)) {
                linkedHashMap.put(str, obj);
            } else if (obj instanceof String) {
                String str2 = (String) obj;
                if (NXStringUtil.containsEmail(str2)) {
                    linkedHashMap.put(str, NXStringUtil.getMaskedEmail(str2));
                } else {
                    linkedHashMap.put(str, obj);
                }
            } else if (obj instanceof NXClassInfo) {
                linkedHashMap.put(str, new LinkedHashMap(((NXClassInfo) obj).toMap()));
            } else if (obj instanceof Bundle) {
                Bundle bundle = (Bundle) obj;
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                for (String str3 : bundle.keySet()) {
                    linkedHashMap2.put(str3, bundle.get(str3));
                }
                linkedHashMap.put(str, maskPersonalDataInMap(linkedHashMap2).toString());
            } else if (obj instanceof Map) {
                linkedHashMap.put(str, maskPersonalDataInMap((Map) obj).toString());
            } else {
                linkedHashMap.put(str, obj.toString());
            }
        }
        return linkedHashMap;
    }

    public static Map<String, Object> parseVarargs(String str, Object... objArr) {
        if (objArr == null || objArr.length == 0 || objArr[0] == null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("message", str);
            return linkedHashMap;
        }
        Object[] objArr2 = objArr;
        if (objArr.length % 2 != 0) {
            objArr2 = new Object[objArr.length + 1];
            System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
            objArr2[objArr.length] = null;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("message", str);
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        for (int i = 0; i < objArr2.length; i += 2) {
            String str2 = objArr2[i];
            if (str2 != null) {
                linkedHashMap3.put(str2 instanceof String ? str2 : str2.toString(), objArr2[i + 1]);
            }
        }
        linkedHashMap2.putAll(maskPersonalDataInMap(linkedHashMap3));
        return linkedHashMap2;
    }
}
