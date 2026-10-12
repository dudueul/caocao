package kr.co.nexon.toy.android.ui.secondpassword.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXInputEditView;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.toy.android.ui.secondpassword.NXPSecondPasswordDialog;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSecondPasswordRegisterView extends NXPLinearLayout implements NXPSecondPasswordRegisterContract.View {
    private static final int INPUT_FILTER_MAX_LENGTH_PASSWORD = 6;
    private Button agreeTermsButton;
    private ImageButton backImageButton;
    private ImageButton closeImageButton;
    private Button confirmButton;
    private NXInputEditView confirmPasswordInputEditView;
    private NXInputEditView emailInputEditView;
    private NXClickListener onClickListener;
    private NXInputEditView passwordInputEditView;
    private NXPSecondPasswordRegisterContract.Presenter registerPresenter;
    private NXPSecondPasswordDialog secondPasswordDialog;
    private Button showTermsButton;
    private LinearLayout termsLayout;
    private TextView termsTitleTextView;
    private TextView titleTextView;

    public class NXPRegisterPasswordTextWatcher implements TextWatcher {
        private NXInputEditView editText;

        public NXPRegisterPasswordTextWatcher(NXInputEditView nXInputEditView) {
            this.editText = nXInputEditView;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (editable.length() == 6) {
                NXPSecondPasswordRegisterView.this.hideSoftKeyboard(this.editText);
            }
            NXPSecondPasswordRegisterView.this.checkNextButtonEnabled();
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public NXPSecondPasswordRegisterView(Context context) {
        super(context);
        this.onClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordRegisterView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.nxp_second_password_register_confirm) {
                    NXPSecondPasswordRegisterView.this.registerPresenter.onRegisterSecondPassword(NXPSecondPasswordRegisterView.this.emailInputEditView.getText(), NXPSecondPasswordRegisterView.this.passwordInputEditView.getText(), NXPSecondPasswordRegisterView.this.confirmPasswordInputEditView.getText());
                    return;
                }
                if (id == R.id.nxp_second_password_register_agree_terms) {
                    boolean z = !((Boolean) view.getTag()).booleanValue();
                    view.setTag(Boolean.valueOf(z));
                    if (z) {
                        view.setBackgroundResource(R.drawable.terms_agreement);
                    } else {
                        view.setBackgroundResource(R.drawable.terms_disagreement);
                    }
                    NXPSecondPasswordRegisterView.this.checkNextButtonEnabled();
                    return;
                }
                if (id == R.id.nxp_second_password_register_show_terms) {
                    NXPSecondPasswordRegisterView.this.registerPresenter.showTerms();
                } else if (id == R.id.closeBtn) {
                    NXPSecondPasswordRegisterView.this.registerPresenter.onCancel();
                }
            }
        };
    }

    public NXPSecondPasswordRegisterView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordRegisterView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.nxp_second_password_register_confirm) {
                    NXPSecondPasswordRegisterView.this.registerPresenter.onRegisterSecondPassword(NXPSecondPasswordRegisterView.this.emailInputEditView.getText(), NXPSecondPasswordRegisterView.this.passwordInputEditView.getText(), NXPSecondPasswordRegisterView.this.confirmPasswordInputEditView.getText());
                    return;
                }
                if (id == R.id.nxp_second_password_register_agree_terms) {
                    boolean z = !((Boolean) view.getTag()).booleanValue();
                    view.setTag(Boolean.valueOf(z));
                    if (z) {
                        view.setBackgroundResource(R.drawable.terms_agreement);
                    } else {
                        view.setBackgroundResource(R.drawable.terms_disagreement);
                    }
                    NXPSecondPasswordRegisterView.this.checkNextButtonEnabled();
                    return;
                }
                if (id == R.id.nxp_second_password_register_show_terms) {
                    NXPSecondPasswordRegisterView.this.registerPresenter.showTerms();
                } else if (id == R.id.closeBtn) {
                    NXPSecondPasswordRegisterView.this.registerPresenter.onCancel();
                }
            }
        };
    }

    public NXPSecondPasswordRegisterView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.onClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordRegisterView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.nxp_second_password_register_confirm) {
                    NXPSecondPasswordRegisterView.this.registerPresenter.onRegisterSecondPassword(NXPSecondPasswordRegisterView.this.emailInputEditView.getText(), NXPSecondPasswordRegisterView.this.passwordInputEditView.getText(), NXPSecondPasswordRegisterView.this.confirmPasswordInputEditView.getText());
                    return;
                }
                if (id == R.id.nxp_second_password_register_agree_terms) {
                    boolean z = !((Boolean) view.getTag()).booleanValue();
                    view.setTag(Boolean.valueOf(z));
                    if (z) {
                        view.setBackgroundResource(R.drawable.terms_agreement);
                    } else {
                        view.setBackgroundResource(R.drawable.terms_disagreement);
                    }
                    NXPSecondPasswordRegisterView.this.checkNextButtonEnabled();
                    return;
                }
                if (id == R.id.nxp_second_password_register_show_terms) {
                    NXPSecondPasswordRegisterView.this.registerPresenter.showTerms();
                } else if (id == R.id.closeBtn) {
                    NXPSecondPasswordRegisterView.this.registerPresenter.onCancel();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkNextButtonEnabled() {
        String text = this.emailInputEditView.getText();
        String text2 = this.passwordInputEditView.getText();
        String text3 = this.confirmPasswordInputEditView.getText();
        if (((Boolean) this.agreeTermsButton.getTag()).booleanValue() && NXStringUtil.isNotNull(text) && NXStringUtil.isNotNull(text2) && text2.length() == 6 && NXStringUtil.isNotNull(text3) && text3.length() == 6) {
            setConfirmButtonEnabled(true);
        } else {
            setConfirmButtonEnabled(false);
        }
    }

    public static View createView(Activity activity) {
        return View.inflate(activity, R.layout.nxp_second_password_register_view, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideSoftKeyboard(View view) {
        ((InputMethodManager) getContext().getSystemService("input_method")).toggleSoftInputFromWindow(view.getApplicationWindowToken(), 2, 0);
    }

    private void initEditText() {
        InputFilter[] inputFilterArr = {new InputFilter.LengthFilter(6)};
        NXInputEditView.NPTextChangedListener nPTextChangedListener = new NXInputEditView.NPTextChangedListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordRegisterView.2
            @Override // kr.co.nexon.mdev.android.view.NXInputEditView.NPTextChangedListener
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() <= 5) {
                    NXPSecondPasswordRegisterView.this.confirmPasswordInputEditView.setErrorMessage("");
                    NXPSecondPasswordRegisterView.this.confirmPasswordInputEditView.setMessageAreaTextViewVisibility(8);
                }
            }
        };
        TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordRegisterView.3
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                NXPSecondPasswordRegisterView.this.checkNextButtonEnabled();
                NXPSecondPasswordRegisterView.this.hideSoftKeyboard(textView);
                return true;
            }
        };
        this.emailInputEditView = (NXInputEditView) findViewById(R.id.nxp_second_password_register_input_email);
        this.emailInputEditView.setMessageAreaTextViewVisibility(8);
        this.emailInputEditView.setImeOption(-1879048186);
        this.emailInputEditView.setEditorActionListener(onEditorActionListener);
        this.emailInputEditView.setFilter(new InputFilter[]{new InputFilter.LengthFilter(100)});
        this.emailInputEditView.setListener(new NXInputEditView.NPTextChangedListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordRegisterView.4
            @Override // kr.co.nexon.mdev.android.view.NXInputEditView.NPTextChangedListener
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                NXPSecondPasswordRegisterView.this.emailInputEditView.setErrorMessage("");
                NXPSecondPasswordRegisterView.this.emailInputEditView.setMessageAreaTextViewVisibility(8);
            }
        });
        this.passwordInputEditView = (NXInputEditView) findViewById(R.id.nxp_second_password_register_input_password);
        this.passwordInputEditView.setMessageAreaTextViewVisibility(8);
        this.passwordInputEditView.setInputType(18);
        this.passwordInputEditView.setImeOption(-1879048186);
        this.passwordInputEditView.setFilter(inputFilterArr);
        this.passwordInputEditView.setEditorActionListener(onEditorActionListener);
        this.passwordInputEditView.addTextChangedListener(new NXPRegisterPasswordTextWatcher(this.passwordInputEditView));
        this.passwordInputEditView.setListener(nPTextChangedListener);
        this.confirmPasswordInputEditView = (NXInputEditView) findViewById(R.id.nxp_second_password_register_input_confirm_password);
        this.confirmPasswordInputEditView.setMessageAreaTextViewVisibility(8);
        this.confirmPasswordInputEditView.setInputType(18);
        this.confirmPasswordInputEditView.setImeOption(-1879048186);
        this.confirmPasswordInputEditView.setFilter(inputFilterArr);
        this.confirmPasswordInputEditView.setEditorActionListener(onEditorActionListener);
        this.confirmPasswordInputEditView.addTextChangedListener(new NXPRegisterPasswordTextWatcher(this.confirmPasswordInputEditView));
        this.confirmPasswordInputEditView.setListener(nPTextChangedListener);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public View getView() {
        return this;
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.termsTitleTextView = (TextView) findViewById(R.id.nxp_second_password_register_terms_title);
        this.confirmButton = (Button) findViewById(R.id.nxp_second_password_register_confirm);
        this.confirmButton.setOnClickListener(this.onClickListener);
        this.agreeTermsButton = (Button) findViewById(R.id.nxp_second_password_register_agree_terms);
        this.agreeTermsButton.setTag(false);
        this.agreeTermsButton.setOnClickListener(this.onClickListener);
        this.showTermsButton = (Button) findViewById(R.id.nxp_second_password_register_show_terms);
        this.showTermsButton.setOnClickListener(this.onClickListener);
        this.backImageButton = (ImageButton) findViewById(R.id.backBtn);
        this.backImageButton.setVisibility(4);
        this.closeImageButton = (ImageButton) findViewById(R.id.closeBtn);
        this.closeImageButton.setOnClickListener(this.onClickListener);
        this.termsLayout = (LinearLayout) findViewById(R.id.nxp_second_password_register_terms_layout);
        initEditText();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void onBackPressed() {
        this.registerPresenter.onCancel();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void onDismiss() {
        this.secondPasswordDialog.dismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.View
    public void setConfirmButtonEnabled(boolean z) {
        this.confirmButton.setEnabled(z);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.View
    public void setConfirmButtonText(String str) {
        this.confirmButton.setText(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.View
    public void setConfirmPasswordInputEditTextHint(String str) {
        this.confirmPasswordInputEditView.setHint(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.View
    public void setEmailId(String str) {
        if (this.registerPresenter == null || NXStringUtil.isNull(str)) {
            return;
        }
        this.emailInputEditView.setText(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.View
    public void setEmailInputEditTextHint(String str) {
        this.emailInputEditView.setHint(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.View
    public void setEmailInvalidErrorMessage(String str) {
        this.emailInputEditView.setMessageAreaTextViewVisibility(0);
        this.emailInputEditView.setErrorMessage(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.View
    public void setEmailTextEditable(boolean z) {
        this.emailInputEditView.setEditable(z);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void setFragment(Fragment fragment) {
        this.secondPasswordDialog = (NXPSecondPasswordDialog) fragment;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.View
    public void setPasswordInputEditTextHint(String str) {
        this.passwordInputEditView.setHint(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.View
    public void setPasswordInvalidErrorMessage(String str) {
        this.confirmPasswordInputEditView.setMessageAreaTextViewVisibility(0);
        this.confirmPasswordInputEditView.setErrorMessage(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void setPresenter(NXPSecondPasswordRegisterContract.Presenter presenter) {
        this.registerPresenter = presenter;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.View
    public void setTermsAgreementStatus(boolean z) {
        this.agreeTermsButton.setTag(Boolean.valueOf(z));
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.View
    public void setTermsLayoutVisibility(int i) {
        this.termsLayout.setVisibility(i);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.View
    public void setTermsTitle(String str) {
        this.termsTitleTextView.setText(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordRegisterContract.View
    public void setTitle(String str) {
        this.titleTextView.setText(str);
    }
}
