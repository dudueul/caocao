package kr.co.nexon.npaccount.push.v1;

import android.content.Context;
import android.support.annotation.NonNull;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.tapjoy.TJAdUnitConstants;
import kr.co.nexon.npaccount.push.NXPPushPolicies;
import kr.co.nexon.npaccount.push.interfaces.NXPPushSetting;
import kr.co.nexon.npaccount.push.request.v1.NXToyOffPushRequest;
import kr.co.nexon.npaccount.push.request.v1.NXToyOnPushRequest;
import kr.co.nexon.npaccount.push.result.v1.NXToyOffPushResult;
import kr.co.nexon.npaccount.push.result.v1.NXToyOnPushResult;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPushSettingImplV1 implements NXPPushSetting {
    public static final int PUSH_AGREE = 1;
    public static final int PUSH_DISAGREE = 0;

    private void sendOffPushRequest(Context context, String str, final NPListener nPListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyOffPushRequest(str), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.push.v1.NXPPushSettingImplV1.2
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                NXToyOffPushResult nXToyOffPushResult = (NXToyOffPushResult) nXToyResult;
                if (nXToyOffPushResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    nXToyOffPushResult.result.status = "off";
                    NXToyCommonPreferenceController.getInstance().setPushAgree(0);
                } else {
                    nXToyOffPushResult.result.status = TJAdUnitConstants.String.SPLIT_VIEW_TRIGGER_ON;
                }
                if (nPListener != null) {
                    nPListener.onResult(nXToyOffPushResult);
                }
            }
        });
    }

    private void sendOnPushRequest(Context context, String str, final NPListener nPListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyOnPushRequest(str), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.push.v1.NXPPushSettingImplV1.1
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                NXToyOnPushResult nXToyOnPushResult = (NXToyOnPushResult) nXToyResult;
                if (nXToyOnPushResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    nXToyOnPushResult.result.status = TJAdUnitConstants.String.SPLIT_VIEW_TRIGGER_ON;
                    NXToyCommonPreferenceController.getInstance().setPushAgree(1);
                } else {
                    nXToyOnPushResult.result.status = "off";
                }
                if (nPListener != null) {
                    nPListener.onResult(nXToyOnPushResult);
                }
            }
        });
    }

    @Override // kr.co.nexon.npaccount.push.interfaces.NXPPushSetting
    public void getPolicy(Context context, NPListener nPListener) {
    }

    @Override // kr.co.nexon.npaccount.push.interfaces.NXPPushSetting
    public void setPolicy(Context context, @NonNull NXPPushPolicies nXPPushPolicies, String str, NPListener nPListener) {
        ToyLog.d("setPolicy, policies:" + nXPPushPolicies + " , pushKey:" + str);
        if (nXPPushPolicies.getAdPolicy().isEnabled()) {
            sendOnPushRequest(context, str, nPListener);
        } else {
            sendOffPushRequest(context, str, nPListener);
        }
    }
}
