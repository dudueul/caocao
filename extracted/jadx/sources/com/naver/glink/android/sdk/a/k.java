package com.naver.glink.android.sdk.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView;

/* JADX INFO: compiled from: KeyboardHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class k {
    private static final l a = l.a(k.class.getSimpleName());

    public static EditText a(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return null;
        }
        View deepestFocusedChild = viewGroup instanceof FragmentView ? ((FragmentView) viewGroup).getDeepestFocusedChild() : viewGroup.getFocusedChild();
        return deepestFocusedChild instanceof EditText ? (EditText) deepestFocusedChild : null;
    }

    public static void a(Context context, View view) {
        try {
            ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(view, 1);
        } catch (Exception e) {
            a.a(e);
        }
    }

    public static void a(Context context, ViewGroup viewGroup) {
        if (context != null) {
            a(context, a(viewGroup));
        }
    }

    public static void a(Context context, EditText editText) {
        if (context == null || editText == null) {
            return;
        }
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
        editText.clearFocus();
    }
}
