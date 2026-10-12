package kr.co.nexon.toy.android.ui.baseplate;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.baseplate.view.NXPUserInfoView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPUserInfoDialog extends NPDialogBase {
    public static final String KEY_EMAIL = "email";
    public static final String KEY_LOGIN_TYPE = "loginType";
    public static final String KEY_TITLE = "title";
    public static final String TAG = "NPUserInfoDialog";
    private String email;
    private int loginType;
    private String title;

    private String getUserNameText(NXToyLoginType nXToyLoginType, String str) {
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(this.applicationContext);
        switch (nXToyLoginType) {
            case LoginTypeNXCom:
            case LoginTypeNXNet:
                return String.format(nXToyLocaleManager.getString(R.string.nplogin_login_text_msg_nxcom), str);
            case LoginTypeEmail:
                return String.format(nXToyLocaleManager.getString(R.string.nplogin_login_text_msg_email), str);
            case LoginTypeFaceBook:
                return String.format(nXToyLocaleManager.getString(R.string.nplogin_login_text_msg_facebook), str);
            case LoginTypeTwitter:
                return String.format(nXToyLocaleManager.getString(R.string.nplogin_login_text_msg_twitter), str);
            case LoginTypeGoogle:
                return String.format(nXToyLocaleManager.getString(R.string.nplogin_login_text_msg_google), str);
            case LoginTypeGuest:
                return nXToyLocaleManager.getString(R.string.nplogin_login_text_msg_guest);
            case LoginTypeNaverChannel:
            case LoginTypeNaver:
                return String.format(nXToyLocaleManager.getString(R.string.nplogin_login_text_msg_naver), str);
            case LoginTypeDaumChannel:
                return String.format(nXToyLocaleManager.getString(R.string.nplogin_login_text_msg_daum), str);
            case LoginTypeGameCenter:
                return String.format(nXToyLocaleManager.getString(R.string.nplogin_login_text_msg_gamecenter), str);
            case LoginTypeVKontakte:
                return String.format(nXToyLocaleManager.getString(R.string.nplogin_login_text_msg_vkontakte), str);
            default:
                return "";
        }
    }

    public static NPUserInfoDialog newInstance(Activity activity, String str, int i, String str2) {
        NPUserInfoDialog nPUserInfoDialog = new NPUserInfoDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("title", str);
        bundle.putInt(KEY_LOGIN_TYPE, i);
        bundle.putString("email", str2);
        nPUserInfoDialog.setArguments(bundle);
        return nPUserInfoDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        NXPUserInfoView nXPUserInfoView = (NXPUserInfoView) View.inflate(this.applicationContext, R.layout.nxp_user_info_view, null);
        this.loginType = getArguments().getInt(KEY_LOGIN_TYPE, 0);
        this.email = getArguments().getString("email", "");
        this.title = getArguments().getString("title", "");
        if (NXStringUtil.isNotNull(this.title)) {
            nXPUserInfoView.setTitle(this.title);
        } else {
            nXPUserInfoView.setTitle("Setting");
        }
        nXPUserInfoView.setLoginTypeIcon(this.loginType);
        String strTrim = getUserNameText(NXToyLoginType.convertIntLoginTypeToEnumLoginType(this.loginType), this.email).trim();
        SpannableString spannableString = new SpannableString(strTrim);
        int iIndexOf = strTrim.indexOf(this.email);
        spannableString.setSpan(new StyleSpan(1), iIndexOf, iIndexOf + this.email.length(), 0);
        nXPUserInfoView.setUserNameText(spannableString);
        nXPUserInfoView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPUserInfoDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPUserInfoDialog.this.dismiss();
            }
        });
        return nXPUserInfoView;
    }

    public void onCloseBtnClick(View view) {
        dismiss();
    }
}
