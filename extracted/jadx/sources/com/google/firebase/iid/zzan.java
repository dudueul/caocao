package com.google.firebase.iid;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final /* synthetic */ class zzan implements ComponentFactory {
    static final ComponentFactory zzcd = new zzan();

    private zzan() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        return new Registrar.zza((FirebaseInstanceId) componentContainer.get(FirebaseInstanceId.class));
    }
}
