package com.nhn.android.naverlogin.data;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum OAuthLoginState {
    NEED_INIT,
    NEED_LOGIN,
    NEED_REFRESH_TOKEN,
    OK;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static OAuthLoginState[] valuesCustom() {
        OAuthLoginState[] oAuthLoginStateArrValuesCustom = values();
        int length = oAuthLoginStateArrValuesCustom.length;
        OAuthLoginState[] oAuthLoginStateArr = new OAuthLoginState[length];
        System.arraycopy(oAuthLoginStateArrValuesCustom, 0, oAuthLoginStateArr, 0, length);
        return oAuthLoginStateArr;
    }
}
