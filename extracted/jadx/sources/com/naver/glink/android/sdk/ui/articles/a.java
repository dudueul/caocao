package com.naver.glink.android.sdk.ui.articles;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.write.model.WritingArticle;

/* JADX INFO: compiled from: ArticlesHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.articles.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArticlesHelper.java */
    public interface InterfaceC0040a {
        void a();
    }

    public static CharSequence a(String str, String str2, int i) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        if (!TextUtils.isEmpty(str2)) {
            String lowerCase = str.toLowerCase();
            for (String str3 : TextUtils.split(str2.toLowerCase(), "\\s")) {
                if (!TextUtils.isEmpty(str3)) {
                    for (int iIndexOf = TextUtils.indexOf(lowerCase, str3); iIndexOf != -1; iIndexOf = TextUtils.indexOf(lowerCase, str3, iIndexOf + str3.length())) {
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(i), iIndexOf, str3.length() + iIndexOf, 33);
                    }
                }
            }
        }
        return spannableStringBuilder;
    }

    public static void a(View view, final int i, final InterfaceC0040a interfaceC0040a) {
        d.e().a(view, R.drawable.cf_btn_flwrite);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.articles.a.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                a.b(i);
                if (interfaceC0040a != null) {
                    interfaceC0040a.a();
                }
            }
        });
    }

    public static void a(View view, boolean z) {
        view.setVisibility(z ? 0 : 8);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.articles.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                com.naver.glink.android.sdk.ui.tabs.b.f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(int i) {
        if (i == 0) {
            i = -1;
        }
        com.naver.glink.android.sdk.ui.tabs.b.a(WritingArticle.a(i, -1).a());
    }
}
