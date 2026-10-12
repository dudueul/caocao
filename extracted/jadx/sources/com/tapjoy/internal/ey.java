package com.tapjoy.internal;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum ey implements em {
    APP(0),
    CAMPAIGN(1),
    CUSTOM(2),
    USAGES(3);

    public static final ej ADAPTER = new ef() { // from class: com.tapjoy.internal.ey.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.tapjoy.internal.ef
        public final /* bridge */ /* synthetic */ em a(int i) {
            return ey.a(i);
        }
    };
    private final int a;

    ey(int i) {
        this.a = i;
    }

    public static ey a(int i) {
        switch (i) {
            case 0:
                return APP;
            case 1:
                return CAMPAIGN;
            case 2:
                return CUSTOM;
            case 3:
                return USAGES;
            default:
                return null;
        }
    }

    @Override // com.tapjoy.internal.em
    public final int a() {
        return this.a;
    }
}
