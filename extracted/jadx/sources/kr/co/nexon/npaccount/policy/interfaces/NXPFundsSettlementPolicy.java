package kr.co.nexon.npaccount.policy.interfaces;

import android.content.Context;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXPFundsSettlementPolicy {
    void getPolicy(Context context, NPListener nPListener);

    void putPolicy(Context context, String str, NPListener nPListener);
}
