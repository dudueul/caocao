package kr.co.nexon.toy.android.ui.secondpassword.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.util.NXResourcesUtil;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.toy.android.ui.secondpassword.NXPSecondPasswordDialog;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSecondPasswordPendingView extends NXPLinearLayout implements NXPSecondPasswordPendingContract.View {
    private ImageButton backImageButton;
    private ImageButton closeImageButton;
    private TextView csButton;
    private TextView emailIdTextView;
    private NXClickListener onClickListener;
    private NXPSecondPasswordPendingContract.Presenter pendingPresenter;
    private TextView resendRegisteredEmailTextView;
    private NXPSecondPasswordDialog secondPasswordDialog;
    private TextView titleTextView;
    private TextView topDescription;

    public NXPSecondPasswordPendingView(Context context) {
        super(context);
        this.onClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordPendingView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.nxp_second_password_pending_cs_button) {
                    NXPSecondPasswordPendingView.this.pendingPresenter.showCS();
                } else if (id == R.id.closeBtn) {
                    NXPSecondPasswordPendingView.this.pendingPresenter.onCancel();
                }
            }
        };
    }

    public NXPSecondPasswordPendingView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordPendingView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.nxp_second_password_pending_cs_button) {
                    NXPSecondPasswordPendingView.this.pendingPresenter.showCS();
                } else if (id == R.id.closeBtn) {
                    NXPSecondPasswordPendingView.this.pendingPresenter.onCancel();
                }
            }
        };
    }

    public NXPSecondPasswordPendingView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.onClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordPendingView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.nxp_second_password_pending_cs_button) {
                    NXPSecondPasswordPendingView.this.pendingPresenter.showCS();
                } else if (id == R.id.closeBtn) {
                    NXPSecondPasswordPendingView.this.pendingPresenter.onCancel();
                }
            }
        };
    }

    public static View createView(Activity activity) {
        return View.inflate(activity, R.layout.nxp_second_password_pending_view, null);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public View getView() {
        return this;
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.topDescription = (TextView) findViewById(R.id.nxp_second_password_pending_confirm_description);
        this.emailIdTextView = (TextView) findViewById(R.id.nxp_second_password_pending_email);
        this.resendRegisteredEmailTextView = (TextView) findViewById(R.id.nxp_second_password_pending_view_resend_description);
        this.csButton = (TextView) findViewById(R.id.nxp_second_password_pending_cs_button);
        this.csButton.setOnClickListener(this.onClickListener);
        this.closeImageButton = (ImageButton) findViewById(R.id.closeBtn);
        this.backImageButton = (ImageButton) findViewById(R.id.backBtn);
        this.closeImageButton.setOnClickListener(this.onClickListener);
        this.backImageButton.setVisibility(4);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void onBackPressed() {
        this.pendingPresenter.onCancel();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void onDismiss() {
        this.secondPasswordDialog.dismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract.View
    public void setCSButtonText(String str) {
        this.csButton.setText(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract.View
    public void setEmailId(String str) {
        this.emailIdTextView.setText(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void setFragment(Fragment fragment) {
        this.secondPasswordDialog = (NXPSecondPasswordDialog) fragment;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void setPresenter(NXPSecondPasswordPendingContract.Presenter presenter) {
        this.pendingPresenter = presenter;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract.View
    public void setResendRegisteredEmailDescription(String str, String str2) {
        SpannableString spannableString = new SpannableString(str);
        int iIndexOf = str.indexOf(str2);
        int length = iIndexOf + str2.length();
        spannableString.setSpan(new ClickableSpan() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordPendingView.2
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                NXPSecondPasswordPendingView.this.pendingPresenter.onResendEmail();
            }
        }, iIndexOf, length, 33);
        spannableString.setSpan(new UnderlineSpan(), iIndexOf, length, 33);
        spannableString.setSpan(new StyleSpan(1), iIndexOf, length, 33);
        spannableString.setSpan(new ForegroundColorSpan(NXResourcesUtil.getColorByResId(getContext(), R.color.nxp_color_black_normal)), iIndexOf, length, 33);
        this.resendRegisteredEmailTextView.setText(spannableString);
        this.resendRegisteredEmailTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract.View
    public void setTitle(String str) {
        this.titleTextView.setText(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordPendingContract.View
    public void setTopDescription(String str) {
        this.topDescription.setText(str);
    }
}
