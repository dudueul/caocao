package com.tapjoy.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ap {

    public static final class a implements as {
        private final aq a;

        public a(aq aqVar) {
            this.a = aqVar;
        }

        @Override // com.tapjoy.internal.an
        public final Object a(Object obj) {
            ao aoVarA;
            Object objA;
            synchronized (this.a) {
                aoVarA = this.a.a(obj, false);
            }
            if (aoVarA == null) {
                return null;
            }
            synchronized (aoVarA) {
                objA = aoVarA.a();
            }
            return objA;
        }

        @Override // com.tapjoy.internal.an
        public final void a(Object obj, Object obj2) {
            ao aoVarA;
            synchronized (this.a) {
                aoVarA = this.a.a(obj, true);
            }
            synchronized (aoVarA) {
                aoVarA.a(obj2);
            }
        }
    }
}
