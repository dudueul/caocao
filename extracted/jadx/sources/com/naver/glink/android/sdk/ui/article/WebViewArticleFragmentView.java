package com.naver.glink.android.sdk.ui.article;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.facebook.places.model.PlaceFields;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.i;
import com.naver.glink.android.sdk.a.y;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.api.response.ArticleMedia;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.glink.android.sdk.model.Comment;
import com.naver.glink.android.sdk.statistics.jackpot.JackpotEvent;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.input.CommentInputPresenter;
import com.naver.glink.android.sdk.ui.input.TextInputFragmentView;
import com.naver.glink.android.sdk.ui.parent.PlugFragmentView;
import com.naver.glink.android.sdk.ui.tabs.Tab;
import com.naver.glink.android.sdk.ui.viewer.MediaViewerDialog;
import com.naver.glink.android.sdk.ui.write.WriteFragmentView;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.Response;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestHelper;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class WebViewArticleFragmentView extends PlugFragmentView {
    private static final String a = "WebViewArticle";
    private static final String b = "com.naver.glink.ARG_ARTICLE_ID";
    private static final long c = 100;
    private int d;
    private Responses.b e;
    private WebView f;
    private SwipeRefreshLayout i;
    private View j;
    private View k;
    private TextInputFragmentView l;
    private CommentInputPresenter m;
    private a n;
    private com.naver.glink.android.sdk.ui.parent.plugfragment.a.c o;
    private boolean p;

    private static class LoggedInListener implements Glink.OnLoggedInListener {
        private final After a;
        private final WeakReference<WebViewArticleFragmentView> b;
        private final Comment c;
        private final String d;
        private final boolean e;

        enum After {
            RELOAD_ARTICLE,
            RETRY_COMMENT,
            LIKE_IT,
            GO_PROFILE
        }

        LoggedInListener(After after, WebViewArticleFragmentView webViewArticleFragmentView, Comment comment) {
            this.a = after;
            this.b = new WeakReference<>(webViewArticleFragmentView);
            this.c = comment;
            this.d = null;
            this.e = false;
        }

        LoggedInListener(After after, WebViewArticleFragmentView webViewArticleFragmentView, String str, boolean z) {
            this.a = after;
            this.b = new WeakReference<>(webViewArticleFragmentView);
            this.c = null;
            this.d = str;
            this.e = z;
        }

        static LoggedInListener a(WebViewArticleFragmentView webViewArticleFragmentView) {
            return new LoggedInListener(After.RELOAD_ARTICLE, webViewArticleFragmentView, null);
        }

        static LoggedInListener a(WebViewArticleFragmentView webViewArticleFragmentView, Comment comment) {
            return new LoggedInListener(After.RETRY_COMMENT, webViewArticleFragmentView, comment);
        }

        static LoggedInListener a(WebViewArticleFragmentView webViewArticleFragmentView, String str, boolean z) {
            return new LoggedInListener(After.GO_PROFILE, webViewArticleFragmentView, str, z);
        }

        static LoggedInListener b(WebViewArticleFragmentView webViewArticleFragmentView) {
            return new LoggedInListener(After.LIKE_IT, webViewArticleFragmentView, null);
        }

        @Override // com.naver.glink.android.sdk.Glink.OnLoggedInListener
        public void onLoggedIn(boolean z) {
            WebViewArticleFragmentView webViewArticleFragmentView;
            if (z && (webViewArticleFragmentView = this.b.get()) != null && webViewArticleFragmentView.isAttachedToWindow()) {
                switch (this.a) {
                    case RELOAD_ARTICLE:
                        webViewArticleFragmentView.c();
                        break;
                    case RETRY_COMMENT:
                        webViewArticleFragmentView.c();
                        webViewArticleFragmentView.a(this.c, false);
                        break;
                    case LIKE_IT:
                        webViewArticleFragmentView.m();
                        break;
                    case GO_PROFILE:
                        webViewArticleFragmentView.a(this.d, this.e);
                        break;
                }
            }
        }
    }

    private static class a extends Handler {
        public static final int a = 1;
        private final WeakReference<WebViewArticleFragmentView> b;

        a(WebViewArticleFragmentView webViewArticleFragmentView) {
            this.b = new WeakReference<>(webViewArticleFragmentView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    WebViewArticleFragmentView webViewArticleFragmentView = this.b.get();
                    if (webViewArticleFragmentView != null) {
                        Comment comment = (Comment) message.obj;
                        if (comment != null) {
                            webViewArticleFragmentView.a(comment, false);
                        } else {
                            webViewArticleFragmentView.a(false);
                        }
                    }
                    break;
            }
        }
    }

    private class b extends WebChromeClient {
        private View b;

        private b() {
        }

        @Override // android.webkit.WebChromeClient
        public View getVideoLoadingProgressView() {
            if (this.b == null) {
                this.b = LayoutInflater.from(WebViewArticleFragmentView.this.getContext()).inflate(R.layout.video_loading_progress, (ViewGroup) null);
            }
            return this.b;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            ((WebView.WebViewTransport) message.obj).setWebView(new WebView(webView.getContext()));
            message.sendToTarget();
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public void onHideCustomView() {
            com.naver.glink.android.sdk.ui.main.b.d();
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (i > 50) {
                com.naver.glink.android.sdk.ui.tabs.b.i();
                WebViewArticleFragmentView.this.p = false;
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            WebViewArticleFragmentView.this.a(false);
            com.naver.glink.android.sdk.ui.main.b.a(view, customViewCallback);
        }
    }

    private class c extends WebViewClient {
        static final String a = "\"error_code\":\"024\"";
        int b;
        int c;

        private c() {
            this.b = 0;
            this.c = 0;
        }

        int a(String str) {
            Responses.i iVarA = com.naver.glink.android.sdk.api.requests.a.a();
            if (iVarA == null) {
                return -1;
            }
            Matcher matcher = Pattern.compile("http://(m.)?cafe.naver.com/" + iVarA.cafeUrl + "/(\\d+)").matcher(str);
            if (!matcher.find() || matcher.groupCount() < 2) {
                return -1;
            }
            return Integer.parseInt(matcher.group(2));
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            com.naver.glink.android.sdk.ui.tabs.b.i();
            WebViewArticleFragmentView.this.p = false;
            if (this.b == 0) {
                WebViewArticleFragmentView.this.g();
                WebViewArticleFragmentView.this.f.setFindListener(new WebView.FindListener() { // from class: com.naver.glink.android.sdk.ui.article.WebViewArticleFragmentView.c.1
                    @Override // android.webkit.WebView.FindListener
                    public void onFindResultReceived(int i, int i2, boolean z) {
                        if (i2 <= 0) {
                            c.this.c = 0;
                        } else if (c.this.c == 0) {
                            LoginHelper.a().login(WebViewArticleFragmentView.this.getContext(), LoggedInListener.a(WebViewArticleFragmentView.this));
                            c.this.c++;
                        }
                        WebViewArticleFragmentView.this.f.setFindListener(null);
                    }
                });
                webView.findAllAsync(a);
            } else {
                WebViewArticleFragmentView.this.b(R.string.network_error);
            }
            WebViewArticleFragmentView.this.i.setRefreshing(false);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.b = 0;
            if (WebViewArticleFragmentView.this.p) {
                com.naver.glink.android.sdk.ui.tabs.b.h();
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            this.b = i;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str == null) {
                return false;
            }
            if (str.startsWith("glink://")) {
                WebViewArticleFragmentView.this.a(y.a(str));
                return true;
            }
            if (str.startsWith("http://")) {
                int iA = a(str);
                if (iA == -1) {
                    i.a(WebViewArticleFragmentView.this.getContext(), str);
                } else {
                    com.naver.glink.android.sdk.ui.tabs.b.b(iA);
                }
                return true;
            }
            if (str.startsWith("https://")) {
                i.a(WebViewArticleFragmentView.this.getContext(), str);
                return true;
            }
            if (!str.startsWith("intent://")) {
                return false;
            }
            i.a(WebViewArticleFragmentView.this.getContext(), str);
            return true;
        }
    }

    public WebViewArticleFragmentView(Context context) {
        super(context);
        this.n = new a(this);
    }

    public static WebViewArticleFragmentView a(Context context, int i) {
        WebViewArticleFragmentView webViewArticleFragmentView = new WebViewArticleFragmentView(context);
        Bundle bundle = new Bundle();
        bundle.putInt(b, i);
        webViewArticleFragmentView.setArguments(bundle);
        return webViewArticleFragmentView;
    }

    private void a(int i) {
        f.a(this.d, i).execute(getContext(), new RequestListener<Responses.m>() { // from class: com.naver.glink.android.sdk.ui.article.WebViewArticleFragmentView.3
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.m mVar) {
                if (mVar.success) {
                    WebViewArticleFragmentView.this.f.loadUrl(String.format("javascript:listComments(%s)", mVar.returnValue));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Uri uri) {
        if (uri == null) {
        }
        String host = uri.getHost();
        if (TextUtils.isEmpty(host)) {
            return;
        }
        if (!Arrays.asList("createComment", "updateComment").contains(host)) {
            a(true);
        }
        switch (host) {
            case "likeIt":
                m();
                break;
            case "updateArticle":
                com.naver.glink.android.sdk.ui.article.b.a(getContext(), this.d);
                break;
            case "deleteArticle":
                com.naver.glink.android.sdk.ui.article.b.a(getContext(), this, this.d, false);
                break;
            case "readComments":
                a(Integer.valueOf(uri.getQueryParameter(PlaceFields.PAGE)).intValue());
                break;
            case "createComment":
                a(Comment.newComment(-1, this.d, !TextUtils.isEmpty(uri.getQueryParameter("refCommentId")) ? Integer.valueOf(uri.getQueryParameter("refCommentId")).intValue() : -1, TextUtils.isEmpty(uri.getQueryParameter("targetMemberId")) ? null : uri.getQueryParameter("targetMemberId")), true);
                break;
            case "updateComment":
                int iIntValue = Integer.valueOf(uri.getQueryParameter("commentId")).intValue();
                String queryParameter = uri.getQueryParameter("content");
                Comment commentNewComment = Comment.newComment(iIntValue, this.d);
                commentNewComment.content = queryParameter;
                a(commentNewComment, true);
                break;
            case "deleteComment":
                AlertDialogFragmentView.b(getContext(), c(R.string.delete_confirm_message)).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.article.WebViewArticleFragmentView.13
                    @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                    public void a(DialogInterface dialogInterface, int i) {
                        WebViewArticleFragmentView.this.a(Comment.newComment(Integer.valueOf(uri.getQueryParameter("commentId")).intValue(), WebViewArticleFragmentView.this.d));
                    }
                }).a();
                break;
            case "back":
                com.naver.glink.android.sdk.ui.tabs.b.f();
                break;
            case "joinCafe":
                com.naver.glink.android.sdk.ui.tabs.b.a(Tab.Type.PROFILE);
                break;
            case "login":
                LoginHelper.a().login(getContext(), LoggedInListener.a(this));
                break;
            case "showArticlePhotoList":
                a(false);
                com.naver.glink.android.sdk.ui.viewer.a.a(MediaViewerDialog.From.ARTICLE, ArticleMedia.a(uri.getQueryParameters("imgUrl")), Integer.valueOf(uri.getQueryParameter("index")).intValue());
                break;
            case "goProfile":
                a(uri.getQueryParameter("userId"), Boolean.valueOf(uri.getQueryParameter("isWriter")).booleanValue());
                break;
            case "voteSuccess":
                com.naver.glink.android.sdk.b.b(this.d);
                break;
            case "goMenu":
                int iIntValue2 = Integer.valueOf(uri.getQueryParameter("menuId")).intValue();
                com.naver.glink.android.sdk.ui.tabs.b.f();
                com.naver.glink.android.sdk.ui.tabs.b.a(iIntValue2, true, false);
                break;
            default:
                Log.d(a, "host: " + host + "에 대한 처리가 없습니다.");
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Comment comment) {
        f.b(comment).showProgress(true).execute(getContext(), new RequestListener<Responses.k>() { // from class: com.naver.glink.android.sdk.ui.article.WebViewArticleFragmentView.4
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.k kVar) {
                if (kVar.success) {
                    WebViewArticleFragmentView.this.f.loadUrl(String.format("javascript:removeComment(%s)", kVar.returnValue));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Comment comment, boolean z) {
        if (z) {
            this.n.removeMessages(1);
            this.n.sendMessageDelayed(Message.obtain(this.n, 1, comment), c);
        } else if (LoginHelper.a().isLogin(getContext())) {
            b(new Runnable() { // from class: com.naver.glink.android.sdk.ui.article.WebViewArticleFragmentView.10
                @Override // java.lang.Runnable
                public void run() {
                    if (!comment.isNewComment() || WebViewArticleFragmentView.this.e.commentWritable) {
                        WebViewArticleFragmentView.this.m.a(comment, -1);
                    } else {
                        AlertDialogFragmentView.b(WebViewArticleFragmentView.this.getContext(), WebViewArticleFragmentView.this.c(R.string.comment_write_permission_error)).a();
                    }
                }
            });
        } else {
            LoginHelper.a().builder(getContext(), LoggedInListener.a(this, comment)).b(getResources().getString(R.string.require_login_message), null).a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, boolean z) {
        if (LoginHelper.a().isLogin(getContext())) {
            com.naver.glink.android.sdk.ui.tabs.b.a(str, z);
        } else {
            LoginHelper.a().builder(getContext(), LoggedInListener.a(this, str, z)).b(c(R.string.require_login_message), null).a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (!z) {
            this.l.c();
        } else {
            this.n.removeMessages(1);
            this.n.sendMessageDelayed(Message.obtain(this.n, 1), c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final Runnable runnable) {
        if (this.e == null) {
            f.c(this.d).showProgress(true).execute(getContext(), new RequestListener<Responses.b>() { // from class: com.naver.glink.android.sdk.ui.article.WebViewArticleFragmentView.11
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.b bVar) {
                    WebViewArticleFragmentView.this.e = bVar;
                    WebViewArticleFragmentView.this.b(runnable);
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    if (plugError.hasErrorMessage()) {
                        AlertDialogFragmentView.b(WebViewArticleFragmentView.this.getContext(), plugError.errorMessage).a();
                    } else {
                        Toast.makeText(WebViewArticleFragmentView.this.getContext(), WebViewArticleFragmentView.this.c(R.string.retry_after_a_while_message), 0).show();
                    }
                }
            });
        } else if (!this.e.isCafeMember) {
            AlertDialogFragmentView.a(getContext(), c(R.string.require_join_message)).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.article.WebViewArticleFragmentView.12
                @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                public void a(DialogInterface dialogInterface, int i) {
                    com.naver.glink.android.sdk.ui.tabs.b.a(Tab.Type.PROFILE);
                }
            }).a();
        } else if (runnable != null) {
            runnable.run();
        }
    }

    private Request<Response> f() {
        return f.a(this.d, d.j());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (LoginHelper.a().isLogin(getContext())) {
            b(new Runnable() { // from class: com.naver.glink.android.sdk.ui.article.WebViewArticleFragmentView.2
                @Override // java.lang.Runnable
                public void run() {
                    f.d(WebViewArticleFragmentView.this.d).showProgress(true).execute(WebViewArticleFragmentView.this.getContext(), new RequestListener<Responses.a>() { // from class: com.naver.glink.android.sdk.ui.article.WebViewArticleFragmentView.2.1
                        @Override // com.naver.plug.android.core.api.request.RequestListener
                        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(@NonNull Responses.a aVar) {
                            if (aVar.success) {
                                WebViewArticleFragmentView.this.f.loadUrl(String.format("javascript:likeIt(%s)", aVar.returnValue));
                            }
                        }

                        @Override // com.naver.plug.android.core.api.request.RequestListener
                        public void onFailure(@NonNull PlugError plugError) {
                            if (plugError.hasErrorMessage()) {
                                AlertDialogFragmentView.b(WebViewArticleFragmentView.this.getContext(), plugError.errorMessage).a();
                            }
                        }
                    });
                }
            });
        } else {
            LoginHelper.a().builder(getContext(), LoggedInListener.b(this)).b(c(R.string.require_login_message), null).a();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_web_view_article, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        this.f.onResume();
        this.k.setVisibility(0);
        this.j.setVisibility(0);
        final com.naver.glink.android.sdk.ui.parent.plugfragment.a aVarA = com.naver.glink.android.sdk.ui.parent.plugfragment.a.a();
        this.o = new com.naver.glink.android.sdk.ui.parent.plugfragment.a.c() { // from class: com.naver.glink.android.sdk.ui.article.WebViewArticleFragmentView.9
            @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.a.c
            public void a() {
                boolean zA = TextInputFragmentView.a(aVarA);
                WebViewArticleFragmentView.this.k.setVisibility(zA ? 8 : 0);
                WebViewArticleFragmentView.this.j.setVisibility(zA ? 8 : 0);
            }
        };
        aVarA.a(this.o);
        com.naver.glink.android.sdk.a.a.a(JackpotEvent.SCENE_ENTER.ARTICLE_DETAIL, this.d);
        super.a();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Bundle bundle) {
        super.a(bundle);
        if (getArguments() != null) {
            this.d = getArguments().getInt(b);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    @SuppressLint({"SetJavaScriptEnabled"})
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        this.p = true;
        this.f = (WebView) view.findViewById(R.id.web_view);
        this.f.getSettings().setJavaScriptEnabled(true);
        this.f.setWebChromeClient(new b());
        this.f.setWebViewClient(new c());
        String userAgent = RequestHelper.getUserAgent();
        if (!TextUtils.isEmpty(userAgent)) {
            this.f.getSettings().setUserAgentString(this.f.getSettings().getUserAgentString() + " " + userAgent);
        }
        this.f.setOnTouchListener(new View.OnTouchListener() { // from class: com.naver.glink.android.sdk.ui.article.WebViewArticleFragmentView.1
            float a;
            float b;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.a = motionEvent.getX();
                    this.b = motionEvent.getY();
                    return false;
                }
                if (motionEvent.getAction() != 1 || WebViewArticleFragmentView.this.l == null || !WebViewArticleFragmentView.this.l.isAttachedToWindow()) {
                    return false;
                }
                if (Math.abs(this.a - motionEvent.getX()) >= 5.0f && Math.abs(this.b - motionEvent.getY()) >= 5.0f) {
                    return false;
                }
                WebViewArticleFragmentView.this.a(true);
                return false;
            }
        });
        this.i = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        this.i.setColorSchemeColors(d.e().a);
        this.i.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.naver.glink.android.sdk.ui.article.WebViewArticleFragmentView.6
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                WebViewArticleFragmentView.this.a(false);
                WebViewArticleFragmentView.this.c();
            }
        });
        this.k = view.findViewById(R.id.back);
        this.k.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.article.WebViewArticleFragmentView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                com.naver.glink.android.sdk.ui.tabs.b.f();
            }
        });
        this.j = view.findViewById(R.id.comment_write);
        d.e().a(this.j, R.drawable.cf_btn_flcomment);
        this.j.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.article.WebViewArticleFragmentView.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                WebViewArticleFragmentView.this.a(Comment.newComment(-1, WebViewArticleFragmentView.this.d), false);
            }
        });
        this.l = TextInputFragmentView.b(getContext());
        this.m = new CommentInputPresenter(this.l, CommentInputPresenter.From.ARTICLE);
        c();
    }

    @Subscribe
    public void a(d.a aVar) {
        a(false);
    }

    @Subscribe
    public void a(CommentInputPresenter.a aVar) {
        if (aVar.c.success) {
            if (aVar.b.id == -1) {
                this.f.loadUrl(String.format("javascript:addComment(%s)", aVar.c.returnValue));
            } else {
                this.f.loadUrl(String.format("javascript:modifyComment(%s)", aVar.c.returnValue));
            }
        }
    }

    @Subscribe
    public void a(WriteFragmentView.a aVar) {
        c();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a_() {
        if (Build.VERSION.SDK_INT != 19 && this.f != null) {
            this.f.destroy();
        }
        super.a_();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void b() {
        super.b();
        a(false);
        com.naver.glink.android.sdk.ui.main.b.d();
        com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b(this.o);
        this.f.onPause();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void c() {
        if (!com.naver.plug.android.core.a.b.a(getContext())) {
            b(R.string.network_error);
        } else if (LoginHelper.a().isNeedRefreshToken(getContext())) {
            LoginHelper.a().login(getContext(), LoggedInListener.a(this));
        } else {
            Request<Response> requestF = f();
            this.f.loadUrl(requestF.getUrl(), requestF.getHeaders());
        }
    }
}
