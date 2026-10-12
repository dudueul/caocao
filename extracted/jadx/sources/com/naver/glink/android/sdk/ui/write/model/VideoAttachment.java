package com.naver.glink.android.sdk.ui.write.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.naver.glink.android.sdk.a.w;
import com.naver.glink.android.sdk.a.y;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.requests.h;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class VideoAttachment extends Attachment {
    public static final Parcelable.Creator<VideoAttachment> CREATOR = new Parcelable.Creator<VideoAttachment>() { // from class: com.naver.glink.android.sdk.ui.write.model.VideoAttachment.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public VideoAttachment createFromParcel(Parcel parcel) {
            return new VideoAttachment((Uri) parcel.readParcelable(getClass().getClassLoader()));
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public VideoAttachment[] newArray(int i) {
            return new VideoAttachment[i];
        }
    };
    public final Uri d;

    public VideoAttachment(Uri uri) {
        super((Responses.u.a) null);
        this.d = uri;
    }

    VideoAttachment(Responses.u.a aVar) {
        super(aVar);
        this.d = null;
    }

    VideoAttachment(Map<String, Object> map) {
        super(map);
        this.d = null;
    }

    @Override // com.naver.glink.android.sdk.ui.write.model.Content
    Map<String, Object> b() {
        if (this.a == null) {
            return super.b();
        }
        if (this.a instanceof Responses.f) {
            Responses.f fVar = (Responses.f) this.a;
            HashMap map = new HashMap();
            map.put("mediaType", "MOVIE");
            map.put("htmlTag", w.b(fVar.movieHtmlTag));
            map.put("param", w.b(fVar.attachmovielist));
            return map;
        }
        if (!(this.a instanceof h.e)) {
            return new HashMap();
        }
        h.e eVar = (h.e) this.a;
        HashMap map2 = new HashMap();
        map2.put("type", "video");
        map2.put("vId", w.b(eVar.data.videoId));
        map2.put("authFlag", w.b("N"));
        map2.put("sourceWidth", w.b(eVar.data.sourceWidth));
        map2.put("sourceHeight", w.b(eVar.data.sourceHeight));
        map2.put("originalWidth", w.b(eVar.data.sourceWidth));
        map2.put("originalHeight", w.b(eVar.data.sourceHeight));
        map2.put("fileSize", w.b(eVar.data.fileSize));
        map2.put("playTime", w.b(eVar.data.playTime));
        map2.put("logoImage", w.b(eVar.data.logoImage));
        return map2;
    }

    public String c() {
        if (this.c != null) {
            return this.c.get("vId") + "";
        }
        return null;
    }

    public String d() {
        if (this.c != null) {
            return this.c.get("logoImage") + "";
        }
        return null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Uri e() {
        if (this.d != null) {
            return this.d;
        }
        if (this.b != null) {
            return y.a(this.b.thumbnail);
        }
        if (this.c != null) {
            return y.a(this.c.get("logoImage") + "");
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.d, i);
    }
}
