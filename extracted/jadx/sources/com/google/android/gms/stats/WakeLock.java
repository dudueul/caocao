package com.google.android.gms.stats;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.providers.PooledExecutorsProvider;
import com.google.android.gms.common.stats.StatsUtils;
import com.google.android.gms.common.stats.WakeLockTracker;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.WorkSourceUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@ThreadSafe
public class WakeLock {
    private static ScheduledExecutorService zzaeg;
    private static Configuration zzaeh = new zza();
    private final PowerManager.WakeLock zzadv;
    private WorkSource zzadw;
    private String zzadx;
    private final int zzady;
    private final String zzadz;
    private final String zzaea;
    private final String zzaeb;
    private boolean zzaec;
    private final Map<String, Integer[]> zzaed;
    private int zzaee;
    private AtomicInteger zzaef;
    private final Context zzjp;

    public interface Configuration {
        long getMaximumTimeout(String str, String str2);

        boolean isWorkChainsEnabled();
    }

    public class HeldLock {
        private boolean zzaek;
        private Future zzael;
        private final String zzaem;

        private HeldLock(String str) {
            this.zzaek = true;
            this.zzaem = str;
        }

        /* synthetic */ HeldLock(WakeLock wakeLock, String str, zza zzaVar) {
            this(str);
        }

        public void finalize() {
            if (this.zzaek) {
                String strValueOf = String.valueOf(this.zzaem);
                Log.e("WakeLock", strValueOf.length() != 0 ? "HeldLock finalized while still holding the WakeLock! Reason: ".concat(strValueOf) : new String("HeldLock finalized while still holding the WakeLock! Reason: "));
                release(0);
            }
        }

        public void release() {
            release(0);
        }

        public synchronized void release(int i) {
            if (this.zzaek) {
                this.zzaek = false;
                if (this.zzael != null) {
                    this.zzael.cancel(false);
                    this.zzael = null;
                }
                WakeLock.this.zzc(this.zzaem, i);
            }
        }
    }

    public WakeLock(Context context, int i, @Nonnull String str) {
        this(context, i, str, null, context == null ? null : context.getPackageName());
    }

