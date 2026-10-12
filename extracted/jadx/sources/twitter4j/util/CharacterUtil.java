package twitter4j.util;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class CharacterUtil {
    private CharacterUtil() {
        throw new AssertionError();
    }

    public static int count(String str) {
        return str.length();
    }

    public static boolean isExceedingLengthLimitation(String str) {
        return count(str) > 140;
    }
}
