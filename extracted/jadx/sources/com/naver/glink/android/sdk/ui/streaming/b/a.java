package com.naver.glink.android.sdk.ui.streaming.b;

import android.annotation.TargetApi;
import android.util.Log;
import android.util.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: compiled from: CameraHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(21)
class a {

    /* JADX INFO: renamed from: com.naver.glink.android.sdk.ui.streaming.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CameraHelper.java */
    private static class C0056a implements Comparator<Size> {
        private C0056a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Size size, Size size2) {
            return Long.signum((((long) size.getWidth()) * ((long) size.getHeight())) - (((long) size2.getWidth()) * ((long) size2.getHeight())));
        }
    }

    a() {
    }

    static Size a(List<Size> list) {
        return (Size) Collections.max(list, new C0056a());
    }

    static Size a(Size[] sizeArr, int i, int i2, int i3, int i4, Size size) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int width = size.getWidth();
        int height = size.getHeight();
        for (Size size2 : sizeArr) {
            if (size2.getWidth() <= i3 && size2.getHeight() <= i4 && size2.getHeight() == (size2.getWidth() * height) / width) {
                if (size2.getWidth() < i || size2.getHeight() < i2) {
                    arrayList2.add(size2);
                } else {
                    arrayList.add(size2);
                }
            }
        }
        if (arrayList.size() > 0) {
            return b(arrayList);
        }
        if (arrayList2.size() > 0) {
            return a(arrayList2);
        }
        Log.e("live", "Couldn't find any suitable preview size");
        return sizeArr[0];
    }

    static Size b(List<Size> list) {
        return (Size) Collections.min(list, new C0056a());
    }
}
