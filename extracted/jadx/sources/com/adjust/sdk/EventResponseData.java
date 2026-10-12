package com.adjust.sdk;

import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class EventResponseData extends ResponseData {
    public String eventToken;

    public EventResponseData(ActivityPackage activityPackage) {
        this.eventToken = activityPackage.getParameters().get("event_token");
    }

    public AdjustEventFailure getFailureResponseData() {
        if (this.success) {
            return null;
        }
        AdjustEventFailure adjustEventFailure = new AdjustEventFailure();
        adjustEventFailure.message = this.message;
        adjustEventFailure.timestamp = this.timestamp;
        adjustEventFailure.adid = this.adid;
        adjustEventFailure.willRetry = this.willRetry;
        if (this.jsonResponse != null) {
            adjustEventFailure.jsonResponse = this.jsonResponse;
        } else {
            adjustEventFailure.jsonResponse = new JSONObject();
        }
        adjustEventFailure.eventToken = this.eventToken;
        return adjustEventFailure;
    }

    public AdjustEventSuccess getSuccessResponseData() {
        if (!this.success) {
            return null;
        }
        AdjustEventSuccess adjustEventSuccess = new AdjustEventSuccess();
        adjustEventSuccess.message = this.message;
        adjustEventSuccess.timestamp = this.timestamp;
        adjustEventSuccess.adid = this.adid;
        if (this.jsonResponse != null) {
            adjustEventSuccess.jsonResponse = this.jsonResponse;
        } else {
            adjustEventSuccess.jsonResponse = new JSONObject();
        }
        adjustEventSuccess.eventToken = this.eventToken;
        return adjustEventSuccess;
    }
}
