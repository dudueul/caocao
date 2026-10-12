package kr.co.nexon.npaccount.jnisupport;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPUnity {
    public static Class getUnityPlayerClass() {
        try {
            return Class.forName("com.unity3d.player.UnityPlayer");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static void unitySendMessage(String str, String str2, String str3) {
        Class unityPlayerClass = getUnityPlayerClass();
        if (unityPlayerClass == null) {
            return;
        }
        try {
            unityPlayerClass.getMethod("UnitySendMessage", String.class, String.class, String.class).invoke(null, str, str2, str3);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }
}
