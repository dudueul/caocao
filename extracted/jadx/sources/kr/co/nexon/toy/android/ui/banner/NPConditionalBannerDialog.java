package kr.co.nexon.toy.android.ui.banner;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.util.NXDateUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import kr.co.nexon.mdev.android.util.NXDeviceUtil;
import kr.co.nexon.mdev.android.view.NPRelativeLayout;
import kr.co.nexon.mdev.android.view.dialog.NXDialog;
import kr.co.nexon.npaccount.NPAccount;
import kr.co.nexon.npaccount.auth.result.NXToyPromotionShowContentClickResult;
import kr.co.nexon.npaccount.auth.result.model.NXToyBannerPromotion;
import kr.co.nexon.npaccount.auth.result.model.NXToyConditionalBannerButton;
import kr.co.nexon.npaccount.listener.NPPromotionShowContentListener;
import kr.co.nexon.npaccount.promotion.NPPromotionPrefCtl;
import kr.co.nexon.toy.android.ui.board.NPShowTodayDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPConditionalBannerDialog extends NXDialog implements NPRelativeLayout.NPOrientationEventListener, View.OnClickListener {
    private int bannerFrameHeight;
    private int bannerFramewidth;
    private DisplayImageOptions defaultOptions;
    private NPRelativeLayout frameLayout;
    private RelativeLayout imageFrameLayout;
    private String imageUrl;
    private ImageView ivBanner;
    private Activity mActivity;
    private NPAccount npAccount;
    private NPPromotionPrefCtl prefCtl;
    private NXToyBannerPromotion promotionData;
    private NPPromotionShowContentListener promotionShowContentListener;
    private int resizeFrameHeight;
    private int resizeFrameWidth;
    private float resizeRate;
    private int screenHeight;
    private int screenWidth;

    public NPConditionalBannerDialog(Activity activity, NXToyBannerPromotion nXToyBannerPromotion) {
        super(activity, R.style.BannerDialog);
        this.resizeRate = 0.0f;
        this.mActivity = activity;
        this.promotionData = nXToyBannerPromotion;
        this.npAccount = NPAccount.getInstance(activity);
        this.prefCtl = NPPromotionPrefCtl.getInstance(this.mActivity.getApplicationContext());
    }

    private void removeDismissMessage() {
        setOnDismissListener(null);
    }

    private NXDialog.NXTouchArea resizeLayout(int i, int i2, int i3, int i4) {
        return new NXDialog.NXTouchArea(Math.round(i * this.resizeRate), Math.round(i2 * this.resizeRate), Math.round(i3 * this.resizeRate), Math.round(i4 * this.resizeRate));
    }

    private void setBannerFrameSize() {
        float f = this.screenWidth / this.bannerFramewidth;
        float f2 = this.screenHeight / this.bannerFrameHeight;
        if (f < f2) {
            this.resizeRate = f;
        } else {
            this.resizeRate = f2;
        }
        this.resizeFrameWidth = Math.round(this.resizeRate * this.bannerFramewidth);
        this.resizeFrameHeight = Math.round(this.resizeRate * this.bannerFrameHeight);
        this.imageFrameLayout.removeAllViews();
        this.ivBanner = new ImageView(this.mActivity.getApplicationContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.width = this.resizeFrameWidth;
        layoutParams.height = this.resizeFrameHeight;
        layoutParams.addRule(13, -1);
        this.ivBanner.setLayoutParams(layoutParams);
        this.imageFrameLayout.addView(this.ivBanner);
        for (NXToyConditionalBannerButton nXToyConditionalBannerButton : this.promotionData.buttonList) {
            addClickArea(resizeLayout(nXToyConditionalBannerButton.left, nXToyConditionalBannerButton.top, nXToyConditionalBannerButton.right, nXToyConditionalBannerButton.bottom), this.imageFrameLayout, this).setTag(nXToyConditionalBannerButton);
        }
    }

    public void displayBannerImage() {
        setBannerFrameSize();
        ImageLoader.getInstance().displayImage(this.imageUrl, this.ivBanner, this.defaultOptions, new ImageLoadingListener() { // from class: kr.co.nexon.toy.android.ui.banner.NPConditionalBannerDialog.1
            @Override // com.nostra13.universalimageloader.core.assist.ImageLoadingListener
            public void onLoadingCancelled(String str, View view) {
            }

            @Override // com.nostra13.universalimageloader.core.assist.ImageLoadingListener
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            }

            @Override // com.nostra13.universalimageloader.core.assist.ImageLoadingListener
            public void onLoadingFailed(String str, View view, FailReason failReason) {
            }

            @Override // com.nostra13.universalimageloader.core.assist.ImageLoadingListener
            public void onLoadingStarted(String str, View view) {
            }
        });
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXDialog, android.app.Dialog
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NXToyConditionalBannerButton nXToyConditionalBannerButton = (NXToyConditionalBannerButton) view.getTag();
        ToyLog.d("banner btn click:" + nXToyConditionalBannerButton.type);
        switch (nXToyConditionalBannerButton.type) {
            case 0:
                NXToyPromotionShowContentClickResult nXToyPromotionShowContentClickResult = new NXToyPromotionShowContentClickResult(0, "", "");
                nXToyPromotionShowContentClickResult.requestTag = NXToyRequestTag.PromotionPid.getValue();
                if (NXStringUtil.isNotNull(nXToyConditionalBannerButton.meta)) {
                    nXToyPromotionShowContentClickResult.result.meta = nXToyConditionalBannerButton.meta;
                }
                if (NXStringUtil.isNotNull(nXToyConditionalBannerButton.pid)) {
                    nXToyPromotionShowContentClickResult.result.pid = nXToyConditionalBannerButton.pid;
                }
                if (this.promotionShowContentListener != null) {
                    this.promotionShowContentListener.onClick(nXToyPromotionShowContentClickResult);
                }
                if (this.promotionData.touchEvent == 0) {
                    removeDismissMessage();
                    dismiss();
                }
                break;
            case 1:
                NXToyPromotionShowContentClickResult nXToyPromotionShowContentClickResult2 = new NXToyPromotionShowContentClickResult(0, "", "");
                nXToyPromotionShowContentClickResult2.requestTag = NXToyRequestTag.PromotionMeta.getValue();
                if (NXStringUtil.isNotNull(nXToyConditionalBannerButton.meta)) {
                    nXToyPromotionShowContentClickResult2.result.meta = nXToyConditionalBannerButton.meta;
                }
                if (this.promotionShowContentListener != null) {
                    this.promotionShowContentListener.onClick(nXToyPromotionShowContentClickResult2);
                }
                if (this.promotionData.touchEvent == 0) {
                    removeDismissMessage();
                    dismiss();
                }
                break;
            case 2:
                if (this.promotionData.touchEvent == 0) {
                    removeDismissMessage();
                    dismiss();
                }
                this.npAccount.showWebNotitle(this.mActivity, nXToyConditionalBannerButton.url);
                break;
            case 3:
                dismiss();
                break;
            case 4:
                this.prefCtl.setBannerTodayDisabledDate(this.promotionData.bannerId, NXDateUtil.getCurrentTimeFormat(NPShowTodayDialog.KEY_DATE_FORMAT));
                dismiss();
                break;
            case 5:
            case 6:
                if (nXToyConditionalBannerButton.urlScheme != null) {
                    runUrlSheme(Uri.parse(nXToyConditionalBannerButton.urlScheme));
                }
                if (this.promotionData.touchEvent == 0) {
                    removeDismissMessage();
                    dismiss();
                }
                break;
        }
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.screenWidth = NXDeviceUtil.getScreenWidth(this.mActivity.getWindowManager());
        this.screenHeight = NXDeviceUtil.getScreenHeight(this.mActivity.getWindowManager());
        this.bannerFramewidth = this.promotionData.width;
        this.bannerFrameHeight = this.promotionData.height;
        this.imageUrl = this.promotionData.imgList.get(0).imgURL;
        this.defaultOptions = new DisplayImageOptions.Builder().cacheOnDisc(true).build();
        ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(this.mActivity.getApplicationContext()).defaultDisplayImageOptions(this.defaultOptions).build());
        setFrameLayout();
        displayBannerImage();
    }

    @Override // kr.co.nexon.mdev.android.view.NPRelativeLayout.NPOrientationEventListener
    public void onOrientationChanged(int i, int i2) {
        ToyLog.d("onOrientationChangedwidth:" + i + ", height:" + i2);
        this.screenWidth = NXDeviceUtil.getScreenWidth(this.mActivity.getWindowManager());
        this.screenHeight = NXDeviceUtil.getScreenHeight(this.mActivity.getWindowManager());
        displayBannerImage();
    }

    public void runUrlSheme(Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            intent.addFlags(131072);
            this.mActivity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            ToyLog.d(e.getMessage());
        }
    }

    public void setFrameLayout() {
        this.frameLayout = new NPRelativeLayout(this.mActivity.getApplicationContext());
        this.frameLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.frameLayout.setOrientationEventListener(this);
        this.frameLayout.setBackgroundColor((((int) (this.promotionData.opacity * 255.0f)) << 24) & ViewCompat.MEASURED_STATE_MASK);
        this.imageFrameLayout = new RelativeLayout(this.mActivity.getApplicationContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13, -1);
        this.imageFrameLayout.setLayoutParams(layoutParams);
        this.frameLayout.addView(this.imageFrameLayout);
        setContentView(this.frameLayout);
    }

    public void setNPPromotionShowContentListener(NPPromotionShowContentListener nPPromotionShowContentListener) {
        this.promotionShowContentListener = nPPromotionShowContentListener;
    }
}
