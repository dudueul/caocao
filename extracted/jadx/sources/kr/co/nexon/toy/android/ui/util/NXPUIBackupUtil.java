package kr.co.nexon.toy.android.ui.util;

import android.app.Dialog;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPUIBackupUtil {

    public static class ViewData {
        boolean enabled;
        boolean isChecked;
        int resouceId;
        CharSequence text;
        int visibillity;
    }

    public static List<ViewData> backupViewDataFromDialog(Dialog dialog, List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        if (dialog != null && list != null) {
            for (Integer num : list) {
                View viewFindViewById = dialog.findViewById(num.intValue());
                if (viewFindViewById == null) {
                    ToyLog.d("In getViewDataFromDialog, resouceId [" + num + "]  not found");
                } else {
                    ViewData viewData = new ViewData();
                    viewData.resouceId = num.intValue();
                    viewData.enabled = viewFindViewById.isEnabled();
                    viewData.visibillity = viewFindViewById.getVisibility();
                    if (viewFindViewById instanceof TextView) {
                        viewData.text = ((TextView) viewFindViewById).getText();
                    }
                    if (viewFindViewById instanceof CompoundButton) {
                        viewData.isChecked = ((CompoundButton) viewFindViewById).isChecked();
                    }
                    arrayList.add(viewData);
                }
            }
        }
        return arrayList;
    }

    public static void restoreViewDataToDialog(Dialog dialog, List<ViewData> list) {
        if (dialog == null || list == null) {
            return;
        }
        for (ViewData viewData : list) {
            View viewFindViewById = dialog.findViewById(viewData.resouceId);
            if (viewFindViewById == null) {
                ToyLog.d("In restoreViewDataToDialog, resouceId [" + viewData.resouceId + "]  not found");
            } else {
                viewFindViewById.setEnabled(viewData.enabled);
                viewFindViewById.setVisibility(viewData.visibillity);
                if (viewFindViewById instanceof TextView) {
                    ((TextView) viewFindViewById).setText(viewData.text);
                }
                if (viewFindViewById instanceof CompoundButton) {
                    ((CompoundButton) viewFindViewById).setChecked(viewData.isChecked);
                }
            }
        }
    }
}
