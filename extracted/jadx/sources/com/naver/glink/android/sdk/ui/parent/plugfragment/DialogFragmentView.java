package com.naver.glink.android.sdk.ui.parent.plugfragment;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.google.android.gms.games.GamesStatusCodes;
import com.naver.glink.android.sdk.a.j;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.d;
import com.tapjoy.TapjoyConstants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DialogFragmentView extends FragmentView implements DialogInterface {
    private static final l a = l.a(DialogFragmentView.class.getSimpleName());
    public static final String e = DialogFragmentView.class.getSimpleName();
    private boolean b;
    private View c;
    private String d;
    protected Dialog f;
    private WindowManager.LayoutParams i;

    private class a extends Dialog {
        public a(Context context) {
            super(context);
        }

        @Override // android.app.Dialog, android.content.DialogInterface
        public void cancel() {
            DialogFragmentView.a.a("cancel", new Object[0]);
            if (com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().d() > 0) {
                com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().e();
            }
            super.cancel();
        }

        @Override // android.app.Dialog, android.view.Window.Callback
        public boolean dispatchKeyEvent(@NonNull KeyEvent keyEvent) {
            if (!j.a(keyEvent)) {
                return super.dispatchKeyEvent(keyEvent);
            }
            DialogFragmentView.this.dispatchKeyEvent(keyEvent);
            return true;
        }
    }

    public DialogFragmentView(Context context) {
        super(context);
    }

    private boolean a(View view, int i, int i2) {
        if (view == null) {
            return true;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return i >= iArr[0] && i <= iArr[0] + view.getWidth() && i2 >= iArr[1] && i2 <= iArr[1] + view.getHeight();
    }

    private Dialog c() {
        a aVar = new a(getContext());
        aVar.requestWindowFeature(1);
        aVar.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        aVar.getWindow().clearFlags(8);
        aVar.setContentView(this);
        aVar.setCanceledOnTouchOutside(false);
        return aVar;
    }

    protected void a(int i, int i2) {
        if (this.f != null) {
            this.f.getWindow().setLayout(i, i2);
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Context context) {
        super.a(context);
        this.b = false;
        if (Build.VERSION.SDK_INT >= 26) {
            setEnabled(false);
            setClickable(true);
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
    }

    public void a(DialogInterface dialogInterface) {
    }

    protected void a(WindowManager.LayoutParams layoutParams) {
        if (layoutParams != null) {
            layoutParams.width = -2;
            layoutParams.height = -2;
            layoutParams.gravity = 17;
            layoutParams.dimAmount = 0.6f;
            layoutParams.format = -2;
            layoutParams.windowAnimations = R.style.Animation.Dialog;
            layoutParams.flags |= 16778242;
        }
    }

    public void a(String str) {
        if (str == null) {
            str = getClass().getSimpleName() + System.currentTimeMillis();
        }
        setTag(str);
        com.naver.glink.android.sdk.ui.parent.plugfragment.a.b bVarB = com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b();
        bVarB.a(this, str);
        bVarB.a(str);
        bVarB.a();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a_() {
        a.a("onDestroy", new Object[0]);
        this.i = null;
        this.c = null;
        this.d = null;
        a(this);
        super.a_();
    }

    @Override // android.content.DialogInterface
    public void cancel() {
        a.a("cancel", new Object[0]);
        com.naver.glink.android.sdk.ui.parent.plugfragment.a.b bVarB = com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b();
        bVarB.a(this);
        bVarB.a();
    }

    @Override // android.content.DialogInterface
    public void dismiss() {
        a.a(TapjoyConstants.TJC_FULLSCREEN_AD_DISMISS_URL, new Object[0]);
        com.naver.glink.android.sdk.ui.parent.plugfragment.a.b bVarB = com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().b();
        bVarB.a(this);
        bVarB.a();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!j.a(keyEvent)) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().d() > 0) {
            com.naver.glink.android.sdk.ui.parent.plugfragment.a.a().e();
            return true;
        }
        dismiss();
        d.c(getContext());
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public Dialog getPlugDialog() {
        return this.f;
    }

    @Override // android.view.View
    public String getTag() {
        return this.d;
    }

    public WindowManager.LayoutParams getWindowParam() {
        return this.i;
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void j() {
        super.j();
        requestFocus();
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void k() {
        WindowManager.LayoutParams layoutParamsI;
        super.k();
        if (isAttachedToWindow() && this.f != null) {
            Log.e(e, "This FragmentView is aleady added to window!");
            this.f.dismiss();
        }
        try {
            if (d.h(getContext()) && (layoutParamsI = d.i(getContext())) != null) {
                this.i = new WindowManager.LayoutParams();
                this.i.copyFrom(layoutParamsI);
                Log.d(e, "copy from service window");
            }
            if (this.i == null) {
                this.i = new WindowManager.LayoutParams();
                if (d.h(getContext())) {
                    this.i.type = GamesStatusCodes.STATUS_REQUEST_TOO_MANY_RECIPIENTS;
                }
            }
            this.f = c();
            a(this.i);
            this.f.getWindow().setAttributes(this.i);
            this.i = this.f.getWindow().getAttributes();
            this.f.show();
        } catch (RuntimeException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void l() {
        try {
            if (this.f != null && this.f.isShowing()) {
                ViewGroup viewGroup = (ViewGroup) this.f.findViewById(R.id.content);
                if (viewGroup != null) {
                    viewGroup.removeView(this);
                } else {
                    this.f.setContentView(new View(getContext()));
                }
                this.f.dismiss();
            }
            removeAllViews();
        } catch (RuntimeException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        Log.e(e, "onConfigurationChanged " + configuration.orientation);
        if (configuration.orientation != this.i.screenOrientation) {
            l();
        }
        super.onConfigurationChanged(configuration);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        a.a("onTouchEvent", new Object[0]);
        if (this.b) {
            a.a("isCancelOutsideTouch is true", new Object[0]);
            if (motionEvent.getAction() == 1 && !a(this.c, (int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                dismiss();
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCanceledOnTouchOutside(boolean z, View view) {
        a.a("setCanceledOnTouchOutside", new Object[0]);
        this.c = view;
        this.b = z;
    }

    protected void setDialogVisible(boolean z) {
        if (this.f != null) {
            if (z) {
                this.f.hide();
            } else {
                this.f.show();
            }
        }
    }

    @Override // android.view.View
    public void setTag(Object obj) {
        if (obj instanceof String) {
            this.d = (String) obj;
        }
        super.setTag(obj);
    }
}
