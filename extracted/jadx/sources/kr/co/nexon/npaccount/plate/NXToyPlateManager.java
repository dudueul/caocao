package kr.co.nexon.npaccount.plate;

import android.app.Activity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import com.nexon.platform.NXPFinalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.request.NXToyGetTermsListRequest;
import kr.co.nexon.npaccount.auth.result.NXToyTermsListResult;
import kr.co.nexon.npaccount.auth.result.model.NXToyBasePlate;
import kr.co.nexon.npaccount.listener.NPPlateListener;
import kr.co.nexon.npaccount.plate.request.NXToyGetGameInfoRequest;
import kr.co.nexon.npaccount.plate.request.NXToyGetPlateRequest;
import kr.co.nexon.npaccount.plate.result.NXToyGameInfoResult;
import kr.co.nexon.npaccount.plate.result.NXToyPlateInfoResult;
import kr.co.nexon.toy.android.ui.baseplate.NPGameInfoDialog;
import kr.co.nexon.toy.android.ui.baseplate.NPTermsListDialog;
import kr.co.nexon.toy.android.ui.baseplate.NPUserInfoDialog;
import kr.co.nexon.toy.android.ui.baseplate.NXPBasePlateDialog;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyPlateManager {
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: kr.co.nexon.npaccount.plate.NXToyPlateManager.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            NXToyPlateManager unused = NXToyPlateManager.instance = null;
        }
    };
    private static NXToyPlateManager instance;
    private NPPlateListener plateListener;

    private NXToyPlateManager() {
    }

    public static NXToyPlateManager getInstance() {
        if (instance == null) {
            synchronized (NXToyPlateManager.class) {
                if (instance == null) {
                    instance = new NXToyPlateManager();
                }
            }
        }
        return instance;
    }

    public void showCustomerService(Activity activity) {
        showCustomerService(activity, null);
    }

    public void showCustomerService(Activity activity, Map<String, Object> map) {
        showCustomerService(activity, map, null);
    }

    public void showCustomerService(Activity activity, Map<String, Object> map, NPPlateListener nPPlateListener) {
        String json = null;
        if (map != null && map.size() > 0) {
            try {
                json = new Gson().toJson(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ToyLog.d("" + json);
        showPlate(activity, 0, map, nPPlateListener);
    }

    public void showGameInfo(final Activity activity) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetGameInfoRequest(), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.plate.NXToyPlateManager.5
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(final NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.e(nXToyResult.toString());
                    activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.plate.NXToyPlateManager.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            new NXPAlertDialog.Builder(activity).setMessage(nXToyResult.errorText).setPositiveButton(NXToyLocaleManager.getInstance().getString(R.string.confirm), null).create().show();
                        }
                    });
                } else {
                    NPGameInfoDialog.newInstance(activity, new Gson().toJson(((NXToyGameInfoResult) nXToyResult).result)).showDialog(activity, NPGameInfoDialog.TAG);
                }
            }
        });
    }

    public void showMyAccount(Activity activity, String str) {
        NXToySessionManager nXToySessionManager = NXToySessionManager.getInstance();
        if (nXToySessionManager.getSession().getType() == -1) {
            return;
        }
        NXToySession session = nXToySessionManager.getSession();
        NPUserInfoDialog.newInstance(activity, str, session.getType(), session.getEmailId()).showDialog(activity, NPUserInfoDialog.TAG);
    }

    public void showPlate(Activity activity, int i, Map<String, Object> map) {
        showPlate(activity, i, map, null);
    }

    public void showPlate(final Activity activity, final int i, final Map<String, Object> map, final NPPlateListener nPPlateListener) {
        this.plateListener = nPPlateListener;
        String json = null;
        if (map == null || map.size() <= 0) {
            json = "{}";
        } else {
            try {
                json = new Gson().toJson(map);
            } catch (Exception e) {
            }
        }
        ToyLog.d("" + json);
        String basePlate = NXToyCommonPreferenceController.getInstance().getBasePlate(NXToyLocaleManager.getInstance().getLocale().getLocaleCode());
        if (!NXStringUtil.isNotNull(basePlate)) {
            NXToyRequestPostman.getInstance().postRequest(new NXToyGetPlateRequest(i), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.plate.NXToyPlateManager.3
                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                public void onComplete(final NXToyResult nXToyResult) {
                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                        ToyLog.e(nXToyResult.toString());
                        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.plate.NXToyPlateManager.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                new NXPAlertDialog.Builder(activity).setMessage(nXToyResult.errorText).setPositiveButton(NXToyLocaleManager.getInstance().getString(R.string.confirm), null).create().show();
                            }
                        });
                        return;
                    }
                    NXToyPlateInfoResult.ResultSet resultSet = ((NXToyPlateInfoResult) nXToyResult).result;
                    NXToyBasePlate nXToyBasePlate = new NXToyBasePlate();
                    nXToyBasePlate.group = i;
                    nXToyBasePlate.items = resultSet.items;
                    nXToyBasePlate.title = resultSet.title;
                    NXPBasePlateDialog nXPBasePlateDialogNewInstance = NXPBasePlateDialog.newInstance(activity, new Gson().toJson(nXToyBasePlate), new Gson().toJson(map));
                    nXPBasePlateDialogNewInstance.setPlateListener(nPPlateListener);
                    nXPBasePlateDialogNewInstance.showDialog(activity, NXPBasePlateDialog.TAG);
                }
            });
            return;
        }
        List list = (List) NXJsonUtil.fromObject(basePlate, new TypeToken<ArrayList<NXToyBasePlate>>() { // from class: kr.co.nexon.npaccount.plate.NXToyPlateManager.2
        }.getType());
        for (int i2 = 0; i2 < list.size(); i2++) {
            NXToyBasePlate nXToyBasePlate = (NXToyBasePlate) list.get(i2);
            if (nXToyBasePlate.group == i) {
                NXPBasePlateDialog nXPBasePlateDialogNewInstance = NXPBasePlateDialog.newInstance(activity, new Gson().toJson(nXToyBasePlate), new Gson().toJson(map));
                nXPBasePlateDialogNewInstance.setPlateListener(nPPlateListener);
                nXPBasePlateDialogNewInstance.showDialog(activity, NXPBasePlateDialog.TAG);
                return;
            }
        }
    }

    public void showPlate(Activity activity, Map<String, Object> map) {
        showPlate(activity, 0, map, null);
    }

    public void showTermsList(final Activity activity, final String str) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetTermsListRequest("baseplate"), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.plate.NXToyPlateManager.4
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(final NXToyResult nXToyResult) {
                if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                    ToyLog.e(nXToyResult.toString());
                    activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.plate.NXToyPlateManager.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            new NXPAlertDialog.Builder(activity).setMessage(nXToyResult.errorText).setPositiveButton(NXToyLocaleManager.getInstance().getString(R.string.confirm), null).create().show();
                        }
                    });
                } else {
                    NXToyTermsListResult.ResultSet resultSet = ((NXToyTermsListResult) nXToyResult).result;
                    NPTermsListDialog nPTermsListDialogNewInstance = NPTermsListDialog.newInstance(activity, str);
                    nPTermsListDialogNewInstance.setTermsList(resultSet.terms);
                    nPTermsListDialogNewInstance.showDialog(activity, NPTermsListDialog.TAG);
                }
            }
        });
    }
}
