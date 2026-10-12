package com.naver.glink.android.sdk.api.streaming;

import android.support.annotation.Keep;
import com.naver.plug.android.core.api.Response;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Keep
public class StreamingsResponse extends Response {
    public Metadata metadata;
    public List<Streaming> streamings;

    @Keep
    public static class Metadata {
        public boolean last;
        public String lastStreamingId;
        public int totalCount;
    }

    @Keep
    public static class Streaming {
        public String createDateTime;
        public int likeCount;
        public String liveId;
        public String nickName;
        public boolean own;
        public String streamerId;
        public String streamerProfileImageUrl;
        public String streamingId;
        private int streamingStatus;
        public String thumbnailImage;
        public String title;
        public int viewCount;

        public StreamingStatus streamingStatus() {
            return StreamingStatus.from(this.streamingStatus);
        }
    }
}
