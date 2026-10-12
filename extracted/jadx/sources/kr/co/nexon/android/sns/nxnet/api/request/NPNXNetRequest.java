package kr.co.nexon.android.sns.nxnet.api.request;

import android.content.Context;
import com.google.gson.Gson;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.HashMap;
import kr.co.nexon.android.sns.nxnet.NPNexonNet;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetErrorResult;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetResult;
import kr.co.nexon.android.sns.nxnet.session.NPNXNetSession;
import kr.co.nexon.mdev.network.NXHttpURLRequest;
import kr.co.nexon.mdev.network.NXHttpURLRequestDelegate;
import kr.co.nexon.mdev.network.NXHttpURLRequestResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class NPNXNetRequest implements NXHttpURLRequestDelegate {
    protected Context context;
    protected NPNXNetRequestListener listener;
    protected NPNXNetSession nxNetSession;
    protected NPNXNetRequestType requestType;
    protected NXHttpURLRequest urlRequest;
    public int retryConnectionCount = 0;
    protected HttpMethod httpMethod = HttpMethod.POST;
    protected HashMap<String, String> arguments = new HashMap<>();
    protected HashMap<String, String> headers = new HashMap<>();

    public NPNXNetRequest(Context context, NPNXNetRequestType nPNXNetRequestType, NPNXNetSession nPNXNetSession) {
        this.requestType = nPNXNetRequestType;
        this.context = context;
        this.nxNetSession = nPNXNetSession;
    }

    private void processFail(NXHttpURLRequestResult nXHttpURLRequestResult) {
        NPNXNetResult nPNXNetResultMakeDefaultResult;
        byte[] bArr = nXHttpURLRequestResult.bytes;
        if (bArr == null || bArr.length == 0) {
            onPostExec(makeDefaultResult(NXToyErrorCode.INVALID_NETWORK.getCode(), "HTTP STATUS " + nXHttpURLRequestResult.httpStatus, ""));
            return;
        }
        try {
            String str = new String(bArr);
            ToyLog.d("result.resultString=" + str);
            NPNXNetErrorResult nPNXNetErrorResult = (NPNXNetErrorResult) new Gson().fromJson(str, NPNXNetErrorResult.class);
            nPNXNetResultMakeDefaultResult = nPNXNetErrorResult.error != null ? makeDefaultResult(NPNexonNet.getNXNetErrorCode(nPNXNetErrorResult.error.code), nPNXNetErrorResult.error.message, nPNXNetErrorResult.error.message) : makeResult(str);
        } catch (Exception e) {
            nPNXNetResultMakeDefaultResult = makeDefaultResult(NXToyErrorCode.PARSE_ERR2.getCode(), "", e.toString());
            nPNXNetResultMakeDefaultResult.requestTag = this.requestType.getCode();
        }
        onPostExec(nPNXNetResultMakeDefaultResult);
    }

    private void processSuccess(NXHttpURLRequestResult nXHttpURLRequestResult) {
        NPNXNetResult nPNXNetResultMakeDefaultResult;
        byte[] bArr = nXHttpURLRequestResult.bytes;
        if (bArr == null || bArr.length == 0) {
            onPostExec(makeDefaultResult(0, "", ""));
            return;
        }
        try {
            String str = new String(bArr);
            ToyLog.d("result.resultString=" + str);
            nPNXNetResultMakeDefaultResult = makeResult(str);
        } catch (Exception e) {
            nPNXNetResultMakeDefaultResult = makeDefaultResult(NXToyErrorCode.PARSE_ERR2.getCode(), "", e.toString());
            nPNXNetResultMakeDefaultResult.requestTag = this.requestType.getCode();
        }
        onPostExec(nPNXNetResultMakeDefaultResult);
    }

    protected void addHeader(String str, String str2) {
        this.headers.put(str, str2);
    }

    protected void addParam(String str, String str2) {
        this.arguments.put(str, str2);
    }

    public void exec() {
        if (onPreExec()) {
            this.urlRequest = new NXHttpURLRequest(this.httpMethod.name(), this, this.requestType.getURL(), this.requestType.getCode());
            if (this.httpMethod == HttpMethod.POST) {
                this.urlRequest.setHeader(HttpRequest.HEADER_CONTENT_TYPE, HttpRequest.CONTENT_TYPE_FORM);
            }
            this.urlRequest.setHeader(HttpRequest.PARAM_CHARSET, "urf-8");
            if (this.headers != null && this.headers.size() > 0) {
                for (String str : this.headers.keySet()) {
                    this.urlRequest.setHeader(str, this.headers.get(str));
                }
            }
            if (this.arguments != null && this.arguments.size() > 0) {
                for (String str2 : this.arguments.keySet()) {
                    this.urlRequest.addParameter(str2, this.arguments.get(str2));
                }
            }
            this.urlRequest.exec();
        }
    }

    public void execAsync() {
        new Thread(new Runnable() { // from class: kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequest.1
            @Override // java.lang.Runnable
            public void run() {
                NPNXNetRequest.this.exec();
            }
        }).start();
    }

    public NPNXNetResult makeDefaultResult(int i, String str) {
        return new NPNXNetResult(i, str);
    }

    public NPNXNetResult makeDefaultResult(int i, String str, String str2) {
        return new NPNXNetResult(i, str, str2);
    }

    public NPNXNetResult makeResult(String str) {
        return (NPNXNetResult) new Gson().fromJson(str, NPNXNetResult.class);
    }

    @Override // kr.co.nexon.mdev.network.NXHttpURLRequestDelegate
    public void onComplete(NXHttpURLRequestResult nXHttpURLRequestResult) {
        if (nXHttpURLRequestResult.httpStatus == 200) {
            processSuccess(nXHttpURLRequestResult);
        } else {
            processFail(nXHttpURLRequestResult);
        }
    }

    @Override // kr.co.nexon.mdev.network.NXHttpURLRequestDelegate
    public void onDataReceive(byte[] bArr, int i, int i2) {
    }

    @Override // kr.co.nexon.mdev.network.NXHttpURLRequestDelegate
    public void onError(Exception exc) {
        int code;
        if (exc.getMessage().contains("TimeoutException")) {
            code = NXToyErrorCode.NETWORK_TIMEOUT.getCode();
        } else if (exc.getMessage().contains("UnknownHostException")) {
            code = NXToyErrorCode.NETWORK_UNAVAILABLE.getCode();
        } else {
            code = (exc.getMessage().contains("ConnectException") || exc.getMessage().contains("SSLException")) ? NXToyErrorCode.NETWORK_CONNECTION_LOST.getCode() : NXToyErrorCode.INVALID_NETWORK.getCode();
        }
        switch (NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(code)) {
            case INVALID_NETWORK:
            case NETWORK_CANCELED:
            case NETWORK_CONNECTION_LOST:
            case NETWORK_TIMEOUT:
            case NETWORK_UNAVAILABLE:
                if (this.retryConnectionCount < 3) {
                    exec();
                    this.retryConnectionCount++;
                    return;
                }
                break;
        }
        this.retryConnectionCount = 0;
        NPNXNetResult nPNXNetResultMakeDefaultResult = makeDefaultResult(code, exc.toString(), "");
        nPNXNetResultMakeDefaultResult.requestTag = this.requestType.getCode();
        onPostExec(nPNXNetResultMakeDefaultResult);
    }

    protected void onPostExec(NPNXNetResult nPNXNetResult) {
        if (this.listener != null) {
            this.listener.onComplete(nPNXNetResult);
        }
    }

    public abstract boolean onPreExec();

    protected void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setListener(NPNXNetRequestListener nPNXNetRequestListener) {
        this.listener = nPNXNetRequestListener;
    }
}
