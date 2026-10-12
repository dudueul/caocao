package com.naver.glink.android.sdk.ui.streaming.viewer;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.naver.glink.android.sdk.api.streaming.StreamingResponse;
import com.naver.glink.android.sdk.api.streaming.StreamingVideoResponse;
import com.naver.glink.android.sdk.ui.streaming.StreamingReportFragment;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class WatchingStreaming implements Parcelable {
    public static final Parcelable.Creator<WatchingStreaming> CREATOR = new Parcelable.Creator<WatchingStreaming>() { // from class: com.naver.glink.android.sdk.ui.streaming.viewer.WatchingStreaming.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public WatchingStreaming createFromParcel(Parcel parcel) {
            return new WatchingStreaming(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public WatchingStreaming[] newArray(int i) {
            return new WatchingStreaming[i];
        }
    };
    public final Type a;
    public final String b;
    public final String c;
    public final boolean d;
    public String e;
    public final String f;
    public final String g;
    public final List<StreamingResponse.Quality> h;
    public final String i;
    public final StreamingResponse.Authorities j;
    public final int k;
    public final int l;
    public final String m;
    public final String n;
    public final int o;
    public final boolean p;
    private StreamingResponse.Quality q;

    public enum Type {
        STREAMING,
        VIDEO
    }

    protected WatchingStreaming(Parcel parcel) {
        this.a = (Type) parcel.readSerializable();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readByte() != 0;
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.createTypedArrayList(StreamingResponse.Quality.CREATOR);
        this.i = parcel.readString();
        this.j = (StreamingResponse.Authorities) parcel.readParcelable(StreamingResponse.Authorities.class.getClassLoader());
        this.k = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readString();
        this.n = parcel.readString();
        this.o = parcel.readInt();
        this.p = parcel.readByte() != 0;
        this.q = (StreamingResponse.Quality) parcel.readParcelable(StreamingResponse.Quality.class.getClassLoader());
    }

    public WatchingStreaming(StreamingVideoResponse streamingVideoResponse) {
        this.a = Type.VIDEO;
        this.b = streamingVideoResponse.streamingId;
        this.c = streamingVideoResponse.streamerId;
        this.d = streamingVideoResponse.own;
        this.e = streamingVideoResponse.title;
        this.f = streamingVideoResponse.nickName;
        this.g = streamingVideoResponse.streamerProfileImageUrl;
        this.h = a(streamingVideoResponse.qualities);
        this.i = streamingVideoResponse.linkUrl;
        this.j = streamingVideoResponse.authority;
        this.k = streamingVideoResponse.viewCount;
        this.l = streamingVideoResponse.likeCount;
        this.m = streamingVideoResponse.createDateTime;
        this.n = null;
        this.o = (int) (streamingVideoResponse.playTime * TimeUnit.SECONDS.toMillis(1L));
        this.p = streamingVideoResponse.encoded;
    }

    public WatchingStreaming(String str, StreamingResponse streamingResponse) {
        this.a = Type.STREAMING;
        this.b = str;
        this.c = streamingResponse.streamerId;
        this.d = streamingResponse.own;
        this.e = streamingResponse.title;
        this.f = streamingResponse.nickName;
        this.g = streamingResponse.streamerProfileImageUrl;
        this.h = a(streamingResponse.qualities);
        this.i = streamingResponse.linkUrl;
        this.j = streamingResponse.authority;
        this.k = streamingResponse.viewCount;
        this.l = streamingResponse.likeCount;
        this.m = streamingResponse.createDateTime;
        this.n = streamingResponse.liveId;
        this.o = -1;
        this.p = false;
    }

    private List<StreamingResponse.Quality> a(List<StreamingResponse.Quality> list) {
        ArrayList arrayList = new ArrayList();
        for (StreamingResponse.Quality quality : list) {
            if (!TextUtils.isEmpty(quality.url) && !TextUtils.equals(quality.url, "null")) {
                arrayList.add(quality);
            }
        }
        return arrayList;
    }

    private StreamingResponse.Quality e() {
        if (this.h == null || this.h.isEmpty()) {
            return null;
        }
        for (StreamingResponse.Quality quality : this.h) {
            if (quality.defaultVideo) {
                return quality;
            }
        }
        return this.h.get(0);
    }

    public void a(StreamingResponse.Quality quality) {
        this.q = quality;
    }

    public boolean a() {
        return this.a == Type.STREAMING;
    }

    public boolean b() {
        return this.a == Type.VIDEO;
    }

    StreamingResponse.Quality c() {
        return this.q != null ? this.q : e();
    }

    StreamingReportFragment.Report d() {
        return StreamingReportFragment.Report.a(this.b);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeByte((byte) (this.d ? 1 : 0));
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeTypedList(this.h);
        parcel.writeString(this.i);
        parcel.writeParcelable(this.j, i);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeInt(this.o);
        parcel.writeByte((byte) (this.p ? 1 : 0));
        parcel.writeParcelable(this.q, i);
    }
}
