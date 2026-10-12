package com.naver.glink.android.sdk.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.connection.NetworkState;
import com.nhn.android.naverlogin.ui.OAuthLoginActivity;
import java.util.UUID;

/* JADX INFO: compiled from: NaverLoginBuilder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b extends com.naver.glink.android.sdk.login.a.AbstractC0034a {
    private static final String e = "NaverLoginBuilder";
    private final OAuthLoginHandler f;

    /* JADX INFO: compiled from: NaverLoginBuilder.java */
    private static class a extends AsyncTask<Void, Void, String> {
        private Context a;
        private InterfaceC0035a b;
        private final String c = UUID.randomUUID().toString();

        /* JADX INFO: renamed from: com.naver.glink.android.sdk.login.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: NaverLoginBuilder.java */
        interface InterfaceC0035a {
            void a(String str);
        }

        a(Context context, InterfaceC0035a interfaceC0035a) {
            this.a = context;
            this.b = interfaceC0035a;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public String doInBackground(Void... voidArr) {
            return OAuthLogin.getInstance().refreshAccessToken(this.a);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(String str) {
            if (this.b != null) {
                this.b.a(str);
            }
            com.naver.glink.android.sdk.ui.widget.progress.b.b(this.c);
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            com.naver.glink.android.sdk.ui.widget.progress.b.a(this.c);
        }
    }

    public b(final Glink.OnLoggedInListener onLoggedInListener) {
        super(onLoggedInListener);
        this.f = new OAuthLoginHandler() { // from class: com.naver.glink.android.sdk.login.b.1
            @Override // com.nhn.android.naverlogin.OAuthLoginHandler
            public void run(boolean z) {
                Context contextR = d.r();
                if (contextR == null) {
                    return;
                }
                if (onLoggedInListener != null) {
                    onLoggedInListener.onLoggedIn(z);
                }
                com.naver.glink.android.sdk.api.requests.a.d();
                com.naver.glink.android.sdk.a.a.b.c(new LoginHelper.b(true));
                if (z) {
                    f.a(contextR);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Context context) {
        if (!this.d) {
            b(false);
        } else if (TextUtils.isEmpty(this.b)) {
            a(context, this.f);
        } else {
            AlertDialogFragmentView.a(context, this.b).a(new AlertDialogFragmentView.c(this.c) { // from class: com.naver.glink.android.sdk.login.b.3
                @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                public void a(DialogInterface dialogInterface, int i) {
                    b.this.a(context, b.this.f);
                }
            }).a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Context context, final OAuthLoginHandler oAuthLoginHandler) {
        if (NetworkState.checkConnectivity(context, true, new NetworkState.RetryListener() { // from class: com.naver.glink.android.sdk.login.b.4
            @Override // com.nhn.android.naverlogin.connection.NetworkState.RetryListener
            public void onResult(boolean z) {
                if (z) {
                    b.this.a(context, oAuthLoginHandler);
                }
            }
        })) {
            OAuthLogin.mOAuthLoginHandler = oAuthLoginHandler;
            Intent intent = new Intent(context, (Class<?>) OAuthLoginActivity.class);
            if (d.h(context)) {
                com.naver.glink.android.sdk.ui.main.b.a();
                intent.addFlags(268435456);
                intent.addFlags(8388608);
                intent.addFlags(524288);
                intent.addFlags(2097152);
                com.naver.glink.android.sdk.ui.parent.plugfragment.a.d.a(true);
            }
            context.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        try {
            this.f.run(z);
        } catch (Exception e2) {
            Log.d(e, e2.toString());
        }
    }

    @Override // com.naver.glink.android.sdk.login.a.AbstractC0034a
    /* JADX INFO: renamed from: a */
    public /* bridge */ /* synthetic */ com.naver.glink.android.sdk.login.a.AbstractC0034a b(String str, AlertDialogFragmentView.c cVar) {
        return super.b(str, cVar);
    }

    @Override // com.naver.glink.android.sdk.login.a.AbstractC0034a, com.naver.glink.android.sdk.login.a.b
    public /* bridge */ /* synthetic */ com.naver.glink.android.sdk.login.a.b a(boolean z) {
        return super.a(z);
    }

    @Override // com.naver.glink.android.sdk.login.a.b
    public void a() {
        if (Log.isLoggable(e, 3)) {
            Log.d(e, "login execute");
            Log.d(e, "alertMessage: " + this.b);
            Log.d(e, "handler: " + this.f);
        }
        final Context contextR = d.r();
        if (contextR == null) {
        }
        LoginHelper.LoginType.NAVER.init(contextR);
        OAuthLogin oAuthLogin = OAuthLogin.getInstance();
        switch (oAuthLogin.getState(contextR)) {
            case NEED_LOGIN:
                a(contextR);
                break;
            case OK:
            case NEED_REFRESH_TOKEN:
                if (!TextUtils.isEmpty(oAuthLogin.getRefreshToken(contextR))) {
                    new a(contextR, new a.InterfaceC0035a() { // from class: com.naver.glink.android.sdk.login.b.2
                        @Override // com.naver.glink.android.sdk.login.b.a.InterfaceC0035a
                        public void a(String str) {
                            if (TextUtils.isEmpty(str)) {
                                b.this.a(contextR);
                            } else {
                                b.this.b(true);
                            }
                        }
                    }).execute(new Void[0]);
                } else {
                    a(contextR);
                }
                break;
        }
    }
}
