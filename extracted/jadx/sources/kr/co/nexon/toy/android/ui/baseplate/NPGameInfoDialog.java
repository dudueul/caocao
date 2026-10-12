package kr.co.nexon.toy.android.ui.baseplate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.google.gson.Gson;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.plate.result.NXToyGameInfoResult;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.baseplate.view.NXPGameInfoView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPGameInfoDialog extends NPDialogBase {
    public static final String KEY_GAME_INFO = "gameInfo";
    public static final String TAG = "NPGameInfoDialog";
    private NXToyGameInfoResult.ResultSet gameInfo;

    private int getGrbFieldImageDrawable(String str) {
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(getActivity());
        if (str.equals(nXToyLocaleManager.getString(R.string.npres_grbfield_sexuality))) {
            return R.drawable.icon_sexuality_72;
        }
        if (str.equals(nXToyLocaleManager.getString(R.string.npres_grbfield_violence))) {
            return R.drawable.icon_violence_72;
        }
        if (str.equals(nXToyLocaleManager.getString(R.string.npres_grbfield_drug))) {
            return R.drawable.icon_drug_72;
        }
        if (str.equals(nXToyLocaleManager.getString(R.string.npres_grbfield_horror))) {
            return R.drawable.icon_horror_72;
        }
        if (str.equals(nXToyLocaleManager.getString(R.string.npres_grbfield_crime))) {
            return R.drawable.icon_crime_72;
        }
        if (str.equals(nXToyLocaleManager.getString(R.string.npres_grbfield_language))) {
            return R.drawable.icon_language_72;
        }
        if (str.equals(nXToyLocaleManager.getString(R.string.npres_grbfield_gambling))) {
            return R.drawable.icon_gambling_72;
        }
        return 0;
    }

    private int getGrbRatingImageDrawable(int i) {
        switch (i) {
            case 0:
                return R.drawable.icon_all_72;
            case 12:
                return R.drawable.icon_12_72;
            case 15:
                return R.drawable.icon_15_72;
            case 18:
                return R.drawable.icon_18_72;
            case 99:
                return R.drawable.icon_test_72;
            default:
                return 0;
        }
    }

    private String getGrbRatingString(int i) {
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(getActivity());
        switch (i) {
            case 0:
                return nXToyLocaleManager.getString(R.string.npres_grbrating_all);
            case 12:
                return nXToyLocaleManager.getString(R.string.npres_grbrating_12);
            case 15:
                return nXToyLocaleManager.getString(R.string.npres_grbrating_15);
            case 18:
                return nXToyLocaleManager.getString(R.string.npres_grbrating_18);
            case 99:
                return nXToyLocaleManager.getString(R.string.npres_grbrating_test);
            default:
                return "";
        }
    }

    public static NPGameInfoDialog newInstance(Activity activity, String str) {
        NPGameInfoDialog nPGameInfoDialog = new NPGameInfoDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString(KEY_GAME_INFO, str);
        nPGameInfoDialog.setArguments(bundle);
        return nPGameInfoDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        NXPGameInfoView nXPGameInfoView = (NXPGameInfoView) View.inflate(this.applicationContext, R.layout.nxp_game_info_view, null);
        String string = getArguments().getString(KEY_GAME_INFO);
        if (NXStringUtil.isNotNull(string)) {
            this.gameInfo = (NXToyGameInfoResult.ResultSet) new Gson().fromJson(string, NXToyGameInfoResult.ResultSet.class);
        }
        if (this.gameInfo == null || !NXStringUtil.isNotNull(this.gameInfo.title)) {
            nXPGameInfoView.setTitle("Setting");
        } else {
            nXPGameInfoView.setTitle(this.gameInfo.title);
        }
        nXPGameInfoView.addGrbFieldRating(getGrbRatingImageDrawable(this.gameInfo.grbRating));
        for (int i = 0; i < this.gameInfo.grbField.size(); i++) {
            nXPGameInfoView.addGrbFieldRating(getGrbFieldImageDrawable(this.gameInfo.grbField.get(i)));
        }
        nXPGameInfoView.setAgeRating(getGrbRatingString(this.gameInfo.grbRating));
        nXPGameInfoView.setServiceTitleText(this.gameInfo.title);
        nXPGameInfoView.setCompanyNameText(this.gameInfo.company);
        nXPGameInfoView.setCompanyNoText(this.gameInfo.companyNo);
        nXPGameInfoView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPGameInfoDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPGameInfoDialog.this.dismiss();
            }
        });
        return nXPGameInfoView;
    }

    public void onCloseBtnClick(View view) {
        dismiss();
    }
}
