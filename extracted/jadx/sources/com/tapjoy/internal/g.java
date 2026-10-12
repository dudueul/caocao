package com.tapjoy.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class g extends at implements ax, Closeable {
    private SQLiteDatabase a;
    private final bd b;
    private int c;

    public g(File file, bd bdVar) {
        this.a = SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
        this.b = bdVar;
        if (this.a.getVersion() != 1) {
            this.a.beginTransaction();
            try {
                this.a.execSQL("CREATE TABLE IF NOT EXISTS List(value BLOB)");
                this.a.setVersion(1);
                this.a.setTransactionSuccessful();
                this.a.endTransaction();
            } catch (Throwable th) {
                this.a.endTransaction();
                throw th;
            }
        }
        this.c = a();
    }

    private int a() {
        Cursor cursorRawQuery = null;
        int i = 0;
        try {
            cursorRawQuery = this.a.rawQuery("SELECT COUNT(1) FROM List", null);
            if (cursorRawQuery.moveToNext()) {
                i = cursorRawQuery.getInt(0);
            }
            return i;
        } finally {
            a(cursorRawQuery);
        }
    }

    private static Cursor a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        cursor.close();
        return null;
    }

    @Override // com.tapjoy.internal.ax
    public final Object a(int i) {
        Cursor cursorRawQuery = null;
        if (i < 0 || i >= this.c) {
            throw new IndexOutOfBoundsException();
        }
        try {
            cursorRawQuery = this.a.rawQuery("SELECT value FROM List ORDER BY rowid LIMIT " + i + ",1", null);
            if (!cursorRawQuery.moveToNext()) {
                throw new NoSuchElementException();
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(cursorRawQuery.getBlob(0));
            try {
                try {
                    Object objB = this.b.b(byteArrayInputStream);
                    jz.a(byteArrayInputStream);
                    a(cursorRawQuery);
                    return objB;
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            } catch (Throwable th) {
                jz.a(byteArrayInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            a(cursorRawQuery);
            throw th2;
        }
    }

    @Override // com.tapjoy.internal.ax
    public final void b(int i) throws Throwable {
        Cursor cursor = null;
        if (i <= 0 || i > this.c) {
            throw new IndexOutOfBoundsException();
        }
        if (i == this.c) {
            clear();
            return;
        }
        try {
            Cursor cursorRawQuery = this.a.rawQuery("SELECT rowid FROM List ORDER BY rowid LIMIT " + (i - 1) + ",1", null);
            try {
                if (!cursorRawQuery.moveToNext()) {
                    throw new IllegalStateException();
                }
                long j = cursorRawQuery.getLong(0);
                cursorRawQuery.close();
                int iDelete = this.a.delete("List", "rowid <= " + j, null);
                this.c -= iDelete;
                if (iDelete != i) {
                    throw new IllegalStateException("Try to delete " + i + ", but deleted " + iDelete);
                }
                a((Cursor) null);
                return;
            } catch (Throwable th) {
                th = th;
                cursor = cursorRawQuery;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        a(cursor);
        throw th;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.a.delete("List", "1", null);
        this.c = 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.a != null) {
            this.a.close();
            this.a = null;
        }
    }

    protected final void finalize() throws Throwable {
        close();
        super.finalize();
    }

    @Override // java.util.Queue
    public final boolean offer(Object obj) {
        jp.a(obj);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                this.b.a(byteArrayOutputStream, obj);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                jz.a(byteArrayOutputStream);
                ContentValues contentValues = new ContentValues();
                contentValues.put("value", byteArray);
                if (this.a.insert("List", null, contentValues) == -1) {
                    return false;
                }
                this.c++;
                return true;
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        } catch (Throwable th) {
            jz.a(byteArrayOutputStream);
            throw th;
        }
    }

    @Override // java.util.Queue
    public final Object peek() {
        if (this.c > 0) {
            return a(0);
        }
        return null;
    }

    @Override // java.util.Queue
    public final Object poll() throws Throwable {
        if (this.c <= 0) {
            return null;
        }
        Object objPeek = peek();
        b(1);
        return objPeek;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.c;
    }
}
