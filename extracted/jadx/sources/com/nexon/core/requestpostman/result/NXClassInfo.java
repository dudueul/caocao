package com.nexon.core.requestpostman.result;

import java.lang.reflect.Field;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXClassInfo {
    public HashMap<String, Object> toMap() {
        Field[] fields = getClass().getFields();
        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i < fields.length; i++) {
            try {
                if ((fields[i].getModifiers() & 16) != 16) {
                    map.put(fields[i].getName(), fields[i].get(this));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            }
        }
        return map;
    }

    public String toString() {
        return toMap().toString();
    }
}
