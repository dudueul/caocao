package kr.co.nexon.toy.android.ui.baseplate.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.nexon.core.log.ToyLog;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.npaccount.R;
import java.util.List;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.toy.android.ui.common.view.NXPSimpleListArrowItem;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPBasePlateTermsListAdapter extends ArrayAdapter<NXToyTerm> {
    private static final int LISTIVEW_ITEM_REOUSCE_ID = R.layout.nxp_simple_list_view_arrow_item;
    private NXClickListener listViewClickLisenter;
    private TermsItemClickListener termsItemClickListener;

    public interface TermsItemClickListener {
        void onClickTerms(NXToyTerm nXToyTerm);
    }

    public NXPBasePlateTermsListAdapter(Context context, List<NXToyTerm> list) {
        super(context, LISTIVEW_ITEM_REOUSCE_ID, list);
        this.listViewClickLisenter = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.view.adapter.NXPBasePlateTermsListAdapter.1
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            protected void onSwallowClick(View view) {
                ToyLog.d("listview item clicked:" + view);
                if (NXPBasePlateTermsListAdapter.this.termsItemClickListener == null) {
                    return;
                }
                NXPSimpleListArrowItem nXPSimpleListArrowItem = (NXPSimpleListArrowItem) view;
                NXToyTerm item = NXPBasePlateTermsListAdapter.this.getItem(nXPSimpleListArrowItem.getPosition());
                ToyLog.d("clicked terms item position:" + nXPSimpleListArrowItem.getPosition() + "  text:" + ((Object) nXPSimpleListArrowItem.getText()));
                if (item != null) {
                    NXPBasePlateTermsListAdapter.this.termsItemClickListener.onClickTerms(item);
                }
            }
        };
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    @NonNull
    public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
        NXPSimpleListArrowItem nXPSimpleListArrowItem;
        NXToyTerm item = getItem(i);
        if (view == null) {
            nXPSimpleListArrowItem = (NXPSimpleListArrowItem) LayoutInflater.from(getContext()).inflate(LISTIVEW_ITEM_REOUSCE_ID, viewGroup, false);
            nXPSimpleListArrowItem.setOnClickListener(this.listViewClickLisenter);
        } else {
            if (!(view instanceof NXPSimpleListArrowItem)) {
                return view;
            }
            nXPSimpleListArrowItem = (NXPSimpleListArrowItem) view;
        }
        if (item != null) {
            nXPSimpleListArrowItem.setText(item.title);
            nXPSimpleListArrowItem.setPosition(i);
        }
        return nXPSimpleListArrowItem;
    }

    public void setOnTermsItemClickListener(TermsItemClickListener termsItemClickListener) {
        this.termsItemClickListener = termsItemClickListener;
    }
}
