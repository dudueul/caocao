package kr.co.nexon.toy.android.ui.util;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.EditText;
import com.nexon.core.log.ToyLog;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAutoSpliterTextWatcher implements TextWatcher {
    private NXPAutoSpliterListener autoSpliterListener;
    private WeakReference<EditText> owner;
    private int splitCount;
    private SpliterChar spliter;

    public interface NXPAutoSpliterListener {
        void onAfterChanged(String str);
    }

    public enum SpliterChar {
        En_dash("-"),
        Wide_En_dash(" - "),
        Asterisk("*"),
        BrokenBar("|");

        private String spliterCharacter;

        SpliterChar(String str) {
            this.spliterCharacter = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.spliterCharacter;
        }
    }

    public NXPAutoSpliterTextWatcher(EditText editText, SpliterChar spliterChar, int i, NXPAutoSpliterListener nXPAutoSpliterListener) {
        this.owner = new WeakReference<>(editText);
        this.spliter = spliterChar;
        this.splitCount = i;
        if (this.splitCount < 0) {
            this.splitCount = 0;
        }
        this.autoSpliterListener = nXPAutoSpliterListener;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(final Editable editable) {
        final EditText editText = this.owner.get();
        if (editText == null) {
            ToyLog.d("Owner EditText is null");
        } else {
            editText.post(new Runnable() { // from class: kr.co.nexon.toy.android.ui.util.NXPAutoSpliterTextWatcher.1
                @Override // java.lang.Runnable
                public void run() {
                    ToyLog.d("before str:" + editable.toString());
                    editText.removeTextChangedListener(NXPAutoSpliterTextWatcher.this);
                    InputFilter[] filters = editText.getFilters();
                    editText.setFilters(new InputFilter[0]);
                    String string = NXPAutoSpliterTextWatcher.this.spliter.toString();
                    String strReplace = editable.toString().replace(string.trim(), "").replace(" ", "");
                    editable.clear();
                    Editable editableAppend = editable.append((CharSequence) strReplace);
                    int length = NXPAutoSpliterTextWatcher.this.splitCount;
                    while (editableAppend.length() > length) {
                        editableAppend.insert(length, string);
                        length = length + string.length() + NXPAutoSpliterTextWatcher.this.splitCount;
                    }
                    editText.setFilters(filters);
                    editText.addTextChangedListener(NXPAutoSpliterTextWatcher.this);
                    if (NXPAutoSpliterTextWatcher.this.autoSpliterListener != null) {
                        NXPAutoSpliterTextWatcher.this.autoSpliterListener.onAfterChanged(editable.toString());
                    }
                }
            });
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
