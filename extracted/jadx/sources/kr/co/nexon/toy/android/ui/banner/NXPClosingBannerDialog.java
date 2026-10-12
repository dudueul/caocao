package kr.co.nexon.toy.android.ui.banner;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import com.google.gson.Gson;
import com.nexon.core.log.ToyLog;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.result.model.NXToyBanner;
import kr.co.nexon.npaccount.promotion.NXBannerManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.banner.view.NXPClosingBannerView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPClosingBannerDialog extends NPDialogBase {
    public static final String KEY_ENDING_BANNER_DATA = "endingBannerData";
    public static final String TAG = "NXPClosingBannerDialog";
    private static final int WORK_ON_BTN_TYPE_NONE = 2;
    private Bitmap closingBannerBitmap;
    private NXToyBanner endingBanner;

    private SimpleImageLoadingListener createImageLoaderBridge(final ImageLoadingListener imageLoadingListener) {
        return new SimpleImageLoadingListener() { // from class: kr.co.nexon.toy.android.ui.banner.NXPClosingBannerDialog.5
            @Override // com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener, com.nostra13.universalimageloader.core.assist.ImageLoadingListener
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                super.onLoadingComplete(str, view, bitmap);
                NXPClosingBannerDialog.this.closingBannerBitmap = bitmap;
                if (imageLoadingListener != null) {
                    imageLoadingListener.onLoadingComplete(str, view, bitmap);
                }
            }

            @Override // com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener, com.nostra13.universalimageloader.core.assist.ImageLoadingListener
            public void onLoadingFailed(String str, View view, FailReason failReason) {
                super.onLoadingFailed(str, view, failReason);
                if (imageLoadingListener != null) {
                    imageLoadingListener.onLoadingFailed(str, view, failReason);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleClosingBannerEvent(int i) {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        NXBannerManager.getInstance(activity).clickClosingBanner(activity, this, this.endingBanner, i);
    }

    private void loadImage(@NonNull String str, ImageLoadingListener imageLoadingListener) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        if (imageLoader.isInited()) {
            imageLoader.destroy();
        }
        DisplayImageOptions displayImageOptionsBuild = new DisplayImageOptions.Builder().cacheOnDisc(true).build();
        imageLoader.init(new ImageLoaderConfiguration.Builder(getActivity()).defaultDisplayImageOptions(displayImageOptionsBuild).build());
        ImageLoader.getInstance().loadImage(str, displayImageOptionsBuild, imageLoadingListener);
    }

    public static NXPClosingBannerDialog newInstance(Activity activity, String str) {
        NXPClosingBannerDialog nXPClosingBannerDialog = new NXPClosingBannerDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString(KEY_ENDING_BANNER_DATA, str);
        nXPClosingBannerDialog.setArguments(bundle);
        return nXPClosingBannerDialog;
    }

    private void setEnddingBannerData(String str) {
        ToyLog.d("endingBannerJson:" + str);
        if (NXStringUtil.isNotNull(str)) {
            this.endingBanner = (NXToyBanner) new Gson().fromJson(str, NXToyBanner.class);
        }
        if (this.endingBanner == null) {
            this.endingBanner = new NXToyBanner();
        }
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        NXPClosingBannerView nXPClosingBannerView = (NXPClosingBannerView) View.inflate(this.applicationContext, R.layout.nxp_closing_banner_view, null);
        setEnddingBannerData(getArguments().getString(KEY_ENDING_BANNER_DATA));
        nXPClosingBannerView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.banner.NXPClosingBannerDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NXPClosingBannerDialog.this.onBackPressed();
            }
        });
        if (NXStringUtil.isNotNull(this.endingBanner.imgURL)) {
            loadImage(this.endingBanner.imgURL, createImageLoaderBridge(nXPClosingBannerView));
            nXPClosingBannerView.setOnClosingBannerClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.banner.NXPClosingBannerDialog.2
                @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                protected void onSwallowClick(View view) {
                    NXPClosingBannerDialog.this.handleClosingBannerEvent(0);
                }
            });
        } else {
            nXPClosingBannerView.setDefaultClosingBannerImage();
        }
        if (NXStringUtil.isNotNull(this.endingBanner.buttonText) && this.endingBanner.landType != 2) {
            nXPClosingBannerView.setCustomButton(this.endingBanner.buttonText, new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.banner.NXPClosingBannerDialog.3
                @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                protected void onSwallowClick(View view) {
                    NXPClosingBannerDialog.this.handleClosingBannerEvent(1);
                }
            });
        }
        nXPClosingBannerView.setExitButton(NXToyLocaleManager.getInstance(this.applicationContext).getString(R.string.quit_game), new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.banner.NXPClosingBannerDialog.4
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NXPClosingBannerDialog.this.handleClosingBannerEvent(2);
            }
        });
        return nXPClosingBannerView;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        handleClosingBannerEvent(3);
        super.onBackPressed();
    }
}
