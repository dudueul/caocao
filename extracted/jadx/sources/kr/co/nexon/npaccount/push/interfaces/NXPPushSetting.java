package kr.co.nexon.npaccount.push.interfaces;

import android.content.Context;
import kr.co.nexon.npaccount.push.NXPPushPolicies;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXPPushSetting {
    void getPolicy(Context context, NPListener nPListener);

    void setPolicy(Context context, NXPPushPolicies nXPPushPolicies, String str, NPListener nPListener);
}
