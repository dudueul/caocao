package twitter4j;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class ScopesImpl implements Scopes {
    private final String[] placeIds;

    ScopesImpl() {
        this.placeIds = new String[0];
    }

    public ScopesImpl(String[] strArr) {
        this.placeIds = strArr;
    }

    @Override // twitter4j.Scopes
    public String[] getPlaceIds() {
        return this.placeIds;
    }
}
