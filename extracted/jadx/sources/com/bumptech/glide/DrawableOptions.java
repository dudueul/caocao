package com.bumptech.glide;

import android.view.animation.Animation;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
interface DrawableOptions {
    GenericRequestBuilder<?, ?, ?, ?> crossFade();

    GenericRequestBuilder<?, ?, ?, ?> crossFade(int i);

    GenericRequestBuilder<?, ?, ?, ?> crossFade(int i, int i2);

    @Deprecated
    GenericRequestBuilder<?, ?, ?, ?> crossFade(Animation animation, int i);
}
