package kr.co.nexon.npaccount.promotion;

import android.app.Activity;
import kr.co.nexon.npaccount.listener.NPPromotionRequestContentListener;
import kr.co.nexon.npaccount.listener.NPPromotionShowContentListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public abstract class NPPromotion {
    public String placementId;

    public abstract boolean isContentReady();

    public abstract void requestContent(NPPromotionRequestContentListener nPPromotionRequestContentListener);

    public abstract void showContent(Activity activity, NPPromotionShowContentListener nPPromotionShowContentListener);
}
