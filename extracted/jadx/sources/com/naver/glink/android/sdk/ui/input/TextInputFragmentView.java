package com.naver.glink.android.sdk.ui.input;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.g;
import com.naver.glink.android.sdk.a.j;
import com.naver.glink.android.sdk.a.k;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.a.v;
import com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView;
import com.naver.glink.android.sdk.ui.widget.PreImeKeyListeningEditText;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TextInputFragmentView extends DialogFragmentView implements b.InterfaceC0044b {
    private static final String a = "com.naver.glink.TEXT_INPUT_BACK_STACK";
    private b.a b;
    private PreImeKeyListeningEditText c;
    private Button d;

    public TextInputFragmentView(Context context) {
        super(context);
    }

    private void a(final View view) {
        view.requestFocus();
        view.postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.ui.input.TextInputFragmentView.6
            @Override // java.lang.Runnable
            public void run() {
                k.a(TextInputFragmentView.this.getContext(), view);
            }
        }, 300L);
        com.naver.glink.android.sdk.ui.main.b.b(true);
    }

    public static boolean a(com.naver.glink.android.sdk.ui.parent.plugfragment.a aVar) {
        return aVar.b(a) != null;
    }

    public static TextInputFragmentView b(Context context) {
        return new TextInputFragmentView(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(View view) {
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        String string = this.c.getText().toString();
        if (TextUtils.isEmpty(string)) {
            return;
        }
        c();
        this.b.a(getContext(), string);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return this.b.b() ? layoutInflater.inflate(R.layout.fragment_input_for_canceled_on_touch_outside, (ViewGroup) null, false) : layoutInflater.inflate(R.layout.fragment_input, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        this.b.a();
        a(this.c);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        if (this.b.b()) {
            view.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.input.TextInputFragmentView.1
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    TextInputFragmentView.this.c();
                }
            });
        }
        this.c = (PreImeKeyListeningEditText) view.findViewById(R.id.comment_edit);
        this.c.setFilters(this.b.c());
        this.c.addTextChangedListener(new g() { // from class: com.naver.glink.android.sdk.ui.input.TextInputFragmentView.2
            @Override // com.naver.glink.android.sdk.a.g, android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextInputFragmentView.this.d.setEnabled(!v.a(charSequence.toString()));
            }
        });
        this.c.setOnKeyPreImeListener(new View.OnKeyListener() { // from class: com.naver.glink.android.sdk.ui.input.TextInputFragmentView.3
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view2, int i, KeyEvent keyEvent) {
                if (!j.a(keyEvent)) {
                    return false;
                }
                TextInputFragmentView.this.c();
                return true;
            }
        });
        this.c.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.naver.glink.android.sdk.ui.input.TextInputFragmentView.4
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view2, boolean z) {
                if (z) {
                    return;
                }
                TextInputFragmentView.this.b(TextInputFragmentView.this.c);
            }
        });
        this.d = (Button) view.findViewById(R.id.comment_save);
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.input.TextInputFragmentView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                TextInputFragmentView.this.e();
            }
        });
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView
    protected void a(WindowManager.LayoutParams layoutParams) {
        if (layoutParams != null) {
            layoutParams.x = 0;
            layoutParams.y = 0;
            layoutParams.width = -1;
            if (this.b.b()) {
                layoutParams.height = -1;
            } else {
                layoutParams.height = -2;
                layoutParams.gravity = 83;
            }
            layoutParams.dimAmount = 0.0f;
            layoutParams.format = -2;
            setBackgroundColor(getResources().getColor(android.R.color.transparent));
            layoutParams.horizontalMargin = 0.0f;
            layoutParams.dimAmount = 0.0f;
            layoutParams.flags |= 32;
            layoutParams.softInputMode |= 18;
        }
    }

    public void c() {
        com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b(this);
        com.naver.glink.android.sdk.ui.main.b.b(false);
    }

    @Override // com.naver.glink.android.sdk.ui.input.b.InterfaceC0044b
    public void c_() {
        com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b().a(this, getClass().getName()).a(a).a();
    }

    @Override // com.naver.glink.android.sdk.ui.input.b.InterfaceC0044b
    public void setInputText(String str) {
        if (str == null) {
            return;
        }
        this.c.setText(str);
        this.c.setSelection(str.length());
    }

    @Override // com.naver.glink.android.sdk.a.d.b
    public void setPresenter(b.a aVar) {
        this.b = aVar;
    }
}
