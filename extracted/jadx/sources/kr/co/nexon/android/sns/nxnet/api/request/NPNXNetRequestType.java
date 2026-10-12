package kr.co.nexon.android.sns.nxnet.api.request;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum NPNXNetRequestType {
    getToken(0, "/auth/token"),
    refreshToken(1, "/auth/token"),
    getUserId(2, "/users/me/userid"),
    validateToken(3, "/auth/token"),
    getProfilename(4, "/users/me/profilename"),
    logout(5, "/auth/logout");

    private static boolean isDevEnv = false;
    private String command;
    private int tag;
    private String PROTOCOL = "https://";
    private String DEV_PROTOCOL = "https://";
    private String SERVER = "api.nexon.net";
    private String DEV_SERVER = "api-stage.nexon.net";

    NPNXNetRequestType(int i, String str) {
        this.tag = i;
        this.command = str;
    }

    public int getCode() {
        return this.tag;
    }

    public String getURL() {
        return isDevEnv ? this.DEV_PROTOCOL + this.DEV_SERVER + this.command : this.PROTOCOL + this.SERVER + this.command;
    }
}
