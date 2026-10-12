package com.nexon.core.log.logger;

import android.content.SharedPreferences;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.NoCache;
import com.nexon.core.log.constant.NXToyLogLevel;
import com.nexon.core.log.model.NXToyLog;
import com.nexon.core.log.model.NXToyLogRequest;
import com.nexon.core.log.model.NXToyStackTraceInfo;
import com.nexon.core.log.util.NXToyLogUtil;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.request.NXToyByteArrayRequest;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.nexonanalyticssdk.NxLogInfo;
import com.nexon.nexonanalyticssdk.NxSystemInfo;
import com.tapjoy.TapjoyConstants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyPlexLogger extends NXToyLogger {
    private final int MAXIMUM_LOG_BUFFER_SIZE = 1000;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private final LinkedList<NXToyLogRequest> logBuffer = new LinkedList<>();
    private final String instanceID = ((System.currentTimeMillis() * TapjoyConstants.TIMER_INCREMENT) + ((long) new Random(System.currentTimeMillis()).nextInt(9999))) + "";
    private final RequestQueue requestQueue = new RequestQueue(new NoCache(), new BasicNetwork(new HurlStack()));
    private final int SCHEDULED_LOGGING_SECOND = 60;
    private final int TIME_OUT_MILLISECOND = 10000;
    private final int RETRY_COUNT = 0;
    private final float backoffMultiplier = 0.0f;
    private final int MAXIMUM_SEND_LOG_COUNT = 200;
    private final String PLEX_SERVER_URL = "https://toy.log.nexon.io";
    private final String PLEX_OLD_SERVER_URL = "http://test-log-logstash-elb01-763059396.ap-northeast-1.elb.amazonaws.com:5050";
    private final String GOOGLE_CLOUD_TEST_SERVER_URL = "http://35.200.59.82:8888/result.json";
    private final String LOCAL_TEST_SERVER_URL = "http://10.10.44.152:8888/result.json";
    private boolean enableSend = false;

    /* JADX INFO: renamed from: com.nexon.core.log.logger.NXToyPlexLogger$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ NXToyLogLevel val$logLevel;
        final /* synthetic */ String val$message;
        final /* synthetic */ NXToyStackTraceInfo val$stackTraceInfo;
        final /* synthetic */ Object[] val$varargs;

        AnonymousClass2(NXToyStackTraceInfo nXToyStackTraceInfo, NXToyLogLevel nXToyLogLevel, String str, Object[] objArr) {
            this.val$stackTraceInfo = nXToyStackTraceInfo;
            this.val$logLevel = nXToyLogLevel;
            this.val$message = str;
            this.val$varargs = objArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (NXToyPlexLogger.this.logBuffer.size() >= 1000) {
                return;
            }
            final String jsonString = NXJsonUtil.toJsonString(NXToyPlexLogger.this.putAdditionalFields(this.val$stackTraceInfo, this.val$logLevel, this.val$message, this.val$varargs));
            NXToyLog nXToyLog = new NXToyLog(this.val$logLevel, jsonString);
            final NXToyLogRequest nXToyLogRequest = new NXToyLogRequest();
            int i = 1;
            String str = "https://toy.log.nexon.io";
            NXToyByteArrayRequest nXToyByteArrayRequest = new NXToyByteArrayRequest(i, str, new Response.Listener<byte[]>() { // from class: com.nexon.core.log.logger.NXToyPlexLogger.2.2
                @Override // com.android.volley.Response.Listener
                public void onResponse(byte[] bArr) {
                    NXToyPlexLogger.this.scheduledExecutorService.submit(new Runnable() { // from class: com.nexon.core.log.logger.NXToyPlexLogger.2.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            NXToyPlexLogger.this.logBuffer.remove(nXToyLogRequest);
                        }
                    });
                }
            }, new Response.ErrorListener() { // from class: com.nexon.core.log.logger.NXToyPlexLogger.2.1
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    nXToyLogRequest.setSentLog(false);
                }
            }) { // from class: com.nexon.core.log.logger.NXToyPlexLogger.2.3
                @Override // com.android.volley.Request
                public byte[] getBody() {
                    return jsonString.getBytes();
                }

                @Override // com.android.volley.Request
                public Map<String, String> getHeaders() {
                    HashMap map = new HashMap();
                    map.put(HttpRequest.HEADER_CONTENT_TYPE, "application/json");
                    return map;
                }

                @Override // com.android.volley.Request
                public Request.Priority getPriority() {
                    return AnonymousClass2.this.val$logLevel.getValue() > NXToyLogLevel.DEBUG.getValue() ? Request.Priority.IMMEDIATE : Request.Priority.NORMAL;
                }
            };
            nXToyByteArrayRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 0, 0.0f));
            nXToyLogRequest.setByteArrayRequest(nXToyByteArrayRequest);
            nXToyLogRequest.setToyLog(nXToyLog);
            if (this.val$logLevel.getValue() <= NXToyLogLevel.DEBUG.getValue()) {
                NXToyPlexLogger.this.logBuffer.add(nXToyLogRequest);
            } else {
                NXToyPlexLogger.this.logBuffer.offerFirst(nXToyLogRequest);
                NXToyPlexLogger.this.sendLogToServer(nXToyLogRequest);
            }
        }
    }

    public NXToyPlexLogger() {
        this.scheduledExecutorService.scheduleWithFixedDelay(new Runnable() { // from class: com.nexon.core.log.logger.NXToyPlexLogger.1
            @Override // java.lang.Runnable
            public void run() {
                int i = 0;
                for (NXToyLogRequest nXToyLogRequest : NXToyPlexLogger.this.logBuffer) {
                    i++;
                    if (i > 200) {
                        return;
                    }
                    if (!nXToyLogRequest.isSentLog()) {
                        if (nXToyLogRequest.getToyLog().getLogLevel().getValue() >= NXToyLogLevel.INFO.getValue()) {
                            NXToyPlexLogger.this.sendLogToServer(nXToyLogRequest);
                        } else if (!NXToyPlexLogger.this.enableSend) {
                            return;
                        } else {
                            NXToyPlexLogger.this.sendLogToServer(nXToyLogRequest);
                        }
                    }
                }
            }
        }, 60L, 60L, TimeUnit.SECONDS);
        this.requestQueue.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, Object> putAdditionalFields(NXToyStackTraceInfo nXToyStackTraceInfo, NXToyLogLevel nXToyLogLevel, String str, Object... objArr) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(NXToyLogUtil.parseVarargs(str, objArr));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        linkedHashMap.put("createDate", simpleDateFormat.format(Calendar.getInstance().getTime()));
        linkedHashMap.put("logLevel", nXToyLogLevel.toString());
        switch (nXToyLogLevel) {
            case INFO:
            case DEBUG:
                linkedHashMap.put("log_type", "toy.sdk.aos-debug");
                break;
            case DEVELOP:
            case VERBOSE:
                linkedHashMap.put("log_type", "toy.sdk.aos-verbose");
                break;
            case ERROR:
                linkedHashMap.put("log_type", "toy.sdk.aos-error");
                break;
        }
        linkedHashMap.put("className", nXToyStackTraceInfo.getClassName());
        linkedHashMap.put("methodName", nXToyStackTraceInfo.getMethodName());
        linkedHashMap.put("lineNumber", nXToyStackTraceInfo.getLineNumber());
        linkedHashMap.put("instanceID", this.instanceID);
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        SharedPreferences preference = nXToyCommonPreferenceController.getPreference();
        linkedHashMap.put(NxSystemInfo.KEY_UUID, preference == null ? "" : nXToyCommonPreferenceController.getUUID());
        linkedHashMap.put(NxLogInfo.KEY_UUID2, preference == null ? "" : nXToyCommonPreferenceController.getUUID2());
        NXToySession session = NXToySessionManager.getInstance().getSession();
        linkedHashMap.put("serviceID", session == null ? "" : session.getServiceId());
        linkedHashMap.put(NxLogInfo.KEY_NPSN, session == null ? "0" : session.getNpsn() + "");
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendLogToServer(NXToyLogRequest nXToyLogRequest) {
        if (nXToyLogRequest == null || nXToyLogRequest.getByteArrayRequest() == null) {
            return;
        }
        nXToyLogRequest.setSentLog(true);
        this.requestQueue.add(nXToyLogRequest.getByteArrayRequest());
    }

    @Override // com.nexon.core.log.logger.NXToyLogger
    public void addLog(NXToyStackTraceInfo nXToyStackTraceInfo, NXToyLogLevel nXToyLogLevel, String str, Object... objArr) {
        if (nXToyLogLevel.getValue() < NXToyLogLevel.DEBUG.getValue()) {
            return;
        }
        this.scheduledExecutorService.submit(new AnonymousClass2(nXToyStackTraceInfo, nXToyLogLevel, str, objArr));
    }

    public void setEnableSend(boolean z) {
        this.enableSend = z;
    }
}
