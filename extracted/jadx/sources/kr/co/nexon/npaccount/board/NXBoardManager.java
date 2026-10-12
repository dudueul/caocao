package kr.co.nexon.npaccount.board;

import android.app.Activity;
import android.content.Context;
import com.google.gson.Gson;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.util.NXDateUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import com.nexon.platform.NXPFinalizer;
import java.util.Map;
import kr.co.nexon.mdev.android.util.NXAdsUtil;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.result.NXToyAdIdResult;
import kr.co.nexon.npaccount.auth.result.NXToyCloseResult;
import kr.co.nexon.npaccount.listener.NPCloseListener;
import kr.co.nexon.toy.android.ui.baseplate.NPPlateCodes;
import kr.co.nexon.toy.android.ui.board.NPCSDialog;
import kr.co.nexon.toy.android.ui.board.NPEventWebDialog;
import kr.co.nexon.toy.android.ui.board.NPFAQDialog;
import kr.co.nexon.toy.android.ui.board.NPForumDialog;
import kr.co.nexon.toy.android.ui.board.NPNoticeDialog;
import kr.co.nexon.toy.android.ui.board.NPShopDialog;
import kr.co.nexon.toy.android.ui.board.NPShowTodayDialog;
import kr.co.nexon.toy.android.ui.board.NPWebDialogFullScreen;
import kr.co.nexon.toy.android.ui.board.NPWebDialogTitleBar;
import kr.co.nexon.toy.android.ui.board.NXPOfferwallDialog;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXBoardManager {
    public static final String BOARD_FAQ = "2";
    public static final String BOARD_FORUM = "4";
    public static final String BOARD_HELPCENTER = "3";
    public static final String BOARD_NOTICE = "1";
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: kr.co.nexon.npaccount.board.NXBoardManager.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            NXBoardManager.instance = null;
        }
    };
    public static NXBoardManager instance;
    public NPCloseListener noticeCloseListener;

    private NXBoardManager(Context context) {
    }

    public static NXBoardManager getInstance(Context context) {
        if (instance == null) {
            synchronized (NXBoardManager.class) {
                if (instance == null) {
                    instance = new NXBoardManager(context);
                }
            }
        }
        return instance;
    }

    public void closeNotice() {
        if (this.noticeCloseListener != null) {
            NXToyCloseResult nXToyCloseResult = new NXToyCloseResult();
            nXToyCloseResult.screenName = NPPlateCodes.CODE_NOTICE;
            this.noticeCloseListener.onClose(nXToyCloseResult);
        }
    }

    public void showEventWeb(Activity activity, String str) {
        NPEventWebDialog.newInstance(activity, str).showDialog(activity, NPEventWebDialog.TAG);
    }

    public void showFAQ(Activity activity) {
        NPFAQDialog.newInstance(activity).showDialog(activity, NPFAQDialog.TAG);
    }

    public void showForum(Activity activity, int i) {
        NPForumDialog.newInstance(activity, i).showDialog(activity, NPForumDialog.TAG);
    }

    public void showHelpCenter(Activity activity, String str) {
        NPWebDialogTitleBar.newInstance(activity, "", str).showDialog(activity, NPWebDialogTitleBar.TAG);
    }

    public void showHelpCenter(Activity activity, Map<String, Object> map) {
        String json = null;
        if (map != null && map.size() > 0) {
            try {
                json = new Gson().toJson(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ToyLog.d("" + json);
        NPCSDialog.newInstance(activity, json).showDialog(activity, NPCSDialog.TAG);
    }

    public void showNotice(Activity activity) {
        showNotice(activity, null);
    }

    public void showNotice(Activity activity, NPCloseListener nPCloseListener) {
        NPNoticeDialog nPNoticeDialogNewInstance = NPNoticeDialog.newInstance(activity);
        nPNoticeDialogNewInstance.setCloseListener(nPCloseListener);
        nPNoticeDialogNewInstance.showDialog(activity, NPNoticeDialog.TAG);
    }

    public void showOfferwall(final Activity activity, final NPCloseListener nPCloseListener) {
        final String offerwall = NXToyCommonPreferenceController.getInstance().getOfferwall();
        if (!NXStringUtil.isNull(offerwall)) {
            NXAdsUtil.getAdvertisingId(activity.getApplicationContext(), new NPListener() { // from class: kr.co.nexon.npaccount.board.NXBoardManager.2
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    if (nXToyResult.errorCode != 0) {
                        ToyLog.e("showOfferwall api, an exception occurred during the call to getAdvertisingId, errorMessage : " + nXToyResult.errorText);
                    }
                    NXPOfferwallDialog nXPOfferwallDialogNewInstance = NXPOfferwallDialog.newInstance(activity, offerwall, ((NXToyAdIdResult) nXToyResult).result.isLimitAdTrackingEnabled);
                    nXPOfferwallDialogNewInstance.setCloseListener(nPCloseListener);
                    nXPOfferwallDialogNewInstance.showDialog(activity, NXPOfferwallDialog.TAG);
                }
            });
            return;
        }
        NXToyCloseResult nXToyCloseResult = new NXToyCloseResult();
        nXToyCloseResult.screenName = "offerwall";
        nXToyCloseResult.requestTag = NXToyRequestTag.ShowOfferwall.getValue();
        nXToyCloseResult.errorCode = NXToyErrorCode.OFFERWALL_INCORRECT_CALL.getCode();
        nXToyCloseResult.errorText = "Incorrect call. Please call EnterToy prior to calling Offerewall.";
        if (nPCloseListener != null) {
            nPCloseListener.onClose(nXToyCloseResult);
        }
    }

    public void showToday(Activity activity, int i, boolean z, NPListener nPListener) {
        String notShowDateForShowToday = NXToyCommonPreferenceController.getInstance().getNotShowDateForShowToday(i);
        String currentTimeFormat = NXDateUtil.getCurrentTimeFormat(NPShowTodayDialog.KEY_DATE_FORMAT);
        if (!z || !currentTimeFormat.equalsIgnoreCase(notShowDateForShowToday)) {
            NPShowTodayDialog nPShowTodayDialogNewInstance = NPShowTodayDialog.newInstance(activity, i, z);
            nPShowTodayDialogNewInstance.setResultListener(nPListener);
            nPShowTodayDialogNewInstance.showDialog(activity, NPShowTodayDialog.TAG);
            return;
        }
        ToyLog.d("Today is not used for a day. useDontShowToday : " + z + ", saveDate/currentDate" + notShowDateForShowToday + "/" + currentTimeFormat);
        if (nPListener != null) {
            NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(activity.getApplicationContext());
            NXToyResult nXToyResult = new NXToyResult();
            nXToyResult.errorText = nXToyLocaleManager.getString(R.string.npres_not_used_showtoday_message);
            nXToyResult.errorCode = NXToyErrorCode.NOT_USED_SHOWTODAY.getCode();
            nXToyResult.requestTag = NXToyRequestTag.ShowToday.getValue();
            nPListener.onResult(nXToyResult);
        }
    }

    public void showWeb(Activity activity, String str, String str2, String str3) {
        NPWebDialogTitleBar nPWebDialogTitleBarNewInstance = NPWebDialogTitleBar.newInstance(activity, str, str2);
        nPWebDialogTitleBarNewInstance.setPostData(str3);
        nPWebDialogTitleBarNewInstance.showDialog(activity, NPWebDialogTitleBar.TAG);
    }

    public void showWebNoTitle(Activity activity, String str) {
        NPWebDialogFullScreen.newInstance(activity, str).showDialog(activity, NPWebDialogFullScreen.TAG);
    }

    public void showWebShop(Activity activity, int i, NPListener nPListener) {
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(activity.getApplicationContext());
        NPShopDialog nPShopDialogNewInstance = NPShopDialog.newInstance(activity, i, nXToyLocaleManager.getLocale().getLocaleCode(), nXToyLocaleManager.getCountry().getCountryCode());
        nPShopDialogNewInstance.setResultListener(nPListener);
        nPShopDialogNewInstance.showDialog(activity, NPShopDialog.TAG);
    }
}
