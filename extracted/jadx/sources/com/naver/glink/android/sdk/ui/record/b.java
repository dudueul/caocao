package com.naver.glink.android.sdk.ui.record;

import android.app.Fragment;

/* JADX INFO: compiled from: PlugRecordImpl.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b implements a {
    public static a c() {
        return new b();
    }

    @Override // com.naver.glink.android.sdk.ui.record.a
    public void a() {
        RecordManager.b(RecordManager.RECORD_TYPE.CUSTOM);
    }

    @Override // com.naver.glink.android.sdk.ui.record.a
    public void a(Fragment fragment, c cVar) {
        RecordManager.a(fragment, cVar, RecordManager.RECORD_TYPE.CUSTOM);
    }

    @Override // com.naver.glink.android.sdk.ui.record.a
    public void a(RecordManager.b bVar) {
        RecordManager.b().a(bVar);
    }

    @Override // com.naver.glink.android.sdk.ui.record.a
    public boolean b() {
        if (com.naver.glink.android.sdk.d.q()) {
            return RecordManager.b().c(RecordManager.RECORD_TYPE.CUSTOM);
        }
        return false;
    }
}
