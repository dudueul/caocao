package kr.co.nexon.android.sns.nxnet.session;

import android.content.Context;
import android.content.SharedPreferences;
import com.nexon.core.util.NXJsonUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNXNetSessionManager {
    private static NPNXNetSessionManager instance = null;
    protected Context applicationContext;
    private SharedPreferences pref;
    private NPNXNetSession session;

    private NPNXNetSessionManager(Context context) {
        this.applicationContext = context;
        load();
    }

    public static void disposeOfInstance() {
        if (instance != null) {
            instance = null;
        }
    }

    public static NPNXNetSessionManager getInstance(Context context) {
        if (instance == null) {
            synchronized (NPNXNetSessionManager.class) {
                instance = new NPNXNetSessionManager(context);
            }
        }
        return instance;
    }

    protected SharedPreferences.Editor getEditor() {
        return getPref().edit();
    }

    protected SharedPreferences getPref() {
        if (this.pref == null) {
            synchronized (NPNXNetSessionManager.class) {
                this.pref = this.applicationContext.getSharedPreferences("NXNetPref", 0);
            }
        }
        return this.pref;
    }

    public NPNXNetSession getSession() {
        if (this.session == null) {
            this.session = new NPNXNetSession();
        }
        return this.session;
    }

    public void load() {
        synchronized (NPNXNetSessionManager.class) {
            this.session = (NPNXNetSession) NXJsonUtil.fromObject(getPref().getString("nxNet", "{}"), NPNXNetSession.class);
        }
    }

    public void remove() {
        synchronized (NPNXNetSessionManager.class) {
            this.session = new NPNXNetSession();
            save();
        }
    }

    public void save() {
        synchronized (NPNXNetSessionManager.class) {
            getEditor().putString("nxNet", NXJsonUtil.toJsonString(this.session)).commit();
        }
    }

    public void setSession(NPNXNetSession nPNXNetSession) {
        this.session = nPNXNetSession;
        save();
    }
}
