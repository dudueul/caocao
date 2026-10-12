package twitter4j;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public enum RequestMethod {
    GET,
    POST,
    DELETE,
    HEAD,
    PUT;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static RequestMethod[] valuesCustom() {
        RequestMethod[] requestMethodArrValuesCustom = values();
        int length = requestMethodArrValuesCustom.length;
        RequestMethod[] requestMethodArr = new RequestMethod[length];
        System.arraycopy(requestMethodArrValuesCustom, 0, requestMethodArr, 0, length);
        return requestMethodArr;
    }
}
