package kr.co.nexon.npaccount.gcm;

import android.content.Context;
import android.os.Bundle;
import com.google.firebase.messaging.RemoteMessage;
import com.nexon.core.log.ToyLog;
import kr.co.nexon.mdev.android.util.NXApplicationUtil;
import kr.co.nexon.npaccount.jnisupport.NXPUnity;
import kr.co.nexon.npaccount.jnisupport.NXPUnreal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPFcmMessagingService extends NXPBaseFcmMessagingService {
    public NXPFcmMessagingService() {
        super("NXPFcmMessagingService", true);
        ToyLog.d("Init NXPFcmMessagingService");
    }

    @Override // kr.co.nexon.npaccount.gcm.NXPBaseFcmMessagingService
    protected void notify(NXPBaseFcmMessagingService.NXNotification nXNotification, Bundle bundle) {
        ToyLog.d("NXFcm Notification : Engine notifyNotification");
        boolean z = nXNotification.isForce;
        try {
            ToyLog.d("Notify Engine sendMessage");
            NXPUnity.unitySendMessage("NPAccount", "OnReceiveNotification", nXNotification.metaString);
            NXPUnreal.sendMessageToUnreal(nXNotification.metaString);
        } catch (Exception e) {
            e.printStackTrace();
            if (!z) {
                notifyNotification(this, nXNotification, bundle, true);
            }
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            if (!z) {
                notifyNotification(this, nXNotification, bundle, true);
            }
        }
        if (z) {
            notifyNotification(this, nXNotification, bundle, true);
        }
    }

    @Override // kr.co.nexon.npaccount.gcm.NXPBaseFcmMessagingService
    public void onLocalPush(Context context, Bundle bundle) {
        ToyLog.d("Received local notification on Engine onLocalPush");
        NXPBaseFcmMessagingService.NXNotification nXNotification = new NXPBaseFcmMessagingService.NXNotification(context, bundle);
        if (!NXApplicationUtil.isRunningForeground(context)) {
            notifyNotification(context, nXNotification, bundle, false);
            return;
        }
        ToyLog.d("Received local notification on foreground at Engine.");
        NXPUnity.unitySendMessage("NPAccount", "OnReceiveNotification", nXNotification.metaString);
        NXPUnreal.sendMessageToUnreal(nXNotification.metaString);
    }

    @Override // kr.co.nexon.npaccount.gcm.NXPBaseFcmMessagingService, com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        ToyLog.d("Engine onMessageReceived");
    }
}
