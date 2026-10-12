package com.nhn.android.naverlogin.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nhn.android.naverlogin.data.OAuthLoginString;
import com.nhn.android.naverlogin.ui.OAuthLoginImage;
import com.nhn.android.naverlogin.util.DeviceDisplayInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class OAuthLoginLayoutNaverAppDownloadBanner extends LinearLayout {
    private static final String TAG = "NaverLoginOAuth|OAuthLoginLayoutNaverAppDownloadBanner";
    private Context mContext;
    private float mDensity;
    private int mDensityDpi;

    public OAuthLoginLayoutNaverAppDownloadBanner(Context context) {
        super(context);
        initData(context);
        initView(null);
    }

    public OAuthLoginLayoutNaverAppDownloadBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initData(context);
        initView(attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadNaverApp() {
        ((Activity) this.mContext).startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.nhn.android.search")));
        ((Activity) this.mContext).finish();
    }

    private RelativeLayout getCloseBtnLayout() {
        ImageView imageView = new ImageView(this.mContext);
        Drawable drawableCloseImg = OAuthLoginImage.getDrawableCloseImg(getResources());
        int iPxFromDp = pxFromDp(10);
        int i = Build.VERSION.SDK_INT >= 8 ? -1 : -1;
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(-2, i));
        imageView.setPadding(iPxFromDp, iPxFromDp, iPxFromDp, iPxFromDp);
        imageView.setImageDrawable(drawableCloseImg);
        imageView.setScaleType(ImageView.ScaleType.FIT_START);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.addRule(11, -1);
        imageView.setLayoutParams(layoutParams);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.nhn.android.naverlogin.ui.view.OAuthLoginLayoutNaverAppDownloadBanner.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OAuthLoginLayoutNaverAppDownloadBanner.this.setVisibility(8);
            }
        });
        RelativeLayout relativeLayout = new RelativeLayout(this.mContext);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(0, i, 1.0f));
        relativeLayout.addView(imageView);
        return relativeLayout;
    }

    private LinearLayout getLayoutDownloadDesc() {
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        TextView textView = new TextView(this.mContext);
        TextView textView2 = new TextView(this.mContext);
        int iPxFromDp = pxFromDp(10);
        linearLayout.setPadding(iPxFromDp, iPxFromDp, 0, iPxFromDp);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        linearLayout.setGravity(16);
        linearLayout.setClickable(true);
        int iPxFromDp2 = pxFromDp(4.0d);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        textView.setPadding(0, iPxFromDp2, 0, iPxFromDp2);
        textView.setTextColor(Color.rgb(51, 51, 51));
        textView.setText(OAuthLoginString.naveroauthlogin_string_msg_naverapp_download_desc.getString(this.mContext));
        textView.setTypeface(null, 1);
        textView.setTextSize(getTextSizeUpper());
        textView2.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        textView2.setPadding(0, 0, 0, iPxFromDp2);
        textView2.setText(OAuthLoginString.naveroauthlogin_string_msg_naverapp_download_link.getString(this.mContext));
        textView2.setTextColor(Color.rgb(45, 180, 0));
        textView2.setPaintFlags(textView2.getPaintFlags() | 8);
        textView2.setTextSize(getTextSizeUnder());
        linearLayout.addView(textView);
        linearLayout.addView(textView2);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.nhn.android.naverlogin.ui.view.OAuthLoginLayoutNaverAppDownloadBanner.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OAuthLoginLayoutNaverAppDownloadBanner.this.downloadNaverApp();
            }
        });
        return linearLayout;
    }

    private ImageView getNaverIconView() {
        ImageView imageView = new ImageView(this.mContext);
        Drawable drawableNaverIcon = OAuthLoginImage.getDrawableNaverIcon(getResources());
        int iPxFromDp = pxFromDp(10);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        imageView.setPadding(iPxFromDp, iPxFromDp, 0, iPxFromDp);
        imageView.setImageDrawable(drawableNaverIcon);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.nhn.android.naverlogin.ui.view.OAuthLoginLayoutNaverAppDownloadBanner.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OAuthLoginLayoutNaverAppDownloadBanner.this.downloadNaverApp();
            }
        });
        return imageView;
    }

    private float getTextSizeUnder() {
        if (DeviceDisplayInfo.isXhdpi(this.mDensityDpi)) {
            return 12.0f;
        }
        return DeviceDisplayInfo.isHdpi(this.mDensityDpi) ? 11.0f : 10.15f;
    }

    private float getTextSizeUpper() {
        if (DeviceDisplayInfo.isXhdpi(this.mDensityDpi)) {
            return 14.0f;
        }
        return DeviceDisplayInfo.isHdpi(this.mDensityDpi) ? 13.0f : 12.0f;
    }

    private void initData(Context context) {
        this.mContext = context;
        this.mDensity = this.mContext.getResources().getDisplayMetrics().density;
        this.mDensityDpi = this.mContext.getResources().getDisplayMetrics().densityDpi;
    }

    private void initView(AttributeSet attributeSet) {
        int i = Build.VERSION.SDK_INT >= 8 ? -1 : -1;
        setBackgroundColor(Color.rgb(254, 252, 227));
        setOrientation(0);
        setLayoutParams(new LinearLayout.LayoutParams(i, -2));
        addView(getNaverIconView());
        addView(getLayoutDownloadDesc());
        addView(getCloseBtnLayout());
    }

    private int pxFromDp(double d) {
        return (int) (((double) this.mDensity) * d);
    }
}
