package twitter4j.util;

import com.naver.glink.android.sdk.ChannelCodes;
import java.io.Serializable;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class TimeSpanConverter implements Serializable {
    private static final int AN_HOUR_AGO = 4;
    private static final int A_MINUTE_AGO = 2;
    private static final int NOW = 0;
    private static final int N_HOURS_AGO = 5;
    private static final int N_MINUTES_AGO = 3;
    private static final int N_SECONDS_AGO = 1;
    private static final int ONE_DAY_IN_SECONDS = 86400;
    private static final int ONE_HOUR_IN_SECONDS = 3600;
    private static final int ONE_MONTH_IN_SECONDS = 2592000;
    private static final long serialVersionUID = 8665013607650804076L;
    private final SimpleDateFormat dateMonth;
    private final SimpleDateFormat dateMonthYear;
    private final MessageFormat[] formats;

    public TimeSpanConverter() {
        this(Locale.getDefault());
    }

    public TimeSpanConverter(Locale locale) {
        this.formats = new MessageFormat[6];
        String language = locale.getLanguage();
        if (ChannelCodes.ITALIAN.equals(language)) {
            this.formats[0] = new MessageFormat("Ora");
            this.formats[1] = new MessageFormat("{0} secondi fa");
            this.formats[2] = new MessageFormat("1 minuto fa");
            this.formats[3] = new MessageFormat("{0} minuti fa");
            this.formats[4] = new MessageFormat("1 ora fa");
            this.formats[5] = new MessageFormat("{0} ore fa");
            this.dateMonth = new SimpleDateFormat("d MMM", locale);
            this.dateMonthYear = new SimpleDateFormat("d MMM yy", locale);
            return;
        }
        if ("kr".equals(language)) {
            this.formats[0] = new MessageFormat("지금");
            this.formats[1] = new MessageFormat("{0}초 전");
            this.formats[2] = new MessageFormat("1분 전");
            this.formats[3] = new MessageFormat("{0}분 전");
            this.formats[4] = new MessageFormat("1시간 전");
            this.formats[5] = new MessageFormat("{0} ore fa");
            this.dateMonth = new SimpleDateFormat("M월 d일", locale);
            this.dateMonthYear = new SimpleDateFormat("yy년 M월 d일", locale);
            return;
        }
        if (ChannelCodes.SPANISH.equals(language)) {
            this.formats[0] = new MessageFormat("Ahora");
            this.formats[1] = new MessageFormat("hace {0} segundos");
            this.formats[2] = new MessageFormat("hace 1 minuto");
            this.formats[3] = new MessageFormat("hace {0} minutos");
            this.formats[4] = new MessageFormat("hace 1 hora");
            this.formats[5] = new MessageFormat("hace {0} horas");
            this.dateMonth = new SimpleDateFormat("d MMM", locale);
            this.dateMonthYear = new SimpleDateFormat("d MMM yy", locale);
            return;
        }
        if (ChannelCodes.FRENCH.equals(language)) {
            this.formats[0] = new MessageFormat("Maintenant");
            this.formats[1] = new MessageFormat("Il y a {0} secondes");
            this.formats[2] = new MessageFormat("Il y a 1 minute");
            this.formats[3] = new MessageFormat("Il y a {0} minutes");
            this.formats[4] = new MessageFormat("Il y a 1 heure");
            this.formats[5] = new MessageFormat("Il y a {0} heures");
            this.dateMonth = new SimpleDateFormat("d MMM", locale);
            this.dateMonthYear = new SimpleDateFormat("d MMM yy", locale);
            return;
        }
        if (ChannelCodes.GERMAN.equals(language)) {
            this.formats[0] = new MessageFormat("Jetzt");
            this.formats[1] = new MessageFormat("vor {0} Sekunden");
            this.formats[2] = new MessageFormat("vor 1 Minute");
            this.formats[3] = new MessageFormat("vor {0} Minuten");
            this.formats[4] = new MessageFormat("vor 1 Stunde");
            this.formats[5] = new MessageFormat("vor {0} Stunden");
            this.dateMonth = new SimpleDateFormat("d MMM", locale);
            this.dateMonthYear = new SimpleDateFormat("d MMM yy", locale);
            return;
        }
        if (ChannelCodes.JAPANESE.equals(language)) {
            this.formats[0] = new MessageFormat("今");
            this.formats[1] = new MessageFormat("{0}秒前");
            this.formats[2] = new MessageFormat("1分前");
            this.formats[3] = new MessageFormat("{0}分前");
            this.formats[4] = new MessageFormat("1時間前");
            this.formats[5] = new MessageFormat("{0}時間前");
            this.dateMonth = new SimpleDateFormat("M月d日", locale);
            this.dateMonthYear = new SimpleDateFormat("yy年M月d日", locale);
            return;
        }
        this.formats[0] = new MessageFormat("now");
        this.formats[1] = new MessageFormat("{0} seconds ago");
        this.formats[2] = new MessageFormat("1 minute ago");
        this.formats[3] = new MessageFormat("{0} minutes ago");
        this.formats[4] = new MessageFormat("1 hour ago");
        this.formats[5] = new MessageFormat("{0} hours ago");
        this.dateMonth = new SimpleDateFormat("d MMM", Locale.ENGLISH);
        this.dateMonthYear = new SimpleDateFormat("d MMM yy", Locale.ENGLISH);
    }

    private String toTimeSpanString(int i) {
        if (i <= 1) {
            return this.formats[0].format(null);
        }
        if (i < 60) {
            return this.formats[1].format(new Object[]{Integer.valueOf(i)});
        }
        if (i >= 2700) {
            return i < 6300 ? this.formats[4].format(null) : this.formats[5].format(new Object[]{Integer.valueOf((i + 900) / 3600)});
        }
        int i2 = i / 60;
        return i2 == 1 ? this.formats[2].format(null) : this.formats[3].format(new Object[]{Integer.valueOf(i2)});
    }

    public String toTimeSpanString(long j) {
        int iCurrentTimeMillis = (int) ((System.currentTimeMillis() - j) / 1000);
        if (iCurrentTimeMillis >= ONE_DAY_IN_SECONDS) {
            return iCurrentTimeMillis >= ONE_MONTH_IN_SECONDS ? this.dateMonthYear.format(new Date(j)) : this.dateMonth.format(new Date(j));
        }
        return toTimeSpanString(iCurrentTimeMillis);
    }

    public String toTimeSpanString(Date date) {
        return toTimeSpanString(date.getTime());
    }
}
