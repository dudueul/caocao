package kr.co.nexon.toy.android.ui.permission;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import java.util.Iterator;
import kr.co.nexon.mdev.android.permission.NXPPermission;
import kr.co.nexon.mdev.android.permission.NXPRequestPermissionInfo;
import kr.co.nexon.mdev.android.util.NXGraphicUtil;
import kr.co.nexon.mdev.android.util.NXResourcesUtil;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.permission.view.NXPPermissionConfirmFullScreenView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPermissionDialog extends NPDialogBase implements NXPPermissionConfirmFullScreenView.NXPPermissionConfirmListener {
    private static final String KEY_PERMISSION_INFO = "permissionsInfo";
    private static final String PATH_IMAGE_RES_FOLDER = "toy/custom/res/drawable/";
    private static final String PATH_PERMISSION_APP_IMAGE_FILE = "toy/custom/res/drawable/permission_confirm_dialog.png";
    public static final String TAG = "NXPPermissionDialog";
    private View.OnClickListener confirmClickListener;
    private NXPRequestPermissionInfo permissionInfo;

    public static NXPPermissionDialog newInstance(Activity activity, NXPRequestPermissionInfo nXPRequestPermissionInfo) {
        ToyLog.d("NXPPermissionInfo: " + nXPRequestPermissionInfo);
        String jsonString = NXJsonUtil.toJsonString(nXPRequestPermissionInfo);
        NXPPermissionDialog nXPPermissionDialog = new NXPPermissionDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString(KEY_PERMISSION_INFO, jsonString);
        nXPPermissionDialog.setArguments(bundle);
        return nXPPermissionDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        Drawable imageByFile;
        NXPPermissionConfirmFullScreenView nXPPermissionConfirmFullScreenView = (NXPPermissionConfirmFullScreenView) View.inflate(this.applicationContext, R.layout.nxp_permission_confirm_full_screen_view, null);
        this.permissionInfo = (NXPRequestPermissionInfo) NXJsonUtil.fromObject(getArguments().getString(KEY_PERMISSION_INFO), NXPRequestPermissionInfo.class);
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(this.applicationContext);
        nXPPermissionConfirmFullScreenView.setConfirmButtonText(nXToyLocaleManager.getString(R.string.confirm));
        nXPPermissionConfirmFullScreenView.setOnConfirmButtonClickListener(this);
        String title = this.permissionInfo.getTitle();
        if (NXStringUtil.isNull(title)) {
            title = nXToyLocaleManager.getString(R.string.npres_runtime_permission_title);
        }
        String serviceTitle = NXToyCommonPreferenceController.getInstance().getServiceTitle();
        nXPPermissionConfirmFullScreenView.setTitle(NXStringUtil.isNotNull(serviceTitle) ? String.format("[%s] %s", serviceTitle, title) : title);
        String cautionText = this.permissionInfo.getCautionText();
        if (NXStringUtil.isNull(cautionText)) {
            cautionText = "";
        }
        nXPPermissionConfirmFullScreenView.setFooterText(cautionText);
        StringBuilder sb = new StringBuilder();
        Iterator<NXPPermission> it = this.permissionInfo.getRequestPermissionsHashMap().values().iterator();
        while (it.hasNext()) {
            NXPPermission next = it.next();
            String str = next.getGroupName() + " : " + next.getDescription();
            if (this.permissionInfo.getVisiblePrefix()) {
                str = String.format(next.getOption() == 1 ? nXToyLocaleManager.getString(R.string.npres_permission_confirm_permission_necessary_contents) : nXToyLocaleManager.getString(R.string.npres_permission_confirm_permission_optional_contents), str);
            }
            sb.append(str);
            if (it.hasNext()) {
                sb.append("\n");
            }
        }
        nXPPermissionConfirmFullScreenView.setPermissionContentsText(sb.toString());
        Drawable drawableByResId = null;
        int identifier = getResources().getIdentifier("permission_confirm_dialog", "drawable", getActivity().getPackageName());
        if (identifier > 0) {
            try {
                drawableByResId = NXResourcesUtil.getDrawableByResId(getActivity(), identifier);
            } catch (Resources.NotFoundException e) {
                ToyLog.d("No matching id exists from screen orientation.");
            }
        }
        if (drawableByResId == null && (imageByFile = NXGraphicUtil.getImageByFile(getActivity(), PATH_PERMISSION_APP_IMAGE_FILE)) != null) {
            drawableByResId = imageByFile;
        }
        boolean visibleImage = this.permissionInfo.getVisibleImage();
        if (visibleImage && drawableByResId != null) {
            nXPPermissionConfirmFullScreenView.setAppImageResource(drawableByResId);
        } else if (visibleImage && drawableByResId == null) {
            nXPPermissionConfirmFullScreenView.setDefaultImageResource();
        } else {
            nXPPermissionConfirmFullScreenView.setVisibilityImageResource(8);
        }
        return nXPPermissionConfirmFullScreenView;
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setContentView(createView());
        }
    }

    @Override // kr.co.nexon.toy.android.ui.permission.view.NXPPermissionConfirmFullScreenView.NXPPermissionConfirmListener
    public void onConfirm(View view) {
        if (this.confirmClickListener != null) {
            this.confirmClickListener.onClick(view);
        }
        dismiss();
    }

    public void setOnConfirmClickListener(View.OnClickListener onClickListener) {
        this.confirmClickListener = onClickListener;
    }
}
