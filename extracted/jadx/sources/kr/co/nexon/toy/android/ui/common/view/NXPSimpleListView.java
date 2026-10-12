package kr.co.nexon.toy.android.ui.common.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.nexon.core.log.ToyLog;
import com.nexon.npaccount.R;
import kr.co.nexon.mdev.android.view.NXPLinearLayout;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPSimpleListView extends NXPLinearLayout {
    private View closeButton;
    private View.OnClickListener closeButtonClickListener;
    private ListView listView;
    private TextView titleTextView;

    public NXPSimpleListView(Context context) {
        super(context);
    }

    public NXPSimpleListView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NXPSimpleListView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // kr.co.nexon.mdev.android.view.NXPLinearLayout
    protected void initView() {
        View viewFindViewById = findViewById(R.id.backBtn);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(4);
        }
        this.listView = (ListView) findViewById(R.id.simple_list_view);
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.closeButton = findViewById(R.id.closeBtn);
        if (this.closeButton != null) {
            this.closeButton.setOnClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.common.view.NXPSimpleListView.1
                @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
                protected void onSwallowClick(View view) {
                    if (NXPSimpleListView.this.closeButtonClickListener != null) {
                        NXPSimpleListView.this.closeButtonClickListener.onClick(view);
                    }
                }
            });
        }
    }

    public void setListAdapter(ListAdapter listAdapter) {
        if (listAdapter == null) {
            ToyLog.d("BasePlatView Adapter is null");
        } else if (this.listView != null) {
            this.listView.setAdapter(listAdapter);
        }
    }

    public void setOnCloseButtonClickListener(View.OnClickListener onClickListener) {
        this.closeButtonClickListener = onClickListener;
    }

    public void setTitle(String str) {
        if (this.titleTextView != null) {
            this.titleTextView.setText(str);
        }
    }
}
