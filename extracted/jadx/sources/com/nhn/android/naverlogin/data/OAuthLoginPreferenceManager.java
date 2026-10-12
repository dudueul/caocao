package com.nhn.android.naverlogin.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.naver.glink.android.sdk.login.neoid.c;
import com.nhn.android.naverlogin.OAuthLoginDefine;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class OAuthLoginPreferenceManager {
    private static final String OAUTH_LOGIN_PREF_NAME_PER_APP = "NaverOAuthLoginPreferenceData";
    private static final String TAG = "NaverLoginOAuth|OAuthLoginPreferenceManager";
    private static Context mContext = null;
    private static SharedPreferences mPref = null;

    protected enum PREF_KEY {
        ACCESS_TOKEN(c.a, String.class),
        REFRESH_TOKEN("REFRESH_TOKEN", String.class),
        EXPIRES_AT("EXPIRES_AT", Long.TYPE),
        TOKEN_TYPE("TOKEN_TYPE", String.class),
        CLIENT_ID("CLIENT_ID", String.class),
        CLIENT_SECRET("CLIENT_SECRET", String.class),
        CLIENT_NAME("CLIENT_NAME", String.class),
        CALLBACK_URL("CALLBACK_URL", String.class),
        LAST_ERROR_CODE("LAST_ERROR_CODE", String.class),
        LAST_ERROR_DESC("LAST_ERROR_DESC", String.class);

        private String mKey;
        private String mType;

        PREF_KEY(String str, Class cls) {
            this.mKey = str;
            this.mType = cls.getCanonicalName();
        }

        private boolean delSub(SharedPreferences sharedPreferences) {
            SharedPreferences.Editor editorEdit;
            if (sharedPreferences == null || (editorEdit = sharedPreferences.edit()) == null) {
                return false;
            }
            try {
                editorEdit.remove(this.mKey);
                return editorEdit.commit();
            } catch (Exception e) {
                if (!OAuthLoginDefine.DEVELOPER_VERSION) {
                    return false;
                }
                Log.e(OAuthLoginPreferenceManager.TAG, "Prefernce del() fail, key:" + this.mKey + ", mType:" + this.mType + "e:" + e.getMessage());
                return false;
            }
        }

        private Object getSub(SharedPreferences sharedPreferences) {
            Object objValueOf = null;
            try {
                if (this.mType.equals(Integer.TYPE.getCanonicalName())) {
                    objValueOf = Integer.valueOf(sharedPreferences.getInt(this.mKey, 0));
                } else if (this.mType.equals(Long.TYPE.getCanonicalName())) {
                    objValueOf = Long.valueOf(sharedPreferences.getLong(this.mKey, 0L));
                } else if (this.mType.equals(String.class.getCanonicalName())) {
                    objValueOf = sharedPreferences.getString(this.mKey, "");
                } else if (this.mType.equals(Boolean.TYPE.getCanonicalName())) {
                    objValueOf = Boolean.valueOf(sharedPreferences.getBoolean(this.mKey, true));
                }
            } catch (Exception e) {
                if (OAuthLoginDefine.DEVELOPER_VERSION) {
                    Log.e(OAuthLoginPreferenceManager.TAG, "get(), key:" + this.mKey + ", pref:" + (sharedPreferences == null ? "null" : "ok"));
                }
            }
            return objValueOf;
        }

        private boolean setSub(SharedPreferences sharedPreferences, Object obj) {
            SharedPreferences.Editor editorEdit;
            boolean zCommit = false;
            if (sharedPreferences == null || (editorEdit = sharedPreferences.edit()) == null) {
                return false;
            }
            try {
                if (this.mType.equals(Integer.TYPE.getCanonicalName())) {
                    editorEdit.putInt(this.mKey, ((Integer) obj).intValue());
                } else if (this.mType.equals(Long.TYPE.getCanonicalName())) {
                    editorEdit.putLong(this.mKey, ((Long) obj).longValue());
                } else if (this.mType.equals(String.class.getCanonicalName())) {
                    editorEdit.putString(this.mKey, (String) obj);
                } else if (this.mType.equals(Boolean.TYPE.getCanonicalName())) {
                    editorEdit.putBoolean(this.mKey, ((Boolean) obj).booleanValue());
                }
                zCommit = editorEdit.commit();
                return zCommit;
            } catch (Exception e) {
                if (!OAuthLoginDefine.DEVELOPER_VERSION) {
                    return zCommit;
                }
                Log.e(OAuthLoginPreferenceManager.TAG, "Prefernce Set() fail, key:" + this.mKey + ", mType:" + this.mType + "e:" + e.getMessage());
                return zCommit;
            }
        }

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static PREF_KEY[] valuesCustom() {
            PREF_KEY[] pref_keyArrValuesCustom = values();
            int length = pref_keyArrValuesCustom.length;
            PREF_KEY[] pref_keyArr = new PREF_KEY[length];
            System.arraycopy(pref_keyArrValuesCustom, 0, pref_keyArr, 0, length);
            return pref_keyArr;
        }

        public boolean del() {
            return delSub(OAuthLoginPreferenceManager.mPref);
        }

        public Object get() {
            try {
                return getSub(OAuthLoginPreferenceManager.mPref);
            } catch (Exception e) {
                if (OAuthLoginDefine.DEVELOPER_VERSION) {
                    Log.e(OAuthLoginPreferenceManager.TAG, "get() fail, e:" + e.getMessage());
                }
                return null;
            }
        }

        public String getValue() {
            return this.mKey;
        }

        public boolean set(Object obj) {
            SharedPreferences sharedPreferences = OAuthLoginPreferenceManager.mPref;
            boolean sub = false;
            for (int i = 0; !sub && i < 3; i++) {
                if (i > 0) {
                    Log.e(OAuthLoginPreferenceManager.TAG, "preference set() fail (cnt:" + i + ")");
                    try {
                        Thread.sleep(50L);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                sub = setSub(sharedPreferences, obj);
            }
            return sub;
        }
    }

    public OAuthLoginPreferenceManager(Context context) {
        mContext = context;
        if (mContext == null) {
            Log.e(TAG, "context is null!");
        } else if (mPref == null) {
            mPref = mContext.getSharedPreferences(OAUTH_LOGIN_PREF_NAME_PER_APP, 0);
        }
    }

    public String getAccessToken() {
        String str = (String) PREF_KEY.ACCESS_TOKEN.get();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if ((System.currentTimeMillis() / 1000) - getExpiresAt() < 0) {
            return str;
        }
        Log.i(TAG, "access token is expired.");
        return null;
    }

    public String getCallbackUrl() {
        return (String) PREF_KEY.CALLBACK_URL.get();
    }

    public String getClientId() {
        return (String) PREF_KEY.CLIENT_ID.get();
    }

    public String getClientName() {
        return (String) PREF_KEY.CLIENT_NAME.get();
    }

    public String getClientSecret() {
        return (String) PREF_KEY.CLIENT_SECRET.get();
    }

    public long getExpiresAt() {
        Long l = (Long) PREF_KEY.EXPIRES_AT.get();
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    public OAuthErrorCode getLastErrorCode() {
        return OAuthErrorCode.fromString((String) PREF_KEY.LAST_ERROR_CODE.get());
    }

    public String getLastErrorDesc() {
        return (String) PREF_KEY.LAST_ERROR_DESC.get();
    }

    public String getRefreshToken() {
        return (String) PREF_KEY.REFRESH_TOKEN.get();
    }

    public String getTokenType() {
        return (String) PREF_KEY.TOKEN_TYPE.get();
    }

    public void setAccessToken(String str) {
        PREF_KEY.ACCESS_TOKEN.set(str);
    }

    public void setCallbackUrl(String str) {
        PREF_KEY.CALLBACK_URL.set(str);
    }

    public void setClientId(String str) {
        PREF_KEY.CLIENT_ID.set(str);
    }

    public void setClientName(String str) {
        PREF_KEY.CLIENT_NAME.set(str);
    }

    public void setClientSecret(String str) {
        PREF_KEY.CLIENT_SECRET.set(str);
    }

    public void setExpiresAt(long j) {
        PREF_KEY.EXPIRES_AT.set(Long.valueOf(j));
    }

    public void setLastErrorCode(OAuthErrorCode oAuthErrorCode) {
        PREF_KEY.LAST_ERROR_CODE.set(oAuthErrorCode.getCode());
    }

    public void setLastErrorDesc(String str) {
        PREF_KEY.LAST_ERROR_DESC.set(str);
    }

    public void setRefreshToken(String str) {
        PREF_KEY.REFRESH_TOKEN.set(str);
    }

    public void setTokenType(String str) {
        PREF_KEY.TOKEN_TYPE.set(str);
    }
}
