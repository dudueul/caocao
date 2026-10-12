package com.tapjoy.internal;

import android.os.Handler;
import android.webkit.WebView;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class du extends ds {
    WebView f;
    private List g;
    private final String h;

    public du(List list, String str) {
        this.g = list;
        this.h = str;
    }

    @Override // com.tapjoy.internal.ds
    public final void a() {
        super.a();
        this.f = new WebView(df.a().a);
        this.f.getSettings().setJavaScriptEnabled(true);
        a(this.f);
        dg.a();
        dg.a(this.f, this.h);
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            String externalForm = ((cy) it.next()).b.toExternalForm();
            dg.a();
            WebView webView = this.f;
            if (externalForm != null) {
                dg.a(webView, "var script=document.createElement('script');script.setAttribute(\"type\",\"text/javascript\");script.setAttribute(\"src\",\"%SCRIPT_SRC%\");document.body.appendChild(script);".replace("%SCRIPT_SRC%", externalForm));
            }
        }
    }

    @Override // com.tapjoy.internal.ds
    public final void b() {
        super.b();
        new Handler().postDelayed(new Runnable() { // from class: com.tapjoy.internal.du.1
            private WebView b;

            {
                this.b = du.this.f;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.b.destroy();
            }
        }, 2000L);
        this.f = null;
    }
}
