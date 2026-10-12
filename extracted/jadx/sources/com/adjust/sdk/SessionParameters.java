package com.adjust.sdk;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class SessionParameters {
    Map<String, String> callbackParameters;
    Map<String, String> partnerParameters;

    public SessionParameters deepCopy() {
        SessionParameters sessionParameters = new SessionParameters();
        if (this.callbackParameters != null) {
            sessionParameters.callbackParameters = new HashMap(this.callbackParameters);
        }
        if (this.partnerParameters != null) {
            sessionParameters.partnerParameters = new HashMap(this.partnerParameters);
        }
        return sessionParameters;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            SessionParameters sessionParameters = (SessionParameters) obj;
            return Util.equalObject(this.callbackParameters, sessionParameters.callbackParameters) && Util.equalObject(this.partnerParameters, sessionParameters.partnerParameters);
        }
        return false;
    }

    public int hashCode() {
        return ((Util.hashObject(this.callbackParameters) + 629) * 37) + Util.hashObject(this.partnerParameters);
    }
}
