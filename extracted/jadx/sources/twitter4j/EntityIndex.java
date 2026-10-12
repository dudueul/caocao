package twitter4j;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
abstract class EntityIndex implements Comparable<EntityIndex>, Serializable {
    private static final long serialVersionUID = 3757474748266170719L;
    private int start = -1;
    private int end = -1;

    EntityIndex() {
    }

    @Override // java.lang.Comparable
    public int compareTo(EntityIndex entityIndex) {
        long j = this.start - entityIndex.start;
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getEnd() {
        return this.end;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getStart() {
        return this.start;
    }

    void setEnd(int i) {
        this.end = i;
    }

    void setStart(int i) {
        this.start = i;
    }
}
