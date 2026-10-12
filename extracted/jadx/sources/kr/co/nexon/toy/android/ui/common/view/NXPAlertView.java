package kr.co.nexon.toy.android.ui.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.toy.android.ui.common.NXPAlertViewBase;
import kr.co.nexon.utility.StringUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAlertView extends NXPAlertViewBase {
    private TextView contentsTextView;
    private View headerSeperatorView;
    private View.OnClickListener negativeBtnClickListener;
    private Button negativeButton;
    private NXClickListener onSwallowClickListener;
    private View.OnClickListener positiveBtnClickListener;
    private Button positiveButton;
    private TextView titleTextView;

    public NXPAlertView(Context context) {
        super(context);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.common.view.NXPAlertView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.alert_btn_positive) {
                    if (NXPAlertView.this.positiveBtnClickListener != null) {
                        NXPAlertView.this.positiveBtnClickListener.onClick(view);
                    }
                } else {
                    if (id != R.id.alert_btn_negative || NXPAlertView.this.negativeBtnClickListener == null) {
                        return;
                    }
                    NXPAlertView.this.negativeBtnClickListener.onClick(view);
                }
            }
        };
    }

    public NXPAlertView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.common.view.NXPAlertView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.alert_btn_positive) {
                    if (NXPAlertView.this.positiveBtnClickListener != null) {
                        NXPAlertView.this.positiveBtnClickListener.onClick(view);
                    }
                } else {
                    if (id != R.id.alert_btn_negative || NXPAlertView.this.negativeBtnClickListener == null) {
                        return;
                    }
                    NXPAlertView.this.negativeBtnClickListener.onClick(view);
                }
            }
        };
    }

    public NXPAlertView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.common.view.NXPAlertView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.alert_btn_positive) {
                    if (NXPAlertView.this.positiveBtnClickListener != null) {
                        NXPAlertView.this.positiveBtnClickListener.onClick(view);
                    }
                } else {
                    if (id != R.id.alert_btn_negative || NXPAlertView.this.negativeBtnClickListener == null) {
                        return;
                    }
                    NXPAlertView.this.negativeBtnClickListener.onClick(view);
                }
            }
        };
    }

    private void adjustHeaderView(boolean z) {
        int i = z ? 0 : 8;
        if (this.titleTextView != null) {
            this.titleTextView.setVisibility(i);
        }
        if (this.headerSeperatorView != null) {
            this.headerSeperatorView.setVisibility(i);
        }
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.alert_dialog_title);
        this.contentsTextView = (TextView) findViewById(R.id.alert_text_contents);
        this.headerSeperatorView = findViewById(R.id.alert_header_line);
        this.positiveButton = (Button) findViewById(R.id.alert_btn_positive);
        this.positiveButton.setOnClickListener(this.onSwallowClickListener);
        this.negativeButton = (Button) findViewById(R.id.alert_btn_negative);
        this.negativeButton.setOnClickListener(this.onSwallowClickListener);
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPAlertViewInterface
    public void setMessage(String str) {
        if (this.contentsTextView != null) {
            this.contentsTextView.setText(str);
        }
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPAlertViewInterface
    public void setNegativeButtonClickListener(View.OnClickListener onClickListener) {
        this.negativeBtnClickListener = onClickListener;
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPAlertViewInterface
    public void setNegativeButtonText(String str) {
        if (this.negativeButton == null) {
            ToyLog.d("negativeButton is null");
        } else if (StringUtil.isNotNull(str)) {
            this.negativeButton.setText(str);
            this.negativeButton.setVisibility(0);
        }
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPAlertViewInterface
    public void setPositiveButtonClickListener(View.OnClickListener onClickListener) {
        this.positiveBtnClickListener = onClickListener;
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPAlertViewInterface
    public void setPositiveButtonText(String str) {
        if (this.positiveButton == null) {
            ToyLog.d("positiveButton is null");
        } else if (StringUtil.isNotNull(str)) {
            this.positiveButton.setText(str);
        }
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPAlertViewInterface
    public void setTitle(String str) {
        if (this.titleTextView == null) {
            ToyLog.d("titleTextview is null");
        } else {
            this.titleTextView.setText(str);
            adjustHeaderView(NXStringUtil.isNotNull(str));
        }
    }
}
