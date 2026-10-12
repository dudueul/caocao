package kr.co.nexon.android.sns.nxcom.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXComUserAuthWebDialog extends NPNXComWebDialog {
    public static final String TAG = "NPNXComUserAuthWebDialog";

    public static NPNXComUserAuthWebDialog newInstance(Activity activity, String str, String str2) {
        NPNXComUserAuthWebDialog nPNXComUserAuthWebDialog = new NPNXComUserAuthWebDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString(NPNXComWebDialog.KEY_APPCRYPT, str);
        bundle.putString("session", str2);
        nPNXComUserAuthWebDialog.setArguments(bundle);
        return nPNXComUserAuthWebDialog;
    }

    @Override // kr.co.nexon.android.sns.nxcom.ui.NPNXComWebDialog, kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        View viewCreateView = super.createView();
        initNXAuth();
        return viewCreateView;
    }
}
