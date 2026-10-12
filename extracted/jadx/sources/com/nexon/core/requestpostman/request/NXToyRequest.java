package com.nexon.core.requestpostman.request;

import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyRequest {
    private String httpURL;
    private byte[] messageBody;
    private Map<String, String> messageHeader = new HashMap();
    private NXToyRequestMethod method = NXToyRequestMethod.GET;
    private Class<? extends NXToyResult> resultClass;

    public void addPathToHttpURL(String str) {
        this.httpURL += str;
        ToyLog.d("Request Http URL : " + this.httpURL);
    }

    public String getHttpURL() {
        return this.httpURL;
    }

    public byte[] getMessageBody() {
        return this.messageBody;
    }

    public Map<String, String> getMessageHeader() {
        return this.messageHeader;
    }

    public NXToyRequestMethod getMethod() {
        return this.method;
    }

    public Class<? extends NXToyResult> getResultClass() {
        return this.resultClass;
    }

    public void putMessageHeader(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return;
        }
        ToyLog.d("Message Header : " + map.toString());
        for (String str : map.keySet()) {
            this.messageHeader.put(str, map.get(str));
        }
    }

    public void setHttpURLWithoutPath(String str) {
        this.httpURL = str;
    }

    public void setMessageBody(byte[] bArr) {
        this.messageBody = bArr;
    }

    public void setMethod(NXToyRequestMethod nXToyRequestMethod) {
        ToyLog.d("Set Request Method : " + nXToyRequestMethod);
        this.method = nXToyRequestMethod;
    }

    public void setResultClass(Class<? extends NXToyResult> cls) {
        ToyLog.d("Target result class : " + cls);
        this.resultClass = cls;
    }
}
