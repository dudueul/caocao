package kr.co.nexon.npaccount.push;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.nexon.core.locale.NXLocale;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.util.NXStringUtil;
import java.util.Locale;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.push.interfaces.NXPPushService;
import kr.co.nexon.npaccount.push.request.v2.NXToyAckRequest;
import kr.co.nexon.npaccount.push.request.v2.NXToyClickRequest;
import kr.co.nexon.npaccount.push.request.v2.NXToyTokenRequest;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPushServiceImplV2 implements NXPPushService {
    private static final String MESSAGE_ID = "google.message_id";
    private static final String PUSH_ID = "pushId";

    private String parseMessageId(Bundle bundle) {
        return bundle == null ? "" : bundle.getString(MESSAGE_ID, "");
    }

    private String parsePushId(Bundle bundle) {
        return bundle == null ? "" : bundle.getString(PUSH_ID, "");
    }

    @Override // kr.co.nexon.npaccount.push.interfaces.NXPPushService
    public void handlePushClickEvent(Context context, Bundle bundle) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyClickRequest(parsePushId(bundle), parseMessageId(bundle)), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.push.NXPPushServiceImplV2.2
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.d("handlePushClickEvent error:" + nXToyResult.errorCode + " , message:" + nXToyResult.errorText);
                } else {
                    ToyLog.d("handlePushClickEvent request was successful. response:" + nXToyResult);
                }
            }
        });
    }

    @Override // kr.co.nexon.npaccount.push.interfaces.NXPPushService
    public void handlePushReceiveEvent(Context context, Bundle bundle, boolean z) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyAckRequest(parsePushId(bundle), parseMessageId(bundle)), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.push.NXPPushServiceImplV2.3
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.d("handlePushReceiveEventEvent error:" + nXToyResult.errorCode + " , message:" + nXToyResult.errorText);
                } else {
                    ToyLog.d("handlePushReceiveEvent request was successful. response:" + nXToyResult);
                }
            }
        });
    }

    @Override // kr.co.nexon.npaccount.push.interfaces.NXPPushService
    public void registerPush(@NonNull Context context, String str) {
        String localeCode;
        String countryCode;
        if (NXStringUtil.isNull(str)) {
            ToyLog.d("registerPush failed token is empty.");
            return;
        }
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance();
        if (nXToyLocaleManager == null) {
            ToyLog.d("LocaleManager is null.");
            localeCode = NXLocale.getLocaleCode(Locale.getDefault().toString()).getLocaleCode();
            countryCode = NXLocale.getCountryCode(Locale.getDefault().getCountry()).getCountryCode();
        } else {
            localeCode = nXToyLocaleManager.getLocale().getLocaleCode();
            countryCode = nXToyLocaleManager.getCountry().getCountryCode();
        }
        NXToyRequestPostman.getInstance().postRequest(new NXToyTokenRequest(str, countryCode, localeCode), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.push.NXPPushServiceImplV2.1
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.d("registerPush failed  errorCode:" + nXToyResult.errorCode + " , errorMessage:" + nXToyResult.errorText);
                } else {
                    ToyLog.d("registerPush request was successful. response:" + nXToyResult);
                }
            }
        });
    }
}
