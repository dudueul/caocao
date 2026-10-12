package kr.co.nexon.npaccount.promotion;

import android.app.Activity;
import android.content.Context;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.util.NXDateUtil;
import com.nexon.npaccount.R;
import com.nexon.platform.NXPFinalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.result.NXToyPromotionShowContentClickResult;
import kr.co.nexon.npaccount.auth.result.model.NXToyBannerPromotion;
import kr.co.nexon.npaccount.listener.NPPromotionRequestContentListener;
import kr.co.nexon.npaccount.listener.NPPromotionShowContentListener;
import kr.co.nexon.npaccount.promotion.Banner.NPBannerPromotion;
import kr.co.nexon.npaccount.promotion.request.NXToyGetPromotionRequest;
import kr.co.nexon.npaccount.promotion.result.NXToyPromotionResult;
import kr.co.nexon.toy.android.ui.auth.NXPAgreeTermsDialog;
import kr.co.nexon.toy.android.ui.board.NPShowTodayDialog;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPPromotionManager {
    private Context mContext;
    private static volatile NPPromotionManager instance = null;
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: kr.co.nexon.npaccount.promotion.NPPromotionManager.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            NPPromotionManager unused = NPPromotionManager.instance = null;
        }
    };
    private HashMap<String, List<NPPromotion>> promotionsTable = new HashMap<>();
    private NXToySessionManager sessionManager = NXToySessionManager.getInstance();
    private NXToyLocaleManager localeManager = NXToyLocaleManager.getInstance();

    private NPPromotionManager(Context context) {
        this.mContext = context;
    }

    private boolean checkBannerStatus(NXToyBannerPromotion nXToyBannerPromotion) {
        String currentTimeFormat = NXDateUtil.getCurrentTimeFormat(NPShowTodayDialog.KEY_DATE_FORMAT);
        NPPromotionPrefCtl nPPromotionPrefCtl = NPPromotionPrefCtl.getInstance(this.mContext);
        if (nPPromotionPrefCtl.getBannerTodayDisabledDate(nXToyBannerPromotion.bannerId).equalsIgnoreCase(currentTimeFormat)) {
            return false;
        }
        int i = nXToyBannerPromotion.maximum;
        int bannerTotalDisplayedCount = nPPromotionPrefCtl.getBannerTotalDisplayedCount(nXToyBannerPromotion.bannerId);
        if (i != 0 && bannerTotalDisplayedCount >= i) {
            return false;
        }
        int i2 = nXToyBannerPromotion.frequency;
        String bannerFrequencyDisplayedDate = nPPromotionPrefCtl.getBannerFrequencyDisplayedDate(nXToyBannerPromotion.bannerId);
        return i2 == 0 || bannerFrequencyDisplayedDate.equalsIgnoreCase("") || NXDateUtil.addHour(bannerFrequencyDisplayedDate, NXPAgreeTermsDialog.DATE_FORMAT_TYPE_A, i2).compareTo(Calendar.getInstance().getTime()) <= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void generateAvailableBannerList(final String str, List<NXToyBannerPromotion> list, final NPListener nPListener) {
        final ArrayList arrayList = new ArrayList();
        for (NXToyBannerPromotion nXToyBannerPromotion : list) {
            if (checkBannerStatus(nXToyBannerPromotion)) {
                NPBannerPromotion nPBannerPromotion = new NPBannerPromotion(this.mContext, nXToyBannerPromotion);
                nPBannerPromotion.placementId = str;
                arrayList.add(nPBannerPromotion);
            }
        }
        if (arrayList.size() != 0) {
            requestContent(arrayList.listIterator(), new NPPromotionRequestContentListener() { // from class: kr.co.nexon.npaccount.promotion.NPPromotionManager.4
                @Override // kr.co.nexon.npaccount.listener.NPPromotionRequestContentListener
                public void onFailed(int i, String str2) {
                }

                @Override // kr.co.nexon.npaccount.listener.NPPromotionRequestContentListener
                public void onFinish() {
                    NXToyPromotionResult nXToyPromotionResult = new NXToyPromotionResult();
                    nXToyPromotionResult.requestTag = NXToyRequestTag.GetPromotion.getValue();
                    NPPromotionManager.this.promotionsTable.put(str, arrayList);
                    nPListener.onResult(nXToyPromotionResult);
                }

                @Override // kr.co.nexon.npaccount.listener.NPPromotionRequestContentListener
                public void onSuccess() {
                }
            });
            return;
        }
        NXToyPromotionResult nXToyPromotionResult = new NXToyPromotionResult(NXToyErrorCode.PROMOTION_DATA_EMPTY.getCode(), this.localeManager.getString(R.string.npres_get_promotion_info_fail), this.localeManager.getString(R.string.npres_get_promotion_info_fail));
        nXToyPromotionResult.requestTag = NXToyRequestTag.GetPromotion.getValue();
        nPListener.onResult(nXToyPromotionResult);
    }

    public static NPPromotionManager getInstance(Context context) {
        if (instance == null) {
            synchronized (NPPromotionManager.class) {
                if (instance == null) {
                    instance = new NPPromotionManager(context);
                }
            }
        }
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestContent(final ListIterator<NPPromotion> listIterator, final NPPromotionRequestContentListener nPPromotionRequestContentListener) {
        if (listIterator.hasNext()) {
            listIterator.next().requestContent(new NPPromotionRequestContentListener() { // from class: kr.co.nexon.npaccount.promotion.NPPromotionManager.5
                @Override // kr.co.nexon.npaccount.listener.NPPromotionRequestContentListener
                public void onFailed(int i, String str) {
                    listIterator.remove();
                    if (listIterator.hasNext()) {
                        NPPromotionManager.this.requestContent(listIterator, nPPromotionRequestContentListener);
                    } else {
                        nPPromotionRequestContentListener.onFinish();
                    }
                }

                @Override // kr.co.nexon.npaccount.listener.NPPromotionRequestContentListener
                public void onFinish() {
                }

                @Override // kr.co.nexon.npaccount.listener.NPPromotionRequestContentListener
                public void onSuccess() {
                    if (listIterator.hasNext()) {
                        NPPromotionManager.this.requestContent(listIterator, nPPromotionRequestContentListener);
                    } else {
                        nPPromotionRequestContentListener.onFinish();
                    }
                }
            });
        }
    }

    public void getPromotionContent(final String str, final boolean z, final NPListener nPListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetPromotionRequest(str), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.promotion.NPPromotionManager.2
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (!z) {
                    if (nPListener != null) {
                        nPListener.onResult(nXToyResult);
                    }
                } else {
                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                        nPListener.onResult(nXToyResult);
                        return;
                    }
                    final NXToyPromotionResult nXToyPromotionResult = (NXToyPromotionResult) nXToyResult;
                    List<NXToyBannerPromotion> list = nXToyPromotionResult.result.bannerList;
                    if (list != null && list.size() != 0) {
                        NPPromotionManager.this.generateAvailableBannerList(str, list, new NPListener() { // from class: kr.co.nexon.npaccount.promotion.NPPromotionManager.2.1
                            @Override // kr.co.nexon.toy.listener.NPListener
                            public void onResult(NXToyResult nXToyResult2) {
                                if (nXToyResult2.errorCode != 0) {
                                    nXToyPromotionResult.errorCode = nXToyResult2.errorCode;
                                    nXToyPromotionResult.errorText = nXToyResult2.errorText;
                                    nXToyPromotionResult.errorDetail = nXToyResult2.errorDetail;
                                }
                                nPListener.onResult(nXToyPromotionResult);
                            }
                        });
                        return;
                    }
                    NXToyPromotionResult nXToyPromotionResult2 = new NXToyPromotionResult(NXToyErrorCode.PROMOTION_DATA_EMPTY.getCode(), NPPromotionManager.this.localeManager.getString(R.string.npres_get_promotion_info_fail), NPPromotionManager.this.localeManager.getString(R.string.npres_get_promotion_info_fail));
                    nXToyPromotionResult2.requestTag = NXToyRequestTag.GetPromotion.getValue();
                    nPListener.onResult(nXToyPromotionResult2);
                }
            }
        });
    }

    public void showContent(final Activity activity, final ListIterator<NPPromotion> listIterator, final NPListener nPListener) {
        if (listIterator.hasNext()) {
            NPPromotion next = listIterator.next();
            if (next.isContentReady()) {
                next.showContent(activity, new NPPromotionShowContentListener() { // from class: kr.co.nexon.npaccount.promotion.NPPromotionManager.3
                    @Override // kr.co.nexon.npaccount.listener.NPPromotionShowContentListener
                    public void onClick(NXToyPromotionShowContentClickResult nXToyPromotionShowContentClickResult) {
                        if (nPListener != null) {
                            nPListener.onResult(nXToyPromotionShowContentClickResult);
                        }
                    }

                    @Override // kr.co.nexon.npaccount.listener.NPPromotionShowContentListener
                    public void onDismiss() {
                        listIterator.remove();
                        if (listIterator.hasNext()) {
                            NPPromotionManager.this.showContent(activity, listIterator, nPListener);
                        } else {
                            nPListener.onResult(new NXToyResult(0, "", "", NXToyRequestTag.ShowPromotion.getValue()));
                        }
                    }

                    @Override // kr.co.nexon.npaccount.listener.NPPromotionShowContentListener
                    public void onError() {
                    }
                });
            }
        }
    }

    public void showPromotionContent(Activity activity, String str, NPListener nPListener) {
        List<NPPromotion> list = this.promotionsTable.get(str);
        if (list != null && list.size() != 0) {
            showContent(activity, list.listIterator(), nPListener);
        } else if (nPListener != null) {
            NXToyResult nXToyResult = new NXToyResult(NXToyErrorCode.PROMOTION_DATA_EMPTY.getCode(), this.localeManager.getString(R.string.npres_get_promotion_info_fail), this.localeManager.getString(R.string.npres_get_promotion_info_fail));
            nXToyResult.requestTag = NXToyRequestTag.ShowPromotion.getValue();
            nPListener.onResult(nXToyResult);
        }
    }
}
