package com.naver.glink.android.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import com.naver.glink.android.sdk.ui.tabs.Tab;
import com.naver.glink.android.sdk.ui.write.model.WritingArticle;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class StartTo implements Parcelable {
    public static final Parcelable.Creator<StartTo> CREATOR = new Parcelable.Creator<StartTo>() { // from class: com.naver.glink.android.sdk.StartTo.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public StartTo createFromParcel(Parcel parcel) {
            return new StartTo((To) parcel.readSerializable(), (Tab.Type) parcel.readSerializable(), parcel.readInt(), parcel.readInt(), (WritingArticle) parcel.readParcelable(getClass().getClassLoader()));
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public StartTo[] newArray(int i) {
            return new StartTo[i];
        }
    };
    public final To a;
    public final Tab.Type b;
    public final int c;
    public final int d;
    public final WritingArticle e;

    public enum To {
        JUST_TAB,
        ARTICLE,
        WRITE,
        MENU,
        ALL_VIDEOS,
        ALL_IMAGES,
        STREAMING_VIEW
    }

    private StartTo(To to, Tab.Type type, int i, int i2, WritingArticle writingArticle) {
        this.a = to;
        this.b = type;
        this.c = i;
        this.d = i2;
        this.e = writingArticle;
    }

    static StartTo a() {
        return new StartTo(To.ALL_VIDEOS, Tab.Type.ALL_MEDIA, -1, -1, null);
    }

    static StartTo a(int i) {
        return new StartTo(To.ARTICLE, Tab.Type.BANNERS, -1, i, null);
    }

    public static StartTo a(Tab.Type type) {
        return new StartTo(To.JUST_TAB, type, -1, -1, null);
    }

    static StartTo a(WritingArticle writingArticle) {
        return new StartTo(To.WRITE, Tab.Type.BANNERS, -1, -1, writingArticle);
    }

    static StartTo b() {
        return new StartTo(To.ALL_IMAGES, Tab.Type.ALL_MEDIA, -1, -1, null);
    }

    static StartTo b(int i) {
        return new StartTo(To.MENU, Tab.Type.NOTICE, i, -1, null);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.a);
        parcel.writeSerializable(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeParcelable(this.e, i);
    }
}
