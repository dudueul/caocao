package com.adjust.sdk;

import com.nexon.nexonanalyticssdk.NxLogInfo;
import com.nexon.nexonanalyticssdk.NxSystemInfo;
import com.tapjoy.TJAdUnitConstants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ActivityState implements Serializable, Cloneable {
    private static final int ORDER_ID_MAXCOUNT = 10;
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField(NxSystemInfo.KEY_UUID, String.class), new ObjectStreamField(TJAdUnitConstants.String.ENABLED, Boolean.TYPE), new ObjectStreamField("askingAttribution", Boolean.TYPE), new ObjectStreamField("eventCount", Integer.TYPE), new ObjectStreamField("sessionCount", Integer.TYPE), new ObjectStreamField("subsessionCount", Integer.TYPE), new ObjectStreamField("sessionLength", Long.TYPE), new ObjectStreamField("timeSpent", Long.TYPE), new ObjectStreamField("lastActivity", Long.TYPE), new ObjectStreamField("lastInterval", Long.TYPE), new ObjectStreamField("updatePackages", Boolean.TYPE), new ObjectStreamField("orderIds", LinkedList.class), new ObjectStreamField("pushToken", String.class), new ObjectStreamField(NxLogInfo.KEY_ADID, String.class), new ObjectStreamField("clickTime", Long.TYPE), new ObjectStreamField("installBegin", Long.TYPE), new ObjectStreamField("installReferrer", String.class)};
    private static final long serialVersionUID = 9039439291143138148L;
    private transient ILogger logger = AdjustFactory.getLogger();
    protected String uuid = Util.createUuid();
    protected boolean enabled = true;
    protected boolean askingAttribution = false;
    protected int eventCount = 0;
    protected int sessionCount = 0;
    protected int subsessionCount = -1;
    protected long sessionLength = -1;
    protected long timeSpent = -1;
    protected long lastActivity = -1;
    protected long lastInterval = -1;
    protected boolean updatePackages = false;
    protected LinkedList<String> orderIds = null;
    protected String pushToken = null;
    protected String adid = null;
    protected long clickTime = 0;
    protected long installBegin = 0;
    protected String installReferrer = null;

    protected ActivityState() {
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        ObjectInputStream.GetField fields = objectInputStream.readFields();
        this.eventCount = Util.readIntField(fields, "eventCount", 0);
        this.sessionCount = Util.readIntField(fields, "sessionCount", 0);
        this.subsessionCount = Util.readIntField(fields, "subsessionCount", -1);
        this.sessionLength = Util.readLongField(fields, "sessionLength", -1L);
        this.timeSpent = Util.readLongField(fields, "timeSpent", -1L);
        this.lastActivity = Util.readLongField(fields, "lastActivity", -1L);
        this.lastInterval = Util.readLongField(fields, "lastInterval", -1L);
        this.uuid = Util.readStringField(fields, NxSystemInfo.KEY_UUID, null);
        this.enabled = Util.readBooleanField(fields, TJAdUnitConstants.String.ENABLED, true);
        this.askingAttribution = Util.readBooleanField(fields, "askingAttribution", false);
        this.updatePackages = Util.readBooleanField(fields, "updatePackages", false);
        this.orderIds = (LinkedList) Util.readObjectField(fields, "orderIds", null);
        this.pushToken = Util.readStringField(fields, "pushToken", null);
        this.adid = Util.readStringField(fields, NxLogInfo.KEY_ADID, null);
        this.clickTime = Util.readLongField(fields, "clickTime", -1L);
        this.installBegin = Util.readLongField(fields, "installBegin", -1L);
        this.installReferrer = Util.readStringField(fields, "installReferrer", null);
        if (this.uuid == null) {
            this.uuid = Util.createUuid();
        }
    }

    private static String stamp(long j) {
        Calendar.getInstance().setTimeInMillis(j);
        return Util.formatString("%02d:%02d:%02d", 11, 12, 13);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    protected void addOrderId(String str) {
        if (this.orderIds == null) {
            this.orderIds = new LinkedList<>();
        }
        if (this.orderIds.size() >= 10) {
            this.orderIds.removeLast();
        }
        this.orderIds.addFirst(str);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ActivityState activityState = (ActivityState) obj;
            return Util.equalString(this.uuid, activityState.uuid) && Util.equalBoolean(Boolean.valueOf(this.enabled), Boolean.valueOf(activityState.enabled)) && Util.equalBoolean(Boolean.valueOf(this.askingAttribution), Boolean.valueOf(activityState.askingAttribution)) && Util.equalInt(Integer.valueOf(this.eventCount), Integer.valueOf(activityState.eventCount)) && Util.equalInt(Integer.valueOf(this.sessionCount), Integer.valueOf(activityState.sessionCount)) && Util.equalInt(Integer.valueOf(this.subsessionCount), Integer.valueOf(activityState.subsessionCount)) && Util.equalLong(Long.valueOf(this.sessionLength), Long.valueOf(activityState.sessionLength)) && Util.equalLong(Long.valueOf(this.timeSpent), Long.valueOf(activityState.timeSpent)) && Util.equalLong(Long.valueOf(this.lastInterval), Long.valueOf(activityState.lastInterval)) && Util.equalBoolean(Boolean.valueOf(this.updatePackages), Boolean.valueOf(activityState.updatePackages)) && Util.equalObject(this.orderIds, activityState.orderIds) && Util.equalString(this.pushToken, activityState.pushToken) && Util.equalString(this.adid, activityState.adid) && Util.equalLong(Long.valueOf(this.clickTime), Long.valueOf(activityState.clickTime)) && Util.equalLong(Long.valueOf(this.installBegin), Long.valueOf(activityState.installBegin)) && Util.equalString(this.installReferrer, activityState.installReferrer);
        }
        return false;
    }

    protected boolean findOrderId(String str) {
        if (this.orderIds == null) {
            return false;
        }
        return this.orderIds.contains(str);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((((Util.hashString(this.uuid) + 629) * 37) + Util.hashBoolean(Boolean.valueOf(this.enabled))) * 37) + Util.hashBoolean(Boolean.valueOf(this.askingAttribution))) * 37) + this.eventCount) * 37) + this.sessionCount) * 37) + this.subsessionCount) * 37) + Util.hashLong(Long.valueOf(this.sessionLength))) * 37) + Util.hashLong(Long.valueOf(this.timeSpent))) * 37) + Util.hashLong(Long.valueOf(this.lastInterval))) * 37) + Util.hashBoolean(Boolean.valueOf(this.updatePackages))) * 37) + Util.hashObject(this.orderIds)) * 37) + Util.hashString(this.pushToken)) * 37) + Util.hashString(this.adid)) * 37) + Util.hashLong(Long.valueOf(this.clickTime))) * 37) + Util.hashLong(Long.valueOf(this.installBegin))) * 37) + Util.hashString(this.installReferrer);
    }

    protected void resetSessionAttributes(long j) {
        this.subsessionCount = 1;
        this.sessionLength = 0L;
        this.timeSpent = 0L;
        this.lastActivity = j;
        this.lastInterval = -1L;
    }

    public String toString() {
        return Util.formatString("ec:%d sc:%d ssc:%d sl:%.1f ts:%.1f la:%s uuid:%s", Integer.valueOf(this.eventCount), Integer.valueOf(this.sessionCount), Integer.valueOf(this.subsessionCount), Double.valueOf(this.sessionLength / 1000.0d), Double.valueOf(this.timeSpent / 1000.0d), stamp(this.lastActivity), this.uuid);
    }
}
