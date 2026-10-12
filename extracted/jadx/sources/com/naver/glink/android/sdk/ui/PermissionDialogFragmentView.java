package com.naver.glink.android.sdk.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class PermissionDialogFragmentView extends DialogFragmentView {
    public static final String a = "com.naver.glink.ARG_MESSAGE";
    public static final String b = "com.naver.glink.ARG_TITLE";
    public static final String c = "com.naver.glink.ARG_POSITIVE_NAME";
    private AlertDialogFragmentView.c d;

    public static class a extends AlertDialogFragmentView.a<a, PermissionDialogFragmentView> {
        protected String c;
        protected String[] d;

        private a(Context context, String... strArr) {
            super(context);
            this.d = strArr;
        }

        public a a(String str) {
            this.c = str;
            return this;
        }

        @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public a a(String... strArr) {
            this.d = strArr;
            return this;
        }

        @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.a
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public PermissionDialogFragmentView b() {
            return new PermissionDialogFragmentView(this.a, this.c, this.d, AlertDialogFragmentView.c(), this.b);
        }
    }

    private PermissionDialogFragmentView(Context context, String str, String[] strArr, String str2, AlertDialogFragmentView.c cVar) {
        super(context);
        Bundle bundle = new Bundle();
        bundle.putStringArray("com.naver.glink.ARG_MESSAGE", strArr);
        bundle.putString(b, str);
        bundle.putString("com.naver.glink.ARG_POSITIVE_NAME", str2);
        setArguments(bundle);
        setAlertDialogListener(cVar);
    }

    public static a a(Context context, String... strArr) {
        return new a(context, strArr);
    }

    private void a(TextView textView, String str) {
        textView.setText(Html.fromHtml(str.replaceAll("\n", "<br>")));
    }

    private TextView c() {
        TextView textView = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(x.a(20.0f), x.a(15.0f), x.a(15.0f), x.a(5.0f));
        textView.setLayoutParams(layoutParams);
        textView.setTextColor(getResources().getColor(R.color.black1));
        textView.setTextSize(0, x.a(15.0f));
        return textView;
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.alert_permission_dialog, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView
    public void a(DialogInterface dialogInterface) {
        super.a(dialogInterface);
        if (this.d != null) {
            this.d.a(dialogInterface);
        }
    }

    protected void a(View view) {
        String[] stringArray = getArguments().getStringArray("com.naver.glink.ARG_MESSAGE");
        String string = getArguments().getString(b);
        String string2 = getArguments().getString("com.naver.glink.ARG_POSITIVE_NAME");
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.dialog_message_layout);
        TextView textView = (TextView) view.findViewById(R.id.dialog_confirm);
        View viewFindViewById = view.findViewById(R.id.dialog_title_layout);
        TextView textView2 = (TextView) view.findViewById(R.id.dialog_title_text);
        if (TextUtils.isEmpty(string)) {
            viewFindViewById.setVisibility(8);
        } else {
            viewFindViewById.setVisibility(0);
            textView2.setText(string);
        }
        for (String str : stringArray) {
            if (!TextUtils.isEmpty(str)) {
                TextView textViewC = c();
                a(textViewC, str);
                viewGroup.addView(textViewC);
            }
        }
        if (TextUtils.isEmpty(string2)) {
            textView.setVisibility(8);
            return;
        }
        textView.setText(string2);
        textView.setVisibility(0);
        textView.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.PermissionDialogFragmentView.1
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                if (PermissionDialogFragmentView.this.d != null) {
                    PermissionDialogFragmentView.this.d.onClick(PermissionDialogFragmentView.this, -1);
                }
                PermissionDialogFragmentView.this.dismiss();
            }
        });
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        Point point = new Point();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getSize(point);
        }
        double d = d.i() ? ((double) point.x) * 0.5d : ((double) point.x) * 0.8d;
        View viewFindViewById = view.findViewById(R.id.dialog_layout);
        viewFindViewById.getLayoutParams().width = (int) d;
        setCanceledOnTouchOutside(true, viewFindViewById);
        a(view);
    }

    public void setAlertDialogListener(AlertDialogFragmentView.c cVar) {
        this.d = cVar;
    }
}
