package com.facebook.internal;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FacebookDialogFragment extends DialogFragment {
    public static final String TAG = "FacebookDialogFragment";
    private Dialog dialog;

    /* JADX INFO: Access modifiers changed from: private */
    public void onCompleteWebDialog(Bundle bundle, FacebookException facebookException) {
        FragmentActivity activity = getActivity();
        activity.setResult(facebookException == null ? -1 : 0, NativeProtocol.createProtocolResultIntent(activity.getIntent(), bundle, facebookException));
        activity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCompleteWebFallbackDialog(Bundle bundle) {
        FragmentActivity activity = getActivity();
        Intent intent = new Intent();
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        activity.setResult(-1, intent);
        activity.finish();
    }

    @Override // android.support.v4.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if ((this.dialog instanceof WebDialog) && isResumed()) {
            ((WebDialog) this.dialog).resize();
        }
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        WebDialog webDialogNewInstance;
        super.onCreate(bundle);
        if (this.dialog == null) {
            FragmentActivity activity = getActivity();
            Bundle methodArgumentsFromIntent = NativeProtocol.getMethodArgumentsFromIntent(activity.getIntent());
            if (methodArgumentsFromIntent.getBoolean(NativeProtocol.WEB_DIALOG_IS_FALLBACK, false)) {
                String string = methodArgumentsFromIntent.getString("url");
                if (Utility.isNullOrEmpty(string)) {
                    Utility.logd(TAG, "Cannot start a fallback WebDialog with an empty/missing 'url'");
                    activity.finish();
                    return;
                } else {
                    webDialogNewInstance = FacebookWebFallbackDialog.newInstance(activity, string, String.format("fb%s://bridge/", FacebookSdk.getApplicationId()));
                    webDialogNewInstance.setOnCompleteListener(new WebDialog.OnCompleteListener() { // from class: com.facebook.internal.FacebookDialogFragment.2
                        @Override // com.facebook.internal.WebDialog.OnCompleteListener
                        public void onComplete(Bundle bundle2, FacebookException facebookException) {
                            FacebookDialogFragment.this.onCompleteWebFallbackDialog(bundle2);
                        }
                    });
                }
            } else {
                String string2 = methodArgumentsFromIntent.getString(NativeProtocol.WEB_DIALOG_ACTION);
                Bundle bundle2 = methodArgumentsFromIntent.getBundle("params");
                if (Utility.isNullOrEmpty(string2)) {
                    Utility.logd(TAG, "Cannot start a WebDialog with an empty/missing 'actionName'");
                    activity.finish();
                    return;
                }
                webDialogNewInstance = new WebDialog.Builder(activity, string2, bundle2).setOnCompleteListener(new WebDialog.OnCompleteListener() { // from class: com.facebook.internal.FacebookDialogFragment.1
                    @Override // com.facebook.internal.WebDialog.OnCompleteListener
                    public void onComplete(Bundle bundle3, FacebookException facebookException) {
                        FacebookDialogFragment.this.onCompleteWebDialog(bundle3, facebookException);
                    }
                }).build();
            }
            this.dialog = webDialogNewInstance;
        }
    }

    @Override // android.support.v4.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        if (this.dialog == null) {
            onCompleteWebDialog(null, null);
            setShowsDialog(false);
        }
        return this.dialog;
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.dialog instanceof WebDialog) {
            ((WebDialog) this.dialog).resize();
        }
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
}
