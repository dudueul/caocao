package kr.co.nexon.toy.android.ui.common.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.toy.android.ui.common.NXPAlertViewBase;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPFullAlertView extends NXPAlertViewBase {
    private View.OnClickListener closeButtonClickListener;
    private TextView contentsTextView;
    private Button negativeButton;
    private View.OnClickListener negativeButtonClickListener;
    private NXClickListener onSwallowClickListener;
    private Button positiveButton;
    private View.OnClickListener positiveButtonClickListener;
    private TextView titleTextView;

    public NXPFullAlertView(Context context) {
        super(context);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.common.view.NXPFullAlertView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.closeBtn) {
                    if (NXPFullAlertView.this.closeButtonClickListener != null) {
                        NXPFullAlertView.this.closeButtonClickListener.onClick(view);
                    }
                } else if (id == R.id.fullAlertViewNegativeButton) {
                    if (NXPFullAlertView.this.negativeButtonClickListener != null) {
                        NXPFullAlertView.this.negativeButtonClickListener.onClick(view);
                    }
                } else {
                    if (id != R.id.fullAlertViewPositiveButton || NXPFullAlertView.this.positiveButtonClickListener == null) {
                        return;
                    }
                    NXPFullAlertView.this.positiveButtonClickListener.onClick(view);
                }
            }
        };
    }

    public NXPFullAlertView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.common.view.NXPFullAlertView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.closeBtn) {
                    if (NXPFullAlertView.this.closeButtonClickListener != null) {
                        NXPFullAlertView.this.closeButtonClickListener.onClick(view);
                    }
                } else if (id == R.id.fullAlertViewNegativeButton) {
                    if (NXPFullAlertView.this.negativeButtonClickListener != null) {
                        NXPFullAlertView.this.negativeButtonClickListener.onClick(view);
                    }
                } else {
                    if (id != R.id.fullAlertViewPositiveButton || NXPFullAlertView.this.positiveButtonClickListener == null) {
                        return;
                    }
                    NXPFullAlertView.this.positiveButtonClickListener.onClick(view);
                }
            }
        };
    }

    public NXPFullAlertView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.common.view.NXPFullAlertView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.closeBtn) {
                    if (NXPFullAlertView.this.closeButtonClickListener != null) {
                        NXPFullAlertView.this.closeButtonClickListener.onClick(view);
                    }
                } else if (id == R.id.fullAlertViewNegativeButton) {
                    if (NXPFullAlertView.this.negativeButtonClickListener != null) {
                        NXPFullAlertView.this.negativeButtonClickListener.onClick(view);
                    }
                } else {
                    if (id != R.id.fullAlertViewPositiveButton || NXPFullAlertView.this.positiveButtonClickListener == null) {
                        return;
                    }
                    NXPFullAlertView.this.positiveButtonClickListener.onClick(view);
                }
            }
        };
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.contentsTextView = (TextView) findViewById(R.id.fullAlertViewContentsTextView);
        this.positiveButton = (Button) findViewById(R.id.fullAlertViewPositiveButton);
        if (this.positiveButton != null) {
            this.positiveButton.setOnClickListener(this.onSwallowClickListener);
        }
        this.negativeButton = (Button) findViewById(R.id.fullAlertViewNegativeButton);
        if (this.negativeButton != null) {
            this.negativeButton.setOnClickListener(this.onSwallowClickListener);
        }
        View viewFindViewById = findViewById(R.id.closeBtn);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(this.onSwallowClickListener);
        }
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPAlertViewInterface
    public void setMessage(String str) {
        if (this.contentsTextView != null) {
            this.contentsTextView.setText(str);
        }
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPAlertViewInterface
    public void setNegativeButtonClickListener(View.OnClickListener onClickListener) {
        this.negativeButtonClickListener = onClickListener;
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPAlertViewInterface
    public void setNegativeButtonText(String str) {
        if (this.negativeButton != null) {
            this.negativeButton.setText(str);
            this.negativeButton.setVisibility(0);
        }
    }

    public void setOnCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.closeButtonClickListener = onClickListener;
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPAlertViewInterface
    public void setPositiveButtonClickListener(View.OnClickListener onClickListener) {
        this.positiveButtonClickListener = onClickListener;
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPAlertViewInterface
    public void setPositiveButtonText(String str) {
        if (this.positiveButton != null) {
            this.positiveButton.setText(str);
            this.positiveButton.setVisibility(0);
        }
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPAlertViewInterface
    public void setTitle(String str) {
        if (this.titleTextView != null) {
            this.titleTextView.setText(str);
        }
    }
}
