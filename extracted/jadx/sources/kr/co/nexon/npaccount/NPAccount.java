package kr.co.nexon.npaccount;

import android.accounts.Account;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.View;
import com.facebook.FacebookSdk;
import com.facebook.GraphResponse;
import com.google.gson.reflect.TypeToken;
import com.nexon.core.NXToyVersion;
import com.nexon.core.android.NXPActivityLifecycleCallbackAdapter;
import com.nexon.core.android.NXPActivityLifecycleCallbackManager;
import com.nexon.core.android.NXPApplicationConfigManager;
import com.nexon.core.android.NXPApplicationManager;
import com.nexon.core.locale.NXLocale;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.preference.model.NXToyMaintenanceEnterToyResultInfo;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.session.model.NXToyTerm;
import com.nexon.core.util.NXCrypto.NXCrypto;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.ngsm.Ngsm;
import com.nexon.ngsm.NgsmResult;
import com.nexon.npaccount.R;
import com.nexon.platform.NXPFinalizer;
import com.nexon.platform.store.NexonStore;
import com.nexon.platform.store.billing.Billing;
import com.nexon.platform.store.billing.Constants;
import com.nexon.platform.store.billing.Error;
import com.nexon.platform.store.billing.PaymentInfo;
import com.nexon.platform.store.billing.Product;
import com.nexon.platform.store.billing.Transaction;
import com.nexon.platform.store.billing.vendor.VendorHolder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import kr.co.nexon.mdev.android.activity.NPActivityResultManager;
import kr.co.nexon.mdev.android.permission.NXPRequestPermissionInfo;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionListener;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager;
import kr.co.nexon.mdev.android.push.local.NPNotification;
import kr.co.nexon.mdev.android.push.local.NPNotificationData;
import kr.co.nexon.mdev.android.storage.NPAStorage;
import kr.co.nexon.mdev.android.util.NXAccountUtil;
import kr.co.nexon.mdev.android.util.NXActivityUtil;
import kr.co.nexon.mdev.android.util.NXAdsUtil;
import kr.co.nexon.mdev.android.util.NXApplicationUtil;
import kr.co.nexon.mdev.android.util.NXPackageUtil;
import kr.co.nexon.mdev.android.util.NXTelephonyUtil;
import kr.co.nexon.npaccount.auth.NXPProviderManager;
import kr.co.nexon.npaccount.auth.NXToyAuthManager;
import kr.co.nexon.npaccount.auth.request.NXToyCheckPaidGoogleAccountRequest;
import kr.co.nexon.npaccount.auth.request.NXToyEnterToyRequest;
import kr.co.nexon.npaccount.auth.request.NXToyGetNexonSNByNXKLoginRequest;
import kr.co.nexon.npaccount.auth.request.NXToyLinkPaidGoogleAccountRequest;
import kr.co.nexon.npaccount.auth.request.NXToyUnregisterPushRequest;
import kr.co.nexon.npaccount.auth.result.NXToyBillingResult;
import kr.co.nexon.npaccount.auth.result.NXToyCloseResult;
import kr.co.nexon.npaccount.auth.result.NXToyEnterResult;
import kr.co.nexon.npaccount.auth.result.NXToyNexonSNResult;
import kr.co.nexon.npaccount.auth.result.NXToyRequestProductsResult;
import kr.co.nexon.npaccount.auth.result.NXToyServiceInfoResult;
import kr.co.nexon.npaccount.auth.result.model.NXPToyOfferwall;
import kr.co.nexon.npaccount.auth.result.model.NXToyBillingProduct;
import kr.co.nexon.npaccount.auth.result.model.NXToyBillingTransaction;
import kr.co.nexon.npaccount.auth.result.model.NXToyService;
import kr.co.nexon.npaccount.billing.NXPPaymentInfo;
import kr.co.nexon.npaccount.board.NXBoardManager;
import kr.co.nexon.npaccount.gcm.NPFcmUtil;
import kr.co.nexon.npaccount.listener.NPBannerListener;
import kr.co.nexon.npaccount.listener.NPCloseListener;
import kr.co.nexon.npaccount.listener.NPEndingBannerListener;
import kr.co.nexon.npaccount.listener.NPGCMListener;
import kr.co.nexon.npaccount.listener.NPPlateListener;
import kr.co.nexon.npaccount.listener.NPRuntimePermissionListener;
import kr.co.nexon.npaccount.plate.NXToyPlateManager;
import kr.co.nexon.npaccount.plate.result.NXToyPlateActionPerformedResult;
import kr.co.nexon.npaccount.policy.NXPPolicyManager;
import kr.co.nexon.npaccount.promotion.NPPromotionManager;
import kr.co.nexon.npaccount.promotion.NXBannerManager;
import kr.co.nexon.npaccount.push.NXPPush;
import kr.co.nexon.npaccount.push.NXPPushPolicies;
import kr.co.nexon.npaccount.push.request.v1.NXToyRegisterPushRequest;
import kr.co.nexon.npaccount.secondpassword.NXPSecondPasswordManager;
import kr.co.nexon.npaccount.setting.NPOptionManager;
import kr.co.nexon.npaccount.setting.NPOptions;
import kr.co.nexon.npaccount.sns.NXPGameCenterManager;
import kr.co.nexon.npaccount.sns.NXPSNSManager;
import kr.co.nexon.npaccount.stats.NXLog;
import kr.co.nexon.npaccount.terms.NXPTermsManager;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.android.ui.constants.NPPermissionRequestCodes;
import kr.co.nexon.toy.android.ui.etc.NPShareDialog;
import kr.co.nexon.toy.listener.NPListener;
import kr.co.nexon.utility.StringUtil;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPAccount {
    public static final String FRIEND_FILTER_TYPE_ALL = "";
    public static final String FRIEND_FILTER_TYPE_FRIENDS = "friends";
    public static final String FRIEND_FILTER_TYPE_INVITES = "invites";
    public static final int PUSH_AGREE = 1;
    public static final int PUSH_DISAGREE = 2;
    public static final int REQ_COMMON_SHARE_CODE = 38936;
    public static final String TERMS_HETHOD_BASEPLATE = "baseplate";
    public static final String TERMS_METHOD_APP = "app";
    private Activity activity;
    private Context applicationContext;
    private NXToyAuthManager authManager;
    private NXBannerManager bannerManager;
    private NXBoardManager boardManager;
    private final NXToyLocaleManager localeManager;
    private NXToyPlateManager plateManager;
    private NXPPolicyManager policyManager;
    private NXToySessionManager sessionManager;
    private NPAStorage storage;
    private NXPTermsManager termsManager;
    public static final int LoginTypeNotLogined = NXToyLoginType.LoginTypeNotLogined.getValue();
    public static final int LoginTypeDefault = NXToyLoginType.LoginTypeDefault.getValue();
    public static final int LoginTypeNXCom = NXToyLoginType.LoginTypeNXCom.getValue();
    public static final int LoginTypeNaverChannel = NXToyLoginType.LoginTypeNaverChannel.getValue();
    public static final int LoginTypeEmail = NXToyLoginType.LoginTypeEmail.getValue();
    public static final int LoginTypeDaumChannel = NXToyLoginType.LoginTypeDaumChannel.getValue();
    public static final int LoginTypeFaceBook = NXToyLoginType.LoginTypeFaceBook.getValue();
    public static final int LoginTypeTwitter = NXToyLoginType.LoginTypeTwitter.getValue();
    public static final int LoginTypeGoogle = NXToyLoginType.LoginTypeGoogle.getValue();
    public static final int LoginTypeKakao = NXToyLoginType.LoginTypeKakao.getValue();
    public static final int LoginTypeNaver = NXToyLoginType.LoginTypeNaver.getValue();
    public static final int LoginTypeNXOneId = NXToyLoginType.LoginTypeNXOneId.getValue();
    public static final int LoginTypeMapleId = NXToyLoginType.LoginTypeMapleId.getValue();
    public static final int LoginTypeNXNet = NXToyLoginType.LoginTypeNXNet.getValue();
    public static final int LoginTypePlayPark = NXToyLoginType.LoginTypePlayPark.getValue();
    public static final int LoginTypeGameCenter = NXToyLoginType.LoginTypeGameCenter.getValue();
    public static final int LoginTypeVKontakte = NXToyLoginType.LoginTypeVKontakte.getValue();
    public static final int LoginTypeGuest = NXToyLoginType.LoginTypeGuest.getValue();
    public static final int SnsTypeFaceBook = NXToyLoginType.LoginTypeFaceBook.getValue();
    public static final int SnsTypeTwitter = NXToyLoginType.LoginTypeTwitter.getValue();
    public static final int PLATE_ACTION_TYPE_DATA_BACKUP = NXToyRequestTag.DataBackup.getValue();
    public static final int PLATE_ACTION_TYPE_DATA_RESTORE = NXToyRequestTag.DataRestore.getValue();
    public static final int PLATE_ACTION_TYPE_META = NXToyRequestTag.PlateButtonClick.getValue();
    public static final int PROMOTION_CLICK_TYPE_META = NXToyRequestTag.PromotionMeta.getValue();
    public static final int PROMOTION_CLICK_TYPE_METANPID = NXToyRequestTag.PromotionPid.getValue();
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: kr.co.nexon.npaccount.NPAccount.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            NPAccount unused = NPAccount.instance = null;
        }
    };
    private static volatile NPAccount instance = null;

    /* JADX INFO: renamed from: kr.co.nexon.npaccount.NPAccount$16, reason: invalid class name */
    class AnonymousClass16 implements NXToyRequestListener {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ NPListener val$listener;

        AnonymousClass16(NPListener nPListener, Activity activity) {
            this.val$listener = nPListener;
            this.val$activity = activity;
        }

        @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
        public void onComplete(NXToyResult nXToyResult) {
            final NPListener nPListener = new NPListener() { // from class: kr.co.nexon.npaccount.NPAccount.16.1
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(final NXToyResult nXToyResult2) {
                    if (AnonymousClass16.this.val$listener != null) {
                        if (!NPOptionManager.getInstance().getOptions().isNgsmEnabled()) {
                            AnonymousClass16.this.val$listener.onResult(nXToyResult2);
                        } else {
                            Ngsm.getInst().ngsmInit(AnonymousClass16.this.val$activity, Integer.valueOf(NXToySessionManager.getInstance().getSession().getServiceId()).intValue(), new Ngsm.OnInitListener() { // from class: kr.co.nexon.npaccount.NPAccount.16.1.1
                                @Override // com.nexon.ngsm.Ngsm.OnInitListener
                                public void onInit(NgsmResult ngsmResult) {
                                    AnonymousClass16.this.val$listener.onResult(nXToyResult2);
                                }
                            });
                        }
                    }
                }
            };
            final NXToyEnterResult nXToyEnterResult = (NXToyEnterResult) nXToyResult;
            if (nXToyEnterResult != null && nXToyEnterResult.result != null) {
                NPAccount.this.saveToyBaseInfo(nXToyEnterResult.result);
                if (nXToyEnterResult.result.service != null) {
                    if (nXToyEnterResult.result.service.buildVer == 2) {
                        ToyLog.setEnableSendServer(nXToyEnterResult.result.enableLogging);
                    } else {
                        ToyLog.setEnableSendServer(true);
                    }
                }
            }
            int loginType = NPAccount.this.getLoginType();
            if (loginType == NPAccount.LoginTypeGameCenter || NPAccount.this.authManager.isFastLogin(loginType)) {
                NPAccount.this.connectGamePlatform(NPAccount.this.getActivity(), new NPListener() { // from class: kr.co.nexon.npaccount.NPAccount.16.2
                    @Override // kr.co.nexon.toy.listener.NPListener
                    public void onResult(NXToyResult nXToyResult2) {
                        NPAccount.this.handleEnterToyResult(nXToyEnterResult, nPListener);
                    }
                });
            } else {
                NPAccount.this.handleEnterToyResult(nXToyEnterResult, nPListener);
            }
        }
    }

    private NPAccount(Activity activity) {
        this.applicationContext = activity.getApplicationContext();
        setActivity(activity);
        this.storage = NPAStorage.getInstance();
        this.localeManager = NXToyLocaleManager.getInstance(this.applicationContext);
        this.sessionManager = NXToySessionManager.getInstance();
        loggingDefaultInfos(activity);
        this.authManager = new NXToyAuthManager(this.applicationContext);
        NXPProviderManager.getInstance().initialize(activity);
        registerNgsm();
        this.bannerManager = NXBannerManager.getInstance(this.applicationContext);
        this.boardManager = NXBoardManager.getInstance(this.applicationContext);
        this.plateManager = NXToyPlateManager.getInstance();
        this.policyManager = NXPPolicyManager.getInstance();
        this.termsManager = NXPTermsManager.getInstance();
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        nXToyCommonPreferenceController.setAppVersionCode(NXPackageUtil.getAppVersionCode(this.applicationContext));
        nXToyCommonPreferenceController.setAppId(NXPackageUtil.getApplicationId(this.applicationContext));
        nXToyCommonPreferenceController.setCarrierName(NXTelephonyUtil.getNetworkOperatorName(this.applicationContext));
        initLocalPush(activity);
        NXPPush.initialize(this.applicationContext);
        VendorHolder.getInstance().initialize(this.applicationContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkPaidGoogleAccount(final Activity activity, final NPListener nPListener) {
        String str = null;
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        for (Account account : NXAccountUtil.getAccounts(activity, NXAccountUtil.TYPE_GOOGLE)) {
            if (pattern.matcher(account.name).matches()) {
                str = account.name;
                ToyLog.d("Email : " + NXStringUtil.getMaskedEmail(str));
                ToyLog.d("account.type : " + account.type);
                break;
            }
        }
        if (str != null) {
            final String str2 = str;
            NXToyRequestPostman.getInstance().postRequest(new NXToyCheckPaidGoogleAccountRequest(str2), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.NPAccount.11
                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                public void onComplete(final NXToyResult nXToyResult) {
                    if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                        nPListener.onResult(nXToyResult);
                    } else if (nXToyResult.errorCode == 40001) {
                        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.NPAccount.11.1
                            @Override // java.lang.Runnable
                            public void run() {
                                String accountId = NPAccount.this.sessionManager.getSession().getAccountId();
                                if (!NXStringUtil.isNull(accountId)) {
                                    NPAccount.this.linkPaidGoogleAccount(activity, nXToyResult.errorText, str2, accountId, nPListener);
                                } else if (nPListener != null) {
                                    NXToyResult nXToyResult2 = new NXToyResult(NXToyErrorCode.NEED_TO_GET_USER_INFO.getCode(), "GetUserInfo API, you must call first.");
                                    nXToyResult2.requestTag = NXToyRequestTag.LinkPaidGoogleAccount.getValue();
                                    nPListener.onResult(nXToyResult2);
                                }
                            }
                        });
                    } else {
                        nPListener.onResult(nXToyResult);
                    }
                }
            });
        } else if (nPListener != null) {
            NXToyResult nXToyResult = new NXToyResult(NXToyErrorCode.FAILED_GET_GOOGLE_ACCOUNT.getCode(), this.localeManager.getString(R.string.npres_failed_get_google_account));
            nXToyResult.requestTag = NXToyRequestTag.LinkPaidGoogleAccount.getValue();
            nPListener.onResult(nXToyResult);
        }
    }

    @Nullable
    public static NPAccount getInstance() {
        if (instance == null) {
            return null;
        }
        return instance;
    }

    public static NPAccount getInstance(Activity activity) throws IllegalArgumentException {
        NXPApplicationConfigManager nXPApplicationConfigManager;
        if (instance == null) {
            synchronized (NPAccount.class) {
                if (instance == null) {
                    try {
                        nXPApplicationConfigManager = NXPApplicationConfigManager.getInstance(true);
                    } catch (IllegalArgumentException e) {
                        String name = activity.getApplication().getClass().getName();
                        if ("android.app.Application".equals(name)) {
                            ToyLog.dd("Application is default. Not allowed default application. name:" + name);
                            throw e;
                        }
                        ToyLog.i("Application is not initialized in NPAccount.getInstance()");
                        ToyLog.setupLogcatLogger(activity.getPackageManager());
                        Context applicationContext = activity.getApplicationContext();
                        nXPApplicationConfigManager = NXPApplicationConfigManager.getInstance();
                        nXPApplicationConfigManager.initialize(applicationContext);
                        NXToyCommonPreferenceController.getInstance().initialize(applicationContext);
                        NXToySessionManager.getInstance().initialize(applicationContext);
                        NXToyRequestPostman.getInstance().initialize(applicationContext);
                    }
                    NexonStore.setActivityForDebug(nXPApplicationConfigManager.isEnabledStoreDebug() ? activity : null);
                    instance = new NPAccount(activity);
                }
            }
        } else if (!activity.equals(instance.getActivity())) {
            instance.setActivity(activity);
        }
        return instance;
    }

    @Deprecated
    public static NPAccount getInstance(Activity activity, String str) {
        return getInstance(activity);
    }

    private boolean getNgsmMetadata() {
        return NXApplicationUtil.getAppMetaDataTypeBoolean(this.activity.getApplicationContext(), "NgsmInitializeOnLoad");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEnterToyResult(NXToyEnterResult nXToyEnterResult, final NPListener nPListener) {
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        String serviceId = NXToySessionManager.getInstance().getSession().getServiceId();
        if (serviceId != null && ((serviceId.contains("1492") || serviceId.contains("1430") || serviceId.contains("1431")) && !nXToyCommonPreferenceController.getIsInitializedHasPhoneNumberFlag())) {
            nXToyCommonPreferenceController.removeHasPhoneNumberFlag();
            nXToyCommonPreferenceController.setIsInitializedHasPhoneNumberFlag();
        }
        NXToyResult nXToyResult = new NXToyResult();
        if (nXToyEnterResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
            nXToyResult.errorCode = nXToyEnterResult.errorCode;
            nXToyResult.errorDetail = nXToyEnterResult.errorDetail;
            nXToyResult.errorText = nXToyEnterResult.errorText;
            nXToyResult.requestTag = NXToyRequestTag.EnterToy.getValue();
        } else if (getLoginType() != LoginTypeNotLogined) {
            NXPTermsManager.getInstance().checkAdditionalTermsFromEnterToy(this.activity, nXToyEnterResult.result.termsAgree, nXToyEnterResult.result.isPrivacyConsigned, new NPListener() { // from class: kr.co.nexon.npaccount.NPAccount.17
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult2) {
                    final NXToyResult nXToyResult3 = new NXToyResult();
                    nXToyResult3.requestTag = NXToyRequestTag.EnterToy.getValue();
                    if (nXToyResult2.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                        nPListener.onResult(nXToyResult3);
                    } else {
                        NPAccount.this.logout(new NPListener() { // from class: kr.co.nexon.npaccount.NPAccount.17.1
                            @Override // kr.co.nexon.toy.listener.NPListener
                            public void onResult(NXToyResult nXToyResult4) {
                                String string = NPAccount.this.localeManager.getString(R.string.npres_logincancel);
                                nXToyResult3.errorCode = NXToyErrorCode.TOYSERVER_AUTH_NOT_VALID.getCode();
                                nXToyResult3.errorText = string;
                                nXToyResult3.errorDetail = string;
                                nXToyResult3.requestTag = NXToyRequestTag.EnterToy.getValue();
                                nPListener.onResult(nXToyResult3);
                            }
                        });
                    }
                }
            });
            return;
        } else {
            ToyLog.d("In handleEnterToyResult, getLoginType() is LoginTypeNotLogined so set 5001 to errorCode ");
            nXToyResult.errorCode = NXToyErrorCode.TOYSERVER_AUTH_NOT_VALID.getCode();
            nXToyResult.errorDetail = this.localeManager.getString(R.string.npres_get_userinfo_fail);
            nXToyResult.errorText = this.localeManager.getString(R.string.npres_get_userinfo_fail);
            nXToyResult.requestTag = NXToyRequestTag.EnterToy.getValue();
        }
        if (nXToyResult.errorCode == NXToyErrorCode.TOYSERVER_CONSOLE_MAINTENANCE.getCode()) {
            NXToyCommonPreferenceController.getInstance().setMaintenanceEnterToyResultInfo(new NXToyMaintenanceEnterToyResultInfo(System.currentTimeMillis(), nXToyResult));
            nPListener.onResult(nXToyResult);
        } else if (!isAuthCrashError(nXToyResult.errorCode)) {
            nPListener.onResult(nXToyResult);
        } else {
            NXPSNSManager.getInstance().disconnectAll(this.applicationContext);
            nPListener.onResult(nXToyResult);
        }
    }

    private void initLocalPush(Activity activity) {
        ToyLog.d("initLocalPush");
        try {
            NPNotification.initLocalPush(activity.getApplicationContext(), activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 128).metaData.getString("LocalPushReceiveClassName"));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initializeNexonStore(NexonStore.InitializeCallback initializeCallback) {
        String storeClientId = NXPApplicationConfigManager.getInstance().getStoreClientId();
        if (StringUtil.isNotNull(storeClientId)) {
            NexonStore.initialize(storeClientId, this.activity, initializeCallback);
        } else {
            NexonStore.initialize(this.activity, initializeCallback);
        }
    }

    private void loggingDefaultInfos(Activity activity) {
        ToyLog.i("Initialize Logging.", "toyServiceId", this.sessionManager.getSession().getServiceId(), "androidUserVisibleVersion", Build.VERSION.RELEASE, "androidSdkVersion", Integer.valueOf(Build.VERSION.SDK_INT), "os", "android", "deviceModel", Build.MODEL, "toyVersion", NXToyVersion.VERSION, "base64SHA1", NXPackageUtil.getBase64SHA1FromKeyStore(this.applicationContext), "hexSHA1", NXPackageUtil.getHexStringSHA1FromKeyStore(this.applicationContext));
        ToyLog.d("Manifest Info", "gmsAppId", NXApplicationUtil.getAppMetaDataTypeString(activity, "com.google.android.gms.games.APP_ID"), "googleWebClientId", NXApplicationUtil.getAppMetaDataTypeString(activity, "com.google.oauth.server_web_client_id"), "fbAppId", NXApplicationUtil.getAppMetaDataTypeString(activity, FacebookSdk.APPLICATION_ID_PROPERTY), "manifestString", NXApplicationUtil.getAndroidManifest(activity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NXToyResult makeBillingResult(List<Transaction> list) {
        NXToyBillingResult nXToyBillingResult = new NXToyBillingResult();
        ArrayList arrayList = new ArrayList();
        for (Transaction transaction : list) {
            NXToyBillingTransaction nXToyBillingTransaction = new NXToyBillingTransaction();
            if (transaction.getState() != null) {
                nXToyBillingTransaction.state = NXToyBillingTransaction.State.getStateFromValue(transaction.getState().getValue());
            }
            if (transaction.getProductId() != null) {
                nXToyBillingTransaction.productId = transaction.getProductId();
            }
            if (transaction.getStampId() != null) {
                nXToyBillingTransaction.stampId = transaction.getStampId();
            }
            if (transaction.getStampToken() != null) {
                nXToyBillingTransaction.stampToken = transaction.getStampToken();
            }
            if (transaction.getServicePayloadString() != null) {
                nXToyBillingTransaction.servicePayload = transaction.getServicePayloadString();
            }
            Error error = transaction.getError();
            if (error != null) {
                nXToyBillingTransaction.error.code = error.getCode();
                nXToyBillingTransaction.error.message = error.getMessage();
                nXToyBillingTransaction.error.description = error.getDescription();
            }
            arrayList.add(nXToyBillingTransaction);
        }
        nXToyBillingResult.result.billingTransactions = arrayList;
        return nXToyBillingResult;
    }

    public static void onApplicationAttachBaseContext(Application application) {
        NXPApplicationManager.getInstance().onAttachBaseContext(application);
    }

    public static void onApplicationCreated(Application application) {
        NXPApplicationManager.getInstance().onCreate(application);
    }

    private void registerNgsm() {
        NPOptions options = NPOptionManager.getInstance().getOptions();
        boolean ngsmMetadata = getNgsmMetadata();
        if (!options.isNgsmEnabled() && ngsmMetadata) {
            options.setNgsmEnabled(true);
            setOptions(options);
        }
        if (options.isNgsmEnabled()) {
            NXPActivityLifecycleCallbackManager.getInstance().addActivityLifecycleCallbacks(new NXPActivityLifecycleCallbackAdapter() { // from class: kr.co.nexon.npaccount.NPAccount.26
                final String luancherActivityName;

                {
                    this.luancherActivityName = NXActivityUtil.getLuancherActivityName(NPAccount.this.activity);
                }

                @Override // com.nexon.core.android.NXPActivityLifecycleCallbackAdapter, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                    if (this.luancherActivityName == null || !activity.getClass().getName().equals(this.luancherActivityName)) {
                        return;
                    }
                    activity.getApplication().unregisterActivityLifecycleCallbacks(this);
                }

                @Override // com.nexon.core.android.NXPActivityLifecycleCallbackAdapter, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                    if (this.luancherActivityName == null || !activity.getClass().getName().equals(this.luancherActivityName)) {
                        return;
                    }
                    Ngsm.getInst().ngsmPause();
                }

                @Override // com.nexon.core.android.NXPActivityLifecycleCallbackAdapter, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    if (this.luancherActivityName == null || !activity.getClass().getName().equals(this.luancherActivityName)) {
                        return;
                    }
                    Ngsm.getInst().ngsmResume();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestEnterToy(Activity activity, NPListener nPListener) {
        int i;
        int i2 = 0;
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        NXToyMaintenanceEnterToyResultInfo maintenanceEnterToyResultInfo = nXToyCommonPreferenceController.getMaintenanceEnterToyResultInfo();
        if (maintenanceEnterToyResultInfo != null && maintenanceEnterToyResultInfo.getEnterToyResult() != null) {
            if (maintenanceEnterToyResultInfo.getNextSendingTime() > System.currentTimeMillis()) {
                if (nPListener != null) {
                    nPListener.onResult(maintenanceEnterToyResultInfo.getEnterToyResult());
                    return;
                }
                return;
            }
            nXToyCommonPreferenceController.removeMaintenanceEnterToyResultInfo();
        }
        String mcc = NXTelephonyUtil.getMcc(activity);
        String mnc = NXTelephonyUtil.getMnc(activity);
        if (mnc == null) {
            i = 0;
        } else {
            try {
                i = Integer.parseInt(mnc);
            } catch (Exception e) {
                i = 0;
                i2 = 0;
            }
        }
        if (mcc != null) {
            i2 = Integer.parseInt(mcc);
        }
        nXToyCommonPreferenceController.setMNC(mnc);
        nXToyCommonPreferenceController.setMCC(mcc);
        NXToyRequestPostman.getInstance().postRequest(new NXToyEnterToyRequest(i, i2), new AnonymousClass16(nPListener, activity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveToyBaseInfo(NXToyEnterResult.ResultSet resultSet) {
        ToyLog.d("enterToyResult:" + resultSet.toString());
        NXToyService nXToyService = resultSet.service;
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        if (nXToyService != null) {
            nXToyCommonPreferenceController.setServiceTitle(nXToyService.title);
            nXToyCommonPreferenceController.setLoginUIType(nXToyService.loginUIType);
            nXToyCommonPreferenceController.setNxkATL(nXToyService.nxkATL);
            nXToyCommonPreferenceController.setServiceClientId(nXToyService.clientId);
            nXToyCommonPreferenceController.setUseMemberships(NXJsonUtil.toJsonString(nXToyService.useMemberships));
            nXToyCommonPreferenceController.setPolicyApiVer(nXToyService.policyApiVer);
            nXToyCommonPreferenceController.setTermsApiVer(nXToyService.termsApiVer);
            nXToyCommonPreferenceController.setUseTPA(nXToyService.useTPA == 1);
            Iterator<Integer> it = nXToyService.useMemberships.iterator();
            while (it.hasNext()) {
                if (it.next().intValue() == LoginTypeGameCenter) {
                    nXToyCommonPreferenceController.setUseGCID(true);
                }
            }
        }
        if (resultSet.endBanner != null) {
            nXToyCommonPreferenceController.setEndingBanner(NXJsonUtil.toJsonString(resultSet.endBanner));
        }
        if (resultSet.basePlate != null) {
            nXToyCommonPreferenceController.setBasePlate(getLocale().getLocaleCode(), NXJsonUtil.toJsonString(resultSet.basePlate));
        }
        if (nXToyCommonPreferenceController.getInitCountryFlag().equals("")) {
            nXToyCommonPreferenceController.setCountry(resultSet.country);
        }
        nXToyCommonPreferenceController.setInitCountryFlag(resultSet.country);
        if (resultSet.offerwall != null) {
            nXToyCommonPreferenceController.setOfferwall(NXJsonUtil.toJsonString(resultSet.offerwall));
        }
    }

    public void NXLogin(Activity activity, String str, String str2, NPListener nPListener) {
        this.authManager.loginWithNX(activity, str, str2, nPListener);
    }

    public void NXLogin(Activity activity, String str, byte[] bArr, NPListener nPListener) {
        this.authManager.loginWithNX(activity, str, bArr, nPListener);
    }

    public void agree(Activity activity, List<NXToyTerm> list, NPListener nPListener) {
        this.termsManager.agree(activity, list, nPListener);
    }

    @Deprecated
    public void billingFinish(String str, NPListener nPListener) {
        ToyLog.d("billingFinish  stampToken:" + str);
        if (nPListener != null) {
            NXToyBillingResult nXToyBillingResult = new NXToyBillingResult();
            nXToyBillingResult.result.billingTransactions = new ArrayList();
            nXToyBillingResult.requestTag = NXToyRequestTag.BillingFinish.getValue();
            nPListener.onResult(nXToyBillingResult);
        }
    }

    @Deprecated
    public void billingPayment(String str, JSONObject jSONObject, Activity activity, NPListener nPListener) {
        billingPayment(new NXPPaymentInfo(str).setMeta(jSONObject), activity, nPListener);
    }

    @Deprecated
    public void billingPayment(String str, JSONObject jSONObject, JSONObject jSONObject2, Activity activity, NPListener nPListener) {
        billingPayment(new NXPPaymentInfo(str).setMeta(jSONObject).setServicePayload(jSONObject2), activity, nPListener);
    }

    public void billingPayment(NXPPaymentInfo nXPPaymentInfo, final Activity activity, final NPListener nPListener) {
        ToyLog.d("billingPayment. paymentInfo:" + nXPPaymentInfo);
        if (getLoginType() == LoginTypeNotLogined) {
            NXToyBillingResult nXToyBillingResult = new NXToyBillingResult(NXToyErrorCode.TOYSERVER_AUTH_NOT_VALID.getCode(), "not logged in user", "not logged in user", NXToyRequestTag.BillingPayment.getValue());
            if (nPListener != null) {
                nPListener.onResult(nXToyBillingResult);
                return;
            }
            return;
        }
        if (nXPPaymentInfo == null) {
            int value = Constants.ErrorCode.BillingParameterInvalidError.getValue();
            String message = Constants.ErrorCode.BillingParameterInvalidError.getMessage();
            NXToyBillingResult nXToyBillingResult2 = new NXToyBillingResult(value, message, message, NXToyRequestTag.BillingPayment.getValue());
            if (nPListener != null) {
                nPListener.onResult(nXToyBillingResult2);
                return;
            }
            return;
        }
        String strValueOf = String.valueOf(this.sessionManager.getSession().getNpsn());
        final Billing.RequestPaymentCallback requestPaymentCallback = new Billing.RequestPaymentCallback() { // from class: kr.co.nexon.npaccount.NPAccount.22
            @Override // com.nexon.platform.store.billing.Billing.RequestPaymentCallback
            public void onRequestPaymentCompleted(Transaction transaction) {
                NXToyResult nXToyResultMakeBillingResult = NPAccount.this.makeBillingResult(Arrays.asList(transaction));
                Error error = transaction.getError();
                if (error != null) {
                    nXToyResultMakeBillingResult.errorCode = error.getCode();
                    nXToyResultMakeBillingResult.errorText = error.getMessage();
                    nXToyResultMakeBillingResult.errorDetail = error.getDescription();
                }
                nXToyResultMakeBillingResult.requestTag = NXToyRequestTag.BillingPayment.getValue();
                if (nPListener != null) {
                    nPListener.onResult(nXToyResultMakeBillingResult);
                }
            }
        };
        final PaymentInfo paymentInfoCreatePaymentInfo = nXPPaymentInfo.createPaymentInfo(strValueOf);
        if (NexonStore.isInitialized()) {
            Billing.requestPayment(paymentInfoCreatePaymentInfo, activity, requestPaymentCallback);
        } else {
            initializeNexonStore(new NexonStore.InitializeCallback() { // from class: kr.co.nexon.npaccount.NPAccount.23
                @Override // com.nexon.platform.store.NexonStore.InitializeCallback
                public void onInitialized(NexonStore.Error error) {
                    if (error == null) {
                        Billing.requestPayment(paymentInfoCreatePaymentInfo, activity, requestPaymentCallback);
                        return;
                    }
                    NXToyBillingResult nXToyBillingResult3 = new NXToyBillingResult(error.code, error.message, error.description, NXToyRequestTag.BillingPayment.getValue());
                    if (nPListener != null) {
                        nPListener.onResult(nXToyBillingResult3);
                    }
                }
            });
        }
    }

    public void billingRequestProducts(final List<String> list, final NPListener nPListener) {
        final Billing.RequestProductsCallback requestProductsCallback = new Billing.RequestProductsCallback() { // from class: kr.co.nexon.npaccount.NPAccount.20
            @Override // com.nexon.platform.store.billing.Billing.RequestProductsCallback
            public void onRequestProductsCompleted(List<Product> list2, Error error) {
                ToyLog.d("onRequestProductCompleted. error:" + error + "  list :" + list2);
                NXToyRequestProductsResult nXToyRequestProductsResult = new NXToyRequestProductsResult();
                nXToyRequestProductsResult.requestTag = NXToyRequestTag.BillingRequestProduct.getValue();
                if (error != null) {
                    nXToyRequestProductsResult.errorCode = error.getCode();
                    nXToyRequestProductsResult.errorText = error.getMessage();
                    nXToyRequestProductsResult.errorDetail = error.getDescription();
                } else {
                    ArrayList arrayList = new ArrayList();
                    for (Product product : list2) {
                        NXToyBillingProduct nXToyBillingProduct = new NXToyBillingProduct();
                        nXToyBillingProduct.valid = product.isValid() ? 1 : 0;
                        nXToyBillingProduct.priceAmountMicros = product.getPriceAmountMicros();
                        if (product.getProductId() != null) {
                            nXToyBillingProduct.productId = product.getProductId();
                        }
                        if (product.getLocalizedDescription() != null) {
                            nXToyBillingProduct.localizedDescription = product.getLocalizedDescription();
                        }
                        if (product.getLocalizedPrice() != null) {
                            nXToyBillingProduct.localizedPrice = product.getLocalizedPrice();
                        }
                        if (product.getLocalizedTitle() != null) {
                            nXToyBillingProduct.localizedTitle = product.getLocalizedTitle();
                        }
                        if (product.getPriceCurrencyCode() != null) {
                            nXToyBillingProduct.priceCurrencyCode = product.getPriceCurrencyCode();
                        }
                        if (product.getOriginalProductDetailJson() != null) {
                            nXToyBillingProduct.skuDetailJsonStr = product.getOriginalProductDetailJson().toString();
                        }
                        arrayList.add(nXToyBillingProduct);
                    }
                    nXToyRequestProductsResult.result.products = arrayList;
                }
                if (nPListener != null) {
                    ToyLog.d("billingRequestProducts result:" + nXToyRequestProductsResult);
                    nPListener.onResult(nXToyRequestProductsResult);
                }
            }
        };
        if (NexonStore.isInitialized()) {
            Billing.requestProducts(list, requestProductsCallback);
        } else {
            initializeNexonStore(new NexonStore.InitializeCallback() { // from class: kr.co.nexon.npaccount.NPAccount.21
                @Override // com.nexon.platform.store.NexonStore.InitializeCallback
                public void onInitialized(NexonStore.Error error) {
                    if (error == null) {
                        Billing.requestProducts(list, requestProductsCallback);
                        return;
                    }
                    NXToyRequestProductsResult nXToyRequestProductsResult = new NXToyRequestProductsResult(error.code, error.message, error.description, NXToyRequestTag.BillingRequestProduct.getValue());
                    if (nPListener != null) {
                        nPListener.onResult(nXToyRequestProductsResult);
                    }
                }
            });
        }
    }

    public void billingRestore(@Nullable final Activity activity, final boolean z, final NPListener nPListener) {
        ToyLog.d("billingRestore, Activity : " + activity + ", showProgressIndicator : " + z);
        if (getLoginType() == LoginTypeNotLogined) {
            NXToyBillingResult nXToyBillingResult = new NXToyBillingResult(NXToyErrorCode.TOYSERVER_AUTH_NOT_VALID.getCode(), "not logged in user", "not logged in user", NXToyRequestTag.BillingRestore.getValue());
            if (nPListener != null) {
                nPListener.onResult(nXToyBillingResult);
                return;
            }
            return;
        }
        final String strValueOf = String.valueOf(this.sessionManager.getSession().getNpsn());
        final Billing.RestoreCallback restoreCallback = new Billing.RestoreCallback() { // from class: kr.co.nexon.npaccount.NPAccount.24
            @Override // com.nexon.platform.store.billing.Billing.RestoreCallback
            public void onRestored(List<Transaction> list) {
                NXToyResult nXToyResultMakeBillingResult = NPAccount.this.makeBillingResult(list);
                nXToyResultMakeBillingResult.requestTag = NXToyRequestTag.BillingRestore.getValue();
                if (nPListener != null) {
                    nPListener.onResult(nXToyResultMakeBillingResult);
                }
            }
        };
        if (NexonStore.isInitialized()) {
            Billing.restore(activity, z, strValueOf, restoreCallback);
        } else {
            initializeNexonStore(new NexonStore.InitializeCallback() { // from class: kr.co.nexon.npaccount.NPAccount.25
                @Override // com.nexon.platform.store.NexonStore.InitializeCallback
                public void onInitialized(NexonStore.Error error) {
                    if (error == null) {
                        Billing.restore(activity, z, strValueOf, restoreCallback);
                        return;
                    }
                    ToyLog.d("NexonStore initialize failed error:" + error);
                    NXToyBillingResult nXToyBillingResult2 = new NXToyBillingResult(error.code, error.message, error.description, NXToyRequestTag.BillingRestore.getValue());
                    if (nPListener != null) {
                        nPListener.onResult(nXToyBillingResult2);
                    }
                }
            });
        }
    }

    public void billingRestore(NPListener nPListener) {
        billingRestore(null, false, nPListener);
    }

    public void cancelAllLocalPush(Activity activity) {
        ToyLog.d("cancelAllLocalPush");
        NPNotification.cancelAll(activity.getApplicationContext());
    }

    public void cancelLocalPush(Activity activity, int i) {
        ToyLog.d("cancelLocalPush notificationID:" + i);
        NPNotification.cancel(activity.getApplicationContext(), i);
    }

    public void changeAccount(Activity activity, int i, NPListener nPListener) {
        this.authManager.changeAccount(activity, i, nPListener);
    }

    public void clear() {
        this.sessionManager.removeSession();
    }

    public void closeNotice() {
        this.boardManager.closeNotice();
    }

    public void commonShare(Activity activity, String str, String str2, String str3) {
        NPShareDialog.newInstance(activity, str, str2, str3).showDialog(activity, NPShareDialog.TAG);
    }

    public void commonShareForResult(Activity activity, String str, String str2, String str3, NPListener nPListener) {
        NPShareDialog nPShareDialogNewInstance = NPShareDialog.newInstance(activity, str, str2, str3);
        nPShareDialogNewInstance.setResultListener(nPListener);
        nPShareDialogNewInstance.showDialog(activity, NPShareDialog.TAG);
    }

    public void connectGamePlatform(@NonNull Activity activity, NPListener nPListener) {
        ToyLog.d("connectGamePlatform");
        NXPGameCenterManager.getInstance().connect(activity, nPListener);
    }

    public void disconnectGamePlatform(Activity activity, NPListener nPListener) {
        ToyLog.d("disconnectGamePlatform");
        NXPGameCenterManager.getInstance().disconnect(nPListener);
    }

    public void dispatchLocalPush(Activity activity, NPNotificationData nPNotificationData) {
        ToyLog.d("dispatchLocalPush, activity " + activity + ", pushType : " + nPNotificationData.pushType);
        NPNotification.dispatch(activity.getApplicationContext(), nPNotificationData);
    }

    public void emailLogin(Activity activity, String str, String str2, NPListener nPListener) {
        this.authManager.loginWithEmail(activity, str, str2, nPListener);
    }

    public void emailSignUp(Activity activity, String str, String str2, NPListener nPListener) {
        this.authManager.signUpWithEmail(activity, str, str2, nPListener);
    }

    public void enterToy(final Activity activity, final NPListener nPListener) {
        ToyLog.d("enterToy");
        if (NXStringUtil.isNotNull(NXAdsUtil.getCachedAdvertisingId())) {
            requestEnterToy(activity, nPListener);
        } else {
            NXAdsUtil.getAdvertisingIdAfterCaching(this.applicationContext, new NPListener() { // from class: kr.co.nexon.npaccount.NPAccount.15
                @Override // kr.co.nexon.toy.listener.NPListener
                public void onResult(NXToyResult nXToyResult) {
                    NPAccount.this.requestEnterToy(activity, nPListener);
                }
            });
        }
    }

    @Deprecated
    public void fbActivateApp() {
        NXPSNSManager.getInstance().fbActivateApp();
    }

    public void fbActivateApp(@NonNull Application application) {
        NXPSNSManager.getInstance().fbActivateApp(application);
    }

    public void fbAddPermission(Activity activity, List<String> list, int i, NPListener nPListener) {
        NXPSNSManager.getInstance().fbAddPermission(activity, list, i, nPListener);
    }

    @Deprecated
    public void fbDeactivateApp() {
        NXPSNSManager.getInstance().fbDeactivateApp();
    }

    public void fbFetchDeferredAppLink(@NonNull Context context, NPListener nPListener) {
        NXPSNSManager.getInstance().fbFetchDeferredAppLink(context, nPListener);
    }

    public void fbGetFriends(int i, NPListener nPListener) {
        NXPSNSManager.getInstance().fbGetFriends(this.applicationContext, i, nPListener);
    }

    public void fbLogEvent(String str) {
        NXPSNSManager.getInstance().fbLogEvent(str);
    }

    public void fbLogEvent(String str, double d) {
        NXPSNSManager.getInstance().fbLogEvent(str, d);
    }

    public void fbLogEvent(String str, double d, String str2) {
        NXPSNSManager.getInstance().fbLogEvent(str, d, str2);
    }

    public void fbLogEvent(String str, String str2) {
        NXPSNSManager.getInstance().fbLogEvent(str, str2);
    }

    public void fbLogPurchase(Double d, String str, String str2) {
        NXPSNSManager.getInstance().fbLogPurchase(d, str, str2);
    }

    public void fbSetIsDebugEnabled(boolean z) {
        NXPSNSManager.getInstance().fbSetIsDebugEnabled(z);
    }

    public void fbShare(Activity activity, String str, String str2, String str3, String str4, NPListener nPListener) {
        NXPSNSManager.getInstance().fbShare(activity, str, str2, str3, str4, nPListener);
    }

    public void fbShareImage(Activity activity, Bitmap bitmap, NPListener nPListener) {
        NXPSNSManager.getInstance().fbShareImage(activity, bitmap, nPListener);
    }

    public boolean gcmInit(Activity activity, String str) {
        ToyLog.d("gcmInit  senderID:" + str);
        return gcmInit(activity, null, false, str);
    }

    public boolean gcmInit(Activity activity, String str, NPGCMListener nPGCMListener) {
        return gcmInit(activity, nPGCMListener, true, str);
    }

    public boolean gcmInit(Activity activity, final NPGCMListener nPGCMListener, boolean z, String str) {
        ToyLog.d("gcmInit  isSkipErrorDialog:" + z + "  senderID:" + str);
        final NPFcmUtil nPFcmUtil = new NPFcmUtil();
        return nPFcmUtil.initializeFCM(activity, new NPGCMListener() { // from class: kr.co.nexon.npaccount.NPAccount.9
            @Override // kr.co.nexon.npaccount.listener.NPGCMListener
            public void onResult(int i) {
                if (i == NXToyErrorCode.SUCCESS.getCode()) {
                    NXPPush.registerPush(NPAccount.this.applicationContext, nPFcmUtil.getRegistrationToken(NPAccount.this.applicationContext));
                }
                if (nPGCMListener != null) {
                    nPGCMListener.onResult(i);
                }
            }
        }, z, str);
    }

    public Activity getActivity() {
        return this.activity;
    }

    public void getAdvertisingId(NPListener nPListener) {
        if (nPListener == null) {
            ToyLog.d("getAdvertisingId, listener is null");
        } else {
            NXAdsUtil.getAdvertisingId(this.applicationContext, nPListener);
        }
    }

    public void getBanners(int i, NPListener nPListener) {
        ToyLog.d("getBanners groupCode:" + i);
        this.bannerManager.getBanners(i, nPListener);
    }

    public NXLocale.COUNTRY getCountry() {
        NXLocale.COUNTRY country = this.localeManager.getCountry();
        ToyLog.d("getCountry :" + country);
        return country;
    }

    @Deprecated
    public void getCountryFromServer(NPListener nPListener) {
        ToyLog.d("getCountryFromServer");
        loadLocatedCountry(nPListener);
    }

    public String getCountryLetterCode() {
        String countryCode = this.localeManager.getCountry().getCountryCode();
        ToyLog.d("getCountryLetterCode:" + countryCode);
        return countryCode;
    }

    public void getFriends(int i, int i2, String str, NPListener nPListener) {
        NXPSNSManager.getInstance().getFriends(this.applicationContext, i, i2, str, nPListener);
    }

    public void getFriends(int i, String str, NPListener nPListener) {
        getFriends(getLoginType(), i, str, nPListener);
    }

    public void getFriends(String str, NPListener nPListener) {
        NXPSNSManager.getInstance().getFriends(this.applicationContext, str, nPListener);
    }

    public void getFriends(NPListener nPListener) {
        getFriends("", nPListener);
    }

    public NXLocale.LOCALE getLocale() {
        NXLocale.LOCALE locale = this.localeManager.getLocale();
        ToyLog.d("getLocale :" + locale);
        return locale;
    }

    public int getLoginType() {
        int type = NXToySessionManager.getInstance().getSession().getType();
        ToyLog.d("getLoginType : " + type);
        return type;
    }

    public void getNexonSN(Activity activity, NPListener nPListener) {
        this.authManager.getNexonSN(activity, false, nPListener);
    }

    public void getNexonSN(Activity activity, boolean z, NPListener nPListener) {
        this.authManager.getNexonSN(activity, z, nPListener);
    }

    public void getNexonSN(String str, String str2, final NPListener nPListener) {
        if (str.contains("@")) {
            try {
                str2 = NXCrypto.encodeHmacSha256ToHexString("NexonUser", str2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetNexonSNByNXKLoginRequest(str, str2), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.NPAccount.2
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                NXToyNexonSNResult nXToyNexonSNResult = (NXToyNexonSNResult) nXToyResult;
                nXToyNexonSNResult.result.nexonSNString = Long.toString(nXToyNexonSNResult.result.nexonSN);
                nPListener.onResult(nXToyNexonSNResult);
            }
        });
    }

    public void getNexonSNByNaverChannel(Activity activity, NPListener nPListener) {
        this.authManager.getNexonSNByNaverChannel(activity, nPListener);
    }

    public NPOptions getOptions() {
        return NPOptionManager.getInstance().getOptions();
    }

    public void getPlayerStats(boolean z, NPListener nPListener) {
        ToyLog.d("getPlayerStats, foreceReload:" + z + ", listener:" + nPListener);
        NXPGameCenterManager.getInstance().getPlayerStats(z, nPListener);
    }

    public void getPolicy(String str, NPListener nPListener) {
        this.policyManager.getPolicyListV1(this.applicationContext, str, nPListener);
    }

    public void getPromotion(Activity activity, String str, NPListener nPListener) {
        getPromotion(activity, str, true, nPListener);
    }

    public void getPromotion(Activity activity, String str, boolean z, NPListener nPListener) {
        ToyLog.d("getPromotion", "placementId", str);
        NPPromotionManager.getInstance(activity).getPromotionContent(str, z, nPListener);
    }

    public void getPushPolicy(NPListener nPListener) {
        this.policyManager.getPushPolicy(this.applicationContext, nPListener);
    }

    public String getServiceId() {
        return NXPApplicationConfigManager.getInstance().getServiceId();
    }

    public void getServiceInfo(NPListener nPListener) {
        NXToyServiceInfoResult nXToyServiceInfoResult;
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        if (nXToyCommonPreferenceController.getUseMemberships().equals("")) {
            nXToyServiceInfoResult = new NXToyServiceInfoResult(NXToyErrorCode.GET_SERVICE_INFO_FAIL.getCode(), this.localeManager.getString(R.string.npres_request_failed), this.localeManager.getString(R.string.npres_request_failed));
            nXToyServiceInfoResult.requestTag = NXToyRequestTag.GetSvcInfo.getValue();
        } else {
            nXToyServiceInfoResult = new NXToyServiceInfoResult(0, GraphResponse.SUCCESS_KEY, "");
            nXToyServiceInfoResult.requestTag = NXToyRequestTag.GetSvcInfo.getValue();
            nXToyServiceInfoResult.result.useMemberships = (List) NXJsonUtil.fromObject(nXToyCommonPreferenceController.getUseMemberships(), new TypeToken<ArrayList<Integer>>() { // from class: kr.co.nexon.npaccount.NPAccount.18
            }.getType());
            nXToyServiceInfoResult.result.title = nXToyCommonPreferenceController.getServiceTitle();
            nXToyServiceInfoResult.result.nxkATL = nXToyCommonPreferenceController.getNxkATL();
        }
        nPListener.onResult(nXToyServiceInfoResult);
    }

    public void getSmsEnabled(Activity activity, NPListener nPListener) {
        this.policyManager.getSmsEnabled(activity, nPListener);
    }

    public void getSnsConnectionStatus(Activity activity, NPListener nPListener) {
        NXPSNSManager.getInstance().queryAllConnectionStatus(activity, nPListener);
    }

    public void getSnsTokenList(Activity activity, NPListener nPListener) {
        NXPSNSManager.getInstance().getTokenList(activity, nPListener);
    }

    public void getSnsUserInfo(int i, Activity activity, NPListener nPListener) {
        NXPSNSManager.getInstance().getUserInfo(i, activity, nPListener);
    }

    public String getUUID() {
        return NXToyCommonPreferenceController.getInstance().getUUID();
    }

    public void getUserInfo(final NPListener nPListener) {
        ToyLog.d("getUserInfo");
        NXAdsUtil.getAdvertisingIdAfterCaching(this.applicationContext, new NPListener() { // from class: kr.co.nexon.npaccount.NPAccount.6
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NPAccount.this.authManager.getUserInfo(NPAccount.this.getActivity(), nPListener);
            }
        });
    }

    public void incrementAchievement(Activity activity, String str, int i) {
        ToyLog.d("incrementAchievement, achievementID:" + str + ", increment:" + i);
        NXPGameCenterManager.getInstance().incrementAchievement(activity, str, i);
    }

    public void incrementAchievementImmediate(Activity activity, String str, int i, NPListener nPListener) {
        ToyLog.d("incrementAchievementImmediate, achievementID:" + str + ", increment:" + i + ", listener:" + nPListener);
        NXPGameCenterManager.getInstance().incrementAchievementImmediate(activity, str, i, nPListener);
    }

    public void invite(Activity activity, int i, ArrayList<String> arrayList, String str, String str2, NPListener nPListener) {
        NXPSNSManager.getInstance().invite(activity, i, arrayList, str, str2, nPListener);
    }

    public boolean isAuthCrashError(int i) {
        ToyLog.d("isAuthCrashError  errorCode:" + i);
        return this.authManager.isAuthCrashError(i);
    }

    public boolean isAvailableOfferwall() {
        NXPToyOfferwall nXPToyOfferwall = (NXPToyOfferwall) NXJsonUtil.fromObject(NXToyCommonPreferenceController.getInstance().getOfferwall(), NXPToyOfferwall.class);
        return nXPToyOfferwall != null && nXPToyOfferwall.id > 0;
    }

    public boolean isEnableGamePlatform() {
        return NXPGameCenterManager.getInstance().isConnected();
    }

    public void linkPaidGoogleAccount(Activity activity, String str, final String str2, String str3, final NPListener nPListener) {
        new NXPAlertDialog.Builder(activity).setMessage(str.replace("{gid}", str2).replace("{nxid}", str3)).setPositiveButton(this.localeManager.getString(R.string.npres_link), new View.OnClickListener() { // from class: kr.co.nexon.npaccount.NPAccount.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NXToyRequestPostman.getInstance().postRequest(new NXToyLinkPaidGoogleAccountRequest(str2), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.NPAccount.14.1
                    @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                    public void onComplete(NXToyResult nXToyResult) {
                        nPListener.onResult(nXToyResult);
                    }
                });
            }
        }).setNegativeButton(this.localeManager.getString(R.string.npres_cancel), new View.OnClickListener() { // from class: kr.co.nexon.npaccount.NPAccount.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NXToyResult nXToyResult = new NXToyResult(NXToyErrorCode.USER_CANCEL_LINK_GOOGLE_ACCOUNT.getCode(), NPAccount.this.localeManager.getString(R.string.npres_user_cancel_link_google_account));
                nXToyResult.requestTag = NXToyRequestTag.LinkPaidGoogleAccount.getValue();
                nPListener.onResult(nXToyResult);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.npaccount.NPAccount.12
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                NXToyResult nXToyResult = new NXToyResult(NXToyErrorCode.USER_CANCEL_LINK_GOOGLE_ACCOUNT.getCode(), NPAccount.this.localeManager.getString(R.string.npres_user_cancel_link_google_account));
                nXToyResult.requestTag = NXToyRequestTag.LinkPaidGoogleAccount.getValue();
                nPListener.onResult(nXToyResult);
            }
        }).create().show();
    }

    public void linkWithGoogleAccount(final Activity activity, final NPListener nPListener) {
        if (getLoginType() == LoginTypeNXCom || getLoginType() == LoginTypeNaverChannel || getLoginType() == LoginTypeDaumChannel) {
            requestPermissions(activity, new NXPRequestPermissionInfo.Builder().setVisiblePrefix(true).addPermission(NXRuntimePermissionManager.GET_ACCOUNTS, 1).build(), NPPermissionRequestCodes.REQUEST_GET_ACCOUNT, new NPRuntimePermissionListener() { // from class: kr.co.nexon.npaccount.NPAccount.10
                @Override // kr.co.nexon.npaccount.listener.NPRuntimePermissionListener
                public void onResult(int i, String[] strArr, int[] iArr) {
                    if (iArr.length > 0 && iArr[0] == 0) {
                        NPAccount.this.checkPaidGoogleAccount(activity, nPListener);
                        return;
                    }
                    NXToyResult nXToyResult = new NXToyResult();
                    nXToyResult.errorCode = NXToyErrorCode.PERMISSION_DENIED.getCode();
                    nXToyResult.errorText = NPAccount.this.localeManager.getString(R.string.npres_runtime_permission_denied_msg);
                    nXToyResult.errorDetail = NPAccount.this.localeManager.getString(R.string.npres_runtime_permission_denied_msg);
                    nXToyResult.requestTag = NXToyRequestTag.LinkPaidGoogleAccount.getValue();
                    ToyLog.d(nXToyResult.toString());
                    nPListener.onResult(nXToyResult);
                }
            });
        } else if (nPListener != null) {
            NXToyResult nXToyResult = new NXToyResult(NXToyErrorCode.NOT_LOGINED_NEXON_COM.getCode(), this.localeManager.getString(R.string.npres_not_logined_nexon_com));
            nXToyResult.requestTag = NXToyRequestTag.LinkPaidGoogleAccount.getValue();
            nPListener.onResult(nXToyResult);
        }
    }

    public void loadAchievementData(@NonNull Activity activity, boolean z, NPListener nPListener) {
        ToyLog.d("loadAchievementData, forceReload:" + z + ", listener:" + nPListener);
        NXPGameCenterManager.getInstance().loadAchievementData(activity, z, nPListener);
    }

    public void loadCurrentPlayerLeaderboardScore(Activity activity, String str, int i, int i2, NPListener nPListener) {
        ToyLog.d("loadCurrentPlayerLeaderboardScore, leaderboardID:" + str + ", span:" + i + ", leaderboardCollection:" + i2 + ", listener:" + nPListener);
        NXPGameCenterManager.getInstance().loadCurrentPlayerLeaderboardScore(activity, str, i, i2, nPListener);
    }

    public void loadLocatedCountry(NPListener nPListener) {
        ToyLog.d("loadLocatedCountry");
        this.localeManager.loadLocatedCountry(nPListener, this.sessionManager);
    }

    public void login(final Activity activity, final int i, final NPListener nPListener) {
        ToyLog.d("login  loginType:" + i);
        NXAdsUtil.getAdvertisingIdAfterCaching(this.applicationContext, new NPListener() { // from class: kr.co.nexon.npaccount.NPAccount.5
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NPAccount.this.authManager.loginWithType(activity, i, nPListener);
            }
        });
    }

    public void login(final Activity activity, final NPListener nPListener) {
        ToyLog.d("login");
        NXAdsUtil.getAdvertisingIdAfterCaching(this.applicationContext, new NPListener() { // from class: kr.co.nexon.npaccount.NPAccount.4
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                NPAccount.this.authManager.showLoginSelector(activity, nPListener);
            }
        });
    }

    public void loginByLastNexonSN(Activity activity, NPListener nPListener) {
        this.authManager.loginByLastNexonSN(activity, nPListener);
    }

    public void loginByQueriedNpsn(Activity activity, Long l, NPListener nPListener) {
        this.authManager.loginByQueriedNpsn(activity, l, nPListener);
    }

    public void loginForKakao(Activity activity, String str, String str2, NPListener nPListener) {
        this.authManager.loginWithExternalAuthType(activity, LoginTypeKakao, str, str2, nPListener);
    }

    public void loginWithExternalAuthType(Activity activity, int i, String str, String str2, NPListener nPListener) {
        this.authManager.loginWithExternalAuthType(activity, i, str, str2, nPListener);
    }

    public void logout(NPListener nPListener) {
        ToyLog.d("logout");
        this.authManager.logout(nPListener);
    }

    public void logoutGamePlatform(@NonNull Activity activity, NPListener nPListener) {
        ToyLog.d("logoutGamePlatform(Activity, NPListener)");
        NXPGameCenterManager.getInstance().logout(activity, nPListener);
    }

    @Deprecated
    public void logoutGamePlatform(NPListener nPListener) {
        ToyLog.d("logoutGamePlatform(NPListener)");
        logoutGamePlatform(this.activity, nPListener);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (activity != null && getNgsmMetadata()) {
            Ngsm.getInst().ngsmLoad();
        }
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        ToyLog.d("requestCode " + i + " resultCode " + i2);
        NexonStore.handleActivityResult(i, i2, intent);
        NPActivityResultManager.getInstance().onActivityResult(i, i2, intent);
        NXRuntimePermissionManager nXRuntimePermissionManager = NXRuntimePermissionManager.getInstance();
        if (i == nXRuntimePermissionManager.getPermissionRequestCode()) {
            nXRuntimePermissionManager.onActivityResult(activity);
        }
    }

    @Deprecated
    public void onActivityResult(Activity activity, int i, int i2, Intent intent, NPListener nPListener) {
        onActivityResult(activity, i, i2, intent);
    }

    public void onNewIntent(Intent intent) {
        NXPPush.onNewIntent(intent);
    }

    public void onRequestPermissionsResult(Activity activity, int i, String[] strArr, int[] iArr) {
        NXRuntimePermissionManager nXRuntimePermissionManager = NXRuntimePermissionManager.getInstance();
        if (i == nXRuntimePermissionManager.getPermissionRequestCode()) {
            nXRuntimePermissionManager.onRequestPermissionsResult(activity, i, strArr, iArr);
        }
    }

    public void queryNpsnByLoginType(Activity activity, int i, NPListener nPListener) {
        this.authManager.queryNpsnByLoginType(activity, i, nPListener);
    }

    public void recoverUser(Activity activity, NPListener nPListener) {
        ToyLog.d("recoverUser");
        this.authManager.recoverUser(activity, nPListener);
    }

    public void registerPush(final NPListener nPListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyRegisterPushRequest(new NPFcmUtil().getRegistrationToken(this.applicationContext)), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.NPAccount.7
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                nPListener.onResult(nXToyResult);
            }
        });
    }

    public void registerResultListener(NPListener nPListener) {
    }

    public void requestPermissions(@NonNull Activity activity, @NonNull NXPRequestPermissionInfo nXPRequestPermissionInfo, int i, @Nullable final NPRuntimePermissionListener nPRuntimePermissionListener) {
        ToyLog.d("requestPermissions permissionsInfo:" + nXPRequestPermissionInfo.toString());
        NXRuntimePermissionManager.getInstance().requestPermissions(activity, nXPRequestPermissionInfo, i, new NXRuntimePermissionListener() { // from class: kr.co.nexon.npaccount.NPAccount.19
            @Override // kr.co.nexon.mdev.android.permission.NXRuntimePermissionListener
            public void onResult(int i2, String[] strArr, int[] iArr) {
                if (nPRuntimePermissionListener != null) {
                    nPRuntimePermissionListener.onResult(i2, strArr, iArr);
                }
            }
        });
    }

    public void requestPermissions(@NonNull Activity activity, @NonNull String[] strArr, int i, @Nullable String str, @Nullable NPRuntimePermissionListener nPRuntimePermissionListener) {
        NXPRequestPermissionInfo.Builder visiblePrefix = new NXPRequestPermissionInfo.Builder().setVisiblePrefix(true);
        for (String str2 : strArr) {
            visiblePrefix.addPermission(str2);
        }
        requestPermissions(activity, visiblePrefix.build(), i, nPRuntimePermissionListener);
    }

    public void resolveAlreadyLoginedUser(Activity activity, NPListener nPListener) {
        ToyLog.d("resolveAlreadyLoginedUser activity, listener");
        this.authManager.resolveAlreadyLoginedUser(activity, false, nPListener);
    }

    public void resolveAlreadyLoginedUser(Activity activity, boolean z, NPListener nPListener) {
        ToyLog.d("resolveAlreadyLoginedUser activity isAlertSkip:" + z);
        this.authManager.resolveAlreadyLoginedUser(activity, z, nPListener);
    }

    public void screenCapture(@NonNull Activity activity, NPListener nPListener) {
        ToyLog.d("screenCapture, listener:" + nPListener);
        NXPGameCenterManager.getInstance().screenCapture(activity, nPListener);
    }

    public boolean sendErrorLog(int i, String str) {
        return NXLog.getInstance().enqueueErrorLog(i, str);
    }

    public boolean sendFunnel(String str) {
        return sendFunnel(str, null);
    }

    public boolean sendFunnel(String str, String str2) {
        return NXLog.getInstance().sendFunnel(str, str2);
    }

    public boolean sendLog(String str, String str2) {
        return NXLog.getInstance().enqueueLog(str, str2);
    }

    public boolean sendNXLog(String str, String str2) {
        return NXLog.getInstance().sendNXLog(str, str2);
    }

    public boolean sendOnlyOnceLog(String str, String str2) {
        return NXLog.getInstance().enqueueLogOnlyOnce(str, str2);
    }

    public boolean sendStageLog(int i, String str) {
        return NXLog.getInstance().enqueueStageLog(i, str);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setCountry(NXLocale.COUNTRY country) {
        ToyLog.d("setCountry:" + country);
        this.localeManager.setCountry(country);
    }

    public void setLocale(NXLocale.LOCALE locale) {
        ToyLog.d("setLocale :" + locale);
        this.localeManager.setLocale(locale);
        NXToyCommonPreferenceController.getInstance().setBasePlate(locale.getLocaleCode(), "");
    }

    public void setNexonAnalyticsUserInfo(String str, String str2) {
        NXLog.getInstance().setUserInfo(str, str2);
    }

    public void setOptions(NPOptions nPOptions) {
        NPOptionManager.getInstance().setOptions(nPOptions);
    }

    public void setPushAgreement(int i, NPListener nPListener) {
        this.policyManager.setPushPolicy(this.applicationContext, i, nPListener);
    }

    public void setPushPolicy(NXPPushPolicies nXPPushPolicies, NPListener nPListener) {
        this.policyManager.setPushPolicy(this.applicationContext, nXPPushPolicies, nPListener);
    }

    public void setStepsAchievement(Activity activity, String str, int i) {
        ToyLog.d("setStepsAchievement achievementID:" + str + ", steps:" + i);
        NXPGameCenterManager.getInstance().setStepsAchievement(activity, str, i);
    }

    public void setStepsAchievementImmediate(Activity activity, String str, int i, NPListener nPListener) {
        ToyLog.d("setStepsAchievementImmediate achievementID:" + str + ", steps:" + i + ", listener:" + nPListener);
        NXPGameCenterManager.getInstance().setStepsAchievementImmediate(activity, str, i, nPListener);
    }

    public void setupSecondPassword(Activity activity, NPListener nPListener) {
        NXPSecondPasswordManager.getInstance().setupSecondPassword(activity, nPListener);
    }

    public void showAccountMenu(Activity activity, NPListener nPListener) {
        ToyLog.d("showAccountMenu");
        this.authManager.showAccountMenu(activity, nPListener);
    }

    public void showAchievement(@NonNull Activity activity, NPListener nPListener) {
        ToyLog.d("showAchievement, activity:" + activity + ", listener:" + nPListener);
        NXPGameCenterManager.getInstance().showAchievement(activity, nPListener);
    }

    public void showAllLeaderBoard(@NonNull Activity activity, NPListener nPListener) {
        ToyLog.d("showAllLeaderBoard, listener:" + nPListener);
        NXPGameCenterManager.getInstance().showAllLeaderBoard(activity, nPListener);
    }

    public void showBanner(Activity activity, int i, NPBannerListener nPBannerListener) {
        ToyLog.d("showBanner groupCode:" + i);
        this.bannerManager.showBanner(activity, i, nPBannerListener);
    }

    public void showCustomerService(Activity activity) {
        ToyLog.d("showCustomerService");
        showCustomerService(activity, null);
    }

    public void showCustomerService(Activity activity, Map<String, Object> map) {
        ToyLog.d("showCustomerService  param:" + map);
        showCustomerService(activity, map, null);
    }

    public void showCustomerService(Activity activity, Map<String, Object> map, NPPlateListener nPPlateListener) {
        ToyLog.d("showCustomerService  param:" + map + "  listener");
        this.plateManager.showCustomerService(activity, map, nPPlateListener);
    }

    public void showDataBackup(Activity activity, String str, NPListener nPListener) {
        this.authManager.showDataBackup(activity, str, nPListener);
    }

    public void showDataBackup(Activity activity, NPListener nPListener) {
        showDataBackup(activity, "", nPListener);
    }

    public void showDataRestore(Activity activity, String str, NPListener nPListener) {
        this.authManager.showDataRestore(activity, str, nPListener);
    }

    public void showDataRestore(Activity activity, NPListener nPListener) {
        showDataRestore(activity, "", nPListener);
    }

    public void showEndingBanner(Activity activity, NPEndingBannerListener nPEndingBannerListener) {
        ToyLog.d("showEndingBanner");
        this.bannerManager.showEndingBanner(activity, nPEndingBannerListener);
    }

    public void showEventWeb(Activity activity, String str) {
        ToyLog.d("showEventWeb url:" + str);
        this.boardManager.showEventWeb(activity, str);
    }

    public void showFAQ(Activity activity) {
        ToyLog.d("showFAQ");
        this.boardManager.showFAQ(activity);
    }

    public void showForum(Activity activity, int i) {
        ToyLog.d("showForum forumId:" + i);
        this.boardManager.showForum(activity, i);
    }

    public void showGameInfo(Activity activity) {
        this.plateManager.showGameInfo(activity);
    }

    public void showHelpCenter(Activity activity, String str) {
        ToyLog.d("showhelpCneter  url:" + str);
        this.boardManager.showHelpCenter(activity, str);
    }

    public void showHelpCenter(Activity activity, Map<String, Object> map) {
        ToyLog.d("showHelpCenter param:" + map);
        this.boardManager.showHelpCenter(activity, map);
    }

    public void showLeaderBoard(@NonNull Activity activity, String str, NPListener nPListener) {
        ToyLog.d("showLeaderBoard, leaderboardID:" + str + ", listener:" + nPListener);
        NXPGameCenterManager.getInstance().showLeaderBoard(activity, str, nPListener);
    }

    public void showMyAccount(Activity activity, String str) {
        ToyLog.d("showMyAccount title:" + str);
        this.plateManager.showMyAccount(activity, str);
    }

    public void showNotice(Activity activity) {
        ToyLog.d("showNotice");
        this.boardManager.showNotice(activity);
    }

    public void showNotice(Activity activity, NPCloseListener nPCloseListener) {
        ToyLog.d("showNotice closeListener:" + nPCloseListener);
        this.boardManager.showNotice(activity, nPCloseListener);
    }

    public void showOfferwall(Activity activity, NPCloseListener nPCloseListener) {
        ToyLog.d("showOfferwall");
        this.boardManager.showOfferwall(activity, nPCloseListener);
    }

    public void showPlate(Activity activity, int i, Map<String, Object> map) {
        ToyLog.d("showPlate  group:" + i + "  param:" + map);
        showPlate(activity, i, map, null);
    }

    public void showPlate(Activity activity, int i, Map<String, Object> map, final NPCloseListener nPCloseListener, final NPListener nPListener, final NPListener nPListener2, final NPPlateListener nPPlateListener) {
        ToyLog.d("showPlate each listener version  group:" + i + "  param:" + map + " listener");
        this.plateManager.showPlate(activity, i, map, new NPPlateListener() { // from class: kr.co.nexon.npaccount.NPAccount.3
            @Override // kr.co.nexon.npaccount.listener.NPPlateListener
            public void onActionPerformedResult(NXToyResult nXToyResult) {
                int i2 = nXToyResult.requestTag;
                if (i2 == NXToyRequestTag.PlateClosed.getValue()) {
                    NXToyCloseResult nXToyCloseResult = new NXToyCloseResult();
                    nXToyCloseResult.screenName = "basePlate";
                    nXToyCloseResult.errorCode = nXToyResult.errorCode;
                    nXToyCloseResult.errorDetail = nXToyResult.errorDetail;
                    nXToyCloseResult.errorText = nXToyResult.errorText;
                    nXToyCloseResult.requestTag = nXToyResult.requestTag;
                    if (nPCloseListener != null) {
                        nPCloseListener.onClose(nXToyCloseResult);
                        return;
                    }
                    return;
                }
                if (i2 == NXToyRequestTag.ShowPushMenu.getValue()) {
                    NXToyCloseResult nXToyCloseResult2 = new NXToyCloseResult();
                    nXToyCloseResult2.screenName = "pushMenu";
                    nXToyCloseResult2.errorCode = nXToyResult.errorCode;
                    nXToyCloseResult2.errorDetail = nXToyResult.errorDetail;
                    nXToyCloseResult2.errorText = nXToyResult.errorText;
                    nXToyCloseResult2.requestTag = nXToyResult.requestTag;
                    if (nPCloseListener != null) {
                        nPCloseListener.onClose(nXToyCloseResult2);
                        return;
                    }
                    return;
                }
                if (i2 == NXToyRequestTag.PlateButtonClick.getValue()) {
                    NXToyPlateActionPerformedResult nXToyPlateActionPerformedResult = (NXToyPlateActionPerformedResult) nXToyResult;
                    if (nPPlateListener != null) {
                        nPPlateListener.onActionPerformedResult(nXToyPlateActionPerformedResult);
                        return;
                    }
                    return;
                }
                if (i2 == NXToyRequestTag.DataBackup.getValue()) {
                    if (nPListener != null) {
                        nPListener.onResult(nXToyResult);
                    }
                } else {
                    if (i2 != NXToyRequestTag.DataRestore.getValue() || nPListener2 == null) {
                        return;
                    }
                    nPListener2.onResult(nXToyResult);
                }
            }
        });
    }

    public void showPlate(Activity activity, int i, Map<String, Object> map, NPPlateListener nPPlateListener) {
        ToyLog.d("showPlate  group:" + i + "  param:" + map + " listener");
        this.plateManager.showPlate(activity, i, map, nPPlateListener);
    }

    public void showPlate(Activity activity, Map<String, Object> map) {
        ToyLog.d("showPlate +param:" + map);
        showPlate(activity, 0, map, null);
    }

    public void showPromotion(Activity activity, String str, NPListener nPListener) {
        ToyLog.d("showPromotion", "placementId", str);
        NPPromotionManager.getInstance(activity).showPromotionContent(activity, str, nPListener);
    }

    public void showPushMenu(Activity activity, NPListener nPListener) {
        this.policyManager.showPolicySettings(activity, nPListener);
    }

    public void showPushNSmsSetting(Activity activity, String str, String str2, String str3, NPListener nPListener) {
        this.policyManager.showPushNSmsSetting(activity, str, str2, str3, nPListener);
    }

    public void showPushNSmsSetting(Activity activity, NPListener nPListener) {
        this.policyManager.showPushNSmsSetting(activity, nPListener);
    }

    public void showSettlementFund(Activity activity, String str, String str2, String str3, NPListener nPListener) {
        this.policyManager.showFundsSettlement(activity, str, str2, str3, nPListener);
    }

    public void showSettlementFund(Activity activity, String str, String str2, NPListener nPListener) {
        showSettlementFund(activity, str, str2, "", nPListener);
    }

    public void showTermsList(Activity activity, String str) {
        this.plateManager.showTermsList(activity, str);
    }

    public void showTermsOfAgree(Activity activity, NPListener nPListener) {
        this.termsManager.showTermsOfAgree(activity, nPListener);
    }

    public void showToday(Activity activity, int i) {
        showToday(activity, i, false, null);
    }

    public void showToday(Activity activity, int i, boolean z, NPListener nPListener) {
        ToyLog.d("showToday groupCode:" + i + " useDontShowToday:" + z + " resultListener:" + nPListener);
        this.boardManager.showToday(activity, i, z, nPListener);
    }

    public void showUserInfoToast(int i, String str) {
        this.authManager.showUserInfoToast(this.activity, i, str);
    }

    public void showWeb(Activity activity, String str, String str2) {
        ToyLog.d("showWeb title:" + str + " url:" + str2);
        showWeb(activity, str, str2, null);
    }

    public void showWeb(Activity activity, String str, String str2, String str3) {
        ToyLog.d("showWeb title:" + str + " url:" + str2 + " postData:" + str3);
        this.boardManager.showWeb(activity, str, str2, str3);
    }

    @Deprecated
    public void showWebNotitle(Activity activity, String str) {
        this.boardManager.showWebNoTitle(activity, str);
    }

    public void snsConnect(Activity activity, int i, NPListener nPListener) {
        ToyLog.d("snsConnect, snsType:" + i + ", listener:" + nPListener);
        NXPSNSManager.getInstance().connectToNpsn(activity, i, nPListener);
    }

    public void snsDisconnect(Activity activity, int i, NPListener nPListener) {
        ToyLog.d("snsDisconnect, snsType:" + i + ", listener:" + nPListener);
        NXPSNSManager.getInstance().disconnectFromNpsn(activity, i, nPListener);
    }

    public void submitScore(Activity activity, String str, long j) {
        ToyLog.d("submitScore, leaderboardID:" + str + ", score:" + j);
        NXPGameCenterManager.getInstance().submitScore(activity, str, j);
    }

    public void submitScoreImmediate(Activity activity, String str, long j, NPListener nPListener) {
        ToyLog.d("submitScoreImmediate, leaderboardID:" + str + ", score:" + j + ", listener:" + nPListener);
        NXPGameCenterManager.getInstance().submitScoreImmediate(activity, str, j, nPListener);
    }

    public void unlockAchievement(@NonNull Activity activity, String str) {
        ToyLog.d("unlockAchievement, achievementID:" + str);
        NXPGameCenterManager.getInstance().unlockAchievement(activity, str);
    }

    public void unlockAchievementImmediate(@NonNull Activity activity, String str, NPListener nPListener) {
        ToyLog.d("unlockAchievementImmediate, achievementID:" + str + ", listener:" + nPListener);
        NXPGameCenterManager.getInstance().unlockAchievementImmediate(activity, str, nPListener);
    }

    public void unregisterPush(final NPListener nPListener) {
        NXToyRequestPostman.getInstance().postRequest(new NXToyUnregisterPushRequest(new NPFcmUtil().getRegistrationToken(this.applicationContext)), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.NPAccount.8
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                nPListener.onResult(nXToyResult);
            }
        });
    }

    public void unregisterResultListener(NPListener nPListener) {
    }

    public void unregisterService(NPListener nPListener) {
        ToyLog.d("unregisterService");
        this.authManager.unregisterService(nPListener);
    }

    public void validatePolicy(int i, Map<String, Object> map, NPListener nPListener) {
        this.policyManager.validatePolicy(i, map, nPListener);
    }

    public void verifySecondPassword(Activity activity, NPListener nPListener) {
        NXPSecondPasswordManager.getInstance().verifySecondPassword(activity, nPListener);
    }

    public void webShop(Activity activity, int i, NPListener nPListener) {
        this.boardManager.showWebShop(activity, i, nPListener);
    }
}
