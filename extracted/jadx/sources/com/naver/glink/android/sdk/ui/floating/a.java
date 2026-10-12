package com.naver.glink.android.sdk.ui.floating;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import com.github.clans.fab.AbsFloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;

/* JADX INFO: compiled from: FullWidgetDialog.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a extends DialogFragment implements View.OnClickListener {
    private static final int a = 160;
    private static final int b = 80;
    private static a c;
    private View d;
    private Handler e;
    private b f;
    private Point g = new Point();
    private View.OnClickListener h;

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.floating.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FullWidgetDialog.java */
    private class DialogC0042a extends Dialog {
        public DialogC0042a(Context context) {
            super(context);
        }

        public DialogC0042a(Context context, int i) {
            super(context, i);
        }

        protected DialogC0042a(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
            super(context, z, onCancelListener);
        }

        @Override // android.app.Dialog, android.view.Window.Callback
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
        }
    }

    /* JADX INFO: compiled from: FullWidgetDialog.java */
    interface b {
        void a();
    }

    private Point a(int i, int i2, int i3) {
        Point point = new Point();
        point.x = i2;
        point.y = i3;
        return point;
    }

    private Animation a(long j, int i, int i2, int i3, int i4) {
        TranslateAnimation translateAnimation = new TranslateAnimation(i, i3, i2, i4);
        translateAnimation.setStartOffset(0L);
        translateAnimation.setDuration((int) j);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(translateAnimation);
        return animationSet;
    }

    static a a(int i, int i2) {
        if (c != null) {
            return null;
        }
        Bundle bundle = new Bundle();
        c = new a();
        bundle.putInt("X", i);
        bundle.putInt("Y", i2);
        c.setArguments(bundle);
        return c;
    }

    private void a() {
        com.naver.glink.android.sdk.api.requests.a.forStreamingCafeRequest.execute(getActivity(), new RequestListener<Responses.i>() { // from class: com.naver.glink.android.sdk.ui.floating.a.2
            @Override // com.naver.plug.android.core.api.request.RequestListener
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(@NonNull Responses.i iVar) {
                FloatingActionButton floatingActionButton = (FloatingActionButton) a.this.d.findViewById(R.id.float_item_live);
                if (iVar.live && iVar.liveAuthority && com.naver.glink.android.sdk.ui.streaming.b.b() && d.q()) {
                    floatingActionButton.setVisibility(0);
                } else {
                    floatingActionButton.setVisibility(8);
                }
                ((FloatingActionButton) a.this.d.findViewById(R.id.float_item_video)).setVisibility(a.this.b() ? 0 : 8);
                FloatingActionButton floatingActionButton2 = (FloatingActionButton) a.this.d.findViewById(R.id.float_item_cafe);
                if (com.naver.glink.android.sdk.alarm.b.c()) {
                    floatingActionButton2.setShowNewIcon(false);
                } else {
                    floatingActionButton2.setShowNewIcon(true);
                    floatingActionButton2.setShowNewIconDrawable(a.this.getResources().getDrawable(R.drawable.cf_blt_tag_live));
                }
                floatingActionButton2.requestLayout();
                a.this.d.requestLayout();
            }

            @Override // com.naver.plug.android.core.api.request.RequestListener
            public void onFailure(@NonNull PlugError plugError) {
                a.this.dismiss();
            }
        });
    }

    private void a(View view) {
        if (view == null) {
            return;
        }
        view.findViewById(R.id.float_item_cafe).setOnClickListener(this);
        view.findViewById(R.id.float_item_capture).setOnClickListener(this);
        view.findViewById(R.id.float_item_video).setOnClickListener(this);
        view.findViewById(R.id.float_item_live).setOnClickListener(this);
        view.findViewById(R.id.float_item_trash).setOnClickListener(this);
    }

    private int b(int i, int i2) {
        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == 1) {
            return i2 > this.g.y / 2 ? 0 : 1;
        }
        if (configuration.orientation == 2) {
            return i > this.g.x / 2 ? 2 : 3;
        }
        return 1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private Point b(int i, int i2, int i3) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.glink_widget_size_normal);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.glink_widget_padding);
        Point point = new Point();
        switch (i) {
            case 0:
                if (i2 > this.g.x / 2) {
                    point.x = (this.g.x - dimensionPixelSize) - dimensionPixelSize2;
                } else {
                    point.x = dimensionPixelSize2;
                }
                point.y = (this.g.y - dimensionPixelSize) - dimensionPixelSize2;
                return point;
            case 1:
                if (i2 > this.g.x / 2) {
                    point.x = (this.g.x - dimensionPixelSize) - dimensionPixelSize2;
                } else {
                    point.x = dimensionPixelSize2;
                }
                point.y = dimensionPixelSize2;
                return point;
            case 2:
                point.x = (this.g.x - dimensionPixelSize) - dimensionPixelSize2;
                point.y = (this.g.y / 2) - (dimensionPixelSize / 2);
                return point;
            case 3:
                point.x = dimensionPixelSize2;
                point.y = (this.g.y / 2) - (dimensionPixelSize / 2);
                return point;
            default:
                return point;
        }
    }

    private void b(View view) {
        view.findViewById(R.id.float_item_capture).setVisibility(p.j(getActivity()) ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b() {
        return p.i(getActivity()) && d.q();
    }

    private int c(int i, int i2) {
        return (int) (((((Math.abs(i - i2) * 100) / (this.g.y / 2)) * 160.0f) / 100.0f) + 80.0f);
    }

    private void c(final View view) {
        final AbsFloatingActionMenu absFloatingActionMenu = (AbsFloatingActionMenu) view.findViewById(R.id.float_menu);
        final View viewFindViewById = view.findViewById(R.id.ani_view);
        com.naver.glink.android.sdk.ui.floating.b.a(viewFindViewById);
        int i = getArguments().getInt("X", 0);
        int i2 = getArguments().getInt("Y", 0);
        int iB = b(i, i2);
        Point pointA = a(iB, i, i2);
        Point pointB = b(iB, i, i2);
        int iC = c(pointA.y, pointB.y);
        absFloatingActionMenu.setVisibility(8);
        absFloatingActionMenu.setOnMenuToggleListener(new AbsFloatingActionMenu.a() { // from class: com.naver.glink.android.sdk.ui.floating.a.3
            @Override // com.github.clans.fab.AbsFloatingActionMenu.a
            public void a(boolean z) {
                if (z) {
                    return;
                }
                a.this.dismiss();
            }
        });
        Configuration configuration = getResources().getConfiguration();
        if (i > this.g.x / 2 && configuration.orientation == 1) {
            absFloatingActionMenu.setOpenLabel(0);
            ((RelativeLayout.LayoutParams) absFloatingActionMenu.getLayoutParams()).addRule(11);
        }
        absFloatingActionMenu.setOpenDirection(iB);
        view.invalidate();
        Animation animationA = a(iC, pointA.x, pointA.y, pointB.x, pointB.y);
        animationA.setAnimationListener(new Animation.AnimationListener() { // from class: com.naver.glink.android.sdk.ui.floating.a.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                viewFindViewById.setVisibility(8);
                absFloatingActionMenu.setVisibility(0);
                if (view != null) {
                    a.this.e.postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.ui.floating.a.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            absFloatingActionMenu.a(true);
                        }
                    }, 20L);
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        viewFindViewById.startAnimation(animationA);
        viewFindViewById.setVisibility(0);
    }

    void a(View.OnClickListener onClickListener) {
        this.h = onClickListener;
    }

    void a(b bVar) {
        this.f = bVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.h != null) {
            this.h.onClick(view);
        }
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        DialogC0042a dialogC0042a = new DialogC0042a(getActivity(), getTheme());
        dialogC0042a.getWindow().requestFeature(1);
        dialogC0042a.getWindow().addFlags(16777216);
        return dialogC0042a;
    }

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = layoutInflater.inflate(R.layout.widget_dialog, viewGroup, false);
        getActivity().getWindowManager().getDefaultDisplay().getSize(this.g);
        this.e = new Handler();
        return this.d;
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.f != null) {
            this.f.a();
        }
        c = null;
        super.onDismiss(dialogInterface);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        dismissAllowingStateLoss();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        a();
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        if (getDialog() == null) {
            return;
        }
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.x = 0;
        attributes.y = this.g.y / 2;
        getDialog().getWindow().addFlags(1024);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getDialog().getWindow().setWindowAnimations(R.style.widget_show_animation);
    }

    @Override // android.app.Fragment
    @TargetApi(17)
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.floating.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                a.this.dismiss();
            }
        });
        a(this.d);
        b(this.d);
        c(this.d);
    }
}
