package kr.co.nexon.toy.android.ui.banner.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;
import com.nexon.npaccount.R;
import java.util.Arrays;
import java.util.List;
import kr.co.nexon.mdev.android.view.NXPRelativeLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.toy.listener.NXPWebViewClientInterface;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPBannerView extends NXPRelativeLayout implements NXPWebViewClientInterface {
    public static final int CLICK_BUTTON = 0;
    public static final int CLICK_IMAGE = 1;
    private static final String ENCODING_TYPE = "UTF8";
    private static final String MIME_TYPE = "text/html";
    private static final String formattedHtml = "<html><head><meta name=\"viewport\" content=\"initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0\"></head><body style='margin:0;padding:0; background-color:#454545;'><script>function c(){document.location.href='npbanner://clickimg';}</script><style type='text/css'>img { width: 100%% ; height: auto; }</style><img onclick='c()' src=\"%s\"/></body></html>";
    private NXPBannerClickListener bannerClickListener;
    private int bannerKey;
    private WebView bannerWebView;
    private NXPBannerCheckBoxChangeListener checkBoxChangeListener;
    private View.OnClickListener closeButtonClickListener;
    private CheckBox dontShowTodayCheckBox;
    private TextView dontshowTodayText;
    private NXClickListener onSwallowClickListener;
    private ProgressBar progressBar;
    private RelativeLayout webviewContainer;
    private View workOnButtonLayout;
    private TextView workOnButtonTextView;

    public interface NXPBannerCheckBoxChangeListener {
        void onChangeCheckBox(boolean z);
    }

    public interface NXPBannerClickListener {
        void onClick(int i, int i2);
    }

    public NXPBannerView(Context context) {
        super(context);
        this.bannerKey = -1017;
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.banner.view.NXPBannerView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.closeBtn) {
                    if (NXPBannerView.this.closeButtonClickListener != null) {
                        NXPBannerView.this.closeButtonClickListener.onClick(view);
                    }
                } else if (id == R.id.bannerWorkOnButtonLayout) {
                    if (NXPBannerView.this.bannerClickListener != null) {
                        NXPBannerView.this.bannerClickListener.onClick(NXPBannerView.this.bannerKey, 0);
                    }
                } else if (id == R.id.dontShowTodayLayout) {
                    NXPBannerView.this.toggleCheckBox();
                }
            }
        };
    }

    public NXPBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.bannerKey = -1017;
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.banner.view.NXPBannerView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.closeBtn) {
                    if (NXPBannerView.this.closeButtonClickListener != null) {
                        NXPBannerView.this.closeButtonClickListener.onClick(view);
                    }
                } else if (id == R.id.bannerWorkOnButtonLayout) {
                    if (NXPBannerView.this.bannerClickListener != null) {
                        NXPBannerView.this.bannerClickListener.onClick(NXPBannerView.this.bannerKey, 0);
                    }
                } else if (id == R.id.dontShowTodayLayout) {
                    NXPBannerView.this.toggleCheckBox();
                }
            }
        };
    }

    public NXPBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.bannerKey = -1017;
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.banner.view.NXPBannerView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                int id = view.getId();
                if (id == R.id.closeBtn) {
                    if (NXPBannerView.this.closeButtonClickListener != null) {
                        NXPBannerView.this.closeButtonClickListener.onClick(view);
                    }
                } else if (id == R.id.bannerWorkOnButtonLayout) {
                    if (NXPBannerView.this.bannerClickListener != null) {
                        NXPBannerView.this.bannerClickListener.onClick(NXPBannerView.this.bannerKey, 0);
                    }
                } else if (id == R.id.dontShowTodayLayout) {
                    NXPBannerView.this.toggleCheckBox();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleCheckBox() {
        if (this.dontShowTodayCheckBox != null) {
            boolean z = !this.dontShowTodayCheckBox.isChecked();
            this.dontShowTodayCheckBox.setChecked(z);
            if (this.checkBoxChangeListener != null) {
                this.checkBoxChangeListener.onChangeCheckBox(z);
            }
        }
    }

    public void addBannerWebView(WebView webView) {
        if (webView == null || this.webviewContainer == null) {
            return;
        }
        this.bannerWebView = webView;
        this.webviewContainer.addView(this.bannerWebView, new RelativeLayout.LayoutParams(-1, -1));
    }

    public List<Integer> getBackupResouceIdList() {
        return Arrays.asList(Integer.valueOf(R.id.bannerProgressBar), Integer.valueOf(R.id.dontShowTodayCheckBox), Integer.valueOf(R.id.dontShowTodayText), Integer.valueOf(R.id.bannerWorkOnButtonTextView), Integer.valueOf(R.id.bannerWorkOnButtonLayout));
    }

    public CharSequence getWorkOnButtonText() {
        return this.workOnButtonTextView == null ? "" : this.workOnButtonTextView.getText();
    }

    @Override // kr.co.nexon.mdev.android.view.NXPRelativeLayout
    protected void initView() {
        this.webviewContainer = (RelativeLayout) findViewById(R.id.bannerWebviewContainer);
        this.progressBar = (ProgressBar) findViewById(R.id.bannerProgressBar);
        this.dontShowTodayCheckBox = (CheckBox) findViewById(R.id.dontShowTodayCheckBox);
        this.dontshowTodayText = (TextView) findViewById(R.id.dontShowTodayText);
        this.workOnButtonTextView = (TextView) findViewById(R.id.bannerWorkOnButtonTextView);
        this.workOnButtonLayout = findViewById(R.id.bannerWorkOnButtonLayout);
        if (this.workOnButtonLayout != null) {
            this.workOnButtonLayout.setOnClickListener(this.onSwallowClickListener);
        }
        View viewFindViewById = findViewById(R.id.closeBtn);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(this.onSwallowClickListener);
        }
        View viewFindViewById2 = findViewById(R.id.dontShowTodayLayout);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(this.onSwallowClickListener);
        }
        this.onSwallowClickListener.setClickEventDelayTime(300L);
    }

    public boolean isCheckedDontShowToday() {
        if (this.dontShowTodayCheckBox != null) {
            return this.dontShowTodayCheckBox.isChecked();
        }
        ToyLog.d("dontShowTodyCheckBox is null.");
        return false;
    }

    public void loadUrl(String str, int i) {
        if (this.bannerWebView == null) {
            ToyLog.d("In loadBanner, bannerWebView is null");
        } else {
            this.bannerKey = i;
            this.bannerWebView.loadDataWithBaseURL(null, String.format(formattedHtml, str), MIME_TYPE, "UTF8", null);
        }
    }

    @Override // kr.co.nexon.toy.listener.NXPWebViewClientInterface
    public void onPageFinished(WebView webView, String str) {
        if (this.progressBar != null) {
            this.progressBar.setVisibility(8);
        }
    }

    @Override // kr.co.nexon.toy.listener.NXPWebViewClientInterface
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.progressBar != null) {
            this.progressBar.setVisibility(0);
        }
    }

    @Override // kr.co.nexon.toy.listener.NXPWebViewClientInterface
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        ToyLog.e("onReceivedError, errorCode " + i + " description " + str + " url " + str2);
    }

    @Nullable
    public WebView removeBannerWebView() {
        if (this.webviewContainer == null) {
            return null;
        }
        this.webviewContainer.removeView(this.bannerWebView);
        WebView webView = this.bannerWebView;
        this.bannerWebView = null;
        return webView;
    }

    public void setBannerKey(int i) {
        this.bannerKey = i;
    }

    public void setCheckBoxButtonText(CharSequence charSequence) {
        if (this.dontshowTodayText != null) {
            this.dontshowTodayText.setText(charSequence);
        }
    }

    public void setChecked(boolean z) {
        if (this.dontShowTodayCheckBox != null) {
            this.dontShowTodayCheckBox.setChecked(z);
        }
    }

    public void setOnBannerClickListener(NXPBannerClickListener nXPBannerClickListener) {
        this.bannerClickListener = nXPBannerClickListener;
    }

    public void setOnCheckBoxChangeListener(NXPBannerCheckBoxChangeListener nXPBannerCheckBoxChangeListener) {
        this.checkBoxChangeListener = nXPBannerCheckBoxChangeListener;
    }

    public void setOnCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.closeButtonClickListener = onClickListener;
    }

    @Override // android.view.View
    public void setTag(Object obj) {
        super.setTag(obj);
    }

    public void setWorkOnButtonText(CharSequence charSequence) {
        if (this.workOnButtonTextView != null) {
            this.workOnButtonTextView.setText(charSequence);
        }
    }

    public void setWorkOnButtonVisibility(int i) {
        if (this.workOnButtonLayout == null) {
            return;
        }
        this.workOnButtonLayout.setVisibility(i);
    }

    @Override // kr.co.nexon.toy.listener.NXPWebViewClientInterface
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!str.startsWith("npbanner://clickimg")) {
            return false;
        }
        if (this.bannerClickListener == null) {
            return true;
        }
        this.bannerClickListener.onClick(this.bannerKey, 1);
        return true;
    }

    public void showProgrees() {
        if (this.progressBar != null) {
            this.progressBar.setVisibility(0);
        }
    }

    public void stopProgress() {
        if (this.progressBar != null) {
            this.progressBar.setVisibility(8);
        }
    }
}
