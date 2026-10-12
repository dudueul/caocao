package com.facebook;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.facebook.internal.FacebookDialogFragment;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.login.LoginFragment;
import com.facebook.share.internal.DeviceShareDialogFragment;
import com.facebook.share.model.ShareContent;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FacebookActivity extends FragmentActivity {
    private Fragment singleFragment;
    public static String PASS_THROUGH_CANCEL_ACTION = "PassThrough";
    private static String FRAGMENT_TAG = "SingleFragment";
    private static final String TAG = FacebookActivity.class.getName();

    private void handlePassThroughError() {
        setResult(0, NativeProtocol.createProtocolResultIntent(getIntent(), null, NativeProtocol.getExceptionFromErrorData(NativeProtocol.getMethodArgumentsFromIntent(getIntent()))));
        finish();
    }

    public Fragment getCurrentFragment() {
        return this.singleFragment;
    }

    protected Fragment getFragment() {
        Intent intent = getIntent();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment fragmentFindFragmentByTag = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (fragmentFindFragmentByTag != null) {
            return fragmentFindFragmentByTag;
        }
        if (FacebookDialogFragment.TAG.equals(intent.getAction())) {
            FacebookDialogFragment facebookDialogFragment = new FacebookDialogFragment();
            facebookDialogFragment.setRetainInstance(true);
            facebookDialogFragment.show(supportFragmentManager, FRAGMENT_TAG);
            return facebookDialogFragment;
        }
        if (!DeviceShareDialogFragment.TAG.equals(intent.getAction())) {
            LoginFragment loginFragment = new LoginFragment();
            loginFragment.setRetainInstance(true);
            supportFragmentManager.beginTransaction().add(com.facebook.common.R.id.com_facebook_fragment_container, loginFragment, FRAGMENT_TAG).commit();
            return loginFragment;
        }
        DeviceShareDialogFragment deviceShareDialogFragment = new DeviceShareDialogFragment();
        deviceShareDialogFragment.setRetainInstance(true);
        deviceShareDialogFragment.setShareContent((ShareContent) intent.getParcelableExtra("content"));
        deviceShareDialogFragment.show(supportFragmentManager, FRAGMENT_TAG);
        return deviceShareDialogFragment;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.singleFragment != null) {
            this.singleFragment.onConfigurationChanged(configuration);
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (!FacebookSdk.isInitialized()) {
            Utility.logd(TAG, "Facebook SDK not initialized. Make sure you call sdkInitialize inside your Application's onCreate method.");
            FacebookSdk.sdkInitialize(getApplicationContext());
        }
        setContentView(com.facebook.common.R.layout.com_facebook_activity_layout);
        if (PASS_THROUGH_CANCEL_ACTION.equals(intent.getAction())) {
            handlePassThroughError();
        } else {
            this.singleFragment = getFragment();
        }
    }
}
