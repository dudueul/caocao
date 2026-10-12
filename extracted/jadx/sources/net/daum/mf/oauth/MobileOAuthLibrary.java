package net.daum.mf.oauth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;
import net.daum.mf.oauth.impl.AccessTokenStore;
import net.daum.mf.oauth.impl.Constant;
import net.daum.mf.oauth.impl.Logging;
import net.daum.mf.oauth.impl.OAuthRequestAsyncTask;
import net.daum.mf.oauth.impl.OAuthUtils;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class MobileOAuthLibrary {
    private static volatile MobileOAuthLibrary instance;
    private AccessTokenStore accessTokenStore;
    private String clientId;
    private Context context;
    private AtomicBoolean isInit = new AtomicBoolean(false);
    private OAuthListener oAuthListener;
    private String urlSchemeSuffix;

    public interface OAuthListener {
        void onAuthorizeFail(OAuthError.OAuthErrorCodes oAuthErrorCodes, String str);

        void onAuthorizeSuccess();

        void onRequestResourceFail(OAuthError.OAuthErrorCodes oAuthErrorCodes, String str);

        void onRequestResourceSuccess(String str);
    }

    private MobileOAuthLibrary() {
    }

    public static MobileOAuthLibrary getInstance() {
        if (instance == null) {
            synchronized (MobileOAuthLibrary.class) {
                if (instance == null) {
                    instance = new MobileOAuthLibrary();
                }
            }
        }
        return instance;
    }

    public void authorize(Activity activity, OAuthListener oAuthListener) {
        authorize(activity, oAuthListener, this.clientId, null);
    }

    public void authorize(Activity activity, OAuthListener oAuthListener, String str, String str2) {
        if (oAuthListener == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(OAuthError.OAuthErrorMessageInvalidAuthorizationRequest);
        }
        try {
            String str3 = String.format(Constant.URL_AUTHORIZE_REQUEST, str, URLEncoder.encode(OAuthUtils.getAuthrizationCallbackUrl(str, str2), Constant.DEFAULT_ENCODING));
            Logging.debug("authorize url : " + str3 + ", oAuthListener : " + oAuthListener + ", clientId : " + str + ", urlSchemeSuffix : " + str2);
            this.oAuthListener = oAuthListener;
            this.clientId = str;
            this.urlSchemeSuffix = str2;
            Intent intent = new Intent();
            try {
                activity.getPackageManager().getPackageInfo(Constant.PACKAGE_DAUM_APP, 1);
                intent.setPackage(Constant.PACKAGE_DAUM_APP);
            } catch (PackageManager.NameNotFoundException e) {
            }
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setData(Uri.parse(str3));
            activity.startActivity(intent);
        } catch (UnsupportedEncodingException e2) {
            oAuthListener.onAuthorizeFail(OAuthError.OAuthErrorCodes.OAuthErrorInvalidAuthorizationRequest, e2.getMessage());
        }
    }

    public void expireAuthorization() {
        if (this.accessTokenStore != null) {
            this.accessTokenStore.removeToken(this.context);
        }
    }

    public void handleUrlScheme(Uri uri) {
        Logging.debug("handleUrlScheme : " + uri.toString() + ", oAuthListener : " + this.oAuthListener);
        if (this.oAuthListener == null) {
            return;
        }
        if (OAuthUtils.isAuthrizationCallbackUrl(uri.getScheme(), this.clientId, this.urlSchemeSuffix)) {
            Uri uri2 = Uri.parse(uri.toString().replace("#", "?"));
            String queryParameter = uri2.getQueryParameter("error");
            String queryParameter2 = uri2.getQueryParameter(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
            boolean z = !TextUtils.isEmpty(queryParameter2);
            if (queryParameter == null) {
                this.accessTokenStore = new AccessTokenStore(this.context, this.clientId, uri2);
                this.oAuthListener.onAuthorizeSuccess();
            } else if (queryParameter.equals("invalid_request")) {
                OAuthListener oAuthListener = this.oAuthListener;
                OAuthError.OAuthErrorCodes oAuthErrorCodes = OAuthError.OAuthErrorCodes.OAuthErrorInvalidAuthorizationRequest;
                if (!z) {
                    queryParameter2 = OAuthError.OAuthErrorMessageInvalidAuthorizationRequest;
                }
                oAuthListener.onAuthorizeFail(oAuthErrorCodes, queryParameter2);
            } else if (queryParameter.equals("unauthorized_client")) {
                OAuthListener oAuthListener2 = this.oAuthListener;
                OAuthError.OAuthErrorCodes oAuthErrorCodes2 = OAuthError.OAuthErrorCodes.OAuthErrorUnauthorizedClient;
                if (!z) {
                    queryParameter2 = OAuthError.OAuthErrorMessageUnauthorizedClient;
                }
                oAuthListener2.onAuthorizeFail(oAuthErrorCodes2, queryParameter2);
            } else if (queryParameter.equals("access_denied")) {
                OAuthListener oAuthListener3 = this.oAuthListener;
                OAuthError.OAuthErrorCodes oAuthErrorCodes3 = OAuthError.OAuthErrorCodes.OAuthErrorAccessDenied;
                if (!z) {
                    queryParameter2 = OAuthError.OAuthErrorMessageAccessDenied;
                }
                oAuthListener3.onAuthorizeFail(oAuthErrorCodes3, queryParameter2);
            } else if (queryParameter.equals("unsupported_response_type")) {
                OAuthListener oAuthListener4 = this.oAuthListener;
                OAuthError.OAuthErrorCodes oAuthErrorCodes4 = OAuthError.OAuthErrorCodes.OAuthErrorUnsupportedResponseType;
                if (!z) {
                    queryParameter2 = OAuthError.OAuthErrorMessageUnsupportedResponseType;
                }
                oAuthListener4.onAuthorizeFail(oAuthErrorCodes4, queryParameter2);
            } else if (queryParameter.equals("invalid_scope")) {
                OAuthListener oAuthListener5 = this.oAuthListener;
                OAuthError.OAuthErrorCodes oAuthErrorCodes5 = OAuthError.OAuthErrorCodes.OAuthErrorInvalidScope;
                if (!z) {
                    queryParameter2 = OAuthError.OAuthErrorMessageInvalidScope;
                }
                oAuthListener5.onAuthorizeFail(oAuthErrorCodes5, queryParameter2);
            }
        } else {
            this.oAuthListener.onAuthorizeFail(OAuthError.OAuthErrorCodes.OAuthErrorInvalidAuthorizationRequest, OAuthError.OAuthErrorMessageInvalidAuthorizationRequest);
        }
        this.oAuthListener = null;
    }

    public void initialize(Context context, String str) {
        if (this.isInit.getAndSet(true)) {
            return;
        }
        this.context = context.getApplicationContext();
        this.accessTokenStore = new AccessTokenStore(context, str);
        this.clientId = str;
    }

    public boolean isAuthorized() {
        return (this.accessTokenStore == null || this.accessTokenStore.isExpired()) ? false : true;
    }

    public void requestResourceWithPath(Context context, OAuthListener oAuthListener, String str) {
        Logging.debug("requestResourceWithPath path : " + str + ", oAuthListener : " + oAuthListener);
        if (oAuthListener == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(OAuthError.OAuthErrorMessageInvalidResourceRequest);
        }
        if (isAuthorized()) {
            new OAuthRequestAsyncTask(context, oAuthListener, str, this.accessTokenStore.getAccessToken()).execute(new Void[0]);
        } else {
            oAuthListener.onRequestResourceFail(OAuthError.OAuthErrorCodes.OAuthErrorInvalidToken, OAuthError.OAuthErrorMessageInvalidToken);
        }
    }

    public void uninitialize() {
        this.context = null;
        this.accessTokenStore = null;
        this.isInit.set(false);
        this.oAuthListener = null;
    }
}
