package com.google.android.gms.common.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CursorWrapper extends android.database.CursorWrapper implements CrossProcessCursor {
    private AbstractWindowedCursor zzxu;

    public CursorWrapper(Cursor cursor) {
        super(cursor);
        Cursor wrappedCursor = cursor;
        for (int i = 0; i < 10 && (wrappedCursor instanceof android.database.CursorWrapper); i++) {
            wrappedCursor = ((android.database.CursorWrapper) wrappedCursor).getWrappedCursor();
        }
        if (!(wrappedCursor instanceof AbstractWindowedCursor)) {
            String strValueOf = String.valueOf(wrappedCursor.getClass().getName());
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "Unknown type: ".concat(strValueOf) : new String("Unknown type: "));
        }
        this.zzxu = (AbstractWindowedCursor) wrappedCursor;
    }

    @Override // android.database.CrossProcessCursor
    public void fillWindow(int i, CursorWindow cursorWindow) {
        this.zzxu.fillWindow(i, cursorWindow);
    }

    @Override // android.database.CrossProcessCursor
    public CursorWindow getWindow() {
        return this.zzxu.getWindow();
    }

    @Override // android.database.CursorWrapper
    public AbstractWindowedCursor getWrappedCursor() {
        return this.zzxu;
    }

    @Override // android.database.CrossProcessCursor
    public boolean onMove(int i, int i2) {
        return this.zzxu.onMove(i, i2);
    }

    public void setWindow(CursorWindow cursorWindow) {
        this.zzxu.setWindow(cursorWindow);
    }
}
