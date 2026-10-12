package twitter4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class OEmbedRequest implements Serializable {
    private static final long serialVersionUID = 7454130135274547901L;
    private String lang;
    private int maxWidth;
    private final long statusId;
    private final String url;
    private boolean hideMedia = true;
    private boolean hideThread = true;
    private boolean omitScript = false;
    private Align align = Align.NONE;
    private String[] related = new String[0];

    public enum Align {
        LEFT,
        CENTER,
        RIGHT,
        NONE;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static Align[] valuesCustom() {
            Align[] alignArrValuesCustom = values();
            int length = alignArrValuesCustom.length;
            Align[] alignArr = new Align[length];
            System.arraycopy(alignArrValuesCustom, 0, alignArr, 0, length);
            return alignArr;
        }
    }

    public OEmbedRequest(long j, String str) {
        this.statusId = j;
        this.url = str;
    }

    private void appendParameter(String str, long j, List<HttpParameter> list) {
        if (0 <= j) {
            list.add(new HttpParameter(str, String.valueOf(j)));
        }
    }

    private void appendParameter(String str, String str2, List<HttpParameter> list) {
        if (str2 != null) {
            list.add(new HttpParameter(str, str2));
        }
    }

    public OEmbedRequest HideMedia(boolean z) {
        this.hideMedia = z;
        return this;
    }

    public OEmbedRequest HideThread(boolean z) {
        this.hideThread = z;
        return this;
    }

    public OEmbedRequest MaxWidth(int i) {
        this.maxWidth = i;
        return this;
    }

    public OEmbedRequest align(Align align) {
        this.align = align;
        return this;
    }

    HttpParameter[] asHttpParameterArray() {
        ArrayList arrayList = new ArrayList(12);
        appendParameter("id", this.statusId, arrayList);
        appendParameter("url", this.url, arrayList);
        appendParameter("maxwidth", this.maxWidth, arrayList);
        arrayList.add(new HttpParameter("hide_media", this.hideMedia));
        arrayList.add(new HttpParameter("hide_thread", this.hideThread));
        arrayList.add(new HttpParameter("omit_script", this.omitScript));
        arrayList.add(new HttpParameter("align", this.align.name().toLowerCase()));
        if (this.related.length > 0) {
            appendParameter("related", StringUtil.join(this.related), arrayList);
        }
        appendParameter("lang", this.lang, arrayList);
        return (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()]);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OEmbedRequest oEmbedRequest = (OEmbedRequest) obj;
        if (this.hideMedia == oEmbedRequest.hideMedia && this.hideThread == oEmbedRequest.hideThread && this.maxWidth == oEmbedRequest.maxWidth && this.omitScript == oEmbedRequest.omitScript && this.statusId == oEmbedRequest.statusId && this.align == oEmbedRequest.align) {
            if (this.lang == null ? oEmbedRequest.lang != null : !this.lang.equals(oEmbedRequest.lang)) {
                return false;
            }
            if (!Arrays.equals(this.related, oEmbedRequest.related)) {
                return false;
            }
            if (this.url != null) {
                if (this.url.equals(oEmbedRequest.url)) {
                    return true;
                }
            } else if (oEmbedRequest.url == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((int) (this.statusId ^ (this.statusId >>> 32))) * 31) + (this.url != null ? this.url.hashCode() : 0)) * 31) + this.maxWidth) * 31) + (this.hideMedia ? 1 : 0)) * 31) + (this.hideThread ? 1 : 0)) * 31) + (this.omitScript ? 1 : 0)) * 31) + (this.align != null ? this.align.hashCode() : 0)) * 31) + (this.related != null ? Arrays.hashCode(this.related) : 0)) * 31) + (this.lang != null ? this.lang.hashCode() : 0);
    }

    public OEmbedRequest lang(String str) {
        this.lang = str;
        return this;
    }

    public OEmbedRequest omitScript(boolean z) {
        this.omitScript = z;
        return this;
    }

    public OEmbedRequest related(String[] strArr) {
        this.related = strArr;
        return this;
    }

    public void setAlign(Align align) {
        this.align = align;
    }

    public void setHideMedia(boolean z) {
        this.hideMedia = z;
    }

    public void setHideThread(boolean z) {
        this.hideThread = z;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public void setMaxWidth(int i) {
        this.maxWidth = i;
    }

    public void setOmitScript(boolean z) {
        this.omitScript = z;
    }

    public void setRelated(String[] strArr) {
        this.related = strArr;
    }

    public String toString() {
        return "OEmbedRequest{statusId=" + this.statusId + ", url='" + this.url + "', maxWidth=" + this.maxWidth + ", hideMedia=" + this.hideMedia + ", hideThread=" + this.hideThread + ", omitScript=" + this.omitScript + ", align=" + this.align + ", related=" + (this.related == null ? null : Arrays.asList(this.related)) + ", lang='" + this.lang + "'}";
    }
}
