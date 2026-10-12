package kr.co.nexon.npaccount.setting;

import com.google.gson.reflect.TypeToken;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.platform.NXPFinalizer;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPOptionManager {
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: kr.co.nexon.npaccount.setting.NPOptionManager.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            NPOptionManager unused = NPOptionManager.instance = null;
        }
    };
    private static NPOptionManager instance;

    private NPOptionManager() {
    }

    public static NPOptionManager getInstance() {
        if (instance == null) {
            synchronized (NPOptionManager.class) {
                if (instance == null) {
                    instance = new NPOptionManager();
                }
            }
        }
        return instance;
    }

    private void saveDefaultLoginTypes(NPOptions nPOptions) {
        List<Integer> loginTypeList = nPOptions.getLoginTypeList();
        if (loginTypeList == null || loginTypeList.size() == 0) {
            ToyLog.d("memberships size is 0");
            return;
        }
        LinkedList linkedList = new LinkedList();
        boolean z = false;
        if (loginTypeList.size() == 1 && loginTypeList.get(0).intValue() == NXToyLoginType.LoginTypeDefault.getValue()) {
            z = true;
            ToyLog.d("NPOption's membership should be reset");
        }
        int size = loginTypeList.size();
        for (int i = 0; i < size; i++) {
            int iIntValue = loginTypeList.get(i).intValue();
            if (NXToyLoginType.isValidLoginType(iIntValue)) {
                linkedList.add(Integer.valueOf(iIntValue));
                z = true;
            }
        }
        if (z) {
            ToyLog.d("valid membershipList:" + linkedList);
            NXToyCommonPreferenceController.getInstance().setOptionUseMemberships(NXJsonUtil.toJsonString(linkedList));
        }
    }

    private void saveGoogleSignInInsteadOfGcidLogin(NPOptions nPOptions) {
        int googleSignInInsteadOfGcidLogin = nPOptions.getGoogleSignInInsteadOfGcidLogin();
        if (googleSignInInsteadOfGcidLogin != -1) {
            NXToyCommonPreferenceController.getInstance().setGoogleSignInInsteadOfGcidLogin(googleSignInInsteadOfGcidLogin);
        }
    }

    private void saveUseNgsm(NPOptions nPOptions) {
        int ngsmEnabled = nPOptions.getNgsmEnabled();
        if (ngsmEnabled != -1) {
            NXToyCommonPreferenceController.getInstance().setUseNgsm(ngsmEnabled);
        }
    }

    public NPOptions getOptions() {
        NPOptions nPOptions;
        synchronized (NPOptionManager.class) {
            NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
            List list = (List) NXJsonUtil.fromObject(nXToyCommonPreferenceController.getOptionUseMemberships(), new TypeToken<LinkedList<Integer>>() { // from class: kr.co.nexon.npaccount.setting.NPOptionManager.2
            }.getType());
            int googleSignInInsteadOfGcidLogin = nXToyCommonPreferenceController.getGoogleSignInInsteadOfGcidLogin();
            int useNgsm = nXToyCommonPreferenceController.getUseNgsm();
            nPOptions = new NPOptions(list);
            nPOptions.setGoogleSignInInsteadOfGcidLogin(googleSignInInsteadOfGcidLogin == 1);
            nPOptions.setNgsmEnabled(useNgsm == 1);
        }
        return nPOptions;
    }

    public void setOptions(NPOptions nPOptions) {
        synchronized (NPOptionManager.class) {
            try {
                if (nPOptions == null) {
                    ToyLog.d("setOptions param is null");
                } else {
                    ToyLog.d("setOptions params:" + nPOptions);
                    saveDefaultLoginTypes(nPOptions);
                    saveGoogleSignInInsteadOfGcidLogin(nPOptions);
                    saveUseNgsm(nPOptions);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
