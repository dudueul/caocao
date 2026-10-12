package kr.co.nexon.npaccount.push.v1;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.npaccount.R;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.push.NXPPushMenuOption;
import kr.co.nexon.npaccount.push.interfaces.NXPPushMenu;
import kr.co.nexon.npaccount.push.interfaces.NXPPushSetting;
import kr.co.nexon.toy.android.ui.baseplate.NPAdInformationDialog;
import kr.co.nexon.toy.android.ui.baseplate.NPPushSettingDialog;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPushMenuImplV1 implements NXPPushMenu {
    @Override // kr.co.nexon.npaccount.push.interfaces.NXPPushMenu
    public void showMenu(@NonNull Activity activity, @NonNull NXPPushSetting nXPPushSetting, NXPPushMenuOption nXPPushMenuOption, NPListener nPListener) {
        String string;
        String pushLabelName;
        String smsLabelName;
        if (NXToySessionManager.getInstance().getSession().getType() == -1) {
            NXToyResult nXToyResult = new NXToyResult(NXToyErrorCode.TOYSERVER_AUTH_NOT_VALID.getCode(), "Not loggined user.", "");
            if (nPListener != null) {
                nPListener.onResult(nXToyResult);
                return;
            }
            return;
        }
        if (nXPPushMenuOption != null) {
            string = nXPPushMenuOption.getTitle();
            pushLabelName = nXPPushMenuOption.getPushLabelName();
            smsLabelName = nXPPushMenuOption.getSmsLabelName();
            nXPPushMenuOption.getEmailLabelName();
        } else {
            string = NXToyLocaleManager.getInstance(activity).getString(R.string.npres_advertisement_receiption_setting_title);
            pushLabelName = "";
            smsLabelName = "";
        }
        if (NXToyCommonPreferenceController.getInstance().getPolicyApiVer() == 1) {
            NPPushSettingDialog nPPushSettingDialogNewInstance = NPPushSettingDialog.newInstance(activity, string, pushLabelName, smsLabelName);
            nPPushSettingDialogNewInstance.setResultListener(nPListener);
            nPPushSettingDialogNewInstance.showDialog(activity, NPPushSettingDialog.TAG);
        } else {
            NPAdInformationDialog nPAdInformationDialogNewInstance = NPAdInformationDialog.newInstance(activity, new NXPPushMenuOption(string, pushLabelName, smsLabelName));
            nPAdInformationDialogNewInstance.setResultListener(nPListener);
            nPAdInformationDialogNewInstance.showDialog(activity, NPAdInformationDialog.TAG);
        }
    }
}
