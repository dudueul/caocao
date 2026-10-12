package com.naver.glink.android.sdk.ui.parent;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.a.b;
import com.naver.glink.android.sdk.a.k;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView;
import com.naver.plug.android.core.api.PlugError;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class PlugFragmentView extends FragmentView {
    private ViewGroup a;
    private List<Runnable> b;

    public PlugFragmentView(Context context) {
        super(context);
        this.b = new CopyOnWriteArrayList();
    }

    @NonNull
    public final String a(@StringRes int i, Object... objArr) {
        return getContext().getString(i, objArr);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        b.a(this);
        for (Runnable runnable : this.b) {
            if (runnable != null) {
                runnable.run();
            }
        }
        this.b.clear();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        this.a = (ViewGroup) view.findViewById(R.id.fragment_error_view);
        if (this.a != null) {
            View viewFindViewById = this.a.findViewById(R.id.retry);
            d.e().e(viewFindViewById);
            viewFindViewById.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.parent.PlugFragmentView.1
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    PlugFragmentView.this.c();
                }
            });
            View viewFindViewById2 = this.a.findViewById(R.id.btn_login);
            d.e().e(viewFindViewById2);
            viewFindViewById2.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.parent.PlugFragmentView.2
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    if (LoginHelper.a().isLogin(PlugFragmentView.this.getContext())) {
                        return;
                    }
                    LoginHelper.a().builder(PlugFragmentView.this.getContext(), new Glink.OnLoggedInListener() { // from class: com.naver.glink.android.sdk.ui.parent.PlugFragmentView.2.1
                        @Override // com.naver.glink.android.sdk.Glink.OnLoggedInListener
                        public void onLoggedIn(boolean z) {
                            PlugFragmentView.this.c();
                        }
                    }).a();
                }
            });
            View viewFindViewById3 = this.a.findViewById(R.id.btn_logout);
            d.e().e(viewFindViewById3);
            viewFindViewById3.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.parent.PlugFragmentView.3
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    LoginHelper.a().logout(PlugFragmentView.this.getContext());
                    d.a(new Runnable() { // from class: com.naver.glink.android.sdk.ui.parent.PlugFragmentView.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            d.c(PlugFragmentView.this.getContext());
                        }
                    });
                }
            });
        }
    }

    public void a(EditText editText) {
        k.a(getContext(), editText);
    }

    public void a(PlugError plugError) {
        if (plugError.isLoginError()) {
            a(plugError.errorMessage);
        } else if (plugError.isCloseMemberError()) {
            b(plugError.errorMessage);
        } else {
            c(plugError.errorMessage);
        }
    }

    public void a(Object obj) {
        b.c(obj);
    }

    protected void a(Runnable runnable) {
        if (isAttachedToWindow()) {
            runnable.run();
        } else {
            this.b.add(runnable);
        }
    }

    public void a(String str) {
        if (str == null) {
            c(getContext().getString(R.string.network_error));
            return;
        }
        if (this.a != null) {
            ((TextView) this.a.findViewById(R.id.message)).setText(str);
            this.a.setVisibility(0);
            ((Button) this.a.findViewById(R.id.retry)).setVisibility(8);
            ((Button) this.a.findViewById(R.id.btn_login)).setVisibility(0);
            ((Button) this.a.findViewById(R.id.btn_logout)).setVisibility(8);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void b() {
        super.b();
        b.b(this);
        h();
        com.naver.glink.android.sdk.ui.tabs.b.i();
    }

    public void b(@StringRes int i) {
        c(getContext().getString(i));
    }

    public void b(String str) {
        if (str == null) {
            c(getContext().getString(R.string.network_error));
            return;
        }
        if (this.a != null) {
            ((TextView) this.a.findViewById(R.id.message)).setText(str);
            this.a.setVisibility(0);
            ((Button) this.a.findViewById(R.id.retry)).setVisibility(8);
            ((Button) this.a.findViewById(R.id.btn_login)).setVisibility(8);
            ((Button) this.a.findViewById(R.id.btn_logout)).setVisibility(0);
        }
    }

    public void b_() {
    }

    @NonNull
    public final String c(@StringRes int i) {
        return getContext().getString(i);
    }

    public void c() {
    }

    public void c(String str) {
        if (str == null) {
            c(getContext().getString(R.string.network_error));
            return;
        }
        if (this.a != null) {
            ((TextView) this.a.findViewById(R.id.message)).setText(str);
            this.a.setVisibility(0);
            ((Button) this.a.findViewById(R.id.retry)).setVisibility(0);
            ((Button) this.a.findViewById(R.id.btn_login)).setVisibility(8);
            ((Button) this.a.findViewById(R.id.btn_logout)).setVisibility(8);
        }
    }

    public void g() {
        if (this.a != null) {
            this.a.setVisibility(8);
        }
    }

    public void h() {
        k.a(getContext(), (ViewGroup) this);
    }

    public void i() {
    }
}
