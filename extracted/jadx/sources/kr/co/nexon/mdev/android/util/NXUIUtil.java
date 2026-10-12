package kr.co.nexon.mdev.android.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import com.nexon.npaccount.R;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXUIUtil {
    public static final int ALIGN_TEXT_CENTER = 0;
    public static final int ALIGN_TEXT_LEFT = 1;
    public static final int ALIGN_TEXT_RIGHT = 2;
    private static Toast mToast = null;

    public static boolean hideSoftInputWindow(View view) {
        return ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
    }

    public static View makeTextView(Context context, CharSequence charSequence, int i) {
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.nx_aldertdialog, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.alert_message);
        if (i == 0) {
            textView.setGravity(17);
        } else if (i == 1) {
            textView.setGravity(3);
        } else if (i == 2) {
            textView.setGravity(5);
        }
        textView.setText(charSequence);
        return viewInflate;
    }

    public static boolean showSoftInputWindow(View view) {
        return ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
    }

    public static void showToast(Context context, int i, int i2) {
        if (mToast == null) {
            mToast = Toast.makeText(context, i, i2);
        } else {
            mToast.setText(i);
        }
        mToast.show();
    }

    public static void showToast(Context context, String str, int i) {
        if (mToast == null) {
            mToast = Toast.makeText(context, str, i);
        } else {
            mToast.setText(str);
        }
        mToast.show();
    }
}
