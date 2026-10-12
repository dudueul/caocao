package com.nexon.platform.store.billing.vendor;

import android.content.Context;
import android.support.annotation.Nullable;
import com.nexon.core.log.ToyLog;
import com.nexon.platform.NXPFinalizer;
import com.nexon.platform.store.billing.vendor.interfaces.VendorInterface;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class VendorHolder {
    private static final String TAG = VendorHolder.class.getName();
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: com.nexon.platform.store.billing.vendor.VendorHolder.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            if (VendorHolder.instance != null && VendorHolder.instance.vendorInterface != null) {
                VendorHolder.instance.vendorInterface.dispose();
            }
            VendorHolder unused = VendorHolder.instance = null;
        }
    };
    private static VendorHolder instance;
    private VendorInterface vendorInterface;
    private final String ONE_STORE_CLASS_NAME = "com.nexon.platform.store.vendor.BillingOneStoreManager";
    private final String ONE_STORE_LIB_CLASS_NAME = "com.onestore.iap.api.PurchaseClient";
    private final String ONE_STORE_META_ATTRIBUTE = "iap:api_version";
    private final String GOOGLE_STORE_CLASS_NAME = "com.nexon.platform.store.vendor.BillingGoogleManager";

    private VendorHolder() {
    }

    public static VendorHolder getInstance() {
        if (instance == null) {
            synchronized (VendorHolder.class) {
                if (instance == null) {
                    instance = new VendorHolder();
                }
            }
        }
        return instance;
    }

    @Nullable
    public VendorInterface getVendorInterface() {
        return this.vendorInterface;
    }

    public void initialize(Context context) {
        try {
            String str = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getInt("iap:api_version") == 0 ? "com.nexon.platform.store.vendor.BillingGoogleManager" : "com.nexon.platform.store.vendor.BillingOneStoreManager";
            try {
                if (str.equals("com.nexon.platform.store.vendor.BillingOneStoreManager")) {
                    Class.forName("com.onestore.iap.api.PurchaseClient");
                }
                try {
                    try {
                        try {
                            this.vendorInterface = (VendorInterface) Class.forName(str).newInstance();
                        } catch (IllegalAccessException e) {
                            ToyLog.e(TAG, e.getMessage());
                        }
                    } catch (SecurityException e2) {
                        ToyLog.e(TAG, e2.getMessage());
                    }
                } catch (InstantiationException e3) {
                    ToyLog.e(TAG, e3.getMessage());
                }
            } catch (ClassNotFoundException e4) {
                ToyLog.d(TAG, "Cannot find Store class. Please add Store library you wish to integrate.");
            }
        } catch (Exception e5) {
            ToyLog.e(TAG, e5.getMessage());
        }
    }
}
