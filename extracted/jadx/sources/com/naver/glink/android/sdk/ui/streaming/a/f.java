package com.naver.glink.android.sdk.ui.streaming.a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.a.x;
import com.naver.glink.android.sdk.api.streaming.ChattingMessage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ChattingMessagesAdapter.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class f extends ArrayAdapter<a> {
    private static final int a = 0;
    private boolean b;
    private b c;

    /* JADX INFO: compiled from: ChattingMessagesAdapter.java */
    public static class a {
        public final int a;
        public final ChattingMessage b;

        a(int i, ChattingMessage chattingMessage) {
            this.a = i;
            this.b = chattingMessage;
        }
    }

    /* JADX INFO: compiled from: ChattingMessagesAdapter.java */
    public interface b {
        void a(a aVar);
    }

    public f(Context context, boolean z) {
        super(context, 0);
        this.b = z;
    }

    private a b(ChattingMessage chattingMessage) {
        return new a(0, chattingMessage);
    }

    public void a(ChattingMessage chattingMessage) {
        int i;
        int i2 = 0;
        while (true) {
            i = i2;
            if (i >= getCount()) {
                i = -1;
                break;
            }
            a item = getItem(i);
            if (item != null && item.b != null && TextUtils.equals(item.b.streamingMessageId, chattingMessage.streamingMessageId)) {
                break;
            } else {
                i2 = i + 1;
            }
        }
        if (i != -1) {
            remove(getItem(i));
            chattingMessage.deleted = true;
            insert(b(chattingMessage), i);
            notifyDataSetChanged();
        }
    }

    void a(b bVar) {
        this.c = bVar;
    }

    public void a(boolean z, List<ChattingMessage> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<ChattingMessage> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(b(it.next()));
        }
        if (!z) {
            addAll(arrayList);
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            insert(arrayList.get(size), 0);
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        return getItem(i).a;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    @NonNull
    public View getView(int i, @Nullable View view, @NonNull ViewGroup viewGroup) {
        e eVar;
        switch (getItemViewType(i)) {
            case 0:
                if (view == null) {
                    view = LayoutInflater.from(getContext()).inflate(R.layout.item_chatting_message, viewGroup, false);
                    if (this.b) {
                        view.setPadding(0, x.a(2.0f), 0, x.a(2.0f));
                    } else {
                        view.setPadding(0, x.a(6.0f), 0, x.a(6.0f));
                    }
                }
                e eVar2 = (e) view.getTag();
                if (eVar2 == null) {
                    e eVar3 = new e(view);
                    view.setTag(eVar3);
                    eVar = eVar3;
                } else {
                    eVar = eVar2;
                }
                final a item = getItem(i);
                eVar.a(getContext(), item.b, this.b);
                view.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.a.f.1
                    @Override // com.naver.glink.android.sdk.a.t
                    public void a(View view2) {
                        if (f.this.c != null) {
                            f.this.c.a(item);
                        }
                    }
                });
                return view;
            default:
                throw new IllegalStateException("지원하지 않는 view type 입니다.");
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 1;
    }
}
