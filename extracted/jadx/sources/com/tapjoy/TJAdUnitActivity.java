package com.tapjoy;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.tapjoy.internal.fm;
import com.tapjoy.internal.fq;
import com.tapjoy.internal.gn;
import com.tapjoy.internal.gz;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TJAdUnitActivity extends Activity implements View.OnClickListener {
    private static TJAdUnitActivity c;
    TJAdUnit a;
    private TJPlacementData d;
    private TJCloseButton g;
    private ProgressBar h;
    private final Handler b = new Handler(Looper.getMainLooper());
    private TJAdUnitSaveStateData e = new TJAdUnitSaveStateData();
    private RelativeLayout f = null;
    private boolean i = false;

    static void a() {
        TJAdUnitActivity tJAdUnitActivity = c;
        if (tJAdUnitActivity != null) {
            tJAdUnitActivity.handleClose(true);
        }
    }

    public void handleClose() {
        handleClose(false);
    }

    public void handleClose(boolean z) {
        if (this.a.getCloseRequested()) {
            return;
        }
        TapjoyLog.d("TJAdUnitActivity", TJAdUnitConstants.String.CLOSE_REQUESTED);
        this.a.closeRequested(z);
        this.b.postDelayed(new Runnable() { // from class: com.tapjoy.TJAdUnitActivity.1
            @Override // java.lang.Runnable
            public final void run() {
                if (TJAdUnitActivity.this.a.getCloseRequested()) {
                    TapjoyLog.d("TJAdUnitActivity", "Did not receive callback from content. Closing ad.");
                    TJAdUnitActivity.this.finish();
                }
            }
        }, 1000L);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        handleClose();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        handleClose();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.a.notifyOrientationChanged();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        TapjoyLog.d("TJAdUnitActivity", "TJAdUnitActivity onCreate: " + bundle);
        super.onCreate(bundle);
        c = this;
        if (bundle != null) {
            this.e = (TJAdUnitSaveStateData) bundle.getSerializable("ad_unit_bundle");
            if (this.e != null && this.e.isVideoComplete) {
                TapjoyLog.d("TJAdUnitActivity", "finishing TJAdUnitActivity");
                finish();
                return;
            }
        }
        Bundle extras = getIntent().getExtras();
        if (extras == null || extras.getSerializable(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA) == null) {
            TapjoyLog.e("TJAdUnitActivity", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Failed to launch AdUnit Activity"));
            finish();
            return;
        }
        this.d = (TJPlacementData) extras.getSerializable(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA);
        if (this.d.getContentViewId() != null) {
            TapjoyConnectCore.viewWillOpen(this.d.getContentViewId(), 1);
        }
        if (TJPlacementManager.a(this.d.getKey()) != null) {
            this.a = TJPlacementManager.a(this.d.getKey()).getAdUnit();
        } else {
            this.a = new TJAdUnit();
            this.a.setAdContentTracker(new fm(this.d.getPlacementName(), this.d.getPlacementType()));
        }
        if (!this.a.hasCalledLoad()) {
            TapjoyLog.d("TJAdUnitActivity", "No content loaded for ad unit -- loading now");
            this.a.load(this.d, false, this);
        }
        this.a.setAdUnitActivity(this);
        if (Build.VERSION.SDK_INT < 11) {
            setTheme(R.style.Theme);
        } else {
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
            getWindow().setFlags(16777216, 16777216);
        }
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.f = new RelativeLayout(this);
        this.f.setLayoutParams(layoutParams);
        this.f.setBackgroundColor(0);
        TJWebView backgroundWebView = this.a.getBackgroundWebView();
        backgroundWebView.setLayoutParams(layoutParams);
        if (backgroundWebView.getParent() != null) {
            ((ViewGroup) backgroundWebView.getParent()).removeView(backgroundWebView);
        }
        TJWebView webView = this.a.getWebView();
        webView.setLayoutParams(layoutParams);
        if (webView.getParent() != null) {
            ((ViewGroup) webView.getParent()).removeView(webView);
        }
        this.f.addView(backgroundWebView);
        VideoView videoView = this.a.getVideoView();
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        if (videoView.getParent() != null) {
            ((ViewGroup) videoView.getParent()).removeView(videoView);
        }
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setGravity(17);
        linearLayout.addView(videoView, new LinearLayout.LayoutParams(-1, -1));
        this.f.addView(linearLayout, layoutParams2);
        this.f.addView(webView);
        this.h = new ProgressBar(this, null, R.attr.progressBarStyleLarge);
        if (this.d.hasProgressSpinner()) {
            setProgressSpinnerVisibility(true);
        } else {
            setProgressSpinnerVisibility(false);
        }
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(13);
        this.h.setLayoutParams(layoutParams3);
        this.f.addView(this.h);
        this.g = new TJCloseButton(this);
        this.g.setOnClickListener(this);
        this.f.addView(this.g);
        setContentView(this.f);
        this.a.setVisible(true);
        TJCorePlacement tJCorePlacementA = TJPlacementManager.a(this.d.getKey());
        if (tJCorePlacementA != null) {
            TapjoyLog.i(TJCorePlacement.a, "Content shown for placement " + tJCorePlacementA.c.getPlacementName());
            tJCorePlacementA.f.a();
            TJPlacement tJPlacementA = tJCorePlacementA.a("SHOW");
            if (tJPlacementA == null || tJPlacementA.getListener() == null) {
                return;
            }
            tJPlacementA.getListener().onContentShow(tJPlacementA);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        TJPlacement tJPlacementA;
        super.onDestroy();
        c = null;
        TapjoyLog.d("TJAdUnitActivity", "onDestroy");
        if (this.a != null) {
            this.a.destroy();
        }
        if (this.d == null || !this.d.isBaseActivity()) {
            return;
        }
        if (this.d.getContentViewId() != null) {
            TapjoyConnectCore.viewDidClose(this.d.getContentViewId());
        }
        TJCorePlacement tJCorePlacementA = TJPlacementManager.a(this.d.getKey());
        if (tJCorePlacementA == null || (tJPlacementA = tJCorePlacementA.a("SHOW")) == null || tJPlacementA.getListener() == null) {
            return;
        }
        TapjoyLog.i(TJCorePlacement.a, "Content dismissed for placement " + tJCorePlacementA.c.getPlacementName());
        fq fqVar = tJCorePlacementA.f.a;
        if (fqVar != null) {
            fqVar.b.clear();
        }
        if (tJPlacementA == null || tJPlacementA.a == null) {
            return;
        }
        tJPlacementA.a.onContentDismiss(tJPlacementA);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        TapjoyLog.d("TJAdUnitActivity", "onPause");
        this.a.pause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        TapjoyLog.d("TJAdUnitActivity", "onResume");
        super.onResume();
        if (this.a.isLockedOrientation()) {
            setRequestedOrientation(this.a.getLockedOrientation());
        }
        this.a.resume(this.e);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        TapjoyLog.d("TJAdUnitActivity", "onSaveInstanceState");
        this.e.seekTime = this.a.getVideoSeekTime();
        this.e.isVideoComplete = this.a.isVideoComplete();
        this.e.isVideoMuted = this.a.isMuted();
        bundle.putSerializable("ad_unit_bundle", this.e);
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        TapjoyLog.d("TJAdUnitActivity", "onStart");
        if (gz.a().n) {
            this.i = true;
            gn.a(this);
        }
        if (this.d.isBaseActivity()) {
            return;
        }
        setResult(-1, getIntent());
    }

    @Override // android.app.Activity
    protected void onStop() {
        if (this.i) {
            this.i = false;
            gn.b(this);
        }
        super.onStop();
        TapjoyLog.d("TJAdUnitActivity", "onStop");
    }

    public void setCloseButtonClickable(boolean z) {
        this.g.setClickableRequested(z);
    }

    public void setCloseButtonVisibility(boolean z) {
        if (z) {
            this.g.setVisibility(0);
        } else {
            this.g.setVisibility(4);
        }
    }

    public void setProgressSpinnerVisibility(boolean z) {
        if (z) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(4);
        }
    }

    public void showErrorDialog() {
        if (isFinishing()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            new AlertDialog.Builder(this, R.style.Theme.Material.Light.Dialog.Alert).setMessage("An error occured. Please try again later.").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.tapjoy.TJAdUnitActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    TJAdUnitActivity.this.handleClose();
                    dialogInterface.cancel();
                }
            }).create().show();
        } else {
            new AlertDialog.Builder(this).setMessage("An error occured. Please try again later.").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.tapjoy.TJAdUnitActivity.3
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    TJAdUnitActivity.this.handleClose();
                    dialogInterface.cancel();
                }
            }).create().show();
        }
    }
}
