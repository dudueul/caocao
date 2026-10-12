package com.naver.plug.android.core.api;

import android.text.TextUtils;
import com.naver.plug.android.core.a.a;
import com.naver.plug.android.core.b;
import twitter4j.HttpResponseCode;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class PlugError extends a {
    public final int errorCode;
    public final String errorMessage;
    public final String moreInfo;
    public final Status status;

    public enum Status {
        OPEN_API_ERROR(-1),
        OK(200),
        BAD_REQUEST(HttpResponseCode.BAD_REQUEST),
        UNAUTHORIZED(HttpResponseCode.UNAUTHORIZED),
        FORBIDDEN(HttpResponseCode.FORBIDDEN),
        NOT_FOUND(HttpResponseCode.NOT_FOUND),
        CONFLICT(409),
        INTERNAL_SERVER_ERROR(500),
        SERVICE(HttpResponseCode.SERVICE_UNAVAILABLE),
        APPLICATION(9999);

        final int code;

        Status(int i) {
            this.code = i;
        }

        static Status by(int i) {
            for (Status status : values()) {
                if (status.code == i) {
                    return status;
                }
            }
            return null;
        }
    }

    public PlugError(int i, int i2, String str, String str2) {
        this.status = Status.by(i);
        this.errorCode = i2;
        this.errorMessage = str;
        this.moreInfo = str2;
    }

    public static PlugError newNetworkError() {
        return new PlugError(-1, -1, b.b(), null);
    }

    public boolean hasErrorMessage() {
        return !TextUtils.isEmpty(this.errorMessage);
    }

    public boolean isAgreeTermsError() {
        return this.status == Status.UNAUTHORIZED && this.errorCode == 9005;
    }

    public boolean isAuthenticationError() {
        switch (this.status) {
            case OPEN_API_ERROR:
                return this.errorCode == 24 || (this.errorMessage != null && this.errorMessage.contains("Authentication"));
            case UNAUTHORIZED:
                return this.errorCode == 9001;
            default:
                return false;
        }
    }

    public boolean isCloseMemberError() {
        return this.status == Status.FORBIDDEN && this.errorCode == 5021;
    }

    public boolean isLoginError() {
        return (this.status == Status.UNAUTHORIZED && this.errorCode == 9001) || (this.status == Status.FORBIDDEN && this.errorCode == 5022);
    }

    public boolean isNeedJoinError() {
        return this.status == Status.FORBIDDEN && this.errorCode == 9003;
    }

    public boolean isNetworkError() {
        return this.errorCode == -1;
    }

    public boolean isPendingJoinError() {
        return this.status == Status.UNAUTHORIZED && this.errorCode == 4016;
    }

    public String toString() {
        return "PlugError{status=" + this.status + ", errorCode=" + this.errorCode + ", errorMessage='" + this.errorMessage + "', moreInfo='" + this.moreInfo + "'}";
    }
}
