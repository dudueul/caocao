package kr.co.nexon.android.sns.facebook;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.applinks.AppLinkData;
import com.facebook.internal.BundleJSONConverter;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.Sharer;
import com.facebook.share.model.GameRequestContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.GameRequestDialog;
import com.facebook.share.widget.ShareDialog;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.util.NXByteUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.platform.auth.NXPProviderAuthenticationListener;
import com.nexon.platform.auth.model.NXPProviderAuthenticationInfo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;
import kr.co.nexon.android.sns.NPAuthFriendsListener;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.NPAuthUser;
import kr.co.nexon.mdev.android.activity.NPActivityResultManager;
import kr.co.nexon.mdev.android.util.NXApplicationUtil;
import kr.co.nexon.toy.listener.NPListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPFacebook extends NPAuthPlugin {
    private static final String FACEBOOK_META_DATA = "FacebookAdditionalReadPermissions";
    private static final int REQ_FACEBOOK_INVITE = 65206;
    private static final int REQ_FACEBOOK_LOGIN = 64206;
    private static final int REQ_FACEBOOK_SHARE = 65208;
    private static final int REQ_FACEBOOK_SHARE_IMAGE = 65209;
    public static final String SERVICE_NAME = "facebook";
    private final CallbackManager callbackManager;
    private String friendsNextPageOffset;
    private AppEventsLogger logger;
    private ShareDialog shareDialog;

    public NPFacebook(Context context) {
        super(context);
        FacebookSdk.sdkInitialize(context);
        this.logger = AppEventsLogger.newLogger(context);
        this.callbackManager = CallbackManager.Factory.create();
        ToyLog.d("facebook sdk version : " + FacebookSdk.getSdkVersion());
    }

    private static boolean hasWritePerms() {
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        if (currentAccessToken == null) {
            return false;
        }
        ToyLog.d(currentAccessToken.getPermissions().toString());
        Iterator<String> it = currentAccessToken.getPermissions().iterator();
        while (it.hasNext()) {
            if (it.next().startsWith("publish")) {
                return true;
            }
        }
        return false;
    }

    private void registerWritePermission(Activity activity, final NPAuthListener nPAuthListener) {
        LoginManager.getInstance().registerCallback(this.callbackManager, new FacebookCallback<LoginResult>() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.5
            @Override // com.facebook.FacebookCallback
            public void onCancel() {
                nPAuthListener.onResult(NPAuthPlugin.CODE_USER_CANCEL, "user cancel", null);
            }

            @Override // com.facebook.FacebookCallback
            public void onError(FacebookException facebookException) {
                ToyLog.d(facebookException.toString());
                nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_GET_WRITE_PERM_FAILED.getCode(), facebookException.toString(), null);
            }

            @Override // com.facebook.FacebookCallback
            public void onSuccess(LoginResult loginResult) {
                nPAuthListener.onResult(0, null, null);
            }
        });
        LoginManager.getInstance().logInWithPublishPermissions(activity, Arrays.asList("publish_actions"));
    }

    @Deprecated
    public void activateApp() {
        AppEventsLogger.activateApp(this.applicationContext);
    }

    public void activateApp(Application application) {
        AppEventsLogger.activateApp(application);
    }

    public void addPermission(Activity activity, List<String> list, int i, final NPAuthListener nPAuthListener) {
        ToyLog.d("Facebook add permission. type:" + i + "  perms:" + list);
        if (!isConnected()) {
            if (nPAuthListener != null) {
                nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_NOT_CONNECT.getCode(), "not connected", null);
                return;
            }
            return;
        }
        NPActivityResultManager.getInstance().registerActivityResultCallback(REQ_FACEBOOK_LOGIN, new NPActivityResultManager.ActivityResultCallback() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.2
            @Override // kr.co.nexon.mdev.android.activity.NPActivityResultManager.ActivityResultCallback
            public void onActivityResult(int i2, int i3, Intent intent) {
                if (i2 < NPFacebook.REQ_FACEBOOK_LOGIN) {
                    return;
                }
                ToyLog.d("OnActivityResult " + i3);
                NPFacebook.this.callbackManager.onActivityResult(i2, i3, intent);
            }
        });
        LoginManager loginManager = LoginManager.getInstance();
        loginManager.registerCallback(this.callbackManager, new FacebookCallback<LoginResult>() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.3
            @Override // com.facebook.FacebookCallback
            public void onCancel() {
                ToyLog.d("fbAddPermission onCancel. listener:" + nPAuthListener);
                if (nPAuthListener != null) {
                    nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_PERM_REGISTER_CANCEL.getCode(), "user cancel", null);
                }
            }

            @Override // com.facebook.FacebookCallback
            public void onError(FacebookException facebookException) {
                ToyLog.d("fbAddPermission onError. listener:" + nPAuthListener);
                if (nPAuthListener != null) {
                    nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_PERM_REGISTER_ERROR.getCode(), facebookException.toString(), null);
                }
            }

            @Override // com.facebook.FacebookCallback
            public void onSuccess(LoginResult loginResult) {
                ToyLog.d("fbAddPermission onSuccess. listener:" + nPAuthListener);
                if (nPAuthListener != null) {
                    nPAuthListener.onResult(0, "", null);
                }
            }
        });
        if (i == 0) {
            loginManager.logInWithReadPermissions(activity, list);
        } else {
            loginManager.logInWithPublishPermissions(activity, list);
        }
    }

    public void checkPermissionGranted(final String str, final NPAuthListener nPAuthListener) {
        GraphRequest graphRequest = new GraphRequest(AccessToken.getCurrentAccessToken(), "me/permissions");
        graphRequest.setCallback(new GraphRequest.Callback() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.16
            @Override // com.facebook.GraphRequest.Callback
            public void onCompleted(GraphResponse graphResponse) {
                if (graphResponse.getError() != null) {
                    nPAuthListener.onResult(graphResponse.getError().getErrorCode(), graphResponse.getError().toString(), null);
                    return;
                }
                JSONObject jSONObject = null;
                try {
                    ToyLog.d("facebook permissions = " + graphResponse.getJSONObject().toString());
                    JSONArray jSONArray = graphResponse.getJSONObject().getJSONArray("data");
                    if (jSONArray == null || jSONArray.length() == 0) {
                        nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_PERM_CHECK_ERROR.getCode(), "data is null or empty", null);
                        return;
                    }
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        if (str.equals(jSONObject2.optString("permission"))) {
                            jSONObject = jSONObject2;
                            break;
                        }
                    }
                    if (jSONObject == null || !jSONObject.optString("status").equals("granted")) {
                        nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_PERM_NOT_GRANTED.getCode(), str + " not granted", null);
                    } else {
                        nPAuthListener.onResult(0, "", null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_PERM_CHECK_ERROR.getCode(), e.toString(), null);
                }
            }
        });
        if (Looper.myLooper() == Looper.getMainLooper()) {
            graphRequest.executeAsync();
        } else {
            graphRequest.executeAndWait();
        }
    }

    @Deprecated
    public void deactivateApp() {
        AppEventsLogger.deactivateApp(this.applicationContext);
    }

    public void fetchDeferredAppLinkData(@NonNull Context context, final NPAuthListener nPAuthListener) {
        AppLinkData.fetchDeferredAppLinkData(context, new AppLinkData.CompletionHandler() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.4
            @Override // com.facebook.applinks.AppLinkData.CompletionHandler
            public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                if (appLinkData == null || appLinkData.getTargetUri() == null) {
                    nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_FETCH_DEFERRED_APPLINKDATA_ERROR.getCode(), "target uri is null", null);
                    return;
                }
                String string = appLinkData.getTargetUri().toString();
                ToyLog.d("deferred applink data target uri:" + string);
                Bundle bundle = new Bundle();
                bundle.putString(NPAuthPlugin.KEY_URL, string);
                nPAuthListener.onResult(0, "", bundle);
            }
        });
    }

    public String getAccessToken(Context context) {
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        if (currentAccessToken == null) {
            return null;
        }
        ToyLog.d("fbPerms " + currentAccessToken.getPermissions());
        return currentAccessToken.getToken();
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getAccessToken(Context context, NPAuthListener nPAuthListener) {
        String accessToken = getAccessToken(context);
        if (accessToken == null) {
            nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_GET_ACCESSTOKEN_FAILED.getCode(), "get accesstoken failed", null);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(NPAuthPlugin.KEY_SNS_NAME, SERVICE_NAME);
        bundle.putString(NPAuthPlugin.KEY_ACCESSTOKEN, accessToken);
        nPAuthListener.onResult(0, "", bundle);
    }

    public void getBusinessToken(final Context context, final NPAuthListener nPAuthListener) {
        Bundle bundle = new Bundle();
        bundle.putString(GraphRequest.FIELDS_PARAM, "token_for_business");
        GraphRequest graphRequest = new GraphRequest(AccessToken.getCurrentAccessToken(), "me", bundle, HttpMethod.GET);
        graphRequest.setCallback(new GraphRequest.Callback() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.13
            @Override // com.facebook.GraphRequest.Callback
            public void onCompleted(GraphResponse graphResponse) {
                if (graphResponse.getError() == null && graphResponse.getJSONObject() != null) {
                    JSONObject jSONObject = graphResponse.getJSONObject();
                    ToyLog.d("graphObject = " + jSONObject.toString());
                    ToyLog.v("ACCESSTOKEN " + AccessToken.getCurrentAccessToken().getToken());
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(NPAuthPlugin.KEY_FB_BIZ_TOKEN, jSONObject.optString("token_for_business"));
                    if (nPAuthListener != null) {
                        nPAuthListener.onResult(0, "", bundle2);
                        return;
                    }
                    return;
                }
                FacebookRequestError error = graphResponse.getError();
                if (nPAuthListener != null) {
                    if (error.getErrorCode() == 190 && error.getSubErrorCode() == 434) {
                        NPFacebook.this.logout(context, null);
                        nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_WITHDRAWAL_USER.getCode(), error.getException() != null ? error.getException().toString() : "190:434 error", null);
                    } else if (error.getErrorCode() == 190) {
                        NPFacebook.this.logout(context, null);
                        nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_GRAPHAPI_FAIL.getCode(), error.getException() != null ? error.getException().toString() : "190 error", null);
                    } else if (error.getErrorCode() != 200) {
                        nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_UNKNOWN_ERR.getCode(), error.getException() != null ? error.getException().toString() : error.getErrorCode() + " error", null);
                    } else {
                        NPFacebook.this.logout(context, null);
                        nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_NOT_ASSOCIATED_BUSINESS.getCode(), error.getException() != null ? error.getException().toString() : "200 error", null);
                    }
                }
            }
        });
        if (Looper.myLooper() == Looper.getMainLooper()) {
            graphRequest.executeAsync();
        } else {
            graphRequest.executeAndWait();
        }
    }

    public byte[] getFBSession() {
        String string = getPref().getString("fbsession", "");
        if ("".equals(string)) {
            return null;
        }
        return NXByteUtil.HexStringToBytes(string);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getFriends(final Context context, boolean z, final NPAuthFriendsListener nPAuthFriendsListener) {
        this.applicationContext = context;
        if (!isConnected()) {
            nPAuthFriendsListener.onResult(NXToyErrorCode.FACEBOOK_GET_ACCESSTOKEN_FAILED.getCode(), "Get AccessToken failed. Facebook is not connected.", false, null);
            return;
        }
        ToyLog.d("getFriends " + z);
        if (!z) {
            this.friendsNextPageOffset = null;
        }
        checkPermissionGranted("user_friends", new NPAuthListener() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.17
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str, Bundle bundle) {
                if (i == 190) {
                    NPFacebook.this.logout(context, null);
                    nPAuthFriendsListener.onResult(NXToyErrorCode.FACEBOOK_GRAPHAPI_FAIL.getCode(), str, false, null);
                    return;
                }
                if (i > 0) {
                    nPAuthFriendsListener.onResult(i, str, false, null);
                    return;
                }
                ToyLog.d("check permission complete. ");
                GraphRequest graphRequestNewMyFriendsRequest = GraphRequest.newMyFriendsRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONArrayCallback() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.17.1
                    @Override // com.facebook.GraphRequest.GraphJSONArrayCallback
                    public void onCompleted(JSONArray jSONArray, GraphResponse graphResponse) {
                        if (graphResponse.getError() != null) {
                            FacebookRequestError error = graphResponse.getError();
                            if (nPAuthFriendsListener != null) {
                                if (error.getErrorCode() != 190) {
                                    nPAuthFriendsListener.onResult(NXToyErrorCode.FACEBOOK_UNKNOWN_ERR.getCode(), error.getException() != null ? error.getException().toString() : error.getErrorCode() + " error", false, null);
                                    return;
                                } else {
                                    NPFacebook.this.logout(context, null);
                                    nPAuthFriendsListener.onResult(NXToyErrorCode.FACEBOOK_GRAPHAPI_FAIL.getCode(), error.getException() != null ? error.getException().toString() : "190 error", false, null);
                                    return;
                                }
                            }
                            return;
                        }
                        JSONObject jSONObject = graphResponse.getJSONObject();
                        ToyLog.d("facebook get friend " + graphResponse.getJSONObject().toString());
                        ToyLog.d("List Size " + (jSONArray != null ? Integer.valueOf(jSONArray.length()) : "none"));
                        try {
                            String string = jSONObject.getJSONObject("paging").getString("next");
                            if (string != null) {
                                NPFacebook.this.friendsNextPageOffset = string.substring(string.indexOf("after=") + "after=".length());
                            }
                        } catch (JSONException e) {
                            NPFacebook.this.friendsNextPageOffset = null;
                        } catch (Throwable th) {
                            th.printStackTrace();
                            NPFacebook.this.friendsNextPageOffset = null;
                        }
                        ArrayList arrayList = new ArrayList();
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            try {
                                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                                ToyLog.d(jSONObject2.toString());
                                NPAuthUser nPAuthUser = new NPAuthUser();
                                nPAuthUser.memID = jSONObject2.optString("id");
                                nPAuthUser.birthDay = jSONObject2.optString("birthday");
                                nPAuthUser.name = jSONObject2.optString("name");
                                try {
                                    nPAuthUser.pictureURL = jSONObject2.getJSONObject("picture").getJSONObject("data").getString("url");
                                } catch (Exception e2) {
                                    nPAuthUser.pictureURL = "";
                                }
                                if (jSONObject2.has("gender")) {
                                    try {
                                        String string2 = jSONObject2.getString("gender");
                                        if (string2.equals("male")) {
                                            nPAuthUser.gender = 0;
                                        } else if (string2.equals("female")) {
                                            nPAuthUser.gender = 1;
                                        } else {
                                            nPAuthUser.gender = 2;
                                        }
                                    } catch (JSONException e3) {
                                        nPAuthUser.gender = 3;
                                    }
                                }
                                if (jSONObject2.has("age_range")) {
                                    try {
                                        if (jSONObject2.getJSONObject("age_range").has("min")) {
                                            nPAuthUser.agerangeMin = jSONObject2.getJSONObject("age_range").getInt("min");
                                        }
                                    } catch (JSONException e4) {
                                        nPAuthUser.agerangeMin = 0;
                                    }
                                    try {
                                        if (jSONObject2.getJSONObject("age_range").has("max")) {
                                            nPAuthUser.agerangeMax = jSONObject2.getJSONObject("age_range").getInt("max");
                                        }
                                    } catch (JSONException e5) {
                                        nPAuthUser.agerangeMax = 0;
                                    }
                                }
                                nPAuthUser.memID = jSONObject2.optString("id");
                                nPAuthUser.name = jSONObject2.optString("name");
                                nPAuthUser.firstName = jSONObject2.optString("first_name");
                                nPAuthUser.lastName = jSONObject2.optString("last_name");
                                arrayList.add(nPAuthUser);
                            } catch (JSONException e6) {
                            }
                        }
                        ToyLog.d(arrayList.toString());
                        nPAuthFriendsListener.onResult(0, "", NPFacebook.this.friendsNextPageOffset != null, arrayList);
                    }
                });
                Bundle bundle2 = new Bundle();
                bundle2.putString(GraphRequest.FIELDS_PARAM, "picture.height(100).width(100),id,first_name,last_name,birthday,email,link,name,gender,age_range");
                bundle2.putString("limit", "100");
                if (NPFacebook.this.friendsNextPageOffset != null) {
                    bundle2.putString("after", NPFacebook.this.friendsNextPageOffset);
                }
                graphRequestNewMyFriendsRequest.setParameters(bundle2);
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    graphRequestNewMyFriendsRequest.executeAsync();
                } else {
                    graphRequestNewMyFriendsRequest.executeAndWait();
                }
            }
        });
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getFriendsAll(Context context, NPAuthFriendsListener nPAuthFriendsListener) {
        this.friendsNextPageOffset = null;
        super.getFriendsAll(context, nPAuthFriendsListener);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getUserInfo(final Context context, final NPAuthListener nPAuthListener) {
        this.applicationContext = context;
        if (!isConnected()) {
            nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_GET_ACCESSTOKEN_FAILED.getCode(), "Get AccessToken failed. Facebook is not connected.", null);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(GraphRequest.FIELDS_PARAM, "picture.height(100).width(100),id,first_name,middle_name,last_name,birthday,email,link,name,gender,age_range");
        GraphRequest graphRequest = new GraphRequest(AccessToken.getCurrentAccessToken(), "me", bundle, HttpMethod.GET);
        graphRequest.setCallback(new GraphRequest.Callback() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.12
            @Override // com.facebook.GraphRequest.Callback
            public void onCompleted(GraphResponse graphResponse) {
                if (graphResponse.getError() != null || graphResponse.getJSONObject() == null) {
                    FacebookRequestError error = graphResponse.getError();
                    if (nPAuthListener != null) {
                        if (error.getErrorCode() == 190 && error.getSubErrorCode() == 434) {
                            NPFacebook.this.logout(context, null);
                            nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_WITHDRAWAL_USER.getCode(), error.getException() != null ? error.getException().toString() : "190:434 error", null);
                            return;
                        } else if (error.getErrorCode() != 190) {
                            nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_UNKNOWN_ERR.getCode(), error.getException() != null ? error.getException().toString() : error.getErrorCode() + " error", null);
                            return;
                        } else {
                            NPFacebook.this.logout(context, null);
                            nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_GRAPHAPI_FAIL.getCode(), error.getException() != null ? error.getException().toString() : "190 error", null);
                            return;
                        }
                    }
                    return;
                }
                JSONObject jSONObject = graphResponse.getJSONObject();
                ToyLog.d("graphObject = " + jSONObject.toString());
                ToyLog.v("ACCESSTOKEN " + AccessToken.getCurrentAccessToken().getToken());
                Bundle bundle2 = new Bundle();
                try {
                    bundle2.putString(NPAuthPlugin.KEY_PICTURE_URL, jSONObject.getJSONObject("picture").getJSONObject("data").getString("url"));
                } catch (Exception e) {
                    bundle2.putString(NPAuthPlugin.KEY_PICTURE_URL, "");
                }
                if (jSONObject.has("gender")) {
                    try {
                        String string = jSONObject.getString("gender");
                        if (string.equals("male")) {
                            bundle2.putInt(NPAuthPlugin.KEY_GENDER, 0);
                        } else if (string.equals("female")) {
                            bundle2.putInt(NPAuthPlugin.KEY_GENDER, 1);
                        } else {
                            bundle2.putInt(NPAuthPlugin.KEY_GENDER, 2);
                        }
                    } catch (JSONException e2) {
                        bundle2.putInt(NPAuthPlugin.KEY_GENDER, 3);
                    }
                }
                if (jSONObject.has("age_range")) {
                    try {
                        if (jSONObject.getJSONObject("age_range").has("min")) {
                            bundle2.putInt(NPAuthPlugin.KEY_AGERANGE_MIN, jSONObject.getJSONObject("age_range").getInt("min"));
                        }
                    } catch (JSONException e3) {
                        bundle2.putInt(NPAuthPlugin.KEY_AGERANGE_MIN, 0);
                    }
                    try {
                        if (jSONObject.getJSONObject("age_range").has("max")) {
                            bundle2.putInt(NPAuthPlugin.KEY_AGERANGE_MAX, jSONObject.getJSONObject("age_range").getInt("max"));
                        }
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                    }
                }
                bundle2.putString(NPAuthPlugin.KEY_EMAIL, jSONObject.optString("email"));
                bundle2.putString(NPAuthPlugin.KEY_ID, jSONObject.optString("id"));
                bundle2.putString(NPAuthPlugin.KEY_NAME, jSONObject.optString("name"));
                bundle2.putString(NPAuthPlugin.KEY_BIRTHDAY, jSONObject.optString("birthday"));
                bundle2.putString(NPAuthPlugin.KEY_FIRSTNAME, jSONObject.optString("first_name"));
                bundle2.putString(NPAuthPlugin.KEY_MIDDLENAME, jSONObject.optString("middle_name"));
                bundle2.putString(NPAuthPlugin.KEY_LASTNAME, jSONObject.optString("last_name"));
                bundle2.putString(NPAuthPlugin.KEY_ACCESSTOKEN, NPFacebook.this.getAccessToken(context));
                if (nPAuthListener != null) {
                    nPAuthListener.onResult(0, "", bundle2);
                }
            }
        });
        if (Looper.myLooper() == Looper.getMainLooper()) {
            graphRequest.executeAsync();
        } else {
            graphRequest.executeAndWait();
        }
    }

    public void invite(Activity activity, List<String> list, String str, String str2, final NPAuthListener nPAuthListener) {
        if (!isConnected()) {
            if (nPAuthListener != null) {
                nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_NOT_CONNECT.getCode(), "not connected", null);
                return;
            }
            return;
        }
        NPActivityResultManager.getInstance().registerActivityResultCallback(REQ_FACEBOOK_INVITE, new NPActivityResultManager.ActivityResultCallback() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.14
            @Override // kr.co.nexon.mdev.android.activity.NPActivityResultManager.ActivityResultCallback
            public void onActivityResult(int i, int i2, Intent intent) {
                if (i != NPFacebook.REQ_FACEBOOK_INVITE) {
                    return;
                }
                ToyLog.d("OnActivityResult " + i2);
                NPFacebook.this.callbackManager.onActivityResult(i, i2, intent);
            }
        });
        GameRequestDialog gameRequestDialog = new GameRequestDialog(activity);
        gameRequestDialog.registerCallback(this.callbackManager, new FacebookCallback<GameRequestDialog.Result>() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.15
            @Override // com.facebook.FacebookCallback
            public void onCancel() {
                nPAuthListener.onResult(NPAuthPlugin.CODE_USER_CANCEL, "user cancel", null);
            }

            @Override // com.facebook.FacebookCallback
            public void onError(FacebookException facebookException) {
                nPAuthListener.onResult(NPAuthPlugin.CODE_INVITE_FAIL, facebookException.toString(), null);
            }

            @Override // com.facebook.FacebookCallback
            public void onSuccess(GameRequestDialog.Result result) {
                List<String> requestRecipients = result.getRequestRecipients();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("recipients", new ArrayList<>(requestRecipients));
                nPAuthListener.onResult(0, "", bundle);
            }
        }, REQ_FACEBOOK_INVITE);
        GameRequestContent.Builder builder = new GameRequestContent.Builder();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        builder.setTo(sb.toString());
        builder.setMessage(str);
        builder.setObjectId(AccessToken.getCurrentAccessToken().getUserId());
        builder.setActionType(GameRequestContent.ActionType.SEND);
        gameRequestDialog.show(builder.build());
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void isConnect(Context context, NPAuthListener nPAuthListener) {
        this.applicationContext = context;
        if (AccessToken.getCurrentAccessToken() == null) {
            nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_NOT_CONNECT.getCode(), "not connected", null);
        } else {
            nPAuthListener.onResult(0, "", null);
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public boolean isConnected() {
        return AccessToken.getCurrentAccessToken() != null;
    }

    public Bundle jsonToBundle(String str) {
        try {
            try {
                return BundleJSONConverter.convertToBundle(new JSONObject(str));
            } catch (JSONException e) {
                ToyLog.d("convertToBundle exception :" + e);
                return null;
            }
        } catch (JSONException e2) {
            ToyLog.d("jsonToBundle exception :" + e2);
            return null;
        }
    }

    public void logEvent(String str) {
        try {
            this.logger.logEvent(str);
        } catch (Exception e) {
            ToyLog.d("facebook logEvent(String) exception :" + e);
        }
    }

    public void logEvent(String str, double d) {
        try {
            this.logger.logEvent(str, d);
        } catch (Exception e) {
            ToyLog.d("facebook logEvent(String, double) exception :" + e);
        }
    }

    public void logEvent(String str, double d, String str2) {
        try {
            this.logger.logEvent(str, d, jsonToBundle(str2));
        } catch (Exception e) {
            ToyLog.d("facebook logEvent(String, double, String) exception :" + e);
        }
    }

    public void logEvent(String str, String str2) {
        try {
            this.logger.logEvent(str, jsonToBundle(str2));
        } catch (Exception e) {
            ToyLog.d("facebook logEvent(String, String) exception :" + e);
        }
    }

    public void logPurchase(Double d, String str, String str2) {
        try {
            if (str2 == null) {
                this.logger.logPurchase(BigDecimal.valueOf(d.doubleValue()), Currency.getInstance(str));
            } else {
                this.logger.logPurchase(BigDecimal.valueOf(d.doubleValue()), Currency.getInstance(str), jsonToBundle(str2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void login(final Activity activity, final NPAuthListener nPAuthListener) {
        if (isConnected()) {
            Bundle bundle = new Bundle();
            AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            bundle.putString(NPAuthPlugin.KEY_ID, currentAccessToken.getUserId());
            bundle.putString(NPAuthPlugin.KEY_ACCESSTOKEN, currentAccessToken.getToken());
            nPAuthListener.onResult(0, "", bundle);
            return;
        }
        NPActivityResultManager.getInstance().registerActivityResultCallback(REQ_FACEBOOK_LOGIN, new NPActivityResultManager.ActivityResultCallback() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.10
            @Override // kr.co.nexon.mdev.android.activity.NPActivityResultManager.ActivityResultCallback
            public void onActivityResult(int i, int i2, Intent intent) {
                if (i < NPFacebook.REQ_FACEBOOK_LOGIN) {
                    return;
                }
                ToyLog.d("OnActivityResult " + i2);
                NPFacebook.this.callbackManager.onActivityResult(i, i2, intent);
            }
        });
        this.applicationContext = activity.getApplicationContext();
        ArrayList arrayList = new ArrayList();
        arrayList.add("email");
        int appMetaDataTypeInteger = NXApplicationUtil.getAppMetaDataTypeInteger(this.applicationContext, FACEBOOK_META_DATA);
        if (appMetaDataTypeInteger != 0) {
            try {
                for (String str : activity.getResources().getStringArray(appMetaDataTypeInteger)) {
                    arrayList.add(str);
                }
            } catch (Resources.NotFoundException e) {
                if (nPAuthListener != null) {
                    nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_INVALID_META_DATA.getCode(), "Please check Facebook meta-data setting in AndroidManifest.", null);
                }
                ToyLog.e(e.getMessage());
                return;
            }
        }
        LoginManager loginManager = LoginManager.getInstance();
        loginManager.registerCallback(this.callbackManager, new FacebookCallback<LoginResult>() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.11
            @Override // com.facebook.FacebookCallback
            public void onCancel() {
                ToyLog.d("facebookCallback<LoginResult> onCancel");
                nPAuthListener.onResult(NPAuthPlugin.CODE_USER_CANCEL, "user cancel", null);
            }

            @Override // com.facebook.FacebookCallback
            public void onError(FacebookException facebookException) {
                ToyLog.d("Facebook Exception" + (facebookException == null ? "null" : " " + facebookException.getClass().getName() + " " + facebookException.toString()));
                if (facebookException instanceof FacebookAuthorizationException) {
                    if (nPAuthListener != null) {
                        nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_INVALID_APPID_OR_HASHKEY.getCode(), facebookException.toString(), null);
                    }
                } else if (nPAuthListener != null) {
                    nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_UNKNOWN_ERR.getCode(), facebookException.toString(), null);
                }
            }

            @Override // com.facebook.FacebookCallback
            public void onSuccess(LoginResult loginResult) {
                final AccessToken currentAccessToken2 = AccessToken.getCurrentAccessToken();
                ToyLog.v("AccessToken :" + currentAccessToken2.getToken());
                ToyLog.d("UserID :" + currentAccessToken2.getUserId());
                NPFacebook.this.getUserInfo(activity, new NPAuthListener() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.11.1
                    @Override // kr.co.nexon.android.sns.NPAuthListener
                    public void onResult(int i, String str2, Bundle bundle2) {
                        Bundle bundle3 = new Bundle();
                        if (i == NXToyErrorCode.SUCCESS.getCode()) {
                            bundle3.putString(NPAuthPlugin.KEY_EMAIL, bundle2.getString(NPAuthPlugin.KEY_EMAIL));
                        }
                        bundle3.putString(NPAuthPlugin.KEY_ID, currentAccessToken2.getUserId());
                        bundle3.putString(NPAuthPlugin.KEY_ACCESSTOKEN, currentAccessToken2.getToken());
                        nPAuthListener.onResult(0, "", bundle3);
                    }
                });
            }
        });
        loginManager.logInWithReadPermissions(activity, arrayList);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void logout(Context context, NPAuthListener nPAuthListener) {
        this.applicationContext = context;
        LoginManager.getInstance().logOut();
        clearLastAuthenticationInfo();
        if (nPAuthListener != null) {
            nPAuthListener.onResult(0, "", null);
        }
    }

    public void setIsDebugEnabled(boolean z) {
        FacebookSdk.setIsDebugEnabled(z);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
    }

    public void share(Activity activity, Bundle bundle, String str, String str2, String str3) {
        share(activity, bundle, str, str2, str3, null, null);
    }

    public void share(Activity activity, Bundle bundle, String str, String str2, String str3, String str4, final NPAuthListener nPAuthListener) {
        NPActivityResultManager.getInstance().registerActivityResultCallback(REQ_FACEBOOK_SHARE, new NPActivityResultManager.ActivityResultCallback() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.6
            @Override // kr.co.nexon.mdev.android.activity.NPActivityResultManager.ActivityResultCallback
            public void onActivityResult(int i, int i2, Intent intent) {
                if (i != NPFacebook.REQ_FACEBOOK_SHARE) {
                    return;
                }
                ToyLog.d("OnActivityResult " + i2);
                NPFacebook.this.callbackManager.onActivityResult(i, i2, intent);
            }
        });
        this.shareDialog = new ShareDialog(activity);
        this.shareDialog.registerCallback(this.callbackManager, new FacebookCallback<Sharer.Result>() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.7
            @Override // com.facebook.FacebookCallback
            public void onCancel() {
                ToyLog.d("share onCancel():");
                if (nPAuthListener != null) {
                    nPAuthListener.onResult(NPAuthPlugin.CODE_USER_CANCEL, "user cancel", null);
                }
            }

            @Override // com.facebook.FacebookCallback
            public void onError(FacebookException facebookException) {
                ToyLog.d("share onError():" + facebookException.getMessage());
                nPAuthListener.onResult(NXToyErrorCode.FACEBOOK_SHARE_ERROR.getCode(), facebookException.toString(), null);
            }

            @Override // com.facebook.FacebookCallback
            public void onSuccess(Sharer.Result result) {
                ToyLog.d("share onSuccess:" + result.toString());
                if (nPAuthListener != null) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(NPAuthPlugin.KEY_POST_ID, result.getPostId());
                    nPAuthListener.onResult(0, "", bundle2);
                }
            }
        }, REQ_FACEBOOK_SHARE);
        this.shareDialog.show(new ShareLinkContent.Builder().setContentTitle(str).setContentDescription(str2).setContentUrl(NXStringUtil.isNotNull(str3) ? Uri.parse(str3) : null).setImageUrl(NXStringUtil.isNotNull(str4) ? Uri.parse(str4) : null).build());
    }

    public void shareImage(Activity activity, Bitmap bitmap, final NPListener nPListener) {
        NPActivityResultManager.getInstance().registerActivityResultCallback(REQ_FACEBOOK_SHARE_IMAGE, new NPActivityResultManager.ActivityResultCallback() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.8
            @Override // kr.co.nexon.mdev.android.activity.NPActivityResultManager.ActivityResultCallback
            public void onActivityResult(int i, int i2, Intent intent) {
                ToyLog.d("OnActivityResult " + i2);
                NPFacebook.this.callbackManager.onActivityResult(i, i2, intent);
            }
        });
        this.shareDialog = new ShareDialog(activity);
        this.shareDialog.registerCallback(this.callbackManager, new FacebookCallback<Sharer.Result>() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.9
            @Override // com.facebook.FacebookCallback
            public void onCancel() {
                ToyLog.d("share onCancel():");
                if (nPListener != null) {
                    nPListener.onResult(new NXToyResult(NPAuthPlugin.CODE_USER_CANCEL, "user cancel"));
                }
            }

            @Override // com.facebook.FacebookCallback
            public void onError(FacebookException facebookException) {
                ToyLog.d("share onError():" + facebookException.getMessage());
                nPListener.onResult(new NXToyResult(NXToyErrorCode.FACEBOOK_SHARE_IMAGE_ERROR.getCode(), facebookException.toString()));
            }

            @Override // com.facebook.FacebookCallback
            public void onSuccess(Sharer.Result result) {
                ToyLog.d("share onSuccess:" + result.toString());
                if (nPListener != null) {
                    nPListener.onResult(new NXToyResult(0, ""));
                }
            }
        }, REQ_FACEBOOK_SHARE_IMAGE);
        this.shareDialog.show(new SharePhotoContent.Builder().addPhoto(new SharePhoto.Builder().setBitmap(bitmap).build()).build());
    }

    public void signIn(final Activity activity, final NXPProviderAuthenticationListener nXPProviderAuthenticationListener) {
        login(activity, new NPAuthListener() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.1
            @Override // kr.co.nexon.android.sns.NPAuthListener
            public void onResult(int i, String str, Bundle bundle) {
                if (i != NXToyErrorCode.SUCCESS.getCode()) {
                    if (nXPProviderAuthenticationListener != null) {
                        nXPProviderAuthenticationListener.onFailure(i, str);
                    }
                } else {
                    final String string = bundle.getString(NPAuthPlugin.KEY_ID);
                    final String string2 = bundle.getString(NPAuthPlugin.KEY_ACCESSTOKEN);
                    final String string3 = bundle.getString(NPAuthPlugin.KEY_EMAIL);
                    NPFacebook.this.getBusinessToken(activity, new NPAuthListener() { // from class: kr.co.nexon.android.sns.facebook.NPFacebook.1.1
                        @Override // kr.co.nexon.android.sns.NPAuthListener
                        public void onResult(int i2, String str2, Bundle bundle2) {
                            if (i2 != NXToyErrorCode.SUCCESS.getCode() && nXPProviderAuthenticationListener != null) {
                                nXPProviderAuthenticationListener.onFailure(i2, str2);
                            }
                            String string4 = bundle2.getString(NPAuthPlugin.KEY_FB_BIZ_TOKEN);
                            ToyLog.d("facebook id:" + string + " , pw:pw ,email:" + NXStringUtil.getMaskedEmail(string3) + " fbBizToken:" + string4);
                            NXPProviderAuthenticationInfo nXPProviderAuthenticationInfo = new NXPProviderAuthenticationInfo(NXToyLoginType.LoginTypeFaceBook.getValue(), string, string2, string3, string4);
                            if (nXPProviderAuthenticationListener != null) {
                                nXPProviderAuthenticationListener.onSuccess(nXPProviderAuthenticationInfo);
                            }
                        }
                    });
                }
            }
        });
    }
}
