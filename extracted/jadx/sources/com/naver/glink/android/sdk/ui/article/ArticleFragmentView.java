package com.naver.glink.android.sdk.ui.article;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.google.gson.GsonBuilder;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.o;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.e;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.glink.android.sdk.model.Comment;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.input.CommentInputPresenter;
import com.naver.glink.android.sdk.ui.input.TextInputFragmentView;
import com.naver.glink.android.sdk.ui.parent.PlugListFragmentView;
import com.naver.glink.android.sdk.ui.write.WriteFragmentView;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ArticleFragmentView extends PlugListFragmentView {
    private static final String a = "ArticleFragmentView";
    private static final String b = "com.naver.glink.ARG_ARTICLE_ID";
    private static final String c = "com.naver.glink.ARG_IS_LIKEABLE";
    private static final String d = "com.naver.glink.ARG_IS_COMMENT_WRITABLE";
    private static final String e = "com.naver.glink.ARG_COMMENT_AUTH_DESC";
    private static final String f = "com.naver.glink.ARG_CAFE_MEMBER_LEVEL";
    private static final String i = "com.naver.glink.ARG_PARENT_COMMENT_JSON";
    private static final String j = "com.naver.glink.ARG_SHOW_COMMENT_EDIT";
    private List<com.naver.glink.android.sdk.api.requests.b.c.a> A;
    private int k;
    private com.naver.glink.android.sdk.api.a.C0019a l;
    private boolean m;
    private boolean n;
    private String o;
    private int p;
    private boolean q;
    private com.naver.glink.android.sdk.api.requests.b.c.a r;
    private Request<com.naver.glink.android.sdk.api.requests.b.c> s;
    private com.naver.glink.android.sdk.ui.article.a t;
    private SwipeRefreshLayout u;
    private View v;
    private View w;
    private TextInputFragmentView x;
    private CommentInputPresenter y;
    private com.naver.glink.android.sdk.ui.parent.plugfragment.a.c z;

    private static class LoggedInListener implements Glink.OnLoggedInListener {
        private final After a;
        private final WeakReference<ArticleFragmentView> b;
        private final Comment c;
        private final String d;
        private final boolean e;

        enum After {
            COMMENT,
            LIKE,
            PROFILE,
            REPORT
        }

        private LoggedInListener(After after, ArticleFragmentView articleFragmentView, Comment comment, String str, boolean z) {
            this.a = after;
            this.b = new WeakReference<>(articleFragmentView);
            this.c = comment;
            this.d = str;
            this.e = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static LoggedInListener b(ArticleFragmentView articleFragmentView, Comment comment) {
            return new LoggedInListener(After.COMMENT, articleFragmentView, comment, null, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static LoggedInListener b(ArticleFragmentView articleFragmentView, String str, boolean z) {
            return new LoggedInListener(After.PROFILE, articleFragmentView, null, str, z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static LoggedInListener c(ArticleFragmentView articleFragmentView) {
            return new LoggedInListener(After.LIKE, articleFragmentView, null, null, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static LoggedInListener d(ArticleFragmentView articleFragmentView) {
            return new LoggedInListener(After.REPORT, articleFragmentView, null, null, false);
        }

        @Override // com.naver.glink.android.sdk.Glink.OnLoggedInListener
        public void onLoggedIn(boolean z) {
            final ArticleFragmentView articleFragmentView;
            if (d.r() == null || !z || (articleFragmentView = this.b.get()) == null) {
                return;
            }
            com.naver.glink.android.sdk.api.requests.a.a(d.r(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.LoggedInListener.1
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.i iVar) {
                    articleFragmentView.a(iVar, false, true, true, new Runnable() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.LoggedInListener.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ArticleFragmentView articleFragmentView2 = (ArticleFragmentView) LoggedInListener.this.b.get();
                            if (articleFragmentView2 == null) {
                            }
                            switch (LoggedInListener.this.a) {
                                case COMMENT:
                                    articleFragmentView2.a(LoggedInListener.this.c);
                                    break;
                                case LIKE:
                                    articleFragmentView2.t();
                                    break;
                                case PROFILE:
                                    articleFragmentView2.b(LoggedInListener.this.d, LoggedInListener.this.e);
                                    break;
                                case REPORT:
                                    articleFragmentView2.a(false);
                                    break;
                            }
                        }
                    });
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                }
            });
        }
    }

    static class a {
        int a;
        com.naver.glink.android.sdk.api.requests.b.c.a b;
        List<com.naver.glink.android.sdk.api.requests.b.c.a> c;
        com.naver.glink.android.sdk.api.requests.b.c.C0027b d;

        a() {
        }
    }

    public ArticleFragmentView(Context context) {
        super(context);
    }

    public static ArticleFragmentView a(Context context, int i2) {
        return a(context, i2, (com.naver.glink.android.sdk.api.a.C0019a) null, (com.naver.glink.android.sdk.api.requests.b.c.a) null, false);
    }

    public static ArticleFragmentView a(Context context, int i2, com.naver.glink.android.sdk.api.a.C0019a c0019a, com.naver.glink.android.sdk.api.requests.b.c.a aVar, boolean z) {
        ArticleFragmentView articleFragmentView = new ArticleFragmentView(context);
        Bundle bundle = new Bundle();
        bundle.putInt(b, i2);
        if (c0019a != null) {
            bundle.putBoolean(c, c0019a.isLikeable);
            bundle.putBoolean(d, c0019a.isCommentWritable);
            bundle.putString(e, c0019a.commentAuthDesc);
            bundle.putInt(f, c0019a.cafeMemberLevel);
        }
        if (aVar != null) {
            bundle.putString(i, aVar.toJsonString());
        }
        bundle.putBoolean(j, z);
        articleFragmentView.setArguments(bundle);
        return articleFragmentView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Responses.i iVar, boolean z, boolean z2, boolean z3, final Runnable runnable) {
        if (z2 && this.s != null) {
            this.s.cancel();
            this.s = null;
        }
        if (z) {
            this.t.a(this.r);
            e(this.r.d());
        } else if (this.l == null || z2) {
            com.naver.glink.android.sdk.api.requests.c.a(this.k).showProgress(z3).execute(getContext(), new RequestListener<com.naver.glink.android.sdk.api.a.C0019a>() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.15
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull com.naver.glink.android.sdk.api.a.C0019a c0019a) {
                    ArticleFragmentView.this.g();
                    ArticleFragmentView.this.l = c0019a;
                    ArticleFragmentView.this.a(iVar, false, false, false, runnable);
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    ArticleFragmentView.this.a(plugError);
                }
            });
            return;
        } else {
            this.t.a(this.l, iVar);
            d(-1);
            s();
        }
        if (runnable != null) {
            runnable.run();
        }
        this.u.setRefreshing(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void a(com.naver.glink.android.sdk.api.requests.b.a aVar) {
        if (m()) {
            com.naver.glink.android.sdk.api.requests.b.c.a aVar2 = ((com.naver.glink.android.sdk.api.requests.b.a.C0025a) aVar.result).comment.b() ? ((com.naver.glink.android.sdk.api.requests.b.a.C0025a) aVar.result).comment : ((com.naver.glink.android.sdk.api.requests.b.a.C0025a) aVar.result).parent;
            this.t.b(aVar2);
            a aVar3 = new a();
            aVar3.a = this.k;
            aVar3.b = aVar2;
            aVar3.c = this.t.a();
            aVar3.d = ((com.naver.glink.android.sdk.api.requests.b.a.C0025a) aVar.result).count;
            a((Object) aVar3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Comment comment) {
        if (p() && this.l == null) {
            return;
        }
        if (!LoginHelper.a().isLogin(getContext())) {
            LoginHelper.a().builder(getContext(), LoggedInListener.b(this, comment)).b(getResources().getString(R.string.need_login), null).a();
            return;
        }
        if (r()) {
            this.y.a(comment, getCafeMemberLevel());
            return;
        }
        String strC = p() ? this.l.commentAuthDesc : this.o;
        if (TextUtils.isEmpty(strC)) {
            strC = c(R.string.comment_write_permission_error);
        }
        AlertDialogFragmentView.b(getContext(), strC).a();
    }

    private void a(Request<com.naver.glink.android.sdk.api.requests.b.c> request) {
        if (this.s != null) {
            return;
        }
        this.s = request;
        this.s.execute(getContext(), new RequestListener<com.naver.glink.android.sdk.api.requests.b.c>() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.16
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull com.naver.glink.android.sdk.api.requests.b.c cVar) {
                if (cVar.success || cVar.result != 0) {
                    com.naver.glink.android.sdk.api.requests.b.c.e eVar = (com.naver.glink.android.sdk.api.requests.b.c.e) cVar.result;
                    if (ArticleFragmentView.this.m() && !eVar.b() && eVar.commentList.size() < 20) {
                        ArticleFragmentView.this.A = ((com.naver.glink.android.sdk.api.requests.b.c.e) cVar.result).commentList;
                        ArticleFragmentView.this.s = null;
                        ArticleFragmentView.this.e(((com.naver.glink.android.sdk.api.requests.b.c.e) cVar.result).pageModel.prevPage);
                        return;
                    }
                    if (ArticleFragmentView.this.A != null) {
                        eVar.commentList.addAll(ArticleFragmentView.this.A);
                        ArticleFragmentView.this.A = null;
                    }
                    ArticleFragmentView.this.t.a(eVar);
                    ArticleFragmentView.this.s = null;
                }
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                ArticleFragmentView.this.s = null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(com.naver.glink.android.sdk.api.requests.b.c.a aVar) {
        Comment commentNewComment = Comment.newComment(aVar.commentNo, this.k, getParentCommentNo(), aVar.i(), aVar.j());
        commentNewComment.content = aVar.f();
        a(commentNewComment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, boolean z) {
        if (LoginHelper.a().isLogin(getContext())) {
            com.naver.glink.android.sdk.ui.tabs.b.a(str, z);
        } else {
            LoginHelper.a().builder(getContext(), LoggedInListener.b(this, str, z)).b(c(R.string.need_login), null).a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final boolean z) {
        com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.14
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                ArticleFragmentView.this.a(iVar, ArticleFragmentView.this.m(), true, z, (Runnable) null);
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                ArticleFragmentView.this.a(plugError);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final com.naver.glink.android.sdk.api.requests.b.c.a aVar) {
        d();
        AlertDialogFragmentView.a(getContext(), c(R.string.delete_confirm_message)).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.4
            @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
            public void a(DialogInterface dialogInterface, int i2) {
                com.naver.glink.android.sdk.api.requests.b.a(ArticleFragmentView.this.k, aVar).checkNetworkConnected(true).execute(ArticleFragmentView.this.getContext(), new RequestListener<com.naver.glink.android.sdk.api.requests.b.a>() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.4.1
                    @Override // com.naver.plug.android.core.api.request.RequestListener
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(@NonNull com.naver.glink.android.sdk.api.requests.b.a aVar2) {
                        if (!aVar2.success) {
                            Toast.makeText(ArticleFragmentView.this.getContext(), aVar2.a(), 1).show();
                        } else {
                            ArticleFragmentView.this.t.b(aVar2);
                            ArticleFragmentView.this.a(aVar2);
                        }
                    }
                });
            }
        }).a();
    }

    private void d(int i2) {
        a(com.naver.glink.android.sdk.api.requests.b.a(this.k, i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i2) {
        a(com.naver.glink.android.sdk.api.requests.b.a(this.k, getParentCommentNo(), i2));
    }

    private int getCafeMemberLevel() {
        return this.l == null ? this.p : this.l.cafeMemberLevel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean m() {
        return this.r != null;
    }

    private boolean p() {
        return !m();
    }

    private boolean q() {
        return this.l == null ? this.m : this.l.isLikeable;
    }

    private boolean r() {
        return this.l == null ? this.n : this.l.isCommentWritable;
    }

    private void s() {
        new e.a(this.k).a(getContext());
    }

    public static void setStaffIconResource(ImageView imageView, int i2) {
        switch (i2) {
            case 99:
                imageView.setImageResource(R.drawable.pl_icon_pfstaff);
                imageView.setVisibility(0);
                break;
            case 999:
                imageView.setImageResource(R.drawable.pl_icon_pfmaster);
                imageView.setVisibility(0);
                break;
            default:
                imageView.setVisibility(8);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        if (!LoginHelper.a().isLogin(getContext())) {
            LoginHelper.a().builder(getContext(), LoggedInListener.c(this)).b(c(R.string.need_login), null).a();
        } else if (q()) {
            new e.a(this.k).b(getContext());
        } else {
            AlertDialogFragmentView.b(getContext(), TextUtils.isEmpty(this.l.likeAuthDesc) ? c(R.string.no_permissions) : this.l.likeAuthDesc).a();
        }
    }

    private void u() {
        if (this.t == null) {
            return;
        }
        this.t.a(getListView(), getListView().getFirstVisiblePosition(), getListView().getLastVisiblePosition());
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_article, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        this.v.setVisibility(0);
        this.w.setVisibility(m() ? 8 : 0);
        final com.naver.glink.android.sdk.ui.parent.plugfragment.a aVarA = com.naver.glink.android.sdk.ui.parent.plugfragment.a.a();
        this.z = new com.naver.glink.android.sdk.ui.parent.plugfragment.a.c() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.13
            @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.a.c
            public void a() {
                boolean zA = TextInputFragmentView.a(aVarA);
                ArticleFragmentView.this.v.setVisibility(zA ? 8 : 0);
                ArticleFragmentView.this.w.setVisibility((ArticleFragmentView.this.m() || zA) ? 8 : 0);
            }
        };
        aVarA.a(this.z);
        if (this.q) {
            this.q = false;
            a(Comment.newComment(-1, this.k, getParentCommentNo(), null));
        }
    }

    public void a(int i2) {
        com.naver.glink.android.sdk.ui.tabs.b.f();
        com.naver.glink.android.sdk.ui.tabs.b.a(i2, true, false);
    }

    void a(int i2, int i3) {
        d();
        if (p()) {
            d(i2);
        } else {
            e(i3);
        }
    }

    public void a(int i2, boolean z) {
        com.naver.glink.android.sdk.api.requests.b.c.a aVarA = this.t.a(i2);
        if (aVarA != null) {
            a(aVarA, z);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Bundle bundle) {
        super.a(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.k = arguments.getInt(b);
            String string = arguments.getString(i);
            if (string != null) {
                this.r = (com.naver.glink.android.sdk.api.requests.b.c.a) new GsonBuilder().create().fromJson(string, com.naver.glink.android.sdk.api.requests.b.c.a.class);
            }
            this.m = arguments.getBoolean(c, false);
            this.n = arguments.getBoolean(d, false);
            this.o = arguments.getString(e, "");
            this.p = arguments.getInt(f, -1);
            this.q = arguments.getBoolean(j, false);
        }
    }

    public void a(View view) {
        d();
        PopupMenu popupMenuA = o.a(view, R.menu.article_more_actions, new PopupMenu.OnMenuItemClickListener() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.5
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.contents_edit) {
                    b.a(ArticleFragmentView.this.getContext(), ArticleFragmentView.this.l.articleId);
                    return true;
                }
                if (menuItem.getItemId() == R.id.contents_delete) {
                    b.a(ArticleFragmentView.this.getContext(), ArticleFragmentView.this, ArticleFragmentView.this.l.articleId, false);
                    return true;
                }
                if (menuItem.getItemId() != R.id.contents_report) {
                    return true;
                }
                ArticleFragmentView.this.a(false);
                return true;
            }
        });
        if (this.l.isWriter) {
            popupMenuA.getMenu().findItem(R.id.contents_report).setVisible(false);
        } else {
            popupMenuA.getMenu().findItem(R.id.contents_edit).setVisible(false);
            popupMenuA.getMenu().findItem(R.id.contents_delete).setVisible(false);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugListFragmentView, com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        view.setBackgroundColor(ContextCompat.getColor(getContext(), p() ? R.color.white1 : R.color.white2));
        this.t = new com.naver.glink.android.sdk.ui.article.a(getContext(), this, p());
        setListAdapter(this.t);
        getListView().setOnTouchListener(new View.OnTouchListener() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.1
            private float b;
            private float c;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.b = motionEvent.getX();
                    this.c = motionEvent.getY();
                    return false;
                }
                if (motionEvent.getAction() != 1 || ArticleFragmentView.this.x == null || !ArticleFragmentView.this.x.isAttachedToWindow()) {
                    return false;
                }
                if (Math.abs(this.b - motionEvent.getX()) >= 5.0f && Math.abs(this.c - motionEvent.getY()) >= 5.0f) {
                    return false;
                }
                ArticleFragmentView.this.d();
                return false;
            }
        });
        getListView().setRecyclerListener(new AbsListView.RecyclerListener() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.9
            private void a(View view2) {
                if (view2 == null || !(view2.getTag() instanceof com.naver.glink.android.sdk.ui.article.a.d)) {
                    return;
                }
                ((com.naver.glink.android.sdk.ui.article.a.d) view2.getTag()).a();
            }

            @Override // android.widget.AbsListView.RecyclerListener
            public void onMovedToScrapHeap(View view2) {
                try {
                    a(view2);
                } catch (Exception e2) {
                    Log.d(ArticleFragmentView.a, "exception:", e2);
                }
            }
        });
        this.u = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        this.u.setColorSchemeColors(d.e().a);
        this.u.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.10
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                ArticleFragmentView.this.d();
                ArticleFragmentView.this.b(false);
            }
        });
        this.v = view.findViewById(R.id.back);
        this.v.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                com.naver.glink.android.sdk.ui.tabs.b.f();
            }
        });
        this.w = view.findViewById(R.id.comment_write);
        d.e().a(this.w, R.drawable.cf_btn_flcomment);
        this.w.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ArticleFragmentView.this.a(Comment.newComment(-1, ArticleFragmentView.this.k, ArticleFragmentView.this.getParentCommentNo(), null));
            }
        });
        this.x = TextInputFragmentView.b(getContext());
        this.y = new CommentInputPresenter(this.x, p() ? CommentInputPresenter.From.ARTICLE : CommentInputPresenter.From.REPLIES);
        c();
    }

    public void a(View view, final com.naver.glink.android.sdk.api.requests.b.c.a aVar) {
        d();
        o.a(view, R.menu.article_more_actions, new PopupMenu.OnMenuItemClickListener() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.2
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.contents_edit) {
                    ArticleFragmentView.this.b(aVar);
                    return true;
                }
                if (menuItem.getItemId() != R.id.contents_delete) {
                    return true;
                }
                ArticleFragmentView.this.c(aVar);
                return true;
            }
        }).getMenu().findItem(R.id.contents_report).setVisible(false);
    }

    public void a(com.naver.glink.android.sdk.api.requests.b.c.a aVar) {
        this.t.a(getContext(), aVar);
    }

    public void a(com.naver.glink.android.sdk.api.requests.b.c.a aVar, boolean z) {
        if (m()) {
            if (aVar.commentNo == getParentCommentNo()) {
                a(Comment.newComment(-1, this.k, getParentCommentNo(), null, null));
                return;
            } else {
                a(Comment.newComment(-1, this.k, getParentCommentNo(), aVar.userIdNo, aVar.userName));
                return;
            }
        }
        if (z && !r() && LoginHelper.a().isLogin(getContext())) {
            AlertDialogFragmentView.b(getContext(), TextUtils.isEmpty(this.l.commentAuthDesc) ? c(R.string.comment_write_permission_error) : this.l.commentAuthDesc).a();
        } else {
            d();
            com.naver.glink.android.sdk.ui.tabs.b.a(this.k, this.l, aVar, z);
        }
    }

    @Subscribe
    public void a(e.b bVar) {
        if (bVar.articleId == this.k) {
            this.t.a(bVar);
        }
    }

    @Subscribe
    public void a(d.a aVar) {
        d();
        u();
    }

    @Subscribe
    public void a(a aVar) {
        if (p() && aVar.a == this.k) {
            this.t.a(aVar);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe
    public void a(CommentInputPresenter.a aVar) {
        if (aVar.d != null && aVar.b.articleId == this.k) {
            if (aVar.b.id != -1) {
                this.t.b(((com.naver.glink.android.sdk.api.requests.b.a.C0025a) aVar.d.result).comment);
                return;
            }
            if ((p() && aVar.a == CommentInputPresenter.From.ARTICLE) || (m() && aVar.a == CommentInputPresenter.From.REPLIES)) {
                this.t.a(aVar.d);
                getListView().postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.3
                    @Override // java.lang.Runnable
                    public void run() {
                        ArticleFragmentView.this.getListView().setSelection(ArticleFragmentView.this.getListView().getCount() - 1);
                    }
                }, 100L);
            }
            a(aVar.d);
        }
    }

    @Subscribe
    public void a(WriteFragmentView.a aVar) {
        c();
    }

    public void a(String str, boolean z) {
        d();
        b(str, z);
    }

    void a(boolean z) {
        if (!LoginHelper.a().isLogin(getContext())) {
            LoginHelper.a().builder(getContext(), LoggedInListener.d(this)).b(c(R.string.need_login), null).a();
        } else if (z) {
            com.naver.glink.android.sdk.api.requests.c.b(this.k).checkNetworkConnected(true).execute(getContext(), new RequestListener<Responses.x>() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.7
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.x xVar) {
                    if (xVar.success) {
                        Toast.makeText(ArticleFragmentView.this.getContext(), ArticleFragmentView.this.c(R.string.article_report_completed), 1).show();
                    }
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    Toast.makeText(ArticleFragmentView.this.getContext(), plugError.errorMessage, 1).show();
                }
            });
        } else {
            AlertDialogFragmentView.a(getContext(), c(R.string.article_report_alert_message)).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.article.ArticleFragmentView.6
                @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                public void a(DialogInterface dialogInterface, int i2) {
                    ArticleFragmentView.this.a(true);
                }
            }).a();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void b() {
        super.b();
        d();
        com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b(this.z);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView
    public void c() {
        b(true);
    }

    public void d() {
        if (this.x.getVisibility() == 0) {
            this.x.c();
        }
    }

    public void e() {
        this.t.a(getContext(), this.k);
    }

    public void f() {
        d();
        t();
    }

    int getParentCommentNo() {
        if (this.r == null) {
            return -1;
        }
        return this.r.commentNo;
    }
}
