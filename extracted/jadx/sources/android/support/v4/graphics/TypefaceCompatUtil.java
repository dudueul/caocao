package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    private TypefaceCompatUtil() {
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    @RequiresApi(19)
    @Nullable
    public static ByteBuffer copyToDirectBuffer(Context context, Resources resources, int i) {
        ByteBuffer byteBufferMmap = null;
        File tempFile = getTempFile(context);
        if (tempFile != null) {
            try {
                if (copyToFile(tempFile, resources, i)) {
                    byteBufferMmap = mmap(tempFile);
                }
            } finally {
                tempFile.delete();
            }
        }
        return byteBufferMmap;
    }

    public static boolean copyToFile(File file, Resources resources, int i) {
        InputStream inputStreamOpenRawResource = null;
        try {
            inputStreamOpenRawResource = resources.openRawResource(i);
            return copyToFile(file, inputStreamOpenRawResource);
        } finally {
            closeQuietly(inputStreamOpenRawResource);
        }
    }

    public static boolean copyToFile(File file, InputStream inputStream) throws Throwable {
        boolean z = false;
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file, false);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = inputStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        fileOutputStream2.write(bArr, 0, i);
                    }
                    z = true;
                    closeQuietly(fileOutputStream2);
                    fileOutputStream = fileOutputStream2;
                } catch (IOException e) {
                    e = e;
                    fileOutputStream = fileOutputStream2;
                    Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
                    closeQuietly(fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
        return z;
    }

    @Nullable
    public static File getTempFile(Context context) {
        String str = CACHE_FILE_PREFIX + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i = 0; i < 100; i++) {
            File file = new File(context.getCacheDir(), str + i);
            try {
                if (file.createNewFile()) {
                    return file;
                }
            } catch (IOException e) {
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0040 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:41:0x0060 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:46:0x006b A[Catch: Throwable -> 0x003a, all -> 0x004d, TRY_LEAVE, TryCatch #1 {Throwable -> 0x003a, blocks: (B:5:0x000c, B:28:0x0049, B:16:0x0036, B:46:0x006b, B:45:0x0067, B:43:0x0065), top: B:54:0x000c }] */
    /* JADX WARN: Code duplicated, block: B:50:0x0074 A[Catch: IOException -> 0x0046, TRY_LEAVE, TryCatch #3 {IOException -> 0x0046, blocks: (B:3:0x0005, B:13:0x0031, B:34:0x0056, B:33:0x0052, B:24:0x0042, B:50:0x0074, B:49:0x0070, B:25:0x0045), top: B:57:0x0005, inners: #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:55:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x0062 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @RequiresApi(19)
    @Nullable
    public static ByteBuffer mmap(Context context, CancellationSignal cancellationSignal, Uri uri) throws Throwable {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r", cancellationSignal);
            Throwable th5 = null;
            try {
                try {
                    FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                    Throwable th6 = null;
                    try {
                        FileChannel channel = fileInputStream.getChannel();
                        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                        if (fileInputStream != null) {
                            if (0 != 0) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable th7) {
                                    th6.addSuppressed(th7);
                                }
                            } else {
                                fileInputStream.close();
                            }
                        }
                        if (parcelFileDescriptorOpenFileDescriptor == null) {
                            return map;
                        }
                        if (0 == 0) {
                            parcelFileDescriptorOpenFileDescriptor.close();
                            return map;
                        }
                        try {
                            parcelFileDescriptorOpenFileDescriptor.close();
                            return map;
                        } catch (Throwable th8) {
                            th5.addSuppressed(th8);
                            return map;
                        }
                    } catch (Throwable th9) {
                        th3 = th9;
                        th4 = null;
                        if (fileInputStream != null) {
                            if (th4 != null) {
                                fileInputStream.close();
                            } else {
                                fileInputStream.close();
                            }
                        }
                        throw th3;
                    }
                } catch (Throwable th10) {
                    try {
                        throw th10;
                    } catch (Throwable th11) {
                        th = th11;
                        th2 = th10;
                        if (parcelFileDescriptorOpenFileDescriptor != null) {
                            if (th2 != null) {
                                try {
                                    parcelFileDescriptorOpenFileDescriptor.close();
                                } catch (Throwable th12) {
                                    th2.addSuppressed(th12);
                                }
                            } else {
                                parcelFileDescriptorOpenFileDescriptor.close();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable th13) {
                th = th13;
                th2 = null;
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    if (th2 != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    } else {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    }
                }
                throw th;
            }
        } catch (IOException e) {
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0031 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:26:0x003c A[Catch: IOException -> 0x0024, TRY_LEAVE, TryCatch #3 {IOException -> 0x0024, blocks: (B:3:0x0001, B:8:0x001b, B:14:0x0027, B:11:0x0020, B:22:0x0033, B:26:0x003c, B:25:0x0038, B:23:0x0036), top: B:32:0x0001, inners: #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:33:0x0033 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @RequiresApi(19)
    @Nullable
    private static ByteBuffer mmap(File file) throws Throwable {
        Throwable th;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Throwable th2 = null;
            try {
                FileChannel channel = fileInputStream.getChannel();
                MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                if (fileInputStream == null) {
                    return map;
                }
                if (0 == 0) {
                    fileInputStream.close();
                    return map;
                }
                try {
                    fileInputStream.close();
                    return map;
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                    return map;
                }
            } catch (Throwable th4) {
                try {
                    throw th4;
                } catch (Throwable th5) {
                    th = th5;
                    th = th4;
                    if (fileInputStream != null) {
                        if (th != null) {
                            fileInputStream.close();
                        } else {
                            fileInputStream.close();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e) {
            return null;
        }
    }
}
