package kr.co.nexon.npaccount.stats;

import android.app.Application;
import android.content.Context;
import com.google.gson.Gson;
import com.nexon.core.android.NXPApplicationConfigManager;
import com.nexon.core.android.NXPApplicationLifeCycleManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXStringUtil;
import com.nexon.nexonanalyticssdk.INxLog;
import com.nexon.nexonanalyticssdk.NexonAnalytics;
import com.nexon.nexonanalyticssdk.NxGameClientInfo;
import com.nexon.nexonanalyticssdk.NxLogInfo;
import com.nexon.nexonanalyticssdk.NxUserInfo;
import java.util.Map;
import kr.co.nexon.mdev.android.util.NXAdsUtil;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXLog {
    private static NXPApplicationLifeCycleManager.ApplicationLifecycleCallbacks applicationLifecycleCallback = new NXPApplicationLifeCycleManager.ApplicationLifecycleCallbacks() { // from class: kr.co.nexon.npaccount.stats.NXLog.1
        @Override // com.nexon.core.android.NXPApplicationLifeCycleManager.ApplicationLifecycleCallbacks
        public void onCreate(Application application) {
            ToyLog.dd("onCreate(Application) in NXLog. application:" + application);
            Context applicationContext = application.getApplicationContext();
            NXLog.getInstance().initialize(applicationContext);
            NXAdsUtil.getAdvertisingIdAfterCaching(applicationContext, new NPListener() { // from class: kr.co.nexon.npaccount.stats.NXLog.1.1
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                }
            });
        }
    };
    private final NxUserInfo userInfo = new NxUserInfo();
    private boolean isInitialized = false;

    private static class Singleton {
        private static final NXLog instance = new NXLog();

        private Singleton() {
        }
    }

    static {
        NXPApplicationLifeCycleManager.getInstance().addApplicationLifecycleCallbacks(applicationLifecycleCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, Object> convertLogDataToMap(String str) {
        try {
            return (Map) new Gson().fromJson(str, Map.class);
        } catch (Exception e) {
            ToyLog.d("NXLog convertLogDataToMap logdata is not Json String.", "logData", str);
            return null;
        }
    }

    public static NXLog getInstance() {
        return Singleton.instance;
    }

    private void updateUserInfo() {
        NXToySession session = NXToySessionManager.getInstance().getSession();
        if (session == null) {
            ToyLog.d("In updateUserInfo, session is null");
            return;
        }
        long npsn = session.getNpsn();
        if (this.userInfo.npsn.longValue() != npsn) {
            this.userInfo.npsn = Long.valueOf(npsn);
            this.userInfo.umk = session.getUMKey();
            ToyLog.dd("userInfo.npsn:" + this.userInfo.npsn + " , userInfo.umk:" + this.userInfo.umk);
            NexonAnalytics.getInstance().setUserInfo(this.userInfo);
        }
    }

    public boolean enqueueErrorLog(int i, String str) {
        if (!this.isInitialized) {
            ToyLog.d("NXLog : NXlog need to init. send fail.");
            return false;
        }
        ToyLog.d("NXLog enqueue ErrorLog.", NxLogInfo.KEY_ERROR_CODE, Integer.valueOf(i), NxLogInfo.KEY_COMMENT, str);
        updateUserInfo();
        return NexonAnalytics.getInstance().enqueueErrorLog(i, str);
    }

    public boolean enqueueLog(String str, String str2) {
        if (!this.isInitialized) {
            ToyLog.d("NXLog : NXlog need to init. send fail.");
            return false;
        }
        ToyLog.d("NXLog enqueue Log.", "type", str, "logData", str2);
        updateUserInfo();
        Map<String, Object> mapConvertLogDataToMap = convertLogDataToMap(str2);
        if (mapConvertLogDataToMap != null) {
            return NexonAnalytics.getInstance().enqueueLog(str, mapConvertLogDataToMap);
        }
        return false;
    }

    public boolean enqueueLogOnlyOnce(String str, String str2) {
        if (!this.isInitialized) {
            ToyLog.d("NXLog : NXlog need to init. send fail.");
            return false;
        }
        Map<String, Object> mapConvertLogDataToMap = convertLogDataToMap(str2);
        if (mapConvertLogDataToMap == null) {
            return false;
        }
        ToyLog.d("NXLog enqueue Log only once.", "type", str, "logData", str2);
        updateUserInfo();
        return NexonAnalytics.getInstance().enqueueLogOnlyOnce(str, mapConvertLogDataToMap);
    }

    public boolean enqueueStageLog(int i, String str) {
        if (!this.isInitialized) {
            ToyLog.d("NXLog : NXlog need to init. send fail.");
            return false;
        }
        ToyLog.d("NXLog enqueue StageLog.", "stageCode", Integer.valueOf(i), NxLogInfo.KEY_COMMENT, str);
        updateUserInfo();
        return NexonAnalytics.getInstance().enqueueStageLog(i, str);
    }

    public void initialize(Context context) {
        NXPApplicationConfigManager nXPApplicationConfigManager = NXPApplicationConfigManager.getInstance();
        String analyticsServiceID = nXPApplicationConfigManager.getAnalyticsServiceID();
        String serviceId = nXPApplicationConfigManager.getServiceId();
        this.userInfo.npsn = 0L;
        if (context == null || NXStringUtil.isNull(analyticsServiceID) || NXStringUtil.isNull(serviceId)) {
            ToyLog.d("NXLog : context or analyticsServiceId or toyServiceID is null, NXlog init fail.");
            return;
        }
        NxGameClientInfo nxGameClientInfo = new NxGameClientInfo();
        nxGameClientInfo.serviceId = analyticsServiceID;
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        nxGameClientInfo.mid = nXToyCommonPreferenceController.getPreference() == null ? "" : nXToyCommonPreferenceController.getUUID();
        nxGameClientInfo.regionDomain = nXPApplicationConfigManager.getAnalyticsRegionHost();
        NexonAnalytics.getInstance().initialize(context, nxGameClientInfo);
        ToyLog.d("NXLog : initialized with Toy service ID : " + serviceId + ", analyticsServiceId :" + analyticsServiceID);
        this.isInitialized = true;
    }

    public boolean sendFunnel(String str, String str2) {
        if (!this.isInitialized) {
            ToyLog.d("NXLog : NXlog need to init. send fail.");
            return false;
        }
        updateUserInfo();
        if (NXStringUtil.isNull(str2)) {
            return NexonAnalytics.getInstance().enqueueFunnelLog(str, null);
        }
        Map<String, Object> mapConvertLogDataToMap = convertLogDataToMap(str2);
        if (mapConvertLogDataToMap == null) {
            return false;
        }
        ToyLog.d("NXLog : enqueueLog " + str2);
        return NexonAnalytics.getInstance().enqueueFunnelLog(str, mapConvertLogDataToMap);
    }

    public boolean sendNXLog(final String str, final String str2) {
        if (!this.isInitialized) {
            ToyLog.d("NXLog : NXlog need to init. send fail.");
            return false;
        }
        updateUserInfo();
        ToyLog.d("NXLog : enqueueLog " + str2);
        return NexonAnalytics.getInstance().enqueueNxLog(new INxLog() { // from class: kr.co.nexon.npaccount.stats.NXLog.2
            @Override // com.nexon.nexonanalyticssdk.INxLog
            public Map getNxLogBody() {
                return NXLog.this.convertLogDataToMap(str2);
            }

            @Override // com.nexon.nexonanalyticssdk.INxLog
            public String getTypeName() {
                return str;
            }
        });
    }

    public void setUserInfo(String str, String str2) {
        if (!this.isInitialized) {
            ToyLog.d("NXLog : NXlog need to init. send fail.");
            return;
        }
        ToyLog.d("NXLog : setUserInfo.", "accountNo", str, "sessionId", str2);
        NXToySession session = NXToySessionManager.getInstance().getSession();
        this.userInfo.npsn = Long.valueOf(session == null ? 0L : session.getNpsn());
        this.userInfo.accountNo = str;
        this.userInfo.sessionId = str2;
        NexonAnalytics.getInstance().setUserInfo(this.userInfo);
    }
}
