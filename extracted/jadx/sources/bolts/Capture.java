package bolts;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Capture<T> {
    private T value;

    public Capture() {
    }

    public Capture(T t) {
        this.value = t;
    }

    public T get() {
        return this.value;
    }

    public void set(T t) {
        this.value = t;
    }
}
