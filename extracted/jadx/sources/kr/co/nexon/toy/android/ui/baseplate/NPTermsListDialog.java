package kr.co.nexon.toy.android.ui.baseplate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import java.util.List;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.terms.NXPTermsManager;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.baseplate.view.adapter.NXPBasePlateTermsListAdapter;
import kr.co.nexon.toy.android.ui.common.view.NXPSimpleListView;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPTermsListDialog extends NPDialogBase implements NXPBasePlateTermsListAdapter.TermsItemClickListener {
    public static final String KEY_TITLE = "title";
    public static final String TAG = "NPTermsListDialog";
    private List<NXToyTerm> termsList;
    private String title;

    public static NPTermsListDialog newInstance(Activity activity, String str) {
        NPTermsListDialog nPTermsListDialog = new NPTermsListDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("title", str);
        nPTermsListDialog.setArguments(bundle);
        return nPTermsListDialog;
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        NXPSimpleListView nXPSimpleListView = (NXPSimpleListView) View.inflate(this.applicationContext, R.layout.nxp_simple_list_view, null);
        this.title = getArguments().getString("title");
        nXPSimpleListView.setTitle(this.title);
        nXPSimpleListView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.NPTermsListDialog.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                NPTermsListDialog.this.dismiss();
            }
        });
        NXPBasePlateTermsListAdapter nXPBasePlateTermsListAdapter = new NXPBasePlateTermsListAdapter(getActivity(), this.termsList);
        nXPBasePlateTermsListAdapter.setOnTermsItemClickListener(this);
        nXPSimpleListView.setListAdapter(nXPBasePlateTermsListAdapter);
        return nXPSimpleListView;
    }

    @Override // kr.co.nexon.toy.android.ui.baseplate.view.adapter.NXPBasePlateTermsListAdapter.TermsItemClickListener
    public void onClickTerms(NXToyTerm nXToyTerm) {
        NXPTermsManager.getInstance().showTermsDetail(getActivity(), nXToyTerm.termID);
    }

    public void setTermsList(List<NXToyTerm> list) {
        if (list != null) {
            this.termsList = list;
        } else {
            this.termsList = new ArrayList();
        }
    }
}
