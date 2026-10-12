package com.nexon.platform.store.billing;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Constants {

    public enum ErrorCode {
        UnknownError(100010, "unknown error occurred"),
        NetworkError(100011, "network error occurred"),
        InternalParseError(100012, "internal parsing error"),
        ParameterInvalidError(100013, "parameter is invalid"),
        ServerResponseInvalidError(100014, "server response data is invalid"),
        StoreInitializeFailError(100050, "store can't initialize"),
        StoreUserSettingError(100051, "user key is not set in nexon store"),
        StoreLibraryNotFound(100052, "cannot find store library."),
        StoreNeedToUpdateError(100053, "please update OneStore service app"),
        StoreNeedToLogin(100054, "please login to OneStore to make purchase"),
        BillingInvalidTransactionStateError(100100, "the billing transaction state is not valid"),
        BillingParameterInvalidError(100101, "the billing parameter is invalid"),
        BillingVendorPurchaseUserCancel(100200, "user canceled in store"),
        BillingItemNotVendorConsumedError(100201, "item not consumed at vendor yet"),
        BillingVendorError(100300, "error in store"),
        BillingVendorServiceUnavailableError(100301, "service unavailable in store"),
        BillingVendorUnavailableError(100302, "unavailable in store"),
        BillingVendorItemUnavailableError(100303, "item unavailable in store"),
        BillingVendorDeveloperError(100304, "developer error in store"),
        BillingVendorItemNotOwned(100305, "item not owned in store account"),
        BillingVendorAccountInvalidError(100306, "google account is invalid. relogin google account"),
        BillingVendorPaymentMethodError(100307, "Please try again after checking purchasability and payment method. (Contact OneStore)"),
        BillingSecurityError(100308, "Purchase was requested from the abnormal application"),
        BillingVendorUndefinedError(100309, "Undefined error. (Contact OneStore)"),
        BillingIabNotInitializedError(100310, "IAB not initialized"),
        BillingIabInProgressError(100311, "another IAB operation in progress"),
        BillingVendorErrorUnknown(100400, "an unknown or unexpected error occurred"),
        BillingVendorErrorClientInvalid(100401, "the client is not allowed to perform the attempted action"),
        BillingVendorErrorPaymentInvalid(100402, "one of the payment parameters was not recognized by the Apple App Store"),
        BillingVendorErrorPaymentNotAllowed(100403, "the user is not allowed to authorize payments"),
        BillingVendorErrorProductNotAvailable(100404, "the requested product is not available in the store"),
        BillingVendorErrorInvalidTransactionState(100405, "the vendor transaction state is not valid"),
        BillingVendorNotFoundVendorTransaction(100406, "payment at vendor, could not find payment result"),
        BillingVendorTransactionFinishFailed(100407, "failed to finish vendor transaction"),
        BillingVendorFailedRefreshReceipt(100408, "failed to refresh receipt in app"),
        BillingVendorFailedValidateProductError(100409, "failed to validate product"),
        BillingLocalInventorySaveFailed(100420, "failed to save transaction in local inventory"),
        BillingLocalInventoryFetchFailed(100421, "failed to fetch transaction in local inventory"),
        BillingLocalInventoryNotFoundTransaction(100422, "the transaction does not exist in local inventory"),
        BillingLocalInventoryDeleteFailed(100423, "failed to delete transaction in local inventory"),
        NeedToRestoreError(24001, "need to restore. call the restore API"),
        AlreadyConsumedReceipt(24702, "already consumed receipt"),
        PurchaseCanceled(24703, "purchase canceled in receipt"),
        ExceedLimitPurchase(24705, "exceed limit of purchase"),
        NotFoundStampId(24706, "can not find stamp id"),
        NotFoundLimitCondition(24708, "can not find purchase condition"),
        InvalidTransactionFlow(29013, "transaction flow is invalid");

        private final String message;
        private final int value;

        public interface ConvertVendorCodeToErrorCode {
            ErrorCode convertCode(int i);
        }

        ErrorCode(int i, String str) {
            this.value = i;
            this.message = str;
        }

        public static ErrorCode convertIABCodeToErrorCode(ConvertVendorCodeToErrorCode convertVendorCodeToErrorCode, int i) {
            return convertVendorCodeToErrorCode.convertCode(i);
        }

        public String getMessage() {
            return this.message;
        }

        public int getValue() {
            return this.value;
        }
    }
}
