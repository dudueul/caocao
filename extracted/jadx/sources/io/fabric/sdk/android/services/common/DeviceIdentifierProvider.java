package io.fabric.sdk.android.services.common;

import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface DeviceIdentifierProvider {
    Map<IdManager.DeviceIdentifierType, String> getDeviceIdentifiers();
}
