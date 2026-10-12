package net.daum.mf.oauth.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class AccessTokenStore {
    private String _accessToken;
    private String _clientId;
    private long _expireInTime;
    private final String PREF_OAUTH_TOKEN = "net.daum.mf.oauth";
    private final String KEY_TOKEN = "oauth.key_token";
    private final String KEY_EXPIRE_IN = "oauth.expire_in";

    public AccessTokenStore(Context context, String str) {
        this._clientId = str;
        SharedPreferences sharedPref = SharedPreferenceUtil.getSharedPref(context, "net.daum.mf.oauth");
        String string = sharedPref.getString("oauth.key_token", null);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        this._accessToken = getDecryptedData(string);
        this._expireInTime = sharedPref.getLong("oauth.expire_in", 0L);
    }

    public AccessTokenStore(Context context, String str, Uri uri) {
        if (uri == null) {
            throw new IllegalArgumentException("uri param is null");
        }
        this._clientId = str;
        this._accessToken = uri.getQueryParameter("access_token");
        String queryParameter = uri.getQueryParameter("expires_in");
        Logging.debug("accessToken : " + this._accessToken + ", expireIn : " + queryParameter);
        if (this._accessToken == null || queryParameter == null) {
            return;
        }
        setAccessToken(context, this._accessToken, queryParameter);
    }

    private String byteArrayToHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & 255) < 16) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Long.toString(bArr[i] & 255, 16));
        }
        return stringBuffer.toString();
    }

    private String decryptAes(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(getRawKey(str2.getBytes()), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, secretKeySpec);
            return new String(cipher.doFinal(hexToByteArray(str)));
        } catch (BadPaddingException e) {
            Log.e("DaumOAuthSDK", null, e);
            return null;
        } catch (Exception e2) {
            Log.e("DaumOAuthSDK", null, e2);
            return null;
        }
    }

    private String encryptAes(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(getRawKey(str2.getBytes()), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, secretKeySpec);
            return byteArrayToHex(cipher.doFinal(str.getBytes()));
        } catch (Exception e) {
            Log.e("DaumOAuthSDK", null, e);
            return null;
        }
    }

    private String getDecryptedData(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String strDecryptAes = decryptAes(str, this._clientId);
        if (strDecryptAes != null) {
            return strDecryptAes;
        }
        Log.e("DaumOAuthSDK", "can`t decrypt password.");
        return strDecryptAes;
    }

    private String getEncryptedData(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return encryptAes(str, this._clientId);
    }

    private byte[] getRawKey(byte[] bArr) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto");
            secureRandom.setSeed(bArr);
            keyGenerator.init(128, secureRandom);
            return keyGenerator.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException e) {
            Log.e("DaumOAuthSDK", null, e);
            return new byte[0];
        } catch (NoSuchProviderException e2) {
            Log.e("DaumOAuthSDK", null, e2);
            return new byte[0];
        }
    }

    private byte[] hexToByteArray(String str) {
        byte[] bArr = null;
        if (str != null && str.length() != 0) {
            bArr = new byte[str.length() / 2];
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) toInt(str.substring(i * 2, (i * 2) + 2), 16, 0);
            }
        }
        return bArr;
    }

    private synchronized void setAccessToken(Context context, String str, String str2) {
        this._accessToken = str;
        try {
            this._expireInTime = (1000 * Long.parseLong(str2)) + System.currentTimeMillis();
        } catch (NumberFormatException e) {
            this._expireInTime = 0L;
        }
        SharedPreferences.Editor editorEdit = SharedPreferenceUtil.getSharedPref(context, "net.daum.mf.oauth").edit();
        editorEdit.putString("oauth.key_token", getEncryptedData(this._accessToken));
        editorEdit.putLong("oauth.expire_in", this._expireInTime);
        editorEdit.apply();
    }

    private static int toInt(String str, int i, int i2) {
        try {
            return Integer.parseInt(str, i);
        } catch (Exception e) {
            Log.e("DaumOAuthSDK", null, e);
            return i2;
        }
    }

    public synchronized String getAccessToken() {
        return this._accessToken;
    }

    public synchronized boolean isExpired() {
        return System.currentTimeMillis() > this._expireInTime;
    }

    public synchronized void removeToken(Context context) {
        this._accessToken = null;
        this._expireInTime = 0L;
        SharedPreferences.Editor editorEdit = SharedPreferenceUtil.getSharedPref(context, "net.daum.mf.oauth").edit();
        editorEdit.remove("oauth.key_token");
        editorEdit.apply();
    }
}
