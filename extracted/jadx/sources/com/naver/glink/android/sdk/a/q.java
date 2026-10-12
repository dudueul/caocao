package com.naver.glink.android.sdk.a;

import android.text.TextUtils;
import com.naver.glink.android.sdk.R;
import com.naver.plug.android.core.api.request.RequestHelper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ReadableDateFormat.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class q {
    private static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz", Locale.getDefault());
    private static String b;
    private static List<b> c;

    /* JADX INFO: compiled from: ReadableDateFormat.java */
    private static class a implements b {
        final long a;
        final String b;
        final DateFormat c;
        final List<a> d;

        a(long j, String str, DateFormat dateFormat) {
            this.a = j;
            this.b = str;
            this.c = dateFormat;
            this.d = Collections.emptyList();
        }

        a(List<a> list) {
            this.a = list.get(list.size() - 1).a;
            this.b = null;
            this.c = null;
            this.d = list;
        }

        @Override // com.naver.glink.android.sdk.a.q.b
        public String a(Date date, long j) {
            if (j >= this.a) {
                return null;
            }
            if (!this.d.isEmpty()) {
                Iterator<a> it = this.d.iterator();
                while (it.hasNext()) {
                    String strA = it.next().a(date, j);
                    if (strA != null) {
                        return strA;
                    }
                }
            } else {
                if (this.b != null) {
                    return this.b;
                }
                if (this.c != null) {
                    return this.c.format(date);
                }
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: ReadableDateFormat.java */
    private interface b {
        String a(Date date, long j);
    }

    /* JADX INFO: compiled from: ReadableDateFormat.java */
    private static class c implements b {
        final DateFormat a;

        private c() {
            this.a = DateFormat.getTimeInstance(3);
        }

        @Override // com.naver.glink.android.sdk.a.q.b
        public String a(Date date, long j) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(5, -1);
            if (calendar.get(1) == calendar2.get(1) && calendar.get(2) == calendar2.get(2) && calendar.get(5) == calendar2.get(5)) {
                return com.naver.glink.android.sdk.d.a(R.string.date_format_yesterday) + " " + this.a.format(date);
            }
            return null;
        }
    }

    static a a(long j, String str) {
        return new a(j, str, null);
    }

    static a a(long j, DateFormat dateFormat) {
        return new a(j, null, dateFormat);
    }

    static a a(List<a> list) {
        return new a(list);
    }

    static b a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(a(TimeUnit.MINUTES.toMillis(1L), com.naver.glink.android.sdk.d.a(R.string.date_format_just_now)));
        for (int i = 2; i <= 60; i++) {
            arrayList.add(a(TimeUnit.MINUTES.toMillis(i), com.naver.glink.android.sdk.d.a(R.string.date_format_mins, Integer.valueOf(i - 1))));
        }
        return a(arrayList);
    }

    public static String a(String str) {
        if (!TextUtils.equals(b, RequestHelper.getSystemLangCode()) || c == null) {
            b = RequestHelper.getSystemLangCode();
            c = new ArrayList();
            c.add(a());
            c.add(new c());
            c.add(b());
            c.add(a(TimeUnit.DAYS.toMillis(30L), DateFormat.getDateTimeInstance(2, 3)));
            c.add(a(Long.MAX_VALUE, DateFormat.getDateInstance(2)));
        }
        try {
            Date date = a.parse(str);
            long time = new Date().getTime() - date.getTime();
            Iterator<b> it = c.iterator();
            while (it.hasNext()) {
                String strA = it.next().a(date, time);
                if (strA != null) {
                    return strA;
                }
            }
            return str;
        } catch (Exception e) {
            return str;
        }
    }

    static b b() {
        ArrayList arrayList = new ArrayList();
        for (int i = 2; i <= 24; i++) {
            arrayList.add(a(TimeUnit.HOURS.toMillis(i), com.naver.glink.android.sdk.d.a(R.string.date_format_hrs, Integer.valueOf(i - 1))));
        }
        return a(arrayList);
    }
}
