package com.naver.glink.android.sdk.ui.write.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.naver.glink.android.sdk.a.w;
import com.naver.glink.android.sdk.a.y;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.model.ThumbnailUri;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ImageAttachment extends Attachment {
    public static final Parcelable.Creator<ImageAttachment> CREATOR = new Parcelable.Creator<ImageAttachment>() { // from class: com.naver.glink.android.sdk.ui.write.model.ImageAttachment.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ImageAttachment createFromParcel(Parcel parcel) {
            return new ImageAttachment((Uri) parcel.readParcelable(getClass().getClassLoader()));
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ImageAttachment[] newArray(int i) {
            return new ImageAttachment[i];
        }
    };
    public final Uri d;

    public ImageAttachment(Uri uri) {
        super((Responses.u.a) null);
        this.d = uri;
    }

    public ImageAttachment(Responses.u.a aVar) {
        super(aVar);
        this.d = null;
    }

    public ImageAttachment(Map<String, Object> map) {
        super(map);
        this.d = null;
    }

    private String j() {
        if (this.c != null) {
            return (String) this.c.get("thumbnail");
        }
        return null;
    }

    @Override // com.naver.glink.android.sdk.ui.write.model.Content
    Map<String, Object> b() {
        if (this.a == null) {
            return super.b();
        }
        if (this.a instanceof Responses.e) {
            Responses.e eVar = (Responses.e) this.a;
            HashMap map = new HashMap();
            map.put("mediaType", ShareConstants.IMAGE_URL);
            map.put("htmlTag", w.b(eVar.imageHtmlTag));
            map.put("param", w.b(eVar.attachfiles));
            return map;
        }
        com.naver.glink.android.sdk.api.a.b bVar = (com.naver.glink.android.sdk.api.a.b) this.a;
        HashMap map2 = new HashMap();
        map2.put("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO);
        map2.put("url", bVar.url);
        map2.put("path", bVar.path);
        map2.put("fileName", bVar.fileName);
        map2.put("width", Integer.valueOf(bVar.width));
        map2.put("height", Integer.valueOf(bVar.height));
        map2.put("colorSpace", bVar.colorSpace);
        map2.put("fileSize", Integer.valueOf(bVar.fileSize));
        map2.put("thumbnail", bVar.thumbnail);
        map2.put("animatedCnt", Integer.valueOf(bVar.animatedCnt));
        return map2;
    }

    public int c() {
        if (this.c != null) {
            Object obj = this.c.get("width");
            if (obj instanceof Integer) {
                return ((Integer) obj).intValue();
            }
        }
        return 0;
    }

    public int d() {
        if (this.c != null) {
            Object obj = this.c.get("height");
            if (obj instanceof Integer) {
                return ((Integer) obj).intValue();
            }
        }
        return 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int e() {
        if (this.c != null) {
            Object obj = this.c.get("animatedCnt");
            if (obj instanceof Integer) {
                return ((Integer) obj).intValue();
            }
        }
        return 0;
    }

    public boolean f() {
        return e() > 1;
    }

    public Uri g() {
        return ThumbnailUri.getViewer(j());
    }

    public Uri h() {
        return ThumbnailUri.getArticle(j());
    }

    public Uri i() {
        if (this.d != null) {
            return this.d;
        }
        if (this.b != null) {
            return y.a(this.b.thumbnail.replace("type=f1", "type=s3"));
        }
        if (this.c != null) {
            return ThumbnailUri.getArticle(j());
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.d, i);
    }
}
