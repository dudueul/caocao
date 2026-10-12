package com.nexon.core.session;

import android.content.Context;
import android.content.SharedPreferences;
import com.nexon.core.android.NXPApplicationConfigManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.session.model.NXToySession;
import com.nexon.core.session.model.NXToyUserInfo;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.platform.NXPFinalizer;
import com.nexon.platform.store.NexonStore;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToySessionManager {
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: com.nexon.core.session.NXToySessionManager.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            NXToySessionManager unused = NXToySessionManager.instance = null;
        }
    };
    private static NXToySessionManager instance;
    private NXToySession lastSession;
    private SharedPreferences preference;
    private String serviceId;
    private NXToySession session;
    HashMap<String, Boolean> snsEnableMap;
    private NXToyUserInfo snsUserinfo;

    public static NXToySessionManager getInstance() {
        if (instance == null) {
            synchronized (NXToySessionManager.class) {
                if (instance == null) {
                    instance = new NXToySessionManager();
                }
            }
        }
        return instance;
    }

    public void backupToLastSession() {
        this.lastSession = this.session.copy();
    }

    protected SharedPreferences.Editor getEditor() {
        return this.preference.edit();
    }

    public NXToySession getSession() {
        return this.session;
    }

    public boolean getSnsEnable(int i) {
        Boolean bool = this.snsEnableMap.get(String.valueOf(i));
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public NXToyUserInfo getSnsUserinfo() {
        return this.snsUserinfo;
    }

    public void initialize(Context context) {
        this.preference = context.getSharedPreferences("NPSNSPref", 0);
        ToyLog.d("Load preference : " + this.preference);
        loadSession();
        String serviceId = NXPApplicationConfigManager.getInstance().getServiceId();
        ToyLog.d("Set service id : " + serviceId);
        this.serviceId = serviceId;
        this.session.setServiceId(this.serviceId);
        saveSession();
    }

    public void loadSession() {
        synchronized (NXToySessionManager.class) {
            ToyLog.d("Load session.");
            this.session = (NXToySession) NXJsonUtil.fromObject(this.preference.getString(NexonStore.USER_TYPE, "{}"), NXToySession.class);
            this.snsEnableMap = (HashMap) NXJsonUtil.fromObject(this.preference.getString("snsStatus", "{}"), HashMap.class);
            this.snsUserinfo = (NXToyUserInfo) NXJsonUtil.fromObject(this.preference.getString("snsUserinfo", "{}"), NXToyUserInfo.class);
        }
    }

    public void removeSession() {
        synchronized (NXToySessionManager.class) {
            ToyLog.d("Remove session.");
            this.session = new NXToySession();
            this.session.setServiceId(this.serviceId);
            this.snsEnableMap.clear();
            this.snsUserinfo = new NXToyUserInfo();
            this.lastSession = new NXToySession();
            saveSession();
        }
    }

    public void restoreLastSession() {
        this.session = this.lastSession.copy();
        this.lastSession = new NXToySession();
    }

    public void saveSession() {
        synchronized (NXToySessionManager.class) {
            ToyLog.d("Save session.");
            getEditor().putString(NexonStore.USER_TYPE, NXJsonUtil.toJsonString(this.session)).commit();
            getEditor().putString("snsStatus", NXJsonUtil.toJsonString(this.snsEnableMap)).commit();
            getEditor().putString("snsUserinfo", NXJsonUtil.toJsonString(this.snsUserinfo)).commit();
        }
    }

    public void setSession(NXToySession nXToySession) {
        nXToySession.setTempUserLoginInfo(this.session.getTempUserLoginInfo());
        this.session = nXToySession;
        saveSession();
    }

    public void setSnsEnable(int i, boolean z) {
        this.snsEnableMap.put(String.valueOf(i), Boolean.valueOf(z));
        saveSession();
    }

    public void setSnsUserinfo(NXToyUserInfo nXToyUserInfo) {
        this.snsUserinfo = nXToyUserInfo;
        saveSession();
    }

    public void snsAllDisable() {
        this.snsEnableMap.clear();
        saveSession();
    }

    public String toString() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (this.snsEnableMap != null) {
            for (String str : this.snsEnableMap.keySet()) {
                linkedHashMap.put(str, Boolean.valueOf(this.snsEnableMap.get(str).booleanValue()));
            }
        }
        linkedHashMap.put("session", this.session.toString());
        linkedHashMap.put("lastSession", this.lastSession.toString());
        linkedHashMap.put("serviceId", this.serviceId);
        linkedHashMap.put("preference", this.preference.toString());
        linkedHashMap.put("snsUserinfo", this.snsUserinfo.toMap());
        return NXJsonUtil.toJsonString(linkedHashMap);
    }
}
