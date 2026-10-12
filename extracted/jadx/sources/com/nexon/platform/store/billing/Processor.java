package com.nexon.platform.store.billing;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Processor {
    private static Processor processor;
    private ArrayList<ProcessingListener> processingListeners;

    public interface ProcessingListener {
        void willProcess(Transaction transaction);
    }

    public static void addTransactionListener(ProcessingListener processingListener) {
        if (getProcessor().processingListeners.contains(processingListener)) {
            return;
        }
        getProcessor().processingListeners.add(processingListener);
    }

    public static synchronized Processor getProcessor() {
        if (processor == null) {
            processor = new Processor();
            processor.processingListeners = new ArrayList<>();
        }
        return processor;
    }

    public static void removeTransactionListener(ProcessingListener processingListener) {
        getProcessor().processingListeners.remove(processingListener);
    }

    public static void willProcess(Transaction transaction) {
        if (getProcessor().processingListeners == null || getProcessor().processingListeners.isEmpty()) {
            return;
        }
        Iterator<ProcessingListener> it = getProcessor().processingListeners.iterator();
        while (it.hasNext()) {
            it.next().willProcess(transaction);
        }
    }
}
