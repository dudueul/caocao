package com.android.iab.util;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class IabResult {
    String mMessage;
    int mResponse;

    public IabResult(int i, String str) {
        this.mResponse = i;
        if (str == null || str.trim().length() == 0) {
            this.mMessage = IabHelper.getResponseDesc(i);
        } else {
            this.mMessage = str + " (response: " + IabHelper.getResponseDesc(i) + ")";
        }
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getMessageForUnity() {
        return this.mResponse + ":" + this.mMessage;
    }

    public int getResponse() {
        return this.mResponse;
    }

    public boolean isFailure() {
        return !isSuccess();
    }

    public boolean isSuccess() {
        return this.mResponse == 0;
    }

    public String toString() {
        return "IabResult: " + getMessage();
    }
}
