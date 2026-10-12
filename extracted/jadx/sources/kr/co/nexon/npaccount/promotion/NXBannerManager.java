package kr.co.nexon.npaccount.promotion;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.util.NXDateUtil;
import com.nexon.npaccount.R;
import com.nexon.platform.NXPFinalizer;
import java.util.ArrayList;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.result.model.NXToyBanner;
import kr.co.nexon.npaccount.board.NXBoardManager;
import kr.co.nexon.npaccount.listener.NPBannerListener;
import kr.co.nexon.npaccount.listener.NPEndingBannerListener;
import kr.co.nexon.npaccount.promotion.request.NXToyGetBannerListRequest;
import kr.co.nexon.npaccount.promotion.request.NXToyIncrBannerClickCountRequest;
import kr.co.nexon.npaccount.promotion.result.NXToyBannerListResult;
import kr.co.nexon.toy.android.ui.banner.NPFullBannerDialog;
import kr.co.nexon.toy.android.ui.banner.NXPClosingBannerDialog;
import kr.co.nexon.toy.android.ui.board.NPShowTodayDialog;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXBannerManager {
    public static final String BANNER_GROUPCODE_ENDING_BANNER = "0";
    public static final String BANNER_GROUPCODE_STARTING_BANNER = "1";
    public static final int REQ_BANNER_CLICK_BTN = 1;
    public static final int REQ_BANNER_CLICK_IMG = 0;
    public static final int REQ_ENDINGBANNER_CLICK_BTN = 1;
    public static final int REQ_ENDINGBANNER_CLICK_END = 2;
    public static final int REQ_ENDINGBANNER_CLICK_IMG = 0;
    public static final int REQ_ENDINGBANNER_DISMISS = 3;
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: kr.co.nexon.npaccount.promotion.NXBannerManager.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            NXBannerManager unused = NXBannerManager.instance = null;
        }
    };
    private static NXBannerManager instance;
    private NPBannerListener bannerListener;
    private NXToyCommonPreferenceController commonPrefCtl = NXToyCommonPreferenceController.getInstance();
    private NPEndingBannerListener endingBannerListener;
    private NXToyLocaleManager localeManager;

    private NXBannerManager(Context context) {
        this.localeManager = NXToyLocaleManager.getInstance(context);
    }

    public static NXBannerManager getInstance(Context context) {
        if (instance == null) {
            synchronized (NXBannerManager.class) {
                if (instance == null) {
                    instance = new NXBannerManager(context);
                }
            }
        }
        return instance;
    }

    private void incrBannerClickCount(String str, int i, int i2) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyIncrBannerClickCountRequest(str, i, i2), null);
    }

    public void clickBanner(Activity activity, NPFullBannerDialog nPFullBannerDialog, NXToyBanner nXToyBanner, int i) {
        if (nXToyBanner.landType == 0) {
            try {
                incrBannerClickCount("1", nXToyBanner.sn, i);
                Intent intent = new Intent("android.intent.action.VIEW", parseUriFromUrlString(nXToyBanner.landURL));
                intent.addFlags(268435456);
                activity.startActivity(intent);
                return;
            } catch (Exception e) {
                ToyLog.d("FullBanner uri moving failed. exception:" + e.toString());
                return;
            }
        }
        if (nXToyBanner.landType != 2) {
            if (nXToyBanner.landType == 3) {
                incrBannerClickCount("1", nXToyBanner.sn, i);
                NXBoardManager.getInstance(activity.getApplicationContext()).showWebNoTitle(activity, nXToyBanner.landURL);
                return;
            }
            incrBannerClickCount("1", nXToyBanner.sn, i);
            nPFullBannerDialog.dismiss();
            if (this.bannerListener != null) {
                this.bannerListener.onBannerClick(activity, nXToyBanner.landInfo);
            }
        }
    }

    public void clickClosingBanner(Activity activity, NXPClosingBannerDialog nXPClosingBannerDialog, NXToyBanner nXToyBanner, int i) {
        if (i == 0) {
            incrBannerClickCount("0", nXToyBanner.sn, i);
            if (nXToyBanner.imgLandType != 0) {
                if (nXToyBanner.imgLandType != 2) {
                    nXPClosingBannerDialog.dismiss();
                    this.endingBannerListener.onBannerClick(activity, nXToyBanner.imgLandInfo);
                    return;
                }
                return;
            }
            if (nXToyBanner.imgLandURL != null) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", parseUriFromUrlString(nXToyBanner.imgLandURL));
                    intent.addFlags(268435456);
                    activity.startActivity(intent);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    ToyLog.d("endingBanner image uri moving failed. exception:" + e.toString());
                    return;
                }
            }
            return;
        }
        if (i != 1) {
            if (i == 2) {
                this.endingBannerListener.onExit(activity);
                return;
            } else {
                if (i == 3) {
                    this.endingBannerListener.onDismissBanner();
                    return;
                }
                return;
            }
        }
        incrBannerClickCount("0", nXToyBanner.sn, i);
        if (nXToyBanner.landType != 0) {
            if (nXToyBanner.landType != 2) {
                nXPClosingBannerDialog.dismiss();
                this.endingBannerListener.onBannerClick(activity, nXToyBanner.landInfo);
                return;
            }
            return;
        }
        if (nXToyBanner.landURL != null) {
            try {
                Intent intent2 = new Intent("android.intent.action.VIEW", parseUriFromUrlString(nXToyBanner.landURL));
                intent2.addFlags(268435456);
                activity.startActivity(intent2);
            } catch (Exception e2) {
                e2.printStackTrace();
                ToyLog.d("endingBanner button uri moving failed. exception:" + e2.toString());
            }
        }
    }

    public void dismissBannerDialog(Dialog dialog) {
        dialog.dismiss();
        if (this.bannerListener != null) {
            this.bannerListener.onDismissBanner();
        }
    }

    public void getBanners(int i, final NPListener nPListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetBannerListRequest(i), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.promotion.NXBannerManager.3
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nPListener != null) {
                    nPListener.onResult(nXToyResult);
                }
            }
        });
    }

    public Uri parseUriFromUrlString(String str) {
        if (str != null && !str.isEmpty()) {
            return Uri.parse(str.trim());
        }
        ToyLog.d("In parseUriFromUrlString, url is emtpy string:[" + str + "]");
        return null;
    }

    public void setNotShowDateForBanner(int i, String str) {
        this.commonPrefCtl.setNotShowDateForBanner(i, str);
    }

    public void showBanner(final Activity activity, int i, final NPBannerListener nPBannerListener) {
        this.bannerListener = nPBannerListener;
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetBannerListRequest(i), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.promotion.NXBannerManager.2
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    nPBannerListener.onFailed(nXToyResult);
                    return;
                }
                ArrayList<NXToyBanner> arrayList = new ArrayList(((NXToyBannerListResult) nXToyResult).result.bannerList);
                if (arrayList.size() == 0) {
                    NXToyBannerListResult nXToyBannerListResult = new NXToyBannerListResult(NXToyErrorCode.NOT_REGISTERED_BANNER.getCode(), NXBannerManager.this.localeManager.getString(R.string.npres_banner_not_registered));
                    nXToyBannerListResult.requestTag = NXToyRequestTag.GetBannerList.getValue();
                    nPBannerListener.onFailed(nXToyBannerListResult);
                    return;
                }
                ArrayList arrayList2 = new ArrayList();
                String currentTimeFormat = NXDateUtil.getCurrentTimeFormat(NPShowTodayDialog.KEY_DATE_FORMAT);
                for (NXToyBanner nXToyBanner : arrayList) {
                    if (!NXBannerManager.this.commonPrefCtl.getNotShowDateForBanner(nXToyBanner.sn).equalsIgnoreCase(currentTimeFormat)) {
                        arrayList2.add(nXToyBanner);
                    }
                }
                if (arrayList2.size() == 0) {
                    NXToyBannerListResult nXToyBannerListResult2 = new NXToyBannerListResult(NXToyErrorCode.HAVENT_SHOW_BANNER.getCode(), NXBannerManager.this.localeManager.getString(R.string.npres_banner_not_registered));
                    nXToyBannerListResult2.requestTag = NXToyRequestTag.GetBannerList.getValue();
                    nPBannerListener.onFailed(nXToyBannerListResult2);
                } else {
                    final NXToyBannerListResult.ResultSet resultSet = ((NXToyBannerListResult) nXToyResult).result;
                    resultSet.bannerList = arrayList2;
                    activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.promotion.NXBannerManager.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            NPFullBannerDialog nPFullBannerDialogNewInstance = NPFullBannerDialog.newInstance(activity);
                            nPFullBannerDialogNewInstance.setBannerInfoList(resultSet.bannerList);
                            nPFullBannerDialogNewInstance.showDialog(activity, NPFullBannerDialog.TAG);
                        }
                    });
                }
            }
        });
    }

    public void showEndingBanner(Activity activity, NPEndingBannerListener nPEndingBannerListener) {
        this.endingBannerListener = nPEndingBannerListener;
        NXPClosingBannerDialog.newInstance(activity, this.commonPrefCtl.getEndingBanner()).showDialog(activity, NXPClosingBannerDialog.TAG);
    }
}
