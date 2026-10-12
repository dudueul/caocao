package kr.co.nexon.android.sns;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.platform.auth.model.NXPProviderAuthenticationInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class NPAuthPlugin {
    private static final String AUTHENTICATION_INFO_SHARED_KEY = "nxp_authentication_info";
    public static final int CODE_INVITE_FAIL = 90203;
    public static final int CODE_NOT_SUPPORT = 90200;
    public static final int CODE_UNKNOWN_ERROR = 90202;
    public static final int CODE_USER_CANCEL = 90201;
    public static final int GENDER_FEMALE = 1;
    public static final int GENDER_MALE = 0;
    public static final int GENDER_NONE = 3;
    public static final int GENDER_OTHER = 2;
    public static final String KEY_ACCESSTOKEN = "KEY_ACCESSTOKEN";
    public static final String KEY_ACCOUNTNAME = "KEY_ACCOUNTNAME";
    public static final String KEY_ACH_CUR_STEP = "KEY_ACH_CUR_STEP";
    public static final String KEY_ACH_ID = "KEY_ACH_ID";
    public static final String KEY_ACH_LIST = "KEY_ACH_LIST";
    public static final String KEY_ACH_NAME = "KEY_ACH_NAME";
    public static final String KEY_ACH_STATE = "KEY_ACH_STATE";
    public static final String KEY_ACH_TOTAL_STEP = "KEY_ACH_TOTAL_STEP";
    public static final String KEY_ACH_TYPE = "KEY_ACH_TYPE";
    public static final String KEY_AGERANGE_MAX = "KEY_AGERANGE_MAX";
    public static final String KEY_AGERANGE_MIN = "KEY_AGERANGE_MIN";
    public static final String KEY_BIRTHDAY = "KEY_BIRTHDAY";
    public static final String KEY_EMAIL = "KEY_EMAIL";
    public static final String KEY_FB_BIZ_TOKEN = "KEY_FB_BIZ_TOKEN";
    public static final String KEY_FIRSTNAME = "KEY_FIRSTNAME";
    public static final String KEY_FRIENDS_IDS_JSON = "KEY_FRIENDS_IDS_JSON";
    public static final String KEY_FRIENDS_JSON = "KEY_FRIENDS_JSON";
    public static final String KEY_GENDER = "KEY_GENDER";
    public static final String KEY_HAS_NEXT = "KEY_HAS_NEXT";
    public static final String KEY_ID = "KEY_ID";
    public static final String KEY_LASTNAME = "KEY_LASTNAME";
    public static final String KEY_MIDDLENAME = "KEY_MIDDLENAME";
    public static final String KEY_NAME = "KEY_PERSON_NAME";
    public static final String KEY_PICTURE_URL = "KEY_PICTURE_URL";
    public static final String KEY_PLAYER_SCORE = "KEY_PLAYER_SCORE";
    public static final String KEY_PLAYER_STATS = "KEY_PLAYER_STATS";
    public static final String KEY_POST_ID = "KEY_POST_ID";
    public static final String KEY_SECRETTOKEN = "KEY_SECRETTOKEN";
    public static final String KEY_SNS_NAME = "KEY_SNS_NAME";
    public static final String KEY_TOY_RESULT = "toyresult";
    public static final String KEY_TOY_SESSION = "toysession";
    public static final String KEY_URL = "KEY_URL";
    protected Context applicationContext;
    protected String extraData;
    private SharedPreferences pref;

    public NPAuthPlugin(Context context) {
        this.applicationContext = context;
    }

    @Nullable
    private NXPProviderAuthenticationInfo getCachedAuthenticationInfo() {
        String string = getPref().getString(AUTHENTICATION_INFO_SHARED_KEY, "");
        if (NXStringUtil.isNull(string)) {
            return null;
        }
        return (NXPProviderAuthenticationInfo) NXJsonUtil.fromObject(string, NXPProviderAuthenticationInfo.class);
    }

    protected void cacheAuthenticationInfo(@NonNull NXPProviderAuthenticationInfo nXPProviderAuthenticationInfo) {
        getEditor().putString(AUTHENTICATION_INFO_SHARED_KEY, NXJsonUtil.toJsonString(nXPProviderAuthenticationInfo)).commit();
    }

    public void clearLastAuthenticationInfo() {
        getEditor().putString(AUTHENTICATION_INFO_SHARED_KEY, "").commit();
    }

    public abstract void getAccessToken(Context context, NPAuthListener nPAuthListener);

    protected SharedPreferences.Editor getEditor() {
        return getPref().edit();
    }

    public abstract void getFriends(Context context, boolean z, NPAuthFriendsListener nPAuthFriendsListener);

    public void getFriendsAll(final Context context, final NPAuthFriendsListener nPAuthFriendsListener) {
        final ArrayList arrayList = new ArrayList();
        getFriends(context, false, new NPAuthFriendsListener() { // from class: kr.co.nexon.android.sns.NPAuthPlugin.1
            @Override // kr.co.nexon.android.sns.NPAuthFriendsListener
            public void onResult(int i, String str, boolean z, List<NPAuthUser> list) {
                if (i != NXToyErrorCode.SUCCESS.getCode()) {
                    nPAuthFriendsListener.onResult(i, str, false, null);
                    return;
                }
                if (list != null && list.size() > 0) {
                    arrayList.addAll(list);
                }
                if (z) {
                    NPAuthPlugin.this.getFriends(context, true, this);
                } else {
                    nPAuthFriendsListener.onResult(0, "", false, arrayList);
                }
            }
        });
    }

    @Nullable
    public NXPProviderAuthenticationInfo getLastAuthenticationInfo() {
        return getCachedAuthenticationInfo();
    }

    protected SharedPreferences getPref() {
        if (this.pref == null) {
            synchronized (NPAuthPlugin.class) {
                if (this.pref == null) {
                    this.pref = this.applicationContext.getSharedPreferences("NPSNSPref", 0);
                }
            }
        }
        return this.pref;
    }

    public abstract String getServiceName();

    public abstract void getUserInfo(Context context, NPAuthListener nPAuthListener);

    public abstract void isConnect(Context context, NPAuthListener nPAuthListener);

    public abstract boolean isConnected();

    public abstract void login(Activity activity, NPAuthListener nPAuthListener);

    public abstract void logout(Context context, NPAuthListener nPAuthListener);

    public void setExtraData(String str) {
        this.extraData = str;
    }
}
