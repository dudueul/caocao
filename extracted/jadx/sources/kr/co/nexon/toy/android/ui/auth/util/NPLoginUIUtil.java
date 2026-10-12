package kr.co.nexon.toy.android.ui.auth.util;

import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.npaccount.R;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kr.co.nexon.npaccount.NXToyLocaleManager;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPLoginUIUtil {
    public static int getDisplayPriority(int i) {
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
                return 1;
            case LoginTypeGoogle:
                return 0;
            case LoginTypeTwitter:
                return 2;
            case LoginTypeNXCom:
                return 3;
            case LoginTypeNXNet:
                return 9;
            case LoginTypeNaver:
                return 10;
            case LoginTypeNaverChannel:
                return 4;
            case LoginTypeDaumChannel:
                return 6;
            case LoginTypeEmail:
                return 5;
            case LoginTypeGuest:
                return 9999;
            case LoginTypeGameCenter:
                return 9998;
            case LoginTypeVKontakte:
                return 11;
            default:
                return 100;
        }
    }

    public static int getDisplayPriorityForNXcom(int i) {
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
                return 1;
            case LoginTypeGoogle:
                return 2;
            case LoginTypeTwitter:
                return 3;
            case LoginTypeNXCom:
                return 0;
            case LoginTypeNXNet:
                return 9;
            case LoginTypeNaver:
                return 10;
            case LoginTypeNaverChannel:
                return 4;
            case LoginTypeDaumChannel:
                return 6;
            case LoginTypeEmail:
                return 5;
            case LoginTypeGuest:
                return 9999;
            case LoginTypeGameCenter:
                return 9998;
            case LoginTypeVKontakte:
                return 11;
            default:
                return 100;
        }
    }

    public static String getLoginButtonDisplayText(int i) {
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance();
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
                return nXToyLocaleManager.getString(R.string.nplogin_type_facebook_btn);
            case LoginTypeGoogle:
                return nXToyLocaleManager.getString(R.string.nplogin_type_gplus_btn);
            case LoginTypeTwitter:
                return nXToyLocaleManager.getString(R.string.nplogin_type_twitter_btn);
            case LoginTypeNXCom:
            case LoginTypeNXNet:
                return nXToyLocaleManager.getString(R.string.nplogin_type_nexon_btn);
            case LoginTypeNaver:
            case LoginTypeNaverChannel:
                return nXToyLocaleManager.getString(R.string.nplogin_type_naver_btn);
            case LoginTypeDaumChannel:
                return nXToyLocaleManager.getString(R.string.nplogin_type_daum_btn);
            case LoginTypeEmail:
                return nXToyLocaleManager.getString(R.string.nplogin_type_email_btn);
            case LoginTypeGuest:
                return nXToyLocaleManager.getString(R.string.nplogin_type_guest_btn);
            case LoginTypeGameCenter:
                return nXToyLocaleManager.getString(R.string.nplogin_type_gamecenter_btn2);
            case LoginTypeVKontakte:
                return nXToyLocaleManager.getString(R.string.nplogin_type_vkontakte_btn);
            case LoginTypeKakao:
                return nXToyLocaleManager.getString(R.string.nplogin_type_kakao_btn);
            case LoginTypeMapleId:
                return nXToyLocaleManager.getString(R.string.nplogin_type_mapleid_btn);
            case LoginTypePlayPark:
                return nXToyLocaleManager.getString(R.string.nplogin_type_playpark_btn);
            default:
                return "";
        }
    }

    public static int getLoginSelectorResourceId(int i) {
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
                return R.drawable.btn_facebook_signin_selector;
            case LoginTypeGoogle:
                return R.drawable.btn_google_signin_selector;
            case LoginTypeTwitter:
                return R.drawable.btn_twitter_signin_selector;
            case LoginTypeNXCom:
            case LoginTypeNXNet:
                return R.drawable.btn_nexon_signin_selector;
            case LoginTypeNaver:
            case LoginTypeNaverChannel:
                return R.drawable.btn_naver_signin_selector;
            case LoginTypeDaumChannel:
                return R.drawable.btn_daum_signin_selector;
            case LoginTypeEmail:
                return R.drawable.btn_email_signin_selector;
            case LoginTypeGuest:
                return R.drawable.btn_guest_signin_selector;
            case LoginTypeGameCenter:
                return R.drawable.btn_gamecenter_signin_selector;
            case LoginTypeVKontakte:
                return R.drawable.btn_vkontakte_signin_selector;
            default:
                return -1;
        }
    }

    public static String getNameFromLoginType(int i) {
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance();
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
                return nXToyLocaleManager.getString(R.string.nplogin_type_facebook_btn);
            case LoginTypeGoogle:
                return nXToyLocaleManager.getString(R.string.nplogin_type_gplus_btn);
            case LoginTypeTwitter:
                return nXToyLocaleManager.getString(R.string.nplogin_type_twitter_btn);
            case LoginTypeNXCom:
            case LoginTypeNXNet:
                return nXToyLocaleManager.getString(R.string.nplogin_type_nexon_btn);
            case LoginTypeNaver:
            case LoginTypeNaverChannel:
                return nXToyLocaleManager.getString(R.string.nplogin_type_naver_btn);
            case LoginTypeDaumChannel:
                return nXToyLocaleManager.getString(R.string.nplogin_type_daum_btn);
            case LoginTypeEmail:
                return nXToyLocaleManager.getString(R.string.nplogin_type_email_btn);
            case LoginTypeGuest:
                return nXToyLocaleManager.getString(R.string.nplogin_type_guest_btn);
            case LoginTypeGameCenter:
                return nXToyLocaleManager.getString(R.string.nplogin_type_gamecenter_btn);
            case LoginTypeVKontakte:
                return nXToyLocaleManager.getString(R.string.nplogin_type_vkontakte_btn);
            case LoginTypeKakao:
                return nXToyLocaleManager.getString(R.string.nplogin_type_kakao_btn);
            case LoginTypeMapleId:
                return nXToyLocaleManager.getString(R.string.nplogin_type_mapleid_btn);
            case LoginTypePlayPark:
                return nXToyLocaleManager.getString(R.string.nplogin_type_playpark_btn);
            default:
                return "";
        }
    }

    public static int getTPALoginSelectorResourceId(int i) {
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
                return R.drawable.btn_facebook_login_tpa_selector;
            case LoginTypeGoogle:
                return R.drawable.btn_google_login_tpa_selector;
            case LoginTypeTwitter:
            case LoginTypeNXCom:
            case LoginTypeNXNet:
            default:
                return -1;
            case LoginTypeNaver:
                return R.drawable.btn_naver_login_tpa_selector;
        }
    }

    public static int loginIconType(int i) {
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
                return R.drawable.btn_account_facebook;
            case LoginTypeGoogle:
                return R.drawable.btn_account_google;
            case LoginTypeTwitter:
                return R.drawable.btn_account_twitter;
            case LoginTypeNXCom:
            case LoginTypeNXNet:
                return R.drawable.btn_account_nexon;
            case LoginTypeNaver:
            case LoginTypeNaverChannel:
                return R.drawable.btn_account_naver;
            case LoginTypeDaumChannel:
                return R.drawable.btn_account_daum;
            case LoginTypeEmail:
                return R.drawable.btn_account_email;
            case LoginTypeGuest:
                return R.drawable.btn_account_guest;
            case LoginTypeGameCenter:
                return R.drawable.btn_account_playgame_white;
            case LoginTypeVKontakte:
                return R.drawable.btn_account_vkontakte;
            default:
                return -1;
        }
    }

    public static void sortDisplayPriority(final int i, List<Integer> list) {
        ToyLog.d("setMembershipList :" + list);
        if (list == null) {
            ToyLog.d("membershipList is null");
        } else if (list.size() >= 2) {
            final boolean zContains = list.contains(Integer.valueOf(NXToyLoginType.LoginTypeNXCom.getValue()));
            Collections.sort(list, new Comparator<Integer>() { // from class: kr.co.nexon.toy.android.ui.auth.util.NPLoginUIUtil.1
                @Override // java.util.Comparator
                public int compare(Integer num, Integer num2) {
                    return (zContains || i == NXToyLoginType.LoginTypeNXCom.getValue()) ? NPLoginUIUtil.getDisplayPriorityForNXcom(num.intValue()) - NPLoginUIUtil.getDisplayPriorityForNXcom(num2.intValue()) : NPLoginUIUtil.getDisplayPriority(num.intValue()) - NPLoginUIUtil.getDisplayPriority(num2.intValue());
                }
            });
        }
    }
}
