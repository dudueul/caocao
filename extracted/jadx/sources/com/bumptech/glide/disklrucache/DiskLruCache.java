package com.bumptech.glide.disklrucache;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class DiskLruCache implements Closeable {
    static final long ANY_SEQUENCE_NUMBER = -1;
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    static final String JOURNAL_FILE_TEMP = "journal.tmp";
    static final String MAGIC = "libcore.io.DiskLruCache";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    static final String VERSION_1 = "1";
    private final int appVersion;
    private final File directory;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    private Writer journalWriter;
    private long maxSize;
    private int redundantOpCount;
    private final int valueCount;
    private long size = 0;
    private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private long nextSequenceNumber = 0;
    final ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final Callable<Void> cleanupCallable = new Callable<Void>() { // from class: com.bumptech.glide.disklrucache.DiskLruCache.1
        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            synchronized (DiskLruCache.this) {
                if (DiskLruCache.this.journalWriter != null) {
                    DiskLruCache.this.trimToSize();
                    if (DiskLruCache.this.journalRebuildRequired()) {
                        DiskLruCache.this.rebuildJournal();
                        DiskLruCache.this.redundantOpCount = 0;
                    }
                }
            }
            return null;
        }
    };

    public final class Editor {
        private boolean committed;
        private final Entry entry;
        private final boolean[] written;

        private Editor(Entry entry) {
            this.entry = entry;
            this.written = entry.readable ? null : new boolean[DiskLruCache.this.valueCount];
        }

        private InputStream newInputStream(int i) throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                }
                if (!this.entry.readable) {
                    return null;
                }
                try {
                    return new FileInputStream(this.entry.getCleanFile(i));
                } catch (FileNotFoundException e) {
                    return null;
                }
            }
        }

        public void abort() throws IOException {
            DiskLruCache.this.completeEdit(this, false);
        }

        public void abortUnlessCommitted() {
            if (this.committed) {
                return;
            }
            try {
                abort();
            } catch (IOException e) {
            }
        }

        public void commit() throws IOException {
            DiskLruCache.this.completeEdit(this, true);
            this.committed = true;
        }

        public File getFile(int i) throws IOException {
            File dirtyFile;
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor != this) {
                    throw new IllegalStateException();
                }
                if (!this.entry.readable) {
                    this.written[i] = true;
                }
                dirtyFile = this.entry.getDirtyFile(i);
                if (!DiskLruCache.this.directory.exists()) {
                    DiskLruCache.this.directory.mkdirs();
                }
            }
            return dirtyFile;
        }

        public String getString(int i) throws IOException {
            InputStream inputStreamNewInputStream = newInputStream(i);
            if (inputStreamNewInputStream != null) {
                return DiskLruCache.inputStreamToString(inputStreamNewInputStream);
            }
            return null;
        }

        public void set(int i, String str) throws Throwable {
            OutputStreamWriter outputStreamWriter = null;
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(getFile(i)), Util.UTF_8);
                try {
                    outputStreamWriter2.write(str);
                    Util.closeQuietly(outputStreamWriter2);
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter = outputStreamWriter2;
                    Util.closeQuietly(outputStreamWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private final class Entry {
        File[] cleanFiles;
        private Editor currentEditor;
        File[] dirtyFiles;
        private final String key;
        private final long[] lengths;
        private boolean readable;
        private long sequenceNumber;

        private Entry(String str) {
            this.key = str;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new File[DiskLruCache.this.valueCount];
            this.dirtyFiles = new File[DiskLruCache.this.valueCount];
            StringBuilder sbAppend = new StringBuilder(str).append('.');
            int length = sbAppend.length();
            for (int i = 0; i < DiskLruCache.this.valueCount; i++) {
                sbAppend.append(i);
                this.cleanFiles[i] = new File(DiskLruCache.this.directory, sbAppend.toString());
                sbAppend.append(".tmp");
                this.dirtyFiles[i] = new File(DiskLruCache.this.directory, sbAppend.toString());
                sbAppend.setLength(length);
            }
        }

        private IOException invalidLengths(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLengths(String[] strArr) throws IOException {
            if (strArr.length != DiskLruCache.this.valueCount) {
                throw invalidLengths(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.lengths[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException e) {
                    throw invalidLengths(strArr);
                }
            }
        }

        public File getCleanFile(int i) {
            return this.cleanFiles[i];
        }

        public File getDirtyFile(int i) {
            return this.dirtyFiles[i];
        }

        public String getLengths() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long j : this.lengths) {
                sb.append(' ').append(j);
            }
            return sb.toString();
        }
    }

    public final class Value {
        private final File[] files;
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;

        private Value(String str, long j, File[] fileArr, long[] jArr) {
            this.key = str;
            this.sequenceNumber = j;
            this.files = fileArr;
            this.lengths = jArr;
        }

        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public File getFile(int i) {
            return this.files[i];
        }

        public long getLength(int i) {
            return this.lengths[i];
        }

        public String getString(int i) throws IOException {
            return DiskLruCache.inputStreamToString(new FileInputStream(this.files[i]));
        }
    }

    private DiskLruCache(File file, int i, int i2, long j) {
        this.directory = file;
        this.appVersion = i;
        this.journalFile = new File(file, JOURNAL_FILE);
        this.journalFileTmp = new File(file, JOURNAL_FILE_TEMP);
        this.journalFileBackup = new File(file, JOURNAL_FILE_BACKUP);
        this.valueCount = i2;
        this.maxSize = j;
    }

    private void checkNotClosed() {
        if (this.journalWriter == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:29:0x005c A[Catch: all -> 0x0011, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000b, B:6:0x0010, B:11:0x0016, B:14:0x001d, B:16:0x0021, B:18:0x0029, B:19:0x0044, B:20:0x0045, B:22:0x004f, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0085, B:35:0x0088, B:36:0x008c, B:38:0x009d, B:40:0x00ca, B:41:0x00d4, B:43:0x00e1, B:45:0x00e7, B:46:0x00f0), top: B:48:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:31:0x0062 A[Catch: all -> 0x0011, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000b, B:6:0x0010, B:11:0x0016, B:14:0x001d, B:16:0x0021, B:18:0x0029, B:19:0x0044, B:20:0x0045, B:22:0x004f, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0085, B:35:0x0088, B:36:0x008c, B:38:0x009d, B:40:0x00ca, B:41:0x00d4, B:43:0x00e1, B:45:0x00e7, B:46:0x00f0), top: B:48:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:33:0x0068 A[Catch: all -> 0x0011, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000b, B:6:0x0010, B:11:0x0016, B:14:0x001d, B:16:0x0021, B:18:0x0029, B:19:0x0044, B:20:0x0045, B:22:0x004f, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0085, B:35:0x0088, B:36:0x008c, B:38:0x009d, B:40:0x00ca, B:41:0x00d4, B:43:0x00e1, B:45:0x00e7, B:46:0x00f0), top: B:48:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x0088 A[Catch: all -> 0x0011, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000b, B:6:0x0010, B:11:0x0016, B:14:0x001d, B:16:0x0021, B:18:0x0029, B:19:0x0044, B:20:0x0045, B:22:0x004f, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0085, B:35:0x0088, B:36:0x008c, B:38:0x009d, B:40:0x00ca, B:41:0x00d4, B:43:0x00e1, B:45:0x00e7, B:46:0x00f0), top: B:48:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x009d A[Catch: all -> 0x0011, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000b, B:6:0x0010, B:11:0x0016, B:14:0x001d, B:16:0x0021, B:18:0x0029, B:19:0x0044, B:20:0x0045, B:22:0x004f, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0085, B:35:0x0088, B:36:0x008c, B:38:0x009d, B:40:0x00ca, B:41:0x00d4, B:43:0x00e1, B:45:0x00e7, B:46:0x00f0), top: B:48:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00ca A[Catch: all -> 0x0011, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000b, B:6:0x0010, B:11:0x0016, B:14:0x001d, B:16:0x0021, B:18:0x0029, B:19:0x0044, B:20:0x0045, B:22:0x004f, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0085, B:35:0x0088, B:36:0x008c, B:38:0x009d, B:40:0x00ca, B:41:0x00d4, B:43:0x00e1, B:45:0x00e7, B:46:0x00f0), top: B:48:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:45:0x00e7 A[Catch: all -> 0x0011, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000b, B:6:0x0010, B:11:0x0016, B:14:0x001d, B:16:0x0021, B:18:0x0029, B:19:0x0044, B:20:0x0045, B:22:0x004f, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0085, B:35:0x0088, B:36:0x008c, B:38:0x009d, B:40:0x00ca, B:41:0x00d4, B:43:0x00e1, B:45:0x00e7, B:46:0x00f0), top: B:48:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:46:0x00f0 A[Catch: all -> 0x0011, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000b, B:6:0x0010, B:11:0x0016, B:14:0x001d, B:16:0x0021, B:18:0x0029, B:19:0x0044, B:20:0x0045, B:22:0x004f, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0085, B:35:0x0088, B:36:0x008c, B:38:0x009d, B:40:0x00ca, B:41:0x00d4, B:43:0x00e1, B:45:0x00e7, B:46:0x00f0), top: B:48:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:54:0x0085 A[SYNTHETIC] */
    public synchronized void completeEdit(Editor editor, boolean z) throws IOException {
        int i;
        File dirtyFile;
        Entry entry = editor.entry;
        if (entry.currentEditor != editor) {
            throw new IllegalStateException();
        }
        if (!z || entry.readable) {
            for (i = 0; i < this.valueCount; i++) {
                dirtyFile = entry.getDirtyFile(i);
                if (z) {
                    deleteIfExists(dirtyFile);
                } else if (dirtyFile.exists()) {
                    File cleanFile = entry.getCleanFile(i);
                    dirtyFile.renameTo(cleanFile);
                    long j = entry.lengths[i];
                    long length = cleanFile.length();
                    entry.lengths[i] = length;
                    this.size = (this.size - j) + length;
                }
            }
            this.redundantOpCount++;
            entry.currentEditor = null;
            if (entry.readable || z) {
                entry.readable = true;
                this.journalWriter.append((CharSequence) CLEAN);
                this.journalWriter.append(' ');
                this.journalWriter.append((CharSequence) entry.key);
                this.journalWriter.append((CharSequence) entry.getLengths());
                this.journalWriter.append('\n');
                if (z) {
                    long j2 = this.nextSequenceNumber;
                    this.nextSequenceNumber = 1 + j2;
                    entry.sequenceNumber = j2;
                }
            } else {
                this.lruEntries.remove(entry.key);
                this.journalWriter.append((CharSequence) REMOVE);
                this.journalWriter.append(' ');
                this.journalWriter.append((CharSequence) entry.key);
                this.journalWriter.append('\n');
            }
            this.journalWriter.flush();
            if (this.size <= this.maxSize || journalRebuildRequired()) {
                this.executorService.submit(this.cleanupCallable);
            }
        } else {
            for (int i2 = 0; i2 < this.valueCount; i2++) {
                if (!editor.written[i2]) {
                    editor.abort();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                }
                if (!entry.getDirtyFile(i2).exists()) {
                    editor.abort();
                }
            }
            while (i < this.valueCount) {
                dirtyFile = entry.getDirtyFile(i);
                if (z) {
                    deleteIfExists(dirtyFile);
                } else if (dirtyFile.exists()) {
                    File cleanFile2 = entry.getCleanFile(i);
                    dirtyFile.renameTo(cleanFile2);
                    long j3 = entry.lengths[i];
                    long length2 = cleanFile2.length();
                    entry.lengths[i] = length2;
                    this.size = (this.size - j3) + length2;
                }
            }
            this.redundantOpCount++;
            entry.currentEditor = null;
            if (entry.readable || z) {
                entry.readable = true;
                this.journalWriter.append((CharSequence) CLEAN);
                this.journalWriter.append(' ');
                this.journalWriter.append((CharSequence) entry.key);
                this.journalWriter.append((CharSequence) entry.getLengths());
                this.journalWriter.append('\n');
                if (z) {
                    long j4 = this.nextSequenceNumber;
                    this.nextSequenceNumber = 1 + j4;
                    entry.sequenceNumber = j4;
                }
            } else {
                this.lruEntries.remove(entry.key);
                this.journalWriter.append((CharSequence) REMOVE);
                this.journalWriter.append(' ');
                this.journalWriter.append((CharSequence) entry.key);
                this.journalWriter.append('\n');
            }
            this.journalWriter.flush();
            if (this.size <= this.maxSize) {
                this.executorService.submit(this.cleanupCallable);
            } else {
                this.executorService.submit(this.cleanupCallable);
            }
        }
    }

    private static void deleteIfExists(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized Editor edit(String str, long j) throws IOException {
        Editor editor = null;
        synchronized (this) {
            checkNotClosed();
            Entry entry = this.lruEntries.get(str);
            if (j == -1 || (entry != null && entry.sequenceNumber == j)) {
                if (entry == null) {
                    entry = new Entry(str);
                    this.lruEntries.put(str, entry);
                } else if (entry.currentEditor == null) {
                }
                editor = new Editor(entry);
                entry.currentEditor = editor;
                this.journalWriter.append((CharSequence) DIRTY);
                this.journalWriter.append(' ');
                this.journalWriter.append((CharSequence) str);
                this.journalWriter.append('\n');
                this.journalWriter.flush();
            }
        }
        return editor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String inputStreamToString(InputStream inputStream) throws IOException {
        return Util.readFully(new InputStreamReader(inputStream, Util.UTF_8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean journalRebuildRequired() {
        return this.redundantOpCount >= 2000 && this.redundantOpCount >= this.lruEntries.size();
    }

    public static DiskLruCache open(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        File file2 = new File(file, JOURNAL_FILE_BACKUP);
        if (file2.exists()) {
            File file3 = new File(file, JOURNAL_FILE);
            if (file3.exists()) {
                file2.delete();
            } else {
                renameTo(file2, file3, false);
            }
        }
        DiskLruCache diskLruCache = new DiskLruCache(file, i, i2, j);
        if (diskLruCache.journalFile.exists()) {
            try {
                diskLruCache.readJournal();
                diskLruCache.processJournal();
                return diskLruCache;
            } catch (IOException e) {
                System.out.println("DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                diskLruCache.delete();
            }
        }
        file.mkdirs();
        DiskLruCache diskLruCache2 = new DiskLruCache(file, i, i2, j);
        diskLruCache2.rebuildJournal();
        return diskLruCache2;
    }

    private void processJournal() throws IOException {
        deleteIfExists(this.journalFileTmp);
        Iterator<Entry> it = this.lruEntries.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            if (next.currentEditor == null) {
                for (int i = 0; i < this.valueCount; i++) {
                    this.size += next.lengths[i];
                }
            } else {
                next.currentEditor = null;
                for (int i2 = 0; i2 < this.valueCount; i2++) {
                    deleteIfExists(next.getCleanFile(i2));
                    deleteIfExists(next.getDirtyFile(i2));
                }
                it.remove();
            }
        }
    }

    private void readJournal() throws IOException {
        StrictLineReader strictLineReader = new StrictLineReader(new FileInputStream(this.journalFile), Util.US_ASCII);
        try {
            String line = strictLineReader.readLine();
            String line2 = strictLineReader.readLine();
            String line3 = strictLineReader.readLine();
            String line4 = strictLineReader.readLine();
            String line5 = strictLineReader.readLine();
            if (!MAGIC.equals(line) || !"1".equals(line2) || !Integer.toString(this.appVersion).equals(line3) || !Integer.toString(this.valueCount).equals(line4) || !"".equals(line5)) {
                throw new IOException("unexpected journal header: [" + line + ", " + line2 + ", " + line4 + ", " + line5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    readJournalLine(strictLineReader.readLine());
                    i++;
                } catch (EOFException e) {
                    this.redundantOpCount = i - this.lruEntries.size();
                    if (strictLineReader.hasUnterminatedLine()) {
                        rebuildJournal();
                    } else {
                        this.journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFile, true), Util.US_ASCII));
                    }
                    Util.closeQuietly(strictLineReader);
                    return;
                }
            }
        } catch (Throwable th) {
            Util.closeQuietly(strictLineReader);
            throw th;
        }
    }

    private void readJournalLine(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i);
        if (iIndexOf2 == -1) {
            strSubstring = str.substring(i);
            if (iIndexOf == REMOVE.length() && str.startsWith(REMOVE)) {
                this.lruEntries.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i, iIndexOf2);
        }
        Entry entry = this.lruEntries.get(strSubstring);
        if (entry == null) {
            entry = new Entry(strSubstring);
            this.lruEntries.put(strSubstring, entry);
        }
        if (iIndexOf2 != -1 && iIndexOf == CLEAN.length() && str.startsWith(CLEAN)) {
            String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
            entry.readable = true;
            entry.currentEditor = null;
            entry.setLengths(strArrSplit);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == DIRTY.length() && str.startsWith(DIRTY)) {
            entry.currentEditor = new Editor(entry);
        } else if (iIndexOf2 != -1 || iIndexOf != READ.length() || !str.startsWith(READ)) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void rebuildJournal() throws IOException {
        if (this.journalWriter != null) {
            this.journalWriter.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFileTmp), Util.US_ASCII));
        try {
            bufferedWriter.write(MAGIC);
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.appVersion));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.valueCount));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (Entry entry : this.lruEntries.values()) {
                if (entry.currentEditor != null) {
                    bufferedWriter.write("DIRTY " + entry.key + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + entry.key + entry.getLengths() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.journalFile.exists()) {
                renameTo(this.journalFile, this.journalFileBackup, true);
            }
            renameTo(this.journalFileTmp, this.journalFile, false);
            this.journalFileBackup.delete();
            this.journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFile, true), Util.US_ASCII));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    private static void renameTo(File file, File file2, boolean z) throws IOException {
        if (z) {
            deleteIfExists(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            remove(this.lruEntries.entrySet().iterator().next().getKey());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.journalWriter != null) {
            for (Entry entry : new ArrayList(this.lruEntries.values())) {
                if (entry.currentEditor != null) {
                    entry.currentEditor.abort();
                }
            }
            trimToSize();
            this.journalWriter.close();
            this.journalWriter = null;
        }
    }

    public void delete() throws IOException {
        close();
        Util.deleteContents(this.directory);
    }

    public Editor edit(String str) throws IOException {
        return edit(str, -1L);
    }

    public synchronized void flush() throws IOException {
        checkNotClosed();
        trimToSize();
        this.journalWriter.flush();
    }

    public synchronized Value get(String str) throws IOException {
        Value value = null;
        synchronized (this) {
            checkNotClosed();
            Entry entry = this.lruEntries.get(str);
            if (entry != null && entry.readable) {
                for (File file : entry.cleanFiles) {
                    if (file.exists()) {
                    }
                }
                this.redundantOpCount++;
                this.journalWriter.append((CharSequence) READ);
                this.journalWriter.append(' ');
                this.journalWriter.append((CharSequence) str);
                this.journalWriter.append('\n');
                if (journalRebuildRequired()) {
                    this.executorService.submit(this.cleanupCallable);
                }
                value = new Value(str, entry.sequenceNumber, entry.cleanFiles, entry.lengths);
            }
        }
        return value;
    }

    public File getDirectory() {
        return this.directory;
    }

    public synchronized long getMaxSize() {
        return this.maxSize;
    }

    public synchronized boolean isClosed() {
        return this.journalWriter == null;
    }

    public synchronized boolean remove(String str) throws IOException {
        boolean z;
        checkNotClosed();
        Entry entry = this.lruEntries.get(str);
        if (entry == null || entry.currentEditor != null) {
            z = false;
        } else {
            for (int i = 0; i < this.valueCount; i++) {
                File cleanFile = entry.getCleanFile(i);
                if (cleanFile.exists() && !cleanFile.delete()) {
                    throw new IOException("failed to delete " + cleanFile);
                }
                this.size -= entry.lengths[i];
                entry.lengths[i] = 0;
            }
            this.redundantOpCount++;
            this.journalWriter.append((CharSequence) REMOVE);
            this.journalWriter.append(' ');
            this.journalWriter.append((CharSequence) str);
            this.journalWriter.append('\n');
            this.lruEntries.remove(str);
            if (journalRebuildRequired()) {
                this.executorService.submit(this.cleanupCallable);
            }
            z = true;
        }
        return z;
    }

    public synchronized void setMaxSize(long j) {
        this.maxSize = j;
        this.executorService.submit(this.cleanupCallable);
    }

    public synchronized long size() {
        return this.size;
    }
}
