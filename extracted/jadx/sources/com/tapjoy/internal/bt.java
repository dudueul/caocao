package com.tapjoy.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class bt implements Closeable {
    final Writer a;
    private final List b = new ArrayList();
    private String c;
    private String d;
    private boolean e;

    public bt(Writer writer) {
        this.b.add(bq.EMPTY_DOCUMENT);
        this.d = ":";
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.a = writer;
    }

    private bt a(bq bqVar, bq bqVar2, String str) throws IOException {
        bq bqVarE = e();
        if (bqVarE != bqVar2 && bqVarE != bqVar) {
            throw new IllegalStateException("Nesting problem: " + this.b);
        }
        this.b.remove(this.b.size() - 1);
        if (bqVarE == bqVar2) {
            g();
        }
        this.a.write(str);
        return this;
    }

    private bt a(bq bqVar, String str) throws IOException {
        a(true);
        this.b.add(bqVar);
        this.a.write(str);
        return this;
    }

    private bt a(Object[] objArr) throws IOException {
        if (objArr == null) {
            return f();
        }
        a();
        for (Object obj : objArr) {
            a(obj);
        }
        b();
        return this;
    }

    private void a(bq bqVar) {
        this.b.set(this.b.size() - 1, bqVar);
    }

    private void a(boolean z) throws IOException {
        switch (e()) {
            case EMPTY_DOCUMENT:
                if (!this.e && !z) {
                    throw new IllegalStateException("JSON must start with an array or an object.");
                }
                a(bq.NONEMPTY_DOCUMENT);
                return;
            case EMPTY_ARRAY:
                a(bq.NONEMPTY_ARRAY);
                g();
                return;
            case NONEMPTY_ARRAY:
                this.a.append(',');
                g();
                return;
            case DANGLING_NAME:
                this.a.append((CharSequence) this.d);
                a(bq.NONEMPTY_OBJECT);
                return;
            case NONEMPTY_DOCUMENT:
                throw new IllegalStateException("JSON must have only one top-level value.");
            default:
                throw new IllegalStateException("Nesting problem: " + this.b);
        }
    }

    private void c(String str) throws IOException {
        this.a.write("\"");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            switch (cCharAt) {
                case '\b':
                    this.a.write("\\b");
                    continue;
                    break;
                case '\t':
                    this.a.write("\\t");
                    continue;
                    break;
                case '\n':
                    this.a.write("\\n");
                    continue;
                    break;
                case '\f':
                    this.a.write("\\f");
                    continue;
                    break;
                case '\r':
                    this.a.write("\\r");
                    continue;
                    break;
                case '\"':
                case '\\':
                    this.a.write(92);
                    break;
                case 8232:
                case 8233:
                    this.a.write(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                    continue;
                    break;
                default:
                    if (cCharAt <= 31) {
                        this.a.write(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                    }
                    break;
            }
            this.a.write(cCharAt);
        }
        this.a.write("\"");
    }

    private bq e() {
        return (bq) this.b.get(this.b.size() - 1);
    }

    private bt f() throws IOException {
        a(false);
        this.a.write("null");
        return this;
    }

    private void g() throws IOException {
        if (this.c == null) {
            return;
        }
        this.a.write("\n");
        for (int i = 1; i < this.b.size(); i++) {
            this.a.write(this.c);
        }
    }

    public final bt a() {
        return a(bq.EMPTY_ARRAY, "[");
    }

    public final bt a(long j) throws IOException {
        a(false);
        this.a.write(Long.toString(j));
        return this;
    }

    public final bt a(bl blVar) throws IOException {
        a(false);
        blVar.a(this.a);
        return this;
    }

    public final bt a(Number number) throws IOException {
        if (number == null) {
            return f();
        }
        String string = number.toString();
        if (!this.e && (string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
        }
        a(false);
        this.a.append((CharSequence) string);
        return this;
    }

    public final bt a(Object obj) throws IOException {
        if (obj == null) {
            return f();
        }
        if (obj instanceof br) {
            if (this.b.size() != this.b.size()) {
                throw new IllegalStateException(obj.getClass().getName() + ".writeToJson(JsonWriter) wrote incomplete value");
            }
            return this;
        }
        if (obj instanceof Boolean) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            a(false);
            this.a.write(zBooleanValue ? "true" : "false");
            return this;
        }
        if (obj instanceof Number) {
            if (obj instanceof Long) {
                return a(((Number) obj).longValue());
            }
            if (!(obj instanceof Double)) {
                return a((Number) obj);
            }
            double dDoubleValue = ((Number) obj).doubleValue();
            if (!this.e && (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue))) {
                throw new IllegalArgumentException("Numeric values must be finite, but was " + dDoubleValue);
            }
            a(false);
            this.a.append((CharSequence) Double.toString(dDoubleValue));
            return this;
        }
        if (obj instanceof String) {
            return b((String) obj);
        }
        if (obj instanceof bl) {
            return a((bl) obj);
        }
        if (obj instanceof Collection) {
            return a((Collection) obj);
        }
        if (obj instanceof Map) {
            return a((Map) obj);
        }
        if (obj instanceof Date) {
            Date date = (Date) obj;
            return date == null ? f() : b(w.a(date));
        }
        if (obj instanceof Object[]) {
            return a((Object[]) obj);
        }
        throw new IllegalArgumentException("Unknown type: " + obj.getClass().getName());
    }

    public final bt a(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        bq bqVarE = e();
        if (bqVarE == bq.NONEMPTY_OBJECT) {
            this.a.write(44);
        } else if (bqVarE != bq.EMPTY_OBJECT) {
            throw new IllegalStateException("Nesting problem: " + this.b);
        }
        g();
        a(bq.DANGLING_NAME);
        c(str);
        return this;
    }

    public final bt a(Collection collection) throws IOException {
        if (collection == null) {
            return f();
        }
        a();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
        b();
        return this;
    }

    public final bt a(Map map) throws IOException {
        if (map == null) {
            return f();
        }
        c();
        for (Map.Entry entry : map.entrySet()) {
            a(String.valueOf(entry.getKey()));
            a(entry.getValue());
        }
        d();
        return this;
    }

    public final bt b() {
        return a(bq.EMPTY_ARRAY, bq.NONEMPTY_ARRAY, "]");
    }

    public final bt b(String str) throws IOException {
        if (str == null) {
            return f();
        }
        a(false);
        c(str);
        return this;
    }

    public final bt c() {
        return a(bq.EMPTY_OBJECT, "{");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.a.close();
        if (e() != bq.NONEMPTY_DOCUMENT) {
            throw new IOException("Incomplete document");
        }
    }

    public final bt d() {
        return a(bq.EMPTY_OBJECT, bq.NONEMPTY_OBJECT, "}");
    }
}
