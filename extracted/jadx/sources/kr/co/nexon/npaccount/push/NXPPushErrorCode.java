package kr.co.nexon.npaccount.push;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public enum NXPPushErrorCode {
    UnKnownError(120000, "Unknown error,"),
    UnSupportedVersionError(120001, "Invalid API for this version. check your version."),
    NetworkError(120002, "Network error occurred."),
    InternalParseError(120003, "Internal parsing error."),
    DeviceTokenNotExist(120004, "Passed device token is empty. Check device token."),
    PushSettingNightPushViolation(120005, "Cannot turn on the night push setting when the ad push setting is disabled."),
    ParameterInvalid(120010, "parameter is invalid"),
    NotRegisteredNpsn(30000, "Not registered Npsn");

    private final String message;
    private final int value;

    NXPPushErrorCode(int i, String str) {
        this.value = i;
        this.message = str;
    }

    public String getMessage() {
        return this.message;
    }

    public int getValue() {
        return this.value;
    }
}
