package com.naver.glink.android.sdk.ui.write.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.d;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Text extends Content {
    public static final Parcelable.Creator<Text> CREATOR = new Parcelable.Creator<Text>() { // from class: com.naver.glink.android.sdk.ui.write.model.Text.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Text createFromParcel(Parcel parcel) {
            return new Text(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Text[] newArray(int i) {
            return new Text[i];
        }
    };
    String a;

    public Text(Responses.u.a aVar) {
        super(aVar);
        this.a = aVar.htmlTag;
    }

    public Text(String str) {
        super((Responses.u.a) null);
        this.a = str;
    }

    public Text(Map<String, Object> map) {
        super(map);
        this.a = (String) map.get("content");
    }

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    @Override // com.naver.glink.android.sdk.ui.write.model.Content
    Map<String, Object> b() {
        HashMap map = new HashMap();
        if (d.k()) {
            map.put("mediaType", "TEXT");
            map.put("htmlTag", this.a);
        } else {
            map.put("type", "text");
            map.put("content", this.a);
        }
        return map;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
    }
}
