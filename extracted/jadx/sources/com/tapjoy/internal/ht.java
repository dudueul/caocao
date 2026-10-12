package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.tapjoy.TapjoyUtil;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ht {
    public static final ht a = new ht();
    public Context b;
    public SharedPreferences c = null;
    public SharedPreferences d = null;
    ExecutorService e = new ThreadPoolExecutor(0, 1, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private File f;

    private ht() {
    }

    public static boolean a(long j) {
        return j >= 3600;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void c() {
        boolean z;
        boolean z2;
        boolean z3 = true;
        synchronized (this) {
            long jB = v.b();
            File[] fileArrListFiles = b().listFiles();
            HashMap map = new HashMap();
            if (fileArrListFiles != null) {
                for (File file : fileArrListFiles) {
                    map.put(file.getName(), file);
                }
            }
            SharedPreferences.Editor editorEdit = this.c.edit();
            SharedPreferences.Editor editorEdit2 = this.d.edit();
            HashMap map2 = new HashMap(this.c.getAll());
            HashMap map3 = new HashMap(this.d.getAll());
            Iterator it = map2.entrySet().iterator();
            boolean z4 = false;
            while (it.hasNext()) {
                String str = (String) ((Map.Entry) it.next()).getKey();
                if (map3.containsKey(str)) {
                    z2 = z4;
                } else {
                    it.remove();
                    editorEdit.remove(str);
                    new Object[1][0] = str;
                    z2 = true;
                }
                z4 = z2;
            }
            Iterator it2 = map3.entrySet().iterator();
            while (it2.hasNext()) {
                String str2 = (String) ((Map.Entry) it2.next()).getKey();
                if (map2.containsKey(str2)) {
                    z = z4;
                } else {
                    it2.remove();
                    editorEdit2.remove(str2);
                    new Object[1][0] = str2;
                    z = true;
                }
                z4 = z;
            }
            Iterator it3 = map2.entrySet().iterator();
            boolean z5 = z4;
            while (it3.hasNext()) {
                Map.Entry entry = (Map.Entry) it3.next();
                String str3 = (String) entry.getKey();
                if (((Long) entry.getValue()).longValue() < jB) {
                    it3.remove();
                    editorEdit.remove(str3);
                    editorEdit2.remove(str3);
                    z5 = true;
                } else {
                    map.remove(str3);
                }
            }
            Iterator it4 = map.entrySet().iterator();
            while (it4.hasNext()) {
                File file2 = (File) ((Map.Entry) it4.next()).getValue();
                file2.delete();
                new Object[1][0] = file2;
            }
            if (map2.size() > 30) {
                int size = map2.size() - 30;
                LinkedList linkedList = new LinkedList(map2.entrySet());
                Collections.sort(linkedList, new Comparator() { // from class: com.tapjoy.internal.ht.3
                    @Override // java.util.Comparator
                    public final /* synthetic */ int compare(Object obj, Object obj2) {
                        return ((Long) ((Map.Entry) obj).getValue()).compareTo((Long) ((Map.Entry) obj2).getValue());
                    }
                });
                Iterator it5 = linkedList.iterator();
                for (int i = 0; i < size && it5.hasNext(); i++) {
                    Map.Entry entry2 = (Map.Entry) it5.next();
                    String str4 = (String) entry2.getKey();
                    Long l = (Long) entry2.getValue();
                    editorEdit.remove(str4);
                    editorEdit2.remove(str4);
                    File fileA = a(str4);
                    fileA.delete();
                    Object[] objArr = {fileA, l};
                }
            } else {
                z3 = z5;
            }
            if (z3) {
                editorEdit.commit();
                editorEdit2.commit();
            }
        }
    }

    final File a(String str) {
        return new File(b(), str);
    }

    public final File a(URL url) {
        if (this.b == null) {
            return null;
        }
        synchronized (this) {
            String strB = b(url);
            File fileA = a(strB);
            if (!fileA.exists()) {
                return null;
            }
            long jB = v.b();
            long j = this.c.getLong(strB, 0L);
            if (j >= jB) {
                Object[] objArr = {strB, url};
                return fileA;
            }
            Object[] objArr2 = {strB, Long.valueOf(jB), Long.valueOf(j)};
            if (j != 0) {
                this.c.edit().remove(strB).commit();
                this.d.edit().remove(strB).commit();
            }
            fileA.delete();
            return null;
        }
    }

    public final void a() {
        this.e.submit(new Runnable() { // from class: com.tapjoy.internal.ht.1
            @Override // java.lang.Runnable
            public final void run() {
                if (ht.this.b == null) {
                    return;
                }
                ht.this.c();
            }
        });
    }

    final File b() {
        File file = this.f;
        if (file == null) {
            file = new File(this.b.getCacheDir(), "tapjoy_mm_cache");
            this.f = file;
        }
        if (!file.isDirectory()) {
            file.delete();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    final synchronized String b(URL url) {
        String strConvertToHex;
        String str;
        String string = url.toString();
        strConvertToHex = TapjoyUtil.convertToHex(ch.a(string.getBytes()));
        String string2 = this.d.getString(strConvertToHex, null);
        if (string2 == null) {
            this.d.edit().putString(strConvertToHex, string).commit();
        } else if (!string2.equals(string)) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                str = strConvertToHex + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + i2;
                String string3 = this.d.getString(str, null);
                if (string3 == null || string3.equals(string)) {
                    break;
                }
                i = i2;
            }
            this.d.edit().putString(str, string).commit();
            strConvertToHex = str;
        }
        return strConvertToHex;
    }
}
