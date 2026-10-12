package com.tapjoy.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class ef extends ej {
    protected ef(Class cls) {
        super(eg.VARINT, cls);
    }

    @Override // com.tapjoy.internal.ej
    public final /* bridge */ /* synthetic */ int a(Object obj) {
        return el.a(((em) obj).a());
    }

    public abstract em a(int i);

    @Override // com.tapjoy.internal.ej
    public final /* synthetic */ Object a(ek ekVar) {
        int iD = ekVar.d();
        em emVarA = a(iD);
        if (emVarA == null) {
            throw new ej.a(iD, this.a);
        }
        return emVarA;
    }

    @Override // com.tapjoy.internal.ej
    public final /* synthetic */ void a(el elVar, Object obj) {
        elVar.c(((em) obj).a());
    }
}
