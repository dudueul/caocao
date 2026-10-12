package com.adjust.sdk;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class InstallReferrer implements InvocationHandler {
    private static final String PACKAGE_BASE_NAME = "com.android.installreferrer.";
    private static final int STATUS_DEVELOPER_ERROR = 3;
    private static final int STATUS_FEATURE_NOT_SUPPORTED = 2;
    private static final int STATUS_OK = 0;
    private static final int STATUS_SERVICE_DISCONNECTED = -1;
    private static final int STATUS_SERVICE_UNAVAILABLE = 1;
    private WeakReference<IActivityHandler> activityHandlerWeakRef;
    private Context context;
    private Object referrerClient;
    private int retryWaitTime = 3000;
    private ILogger logger = AdjustFactory.getLogger();
    private final Object flagLock = new Object();
    private boolean hasInstallReferrerBeenRead = false;
    private int retries = 0;
    private TimerOnce retryTimer = new TimerOnce(new Runnable() { // from class: com.adjust.sdk.InstallReferrer.1
        @Override // java.lang.Runnable
        public void run() {
            InstallReferrer.this.startConnection();
        }
    }, "InstallReferrer");

    public InstallReferrer(Context context, IActivityHandler iActivityHandler) {
        this.context = context;
        this.activityHandlerWeakRef = new WeakReference<>(iActivityHandler);
    }

    private void closeReferrerClient() {
        if (this.referrerClient == null) {
            return;
        }
        try {
            Reflection.invokeInstanceMethod(this.referrerClient, "endConnection", null, new Object[0]);
        } catch (Exception e) {
            this.logger.error("closeReferrerClient error (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
        }
        this.referrerClient = null;
    }

    private Object createInstallReferrerClient(Context context) {
        try {
            return Reflection.invokeInstanceMethod(Reflection.invokeStaticMethod("com.android.installreferrer.api.InstallReferrerClient", "newBuilder", new Class[]{Context.class}, context), "build", null, new Object[0]);
        } catch (ClassNotFoundException e) {
            this.logger.warn("InstallReferrer not integrated in project (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
            return null;
        } catch (Exception e2) {
            this.logger.error("createInstallReferrerClient error (%s) from (%s)", e2.getMessage(), e2.getClass().getCanonicalName());
            return null;
        }
    }

    private Object createProxyInstallReferrerStateListener(Class cls) {
        try {
            return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, this);
        } catch (IllegalArgumentException e) {
            this.logger.error("InstallReferrer proxy violating parameter restrictions", new Object[0]);
            return null;
        } catch (NullPointerException e2) {
            this.logger.error("Null argument passed to InstallReferrer proxy", new Object[0]);
            return null;
        }
    }

    private long getInstallBeginTimestampSeconds(Object obj) {
        if (obj == null) {
            return -1L;
        }
        try {
            return ((Long) Reflection.invokeInstanceMethod(obj, "getInstallBeginTimestampSeconds", null, new Object[0])).longValue();
        } catch (Exception e) {
            this.logger.error("getInstallBeginTimestampSeconds error (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
            return -1L;
        }
    }

    private Object getInstallReferrer() {
        if (this.referrerClient == null) {
            return null;
        }
        try {
            return Reflection.invokeInstanceMethod(this.referrerClient, "getInstallReferrer", null, new Object[0]);
        } catch (Exception e) {
            this.logger.error("getInstallReferrer error (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
            return null;
        }
    }

    private Class getInstallReferrerStateListenerClass() {
        try {
            return Class.forName("com.android.installreferrer.api.InstallReferrerStateListener");
        } catch (Exception e) {
            this.logger.error("getInstallReferrerStateListenerClass error (%s) from (%s)", e.getMessage(), e.getClass().getCanonicalName());
            return null;
        }
    }

    private long getReferrerClickTimestampSeconds(Object obj) {
        if (obj == null) {
            return -1L;
        }
        try {
            return ((Long) Reflection.invokeInstanceMethod(obj, "getReferrerClickTimestampSeconds", null, new Object[0])).longValue();
        } catch (Exception e) {
            this.logger.error("getReferrerClickTimestampSeconds error (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
            return -1L;
        }
    }

    private String getStringInstallReferrer(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return (String) Reflection.invokeInstanceMethod(obj, "getInstallReferrer", null, new Object[0]);
        } catch (Exception e) {
            this.logger.error("getStringInstallReferrer error (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
            return null;
        }
    }

    private void onInstallReferrerSetupFinishedInt(int i) {
        switch (i) {
            case -1:
                this.logger.debug("Play Store service is not connected now. Retrying ...", new Object[0]);
                retry();
                return;
            case 0:
                try {
                    Object installReferrer = getInstallReferrer();
                    String stringInstallReferrer = getStringInstallReferrer(installReferrer);
                    long referrerClickTimestampSeconds = getReferrerClickTimestampSeconds(installReferrer);
                    long installBeginTimestampSeconds = getInstallBeginTimestampSeconds(installReferrer);
                    this.logger.debug("installReferrer: %s, clickTime: %d, installBeginTime: %d", stringInstallReferrer, Long.valueOf(referrerClickTimestampSeconds), Long.valueOf(installBeginTimestampSeconds));
                    IActivityHandler iActivityHandler = this.activityHandlerWeakRef.get();
                    if (iActivityHandler != null) {
                        iActivityHandler.sendInstallReferrer(referrerClickTimestampSeconds, installBeginTimestampSeconds, stringInstallReferrer);
                    }
                    synchronized (this.flagLock) {
                        this.hasInstallReferrerBeenRead = true;
                        break;
                    }
                    closeReferrerClient();
                    return;
                } catch (Exception e) {
                    this.logger.warn("Couldn't get install referrer from client (%s). Retrying ...", e.getMessage());
                    retry();
                    return;
                }
            case 1:
                this.logger.debug("Could not initiate connection to the Install Referrer service. Retrying ...", new Object[0]);
                retry();
                return;
            case 2:
                this.logger.debug("Install referrer not available on the current Play Store app.", new Object[0]);
                return;
            case 3:
                this.logger.debug("Install referrer general errors caused by incorrect usage. Retrying ...", new Object[0]);
                retry();
                return;
            default:
                this.logger.debug("Unexpected response code of install referrer response: %d", Integer.valueOf(i));
                closeReferrerClient();
                return;
        }
    }

    private void retry() {
        synchronized (this.flagLock) {
            if (this.hasInstallReferrerBeenRead) {
                this.logger.debug("Install referrer has already been read", new Object[0]);
                return;
            }
            this.retries++;
            if (this.retries > 2) {
                this.logger.debug("Limit number of retry for install referrer surpassed", new Object[0]);
                return;
            }
            long fireIn = this.retryTimer.getFireIn();
            if (fireIn > 0) {
                this.logger.debug("Already waiting to retry to read install referrer in %d milliseconds", Long.valueOf(fireIn));
            } else {
                this.retryTimer.startIn(this.retryWaitTime);
            }
        }
    }

    private void startConnection(Class cls, Object obj) {
        try {
            Reflection.invokeInstanceMethod(this.referrerClient, "startConnection", new Class[]{cls}, obj);
        } catch (InvocationTargetException e) {
            if (Util.hasRootCause(e)) {
                this.logger.error("InstallReferrer encountered an InvocationTargetException %s", Util.getRootCause(e));
            }
        } catch (Exception e2) {
            this.logger.error("startConnection error (%s) thrown by (%s)", e2.getMessage(), e2.getClass().getCanonicalName());
        }
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        if (method == null) {
            this.logger.error("InstallReferrer invoke method null", new Object[0]);
        } else {
            String name = method.getName();
            if (name == null) {
                this.logger.error("InstallReferrer invoke method name null", new Object[0]);
            } else {
                this.logger.debug("InstallReferrer invoke method name: %s", name);
                if (objArr == null) {
                    this.logger.warn("InstallReferrer invoke args null", new Object[0]);
                    objArr = new Object[0];
                }
                for (Object obj2 : objArr) {
                    this.logger.debug("InstallReferrer invoke arg: %s", obj2);
                }
                if (name.equals("onInstallReferrerSetupFinished")) {
                    if (objArr.length != 1) {
                        this.logger.error("InstallReferrer invoke onInstallReferrerSetupFinished args lenght not 1: %d", Integer.valueOf(objArr.length));
                    } else {
                        Object obj3 = objArr[0];
                        if (obj3 instanceof Integer) {
                            Integer num = (Integer) obj3;
                            if (num == null) {
                                this.logger.error("InstallReferrer invoke onInstallReferrerSetupFinished responseCode arg is null", new Object[0]);
                            } else {
                                onInstallReferrerSetupFinishedInt(num.intValue());
                            }
                        } else {
                            this.logger.error("InstallReferrer invoke onInstallReferrerSetupFinished arg not int", new Object[0]);
                        }
                    }
                } else if (name.equals("onInstallReferrerServiceDisconnected")) {
                    this.logger.debug("InstallReferrer onInstallReferrerServiceDisconnected", new Object[0]);
                }
            }
        }
        return null;
    }

    public void startConnection() {
        Class installReferrerStateListenerClass;
        Object objCreateProxyInstallReferrerStateListener;
        if (AdjustFactory.getTryInstallReferrer()) {
            closeReferrerClient();
            synchronized (this.flagLock) {
                if (this.hasInstallReferrerBeenRead) {
                    this.logger.debug("Install referrer has already been read", new Object[0]);
                } else if (this.context != null) {
                    this.referrerClient = createInstallReferrerClient(this.context);
                    if (this.referrerClient != null && (installReferrerStateListenerClass = getInstallReferrerStateListenerClass()) != null && (objCreateProxyInstallReferrerStateListener = createProxyInstallReferrerStateListener(installReferrerStateListenerClass)) != null) {
                        startConnection(installReferrerStateListenerClass, objCreateProxyInstallReferrerStateListener);
                    }
                }
            }
        }
    }
}
