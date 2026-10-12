package kr.co.nexon.mdev.network;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXHttpURLRequestResult {
    public byte[] bytes;
    public int httpStatus;
    public Map<String, List<String>> responseHeaders;
    public int tag;
    public String errmsg = null;
    public boolean bErr = false;

    public HashMap<String, Object> toMap() {
        Field[] fields = getClass().getFields();
        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i < fields.length; i++) {
            try {
                map.put(fields[i].getName(), fields[i].get(this));
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
