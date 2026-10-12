package twitter4j;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class Version {
    private static final String TITLE = "Twitter4J";
    private static final String VERSION = "4.0.3";

    private Version() {
        throw new AssertionError();
    }

    public static String getVersion() {
        return VERSION;
    }

    public static void main(String[] strArr) {
        System.out.println("Twitter4J 4.0.3");
    }
}
