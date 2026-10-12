package com.facebook.share.model;

import com.facebook.share.ShareBuilder;
import com.facebook.share.model.ShareModel;
import com.facebook.share.model.ShareModelBuilder;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface ShareModelBuilder<P extends ShareModel, E extends ShareModelBuilder> extends ShareBuilder<P, E> {
    E readFrom(P p);
}
