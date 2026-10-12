package kr.co.nexon.npaccount.promotion.Banner;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.View;
import android.view.WindowManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.util.NXDateUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import kr.co.nexon.npaccount.auth.result.model.NXToyBannerPromotion;
import kr.co.nexon.npaccount.listener.NPPromotionRequestContentListener;
import kr.co.nexon.npaccount.listener.NPPromotionShowContentListener;
import kr.co.nexon.npaccount.promotion.NPPromotion;
import kr.co.nexon.npaccount.promotion.NPPromotionPrefCtl;
import kr.co.nexon.toy.android.ui.auth.NXPAgreeTermsDialog;
import kr.co.nexon.toy.android.ui.banner.NPConditionalBannerDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPBannerPromotion extends NPPromotion {
    public static final int TOUCH_EVENT_CLOSE = 0;
    public static final int TOUCH_EVENT_KEEP = 1;
    public static final int TYPE_CLOSE_BUTTON = 3;
    public static final int TYPE_DO_NOT_SHOW_TODAY = 4;
    public static final int TYPE_META = 1;
    public static final int TYPE_META_PURCHASED = 0;
    public static final int TYPE_URL = 2;
    public static final int TYPE_URL_SCHEME = 6;
    public static final int TYPE_URL_SCHEME_PURCHASED = 5;
    public static final int UNLIMITED = 0;
    private Context mContext;
    private NPPromotionPrefCtl prefCtl;
    private NXToyBannerPromotion promotionData;
    private boolean isContentReady = false;
    private DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheOnDisc(true).build();

    public NPBannerPromotion(Context context, NXToyBannerPromotion nXToyBannerPromotion) {
        this.mContext = context;
        this.prefCtl = NPPromotionPrefCtl.getInstance(context.getApplicationContext());
        this.promotionData = nXToyBannerPromotion;
        ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(this.mContext.getApplicationContext()).defaultDisplayImageOptions(this.defaultOptions).build());
    }

    @Override // kr.co.nexon.npaccount.promotion.NPPromotion
    public boolean isContentReady() {
        return this.isContentReady;
    }

    @Override // kr.co.nexon.npaccount.promotion.NPPromotion
    public void requestContent(final NPPromotionRequestContentListener nPPromotionRequestContentListener) {
        final String str = this.promotionData.imgList.get(0).imgURL;
        ((Activity) this.mContext).runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.promotion.Banner.NPBannerPromotion.1
            @Override // java.lang.Runnable
            public void run() {
                ImageLoader.getInstance().loadImage(str, NPBannerPromotion.this.defaultOptions, new ImageLoadingListener() { // from class: kr.co.nexon.npaccount.promotion.Banner.NPBannerPromotion.1.1
                    @Override // com.nostra13.universalimageloader.core.assist.ImageLoadingListener
                    public void onLoadingCancelled(String str2, View view) {
                        ToyLog.d("onLoadingCancelled:" + str2);
                        NPBannerPromotion.this.isContentReady = false;
                        nPPromotionRequestContentListener.onFailed(NXToyErrorCode.PROMOTION_IMAGE_LOAD_FAIL.getCode(), "Load Cancel");
                    }

                    @Override // com.nostra13.universalimageloader.core.assist.ImageLoadingListener
                    public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                        ToyLog.d("onLoadingComplete:" + str2);
                        NPBannerPromotion.this.isContentReady = true;
                        nPPromotionRequestContentListener.onSuccess();
                    }

                    @Override // com.nostra13.universalimageloader.core.assist.ImageLoadingListener
                    public void onLoadingFailed(String str2, View view, FailReason failReason) {
                        ToyLog.d("onLoadingFailed:" + str2);
                        NPBannerPromotion.this.isContentReady = false;
                        nPPromotionRequestContentListener.onFailed(NXToyErrorCode.PROMOTION_IMAGE_LOAD_FAIL.getCode(), failReason.getCause().getMessage());
                    }

                    @Override // com.nostra13.universalimageloader.core.assist.ImageLoadingListener
                    public void onLoadingStarted(String str2, View view) {
                        ToyLog.d("onLoadingStarted:" + str2);
                    }
                });
            }
        });
    }

    @Override // kr.co.nexon.npaccount.promotion.NPPromotion
    public void showContent(Activity activity, final NPPromotionShowContentListener nPPromotionShowContentListener) {
        try {
            NPConditionalBannerDialog nPConditionalBannerDialog = new NPConditionalBannerDialog(activity, this.promotionData);
            nPConditionalBannerDialog.setNPPromotionShowContentListener(nPPromotionShowContentListener);
            nPConditionalBannerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: kr.co.nexon.npaccount.promotion.Banner.NPBannerPromotion.2
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    nPPromotionShowContentListener.onDismiss();
                }
            });
            nPConditionalBannerDialog.show();
            int i = this.promotionData.frequency;
            int i2 = this.promotionData.maximum;
            if (i != 0) {
                this.prefCtl.setBannerFrequencyDisplayedDate(this.promotionData.bannerId, NXDateUtil.getCurrentTimeFormat(NXPAgreeTermsDialog.DATE_FORMAT_TYPE_A));
            }
            if (i2 != 0) {
                this.prefCtl.incrementBannerTotalDisplayedCount(this.promotionData.bannerId);
            }
        } catch (ActivityNotFoundException e) {
            nPPromotionShowContentListener.onDismiss();
        } catch (WindowManager.BadTokenException e2) {
            nPPromotionShowContentListener.onDismiss();
        }
    }
}
