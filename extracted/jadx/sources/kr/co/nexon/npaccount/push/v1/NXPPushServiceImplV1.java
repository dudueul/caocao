package kr.co.nexon.npaccount.push.v1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import kr.co.nexon.npaccount.push.interfaces.NXPPushService;
import kr.co.nexon.npaccount.push.request.v1.NXToyRegisterPushRequest;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPushServiceImplV1 implements NXPPushService {
    @Override // kr.co.nexon.npaccount.push.interfaces.NXPPushService
    public void handlePushClickEvent(Context context, Bundle bundle) {
        ToyLog.d("v1 PushServiceImplV1 pushClickEvent  bundle: " + bundle);
    }

    @Override // kr.co.nexon.npaccount.push.interfaces.NXPPushService
    public void handlePushReceiveEvent(Context context, Bundle bundle, boolean z) {
        ToyLog.d("v1 PushServiceImplV1 pushReceiveEvent, " + z + " , bundle:" + bundle);
    }

    @Override // kr.co.nexon.npaccount.push.interfaces.NXPPushService
    public void registerPush(@NonNull Context context, String str) {
        ToyLog.d("registerPush pushKey:" + str);
        NXToyRequestPostman.getInstance().postRequest(new NXToyRegisterPushRequest(str), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.push.v1.NXPPushServiceImplV1.1
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                ToyLog.d(nXToyResult.toString());
            }
        });
    }
}
