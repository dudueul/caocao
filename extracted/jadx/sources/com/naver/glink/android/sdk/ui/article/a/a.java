package com.naver.glink.android.sdk.ui.article.a;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.a.v;
import com.naver.glink.android.sdk.ui.article.ArticleFragmentView;

/* JADX INFO: compiled from: CommentViewHolder.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    private final View a;
    private final ImageView b;
    private final ImageView c;
    private final TextView d;
    private final TextView e;
    private final TextView f;
    private final View g;
    private final TextView h;
    private final TextView i;
    private final View j;
    private final TextView k;
    private final View l;

    public a(View view) {
        this.a = view;
        this.b = (ImageView) view.findViewById(R.id.writer_profile_image);
        this.c = (ImageView) view.findViewById(R.id.staff_image);
        this.d = (TextView) view.findViewById(R.id.writer_nickname);
        this.e = (TextView) view.findViewById(R.id.write_date);
        this.f = (TextView) view.findViewById(R.id.contents);
        this.g = view.findViewById(R.id.more_actions);
        this.h = (TextView) view.findViewById(R.id.reply);
        this.i = (TextView) view.findViewById(R.id.translate);
        this.j = view.findViewById(R.id.more_replies);
        this.k = (TextView) view.findViewById(R.id.more_replies_message);
        this.l = view.findViewById(R.id.comment_divider);
    }

    private CharSequence a(int i) {
        String strA = com.naver.glink.android.sdk.d.a(R.string.replies_more_view, Integer.valueOf(i));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(strA);
        String strValueOf = String.valueOf(i);
        int iIndexOf = strA.indexOf(strValueOf);
        if (iIndexOf != -1) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#3b7adb")), iIndexOf, strValueOf.length() + iIndexOf, 33);
        }
        return spannableStringBuilder;
    }

    private CharSequence a(ArticleFragmentView articleFragmentView, com.naver.glink.android.sdk.api.requests.b.c.a aVar, com.naver.glink.android.sdk.ui.article.a.C0038a c0038a) {
        if (aVar.deleted) {
            return com.naver.glink.android.sdk.d.a(R.string.deleted_comment);
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(c0038a.a ? c0038a.b : aVar.e());
        if (aVar.mentions != null) {
            for (String str : aVar.mentions.keySet()) {
                String str2 = "@" + str;
                String strB = v.b(aVar.mentions.get(str));
                int iIndexOf = TextUtils.indexOf(spannableStringBuilder, str2);
                if (iIndexOf != -1) {
                    spannableStringBuilder.replace(iIndexOf, str2.length() + iIndexOf, (CharSequence) strB);
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(articleFragmentView.getContext(), R.color.black2)), iIndexOf, strB.length() + iIndexOf, 33);
                }
            }
        }
        return spannableStringBuilder;
    }

    private boolean a(com.naver.glink.android.sdk.api.requests.b.c.a aVar, boolean z) {
        return ((aVar == null) || !z || aVar.c()) ? false : true;
    }

    public void a(final ArticleFragmentView articleFragmentView, final com.naver.glink.android.sdk.api.requests.b.c.a aVar, com.naver.glink.android.sdk.api.requests.b.c.a aVar2, boolean z, com.naver.glink.android.sdk.ui.article.a.C0038a c0038a) {
        if (articleFragmentView == null) {
            return;
        }
        Glide.with(articleFragmentView.getContext()).load(aVar.userProfileImage).asBitmap().error(R.drawable.cf_img_pfdefault).fitCenter().into(new BitmapImageViewTarget(this.b) { // from class: com.naver.glink.android.sdk.ui.article.a.a.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.bumptech.glide.request.target.BitmapImageViewTarget, com.bumptech.glide.request.target.ImageViewTarget
            public void setResource(Bitmap bitmap) {
                RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(articleFragmentView.getResources(), bitmap);
                roundedBitmapDrawableCreate.setCircular(true);
                setDrawable(roundedBitmapDrawableCreate);
            }
        });
        this.b.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.article.a.a.2
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                if (aVar.deleted) {
                    return;
                }
                articleFragmentView.a(aVar.userIdNo, aVar.mine);
            }
        });
        if (aVar.deleted) {
            this.c.setVisibility(8);
        } else {
            ArticleFragmentView.setStaffIconResource(this.c, aVar.k());
        }
        this.d.setVisibility(aVar.deleted ? 8 : 0);
        this.d.setText(aVar.userName);
        this.d.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.article.a.a.3
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                if (aVar.deleted) {
                    return;
                }
                articleFragmentView.a(aVar.userIdNo, aVar.mine);
            }
        });
        this.e.setText(aVar.a());
        this.f.setTextColor(ContextCompat.getColor(articleFragmentView.getContext(), aVar.deleted ? R.color.gray4 : R.color.gray7));
        this.f.setText(a(articleFragmentView, aVar, c0038a));
        boolean z2 = z && aVar.c();
        this.g.setVisibility((!aVar.mine || aVar.deleted || z2) ? 8 : 0);
        this.g.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.article.a.a.4
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                articleFragmentView.a(view, aVar);
            }
        });
        this.h.setVisibility((aVar.deleted || z2) ? 8 : 0);
        this.h.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.article.a.a.5
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                articleFragmentView.a(aVar, true);
            }
        });
        boolean z3 = !aVar.deleted && com.naver.glink.android.sdk.d.s();
        this.h.setCompoundDrawablesWithIntrinsicBounds(0, 0, z3 ? R.drawable.oval_gray_2dp : 0, 0);
        this.i.setVisibility(z3 ? 0 : 8);
        this.i.setText(c0038a.a ? com.naver.glink.android.sdk.d.a(R.string.view_original) : com.naver.glink.android.sdk.d.a(R.string.translation));
        this.i.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.article.a.a.6
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                articleFragmentView.a(aVar);
            }
        });
        if (z2) {
            t tVar = new t() { // from class: com.naver.glink.android.sdk.ui.article.a.a.7
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view) {
                    articleFragmentView.a(aVar.parentCommentNo, false);
                }
            };
            this.a.setOnClickListener(tVar);
            this.f.setOnClickListener(tVar);
        } else {
            this.a.setOnClickListener(null);
            this.f.setOnClickListener(null);
        }
        int i = aVar.replyCount - 3;
        this.j.setVisibility((!z || i <= 0) ? 8 : 0);
        this.k.setText(a(i));
        this.j.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.article.a.a.8
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view) {
                articleFragmentView.a(aVar, false);
            }
        });
        this.l.setVisibility(a(aVar2, z) ? 0 : 8);
    }
}
