package kr.co.nexon.toy.android.ui.backup.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.View;
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
import kr.co.nexon.toy.android.ui.common.NXPEditText;
import kr.co.nexon.toy.android.ui.util.NXPPatternFilter;
import kr.co.nexon.utility.StringUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPDataRestoreView extends NXPLinearLayout implements NXPEditText.NXPTextWatcher {
    private static final int LENGTH_FILTER_SIZE = 4;
    private View.OnClickListener closeButtonClickListener;
    private TextView descriptionTextView;
    private TextView errorDescriptionTextView;
    private List<NXPEditText> mgTokenEditTextList;
    private List<Integer> mgTokenIdList;
    private NXClickListener onSwallowClickListener;
    private Button restoreButton;
    private RestoreButtonClickListener restoreButtonClickListener;
    private TextView titleTextView;

    public interface RestoreButtonClickListener {
        void onClickRestoreButton(View view, String str);
    }

    public NXPDataRestoreView(Context context) {
        super(context);
        this.mgTokenIdList = Arrays.asList(Integer.valueOf(R.id.migrationCodeEditText1), Integer.valueOf(R.id.migrationCodeEditText2), Integer.valueOf(R.id.migrationCodeEditText3), Integer.valueOf(R.id.migrationCodeEditText4));
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.backup.view.NXPDataRestoreView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.closeBtn) {
                    if (NXPDataRestoreView.this.closeButtonClickListener != null) {
                        NXPDataRestoreView.this.closeButtonClickListener.onClick(view);
                    }
                } else {
                    if (id != R.id.dataRestoreCodeLoginBtn || NXPDataRestoreView.this.restoreButtonClickListener == null) {
                        return;
                    }
                    NXPDataRestoreView.this.setErrorDescriptionVisibility(8);
                    NXPDataRestoreView.this.restoreButtonClickListener.onClickRestoreButton(view, NXPDataRestoreView.this.getMigrationCode());
                }
            }
        };
    }

    public NXPDataRestoreView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mgTokenIdList = Arrays.asList(Integer.valueOf(R.id.migrationCodeEditText1), Integer.valueOf(R.id.migrationCodeEditText2), Integer.valueOf(R.id.migrationCodeEditText3), Integer.valueOf(R.id.migrationCodeEditText4));
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.backup.view.NXPDataRestoreView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.closeBtn) {
                    if (NXPDataRestoreView.this.closeButtonClickListener != null) {
                        NXPDataRestoreView.this.closeButtonClickListener.onClick(view);
                    }
                } else {
                    if (id != R.id.dataRestoreCodeLoginBtn || NXPDataRestoreView.this.restoreButtonClickListener == null) {
                        return;
                    }
                    NXPDataRestoreView.this.setErrorDescriptionVisibility(8);
                    NXPDataRestoreView.this.restoreButtonClickListener.onClickRestoreButton(view, NXPDataRestoreView.this.getMigrationCode());
                }
            }
        };
    }

    public NXPDataRestoreView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mgTokenIdList = Arrays.asList(Integer.valueOf(R.id.migrationCodeEditText1), Integer.valueOf(R.id.migrationCodeEditText2), Integer.valueOf(R.id.migrationCodeEditText3), Integer.valueOf(R.id.migrationCodeEditText4));
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.backup.view.NXPDataRestoreView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.closeBtn) {
                    if (NXPDataRestoreView.this.closeButtonClickListener != null) {
                        NXPDataRestoreView.this.closeButtonClickListener.onClick(view);
                    }
                } else {
                    if (id != R.id.dataRestoreCodeLoginBtn || NXPDataRestoreView.this.restoreButtonClickListener == null) {
                        return;
                    }
                    NXPDataRestoreView.this.setErrorDescriptionVisibility(8);
                    NXPDataRestoreView.this.restoreButtonClickListener.onClickRestoreButton(view, NXPDataRestoreView.this.getMigrationCode());
                }
            }
        };
    }

    private void requestNextViewFocus(View view) {
        int iIndexOf = this.mgTokenEditTextList.indexOf(view);
        ToyLog.d("current TextView index:" + iIndexOf);
        if (iIndexOf == -1) {
            return;
        }
        int i = iIndexOf + 1;
        if (i >= this.mgTokenEditTextList.size()) {
            setRestoreButtonEnabled(true);
            return;
        }
        try {
            NXPEditText nXPEditText = this.mgTokenEditTextList.get(i);
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
                setRestoreButtonEnabled(false);
                setErrorDescriptionVisibility(8);
            }
        }
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPEditText.NXPTextWatcher
    public void beforeTextChanged(View view, CharSequence charSequence, int i, int i2, int i3) {
    }

    public void displayErrorText(String str) {
        if (StringUtil.isNull(str) || this.errorDescriptionTextView == null) {
            return;
        }
        setErrorDescriptionVisibility(0);
        this.errorDescriptionTextView.setText(str);
    }

    public String getMigrationCode() {
        StringBuilder sb = new StringBuilder();
        Iterator<NXPEditText> it = this.mgTokenEditTextList.iterator();
        while (it.hasNext()) {
            sb.append((CharSequence) it.next().getText());
        }
        return sb.toString();
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.mgTokenEditTextList = new ArrayList();
        View viewFindViewById = findViewById(R.id.backBtn);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(4);
        }
        View viewFindViewById2 = findViewById(R.id.closeBtn);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(this.onSwallowClickListener);
        }
        this.restoreButton = (Button) findViewById(R.id.dataRestoreCodeLoginBtn);
        if (this.restoreButton != null) {
            this.restoreButton.setOnClickListener(this.onSwallowClickListener);
        }
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.descriptionTextView = (TextView) findViewById(R.id.descCode);
        InputFilter[] inputFilterArr = {new InputFilter.LengthFilter(4), new InputFilter.AllCaps(), new NXPPatternFilter("^[a-zA-Z0-9]+$")};
        for (int i = 0; i < this.mgTokenIdList.size(); i++) {
            NXPEditText nXPEditText = (NXPEditText) findViewById(this.mgTokenIdList.get(i).intValue());
            if (nXPEditText == null) {
                ToyLog.d("mgToken TextView not found.");
            } else {
                nXPEditText.setFilters(inputFilterArr);
                nXPEditText.setTextWatcher(this);
                this.mgTokenEditTextList.add(nXPEditText);
                if (i == 0) {
                    nXPEditText.requestFocus();
                }
            }
        }
        this.errorDescriptionTextView = (TextView) findViewById(R.id.dataRestoreErrorDescription);
    }

    @Override // kr.co.nexon.toy.android.ui.common.NXPEditText.NXPTextWatcher
    public void onTextChanged(View view, CharSequence charSequence, int i, int i2, int i3) {
    }

    public void setDescriptionText(CharSequence charSequence) {
        if (this.descriptionTextView != null) {
            this.descriptionTextView.setText(charSequence);
        }
    }

    public void setErrorDescriptionVisibility(int i) {
        if (this.errorDescriptionTextView != null) {
            this.errorDescriptionTextView.setVisibility(i);
        }
    }

    public void setInputFilters(InputFilter[] inputFilterArr) {
        Iterator<NXPEditText> it = this.mgTokenEditTextList.iterator();
        while (it.hasNext()) {
            it.next().setFilters(inputFilterArr);
        }
    }

    public void setOnCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.closeButtonClickListener = onClickListener;
    }

    public void setOnRestoreButtonClickListener(RestoreButtonClickListener restoreButtonClickListener) {
        this.restoreButtonClickListener = restoreButtonClickListener;
    }

    public void setRestoreButtonEnabled(boolean z) {
        if (this.restoreButton != null) {
            this.restoreButton.setEnabled(z);
        }
    }

    public void setRestoreButtonText(CharSequence charSequence) {
        if (this.restoreButton != null) {
            this.restoreButton.setText(charSequence);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (this.titleTextView != null) {
            this.titleTextView.setText(charSequence);
        }
    }
}
