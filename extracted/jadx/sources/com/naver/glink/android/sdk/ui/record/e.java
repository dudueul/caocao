package com.naver.glink.android.sdk.ui.record;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.a.y;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: compiled from: RecordWidgetDialog.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class e extends DialogFragment {
    private static final l a = l.a(e.class.getSimpleName());
    private static final int b = 40;
    private static final int c = 400;
    private static final int d = 3;
    private static final int e = 1000;
    private static final int f = 1;
    private static final int g = 2;
    private static final int h = 3;
    private static final int i = 4;
    private WindowManager.LayoutParams A;
    private WindowManager B;
    private com.naver.glink.android.sdk.ui.floating.b.a C;
    private c D;
    private long E;
    private View j;
    private View k;
    private View l;
    private View m;
    private View n;
    private TextView o;
    private ViewGroup p;
    private float q;
    private float r;
    private int s;
    private int t;
    private int u;
    private Point v;
    private boolean w;
    private boolean x;
    private boolean y;
    private Handler z = new b();

    /* JADX INFO: compiled from: RecordWidgetDialog.java */
    class a extends Timer {
        private final int b = 200;
        private TimerTask c;

        a(final Handler handler, final int i) {
            final int i2;
            final int i3;
            int i4;
            e.this.u = 0;
            if (i == 3) {
                int i5 = (e.this.v.x - e.this.A.x) + e.this.A.width;
                i4 = e.this.A.y > 0 ? e.this.A.y : 0;
                int i6 = i5 / 200 > 0 ? (i5 / 200) + 1 : 1;
                i2 = i4 / 200 > 0 ? (i4 / 200) + 1 : 1;
                i3 = i6;
            } else if (i == 1) {
                int i7 = e.this.A.x;
                i4 = e.this.A.y > 0 ? e.this.A.y : 0;
                int i8 = i7 / 200 > 0 ? (i7 / 200) + 1 : 1;
                i2 = i4 / 200 > 0 ? (i4 / 200) + 1 : 1;
                i3 = i8;
            } else if (i == 4) {
                int i9 = e.this.A.x;
                int i10 = (e.this.v.y - e.this.A.y) + e.this.A.height;
                int i11 = i9 / 200 > 0 ? (i9 / 200) + 1 : 1;
                i2 = i10 / 200 > 0 ? (i10 / 200) + 1 : 1;
                i3 = i11;
            } else {
                if (i != 2) {
                    return;
                }
                int i12 = e.this.A.x;
                int i13 = (e.this.v.y - e.this.A.y) + e.this.A.height;
                int i14 = i12 / 200 > 0 ? (i12 / 200) + 1 : 1;
                i2 = i13 / 200 > 0 ? (i13 / 200) + 1 : 1;
                i3 = i14;
            }
            this.c = new TimerTask() { // from class: com.naver.glink.android.sdk.ui.record.e.a.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    e.this.w = true;
                    Message message = new Message();
                    message.what = i;
                    message.arg1 = i3;
                    message.arg2 = i2;
                    handler.sendMessage(message);
                    if (e.this.u >= 200) {
                        a.this.cancel();
                        e.this.w = false;
                    }
                }
            };
        }

        public void a() {
            schedule(this.c, 0L, 1L);
        }
    }

    /* JADX INFO: compiled from: RecordWidgetDialog.java */
    class b extends Handler {
        b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.arg1;
            int i2 = message.arg2;
            switch (message.what) {
                case 1:
                    e.this.A.x -= i;
                    e.this.A.y -= i2;
                    break;
                case 2:
                    e.this.A.x -= i;
                    WindowManager.LayoutParams layoutParams = e.this.A;
                    layoutParams.y = i2 + layoutParams.y;
                    break;
                case 3:
                    WindowManager.LayoutParams layoutParams2 = e.this.A;
                    layoutParams2.x = i + layoutParams2.x;
                    e.this.A.y -= i2;
                    break;
                case 4:
                    WindowManager.LayoutParams layoutParams3 = e.this.A;
                    layoutParams3.x = i + layoutParams3.x;
                    WindowManager.LayoutParams layoutParams4 = e.this.A;
                    layoutParams4.y = i2 + layoutParams4.y;
                    break;
            }
            if (e.this.A.x < 0) {
                e.this.A.x = 0;
            } else if (e.this.A.x + e.this.j.getWidth() > e.this.v.x) {
                e.this.A.x = e.this.v.x - e.this.j.getWidth();
            }
            if (e.this.A.y < 0) {
                e.this.A.y = 0;
            } else if (e.this.A.y + e.this.j.getHeight() > e.this.v.y) {
                e.this.A.y = e.this.v.y - e.this.j.getHeight();
            }
            e.y(e.this);
            if (e.this.k != null) {
                e.this.B.updateViewLayout(e.this.k, e.this.A);
            }
        }
    }

    public static e a(c cVar) {
        Bundle bundle = new Bundle();
        e eVar = new e();
        eVar.setArguments(bundle);
        eVar.D = cVar;
        return eVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        final View viewFindViewById = this.p.findViewById(R.id.record_ani_layout);
        final TextView textView = (TextView) this.p.findViewById(R.id.record_ani_timer_text);
        final TextView textView2 = (TextView) this.p.findViewById(R.id.record_ani_copy_text);
        final View viewFindViewById2 = this.p.findViewById(R.id.record_ani_icon);
        this.l.setVisibility(4);
        this.p.setVisibility(4);
        textView.setText(String.format("Start Recording", 3));
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.p, "x", 0.0f, 0.0f);
        animatorSet.play(objectAnimatorOfFloat);
        animatorSet.setInterpolator(new DecelerateInterpolator(2.5f));
        animatorSet.setDuration(1000L);
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.naver.glink.android.sdk.ui.record.e.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                e.this.p.postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.ui.record.e.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        e.this.c();
                    }
                }, 1000L);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                e.this.j.setEnabled(false);
                e.this.p.setVisibility(0);
            }
        });
        final int iA = x.a(48.0f);
        final int i2 = viewFindViewById.getLayoutParams().width;
        objectAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.naver.glink.android.sdk.ui.record.e.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                viewFindViewById.getLayoutParams().width = iA + ((int) ((i2 - iA) * animatedFraction));
                viewFindViewById.requestLayout();
                textView.setAlpha((0.999f * animatedFraction) + 1.0E-4f);
                textView2.setAlpha((animatedFraction * 0.999f) + 1.0E-4f);
                viewFindViewById2.setAlpha(1.0f);
            }
        });
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        final View viewFindViewById = this.p.findViewById(R.id.record_ani_layout);
        final TextView textView = (TextView) this.p.findViewById(R.id.record_ani_timer_text);
        final TextView textView2 = (TextView) this.p.findViewById(R.id.record_ani_copy_text);
        final View viewFindViewById2 = this.p.findViewById(R.id.record_ani_icon);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.p, "x", 0.0f, 0.0f);
        animatorSet.play(objectAnimatorOfFloat);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.setDuration(600L);
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.naver.glink.android.sdk.ui.record.e.5
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                e.this.m.setVisibility(8);
                e.this.n.setVisibility(0);
                e.this.l.setVisibility(0);
                e.this.p.setVisibility(8);
                e.this.j.setEnabled(true);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        final int i2 = viewFindViewById.getLayoutParams().width;
        final int iA = x.a(48.0f);
        objectAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.naver.glink.android.sdk.ui.record.e.6
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                viewFindViewById.getLayoutParams().width = i2 - ((int) ((i2 - iA) * animatedFraction));
                viewFindViewById.requestLayout();
                textView.setAlpha(1.0f - (0.999f * animatedFraction));
                textView2.setAlpha(1.0f - (0.999f * animatedFraction));
                viewFindViewById2.setAlpha(1.0f - (animatedFraction * 0.999f));
            }
        });
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RecordManager.b d() {
        final DecimalFormat decimalFormat = new DecimalFormat("00");
        return new RecordManager.b() { // from class: com.naver.glink.android.sdk.ui.record.e.7
            @Override // com.naver.glink.android.sdk.ui.record.RecordManager.b
            public void a() {
                e.this.y = true;
                e.this.b();
                e.a.a("recording - onStart()", new Object[0]);
                if (com.naver.glink.android.sdk.ui.main.b.i() == null || com.naver.glink.android.sdk.ui.main.b.i().getPlugDialog() == null) {
                    return;
                }
                com.naver.glink.android.sdk.ui.main.b.i().getPlugDialog().setCanceledOnTouchOutside(true);
            }

            @Override // com.naver.glink.android.sdk.ui.record.RecordManager.b
            public void a(long j) {
                if (e.this.y) {
                    e.this.o.setText(decimalFormat.format(((int) j) / 60) + ":" + decimalFormat.format(((int) j) % 60));
                }
            }

            @Override // com.naver.glink.android.sdk.ui.record.RecordManager.b
            public void a(String str) {
                if (e.this.getActivity() != null) {
                    e.a.a("recording = onStop()", new Object[0]);
                    e.this.m.setVisibility(0);
                    e.this.n.setVisibility(8);
                    e.this.o.setText("00:00");
                    Toast.makeText(e.this.getActivity(), e.this.getResources().getString(R.string.record_saved), 0).show();
                    e.this.getActivity().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", y.a("file://" + str)));
                    if (com.naver.glink.android.sdk.ui.main.b.i() != null && com.naver.glink.android.sdk.ui.main.b.i().getPlugDialog() != null) {
                        com.naver.glink.android.sdk.ui.main.b.i().getPlugDialog().setCanceledOnTouchOutside(false);
                    }
                }
                com.naver.glink.android.sdk.b.b(y.a("file://" + str).toString());
                e.this.dismissAllowingStateLoss();
            }

            @Override // com.naver.glink.android.sdk.ui.record.RecordManager.b
            public void b() {
                Log.e("PLUG", "Record error");
            }
        };
    }

    static /* synthetic */ int y(e eVar) {
        int i2 = eVar.u;
        eVar.u = i2 + 1;
        return i2;
    }

    public void a(com.naver.glink.android.sdk.ui.floating.b.a aVar) {
        this.C = aVar;
    }

    @Override // android.app.DialogFragment
    public void dismiss() {
        this.k = null;
        super.dismiss();
    }

    @Override // android.app.DialogFragment
    public void dismissAllowingStateLoss() {
        this.k = null;
        super.dismissAllowingStateLoss();
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (!RecordManager.a(i2, i3, intent)) {
            this.y = false;
        }
        super.onActivityResult(i2, i3, intent);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialogOnCreateDialog = super.onCreateDialog(bundle);
        if (this.D == null) {
            dismissAllowingStateLoss();
        }
        WindowManager.LayoutParams attributes = dialogOnCreateDialog.getWindow().getAttributes();
        attributes.flags = 263176;
        dialogOnCreateDialog.getWindow().requestFeature(1);
        dialogOnCreateDialog.getWindow().addFlags(16777216);
        dialogOnCreateDialog.getWindow().clearFlags(2);
        if (Build.VERSION.SDK_INT < 25) {
            dialogOnCreateDialog.getWindow().setType(2005);
        }
        attributes.gravity = 51;
        attributes.width = -2;
        attributes.height = -2;
        attributes.dimAmount = 0.0f;
        attributes.format = -2;
        attributes.x = 0;
        attributes.y = 0;
        dialogOnCreateDialog.setCanceledOnTouchOutside(false);
        dialogOnCreateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialogOnCreateDialog.getWindow().setWindowAnimations(R.style.record_widget_anim);
        this.A = attributes;
        this.B = dialogOnCreateDialog.getWindow().getWindowManager();
        return dialogOnCreateDialog;
    }

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.j = layoutInflater.inflate(R.layout.widget_record_layout, viewGroup, false);
        return this.j;
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.k = null;
        RecordManager.a();
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.y) {
            RecordManager.c();
            dismissAllowingStateLoss();
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.v = com.naver.glink.android.sdk.d.p().b();
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        if (getDialog() == null) {
        }
    }

    @Override // android.app.Fragment
    @TargetApi(17)
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.l = view.findViewById(R.id.record_layout);
        this.m = view.findViewById(R.id.record_view);
        this.n = view.findViewById(R.id.record_view_stop);
        this.o = (TextView) view.findViewById(R.id.record_timer);
        this.o.setText("00:00");
        this.p = (ViewGroup) view.findViewById(R.id.record_ani_view);
        this.k = getDialog().getWindow().getDecorView();
        this.j.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.record.e.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
            }
        });
        this.j.setOnTouchListener(new View.OnTouchListener() { // from class: com.naver.glink.android.sdk.ui.record.e.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (e.this.w || e.this.j.getVisibility() == 8) {
                    return true;
                }
                int rawX = (int) (motionEvent.getRawX() - e.this.q);
                int rawY = (int) (motionEvent.getRawY() - e.this.r);
                if (motionEvent.getEventTime() - motionEvent.getDownTime() > 400 && !e.this.x && e.this.C != null && !e.this.y) {
                    e.this.C.a();
                    e.this.x = true;
                }
                switch (motionEvent.getAction()) {
                    case 0:
                        e.this.q = motionEvent.getRawX();
                        e.this.r = motionEvent.getRawY();
                        e.this.s = e.this.A.x;
                        e.this.t = e.this.A.y;
                        return false;
                    case 1:
                        if (e.this.C != null) {
                            e.this.C.a(motionEvent);
                            e.this.x = false;
                        }
                        if (motionEvent.getRawX() > e.this.v.x / 2 && motionEvent.getRawY() < e.this.v.y / 2) {
                            e.this.new a(e.this.z, 3).a();
                        } else if (motionEvent.getRawX() < e.this.v.x / 2 && motionEvent.getRawY() < e.this.v.y / 2) {
                            e.this.new a(e.this.z, 1).a();
                        } else if (motionEvent.getRawX() <= e.this.v.x / 2 || motionEvent.getRawY() <= e.this.v.y / 2) {
                            e.this.new a(e.this.z, 2).a();
                        } else {
                            e.this.new a(e.this.z, 4).a();
                        }
                        if (Math.abs(e.this.r - motionEvent.getRawY()) >= 40.0f || Math.abs(e.this.q - motionEvent.getRawX()) >= 40.0f || e.this.C == null) {
                            return false;
                        }
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        if (e.this.E != 0 && jCurrentTimeMillis - e.this.E < 800) {
                            return false;
                        }
                        e.this.E = jCurrentTimeMillis;
                        e.this.C.a(motionEvent, rawX + e.this.s, rawY + e.this.t);
                        if (e.this.y) {
                            RecordManager.b(RecordManager.RECORD_TYPE.WIDGET);
                            return false;
                        }
                        if (RecordManager.b().c(RecordManager.RECORD_TYPE.WIDGET)) {
                            return false;
                        }
                        RecordManager.a(e.this, e.this.D, RecordManager.RECORD_TYPE.WIDGET);
                        RecordManager.b().a(e.this.d());
                        return false;
                    case 2:
                        if (e.this.s + rawX > e.this.v.x - e.this.getResources().getDimensionPixelSize(R.dimen.glink_widget_size_normal)) {
                            e.this.A.x = e.this.v.x - e.this.getResources().getDimensionPixelSize(R.dimen.glink_widget_size_normal);
                        } else {
                            e.this.A.x = rawX + e.this.s;
                        }
                        if (e.this.t + rawY > e.this.v.y - e.this.getResources().getDimensionPixelSize(R.dimen.glink_widget_size_normal)) {
                            e.this.A.y = e.this.v.y - e.this.getResources().getDimensionPixelSize(R.dimen.glink_widget_size_normal);
                        } else {
                            e.this.A.y = e.this.t + rawY;
                        }
                        if (e.this.k != null) {
                            e.this.B.updateViewLayout(e.this.k, e.this.A);
                        }
                        if (e.this.C == null) {
                            return false;
                        }
                        e.this.C.b(motionEvent);
                        return false;
                    default:
                        return false;
                }
            }
        });
    }
}
