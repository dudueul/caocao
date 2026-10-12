package kr.co.nexon.npaccount.gcm;

import android.content.Context;
import android.os.Bundle;
import com.nexon.core.log.ToyLog;
import kr.co.nexon.mdev.android.util.NXApplicationUtil;
import kr.co.nexon.npaccount.jnisupport.NXPUnity;
import kr.co.nexon.npaccount.jnisupport.NXPUnreal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPFcmEngineImpl implements NXPBaseFcmMessagingService.FcmInterface {
    NXPBaseFcmMessagingService fcmMessagingService;

    public NXPFcmEngineImpl(NXPBaseFcmMessagingService nXPBaseFcmMessagingService) {
        this.fcmMessagingService = nXPBaseFcmMessagingService;
    }

    @Override // kr.co.nexon.npaccount.gcm.NXPBaseFcmMessagingService.FcmInterface
    public void notifyNotification(NXPBaseFcmMessagingService.NXNotification nXNotification, Bundle bundle) {
        ToyLog.d("NXFcm Notification : Engine notifyNotification");
        Context applicationContext = this.fcmMessagingService.getApplicationContext();
        boolean z = nXNotification.isForce;
        try {
            ToyLog.d("Notify Engine sendMessage");
            NXPUnity.unitySendMessage("NPAccount", "OnReceiveNotification", nXNotification.metaString);
            NXPUnreal.sendMessageToUnreal(nXNotification.metaString);
        } catch (Exception e) {
            e.printStackTrace();
            if (!z) {
                this.fcmMessagingService.notifyNotification(applicationContext, nXNotification, bundle, true);
            }
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            if (!z) {
                this.fcmMessagingService.notifyNotification(applicationContext, nXNotification, bundle, true);
            }
        }
        if (z) {
            this.fcmMessagingService.notifyNotification(applicationContext, nXNotification, bundle, true);
        }
    }

    @Override // kr.co.nexon.npaccount.gcm.NXPBaseFcmMessagingService.FcmInterface
    public void onLocalPush(Context context, Bundle bundle, NXPBaseFcmMessagingService.NXNotification nXNotification) {
        ToyLog.d("Received local notification on Engine onLocalPush");
        if (!NXApplicationUtil.isRunningForeground(context)) {
            this.fcmMessagingService.notifyNotification(context, nXNotification, bundle, false);
            return;
        }
        ToyLog.d("Received local notification on foreground at Engine.");
        NXPUnity.unitySendMessage("NPAccount", "OnReceiveNotification", nXNotification.metaString);
        NXPUnreal.sendMessageToUnreal(nXNotification.metaString);
    }
}
