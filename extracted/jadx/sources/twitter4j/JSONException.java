package twitter4j;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class JSONException extends Exception {
    private static final long serialVersionUID = -4144585377907783745L;
    private Throwable cause;

    public JSONException(String str) {
        super(str);
    }

    public JSONException(Throwable th) {
        super(th.getMessage());
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
