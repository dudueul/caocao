package kr.co.nexon.toy.android.ui.baseplate.view.adapter.model;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAdInformationItem {
    public static final int ITEM_ORDER_AD_PUSH = 1;
    public static final int ITEM_ORDER_EMAIL_COLLECTION = 4;
    public static final int ITEM_ORDER_NIGHT_PUSH = 2;
    public static final int ITEM_ORDER_PHONE_NUMBER_COLLECTION = 3;
    private int adInformationViewOrder;
    private boolean enabled = true;
    private boolean isChecked;
    private String title;

    public NXPAdInformationItem(int i, String str, boolean z) {
        this.adInformationViewOrder = i;
        this.title = str;
        this.isChecked = z;
    }

    public int getAdInformationViewOrder() {
        return this.adInformationViewOrder;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }
}
