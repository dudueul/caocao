package twitter4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
final class DispatcherImpl implements Dispatcher {
    private static final long SHUTDOWN_TIME = 5000;
    private static final Logger logger = Logger.getLogger(DispatcherImpl.class);
    private final ExecutorService executorService;

    public DispatcherImpl(final Configuration configuration) {
        this.executorService = Executors.newFixedThreadPool(configuration.getAsyncNumThreads(), new ThreadFactory() { // from class: twitter4j.DispatcherImpl.1
            int count = 0;

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                int i = this.count;
                this.count = i + 1;
                thread.setName(String.format("Twitter4J Async Dispatcher[%d]", Integer.valueOf(i)));
                thread.setDaemon(configuration.isDaemonEnabled());
                return thread;
            }
        });
        Runtime.getRuntime().addShutdownHook(new Thread() { // from class: twitter4j.DispatcherImpl.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                DispatcherImpl.this.executorService.shutdown();
            }
        });
    }

    @Override // twitter4j.Dispatcher
    public synchronized void invokeLater(Runnable runnable) {
        this.executorService.execute(runnable);
    }

    @Override // twitter4j.Dispatcher
    public synchronized void shutdown() {
        this.executorService.shutdown();
        try {
            if (!this.executorService.awaitTermination(SHUTDOWN_TIME, TimeUnit.MILLISECONDS)) {
                this.executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            logger.warn(e.getMessage());
        }
    }
}
