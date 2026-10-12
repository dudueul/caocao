package kr.co.nexon.npaccount.gcm;

import android.content.Context;
import android.os.Bundle;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPFcmNativeImpl implements NXPBaseFcmMessagingService.FcmInterface {
    NXPBaseFcmMessagingService fcmMessagingService;

    public NXPFcmNativeImpl(NXPBaseFcmMessagingService nXPBaseFcmMessagingService) {
        this.fcmMessagingService = nXPBaseFcmMessagingService;
    }

    @Override // kr.co.nexon.npaccount.gcm.NXPBaseFcmMessagingService.FcmInterface
    public void notifyNotification(NXPBaseFcmMessagingService.NXNotification nXNotification, Bundle bundle) {
    }

    @Override // kr.co.nexon.npaccount.gcm.NXPBaseFcmMessagingService.FcmInterface
    public void onLocalPush(Context context, Bundle bundle, NXPBaseFcmMessagingService.NXNotification nXNotification) {
        this.fcmMessagingService.notifyNotification(context, nXNotification, bundle, false);
    }
}
