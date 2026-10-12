package kr.co.nexon.npaccount.push;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPushMenuOption implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<NXPPushMenuOption>() { // from class: kr.co.nexon.npaccount.push.NXPPushMenuOption.1
        @Override // android.os.Parcelable.Creator
        public NXPPushMenuOption createFromParcel(Parcel parcel) {
            return new NXPPushMenuOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public NXPPushMenuOption[] newArray(int i) {
            return new NXPPushMenuOption[i];
        }
    };
    private String emailLabelName;
    private String nightPushLabelName;
    private String pushLabelName;
    private String smsLabelName;
    private String title;

    public NXPPushMenuOption() {
        this.title = "";
        this.pushLabelName = "";
        this.smsLabelName = "";
        this.nightPushLabelName = "";
        this.emailLabelName = "";
    }

    private NXPPushMenuOption(Parcel parcel) {
        readFromParcel(parcel);
    }

    public NXPPushMenuOption(String str, String str2, String str3) {
        this(str, str2, str3, null, null);
    }

    public NXPPushMenuOption(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, null);
    }

    public NXPPushMenuOption(String str, String str2, String str3, String str4, String str5) {
        this.title = str == null ? "" : str;
        this.pushLabelName = str2 == null ? "" : str2;
        this.smsLabelName = str3 == null ? "" : str3;
        this.nightPushLabelName = str4 == null ? "" : str4;
        this.emailLabelName = str5 == null ? "" : str5;
    }

    private void readFromParcel(Parcel parcel) {
        this.title = parcel.readString();
        this.pushLabelName = parcel.readString();
        this.smsLabelName = parcel.readString();
        this.nightPushLabelName = parcel.readString();
        this.emailLabelName = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getEmailLabelName() {
        return this.emailLabelName;
    }

    public String getNightPushLabelName() {
        return this.nightPushLabelName;
    }

    public String getPushLabelName() {
        return this.pushLabelName;
    }

    public String getSmsLabelName() {
        return this.smsLabelName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setEmailLabelName(String str) {
        this.emailLabelName = str;
    }

    public void setNightPushLabelName(String str) {
        this.nightPushLabelName = str;
    }

    public void setPushLabelName(String str) {
        this.pushLabelName = str;
    }

    public void setSmsLabelName(String str) {
        this.smsLabelName = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.pushLabelName);
        parcel.writeString(this.smsLabelName);
        parcel.writeString(this.nightPushLabelName);
        parcel.writeString(this.emailLabelName);
    }
}
