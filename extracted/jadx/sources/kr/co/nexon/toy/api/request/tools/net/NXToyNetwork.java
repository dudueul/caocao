package kr.co.nexon.toy.api.request.tools.net;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.util.NXKeyValuePair;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXToyNetwork {
    public static final int CODE_INVALID_NETWORK = 10004;
    public static final int CODE_NETWORK_CANCELED = 10007;
    public static final int CODE_NETWORK_CONNECTION_LOST = 10010;
    public static final int CODE_NETWORK_TIMEOUT = 10008;
    public static final int CODE_NETWORK_UNAVAILABLE = 10009;

    void addParam(NXKeyValuePair<String, Object> nXKeyValuePair);

    void read(NXToyNetworkListener nXToyNetworkListener);

    void send(byte[] bArr);

    void setHeader(String str, String str2);

    void setRequestTag(NXToyRequestTag nXToyRequestTag);
}
