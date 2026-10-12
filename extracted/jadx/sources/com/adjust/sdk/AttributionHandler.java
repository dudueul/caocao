package com.adjust.sdk;

import android.net.Uri;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class AttributionHandler implements IAttributionHandler {
    private static final String ATTRIBUTION_TIMER_NAME = "Attribution timer";
    private WeakReference<IActivityHandler> activityHandlerWeakRef;
    private ActivityPackage attributionPackage;
    private String basePath;
    private boolean paused;
    private CustomScheduledExecutor scheduledExecutor = new CustomScheduledExecutor("AttributionHandler", false);
    private ILogger logger = AdjustFactory.getLogger();
    private TimerOnce timer = new TimerOnce(new Runnable() { // from class: com.adjust.sdk.AttributionHandler.1
        @Override // java.lang.Runnable
        public void run() {
            AttributionHandler.this.sendAttributionRequest();
        }
    }, ATTRIBUTION_TIMER_NAME);

    public AttributionHandler(IActivityHandler iActivityHandler, ActivityPackage activityPackage, boolean z) {
        this.basePath = iActivityHandler.getBasePath();
        init(iActivityHandler, activityPackage, z);
    }

    private void checkAttributionI(IActivityHandler iActivityHandler, ResponseData responseData) {
        if (responseData.jsonResponse == null) {
            return;
        }
        long jOptLong = responseData.jsonResponse.optLong("ask_in", -1L);
        if (jOptLong >= 0) {
            iActivityHandler.setAskingAttribution(true);
            getAttributionI(jOptLong);
        } else {
            iActivityHandler.setAskingAttribution(false);
            responseData.attribution = AdjustAttribution.fromJson(responseData.jsonResponse.optJSONObject("attribution"), responseData.adid);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAttributionResponseI(IActivityHandler iActivityHandler, AttributionResponseData attributionResponseData) {
        checkAttributionI(iActivityHandler, attributionResponseData);
        checkDeeplinkI(attributionResponseData);
        iActivityHandler.launchAttributionResponseTasks(attributionResponseData);
    }

    private void checkDeeplinkI(AttributionResponseData attributionResponseData) {
        JSONObject jSONObjectOptJSONObject;
        String strOptString;
        if (attributionResponseData.jsonResponse == null || (jSONObjectOptJSONObject = attributionResponseData.jsonResponse.optJSONObject("attribution")) == null || (strOptString = jSONObjectOptJSONObject.optString(Constants.DEEPLINK, null)) == null) {
            return;
        }
        attributionResponseData.deeplink = Uri.parse(strOptString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkSdkClickResponseI(IActivityHandler iActivityHandler, SdkClickResponseData sdkClickResponseData) {
        checkAttributionI(iActivityHandler, sdkClickResponseData);
        iActivityHandler.launchSdkClickResponseTasks(sdkClickResponseData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkSessionResponseI(IActivityHandler iActivityHandler, SessionResponseData sessionResponseData) {
        checkAttributionI(iActivityHandler, sessionResponseData);
        iActivityHandler.launchSessionResponseTasks(sessionResponseData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAttributionI(long j) {
        if (this.timer.getFireIn() > j) {
            return;
        }
        if (j != 0) {
            this.logger.debug("Waiting to query attribution in %s seconds", Util.SecondsDisplayFormat.format(j / 1000.0d));
        }
        this.timer.startIn(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAttributionRequestI() {
        if (this.paused) {
            this.logger.debug("Attribution handler is paused", new Object[0]);
            return;
        }
        this.logger.verbose("%s", this.attributionPackage.getExtendedString());
        try {
            ResponseData responseDataCreateGETHttpsURLConnection = UtilNetworking.createGETHttpsURLConnection(this.attributionPackage, this.basePath);
            if (responseDataCreateGETHttpsURLConnection instanceof AttributionResponseData) {
                checkAttributionResponse((AttributionResponseData) responseDataCreateGETHttpsURLConnection);
            }
        } catch (Exception e) {
            this.logger.error("Failed to get attribution (%s)", e.getMessage());
        }
    }

    public void checkAttributionResponse(final AttributionResponseData attributionResponseData) {
        this.scheduledExecutor.submit(new Runnable() { // from class: com.adjust.sdk.AttributionHandler.5
            @Override // java.lang.Runnable
            public void run() {
                IActivityHandler iActivityHandler = (IActivityHandler) AttributionHandler.this.activityHandlerWeakRef.get();
                if (iActivityHandler == null) {
                    return;
                }
                AttributionHandler.this.checkAttributionResponseI(iActivityHandler, attributionResponseData);
            }
        });
    }

    @Override // com.adjust.sdk.IAttributionHandler
    public void checkSdkClickResponse(final SdkClickResponseData sdkClickResponseData) {
        this.scheduledExecutor.submit(new Runnable() { // from class: com.adjust.sdk.AttributionHandler.4
            @Override // java.lang.Runnable
            public void run() {
                IActivityHandler iActivityHandler = (IActivityHandler) AttributionHandler.this.activityHandlerWeakRef.get();
                if (iActivityHandler == null) {
                    return;
                }
                AttributionHandler.this.checkSdkClickResponseI(iActivityHandler, sdkClickResponseData);
            }
        });
    }

    @Override // com.adjust.sdk.IAttributionHandler
    public void checkSessionResponse(final SessionResponseData sessionResponseData) {
        this.scheduledExecutor.submit(new Runnable() { // from class: com.adjust.sdk.AttributionHandler.3
            @Override // java.lang.Runnable
            public void run() {
                IActivityHandler iActivityHandler = (IActivityHandler) AttributionHandler.this.activityHandlerWeakRef.get();
                if (iActivityHandler == null) {
                    return;
                }
                AttributionHandler.this.checkSessionResponseI(iActivityHandler, sessionResponseData);
            }
        });
    }

    @Override // com.adjust.sdk.IAttributionHandler
    public void getAttribution() {
        this.scheduledExecutor.submit(new Runnable() { // from class: com.adjust.sdk.AttributionHandler.2
            @Override // java.lang.Runnable
            public void run() {
                AttributionHandler.this.getAttributionI(0L);
            }
        });
    }

    @Override // com.adjust.sdk.IAttributionHandler
    public void init(IActivityHandler iActivityHandler, ActivityPackage activityPackage, boolean z) {
        this.activityHandlerWeakRef = new WeakReference<>(iActivityHandler);
        this.attributionPackage = activityPackage;
        this.paused = !z;
    }

    @Override // com.adjust.sdk.IAttributionHandler
    public void pauseSending() {
        this.paused = true;
    }

    @Override // com.adjust.sdk.IAttributionHandler
    public void resumeSending() {
        this.paused = false;
    }

    public void sendAttributionRequest() {
        this.scheduledExecutor.submit(new Runnable() { // from class: com.adjust.sdk.AttributionHandler.6
            @Override // java.lang.Runnable
            public void run() {
                AttributionHandler.this.sendAttributionRequestI();
            }
        });
    }

    @Override // com.adjust.sdk.IAttributionHandler
    public void teardown() {
        this.logger.verbose("AttributionHandler teardown", new Object[0]);
        if (this.timer != null) {
            this.timer.teardown();
        }
        if (this.scheduledExecutor != null) {
            try {
                this.scheduledExecutor.shutdownNow();
            } catch (SecurityException e) {
            }
        }
        if (this.activityHandlerWeakRef != null) {
            this.activityHandlerWeakRef.clear();
        }
        this.scheduledExecutor = null;
        this.activityHandlerWeakRef = null;
        this.logger = null;
        this.attributionPackage = null;
        this.timer = null;
    }
}
