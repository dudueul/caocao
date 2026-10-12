package com.nhn.android.naverlogin.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.OAuthLoginImage;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class OAuthLoginButton extends ImageButton {
    private static OAuthLoginHandler mOAuthLoginHandler;
    private int mBgDrawableResId;
    private Context mContext;

    public OAuthLoginButton(Context context) {
        super(context);
        this.mBgDrawableResId = -1;
        init(context);
    }

    public OAuthLoginButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBgDrawableResId = -1;
        init(context);
    }

    public OAuthLoginButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBgDrawableResId = -1;
        init(context);
    }

    private void init(Context context) {
        this.mBgDrawableResId = -1;
        this.mContext = context;
        setOnClickListener(new View.OnClickListener() { // from class: com.nhn.android.naverlogin.ui.view.OAuthLoginButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OAuthLogin.getInstance().startOauthLoginActivity((Activity) OAuthLoginButton.this.mContext, OAuthLoginButton.mOAuthLoginHandler);
            }
        });
    }

    private void setBgType() {
        Bitmap bitmapDecodeByteArray;
        BitmapDrawable bitmapDrawable;
        int height;
        if (-1 != this.mBgDrawableResId) {
            Drawable drawable = getResources().getDrawable(this.mBgDrawableResId);
            if (Build.VERSION.SDK_INT >= 16) {
                setBackground(drawable);
                return;
            } else {
                setBackgroundDrawable(drawable);
                return;
            }
        }
        byte[] bArrHexToByteArray = OAuthLoginImage.hexToByteArray(OAuthLoginImage.getDrawableByteStrLoginBtnImg());
        try {
            bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrHexToByteArray, 0, bArrHexToByteArray.length);
        } catch (Exception e) {
            bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrHexToByteArray, 0, bArrHexToByteArray.length, new BitmapFactory.Options());
        }
        try {
            int width = getLayoutParams().height;
            if (width > 0) {
                height = (int) ((((double) width) / ((double) bitmapDecodeByteArray.getHeight())) * ((double) bitmapDecodeByteArray.getWidth()));
            } else {
                height = getLayoutParams().width;
                if (height > 0) {
                    width = (int) ((((double) height) / ((double) bitmapDecodeByteArray.getWidth())) * ((double) bitmapDecodeByteArray.getHeight()));
                } else {
                    width = 80;
                    height = (int) ((((double) 80) / ((double) bitmapDecodeByteArray.getHeight())) * ((double) bitmapDecodeByteArray.getWidth()));
                }
            }
            bitmapDrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmapDecodeByteArray, height, width, true));
        } catch (Exception e2) {
            bitmapDrawable = new BitmapDrawable(getResources(), bitmapDecodeByteArray);
            e2.printStackTrace();
        }
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                setBackground(bitmapDrawable);
            } else {
                setBackgroundDrawable(bitmapDrawable);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (isInEditMode()) {
            return;
        }
        setBgType();
        super.onLayout(z, i, i2, i3, i4);
    }

    public void setBgResourceId(int i) {
        this.mBgDrawableResId = i;
    }

    @Deprecated
    public void setBgType(String str, String str2) {
        setBgType();
    }

    public void setOAuthLoginHandler(OAuthLoginHandler oAuthLoginHandler) {
        mOAuthLoginHandler = oAuthLoginHandler;
    }
}
