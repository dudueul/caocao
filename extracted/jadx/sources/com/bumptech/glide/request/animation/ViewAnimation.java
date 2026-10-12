package com.bumptech.glide.request.animation;

import android.view.View;
import android.view.animation.Animation;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ViewAnimation<R> implements GlideAnimation<R> {
    private final AnimationFactory animationFactory;

    interface AnimationFactory {
        Animation build();
    }

    ViewAnimation(AnimationFactory animationFactory) {
        this.animationFactory = animationFactory;
    }

    @Override // com.bumptech.glide.request.animation.GlideAnimation
    public boolean animate(R r, GlideAnimation.ViewAdapter viewAdapter) {
        View view = viewAdapter.getView();
        if (view == null) {
            return false;
        }
        view.clearAnimation();
        view.startAnimation(this.animationFactory.build());
        return false;
    }
}
