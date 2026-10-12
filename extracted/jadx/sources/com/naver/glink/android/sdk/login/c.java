package com.naver.glink.android.sdk.login;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.login.neoid.NeoIdSdkManager;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;

/* JADX INFO: compiled from: NeoIdLoginBuilder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c extends a.AbstractC0034a {
    private boolean e;

    public c(Glink.OnLoggedInListener onLoggedInListener) {
        super(onLoggedInListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        Context contextR = d.r();
        if (contextR == null) {
            return;
        }
        if (this.a != null) {
            this.a.onLoggedIn(z);
        }
        com.naver.glink.android.sdk.api.requests.a.d();
        com.naver.glink.android.sdk.a.a.b.c(new LoginHelper.b(true));
        if (z) {
            f.a(contextR);
        }
    }

    @Override // com.naver.glink.android.sdk.login.a.AbstractC0034a
    /* JADX INFO: renamed from: a */
    public /* bridge */ /* synthetic */ a.AbstractC0034a b(String str, AlertDialogFragmentView.c cVar) {
        return super.b(str, cVar);
    }

    @Override // com.naver.glink.android.sdk.login.a.AbstractC0034a, com.naver.glink.android.sdk.login.a.b
    public /* bridge */ /* synthetic */ a.b a(boolean z) {
        return super.a(z);
    }

    @Override // com.naver.glink.android.sdk.login.a.b
    public void a() {
        Context contextR = d.r();
        if (contextR == null) {
        }
        LoginHelper.LoginType.NEO_ID.init(contextR);
        switch (NeoIdSdkManager.c()) {
            case NEED_LOGIN:
                if (!this.d) {
                    b(false);
                } else if (TextUtils.isEmpty(this.b) || this.e) {
                    NeoIdSdkManager.a(contextR, new Glink.OnLoggedInListener() { // from class: com.naver.glink.android.sdk.login.c.1
                        @Override // com.naver.glink.android.sdk.Glink.OnLoggedInListener
                        public void onLoggedIn(boolean z) {
                            c.this.b(z);
                        }
                    });
                } else {
                    AlertDialogFragmentView.a(contextR, this.b).a(new AlertDialogFragmentView.c(this.c) { // from class: com.naver.glink.android.sdk.login.c.2
                        @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                        public void a(DialogInterface dialogInterface, int i) {
                            c.this.e = true;
                            c.this.a();
                        }
                    }).a();
                }
                break;
            case OK:
                b(true);
                break;
        }
    }
}
