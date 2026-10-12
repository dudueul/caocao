package com.naver.glink.android.sdk.api.streaming;

import android.support.annotation.Keep;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Keep
public enum StreamingStatus {
    UNKNOWN(-1, "unknown"),
    ASSIGN_CHANNEL(0, "채널 할당됨"),
    ON_AIR(1, "라이브 시작"),
    STOP(2, "라이브 종료"),
    ENCODING(3, "인코딩 중"),
    VIDEO(4, "비디오"),
    DELETED_VIDEO(5, "삭제된 비디오"),
    PUBLISH_DROPPED(10000, "가용량 문제로 우선순위 낮은 채널 송출 드랍"),
    VOD_GENERATE_FAILED(10001, "VOD 생성 실패"),
    BLOCK_STREAMING(10002, "라이브 게시 중단 처리"),
    BLOCK_VIDEO(10003, "동영상 게시 중단 처리"),
    INVALID_KEY_FRAME_INTERVAL(10004, "송출 key frame 간격이 4초 이상일 때, 송출 중단");

    public final int code;
    public final String desc;

    StreamingStatus(int i, String str) {
        this.code = i;
        this.desc = str;
    }

    public static StreamingStatus from(int i) {
        for (StreamingStatus streamingStatus : values()) {
            if (streamingStatus.code == i) {
                return streamingStatus;
            }
        }
        return UNKNOWN;
    }
}
