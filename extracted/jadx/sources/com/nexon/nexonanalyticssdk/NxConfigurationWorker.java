package com.nexon.nexonanalyticssdk;

import com.adjust.sdk.Constants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxConfigurationWorker implements Runnable {
    private NxConfigurationSetupCallback configurationSetupCallback;
    private String configURLFormat = "%s://%s/v2/configurations/na_sdk_config?key-path=%s";
    private String protocol = Constants.SCHEME;
    private String domain = "config.livelog.nexon.com";

    public NxConfigurationWorker(NxConfigurationSetupCallback nxConfigurationSetupCallback) {
        this.configurationSetupCallback = nxConfigurationSetupCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            String info = NxNetwork.getInstance().getInfo(String.format(this.configURLFormat, this.protocol, this.domain, (String) NxLogInfo.getInstance().getGameClientInfo().get(NxLogInfo.KEY_SERVICE_ID)));
            if (info == null || info.isEmpty()) {
                return;
            }
            this.configurationSetupCallback.setupConfigurationInfo(info);
        } catch (Exception e) {
            NxLogcat.e("Error : " + e.getMessage());
        }
    }

    public void setDomain(String str) {
        if (str.contains("http://")) {
            str = str.replace("http://", "");
        } else if (str.contains("https://")) {
            str = str.replace("https://", "");
        }
        this.domain = str;
    }

    public void setProtocol(String str) {
        if (str.equals("http") || str.equals(Constants.SCHEME)) {
            this.protocol = str;
        }
    }
}
