package kr.co.nexon.toy.android.ui.etc.view;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import com.nexon.core.log.ToyLog;
import com.nexon.npaccount.R;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.GregorianCalendar;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPDatePickerView extends NXPLinearLayout {
    private NXPDatePcikerListener datePickerListener;
    private NumberPicker monthPicker;
    private NXClickListener onSwallowClickListener;
    private NumberPicker yearPicker;

    public interface NXPDatePcikerListener {
        void onCancel();

        void onConfirm(int i, int i2);
    }

    public NXPDatePickerView(Context context) {
        super(context);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.view.NXPDatePickerView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPDatePickerView.this.datePickerListener == null) {
                    ToyLog.d("datePickerListener is null.");
                    return;
                }
                int id = view.getId();
                if (id == R.id.number_picker_ok_btn) {
                    NXPDatePickerView.this.datePickerListener.onConfirm(NXPDatePickerView.this.yearPicker.getValue(), NXPDatePickerView.this.monthPicker.getValue());
                } else if (id == R.id.closeBtn) {
                    NXPDatePickerView.this.datePickerListener.onCancel();
                }
            }
        };
    }

    public NXPDatePickerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.view.NXPDatePickerView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPDatePickerView.this.datePickerListener == null) {
                    ToyLog.d("datePickerListener is null.");
                    return;
                }
                int id = view.getId();
                if (id == R.id.number_picker_ok_btn) {
                    NXPDatePickerView.this.datePickerListener.onConfirm(NXPDatePickerView.this.yearPicker.getValue(), NXPDatePickerView.this.monthPicker.getValue());
                } else if (id == R.id.closeBtn) {
                    NXPDatePickerView.this.datePickerListener.onCancel();
                }
            }
        };
    }

    public NXPDatePickerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.view.NXPDatePickerView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPDatePickerView.this.datePickerListener == null) {
                    ToyLog.d("datePickerListener is null.");
                    return;
                }
                int id = view.getId();
                if (id == R.id.number_picker_ok_btn) {
                    NXPDatePickerView.this.datePickerListener.onConfirm(NXPDatePickerView.this.yearPicker.getValue(), NXPDatePickerView.this.monthPicker.getValue());
                } else if (id == R.id.closeBtn) {
                    NXPDatePickerView.this.datePickerListener.onCancel();
                }
            }
        };
    }

    private void setTextColor(NumberPicker numberPicker, int i) {
        int childCount = numberPicker.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = numberPicker.getChildAt(i2);
            if (childAt instanceof EditText) {
                try {
                    Field declaredField = numberPicker.getClass().getDeclaredField("mSelectorWheelPaint");
                    declaredField.setAccessible(true);
                    ((Paint) declaredField.get(numberPicker)).setColor(i);
                    ((EditText) childAt).setTextColor(i);
                    numberPicker.invalidate();
                } catch (Exception e) {
                }
            }
        }
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        Calendar gregorianCalendar = GregorianCalendar.getInstance();
        int i = gregorianCalendar.get(1);
        int i2 = gregorianCalendar.get(2);
        this.yearPicker = (NumberPicker) findViewById(R.id.number_pciker_year);
        if (this.yearPicker != null) {
            this.yearPicker.setMaxValue(i - 1);
            this.yearPicker.setMinValue(i - 150);
            this.yearPicker.setValue(i - 21);
            this.yearPicker.setWrapSelectorWheel(false);
            setTextColor(this.yearPicker, ViewCompat.MEASURED_STATE_MASK);
        }
        this.monthPicker = (NumberPicker) findViewById(R.id.number_pciker_month);
        if (this.monthPicker != null) {
            this.monthPicker.setMinValue(1);
            this.monthPicker.setMaxValue(12);
            this.monthPicker.setValue(i2 + 1);
            this.monthPicker.setWrapSelectorWheel(false);
            setTextColor(this.monthPicker, ViewCompat.MEASURED_STATE_MASK);
        }
        View viewFindViewById = findViewById(R.id.number_picker_ok_btn);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(this.onSwallowClickListener);
        }
        View viewFindViewById2 = findViewById(R.id.closeBtn);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(this.onSwallowClickListener);
        }
        View viewFindViewById3 = findViewById(R.id.backBtn);
        if (viewFindViewById3 != null) {
            viewFindViewById3.setVisibility(4);
        }
    }

    public void setOnDatePickerListener(NXPDatePcikerListener nXPDatePcikerListener) {
        this.datePickerListener = nXPDatePcikerListener;
    }
}
