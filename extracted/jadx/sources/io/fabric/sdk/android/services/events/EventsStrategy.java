package io.fabric.sdk.android.services.events;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface EventsStrategy<T> extends FileRollOverManager, EventsManager<T> {
    FilesSender getFilesSender();
}
