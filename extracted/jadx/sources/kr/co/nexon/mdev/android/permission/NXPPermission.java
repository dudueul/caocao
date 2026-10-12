package kr.co.nexon.mdev.android.permission;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPPermission {
    public static final int PERMISSION_OPTIONAL = 0;
    public static final int PERMISSION_REQUIRED = 1;
    private String description;
    private int grantResult;
    private String groupName;
    private final String name;
    private final int option;

    public NXPPermission(String str) {
        this(str, 0, null);
    }

    public NXPPermission(String str, int i) {
        this(str, i, null);
    }

    public NXPPermission(String str, int i, String str2) {
        this.grantResult = -1;
        this.name = str;
        this.description = str2;
        this.option = i;
    }

    public String getDescription() {
        return this.description;
    }

    public int getGrantResult() {
        return this.grantResult;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getName() {
        return this.name;
    }

    public int getOption() {
        return this.option;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setGrantResult(int i) {
        this.grantResult = i;
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public String toString() {
        return "NXPPermission[name: " + this.name + ", groupName: " + this.groupName + ", description: " + this.description + ", option: " + this.option + ", grantResult: " + this.grantResult + "]";
    }
}
