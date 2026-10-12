package com.naver.glink.android.sdk.ui.viewer.b;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import com.naver.glink.android.sdk.api.response.ArticleMedia;
import com.naver.glink.android.sdk.ui.viewer.MediaViewerDialog;
import com.naver.glink.android.sdk.ui.widget.dragviewer.DragView;
import com.squareup.otto.Subscribe;

/* JADX INFO: compiled from: ViewerVideoViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class d implements a {
    private final View a;
    private final c b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;

    public d(View view) {
        this.a = view;
        this.b = new c(view, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        com.naver.glink.android.sdk.a.a.b.c(new MediaViewerDialog.a());
    }

    private void e() {
        if (this.d) {
            return;
        }
        this.d = true;
        com.naver.glink.android.sdk.a.a.b.a(this);
    }

    private void f() {
        if (this.d) {
            this.d = false;
            com.naver.glink.android.sdk.a.a.b.b(this);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.viewer.b.a
    public View a() {
        return this.a;
    }

    @Override // com.naver.glink.android.sdk.ui.viewer.b.a
    public void a(Context context, final MediaViewerDialog.From from, ArticleMedia articleMedia, boolean z) {
        if (!z) {
            this.b.b();
            f();
            return;
        }
        if (articleMedia.a(true)) {
            this.b.a(articleMedia.b(true));
        } else {
            this.b.a(context, articleMedia.vid, articleMedia.logoImage);
            final c.a aVarC = this.b.c();
            this.b.a(new c.a() { // from class: com.naver.glink.android.sdk.ui.viewer.b.d.1
                @Override // com.naver.glink.android.sdk.ui.viewer.b.c.a
                public void a(boolean z2) {
                    if (d.this.c) {
                        d.this.c = false;
                        return;
                    }
                    d.this.e = true;
                    aVarC.a(z2);
                    com.naver.glink.android.sdk.a.a.b.c(new MediaViewerDialog.b(8));
                }
            });
            this.b.a.setOnVideoCloseClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.viewer.b.d.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (from == MediaViewerDialog.From.BANNER) {
                        d.this.d();
                        return;
                    }
                    d.this.e = false;
                    d.this.b.a.j();
                    d.this.b.a();
                    com.naver.glink.android.sdk.a.a.b.c(new MediaViewerDialog.b(0));
                }
            });
            this.b.a.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.naver.glink.android.sdk.ui.viewer.b.d.3
                @Override // android.media.MediaPlayer.OnCompletionListener
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if (from == MediaViewerDialog.From.BANNER) {
                        d.this.d();
                    }
                }
            });
            if (this.f) {
                this.f = false;
                this.b.a(true);
            }
        }
        e();
    }

    @Subscribe
    public void a(DragView.c cVar) {
        if (cVar.a == DragView.DragState.DRAGGING) {
            this.c = true;
        }
    }

    public void a(boolean z) {
        this.f = z;
    }

    @Override // com.naver.glink.android.sdk.ui.viewer.b.a
    public void b() {
        f();
    }

    @Override // com.naver.glink.android.sdk.ui.viewer.b.a
    public boolean c() {
        return this.e;
    }
}
