package kr.co.nexon.npaccount.listener;

import java.util.List;
import kr.co.nexon.npaccount.promotion.NPPromotion;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NPPromotionListener {
    void onError(int i, String str, String str2);

    void onSuccess(List<NPPromotion> list);
}
