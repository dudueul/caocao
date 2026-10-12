package com.naver.glink.android.sdk.ui.write;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.signature.StringSignature;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.aa;
import com.naver.glink.android.sdk.a.g;
import com.naver.glink.android.sdk.a.i;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.a.s;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.api.requests.h;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.glink.android.sdk.model.Menu;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.parent.PlugListFragmentView;
import com.naver.glink.android.sdk.ui.tabs.Tab;
import com.naver.glink.android.sdk.ui.write.model.Attachment;
import com.naver.glink.android.sdk.ui.write.model.ImageAttachment;
import com.naver.glink.android.sdk.ui.write.model.Text;
import com.naver.glink.android.sdk.ui.write.model.VideoAttachment;
import com.naver.glink.android.sdk.ui.write.model.WritingArticle;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.Response;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;
import com.squareup.otto.Subscribe;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class WriteFragmentView extends PlugListFragmentView {
    static final int a = 209715200;
    private static final int b = 500;
    private static final int c = 501;
    private static final int d = 502;
    private static final int e = 503;
    private static final String f = "com.naver.glink.ARG_ARTICLE";
    private static final String i = "com.naver.glink.ARG_SHORTCUT";
    private static final String j = "com.naver.glink.ARG_COLLAPSED";
    private WritingArticle k;
    private boolean l;
    private boolean m;
    private d n;
    private Responses.y o;
    private Responses.d p;
    private List<Request<? extends Response>> q;
    private List<Attachment> r;
    private boolean s;
    private boolean t;
    private Responses.w u;
    private EditText v;
    private EditText w;
    private View x;

    public static class a extends com.naver.plug.android.core.a.a {
        public final Responses.w a;

        a(Responses.w wVar) {
            this.a = wVar;
        }
    }

    private static class b {
        final int a;
        final int b;
        final int c;
        final int d;
        final int e;
        final int f;

        b(int i, int i2, int i3, int i4, int i5, int i6) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
        }
    }

    private static class c implements Glink.OnLoggedInListener {
        private final WeakReference<WriteFragmentView> a;

        c(WriteFragmentView writeFragmentView) {
            this.a = new WeakReference<>(writeFragmentView);
        }

        @Override // com.naver.glink.android.sdk.Glink.OnLoggedInListener
        public void onLoggedIn(boolean z) {
            WriteFragmentView writeFragmentView = this.a.get();
            if (writeFragmentView == null || !writeFragmentView.isAttachedToWindow()) {
                return;
            }
            if (z) {
                writeFragmentView.m();
            } else {
                writeFragmentView.a(false);
            }
        }
    }

    private class d extends BaseAdapter {
        static final int a = 200;
        static final int b = 0;
        static final int c = 1;
        static final int d = 2;
        static final int e = 3;
        final List<Integer> f;
        final List<b> g = Arrays.asList(new b(R.id.attachment1, R.id.thumbnail1, R.id.delete_attachment1, R.id.video_icon1, R.id.video_gradient1, R.id.gif_thumbnail1), new b(R.id.attachment2, R.id.thumbnail2, R.id.delete_attachment2, R.id.video_icon2, R.id.video_gradient2, R.id.gif_thumbnail2), new b(R.id.attachment3, R.id.thumbnail3, R.id.delete_attachment3, R.id.video_icon3, R.id.video_gradient3, R.id.gif_thumbnail3), new b(R.id.attachment4, R.id.thumbnail4, R.id.delete_attachment4, R.id.video_icon4, R.id.video_gradient4, R.id.gif_thumbnail4));

        d() {
            ArrayList arrayList = new ArrayList();
            if (com.naver.glink.android.sdk.d.i()) {
                arrayList.add(0);
            }
            arrayList.add(1);
            arrayList.add(2);
            arrayList.add(3);
            this.f = Collections.unmodifiableList(arrayList);
        }

        private View a(View view, ViewGroup viewGroup) {
            boolean z;
            if (view == null) {
                view = LayoutInflater.from(WriteFragmentView.this.getContext()).inflate(R.layout.item_write_header, viewGroup, false);
                z = true;
            } else {
                z = false;
            }
            TextView textView = (TextView) view.findViewById(R.id.menus_button);
            Menu menuA = WriteFragmentView.this.o == null ? null : WriteFragmentView.this.o.a(WriteFragmentView.this.k.c());
            if (menuA == null) {
                textView.setText(WriteFragmentView.this.getContext().getString(R.string.select_menu_button));
            } else {
                textView.setText(menuA.getMenuName());
            }
            textView.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.d.1
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    WriteFragmentView.this.q();
                }
            });
            WriteFragmentView.this.v = (EditText) view.findViewById(R.id.subject);
            WriteFragmentView.this.v.setText(WriteFragmentView.this.k.d());
            if (z) {
                WriteFragmentView.this.v.setFilters(new InputFilter[]{new com.naver.glink.android.sdk.a.b(200)});
                WriteFragmentView.this.v.addTextChangedListener(new g() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.d.2
                    @Override // com.naver.glink.android.sdk.a.g, android.text.TextWatcher
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        WriteFragmentView.this.k.a(charSequence.toString());
                        WriteFragmentView.this.s();
                    }
                });
            }
            return view;
        }

        private View a(Text text, View view, ViewGroup viewGroup) {
            boolean z;
            if (view == null) {
                view = LayoutInflater.from(WriteFragmentView.this.getContext()).inflate(R.layout.item_write_text, viewGroup, false);
                z = true;
            } else {
                z = false;
            }
            WriteFragmentView.this.w = (EditText) view.findViewById(R.id.content);
            WriteFragmentView.this.w.setText(text.a());
            WriteFragmentView.this.w.setTag(text);
            if (z) {
                WriteFragmentView.this.w.addTextChangedListener(new g() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.d.3
                    @Override // com.naver.glink.android.sdk.a.g, android.text.TextWatcher
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        if (WriteFragmentView.this.w.getTag() != null) {
                            ((Text) WriteFragmentView.this.w.getTag()).a(charSequence.toString());
                            WriteFragmentView.this.s();
                        }
                    }
                });
            }
            return view;
        }

        private View a(List<Attachment> list, View view, ViewGroup viewGroup) {
            int i = 0;
            if (view == null) {
                view = LayoutInflater.from(WriteFragmentView.this.getContext()).inflate(R.layout.item_write_attachments, viewGroup, false);
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = (com.naver.glink.android.sdk.d.p().c * 77) / ((WriteFragmentView.this.k.f() * 128) + ((WriteFragmentView.this.k.f() - 1) * 4));
                view.setLayoutParams(layoutParams);
            }
            while (true) {
                int i2 = i;
                if (i2 >= this.g.size()) {
                    return view;
                }
                b bVar = this.g.get(i2);
                if (i2 < WriteFragmentView.this.k.f()) {
                    a(view, bVar, i2 < list.size() ? list.get(i2) : null);
                } else {
                    view.findViewById(bVar.a).setVisibility(8);
                }
                i = i2 + 1;
            }
        }

        private void a(View view, b bVar, final Attachment attachment) {
            View viewFindViewById = view.findViewById(bVar.a);
            if (attachment == null) {
                viewFindViewById.setVisibility(4);
                return;
            }
            viewFindViewById.setVisibility(0);
            ImageView imageView = (ImageView) view.findViewById(bVar.b);
            if (attachment instanceof ImageAttachment) {
                Uri uriI = ((ImageAttachment) attachment).i();
                if (com.naver.glink.android.sdk.ui.write.a.a(WriteFragmentView.this.getContext(), uriI)) {
                    Glide.with(WriteFragmentView.this.getContext()).load(uriI).asBitmap().centerCrop().into(imageView);
                    view.findViewById(bVar.f).setVisibility(0);
                } else {
                    Glide.with(WriteFragmentView.this.getContext()).load(uriI).error(R.drawable.cf_icon_nophoto).centerCrop().into(s.a(imageView));
                }
                view.findViewById(bVar.d).setVisibility(8);
                view.findViewById(bVar.e).setVisibility(8);
            } else if (attachment instanceof VideoAttachment) {
                VideoAttachment videoAttachment = (VideoAttachment) attachment;
                Glide.with(WriteFragmentView.this.getContext()).load(videoAttachment.e()).signature((Key) new StringSignature(videoAttachment.e() + ":" + WriteFragmentView.this)).error(R.drawable.cf_icon_novideo).centerCrop().into(s.a(imageView));
                view.findViewById(bVar.d).setVisibility(0);
                view.findViewById(bVar.e).setVisibility(0);
                view.findViewById(bVar.f).setVisibility(8);
            }
            view.findViewById(bVar.c).setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.d.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    WriteFragmentView.this.k.b(attachment);
                    WriteFragmentView.this.n.notifyDataSetChanged();
                }
            });
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return (this.f.size() - 1) + WriteFragmentView.this.k.o();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            switch (getItemViewType(i)) {
                case 0:
                case 1:
                    return null;
                case 2:
                    return WriteFragmentView.this.k.e().get(0);
                case 3:
                    return WriteFragmentView.this.k.c(i - (this.f.size() - 1));
                default:
                    throw new IllegalStateException("지원하지 않는 view type 입니다.");
            }
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i) {
            return this.f.get(Math.min(i, this.f.size() - 1)).intValue();
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            switch (getItemViewType(i)) {
                case 0:
                    return WriteFragmentView.this.a(view, viewGroup);
                case 1:
                    return a(view, viewGroup);
                case 2:
                    return a((Text) getItem(i), view, viewGroup);
                case 3:
                    return a((List<Attachment>) getItem(i), view, viewGroup);
                default:
                    throw new IllegalStateException("지원하지 않는 viewType 입니다.");
            }
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 4;
        }
    }

    public WriteFragmentView(Context context) {
        super(context);
        this.r = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View a(View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_write_navigation, viewGroup, false);
        }
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                WriteFragmentView.this.a(true);
            }
        });
        this.x = view.findViewById(R.id.save);
        s();
        this.x.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) throws Throwable {
                WriteFragmentView.this.r();
            }
        });
        return view;
    }

    public static WriteFragmentView a(Context context, WritingArticle writingArticle, boolean z, boolean z2) {
        WriteFragmentView writeFragmentView = new WriteFragmentView(context);
        Bundle bundle = new Bundle();
        bundle.putParcelable(f, writingArticle);
        bundle.putBoolean(i, z);
        bundle.putBoolean(j, z2);
        writeFragmentView.setArguments(bundle);
        return writeFragmentView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Responses.w wVar) {
        a(new a(wVar));
        com.naver.glink.android.sdk.ui.write.a.a(getContext());
        Toast.makeText(getContext(), R.string.article_save_complete_message, 0).show();
        com.naver.glink.android.sdk.ui.widget.progress.b.a();
        w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Attachment attachment, Request<? extends Response> request, Response response, PlugError plugError) throws Throwable {
        if (plugError != null) {
            b(plugError);
            return;
        }
        attachment.a(response);
        if (this.k != null && this.k.e().size() - 1 > 0 && (!com.naver.glink.android.sdk.d.l() || !(attachment instanceof VideoAttachment))) {
            com.naver.glink.android.sdk.ui.widget.progress.b.a(100 / (this.k.e().size() - 1));
        }
        if (this.q != null) {
            this.q.remove(request);
            if (this.q.isEmpty()) {
                this.q = null;
                r();
            }
        }
    }

    private void a(List<Attachment> list) throws Throwable {
        if (this.p == null) {
            (com.naver.glink.android.sdk.d.k() ? f.e() : com.naver.glink.android.sdk.api.requests.c.b()).execute(getContext(), new RequestListener<Responses.d>() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.5
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.d dVar) throws Throwable {
                    WriteFragmentView.this.p = dVar;
                    WriteFragmentView.this.r();
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    com.naver.glink.android.sdk.ui.widget.progress.b.a();
                }
            });
            return;
        }
        this.q = new ArrayList();
        if (com.naver.glink.android.sdk.d.l()) {
            com.naver.glink.android.sdk.ui.write.c.b();
        }
        for (Attachment attachment : list) {
            if (attachment instanceof ImageAttachment) {
                final ImageAttachment imageAttachment = (ImageAttachment) attachment;
                String strA = com.naver.glink.android.sdk.a.f.a(getContext(), imageAttachment.d);
                if (TextUtils.isEmpty(strA)) {
                    this.r.add(attachment);
                } else {
                    File file = new File(strA);
                    if (com.naver.glink.android.sdk.d.k()) {
                        final Request<Responses.e> requestA = f.a(this.p, file);
                        requestA.execute(getContext(), new RequestListener<Responses.e>() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.6
                            @Override // com.naver.plug.android.core.api.request.RequestListener
                            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                            public void onSuccess(@NonNull Responses.e eVar) throws Throwable {
                                WriteFragmentView.this.a(imageAttachment, (Request<? extends Response>) requestA, eVar, (PlugError) null);
                            }

                            @Override // com.naver.plug.android.core.api.request.RequestListener
                            public void onFailure(@NonNull PlugError plugError) throws Throwable {
                                WriteFragmentView.this.a(imageAttachment, (Request<? extends Response>) requestA, (Response) null, plugError);
                            }
                        });
                        this.q.add(requestA);
                    } else {
                        final Request<com.naver.glink.android.sdk.api.a.b> requestA2 = com.naver.glink.android.sdk.api.requests.c.a(this.p, file);
                        requestA2.execute(getContext(), new RequestListener<com.naver.glink.android.sdk.api.a.b>() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.7
                            @Override // com.naver.plug.android.core.api.request.RequestListener
                            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                            public void onSuccess(@NonNull com.naver.glink.android.sdk.api.a.b bVar) throws Throwable {
                                WriteFragmentView.this.a(imageAttachment, (Request<? extends Response>) requestA2, bVar, (PlugError) null);
                            }

                            @Override // com.naver.plug.android.core.api.request.RequestListener
                            public void onFailure(@NonNull PlugError plugError) throws Throwable {
                                WriteFragmentView.this.a(imageAttachment, (Request<? extends Response>) requestA2, (Response) null, plugError);
                            }
                        });
                        this.q.add(requestA2);
                    }
                }
            } else if (attachment instanceof VideoAttachment) {
                final VideoAttachment videoAttachment = (VideoAttachment) attachment;
                String strA2 = com.naver.glink.android.sdk.a.f.a(getContext(), videoAttachment.d);
                if (TextUtils.isEmpty(strA2)) {
                    this.r.add(attachment);
                } else {
                    File file2 = new File(strA2);
                    if (com.naver.glink.android.sdk.d.k()) {
                        final Request<Responses.f> requestB = f.b(this.p, file2);
                        requestB.execute(getContext(), new RequestListener<Responses.f>() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.8
                            @Override // com.naver.plug.android.core.api.request.RequestListener
                            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                            public void onSuccess(@NonNull Responses.f fVar) throws Throwable {
                                WriteFragmentView.this.a(videoAttachment, (Request<? extends Response>) requestB, fVar, (PlugError) null);
                            }

                            @Override // com.naver.plug.android.core.api.request.RequestListener
                            public void onFailure(@NonNull PlugError plugError) throws Throwable {
                                WriteFragmentView.this.a(videoAttachment, (Request<? extends Response>) requestB, (Response) null, plugError);
                            }
                        });
                        this.q.add(requestB);
                    } else {
                        final com.naver.glink.android.sdk.ui.write.c cVarA = com.naver.glink.android.sdk.ui.write.c.a();
                        final Request<? extends Response> requestA3 = cVarA.a(getContext(), file2, new RequestListener<h.c>() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.9
                            @Override // com.naver.plug.android.core.api.request.RequestListener
                            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                            public void onSuccess(@NonNull h.c cVar) {
                                super.onSuccess(cVar);
                                cVarA.a(WriteFragmentView.this.getContext(), cVar.key);
                            }

                            @Override // com.naver.plug.android.core.api.request.RequestListener
                            public void onFailure(@NonNull PlugError plugError) throws Throwable {
                                WriteFragmentView.this.a(videoAttachment, (Request<? extends Response>) null, (Response) null, plugError);
                            }
                        });
                        if (requestA3 == null) {
                            a(videoAttachment, (Request<? extends Response>) null, (Response) null, PlugError.newNetworkError());
                            return;
                        } else {
                            cVarA.a(new com.naver.glink.android.sdk.ui.write.c.a() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.10
                                @Override // com.naver.glink.android.sdk.ui.write.c.a
                                public void a(int i2, int i3) {
                                    if (WriteFragmentView.this.k == null || WriteFragmentView.this.k.e().size() - 1 <= 0) {
                                        return;
                                    }
                                    com.naver.glink.android.sdk.ui.widget.progress.b.a((100 / (WriteFragmentView.this.k.e().size() - 1)) / i3);
                                }

                                @Override // com.naver.glink.android.sdk.ui.write.c.a
                                public void a(Response response, PlugError plugError, Request request) throws Throwable {
                                    WriteFragmentView.this.a(videoAttachment, (Request<? extends Response>) requestA3, response, plugError);
                                }

                                @Override // com.naver.glink.android.sdk.ui.write.c.a
                                public void a(Response response, Request request) throws Throwable {
                                    WriteFragmentView.this.a(videoAttachment, (Request<? extends Response>) requestA3, response, (PlugError) null);
                                }
                            });
                            this.q.add(requestA3);
                        }
                    }
                }
            } else {
                continue;
            }
        }
        if (com.naver.glink.android.sdk.d.l()) {
            com.naver.glink.android.sdk.ui.write.c.a(getContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (!o()) {
            this.t = true;
        } else if (z) {
            b(new Runnable() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.2
                @Override // java.lang.Runnable
                public void run() {
                    WriteFragmentView.this.w();
                }
            });
        } else {
            w();
        }
    }

    private void b(PlugError plugError) {
        if (plugError.isNetworkError()) {
            AlertDialogFragmentView.a(getContext(), getContext().getString(R.string.article_save_fail_message)).a(getContext().getString(R.string.retry)).b(getContext().getString(R.string.cancel)).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.11
                @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                public void a(DialogInterface dialogInterface, int i2) throws Throwable {
                    WriteFragmentView.this.r();
                }
            }).a();
        } else {
            String string = plugError.errorMessage;
            if (TextUtils.equals(string, "illegal type of image file.")) {
                string = getContext().getString(R.string.image_upload_fail_message);
            }
            AlertDialogFragmentView.b(getContext(), string).a();
        }
        this.p = null;
        t();
        if (com.naver.glink.android.sdk.d.l()) {
            com.naver.glink.android.sdk.ui.write.c.b();
        }
        com.naver.glink.android.sdk.ui.widget.progress.b.a();
    }

    private void b(final Runnable runnable) {
        h();
        if (this.k.i()) {
            w();
        } else {
            if (this.s) {
                return;
            }
            this.s = true;
            AlertDialogFragmentView.a(getContext(), getContext().getString(R.string.cancel_article_write_confirm_message)).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.3
                @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                public void a(DialogInterface dialogInterface, int i2) {
                    if (runnable != null) {
                        runnable.run();
                    }
                }

                @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                public void b(DialogInterface dialogInterface) {
                    super.b(dialogInterface);
                    WriteFragmentView.this.s = false;
                }
            }).a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (!LoginHelper.a().isLogin(getContext())) {
            LoginHelper.a().builder(getContext(), new c(this)).b(com.naver.glink.android.sdk.d.k() ? getContext().getString(R.string.require_login_message) : getContext().getString(R.string.need_login), new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.19
                @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c, android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    if (i2 != -1) {
                        WriteFragmentView.this.a(false);
                    }
                }
            }).a();
        } else if (com.naver.glink.android.sdk.api.requests.a.a() == null) {
            com.naver.glink.android.sdk.api.requests.a.a(getContext(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.20
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.i iVar) {
                    WriteFragmentView.this.m();
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    WriteFragmentView.this.a(false);
                    Toast.makeText(WriteFragmentView.this.getContext(), plugError.errorMessage, 1).show();
                }
            });
        } else if (this.o == null) {
            (this.k.g() ? f.d() : f.b(this.k.b())).showProgress(true).execute(getContext(), new RequestListener<Responses.y>() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.21
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.y yVar) {
                    if (!a(yVar, WriteFragmentView.this.k.c())) {
                        WriteFragmentView.this.k.a(-1);
                        AlertDialogFragmentView.b(WriteFragmentView.this.getContext(), WriteFragmentView.this.getContext().getString(R.string.write_permission_error)).a();
                    }
                    WriteFragmentView.this.o = yVar;
                    WriteFragmentView.this.k.a(WriteFragmentView.this.o);
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onFinally(Responses.y yVar, PlugError plugError) {
                    if (WriteFragmentView.this.getListAdapter() == null) {
                        WriteFragmentView.this.n = WriteFragmentView.this.new d();
                        WriteFragmentView.this.setListAdapter(WriteFragmentView.this.n);
                    }
                    WriteFragmentView.this.n.notifyDataSetChanged();
                }

                boolean a(Responses.y yVar, int i2) {
                    return i2 == -1 || yVar.a(i2) != null;
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    if (plugError.isNetworkError()) {
                        AlertDialogFragmentView.b(WriteFragmentView.this.getContext(), plugError.errorMessage).a();
                    } else if (plugError.isNeedJoinError()) {
                        AlertDialogFragmentView.a(WriteFragmentView.this.getContext(), plugError.errorMessage).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.21.1
                            @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                            public void a(DialogInterface dialogInterface, int i2) {
                                WriteFragmentView.this.l = false;
                                WriteFragmentView.this.w();
                                com.naver.glink.android.sdk.ui.tabs.b.a(Tab.Type.PROFILE);
                            }

                            @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                            public void b(DialogInterface dialogInterface, int i2) {
                                WriteFragmentView.this.w();
                            }
                        }).a();
                    } else {
                        AlertDialogFragmentView.b(WriteFragmentView.this.getContext(), plugError.errorMessage).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.21.2
                            @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c, android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i2) {
                                WriteFragmentView.this.w();
                            }
                        }).a();
                    }
                }
            });
        }
    }

    private void p() {
        final ArrayList arrayList = new ArrayList();
        for (final ImageAttachment imageAttachment : this.k.m()) {
            if (imageAttachment.a == null) {
                arrayList.add(imageAttachment);
                com.naver.glink.android.sdk.ui.write.a.a(getContext(), imageAttachment.d, new com.naver.glink.android.sdk.ui.write.a.InterfaceC0066a() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.22
                    @Override // com.naver.glink.android.sdk.ui.write.a.InterfaceC0066a
                    public void a(Uri uri) {
                        if (WriteFragmentView.this.getContext() == null) {
                            return;
                        }
                        File fileB = com.naver.glink.android.sdk.ui.write.a.b(WriteFragmentView.this.getContext());
                        if (uri != null && uri.getPath().contains(fileB.getPath())) {
                            WriteFragmentView.this.k.a(imageAttachment, new ImageAttachment(uri));
                        }
                        arrayList.remove(imageAttachment);
                        if (arrayList.isEmpty()) {
                            WriteFragmentView.this.m();
                        }
                    }
                });
            }
        }
        if (arrayList.isEmpty()) {
            m();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (this.o == null) {
            m();
        } else {
            com.naver.glink.android.sdk.ui.articles.b.a(getContext(), this.o.menus, this.k.c(), new com.naver.glink.android.sdk.ui.articles.b.a() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.23
                @Override // com.naver.glink.android.sdk.ui.articles.b.a
                public void a(Menu menu) {
                    if (WriteFragmentView.this.o != null) {
                        WriteFragmentView.this.k.a(menu.getMenuId());
                        WriteFragmentView.this.n.notifyDataSetChanged();
                        WriteFragmentView.this.s();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() throws Throwable {
        h();
        String strH = this.k.h();
        if (!TextUtils.isEmpty(strH)) {
            Toast.makeText(getContext(), strH, 1).show();
            return;
        }
        if (!com.naver.plug.android.core.a.b.a(getContext())) {
            AlertDialogFragmentView.b(getContext(), getContext().getString(R.string.internet_not_connected_error)).a();
            return;
        }
        if (!com.naver.glink.android.sdk.ui.widget.progress.b.b()) {
            com.naver.glink.android.sdk.ui.widget.progress.b.a(getContext(), getContext().getString(R.string.article_save_progress_message), this.k.e().size() != 1);
        }
        List<Attachment> listL = this.k.l();
        listL.removeAll(this.r);
        if (listL.size() > 0) {
            a(listL);
        } else {
            f.a(this.k).execute(getContext(), new RequestListener<Responses.w>() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.4
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.w wVar) {
                    WriteFragmentView.this.u = wVar;
                    WriteFragmentView.this.a(wVar);
                    if (WriteFragmentView.this.k.g()) {
                        com.naver.glink.android.sdk.b.a(wVar.menuId, WriteFragmentView.this.k.m().size(), WriteFragmentView.this.k.n().size());
                    }
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onFinally(Responses.w wVar, PlugError plugError) {
                    com.naver.glink.android.sdk.ui.widget.progress.b.a();
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    AlertDialogFragmentView.b(WriteFragmentView.this.getContext(), plugError.errorMessage).a();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        if (TextUtils.isEmpty(this.k.h())) {
            com.naver.glink.android.sdk.d.e().b(this.x, true);
        } else {
            aa.a(this.x, getResources().getDrawable(R.drawable.empty_rectangle_fill_corner_background));
        }
    }

    private void t() {
        if (this.q != null) {
            Iterator<Request<? extends Response>> it = this.q.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
            this.q = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        if (!com.naver.glink.android.sdk.ui.write.b.a(getContext(), NXRuntimePermissionManager.READ_EXTERNAL_STORAGE)) {
            com.naver.glink.android.sdk.ui.write.b.a(getContext(), NXRuntimePermissionManager.READ_EXTERNAL_STORAGE, c(R.string.permission_storage), 502);
            return;
        }
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        if (i.a(getContext(), intent)) {
            com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult.a.a(getContext(), intent, 500);
        } else {
            AlertDialogFragmentView.b(getContext(), getContext().getString(R.string.attachment_add_fail_message)).a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        if (!com.naver.glink.android.sdk.ui.write.b.a(getContext(), NXRuntimePermissionManager.READ_EXTERNAL_STORAGE)) {
            com.naver.glink.android.sdk.ui.write.b.a(getContext(), NXRuntimePermissionManager.READ_EXTERNAL_STORAGE, c(R.string.permission_storage), 503);
            return;
        }
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("video/*");
        intent.putExtra("android.intent.extra.LOCAL_ONLY", true);
        if (i.a(getContext(), intent)) {
            com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult.a.a(getContext(), intent, c);
        } else {
            AlertDialogFragmentView.b(getContext(), getContext().getString(R.string.attachment_add_fail_message)).a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        if (this.l && p.h(getContext())) {
            com.naver.glink.android.sdk.d.c(getContext());
            com.naver.glink.android.sdk.d.f(com.naver.glink.android.sdk.d.r());
            return;
        }
        if (this.l) {
            com.naver.glink.android.sdk.d.c(getContext());
            return;
        }
        com.naver.glink.android.sdk.ui.tabs.b.f();
        if (this.m) {
            if (LoginHelper.a().isLogin(getContext())) {
                com.naver.glink.android.sdk.ui.main.b.a();
            } else {
                com.naver.glink.android.sdk.d.c(getContext());
                com.naver.glink.android.sdk.d.f(com.naver.glink.android.sdk.d.r());
            }
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_write, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a() {
        super.a();
        if (this.t) {
            a(false);
            return;
        }
        if (isAttachedToWindow()) {
            postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.18
                @Override // java.lang.Runnable
                public void run() {
                    if (WriteFragmentView.this.n != null) {
                        WriteFragmentView.this.n.notifyDataSetChanged();
                    }
                }
            }, 300L);
        }
        if (this.u != null) {
            a(this.u);
        }
        com.naver.glink.android.sdk.ui.main.b.f();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Bundle bundle) {
        super.a(bundle);
        if (getArguments() != null) {
            this.k = (WritingArticle) getArguments().getParcelable(f);
            if (this.k != null) {
                this.k.b(com.naver.glink.android.sdk.d.i() ? 3 : 2);
            }
            this.l = getArguments().getBoolean(i);
            this.m = getArguments().getBoolean(j);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.PlugListFragmentView, com.naver.glink.android.sdk.ui.parent.PlugFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, Bundle bundle) {
        super.a(view, bundle);
        if (bundle != null) {
            com.naver.glink.android.sdk.d.c(getContext());
            return;
        }
        if (com.naver.glink.android.sdk.d.i()) {
            view.findViewById(R.id.navigation_for_portrait).setVisibility(8);
        } else {
            a(view.findViewById(R.id.navigation_for_portrait), (ViewGroup) null);
        }
        View viewFindViewById = view.findViewById(R.id.pick_image);
        com.naver.glink.android.sdk.d.e().a(viewFindViewById, R.drawable.cf_btn_flphoto);
        viewFindViewById.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.1
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                if (WriteFragmentView.this.o == null) {
                    return;
                }
                WriteFragmentView.this.u();
            }
        });
        View viewFindViewById2 = view.findViewById(R.id.pick_movie);
        com.naver.glink.android.sdk.d.e().a(viewFindViewById2, R.drawable.cf_btn_flvideo);
        viewFindViewById2.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.12
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                if (WriteFragmentView.this.o == null) {
                    return;
                }
                WriteFragmentView.this.v();
            }
        });
        getListView().setFocusable(false);
        getListView().setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.17
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                if (WriteFragmentView.this.v != null) {
                    if (i2 > (com.naver.glink.android.sdk.d.i() ? 1 : 0) && WriteFragmentView.this.v.isFocused()) {
                        WriteFragmentView.this.a(WriteFragmentView.this.v);
                    }
                }
                if (WriteFragmentView.this.w != null) {
                    if (i2 <= (com.naver.glink.android.sdk.d.i() ? 2 : 1) || !WriteFragmentView.this.w.isFocused()) {
                        return;
                    }
                    WriteFragmentView.this.a(WriteFragmentView.this.w);
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i2) {
            }
        });
        p();
        if (com.naver.glink.android.sdk.d.l()) {
            com.naver.glink.android.sdk.ui.write.c.b(getContext());
        }
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.d.a aVar) {
        a(this.v);
        a(this.w);
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult.a.C0048a c0048a) {
        if (c0048a == null) {
        }
        int i2 = c0048a.a;
        int i3 = c0048a.b;
        Intent intent = c0048a.c;
        if (i3 == -1) {
            switch (i2) {
                case 500:
                    com.naver.glink.android.sdk.ui.write.a.a(getContext(), intent.getData(), new com.naver.glink.android.sdk.ui.write.a.InterfaceC0066a() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.13
                        @Override // com.naver.glink.android.sdk.ui.write.a.InterfaceC0066a
                        public void a(Uri uri) {
                            WriteFragmentView.this.k.a(new ImageAttachment(uri));
                            WriteFragmentView.this.n.notifyDataSetChanged();
                            WriteFragmentView.this.getListView().setSelection(WriteFragmentView.this.getListView().getCount() - 1);
                        }
                    });
                    break;
                case c /* 501 */:
                    Uri data = intent.getData();
                    if (com.naver.glink.android.sdk.a.f.b(getContext(), data) >= 209715200) {
                        a(new Runnable() { // from class: com.naver.glink.android.sdk.ui.write.WriteFragmentView.14
                            @Override // java.lang.Runnable
                            public void run() {
                                AlertDialogFragmentView.b(WriteFragmentView.this.getContext(), WriteFragmentView.this.getContext().getString(R.string.movie_over_limit_size_message)).a();
                            }
                        });
                    } else {
                        this.k.a(new VideoAttachment(data));
                        this.n.notifyDataSetChanged();
                        getListView().setSelection(getListView().getCount() - 1);
                    }
                    break;
            }
        }
    }

    @Subscribe
    public void a(com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult.a.b bVar) {
        if (bVar == null) {
        }
        int i2 = bVar.a;
        int[] iArr = bVar.c;
        switch (i2) {
            case 502:
                if (!com.naver.glink.android.sdk.ui.write.b.a(iArr)) {
                    Toast.makeText(getContext(), R.string.image_permission_setting_message, 1).show();
                } else {
                    u();
                }
                break;
            case 503:
                if (!com.naver.glink.android.sdk.ui.write.b.a(iArr)) {
                    Toast.makeText(getContext(), R.string.movie_permission_setting_message, 1).show();
                } else {
                    v();
                }
                break;
        }
    }

    public void f() {
        a(true);
    }

    public WritingArticle getArticle() {
        return this.k;
    }
}
