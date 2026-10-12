package kr.co.nexon.toy.android.ui.common.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSimpleListSwitchItem extends NXPLinearLayout {
    private CheckedChangedListener checkedChangeListener;
    private CompoundButton.OnCheckedChangeListener innerCheckedChangeListener;
    private int position;
    private CompoundButton switchButton;
    private TextView textView;

    public interface CheckedChangedListener {
        void onCheckedChanged(CompoundButton compoundButton, boolean z, int i);
    }

    public NXPSimpleListSwitchItem(Context context) {
        super(context);
        this.innerCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: kr.co.nexon.toy.android.ui.common.view.NXPSimpleListSwitchItem.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (NXPSimpleListSwitchItem.this.checkedChangeListener != null) {
                    NXPSimpleListSwitchItem.this.checkedChangeListener.onCheckedChanged(compoundButton, z, NXPSimpleListSwitchItem.this.position);
                }
            }
        };
    }

    public NXPSimpleListSwitchItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.innerCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: kr.co.nexon.toy.android.ui.common.view.NXPSimpleListSwitchItem.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (NXPSimpleListSwitchItem.this.checkedChangeListener != null) {
                    NXPSimpleListSwitchItem.this.checkedChangeListener.onCheckedChanged(compoundButton, z, NXPSimpleListSwitchItem.this.position);
                }
            }
        };
    }

    public NXPSimpleListSwitchItem(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.innerCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: kr.co.nexon.toy.android.ui.common.view.NXPSimpleListSwitchItem.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (NXPSimpleListSwitchItem.this.checkedChangeListener != null) {
                    NXPSimpleListSwitchItem.this.checkedChangeListener.onCheckedChanged(compoundButton, z, NXPSimpleListSwitchItem.this.position);
                }
            }
        };
    }

    public int getPosition() {
        return this.position;
    }

    public CharSequence getText() {
        return this.textView != null ? this.textView.getText() : "";
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.position = -1;
        this.textView = (TextView) findViewById(R.id.simple_listivew_text);
        this.switchButton = (CompoundButton) findViewById(R.id.simple_listview_right_switch_icon);
        this.switchButton.setOnCheckedChangeListener(this.innerCheckedChangeListener);
        if (this.switchButton == null || !(this.switchButton instanceof SwitchCompat)) {
            return;
        }
        ((SwitchCompat) this.switchButton).setTrackResource(R.drawable.switch_track_selector);
    }

    public void setChecked(boolean z) {
        if (this.switchButton != null) {
            this.switchButton.setChecked(z);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        if (this.switchButton != null) {
            this.switchButton.setEnabled(z);
        }
    }

    public void setOnCheckedChangeListener(CheckedChangedListener checkedChangedListener) {
        this.checkedChangeListener = checkedChangedListener;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public void setText(CharSequence charSequence) {
        if (this.textView != null) {
            this.textView.setText(charSequence);
        }
    }
}
