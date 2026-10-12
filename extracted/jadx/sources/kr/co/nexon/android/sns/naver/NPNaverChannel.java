package kr.co.nexon.android.sns.naver;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.util.NXStringUtil;
import com.nexon.platform.auth.NXPProviderAuthenticationListener;
import com.nexon.platform.auth.model.NXPProviderAuthenticationInfo;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import kr.co.nexon.android.sns.NPAuthFriendsListener;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNaverChannel extends NPAuthPlugin {
    public static String SERVICE_NAME = "naver";
    private static final String TAG = "NPNaverChannel";
    private String clientID;
    private String clientName;
    private String clientSecret;
    private NPAuthListener mLoginListener;
    private OAuthLoginHandler mOAuthLoginHandler;
    private OAuthLogin mOAuthLoginInstance;

    private class DeleteTokenTask extends AsyncTask<Void, Void, Void> {
        private DeleteTokenTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (NPNaverChannel.this.mOAuthLoginInstance.logoutAndDeleteToken(NPNaverChannel.this.applicationContext)) {
                return null;
            }
            ToyLog.d("errorCode:" + NPNaverChannel.this.mOAuthLoginInstance.getLastErrorCode(NPNaverChannel.this.applicationContext));
            ToyLog.d("errorDesc:" + NPNaverChannel.this.mOAuthLoginInstance.getLastErrorDesc(NPNaverChannel.this.applicationContext));
            return null;
        }
    }

    public NPNaverChannel(Context context) throws Exception {
        super(context);
        this.mOAuthLoginHandler = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            this.clientID = applicationInfo.metaData.getString("NAVER_OAUTH_CLIENT_ID");
            this.clientSecret = applicationInfo.metaData.getString("NAVER_OAUTH_CLIENT_SECRET");
            this.clientName = applicationInfo.metaData.getString("NAVER_OAUTH_CLIENT_NAME");
            if (NXStringUtil.isNull(this.clientID) || NXStringUtil.isNull(this.clientSecret) || NXStringUtil.isNull(this.clientName)) {
                throw new Exception("naver clientID, clientSecret, clientName CHECK!");
            }
            init(context);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("naver clientID, clientSecret, clientName CHECK!");
        }
    }

    private void init(Context context) {
        makeOAuthLogin(context);
        makeOAuthLoginHandler();
    }

    private void makeOAuthLogin(Context context) {
        if (this.mOAuthLoginInstance == null) {
            this.mOAuthLoginInstance = OAuthLogin.getInstance();
            this.mOAuthLoginInstance.init(context, this.clientID, this.clientSecret, this.clientName);
            this.mOAuthLoginInstance.setMarketLinkWorking(false);
        }
    }

    private void makeOAuthLoginHandler() {
        if (this.mOAuthLoginHandler != null) {
            return;
        }
        this.mOAuthLoginHandler = new OAuthLoginHandler() { // from class: kr.co.nexon.android.sns.naver.NPNaverChannel.1
            @Override // com.nhn.android.naverlogin.OAuthLoginHandler
            public void run(boolean z) {
                if (!z) {
                    String code = NPNaverChannel.this.mOAuthLoginInstance.getLastErrorCode(NPNaverChannel.this.applicationContext).getCode();
                    String lastErrorDesc = NPNaverChannel.this.mOAuthLoginInstance.getLastErrorDesc(NPNaverChannel.this.applicationContext);
                    ToyLog.d("NAVER OAuthLoginFailed errorCode:" + code + ", errorDesc:" + lastErrorDesc);
                    if ("user_cancel".equals(code) || "access_denied".equals(code)) {
                        NPNaverChannel.this.mLoginListener.onResult(NXToyErrorCode.NAVERCHN_LOGIN_USER_CANCELED.getCode(), "naver errcode " + code + " errdesc" + lastErrorDesc, null);
                        return;
                    } else {
                        NPNaverChannel.this.mLoginListener.onResult(NXToyErrorCode.NAVERCHN_LOGIN_FAILED.getCode(), "naver errcode " + code + " errdesc" + lastErrorDesc, null);
                        return;
                    }
                }
                String accessToken = NPNaverChannel.this.mOAuthLoginInstance.getAccessToken(NPNaverChannel.this.applicationContext);
                NPNaverChannel.this.mOAuthLoginInstance.getRefreshToken(NPNaverChannel.this.applicationContext);
                NPNaverChannel.this.mOAuthLoginInstance.getExpiresAt(NPNaverChannel.this.applicationContext);
                NPNaverChannel.this.mOAuthLoginInstance.getTokenType(NPNaverChannel.this.applicationContext);
                Bundle bundle = new Bundle();
                bundle.putString(NPAuthPlugin.KEY_ID, "NAVER_CHANNEL_LOGIN");
                bundle.putString(NPAuthPlugin.KEY_ACCESSTOKEN, "Bearer " + accessToken);
                ToyLog.d("NAVER OAuthLoginSuccess");
                NPNaverChannel.this.mLoginListener.onResult(0, "", bundle);
            }
        };
    }

    private void naverChannelDeleteToken(Context context) {
        makeOAuthLogin(context);
        new DeleteTokenTask().execute(new Void[0]);
    }

    public String getAccessToken() {
        return this.mOAuthLoginInstance.getAccessToken(this.applicationContext);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getAccessToken(Context context, NPAuthListener nPAuthListener) {
        makeOAuthLogin(context);
        String accessToken = this.mOAuthLoginInstance.getAccessToken(context);
        if (accessToken == null) {
            nPAuthListener.onResult(NXToyErrorCode.NAVERCHN_GET_TOKEN_FAILED.getCode(), null, null);
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

    public void getNexonSN(Activity activity, NPAuthListener nPAuthListener) {
        login(activity, nPAuthListener);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getUserInfo(final Context context, final NPAuthListener nPAuthListener) {
        makeOAuthLogin(context);
        new Thread(new Runnable() { // from class: kr.co.nexon.android.sns.naver.NPNaverChannel.2
            @Override // java.lang.Runnable
            public void run() {
                String strRefreshAccessToken = NPNaverChannel.this.mOAuthLoginInstance.refreshAccessToken(context);
                if (strRefreshAccessToken == null) {
                    if (nPAuthListener != null) {
                        nPAuthListener.onResult(NXToyErrorCode.NAVERCHN_TOKEN_EXPIRED.getCode(), "" + NPNaverChannel.this.mOAuthLoginInstance.getLastErrorCode(context) + " " + NPNaverChannel.this.mOAuthLoginInstance.getLastErrorDesc(context), null);
                    }
                } else if (nPAuthListener != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(NPAuthPlugin.KEY_ACCESSTOKEN, "Bearer " + strRefreshAccessToken);
                    nPAuthListener.onResult(0, null, bundle);
                }
            }
        }).start();
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void isConnect(Context context, NPAuthListener nPAuthListener) {
        makeOAuthLogin(context);
        if (this.mOAuthLoginInstance.getAccessToken(context) != null) {
            nPAuthListener.onResult(0, null, null);
        } else {
            nPAuthListener.onResult(NXToyErrorCode.NAVERCHN_NOT_CONNECTED.getCode(), null, null);
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public boolean isConnected() {
        makeOAuthLogin(this.applicationContext);
        return this.mOAuthLoginInstance.getAccessToken(this.applicationContext) != null;
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void login(Activity activity, NPAuthListener nPAuthListener) {
        this.mLoginListener = nPAuthListener;
        init(activity);
        if (this.clientID == null || this.clientSecret == null || this.clientName == null) {
            nPAuthListener.onResult(NXToyErrorCode.NAVERCHN_NOT_EXIST_CLIENT_INFO_ERR.getCode(), "clientID = " + this.clientID + " clientSecret =" + this.clientSecret + " clientName = " + this.clientName, null);
        } else {
            logout(activity, null);
            this.mOAuthLoginInstance.startOauthLoginActivity(activity, this.mOAuthLoginHandler);
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void logout(Context context, NPAuthListener nPAuthListener) {
        makeOAuthLogin(context);
        this.mOAuthLoginInstance.logout(context);
        clearLastAuthenticationInfo();
        if (nPAuthListener != null) {
            nPAuthListener.onResult(0, null, null);
        }
    }

    public void signIn(Activity activity, final NXPProviderAuthenticationListener nXPProviderAuthenticationListener) {
        login(activity, new NPAuthListener() { // from class: kr.co.nexon.android.sns.naver.NPNaverChannel.3
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str, Bundle bundle) {
                if (i != NXToyErrorCode.SUCCESS.getCode()) {
                    if (nXPProviderAuthenticationListener != null) {
                        nXPProviderAuthenticationListener.onFailure(i, str);
                        return;
                    }
                    return;
                }
                String string = bundle.getString(NPAuthPlugin.KEY_ID);
                String string2 = bundle.getString(NPAuthPlugin.KEY_ACCESSTOKEN);
                String string3 = bundle.getString(NPAuthPlugin.KEY_EMAIL);
                ToyLog.d("naver id:" + string + " , pw:pw ,email:" + NXStringUtil.getMaskedEmail(string3));
                NXPProviderAuthenticationInfo nXPProviderAuthenticationInfo = new NXPProviderAuthenticationInfo(NXToyLoginType.LoginTypeNaver.getValue(), string, string2, string3, (String) null, string2);
                if (nXPProviderAuthenticationListener != null) {
                    nXPProviderAuthenticationListener.onSuccess(nXPProviderAuthenticationInfo);
                }
            }
        });
    }
}
