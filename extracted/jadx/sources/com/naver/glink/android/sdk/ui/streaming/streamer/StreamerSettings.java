package com.naver.glink.android.sdk.ui.streaming.streamer;

import android.os.Parcel;
import android.os.Parcelable;
import com.naver.glink.android.sdk.a.n;
import com.naver.glink.android.sdk.api.streaming.AuthorizeResponse;
import com.naver.glink.android.sdk.api.streaming.StreamingKeyResponse;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class StreamerSettings implements Parcelable {
    public static final Parcelable.Creator<StreamerSettings> CREATOR = new Parcelable.Creator<StreamerSettings>() { // from class: com.naver.glink.android.sdk.ui.streaming.streamer.StreamerSettings.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public StreamerSettings createFromParcel(Parcel parcel) {
            return new StreamerSettings(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public StreamerSettings[] newArray(int i) {
            return new StreamerSettings[i];
        }
    };
    public String a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public VideoResolution f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    private int l;
    private long m;
    private boolean n;

    public enum VideoResolution {
        HIGH("720p", com.naver.glink.android.sdk.ui.record.c.c, 2048000),
        NORMAL("480p", com.naver.glink.android.sdk.ui.record.c.b, 1638400),
        LOW("360p", com.naver.glink.android.sdk.ui.record.c.a, 1228800);

        public final int bitRate;
        final String displayName;
        public final int size;

        VideoResolution(String str, int i, int i2) {
            this.displayName = str;
            this.size = i;
            this.bitRate = i2;
        }
    }

    protected StreamerSettings(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readByte() != 0;
        this.l = parcel.readInt();
        this.c = parcel.readByte() != 0;
        this.d = parcel.readByte() != 0;
        this.e = parcel.readByte() != 0;
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.m = parcel.readLong();
        this.n = parcel.readByte() != 0;
        this.f = (VideoResolution) parcel.readSerializable();
    }

    public StreamerSettings(AuthorizeResponse authorizeResponse) {
        this.a = "";
        this.b = authorizeResponse.selfCamera;
        this.l = authorizeResponse.publishActiveTime;
        this.c = true;
        this.d = this.b;
        this.f = VideoResolution.NORMAL;
    }

    void a(long j) {
        this.m += j;
    }

    void a(StreamingKeyResponse streamingKeyResponse) {
        this.g = streamingKeyResponse.streamingId;
        this.h = streamingKeyResponse.streamKey;
        this.i = streamingKeyResponse.streamUrl;
    }

    public void a(boolean z) {
        this.n = z;
    }

    public boolean a() {
        return this.n;
    }

    boolean b() {
        return this.m >= TimeUnit.SECONDS.toMillis((long) this.l);
    }

    String c() {
        return n.b((int) this.m);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeByte((byte) (this.b ? 1 : 0));
        parcel.writeInt(this.l);
        parcel.writeByte((byte) (this.c ? 1 : 0));
        parcel.writeByte((byte) (this.d ? 1 : 0));
        parcel.writeByte((byte) (this.e ? 1 : 0));
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeLong(this.m);
        parcel.writeByte((byte) (this.n ? 1 : 0));
        parcel.writeSerializable(this.f);
    }
}
