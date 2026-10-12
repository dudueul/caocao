package com.facebook.login;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.GraphResponse;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.tapjoy.TJAdUnitConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class LoginClient implements Parcelable {
    public static final Parcelable.Creator<LoginClient> CREATOR = new Parcelable.Creator<LoginClient>() { // from class: com.facebook.login.LoginClient.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LoginClient createFromParcel(Parcel parcel) {
            return new LoginClient(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LoginClient[] newArray(int i) {
            return new LoginClient[i];
        }
    };
    BackgroundProcessingListener backgroundProcessingListener;
    boolean checkedInternetPermission;
    int currentHandler;
    Fragment fragment;
    LoginMethodHandler[] handlersToTry;
    Map<String, String> loggingExtras;
    private LoginLogger loginLogger;
    OnCompletedListener onCompletedListener;
    Request pendingRequest;

    interface BackgroundProcessingListener {
        void onBackgroundProcessingStarted();

        void onBackgroundProcessingStopped();
    }

    public interface OnCompletedListener {
        void onCompleted(Result result);
    }

    public static class Request implements Parcelable {
        public static final Parcelable.Creator<Request> CREATOR = new Parcelable.Creator<Request>() { // from class: com.facebook.login.LoginClient.Request.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Request createFromParcel(Parcel parcel) {
                return new Request(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Request[] newArray(int i) {
                return new Request[i];
            }
        };
        private final String applicationId;
        private final String authId;
        private String authType;
        private final DefaultAudience defaultAudience;
        private String deviceRedirectUriString;
        private boolean isRerequest;
        private final LoginBehavior loginBehavior;
        private Set<String> permissions;

        private Request(Parcel parcel) {
            this.isRerequest = false;
            String string = parcel.readString();
            this.loginBehavior = string != null ? LoginBehavior.valueOf(string) : null;
            ArrayList arrayList = new ArrayList();
            parcel.readStringList(arrayList);
            this.permissions = new HashSet(arrayList);
            String string2 = parcel.readString();
            this.defaultAudience = string2 != null ? DefaultAudience.valueOf(string2) : null;
            this.applicationId = parcel.readString();
            this.authId = parcel.readString();
            this.isRerequest = parcel.readByte() != 0;
            this.deviceRedirectUriString = parcel.readString();
            this.authType = parcel.readString();
        }

        Request(LoginBehavior loginBehavior, Set<String> set, DefaultAudience defaultAudience, String str, String str2, String str3) {
            this.isRerequest = false;
            this.loginBehavior = loginBehavior;
            this.permissions = set == null ? new HashSet<>() : set;
            this.defaultAudience = defaultAudience;
            this.authType = str;
            this.applicationId = str2;
            this.authId = str3;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        String getApplicationId() {
            return this.applicationId;
        }

        String getAuthId() {
            return this.authId;
        }

        String getAuthType() {
            return this.authType;
        }

        DefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }

        String getDeviceRedirectUriString() {
            return this.deviceRedirectUriString;
        }

        LoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }

        Set<String> getPermissions() {
            return this.permissions;
        }

        boolean hasPublishPermission() {
            Iterator<String> it = this.permissions.iterator();
            while (it.hasNext()) {
                if (LoginManager.isPublishPermission(it.next())) {
                    return true;
                }
            }
            return false;
        }

        boolean isRerequest() {
            return this.isRerequest;
        }

        void setAuthType(String str) {
            this.authType = str;
        }

        void setDeviceRedirectUriString(String str) {
            this.deviceRedirectUriString = str;
        }

        void setPermissions(Set<String> set) {
            Validate.notNull(set, NativeProtocol.RESULT_ARGS_PERMISSIONS);
            this.permissions = set;
        }

        void setRerequest(boolean z) {
            this.isRerequest = z;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.loginBehavior != null ? this.loginBehavior.name() : null);
            parcel.writeStringList(new ArrayList(this.permissions));
            parcel.writeString(this.defaultAudience != null ? this.defaultAudience.name() : null);
            parcel.writeString(this.applicationId);
            parcel.writeString(this.authId);
            parcel.writeByte((byte) (this.isRerequest ? 1 : 0));
            parcel.writeString(this.deviceRedirectUriString);
            parcel.writeString(this.authType);
        }
    }

    public static class Result implements Parcelable {
        public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() { // from class: com.facebook.login.LoginClient.Result.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Result createFromParcel(Parcel parcel) {
                return new Result(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Result[] newArray(int i) {
                return new Result[i];
            }
        };
        final Code code;
        final String errorCode;
        final String errorMessage;
        public Map<String, String> loggingExtras;
        final Request request;
        final AccessToken token;

        enum Code {
            SUCCESS(GraphResponse.SUCCESS_KEY),
            CANCEL("cancel"),
            ERROR("error");

            private final String loggingValue;

            Code(String str) {
                this.loggingValue = str;
            }

            String getLoggingValue() {
                return this.loggingValue;
            }
        }

        private Result(Parcel parcel) {
            this.code = Code.valueOf(parcel.readString());
            this.token = (AccessToken) parcel.readParcelable(AccessToken.class.getClassLoader());
            this.errorMessage = parcel.readString();
            this.errorCode = parcel.readString();
            this.request = (Request) parcel.readParcelable(Request.class.getClassLoader());
            this.loggingExtras = Utility.readStringMapFromParcel(parcel);
        }

        Result(Request request, Code code, AccessToken accessToken, String str, String str2) {
            Validate.notNull(code, "code");
            this.request = request;
            this.token = accessToken;
            this.errorMessage = str;
            this.code = code;
            this.errorCode = str2;
        }

        static Result createCancelResult(Request request, String str) {
            return new Result(request, Code.CANCEL, null, str, null);
        }

        static Result createErrorResult(Request request, String str, String str2) {
            return createErrorResult(request, str, str2, null);
        }

        static Result createErrorResult(Request request, String str, String str2, String str3) {
            return new Result(request, Code.ERROR, null, TextUtils.join(": ", Utility.asListNoNulls(str, str2)), str3);
        }

        static Result createTokenResult(Request request, AccessToken accessToken) {
            return new Result(request, Code.SUCCESS, accessToken, null, null);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.code.name());
            parcel.writeParcelable(this.token, i);
            parcel.writeString(this.errorMessage);
            parcel.writeString(this.errorCode);
            parcel.writeParcelable(this.request, i);
            Utility.writeStringMapToParcel(parcel, this.loggingExtras);
        }
    }

    public LoginClient(Parcel parcel) {
        this.currentHandler = -1;
        Parcelable[] parcelableArray = parcel.readParcelableArray(LoginMethodHandler.class.getClassLoader());
        this.handlersToTry = new LoginMethodHandler[parcelableArray.length];
        for (int i = 0; i < parcelableArray.length; i++) {
            this.handlersToTry[i] = (LoginMethodHandler) parcelableArray[i];
            this.handlersToTry[i].setLoginClient(this);
        }
        this.currentHandler = parcel.readInt();
        this.pendingRequest = (Request) parcel.readParcelable(Request.class.getClassLoader());
        this.loggingExtras = Utility.readStringMapFromParcel(parcel);
    }

    public LoginClient(Fragment fragment) {
        this.currentHandler = -1;
        this.fragment = fragment;
    }

    private void addLoggingExtra(String str, String str2, boolean z) {
        if (this.loggingExtras == null) {
            this.loggingExtras = new HashMap();
        }
        if (this.loggingExtras.containsKey(str) && z) {
            str2 = this.loggingExtras.get(str) + "," + str2;
        }
        this.loggingExtras.put(str, str2);
    }

    private void completeWithFailure() {
        complete(Result.createErrorResult(this.pendingRequest, "Login attempt failed.", null));
    }

    static String getE2E() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("init", System.currentTimeMillis());
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }

    private LoginLogger getLogger() {
        if (this.loginLogger == null || !this.loginLogger.getApplicationId().equals(this.pendingRequest.getApplicationId())) {
            this.loginLogger = new LoginLogger(getActivity(), this.pendingRequest.getApplicationId());
        }
        return this.loginLogger;
    }

    public static int getLoginRequestCode() {
        return CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode();
    }

    private void logAuthorizationMethodComplete(String str, Result result, Map<String, String> map) {
        logAuthorizationMethodComplete(str, result.code.getLoggingValue(), result.errorMessage, result.errorCode, map);
    }

    private void logAuthorizationMethodComplete(String str, String str2, String str3, String str4, Map<String, String> map) {
        if (this.pendingRequest == null) {
            getLogger().logUnexpectedError("fb_mobile_login_method_complete", "Unexpected call to logCompleteLogin with null pendingAuthorizationRequest.", str);
        } else {
            getLogger().logAuthorizationMethodComplete(this.pendingRequest.getAuthId(), str, str2, str3, str4, map);
        }
    }

    private void notifyOnCompleteListener(Result result) {
        if (this.onCompletedListener != null) {
            this.onCompletedListener.onCompleted(result);
        }
    }

    void authorize(Request request) {
        if (request == null) {
            return;
        }
        if (this.pendingRequest != null) {
            throw new FacebookException("Attempted to authorize while a request is pending.");
        }
        if (!AccessToken.isCurrentAccessTokenActive() || checkInternetPermission()) {
            this.pendingRequest = request;
            this.handlersToTry = getHandlersToTry(request);
            tryNextHandler();
        }
    }

    void cancelCurrentHandler() {
        if (this.currentHandler >= 0) {
            getCurrentHandler().cancel();
        }
    }

    boolean checkInternetPermission() {
        if (this.checkedInternetPermission) {
            return true;
        }
        if (checkPermission("android.permission.INTERNET") == 0) {
            this.checkedInternetPermission = true;
            return true;
        }
        FragmentActivity activity = getActivity();
        complete(Result.createErrorResult(this.pendingRequest, activity.getString(com.facebook.common.R.string.com_facebook_internet_permission_error_title), activity.getString(com.facebook.common.R.string.com_facebook_internet_permission_error_message)));
        return false;
    }

    int checkPermission(String str) {
        return getActivity().checkCallingOrSelfPermission(str);
    }

    void complete(Result result) {
        LoginMethodHandler currentHandler = getCurrentHandler();
        if (currentHandler != null) {
            logAuthorizationMethodComplete(currentHandler.getNameForLogging(), result, currentHandler.methodLoggingExtras);
        }
        if (this.loggingExtras != null) {
            result.loggingExtras = this.loggingExtras;
        }
        this.handlersToTry = null;
        this.currentHandler = -1;
        this.pendingRequest = null;
        this.loggingExtras = null;
        notifyOnCompleteListener(result);
    }

    void completeAndValidate(Result result) {
        if (result.token == null || !AccessToken.isCurrentAccessTokenActive()) {
            complete(result);
        } else {
            validateSameFbidAndFinish(result);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    FragmentActivity getActivity() {
        return this.fragment.getActivity();
    }

    BackgroundProcessingListener getBackgroundProcessingListener() {
        return this.backgroundProcessingListener;
    }

    LoginMethodHandler getCurrentHandler() {
        if (this.currentHandler >= 0) {
            return this.handlersToTry[this.currentHandler];
        }
        return null;
    }

    public Fragment getFragment() {
        return this.fragment;
    }

    protected LoginMethodHandler[] getHandlersToTry(Request request) {
        ArrayList arrayList = new ArrayList();
        LoginBehavior loginBehavior = request.getLoginBehavior();
        if (loginBehavior.allowsGetTokenAuth()) {
            arrayList.add(new GetTokenLoginMethodHandler(this));
        }
        if (loginBehavior.allowsKatanaAuth()) {
            arrayList.add(new KatanaProxyLoginMethodHandler(this));
        }
        if (loginBehavior.allowsFacebookLiteAuth()) {
            arrayList.add(new FacebookLiteLoginMethodHandler(this));
        }
        if (loginBehavior.allowsCustomTabAuth()) {
            arrayList.add(new CustomTabLoginMethodHandler(this));
        }
        if (loginBehavior.allowsWebViewAuth()) {
            arrayList.add(new WebViewLoginMethodHandler(this));
        }
        if (loginBehavior.allowsDeviceAuth()) {
            arrayList.add(new DeviceAuthMethodHandler(this));
        }
        LoginMethodHandler[] loginMethodHandlerArr = new LoginMethodHandler[arrayList.size()];
        arrayList.toArray(loginMethodHandlerArr);
        return loginMethodHandlerArr;
    }

    boolean getInProgress() {
        return this.pendingRequest != null && this.currentHandler >= 0;
    }

    OnCompletedListener getOnCompletedListener() {
        return this.onCompletedListener;
    }

    public Request getPendingRequest() {
        return this.pendingRequest;
    }

    void notifyBackgroundProcessingStart() {
        if (this.backgroundProcessingListener != null) {
            this.backgroundProcessingListener.onBackgroundProcessingStarted();
        }
    }

    void notifyBackgroundProcessingStop() {
        if (this.backgroundProcessingListener != null) {
            this.backgroundProcessingListener.onBackgroundProcessingStopped();
        }
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (this.pendingRequest != null) {
            return getCurrentHandler().onActivityResult(i, i2, intent);
        }
        return false;
    }

    void setBackgroundProcessingListener(BackgroundProcessingListener backgroundProcessingListener) {
        this.backgroundProcessingListener = backgroundProcessingListener;
    }

    void setFragment(Fragment fragment) {
        if (this.fragment != null) {
            throw new FacebookException("Can't set fragment once it is already set.");
        }
        this.fragment = fragment;
    }

    void setOnCompletedListener(OnCompletedListener onCompletedListener) {
        this.onCompletedListener = onCompletedListener;
    }

    void startOrContinueAuth(Request request) {
        if (getInProgress()) {
            return;
        }
        authorize(request);
    }

    boolean tryCurrentHandler() {
        boolean zTryAuthorize = false;
        LoginMethodHandler currentHandler = getCurrentHandler();
        if (!currentHandler.needsInternetPermission() || checkInternetPermission()) {
            zTryAuthorize = currentHandler.tryAuthorize(this.pendingRequest);
            if (zTryAuthorize) {
                getLogger().logAuthorizationMethodStart(this.pendingRequest.getAuthId(), currentHandler.getNameForLogging());
            } else {
                getLogger().logAuthorizationMethodNotTried(this.pendingRequest.getAuthId(), currentHandler.getNameForLogging());
                addLoggingExtra("not_tried", currentHandler.getNameForLogging(), true);
            }
        } else {
            addLoggingExtra("no_internet_permission", "1", false);
        }
        return zTryAuthorize;
    }

    void tryNextHandler() {
        if (this.currentHandler >= 0) {
            logAuthorizationMethodComplete(getCurrentHandler().getNameForLogging(), TJAdUnitConstants.String.VIDEO_SKIPPED, null, null, getCurrentHandler().methodLoggingExtras);
        }
        while (this.handlersToTry != null && this.currentHandler < this.handlersToTry.length - 1) {
            this.currentHandler++;
            if (tryCurrentHandler()) {
                return;
            }
        }
        if (this.pendingRequest != null) {
            completeWithFailure();
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0030 A[Catch: Exception -> 0x003a, TRY_LEAVE, TryCatch #0 {Exception -> 0x003a, blocks: (B:9:0x0016, B:11:0x0024, B:12:0x002c, B:14:0x0030), top: B:18:0x0016 }] */
    void validateSameFbidAndFinish(Result result) {
        Result resultCreateErrorResult;
        if (result.token == null) {
            throw new FacebookException("Can't validate without a token");
        }
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        AccessToken accessToken = result.token;
        if (currentAccessToken == null || accessToken == null) {
            resultCreateErrorResult = Result.createErrorResult(this.pendingRequest, "User logged in as different Facebook user.", null);
        } else {
            try {
                if (currentAccessToken.getUserId().equals(accessToken.getUserId())) {
                    resultCreateErrorResult = Result.createTokenResult(this.pendingRequest, result.token);
                } else {
                    resultCreateErrorResult = Result.createErrorResult(this.pendingRequest, "User logged in as different Facebook user.", null);
                }
            } catch (Exception e) {
                complete(Result.createErrorResult(this.pendingRequest, "Caught exception", e.getMessage()));
                return;
            }
        }
        complete(resultCreateErrorResult);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelableArray(this.handlersToTry, i);
        parcel.writeInt(this.currentHandler);
        parcel.writeParcelable(this.pendingRequest, i);
        Utility.writeStringMapToParcel(parcel, this.loggingExtras);
    }
}
