package com.adjust.sdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class RequestHandler implements IRequestHandler {
    private String basePath;
    private WeakReference<IPackageHandler> packageHandlerWeakRef;
    private ILogger logger = AdjustFactory.getLogger();
    private CustomScheduledExecutor scheduledExecutor = new CustomScheduledExecutor("RequestHandler", false);

    public RequestHandler(IPackageHandler iPackageHandler) {
        init(iPackageHandler);
        this.basePath = iPackageHandler.getBasePath();
    }

    private void closePackageI(ActivityPackage activityPackage, String str, Throwable th) {
        String string = Util.formatString("%s. (%s) Will retry later", activityPackage.getFailureMessage(), Util.getReasonString(str, th));
        this.logger.error(string, new Object[0]);
        ResponseData responseDataBuildResponseData = ResponseData.buildResponseData(activityPackage);
        responseDataBuildResponseData.message = string;
        IPackageHandler iPackageHandler = this.packageHandlerWeakRef.get();
        if (iPackageHandler == null) {
            return;
        }
        iPackageHandler.closeFirstPackage(responseDataBuildResponseData, activityPackage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendI(ActivityPackage activityPackage, int i) {
        String baseUrl = AdjustFactory.getBaseUrl();
        if (this.basePath != null) {
            baseUrl = baseUrl + this.basePath;
        }
        try {
            ResponseData responseDataCreatePOSTHttpsURLConnection = UtilNetworking.createPOSTHttpsURLConnection(baseUrl + activityPackage.getPath(), activityPackage, i);
            IPackageHandler iPackageHandler = this.packageHandlerWeakRef.get();
            if (iPackageHandler != null) {
                if (responseDataCreatePOSTHttpsURLConnection.jsonResponse == null) {
                    iPackageHandler.closeFirstPackage(responseDataCreatePOSTHttpsURLConnection, activityPackage);
                } else {
                    iPackageHandler.sendNextPackage(responseDataCreatePOSTHttpsURLConnection);
                }
            }
        } catch (UnsupportedEncodingException e) {
            sendNextPackageI(activityPackage, "Failed to encode parameters", e);
        } catch (SocketTimeoutException e2) {
            closePackageI(activityPackage, "Request timed out", e2);
        } catch (IOException e3) {
            closePackageI(activityPackage, "Request failed", e3);
        } catch (Throwable th) {
            sendNextPackageI(activityPackage, "Runtime exception", th);
        }
    }

    private void sendNextPackageI(ActivityPackage activityPackage, String str, Throwable th) {
        String string = Util.formatString("%s. (%s)", activityPackage.getFailureMessage(), Util.getReasonString(str, th));
        this.logger.error(string, new Object[0]);
        ResponseData responseDataBuildResponseData = ResponseData.buildResponseData(activityPackage);
        responseDataBuildResponseData.message = string;
        IPackageHandler iPackageHandler = this.packageHandlerWeakRef.get();
        if (iPackageHandler == null) {
            return;
        }
        iPackageHandler.sendNextPackage(responseDataBuildResponseData);
    }

    @Override // com.adjust.sdk.IRequestHandler
    public void init(IPackageHandler iPackageHandler) {
        this.packageHandlerWeakRef = new WeakReference<>(iPackageHandler);
    }

    @Override // com.adjust.sdk.IRequestHandler
    public void sendPackage(final ActivityPackage activityPackage, final int i) {
        this.scheduledExecutor.submit(new Runnable() { // from class: com.adjust.sdk.RequestHandler.1
            @Override // java.lang.Runnable
            public void run() {
                RequestHandler.this.sendI(activityPackage, i);
            }
        });
    }

    @Override // com.adjust.sdk.IRequestHandler
    public void teardown() {
        this.logger.verbose("RequestHandler teardown", new Object[0]);
        if (this.scheduledExecutor != null) {
            try {
                this.scheduledExecutor.shutdownNow();
            } catch (SecurityException e) {
            }
        }
        if (this.packageHandlerWeakRef != null) {
            this.packageHandlerWeakRef.clear();
        }
        this.scheduledExecutor = null;
        this.packageHandlerWeakRef = null;
        this.logger = null;
    }
}
