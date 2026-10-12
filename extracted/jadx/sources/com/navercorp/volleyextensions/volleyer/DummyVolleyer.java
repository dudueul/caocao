package com.navercorp.volleyextensions.volleyer;

import com.navercorp.volleyextensions.volleyer.builder.DeleteBuilder;
import com.navercorp.volleyextensions.volleyer.builder.GetBuilder;
import com.navercorp.volleyextensions.volleyer.builder.PostBuilder;
import com.navercorp.volleyextensions.volleyer.builder.PutBuilder;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class DummyVolleyer extends Volleyer {
    DummyVolleyer() {
        super(null);
    }

    private void throwError() {
        throw new UnsupportedOperationException();
    }

    @Override // com.navercorp.volleyextensions.volleyer.Volleyer
    public DeleteBuilder delete(String str) {
        throwError();
        return null;
    }

    @Override // com.navercorp.volleyextensions.volleyer.Volleyer
    public GetBuilder get(String str) {
        throwError();
        return null;
    }

    @Override // com.navercorp.volleyextensions.volleyer.Volleyer
    public PostBuilder post(String str) {
        throwError();
        return null;
    }

    @Override // com.navercorp.volleyextensions.volleyer.Volleyer
    public PutBuilder put(String str) {
        throwError();
        return null;
    }

    @Override // com.navercorp.volleyextensions.volleyer.Volleyer
    public Volleyer.Settings settings() {
        throwError();
        return null;
    }
}
