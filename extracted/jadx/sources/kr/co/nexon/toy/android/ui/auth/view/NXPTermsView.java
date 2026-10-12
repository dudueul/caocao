package kr.co.nexon.toy.android.ui.auth.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nexon.core.locale.NXLocale;
import com.nexon.core.log.ToyLog;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.toy.android.ui.common.NPScrollView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPTermsView extends NXPLinearLayout {
    private ImageButton closeButton;
    private View.OnClickListener closeButtonClickListener;
    private TextView descriptionTextView;
    private Button leftButton;
    private NXClickListener onSwallowClickListener;
    private AppCompatButton rightButton;
    private NXPTermsAgreementButtonClickListener termsAgreementButtonClickListener;
    private NXPTermsAgreementStateChangeListener termsAgreementStateChangeListener;
    private List<NXPTermsItem> termsItemList;
    private NXPTermsViewContentsButtonClickListener termsViewContentsButtonClickListener;
    private TextView titleTextView;

    public interface NXPTermsAgreementButtonClickListener {
        void onLeftButtonClick();

        void onRightButtonClick();
    }

    public interface NXPTermsAgreementStateChangeListener {
        void onCheckBoxClick(NXToyTerm nXToyTerm, NXPTermsItem nXPTermsItem);
    }

    public interface NXPTermsViewContentsButtonClickListener {
        void onShowTerms(String str, int i);
    }

    public NXPTermsView(Context context) {
        super(context);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.view.NXPTermsView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPTermsView.this.termsAgreementButtonClickListener == null) {
                    ToyLog.d("NXPTermsAgreementButtonClickListener is null");
                    return;
                }
                int id = view.getId();
                if (id == R.id.npterms_agree_all) {
                    NXPTermsView.this.termsAgreementButtonClickListener.onRightButtonClick();
                    return;
                }
                if (id == R.id.npterms_agree_and_start) {
                    NXPTermsView.this.termsAgreementButtonClickListener.onLeftButtonClick();
                } else if (id == R.id.closeBtn) {
                    if (NXPTermsView.this.closeButtonClickListener == null) {
                        ToyLog.d("CloseButtonClickListener is null");
                    } else {
                        NXPTermsView.this.closeButtonClickListener.onClick(view);
                    }
                }
            }
        };
    }

    public NXPTermsView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.view.NXPTermsView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPTermsView.this.termsAgreementButtonClickListener == null) {
                    ToyLog.d("NXPTermsAgreementButtonClickListener is null");
                    return;
                }
                int id = view.getId();
                if (id == R.id.npterms_agree_all) {
                    NXPTermsView.this.termsAgreementButtonClickListener.onRightButtonClick();
                    return;
                }
                if (id == R.id.npterms_agree_and_start) {
                    NXPTermsView.this.termsAgreementButtonClickListener.onLeftButtonClick();
                } else if (id == R.id.closeBtn) {
                    if (NXPTermsView.this.closeButtonClickListener == null) {
                        ToyLog.d("CloseButtonClickListener is null");
                    } else {
                        NXPTermsView.this.closeButtonClickListener.onClick(view);
                    }
                }
            }
        };
    }

    public NXPTermsView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.view.NXPTermsView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPTermsView.this.termsAgreementButtonClickListener == null) {
                    ToyLog.d("NXPTermsAgreementButtonClickListener is null");
                    return;
                }
                int id = view.getId();
                if (id == R.id.npterms_agree_all) {
                    NXPTermsView.this.termsAgreementButtonClickListener.onRightButtonClick();
                    return;
                }
                if (id == R.id.npterms_agree_and_start) {
                    NXPTermsView.this.termsAgreementButtonClickListener.onLeftButtonClick();
                } else if (id == R.id.closeBtn) {
                    if (NXPTermsView.this.closeButtonClickListener == null) {
                        ToyLog.d("CloseButtonClickListener is null");
                    } else {
                        NXPTermsView.this.closeButtonClickListener.onClick(view);
                    }
                }
            }
        };
    }

    private NXPTermsItem createTermsItem(final NXToyTerm nXToyTerm, NXLocale.COUNTRY country) {
        final NXPTermsItem nXPTermsItem = (NXPTermsItem) View.inflate(getContext(), R.layout.nxp_terms_view_item, null);
        String str = "";
        if (country == NXLocale.COUNTRY.Korea) {
            str = nXToyTerm.optional == 1 ? String.format("(%s) ", getContext().getResources().getString(R.string.npterms_optional)) : String.format("(%s) ", getContext().getResources().getString(R.string.npterms_required));
        }
        nXPTermsItem.setTermsTitle(str + nXToyTerm.title);
        nXPTermsItem.setTag(nXToyTerm);
        nXPTermsItem.setOnCheckBoxClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.view.NXPTermsView.2
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPTermsView.this.termsAgreementStateChangeListener == null) {
                    return;
                }
                NXPTermsView.this.termsAgreementStateChangeListener.onCheckBoxClick(nXToyTerm, nXPTermsItem);
            }
        });
        nXPTermsItem.setOnTermsViewButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.auth.view.NXPTermsView.3
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (NXPTermsView.this.termsViewContentsButtonClickListener == null) {
                    return;
                }
                NXPTermsView.this.termsViewContentsButtonClickListener.onShowTerms(nXToyTerm.title, nXToyTerm.termID);
            }
        });
        return nXPTermsItem;
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    @TargetApi(26)
    protected void initView() {
        findViewById(R.id.backBtn).setVisibility(4);
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.titleTextView.setMaxLines(2);
        this.descriptionTextView = (TextView) findViewById(R.id.terms_description);
        this.rightButton = (AppCompatButton) findViewById(R.id.npterms_agree_all);
        this.rightButton.setOnClickListener(this.onSwallowClickListener);
        this.rightButton.setAutoSizeTextTypeUniformWithConfiguration(3, 14, 1, 2);
        this.leftButton = (Button) findViewById(R.id.npterms_agree_and_start);
        this.leftButton.setOnClickListener(this.onSwallowClickListener);
        this.closeButton = (ImageButton) findViewById(R.id.closeBtn);
        this.closeButton.setOnClickListener(this.onSwallowClickListener);
        this.termsItemList = new ArrayList();
    }

    public void setAllCheckBoxBackgroundResource(int i) {
        Iterator<NXPTermsItem> it = this.termsItemList.iterator();
        while (it.hasNext()) {
            it.next().setCheckBoxBackgroundResource(i);
        }
    }

    public void setCloseButtonVisibility(int i) {
        if (this.closeButton != null) {
            this.closeButton.setVisibility(i);
        }
    }

    public void setDescriptionTextView(String str) {
        if (this.descriptionTextView != null) {
            this.descriptionTextView.setText(str);
        }
    }

    public void setLeftButtonEnabled(boolean z) {
        if (this.leftButton != null) {
            this.leftButton.setEnabled(z);
        }
    }

    public void setLeftButtonText(String str) {
        if (this.leftButton != null) {
            this.leftButton.setText(str);
        }
    }

    public void setOnCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.closeButtonClickListener = onClickListener;
    }

    public void setRightButtonEnabled(boolean z) {
        if (this.rightButton != null) {
            this.rightButton.setEnabled(z);
        }
    }

    public void setRightButtonText(String str) {
        if (this.rightButton != null) {
            this.rightButton.setText(str);
        }
    }

    public void setTermsAgreementButtonClickListener(NXPTermsAgreementButtonClickListener nXPTermsAgreementButtonClickListener) {
        this.termsAgreementButtonClickListener = nXPTermsAgreementButtonClickListener;
    }

    public void setTermsAgreementStateChangeListener(NXPTermsAgreementStateChangeListener nXPTermsAgreementStateChangeListener) {
        this.termsAgreementStateChangeListener = nXPTermsAgreementStateChangeListener;
    }

    public void setTermsList(List<NXToyTerm> list, NXLocale.COUNTRY country) {
        ToyLog.d("setTermsList :" + list);
        if (list == null || list.isEmpty()) {
            ToyLog.d("setTermsList is null");
            return;
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.npterms_terms);
        Iterator<NXToyTerm> it = list.iterator();
        while (it.hasNext()) {
            NXPTermsItem nXPTermsItemCreateTermsItem = createTermsItem(it.next(), country);
            linearLayout.addView(nXPTermsItemCreateTermsItem);
            this.termsItemList.add(nXPTermsItemCreateTermsItem);
        }
        NPScrollView nPScrollView = (NPScrollView) findViewById(R.id.terms_list_scrollview);
        if (nPScrollView != null) {
            nPScrollView.setScrollEnabled(true);
        }
        findViewById(R.id.terms_layout).setVisibility(0);
    }

    public void setTermsViewContentButtonClickListener(NXPTermsViewContentsButtonClickListener nXPTermsViewContentsButtonClickListener) {
        this.termsViewContentsButtonClickListener = nXPTermsViewContentsButtonClickListener;
    }

    public void setTitleText(String str) {
        if (this.titleTextView != null) {
            this.titleTextView.setText(str);
        }
    }
}
