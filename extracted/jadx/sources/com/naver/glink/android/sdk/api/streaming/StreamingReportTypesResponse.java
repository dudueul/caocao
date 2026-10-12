package com.naver.glink.android.sdk.api.streaming;

import android.support.annotation.Keep;
import com.naver.plug.android.core.api.Response;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Keep
public class StreamingReportTypesResponse extends Response {
    public String maskedUserId;
    public List<ReportType> messageReportTypes;
    public List<ReportType> reportTypes;

    @Keep
    public static class ReportType {
        public String code;
        public boolean reason;
        public String text;
    }
}
