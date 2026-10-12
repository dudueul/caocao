package com.nhn.android.naverlogin.data;

import android.text.TextUtils;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum OAuthErrorCode {
    NONE("", ""),
    SERVER_ERROR_INVALID_REQUEST("invalid_request", "invalid_request"),
    SERVER_ERROR_UNAUTHORIZED_CLIENT("unauthorized_client", "unauthorized_client"),
    SERVER_ERROR_ACCESS_DENIED("access_denied", "access_denied"),
    SERVER_ERROR_UNSUPPORTED_RESPONSE_TYPE("unsupported_response_type", "unsupported_response_type"),
    SERVER_ERROR_INVALID_SCOPE("invalid_scope", "invalid_scope"),
    SERVER_ERROR_SERVER_ERROR("server_error", "server_error"),
    SERVER_ERROR_TEMPORARILY_UNAVAILABLE("temporarily_unavailable", "temporarily_unavailable"),
    ERROR_NO_CATAGORIZED("no_catagorized_error", "no_catagorized_error"),
    CLIENT_ERROR_PARSING_FAIL("parsing_fail", "parsing_fail"),
    CLIENT_ERROR_NO_CLIENTID("invalid_request", "no_clientid"),
    CLIENT_ERROR_NO_CLIENTSECRET("invalid_request", "no_clientsecret"),
    CLIENT_ERROR_NO_CLIENTNAME("invalid_request", "no_clientname"),
    CLIENT_ERROR_NO_CALLBACKURL("invalid_request", "no_callbackurl"),
    CLIENT_ERROR_CONNECTION_ERROR("server_error", "connection_error"),
    CLIENT_ERROR_CERTIFICATION_ERROR("server_error", "certification_error"),
    CLIENT_USER_CANCEL("user_cancel", "user_cancel");

    private String mCode;
    private String mDesc;

    OAuthErrorCode(String str, String str2) {
        this.mCode = str;
        this.mDesc = str2;
    }

    public static OAuthErrorCode fromString(String str) {
        if (TextUtils.isEmpty(str)) {
            return NONE;
        }
        OAuthErrorCode[] oAuthErrorCodeArrValuesCustom = valuesCustom();
        int length = oAuthErrorCodeArrValuesCustom.length;
        for (int i = 0; i < length; i++) {
            OAuthErrorCode oAuthErrorCode = oAuthErrorCodeArrValuesCustom[i];
            if (str.equalsIgnoreCase(oAuthErrorCode.mCode) || str.equalsIgnoreCase(oAuthErrorCode.name())) {
                return oAuthErrorCode;
            }
        }
        return ERROR_NO_CATAGORIZED;
    }

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static OAuthErrorCode[] valuesCustom() {
        OAuthErrorCode[] oAuthErrorCodeArrValuesCustom = values();
        int length = oAuthErrorCodeArrValuesCustom.length;
        OAuthErrorCode[] oAuthErrorCodeArr = new OAuthErrorCode[length];
        System.arraycopy(oAuthErrorCodeArrValuesCustom, 0, oAuthErrorCodeArr, 0, length);
        return oAuthErrorCodeArr;
    }

    public String getCode() {
        return this.mCode;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public void setDesc(String str) {
        this.mDesc = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mCode;
    }
}
