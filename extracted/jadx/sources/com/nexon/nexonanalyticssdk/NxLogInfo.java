package com.nexon.nexonanalyticssdk;

import android.content.Context;
import android.os.SystemClock;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
enum NxLogInfo {
    INSTANCE;

    public static final String KEY_ACCOUNTNO = "accountno";
    public static final String KEY_ADID = "adid";
    public static final String KEY_CLIENT_IP_ADDRESS = "ipaddress";
    public static final String KEY_COMMENT = "comment";
    public static final String KEY_CREATE_DATE = "createdate";
    public static final String KEY_CREATE_UP_TIME = "createUpTime";
    public static final String KEY_CURRENT_STEP = "currentstep";
    public static final String KEY_ERROR_CODE = "errorCode";
    public static final String KEY_FUNNEL_INFO = "funnelinfo";
    public static final String KEY_FUNNEL_STAGE_NAME = "funnelstagename";
    public static final String KEY_GAMECLIENT_INFO = "gameclientInfo";
    public static final String KEY_INITIALIZE_TYPE = "initialize";
    public static final String KEY_LOG_TYPE = "type";
    public static final String KEY_MID = "mid";
    public static final String KEY_MOBILE_FUNNEL = "Mobile_Funnel";
    public static final String KEY_NPSN = "npsn";
    public static final String KEY_NULL_TYPE = "null_type";
    public static final String KEY_PREV_STAGE_CODE = "prevStage";
    public static final String KEY_SEQUENCE_NO = "sequenceno";
    public static final String KEY_SERVICE_ID = "serviceid";
    public static final String KEY_SESSION_ID = "sessionid";
    public static final String KEY_STABILITY_TYPE = "stability";
    public static final String KEY_STAGE_CODE = "stage";
    public static final String KEY_TIME_SYNC = "timesync";
    public static final String KEY_UMK = "umk";
    public static final String KEY_UUID2 = "uuid2";
    public static final String KEY_VERSION = "version";
    public static final String ONLYONCE_PRE_TAG = "OncePerKeyLog_";
    public static final String VALUE_DEFAULT_CREATE_DATE = "YYYY-MM-DDTHH:MM:SS.SSSZ";
    public static final String VALUE_DEFAULT_TIME_SYNC = "XXXXX";
    public static final String VALUE_DEFAULT_TYPE = "log";
    public static final int VALUE_EXECUTION_STAGE = 4;
    public static final String VALUE_NEXON_ANALYTICS_SDK_VERSION = "2.1.3.0";
    public static final String VALUE_NXLOG_VERSION = "1.0.0";
    public static final int VALUE_STAGE_INVALID_CODE = -9999;
    private String adid;
    private String clientIPAddress;
    private long currentLogKey;
    private Map<String, Object> gameClientInfo;
    private long initUpTime;
    private String mid;
    private int prevStage = -3;
    private AtomicLong sequenceNo = null;
    private NxSharedPreference sharedPreference;
    private Map<String, Object> userInfo;
    private String uuid2;
    private static Set<String> onceTypeContainer = new HashSet();
    private static Set<String> funnelNameContainer = new HashSet();

    NxLogInfo() {
    }

    public static boolean checkAndSetFunnelName(String str) {
        return funnelNameContainer.add(str);
    }

    public static boolean checkAndSetOnlyOnceType(String str) {
        return onceTypeContainer.add(str);
    }

    public static NxLogInfo getInstance() {
        return INSTANCE;
    }

    public String getAdid() {
        return this.adid;
    }

    public String getClientIPAddress() {
        return this.clientIPAddress;
    }

    public long getCurrentLogKey() {
        return this.currentLogKey;
    }

    public long getCurrentTime() {
        return NxDatabase.getInstance().getBaseTime(this.currentLogKey) + (SystemClock.uptimeMillis() - this.initUpTime);
    }

    public Map<String, Object> getGameClientInfo() {
        return this.gameClientInfo;
    }

    public long getInitUpTime() {
        return this.initUpTime;
    }

    public String getMid() {
        return this.mid;
    }

    public int getPrevStage() {
        return this.prevStage;
    }

    public long getSequenceNo() {
        return this.sequenceNo.incrementAndGet();
    }

    public Map<String, Object> getUserInfo() {
        return this.userInfo;
    }

    public String getUuid2() {
        return this.uuid2;
    }

    public void initialize(Context context, Map<String, Object> map) {
        String strMidForDevice = midForDevice(context, (String) map.get(KEY_MID));
        map.put(KEY_MID, strMidForDevice);
        this.mid = strMidForDevice;
        this.gameClientInfo = map;
        this.uuid2 = NxUtils.loadMachineId(context);
        this.clientIPAddress = NxUtils.loadLocalIpV4Address();
        try {
            this.adid = AdvertisingIdClient.getAdvertisingIdInfo(context).getId();
        } catch (GooglePlayServicesNotAvailableException e) {
            NxLogcat.e("loadAppInfo GooglePlayServicesNotAvailableException : " + e.getMessage());
        } catch (GooglePlayServicesRepairableException e2) {
            NxLogcat.e("loadAppInfo GooglePlayServicesRepairableException : " + e2.getMessage());
        } catch (IOException e3) {
            NxLogcat.e("loadAppInfo IOException : " + e3.getMessage());
        }
        if (this.sequenceNo == null) {
            this.sequenceNo = new AtomicLong(0L);
        }
        this.sequenceNo.set(0L);
    }

    public String midForDevice(Context context, String str) {
        String strGenerateUUID;
        this.sharedPreference = new NxSharedPreference();
        String uuidInNxPreference = this.sharedPreference.getUuidInNxPreference(context);
        if (!uuidInNxPreference.equals(NxSharedPreference.IS_NOT_EXIST)) {
            return uuidInNxPreference;
        }
        if (str != null) {
            NxLogcat.d("UUID is not exist In preference!");
            strGenerateUUID = str;
        } else {
            strGenerateUUID = NxUtils.generateUUID();
        }
        this.sharedPreference.setDataInNxPreference(context, KEY_MID, strGenerateUUID);
        return strGenerateUUID;
    }

    public void setCurrentLogKey(long j) {
        this.currentLogKey = j;
    }

    public void setInitUpTime(long j) {
        this.initUpTime = j;
    }

    public void setIpAdress(String str) {
        this.clientIPAddress = str;
    }

    public void setPrevStage(int i) {
        this.prevStage = i;
    }

    public void setUserInfo(Map<String, Object> map) {
        this.userInfo = map;
    }
}
