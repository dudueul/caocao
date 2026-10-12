package kr.co.nexon.npaccount.listener;

import kr.co.nexon.npaccount.auth.result.NXToyPromotionShowContentClickResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NPPromotionShowContentListener {
    void onClick(NXToyPromotionShowContentClickResult nXToyPromotionShowContentClickResult);

    void onDismiss();

    void onError();
}
