package com.naver.glink.android.sdk.a.c;

import android.os.Handler;
import android.os.Looper;
import com.naver.glink.android.sdk.a.l;
import com.naver.kaleido.DataHandler;
import com.naver.kaleido.DataTypeOptions;
import com.naver.kaleido.KaleidoAcl;
import com.naver.kaleido.KaleidoClient;
import com.naver.kaleido.KaleidoVariable;
import com.naver.kaleido.OnRemote;
import com.naver.kaleido.OnState;
import com.naver.kaleido.PublicPermission;
import com.naver.kaleido.UserType;

/* JADX INFO: compiled from: VariableWrapper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class d<T> implements DataHandler<KaleidoVariable<T>> {
    private static final int b = 100;
    private final KaleidoClient d;
    private final String e;
    private final Class<T> f;
    private KaleidoVariable<T> g;
    private boolean h;
    private static final l a = l.a(d.class.getSimpleName());
    private static Handler c = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.a.c.d$3, reason: invalid class name */
    /* JADX INFO: compiled from: VariableWrapper.java */
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

    public d(KaleidoClient kaleidoClient, String str, Class<T> cls) {
        this.d = kaleidoClient;
        this.e = str;
        this.f = cls;
    }

    public abstract void a();

    public void a(T t) {
        if (this.g == null) {
            return;
        }
        try {
            this.g.set(t);
        } catch (Exception e) {
            a.a(e);
        }
    }

    public abstract void a(T t, boolean z);

    public void b() {
        try {
            switch (AnonymousClass3.a[this.d.getAuthentication().getOwnerType().ordinal()]) {
                case 1:
                    this.g = KaleidoVariable.attach(this.d, this.e, new DataTypeOptions.Builder().setAcl(KaleidoAcl.create(PublicPermission.ALL_READ_LOGIN_READ_WRITE)).setMaxPullOperationSize(100).build(), this.f, this);
                    break;
                case 2:
                case 3:
                    this.g = KaleidoVariable.attachOrCreate(this.d, this.e, new DataTypeOptions.Builder().setAcl(KaleidoAcl.create(PublicPermission.ALL_READ_WRITE_LOGIN_READ_WRITE)).setMaxPullOperationSize(100).build(), this.f, this);
                    break;
            }
        } catch (Exception e) {
            a.a(e);
        }
    }

    public void c() {
        try {
            this.g.detach();
        } catch (Exception e) {
            a.a(e);
        }
    }

    public T d() {
        if (this.g == null) {
            return null;
        }
        try {
            return (T) this.g.get();
        } catch (Exception e) {
            a.a(e);
            return null;
        }
    }

    public void onRemoteOperations(final OnRemote<KaleidoVariable<T>> onRemote) {
        c.post(new Runnable() { // from class: com.naver.glink.android.sdk.a.c.d.2
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java.lang.Runnable
            public void run() {
                try {
                    d.this.a(onRemote.getData().get(), !d.this.h);
                } catch (Exception e) {
                    d.a.a(e);
                }
                d.this.h = true;
            }
        });
    }

    public void onStateChanged(OnState<KaleidoVariable<T>> onState) {
        switch (AnonymousClass3.b[onState.getCode().ordinal()]) {
            case 1:
                c.post(new Runnable() { // from class: com.naver.glink.android.sdk.a.c.d.1
                    @Override // java.lang.Runnable
                    public void run() {
                        d.this.a();
                    }
                });
                break;
            case 2:
                b();
                break;
        }
    }
}
