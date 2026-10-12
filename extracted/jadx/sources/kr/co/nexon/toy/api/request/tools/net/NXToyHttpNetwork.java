package kr.co.nexon.toy.api.request.tools.net;

import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.util.NXKeyValuePair;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kr.co.nexon.mdev.network.NXHttpURLRequest;
import kr.co.nexon.mdev.network.NXHttpURLRequestDelegate;
import kr.co.nexon.mdev.network.NXHttpURLRequestResult;
import kr.co.nexon.toy.api.request.tools.net.logger.NXToyNetworkLogger;
import net.daum.mf.oauth.impl.Constant;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public abstract class NXToyHttpNetwork implements NXToyNetwork, NXHttpURLRequestDelegate {
    private ArrayList<NXKeyValuePair<String, Object>> arguments;
    private HashMap<String, String> headers;
    private NXToyNetworkListener listener;
    public NXToyNetworkLogger networkLogger;
    protected NXToyRequestTag requestTag;
    private long start;
    NXHttpURLRequest urlRequest;

    public NXToyHttpNetwork() {
        this.networkLogger = null;
        this.arguments = new ArrayList<>();
        this.headers = new HashMap<>();
        this.networkLogger = new NXToyNetworkLogger() { // from class: kr.co.nexon.toy.api.request.tools.net.NXToyHttpNetwork.1
            @Override // kr.co.nexon.toy.api.request.tools.net.logger.NXToyNetworkLogger
            public void sendElapsedTime(String str, long j) {
                ToyLog.d(str + " " + j);
            }

            @Override // kr.co.nexon.toy.api.request.tools.net.logger.NXToyNetworkLogger
            public void sendEvent(String str, String str2) {
                ToyLog.d(str + " " + str2);
            }
        };
    }

    public NXToyHttpNetwork(NXToyRequestTag nXToyRequestTag) {
        this();
        this.requestTag = nXToyRequestTag;
    }

    @Override // kr.co.nexon.toy.api.request.tools.net.NXToyNetwork
    public void addParam(NXKeyValuePair<String, Object> nXKeyValuePair) {
        this.arguments.add(nXKeyValuePair);
    }

    protected abstract String getURL();

    @Override // kr.co.nexon.mdev.network.NXHttpURLRequestDelegate
    public void onComplete(NXHttpURLRequestResult nXHttpURLRequestResult) {
        this.networkLogger.sendElapsedTime(getURL(), System.currentTimeMillis() - this.start);
        if (nXHttpURLRequestResult.httpStatus != 200) {
            this.listener.onError(10004, "HTTP STATUS " + nXHttpURLRequestResult.httpStatus);
        } else {
            this.listener.onComplete(nXHttpURLRequestResult.bytes);
        }
    }

    @Override // kr.co.nexon.mdev.network.NXHttpURLRequestDelegate
    public void onDataReceive(byte[] bArr, int i, int i2) {
    }

    @Override // kr.co.nexon.mdev.network.NXHttpURLRequestDelegate
    public void onError(Exception exc) {
        int i;
        this.networkLogger.sendElapsedTime(getURL(), System.currentTimeMillis() - this.start);
        if (exc.getMessage().contains("TimeoutException")) {
            i = 10008;
        } else if (exc.getMessage().contains("UnknownHostException")) {
            i = NXToyNetwork.CODE_NETWORK_UNAVAILABLE;
        } else {
            i = (exc.getMessage().contains("ConnectException") || exc.getMessage().contains("SSLException")) ? NXToyNetwork.CODE_NETWORK_CONNECTION_LOST : 10004;
        }
        this.listener.onError(i, exc.toString());
    }

    @Override // kr.co.nexon.toy.api.request.tools.net.NXToyNetwork
    public void read(NXToyNetworkListener nXToyNetworkListener) {
        this.listener = nXToyNetworkListener;
        this.urlRequest.exec();
    }

    @Override // kr.co.nexon.toy.api.request.tools.net.NXToyNetwork
    public void send(byte[] bArr) {
        ToyLog.d(getURL());
        this.start = System.currentTimeMillis();
        this.urlRequest = new NXHttpURLRequest("POST", this, getURL(), this.requestTag.getValue());
        this.urlRequest.setHeader(HttpRequest.PARAM_CHARSET, Constant.DEFAULT_ENCODING);
        for (Map.Entry<String, String> entry : this.headers.entrySet()) {
            this.urlRequest.setHeader(entry.getKey(), entry.getValue());
        }
        ToyLog.d("request headers:" + this.headers.toString());
        this.urlRequest.setHTTPBody(bArr);
    }

    @Override // kr.co.nexon.toy.api.request.tools.net.NXToyNetwork
    public void setHeader(String str, String str2) {
        this.headers.put(str, str2);
    }

    public void setNetworkLogger(NXToyNetworkLogger nXToyNetworkLogger) {
        if (nXToyNetworkLogger != null) {
            this.networkLogger = nXToyNetworkLogger;
        }
    }

    @Override // kr.co.nexon.toy.api.request.tools.net.NXToyNetwork
    public void setRequestTag(NXToyRequestTag nXToyRequestTag) {
        this.requestTag = nXToyRequestTag;
    }
}
