package io.fabric.sdk.android.services.concurrency.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class AbstractFuture<V> implements Future<V> {
    private final Sync<V> sync = new Sync<>();

    static final class Sync<V> extends AbstractQueuedSynchronizer {
        static final int CANCELLED = 4;
        static final int COMPLETED = 2;
        static final int COMPLETING = 1;
        static final int INTERRUPTED = 8;
        static final int RUNNING = 0;
        private static final long serialVersionUID = 0;
        private Throwable exception;
        private V value;

        Sync() {
        }

        private boolean complete(V v, Throwable th, int i) {
            boolean zCompareAndSetState = compareAndSetState(0, 1);
            if (zCompareAndSetState) {
                this.value = v;
                if ((i & 12) != 0) {
                    th = new CancellationException("Future.cancel() was called.");
                }
                this.exception = th;
                releaseShared(i);
            } else if (getState() == 1) {
                acquireShared(-1);
            }
            return zCompareAndSetState;
        }

        private V getValue() throws ExecutionException, CancellationException {
            int state = getState();
            switch (state) {
                case 2:
                    if (this.exception != null) {
                        throw new ExecutionException(this.exception);
                    }
                    return this.value;
                case 4:
                case 8:
                    throw AbstractFuture.cancellationExceptionWithCause("Task was cancelled.", this.exception);
                default:
                    throw new IllegalStateException("Error, synchronizer in invalid state: " + state);
            }
        }

        boolean cancel(boolean z) {
            return complete(null, null, z ? 8 : 4);
        }

        V get() throws ExecutionException, CancellationException, InterruptedException {
            acquireSharedInterruptibly(-1);
            return getValue();
        }

        V get(long j) throws ExecutionException, CancellationException, InterruptedException, TimeoutException {
            if (tryAcquireSharedNanos(-1, j)) {
                return getValue();
            }
            throw new TimeoutException("Timeout waiting for task.");
        }

        boolean isCancelled() {
            return (getState() & 12) != 0;
        }

        boolean isDone() {
            return (getState() & 14) != 0;
        }

        boolean set(V v) {
            return complete(v, null, 2);
        }

        boolean setException(Throwable th) {
            return complete(null, th, 2);
        }

        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        protected int tryAcquireShared(int i) {
            return isDone() ? 1 : -1;
        }

        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        protected boolean tryReleaseShared(int i) {
            setState(i);
            return true;
        }

        boolean wasInterrupted() {
            return getState() == 8;
        }
    }

    protected AbstractFuture() {
    }

    static final CancellationException cancellationExceptionWithCause(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        if (!this.sync.cancel(z)) {
            return false;
        }
        if (z) {
            interruptTask();
        }
        return true;
    }

    @Override // java.util.concurrent.Future
    public V get() throws ExecutionException, InterruptedException {
        return this.sync.get();
    }

    @Override // java.util.concurrent.Future
    public V get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return this.sync.get(timeUnit.toNanos(j));
    }

    protected void interruptTask() {
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.sync.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.sync.isDone();
    }

    protected boolean set(V v) {
        return this.sync.set(v);
    }

    protected boolean setException(Throwable th) {
        if (th == null) {
            throw new NullPointerException();
        }
        return this.sync.setException(th);
    }

    protected final boolean wasInterrupted() {
        return this.sync.wasInterrupted();
    }
}
