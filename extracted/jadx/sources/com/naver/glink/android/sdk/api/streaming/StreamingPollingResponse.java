package com.naver.glink.android.sdk.api.streaming;

import android.support.annotation.Keep;
import com.naver.plug.android.core.api.Response;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Keep
public class StreamingPollingResponse extends Response {
    public Count count;
    public OliveStat oliveStat;
    public StreamingStat streamingStatus;
    public String title;

    @Keep
    public static class Count {
        public int cv;
    }

    @Keep
    public static class OliveStat {
        public int cv;
        public String liveId;
        public int pv;
        public String status;
    }

    @Keep
    public static class StreamingStat {
        public boolean error;
        public String message;
        private int streamingStatus;

        public StreamingStatus status() {
            return StreamingStatus.from(this.streamingStatus);
        }
    }
}
