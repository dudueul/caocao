package com.naver.glink.android.sdk;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Keep;
import android.widget.Toast;
import com.naver.glink.android.sdk.a.y;
import com.naver.glink.android.sdk.ui.record.RecordManager;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Keep
public class PlugRecordManager {

    @Keep
    public interface OnRecordManagerListener {
        void onErrorRecord();

        void onFinishRecord(String str);

        void onStartRecord();
    }

    public static boolean isRecording() {
        return com.naver.glink.android.sdk.ui.record.b.c().b();
    }

    public static void setOnRecordManagerListener(OnRecordManagerListener onRecordManagerListener) {
        b.a(onRecordManagerListener);
    }

    public static void startRecord(final Activity activity) {
        final com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult.b bVar;
        if (d.q() && activity != null) {
            if (!com.naver.glink.android.sdk.ui.write.b.a(activity, NXRuntimePermissionManager.WRITE_EXTERNAL_STORAGE)) {
                com.naver.glink.android.sdk.ui.write.b.a(activity, NXRuntimePermissionManager.WRITE_EXTERNAL_STORAGE, activity.getString(R.string.record_permission_error), 8090);
            } else {
                if (isRecording() || (bVar = (com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult.b) com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult.b.a(activity)) == null) {
                    return;
                }
                bVar.a(new com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult.b.a() { // from class: com.naver.glink.android.sdk.PlugRecordManager.1
                    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult.b.a
                    public void a() {
                        if (bVar == null || bVar.isDetached()) {
                            return;
                        }
                        com.naver.glink.android.sdk.ui.record.a aVarC = com.naver.glink.android.sdk.ui.record.b.c();
                        aVarC.a(bVar, com.naver.glink.android.sdk.ui.record.c.a());
                        aVarC.a(new RecordManager.b() { // from class: com.naver.glink.android.sdk.PlugRecordManager.1.1
                            @Override // com.naver.glink.android.sdk.ui.record.RecordManager.b
                            public void a() {
                                b.e();
                            }

                            @Override // com.naver.glink.android.sdk.ui.record.RecordManager.b
                            public void a(long j) {
                            }

                            @Override // com.naver.glink.android.sdk.ui.record.RecordManager.b
                            public void a(String str) {
                                if (activity != null) {
                                    Toast.makeText(activity, activity.getResources().getString(R.string.record_saved), 0).show();
                                    activity.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", y.a("file://" + str)));
                                }
                                b.c(y.a("file://" + str).toString());
                            }

                            @Override // com.naver.glink.android.sdk.ui.record.RecordManager.b
                            public void b() {
                                b.f();
                            }
                        });
                    }
                });
            }
        }
    }

    public static void stopRecord() {
        if (d.q()) {
            com.naver.glink.android.sdk.ui.record.b.c().a();
        }
    }
}
