package kr.co.nexon.npaccount.auth.request;

import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.core.util.NXByteUtil;
import com.nexon.core.util.NXCrypto.NXCrypto;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.nexonanalyticssdk.NxLogInfo;
import com.nexon.nexonanalyticssdk.NxSystemInfo;
import com.tapjoy.TapjoyConstants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kr.co.nexon.npaccount.auth.request.model.NXToySignInRequestOptionalBody;
import kr.co.nexon.npaccount.auth.result.NXToyLoginResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToySignInRequest extends NXToyBoltRequest {
    private static final String DEFAULT_PASSWD_BODY_ARGS_VALUE = "PASSWORD";
    private static final String NXCOM_CRYPTO_KEY = "NexonUser";
    private static final String PASSWD_BODY_ARGS_FIELD_NAME = "passwd";
    private HashMap<String, Object> bodyArguments;
    private byte[] passwd;

    private NXToySignInRequest(String str, String str2, String str3, String str4, int i, NXToySignInRequestOptionalBody nXToySignInRequestOptionalBody) {
        this.bodyArguments = new HashMap<>();
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/signIn.nx");
        this.encryptType = NXToyCryptoType.COMMON;
        this.decryptType = NXToyCryptoType.COMMON;
        super.setRequestHeader();
        HashMap map = new HashMap();
        map.put(NxLogInfo.KEY_NPSN, str);
        super.putMessageHeader(map);
        if (i != NXToyLoginType.LoginTypeGuest.getValue()) {
            this.bodyArguments.put("userID", str2);
        }
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        this.bodyArguments.put(NxSystemInfo.KEY_UUID, str3);
        this.bodyArguments.put(NxLogInfo.KEY_UUID2, str4);
        this.bodyArguments.put("memType", Integer.valueOf(i));
        this.bodyArguments.put("termsApiVer", Integer.valueOf(nXToyCommonPreferenceController.getTermsApiVer()));
        HashMap map2 = new HashMap();
        map2.put(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, nXToySignInRequestOptionalBody.getDeviceModelName());
        map2.put("name", nXToySignInRequestOptionalBody.getDeviceName());
        map2.put("email", nXToySignInRequestOptionalBody.getEmailId());
        map2.put("fbBizToken", nXToySignInRequestOptionalBody.getFbBizToken());
        map2.put("carrierName", nXToySignInRequestOptionalBody.getCarrierName());
        this.bodyArguments.put(NXToyTerm.TYPE_OPTIONAL, map2);
        super.setResultClass(NXToyLoginResult.class);
    }

    public NXToySignInRequest(String str, String str2, String str3, String str4, String str5, int i, NXToySignInRequestOptionalBody nXToySignInRequestOptionalBody) {
        this(str, str2, str4, str5, i, nXToySignInRequestOptionalBody);
        String strEncodeHmacSha256ToHexString = str3;
        if (i == NXToyLoginType.LoginTypeNXCom.getValue() && NXStringUtil.isValidEmail(str2)) {
            try {
                strEncodeHmacSha256ToHexString = NXCrypto.encodeHmacSha256ToHexString(NXCOM_CRYPTO_KEY, strEncodeHmacSha256ToHexString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.bodyArguments.put(PASSWD_BODY_ARGS_FIELD_NAME, strEncodeHmacSha256ToHexString);
        super.setMessageBody(this.bodyArguments);
    }

    public NXToySignInRequest(String str, String str2, byte[] bArr, String str3, String str4, int i, NXToySignInRequestOptionalBody nXToySignInRequestOptionalBody) {
        this(str, str2, str3, str4, i, nXToySignInRequestOptionalBody);
        this.passwd = bArr;
        String strEncodeHmacSha256ToHexString = "";
        if (i == NXToyLoginType.LoginTypeNXCom.getValue() && NXStringUtil.isValidEmail(str2)) {
            try {
                strEncodeHmacSha256ToHexString = NXCrypto.encodeHmacSha256ToHexString(NXCOM_CRYPTO_KEY, bArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (NXStringUtil.isNotNull(strEncodeHmacSha256ToHexString)) {
            this.bodyArguments.put(PASSWD_BODY_ARGS_FIELD_NAME, strEncodeHmacSha256ToHexString);
            super.setMessageBody(this.bodyArguments);
        } else {
            this.bodyArguments.put(PASSWD_BODY_ARGS_FIELD_NAME, DEFAULT_PASSWD_BODY_ARGS_VALUE);
            setMessageBody(this.bodyArguments);
        }
        this.bodyArguments = null;
    }

    @Override // com.nexon.core.requestpostman.request.NXToyBoltRequest
    protected void setMessageBody(Map<String, Object> map) {
        long npsn = this.cryptoKey < 1 ? NXToySessionManager.getInstance().getSession().getNpsn() : this.cryptoKey;
        byte[] bytes = DEFAULT_PASSWD_BODY_ARGS_VALUE.getBytes();
        new NXJsonUtil();
        byte[] bytes2 = NXJsonUtil.toJsonString(map).getBytes();
        byte[] bArrReplace = NXByteUtil.replace(bytes2, bytes, this.passwd);
        byte[] bArrEncrypt = new NXCrypto().encrypt(bArrReplace, this.encryptType, npsn);
        Arrays.fill(bytes, (byte) 0);
        Arrays.fill(bytes2, (byte) 0);
        Arrays.fill(bArrReplace, (byte) 0);
        super.setMessageBody(bArrEncrypt);
    }
}
