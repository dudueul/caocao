package com.naver.glink.android.sdk.api.streaming;

import android.support.annotation.Keep;
import com.naver.plug.android.core.api.Response;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Keep
public class StreamingHomeResponse extends Response {
    public StreamingsResponse live;
    public StreamingMembers popularStreamer;
    public StreamingVideosResponse vod;

    @Keep
    public static class Streamer {
        public boolean live;
        public String nickName;
        public boolean own;
        public String profileImageUrl;
        public String streamerId;
    }

    @Keep
    public static class StreamingMembers {
        public List<Streamer> streamers;
    }

    public boolean isEmpty() {
        return this.live.metadata.totalCount == 0 && this.vod.metadata.totalCount == 0;
    }
}
