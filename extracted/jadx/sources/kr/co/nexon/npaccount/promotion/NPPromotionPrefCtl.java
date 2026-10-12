package kr.co.nexon.npaccount.promotion;

import android.content.Context;
import android.content.SharedPreferences;
import com.nexon.platform.NXPFinalizer;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPPromotionPrefCtl {
    public static final String PREF_NAME = "promotion_pref";
    private SharedPreferences.Editor editor;
    private Context mContext;
    private SharedPreferences pref;
    private static volatile NPPromotionPrefCtl instance = null;
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: kr.co.nexon.npaccount.promotion.NPPromotionPrefCtl.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            NPPromotionPrefCtl unused = NPPromotionPrefCtl.instance = null;
        }
    };

    private NPPromotionPrefCtl(Context context) {
        this.mContext = context;
    }

    private SharedPreferences.Editor getEditor() {
        if (this.editor == null) {
            this.editor = getPref().edit();
        }
        return this.editor;
    }

    public static NPPromotionPrefCtl getInstance(Context context) {
        if (instance == null) {
            synchronized (NPPromotionPrefCtl.class) {
                if (instance == null) {
                    instance = new NPPromotionPrefCtl(context);
                }
            }
        }
        return instance;
    }

    private SharedPreferences getPref() {
        if (this.pref == null) {
            this.pref = this.mContext.getSharedPreferences(PREF_NAME, 0);
        }
        return this.pref;
    }

    public String getBannerFrequencyDisplayedDate(int i) {
        return getPref().getString("banner_frequency_displayed_date_" + i, "");
    }

    public String getBannerTodayDisabledDate(int i) {
        return getPref().getString("banner_today_disabled_" + i, "");
    }

    public int getBannerTotalDisplayedCount(int i) {
        return getPref().getInt("banner_total_displayed_count_" + i, 0);
    }

    public void incrementBannerTotalDisplayedCount(int i) {
        setBannerTotalDisplayedCount(i, getBannerTotalDisplayedCount(i) + 1);
    }

    public void removeAll() {
        getEditor().clear().commit();
    }

    public void setBannerFrequencyDisplayedDate(int i, String str) {
        getEditor().putString("banner_frequency_displayed_date_" + i, str).commit();
    }

    public void setBannerTodayDisabledDate(int i, String str) {
        getEditor().putString("banner_today_disabled_" + i, str).commit();
    }

    public void setBannerTotalDisplayedCount(int i, int i2) {
        getEditor().putInt("banner_total_displayed_count_" + i, i2).commit();
    }
}
