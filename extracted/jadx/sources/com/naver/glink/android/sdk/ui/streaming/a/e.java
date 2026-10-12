package com.naver.glink.android.sdk.ui.streaming.a;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.api.streaming.ChattingMessage;

/* JADX INFO: compiled from: ChattingMessageViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class e {
    private final ImageView a;
    private final TextView b;

    e(View view) {
        this.a = (ImageView) view.findViewById(R.id.chatting_profile_image);
        this.b = (TextView) view.findViewById(R.id.chatting_contents);
    }

    private CharSequence a(ChattingMessage chattingMessage) {
        switch (chattingMessage.getType()) {
            case REACTION:
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(com.naver.glink.android.sdk.d.a(R.string.live_chatting_message_like, chattingMessage.contents));
                spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#f1f231")), 0, spannableStringBuilder.length(), 18);
                return spannableStringBuilder;
            case TEXT:
                if (chattingMessage.deleted) {
                    SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(com.naver.glink.android.sdk.d.a(R.string.delete_message));
                    spannableStringBuilder2.setSpan(new ForegroundColorSpan(Color.parseColor("#747474")), 0, spannableStringBuilder2.length(), 18);
                    return spannableStringBuilder2;
                }
                String str = chattingMessage.nickName != null ? chattingMessage.nickName : "";
                SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(str + " " + chattingMessage.contents);
                spannableStringBuilder3.setSpan(new ForegroundColorSpan(-1), 0, spannableStringBuilder3.length(), 18);
                spannableStringBuilder3.setSpan(new ForegroundColorSpan(chattingMessage.own ? Color.parseColor("#32cc42") : Color.parseColor("#999999")), 0, str.length(), 33);
                return spannableStringBuilder3;
            case UNKNOWN:
                SpannableStringBuilder spannableStringBuilder4 = new SpannableStringBuilder("현재 버전에서는 지원하지 않는 기능입니다.");
                spannableStringBuilder4.setSpan(new ForegroundColorSpan(Color.parseColor("#747474")), 0, spannableStringBuilder4.length(), 18);
                return spannableStringBuilder4;
            default:
                return "";
        }
    }

    public void a(Context context, ChattingMessage chattingMessage, boolean z) {
        if (!z) {
            switch (chattingMessage.getType()) {
                case REACTION:
                    Glide.with(context).load(Integer.valueOf(R.drawable.cf_icon_chatlike_s)).into(this.a);
                    break;
                default:
                    Glide.with(context).load(chattingMessage.deleted ? Integer.valueOf(R.drawable.cf_img_pfdefault) : chattingMessage.profileImageUrl).placeholder(R.drawable.cf_img_pfdefault).into(this.a);
                    break;
            }
        } else {
            this.a.setVisibility(8);
        }
        this.b.setText(a(chattingMessage));
    }
}
