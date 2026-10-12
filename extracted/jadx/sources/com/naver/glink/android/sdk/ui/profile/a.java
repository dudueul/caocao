package com.naver.glink.android.sdk.ui.profile;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.TextUtils;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.b;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.f;
import com.naver.glink.android.sdk.d;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.Response;
import com.naver.plug.android.core.api.request.Request;
import com.naver.plug.android.core.api.request.RequestListener;
import java.util.HashMap;
import java.util.Map;
import twitter4j.HttpResponseCode;

/* JADX INFO: compiled from: NickNameHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    static final int a = 20;
    private static final Map<Integer, Integer> b = new HashMap();
    private Request<Responses.g> c;
    private Handler d = new Handler();
    private InterfaceC0051a e;

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.profile.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NickNameHelper.java */
    public interface InterfaceC0051a {
        void a();

        void a(Responses.g gVar, String str, boolean z, int i);

        void a(PlugError plugError);

        void a(Response response);
    }

    static {
        b.put(200, Integer.valueOf(R.string.available_nickname_message));
        b.put(Integer.valueOf(HttpResponseCode.BAD_REQUEST), Integer.valueOf(R.string.not_available_nickname_message));
        b.put(Integer.valueOf(HttpResponseCode.UNAUTHORIZED), Integer.valueOf(R.string.already_exist_nickname_message));
        b.put(402, Integer.valueOf(R.string.exist_prohibit_word_nickname_message));
    }

    public a(InterfaceC0051a interfaceC0051a) {
        this.e = interfaceC0051a;
    }

    public static String a(Context context) {
        return context.getString(d.k() ? R.string.nickname_rule_message : R.string.nickname_rule_global_message);
    }

    public static InputFilter[] a() {
        return d.k() ? new InputFilter[]{new b(20)} : new InputFilter[]{new InputFilter.LengthFilter(20)};
    }

    public void a(String str, Context context) {
        f.a(str, p.c(context).a, true).showProgress(true).execute(context, new RequestListener<Responses.n>() { // from class: com.naver.glink.android.sdk.ui.profile.a.3
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.n nVar) {
                a.this.e.a(nVar);
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                a.this.e.a(plugError);
            }
        });
    }

    public void a(String str, Responses.q qVar, String str2, String str3, Context context) {
        if (qVar == null || context == null || qVar.getError() != null) {
            return;
        }
        f.a(qVar.memberId, str, str2, str3).showProgress(true).execute(context, new RequestListener<Response>() { // from class: com.naver.glink.android.sdk.ui.profile.a.2
            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                a.this.e.a(plugError);
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onSuccess(@NonNull Response response) {
                a.this.e.a(response);
            }
        });
    }

    public void a(String str, boolean z, boolean z2, Responses.q qVar, final Context context) {
        if (this.c != null) {
            this.c.cancel();
            this.c = null;
        }
        if (TextUtils.isEmpty(str)) {
            if (this.e != null) {
                this.e.a(null, a(context), false, R.color.gray4);
            }
        } else if (z && qVar != null && TextUtils.equals(qVar.nickname, str)) {
            if (this.e != null) {
                this.e.a(null, a(context), false, R.color.gray4);
            }
        } else if (z2) {
            if (this.e != null) {
                this.e.a();
            }
            final Request<Responses.g> requestB = f.b(str);
            this.c = requestB;
            this.d.postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.ui.profile.a.1
                @Override // java.lang.Runnable
                public void run() {
                    if (requestB == a.this.c) {
                        requestB.execute(context, new RequestListener<Responses.g>() { // from class: com.naver.glink.android.sdk.ui.profile.a.1.1
                            @Override // com.naver.plug.android.core.api.request.RequestListener
                            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                            public void onSuccess(@NonNull Responses.g gVar) {
                                if (requestB == a.this.c) {
                                    a.this.c = null;
                                    Integer numValueOf = (Integer) a.b.get(Integer.valueOf(gVar.messageCode));
                                    if (numValueOf == null) {
                                        numValueOf = Integer.valueOf(R.string.not_available_nickname_message);
                                    }
                                    String string = context.getString(numValueOf.intValue());
                                    if (a.this.e != null) {
                                        a.this.e.a(gVar, string, gVar.available, gVar.available ? R.color.blue2 : R.color.red1);
                                    }
                                }
                            }
                        });
                    }
                }
            }, 1000L);
        }
    }
}
