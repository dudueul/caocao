package com.google.firebase.iid;

import com.google.firebase.FirebaseApp;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final /* synthetic */ class zzam implements ComponentFactory {
    static final ComponentFactory zzcd = new zzam();

    private zzam() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        return new FirebaseInstanceId((FirebaseApp) componentContainer.get(FirebaseApp.class));
    }
}
