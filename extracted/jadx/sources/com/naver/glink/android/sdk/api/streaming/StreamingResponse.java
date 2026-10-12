package com.naver.glink.android.sdk.api.streaming;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;
import com.naver.plug.android.core.api.Response;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Keep
public class StreamingResponse extends Response {
    public static final int STREAMING_ENDED_ERROR_CODE = 11017;
    public Authorities authority;
    public String createDateTime;
    public int likeCount;
    public String linkUrl;
    public String liveId;
    public String nickName;
    public boolean own;
    public List<Quality> qualities = Collections.emptyList();
    public String streamerId;
    public String streamerProfileImageUrl;
    public int streamingStatus;
    public String thumbnailImage;
    public String title;
    public int viewCount;

    @Keep
    public static class Authorities implements Parcelable {
        public static final Parcelable.Creator<Authorities> CREATOR = new Parcelable.Creator<Authorities>() { // from class: com.naver.glink.android.sdk.api.streaming.StreamingResponse.Authorities.1
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Authorities createFromParcel(Parcel parcel) {
                return new Authorities(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Authorities[] newArray(int i) {
                return new Authorities[i];
            }
        };
        public Authority chat;
        public Authority deletion;
        public Authority like;
        public Authority report;

        protected Authorities(Parcel parcel) {
            this.chat = (Authority) parcel.readParcelable(Authority.class.getClassLoader());
            this.like = (Authority) parcel.readParcelable(Authority.class.getClassLoader());
            this.report = (Authority) parcel.readParcelable(Authority.class.getClassLoader());
            this.deletion = (Authority) parcel.readParcelable(Authority.class.getClassLoader());
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.chat, i);
            parcel.writeParcelable(this.like, i);
            parcel.writeParcelable(this.report, i);
            parcel.writeParcelable(this.deletion, i);
        }
    }

    @Keep
    public static class Authority implements Parcelable {
        public static final Parcelable.Creator<Authority> CREATOR = new Parcelable.Creator<Authority>() { // from class: com.naver.glink.android.sdk.api.streaming.StreamingResponse.Authority.1
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Authority createFromParcel(Parcel parcel) {
                return new Authority(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Authority[] newArray(int i) {
                return new Authority[i];
            }
        };
        public boolean able;
        public String errorMessage;

        protected Authority(Parcel parcel) {
            this.able = parcel.readByte() != 0;
            this.errorMessage = parcel.readString();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeByte((byte) (this.able ? 1 : 0));
            parcel.writeString(this.errorMessage);
        }
    }

    @Keep
    public static class Quality implements Parcelable {
        public static final Parcelable.Creator<Quality> CREATOR = new Parcelable.Creator<Quality>() { // from class: com.naver.glink.android.sdk.api.streaming.StreamingResponse.Quality.1
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Quality createFromParcel(Parcel parcel) {
                return new Quality(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Quality[] newArray(int i) {
                return new Quality[i];
            }
        };
        public boolean defaultVideo;
        public String qualityId;
        public String title;
        public String url;

        protected Quality(Parcel parcel) {
            this.defaultVideo = parcel.readByte() != 0;
            this.qualityId = parcel.readString();
            this.title = parcel.readString();
            this.url = parcel.readString();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeByte((byte) (this.defaultVideo ? 1 : 0));
            parcel.writeString(this.qualityId);
            parcel.writeString(this.title);
            parcel.writeString(this.url);
        }
    }

    public StreamingStatus getStreamingStatus() {
        return StreamingStatus.from(this.streamingStatus);
    }
}
