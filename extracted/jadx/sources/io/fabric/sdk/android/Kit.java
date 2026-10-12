package io.fabric.sdk.android;

import android.content.Context;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import io.fabric.sdk.android.services.concurrency.Task;
import java.io.File;
import java.util.Collection;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class Kit<Result> implements Comparable<Kit> {
    Context context;
    Fabric fabric;
    IdManager idManager;
    InitializationCallback<Result> initializationCallback;
    InitializationTask<Result> initializationTask = new InitializationTask<>(this);
    final DependsOn dependsOnAnnotation = (DependsOn) getClass().getAnnotation(DependsOn.class);

    @Override // java.lang.Comparable
    public int compareTo(Kit kit) {
        if (containsAnnotatedDependency(kit)) {
            return 1;
        }
        if (kit.containsAnnotatedDependency(this)) {
            return -1;
        }
        if (!hasAnnotatedDependency() || kit.hasAnnotatedDependency()) {
            return (hasAnnotatedDependency() || !kit.hasAnnotatedDependency()) ? 0 : -1;
        }
        return 1;
    }

    boolean containsAnnotatedDependency(Kit kit) {
        if (!hasAnnotatedDependency()) {
            return false;
        }
        for (Class<?> cls : this.dependsOnAnnotation.value()) {
            if (cls.isAssignableFrom(kit.getClass())) {
                return true;
            }
        }
        return false;
    }

    protected abstract Result doInBackground();

    public Context getContext() {
        return this.context;
    }

    protected Collection<Task> getDependencies() {
        return this.initializationTask.getDependencies();
    }

    public Fabric getFabric() {
        return this.fabric;
    }

    protected IdManager getIdManager() {
        return this.idManager;
    }

    public abstract String getIdentifier();

    public String getPath() {
        return ".Fabric" + File.separator + getIdentifier();
    }

    public abstract String getVersion();

    boolean hasAnnotatedDependency() {
        return this.dependsOnAnnotation != null;
    }

    final void initialize() {
        this.initializationTask.executeOnExecutor(this.fabric.getExecutorService(), (Object[]) new Void[]{(Void) null});
    }

    void injectParameters(Context context, Fabric fabric, InitializationCallback<Result> initializationCallback, IdManager idManager) {
        this.fabric = fabric;
        this.context = new FabricContext(context, getIdentifier(), getPath());
        this.initializationCallback = initializationCallback;
        this.idManager = idManager;
    }

    protected void onCancelled(Result result) {
    }

    protected void onPostExecute(Result result) {
    }

    protected boolean onPreExecute() {
        return true;
    }
}
