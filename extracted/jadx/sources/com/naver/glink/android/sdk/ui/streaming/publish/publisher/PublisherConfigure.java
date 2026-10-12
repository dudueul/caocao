package com.naver.glink.android.sdk.ui.streaming.publish.publisher;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.naver.glink.android.sdk.ui.streaming.streamer.StreamerSettings;
import com.naver.vapp.broadcast.publisher.RTMPPublisher;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(21)
public class PublisherConfigure implements Parcelable {
    public static final Parcelable.Creator<PublisherConfigure> CREATOR = new Parcelable.Creator<PublisherConfigure>() { // from class: com.naver.glink.android.sdk.ui.streaming.publish.publisher.PublisherConfigure.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PublisherConfigure createFromParcel(Parcel parcel) {
            return new PublisherConfigure(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PublisherConfigure[] newArray(int i) {
            return new PublisherConfigure[i];
        }
    };
    static final String a = "audio/mp4a-latm";
    static final int b = 44100;
    static final int c = 1;
    static final int d = 131072;
    static final int e = 1024;
    static final String f = "video/avc";
    static final int g = 30;
    static final int h = 2;
    final String i;
    final int j;
    final int k;
    final int l;

    protected PublisherConfigure(Parcel parcel) {
        this.i = parcel.readString();
        this.j = parcel.readInt();
        this.k = parcel.readInt();
        this.l = parcel.readInt();
    }

    public PublisherConfigure(StreamerSettings streamerSettings) {
        this.i = streamerSettings.i;
        StreamerSettings.VideoResolution videoResolution = streamerSettings.f;
        Point pointB = com.naver.glink.android.sdk.d.p().b();
        if (com.naver.glink.android.sdk.d.i()) {
            this.j = a((videoResolution.size * pointB.x) / pointB.y);
            this.k = a(videoResolution.size);
        } else {
            this.j = a(videoResolution.size);
            this.k = a((videoResolution.size * pointB.y) / pointB.x);
        }
        this.l = videoResolution.bitRate;
    }

    private int a(int i) {
        return i % 16 == 0 ? i : ((i / 16) + 1) * 16;
    }

    RTMPPublisher.RTMPOptions a() {
        RTMPPublisher.RTMPOptions rTMPOptions = new RTMPPublisher.RTMPOptions();
        rTMPOptions.v_width = this.j;
        rTMPOptions.v_height = this.k;
        rTMPOptions.v_framerate = 30;
        rTMPOptions.v_bitrate = this.l;
        rTMPOptions.v_keyFrameInterval = 2;
        rTMPOptions.a_sampleRate = b;
        rTMPOptions.a_channels = 1;
        rTMPOptions.a_sampleSize = 16;
        return rTMPOptions;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.i);
        parcel.writeInt(this.j);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
    }
}
