package com.naver.glink.android.sdk.ui.streaming.streamer;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.adjust.sdk.Constants;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.plug.android.core.api.Response;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;

/* JADX INFO: compiled from: StreamerAgreeFragment.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a extends com.naver.glink.android.sdk.ui.parent.a.a {
    private static final String a = "com.naver.glink.ARG_AGREEMENT_PATH";
    private static final String b = "com.naver.glink.ARG_SHOW_WIDGET";
    private boolean c;

    public static a a(String str, boolean z) {
        a aVar = new a();
        Bundle bundle = new Bundle();
        bundle.putString(a, str);
        bundle.putBoolean(b, z);
        aVar.setArguments(bundle);
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        com.naver.glink.android.sdk.api.streaming.c.b().execute(getActivity(), new RequestListener<Responses.x>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.a.3
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.x xVar) {
                com.naver.glink.android.sdk.ui.streaming.b.a(a.this.getActivity(), a.this.getArguments().getBoolean(a.b));
                a.this.c = true;
                a.this.dismiss();
            }
        });
    }

    @Override // com.naver.glink.android.sdk.ui.parent.a.a
    public void b() {
        dismiss();
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_streamer_agree, viewGroup, false);
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.c || !getArguments().getBoolean(b)) {
            return;
        }
        com.naver.glink.android.sdk.d.f(getActivity());
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        a();
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        String string = getArguments().getString(a);
        this.c = false;
        WebView webView = (WebView) view.findViewById(R.id.web_view);
        Request<Response> requestF = com.naver.glink.android.sdk.api.requests.f.f("/" + string);
        webView.loadUrl(requestF.getUrl(), requestF.getHeaders());
        webView.setWebViewClient(new WebViewClient() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.a.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                Uri uri = Uri.parse(str);
                if (!uri.getScheme().equals(Constants.SCHEME)) {
                    return false;
                }
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(uri);
                webView2.getContext().startActivity(intent);
                return true;
            }
        });
        view.findViewById(R.id.agree).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.a.2
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                a.this.c();
            }
        });
    }
}
