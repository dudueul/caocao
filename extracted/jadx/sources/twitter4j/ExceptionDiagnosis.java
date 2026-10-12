package twitter4j;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class ExceptionDiagnosis implements Serializable {
    private static final long serialVersionUID = 8501009773274399369L;
    private String hexString;
    private int lineNumberHash;
    private int stackLineHash;

    ExceptionDiagnosis(Throwable th) {
        this(th, new String[0]);
    }

    ExceptionDiagnosis(Throwable th, String[] strArr) {
        this.hexString = "";
        StackTraceElement[] stackTrace = th.getStackTrace();
        this.stackLineHash = 0;
        this.lineNumberHash = 0;
        for (int length = stackTrace.length - 1; length >= 0; length--) {
            StackTraceElement stackTraceElement = stackTrace[length];
            for (String str : strArr) {
                if (stackTraceElement.getClassName().startsWith(str)) {
                    this.stackLineHash = (this.stackLineHash * 31) + stackTraceElement.getClassName().hashCode() + stackTraceElement.getMethodName().hashCode();
                    this.lineNumberHash = (this.lineNumberHash * 31) + stackTraceElement.getLineNumber();
                    break;
                }
            }
        }
        this.hexString = String.valueOf(this.hexString) + toHexString(this.stackLineHash) + "-" + toHexString(this.lineNumberHash);
        if (th.getCause() != null) {
            this.hexString = String.valueOf(this.hexString) + " " + new ExceptionDiagnosis(th.getCause(), strArr).asHexString();
        }
    }

    private String toHexString(int i) {
        String str = "0000000" + Integer.toHexString(i);
        return str.substring(str.length() - 8, str.length());
    }

    String asHexString() {
        return this.hexString;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExceptionDiagnosis exceptionDiagnosis = (ExceptionDiagnosis) obj;
        return this.lineNumberHash == exceptionDiagnosis.lineNumberHash && this.stackLineHash == exceptionDiagnosis.stackLineHash;
    }

    int getLineNumberHash() {
        return this.lineNumberHash;
    }

    String getLineNumberHashAsHex() {
        return toHexString(this.lineNumberHash);
    }

    int getStackLineHash() {
        return this.stackLineHash;
    }

    String getStackLineHashAsHex() {
        return toHexString(this.stackLineHash);
    }

    public int hashCode() {
        return (this.stackLineHash * 31) + this.lineNumberHash;
    }

    public String toString() {
        return "ExceptionDiagnosis{stackLineHash=" + this.stackLineHash + ", lineNumberHash=" + this.lineNumberHash + '}';
    }
}
