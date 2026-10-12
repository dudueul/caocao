package kr.co.nexon.toy.android.ui.secondpassword.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.toy.android.ui.secondpassword.NXPSecondPasswordDialog;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordSetupContract;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSecondPasswordSetupView extends NXPLinearLayout implements NXPSecondPasswordSetupContract.View {
    private ImageButton backImageButton;
    private Button changePasswordButton;
    private TextView changePasswordDescriptionTextView;
    private ImageButton closeImageButton;
    private NXClickListener onClickListener;
    private NXPSecondPasswordDialog secondPasswordDialog;
    private NXPSecondPasswordSetupContract.Presenter setupPresenter;
    private TextView titleTextView;
    private Button unregisterButton;
    private TextView unregisterDescriptionTextView;

    public NXPSecondPasswordSetupView(Context context) {
        super(context);
        this.onClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordSetupView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.nxp_second_password_setup_change_password_button) {
                    NXPSecondPasswordSetupView.this.setupPresenter.onChangeSecondPassword();
                } else if (id == R.id.nxp_second_password_setup_unregister_button) {
                    NXPSecondPasswordSetupView.this.setupPresenter.onUnregisterSecondPassword();
                } else if (id == R.id.closeBtn) {
                    NXPSecondPasswordSetupView.this.setupPresenter.onCancel();
                }
            }
        };
    }

    public NXPSecondPasswordSetupView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordSetupView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.nxp_second_password_setup_change_password_button) {
                    NXPSecondPasswordSetupView.this.setupPresenter.onChangeSecondPassword();
                } else if (id == R.id.nxp_second_password_setup_unregister_button) {
                    NXPSecondPasswordSetupView.this.setupPresenter.onUnregisterSecondPassword();
                } else if (id == R.id.closeBtn) {
                    NXPSecondPasswordSetupView.this.setupPresenter.onCancel();
                }
            }
        };
    }

    public NXPSecondPasswordSetupView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.onClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordSetupView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.nxp_second_password_setup_change_password_button) {
                    NXPSecondPasswordSetupView.this.setupPresenter.onChangeSecondPassword();
                } else if (id == R.id.nxp_second_password_setup_unregister_button) {
                    NXPSecondPasswordSetupView.this.setupPresenter.onUnregisterSecondPassword();
                } else if (id == R.id.closeBtn) {
                    NXPSecondPasswordSetupView.this.setupPresenter.onCancel();
                }
            }
        };
    }

    public static View createView(Activity activity) {
        return View.inflate(activity, R.layout.nxp_second_password_setup_view, null);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public View getView() {
        return this;
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.unregisterDescriptionTextView = (TextView) findViewById(R.id.nxp_second_password_setup_unregister_description);
        this.changePasswordDescriptionTextView = (TextView) findViewById(R.id.nxp_second_password_setup_change_password_description);
        this.unregisterButton = (Button) findViewById(R.id.nxp_second_password_setup_unregister_button);
        this.changePasswordButton = (Button) findViewById(R.id.nxp_second_password_setup_change_password_button);
        this.closeImageButton = (ImageButton) findViewById(R.id.closeBtn);
        this.backImageButton = (ImageButton) findViewById(R.id.backBtn);
        this.backImageButton.setVisibility(4);
        this.changePasswordButton.setOnClickListener(this.onClickListener);
        this.unregisterButton.setOnClickListener(this.onClickListener);
        this.closeImageButton.setOnClickListener(this.onClickListener);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void onBackPressed() {
        this.setupPresenter.onCancel();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void onDismiss() {
        this.secondPasswordDialog.dismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordSetupContract.View
    public void setChangePasswordButtonText(String str) {
        this.changePasswordButton.setText(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordSetupContract.View
    public void setChangePasswordDescription(String str) {
        this.changePasswordDescriptionTextView.setText(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void setFragment(Fragment fragment) {
        this.secondPasswordDialog = (NXPSecondPasswordDialog) fragment;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void setPresenter(NXPSecondPasswordSetupContract.Presenter presenter) {
        this.setupPresenter = presenter;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordSetupContract.View
    public void setTitle(String str) {
        this.titleTextView.setText(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordSetupContract.View
    public void setUnregisterButtonText(String str) {
        this.unregisterButton.setText(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordSetupContract.View
    public void setUnregisterDescription(String str) {
        this.unregisterDescriptionTextView.setText(str);
    }
}
