package com.naver.glink.android.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.naver.glink.android.sdk.ChannelCodes;
import com.naver.glink.android.sdk.model.Article;
import com.naver.glink.android.sdk.model.Menu;
import com.naver.glink.android.sdk.ui.write.model.Content;
import com.naver.glink.android.sdk.ui.write.model.Text;
import com.naver.plug.android.core.api.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Responses {

    public static class BannersResponse extends Response {
        public static final int BANNERS_LAYOUT_FOR_PORTRAIT = 103;
        public static final int FULL_BANNERS_LAYOUT = 1;
        public static final int FULL_BANNERS_LAYOUT_FOR_PORTRAIT = 101;
        public static final int MAIN_AND_BANNERS_LAYOUT = 2;
        public static final int MAIN_AND_BANNERS_LAYOUT_FOR_PORTRAIT = 102;
        public int layout;
        public List<HomeBanner> mains = new ArrayList();
        public List<HomeBanner> banners = new ArrayList();

        public static class HomeBanner extends com.naver.plug.android.core.a.a implements Parcelable {
            public static final Parcelable.Creator<HomeBanner> CREATOR = new Parcelable.Creator<HomeBanner>() { // from class: com.naver.glink.android.sdk.api.Responses.BannersResponse.HomeBanner.1
                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public HomeBanner createFromParcel(Parcel parcel) {
                    HomeBanner homeBanner = new HomeBanner();
                    homeBanner.imageUrl = parcel.readString();
                    homeBanner.mediaType = parcel.readString();
                    homeBanner.videoId = parcel.readString();
                    homeBanner.articleId = parcel.readInt();
                    homeBanner.appScheme = parcel.readString();
                    homeBanner.androidLink = parcel.readString();
                    homeBanner.aC = parcel.readString();
                    homeBanner.aP = parcel.readString();
                    return homeBanner;
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public HomeBanner[] newArray(int i) {
                    return new HomeBanner[i];
                }
            };
            public String aC;
            public String aP;
            public String androidLink;
            public String appScheme;
            public int articleId = -1;
            public String imageUrl;
            public String mediaType;
            public String videoId;

            public boolean a() {
                return TextUtils.equals(this.mediaType, "M");
            }

            public boolean b() {
                return TextUtils.equals(this.mediaType, "I");
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(this.imageUrl);
                parcel.writeString(this.mediaType);
                parcel.writeString(this.videoId);
                parcel.writeInt(this.articleId);
                parcel.writeString(this.appScheme);
                parcel.writeString(this.androidLink);
                parcel.writeString(this.aC);
                parcel.writeString(this.aP);
            }
        }
    }

    public static class a extends Response {
        public String returnValue;
        public boolean success;
    }

    public static class b extends Response {
        public boolean commentWritable;
        public boolean isCafeMember = true;
    }

    public static class c extends Response {
        public Menu menu;
        public List<Article> articles = new ArrayList();
        public a metadata = new a();
        public List<Article> notices = new ArrayList();

        public static class a extends Responses {
            public boolean isLast;
            public boolean isLastPage;
            public int lastArticleId;
            public int totalCount;
        }
    }

    public static class d extends Response {
        public int index = 0;
        public String key;
    }

    public static class e extends Response {
        public String attachfiles;
        public String imageHtmlTag;
        public String imagePath;
    }

    public static class f extends Response {
        public String attachmovielist;
        public String movieHtmlTag;
    }

    public static class g extends Response {
        public boolean available;
        public int messageCode;
        public String nickname;
    }

    public static class h extends Response {
        public int articleCount;
        public int cafeId;
        public String cafeName;
        public String cafeUrl;
        public String desc;
        public String iconImageUrl;
        public List<o> managers;
        public int memberCount;
        public int todayVisitCount;
        public String topCoverImageUrl;
    }

    public static class i extends Response {
        public static final String CAFE_TYPE_R = "R";
        public int cafeId;
        public String cafeName;
        public String cafeType;
        public String cafeUrl;
        public String desc;
        public long eventUpdateTimestamp;
        public String fullCoverImageUrl;
        public boolean hasNewEventArticle;
        public boolean hasNewNoticeArticle;
        public String iconImageUrl;
        public String langCode;
        public boolean live;
        public boolean liveAuthority;
        public int memberCount;
        public long noticeUpdateTimestamp;
        public boolean popularArticle;
        public String topCoverImageUrl;
        public int noticeMenuId = -1;
        public int eventMenuId = -1;
        public int tipMenuId = -1;
        public int channelId = -1;
        private List<j> channels = Collections.emptyList();

        public j a(int i) {
            if (i == -1) {
                return null;
            }
            for (j jVar : a()) {
                if (jVar.channelId == i) {
                    return jVar;
                }
            }
            return null;
        }

        public j a(String str) {
            if (str == null) {
                return a(this.channelId);
            }
            for (j jVar : a()) {
                if (TextUtils.equals(jVar.langCode, str)) {
                    return jVar;
                }
            }
            return null;
        }

        public List<j> a() {
            if (com.naver.glink.android.sdk.d.b().e()) {
                return this.channels;
            }
            ArrayList arrayList = new ArrayList();
            for (j jVar : this.channels) {
                if (!ChannelCodes.KOREAN.equals(jVar.langCode)) {
                    arrayList.add(jVar);
                }
            }
            return arrayList;
        }

        public j b() {
            return a(this.channelId);
        }

        public boolean c() {
            return CAFE_TYPE_R.equals(this.cafeType);
        }
    }

    public static class j extends com.naver.plug.android.core.a.a {
        public static final int INVALID_CHANNEL_ID = -1;
        public int channelId = -1;
        public String langCode;
        public String language;

        public String a() {
            return this.langCode;
        }

        public int b() {
            return this.channelId;
        }
    }

    public static class k extends Response {
        public String returnValue;
        public boolean success;
    }

    public static class l extends Response {
        public String returnValue;
        public boolean success;
    }

    public static class m extends Response {
        public String returnValue;
        public boolean success;
    }

    public static class n extends Response {
        public boolean isApply;
        public boolean success;
    }

    public static class o extends com.naver.plug.android.core.a.a {
        public String memberId;
        public String nickname;
    }

    public static class p extends Response {
        public List<Article> articles = new ArrayList();
        public a metadata = new a();

        public static class a extends Responses {
            public boolean isLastPage;
            public boolean lastPage;
            public int nextOffset;
            public int totalCount;
        }
    }

    public static class q extends Response {
        public String backgroundImage;
        public int cafeId;
        public String gameUserId;
        public String gender;
        public String joinDate;
        public String lastVisitDate;
        public boolean live;
        public String memberId;
        public int memberLevel;
        public String memberLevelName;
        public String nickname;
        public String profileImage;
        public int visitCount;
        public List<Article> articles = new ArrayList();
        public a count = new a();

        public static class a extends Responses {
            public int comment;
            public int like;
            public int live;
            public int write;
        }
    }

    public static class r extends Response {
        public List<Menu> menus = new ArrayList();
        public a metadata = new a();

        public static class a extends com.naver.plug.android.core.a.a {
            public String allPhotos;
            public String allPosts;
            public String allVideos;
        }
    }

    public static class s extends Response {
    }

    public static class t extends Response {
        public boolean isEncodingComplete = true;
        public String playUrl;
        public boolean success;
    }

    public static class u extends com.naver.plug.android.core.a.a {
        public String contents;
        public List<a> resolvedArticleList = Collections.emptyList();
        public String subject;

        public static class a extends com.naver.plug.android.core.a.a {
            public String htmlTag;
            public String mediaType;
            public String param;
            public String thumbnail;
        }
    }

    public static class v extends Response {
        public String result;
    }

    public static class w extends Response {
        public int articleId;
        public int menuId;
        public boolean success;
    }

    public static class x extends Response {
        public boolean success;
    }

    public static class y extends Response {
        public int articleId;
        private List<Content> contents;
        public int menuId;
        public List<Menu> menus = Collections.emptyList();
        public u property;

        public Menu a() {
            return a(this.menuId);
        }

        public Menu a(int i) {
            if (i == -1) {
                return null;
            }
            for (Menu menu : this.menus) {
                if (menu.getMenuId() == i) {
                    return menu;
                }
            }
            return null;
        }

        public List<Content> b() {
            if (this.contents == null) {
                if (this.property.contents != null) {
                    this.contents = com.naver.glink.android.sdk.ui.write.model.a.a(this.property.contents);
                } else {
                    this.contents = com.naver.glink.android.sdk.ui.write.model.a.a(this.property.resolvedArticleList);
                }
                if (this.contents.isEmpty() || !(this.contents.get(0) instanceof Text)) {
                    this.contents.add(new Text(""));
                }
            }
            return this.contents;
        }
    }
}
