package com.naver.glink.android.sdk.ui.write;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.gson.GsonBuilder;
import com.naver.glink.android.sdk.api.requests.h;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.Response;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/* JADX INFO: compiled from: VideoUploadHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class c {
    private static Vector<Request> a = new Vector<>();
    private File b;
    private int c;
    private Vector<Request> d;
    private a e;

    /* JADX INFO: compiled from: VideoUploadHelper.java */
    public interface a {
        void a(int i, int i2);

        void a(Response response, PlugError plugError, Request request);

        void a(Response response, Request request);
    }

    private c() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(File file) {
        return (file.length() % 5242880 == 0 ? 0 : 1) + ((int) (file.length() / 5242880));
    }

    public static synchronized c a() {
        return new c();
    }

    public static void a(Context context) {
        if (a == null || a.size() == 0) {
            return;
        }
        Request request = a.get(0);
        if (request != null) {
            request.execute(context, (RequestListener) request.getTag());
        }
        a.remove(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Context context, final String str, int i) {
        if (this.d == null || this.d.size() <= i) {
            return;
        }
        final Request request = this.d.get(i);
        request.execute(context, new RequestListener<h.d>() { // from class: com.naver.glink.android.sdk.ui.write.c.2
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull h.d dVar) {
                synchronized (this) {
                    c.b(c.this);
                    if (c.this.d != null || c.this.d.size() > c.this.c) {
                        c.this.a(context, str, c.this.c);
                    }
                    if (c.this.e != null) {
                        c.this.e.a(c.this.c, c.this.d.size());
                    }
                }
                if (c.this.c >= c.this.a(c.this.b)) {
                    c.this.c(context, str);
                }
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                if (c.this.e != null) {
                    c.this.e.a(null, plugError, request);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Context context, String str, String str2) {
        final Request<h.e> requestB = h.b(str, this.b.getName(), str2);
        requestB.execute(context, new RequestListener<h.e>() { // from class: com.naver.glink.android.sdk.ui.write.c.5
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull h.e eVar) {
                super.onSuccess(eVar);
                if (c.this.e != null) {
                    c.this.e.a(eVar, requestB);
                }
                c.a(context);
                c.this.d();
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                if (c.this.e != null) {
                    c.this.e.a(null, plugError, requestB);
                }
                c.this.d();
            }
        });
    }

    static /* synthetic */ int b(c cVar) {
        int i = cVar.c;
        cVar.c = i + 1;
        return i;
    }

    public static void b() {
        Iterator<Request> it = a.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
        a.clear();
    }

    public static void b(Context context) {
        com.naver.glink.android.sdk.api.requests.a.connectionsRequest.execute(context, new RequestListener<com.naver.glink.android.sdk.api.a.c>() { // from class: com.naver.glink.android.sdk.ui.write.c.6
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull com.naver.glink.android.sdk.api.a.c cVar) {
                if (cVar.optional.apis.video == null || cVar.optional.apis.video.size() == 0) {
                    return;
                }
                h.a(cVar.optional.apis.video.get((int) (Math.random() * ((double) cVar.optional.apis.video.size()))));
            }
        });
    }

    private void b(Context context, String str) {
        int length = 0;
        int iA = a(this.b);
        int i = 0;
        while (i < iA) {
            Request<h.d> requestA = h.a(i + 1, i == iA + (-1) ? (int) (this.b.length() % 5242880) : h.CHUNK_SIZE, str, this.b, length);
            length += i == iA + (-1) ? (int) (this.b.length() % 5242880) : h.CHUNK_SIZE;
            this.d.add(requestA);
            i++;
        }
        a(context, str, 0);
    }

    private String c() {
        int length = (int) (this.b.length() % 5242880);
        int iA = a(this.b);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < iA) {
            HashMap map = new HashMap();
            map.put("chunkNum", String.valueOf(i + 1));
            map.put("chunkSize", String.valueOf(i == iA + (-1) ? length : h.CHUNK_SIZE));
            arrayList.add(map);
            i++;
        }
        return new GsonBuilder().create().toJson(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final Context context, final String str) {
        final Request<h.a> requestA = h.a(str, this.b.getName(), c());
        requestA.execute(context, new RequestListener<h.a>() { // from class: com.naver.glink.android.sdk.ui.write.c.3
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull h.a aVar) {
                if (aVar.resultCode) {
                    c.this.d(context, str);
                }
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                if (c.this.e != null) {
                    c.this.e.a(null, plugError, requestA);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.d.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final Context context, final String str) {
        final Request<h.f> requestA = h.a(str, this.b.getName());
        requestA.execute(context, new RequestListener<h.f>() { // from class: com.naver.glink.android.sdk.ui.write.c.4
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull h.f fVar) {
                if (!fVar.resultCode || fVar.data.thumbnailList.size() == 0) {
                    return;
                }
                c.this.a(context, str, fVar.data.thumbnailList.get(0));
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                if (c.this.e != null) {
                    c.this.e.a(null, plugError, requestA);
                }
            }
        });
    }

    public Request a(Context context, File file, RequestListener<h.c> requestListener) {
        this.b = file;
        if (file == null || !file.exists()) {
            return null;
        }
        if (h.a()) {
            b(context);
            return null;
        }
        Request<h.c> requestA = h.a(file.length(), file.getName());
        requestA.setTag(requestListener);
        a.add(requestA);
        return requestA;
    }

    public void a(final Context context, final String str) {
        this.d = new Vector<>();
        if (this.b.length() > 5242880) {
            b(context, str);
        } else {
            final Request<h.d> requestA = h.a(1, this.b.length(), str, this.b);
            requestA.execute(context, new RequestListener<h.d>() { // from class: com.naver.glink.android.sdk.ui.write.c.1
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull h.d dVar) {
                    if (c.this.e != null) {
                        c.this.e.a(1, 1);
                    }
                    c.this.c(context, str);
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    if (c.this.e != null) {
                        c.this.e.a(null, plugError, requestA);
                    }
                }
            });
        }
    }

    public void a(a aVar) {
        this.e = aVar;
    }
}
