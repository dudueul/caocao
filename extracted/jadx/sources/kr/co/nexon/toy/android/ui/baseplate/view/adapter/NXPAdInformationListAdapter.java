package kr.co.nexon.toy.android.ui.baseplate.view.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import com.nexon.core.log.ToyLog;
import com.nexon.npaccount.R;
import java.util.List;
import kr.co.nexon.toy.android.ui.baseplate.view.adapter.model.NXPAdInformationItem;
import kr.co.nexon.toy.android.ui.common.view.NXPSimpleListSwitchItem;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPAdInformationListAdapter extends ArrayAdapter<NXPAdInformationItem> {
    private static final int LISTIVEW_ITEM_REOUSCE_ID;
    private ItemClickListener itemClickListener;
    private NXPSimpleListSwitchItem.CheckedChangedListener switchListener;

    public interface ItemClickListener {
        void onClickItem(CompoundButton compoundButton, NXPAdInformationItem nXPAdInformationItem);
    }

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            LISTIVEW_ITEM_REOUSCE_ID = R.layout.nxp_simple_list_view_switchcompat_item;
        } else {
            LISTIVEW_ITEM_REOUSCE_ID = R.layout.nxp_simple_list_view_switch_item;
        }
    }

    public NXPAdInformationListAdapter(Context context, List<NXPAdInformationItem> list) {
        super(context, LISTIVEW_ITEM_REOUSCE_ID, list);
        this.switchListener = new NXPSimpleListSwitchItem.CheckedChangedListener() { // from class: kr.co.nexon.toy.android.ui.baseplate.view.adapter.NXPAdInformationListAdapter.1
            @Override // kr.co.nexon.toy.android.ui.common.view.NXPSimpleListSwitchItem.CheckedChangedListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z, int i) {
                ToyLog.d("listview item changed:" + compoundButton + " isChecked:" + z + "  position:" + i);
                if (i < 0) {
                    return;
                }
                NXPAdInformationItem item = NXPAdInformationListAdapter.this.getItem(i);
                if (NXPAdInformationListAdapter.this.itemClickListener != null) {
                    NXPAdInformationListAdapter.this.itemClickListener.onClickItem(compoundButton, item);
                }
            }
        };
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    @NonNull
    public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
        NXPSimpleListSwitchItem nXPSimpleListSwitchItem;
        NXPAdInformationItem item = getItem(i);
        if (view == null) {
            nXPSimpleListSwitchItem = (NXPSimpleListSwitchItem) LayoutInflater.from(getContext()).inflate(LISTIVEW_ITEM_REOUSCE_ID, viewGroup, false);
            nXPSimpleListSwitchItem.setOnCheckedChangeListener(this.switchListener);
        } else {
            if (!(view instanceof NXPSimpleListSwitchItem)) {
                return view;
            }
            nXPSimpleListSwitchItem = (NXPSimpleListSwitchItem) view;
        }
        nXPSimpleListSwitchItem.setText(item.getTitle());
        nXPSimpleListSwitchItem.setChecked(item.isChecked());
        nXPSimpleListSwitchItem.setEnabled(item.isEnabled());
        nXPSimpleListSwitchItem.setPosition(i);
        return nXPSimpleListSwitchItem;
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void toggleSwitch(NXPAdInformationItem nXPAdInformationItem) {
        nXPAdInformationItem.setChecked(!nXPAdInformationItem.isChecked());
        notifyDataSetChanged();
    }
}
