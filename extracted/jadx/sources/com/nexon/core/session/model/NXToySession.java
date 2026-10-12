package com.nexon.core.session.model;

import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.nexonanalyticssdk.NxLogInfo;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToySession {
    private String agcId;
    private String emailAccountId;
    private String emailId;
    private String externalAuthId;
    private String externalAuthToken;
    private String gcId;
    private String gpgId;
    private List<NXToyTerm> loginTermsList;
    private String name;
    private String npacode;
    private long npsn;
    private String nptoken;
    private String serviceId;
    private Map<String, Object> tempUserLoginInfo;
    private Map<Long, Map<String, Object>> tempUserLoginInfoForNpsn;
    private int type = NXToyLoginType.LoginTypeNotLogined.getValue();
    private String umKey;

    public NXToySession copy() {
        NXToySession nXToySession = new NXToySession();
        nXToySession.npsn = this.npsn;
        nXToySession.nptoken = this.nptoken;
        nXToySession.serviceId = this.serviceId;
        nXToySession.umKey = this.umKey;
        nXToySession.emailAccountId = this.emailAccountId;
        nXToySession.type = this.type;
        nXToySession.externalAuthId = this.externalAuthId;
        nXToySession.externalAuthToken = this.externalAuthToken;
        nXToySession.gcId = this.gcId;
        nXToySession.gpgId = this.gpgId;
        nXToySession.agcId = this.agcId;
        nXToySession.name = this.name;
        return nXToySession;
    }

    public String getAccountId() {
        return this.emailAccountId;
    }

    public String getAgcId() {
        return this.agcId;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public String getExternalAuthId() {
        return this.externalAuthId == null ? "" : this.externalAuthId;
    }

    public String getExternalAuthToken() {
        return this.externalAuthToken == null ? "" : this.externalAuthToken;
    }

    public String getGcId() {
        return this.gcId;
    }

    public String getGpgId() {
        return this.gpgId;
    }

    public List<NXToyTerm> getLoginTermsList() {
        return this.loginTermsList;
    }

    public String getName() {
        return this.name;
    }

    public String getNpaCode() {
        return this.npacode == null ? "" : this.npacode;
    }

    public long getNpsn() {
        return this.npsn;
    }

    public String getNptoken() {
        return this.nptoken;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public Map<String, Object> getTempUserLoginInfo() {
        return this.tempUserLoginInfo;
    }

    public Map<String, Object> getTempUserLoginInfoForNpsn(long j) {
        if (this.tempUserLoginInfoForNpsn == null) {
            return null;
        }
        return this.tempUserLoginInfoForNpsn.get(Long.valueOf(j));
    }

    public int getType() {
        return this.type;
    }

    public String getUMKey() {
        return this.umKey;
    }

    public void setAccountId(String str) {
        this.emailAccountId = str;
    }

    public void setAgcId(String str) {
        this.agcId = str;
    }

    public void setEmailId(String str) {
        this.emailId = str;
    }

    public void setExternalAuthId(String str) {
        this.externalAuthId = str;
    }

    public void setExternalAuthToken(String str) {
        this.externalAuthToken = str;
    }

    public void setGcId(String str) {
        this.gcId = str;
    }

    public void setGpgId(String str) {
        this.gpgId = str;
    }

    public void setLoginTermsList(List<NXToyTerm> list) {
        this.loginTermsList = list;
    }

    public void setNPToken(String str) {
        this.nptoken = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNpaCode(String str) {
        this.npacode = str;
    }

    public void setNpsn(long j) {
        this.npsn = j;
    }

    public void setServiceId(String str) {
        this.serviceId = str;
    }

    public void setTempUserLoginInfo(Map<String, Object> map) {
        this.tempUserLoginInfo = map;
    }

    public void setTempUserLoginInfoForNpsn(long j, Map<String, Object> map) {
        if (this.tempUserLoginInfoForNpsn == null) {
            this.tempUserLoginInfoForNpsn = new HashMap();
        }
        this.tempUserLoginInfoForNpsn.put(Long.valueOf(j), map);
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setUMKey(String str) {
        this.umKey = str;
    }

    public String toString() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(NxLogInfo.KEY_NPSN, Long.valueOf(this.npsn));
        linkedHashMap.put("nptoken", this.nptoken);
        linkedHashMap.put("serviceId", this.serviceId);
        linkedHashMap.put("umKey", this.umKey);
        linkedHashMap.put("emailAccountId", NXStringUtil.getMaskedEmail(this.emailAccountId));
        linkedHashMap.put("type", Integer.valueOf(this.type));
        linkedHashMap.put("externalAuthId", this.externalAuthId);
        linkedHashMap.put("externalAuthToken", "hidden");
        linkedHashMap.put("npacode", this.npacode);
        linkedHashMap.put("gcId", this.emailAccountId);
        linkedHashMap.put("gpgId", this.gpgId);
        linkedHashMap.put("agcId", this.agcId);
        linkedHashMap.put("name", this.name);
        linkedHashMap.put("emailId", NXStringUtil.getMaskedEmail(this.emailId));
        linkedHashMap.put("loginTermsList", this.loginTermsList);
        return NXJsonUtil.toJsonString(linkedHashMap);
    }
}
