package kr.co.nexon.npaccount.policy.interfaces;

import android.content.Context;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXPPhoneNumberPolicy {
    void deletePolicy(String str, NPListener nPListener);

    void getPolicy(Context context, String str, NPListener nPListener);

    void hasPolicy(Context context, NPListener nPListener);

    void putPolicy(String str, NPListener nPListener);
}
