package kr.co.nexon.toy.android.ui.auth.view;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kr.co.nexon.mdev.android.view.NXInputEditView;
import kr.co.nexon.mdev.android.view.NXPRelativeLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.toy.android.ui.auth.util.NPLoginUIUtil;
import kr.co.nexon.toy.android.ui.common.NPScrollView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPLoginSelectView extends NXPRelativeLayout {
    private ImageButton closeButton;
    private NXInputEditView idInputView;
    private boolean isTPAMode;
    private NXPLoginButtonClickListener loginButtonClickListener;
    private TextView loginDescriptionTextView;
    private TextView loginTitleTextView;
    private Button nexonLoginButton;
    private NXClickListener nexonLoginButtonClickListener;
    private NXClickListener onSwallowClickListener;
    private NXInputEditView passwordInputView;
    private View.OnClickListener searchIdButtonClickListener;
    private TextView searchIdButtonTextView;
    private View.OnClickListener searchPwButtonClickListener;
    private TextView searchPwButtonTextView;
    private View.OnClickListener signUpButtonClickListener;
    private TextView signUpButtonTextView;

    public interface NXPLoginButtonClickListener {
        void onClose();

        void onLogin(String str);
    }

    public NXPLoginSelectView(Context context) {
        super(context);
        this.isTPAMode = false;
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.view.NXPLoginSelectView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPLoginSelectView.this.loginButtonClickListener == null) {
                    ToyLog.d("NXPLoginViewStateChnageListener is null");
                    return;
                }
                int id = view.getId();
                if (id == R.id.login_button) {
                    NXPLoginSelectView.this.loginButtonClickListener.onLogin(String.valueOf(view.getTag()));
                    return;
                }
                if (id == R.id.nplogin_login_btn) {
                    if (NXPLoginSelectView.this.nexonLoginButtonClickListener != null) {
                        NXPLoginSelectView.this.nexonLoginButtonClickListener.onClick(view);
                        return;
                    }
                    return;
                }
                if (id == R.id.closeBtn) {
                    NXPLoginSelectView.this.loginButtonClickListener.onClose();
                    return;
                }
                if (id == R.id.nplogin_join_btn) {
                    if (NXPLoginSelectView.this.signUpButtonClickListener != null) {
                        NXPLoginSelectView.this.signUpButtonClickListener.onClick(view);
                    }
                } else if (id == R.id.nplogin_searchid_btn) {
                    if (NXPLoginSelectView.this.searchIdButtonClickListener != null) {
                        NXPLoginSelectView.this.searchIdButtonClickListener.onClick(view);
                    }
                } else {
                    if (id != R.id.nplogin_searchpw_btn || NXPLoginSelectView.this.searchPwButtonClickListener == null) {
                        return;
                    }
                    NXPLoginSelectView.this.searchPwButtonClickListener.onClick(view);
                }
            }
        };
    }

    public NXPLoginSelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isTPAMode = false;
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.view.NXPLoginSelectView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPLoginSelectView.this.loginButtonClickListener == null) {
                    ToyLog.d("NXPLoginViewStateChnageListener is null");
                    return;
                }
                int id = view.getId();
                if (id == R.id.login_button) {
                    NXPLoginSelectView.this.loginButtonClickListener.onLogin(String.valueOf(view.getTag()));
                    return;
                }
                if (id == R.id.nplogin_login_btn) {
                    if (NXPLoginSelectView.this.nexonLoginButtonClickListener != null) {
                        NXPLoginSelectView.this.nexonLoginButtonClickListener.onClick(view);
                        return;
                    }
                    return;
                }
                if (id == R.id.closeBtn) {
                    NXPLoginSelectView.this.loginButtonClickListener.onClose();
                    return;
                }
                if (id == R.id.nplogin_join_btn) {
                    if (NXPLoginSelectView.this.signUpButtonClickListener != null) {
                        NXPLoginSelectView.this.signUpButtonClickListener.onClick(view);
                    }
                } else if (id == R.id.nplogin_searchid_btn) {
                    if (NXPLoginSelectView.this.searchIdButtonClickListener != null) {
                        NXPLoginSelectView.this.searchIdButtonClickListener.onClick(view);
                    }
                } else {
                    if (id != R.id.nplogin_searchpw_btn || NXPLoginSelectView.this.searchPwButtonClickListener == null) {
                        return;
                    }
                    NXPLoginSelectView.this.searchPwButtonClickListener.onClick(view);
                }
            }
        };
    }

    public NXPLoginSelectView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isTPAMode = false;
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.view.NXPLoginSelectView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPLoginSelectView.this.loginButtonClickListener == null) {
                    ToyLog.d("NXPLoginViewStateChnageListener is null");
                    return;
                }
                int id = view.getId();
                if (id == R.id.login_button) {
                    NXPLoginSelectView.this.loginButtonClickListener.onLogin(String.valueOf(view.getTag()));
                    return;
                }
                if (id == R.id.nplogin_login_btn) {
                    if (NXPLoginSelectView.this.nexonLoginButtonClickListener != null) {
                        NXPLoginSelectView.this.nexonLoginButtonClickListener.onClick(view);
                        return;
                    }
                    return;
                }
                if (id == R.id.closeBtn) {
                    NXPLoginSelectView.this.loginButtonClickListener.onClose();
                    return;
                }
                if (id == R.id.nplogin_join_btn) {
                    if (NXPLoginSelectView.this.signUpButtonClickListener != null) {
                        NXPLoginSelectView.this.signUpButtonClickListener.onClick(view);
                    }
                } else if (id == R.id.nplogin_searchid_btn) {
                    if (NXPLoginSelectView.this.searchIdButtonClickListener != null) {
                        NXPLoginSelectView.this.searchIdButtonClickListener.onClick(view);
                    }
                } else {
                    if (id != R.id.nplogin_searchpw_btn || NXPLoginSelectView.this.searchPwButtonClickListener == null) {
                        return;
                    }
                    NXPLoginSelectView.this.searchPwButtonClickListener.onClick(view);
                }
            }
        };
    }

    private Button createLoginButton(int i, boolean z, boolean z2) {
        int loginSelectorResourceId;
        Button button;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        if (z) {
            loginSelectorResourceId = NPLoginUIUtil.getTPALoginSelectorResourceId(i);
            button = z2 ? (Button) layoutInflaterFrom.inflate(R.layout.nxp_tpa_login_select_button_wide, (ViewGroup) null) : (Button) layoutInflaterFrom.inflate(R.layout.nxp_tpa_login_select_button, (ViewGroup) null);
        } else {
            loginSelectorResourceId = NPLoginUIUtil.getLoginSelectorResourceId(i);
            button = (Button) layoutInflaterFrom.inflate(R.layout.nxp_login_select_view_button, (ViewGroup) null);
        }
        if (loginSelectorResourceId == -1) {
            return null;
        }
        button.setBackgroundResource(loginSelectorResourceId);
        button.setPadding(0, 0, 0, 0);
        button.setText(NPLoginUIUtil.getLoginButtonDisplayText(i));
        button.setTag(Integer.valueOf(i));
        button.setOnClickListener(this.onSwallowClickListener);
        return button;
    }

    private void drawLinear(List<Button> list) {
        if (list.size() >= 5) {
            ((NPScrollView) findViewById(R.id.login_select_scrollview)).setScrollEnabled(true);
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.midContainer);
        for (int i = 0; i < list.size(); i++) {
            linearLayout.addView(list.get(i));
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

    public void initTPAView() {
        this.nexonLoginButton = (Button) findViewById(R.id.nplogin_login_btn);
        if (this.nexonLoginButton != null) {
            this.nexonLoginButton.setOnClickListener(this.onSwallowClickListener);
        }
        this.idInputView = (NXInputEditView) findViewById(R.id.etIdInput);
        this.idInputView.setImeOption(-1879048186);
        this.idInputView.setInputType(32);
        this.idInputView.setFocus();
        this.passwordInputView = (NXInputEditView) findViewById(R.id.etPasswordInput);
        this.passwordInputView.setImeOption(-1879048186);
        this.passwordInputView.setInputType(128);
        this.passwordInputView.setTransformationMethod();
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
    }

    @Override // kr.co.nexon.mdev.android.view.NXPRelativeLayout
    protected void initView() {
        this.loginTitleTextView = (TextView) findViewById(R.id.tvLoginTitleArea);
        this.loginDescriptionTextView = (TextView) findViewById(R.id.tvMessageArea);
        this.closeButton = (ImageButton) findViewById(R.id.closeBtn);
        this.closeButton.setOnClickListener(this.onSwallowClickListener);
    }

    public void setDescriptionText(String str) {
        if (this.loginDescriptionTextView != null) {
            this.loginDescriptionTextView.setText(str);
        }
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

    public void setIsTPAMode(boolean z) {
        this.isTPAMode = z;
    }

    public void setLoginViewStateChangeListener(NXPLoginButtonClickListener nXPLoginButtonClickListener) {
        this.loginButtonClickListener = nXPLoginButtonClickListener;
    }

    public void setMembershipList(List<Integer> list) {
        ToyLog.d("setMembershipList :" + list);
        if (list == null) {
            ToyLog.d("membershipList is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Button buttonCreateLoginButton = createLoginButton(list.get(i).intValue(), false, false);
            if (buttonCreateLoginButton != null) {
                arrayList.add(buttonCreateLoginButton);
            }
        }
        drawLinear(arrayList);
    }

    public void setNexonLoginButton(String str, NXClickListener nXClickListener) {
        if (this.nexonLoginButton != null) {
            this.nexonLoginButton.setText(str);
            this.nexonLoginButtonClickListener = nXClickListener;
        }
    }

    public void setNexonLoginButtonEnabled(boolean z) {
        if (this.nexonLoginButton != null) {
            this.nexonLoginButton.setEnabled(z);
        }
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

    public void setTPAMembershipList(List<Integer> list) {
        if (list == null || list.size() == 0) {
            findViewById(R.id.midContainer).setVisibility(8);
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(iIntValue)) {
                case LoginTypeFaceBook:
                case LoginTypeGoogle:
                case LoginTypeNaver:
                    arrayList.add(Integer.valueOf(iIntValue));
                    break;
            }
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.midContainer);
        if (arrayList.size() == 0) {
            linearLayout.setVisibility(8);
            return;
        }
        if (arrayList.size() >= 3) {
            ((LinearLayout) linearLayout.findViewById(R.id.slot0)).addView(createLoginButton(((Integer) arrayList.get(0)).intValue(), true, true));
            ((LinearLayout) linearLayout.findViewById(R.id.slot1)).addView(createLoginButton(((Integer) arrayList.get(1)).intValue(), true, false));
            ((LinearLayout) linearLayout.findViewById(R.id.slot2)).addView(createLoginButton(((Integer) arrayList.get(2)).intValue(), true, false));
        } else if (arrayList.size() == 2) {
            ((LinearLayout) linearLayout.findViewById(R.id.slot0)).setVisibility(8);
            ((LinearLayout) linearLayout.findViewById(R.id.slot1)).addView(createLoginButton(((Integer) arrayList.get(0)).intValue(), true, false));
            ((LinearLayout) linearLayout.findViewById(R.id.slot2)).addView(createLoginButton(((Integer) arrayList.get(1)).intValue(), true, false));
        } else if (arrayList.size() == 1) {
            ((LinearLayout) linearLayout.findViewById(R.id.slot0)).addView(createLoginButton(((Integer) arrayList.get(0)).intValue(), true, true));
        }
    }

    public void setTitleText(String str) {
        if (this.loginTitleTextView != null) {
            this.loginTitleTextView.setText(str);
        }
    }
}
