package com.adjust.sdk;

import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ResponseData {
    public ActivityKind activityKind;
    public String adid;
    public AdjustAttribution attribution;
    public JSONObject jsonResponse;
    public String message;
    public boolean success;
    public String timestamp;
    public boolean willRetry;

    protected ResponseData() {
    }

    public static ResponseData buildResponseData(ActivityPackage activityPackage) {
        ResponseData eventResponseData;
        ActivityKind activityKind = activityPackage.getActivityKind();
        switch (activityKind) {
            case SESSION:
                eventResponseData = new SessionResponseData();
                break;
            case CLICK:
                eventResponseData = new SdkClickResponseData();
                break;
            case ATTRIBUTION:
                eventResponseData = new AttributionResponseData();
                break;
            case EVENT:
                eventResponseData = new EventResponseData(activityPackage);
                break;
            default:
                eventResponseData = new ResponseData();
                break;
        }
        eventResponseData.activityKind = activityKind;
        return eventResponseData;
    }

    public String toString() {
        return Util.formatString("message:%s timestamp:%s json:%s", this.message, this.timestamp, this.jsonResponse);
    }
}
