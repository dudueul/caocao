package kr.co.nexon.npaccount.auth.request.model;

import android.content.Context;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.util.NXStringUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToySignInRequestOptionalBody {
    private String carrierName;
    private String deviceModelName = NXToyCommonPreferenceController.getInstance().getDeviceModel();
    private String deviceName = "";
    private String emailId;
    private String fbBizToken;

    public NXToySignInRequestOptionalBody(Context context, String str, String str2) {
        if (NXStringUtil.isValidEmail(str)) {
            this.emailId = str;
        }
        if (NXStringUtil.isNotNull(str2)) {
            this.fbBizToken = str2;
        }
        this.carrierName = NXToyCommonPreferenceController.getInstance().getCarrierName();
    }

    public String getCarrierName() {
        return this.carrierName;
    }

    public String getDeviceModelName() {
        return this.deviceModelName;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public String getFbBizToken() {
        return this.fbBizToken;
    }
}
