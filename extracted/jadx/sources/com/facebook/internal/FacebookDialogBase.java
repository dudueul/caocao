package com.facebook.internal;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookDialog;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class FacebookDialogBase<CONTENT, RESULT> implements FacebookDialog<CONTENT, RESULT> {
    protected static final Object BASE_AUTOMATIC_MODE = new Object();
    private static final String TAG = "FacebookDialog";
    private final Activity activity;
    private final FragmentWrapper fragmentWrapper;
    private List<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> modeHandlers;
    private int requestCode;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract class ModeHandler {
        protected ModeHandler() {
        }

        public abstract boolean canShow(CONTENT content, boolean z);

        public abstract AppCall createAppCall(CONTENT content);

        public Object getMode() {
            return FacebookDialogBase.BASE_AUTOMATIC_MODE;
        }
    }

    protected FacebookDialogBase(Activity activity, int i) {
        Validate.notNull(activity, "activity");
        this.activity = activity;
        this.fragmentWrapper = null;
        this.requestCode = i;
    }

    protected FacebookDialogBase(FragmentWrapper fragmentWrapper, int i) {
        Validate.notNull(fragmentWrapper, "fragmentWrapper");
        this.fragmentWrapper = fragmentWrapper;
        this.activity = null;
        this.requestCode = i;
        if (fragmentWrapper.getActivity() == null) {
            throw new IllegalArgumentException("Cannot use a fragment that is not attached to an activity");
        }
    }

    private List<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> cachedModeHandlers() {
        if (this.modeHandlers == null) {
            this.modeHandlers = getOrderedModeHandlers();
        }
        return this.modeHandlers;
    }

    private AppCall createAppCallForMode(CONTENT content, Object obj) {
        boolean z = obj == BASE_AUTOMATIC_MODE;
        AppCall appCallCreateBaseAppCall = null;
        for (FacebookDialogBase<CONTENT, RESULT>.ModeHandler modeHandler : cachedModeHandlers()) {
            if (z || Utility.areObjectsEqual(modeHandler.getMode(), obj)) {
                if (modeHandler.canShow(content, true)) {
                    try {
                        appCallCreateBaseAppCall = modeHandler.createAppCall(content);
                        break;
                    } catch (FacebookException e) {
                        appCallCreateBaseAppCall = createBaseAppCall();
                        DialogPresenter.setupAppCallForValidationError(appCallCreateBaseAppCall, e);
                    }
                }
            }
        }
        if (appCallCreateBaseAppCall != null) {
            return appCallCreateBaseAppCall;
        }
        AppCall appCallCreateBaseAppCall2 = createBaseAppCall();
        DialogPresenter.setupAppCallForCannotShowError(appCallCreateBaseAppCall2);
        return appCallCreateBaseAppCall2;
    }

    @Override // com.facebook.FacebookDialog
    public boolean canShow(CONTENT content) {
        return canShowImpl(content, BASE_AUTOMATIC_MODE);
    }

    protected boolean canShowImpl(CONTENT content, Object obj) {
        boolean z = obj == BASE_AUTOMATIC_MODE;
        for (FacebookDialogBase<CONTENT, RESULT>.ModeHandler modeHandler : cachedModeHandlers()) {
            if (z || Utility.areObjectsEqual(modeHandler.getMode(), obj)) {
                if (modeHandler.canShow(content, false)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected abstract AppCall createBaseAppCall();

    protected Activity getActivityContext() {
        if (this.activity != null) {
            return this.activity;
        }
        if (this.fragmentWrapper != null) {
            return this.fragmentWrapper.getActivity();
        }
        return null;
    }

    protected abstract List<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> getOrderedModeHandlers();

    public int getRequestCode() {
        return this.requestCode;
    }

    @Override // com.facebook.FacebookDialog
    public final void registerCallback(CallbackManager callbackManager, FacebookCallback<RESULT> facebookCallback) {
        if (!(callbackManager instanceof CallbackManagerImpl)) {
            throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
        }
        registerCallbackImpl((CallbackManagerImpl) callbackManager, facebookCallback);
    }

    @Override // com.facebook.FacebookDialog
    public final void registerCallback(CallbackManager callbackManager, FacebookCallback<RESULT> facebookCallback, int i) {
        setRequestCode(i);
        registerCallback(callbackManager, facebookCallback);
    }

    protected abstract void registerCallbackImpl(CallbackManagerImpl callbackManagerImpl, FacebookCallback<RESULT> facebookCallback);

    protected void setRequestCode(int i) {
        if (FacebookSdk.isFacebookRequestCode(i)) {
            throw new IllegalArgumentException("Request code " + i + " cannot be within the range reserved by the Facebook SDK.");
        }
        this.requestCode = i;
    }

    @Override // com.facebook.FacebookDialog
    public void show(CONTENT content) {
        showImpl(content, BASE_AUTOMATIC_MODE);
    }

    protected void showImpl(CONTENT content, Object obj) {
        AppCall appCallCreateAppCallForMode = createAppCallForMode(content, obj);
        if (appCallCreateAppCallForMode == null) {
            Log.e(TAG, "No code path should ever result in a null appCall");
            if (FacebookSdk.isDebugEnabled()) {
                throw new IllegalStateException("No code path should ever result in a null appCall");
            }
        } else if (this.fragmentWrapper != null) {
            DialogPresenter.present(appCallCreateAppCallForMode, this.fragmentWrapper);
        } else {
            DialogPresenter.present(appCallCreateAppCallForMode, this.activity);
        }
    }

    protected void startActivityForResult(Intent intent, int i) {
        String str = null;
        if (this.activity != null) {
            this.activity.startActivityForResult(intent, i);
        } else if (this.fragmentWrapper == null) {
            str = "Failed to find Activity or Fragment to startActivityForResult ";
        } else if (this.fragmentWrapper.getNativeFragment() != null) {
            this.fragmentWrapper.getNativeFragment().startActivityForResult(intent, i);
        } else if (this.fragmentWrapper.getSupportFragment() != null) {
            this.fragmentWrapper.getSupportFragment().startActivityForResult(intent, i);
        } else {
            str = "Failed to find Activity or Fragment to startActivityForResult ";
        }
        if (str != null) {
            Logger.log(LoggingBehavior.DEVELOPER_ERRORS, 6, getClass().getName(), str);
        }
    }
}
