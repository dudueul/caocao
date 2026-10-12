package com.adjust.sdk;

import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class SessionResponseData extends ResponseData {
    public AdjustSessionFailure getFailureResponseData() {
        if (this.success) {
            return null;
        }
        AdjustSessionFailure adjustSessionFailure = new AdjustSessionFailure();
        adjustSessionFailure.message = this.message;
        adjustSessionFailure.timestamp = this.timestamp;
        adjustSessionFailure.adid = this.adid;
        adjustSessionFailure.willRetry = this.willRetry;
        if (this.jsonResponse != null) {
            adjustSessionFailure.jsonResponse = this.jsonResponse;
            return adjustSessionFailure;
        }
        adjustSessionFailure.jsonResponse = new JSONObject();
        return adjustSessionFailure;
    }

    public AdjustSessionSuccess getSuccessResponseData() {
        if (!this.success) {
            return null;
        }
        AdjustSessionSuccess adjustSessionSuccess = new AdjustSessionSuccess();
        adjustSessionSuccess.message = this.message;
        adjustSessionSuccess.timestamp = this.timestamp;
        adjustSessionSuccess.adid = this.adid;
        if (this.jsonResponse != null) {
            adjustSessionSuccess.jsonResponse = this.jsonResponse;
            return adjustSessionSuccess;
        }
        adjustSessionSuccess.jsonResponse = new JSONObject();
        return adjustSessionSuccess;
    }
}
