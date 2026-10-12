package net.daum.mf.oauth.impl;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class Constant {
    public static final String DEFAULT_ENCODING = "utf-8";
    public static final String PACKAGE_DAUM_APP = "net.daum.android.daum";
    public static final String PARAM_ACCESS_TOKEN = "access_token";
    public static final String PARAM_EXPIRES_IN = "expires_in";
    public static final String PARAM_TEMPLITE = "%s=%s";
    public static final String SCHEME_OAUTH_PREFIX = "daum-";
    public static final int THIRTY_SECONDS = 30000;
    public static final String URL_AUTHORIZE_CALLBACK = "daum-%s://oauth/callback";
    public static final String URL_AUTHORIZE_CALLBACK_WITH_SUFFIX = "daum-%s-%s://oauth/callback";
    public static final String URL_AUTHORIZE_REQUEST = "https://apis.daum.net/oauth2/authorize?response_type=token&client_id=%s&redirect_uri=%s";
    public static final String URL_BASE_OPEN_API = "https://apis.daum.net";
}
