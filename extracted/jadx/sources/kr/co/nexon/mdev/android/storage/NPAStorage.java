package kr.co.nexon.mdev.android.storage;

import android.os.Environment;
import com.google.gson.Gson;
import com.nexon.core.log.ToyLog;
import com.nexon.platform.NXPFinalizer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPAStorage {
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: kr.co.nexon.mdev.android.storage.NPAStorage.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            NPAStorage unused = NPAStorage.instance = null;
        }
    };
    private static NPAStorage instance;
    private ConcurrentHashMap<String, String> datamap = null;
    private AtomicBoolean nowSaving = new AtomicBoolean(false);

    private NPAStorage() {
    }

    private boolean canWriteExternalStorage() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static NPAStorage getInstance() {
        if (instance == null) {
            synchronized (NPAStorage.class) {
                if (instance == null) {
                    instance = new NPAStorage();
                }
            }
        }
        return instance;
    }

    private String getNPADataFilePath() {
        return getNexonPlayDataDirPath() + File.separator + "NPA.dat";
    }

    private File getNPAFile() {
        File file = new File(getNPADataFilePath());
        if (!file.exists()) {
            makeNPADataFile(file);
        }
        File file2 = new File(getNPADataFilePath());
        if (file2.exists()) {
            return file2;
        }
        return null;
    }

    private String getNexonPlayDataDirPath() {
        return Environment.getExternalStorageDirectory().getPath() + File.separator + "NexonPlay";
    }

    private String getNexonPlayDataFilePath() {
        return getNexonPlayDataDirPath() + File.separator + "NexonPlay.dat";
    }

    private String loadNPADataFile() {
        File nPAFile = getNPAFile();
        if (nPAFile == null) {
            return null;
        }
        FileInputStream fileInputStream = null;
        byte[] bArr = new byte[65536];
        try {
            FileInputStream fileInputStream2 = new FileInputStream(nPAFile);
            try {
                int i = fileInputStream2.read(bArr);
                fileInputStream2.close();
                return i <= 0 ? "" : new String(bArr, 0, i);
            } catch (FileNotFoundException e) {
                e = e;
                e.printStackTrace();
                return null;
            } catch (IOException e2) {
                e = e2;
                fileInputStream = fileInputStream2;
                e.printStackTrace();
                try {
                    fileInputStream.close();
                    return null;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return null;
                }
            }
        } catch (FileNotFoundException e4) {
            e = e4;
        } catch (IOException e5) {
            e = e5;
        }
    }

    private void makeNPADataFile(File file) {
        if (canWriteExternalStorage()) {
            File file2 = new File(getNexonPlayDataDirPath());
            if (!file2.exists() && !file2.mkdirs()) {
                ToyLog.d("failed mkdir");
                return;
            }
            try {
                if (!file.createNewFile()) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveNPADataFile(String str) {
        File nPAFile = getNPAFile();
        if (nPAFile == null) {
            return;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(nPAFile);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void storeGUID(String str) {
        if (canWriteExternalStorage()) {
            File file = new File(getNexonPlayDataFilePath());
            if (file.exists()) {
                return;
            }
            File file2 = new File(getNexonPlayDataDirPath());
            if (!file2.exists() && !file2.mkdirs()) {
                ToyLog.d("failed mkdir");
                return;
            }
            try {
                if (file.createNewFile() && file.exists() && file.canWrite()) {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(str.getBytes());
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    public String getGUID() {
        if (!new File(getNexonPlayDataFilePath()).exists()) {
            storeGUID(UUID.randomUUID().toString());
        }
        File file = new File(getNexonPlayDataFilePath());
        if (!file.exists()) {
            return "";
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            int i = fileInputStream.read(bArr);
            fileInputStream.close();
            return i < 1 ? "" : new String(bArr, 0, i);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String getValue(String str) throws NPAStorageNotLoadException {
        if (this.datamap == null) {
            throw new NPAStorageNotLoadException();
        }
        return this.datamap.get(str);
    }

    public boolean hasFileldfjwoj() {
        return new File(getNexonPlayDataDirPath() + File.separator + "ldfjwoj").exists();
    }

    public void save() throws NPAStorageNotLoadException {
        if (this.datamap == null) {
            throw new NPAStorageNotLoadException();
        }
        if (this.nowSaving.get()) {
            return;
        }
        this.nowSaving.set(true);
        new Thread(new Runnable() { // from class: kr.co.nexon.mdev.android.storage.NPAStorage.2
            @Override // java.lang.Runnable
            public void run() {
                NPAStorage.this.saveNPADataFile(new Gson().toJson(new HashMap(NPAStorage.this.datamap)));
                NPAStorage.this.nowSaving.set(false);
            }
        }).start();
    }

    public void setValue(String str, String str2) throws NPAStorageNotLoadException {
        if (this.datamap == null) {
            throw new NPAStorageNotLoadException();
        }
        this.datamap.put(str, str2);
    }
}
