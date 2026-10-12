package kr.co.nexon.toy.android.ui.baseplate.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.auth.result.model.NXToyBasePlate;
import kr.co.nexon.npaccount.auth.result.model.NXToyPlateInfo;
import kr.co.nexon.toy.android.ui.baseplate.NPPlateCodes;
import kr.co.nexon.toy.android.ui.common.NPScrollView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPBaseplateView extends NXPLinearLayout {
    private GridLayout baseplateContanier;
    private View.OnClickListener closeButtonClickListener;
    private BaseplateItemClickListener itemClickListener;
    private NXClickListener onSwallowClickListener;
    private NPScrollView scrollview;
    private TextView titleTextView;

    public interface BaseplateItemClickListener {
        void onItemClick(NXToyPlateInfo nXToyPlateInfo);
    }

    public NXPBaseplateView(Context context) {
        super(context);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.view.NXPBaseplateView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (view.getId() == R.id.closeBtn) {
                    if (NXPBaseplateView.this.closeButtonClickListener != null) {
                        NXPBaseplateView.this.closeButtonClickListener.onClick(view);
                    }
                } else if (NXPBaseplateView.this.itemClickListener != null) {
                    Object tag = view.getTag();
                    ToyLog.d("NXPBasePlateItem tag info:" + tag);
                    if (tag instanceof NXToyPlateInfo) {
                        NXPBaseplateView.this.itemClickListener.onItemClick((NXToyPlateInfo) tag);
                    }
                }
            }
        };
    }

    public NXPBaseplateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.view.NXPBaseplateView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (view.getId() == R.id.closeBtn) {
                    if (NXPBaseplateView.this.closeButtonClickListener != null) {
                        NXPBaseplateView.this.closeButtonClickListener.onClick(view);
                    }
                } else if (NXPBaseplateView.this.itemClickListener != null) {
                    Object tag = view.getTag();
                    ToyLog.d("NXPBasePlateItem tag info:" + tag);
                    if (tag instanceof NXToyPlateInfo) {
                        NXPBaseplateView.this.itemClickListener.onItemClick((NXToyPlateInfo) tag);
                    }
                }
            }
        };
    }

    public NXPBaseplateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.onSwallowClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.view.NXPBaseplateView.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                if (view.getId() == R.id.closeBtn) {
                    if (NXPBaseplateView.this.closeButtonClickListener != null) {
                        NXPBaseplateView.this.closeButtonClickListener.onClick(view);
                    }
                } else if (NXPBaseplateView.this.itemClickListener != null) {
                    Object tag = view.getTag();
                    ToyLog.d("NXPBasePlateItem tag info:" + tag);
                    if (tag instanceof NXToyPlateInfo) {
                        NXPBaseplateView.this.itemClickListener.onItemClick((NXToyPlateInfo) tag);
                    }
                }
            }
        };
    }

    private void adjustBorderLine(NXPBasePlateItem nXPBasePlateItem, int i, int i2) {
        if (i2 == 0) {
            nXPBasePlateItem.setBackgroundResource(R.drawable.baseplate_item_type1_selector);
            return;
        }
        if (i2 < i) {
            nXPBasePlateItem.setBackgroundResource(R.drawable.baseplate_item_type2_selector);
        } else if (i2 % i == 0) {
            nXPBasePlateItem.setBackgroundResource(R.drawable.baseplate_item_type3_selector);
        } else {
            nXPBasePlateItem.setBackgroundResource(R.drawable.baseplate_item_type4_selector);
        }
    }

    private int getBaseplateItemResouceIdByCode(String str) {
        if (NPPlateCodes.CODE_NOTICE.equalsIgnoreCase(str)) {
            return R.drawable.icon_baseplate_notice;
        }
        if (NPPlateCodes.CODE_FAQ.equalsIgnoreCase(str)) {
            return R.drawable.icon_baseplate_faq;
        }
        if ("terms".equalsIgnoreCase(str)) {
            return R.drawable.icon_baseplate_terms;
        }
        if (NPPlateCodes.CODE_GAMEINFO.equalsIgnoreCase(str)) {
            return R.drawable.icon_baseplate_grade;
        }
        if (NPPlateCodes.CODE_ACCOUNT.equalsIgnoreCase(str)) {
            return R.drawable.icon_baseplate_account;
        }
        if (NPPlateCodes.CODE_CUSTOMER_CENTER.equalsIgnoreCase(str)) {
            return R.drawable.icon_baseplate_support;
        }
        if (NPPlateCodes.CODE_FORUM.equalsIgnoreCase(str)) {
            return R.drawable.icon_baseplate_forum;
        }
        if (!"url".equalsIgnoreCase(str) && !NPPlateCodes.CODE_URL_EXTERN.equalsIgnoreCase(str)) {
            if (NPPlateCodes.CODE_META.equalsIgnoreCase(str)) {
                return R.drawable.icon_baseplate_meta;
            }
            if (NPPlateCodes.CODE_DATA_BACKUP.equalsIgnoreCase(str)) {
                return R.drawable.icon_baseplate_backup;
            }
            if (NPPlateCodes.CODE_DATA_RESTORE.equalsIgnoreCase(str)) {
                return R.drawable.icon_baseplate_restore;
            }
            if (NPPlateCodes.CODE_PUSH_SETTING.equalsIgnoreCase(str)) {
                return R.drawable.icon_baseplate_push;
            }
            return NPPlateCodes.CODE_TODAY.equalsIgnoreCase(str) ? R.drawable.icon_baseplate_today : R.drawable.icon_baseplate_meta;
        }
        return R.drawable.icon_baseplate_link;
    }

    @Nullable
    public NXPBasePlateItem createBasePlateItem(NXToyPlateInfo nXToyPlateInfo) {
        NXPBasePlateItem nXPBasePlateItem = (NXPBasePlateItem) View.inflate(getContext(), R.layout.nxp_baseplate_view_item, null);
        nXPBasePlateItem.setTitle(nXToyPlateInfo.title);
        nXPBasePlateItem.setIconResource(getBaseplateItemResouceIdByCode(nXToyPlateInfo.code));
        nXPBasePlateItem.setOnClickListener(this.onSwallowClickListener);
        nXPBasePlateItem.setTag(nXToyPlateInfo);
        return nXPBasePlateItem;
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        this.baseplateContanier = (GridLayout) findViewById(R.id.baseplate_container);
        this.scrollview = (NPScrollView) findViewById(R.id.baseplate_scrollview);
        this.titleTextView = (TextView) findViewById(R.id.title);
        View viewFindViewById = findViewById(R.id.backBtn);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(4);
        }
        View viewFindViewById2 = findViewById(R.id.closeBtn);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(this.onSwallowClickListener);
        }
    }

    public void setBaseplateInfo(@NonNull NXToyBasePlate nXToyBasePlate) {
        if (nXToyBasePlate.items == null) {
            return;
        }
        int columnCount = this.baseplateContanier.getColumnCount();
        int size = nXToyBasePlate.items.size();
        ToyLog.d("columnCount:" + columnCount + " , basePlateItem count:" + size);
        for (int i = 0; i < size; i++) {
            NXPBasePlateItem nXPBasePlateItemCreateBasePlateItem = createBasePlateItem(nXToyBasePlate.items.get(i));
            this.baseplateContanier.addView(nXPBasePlateItemCreateBasePlateItem);
            adjustBorderLine(nXPBasePlateItemCreateBasePlateItem, columnCount, i);
        }
    }

    public void setItemClickListener(BaseplateItemClickListener baseplateItemClickListener) {
        this.itemClickListener = baseplateItemClickListener;
    }

    public void setOnCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.closeButtonClickListener = onClickListener;
    }

    public void setScrollEnabled(boolean z) {
        if (this.scrollview != null) {
            this.scrollview.setScrollEnabled(z);
        }
    }

    public void setTitle(String str) {
        if (this.titleTextView != null) {
            this.titleTextView.setText(str);
        }
    }
}
