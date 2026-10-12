package com.nexon.core.requestpostman.request;

import com.nexon.core.NXToyVersion;
import com.nexon.core.android.NXPApplicationConfigInterface;
import com.nexon.core.android.NXPApplicationConfigManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXByteUtil;
import com.nexon.core.util.NXCrypto.NXCrypto;
import com.nexon.core.util.NXDateUtil;
import com.nexon.core.util.NXJsonUtil;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyBoltRequest extends NXToyRequest {
    private static final String ACCEPT_COUNTRY_HEADER_FIELD_NAME = "acceptCountry";
    private static final String ACCEPT_LANGUAGE_HEADER_FIELD_NAME = "acceptLanguage";
    private static final String ANDROID_ADVERTISING_ID_HEADER_FIELD_NAME = "adid";
    private static final String APP_ID_HEADER_FIELD_NAME = "appId";
    private static final String APP_VERSION_CODE_HEADER_FILED_NAME = "appVersionNumber";
    private static final String CARRIER_NAME_HEADER_FIELD_NAME = "carrierName";
    private static final String CHARSET_HEADER_FIELD_NAME = "charset";
    private static final String CHARSET_HEADER_FIELD_VALUE = "utf-8";
    private static final String GAID_ENCODING_KEY = "NexonUser";
    private static final String GAID_HEADER_FIELD_NAME = "gaid";
    private static final String MCC_HEADER_FIELD_NAME = "mcc";
    private static final String MNC_HEADER_FIELD_NAME = "mnc";
    private static final String MODEL_HEADER_FIELD_NAME = "model";
    private static final String NPPARAMS_HEADER_FIELD_NAME = "npparams";
    private static final String NPSN_HEADER_FIELD_NAME = "npsn";
    private static final String NPTOKEN_HEADER_FIELD_NAME = "npToken";
    private static final String OS_TYPE_HEADER_FIELD_NAME = "os";
    private static final String SDK_VERSION_HEADER_FIELD_NAME = "sdkVer";
    private static final String SERVICE_ID_HEADER_FIELD_NAME = "svcID";
    private static final String TIME_ZONE_UTC_HEADER_FIELD_NAME = "timeZone";
    private static final String UUID2_HEADER_FIELD_NAME = "uuid2";
    private static final String UUID_HEADER_FIELD_NAME = "uuid";
    protected long cryptoKey;
    protected NXToyCryptoType decryptType;
    protected NXToyCryptoType encryptType;

    public NXToyBoltRequest() {
        super.setHttpURLWithoutPath(NXPApplicationConfigManager.getInstance().getServerURL(NXPApplicationConfigInterface.ServerType.Bolt).getUrl());
        this.encryptType = NXToyCryptoType.NONE;
        this.decryptType = NXToyCryptoType.NONE;
    }

    public long getCryptoKey() {
        return this.cryptoKey;
    }

    public NXToyCryptoType getDecryptType() {
        return this.decryptType;
    }

    public void setCryptoKey(long j) {
        this.cryptoKey = j;
    }

    public void setDecryptType(NXToyCryptoType nXToyCryptoType) {
        this.decryptType = nXToyCryptoType;
    }

    public void setEncryptType(NXToyCryptoType nXToyCryptoType) {
        this.encryptType = nXToyCryptoType;
    }

    protected void setMessageBody(Map<String, Object> map) {
        ToyLog.d("BodyArguments", "bodyArgs", map);
        long npsn = this.cryptoKey < 1 ? NXToySessionManager.getInstance().getSession().getNpsn() : this.cryptoKey;
        NXCrypto nXCrypto = new NXCrypto();
        new NXJsonUtil();
        super.setMessageBody(nXCrypto.encrypt(NXJsonUtil.toJsonString(map).getBytes(), this.encryptType, npsn));
    }

    protected void setRequestHeader() {
        setRequestHeader(null);
    }

    public void setRequestHeader(NXToySession nXToySession) {
        NXToySession session = nXToySession == null ? NXToySessionManager.getInstance().getSession() : nXToySession;
        HashMap map = new HashMap();
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        map.put("npsn", Long.toString(session.getNpsn()));
        HashMap map2 = new HashMap();
        map2.put(SDK_VERSION_HEADER_FIELD_NAME, NXToyVersion.VERSION);
        map2.put(OS_TYPE_HEADER_FIELD_NAME, nXToyCommonPreferenceController.getOs());
        map2.put(SERVICE_ID_HEADER_FIELD_NAME, NXToySessionManager.getInstance().getSession().getServiceId());
        map2.put(NPTOKEN_HEADER_FIELD_NAME, session.getNptoken());
        map2.put(APP_VERSION_CODE_HEADER_FILED_NAME, Integer.valueOf(nXToyCommonPreferenceController.getAppVersionCode()));
        map2.put(APP_ID_HEADER_FIELD_NAME, nXToyCommonPreferenceController.getAppId());
        map2.put(TIME_ZONE_UTC_HEADER_FIELD_NAME, NXDateUtil.getCurrentTimeZoneOffset());
        map2.put("adid", nXToyCommonPreferenceController.getAdvertisingId());
        map2.put(MNC_HEADER_FIELD_NAME, nXToyCommonPreferenceController.getMNC());
        map2.put(MCC_HEADER_FIELD_NAME, nXToyCommonPreferenceController.getMCC());
        map2.put(MODEL_HEADER_FIELD_NAME, nXToyCommonPreferenceController.getDeviceModel());
        map2.put(CARRIER_NAME_HEADER_FIELD_NAME, nXToyCommonPreferenceController.getCarrierName());
        String jsonString = NXJsonUtil.toJsonString(map2);
        ToyLog.d("NPParams header : " + jsonString);
        try {
            map.put(NPPARAMS_HEADER_FIELD_NAME, NXByteUtil.bytesToHexString(new NXCrypto().encrypt(jsonString.getBytes(), this.encryptType, session.getNpsn())));
        } catch (Exception e) {
            ToyLog.e("Catch exception. message is " + e.getMessage());
            e.printStackTrace();
        }
        map.put(ACCEPT_LANGUAGE_HEADER_FIELD_NAME, nXToyCommonPreferenceController.getLocale());
        map.put(ACCEPT_COUNTRY_HEADER_FIELD_NAME, nXToyCommonPreferenceController.getCountry());
        map.put("uuid", nXToyCommonPreferenceController.getUUID());
        map.put("uuid2", nXToyCommonPreferenceController.getUUID2());
        map.put("charset", "utf-8");
        String advertisingId = nXToyCommonPreferenceController.getAdvertisingId();
        if (!advertisingId.isEmpty()) {
            try {
                advertisingId = NXCrypto.encodeHmacSha256ToHexString(GAID_ENCODING_KEY, advertisingId);
            } catch (Exception e2) {
                ToyLog.e("Catch exception. message is " + e2.getMessage());
                e2.printStackTrace();
            }
        }
        map.put(GAID_HEADER_FIELD_NAME, advertisingId);
        ToyLog.d("Set message header : " + map);
        super.putMessageHeader(map);
    }
}
