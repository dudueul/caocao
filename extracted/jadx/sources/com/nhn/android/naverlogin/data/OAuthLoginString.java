package com.nhn.android.naverlogin.data;

import android.content.Context;
import com.nhn.android.naverlogin.util.DeviceAppInfo;
import java.lang.reflect.Field;
import java.util.Locale;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum OAuthLoginString {
    naveroauthlogin_string_token_invalid("naveroauthlogin_string_token_invalid", "간편로그인이 해제되어 재로그인이 필요합니다.", "Quick Sign-in account has been expired.", "因解除便捷登录，需要再次登录。 ", "快速登入已解除，須重新登入。"),
    naveroauthlogin_string_getting_token("naveroauthlogin_string_getting_token", "네이버 아이디로 로그인 중입니다.", "Sign in with NAVER", "正在使用NAVER ID登录。", "正在使用NAVER ID登入"),
    naveroauthlogin_string_group_id_not_available("naveroauthlogin_string_group_id_not_available", "단체아이디는 네이버 아이디로 로그인이 지원되지 않습니다. 개인아이디로 로그인 해 주세요.", "Group ID can't be used to 'Sign in with NAVER'. Please sign in as a normal account.", "群组ID不支持NAVER ID登录。请使用个人ID登录。 ", "群組帳號不支持NAVER ID登入，請使用個人帳號登入。"),
    naveroauthlogin_string_install_naverapp("naveroauthlogin_string_install_naverapp", "네이버 앱을 설치하면\n이용할 수 있는 서비스입니다.", "Please install Naver app to use this service.", "安装NAVER软件后，\n才可以使用此服务。", "安裝NAVER App\n即可使用。"),
    naveroauthlogin_string_update_naverapp("naveroauthlogin_string_update_naverapp", "네이버 앱 업데이트 후\n이용할 수 있는 서비스입니다.", "Please update your Naver app to use this service.", "更新NAVER软件后，\n才可以使用此服务。", "更新NAVER App\n即可使用。"),
    naveroauthlogin_string_network_state_not_available("naveroauthlogin_string_network_state_not_available", "네트워크에 접속할 수 없습니다. 네트워크 연결상태를 확인해 주세요.", "Network is not available. Please check your network connection and try again.", "网络有问题，无法登录。\n您要再试吗？", "網絡有問題，無法登入。\n您要再試嗎？"),
    naveroauthlogin_string_msg_update("naveroauthlogin_string_msg_update", "업데이트", "Update", "更新 ", "更新"),
    naveroauthlogin_string_msg_install("naveroauthlogin_string_msg_install", "설치", "Install", "安装 ", "安裝"),
    naveroauthlogin_string_msg_cancel("naveroauthlogin_string_msg_cancel", "취소", "Cancel", "取消", "取消"),
    naveroauthlogin_string_msg_retry("naveroauthlogin_string_retry", "재시도", "Retry", "再试", "再試"),
    naveroauthlogin_string_msg_confirm("naveroauthlogin_string_confirm", "확인", "OK", "确定", "確定"),
    naveroauthlogin_string_msg_naverapp_download_desc("naveroauthlogin_string_msg_naverapp_download_desc", "네이버 앱으로 더욱 간편하게 로그인!", "Get NAVER App and sign in faster", "通过NAVER App 便捷登录", "通過NAVER App 便捷登錄"),
    naveroauthlogin_string_msg_naverapp_download_link("naveroauthlogin_string_msg_naverapp_download_link", "앱 다운로드", "Download App", "下载APP", "下載APP");

    String mEnMsg;
    String mKoMsg;
    String mResourceCode;
    String mZhCnMsg;
    String mZhTwMsg;

    OAuthLoginString(String str, String str2, String str3, String str4, String str5) {
        this.mResourceCode = str;
        this.mKoMsg = str2;
        this.mEnMsg = str3;
        this.mZhCnMsg = str4;
        this.mZhTwMsg = str5;
    }

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static OAuthLoginString[] valuesCustom() {
        OAuthLoginString[] oAuthLoginStringArrValuesCustom = values();
        int length = oAuthLoginStringArrValuesCustom.length;
        OAuthLoginString[] oAuthLoginStringArr = new OAuthLoginString[length];
        System.arraycopy(oAuthLoginStringArrValuesCustom, 0, oAuthLoginStringArr, 0, length);
        return oAuthLoginStringArr;
    }

    public String getString(Context context) {
        try {
            Integer num = 0;
            Class<?> cls = Class.forName(String.valueOf(context.getPackageName()) + ".R$string");
            for (Field field : cls.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equals(this.mResourceCode)) {
                    num = (Integer) field.get(cls);
                }
            }
            return context.getResources().getString(num.intValue());
        } catch (Exception e) {
            return getStringInLib(context);
        }
    }

    public String getStringInLib(Context context) {
        try {
            if (DeviceAppInfo.getBaseInstance().isKorean(context)) {
                return this.mKoMsg;
            }
            try {
                Locale locale = context.getResources().getConfiguration().locale;
                String language = locale.getLanguage();
                String country = locale.getCountry();
                if ("zh".equalsIgnoreCase(language)) {
                    return "TW".equalsIgnoreCase(country) ? this.mZhTwMsg : this.mZhCnMsg;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return this.mEnMsg;
        } catch (Exception e2) {
            e2.printStackTrace();
            return this.mKoMsg;
        }
    }
}
