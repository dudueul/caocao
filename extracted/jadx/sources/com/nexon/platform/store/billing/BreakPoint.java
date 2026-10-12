package com.nexon.platform.store.billing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import com.nexon.platform.store.NexonStore;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class BreakPoint {

    interface ActionCallback {
        void onContinued(Error error);
    }

    public static void checkContinue(final String str, boolean z, final ActionCallback actionCallback) {
        if (!z) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.nexon.platform.store.billing.BreakPoint.1
                @Override // java.lang.Runnable
                public void run() {
                    Activity activityForDebug = NexonStore.getActivityForDebug();
                    if (activityForDebug == null) {
                        if (actionCallback != null) {
                            actionCallback.onContinued(null);
                        }
                    } else {
                        AlertDialog alertDialogCreate = new AlertDialog.Builder(activityForDebug).setTitle(str).setMessage("Continue?").setPositiveButton("YES", new DialogInterface.OnClickListener() { // from class: com.nexon.platform.store.billing.BreakPoint.1.3
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (actionCallback != null) {
                                    actionCallback.onContinued(null);
                                }
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() { // from class: com.nexon.platform.store.billing.BreakPoint.1.2
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (actionCallback != null) {
                                    actionCallback.onContinued(Error.createError(Constants.ErrorCode.BillingInvalidTransactionStateError));
                                }
                            }
                        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.nexon.platform.store.billing.BreakPoint.1.1
                            @Override // android.content.DialogInterface.OnCancelListener
                            public void onCancel(DialogInterface dialogInterface) {
                                if (actionCallback != null) {
                                    actionCallback.onContinued(Error.createError(Constants.ErrorCode.BillingInvalidTransactionStateError));
                                }
                            }
                        }).create();
                        alertDialogCreate.setCanceledOnTouchOutside(false);
                        alertDialogCreate.show();
                    }
                }
            });
        } else if (actionCallback != null) {
            actionCallback.onContinued(null);
        }
    }
}
