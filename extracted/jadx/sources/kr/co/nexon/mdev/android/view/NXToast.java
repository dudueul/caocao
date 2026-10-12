package kr.co.nexon.mdev.android.view;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.nexon.npaccount.R;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToast extends Toast {
    public static final int ALIGN_TEXT_CENTER = 0;
    public static final int ALIGN_TEXT_LEFT = 1;
    public static final int ALIGN_TEXT_RIGHT = 2;
    Context mContext;

    public NXToast(Context context) {
        super(context);
        this.mContext = context;
    }

    public static NXToast make(Context context, int i, int i2, int i3, int i4) {
        return make(context, i, context.getResources().getText(i2), i3, i4);
    }

    public static NXToast make(Context context, int i, CharSequence charSequence, int i2, int i3) {
        NXToast nXToast = new NXToast(context);
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.nx_toast, (ViewGroup) null);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ivNXToast);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tvNXToast);
        if (i != 0) {
            imageView.setVisibility(0);
            imageView.setImageResource(i);
        } else {
            imageView.setVisibility(8);
        }
        if (i2 == 0) {
            textView.setGravity(17);
        } else if (i2 == 1) {
            textView.setGravity(19);
        } else if (i2 == 2) {
            textView.setGravity(21);
        }
        textView.setText(charSequence);
        nXToast.setView(viewInflate);
        nXToast.setDuration(i3);
        return nXToast;
    }

    public static NXToast makeText(Context context, int i, int i2, int i3) throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(i), i2, i3);
    }

    public static NXToast makeText(Context context, CharSequence charSequence, int i, int i2) {
        return make(context, 0, charSequence, i, i2);
    }
}
