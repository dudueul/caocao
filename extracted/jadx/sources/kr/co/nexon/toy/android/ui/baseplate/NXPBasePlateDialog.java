package kr.co.nexon.toy.android.ui.baseplate;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.google.gson.Gson;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.npaccount.R;
import java.util.Map;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NPAccount;
import kr.co.nexon.npaccount.auth.result.model.NXToyBasePlate;
import kr.co.nexon.npaccount.auth.result.model.NXToyPlateInfo;
import kr.co.nexon.npaccount.listener.NPPlateListener;
import kr.co.nexon.npaccount.plate.result.NXToyPlateActionPerformedResult;
import kr.co.nexon.npaccount.push.NXPPush;
import kr.co.nexon.npaccount.push.NXPPushMenuOption;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.baseplate.view.NXPBaseplateView;
import kr.co.nexon.toy.android.ui.board.NPNCSDialog;
import kr.co.nexon.toy.listener.NPListener;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPBasePlateDialog extends NPDialogBase implements NXPBaseplateView.BaseplateItemClickListener {
    public static final String KEY_PARAM_DATA = "params";
    public static final String KEY_PLATE_INFO = "plateInfo";
    public static final String TAG = "NXPBasePlateDialog";
    private NPAccount account;
    private Map<String, Object> params;
    private NXToyBasePlate plateInfo;
    private NPPlateListener plateListener;

    private void handleDataRestore(NXToyPlateInfo nXToyPlateInfo) {
        this.account.showDataRestore(getActivity(), nXToyPlateInfo.title, new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPBasePlateDialog.3
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (NXPBasePlateDialog.this.plateListener != null) {
                    NXPBasePlateDialog.this.plateListener.onActionPerformedResult(nXToyResult);
                }
                NXPBasePlateDialog.this.dismiss();
            }
        });
    }

    private void handlePushSetting(NXToyPlateInfo nXToyPlateInfo) {
        NXPPushMenuOption nXPPushMenuOption = new NXPPushMenuOption();
        String string = (nXToyPlateInfo.meta.isEmpty() || !nXToyPlateInfo.meta.containsKey(NPPlateCodes.META_PUSH_LABEL)) ? "" : nXToyPlateInfo.meta.get(NPPlateCodes.META_PUSH_LABEL).toString();
        String string2 = (nXToyPlateInfo.meta.isEmpty() || !nXToyPlateInfo.meta.containsKey(NPPlateCodes.META_PHONE_LABEL)) ? "" : nXToyPlateInfo.meta.get(NPPlateCodes.META_PHONE_LABEL).toString();
        String string3 = (nXToyPlateInfo.meta.isEmpty() || !nXToyPlateInfo.meta.containsKey(NPPlateCodes.META_NIGHT_PUSH_LABEL)) ? "" : nXToyPlateInfo.meta.get(NPPlateCodes.META_NIGHT_PUSH_LABEL).toString();
        String string4 = (nXToyPlateInfo.meta.isEmpty() || !nXToyPlateInfo.meta.containsKey(NPPlateCodes.META_EMAIL_LABEL)) ? "" : nXToyPlateInfo.meta.get(NPPlateCodes.META_EMAIL_LABEL).toString();
        nXPPushMenuOption.setTitle(nXToyPlateInfo.title);
        nXPPushMenuOption.setPushLabelName(string);
        nXPPushMenuOption.setSmsLabelName(string2);
        nXPPushMenuOption.setNightPushLabelName(string3);
        nXPPushMenuOption.setEmailLabelName(string4);
        NXPPush.showPushSettingDialog(getActivity(), nXPPushMenuOption, new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPBasePlateDialog.4
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (NXPBasePlateDialog.this.plateListener != null) {
                    NXPBasePlateDialog.this.plateListener.onActionPerformedResult(nXToyResult);
                }
                NXPBasePlateDialog.this.dismiss();
            }
        });
    }

    private void handleURL(NXToyPlateInfo nXToyPlateInfo) {
        String string = (nXToyPlateInfo.meta.isEmpty() || !nXToyPlateInfo.meta.containsKey("url")) ? "" : nXToyPlateInfo.meta.get("url").toString();
        if (nXToyPlateInfo.meta.containsKey("query")) {
            string = string + "?" + nXToyPlateInfo.meta.get("query").toString();
        }
        Activity activity = getActivity();
        String json = new Gson().toJson(this.params, Map.class);
        if (!string.contains("support.nexon.com")) {
            this.account.showWeb(activity, nXToyPlateInfo.title, string);
        } else {
            ToyLog.d(string);
            NPNCSDialog.newInstance(activity, this.plateInfo.title, string, json).showDialog(activity, NPNCSDialog.TAG);
        }
    }

    public static NXPBasePlateDialog newInstance(Activity activity, String str, String str2) {
        NXPBasePlateDialog nXPBasePlateDialog = new NXPBasePlateDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString(KEY_PLATE_INFO, str);
        bundle.putString("params", str2);
        nXPBasePlateDialog.setArguments(bundle);
        return nXPBasePlateDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        NXPBaseplateView nXPBaseplateView = (NXPBaseplateView) View.inflate(this.applicationContext, R.layout.nxp_baseplate_view, null);
        this.account = NPAccount.getInstance(getActivity());
        this.plateInfo = (NXToyBasePlate) new Gson().fromJson(getArguments().getString(KEY_PLATE_INFO), NXToyBasePlate.class);
        this.params = (Map) new Gson().fromJson(getArguments().getString("params"), Map.class);
        if (this.plateInfo != null) {
            nXPBaseplateView.setTitle(this.plateInfo.title);
            nXPBaseplateView.setBaseplateInfo(this.plateInfo);
            int integer = getResources().getInteger(R.integer.baseplate_view_scroll_enable_count);
            ToyLog.d("Baseplateview scrollableCount:" + integer);
            if (this.plateInfo.items == null || this.plateInfo.items.size() < integer) {
                nXPBaseplateView.setScrollEnabled(false);
            }
        }
        nXPBaseplateView.setItemClickListener(this);
        nXPBaseplateView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPBasePlateDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NXPBasePlateDialog.this.onBackPressed();
            }
        });
        return nXPBaseplateView;
    }

    public void handleDataBackup(NXToyPlateInfo nXToyPlateInfo) {
        this.account.showDataBackup(getActivity(), nXToyPlateInfo.title, new NPListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NXPBasePlateDialog.2
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (NXPBasePlateDialog.this.plateListener != null) {
                    NXPBasePlateDialog.this.plateListener.onActionPerformedResult(nXToyResult);
                }
                NXPBasePlateDialog.this.dismiss();
            }
        });
    }

    public void handleForum(NXToyPlateInfo nXToyPlateInfo) {
        this.account.showForum(getActivity(), nXToyPlateInfo.meta.containsKey(NPPlateCodes.META_FORUM_ID) ? Integer.valueOf(nXToyPlateInfo.meta.get(NPPlateCodes.META_FORUM_ID).toString()).intValue() : -1);
    }

    public void handleMeta(NXToyPlateInfo nXToyPlateInfo) {
        NXToyPlateActionPerformedResult nXToyPlateActionPerformedResult = new NXToyPlateActionPerformedResult(0, "", "");
        nXToyPlateActionPerformedResult.requestTag = NXToyRequestTag.PlateButtonClick.getValue();
        if (nXToyPlateInfo.meta != null) {
            ToyLog.d("meta " + nXToyPlateInfo.meta.toString());
            nXToyPlateActionPerformedResult.result = new JSONObject(nXToyPlateInfo.meta).toString();
        } else {
            nXToyPlateActionPerformedResult.result = "";
        }
        if (this.plateListener != null) {
            this.plateListener.onActionPerformedResult(nXToyPlateActionPerformedResult);
        }
        dismiss();
    }

    public void handleURLExtern(NXToyPlateInfo nXToyPlateInfo) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(nXToyPlateInfo.meta.containsKey("url") ? nXToyPlateInfo.meta.get("url").toString() : ""));
        intent.addFlags(268435456);
        try {
            getActivity().startActivity(intent);
        } catch (Exception e) {
            ToyLog.d("exception :" + e);
        }
    }

    @Override // kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment
    public void onBackPressed() {
        if (this.plateListener != null) {
            NXToyResult nXToyResult = new NXToyResult(NXToyErrorCode.SUCCESS.getCode(), "", "");
            nXToyResult.requestTag = NXToyRequestTag.PlateClosed.getValue();
            this.plateListener.onActionPerformedResult(nXToyResult);
        }
        super.onBackPressed();
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setContentView(createView());
        }
    }

    @Override // kr.co.nexon.toy.android.ui.baseplate.view.NXPBaseplateView.BaseplateItemClickListener
    public void onItemClick(NXToyPlateInfo nXToyPlateInfo) {
        String str = nXToyPlateInfo.code;
        Activity activity = getActivity();
        if (str.equalsIgnoreCase(NPPlateCodes.CODE_NOTICE)) {
            this.account.showNotice(activity);
            return;
        }
        if (str.equalsIgnoreCase(NPPlateCodes.CODE_FAQ)) {
            this.account.showFAQ(activity);
            return;
        }
        if (str.equalsIgnoreCase("terms")) {
            this.account.showTermsList(activity, nXToyPlateInfo.title);
            return;
        }
        if (str.equalsIgnoreCase(NPPlateCodes.CODE_ACCOUNT)) {
            this.account.showMyAccount(activity, nXToyPlateInfo.title);
            return;
        }
        if (str.equalsIgnoreCase(NPPlateCodes.CODE_GAMEINFO)) {
            this.account.showGameInfo(activity);
            return;
        }
        if (str.equalsIgnoreCase(NPPlateCodes.CODE_CUSTOMER_CENTER)) {
            this.account.showHelpCenter(activity, this.params);
            return;
        }
        if (str.equalsIgnoreCase("url")) {
            handleURL(nXToyPlateInfo);
            return;
        }
        if (str.equalsIgnoreCase(NPPlateCodes.CODE_URL_EXTERN)) {
            handleURLExtern(nXToyPlateInfo);
            return;
        }
        if (str.equalsIgnoreCase(NPPlateCodes.CODE_META)) {
            handleMeta(nXToyPlateInfo);
            return;
        }
        if (str.equalsIgnoreCase(NPPlateCodes.CODE_FORUM)) {
            handleForum(nXToyPlateInfo);
            return;
        }
        if (str.equalsIgnoreCase(NPPlateCodes.CODE_DATA_BACKUP)) {
            handleDataBackup(nXToyPlateInfo);
            return;
        }
        if (str.equalsIgnoreCase(NPPlateCodes.CODE_DATA_RESTORE)) {
            handleDataRestore(nXToyPlateInfo);
            return;
        }
        if (str.equalsIgnoreCase(NPPlateCodes.CODE_PUSH_SETTING)) {
            handlePushSetting(nXToyPlateInfo);
        } else if (str.equalsIgnoreCase(NPPlateCodes.CODE_TODAY)) {
            this.account.showToday(activity, this.plateInfo.group, false, null);
        }
    }

    public void setPlateListener(NPPlateListener nPPlateListener) {
        this.plateListener = nPPlateListener;
    }
}
