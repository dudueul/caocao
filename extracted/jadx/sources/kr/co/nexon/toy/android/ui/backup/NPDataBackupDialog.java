package kr.co.nexon.toy.android.ui.backup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.backup.view.NXPDataBackupView;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPDataBackupDialog extends NPDialogBase {
    public static final String KEY_ERROR_DESCRIPTOIN = "errorDescription";
    public static final String KEY_MIGRATION_TOKEN = "mgToken";
    public static final String KEY_TITLE = "title";
    public static final String TAG = "NPDataBackupDialog";
    private String errorDescription;
    private String mgToken;
    private NXToyResult result;
    private NPListener resultListener;
    private String title;

    private String getFormattedMGToken() {
        if (this.mgToken == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.mgToken);
        if (sb.length() > 12) {
            sb.insert(4, " ");
            sb.insert(9, " ");
            sb.insert(14, " ");
        }
        return sb.toString();
    }

    public static NPDataBackupDialog newInstance(Activity activity, String str, String str2) {
        NPDataBackupDialog nPDataBackupDialog = new NPDataBackupDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("title", str);
        if (str2 != null) {
            bundle.putString(KEY_MIGRATION_TOKEN, str2);
        }
        nPDataBackupDialog.setArguments(bundle);
        return nPDataBackupDialog;
    }

    public static NPDataBackupDialog newInstanceForDescription(Activity activity, String str, String str2) {
        NPDataBackupDialog nPDataBackupDialog = new NPDataBackupDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("title", str);
        if (str2 != null) {
            bundle.putString(KEY_ERROR_DESCRIPTOIN, str2);
        }
        nPDataBackupDialog.setArguments(bundle);
        return nPDataBackupDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        NXPDataBackupView nXPDataBackupView = (NXPDataBackupView) View.inflate(this.applicationContext, R.layout.nxp_data_backup_view, null);
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(this.applicationContext);
        Bundle arguments = getArguments();
        this.title = arguments.getString("title");
        this.mgToken = arguments.getString(KEY_MIGRATION_TOKEN, null);
        this.errorDescription = arguments.getString(KEY_ERROR_DESCRIPTOIN, null);
        nXPDataBackupView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.backup.NPDataBackupDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPDataBackupDialog.this.onBackPressed();
            }
        });
        nXPDataBackupView.setTitleTextView(NXStringUtil.isNotNull(this.title) ? this.title : nXToyLocaleManager.getString(R.string.npres_data_backup_title));
        String formattedMGToken = getFormattedMGToken();
        if (NXStringUtil.isNull(formattedMGToken)) {
            nXPDataBackupView.setMgTokenTextVisibility(8);
        } else {
            nXPDataBackupView.setMgTokenText(formattedMGToken);
        }
        if (NXStringUtil.isNotNull(this.errorDescription)) {
            nXPDataBackupView.setDescriptionCodeText(this.errorDescription);
        } else {
            nXPDataBackupView.setDescriptionCodeText(nXToyLocaleManager.getString(R.string.npres_backup_description_code));
        }
        return nXPDataBackupView;
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (this.resultListener != null && this.result != null) {
            this.resultListener.onResult(this.result);
        }
        super.onBackPressed();
    }

    public void setResultInfo(NPListener nPListener, NXToyResult nXToyResult) {
        this.resultListener = nPListener;
        this.result = nXToyResult;
    }
}
