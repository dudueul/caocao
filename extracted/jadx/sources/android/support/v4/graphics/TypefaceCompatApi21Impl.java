package android.support.v4.graphics;

import android.content.Context;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.provider.FontsContractCompat;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@RequiresApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    TypefaceCompatApi21Impl() {
    }

    private File getFile(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String str = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat(str).st_mode)) {
                return new File(str);
            }
            return null;
        } catch (ErrnoException e) {
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x004e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:47:0x006c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:52:0x0077 A[Catch: Throwable -> 0x0048, all -> 0x005a, TryCatch #7 {all -> 0x005a, blocks: (B:8:0x0019, B:10:0x001f, B:53:0x007b, B:12:0x0025, B:17:0x0037, B:35:0x0056, B:23:0x0044, B:48:0x006e, B:52:0x0077, B:51:0x0073, B:49:0x0071), top: B:80:0x0019 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x0097 A[Catch: IOException -> 0x0054, TRY_LEAVE, TryCatch #1 {IOException -> 0x0054, blocks: (B:6:0x000e, B:56:0x0083, B:60:0x008e, B:59:0x008a, B:20:0x003e, B:41:0x0063, B:40:0x005f, B:31:0x0050, B:63:0x0097, B:62:0x0093, B:32:0x0053), top: B:69:0x000e, inners: #0, #3, #10 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x0050 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:74:0x006e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // android.support.v4.graphics.TypefaceCompatBaseImpl, android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fontInfoArr, int i) throws Throwable {
        Throwable th;
        if (fontInfoArr.length < 1) {
            return null;
        }
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(findBestInfo(fontInfoArr, i).getUri(), "r", cancellationSignal);
            Throwable th2 = null;
            try {
                try {
                    File file = getFile(parcelFileDescriptorOpenFileDescriptor);
                    if (file != null && file.canRead()) {
                        Typeface typefaceCreateFromFile = Typeface.createFromFile(file);
                        if (parcelFileDescriptorOpenFileDescriptor != null) {
                            if (0 != 0) {
                                try {
                                    parcelFileDescriptorOpenFileDescriptor.close();
                                } catch (Throwable th3) {
                                    th2.addSuppressed(th3);
                                }
                            } else {
                                parcelFileDescriptorOpenFileDescriptor.close();
                            }
                        }
                        return typefaceCreateFromFile;
                    }
                    FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                    Throwable th4 = null;
                    try {
                        Typeface typefaceCreateFromInputStream = super.createFromInputStream(context, fileInputStream);
                        if (fileInputStream != null) {
                            if (0 != 0) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable th5) {
                                    th4.addSuppressed(th5);
                                }
                            } else {
                                fileInputStream.close();
                            }
                        }
                        if (parcelFileDescriptorOpenFileDescriptor != null) {
                            if (0 != 0) {
                                try {
                                    parcelFileDescriptorOpenFileDescriptor.close();
                                } catch (Throwable th6) {
                                    th2.addSuppressed(th6);
                                }
                            } else {
                                parcelFileDescriptorOpenFileDescriptor.close();
                            }
                        }
                        return typefaceCreateFromInputStream;
                    } catch (Throwable th7) {
                        th = th7;
                        try {
                            throw th;
                        } catch (Throwable th8) {
                            th = th8;
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
                } catch (Throwable th9) {
                    th = th9;
                    th = null;
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        if (th != null) {
                            try {
                                parcelFileDescriptorOpenFileDescriptor.close();
                            } catch (Throwable th10) {
                                th.addSuppressed(th10);
                            }
                        } else {
                            parcelFileDescriptorOpenFileDescriptor.close();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th11) {
                try {
                    throw th11;
                } catch (Throwable th12) {
                    th = th12;
                    th = th11;
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        if (th != null) {
                            parcelFileDescriptorOpenFileDescriptor.close();
                        } else {
                            parcelFileDescriptorOpenFileDescriptor.close();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }
}
