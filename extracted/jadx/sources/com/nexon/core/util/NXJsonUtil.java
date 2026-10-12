package com.nexon.core.util;

import android.support.v4.util.ArrayMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.nexon.core.log.ToyLog;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXJsonUtil {
    private static Gson gson;

    public static class JSONObjectDeserializer implements JsonDeserializer<JSONObject> {
        @Override // com.google.gson.JsonDeserializer
        public JSONObject deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            try {
                return new JSONObject(jsonElement.toString());
            } catch (JSONException e) {
                return new JSONObject();
            }
        }
    }

    public static class JSONObjectSerializer implements JsonSerializer<JSONObject> {
        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(JSONObject jSONObject, Type type, JsonSerializationContext jsonSerializationContext) {
            try {
                return jsonSerializationContext.serialize(new Gson().fromJson(jSONObject.toString(), new TypeToken<ArrayMap<String, Object>>() { // from class: com.nexon.core.util.NXJsonUtil.JSONObjectSerializer.1
                }.getType()));
            } catch (Exception e) {
                return jsonSerializationContext.serialize(new ArrayMap());
            }
        }
    }

    public static JsonObject fromJsonString(String str) {
        JsonObject asJsonObject = null;
        try {
            JsonElement jsonElement = new JsonParser().parse(str);
            if (!jsonElement.isJsonObject() || jsonElement.isJsonNull()) {
                ToyLog.e("Not json String or json String is null : " + str);
            } else {
                asJsonObject = jsonElement.getAsJsonObject();
            }
        } catch (JsonParseException e) {
            ToyLog.e("Fail Json Parsing : " + str);
        }
        return asJsonObject;
    }

    public static <T> T fromObject(String str, Class<T> cls) {
        return (T) sharedGson().fromJson(str, (Class) cls);
    }

    public static <T> T fromObject(String str, Type type) {
        return (T) sharedGson().fromJson(str, type);
    }

    private static Gson sharedGson() {
        if (gson == null) {
            synchronized (NXJsonUtil.class) {
                if (gson == null) {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.registerTypeAdapter(JSONObject.class, new JSONObjectDeserializer());
                    gsonBuilder.registerTypeAdapter(JSONObject.class, new JSONObjectSerializer());
                    gsonBuilder.disableHtmlEscaping();
                    gsonBuilder.serializeNulls();
                    gson = gsonBuilder.create();
                }
            }
        }
        return gson;
    }

    public static String toJsonString(Object obj) {
        return sharedGson().toJson(obj);
    }

    public static String toJsonString(Object obj, Type type) {
        return sharedGson().toJson(obj, type);
    }
}
