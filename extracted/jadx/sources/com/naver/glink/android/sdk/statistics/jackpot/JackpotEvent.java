package com.naver.glink.android.sdk.statistics.jackpot;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class JackpotEvent {

    public enum CLICK implements a.InterfaceC0036a {
        MEDIA_VIDEO_LIST_POST(SCENE.MEDIA_VIDEO_LIST, "article_post_button"),
        MEDIA_IMAGE_LIST_POST(SCENE.MEDIA_IMAGE_LIST, "article_post_button"),
        STREAMING_LIST_LIVE_START(SCENE.STREAMING_LIST, "live_start_button"),
        ARTICLE_LIST_TIP_POST(SCENE.ARTICLE_LIST_TIP, "article_post_button"),
        ARTICLE_LIST_EVENT_POST(SCENE.ARTICLE_LIST_EVENT, "article_post_button"),
        ARTICLE_LIST_NOTICE_POST(SCENE.ARTICLE_LIST_NOTICE, "article_post_button"),
        ARTICLE_LIST_ALL_POST(SCENE.ARTICLE_LIST_ALL, "article_post_button");

        final JackpotAction action = JackpotAction.CLICK;
        final String classifier;
        final String scene_id;

        CLICK(SCENE scene) {
            this.scene_id = scene.sceneId;
            this.classifier = scene.sceneId;
        }

        CLICK(SCENE scene, String str) {
            this.scene_id = scene.sceneId;
            this.classifier = str;
        }

        @Override // com.naver.glink.android.sdk.statistics.jackpot.a.InterfaceC0036a
        public JackpotAction getAction() {
            return this.action;
        }

        @Override // com.naver.glink.android.sdk.statistics.jackpot.a.InterfaceC0036a
        public String getClassifier() {
            return this.classifier;
        }

        @Override // com.naver.glink.android.sdk.statistics.jackpot.a.InterfaceC0036a
        public String getSceneId() {
            return this.scene_id;
        }
    }

    private enum SCENE {
        PROFILE_USER("profile_user"),
        PROFILE_JOIN("profile_join"),
        PROFILE_LOGIN("profile_login"),
        SEARCH("search"),
        MEDIA_VIDEO_LIST("media_video_list"),
        MEDIA_IMAGE_LIST("media_image_list"),
        STREAMING_DETAIL_VOD("streaming_detail_vod"),
        STREAMING_DETAIL_ONAIR("streaming_detail_onair"),
        STREAMING_LIST("streaming_list"),
        ARTICLE_DETAIL("article_detail"),
        ARTICLE_LIST_ALL("article_list_all"),
        ARTICLE_LIST_TIP("article_list_tip"),
        ARTICLE_LIST_EVENT("article_list_event"),
        ARTICLE_LIST_NOTICE("article_list_notice"),
        HOME_PORTRAIT_TYPE3("home_portrait_type3"),
        HOME_PORTRAIT_TYPE2("home_portrait_type2"),
        HOME_PORTRAIT_TYPE1("home_portrait_type1"),
        HOME_LAND_TYPE2("home_land_type2"),
        HOME_LAND_TYPE1("home_land_type1");

        String sceneId;

        SCENE(String str) {
            this.sceneId = str;
        }
    }

    public enum SCENE_ENTER implements a.InterfaceC0036a {
        PROFILE_USER(SCENE.PROFILE_USER),
        PROFILE_JOIN(SCENE.PROFILE_JOIN),
        PROFILE_LOGIN(SCENE.PROFILE_LOGIN),
        SEARCH(SCENE.SEARCH),
        MEDIA_VIDEO_LIST(SCENE.MEDIA_VIDEO_LIST),
        MEDIA_IMAGE_LIST(SCENE.MEDIA_IMAGE_LIST),
        STREAMING_DETAIL_VOD(SCENE.STREAMING_DETAIL_VOD),
        STREAMING_DETAIL_ONAIR(SCENE.STREAMING_DETAIL_ONAIR),
        STREAMING_LIST(SCENE.STREAMING_LIST),
        ARTICLE_DETAIL(SCENE.ARTICLE_DETAIL),
        ARTICLE_LIST_ALL(SCENE.ARTICLE_LIST_ALL),
        ARTICLE_LIST_TIP(SCENE.ARTICLE_LIST_TIP),
        ARTICLE_LIST_EVENT(SCENE.ARTICLE_LIST_EVENT),
        ARTICLE_LIST_NOTICE(SCENE.ARTICLE_LIST_NOTICE),
        HOME_PORTRAIT_TYPE3(SCENE.HOME_PORTRAIT_TYPE3),
        HOME_PORTRAIT_TYPE2(SCENE.HOME_PORTRAIT_TYPE2),
        HOME_PORTRAIT_TYPE1(SCENE.HOME_PORTRAIT_TYPE1),
        HOME_LAND_TYPE2(SCENE.HOME_LAND_TYPE2),
        HOME_LAND_TYPE1(SCENE.HOME_LAND_TYPE1);

        final JackpotAction action = JackpotAction.SCENE_ENTER;
        final String classifier;
        final String scene_id;

        SCENE_ENTER(SCENE scene) {
            this.scene_id = scene.sceneId;
            this.classifier = scene.sceneId;
        }

        SCENE_ENTER(SCENE scene, String str) {
            this.scene_id = scene.sceneId;
            this.classifier = str;
        }

        @Override // com.naver.glink.android.sdk.statistics.jackpot.a.InterfaceC0036a
        public JackpotAction getAction() {
            return this.action;
        }

        @Override // com.naver.glink.android.sdk.statistics.jackpot.a.InterfaceC0036a
        public String getClassifier() {
            return this.classifier;
        }

        @Override // com.naver.glink.android.sdk.statistics.jackpot.a.InterfaceC0036a
        public String getSceneId() {
            return this.scene_id;
        }
    }
}
