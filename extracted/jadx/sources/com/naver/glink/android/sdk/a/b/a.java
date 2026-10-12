package com.naver.glink.android.sdk.a.b;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: GsonSingleton.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    static Gson a;

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.a.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GsonSingleton.java */
    public static class C0016a implements JsonDeserializer<Map<String, Object>> {
        public Object a(JsonElement jsonElement) {
            if (jsonElement.isJsonArray()) {
                ArrayList arrayList = new ArrayList();
                Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
                while (it.hasNext()) {
                    arrayList.add(a(it.next()));
                }
                return arrayList;
            }
            if (jsonElement.isJsonObject()) {
                LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
                for (Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                    linkedTreeMap.put(entry.getKey(), a(entry.getValue()));
                }
                return linkedTreeMap;
            }
            if (jsonElement.isJsonPrimitive()) {
                JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
                if (asJsonPrimitive.isBoolean()) {
                    return Boolean.valueOf(asJsonPrimitive.getAsBoolean());
                }
                if (asJsonPrimitive.isString()) {
                    return asJsonPrimitive.getAsString();
                }
                if (asJsonPrimitive.isNumber()) {
                    Number asNumber = asJsonPrimitive.getAsNumber();
                    return Math.ceil(asNumber.doubleValue()) == ((double) asNumber.longValue()) ? Long.valueOf(asNumber.longValue()) : Double.valueOf(asNumber.doubleValue());
                }
            }
            return null;
        }

        @Override // com.google.gson.JsonDeserializer
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Map<String, Object> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
            return (Map) a(jsonElement);
        }
    }

    public static Gson a() {
        if (a == null) {
            a = new GsonBuilder().setLenient().registerTypeAdapter(new TypeToken<Map<String, Object>>() { // from class: com.naver.glink.android.sdk.a.b.a.1
            }.getType(), new C0016a()).create();
        }
        return a;
    }
}
