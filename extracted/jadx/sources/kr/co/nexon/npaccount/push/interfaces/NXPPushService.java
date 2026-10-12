package kr.co.nexon.npaccount.push.interfaces;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXPPushService {
    void handlePushClickEvent(Context context, Bundle bundle);

    void handlePushReceiveEvent(Context context, Bundle bundle, boolean z);

    void registerPush(@NonNull Context context, String str);
}
