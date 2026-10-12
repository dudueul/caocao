package com.crashlytics.android.answers;

import io.fabric.sdk.android.services.concurrency.internal.Backoff;
import java.util.Random;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class RandomBackoff implements Backoff {
    final Backoff backoff;
    final double jitterPercent;
    final Random random;

    public RandomBackoff(Backoff backoff, double d) {
        this(backoff, d, new Random());
    }

    public RandomBackoff(Backoff backoff, double d, Random random) {
        if (d < 0.0d || d > 1.0d) {
            throw new IllegalArgumentException("jitterPercent must be between 0.0 and 1.0");
        }
        if (backoff == null) {
            throw new NullPointerException("backoff must not be null");
        }
        if (random == null) {
            throw new NullPointerException("random must not be null");
        }
        this.backoff = backoff;
        this.jitterPercent = d;
        this.random = random;
    }

    @Override // io.fabric.sdk.android.services.concurrency.internal.Backoff
    public long getDelayMillis(int i) {
        return (long) (randomJitter() * this.backoff.getDelayMillis(i));
    }

    double randomJitter() {
        double d = 1.0d - this.jitterPercent;
        double d2 = 1.0d + this.jitterPercent;
        return ((d2 - d) * this.random.nextDouble()) + d;
    }
}
