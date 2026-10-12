package com.nhn.android.naverlogin.data;

import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.naver.glink.android.sdk.login.neoid.PlugSchemeActivity;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class OAuthResponse {
    private String mAccessToken;
    private OAuthErrorCode mErrorCode;
    private String mErrorDescription;
    private long mExpiresIn;
    private String mRefreshToken;
    private String mResultValue;
    private String mTokenType;

    public OAuthResponse(OAuthErrorCode oAuthErrorCode) {
        this.mErrorCode = oAuthErrorCode;
        this.mErrorDescription = oAuthErrorCode.getDesc();
    }

    public OAuthResponse(OAuthErrorCode oAuthErrorCode, String str) {
        this.mErrorCode = oAuthErrorCode;
        this.mErrorDescription = str;
    }

    public OAuthResponse(Map<String, String> map) {
        this.mAccessToken = map.get("access_token");
        this.mRefreshToken = map.get("refresh_token");
        this.mTokenType = map.get("token_type");
        try {
            this.mExpiresIn = Long.parseLong(map.get("expires_in"));
        } catch (Exception e) {
            this.mExpiresIn = 3600L;
        }
        this.mErrorCode = OAuthErrorCode.fromString(map.get("error"));
        this.mErrorDescription = map.get(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
        this.mResultValue = map.get(PlugSchemeActivity.b);
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public OAuthErrorCode getErrorCode() {
        return this.mErrorCode;
    }

    public String getErrorDesc() {
        return this.mErrorDescription;
    }

    public long getExpiresIn() {
        return this.mExpiresIn;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public String getResultValue() {
        return this.mResultValue;
    }

    public String getTokenType() {
        return this.mTokenType;
    }

    public boolean isSuccess() {
        return TextUtils.isEmpty(this.mErrorCode.getCode()) && !TextUtils.isEmpty(this.mAccessToken);
    }
}
