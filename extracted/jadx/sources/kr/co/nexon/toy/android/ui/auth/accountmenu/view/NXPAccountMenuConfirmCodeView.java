package kr.co.nexon.toy.android.ui.auth.accountmenu.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuOnClickListener;
import kr.co.nexon.toy.android.ui.common.NXPEditText;
import kr.co.nexon.toy.android.ui.util.NXPPatternFilter;
import kr.co.nexon.utility.StringUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAccountMenuConfirmCodeView extends NXPLinearLayout implements NXPAccountMenuOnClickListener, NXPEditText.NXPTextWatcher {
    private static final int LENGTH_FILTER_SIZE = 4;
    private View.OnClickListener buttonClickListener;
    private View.OnClickListener closeButtonClickListener;
    private List<NXPEditText> linkCodeEditTextList;
    private List<Integer> linkCodeIdList;
    private Button linkOtherDeviceButton;
    private TextView linkOtherDeviceCodeDescription;
    private TextView linkOtherDeviceErrorCdoe;
    private NXClickListener onSwallowClickListener;

    public NXPAccountMenuConfirmCodeView(Context context) {
        super(context);
        this.linkCodeIdList = Arrays.asList(Integer.valueOf(R.id.account_menu_link_code1), Integer.valueOf(R.id.account_menu_link_code2), Integer.valueOf(R.id.account_menu_link_code3), Integer.valueOf(R.id.account_menu_link_code4));
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuConfirmCodeView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (view.getId() == R.id.closeBtn) {
                    if (NXPAccountMenuConfirmCodeView.this.closeButtonClickListener != null) {
                        NXPAccountMenuConfirmCodeView.this.closeButtonClickListener.onClick(view);
                    }
                } else {
                    if (NXPAccountMenuConfirmCodeView.this.buttonClickListener != null) {
                        NXPAccountMenuConfirmCodeView.this.buttonClickListener.onClick(view);
                    }
                    View rootView = NXPAccountMenuConfirmCodeView.this.getRootView();
                    if (rootView != null) {
                        ((InputMethodManager) rootView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(rootView.getWindowToken(), 0);
                    }
                }
            }
        };
    }

    public NXPAccountMenuConfirmCodeView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.linkCodeIdList = Arrays.asList(Integer.valueOf(R.id.account_menu_link_code1), Integer.valueOf(R.id.account_menu_link_code2), Integer.valueOf(R.id.account_menu_link_code3), Integer.valueOf(R.id.account_menu_link_code4));
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.accountmenu.view.NXPAccountMenuConfirmCodeView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (view.getId() == R.id.closeBtn) {
                    if (NXPAccountMenuConfirmCodeView.this.closeButtonClickListener != null) {
                        NXPAccountMenuConfirmCodeView.this.closeButtonClickListener.onClick(view);
                    }
                } else {
                    if (NXPAccountMenuConfirmCodeView.this.buttonClickListener != null) {
                        NXPAccountMenuConfirmCodeView.this.buttonClickListener.onClick(view);
                    }
                    View rootView = NXPAccountMenuConfirmCodeView.this.getRootView();
                    if (rootView != null) {
                        ((InputMethodManager) rootView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(rootView.getWindowToken(), 0);
                    }
                }
            }
        };
    }

    private void requestNextViewFocus(View view) {
        int iIndexOf = this.linkCodeEditTextList.indexOf(view);
        ToyLog.d("current TextView index:" + iIndexOf);
        if (iIndexOf == -1) {
            return;
        }
        int i = iIndexOf + 1;
        if (i >= this.linkCodeEditTextList.size()) {
            setRestorelinkOtherDeviceButtonEnable(true);
            return;
        }
        try {
            NXPEditText nXPEditText = this.linkCodeEditTextList.get(i);
            if (nXPEditText.getText().length() == 4) {
                requestNextViewFocus(nXPEditText);
            } else {
                nXPEditText.requestFocus();
                nXPEditText.setSelection(nXPEditText.getText().length());
            }
        } catch (IndexOutOfBoundsException e) {
            ToyLog.e("exception:" + e.toString());
        }
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPEditText.NXPTextWatcher
    public void afterTextChanged(View view, Editable editable) {
        if (view instanceof TextView) {
            if (((TextView) view).getText().length() >= 4) {
                requestNextViewFocus(view);
            } else {
                setRestorelinkOtherDeviceButtonEnable(false);
                setErrorDescriptionVisibility(8);
            }
        }
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPEditText.NXPTextWatcher
    public void beforeTextChanged(View view, CharSequence charSequence, int i, int i2, int i3) {
    }

    public String getLinkCode() {
        StringBuilder sb = new StringBuilder();
        Iterator<NXPEditText> it = this.linkCodeEditTextList.iterator();
        while (it.hasNext()) {
            sb.append((CharSequence) it.next().getText());
        }
        return sb.toString();
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(getContext());
        TextView textView = (TextView) findViewById(R.id.title);
        if (textView != null) {
            textView.setText(nXToyLocaleManager.getString(R.string.npres_account_menu_add_device_btn));
        }
        this.linkOtherDeviceCodeDescription = (TextView) findViewById(R.id.account_menu_link_other_device_code_desc);
        if (this.linkOtherDeviceCodeDescription != null) {
            this.linkOtherDeviceCodeDescription.setText(nXToyLocaleManager.getString(R.string.npres_account_menu_receive_link_other_device_code_desc));
        }
        this.linkOtherDeviceErrorCdoe = (TextView) findViewById(R.id.account_menu_link_other_device_error_code);
        this.linkOtherDeviceButton = (Button) findViewById(R.id.account_menu_link_other_device_code_btn);
        if (this.linkOtherDeviceButton != null) {
            this.linkOtherDeviceButton.setText(nXToyLocaleManager.getString(R.string.confirm));
            this.linkOtherDeviceButton.setOnClickListener(this.onSwallowClickListener);
        }
        View viewFindViewById = findViewById(R.id.backBtn);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(this.onSwallowClickListener);
        }
        View viewFindViewById2 = findViewById(R.id.closeBtn);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(this.onSwallowClickListener);
        }
        InputFilter[] inputFilterArr = {new NXPPatternFilter("^[a-zA-Z0-9]+$"), new InputFilter.LengthFilter(4), new InputFilter.AllCaps()};
        this.linkCodeEditTextList = new ArrayList();
        for (int i = 0; i < this.linkCodeIdList.size(); i++) {
            NXPEditText nXPEditText = (NXPEditText) findViewById(this.linkCodeIdList.get(i).intValue());
            if (nXPEditText == null) {
                ToyLog.d("linkCode TextView not found.");
            } else {
                nXPEditText.setFilters(inputFilterArr);
                nXPEditText.setTextWatcher(this);
                this.linkCodeEditTextList.add(nXPEditText);
                if (i == 0) {
                    nXPEditText.requestFocus();
                }
            }
        }
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPEditText.NXPTextWatcher
    public void onTextChanged(View view, CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuOnClickListener
    public void setCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.closeButtonClickListener = onClickListener;
    }

    public void setErrorDescriptionVisibility(int i) {
        if (this.linkOtherDeviceErrorCdoe != null) {
            this.linkOtherDeviceErrorCdoe.setVisibility(i);
        }
    }

    public void setErrorText(String str) {
        if (StringUtil.isNull(str) || this.linkOtherDeviceErrorCdoe == null) {
            return;
        }
        setErrorDescriptionVisibility(0);
        this.linkOtherDeviceErrorCdoe.setText(str);
    }

    @Override // android.view.View, kr.co.nexon.toy.android.ui.auth.accountmenu.interfaces.NXPAccountMenuOnClickListener
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.buttonClickListener = onClickListener;
    }

    public void setRestorelinkOtherDeviceButtonEnable(boolean z) {
        if (this.linkOtherDeviceButton != null) {
            this.linkOtherDeviceButton.setEnabled(z);
        }
    }
}
