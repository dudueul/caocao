package com.tapjoy;

import android.R;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.adjust.sdk.Constants;
import com.tapjoy.internal.jq;
import java.util.Arrays;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TJSplitWebView extends RelativeLayout {
    private TJWebView a;

    @Nullable
    private a b;

    @Nullable
    private a c;
    private String d;
    private String e;

    @Nullable
    private String f;

    @Nullable
    private Uri g;
    private HashSet h;
    private JSONObject i;
    private TJAdUnitJSBridge j;
    private Context k;
    private Boolean l;
    private RelativeLayout m;
    private FrameLayout n;
    private ProgressBar o;
    private TextView p;
    private TJImageButton q;
    private TJImageButton r;
    private String s;
    private boolean t;
    private String u;
    private String v;
    private String w;

    static class a {
        final double a;
        final double b;
        final double c;
        final double d;
        final float e;

        a(JSONObject jSONObject) {
            this.a = jSONObject.optDouble("width", 0.0d);
            this.b = jSONObject.optDouble("height", 0.0d);
            this.c = jSONObject.optDouble(TJAdUnitConstants.String.LEFT, 0.0d);
            this.d = jSONObject.optDouble(TJAdUnitConstants.String.TOP, 0.0d);
            this.e = (float) jSONObject.optDouble("cornerRadius", 0.0d);
        }
    }

    class b extends WebViewClient {
        private b() {
        }

        /* synthetic */ b(TJSplitWebView tJSplitWebView, byte b) {
            this();
        }

        @Override // android.webkit.WebViewClient
        public final void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (TJSplitWebView.this.l.booleanValue()) {
                if (TJSplitWebView.this.o != null) {
                    TJSplitWebView.this.o.setProgress(0);
                    TJSplitWebView.this.o.setVisibility(8);
                }
                TJSplitWebView.this.isFirstOrLastPage();
            }
        }

        @Override // android.webkit.WebViewClient
        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (TJSplitWebView.this.l.booleanValue()) {
                TJSplitWebView.this.p.setText(TapjoyUrlFormatter.getDomain(str));
                TJSplitWebView.this.o.setVisibility(0);
            }
            TapjoyLog.d("TJSplitWebView", "onPageStarted: " + str);
        }

        @Override // android.webkit.WebViewClient
        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            TapjoyLog.d("TJSplitWebView", "onReceivedError: " + str2 + " firstUrl:" + TJSplitWebView.this.d);
            if (TJSplitWebView.this.t) {
                TJSplitWebView.this.showErrorDialog();
            } else if (str2.equals(TJSplitWebView.this.d)) {
                TJSplitWebView.this.a();
            }
        }

        @Override // android.webkit.WebViewClient
        @Nullable
        public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            String str2 = TJSplitWebView.this.f;
            Uri uri = TJSplitWebView.this.g;
            if (str2 != null && uri != null && str != null && str.startsWith(str2)) {
                TJSplitWebView.this.k.startActivity(new Intent("android.intent.action.VIEW", uri));
                TJSplitWebView.this.a();
            }
            return super.shouldInterceptRequest(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            TapjoyLog.d("TJSplitWebView", "shouldOverrideUrlLoading: " + str);
            Uri uri = Uri.parse(str);
            if (uri != null) {
                String host = uri.getHost();
                String scheme = uri.getScheme();
                if (scheme != null && host != null && ((scheme.equals("http") || scheme.equals(Constants.SCHEME)) && (TJSplitWebView.this.h == null || !TJSplitWebView.this.h.contains(host)))) {
                    TJSplitWebView.this.e = str;
                    return false;
                }
            }
            try {
                TJSplitWebView.this.k.startActivity(new Intent("android.intent.action.VIEW", uri));
                if (!TJSplitWebView.this.l.booleanValue()) {
                    TJSplitWebView.this.a();
                }
            } catch (Exception e) {
                TapjoyLog.e("TJSplitWebView", e.getMessage());
            }
            return true;
        }
    }

    @TargetApi(21)
    public TJSplitWebView(Context context, JSONObject jSONObject, TJAdUnitJSBridge tJAdUnitJSBridge) {
        super(context);
        this.j = tJAdUnitJSBridge;
        this.k = context;
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(TJAdUnitConstants.String.SPLIT_VIEW_LAYOUT);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(TJAdUnitConstants.String.SPLIT_VIEW_EXIT_HOSTS);
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject(TJAdUnitConstants.String.SPLIT_VIEW_ERROR_DIALOG_STRINGS);
        this.s = jSONObject.optString(TJAdUnitConstants.String.SPLIT_VIEW_URL_FOR_EXTERNAL_OPEN);
        this.i = jSONObject.optJSONObject(TJAdUnitConstants.String.SPLIT_VIEW_ANIMATION);
        setLayoutOption(jSONObjectOptJSONObject);
        setExitHosts(jSONArrayOptJSONArray);
        setErrorDialog(jSONObjectOptJSONObject2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.a = new TJWebView(this.k);
        this.a.setId(TapjoyUtil.generateViewId());
        this.a.setBackgroundColor(-1);
        WebSettings settings = this.a.getSettings();
        if (settings != null) {
            settings.setUseWideViewPort(true);
        }
        this.a.setWebViewClient(new b(this, (byte) 0));
        this.l = Boolean.valueOf(jSONObject.optBoolean(TJAdUnitConstants.String.SPLIT_VIEW_SHOW_TOOLBAR));
        if (this.l.booleanValue()) {
            addToolbar();
            addLineBreak();
            addProgressBar();
            this.a.setWebChromeClient(new WebChromeClient() { // from class: com.tapjoy.TJSplitWebView.1
                @Override // android.webkit.WebChromeClient
                public final void onProgressChanged(WebView webView, int i) {
                    super.onProgressChanged(webView, i);
                    TJSplitWebView.this.o.setProgress(i);
                    TJSplitWebView.this.isFirstOrLastPage();
                }
            });
        }
        addView(this.a, layoutParams);
    }

    private void a(int i, int i2) {
        a aVar = i <= i2 ? this.b : this.c;
        if (aVar == null) {
            this.a.setVisibility(4);
            return;
        }
        int i3 = (int) (((double) i) * aVar.a);
        int i4 = (int) (((double) i2) * aVar.b);
        if (i3 == 0 || i4 == 0) {
            this.a.setVisibility(4);
            return;
        }
        int i5 = (int) (((double) i) * aVar.c);
        int i6 = (int) (((double) i2) * aVar.d);
        int i7 = (i - i3) - i5;
        int i8 = (i2 - i4) - i6;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.a.getLayoutParams();
        layoutParams.width = i3;
        layoutParams.height = i4;
        if (this.l == null || !this.l.booleanValue()) {
            layoutParams.setMargins(i5, i6, i7, i8);
        } else {
            float screenDensityScale = new TapjoyDisplayMetricsUtil(getContext()).getScreenDensityScale();
            int height = this.n.getHeight() + ((int) (40.0f * screenDensityScale));
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.m.getLayoutParams();
            layoutParams2.setMargins(i5, i6, i7, i8);
            this.m.setLayoutParams(layoutParams2);
            layoutParams.setMargins(i5, i6 + height, i7, i8);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(i3, (int) screenDensityScale);
            layoutParams3.setMargins(i5, layoutParams.topMargin - this.o.getHeight(), i7, i8);
            this.o.setLayoutParams(layoutParams3);
            this.n.setLayoutParams(layoutParams3);
        }
        this.a.setLayoutParams(layoutParams);
        this.a.setVisibility(0);
        if (Build.VERSION.SDK_INT >= 21) {
            if (aVar.e <= 0.0f) {
                this.a.setBackground(null);
                this.a.setClipToOutline(false);
                if (this.l == null || !this.l.booleanValue()) {
                    return;
                }
                this.m.setClipToOutline(false);
                return;
            }
            float[] fArr = new float[8];
            final float f = aVar.e * getResources().getDisplayMetrics().density;
            if (this.l != null && this.l.booleanValue()) {
                this.m.setOutlineProvider(new ViewOutlineProvider() { // from class: com.tapjoy.TJSplitWebView.8
                    @Override // android.view.ViewOutlineProvider
                    public final void getOutline(View view, Outline outline) {
                        outline.setRoundRect(0, 0, view.getWidth(), (int) (view.getHeight() + f), f);
                    }
                });
                this.m.setClipToOutline(true);
                return;
            }
            Arrays.fill(fArr, f);
            ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, null, null));
            shapeDrawable.getPaint().setColor(-1);
            this.a.setBackground(shapeDrawable);
            this.a.setClipToOutline(true);
        }
    }

    protected final void a() {
        this.j.dismissSplitView(null, null);
    }

    @TargetApi(21)
    public void addLineBreak() {
        this.n = new FrameLayout(getContext());
        this.n.setBackgroundColor(Color.parseColor("#dddddd"));
        addView(this.n);
    }

    @TargetApi(21)
    public void addProgressBar() {
        this.o = new ProgressBar(this.k, null, R.attr.progressBarStyleHorizontal);
        this.o.setMax(100);
        this.o.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#5d95ff")));
        this.o.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#dddddd")));
        addView(this.o);
    }

    @TargetApi(21)
    public void addToolbar() {
        this.m = new RelativeLayout(this.k);
        this.m.setId(TapjoyUtil.generateViewId());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (40.0f * new TapjoyDisplayMetricsUtil(getContext()).getScreenDensityScale()));
        layoutParams.addRule(6);
        this.m.setBackgroundColor(-1);
        this.m.setVisibility(0);
        setupToolbarUI();
        addView(this.m, layoutParams);
    }

    public void animateOpen(ViewGroup viewGroup) {
        if (this.i != null && this.i.has(TJAdUnitConstants.String.ANIMATION_EVENT_ON_OPEN) && this.i.optString(TJAdUnitConstants.String.ANIMATION_EVENT_ON_OPEN).equalsIgnoreCase(TJAdUnitConstants.String.ANIMATION_TYPE_SLIDE_UP)) {
            setY(viewGroup.getHeight());
            animate().translationY(0.0f);
        }
    }

    public void applyLayoutOption(JSONObject jSONObject) {
        setLayoutOption(jSONObject);
        a(getWidth(), getHeight());
    }

    public String getLastUrl() {
        return this.e;
    }

    public boolean goBack() {
        if (!this.a.canGoBack()) {
            return false;
        }
        this.a.goBack();
        return true;
    }

    public void isFirstOrLastPage() {
        this.q.setEnabled(this.a.canGoBack());
        this.r.setEnabled(this.a.canGoForward());
    }

    public void loadUrl(String str) {
        if (this.a != null) {
            this.d = str;
            this.e = str;
            this.a.loadUrl(str);
        }
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        a(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        super.onMeasure(i, i2);
    }

    public void openInExternalBrowser() {
        Uri uri;
        if (jq.c(this.s)) {
            uri = Uri.parse(this.a.getUrl());
            if (uri == null) {
                uri = Uri.parse(getLastUrl());
            }
        } else {
            uri = Uri.parse(this.s);
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(uri);
        intent.addFlags(268435456);
        if (this.a.getContext() != null) {
            try {
                this.a.getContext().startActivity(intent);
            } catch (Exception e) {
                TapjoyLog.d("TJSplitWebView", e.getMessage());
            }
        }
    }

    public void setErrorDialog(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.t = true;
            this.u = jSONObject.optString("description");
            this.v = jSONObject.optString(TJAdUnitConstants.String.CLOSE);
            this.w = jSONObject.optString("reload");
        }
    }

    public void setExitHosts(JSONArray jSONArray) {
        if (jSONArray == null) {
            this.h = null;
            return;
        }
        this.h = new HashSet();
        for (int i = 0; i <= jSONArray.length(); i++) {
            String strOptString = jSONArray.optString(i);
            if (strOptString != null) {
                this.h.add(strOptString);
            }
        }
    }

    protected void setLayoutOption(JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(TJAdUnitConstants.String.LANDSCAPE);
            this.c = jSONObjectOptJSONObject != null ? new a(jSONObjectOptJSONObject) : null;
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject(TJAdUnitConstants.String.PORTRAIT);
            this.b = jSONObjectOptJSONObject2 != null ? new a(jSONObjectOptJSONObject2) : null;
        }
    }

    public void setTrigger(@Nullable String str, @Nullable String str2) {
        this.f = jq.b(str);
        this.g = str2 != null ? Uri.parse(str2) : null;
    }

    public void setUserAgent(String str) {
        this.a.getSettings().setUserAgentString(str);
    }

    @TargetApi(21)
    public void setupToolbarUI() {
        float screenDensityScale = new TapjoyDisplayMetricsUtil(getContext()).getScreenDensityScale();
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        this.q = new TJImageButton(this.k);
        this.q.setId(TapjoyUtil.generateViewId());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(9);
        layoutParams.setMargins((int) (10.0f * screenDensityScale), (int) (10.0f * screenDensityScale), (int) (10.0f * screenDensityScale), (int) (10.0f * screenDensityScale));
        this.q.setPadding((int) (5.0f * screenDensityScale), (int) (10.0f * screenDensityScale), (int) (10.0f * screenDensityScale), (int) (10.0f * screenDensityScale));
        this.q.setEnabledImageBitmap(TapjoyIcons.getBackEnabledImage(screenDensityScale));
        this.q.setDisableImageBitmap(TapjoyIcons.getBackDisabledImage(screenDensityScale));
        this.q.setBackgroundColor(0);
        this.q.setOnClickListener(new View.OnClickListener() { // from class: com.tapjoy.TJSplitWebView.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (TJSplitWebView.this.a.canGoBack()) {
                    TJSplitWebView.this.a.goBack();
                }
            }
        });
        relativeLayout.addView(this.q, layoutParams);
        this.r = new TJImageButton(this.k);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        layoutParams2.addRule(1, this.q.getId());
        layoutParams2.setMargins((int) (10.0f * screenDensityScale), (int) (10.0f * screenDensityScale), (int) (10.0f * screenDensityScale), (int) (10.0f * screenDensityScale));
        this.r.setPadding((int) (10.0f * screenDensityScale), (int) (10.0f * screenDensityScale), (int) (5.0f * screenDensityScale), (int) (10.0f * screenDensityScale));
        this.r.setEnabledImageBitmap(TapjoyIcons.getForwardEnabledImage(screenDensityScale));
        this.r.setDisableImageBitmap(TapjoyIcons.getForwardDisabledImage(screenDensityScale));
        this.r.setBackgroundColor(0);
        this.r.setOnClickListener(new View.OnClickListener() { // from class: com.tapjoy.TJSplitWebView.3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TJSplitWebView.this.a.goForward();
            }
        });
        relativeLayout.addView(this.r, layoutParams2);
        ImageButton imageButton = new ImageButton(this.k);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(15);
        layoutParams3.addRule(11);
        layoutParams3.setMargins((int) (10.0f * screenDensityScale), (int) (10.0f * screenDensityScale), (int) (10.0f * screenDensityScale), (int) (10.0f * screenDensityScale));
        imageButton.setPadding((int) (5.0f * screenDensityScale), (int) (5.0f * screenDensityScale), (int) (5.0f * screenDensityScale), (int) (5.0f * screenDensityScale));
        imageButton.setImageBitmap(TapjoyIcons.getCloseImage(screenDensityScale));
        imageButton.setBackgroundColor(0);
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.tapjoy.TJSplitWebView.4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TJSplitWebView.this.a();
            }
        });
        relativeLayout.addView(imageButton, layoutParams3);
        this.p = new TextView(this.k);
        this.p.setId(TapjoyUtil.generateViewId());
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(13);
        this.p.setMaxLines(1);
        this.p.setMaxEms(200);
        this.p.setTextAlignment(4);
        this.p.setTextColor(Color.parseColor("#5d95ff"));
        this.p.setBackgroundColor(0);
        this.p.setEnabled(false);
        this.p.setTypeface(Typeface.create("sans-serif-medium", 0));
        relativeLayout.addView(this.p, layoutParams4);
        ImageButton imageButton2 = new ImageButton(this.k);
        imageButton2.setId(TapjoyUtil.generateViewId());
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(1, this.p.getId());
        layoutParams5.addRule(15);
        imageButton2.setPadding((int) (5.0f * screenDensityScale), (int) (5.0f * screenDensityScale), (int) (5.0f * screenDensityScale), (int) (5.0f * screenDensityScale));
        imageButton2.setImageBitmap(TapjoyIcons.getOpenBrowserImage(screenDensityScale));
        imageButton2.setBackgroundColor(0);
        imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.tapjoy.TJSplitWebView.5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TJSplitWebView.this.openInExternalBrowser();
            }
        });
        relativeLayout.addView(imageButton2, layoutParams5);
        this.m.addView(relativeLayout, new RelativeLayout.LayoutParams(-1, -2));
    }

    @TargetApi(21)
    public void showErrorDialog() {
        new AlertDialog.Builder(this.k, R.style.Theme.Material.Light.Dialog.Alert).setMessage(this.u).setPositiveButton(this.v, new DialogInterface.OnClickListener() { // from class: com.tapjoy.TJSplitWebView.7
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).setNegativeButton(this.w, new DialogInterface.OnClickListener() { // from class: com.tapjoy.TJSplitWebView.6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                if (jq.c(TJSplitWebView.this.getLastUrl())) {
                    TJSplitWebView.this.loadUrl(TJSplitWebView.this.d);
                } else {
                    TJSplitWebView.this.loadUrl(TJSplitWebView.this.getLastUrl());
                }
                dialogInterface.cancel();
            }
        }).create().show();
    }
}
