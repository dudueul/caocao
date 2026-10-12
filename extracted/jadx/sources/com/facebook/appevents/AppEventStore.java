package com.facebook.appevents;

import android.content.Context;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.Utility;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class AppEventStore {
    private static final String PERSISTED_EVENTS_FILENAME = "AppEventsLogger.persistedevents";
    private static final String TAG = AppEventStore.class.getName();

    private static class MovedClassObjectInputStream extends ObjectInputStream {
        private static final String ACCESS_TOKEN_APP_ID_PAIR_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1";
        private static final String APP_EVENT_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AppEvent$SerializationProxyV1";

        public MovedClassObjectInputStream(InputStream inputStream) throws IOException {
            super(inputStream);
        }

        @Override // java.io.ObjectInputStream
        protected ObjectStreamClass readClassDescriptor() throws ClassNotFoundException, IOException {
            ObjectStreamClass classDescriptor = super.readClassDescriptor();
            if (classDescriptor.getName().equals(ACCESS_TOKEN_APP_ID_PAIR_SERIALIZATION_PROXY_V1_CLASS_NAME)) {
                return ObjectStreamClass.lookup(AccessTokenAppIdPair.SerializationProxyV1.class);
            }
            return classDescriptor.getName().equals(APP_EVENT_SERIALIZATION_PROXY_V1_CLASS_NAME) ? ObjectStreamClass.lookup(AppEvent.SerializationProxyV1.class) : classDescriptor;
        }
    }

    AppEventStore() {
    }

    public static synchronized void persistEvents(AccessTokenAppIdPair accessTokenAppIdPair, SessionEventsState sessionEventsState) {
        AppEventUtility.assertIsNotMainThread();
        PersistedEvents andClearStore = readAndClearStore();
        if (andClearStore.containsKey(accessTokenAppIdPair)) {
            andClearStore.get(accessTokenAppIdPair).addAll(sessionEventsState.getEventsToPersist());
        } else {
            andClearStore.addEvents(accessTokenAppIdPair, sessionEventsState.getEventsToPersist());
        }
        saveEventsToDisk(andClearStore);
    }

    public static synchronized void persistEvents(AppEventCollection appEventCollection) {
        AppEventUtility.assertIsNotMainThread();
        PersistedEvents andClearStore = readAndClearStore();
        for (AccessTokenAppIdPair accessTokenAppIdPair : appEventCollection.keySet()) {
            andClearStore.addEvents(accessTokenAppIdPair, appEventCollection.get(accessTokenAppIdPair).getEventsToPersist());
        }
        saveEventsToDisk(andClearStore);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0033 A[Catch: all -> 0x005b, TRY_ENTER, TRY_LEAVE, TryCatch #7 {, blocks: (B:4:0x0003, B:7:0x0024, B:8:0x0027, B:11:0x0033, B:15:0x003b, B:34:0x007d, B:35:0x0080, B:36:0x0089, B:38:0x008b, B:28:0x0066, B:29:0x0069, B:32:0x0074, B:17:0x0045, B:18:0x0048, B:21:0x0053), top: B:50:0x0003, inners: #3, #4, #9, #10 }] */
    public static synchronized PersistedEvents readAndClearStore() {
        PersistedEvents persistedEvents;
        AppEventUtility.assertIsNotMainThread();
        MovedClassObjectInputStream movedClassObjectInputStream = null;
        persistedEvents = null;
        Context applicationContext = FacebookSdk.getApplicationContext();
        try {
            try {
                MovedClassObjectInputStream movedClassObjectInputStream2 = new MovedClassObjectInputStream(new BufferedInputStream(applicationContext.openFileInput(PERSISTED_EVENTS_FILENAME)));
                try {
                    persistedEvents = (PersistedEvents) movedClassObjectInputStream2.readObject();
                    Utility.closeQuietly(movedClassObjectInputStream2);
                    try {
                        applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                        movedClassObjectInputStream = movedClassObjectInputStream2;
                    } catch (Exception e) {
                        Log.w(TAG, "Got unexpected exception when removing events file: ", e);
                        movedClassObjectInputStream = movedClassObjectInputStream2;
                    }
                } catch (FileNotFoundException e2) {
                    movedClassObjectInputStream = movedClassObjectInputStream2;
                    Utility.closeQuietly(movedClassObjectInputStream);
                    try {
                        applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                    } catch (Exception e3) {
                        Log.w(TAG, "Got unexpected exception when removing events file: ", e3);
                    }
                    if (persistedEvents == null) {
                        persistedEvents = new PersistedEvents();
                    }
                    return persistedEvents;
                } catch (Exception e4) {
                    e = e4;
                    movedClassObjectInputStream = movedClassObjectInputStream2;
                    Log.w(TAG, "Got unexpected exception while reading events: ", e);
                    Utility.closeQuietly(movedClassObjectInputStream);
                    try {
                        applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                    } catch (Exception e5) {
                        Log.w(TAG, "Got unexpected exception when removing events file: ", e5);
                    }
                } catch (Throwable th) {
                    th = th;
                    movedClassObjectInputStream = movedClassObjectInputStream2;
                    Utility.closeQuietly(movedClassObjectInputStream);
                    try {
                        applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                    } catch (Exception e6) {
                        Log.w(TAG, "Got unexpected exception when removing events file: ", e6);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (FileNotFoundException e7) {
        } catch (Exception e8) {
            e = e8;
        }
        if (persistedEvents == null) {
            persistedEvents = new PersistedEvents();
        }
        return persistedEvents;
    }

    private static void saveEventsToDisk(PersistedEvents persistedEvents) throws Throwable {
        ObjectOutputStream objectOutputStream = null;
        Context applicationContext = FacebookSdk.getApplicationContext();
        try {
            try {
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new BufferedOutputStream(applicationContext.openFileOutput(PERSISTED_EVENTS_FILENAME, 0)));
                try {
                    objectOutputStream2.writeObject(persistedEvents);
                    Utility.closeQuietly(objectOutputStream2);
                } catch (Exception e) {
                    e = e;
                    objectOutputStream = objectOutputStream2;
                    Log.w(TAG, "Got unexpected exception while persisting events: ", e);
                    try {
                        applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                    } catch (Exception e2) {
                    }
                    Utility.closeQuietly(objectOutputStream);
                } catch (Throwable th) {
                    th = th;
                    objectOutputStream = objectOutputStream2;
                    Utility.closeQuietly(objectOutputStream);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
