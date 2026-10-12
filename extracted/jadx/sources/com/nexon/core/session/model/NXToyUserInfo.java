package com.nexon.core.session.model;

import com.nexon.core.requestpostman.result.NXClassInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyUserInfo extends NXClassInfo {
    public String agcId;
    public int age_range_max;
    public int age_range_min;
    public String birthDay;
    public String email;
    public String fbBizToken;
    public String firstName;
    public String gcid;
    public int gender;
    public String gpgId;
    public String lastName;
    public String memID;
    public int memType;
    public String middleName;
    public String name;
    public long npsn;
    public String pictureUrl;
    public String subID;
    public String thirdPartyToken;

    public void copyFrom(NXToyUserInfo nXToyUserInfo) {
        this.memType = nXToyUserInfo.memType;
        this.npsn = nXToyUserInfo.npsn;
        if (nXToyUserInfo.subID != null) {
            this.subID = new String(nXToyUserInfo.subID);
        }
        if (nXToyUserInfo.pictureUrl != null) {
            this.pictureUrl = new String(nXToyUserInfo.pictureUrl);
        }
        if (nXToyUserInfo.name != null) {
            this.name = new String(nXToyUserInfo.name);
        }
        if (nXToyUserInfo.memID != null) {
            this.memID = new String(nXToyUserInfo.memID);
        }
        this.age_range_max = nXToyUserInfo.age_range_max;
        this.age_range_min = nXToyUserInfo.age_range_min;
        if (nXToyUserInfo.email != null) {
            this.email = new String(nXToyUserInfo.email);
        }
        this.gender = nXToyUserInfo.gender;
        if (nXToyUserInfo.birthDay != null) {
            this.birthDay = new String(nXToyUserInfo.birthDay);
        }
        if (nXToyUserInfo.firstName != null) {
            this.firstName = new String(nXToyUserInfo.firstName);
        }
        if (nXToyUserInfo.middleName != null) {
            this.middleName = new String(nXToyUserInfo.middleName);
        }
        if (nXToyUserInfo.lastName != null) {
            this.lastName = new String(nXToyUserInfo.lastName);
        }
        if (nXToyUserInfo.gcid != null) {
            this.gcid = new String(nXToyUserInfo.gcid);
        }
        if (nXToyUserInfo.gpgId != null) {
            this.gpgId = new String(nXToyUserInfo.gpgId);
        }
        if (nXToyUserInfo.agcId != null) {
            this.agcId = new String(nXToyUserInfo.agcId);
        }
        if (nXToyUserInfo.thirdPartyToken != null) {
            this.thirdPartyToken = new String(nXToyUserInfo.thirdPartyToken);
        }
        if (nXToyUserInfo.fbBizToken != null) {
            this.fbBizToken = new String(nXToyUserInfo.fbBizToken);
        }
    }
}
