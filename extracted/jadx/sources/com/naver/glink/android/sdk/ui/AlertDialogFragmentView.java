package com.naver.glink.android.sdk.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class AlertDialogFragmentView extends DialogFragmentView {
    public static final String a = "com.naver.glink.ARG_MESSAGE";
    public static final String b = "com.naver.glink.ARG_POSITIVE_NAME";
    public static final String c = "com.naver.glink.ARG_NEGATIVE_NAME";
    protected c d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static abstract class a<B extends a<B, T>, T extends DialogFragmentView> {
        protected Context a;
        protected c b;

        public a(Context context) {
            this.a = context;
        }

        public final B a(c cVar) {
            this.b = cVar;
            return this;
        }

        public abstract B a(String... strArr);

        public final void a() {
            b().a((String) null);
        }

        public abstract T b();
    }

    public static class b extends a<b, AlertDialogFragmentView> {
        protected String c;
        protected String d;
        protected String e;

        private b(Context context) {
            super(context);
        }

        private b(Context context, String str) {
            super(context);
            this.d = str;
        }

        public b a(String str) {
            this.c = str;
            return this;
        }

        public b b(String str) {
            this.e = str;
            return this;
        }

        @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public b a(String... strArr) {
            this.d = AlertDialogFragmentView.a(strArr);
            return this;
        }

        @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.a
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public AlertDialogFragmentView b() {
            return new AlertDialogFragmentView(this.a, this.d, this.c == null ? AlertDialogFragmentView.c() : this.c, this.e == null ? AlertDialogFragmentView.e() : this.e, this.b);
        }
    }

    public static abstract class c implements DialogInterface.OnClickListener {
        private c a;
        private boolean b = false;

        public c() {
        }

        public c(c cVar) {
            this.a = cVar;
        }

        public void a(DialogInterface dialogInterface) {
            if (this.a != null) {
                this.a.a(dialogInterface);
            }
            if (this.b) {
                return;
            }
            this.b = true;
            b(dialogInterface);
        }

        public void a(DialogInterface dialogInterface, int i) {
            if (this.a != null) {
                this.a.a(dialogInterface, i);
            }
        }

        public void b(DialogInterface dialogInterface) {
            if (this.b) {
                return;
            }
            if (this.a != null) {
                this.a.b(dialogInterface);
            }
            this.b = true;
        }

        public void b(DialogInterface dialogInterface, int i) {
            if (this.a != null) {
                this.a.b(dialogInterface, i);
            }
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            if (this.a != null) {
                this.a.onClick(dialogInterface, i);
            }
            if (i == -1) {
                a(dialogInterface, i);
            }
            if (i == -2) {
                b(dialogInterface, i);
            }
            if (this.b) {
                return;
            }
            this.b = true;
            b(dialogInterface);
        }
    }

    public static class d extends a<d, AlertDialogFragmentView> {
        protected String c;

        private d(Context context) {
            super(context);
        }

        private d(Context context, String str) {
            super(context);
            this.c = str;
        }

        @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public d a(String... strArr) {
            this.c = AlertDialogFragmentView.a(strArr);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.a
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public AlertDialogFragmentView b() {
            return new AlertDialogFragmentView(this.a, this.c, AlertDialogFragmentView.c(), null, this.b);
        }
    }

    public AlertDialogFragmentView(Context context) {
        super(context);
    }

    private AlertDialogFragmentView(Context context, String str, String str2, String str3, c cVar) {
        super(context);
        Bundle bundle = new Bundle();
        bundle.putString("com.naver.glink.ARG_MESSAGE", str);
        bundle.putString("com.naver.glink.ARG_POSITIVE_NAME", str2);
        bundle.putString(c, str3);
        setArguments(bundle);
        setAlertDialogListener(cVar);
    }

    public static b a(Context context, String str) {
        return new b(context, str);
    }

    protected static String a(String[] strArr) {
        if (strArr == null) {
            return "";
        }
        return strArr.length == 1 ? strArr[0] : new StringBuilder().append(strArr).toString();
    }

    public static d b(Context context, String str) {
        return new d(context, str);
    }

    protected static String c() {
        return com.naver.glink.android.sdk.d.a(R.string.confirm);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String e() {
        return com.naver.glink.android.sdk.d.a(R.string.cancel);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.alert_dialog, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Context context) {
        super.a(context);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView
    public void a(DialogInterface dialogInterface) {
        super.a(dialogInterface);
        if (this.d != null) {
            this.d.a(dialogInterface);
        }
    }

    protected void a(View view) {
        String string = getArguments().getString("com.naver.glink.ARG_MESSAGE");
        String string2 = getArguments().getString("com.naver.glink.ARG_POSITIVE_NAME");
        String string3 = getArguments().getString(c);
        TextView textView = (TextView) view.findViewById(R.id.dialog_message);
        TextView textView2 = (TextView) view.findViewById(R.id.dialog_confirm);
        TextView textView3 = (TextView) view.findViewById(R.id.dialog_cancel);
        if (TextUtils.isEmpty(string)) {
            textView.setVisibility(8);
        } else {
            textView.setText(string);
            textView.setVisibility(0);
        }
        if (TextUtils.isEmpty(string2)) {
            textView2.setVisibility(8);
        } else {
            textView2.setText(string2);
            textView2.setVisibility(0);
            textView2.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.AlertDialogFragmentView.1
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    if (AlertDialogFragmentView.this.d != null) {
                        AlertDialogFragmentView.this.d.onClick(AlertDialogFragmentView.this, -1);
                    }
                    AlertDialogFragmentView.this.dismiss();
                }
            });
        }
        if (TextUtils.isEmpty(string3)) {
            textView3.setVisibility(8);
            return;
        }
        textView3.setText(string3);
        textView3.setVisibility(0);
        textView3.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.AlertDialogFragmentView.2
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                if (AlertDialogFragmentView.this.d != null) {
                    AlertDialogFragmentView.this.d.onClick(AlertDialogFragmentView.this, -2);
                }
                AlertDialogFragmentView.this.dismiss();
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
        double d2 = com.naver.glink.android.sdk.d.i() ? ((double) point.x) * 0.5d : ((double) point.x) * 0.8d;
        View viewFindViewById = view.findViewById(R.id.dialog_layout);
        viewFindViewById.getLayoutParams().width = (int) d2;
        setCanceledOnTouchOutside(true, viewFindViewById);
        a(view);
    }

    public void setAlertDialogListener(c cVar) {
        this.d = cVar;
    }
}
