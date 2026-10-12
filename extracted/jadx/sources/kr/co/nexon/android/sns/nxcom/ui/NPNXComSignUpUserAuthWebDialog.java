package kr.co.nexon.android.sns.nxcom.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXComSignUpUserAuthWebDialog extends NPNXComWebDialog {
    public static final String KEY_POST_DATA = "postData";
    public static final String KEY_URL = "url";
    public static final String TAG = "NPNXComSignUpUserAuthWebDialog";

    public static NPNXComSignUpUserAuthWebDialog newInstance(Activity activity, String str, String str2, String str3) {
        NPNXComSignUpUserAuthWebDialog nPNXComSignUpUserAuthWebDialog = new NPNXComSignUpUserAuthWebDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("session", str);
        bundle.putString("url", str2);
        bundle.putString(KEY_POST_DATA, str3);
        nPNXComSignUpUserAuthWebDialog.setArguments(bundle);
        return nPNXComSignUpUserAuthWebDialog;
    }

    @Override // kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog, kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        View viewCreateView = super.createView();
        initNXJoinAuth(getArguments().getString("url"), getArguments().getString(KEY_POST_DATA));
        return viewCreateView;
    }
}
