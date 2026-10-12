package kr.co.nexon.android.sns.nxcom.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.nexon.npaccount.R;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXComSearchIDPWDialog extends NPNXComWebDialog {
    public static final int CODE_SEARCH_TYPE_ID = 0;
    public static final int CODE_SEARCH_TYPE_PW = 1;
    public static final String KEY_SEARCH_TYPE = "searchType";
    public static final String TAG = "NPNXComSearchIDPWDialog";
    private NPAuthListener resultListener;

    public static NPNXComSearchIDPWDialog newInstance(Activity activity, String str, int i) {
        NPNXComSearchIDPWDialog nPNXComSearchIDPWDialog = new NPNXComSearchIDPWDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putInt(KEY_SEARCH_TYPE, i);
        bundle.putString("session", str);
        nPNXComSearchIDPWDialog.setArguments(bundle);
        return nPNXComSearchIDPWDialog;
    }

    @Override // kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog, kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        View viewCreateView = super.createView();
        int i = getArguments().getInt(KEY_SEARCH_TYPE, 0);
        if (i == 0) {
            initSearchID();
        } else if (i == 1) {
            initSearchPW();
        }
        return viewCreateView;
    }

    @Override // kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog
    public String loadingMessage() {
        return getString(R.string.progress_loading);
    }

    @Override // kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog
    public void setResultListener(NPAuthListener nPAuthListener) {
        this.resultListener = nPAuthListener;
    }
}
