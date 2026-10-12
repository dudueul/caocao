package com.adjust.sdk;

import android.content.Context;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class AdjustConfig {
    public static final String ENVIRONMENT_PRODUCTION = "production";
    public static final String ENVIRONMENT_SANDBOX = "sandbox";
    String appSecret;
    String appToken;
    String basePath;
    Context context;
    Class deepLinkComponent;
    String defaultTracker;
    Double delayStart;
    Boolean deviceKnown;
    String environment;
    boolean eventBufferingEnabled;
    ILogger logger;
    OnAttributionChangedListener onAttributionChangedListener;
    OnDeeplinkResponseListener onDeeplinkResponseListener;
    OnEventTrackingFailedListener onEventTrackingFailedListener;
    OnEventTrackingSucceededListener onEventTrackingSucceededListener;
    OnSessionTrackingFailedListener onSessionTrackingFailedListener;
    OnSessionTrackingSucceededListener onSessionTrackingSucceededListener;
    List<IRunActivityHandler> preLaunchActionsArray;
    String processName;
    String pushToken;
    boolean readMobileEquipmentIdentity;
    String sdkPrefix;
    String secretId;
    boolean sendInBackground;
    Boolean startEnabled;
    boolean startOffline;
    String userAgent;

    public AdjustConfig(Context context, String str, String str2) {
        init(context, str, str2, false);
    }

    public AdjustConfig(Context context, String str, String str2, boolean z) {
        init(context, str, str2, z);
    }

    private boolean checkAppToken(String str) {
        if (str == null) {
            this.logger.error("Missing App Token", new Object[0]);
            return false;
        }
        if (str.length() == 12) {
            return true;
        }
        this.logger.error("Malformed App Token '%s'", str);
        return false;
    }

    private boolean checkContext(Context context) {
        if (context == null) {
            this.logger.error("Missing context", new Object[0]);
            return false;
        }
        if (Util.checkPermission(context, "android.permission.INTERNET")) {
            return true;
        }
        this.logger.error("Missing permission: INTERNET", new Object[0]);
        return false;
    }

    private boolean checkEnvironment(String str) {
        if (str == null) {
            this.logger.error("Missing environment", new Object[0]);
            return false;
        }
        if (str.equals(ENVIRONMENT_SANDBOX)) {
            this.logger.warnInProduction("SANDBOX: Adjust is running in Sandbox mode. Use this setting for testing. Don't forget to set the environment to `production` before publishing!", new Object[0]);
            return true;
        }
        if (str.equals(ENVIRONMENT_PRODUCTION)) {
            this.logger.warnInProduction("PRODUCTION: Adjust is running in Production mode. Use this setting only for the build that you want to publish. Set the environment to `sandbox` if you want to test your app!", new Object[0]);
            return true;
        }
        this.logger.error("Unknown environment '%s'", str);
        return false;
    }

    private void init(Context context, String str, String str2, boolean z) {
        this.logger = AdjustFactory.getLogger();
        if (z && ENVIRONMENT_PRODUCTION.equals(str2)) {
            setLogLevel(LogLevel.SUPRESS, str2);
        } else {
            setLogLevel(LogLevel.INFO, str2);
        }
        if (context != null) {
            context = context.getApplicationContext();
        }
        this.context = context;
        this.appToken = str;
        this.environment = str2;
        this.eventBufferingEnabled = false;
        this.sendInBackground = false;
    }

    private void setLogLevel(LogLevel logLevel, String str) {
        this.logger.setLogLevel(logLevel, ENVIRONMENT_PRODUCTION.equals(str));
    }

    public boolean isValid() {
        return checkAppToken(this.appToken) && checkEnvironment(this.environment) && checkContext(this.context);
    }

    public void setAppSecret(long j, long j2, long j3, long j4, long j5) {
        this.secretId = Util.formatString("%d", Long.valueOf(j));
        this.appSecret = Util.formatString("%d%d%d%d", Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(j4), Long.valueOf(j5));
    }

    public void setDeepLinkComponent(Class cls) {
        this.deepLinkComponent = cls;
    }

    public void setDefaultTracker(String str) {
        this.defaultTracker = str;
    }

    public void setDelayStart(double d) {
        this.delayStart = Double.valueOf(d);
    }

    public void setDeviceKnown(boolean z) {
        this.deviceKnown = Boolean.valueOf(z);
    }

    public void setEventBufferingEnabled(Boolean bool) {
        if (bool == null) {
            this.eventBufferingEnabled = false;
        } else {
            this.eventBufferingEnabled = bool.booleanValue();
        }
    }

    public void setLogLevel(LogLevel logLevel) {
        setLogLevel(logLevel, this.environment);
    }

    public void setOnAttributionChangedListener(OnAttributionChangedListener onAttributionChangedListener) {
        this.onAttributionChangedListener = onAttributionChangedListener;
    }

    public void setOnDeeplinkResponseListener(OnDeeplinkResponseListener onDeeplinkResponseListener) {
        this.onDeeplinkResponseListener = onDeeplinkResponseListener;
    }

    public void setOnEventTrackingFailedListener(OnEventTrackingFailedListener onEventTrackingFailedListener) {
        this.onEventTrackingFailedListener = onEventTrackingFailedListener;
    }

    public void setOnEventTrackingSucceededListener(OnEventTrackingSucceededListener onEventTrackingSucceededListener) {
        this.onEventTrackingSucceededListener = onEventTrackingSucceededListener;
    }

    public void setOnSessionTrackingFailedListener(OnSessionTrackingFailedListener onSessionTrackingFailedListener) {
        this.onSessionTrackingFailedListener = onSessionTrackingFailedListener;
    }

    public void setOnSessionTrackingSucceededListener(OnSessionTrackingSucceededListener onSessionTrackingSucceededListener) {
        this.onSessionTrackingSucceededListener = onSessionTrackingSucceededListener;
    }

    public void setProcessName(String str) {
        this.processName = str;
    }

    public void setReadMobileEquipmentIdentity(boolean z) {
        this.readMobileEquipmentIdentity = z;
    }

    public void setSdkPrefix(String str) {
        this.sdkPrefix = str;
    }

    public void setSendInBackground(boolean z) {
        this.sendInBackground = z;
    }

    public void setUserAgent(String str) {
        this.userAgent = str;
    }
}
