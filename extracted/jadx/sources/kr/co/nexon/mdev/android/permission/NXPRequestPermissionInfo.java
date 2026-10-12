package kr.co.nexon.mdev.android.permission;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPRequestPermissionInfo {
    private String cautionText;
    private LinkedHashMap<String, NXPPermission> requestPermissionsHashMap;
    private String title;
    private boolean visibleImage;
    private boolean visiblePrefix;

    public static class Builder {
        private String cautionText;
        private String title;
        private boolean visiblePrefix = true;
        private boolean visibleImage = true;
        private LinkedHashMap<String, NXPPermission> requestPermissionsHashMap = new LinkedHashMap<>();

        public Builder addPermission(@NonNull String str) {
            if (this.requestPermissionsHashMap != null) {
                this.requestPermissionsHashMap.put(str, new NXPPermission(str));
            }
            return this;
        }

        public Builder addPermission(@NonNull String str, int i) {
            if (this.requestPermissionsHashMap != null) {
                this.requestPermissionsHashMap.put(str, new NXPPermission(str, i));
            }
            return this;
        }

        public Builder addPermission(@NonNull String str, int i, String str2) {
            if (this.requestPermissionsHashMap != null) {
                this.requestPermissionsHashMap.put(str, new NXPPermission(str, i, str2));
            }
            return this;
        }

        public NXPRequestPermissionInfo build() {
            return new NXPRequestPermissionInfo(this);
        }

        public Builder setCautionText(@Nullable String str) {
            this.cautionText = str;
            return this;
        }

        public Builder setTitle(@Nullable String str) {
            this.title = str;
            return this;
        }

        public Builder setVisibleImage(boolean z) {
            this.visibleImage = z;
            return this;
        }

        public Builder setVisiblePrefix(boolean z) {
            this.visiblePrefix = z;
            return this;
        }
    }

    public NXPRequestPermissionInfo(Builder builder) {
        this.title = builder.title;
        this.cautionText = builder.cautionText;
        this.visiblePrefix = builder.visiblePrefix;
        this.visibleImage = builder.visibleImage;
        this.requestPermissionsHashMap = builder.requestPermissionsHashMap;
    }

    public String getCautionText() {
        return this.cautionText;
    }

    public LinkedHashMap<String, NXPPermission> getRequestPermissionsHashMap() {
        return this.requestPermissionsHashMap;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean getVisibleImage() {
        return this.visibleImage;
    }

    public boolean getVisiblePrefix() {
        return this.visiblePrefix;
    }

    public int[] toArrayRequestPermissionResults() {
        if (this.requestPermissionsHashMap == null || this.requestPermissionsHashMap.isEmpty()) {
            return new int[0];
        }
        int i = 0;
        int[] iArr = new int[this.requestPermissionsHashMap.size()];
        for (NXPPermission nXPPermission : this.requestPermissionsHashMap.values()) {
            if (nXPPermission != null) {
                iArr[i] = nXPPermission.getGrantResult();
                i++;
            }
        }
        return iArr;
    }

    public String[] toArrayRequestPermissions() {
        if (this.requestPermissionsHashMap == null || this.requestPermissionsHashMap.isEmpty()) {
            return new String[0];
        }
        Set<String> setKeySet = this.requestPermissionsHashMap.keySet();
        return (String[]) setKeySet.toArray(new String[setKeySet.size()]);
    }

    public String toString() {
        return "title: " + this.title + ", cautionText: " + this.cautionText + ", visibleImage: " + this.visibleImage + ", visiblePrefix: " + this.visiblePrefix + ", permissions: " + this.requestPermissionsHashMap.toString();
    }
}
