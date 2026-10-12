package twitter4j;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeSet;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class JSONObject {
    public static final Object NULL = new Null(null);
    private final Map map;

    private static final class Null {
        private Null() {
        }

        /* synthetic */ Null(Null r1) {
            this();
        }

        protected final Object clone() {
            return this;
        }

        public boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public String toString() {
            return "null";
        }
    }

    public JSONObject() {
        this.map = new HashMap();
    }

    public JSONObject(Object obj) {
        this();
        populateMap(obj);
    }

    public JSONObject(Object obj, String[] strArr) {
        this();
        Class<?> cls = obj.getClass();
        for (String str : strArr) {
            try {
                putOpt(str, cls.getField(str).get(obj));
            } catch (Exception e) {
            }
        }
    }

    public JSONObject(String str) throws JSONException {
        this(new JSONTokener(str));
    }

    public JSONObject(String str, Locale locale) throws JSONException {
        this();
        ResourceBundle bundle = ResourceBundle.getBundle(str, locale, Thread.currentThread().getContextClassLoader());
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String strNextElement = keys.nextElement();
            if (strNextElement instanceof String) {
                String[] strArrSplit = strNextElement.split("\\.");
                int length = strArrSplit.length - 1;
                JSONObject jSONObject = this;
                for (int i = 0; i < length; i++) {
                    String str2 = strArrSplit[i];
                    Object objOpt = jSONObject.opt(str2);
                    JSONObject jSONObject2 = objOpt instanceof JSONObject ? (JSONObject) objOpt : null;
                    if (jSONObject2 == null) {
                        jSONObject2 = new JSONObject();
                        jSONObject.put(str2, jSONObject2);
                    }
                    jSONObject = jSONObject2;
                }
                jSONObject.put(strArrSplit[length], bundle.getString(strNextElement));
            }
        }
    }

    public JSONObject(Map map) {
        this.map = new HashMap();
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value != null) {
                    this.map.put(entry.getKey(), wrap(value));
                }
            }
        }
    }

    public JSONObject(JSONObject jSONObject, String[] strArr) {
        this();
        for (String str : strArr) {
            try {
                putOnce(str, jSONObject.opt(str));
            } catch (Exception e) {
            }
        }
    }

    /* JADX WARN: Switch 'out' block B:7:0x0026 for B:15:0x0056 already processed. Defaulting to fallback option. */
    /* JADX WARN: Switch 'out' block B:7:0x0026 for B:8:0x002a already processed. Defaulting to fallback option. */
    public JSONObject(JSONTokener jSONTokener) throws JSONException {
        this();
        if (jSONTokener.nextClean() != '{') {
            throw jSONTokener.syntaxError("A JSONObject text must begin with '{' found:" + jSONTokener.nextClean());
        }
        while (true) {
            switch (jSONTokener.nextClean()) {
                case 0:
                    throw jSONTokener.syntaxError("A JSONObject text must end with '}'");
                case '}':
                    return;
                default:
                    jSONTokener.back();
                    String string = jSONTokener.nextValue().toString();
                    char cNextClean = jSONTokener.nextClean();
                    if (cNextClean == '=') {
                        if (jSONTokener.next() != '>') {
                            jSONTokener.back();
                        }
                    } else if (cNextClean != ':') {
                        throw jSONTokener.syntaxError("Expected a ':' after a key");
                    }
                    putOnce(string, jSONTokener.nextValue());
                    switch (jSONTokener.nextClean()) {
                        case ',':
                        case ';':
                            if (jSONTokener.nextClean() == '}') {
                                return;
                            } else {
                                jSONTokener.back();
                            }
                            break;
                        case '}':
                            return;
                        default:
                            throw jSONTokener.syntaxError("Expected a ',' or '}'");
                    }
                    break;
            }
        }
    }

    public static String numberToString(Number number) throws JSONException {
        if (number == null) {
            throw new JSONException("Null pointer");
        }
        testValidity(number);
        String string = number.toString();
        if (string.indexOf(46) <= 0 || string.indexOf(101) >= 0 || string.indexOf(69) >= 0) {
            return string;
        }
        while (string.endsWith("0")) {
            string = string.substring(0, string.length() - 1);
        }
        return string.endsWith(".") ? string.substring(0, string.length() - 1) : string;
    }

    private void populateMap(Object obj) {
        Class<?> cls = obj.getClass();
        for (Method method : cls.getClassLoader() != null ? cls.getMethods() : cls.getDeclaredMethods()) {
            try {
                if (Modifier.isPublic(method.getModifiers())) {
                    String name = method.getName();
                    String strSubstring = "";
                    if (name.startsWith("get")) {
                        strSubstring = (name.equals("getClass") || name.equals("getDeclaringClass")) ? "" : name.substring(3);
                    } else if (name.startsWith("is")) {
                        strSubstring = name.substring(2);
                    }
                    if (strSubstring.length() > 0 && Character.isUpperCase(strSubstring.charAt(0)) && method.getParameterTypes().length == 0) {
                        if (strSubstring.length() == 1) {
                            strSubstring = strSubstring.toLowerCase();
                        } else if (!Character.isUpperCase(strSubstring.charAt(1))) {
                            strSubstring = String.valueOf(strSubstring.substring(0, 1).toLowerCase()) + strSubstring.substring(1);
                        }
                        Object objInvoke = method.invoke(obj, null);
                        if (objInvoke != null) {
                            this.map.put(strSubstring, wrap(objInvoke));
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public static String quote(String str) {
        if (str == null || str.length() == 0) {
            return "\"\"";
        }
        char cCharAt = 0;
        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 4);
        sb.append('\"');
        for (int i = 0; i < length; i++) {
            char c = cCharAt;
            cCharAt = str.charAt(i);
            switch (cCharAt) {
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\"':
                case '\\':
                    sb.append('\\');
                    sb.append(cCharAt);
                    break;
                case '/':
                    if (c == '<') {
                        sb.append('\\');
                    }
                    sb.append(cCharAt);
                    break;
                default:
                    if (cCharAt < ' ' || ((cCharAt >= 128 && cCharAt < 160) || (cCharAt >= 8192 && cCharAt < 8448))) {
                        String str2 = "000" + Integer.toHexString(cCharAt);
                        sb.append("\\u").append(str2.substring(str2.length() - 4));
                    } else {
                        sb.append(cCharAt);
                    }
                    break;
            }
        }
        sb.append('\"');
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Object stringToValue(String str) {
        if (str.equals("")) {
            return str;
        }
        if (str.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        }
        if (str.equalsIgnoreCase("false")) {
            return Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("null")) {
            return NULL;
        }
        char cCharAt = str.charAt(0);
        if ((cCharAt < '0' || cCharAt > '9') && cCharAt != '.' && cCharAt != '-' && cCharAt != '+') {
            return str;
        }
        if (cCharAt == '0' && str.length() > 2 && (str.charAt(1) == 'x' || str.charAt(1) == 'X')) {
            try {
                return Integer.valueOf(Integer.parseInt(str.substring(2), 16));
            } catch (Exception e) {
            }
        }
        try {
            if (str.indexOf(46) > -1 || str.indexOf(101) > -1 || str.indexOf(69) > -1) {
                str = Double.valueOf(str);
            } else {
                Long l = new Long(str);
                str = l.longValue() == ((long) l.intValue()) ? Integer.valueOf(l.intValue()) : l;
            }
            return str;
        } catch (Exception e2) {
            return str;
        }
    }

    public static void testValidity(Object obj) throws JSONException {
        if (obj != null) {
            if (obj instanceof Double) {
                if (((Double) obj).isInfinite() || ((Double) obj).isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
            } else if (obj instanceof Float) {
                if (((Float) obj).isInfinite() || ((Float) obj).isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
            }
        }
    }

    public static String valueToString(Object obj) throws JSONException {
        if (obj == null || obj.equals(null)) {
            return "null";
        }
        if (obj instanceof Number) {
            return numberToString((Number) obj);
        }
        if ((obj instanceof Boolean) || (obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            return obj.toString();
        }
        if (obj instanceof Map) {
            return new JSONObject((Map) obj).toString();
        }
        if (obj instanceof Collection) {
            return new JSONArray((Collection) obj).toString();
        }
        return obj.getClass().isArray() ? new JSONArray(obj).toString() : quote(obj.toString());
    }

    static String valueToString(Object obj, int i, int i2) throws JSONException {
        if (obj == null || obj.equals(null)) {
            return "null";
        }
        if (obj instanceof Number) {
            return numberToString((Number) obj);
        }
        if (obj instanceof Boolean) {
            return obj.toString();
        }
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).toString(i, i2);
        }
        if (obj instanceof JSONArray) {
            return ((JSONArray) obj).toString(i, i2);
        }
        if (obj instanceof Map) {
            return new JSONObject((Map) obj).toString(i, i2);
        }
        if (obj instanceof Collection) {
            return new JSONArray((Collection) obj).toString(i, i2);
        }
        return obj.getClass().isArray() ? new JSONArray(obj).toString(i, i2) : quote(obj.toString());
    }

    public static Object wrap(Object obj) {
        try {
            if (obj == null) {
                return NULL;
            }
            if ((obj instanceof JSONObject) || (obj instanceof JSONArray) || NULL.equals(obj) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Boolean) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof String)) {
                return obj;
            }
            if (obj instanceof Collection) {
                return new JSONArray((Collection) obj);
            }
            if (obj.getClass().isArray()) {
                return new JSONArray(obj);
            }
            if (obj instanceof Map) {
                return new JSONObject((Map) obj);
            }
            Package r1 = obj.getClass().getPackage();
            String name = r1 != null ? r1.getName() : "";
            return (name.startsWith("java.") || name.startsWith("javax.") || obj.getClass().getClassLoader() == null) ? obj.toString() : new JSONObject(obj);
        } catch (Exception e) {
            return null;
        }
    }

    public JSONObject append(String str, Object obj) throws JSONException {
        testValidity(obj);
        Object objOpt = opt(str);
        if (objOpt == null) {
            put(str, new JSONArray().put(obj));
        } else {
            if (!(objOpt instanceof JSONArray)) {
                throw new JSONException("JSONObject[" + str + "] is not a JSONArray.");
            }
            put(str, ((JSONArray) objOpt).put(obj));
        }
        return this;
    }

    public Object get(String str) throws JSONException {
        if (str == null) {
            throw new JSONException("Null key.");
        }
        Object objOpt = opt(str);
        if (objOpt == null) {
            throw new JSONException("JSONObject[" + quote(str) + "] not found.");
        }
        return objOpt;
    }

    public boolean getBoolean(String str) throws JSONException {
        Object obj = get(str);
        if (obj.equals(Boolean.FALSE) || ((obj instanceof String) && ((String) obj).equalsIgnoreCase("false"))) {
            return false;
        }
        if (obj.equals(Boolean.TRUE) || ((obj instanceof String) && ((String) obj).equalsIgnoreCase("true"))) {
            return true;
        }
        throw new JSONException("JSONObject[" + quote(str) + "] is not a Boolean.");
    }

    public int getInt(String str) throws JSONException {
        Object obj = get(str);
        try {
            return obj instanceof Number ? ((Number) obj).intValue() : Integer.parseInt((String) obj);
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + quote(str) + "] is not an int.");
        }
    }

    public JSONArray getJSONArray(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        throw new JSONException("JSONObject[" + quote(str) + "] is not a JSONArray.");
    }

    public JSONObject getJSONObject(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        throw new JSONException("JSONObject[" + quote(str) + "] is not a JSONObject.");
    }

    public long getLong(String str) throws JSONException {
        Object obj = get(str);
        try {
            return obj instanceof Number ? ((Number) obj).longValue() : Long.parseLong((String) obj);
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + quote(str) + "] is not a long.");
        }
    }

    public String getString(String str) throws JSONException {
        Object obj = get(str);
        if (obj == NULL) {
            return null;
        }
        return obj.toString();
    }

    public boolean has(String str) {
        return this.map.containsKey(str);
    }

    public boolean isNull(String str) {
        return NULL.equals(opt(str));
    }

    public Iterator keys() {
        return this.map.keySet().iterator();
    }

    public int length() {
        return this.map.size();
    }

    public JSONArray names() {
        JSONArray jSONArray = new JSONArray();
        Iterator itKeys = keys();
        while (itKeys.hasNext()) {
            jSONArray.put(itKeys.next());
        }
        if (jSONArray.length() == 0) {
            return null;
        }
        return jSONArray;
    }

    public Object opt(String str) {
        if (str == null) {
            return null;
        }
        return this.map.get(str);
    }

    public JSONObject put(String str, double d) throws JSONException {
        put(str, new Double(d));
        return this;
    }

    public JSONObject put(String str, int i) throws JSONException {
        put(str, new Integer(i));
        return this;
    }

    public JSONObject put(String str, long j) throws JSONException {
        put(str, new Long(j));
        return this;
    }

    public JSONObject put(String str, Object obj) throws JSONException {
        if (str == null) {
            throw new JSONException("Null key.");
        }
        if (obj != null) {
            testValidity(obj);
            this.map.put(str, obj);
        } else {
            remove(str);
        }
        return this;
    }

    public JSONObject put(String str, Collection collection) throws JSONException {
        put(str, new JSONArray(collection));
        return this;
    }

    public JSONObject put(String str, Map map) throws JSONException {
        put(str, new JSONObject(map));
        return this;
    }

    public JSONObject put(String str, boolean z) throws JSONException {
        put(str, z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public JSONObject putOnce(String str, Object obj) throws JSONException {
        if (str != null && obj != null) {
            if (opt(str) != null) {
                throw new JSONException("Duplicate key \"" + str + "\"");
            }
            put(str, obj);
        }
        return this;
    }

    public JSONObject putOpt(String str, Object obj) throws JSONException {
        if (str != null && obj != null) {
            put(str, obj);
        }
        return this;
    }

    public Object remove(String str) {
        return this.map.remove(str);
    }

    public Iterator sortedKeys() {
        return new TreeSet(this.map.keySet()).iterator();
    }

    public String toString() {
        try {
            Iterator itKeys = keys();
            StringBuilder sb = new StringBuilder("{");
            while (itKeys.hasNext()) {
                if (sb.length() > 1) {
                    sb.append(',');
                }
                Object next = itKeys.next();
                sb.append(quote(next.toString()));
                sb.append(':');
                sb.append(valueToString(this.map.get(next)));
            }
            sb.append('}');
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String toString(int i) throws JSONException {
        return toString(i, 0);
    }

    String toString(int i, int i2) throws JSONException {
        int length = length();
        if (length == 0) {
            return "{}";
        }
        Iterator itSortedKeys = sortedKeys();
        int i3 = i2 + i;
        StringBuilder sb = new StringBuilder("{");
        if (length == 1) {
            Object next = itSortedKeys.next();
            sb.append(quote(next.toString()));
            sb.append(": ");
            sb.append(valueToString(this.map.get(next), i, i2));
        } else {
            while (itSortedKeys.hasNext()) {
                Object next2 = itSortedKeys.next();
                if (sb.length() > 1) {
                    sb.append(",\n");
                } else {
                    sb.append('\n');
                }
                for (int i4 = 0; i4 < i3; i4++) {
                    sb.append(' ');
                }
                sb.append(quote(next2.toString()));
                sb.append(": ");
                sb.append(valueToString(this.map.get(next2), i, i3));
            }
            if (sb.length() > 1) {
                sb.append('\n');
                for (int i5 = 0; i5 < i2; i5++) {
                    sb.append(' ');
                }
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public Writer write(Writer writer) throws JSONException {
        boolean z = false;
        try {
            Iterator itKeys = keys();
            writer.write(123);
            while (itKeys.hasNext()) {
                if (z) {
                    writer.write(44);
                }
                Object next = itKeys.next();
                writer.write(quote(next.toString()));
                writer.write(58);
                Object obj = this.map.get(next);
                if (obj instanceof JSONObject) {
                    ((JSONObject) obj).write(writer);
                } else if (obj instanceof JSONArray) {
                    ((JSONArray) obj).write(writer);
                } else {
                    writer.write(valueToString(obj));
                }
                z = true;
            }
            writer.write(125);
            return writer;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }
}
