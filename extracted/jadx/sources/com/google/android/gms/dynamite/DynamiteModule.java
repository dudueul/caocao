package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class DynamiteModule {

    @GuardedBy("DynamiteModule.class")
    private static Boolean zzabr;

    @GuardedBy("DynamiteModule.class")
    private static IDynamiteLoader zzabs;

    @GuardedBy("DynamiteModule.class")
    private static IDynamiteLoaderV2 zzabt;

    @GuardedBy("DynamiteModule.class")
    private static String zzabu;
    private final Context zzabx;
    private static final ThreadLocal<zza> zzabv = new ThreadLocal<>();
    private static final VersionPolicy.IVersions zzabw = new com.google.android.gms.dynamite.zza();
    public static final VersionPolicy PREFER_REMOTE = new com.google.android.gms.dynamite.zzb();
    public static final VersionPolicy PREFER_LOCAL = new zzc();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzd();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzf();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION_NO_FORCE_STAGING = new zzg();

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {

        @GuardedBy("DynamiteLoaderClassLoader.class")
        public static ClassLoader sClassLoader;
    }

    public static class LoadingException extends Exception {
        private LoadingException(String str) {
            super(str);
        }

        /* synthetic */ LoadingException(String str, com.google.android.gms.dynamite.zza zzaVar) {
            this(str);
        }

        private LoadingException(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ LoadingException(String str, Throwable th, com.google.android.gms.dynamite.zza zzaVar) {
            this(str, th);
        }
    }

    public interface VersionPolicy {

        public interface IVersions {
            int getLocalVersion(Context context, String str);

            int getRemoteVersion(Context context, String str, boolean z) throws LoadingException;
        }

        public static class SelectionResult {
            public int localVersion = 0;
            public int remoteVersion = 0;
            public int selection = 0;
        }

        SelectionResult selectModule(Context context, String str, IVersions iVersions) throws LoadingException;
    }

    private static class zza {
        public Cursor zzaby;

        private zza() {
        }

        /* synthetic */ zza(com.google.android.gms.dynamite.zza zzaVar) {
            this();
        }
    }

    private static class zzb implements VersionPolicy.IVersions {
        private final int zzabz;
        private final int zzaca = 0;

        public zzb(int i, int i2) {
            this.zzabz = i;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
        public final int getLocalVersion(Context context, String str) {
            return this.zzabz;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
        public final int getRemoteVersion(Context context, String str, boolean z) {
            return 0;
        }
    }

    private DynamiteModule(Context context) {
        this.zzabx = (Context) Preconditions.checkNotNull(context);
    }

    public static int getLocalVersion(Context context, String str) {
        int i;
        try {
            Class<?> clsLoadClass = context.getApplicationContext().getClassLoader().loadClass(new StringBuilder(String.valueOf(str).length() + 61).append("com.google.android.gms.dynamite.descriptors.").append(str).append(".ModuleDescriptor").toString());
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                i = declaredField2.getInt(null);
            } else {
                String strValueOf = String.valueOf(declaredField.get(null));
                Log.e("DynamiteModule", new StringBuilder(String.valueOf(strValueOf).length() + 51 + String.valueOf(str).length()).append("Module descriptor id '").append(strValueOf).append("' didn't match expected id '").append(str).append("'").toString());
                i = 0;
            }
            return i;
        } catch (ClassNotFoundException e) {
            Log.w("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 45).append("Local module descriptor class for ").append(str).append(" not found.").toString());
            return 0;
        } catch (Exception e2) {
            String strValueOf2 = String.valueOf(e2.getMessage());
            Log.e("DynamiteModule", strValueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(strValueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static Uri getQueryUri(String str, boolean z) {
        String str2 = z ? ProviderConstants.API_PATH_FORCE_STAGING : ProviderConstants.API_PATH;
        return Uri.parse(new StringBuilder(String.valueOf(str2).length() + 42 + String.valueOf(str).length()).append("content://com.google.android.gms.chimera/").append(str2).append("/").append(str).toString());
    }

    public static int getRemoteVersion(Context context, String str) {
        return getRemoteVersion(context, str, false);
    }

    public static int getRemoteVersion(Context context, String str, boolean z) {
        synchronized (DynamiteModule.class) {
            Boolean bool = zzabr;
            if (bool == null) {
                try {
                    Class<?> clsLoadClass = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName());
                    Field declaredField = clsLoadClass.getDeclaredField("sClassLoader");
                    synchronized (clsLoadClass) {
                        try {
                            ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                            if (classLoader != null) {
                                if (classLoader == ClassLoader.getSystemClassLoader()) {
                                    bool = Boolean.FALSE;
                                } else {
                                    try {
                                        zza(classLoader);
                                    } catch (LoadingException e) {
                                    }
                                    bool = Boolean.TRUE;
                                }
                            } else if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName())) {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                bool = Boolean.FALSE;
                            } else {
                                try {
                                    int iZzb = zzb(context, str, z);
                                    if (zzabu == null || zzabu.isEmpty()) {
                                        return iZzb;
                                    }
                                    zzh zzhVar = new zzh(zzabu, ClassLoader.getSystemClassLoader());
                                    zza(zzhVar);
                                    declaredField.set(null, zzhVar);
                                    zzabr = Boolean.TRUE;
                                    return iZzb;
                                } catch (LoadingException e2) {
                                    declaredField.set(null, ClassLoader.getSystemClassLoader());
                                    bool = Boolean.FALSE;
                                }
                            }
                            zzabr = bool;
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e3) {
                    String strValueOf = String.valueOf(e3);
                    Log.w("DynamiteModule", new StringBuilder(String.valueOf(strValueOf).length() + 30).append("Failed to load module via V2: ").append(strValueOf).toString());
                    bool = Boolean.FALSE;
                }
            }
            if (!bool.booleanValue()) {
                return zza(context, str, z);
            }
            try {
                return zzb(context, str, z);
            } catch (LoadingException e4) {
                String strValueOf2 = String.valueOf(e4.getMessage());
                Log.w("DynamiteModule", strValueOf2.length() != 0 ? "Failed to retrieve remote module version: ".concat(strValueOf2) : new String("Failed to retrieve remote module version: "));
                return 0;
            }
        }
    }

    @VisibleForTesting
    public static synchronized Boolean getUseV2ForTesting() {
        return zzabr;
    }

    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) throws LoadingException {
        zza zzaVar = zzabv.get();
        zza zzaVar2 = new zza(null);
        zzabv.set(zzaVar2);
        try {
            VersionPolicy.SelectionResult selectionResultSelectModule = versionPolicy.selectModule(context, str, zzabw);
            Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length()).append("Considering local module ").append(str).append(":").append(selectionResultSelectModule.localVersion).append(" and remote module ").append(str).append(":").append(selectionResultSelectModule.remoteVersion).toString());
            if (selectionResultSelectModule.selection == 0 || ((selectionResultSelectModule.selection == -1 && selectionResultSelectModule.localVersion == 0) || (selectionResultSelectModule.selection == 1 && selectionResultSelectModule.remoteVersion == 0))) {
                throw new LoadingException(new StringBuilder(91).append("No acceptable module found. Local version is ").append(selectionResultSelectModule.localVersion).append(" and remote version is ").append(selectionResultSelectModule.remoteVersion).append(".").toString(), (com.google.android.gms.dynamite.zza) null);
            }
            if (selectionResultSelectModule.selection == -1) {
                DynamiteModule dynamiteModuleZzd = zzd(context, str);
                if (zzaVar2.zzaby != null) {
                    zzaVar2.zzaby.close();
                }
                zzabv.set(zzaVar);
                return dynamiteModuleZzd;
            }
            if (selectionResultSelectModule.selection != 1) {
                throw new LoadingException(new StringBuilder(47).append("VersionPolicy returned invalid code:").append(selectionResultSelectModule.selection).toString(), (com.google.android.gms.dynamite.zza) null);
            }
            try {
                DynamiteModule dynamiteModuleZza = zza(context, str, selectionResultSelectModule.remoteVersion);
                if (zzaVar2.zzaby != null) {
                    zzaVar2.zzaby.close();
                }
                zzabv.set(zzaVar);
                return dynamiteModuleZza;
            } catch (LoadingException e) {
                String strValueOf = String.valueOf(e.getMessage());
                Log.w("DynamiteModule", strValueOf.length() != 0 ? "Failed to load remote module: ".concat(strValueOf) : new String("Failed to load remote module: "));
                if (selectionResultSelectModule.localVersion == 0 || versionPolicy.selectModule(context, str, new zzb(selectionResultSelectModule.localVersion, 0)).selection != -1) {
                    throw new LoadingException("Remote load failed. No local fallback found.", e, null);
                }
                DynamiteModule dynamiteModuleZzd2 = zzd(context, str);
                if (zzaVar2.zzaby != null) {
                    zzaVar2.zzaby.close();
                }
                zzabv.set(zzaVar);
                return dynamiteModuleZzd2;
            }
        } catch (Throwable th) {
            if (zzaVar2.zzaby != null) {
                zzaVar2.zzaby.close();
            }
            zzabv.set(zzaVar);
            throw th;
        }
    }

    public static Cursor queryForDynamiteModule(Context context, String str, boolean z) {
        return context.getContentResolver().query(getQueryUri(str, z), null, null, null, null);
    }

    @VisibleForTesting
    public static synchronized void resetInternalStateForTesting() {
        zzabs = null;
        zzabt = null;
        zzabu = null;
        zzabr = null;
        synchronized (DynamiteLoaderClassLoader.class) {
            DynamiteLoaderClassLoader.sClassLoader = null;
        }
    }

    @VisibleForTesting
    public static synchronized void setUseV2ForTesting(Boolean bool) {
        zzabr = bool;
    }

    private static int zza(Context context, String str, boolean z) {
        IDynamiteLoader iDynamiteLoaderZzg = zzg(context);
        if (iDynamiteLoaderZzg == null) {
            return 0;
        }
        try {
            return iDynamiteLoaderZzg.getModuleVersion2(ObjectWrapper.wrap(context), str, z);
        } catch (RemoteException e) {
            String strValueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", strValueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(strValueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    private static Context zza(Context context, String str, int i, Cursor cursor, IDynamiteLoaderV2 iDynamiteLoaderV2) {
        try {
            return (Context) ObjectWrapper.unwrap(iDynamiteLoaderV2.loadModule2(ObjectWrapper.wrap(context), str, i, ObjectWrapper.wrap(cursor)));
        } catch (Exception e) {
            String strValueOf = String.valueOf(e.toString());
            Log.e("DynamiteModule", strValueOf.length() != 0 ? "Failed to load DynamiteLoader: ".concat(strValueOf) : new String("Failed to load DynamiteLoader: "));
            return null;
        }
    }

    private static DynamiteModule zza(Context context, String str, int i) throws LoadingException {
        Boolean bool;
        synchronized (DynamiteModule.class) {
            bool = zzabr;
        }
        if (bool == null) {
            throw new LoadingException("Failed to determine which loading route to use.", (com.google.android.gms.dynamite.zza) null);
        }
        return bool.booleanValue() ? zzc(context, str, i) : zzb(context, str, i);
    }

    @GuardedBy("DynamiteModule.class")
    private static void zza(ClassLoader classLoader) throws LoadingException {
        try {
            zzabt = IDynamiteLoaderV2.Stub.asInterface((IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0026  */
    private static int zzb(Context context, String str, boolean z) throws Throwable {
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQueryForDynamiteModule = queryForDynamiteModule(context, str, z);
                if (cursorQueryForDynamiteModule != null) {
                    try {
                        if (cursorQueryForDynamiteModule.moveToFirst()) {
                            int i = cursorQueryForDynamiteModule.getInt(0);
                            if (i > 0) {
                                synchronized (DynamiteModule.class) {
                                    zzabu = cursorQueryForDynamiteModule.getString(2);
                                }
                                zza zzaVar = zzabv.get();
                                if (zzaVar != null && zzaVar.zzaby == null) {
                                    zzaVar.zzaby = cursorQueryForDynamiteModule;
                                    cursorQueryForDynamiteModule = null;
                                }
                            }
                            if (cursorQueryForDynamiteModule != null) {
                                cursorQueryForDynamiteModule.close();
                            }
                            return i;
                        }
                    } catch (Exception e) {
                        e = e;
                        if (e instanceof LoadingException) {
                            throw e;
                        }
                        throw new LoadingException("V2 version check failed", e, null);
                    }
                }
                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                throw new LoadingException("Failed to connect to dynamite module ContentResolver.", (com.google.android.gms.dynamite.zza) null);
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws LoadingException {
        com.google.android.gms.dynamite.zza zzaVar = null;
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        IDynamiteLoader iDynamiteLoaderZzg = zzg(context);
        if (iDynamiteLoaderZzg == null) {
            throw new LoadingException("Failed to create IDynamiteLoader.", zzaVar);
        }
        try {
            IObjectWrapper iObjectWrapperCreateModuleContext = iDynamiteLoaderZzg.createModuleContext(ObjectWrapper.wrap(context), str, i);
            if (ObjectWrapper.unwrap(iObjectWrapperCreateModuleContext) == null) {
                throw new LoadingException("Failed to load remote module.", zzaVar);
            }
            return new DynamiteModule((Context) ObjectWrapper.unwrap(iObjectWrapperCreateModuleContext));
        } catch (RemoteException e) {
            throw new LoadingException("Failed to load remote module.", e, zzaVar);
        }
    }

    private static DynamiteModule zzc(Context context, String str, int i) throws LoadingException {
        IDynamiteLoaderV2 iDynamiteLoaderV2;
        com.google.android.gms.dynamite.zza zzaVar = null;
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        synchronized (DynamiteModule.class) {
            iDynamiteLoaderV2 = zzabt;
        }
        if (iDynamiteLoaderV2 == null) {
            throw new LoadingException("DynamiteLoaderV2 was not cached.", zzaVar);
        }
        zza zzaVar2 = zzabv.get();
        if (zzaVar2 == null || zzaVar2.zzaby == null) {
            throw new LoadingException("No result cursor", zzaVar);
        }
        Context contextZza = zza(context.getApplicationContext(), str, i, zzaVar2.zzaby, iDynamiteLoaderV2);
        if (contextZza == null) {
            throw new LoadingException("Failed to get module context", zzaVar);
        }
        return new DynamiteModule(contextZza);
    }

    private static DynamiteModule zzd(Context context, String str) {
        String strValueOf = String.valueOf(str);
        Log.i("DynamiteModule", strValueOf.length() != 0 ? "Selected local version of ".concat(strValueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static IDynamiteLoader zzg(Context context) {
        synchronized (DynamiteModule.class) {
            if (zzabs != null) {
                return zzabs;
            }
            if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context) != 0) {
                return null;
            }
            try {
                IDynamiteLoader iDynamiteLoaderAsInterface = IDynamiteLoader.Stub.asInterface((IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance());
                if (iDynamiteLoaderAsInterface != null) {
                    zzabs = iDynamiteLoaderAsInterface;
                    return iDynamiteLoaderAsInterface;
                }
            } catch (Exception e) {
                String strValueOf = String.valueOf(e.getMessage());
                Log.e("DynamiteModule", strValueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(strValueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
            }
            return null;
        }
    }

    public final Context getModuleContext() {
        return this.zzabx;
    }

    public final IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzabx.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String strValueOf = String.valueOf(str);
            throw new LoadingException(strValueOf.length() != 0 ? "Failed to instantiate module class: ".concat(strValueOf) : new String("Failed to instantiate module class: "), e, null);
        }
    }
}
