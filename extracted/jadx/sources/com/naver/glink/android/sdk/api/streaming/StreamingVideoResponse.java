package com.naver.glink.android.sdk.api.streaming;

import android.support.annotation.Keep;
import com.naver.plug.android.core.api.Response;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Keep
public class StreamingVideoResponse extends Response {
    private static final int STREAMING_VIDEO_NOT_EXIST_ERROR_CODE = 11010;
    public StreamingResponse.Authorities authority;
    public String createDateTime;
    public boolean encoded;
    public int likeCount;
    public String linkUrl;
    public String nickName;
    public boolean own;
    public float playTime;
    public List<StreamingResponse.Quality> qualities = Collections.emptyList();
    public String streamerId;
    public String streamerProfileImageUrl;
    public String streamingId;
    public String thumbnailImage;
    public String title;
    public String vid;
    public int viewCount;
}
