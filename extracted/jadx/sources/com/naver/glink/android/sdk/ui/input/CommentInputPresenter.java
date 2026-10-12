package com.naver.glink.android.sdk.ui.input;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.model.Comment;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CommentInputPresenter implements b.a {
    private static final int a = 1000;
    private final b.InterfaceC0044b b;
    private final From c;
    private Comment d;
    private int e;

    public enum From {
        ARTICLE,
        REPLIES
    }

    public static class a {
        public final From a;
        public final Comment b;
        public final Responses.l c;
        public final com.naver.glink.android.sdk.api.requests.b.a d;

        a(From from, Comment comment, Responses.l lVar) {
            this.a = from;
            this.b = comment;
            this.c = lVar;
            this.d = null;
        }

        a(From from, Comment comment, com.naver.glink.android.sdk.api.requests.b.a aVar) {
            this.a = from;
            this.b = comment;
            this.c = null;
            this.d = aVar;
        }
    }

    public CommentInputPresenter(b.InterfaceC0044b interfaceC0044b, From from) {
        this.b = interfaceC0044b;
        this.c = from;
        interfaceC0044b.setPresenter(this);
    }

    @Override // com.naver.glink.android.sdk.a.d.a
    public void a() {
        this.b.setInputText(this.d.content);
    }

    @Override // com.naver.glink.android.sdk.ui.input.b.a
    public void a(final Context context, String str) {
        this.d.content = str;
        final Comment comment = this.d;
        if (d.k()) {
            f.a(comment).showProgress(true).checkNetworkConnected(true).execute(context, new RequestListener<Responses.l>() { // from class: com.naver.glink.android.sdk.ui.input.CommentInputPresenter.1
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.l lVar) {
                    com.naver.glink.android.sdk.a.a.b.c(new a(CommentInputPresenter.this.c, comment, lVar));
                    if (comment.isNewComment()) {
                        com.naver.glink.android.sdk.b.a(comment.articleId);
                    }
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    AlertDialogFragmentView.b(context, plugError.errorMessage).a();
                }
            });
        } else {
            com.naver.glink.android.sdk.api.requests.b.a(comment, this.e).showProgress(true).checkNetworkConnected(true).execute(context, new RequestListener<com.naver.glink.android.sdk.api.requests.b.a>() { // from class: com.naver.glink.android.sdk.ui.input.CommentInputPresenter.2
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull com.naver.glink.android.sdk.api.requests.b.a aVar) {
                    if (!aVar.success) {
                        AlertDialogFragmentView.b(context, aVar.a()).a();
                        return;
                    }
                    com.naver.glink.android.sdk.a.a.b.c(new a(CommentInputPresenter.this.c, comment, aVar));
                    if (comment.isNewComment()) {
                        com.naver.glink.android.sdk.b.a(comment.articleId);
                    }
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    AlertDialogFragmentView.b(context, plugError.errorMessage).a();
                }
            });
        }
    }

    public void a(Comment comment, int i) {
        this.d = comment;
        this.e = i;
        this.b.c_();
    }

    @Override // com.naver.glink.android.sdk.ui.input.b.a
    public boolean b() {
        return false;
    }

    @Override // com.naver.glink.android.sdk.ui.input.b.a
    public InputFilter[] c() {
        return new InputFilter[]{new com.naver.glink.android.sdk.a.b(1000)};
    }
}
