package kr.co.nexon.mdev.work;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPendingWork<T> {
    private NXPendingWorkJob<T> mJob;
    private T mListener;

    public interface NXPendingWorkCancelJob<T> {
        void doCancel(T t);
    }

    public interface NXPendingWorkJob<T> {
        void doWork(T t);
    }

    public NXPendingWork(T t, NXPendingWorkJob<T> nXPendingWorkJob) {
        this.mListener = t;
        this.mJob = nXPendingWorkJob;
    }

    public void cancel(NXPendingWorkCancelJob<T> nXPendingWorkCancelJob) {
        if (nXPendingWorkCancelJob == null) {
            return;
        }
        nXPendingWorkCancelJob.doCancel(this.mListener);
    }

    public void start() {
        if (this.mJob != null) {
            this.mJob.doWork(this.mListener);
        }
    }
}
