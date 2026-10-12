package com.naver.glink.android.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.naver.plug.android.core.a.a;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Menu extends a implements Parcelable {
    public static final int ALL_ARTICLES_MENU_ID = 0;
    public static final int ALL_IMAGES_MENU_ID = -3;
    public static final int ALL_VIDEOS_MENU_ID = -2;
    public static final Parcelable.Creator<Menu> CREATOR = new Parcelable.Creator<Menu>() { // from class: com.naver.glink.android.sdk.model.Menu.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Menu createFromParcel(Parcel parcel) {
            Menu menu = new Menu();
            menu.type = (Type) parcel.readSerializable();
            menu.menuId = parcel.readInt();
            menu.menuName = parcel.readString();
            menu.hasNewArticle = parcel.readByte() != 0;
            return menu;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Menu[] newArray(int i) {
            return new Menu[i];
        }
    };
    public static final int INVALID_MENU_ID = -1;
    public static final int POPULAR_MENU_ID = -4;
    private boolean hasNewArticle;
    private int menuId;
    private String menuName;
    private Type type = Type.ARTICLES;

    public enum Type {
        ARTICLES,
        VIDEOS,
        IMAGES
    }

    public static Menu allArticles(String str) {
        return allMenu(Type.ARTICLES, 0, str);
    }

    public static Menu allImages(String str) {
        return allMenu(Type.IMAGES, -3, str);
    }

    private static Menu allMenu(Type type, int i, String str) {
        Menu menu = new Menu();
        menu.type = type;
        menu.menuId = i;
        menu.menuName = str;
        return menu;
    }

    public static Menu allVideos(String str) {
        return allMenu(Type.VIDEOS, -2, str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getMenuId() {
        return this.menuId;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public Type getType() {
        return this.type;
    }

    public boolean isHasNewArticle() {
        return this.hasNewArticle;
    }

    public void setMenuName(String str) {
        this.menuName = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.type);
        parcel.writeInt(this.menuId);
        parcel.writeString(this.menuName);
        parcel.writeByte((byte) (this.hasNewArticle ? 1 : 0));
    }
}
