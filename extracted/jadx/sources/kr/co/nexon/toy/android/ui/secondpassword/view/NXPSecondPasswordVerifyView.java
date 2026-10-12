package kr.co.nexon.toy.android.ui.secondpassword.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kr.co.nexon.mdev.android.util.NXGraphicUtil;
import kr.co.nexon.mdev.android.util.NXResourcesUtil;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.toy.android.ui.secondpassword.NXPSecondPasswordDialog;
import kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSecondPasswordVerifyView extends NXPLinearLayout implements NXPSecondPasswordVerifyContract.View, TextWatcher {
    private static final int PASSWORD_MAX_LENGTH = 6;
    private static final int VIEW_TYPE_UNREGISTER = 1;
    private TextView changePasswordButton;
    private ImageButton closeImageButton;
    private int errorTextColor;
    private TextView errorTextView;
    private NXClickListener onClickListener;
    private NXPPasswordEditText passwordEditText;
    private LinearLayout passwordLayout;
    private int passwordViewDisabledColor;
    private int passwordViewEnabledColor;
    private List<ImageView> passwordViewList;
    private NXPSecondPasswordDialog secondPasswordDialog;
    private String subDescriptionText;
    private int subDescriptionTextColor;
    private TextView titleTextView;
    private NXPSecondPasswordVerifyContract.Presenter verifyPresenter;
    private int viewType;

    public NXPSecondPasswordVerifyView(Context context) {
        super(context);
        this.subDescriptionText = null;
        this.onClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordVerifyView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                NXPSecondPasswordVerifyView.this.hideSoftKeyboard();
                if (id == R.id.nxp_second_password_verify_change_password) {
                    NXPSecondPasswordVerifyView.this.verifyPresenter.showChangePasswordView();
                } else if (id == R.id.nxp_second_password_verify_close) {
                    NXPSecondPasswordVerifyView.this.verifyPresenter.onCancel();
                }
            }
        };
    }

    public NXPSecondPasswordVerifyView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.subDescriptionText = null;
        this.onClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordVerifyView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                NXPSecondPasswordVerifyView.this.hideSoftKeyboard();
                if (id == R.id.nxp_second_password_verify_change_password) {
                    NXPSecondPasswordVerifyView.this.verifyPresenter.showChangePasswordView();
                } else if (id == R.id.nxp_second_password_verify_close) {
                    NXPSecondPasswordVerifyView.this.verifyPresenter.onCancel();
                }
            }
        };
    }

    public NXPSecondPasswordVerifyView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.subDescriptionText = null;
        this.onClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordVerifyView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                NXPSecondPasswordVerifyView.this.hideSoftKeyboard();
                if (id == R.id.nxp_second_password_verify_change_password) {
                    NXPSecondPasswordVerifyView.this.verifyPresenter.showChangePasswordView();
                } else if (id == R.id.nxp_second_password_verify_close) {
                    NXPSecondPasswordVerifyView.this.verifyPresenter.onCancel();
                }
            }
        };
    }

    public static View createView(Activity activity) {
        return View.inflate(activity, R.layout.nxp_second_password_verify_view, null);
    }

    private int getColor(int i) {
        return NXResourcesUtil.getColorByResId(getContext(), i);
    }

    private ColorStateList getColorStateList(int i) {
        return NXResourcesUtil.getColorStateListByResId(getContext(), i);
    }

    private Drawable getDrawable(int i) {
        return NXResourcesUtil.getDrawableByResId(getContext(), i);
    }

    private void setAllPasswordViewColor(int i) {
        Iterator<ImageView> it = this.passwordViewList.iterator();
        while (it.hasNext()) {
            ((GradientDrawable) it.next().getBackground()).setColor(i);
        }
    }

    private void setPasswordView() {
        for (int i = 0; i < this.passwordLayout.getChildCount(); i++) {
            this.passwordViewList.add((ImageView) this.passwordLayout.getChildAt(i));
        }
    }

    private void setTargetPasswordViewColor(ImageView imageView, int i) {
        ((GradientDrawable) imageView.getBackground()).setColor(i);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (editable.length() == 6) {
            this.verifyPresenter.onVerifySecondPassword(editable.toString());
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public View getView() {
        return this;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract.View
    public void hideSoftKeyboard() {
        this.passwordEditText.postDelayed(new Runnable() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordVerifyView.5
            @Override // java.lang.Runnable
            public void run() {
                ((InputMethodManager) NXPSecondPasswordVerifyView.this.getContext().getSystemService("input_method")).hideSoftInputFromWindow(NXPSecondPasswordVerifyView.this.passwordEditText.getWindowToken(), 1);
            }
        }, 50L);
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.passwordViewList = new ArrayList();
        this.titleTextView = (TextView) findViewById(R.id.nxp_second_password_verify_title);
        this.errorTextView = (TextView) findViewById(R.id.nxp_second_password_verify_description_or_error);
        this.closeImageButton = (ImageButton) findViewById(R.id.nxp_second_password_verify_close);
        this.closeImageButton.setOnClickListener(this.onClickListener);
        this.changePasswordButton = (TextView) findViewById(R.id.nxp_second_password_verify_change_password);
        this.changePasswordButton.setOnClickListener(this.onClickListener);
        this.passwordLayout = (LinearLayout) findViewById(R.id.nxp_second_password_password_view);
        this.passwordEditText = (NXPPasswordEditText) findViewById(R.id.nxp_second_password_verify_password_edit_text);
        this.passwordEditText.addTextChangedListener(this);
        this.passwordEditText.setOnBackPressListener(new NXPPasswordEditText.OnBackPressListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordVerifyView.2
            @Override // kr.co.nexon.toy.android.ui.secondpassword.view.NXPPasswordEditText.OnBackPressListener
            public void onBackPress() {
                NXPSecondPasswordVerifyView.this.onBackPressed();
            }
        });
        this.passwordEditText.setOnEnterKeyEventListener(new NXPPasswordEditText.OnEnterKeyEventListener() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordVerifyView.3
            @Override // kr.co.nexon.toy.android.ui.secondpassword.view.NXPPasswordEditText.OnEnterKeyEventListener
            public void onEnterKeyEvent() {
                ToyLog.d("Enter key event : user action - not dismiss soft keyboard");
            }
        });
        this.passwordEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        this.passwordEditText.requestFocus();
        setPasswordView();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void onBackPressed() {
        this.verifyPresenter.onCancel();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setDefaultDisplayUI(configuration.orientation, this.viewType);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void onDismiss() {
        this.secondPasswordDialog.dismiss();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.passwordViewList == null || this.passwordViewList.isEmpty()) {
            return;
        }
        int length = charSequence.length();
        if (length == 0) {
            setTargetPasswordViewColor(this.passwordViewList.get(length), this.passwordViewDisabledColor);
            return;
        }
        if (length <= 5) {
            if (NXStringUtil.isNull(this.subDescriptionText)) {
                setErrorText("");
                this.errorTextView.setVisibility(8);
            } else {
                setSubDescriptionText(this.subDescriptionText);
            }
        }
        setTargetPasswordViewColor(this.passwordViewList.get(length - 1), this.passwordViewEnabledColor);
        if (length < 6) {
            setTargetPasswordViewColor(this.passwordViewList.get(length), this.passwordViewDisabledColor);
        }
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract.View
    public void resetInputPassword() {
        this.passwordEditText.setText("");
        setAllPasswordViewColor(this.passwordViewDisabledColor);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract.View
    public void setChangePasswordButtonText(String str) {
        this.changePasswordButton.setText(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract.View
    public void setDefaultDisplayUI(int i, int i2) {
        this.viewType = i2;
        switch (i) {
            case 1:
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.passwordLayout.getLayoutParams());
                layoutParams.bottomMargin = NXGraphicUtil.dipToPix(getContext(), 40.0d);
                this.passwordLayout.setLayoutParams(layoutParams);
                break;
            case 2:
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(this.passwordLayout.getLayoutParams());
                layoutParams2.bottomMargin = 0;
                this.passwordLayout.setLayoutParams(layoutParams2);
                break;
        }
        this.subDescriptionTextColor = getColor(R.color.nxp_color_black_normal);
        this.errorTextColor = getColor(R.color.nxp_color_red_normal);
        if (i2 == 1) {
            setBackgroundColor(getColor(R.color.nxp_color_white_normal));
            this.titleTextView.setTextColor(getColor(R.color.nxp_color_black_normal));
            this.changePasswordButton.setTextColor(getColorStateList(R.color.common_text_button_black_selector));
            this.changePasswordButton.setBackgroundColor(getColor(R.color.nxp_color_white_normal));
            this.closeImageButton.setBackgroundColor(getColor(R.color.nxp_color_white_normal));
            this.closeImageButton.setImageDrawable(getDrawable(R.drawable.common_titlebar_close_btn_selector));
            this.passwordViewEnabledColor = getColor(R.color.second_password_verify_black_view_enabled);
            this.passwordViewDisabledColor = getColor(R.color.second_password_verify_black_view_disabled);
        } else {
            this.passwordViewEnabledColor = getColor(R.color.second_password_verify_white_view_enabled);
            this.passwordViewDisabledColor = getColor(R.color.second_password_verify_white_view_disabled);
        }
        setAllPasswordViewColor(this.passwordViewDisabledColor);
        if (this.passwordViewList != null || !this.passwordViewList.isEmpty()) {
            for (int i3 = 0; i3 < this.passwordEditText.length(); i3++) {
                setTargetPasswordViewColor(this.passwordViewList.get(i3), this.passwordViewEnabledColor);
            }
        }
        showSoftKeyboard();
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract.View
    public void setErrorText(String str) {
        if (NXStringUtil.isNull(str)) {
            this.errorTextView.setText("");
            this.errorTextView.setVisibility(8);
        } else {
            this.errorTextView.setTextColor(this.errorTextColor);
            this.errorTextView.setText(str);
            this.errorTextView.setVisibility(0);
        }
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void setFragment(Fragment fragment) {
        this.secondPasswordDialog = (NXPSecondPasswordDialog) fragment;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.view.NXPBaseView
    public void setPresenter(NXPSecondPasswordVerifyContract.Presenter presenter) {
        this.verifyPresenter = presenter;
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract.View
    public void setSubDescriptionText(String str) {
        this.subDescriptionText = str;
        this.errorTextView.setTextColor(this.subDescriptionTextColor);
        this.errorTextView.setText(str);
        this.errorTextView.setVisibility(0);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract.View
    public void setTitle(String str) {
        this.titleTextView.setText(str);
    }

    @Override // kr.co.nexon.toy.android.ui.secondpassword.interfaces.contract.NXPSecondPasswordVerifyContract.View
    public void showSoftKeyboard() {
        this.passwordEditText.postDelayed(new Runnable() { // from class: kr.co.nexon.toy.android.ui.secondpassword.view.NXPSecondPasswordVerifyView.4
            @Override // java.lang.Runnable
            public void run() {
                ((InputMethodManager) NXPSecondPasswordVerifyView.this.getContext().getSystemService("input_method")).showSoftInput(NXPSecondPasswordVerifyView.this.passwordEditText, 0);
            }
        }, 50L);
    }
}
