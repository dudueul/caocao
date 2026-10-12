package kr.co.nexon.mdev.android.view;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nexon.core.util.NXByteUtil;
import com.nexon.npaccount.R;
import java.util.Arrays;
import kr.co.nexon.mdev.android.util.NXResourcesUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXInputEditView extends NXPRelativeLayout {
    public EditText editText;
    private RelativeLayout editTextContainer;
    Context mContext;
    NPTextChangedListener mListener;
    private TextView messageAreaTextView;
    private ImageButton rightButton;
    TextWatcher textWatcherInput;

    public interface NPTextChangedListener {
        void onTextChanged(CharSequence charSequence, int i, int i2, int i3);
    }

    public NXInputEditView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mListener = null;
        this.textWatcherInput = new TextWatcher() { // from class: kr.co.nexon.mdev.android.view.NXInputEditView.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (NXInputEditView.this.mListener != null) {
                    NXInputEditView.this.mListener.onTextChanged(charSequence, i, i2, i3);
                }
                if (charSequence.length() > 0) {
                    NXInputEditView.this.rightButton.setVisibility(0);
                } else {
                    NXInputEditView.this.rightButton.setVisibility(8);
                }
            }
        };
        this.mContext = context;
    }

    public void addTextChangedListener(TextWatcher textWatcher) {
        this.editText.addTextChangedListener(textWatcher);
    }

    public void clearText() {
        this.editText.getText().clear();
    }

    @Override // android.view.View
    public int getPaddingBottom() {
        return this.editText.getPaddingBottom();
    }

    @Override // android.view.View
    public int getPaddingLeft() {
        return this.editText.getPaddingLeft();
    }

    @Override // android.view.View
    public int getPaddingRight() {
        return this.editText.getPaddingRight();
    }

    @Override // android.view.View
    public int getPaddingTop() {
        return this.editText.getPaddingTop();
    }

    public String getText() {
        return this.editText.getText().toString();
    }

    public byte[] getTextToBytes() {
        Editable text = this.editText.getText();
        char[] cArr = new char[text.length()];
        text.getChars(0, text.length(), cArr, 0);
        text.clear();
        byte[] bArrCharArrayToBytes = NXByteUtil.charArrayToBytes(cArr);
        Arrays.fill(cArr, (char) 0);
        return bArrCharArrayToBytes;
    }

    public void hideSoftKeyBoard() {
        ((InputMethodManager) this.mContext.getSystemService("input_method")).hideSoftInputFromWindow(this.editText.getWindowToken(), 0);
    }

    @Override // kr.co.nexon.mdev.android.view.NXPRelativeLayout
    protected void initView() {
        this.editTextContainer = (RelativeLayout) findViewById(R.id.edit_text_container);
        this.editText = (EditText) findViewById(R.id.common_edit_text);
        this.rightButton = (ImageButton) findViewById(R.id.clear_btn);
        this.rightButton.setVisibility(8);
        this.messageAreaTextView = (TextView) findViewById(R.id.tvMessageArea);
        this.rightButton.setOnClickListener(new View.OnClickListener() { // from class: kr.co.nexon.mdev.android.view.NXInputEditView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NXInputEditView.this.editText.setText("");
                NXInputEditView.this.rightButton.setVisibility(8);
            }
        });
        this.editText.addTextChangedListener(this.textWatcherInput);
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        int paddingBottom = this.editTextContainer.getPaddingBottom();
        int paddingTop = this.editTextContainer.getPaddingTop();
        int paddingRight = this.editTextContainer.getPaddingRight();
        int paddingLeft = this.editTextContainer.getPaddingLeft();
        this.editTextContainer.setBackgroundResource(i);
        this.editTextContainer.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public void setEditable(boolean z) {
        this.editText.setFocusable(z);
        this.editText.setFocusableInTouchMode(z);
        this.editText.setClickable(z);
        this.rightButton.setClickable(z);
        this.rightButton.setBackground(NXResourcesUtil.getDrawableByResId(this.mContext, R.drawable.check));
    }

    public void setEditorActionListener(TextView.OnEditorActionListener onEditorActionListener) {
        this.editText.setOnEditorActionListener(onEditorActionListener);
    }

    public void setErrorMessage(String str) {
        this.messageAreaTextView.setText(str);
    }

    public void setFilter(InputFilter[] inputFilterArr) {
        this.editText.setFilters(inputFilterArr);
    }

    public void setFilters(InputFilter[] inputFilterArr) {
        this.editText.setFilters(inputFilterArr);
    }

    public void setFocus() {
        this.editText.requestFocus();
    }

    public void setHint(String str) {
        this.editText.setHint(str);
    }

    public void setImeOption(int i) {
        this.editText.setImeOptions(i);
    }

    public void setInputType(int i) {
        this.editText.setInputType(i);
    }

    public void setKeyListener(View.OnKeyListener onKeyListener) {
        this.editText.setOnKeyListener(onKeyListener);
    }

    public void setListener(NPTextChangedListener nPTextChangedListener) {
        this.mListener = nPTextChangedListener;
    }

    public void setMessageAreaTextViewVisibility(int i) {
        this.messageAreaTextView.setVisibility(i);
    }

    public void setPrivateImeOptions(String str) {
        this.editText.setPrivateImeOptions(str);
    }

    public void setSelection(int i) {
        this.editText.setSelection(i);
    }

    public void setText(CharSequence charSequence) {
        this.editText.setText(charSequence);
    }

    public void setTextColorHint(int i) {
        this.editText.setHintTextColor(i);
    }

    public void setTransformationMethod() {
        this.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    public void showSoftKeyBoard() {
        ((InputMethodManager) this.mContext.getSystemService("input_method")).showSoftInput(this.editText, 0);
    }
}
