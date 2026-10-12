package kr.co.nexon.toy.android.ui.permission.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.util.NXResourcesUtil;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPermissionConfirmFullScreenView extends NXPLinearLayout {
    private Button confirmButton;
    private NXPPermissionConfirmListener confirmButtonClickListener;
    private NXClickListener onSwallowClickListener;
    private ImageView permissionAppImage;
    private TextView permissionContentsTextView;
    private TextView permissionFooterTextView;
    private TextView permissionHeaderTextView;

    public interface NXPPermissionConfirmListener {
        void onConfirm(View view);
    }

    public NXPPermissionConfirmFullScreenView(Context context) {
        super(context);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.permission.view.NXPPermissionConfirmFullScreenView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPPermissionConfirmFullScreenView.this.confirmButtonClickListener == null) {
                    ToyLog.d("NXPermissionConfirmListener is null");
                } else {
                    NXPPermissionConfirmFullScreenView.this.confirmButtonClickListener.onConfirm(view);
                }
            }
        };
    }

    public NXPPermissionConfirmFullScreenView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.permission.view.NXPPermissionConfirmFullScreenView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPPermissionConfirmFullScreenView.this.confirmButtonClickListener == null) {
                    ToyLog.d("NXPermissionConfirmListener is null");
                } else {
                    NXPPermissionConfirmFullScreenView.this.confirmButtonClickListener.onConfirm(view);
                }
            }
        };
    }

    public NXPPermissionConfirmFullScreenView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.permission.view.NXPPermissionConfirmFullScreenView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPPermissionConfirmFullScreenView.this.confirmButtonClickListener == null) {
                    ToyLog.d("NXPermissionConfirmListener is null");
                } else {
                    NXPPermissionConfirmFullScreenView.this.confirmButtonClickListener.onConfirm(view);
                }
            }
        };
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.permissionAppImage = (ImageView) findViewById(R.id.permission_app_image);
        this.permissionHeaderTextView = (TextView) findViewById(R.id.permission_header);
        this.permissionContentsTextView = (TextView) findViewById(R.id.permission_contents);
        this.permissionFooterTextView = (TextView) findViewById(R.id.permission_footer);
        this.confirmButton = (Button) findViewById(R.id.permission_confirm);
        this.confirmButton.setOnClickListener(this.onSwallowClickListener);
    }

    public void setAppImageResource(@Nullable Drawable drawable) {
        if (this.permissionAppImage != null) {
            this.permissionAppImage.setBackground(NXResourcesUtil.getDrawableByResId(getContext(), R.drawable.nxp_drawable_white));
            this.permissionAppImage.setImageDrawable(drawable);
        }
    }

    public void setConfirmButtonText(String str) {
        if (this.confirmButton != null) {
            this.confirmButton.setText(str);
        }
    }

    public void setDefaultImageResource() {
        if (this.permissionAppImage != null) {
            this.permissionAppImage.setBackground(NXResourcesUtil.getDrawableByResId(getContext(), R.drawable.permission_default_image_view_background));
            this.permissionAppImage.setImageResource(R.drawable.nexon_logo);
            this.permissionAppImage.setScaleType(ImageView.ScaleType.CENTER);
        }
    }

    public void setFooterText(String str) {
        if (this.permissionFooterTextView != null) {
            this.permissionFooterTextView.setText(str);
        }
    }

    public void setOnConfirmButtonClickListener(NXPPermissionConfirmListener nXPPermissionConfirmListener) {
        this.confirmButtonClickListener = nXPPermissionConfirmListener;
    }

    public void setPermissionContentsText(String str) {
        if (this.permissionContentsTextView != null) {
            this.permissionContentsTextView.setText(str);
        }
    }

    public void setTitle(String str) {
        if (this.permissionHeaderTextView != null) {
            this.permissionHeaderTextView.setText(str);
        }
    }

    public void setVisibilityImageResource(int i) {
        if (this.permissionAppImage != null) {
            this.permissionAppImage.setVisibility(i);
        }
    }
}
