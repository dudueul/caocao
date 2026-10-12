package twitter4j;

import com.facebook.places.model.PlaceFields;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class Paging implements Serializable {
    static final String COUNT = "count";
    static final String PER_PAGE = "per_page";
    private static final long serialVersionUID = -7226113618341047983L;
    private int count;
    private long maxId;
    private int page;
    private long sinceId;
    static final char[] S = {'s'};
    static final char[] SMCP = {'s', 'm', 'c', 'p'};
    private static final HttpParameter[] NULL_PARAMETER_ARRAY = new HttpParameter[0];
    private static final List<HttpParameter> NULL_PARAMETER_LIST = new ArrayList(0);

    public Paging() {
        this.page = -1;
        this.count = -1;
        this.sinceId = -1L;
        this.maxId = -1L;
    }

    public Paging(int i) {
        this.page = -1;
        this.count = -1;
        this.sinceId = -1L;
        this.maxId = -1L;
        setPage(i);
    }

    public Paging(int i, int i2) {
        this(i);
        setCount(i2);
    }

    public Paging(int i, int i2, long j) {
        this(i, i2);
        setSinceId(j);
    }

    public Paging(int i, int i2, long j, long j2) {
        this(i, i2, j);
        setMaxId(j2);
    }

    public Paging(int i, long j) {
        this(i);
        setSinceId(j);
    }

    public Paging(long j) {
        this.page = -1;
        this.count = -1;
        this.sinceId = -1L;
        this.maxId = -1L;
        setSinceId(j);
    }

    private void addPostParameter(char[] cArr, char c, List<HttpParameter> list, String str, long j) {
        boolean z = false;
        for (char c2 : cArr) {
            if (c2 == c) {
                z = true;
                break;
            }
        }
        if (!z && -1 != j) {
            throw new IllegalStateException("Paging parameter [" + str + "] is not supported with this operation.");
        }
        if (-1 != j) {
            list.add(new HttpParameter(str, String.valueOf(j)));
        }
    }

    HttpParameter[] asPostParameterArray() {
        List<HttpParameter> listAsPostParameterList = asPostParameterList(SMCP, "count");
        return listAsPostParameterList.size() == 0 ? NULL_PARAMETER_ARRAY : (HttpParameter[]) listAsPostParameterList.toArray(new HttpParameter[listAsPostParameterList.size()]);
    }

    HttpParameter[] asPostParameterArray(char[] cArr, String str) {
        ArrayList arrayList = new ArrayList(cArr.length);
        addPostParameter(cArr, 's', arrayList, "since_id", getSinceId());
        addPostParameter(cArr, 'm', arrayList, "max_id", getMaxId());
        addPostParameter(cArr, 'c', arrayList, str, getCount());
        addPostParameter(cArr, 'p', arrayList, PlaceFields.PAGE, getPage());
        return arrayList.size() == 0 ? NULL_PARAMETER_ARRAY : (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()]);
    }

    List<HttpParameter> asPostParameterList() {
        return asPostParameterList(SMCP, "count");
    }

    List<HttpParameter> asPostParameterList(char[] cArr) {
        return asPostParameterList(cArr, "count");
    }

    List<HttpParameter> asPostParameterList(char[] cArr, String str) {
        ArrayList arrayList = new ArrayList(cArr.length);
        addPostParameter(cArr, 's', arrayList, "since_id", getSinceId());
        addPostParameter(cArr, 'm', arrayList, "max_id", getMaxId());
        addPostParameter(cArr, 'c', arrayList, str, getCount());
        addPostParameter(cArr, 'p', arrayList, PlaceFields.PAGE, getPage());
        return arrayList.size() == 0 ? NULL_PARAMETER_LIST : arrayList;
    }

    public Paging count(int i) {
        setCount(i);
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Paging)) {
            return false;
        }
        Paging paging = (Paging) obj;
        return this.count == paging.count && this.maxId == paging.maxId && this.page == paging.page && this.sinceId == paging.sinceId;
    }

    public int getCount() {
        return this.count;
    }

    public long getMaxId() {
        return this.maxId;
    }

    public int getPage() {
        return this.page;
    }

    public long getSinceId() {
        return this.sinceId;
    }

    public int hashCode() {
        return (((((this.page * 31) + this.count) * 31) + ((int) (this.sinceId ^ (this.sinceId >>> 32)))) * 31) + ((int) (this.maxId ^ (this.maxId >>> 32)));
    }

    public Paging maxId(long j) {
        setMaxId(j);
        return this;
    }

    public void setCount(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("count should be positive integer. passed:" + i);
        }
        this.count = i;
    }

    public void setMaxId(long j) {
        if (j < 1) {
            throw new IllegalArgumentException("max_id should be positive integer. passed:" + j);
        }
        this.maxId = j;
    }

    public void setPage(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("page should be positive integer. passed:" + i);
        }
        this.page = i;
    }

    public void setSinceId(long j) {
        if (j < 1) {
            throw new IllegalArgumentException("since_id should be positive integer. passed:" + j);
        }
        this.sinceId = j;
    }

    public Paging sinceId(long j) {
        setSinceId(j);
        return this;
    }

    public String toString() {
        return "Paging{page=" + this.page + ", count=" + this.count + ", sinceId=" + this.sinceId + ", maxId=" + this.maxId + '}';
    }
}
