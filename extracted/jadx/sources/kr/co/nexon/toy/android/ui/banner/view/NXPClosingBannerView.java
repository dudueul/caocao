package kr.co.nexon.toy.android.ui.banner.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.nexon.core.log.ToyLog;
import com.nexon.npaccount.R;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import kr.co.nexon.mdev.android.view.NXPRelativeLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPClosingBannerView extends NXPRelativeLayout implements ImageLoadingListener {
    private ImageView backgroundImageView;
    private Button closeButton;
    private View.OnClickListener closeButtonClickListener;
    private View.OnClickListener closingBannerClickListener;
    private Button customButton;
    private View.OnClickListener customButtonClickListener;
    private Button exitButton;
    private View.OnClickListener exitButtonClickListener;
    private NXClickListener onSwallowClickSListener;

    public NXPClosingBannerView(Context context) {
        super(context);
        this.onSwallowClickSListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.banner.view.NXPClosingBannerView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.closingBannerExitButton) {
                    if (NXPClosingBannerView.this.exitButtonClickListener != null) {
                        NXPClosingBannerView.this.exitButtonClickListener.onClick(view);
                    }
                } else if (id == R.id.closingBannerCustomButton) {
                    if (NXPClosingBannerView.this.customButtonClickListener != null) {
                        NXPClosingBannerView.this.customButtonClickListener.onClick(view);
                    }
                } else if (id == R.id.closingBannerImageView) {
                    if (NXPClosingBannerView.this.closingBannerClickListener != null) {
                        NXPClosingBannerView.this.closingBannerClickListener.onClick(view);
                    }
                } else {
                    if (id != R.id.closeBtn || NXPClosingBannerView.this.closeButtonClickListener == null) {
                        return;
                    }
                    NXPClosingBannerView.this.closeButtonClickListener.onClick(view);
                }
            }
        };
    }

    public NXPClosingBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onSwallowClickSListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.banner.view.NXPClosingBannerView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.closingBannerExitButton) {
                    if (NXPClosingBannerView.this.exitButtonClickListener != null) {
                        NXPClosingBannerView.this.exitButtonClickListener.onClick(view);
                    }
                } else if (id == R.id.closingBannerCustomButton) {
                    if (NXPClosingBannerView.this.customButtonClickListener != null) {
                        NXPClosingBannerView.this.customButtonClickListener.onClick(view);
                    }
                } else if (id == R.id.closingBannerImageView) {
                    if (NXPClosingBannerView.this.closingBannerClickListener != null) {
                        NXPClosingBannerView.this.closingBannerClickListener.onClick(view);
                    }
                } else {
                    if (id != R.id.closeBtn || NXPClosingBannerView.this.closeButtonClickListener == null) {
                        return;
                    }
                    NXPClosingBannerView.this.closeButtonClickListener.onClick(view);
                }
            }
        };
    }

    public NXPClosingBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.onSwallowClickSListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.banner.view.NXPClosingBannerView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.closingBannerExitButton) {
                    if (NXPClosingBannerView.this.exitButtonClickListener != null) {
                        NXPClosingBannerView.this.exitButtonClickListener.onClick(view);
                    }
                } else if (id == R.id.closingBannerCustomButton) {
                    if (NXPClosingBannerView.this.customButtonClickListener != null) {
                        NXPClosingBannerView.this.customButtonClickListener.onClick(view);
                    }
                } else if (id == R.id.closingBannerImageView) {
                    if (NXPClosingBannerView.this.closingBannerClickListener != null) {
                        NXPClosingBannerView.this.closingBannerClickListener.onClick(view);
                    }
                } else {
                    if (id != R.id.closeBtn || NXPClosingBannerView.this.closeButtonClickListener == null) {
                        return;
                    }
                    NXPClosingBannerView.this.closeButtonClickListener.onClick(view);
                }
            }
        };
    }

    @Override // kr.co.nexon.mdev.android.view.NXPRelativeLayout
    protected void initView() {
        this.backgroundImageView = (ImageView) findViewById(R.id.closingBannerImageView);
        if (this.backgroundImageView != null) {
            this.backgroundImageView.setOnClickListener(this.onSwallowClickSListener);
        }
        this.exitButton = (Button) findViewById(R.id.closingBannerExitButton);
        if (this.exitButton != null) {
            this.exitButton.setOnClickListener(this.onSwallowClickSListener);
        }
        this.customButton = (Button) findViewById(R.id.closingBannerCustomButton);
        if (this.customButton != null) {
            this.customButton.setOnClickListener(this.onSwallowClickSListener);
        }
        this.closeButton = (Button) findViewById(R.id.closeBtn);
        if (this.closeButton != null) {
            this.closeButton.setOnClickListener(this.onSwallowClickSListener);
        }
    }

    @Override // com.nostra13.universalimageloader.core.assist.ImageLoadingListener
    public void onLoadingCancelled(String str, View view) {
    }

    @Override // com.nostra13.universalimageloader.core.assist.ImageLoadingListener
    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        if (this.backgroundImageView != null) {
            this.backgroundImageView.setImageBitmap(bitmap);
        }
    }

    @Override // com.nostra13.universalimageloader.core.assist.ImageLoadingListener
    public void onLoadingFailed(String str, View view, FailReason failReason) {
        ToyLog.d("closingBanner image load failed. imageUri:" + str + " , failReason:" + failReason);
    }

    @Override // com.nostra13.universalimageloader.core.assist.ImageLoadingListener
    public void onLoadingStarted(String str, View view) {
    }

    public void setCustomButton(String str, View.OnClickListener onClickListener) {
        if (this.customButton != null) {
            this.customButton.setVisibility(0);
            this.customButton.setText(str);
            this.customButtonClickListener = onClickListener;
        }
    }

    public void setDefaultClosingBannerImage() {
        if (this.backgroundImageView != null) {
            this.backgroundImageView.setImageResource(R.drawable.nexon_logo);
            this.backgroundImageView.setScaleType(ImageView.ScaleType.CENTER);
        }
    }

    public void setExitButton(String str, View.OnClickListener onClickListener) {
        if (this.exitButton != null) {
            this.exitButton.setText(str);
            this.exitButtonClickListener = onClickListener;
        }
    }

    public void setOnCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.closeButtonClickListener = onClickListener;
    }

    public void setOnClosingBannerClickListener(View.OnClickListener onClickListener) {
        this.closingBannerClickListener = onClickListener;
    }
}
