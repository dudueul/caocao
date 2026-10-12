package kr.co.nexon.android.sns.nxcom.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXComSignUpWebDialog extends NPNXComWebDialog {
    public static final String KEY_EMAIL_ID = "emailId";
    public static final String KEY_NICK_NAME = "nickName";
    public static final String TAG = "NPNXComSignUpWebDialog";

    public static NPNXComSignUpWebDialog newInstance(Activity activity, String str, String str2, String str3) {
        NPNXComSignUpWebDialog nPNXComSignUpWebDialog = new NPNXComSignUpWebDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("session", str);
        bundle.putString("emailId", str2);
        bundle.putString(KEY_NICK_NAME, str3);
        nPNXComSignUpWebDialog.setArguments(bundle);
        return nPNXComSignUpWebDialog;
    }

    @Override // kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog, kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        View viewCreateView = super.createView();
        initNXJoin(getArguments().getString("emailId"), getArguments().getString(KEY_NICK_NAME));
        return viewCreateView;
    }
}
