package kr.co.nexon.android.sns.google;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.util.NXStringUtil;
import com.nexon.platform.auth.NXPProviderAuthenticationListener;
import com.nexon.platform.auth.model.NXPProviderAuthenticationInfo;
import kr.co.nexon.android.sns.NPAuthFriendsListener;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.mdev.android.activity.NPActivityResultManager;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPGoogleSignIn extends NPAuthPlugin implements GoogleApiClient.ConnectionCallbacks {
    public static final int RC_SIGN_IN = 9010;
    public static final int REQUEST_PLAY_SERVICES_RESOLUTION = 9001;
    public static final int SERVICE_MISSING = 1;
    public static final String SERVICE_NAME = "google";
    public static final int SUCCESS = 0;
    private final GoogleApiClient mGoogleApiClient;

    public NPGoogleSignIn(Context context) {
        String string;
        super(context);
        try {
            string = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("com.google.oauth.server_web_client_id");
        } catch (PackageManager.NameNotFoundException e) {
            ToyLog.d("Google server web clientID CHECK! " + e.getMessage());
            string = null;
        }
        GoogleSignInOptions.Builder builderRequestProfile = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestId().requestProfile();
        if (NXStringUtil.isNotNull(string)) {
            builderRequestProfile.requestIdToken(string);
        }
        this.mGoogleApiClient = new GoogleApiClient.Builder(context).addConnectionCallbacks(this).addApi(Auth.GOOGLE_SIGN_IN_API, builderRequestProfile.build()).build();
        this.mGoogleApiClient.connect();
    }

    public static int isAvailable(Activity activity, boolean z) {
        int iIsGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(activity.getApplicationContext());
        if (GoogleApiAvailability.getInstance().isUserResolvableError(iIsGooglePlayServicesAvailable) && z) {
            showGoogleErrorDialog(activity, iIsGooglePlayServicesAvailable);
        }
        return iIsGooglePlayServicesAvailable;
    }

    public static void showGoogleErrorDialog(final Activity activity, final int i) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.android.sns.google.NPGoogleSignIn.1
            @Override // java.lang.Runnable
            public void run() {
                GoogleApiAvailability.getInstance().getErrorDialog(activity, i, 9001).show();
            }
        });
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getAccessToken(Context context, final NPAuthListener nPAuthListener) {
        if (this.mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.silentSignIn(this.mGoogleApiClient).setResultCallback(new ResultCallback<GoogleSignInResult>() { // from class: kr.co.nexon.android.sns.google.NPGoogleSignIn.5
                @Override // com.google.android.gms.common.api.ResultCallback
                public void onResult(GoogleSignInResult googleSignInResult) {
                    if (googleSignInResult.isSuccess()) {
                        ToyLog.d("isSuccess");
                        NPGoogleSignIn.this.handleSignInResult(googleSignInResult, nPAuthListener);
                    } else {
                        ToyLog.d("isFailed");
                        nPAuthListener.onResult(NXToyErrorCode.GOOGLE_NOT_CONNECTED.getCode(), "silent login failed", null);
                    }
                }
            });
        } else {
            nPAuthListener.onResult(NXToyErrorCode.GOOGLE_NOT_CONNECTED.getCode(), "api client not connected", null);
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getFriends(Context context, boolean z, NPAuthFriendsListener nPAuthFriendsListener) {
        nPAuthFriendsListener.onResult(NPAuthPlugin.CODE_NOT_SUPPORT, "not support", false, null);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getUserInfo(Context context, final NPAuthListener nPAuthListener) {
        if (this.mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.silentSignIn(this.mGoogleApiClient).setResultCallback(new ResultCallback<GoogleSignInResult>() { // from class: kr.co.nexon.android.sns.google.NPGoogleSignIn.4
                @Override // com.google.android.gms.common.api.ResultCallback
                public void onResult(GoogleSignInResult googleSignInResult) {
                    if (!googleSignInResult.isSuccess()) {
                        ToyLog.d("isFailed");
                        nPAuthListener.onResult(NXToyErrorCode.GOOGLE_GET_PERSON_FAILED.getCode(), "silent login failed", null);
                        return;
                    }
                    ToyLog.d("isSuccess");
                    GoogleSignInAccount signInAccount = googleSignInResult.getSignInAccount();
                    ToyLog.d("ppp " + signInAccount.getId());
                    Bundle bundle = new Bundle();
                    bundle.putString(NPAuthPlugin.KEY_ID, signInAccount.getId());
                    bundle.putString(NPAuthPlugin.KEY_ACCOUNTNAME, signInAccount.getDisplayName());
                    bundle.putString(NPAuthPlugin.KEY_EMAIL, signInAccount.getEmail());
                    bundle.putString(NPAuthPlugin.KEY_NAME, signInAccount.getDisplayName());
                    bundle.putString(NPAuthPlugin.KEY_PICTURE_URL, signInAccount.getPhotoUrl() != null ? signInAccount.getPhotoUrl().toString() : "");
                    nPAuthListener.onResult(0, "", bundle);
                }
            });
        } else {
            nPAuthListener.onResult(NXToyErrorCode.GOOGLE_NOT_CONNECTED.getCode(), "api client not connected", null);
        }
    }

    public void handleSignInResult(GoogleSignInResult googleSignInResult, NPAuthListener nPAuthListener) {
        ToyLog.d("handleSignInResult:" + googleSignInResult.toString());
        if (!googleSignInResult.isSuccess()) {
            if (nPAuthListener != null) {
                nPAuthListener.onResult(googleSignInResult.getStatus().getStatusCode(), googleSignInResult.getStatus().getStatusMessage(), null);
                return;
            }
            return;
        }
        GoogleSignInAccount signInAccount = googleSignInResult.getSignInAccount();
        Bundle bundle = new Bundle();
        bundle.putString(NPAuthPlugin.KEY_ID, signInAccount.getId());
        bundle.putString(NPAuthPlugin.KEY_SNS_NAME, SERVICE_NAME);
        String idToken = signInAccount.getIdToken();
        if (idToken == null) {
            idToken = "GoogleSignIn_Token";
        }
        bundle.putString(NPAuthPlugin.KEY_ACCESSTOKEN, idToken);
        bundle.putString(NPAuthPlugin.KEY_EMAIL, signInAccount.getEmail());
        if (nPAuthListener != null) {
            nPAuthListener.onResult(0, "", bundle);
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void isConnect(Context context, final NPAuthListener nPAuthListener) {
        if (this.mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.silentSignIn(this.mGoogleApiClient).setResultCallback(new ResultCallback<GoogleSignInResult>() { // from class: kr.co.nexon.android.sns.google.NPGoogleSignIn.6
                @Override // com.google.android.gms.common.api.ResultCallback
                public void onResult(GoogleSignInResult googleSignInResult) {
                    if (googleSignInResult.isSuccess()) {
                        ToyLog.d("isSuccess");
                        nPAuthListener.onResult(0, "", null);
                    } else {
                        ToyLog.d("isFailed");
                        nPAuthListener.onResult(NXToyErrorCode.GOOGLE_NOT_CONNECTED.getCode(), "silent login failed", null);
                    }
                }
            });
        } else {
            nPAuthListener.onResult(NXToyErrorCode.GOOGLE_NOT_CONNECTED.getCode(), "api client not connected", null);
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public boolean isConnected() {
        return this.mGoogleApiClient.isConnected();
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void login(Activity activity, final NPAuthListener nPAuthListener) {
        NPActivityResultManager.getInstance().registerActivityResultCallback(RC_SIGN_IN, new NPActivityResultManager.ActivityResultCallback() { // from class: kr.co.nexon.android.sns.google.NPGoogleSignIn.2
            @Override // kr.co.nexon.mdev.android.activity.NPActivityResultManager.ActivityResultCallback
            public void onActivityResult(int i, int i2, Intent intent) {
                if (i == 9010) {
                    if (i2 == -1) {
                        NPGoogleSignIn.this.handleSignInResult(Auth.GoogleSignInApi.getSignInResultFromIntent(intent), nPAuthListener);
                    } else if (i2 == 0) {
                        nPAuthListener.onResult(NXToyErrorCode.GOOGLE_LOGIN_USER_CANCEL.getCode(), "user cancel", null);
                    } else {
                        nPAuthListener.onResult(NXToyErrorCode.GOOGLE_LOGIN_FAIL.getCode(), "google error code:" + i2, null);
                    }
                }
            }
        });
        activity.startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(this.mGoogleApiClient), RC_SIGN_IN);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void logout(Context context, final NPAuthListener nPAuthListener) {
        clearLastAuthenticationInfo();
        if (this.mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(this.mGoogleApiClient).setResultCallback(new ResultCallback<Status>() { // from class: kr.co.nexon.android.sns.google.NPGoogleSignIn.3
                @Override // com.google.android.gms.common.api.ResultCallback
                public void onResult(Status status) {
                    if (nPAuthListener != null) {
                        nPAuthListener.onResult(0, "", null);
                    }
                }
            });
        } else if (nPAuthListener != null) {
            nPAuthListener.onResult(0, "", null);
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnected(Bundle bundle) {
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnectionSuspended(int i) {
    }

    public void signIn(Activity activity, final NXPProviderAuthenticationListener nXPProviderAuthenticationListener) {
        login(activity, new NPAuthListener() { // from class: kr.co.nexon.android.sns.google.NPGoogleSignIn.7
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
                ToyLog.d("google id:" + string + " , pw:pw ,email:" + NXStringUtil.getMaskedEmail(string3));
                NXPProviderAuthenticationInfo nXPProviderAuthenticationInfo = new NXPProviderAuthenticationInfo(NXToyLoginType.LoginTypeGoogle.getValue(), string, string2, string3);
                if (nXPProviderAuthenticationListener != null) {
                    nXPProviderAuthenticationListener.onSuccess(nXPProviderAuthenticationInfo);
                }
            }
        });
    }
}
