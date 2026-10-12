package com.naver.glink.android.sdk.a.c;

import android.os.Handler;
import android.os.Looper;
import com.naver.glink.android.sdk.a.l;
import com.naver.kaleido.DataHandler;
import com.naver.kaleido.DataTypeOptions;
import com.naver.kaleido.KaleidoAcl;
import com.naver.kaleido.KaleidoClient;
import com.naver.kaleido.KaleidoIntCnt;
import com.naver.kaleido.OnRemote;
import com.naver.kaleido.OnState;
import com.naver.kaleido.PublicPermission;
import com.naver.kaleido.UserType;

/* JADX INFO: compiled from: IntCntWrapper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class a implements DataHandler<KaleidoIntCnt> {
    private static final int b = 10;
    private final KaleidoClient d;
    private final String e;
    private KaleidoIntCnt f;
    private boolean g;
    private static final l a = l.a(a.class.getSimpleName());
    private static Handler c = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.a.c.a$3, reason: invalid class name */
    /* JADX INFO: compiled from: IntCntWrapper.java */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b = new int[OnState.Code.values().length];

        static {
            try {
                b[OnState.Code.OK_ATTACH.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[OnState.Code.ERR_MEMORY_ONLY_BY_SYNC_CORRUPTION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            a = new int[UserType.values().length];
            try {
                a[UserType.ANONYMOUS.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[UserType.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[UserType.MASTER.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public a(KaleidoClient kaleidoClient, String str) {
        this.d = kaleidoClient;
        this.e = str;
    }

    public void a() {
        try {
            switch (AnonymousClass3.a[this.d.getAuthentication().getOwnerType().ordinal()]) {
                case 1:
                    this.f = KaleidoIntCnt.attach(this.d, this.e, new DataTypeOptions.Builder().setAcl(KaleidoAcl.create(PublicPermission.ALL_READ_LOGIN_READ_WRITE)).setMaxPullOperationSize(10).build(), this);
                    break;
                case 2:
                case 3:
                    this.f = KaleidoIntCnt.attachOrCreate(this.d, this.e, new DataTypeOptions.Builder().setAcl(KaleidoAcl.create(PublicPermission.ALL_READ_WRITE_LOGIN_READ_WRITE)).setMaxPullOperationSize(10).build(), this);
                    break;
            }
        } catch (Exception e) {
            a.a(e);
        }
    }

    public abstract void a(int i);

    public abstract void a(int i, boolean z);

    public int b(int i) {
        try {
            return this.f.inc(i);
        } catch (Exception e) {
            a.a(e);
            return 0;
        }
    }

    public void b() {
        try {
            this.f.detach();
        } catch (Exception e) {
            a.a(e);
        }
    }

    public int c() {
        try {
            return this.f.get();
        } catch (Exception e) {
            a.a(e);
            return 0;
        }
    }

    public int d() {
        return b(1);
    }

    public int e() {
        try {
            return this.f.dec();
        } catch (Exception e) {
            a.a(e);
            return 0;
        }
    }

    public void onRemoteOperations(final OnRemote<KaleidoIntCnt> onRemote) {
        c.post(new Runnable() { // from class: com.naver.glink.android.sdk.a.c.a.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.a(onRemote.getData().get(), !a.this.g);
                } catch (Exception e) {
                    a.a.a(e);
                }
                a.this.g = true;
            }
        });
    }

    public void onStateChanged(final OnState<KaleidoIntCnt> onState) {
        switch (AnonymousClass3.b[onState.getCode().ordinal()]) {
            case 1:
                c.post(new Runnable() { // from class: com.naver.glink.android.sdk.a.c.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.a(onState.getData().get());
                    }
                });
                break;
            case 2:
                a();
                break;
        }
    }
}
