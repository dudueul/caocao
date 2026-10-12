package com.crashlytics.android.core;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class Utils {
    private static final FilenameFilter ALL_FILES_FILTER = new FilenameFilter() { // from class: com.crashlytics.android.core.Utils.1
        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return true;
        }
    };

    private Utils() {
    }

    static int capFileCount(File file, int i, Comparator<File> comparator) {
        return capFileCount(file, ALL_FILES_FILTER, i, comparator);
    }

    static int capFileCount(File file, FilenameFilter filenameFilter, int i, Comparator<File> comparator) {
        File[] fileArrListFiles = file.listFiles(filenameFilter);
        if (fileArrListFiles == null) {
            return 0;
        }
        int length = fileArrListFiles.length;
        Arrays.sort(fileArrListFiles, comparator);
        for (File file2 : fileArrListFiles) {
            if (length <= i) {
                return length;
            }
            file2.delete();
            length--;
        }
        return length;
    }
}
