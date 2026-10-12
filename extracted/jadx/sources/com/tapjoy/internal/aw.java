package com.tapjoy.internal;

import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class aw {
    public static void a(List list, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i <= 0) {
            return;
        }
        int size = list.size();
        if (i > size) {
            throw new IndexOutOfBoundsException();
        }
        if (i == size) {
            list.clear();
            return;
        }
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return;
            }
            list.remove(0);
            i = i2;
        }
    }
}
