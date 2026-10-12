package com.naver.glink.android.sdk.ui.widget.dragviewer;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.naver.glink.android.sdk.a.a.b;

/* JADX INFO: compiled from: MultiMediaAnimationUtil.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    private static int g = 370;
    public static String a = "start_img";
    public static String b = "start_left";
    public static String c = "start_top";
    public static String d = "start_right";
    public static String e = "start_bottom";
    static TimeInterpolator f = new AccelerateDecelerateInterpolator();

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.widget.dragviewer.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MultiMediaAnimationUtil.java */
    public static class C0064a extends com.naver.plug.android.core.a.a {
        public final float a;

        C0064a(float f) {
            this.a = f;
        }
    }

    public static void a(Bundle bundle, View view, Rect rect) {
        if (bundle == null) {
            return;
        }
        a(view, new Rect(bundle.getInt(b, 0), bundle.getInt(c, 0), bundle.getInt(d, 0), bundle.getInt(e, 0)), rect);
    }

    public static void a(final View view, final Rect rect, final Rect rect2) {
        view.setPivotX(0.0f);
        view.setPivotY(0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "x", rect.left, rect2.left);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, "y", rect.top, rect2.top);
        objectAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.naver.glink.android.sdk.ui.widget.dragviewer.a.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                float fWidth = rect2.width() - rect.width();
                float fHeight = rect2.height() - rect.height();
                view.getLayoutParams().width = (int) ((fWidth * animatedFraction) + rect.width());
                view.getLayoutParams().height = (int) ((animatedFraction * fHeight) + rect.height());
                view.requestLayout();
            }
        });
        animatorSet.play(objectAnimatorOfFloat).with(objectAnimatorOfFloat2);
        animatorSet.setInterpolator(f);
        animatorSet.setDuration(g);
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.naver.glink.android.sdk.ui.widget.dragviewer.a.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                b.c(new C0064a(1.0f));
                if (view != null) {
                    view.setVisibility(8);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        animatorSet.start();
    }
}
