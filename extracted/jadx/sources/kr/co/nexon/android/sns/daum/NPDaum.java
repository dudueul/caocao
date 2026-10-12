package kr.co.nexon.android.sns.daum;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.util.NXStringUtil;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import kr.co.nexon.android.sns.NPAuthFriendsListener;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;
import net.daum.mf.oauth.MobileOAuthLibrary;
import net.daum.mf.oauth.impl.AccessTokenStore;
import net.daum.mf.oauth.impl.Constant;
import net.daum.mf.oauth.impl.OAuthUtils;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPDaum extends NPAuthPlugin {
    public static final int CODE_DAUM_ACCESS_DENIED = 90706;
    public static final int CODE_DAUM_GET_TOKEN_FAILED = 90703;
    public static final int CODE_DAUM_INVALID_AUTH_REQUEST = 90701;
    public static final int CODE_DAUM_INVALID_SCOPE = 90708;
    public static final int CODE_DAUM_NETWORK_FAIL = 90710;
    public static final int CODE_DAUM_NOT_CONNECTED = 90704;
    public static final int CODE_DAUM_TOKEN_EXPIRED = 90702;
    public static final int CODE_DAUM_UNAUTH_CLIENT = 90705;
    public static final int CODE_DAUM_UNKNOWN = 90709;
    public static final int CODE_DAUM_UNSUPPORTED_RESPONSE_TYPE = 90707;
    public static final int REQ_DAUM_CODE = 38951;
    public static String SERVICE_NAME = "daum";
    public static NPDaumOAuthLoginHandler mDaumOAuthLoginHandler;
    private AccessTokenStore accessTokenStore;
    private String clientID;
    private NPAuthListener mLoginListener;

    public NPDaum(Context context) throws Exception {
        super(context);
        try {
            this.clientID = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("DAUM_OAUTH_CLIENT_ID");
            if (NXStringUtil.isNull(this.clientID)) {
                throw new Exception("Daum clientID CHECK!");
            }
            init(context);
            CookieSyncManager.createInstance(context);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Daum clientID CHECK!");
        }
    }

    private void init(Context context) {
        makeOAuthLoginHandler();
        this.accessTokenStore = new AccessTokenStore(context, this.clientID);
    }

    private void makeOAuthLoginHandler() {
        if (mDaumOAuthLoginHandler != null) {
            return;
        }
        mDaumOAuthLoginHandler = new NPDaumOAuthLoginHandler() { // from class: kr.co.nexon.android.sns.daum.NPDaum.1
            @Override // kr.co.nexon.android.sns.daum.NPDaumOAuthLoginHandler
            public void run(int i, Uri uri) {
                if (i == 1) {
                    NPDaum.this.handleUrlScheme(uri, NPDaum.this.mLoginListener);
                    return;
                }
                if (uri != null) {
                    NPDaum.this.handleUrlScheme(uri, NPDaum.this.mLoginListener);
                } else {
                    if (i != -2 || NPDaum.this.mLoginListener == null) {
                        return;
                    }
                    NPDaum.this.mLoginListener.onResult(NPDaum.CODE_DAUM_TOKEN_EXPIRED, "daum token expired", null);
                }
            }
        };
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getAccessToken(Context context, NPAuthListener nPAuthListener) {
        String accessToken = this.accessTokenStore != null ? this.accessTokenStore.getAccessToken() : "";
        if (!NXStringUtil.isNotNull(accessToken)) {
            nPAuthListener.onResult(CODE_DAUM_GET_TOKEN_FAILED, null, null);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(NPAuthPlugin.KEY_ACCESSTOKEN, "Bearer " + accessToken);
        nPAuthListener.onResult(0, null, bundle);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getFriends(Context context, boolean z, NPAuthFriendsListener nPAuthFriendsListener) {
        nPAuthFriendsListener.onResult(NPAuthPlugin.CODE_NOT_SUPPORT, null, false, null);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getUserInfo(Context context, final NPAuthListener nPAuthListener) {
        try {
            final String str = String.format(Constant.URL_AUTHORIZE_REQUEST, this.clientID, URLEncoder.encode(OAuthUtils.getAuthrizationCallbackUrl(this.clientID, null), Constant.DEFAULT_ENCODING));
            final String cookie = CookieManager.getInstance().getCookie(".daum.net");
            new Thread(new Runnable() { // from class: kr.co.nexon.android.sns.daum.NPDaum.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                        httpURLConnection.setReadTimeout(30000);
                        httpURLConnection.setConnectTimeout(30000);
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setRequestProperty("Cookie", cookie);
                        httpURLConnection.connect();
                        if (httpURLConnection.getResponseCode() == 200) {
                            String string = httpURLConnection.getURL().toString();
                            ToyLog.d("resultUrl:" + string);
                            if (string.startsWith("https://logins.daum.net")) {
                                nPAuthListener.onResult(NPDaum.CODE_DAUM_TOKEN_EXPIRED, "daum token expired", null);
                            } else {
                                NPDaum.this.handleUrlScheme(Uri.parse(string.split("redirect_uri=")[1].replace("#", "?")), new NPAuthListener() { // from class: kr.co.nexon.android.sns.daum.NPDaum.2.1
                                    @Override // kr.co.nexon.android.sns.NPAuthListener
                                    public void onResult(int i, String str2, Bundle bundle) {
                                        if (i == 90701 && str2.contains("AuthrizationCallbackUrl invalid")) {
                                            nPAuthListener.onResult(NPDaum.CODE_DAUM_TOKEN_EXPIRED, str2, null);
                                        } else {
                                            nPAuthListener.onResult(i, str2, bundle);
                                        }
                                    }
                                });
                            }
                        } else {
                            nPAuthListener.onResult(NPDaum.CODE_DAUM_NETWORK_FAIL, "Request failed. Please check your network settings", null);
                        }
                    } catch (Exception e) {
                        nPAuthListener.onResult(NPDaum.CODE_DAUM_UNKNOWN, e.getMessage(), null);
                    }
                }
            }).start();
        } catch (UnsupportedEncodingException e) {
            nPAuthListener.onResult(CODE_DAUM_INVALID_AUTH_REQUEST, "invaild auth request", null);
        }
    }

    public void handleUrlScheme(Uri uri, NPAuthListener nPAuthListener) {
        if (!OAuthUtils.isAuthrizationCallbackUrl(uri.getScheme(), this.clientID, null)) {
            nPAuthListener.onResult(CODE_DAUM_INVALID_AUTH_REQUEST, "AuthrizationCallbackUrl invalid", null);
            return;
        }
        Uri uri2 = Uri.parse(uri.toString().replace("#", "?"));
        String queryParameter = uri2.getQueryParameter("error");
        if (queryParameter == null) {
            this.accessTokenStore = new AccessTokenStore(this.applicationContext, this.clientID, uri2);
            Bundle bundle = new Bundle();
            bundle.putString(NPAuthPlugin.KEY_ID, "DAUM_CHANNEL_LOGIN");
            bundle.putString(NPAuthPlugin.KEY_ACCESSTOKEN, this.accessTokenStore.getAccessToken());
            nPAuthListener.onResult(0, "", bundle);
            return;
        }
        if (queryParameter.equals("invalid_request")) {
            nPAuthListener.onResult(CODE_DAUM_INVALID_AUTH_REQUEST, "", null);
            return;
        }
        if (queryParameter.equals("unauthorized_client")) {
            nPAuthListener.onResult(CODE_DAUM_UNAUTH_CLIENT, "", null);
            return;
        }
        if (queryParameter.equals("access_denied")) {
            nPAuthListener.onResult(CODE_DAUM_ACCESS_DENIED, "", null);
        } else if (queryParameter.equals("unsupported_response_type")) {
            nPAuthListener.onResult(CODE_DAUM_UNSUPPORTED_RESPONSE_TYPE, "", null);
        } else if (queryParameter.equals("invalid_scope")) {
            nPAuthListener.onResult(CODE_DAUM_INVALID_SCOPE, "", null);
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void isConnect(Context context, NPAuthListener nPAuthListener) {
        if ((this.accessTokenStore != null ? this.accessTokenStore.getAccessToken() : "") != null) {
            nPAuthListener.onResult(0, null, null);
        } else {
            nPAuthListener.onResult(CODE_DAUM_NOT_CONNECTED, null, null);
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public boolean isConnected() {
        return (this.accessTokenStore != null ? this.accessTokenStore.getAccessToken() : "") != null;
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void login(Activity activity, NPAuthListener nPAuthListener) {
        this.mLoginListener = nPAuthListener;
        try {
            NPDaumLoginWebDialog nPDaumLoginWebDialogNewInstance = NPDaumLoginWebDialog.newInstance(activity, String.format(Constant.URL_AUTHORIZE_REQUEST, this.clientID, URLEncoder.encode(OAuthUtils.getAuthrizationCallbackUrl(this.clientID, null), Constant.DEFAULT_ENCODING)), this.clientID);
            nPDaumLoginWebDialogNewInstance.setResultListener(nPAuthListener);
            nPDaumLoginWebDialogNewInstance.showDialog(activity, NPDaumLoginWebDialog.TAG);
        } catch (UnsupportedEncodingException e) {
            nPAuthListener.onResult(CODE_DAUM_INVALID_AUTH_REQUEST, "invaild auth request", null);
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void logout(Context context, NPAuthListener nPAuthListener) {
        if (this.accessTokenStore != null) {
            this.accessTokenStore.removeToken(context);
            MobileOAuthLibrary.getInstance().expireAuthorization();
        }
        if (nPAuthListener != null) {
            nPAuthListener.onResult(0, null, null);
        }
    }
}
