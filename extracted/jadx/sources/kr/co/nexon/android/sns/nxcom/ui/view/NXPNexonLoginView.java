package kr.co.nexon.android.sns.nxcom.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.npaccount.R;
import java.util.Arrays;
import java.util.List;
import kr.co.nexon.mdev.android.view.NXInputEditView;
import kr.co.nexon.mdev.android.view.NXPRelativeLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.toy.android.ui.auth.util.NPLoginUIUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPNexonLoginView extends NXPRelativeLayout {
    private View bottomContainerLayout;
    private ImageButton closeButton;
    private View.OnClickListener closeButtonClickListener;
    private NXInputEditView idInputView;
    private Button loginButton;
    private View.OnClickListener loginButtonClickListener;
    private View midContainer;
    private NXClickListener onSwallowClickListener;
    private NXInputEditView passwordInputView;
    private View.OnClickListener searchIdButtonClickListener;
    private TextView searchIdButtonTextView;
    private View.OnClickListener searchPwButtonClickListener;
    private TextView searchPwButtonTextView;
    private View.OnClickListener signUpButtonClickListener;
    private TextView signUpButtonTextView;
    private View signUpLayout;
    private TextView titleTextView;
    private View topContainer;
    private NXPTAPButtonClickListener tpaClickListener;

    public interface NXPTAPButtonClickListener {
        void onClick(int i);
    }

    public NXPNexonLoginView(Context context) {
        super(context);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.view.NXPNexonLoginView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                ToyLog.d("onClick, id:" + id + ", class:" + view.getClass());
                if (id == R.id.closeBtn) {
                    if (NXPNexonLoginView.this.closeButtonClickListener != null) {
                        NXPNexonLoginView.this.closeButtonClickListener.onClick(view);
                        return;
                    }
                    return;
                }
                if (id == R.id.nplogin_login_btn) {
                    if (NXPNexonLoginView.this.loginButtonClickListener != null) {
                        NXPNexonLoginView.this.loginButtonClickListener.onClick(view);
                        return;
                    }
                    return;
                }
                if (id == R.id.nplogin_join_btn) {
                    if (NXPNexonLoginView.this.signUpButtonClickListener != null) {
                        NXPNexonLoginView.this.signUpButtonClickListener.onClick(view);
                        return;
                    }
                    return;
                }
                if (id == R.id.nplogin_searchid_btn) {
                    if (NXPNexonLoginView.this.searchIdButtonClickListener != null) {
                        NXPNexonLoginView.this.searchIdButtonClickListener.onClick(view);
                    }
                } else {
                    if (id == R.id.nplogin_searchpw_btn) {
                        if (NXPNexonLoginView.this.searchPwButtonClickListener != null) {
                            NXPNexonLoginView.this.searchPwButtonClickListener.onClick(view);
                            return;
                        }
                        return;
                    }
                    Object tag = view.getTag();
                    if (tag == null || !(tag instanceof Number)) {
                        return;
                    }
                    int iIntValue = ((Number) view.getTag()).intValue();
                    if (NXPNexonLoginView.this.tpaClickListener != null) {
                        NXPNexonLoginView.this.tpaClickListener.onClick(iIntValue);
                    }
                }
            }
        };
    }

    public NXPNexonLoginView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.view.NXPNexonLoginView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                ToyLog.d("onClick, id:" + id + ", class:" + view.getClass());
                if (id == R.id.closeBtn) {
                    if (NXPNexonLoginView.this.closeButtonClickListener != null) {
                        NXPNexonLoginView.this.closeButtonClickListener.onClick(view);
                        return;
                    }
                    return;
                }
                if (id == R.id.nplogin_login_btn) {
                    if (NXPNexonLoginView.this.loginButtonClickListener != null) {
                        NXPNexonLoginView.this.loginButtonClickListener.onClick(view);
                        return;
                    }
                    return;
                }
                if (id == R.id.nplogin_join_btn) {
                    if (NXPNexonLoginView.this.signUpButtonClickListener != null) {
                        NXPNexonLoginView.this.signUpButtonClickListener.onClick(view);
                        return;
                    }
                    return;
                }
                if (id == R.id.nplogin_searchid_btn) {
                    if (NXPNexonLoginView.this.searchIdButtonClickListener != null) {
                        NXPNexonLoginView.this.searchIdButtonClickListener.onClick(view);
                    }
                } else {
                    if (id == R.id.nplogin_searchpw_btn) {
                        if (NXPNexonLoginView.this.searchPwButtonClickListener != null) {
                            NXPNexonLoginView.this.searchPwButtonClickListener.onClick(view);
                            return;
                        }
                        return;
                    }
                    Object tag = view.getTag();
                    if (tag == null || !(tag instanceof Number)) {
                        return;
                    }
                    int iIntValue = ((Number) view.getTag()).intValue();
                    if (NXPNexonLoginView.this.tpaClickListener != null) {
                        NXPNexonLoginView.this.tpaClickListener.onClick(iIntValue);
                    }
                }
            }
        };
    }

    public NXPNexonLoginView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.android.sns.nxcom.ui.view.NXPNexonLoginView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                ToyLog.d("onClick, id:" + id + ", class:" + view.getClass());
                if (id == R.id.closeBtn) {
                    if (NXPNexonLoginView.this.closeButtonClickListener != null) {
                        NXPNexonLoginView.this.closeButtonClickListener.onClick(view);
                        return;
                    }
                    return;
                }
                if (id == R.id.nplogin_login_btn) {
                    if (NXPNexonLoginView.this.loginButtonClickListener != null) {
                        NXPNexonLoginView.this.loginButtonClickListener.onClick(view);
                        return;
                    }
                    return;
                }
                if (id == R.id.nplogin_join_btn) {
                    if (NXPNexonLoginView.this.signUpButtonClickListener != null) {
                        NXPNexonLoginView.this.signUpButtonClickListener.onClick(view);
                        return;
                    }
                    return;
                }
                if (id == R.id.nplogin_searchid_btn) {
                    if (NXPNexonLoginView.this.searchIdButtonClickListener != null) {
                        NXPNexonLoginView.this.searchIdButtonClickListener.onClick(view);
                    }
                } else {
                    if (id == R.id.nplogin_searchpw_btn) {
                        if (NXPNexonLoginView.this.searchPwButtonClickListener != null) {
                            NXPNexonLoginView.this.searchPwButtonClickListener.onClick(view);
                            return;
                        }
                        return;
                    }
                    Object tag = view.getTag();
                    if (tag == null || !(tag instanceof Number)) {
                        return;
                    }
                    int iIntValue = ((Number) view.getTag()).intValue();
                    if (NXPNexonLoginView.this.tpaClickListener != null) {
                        NXPNexonLoginView.this.tpaClickListener.onClick(iIntValue);
                    }
                }
            }
        };
    }

    private Button createLoginButton(int i, boolean z) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        Button button = z ? (Button) layoutInflaterFrom.inflate(R.layout.nxp_tpa_login_select_button_wide, (ViewGroup) null) : (Button) layoutInflaterFrom.inflate(R.layout.nxp_tpa_login_select_button, (ViewGroup) null);
        button.setPadding(0, 0, 0, 0);
        int tPALoginSelectorResourceId = NPLoginUIUtil.getTPALoginSelectorResourceId(i);
        if (tPALoginSelectorResourceId == -1) {
            return null;
        }
        button.setBackgroundResource(tPALoginSelectorResourceId);
        button.setPadding(0, 0, 0, 0);
        button.setText(NPLoginUIUtil.getLoginButtonDisplayText(i));
        button.setTag(Integer.valueOf(i));
        button.setOnClickListener(this.onSwallowClickListener);
        return button;
    }

    private void initClickableView() {
        this.closeButton = (ImageButton) findViewById(R.id.closeBtn);
        if (this.closeButton != null) {
            this.closeButton.setOnClickListener(this.onSwallowClickListener);
        }
        this.loginButton = (Button) findViewById(R.id.nplogin_login_btn);
        if (this.loginButton != null) {
            this.loginButton.setOnClickListener(this.onSwallowClickListener);
        }
        this.signUpButtonTextView = (TextView) findViewById(R.id.nplogin_join_btn);
        if (this.signUpButtonTextView != null) {
            this.signUpButtonTextView.setOnClickListener(this.onSwallowClickListener);
        }
        this.searchIdButtonTextView = (TextView) findViewById(R.id.nplogin_searchid_btn);
        if (this.searchIdButtonTextView != null) {
            this.searchIdButtonTextView.setOnClickListener(this.onSwallowClickListener);
        }
        this.searchPwButtonTextView = (TextView) findViewById(R.id.nplogin_searchpw_btn);
        if (this.searchPwButtonTextView != null) {
            this.searchPwButtonTextView.setOnClickListener(this.onSwallowClickListener);
        }
        setMembershipList();
    }

    private void setMembershipList() {
        List listAsList = Arrays.asList(Integer.valueOf(NXToyLoginType.LoginTypeFaceBook.getValue()), Integer.valueOf(NXToyLoginType.LoginTypeGoogle.getValue()), Integer.valueOf(NXToyLoginType.LoginTypeNaver.getValue()));
        ToyLog.d("setMembershipList :" + listAsList);
        ((LinearLayout) findViewById(R.id.slot0)).addView(createLoginButton(((Integer) listAsList.get(0)).intValue(), true));
        ((LinearLayout) findViewById(R.id.slot1)).addView(createLoginButton(((Integer) listAsList.get(1)).intValue(), false));
        ((LinearLayout) findViewById(R.id.slot2)).addView(createLoginButton(((Integer) listAsList.get(2)).intValue(), false));
    }

    private void setMidContainerVisibility(int i) {
        if (this.midContainer != null) {
            this.midContainer.setVisibility(i);
        }
    }

    private void setTopContainerVisibility(int i) {
        if (this.topContainer != null) {
            this.topContainer.setVisibility(i);
        }
    }

    public void clearPasswordText() {
        if (this.passwordInputView != null) {
            this.passwordInputView.clearText();
        }
    }

    public String getIdText() {
        return this.idInputView != null ? this.idInputView.getText() : "";
    }

    public String getPasswordText() {
        return this.passwordInputView != null ? this.passwordInputView.getText() : "";
    }

    public byte[] getPasswordTextToBytes() {
        if (this.passwordInputView != null) {
            return this.passwordInputView.getTextToBytes();
        }
        return null;
    }

    @Override // kr.co.nexon.mdev.android.view.NXPRelativeLayout
    protected void initView() {
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.topContainer = findViewById(R.id.topContainer);
        this.midContainer = findViewById(R.id.midContainer);
        this.idInputView = (NXInputEditView) findViewById(R.id.etIdInput);
        this.idInputView.setImeOption(-1879048186);
        this.idInputView.setInputType(32);
        this.idInputView.setFocus();
        this.passwordInputView = (NXInputEditView) findViewById(R.id.etPasswordInput);
        this.passwordInputView.setImeOption(-1879048186);
        this.passwordInputView.setInputType(128);
        this.passwordInputView.setTransformationMethod();
        this.signUpLayout = findViewById(R.id.signup_layout);
        this.bottomContainerLayout = findViewById(R.id.bottomContainer);
        View viewFindViewById = findViewById(R.id.backBtn);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(4);
        }
        initClickableView();
    }

    public void setErrorMessage(String str) {
        if (this.passwordInputView != null) {
            this.passwordInputView.setErrorMessage(str);
        }
    }

    public void setIdEditHint(String str) {
        if (this.idInputView != null) {
            this.idInputView.setHint(str);
        }
    }

    public void setIdEditorActionListener(TextView.OnEditorActionListener onEditorActionListener) {
        if (this.idInputView != null) {
            this.idInputView.setEditorActionListener(onEditorActionListener);
        }
    }

    public void setIdErrorMessage(String str) {
        if (this.idInputView != null) {
            this.idInputView.setErrorMessage(str);
        }
    }

    public void setIdText(String str) {
        if (this.idInputView != null) {
            this.idInputView.setText(str);
        }
    }

    public void setIdTextChangeListener(NXInputEditView.NPTextChangedListener nPTextChangedListener) {
        if (this.idInputView != null) {
            this.idInputView.setListener(nPTextChangedListener);
        }
    }

    public void setLoginButton(String str, View.OnClickListener onClickListener) {
        if (this.loginButton != null) {
            this.loginButton.setText(str);
            this.loginButtonClickListener = onClickListener;
        }
    }

    public void setLoginButtonEnabled(boolean z) {
        if (this.loginButton != null) {
            this.loginButton.setEnabled(z);
        }
    }

    public void setLoginTPAButtonClickListener(NXPTAPButtonClickListener nXPTAPButtonClickListener) {
        this.tpaClickListener = nXPTAPButtonClickListener;
    }

    public void setOnCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.closeButtonClickListener = onClickListener;
    }

    public void setPasswordEditHint(String str) {
        if (this.passwordInputView != null) {
            this.passwordInputView.setHint(str);
        }
    }

    public void setPasswordEditorActionListener(TextView.OnEditorActionListener onEditorActionListener) {
        if (this.passwordInputView != null) {
            this.passwordInputView.setEditorActionListener(onEditorActionListener);
        }
    }

    public void setPasswordTextChangeListener(NXInputEditView.NPTextChangedListener nPTextChangedListener) {
        if (this.passwordInputView != null) {
            this.passwordInputView.setListener(nPTextChangedListener);
        }
    }

    public void setSearchIdButton(String str, View.OnClickListener onClickListener) {
        if (this.searchIdButtonTextView != null) {
            this.searchIdButtonTextView.setText(str);
            this.searchIdButtonClickListener = onClickListener;
        }
    }

    public void setSearchPwButton(String str, View.OnClickListener onClickListener) {
        if (this.searchPwButtonTextView != null) {
            this.searchPwButtonTextView.setText(str);
            this.searchPwButtonClickListener = onClickListener;
        }
    }

    public void setSignUpButton(String str, View.OnClickListener onClickListener) {
        if (this.signUpButtonTextView != null) {
            this.signUpButtonTextView.setText(str);
            this.signUpButtonClickListener = onClickListener;
        }
    }

    public void setSignUpVisibility(int i) {
        if (this.signUpLayout != null) {
            this.signUpLayout.setVisibility(i);
        }
    }

    public void setTPALoginVisibility(int i) {
        if (this.bottomContainerLayout != null) {
            this.bottomContainerLayout.setVisibility(i);
        }
    }

    public void setTitle(String str) {
        if (this.titleTextView != null) {
            this.titleTextView.setText(str);
        }
    }
}
