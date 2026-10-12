package com.crashlytics.android.beta;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class ImmediateCheckForUpdatesController extends AbstractCheckForUpdatesController {
    public ImmediateCheckForUpdatesController() {
        super(true);
    }

    @Override // com.crashlytics.android.beta.UpdatesController
    public boolean isActivityLifecycleTriggered() {
        return false;
    }
}
