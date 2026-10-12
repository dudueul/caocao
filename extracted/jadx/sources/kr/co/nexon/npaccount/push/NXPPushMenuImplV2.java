package kr.co.nexon.npaccount.push;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.npaccount.R;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.push.interfaces.NXPPushMenu;
import kr.co.nexon.npaccount.push.interfaces.NXPPushSetting;
import kr.co.nexon.toy.android.ui.baseplate.NXPAdInformationDialog;
import kr.co.nexon.toy.android.ui.baseplate.NXPPushSettingDialog;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPushMenuImplV2 implements NXPPushMenu {
    @Override // kr.co.nexon.npaccount.push.interfaces.NXPPushMenu
    public void showMenu(@NonNull Activity activity, @NonNull NXPPushSetting nXPPushSetting, NXPPushMenuOption nXPPushMenuOption, NPListener nPListener) {
        if (NXToySessionManager.getInstance().getSession().getType() == -1) {
            NXToyResult nXToyResult = new NXToyResult(NXToyErrorCode.TOYSERVER_AUTH_NOT_VALID.getCode(), "Not loggined user.", "", NXToyRequestTag.ShowPushMenu.getValue());
            if (nPListener != null) {
                nPListener.onResult(nXToyResult);
                return;
            }
            return;
        }
        if (nXPPushMenuOption == null) {
            nXPPushMenuOption = new NXPPushMenuOption();
            nXPPushMenuOption.setTitle(NXToyLocaleManager.getInstance(activity).getString(R.string.npres_advertisement_receiption_setting_title));
        }
        if (NXToyCommonPreferenceController.getInstance().getPolicyApiVer() == 1) {
            NXPPushSettingDialog nXPPushSettingDialogNewInstance = NXPPushSettingDialog.newInstance(activity, nXPPushMenuOption);
            nXPPushSettingDialogNewInstance.setResultListener(nPListener);
            nXPPushSettingDialogNewInstance.showDialog(activity, NXPPushSettingDialog.TAG);
        } else {
            NXPAdInformationDialog nXPAdInformationDialogNewInstance = NXPAdInformationDialog.newInstance(activity, nXPPushMenuOption);
            nXPAdInformationDialogNewInstance.setResultListener(nPListener);
            nXPAdInformationDialogNewInstance.showDialog(activity, NXPAdInformationDialog.TAG);
        }
    }
}
