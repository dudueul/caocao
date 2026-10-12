package com.nexon.nexonanalyticssdk;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxTESTInfos {
    private static NxTESTInfos instance;
    private long deletedLogCount;
    private boolean firstSendCompletedFlag;
    private long remainLogConunt;
    private long sendCompletedLogCount;
    private long sendTryLogCount;

    NxTESTInfos() {
    }

    public static NxTESTInfos getInstance() {
        if (instance == null) {
            instance = new NxTESTInfos();
        }
        return instance;
    }

    public void addDeletedLogCount(long j) {
        this.deletedLogCount += j;
    }

    public void addSendCompletedLogCount(long j) {
        this.sendCompletedLogCount += j;
    }

    public long getDeletedLogCount() {
        return this.deletedLogCount;
    }

    public long getRemainLogConunt() {
        return this.remainLogConunt;
    }

    public long getSendCompletedLogCount() {
        return this.sendCompletedLogCount;
    }

    public long getSendTryLogCount() {
        return this.sendTryLogCount;
    }

    public boolean isFirstSendCompletedFlag() {
        return this.firstSendCompletedFlag;
    }

    public void setDeletedLogCount(long j) {
        this.deletedLogCount = j;
    }

    public void setFirstSendCompletedFlag(boolean z) {
        this.firstSendCompletedFlag = z;
    }

    public void setRemainLogConunt(long j) {
        this.remainLogConunt = j;
    }

    public void setSendCompletedLogCount(long j) {
        this.sendCompletedLogCount = j;
    }

    public void setSendTryLogCount(long j) {
        this.sendTryLogCount = j;
    }
}
