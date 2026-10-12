package kr.co.nexon.mdev.work;

import com.nexon.platform.NXPFinalizer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPJobManager extends Thread {
    private boolean suppend = false;
    private static BlockingQueue<Object> queue = new ArrayBlockingQueue(2);
    private static volatile NXPJobManager instance = null;
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: kr.co.nexon.mdev.work.NXPJobManager.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            NXPJobManager unused = NXPJobManager.instance = null;
        }
    };

    public interface NXPJobManagerKiller {
    }

    public interface NXPWorkJob {
        void exec();
    }

    public NXPJobManager() {
        if (isAlive()) {
            return;
        }
        start();
    }

    public static NXPJobManager getInstance() {
        if (instance == null) {
            synchronized (NXPJobManager.class) {
                if (instance == null) {
                    instance = new NXPJobManager();
                }
            }
        }
        return instance;
    }

    public void deQueue(NXPWorkJob nXPWorkJob) {
        queue.remove(nXPWorkJob);
    }

    public void destory() {
        try {
            queue.put(new NXPJobManagerKiller() { // from class: kr.co.nexon.mdev.work.NXPJobManager.2
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void enQueue(NXPWorkJob nXPWorkJob) {
        try {
            queue.put(nXPWorkJob);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void finalize() {
        destory();
    }

    public void resumed() {
        this.suppend = false;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            if (!this.suppend) {
                try {
                    Object objTake = queue.take();
                    if (objTake instanceof NXPJobManagerKiller) {
                        return;
                    } else {
                        ((NXPWorkJob) objTake).exec();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void suspended() {
        this.suppend = true;
    }
}
