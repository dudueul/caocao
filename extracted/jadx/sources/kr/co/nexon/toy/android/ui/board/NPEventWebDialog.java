package kr.co.nexon.toy.android.ui.board;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToySession;
import java.util.HashMap;
import java.util.Map;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.npaccount.NXToyLocaleManager;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPEventWebDialog extends NPWebDialogFullScreen {
    public static final String TAG = "NPEventWebDialog";

    private Map<String, String> getEventToyExtraHeader() {
        HashMap map = new HashMap();
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(getActivity());
        NXToySession session = NXToySessionManager.getInstance().getSession();
        map.put("x-toy-npsn", String.valueOf(session.getNpsn()));
        map.put("x-toy-nptoken", session.getNptoken());
        map.put("x-toy-npacode", session.getNpaCode());
        map.put("x-toy-country", nXToyLocaleManager.getCountry().getCountryCode());
        map.put("x-toy-language", nXToyLocaleManager.getLocale().getLocaleCode());
        map.put("x-toy-os", "Android_" + Build.VERSION.RELEASE);
        return map;
    }

    public static NPEventWebDialog newInstance(Activity activity, String str) {
        NPEventWebDialog nPEventWebDialog = new NPEventWebDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("url", str);
        nPEventWebDialog.setArguments(bundle);
        return nPEventWebDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.board.NPWebDialogBase
    protected void loadURL(String str) {
        this.currentWebView.loadUrl(str, getEventToyExtraHeader());
    }
}
