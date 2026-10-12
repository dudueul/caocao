package com.nexon.platform.auth.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import java.util.Arrays;
import java.util.HashMap;
import kr.co.nexon.toy.android.ui.baseplate.NPUserInfoDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPProviderAuthenticationInfo {
    private String email;
    private String fbBizToken;
    private String id;
    private int loginType;
    private String naverAccessToken;
    private String password;
    private char[] passwordChars;

    public NXPProviderAuthenticationInfo(int i, String str, String str2) {
        this(i, str, str2, (String) null);
    }

    public NXPProviderAuthenticationInfo(int i, String str, String str2, String str3) {
        this(i, str, str2, str3, (String) null);
    }

    public NXPProviderAuthenticationInfo(int i, String str, String str2, String str3, String str4) {
        this(i, str, str2, str3, str4, (String) null);
    }

    public NXPProviderAuthenticationInfo(int i, String str, String str2, String str3, String str4, String str5) {
        this.loginType = i;
        this.id = str;
        this.password = str2;
        this.email = str3;
        this.fbBizToken = str4;
        this.naverAccessToken = str5;
        this.passwordChars = null;
    }

    public NXPProviderAuthenticationInfo(int i, String str, char[] cArr) {
        this(i, str, cArr, (String) null);
    }

    public NXPProviderAuthenticationInfo(int i, String str, char[] cArr, String str2) {
        this(i, str, cArr, str2, (String) null);
    }

    public NXPProviderAuthenticationInfo(int i, String str, char[] cArr, String str2, String str3) {
        this(i, str, cArr, str2, str3, (String) null);
    }

    public NXPProviderAuthenticationInfo(int i, String str, char[] cArr, String str2, String str3, String str4) {
        this.loginType = i;
        this.id = str;
        this.passwordChars = cArr;
        this.email = str2;
        this.fbBizToken = str3;
        this.password = null;
        this.naverAccessToken = str4;
    }

    public void clearPasswordChars() {
        if (this.passwordChars != null) {
            Arrays.fill(this.passwordChars, (char) 0);
        }
    }

    @Nullable
    public String getEmail() {
        return this.email;
    }

    @Nullable
    public String getFbBizToken() {
        return this.fbBizToken;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    public int getLoginType() {
        return this.loginType;
    }

    @Nullable
    public String getNaverAccessToken() {
        return this.naverAccessToken;
    }

    @Nullable
    public String getPassword() {
        return this.password;
    }

    @Nullable
    public char[] getPasswordChars() {
        return this.passwordChars;
    }

    public String toString() {
        HashMap map = new HashMap();
        map.put(NPUserInfoDialog.KEY_LOGIN_TYPE, Integer.valueOf(this.loginType));
        map.put("id", this.id);
        map.put("pw", "hidden");
        map.put("email", NXStringUtil.getMaskedEmail(this.email));
        map.put("fbBizToken", this.fbBizToken);
        map.put("naverAccessToken", this.naverAccessToken);
        return NXJsonUtil.toJsonString(map);
    }
}
