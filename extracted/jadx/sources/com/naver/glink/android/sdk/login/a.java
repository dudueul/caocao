package com.naver.glink.android.sdk.login;

import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;

/* JADX INFO: compiled from: LoginBuilders.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.login.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LoginBuilders.java */
    static abstract class AbstractC0034a implements b {
        protected final Glink.OnLoggedInListener a;
        protected String b;
        protected AlertDialogFragmentView.c c;
        protected boolean d = true;

        AbstractC0034a(Glink.OnLoggedInListener onLoggedInListener) {
            this.a = onLoggedInListener;
        }

        @Override // com.naver.glink.android.sdk.login.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public AbstractC0034a b(String str, AlertDialogFragmentView.c cVar) {
            this.b = str;
            this.c = cVar;
            return this;
        }

        @Override // com.naver.glink.android.sdk.login.a.b
        public b a(boolean z) {
            this.d = z;
            return this;
        }
    }

    /* JADX INFO: compiled from: LoginBuilders.java */
    public interface b {
        b a(boolean z);

        void a();

        b b(String str, AlertDialogFragmentView.c cVar);
    }
}
