package com.naver.glink.android.sdk.ui.article;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.parent.PlugFragmentView;
import com.naver.glink.android.sdk.ui.write.model.WritingArticle;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;

/* JADX INFO: compiled from: ArticleCompat.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {

    /* JADX INFO: compiled from: ArticleCompat.java */
    public static class a extends com.naver.plug.android.core.a.a {
        public final int a;

        public a(int i) {
            this.a = i;
        }
    }

    static void a(Context context, int i) {
        if (com.naver.plug.android.core.a.b.a(context)) {
            com.naver.glink.android.sdk.ui.tabs.b.a(WritingArticle.a(-1, i).a());
        } else {
            AlertDialogFragmentView.b(context, context.getString(R.string.internet_not_connected_error)).a();
        }
    }

    static void a(final Context context, final PlugFragmentView plugFragmentView, final int i, boolean z) {
        if (z) {
            f.a(i).showProgress(true).execute(context, new RequestListener<Responses.x>() { // from class: com.naver.glink.android.sdk.ui.article.b.2
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.x xVar) {
                    com.naver.glink.android.sdk.ui.tabs.b.f();
                    plugFragmentView.a(new a(i));
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    AlertDialogFragmentView.b(context, plugError.errorMessage).a();
                }
            });
        } else {
            AlertDialogFragmentView.a(context, context.getString(R.string.delete_confirm_message)).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.article.b.1
                @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                public void a(DialogInterface dialogInterface, int i2) {
                    b.a(context, plugFragmentView, i, true);
                }
            }).a();
        }
    }
}
