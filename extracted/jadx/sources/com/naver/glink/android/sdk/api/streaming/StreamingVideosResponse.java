package com.naver.glink.android.sdk.api.streaming;

import android.support.annotation.Keep;
import com.naver.plug.android.core.api.Response;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Keep
public class StreamingVideosResponse extends Response {
    public Metadata metadata;
    public List<StreamingVideo> streamingVideos;

    @Keep
    public static class Metadata {
        public boolean last;
        public String lastStreamingId;
        public int totalCount;
    }

    @Keep
    public static class StreamingVideo {
        public String createDateTime;
        public boolean encoded;
        public int likeCount;
        public String nickName;
        public boolean own;
        public float playTime;
        public String streamerId;
        public String streamerProfileImageUrl;
        public String streamingId;
        public String thumbnailImage;
        public String title;
        public String vid;
        public int videoLikeCount;
        public int viewCount;
    }
}
