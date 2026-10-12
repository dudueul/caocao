package com.naver.glink.android.sdk.ui.write.model;

import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.naver.glink.android.sdk.api.Responses;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ContentsHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    private static JsonDeserializer<List> a = new JsonDeserializer<List>() { // from class: com.naver.glink.android.sdk.ui.write.model.a.2
        @Override // com.google.gson.JsonDeserializer
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public List deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
            Object objValueOf;
            Object asString;
            ArrayList arrayList = new ArrayList();
            JsonArray asJsonArray = jsonElement.getAsJsonArray();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= asJsonArray.size()) {
                    return arrayList;
                }
                JsonElement jsonElement2 = asJsonArray.get(i2);
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Map.Entry<String, JsonElement> entry : jsonElement2.getAsJsonObject().entrySet()) {
                    JsonElement value = entry.getValue();
                    if (value.isJsonPrimitive() && ((JsonPrimitive) value).isNumber()) {
                        try {
                            objValueOf = value.getAsString().contains(".") ? Double.valueOf(value.getAsDouble()) : Integer.valueOf(value.getAsInt());
                        } catch (Exception e) {
                            objValueOf = null;
                        }
                    } else {
                        objValueOf = null;
                    }
                    if (objValueOf == null) {
                        try {
                            asString = value.getAsString();
                        } catch (Exception e2) {
                            asString = objValueOf;
                        }
                    } else {
                        asString = objValueOf;
                    }
                    linkedHashMap.put(entry.getKey(), asString);
                }
                arrayList.add(linkedHashMap);
                i = i2 + 1;
            }
        }
    };

    public static Gson a() {
        return new GsonBuilder().registerTypeAdapter(new TypeToken<List>() { // from class: com.naver.glink.android.sdk.ui.write.model.a.1
        }.getType(), a).create();
    }

    public static List<Content> a(String str) {
        ArrayList arrayList = new ArrayList();
        for (Map map : (List) a().fromJson(str, List.class)) {
            String str2 = (String) map.get("type");
            if (TextUtils.equals(str2, "text")) {
                arrayList.add(new Text((Map<String, Object>) map));
            } else if (TextUtils.equals(str2, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO)) {
                arrayList.add(new ImageAttachment((Map<String, Object>) map));
            } else if (TextUtils.equals(str2, "video")) {
                arrayList.add(new VideoAttachment((Map<String, Object>) map));
            }
        }
        return arrayList;
    }

    public static List<Content> a(List<Responses.u.a> list) {
        ArrayList arrayList = new ArrayList();
        for (Responses.u.a aVar : list) {
            if (TextUtils.equals(aVar.mediaType, "TEXT")) {
                arrayList.add(new Text(aVar));
            } else if (TextUtils.equals(aVar.mediaType, ShareConstants.IMAGE_URL)) {
                arrayList.add(new ImageAttachment(aVar));
            } else if (TextUtils.equals(aVar.mediaType, "MOVIE")) {
                arrayList.add(new VideoAttachment(aVar));
            }
        }
        return arrayList;
    }

    public static List<Attachment> b(List<Content> list) {
        ArrayList arrayList = new ArrayList();
        for (Content content : list) {
            if (content instanceof Attachment) {
                arrayList.add((Attachment) content);
            }
        }
        return arrayList;
    }

    public static List<ImageAttachment> c(List<Content> list) {
        ArrayList arrayList = new ArrayList();
        for (Attachment attachment : b(list)) {
            if (attachment instanceof ImageAttachment) {
                ImageAttachment imageAttachment = (ImageAttachment) attachment;
                if (imageAttachment.d != null) {
                    arrayList.add(imageAttachment);
                }
            }
        }
        return arrayList;
    }

    public static List<VideoAttachment> d(List<Content> list) {
        ArrayList arrayList = new ArrayList();
        for (Attachment attachment : b(list)) {
            if (attachment instanceof VideoAttachment) {
                VideoAttachment videoAttachment = (VideoAttachment) attachment;
                if (videoAttachment.d != null) {
                    arrayList.add(videoAttachment);
                }
            }
        }
        return arrayList;
    }
}