    public WakeLock(Context context, int i, @Nonnull String str, @Nullable String str2) {
        this(context, i, str, str2, context == null ? null : context.getPackageName());
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public WakeLock(Context context, int i, @Nonnull String str, @Nullable String str2, @Nonnull String str3) {
        this(context, i, str, str2, str3, null);
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public WakeLock(Context context, int i, @Nonnull String str, @Nullable String str2, @Nonnull String str3, @Nullable String str4) {
        this.zzaec = true;
        this.zzaed = new HashMap();
        this.zzaef = new AtomicInteger(0);
        Preconditions.checkNotEmpty(str, "Wake lock name can NOT be empty");
        this.zzady = i;
        this.zzaea = str2;
        this.zzaeb = str4;
        this.zzjp = context.getApplicationContext();
        if ("com.google.android.gms".equals(context.getPackageName())) {
            this.zzadz = str;
        } else {
            String strValueOf = String.valueOf("*gcore*:");
            String strValueOf2 = String.valueOf(str);
            this.zzadz = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
        }
        this.zzadv = ((PowerManager) context.getSystemService("power")).newWakeLock(i, str);
        if (WorkSourceUtil.hasWorkSourcePermission(context)) {
            str3 = Strings.isEmptyOrWhitespace(str3) ? context.getPackageName() : str3;
            if (!zzaeh.isWorkChainsEnabled() || str3 == null || str4 == null) {
                this.zzadw = WorkSourceUtil.fromPackage(context, str3);
            } else {
                Log.d("WakeLock", new StringBuilder(String.valueOf(str3).length() + 42 + String.valueOf(str4).length()).append("Using experimental Pi WorkSource chains: ").append(str3).append(",").append(str4).toString());
                this.zzadx = str3;
                this.zzadw = WorkSourceUtil.fromPackageAndModuleExperimentalPi(context, str3, str4);
            }
            addWorkSource(this.zzadw);
        }
        if (zzaeg == null) {
            zzaeg = PooledExecutorsProvider.getInstance().newSingleThreadScheduledExecutor();
        }
    }

    public static void setConfiguration(Configuration configuration) {
        zzaeh = configuration;
    }

    private final void zza(WorkSource workSource) {
        try {
            this.zzadv.setWorkSource(workSource);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            Log.wtf("WakeLock", e.toString());
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0044 A[Catch: all -> 0x00b9, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000f, B:11:0x0023, B:13:0x0027, B:15:0x0031, B:21:0x004c, B:22:0x006d, B:35:0x00a9, B:17:0x0044, B:19:0x0048, B:8:0x0013, B:10:0x001b), top: B:40:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:21:0x004c A[Catch: all -> 0x00b9, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000f, B:11:0x0023, B:13:0x0027, B:15:0x0031, B:21:0x004c, B:22:0x006d, B:35:0x00a9, B:17:0x0044, B:19:0x0048, B:8:0x0013, B:10:0x001b), top: B:40:0x0007 }] */
    @SuppressLint({"WakelockTimeout"})
    private final void zza(String str, long j) {
        boolean z;
        String strZzn = zzn(str);
        synchronized (this) {
            if ((!this.zzaed.isEmpty() || this.zzaee > 0) && !this.zzadv.isHeld()) {
                this.zzaed.clear();
                this.zzaee = 0;
            }
            if (this.zzaec) {
                Integer[] numArr = this.zzaed.get(strZzn);
                if (numArr == null) {
                    this.zzaed.put(strZzn, new Integer[]{1});
                    z = true;
                } else {
                    numArr[0] = Integer.valueOf(numArr[0].intValue() + 1);
                    z = false;
                }
                if (z) {
                    WakeLockTracker.getInstance().registerEvent(this.zzjp, StatsUtils.getEventKey(this.zzadv, strZzn), 7, this.zzadz, strZzn, this.zzaeb, this.zzady, zzdo(), j);
                    this.zzaee++;
                } else if (!this.zzaec) {
                    WakeLockTracker.getInstance().registerEvent(this.zzjp, StatsUtils.getEventKey(this.zzadv, strZzn), 7, this.zzadz, strZzn, this.zzaeb, this.zzady, zzdo(), j);
                    this.zzaee++;
                }
            } else if (!this.zzaec && this.zzaee == 0) {
                WakeLockTracker.getInstance().registerEvent(this.zzjp, StatsUtils.getEventKey(this.zzadv, strZzn), 7, this.zzadz, strZzn, this.zzaeb, this.zzady, zzdo(), j);
                this.zzaee++;
            }
        }
        this.zzadv.acquire();
        if (j > 0) {
            zzaeg.schedule(new zzb(this), j, TimeUnit.MILLISECONDS);
            if (PlatformVersion.isAtLeastIceCreamSandwich() || !this.zzaec) {
                return;
            }
            String strValueOf = String.valueOf(this.zzadz);
            Log.wtf("WakeLock", strValueOf.length() != 0 ? "Do not acquire with timeout on reference counted wakeLocks before ICS. wakelock: ".concat(strValueOf) : new String("Do not acquire with timeout on reference counted wakeLocks before ICS. wakelock: "));
        }
    }

    private final void zzb(String str, int i) {
        if (this.zzaef.decrementAndGet() < 0) {
            Log.e("WakeLock", "release without a matched acquire!");
        }
        zzc(str, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:10:0x0018 A[Catch: all -> 0x0068, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000b, B:14:0x0020, B:15:0x0041, B:19:0x0047, B:21:0x004f, B:22:0x0056, B:10:0x0018, B:12:0x001c), top: B:26:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:14:0x0020 A[Catch: all -> 0x0068, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x000b, B:14:0x0020, B:15:0x0041, B:19:0x0047, B:21:0x004f, B:22:0x0056, B:10:0x0018, B:12:0x001c), top: B:26:0x0007 }] */
    public final void zzc(String str, int i) {
        boolean z;
        String strZzn = zzn(str);
        synchronized (this) {
            if (this.zzaec) {
                Integer[] numArr = this.zzaed.get(strZzn);
                if (numArr == null) {
                    z = false;
                } else if (numArr[0].intValue() == 1) {
                    this.zzaed.remove(strZzn);
                    z = true;
                } else {
                    numArr[0] = Integer.valueOf(numArr[0].intValue() - 1);
                    z = false;
                }
                if (z) {
                    WakeLockTracker.getInstance().registerEvent(this.zzjp, StatsUtils.getEventKey(this.zzadv, strZzn), 8, this.zzadz, strZzn, this.zzaeb, this.zzady, zzdo());
                    this.zzaee--;
                } else if (!this.zzaec) {
                    WakeLockTracker.getInstance().registerEvent(this.zzjp, StatsUtils.getEventKey(this.zzadv, strZzn), 8, this.zzadz, strZzn, this.zzaeb, this.zzady, zzdo());
                    this.zzaee--;
                }
            } else if (!this.zzaec && this.zzaee == 1) {
                WakeLockTracker.getInstance().registerEvent(this.zzjp, StatsUtils.getEventKey(this.zzadv, strZzn), 8, this.zzadz, strZzn, this.zzaeb, this.zzady, zzdo());
                this.zzaee--;
            }
        }
        zzn(i);
    }

    private final List<String> zzdo() {
        List<String> names = WorkSourceUtil.getNames(this.zzadw);
        if (this.zzadx == null) {
            return names;
        }
        ArrayList arrayList = new ArrayList(names);
        arrayList.add(this.zzadx);
        return arrayList;
    }

    private final String zzn(String str) {
        return (!this.zzaec || TextUtils.isEmpty(str)) ? this.zzaea : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzn(int i) {
        if (this.zzadv.isHeld()) {
            try {
                if (Build.VERSION.SDK_INT < 21 || i <= 0) {
                    this.zzadv.release();
                } else {
                    this.zzadv.release(i);
                }
            } catch (RuntimeException e) {
                if (!e.getClass().equals(RuntimeException.class)) {
                    throw e;
                }
                Log.e("WakeLock", String.valueOf(this.zzadz).concat(" was already released!"), e);
            }
        }
    }

    public void acquire() {
        this.zzaef.incrementAndGet();
        zza((String) null, 0L);
    }

    public void acquire(long j) {
        this.zzaef.incrementAndGet();
        zza((String) null, j);
    }

    public void acquire(String str) {
        this.zzaef.incrementAndGet();
        zza(str, 0L);
    }

    public void acquire(String str, long j) {
        this.zzaef.incrementAndGet();
        zza(str, j);
    }

    public HeldLock acquireLock(long j, String str) {
        long jMin = Math.min(j, zzaeh.getMaximumTimeout(this.zzadz, str));
        HeldLock heldLock = new HeldLock(this, str, null);
        zza(str, 0L);
        heldLock.zzael = zzaeg.schedule(new zzc(new WeakReference(heldLock)), jMin, TimeUnit.MILLISECONDS);
        return heldLock;
    }

    public void addWorkSource(WorkSource workSource) {
        if (workSource == null || !WorkSourceUtil.hasWorkSourcePermission(this.zzjp)) {
            return;
        }
        if (this.zzadw != null) {
            this.zzadw.add(workSource);
        } else {
            this.zzadw = workSource;
        }
        zza(this.zzadw);
    }

    public PowerManager.WakeLock getWakeLock() {
        return this.zzadv;
    }

    public boolean isHeld() {
        return this.zzadv.isHeld();
    }

    public void release() {
        zzb(null, 0);
    }

    public void release(int i) {
        zzb(null, i);
    }

    public void release(String str) {
        zzb(str, 0);
    }

    public void release(String str, int i) {
        zzb(str, i);
    }

    public void removeWorkSource(WorkSource workSource) {
        if (workSource == null || !WorkSourceUtil.hasWorkSourcePermission(this.zzjp)) {
            return;
        }
        try {
            if (this.zzadw != null) {
                this.zzadw.remove(workSource);
            }
            zza(this.zzadw);
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e("WakeLock", e.toString());
        }
    }

    public void setReferenceCounted(boolean z) {
        this.zzadv.setReferenceCounted(z);
        this.zzaec = z;
    }

    public void setWorkSource(WorkSource workSource) {
        if (WorkSourceUtil.hasWorkSourcePermission(this.zzjp)) {
            zza(workSource);
            this.zzadw = workSource;
            this.zzadx = null;
        }
    }
}
