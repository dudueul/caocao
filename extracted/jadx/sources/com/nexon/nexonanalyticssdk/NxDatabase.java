package com.nexon.nexonanalyticssdk;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxDatabase extends SQLiteOpenHelper {
    private static final String COLUMN_BASETIME = "base_time";
    private static final String COLUMN_CONTENTS = "contents";
    private static final String COLUMN_FUNNEL_ID = "funnel_id";
    private static final String COLUMN_FUNNEL_NAME = "funnel_name";
    private static final String COLUMN_IS_NXLOG_TYPE = "is_nxlog_type";
    private static final String COLUMN_LOGID = "log_id";
    private static final String COLUMN_LOG_KEY = "log_key";
    private static final String COLUMN_LOG_TYPE = "log_type";
    private static final String COLUMN_PASSINGTIME = "passing_time";
    private static final String COLUMN_SUMMARY_BEGIN_TIME = "summary_begin_time";
    private static final String COLUMN_SUMMARY_TYPE = "summary_type";
    private static final String COLUMN_TIMESYNC = "time_sync";
    public static final String DATABASE_NAME = "nxlog";
    private static final int DATABASE_VERSION = 3;
    public static final int IS_NOT_NXLOG = 0;
    public static final int IS_NXLOG = 1;
    private static final String TABLE_NAME_FUNNELS = "funnels";
    private static final String TABLE_NAME_LOG = "log";
    private static final String TABLE_NAME_LOG_COMMON_INFO = "log_common_info";
    private static final String TABLE_NAME_SUMMARY_INFO = "summary_info";
    private static final String TABLE_NAME_SUMMARY_SUB_INFO = "summary_sub_info";
    private static NxDatabase database;
    private final String QUERY_CREATE_FUNNELS_TABLE;
    private final String QUERY_CREATE_LOG_COMMON_INFO_TABLE;
    private final String QUERY_CREATE_LOG_TABLE;
    private final String QUERY_CREATE_SUMMARY_INFO_TABLE;
    private final String QUERY_CREATE_SUMMARY_SUB_INFO_TABLE;
    private final String QUERY_DELETE_BULK_LOG_IN_LOGID;
    private final String QUERY_DELETE_LOG_COMMON_WHERE_LOG_KEY;
    private final String QUERY_DELETE_LOG_WHERE_SELECTED_LOGID;
    private final String QUERY_DELETE_SUMMARY_INFO;
    private final String QUERY_INSERT_BULK_SUMMARY_INFO;
    private final String QUERY_INSERT_COMMON_BASE_TIME_VALUE;
    private final String QUERY_INSERT_FUNNEL_NAME;
    private final String QUERY_INSERT_LOG;
    private final String QUERY_INSERT_SUMMARY_BEGIN_TIME;
    private final String QUERY_INSERT_SUMMARY_INFO;
    private final String QUERY_PRAGMA_JOURNA;
    private final String QUERY_PRAGMA_SYNC;
    private final String QUERY_SELECT_BASETIME;
    private final String QUERY_SELECT_COUNT;
    private final String QUERY_SELECT_FUNNEL_NAMES;
    private final String QUERY_SELECT_LAST_INSERT_ROWID;
    private final String QUERY_SELECT_LOG;
    private final String QUERY_SELECT_LOG_KEY;
    private final String QUERY_SELECT_LOG_TO_DELETE;
    private final String QUERY_SELECT_SUMMARY_BEGIN_TIME;
    private final String QUERY_SELECT_SUMMARY_INFO;
    private final String QUERY_UPDATE_BASETIME_TIMESYNC;
    private final String QUERY_UPDATE_SUMMARY_BEGIN_TIME;
    private ArrayList<String> loadedNxLogType;
    private StringBuilder logContentBuilder;
    private StringBuilder logIdCounterBuilder;
    private int storageLimitCount;

    interface JournalMode {
        public static final String OFF = "OFF";
        public static final String PERSIST = "PERSIST";
        public static final String TRUNCATE = "TRUNCATE";
        public static final String WAL = "WAL";
    }

    interface SyncLevel {
        public static final int EXTRA = 3;
        public static final int FULL = 2;
        public static final int NORMAL = 1;
        public static final int OFF = 0;
    }

    private NxDatabase(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 3);
        this.QUERY_PRAGMA_SYNC = "PRAGMA synchronous=";
        this.QUERY_PRAGMA_JOURNA = "PRAGMA journal_mode=";
        this.QUERY_CREATE_LOG_TABLE = "CREATE TABLE log(log_id INTEGER PRIMARY KEY AUTOINCREMENT, contents TEXT, passing_time INTEGER, log_key INTEGER, log_type TEXT, is_nxlog_type INTEGER, FOREIGN KEY(log_key) REFERENCES log_common_info (log_key));";
        this.QUERY_CREATE_LOG_COMMON_INFO_TABLE = "CREATE TABLE log_common_info(log_key INTEGER PRIMARY KEY AUTOINCREMENT, base_time INTEGER, time_sync INTEGER DEFAULT 0)";
        this.QUERY_CREATE_SUMMARY_INFO_TABLE = "CREATE TABLE summary_info(log_type TEXT, summary_type INTEGER );";
        this.QUERY_CREATE_SUMMARY_SUB_INFO_TABLE = "CREATE TABLE summary_sub_info(summary_begin_time INTEGER );";
        this.QUERY_CREATE_FUNNELS_TABLE = "CREATE TABLE funnels ( funnel_id INTEGER PRIMARY KEY AUTOINCREMENT, funnel_name TEXT  );";
        this.QUERY_SELECT_SUMMARY_INFO = "SELECT log_type, summary_type FROM summary_info";
        this.QUERY_INSERT_SUMMARY_INFO = "INSERT INTO summary_info ( log_type, summary_type ) VALUES ( '%s', %d )";
        this.QUERY_INSERT_BULK_SUMMARY_INFO = "INSERT INTO summary_info VALUES %s;";
        this.QUERY_SELECT_LOG_KEY = "SELECT log_key FROM log_common_info ORDER BY log_key ASC LIMIT 1;";
        this.QUERY_SELECT_SUMMARY_BEGIN_TIME = "SELECT summary_begin_time FROM summary_sub_info WHERE ROWID = 1";
        this.QUERY_SELECT_FUNNEL_NAMES = "SELECT funnel_name FROM funnels";
        this.QUERY_INSERT_SUMMARY_BEGIN_TIME = "INSERT INTO summary_sub_info ( summary_begin_time ) VALUES ( %d )";
        this.QUERY_INSERT_FUNNEL_NAME = "INSERT INTO funnels ( funnel_name ) VALUES ( '%s' )";
        this.QUERY_UPDATE_SUMMARY_BEGIN_TIME = "UPDATE summary_sub_info SET summary_begin_time = %d WHERE rowid = 1";
        this.QUERY_DELETE_SUMMARY_INFO = "DELETE FROM summary_info";
        this.QUERY_SELECT_COUNT = "SELECT COUNT(log_id) FROM log";
        this.QUERY_INSERT_COMMON_BASE_TIME_VALUE = "INSERT INTO log_common_info ( base_time) VALUES ( %d );";
        this.QUERY_SELECT_LAST_INSERT_ROWID = "SELECT LAST_INSERT_ROWID();";
        this.QUERY_INSERT_LOG = "INSERT INTO log ( contents, passing_time, log_key, log_type, is_nxlog_type) VALUES ( '%s', %d, %d, '%s', %d );";
        this.QUERY_SELECT_LOG_TO_DELETE = "SELECT log_id, log_type, is_nxlog_type FROM log LIMIT %d;";
        this.QUERY_SELECT_LOG = "SELECT log.log_id, log.contents, log.passing_time, log.log_type, log.is_nxlog_type, log_common_info.base_time, log_common_info.time_sync FROM log INNER JOIN log_common_info ON log.log_key = log_common_info.log_key WHERE log_common_info.log_key = %d ORDER BY log.log_id ASC LIMIT %d ;";
        this.QUERY_SELECT_BASETIME = "SELECT base_time FROM log_common_info WHERE log_key = %d ";
        this.QUERY_UPDATE_BASETIME_TIMESYNC = "UPDATE log_common_info SET base_time = %d, time_sync = %d  WHERE log_key = %d;";
        this.QUERY_DELETE_LOG_COMMON_WHERE_LOG_KEY = "log_key = %d";
        this.QUERY_DELETE_BULK_LOG_IN_LOGID = "log_id IN ( %s );";
        this.QUERY_DELETE_LOG_WHERE_SELECTED_LOGID = "log_id IN ( %s )";
        this.storageLimitCount = 1000;
        this.logIdCounterBuilder = new StringBuilder(2048);
        this.logContentBuilder = new StringBuilder(40960);
        this.loadedNxLogType = new ArrayList<>();
    }

    public static void create(Context context) {
        database = new NxDatabase(context);
    }

    static NxDatabase getInstance() {
        if (database != null) {
            return database;
        }
        NxLogcat.w("You must create Nxdatabase!");
        return null;
    }

    public void addFunnelDB(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE funnels ( funnel_id INTEGER PRIMARY KEY AUTOINCREMENT, funnel_name TEXT  );");
    }

    public void addSummaryDB(SQLiteDatabase sQLiteDatabase) {
        String string;
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name ='summary_info'", null);
        try {
            if (cursorRawQuery.moveToFirst() && (string = cursorRawQuery.getString(0)) != null && string.equals(TABLE_NAME_SUMMARY_INFO)) {
                cursorRawQuery.close();
                return;
            }
            cursorRawQuery.close();
            sQLiteDatabase.execSQL("ALTER TABLE log ADD COLUMN log_type TEXT ");
            sQLiteDatabase.execSQL("ALTER TABLE log ADD COLUMN is_nxlog_type INTEGER ");
            sQLiteDatabase.execSQL("CREATE TABLE summary_info(log_type TEXT, summary_type INTEGER );");
            sQLiteDatabase.execSQL("CREATE TABLE summary_sub_info(summary_begin_time INTEGER );");
        } catch (Throwable th) {
            cursorRawQuery.close();
            throw th;
        }
    }

    public void checkAndSetSummary(Map<String, Integer> map, Map<String, Object> map2, String str, int i) {
        if (map.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : map.keySet()) {
            int iIntValue = map.get(str2).intValue();
            HashMap map3 = new HashMap();
            map3.put(NxLogSummaryInfos.KEY_TYPENAME, str2);
            map3.put(NxLogSummaryInfos.KEY_COUNT, Integer.valueOf(iIntValue));
            arrayList.add(map3);
        }
        HashMap map4 = new HashMap();
        map4.put(NxLogSummaryInfos.KEY_TYPENAME, NxLogSummaryInfos.KEY_TOTAL);
        map4.put(NxLogSummaryInfos.KEY_COUNT, Integer.valueOf(i));
        arrayList.add(map4);
        map2.put(str, arrayList);
    }

    public long deleteLog(String str) {
        return getWritableDatabase().delete("log", String.format("log_id IN ( %s )", str), null);
    }

    public long deleteLog(List<Object[]> list) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        Iterator<Object[]> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next()[0]).append(",");
        }
        sb.setCharAt(sb.length() - 1, ' ');
        return writableDatabase.delete("log", String.format("log_id IN ( %s );", sb.toString()), null);
    }

    public boolean deleteLogKey(long j) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        NxLogcat.d("delete logKey Number : " + j);
        return writableDatabase.delete(TABLE_NAME_LOG_COMMON_INFO, String.format("log_key = %d", Long.valueOf(j)), null) > 0;
    }

    public long getBaseTime(long j) {
        Cursor cursorRawQuery = getReadableDatabase().rawQuery(String.format("SELECT base_time FROM log_common_info WHERE log_key = %d ", Long.valueOf(j)), null);
        try {
            cursorRawQuery.moveToFirst();
            return cursorRawQuery.getLong(0);
        } finally {
            cursorRawQuery.close();
        }
    }

    public String getBulkLog() {
        String string = this.logContentBuilder.toString();
        this.logContentBuilder.setLength(0);
        return string;
    }

    public List<String> getBulkNxLogType() {
        return this.loadedNxLogType;
    }

    public long getLatestLogKey() {
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT LAST_INSERT_ROWID();", null);
        try {
            cursorRawQuery.moveToFirst();
            return cursorRawQuery.getLong(0);
        } finally {
            cursorRawQuery.close();
        }
    }

    public int getLogCount() {
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT COUNT(log_id) FROM log", null);
        cursorRawQuery.moveToFirst();
        int i = cursorRawQuery.getInt(0);
        cursorRawQuery.close();
        return i;
    }

    public String getLogIds() {
        String string = this.logIdCounterBuilder.toString();
        this.logIdCounterBuilder.setLength(0);
        return string;
    }

    public long getOldestLogKey() {
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT log_key FROM log_common_info ORDER BY log_key ASC LIMIT 1;", null);
        try {
            cursorRawQuery.moveToFirst();
            if (cursorRawQuery.isAfterLast()) {
                return 0L;
            }
            return cursorRawQuery.getInt(0);
        } finally {
            cursorRawQuery.close();
        }
    }

    public int getStorageLimitCount() {
        return this.storageLimitCount;
    }

    public long getSummaryBeginTime() {
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT summary_begin_time FROM summary_sub_info WHERE ROWID = 1", null);
        try {
            cursorRawQuery.moveToFirst();
            return cursorRawQuery.isAfterLast() ? 0L : cursorRawQuery.getLong(0);
        } finally {
            cursorRawQuery.close();
        }
    }

    public void insertBaseTime(long j) {
        try {
            getWritableDatabase().execSQL(String.format("INSERT INTO log_common_info ( base_time) VALUES ( %d );", Long.valueOf(j)));
        } catch (SQLException e) {
            NxLogcat.e("insertBaseTime : " + e.getMessage());
        }
    }

    public <T> void insertBulkSummaryInfo(List<T> list, int i) {
        if (list.size() == 0) {
            return;
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (T t : list) {
            String str = null;
            if (t instanceof Object[]) {
                Object[] objArr = (Object[]) t;
                if (((Integer) objArr[2]).intValue() == 1) {
                    i2++;
                    str = (String) objArr[1];
                }
            } else if (t instanceof String) {
                str = (String) t;
            }
            sb.append("('").append(str).append("',").append(i).append("),");
        }
        Object obj = null;
        if ((obj instanceof Object[]) && i2 == 0) {
            return;
        }
        sb.setCharAt(sb.length() - 1, ' ');
        try {
            writableDatabase.execSQL(String.format("INSERT INTO summary_info VALUES %s;", sb.toString()));
        } catch (SQLException e) {
            NxLogcat.e("insertBulkSummaryInfo : " + e.getMessage());
        }
    }

    public boolean insertFunnelName(String str) {
        try {
            getWritableDatabase().execSQL(String.format("INSERT INTO funnels ( funnel_name ) VALUES ( '%s' )", str));
            return true;
        } catch (SQLException e) {
            NxLogcat.e("insert FunnelName : " + e.getMessage());
            return false;
        }
    }

    public synchronized void insertLog(String str, long j, long j2, String str2, boolean z) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Object[] objArr = new Object[5];
        objArr[0] = str;
        objArr[1] = Long.valueOf(j2);
        objArr[2] = Long.valueOf(j);
        objArr[3] = str2;
        objArr[4] = Integer.valueOf(z ? 1 : 0);
        try {
            writableDatabase.execSQL(String.format("INSERT INTO log ( contents, passing_time, log_key, log_type, is_nxlog_type) VALUES ( '%s', %d, %d, '%s', %d );", objArr));
        } catch (SQLException e) {
            NxLogcat.e("insertLog : " + e.getMessage());
        }
    }

    public void insertSummaryBeginTime(long j) {
        try {
            getWritableDatabase().execSQL(String.format("INSERT INTO summary_sub_info ( summary_begin_time ) VALUES ( %d )", Long.valueOf(j)));
        } catch (SQLException e) {
            NxLogcat.e("insertSummaryBeginTime : " + e.getMessage());
        }
    }

    public void insertSummaryInfo(String str, int i) {
        try {
            getWritableDatabase().execSQL(String.format("INSERT INTO summary_info ( log_type, summary_type ) VALUES ( '%s', %d )", str, Integer.valueOf(i)));
        } catch (SQLException e) {
            NxLogcat.e("insertSummaryInfo : " + e.getMessage());
        }
    }

    public void loadFunnelNames() {
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT funnel_name FROM funnels", null);
        try {
            cursorRawQuery.moveToFirst();
            while (!cursorRawQuery.isAfterLast()) {
                NxLogInfo.checkAndSetFunnelName(cursorRawQuery.getString(0));
                cursorRawQuery.moveToNext();
            }
            cursorRawQuery.close();
        } catch (Throwable th) {
            cursorRawQuery.close();
            throw th;
        }
    }

    public int loadNxLogInfo(long j, int i) {
        Cursor cursorRawQuery = getReadableDatabase().rawQuery(String.format("SELECT log.log_id, log.contents, log.passing_time, log.log_type, log.is_nxlog_type, log_common_info.base_time, log_common_info.time_sync FROM log INNER JOIN log_common_info ON log.log_key = log_common_info.log_key WHERE log_common_info.log_key = %d ORDER BY log.log_id ASC LIMIT %d ;", Long.valueOf(j), Integer.valueOf(i)), null);
        int count = cursorRawQuery.getCount();
        try {
            cursorRawQuery.moveToFirst();
            if (count == 0) {
                return 0;
            }
            this.loadedNxLogType.clear();
            this.logIdCounterBuilder.setLength(0);
            this.logContentBuilder.append("[");
            while (!cursorRawQuery.isAfterLast()) {
                long j2 = cursorRawQuery.getLong(0);
                String string = cursorRawQuery.getString(1);
                long j3 = cursorRawQuery.getLong(2);
                String string2 = cursorRawQuery.getString(3);
                int i2 = cursorRawQuery.getInt(4);
                long j4 = cursorRawQuery.getLong(5);
                int i3 = cursorRawQuery.getInt(6);
                int length = this.logContentBuilder.length();
                this.logContentBuilder.append(string);
                this.logContentBuilder = NxUtils.completeLog(this.logContentBuilder, length, j4 + j3, i3);
                this.logContentBuilder.append(",");
                this.logIdCounterBuilder.append(j2 + ",");
                if (i2 == 1) {
                    this.loadedNxLogType.add(string2);
                }
                cursorRawQuery.moveToNext();
            }
            this.logIdCounterBuilder.setCharAt(this.logIdCounterBuilder.length() - 1, ' ');
            this.logContentBuilder.setCharAt(this.logContentBuilder.length() - 1, ' ');
            this.logContentBuilder.append("]");
            return count;
        } finally {
            cursorRawQuery.close();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onConfigure(SQLiteDatabase sQLiteDatabase) {
        setDatabaseMode(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE log_common_info(log_key INTEGER PRIMARY KEY AUTOINCREMENT, base_time INTEGER, time_sync INTEGER DEFAULT 0)");
            sQLiteDatabase.execSQL("CREATE TABLE log(log_id INTEGER PRIMARY KEY AUTOINCREMENT, contents TEXT, passing_time INTEGER, log_key INTEGER, log_type TEXT, is_nxlog_type INTEGER, FOREIGN KEY(log_key) REFERENCES log_common_info (log_key));");
            sQLiteDatabase.execSQL("CREATE TABLE summary_info(log_type TEXT, summary_type INTEGER );");
            sQLiteDatabase.execSQL("CREATE TABLE summary_sub_info(summary_begin_time INTEGER );");
            sQLiteDatabase.execSQL("CREATE TABLE funnels ( funnel_id INTEGER PRIMARY KEY AUTOINCREMENT, funnel_name TEXT  );");
        } catch (SQLException e) {
            NxLogcat.e("NxDatabase onCreate : " + e.getMessage());
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        NxLogcat.d("Old DB Version : " + i + ", Current DB version : 3");
        if (i < 3) {
            switch (i) {
                case 1:
                    addSummaryDB(sQLiteDatabase);
                    addFunnelDB(sQLiteDatabase);
                    break;
                case 2:
                    addFunnelDB(sQLiteDatabase);
                    break;
            }
        }
    }

    public Map<String, Object> popSummaryInfo() {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        HashMap map4 = new HashMap();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        Cursor cursorRawQuery = null;
        try {
            writableDatabase.beginTransaction();
            cursorRawQuery = writableDatabase.rawQuery("SELECT log_type, summary_type FROM summary_info", null);
            cursorRawQuery.moveToFirst();
            if (cursorRawQuery.isAfterLast()) {
                return null;
            }
            while (!cursorRawQuery.isAfterLast()) {
                String string = cursorRawQuery.getString(0);
                switch (cursorRawQuery.getInt(1)) {
                    case 1:
                        map.put(string, Integer.valueOf((map.containsKey(string) ? map.get(string).intValue() : 0) + 1));
                        i++;
                        break;
                    case 2:
                        map2.put(string, Integer.valueOf((map2.containsKey(string) ? map2.get(string).intValue() : 0) + 1));
                        i2++;
                        break;
                    case 3:
                        map3.put(string, Integer.valueOf((map3.containsKey(string) ? map3.get(string).intValue() : 0) + 1));
                        i3++;
                        break;
                    case 4:
                        map4.put(string, Integer.valueOf((map4.containsKey(string) ? map4.get(string).intValue() : 0) + 1));
                        i4++;
                        break;
                }
                cursorRawQuery.moveToNext();
            }
            writableDatabase.execSQL("DELETE FROM summary_info");
            HashMap map5 = new HashMap();
            checkAndSetSummary(map, map5, NxLogSummaryInfos.KEY_FAILED_MESSAGES, i);
            checkAndSetSummary(map2, map5, NxLogSummaryInfos.KEY_REQUESTED_MESSAGES, i2);
            checkAndSetSummary(map3, map5, NxLogSummaryInfos.KEY_SENT_MESSAGES, i3);
            checkAndSetSummary(map4, map5, NxLogSummaryInfos.KEY_DELETED_MESSAGES, i4);
            return map5;
        } catch (SQLException e) {
            NxLogcat.e("popSummaryInfo : " + e.getMessage());
        } finally {
            cursorRawQuery.close();
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
        }
    }

    public List<Object[]> selectLogToDelete(int i) {
        Cursor cursorRawQuery = getWritableDatabase().rawQuery(String.format("SELECT log_id, log_type, is_nxlog_type FROM log LIMIT %d;", Integer.valueOf(i)), null);
        ArrayList arrayList = new ArrayList();
        try {
            cursorRawQuery.moveToFirst();
            while (!cursorRawQuery.isAfterLast()) {
                Object[] objArr = {Long.valueOf(cursorRawQuery.getLong(0)), cursorRawQuery.getString(1), Integer.valueOf(cursorRawQuery.getInt(2))};
                cursorRawQuery.moveToNext();
                arrayList.add(objArr);
            }
            cursorRawQuery.close();
            return arrayList;
        } catch (Throwable th) {
            cursorRawQuery.close();
            throw th;
        }
    }

    public void setDatabaseMode(SQLiteDatabase sQLiteDatabase) {
        setJournalMode(sQLiteDatabase, JournalMode.OFF);
        setSynchronous(sQLiteDatabase, 0);
    }

    public void setJournalMode(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=" + str, null);
        cursorRawQuery.moveToNext();
        NxLogcat.i("Database journal mode : " + str);
        cursorRawQuery.close();
    }

    public void setStorageLimitCount(int i) {
        if (i == 0) {
            return;
        }
        this.storageLimitCount = i;
    }

    public void setSynchronous(SQLiteDatabase sQLiteDatabase, int i) {
        if (i > 3 || i < 0) {
            NxLogcat.w("Sqlite Sync level Out of range.");
        }
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("PRAGMA synchronous=" + i, null);
        cursorRawQuery.moveToNext();
        NxLogcat.i("Sqlite Sync level : " + i);
        cursorRawQuery.close();
    }

    public void updateBaseTimeAndTimeSync(long j, int i, long j2) {
        try {
            getWritableDatabase().execSQL(String.format("UPDATE log_common_info SET base_time = %d, time_sync = %d  WHERE log_key = %d;", Long.valueOf(j), Integer.valueOf(i), Long.valueOf(j2)));
        } catch (SQLException e) {
            NxLogcat.e("updateBaseTimeAndTimeSync : " + e.getMessage());
        }
    }

    public void updateSummaryBeginTime(long j) {
        try {
            getWritableDatabase().execSQL(String.format("UPDATE summary_sub_info SET summary_begin_time = %d WHERE rowid = 1", Long.valueOf(j)));
        } catch (SQLException e) {
            NxLogcat.e("updateSummaryBeginTime : " + e.getMessage());
        }
    }
}
