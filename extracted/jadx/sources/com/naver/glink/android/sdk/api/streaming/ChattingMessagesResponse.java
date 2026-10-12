package com.naver.glink.android.sdk.api.streaming;

import android.support.annotation.Keep;
import com.naver.plug.android.core.api.Response;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Keep
public class ChattingMessagesResponse extends Response {
    public List<ChattingMessage> messages = Collections.emptyList();
    public MetaData metaData;

    @Keep
    public static class MetaData {
        public boolean last;
        public String lastTimeStamp;
    }
}
