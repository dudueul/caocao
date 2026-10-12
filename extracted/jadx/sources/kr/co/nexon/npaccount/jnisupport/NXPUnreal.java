package kr.co.nexon.npaccount.jnisupport;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPUnreal {
    public static Class getNPAForUnrealClass() {
        try {
            return Class.forName("kr.co.nexon.npaccount.NPAForUnreal");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static void sendMessageToUnreal(String str) {
        Class nPAForUnrealClass = getNPAForUnrealClass();
        if (nPAForUnrealClass == null) {
            return;
        }
        try {
            nPAForUnrealClass.getMethod("sendMessageToUnreal", String.class).invoke(null, str);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }
}
