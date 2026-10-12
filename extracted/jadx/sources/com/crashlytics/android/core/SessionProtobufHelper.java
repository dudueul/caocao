package com.crashlytics.android.core;

import android.app.ActivityManager;
import android.os.Build;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.IdManager;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class SessionProtobufHelper {
    private static final String SIGNAL_DEFAULT = "0";
    private static final ByteString SIGNAL_DEFAULT_BYTE_STRING = ByteString.copyFromUtf8("0");
    private static final ByteString UNITY_PLATFORM_BYTE_STRING = ByteString.copyFromUtf8("Unity");

    private SessionProtobufHelper() {
    }

    private static int getBinaryImageSize(ByteString byteString, ByteString byteString2) {
        int iComputeUInt64Size = 0 + CodedOutputStream.computeUInt64Size(1, 0L) + CodedOutputStream.computeUInt64Size(2, 0L) + CodedOutputStream.computeBytesSize(3, byteString);
        return byteString2 != null ? iComputeUInt64Size + CodedOutputStream.computeBytesSize(4, byteString2) : iComputeUInt64Size;
    }

    private static int getDeviceIdentifierSize(IdManager.DeviceIdentifierType deviceIdentifierType, String str) {
        return CodedOutputStream.computeEnumSize(1, deviceIdentifierType.protobufIndex) + CodedOutputStream.computeBytesSize(2, ByteString.copyFromUtf8(str));
    }

    private static int getEventAppCustomAttributeSize(String str, String str2) {
        int iComputeBytesSize = CodedOutputStream.computeBytesSize(1, ByteString.copyFromUtf8(str));
        if (str2 == null) {
            str2 = "";
        }
        return iComputeBytesSize + CodedOutputStream.computeBytesSize(2, ByteString.copyFromUtf8(str2));
    }

    private static int getEventAppExecutionExceptionSize(TrimmedThrowableData trimmedThrowableData, int i, int i2) {
        int iComputeBytesSize = 0 + CodedOutputStream.computeBytesSize(1, ByteString.copyFromUtf8(trimmedThrowableData.className));
        String str = trimmedThrowableData.localizedMessage;
        if (str != null) {
            iComputeBytesSize += CodedOutputStream.computeBytesSize(3, ByteString.copyFromUtf8(str));
        }
        for (StackTraceElement stackTraceElement : trimmedThrowableData.stacktrace) {
            int frameSize = getFrameSize(stackTraceElement, true);
            iComputeBytesSize += CodedOutputStream.computeTagSize(4) + CodedOutputStream.computeRawVarint32Size(frameSize) + frameSize;
        }
        TrimmedThrowableData trimmedThrowableData2 = trimmedThrowableData.cause;
        if (trimmedThrowableData2 == null) {
            return iComputeBytesSize;
        }
        if (i < i2) {
            int eventAppExecutionExceptionSize = getEventAppExecutionExceptionSize(trimmedThrowableData2, i + 1, i2);
            return iComputeBytesSize + CodedOutputStream.computeTagSize(6) + CodedOutputStream.computeRawVarint32Size(eventAppExecutionExceptionSize) + eventAppExecutionExceptionSize;
        }
        int i3 = 0;
        while (trimmedThrowableData2 != null) {
            trimmedThrowableData2 = trimmedThrowableData2.cause;
            i3++;
        }
        return iComputeBytesSize + CodedOutputStream.computeUInt32Size(7, i3);
    }

    private static int getEventAppExecutionSignalSize() {
        return 0 + CodedOutputStream.computeBytesSize(1, SIGNAL_DEFAULT_BYTE_STRING) + CodedOutputStream.computeBytesSize(2, SIGNAL_DEFAULT_BYTE_STRING) + CodedOutputStream.computeUInt64Size(3, 0L);
    }

    private static int getEventAppExecutionSize(TrimmedThrowableData trimmedThrowableData, Thread thread, StackTraceElement[] stackTraceElementArr, Thread[] threadArr, List<StackTraceElement[]> list, int i, ByteString byteString, ByteString byteString2) {
        int threadSize = getThreadSize(thread, stackTraceElementArr, 4, true);
        int iComputeTagSize = 0 + CodedOutputStream.computeTagSize(1) + CodedOutputStream.computeRawVarint32Size(threadSize) + threadSize;
        int length = threadArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            int threadSize2 = getThreadSize(threadArr[i2], list.get(i2), 0, false);
            iComputeTagSize += CodedOutputStream.computeTagSize(1) + CodedOutputStream.computeRawVarint32Size(threadSize2) + threadSize2;
        }
        int eventAppExecutionExceptionSize = getEventAppExecutionExceptionSize(trimmedThrowableData, 1, i);
        int iComputeTagSize2 = iComputeTagSize + CodedOutputStream.computeTagSize(2) + CodedOutputStream.computeRawVarint32Size(eventAppExecutionExceptionSize) + eventAppExecutionExceptionSize;
        int eventAppExecutionSignalSize = getEventAppExecutionSignalSize();
        int iComputeTagSize3 = iComputeTagSize2 + CodedOutputStream.computeTagSize(3) + CodedOutputStream.computeRawVarint32Size(eventAppExecutionSignalSize) + eventAppExecutionSignalSize;
        int binaryImageSize = getBinaryImageSize(byteString, byteString2);
        return iComputeTagSize3 + CodedOutputStream.computeTagSize(3) + CodedOutputStream.computeRawVarint32Size(binaryImageSize) + binaryImageSize;
    }

    private static int getEventAppSize(TrimmedThrowableData trimmedThrowableData, Thread thread, StackTraceElement[] stackTraceElementArr, Thread[] threadArr, List<StackTraceElement[]> list, int i, ByteString byteString, ByteString byteString2, Map<String, String> map, ActivityManager.RunningAppProcessInfo runningAppProcessInfo, int i2) {
        int eventAppExecutionSize = getEventAppExecutionSize(trimmedThrowableData, thread, stackTraceElementArr, threadArr, list, i, byteString, byteString2);
        int iComputeTagSize = 0 + CodedOutputStream.computeTagSize(1) + CodedOutputStream.computeRawVarint32Size(eventAppExecutionSize) + eventAppExecutionSize;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                int eventAppCustomAttributeSize = getEventAppCustomAttributeSize(entry.getKey(), entry.getValue());
                iComputeTagSize += CodedOutputStream.computeTagSize(2) + CodedOutputStream.computeRawVarint32Size(eventAppCustomAttributeSize) + eventAppCustomAttributeSize;
            }
        }
        if (runningAppProcessInfo != null) {
            iComputeTagSize += CodedOutputStream.computeBoolSize(3, runningAppProcessInfo.importance != 100);
        }
        return iComputeTagSize + CodedOutputStream.computeUInt32Size(4, i2);
    }

    private static int getEventDeviceSize(Float f, int i, boolean z, int i2, long j, long j2) {
        return (f != null ? 0 + CodedOutputStream.computeFloatSize(1, f.floatValue()) : 0) + CodedOutputStream.computeSInt32Size(2, i) + CodedOutputStream.computeBoolSize(3, z) + CodedOutputStream.computeUInt32Size(4, i2) + CodedOutputStream.computeUInt64Size(5, j) + CodedOutputStream.computeUInt64Size(6, j2);
    }

    private static int getEventLogSize(ByteString byteString) {
        return CodedOutputStream.computeBytesSize(1, byteString);
    }

    private static int getFrameSize(StackTraceElement stackTraceElement, boolean z) {
        int iComputeUInt64Size = (stackTraceElement.isNativeMethod() ? 0 + CodedOutputStream.computeUInt64Size(1, Math.max(stackTraceElement.getLineNumber(), 0)) : 0 + CodedOutputStream.computeUInt64Size(1, 0L)) + CodedOutputStream.computeBytesSize(2, ByteString.copyFromUtf8(stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName()));
        if (stackTraceElement.getFileName() != null) {
            iComputeUInt64Size += CodedOutputStream.computeBytesSize(3, ByteString.copyFromUtf8(stackTraceElement.getFileName()));
        }
        if (!stackTraceElement.isNativeMethod() && stackTraceElement.getLineNumber() > 0) {
            iComputeUInt64Size += CodedOutputStream.computeUInt64Size(4, stackTraceElement.getLineNumber());
        }
        return iComputeUInt64Size + CodedOutputStream.computeUInt32Size(5, z ? 2 : 0);
    }

    private static int getSessionAppOrgSize(ByteString byteString) {
        return 0 + CodedOutputStream.computeBytesSize(1, byteString);
    }

    private static int getSessionAppSize(ByteString byteString, ByteString byteString2, ByteString byteString3, ByteString byteString4, ByteString byteString5, int i, ByteString byteString6) {
        int iComputeBytesSize = 0 + CodedOutputStream.computeBytesSize(1, byteString) + CodedOutputStream.computeBytesSize(2, byteString3) + CodedOutputStream.computeBytesSize(3, byteString4);
        int sessionAppOrgSize = getSessionAppOrgSize(byteString2);
        int iComputeTagSize = iComputeBytesSize + CodedOutputStream.computeTagSize(5) + CodedOutputStream.computeRawVarint32Size(sessionAppOrgSize) + sessionAppOrgSize + CodedOutputStream.computeBytesSize(6, byteString5);
        if (byteString6 != null) {
            iComputeTagSize = iComputeTagSize + CodedOutputStream.computeBytesSize(8, UNITY_PLATFORM_BYTE_STRING) + CodedOutputStream.computeBytesSize(9, byteString6);
        }
        return iComputeTagSize + CodedOutputStream.computeEnumSize(10, i);
    }

    private static int getSessionDeviceSize(int i, ByteString byteString, ByteString byteString2, int i2, long j, long j2, boolean z, Map<IdManager.DeviceIdentifierType, String> map, int i3, ByteString byteString3, ByteString byteString4) {
        int iComputeBytesSize = 0 + CodedOutputStream.computeBytesSize(1, byteString) + CodedOutputStream.computeEnumSize(3, i) + (byteString2 == null ? 0 : CodedOutputStream.computeBytesSize(4, byteString2)) + CodedOutputStream.computeUInt32Size(5, i2) + CodedOutputStream.computeUInt64Size(6, j) + CodedOutputStream.computeUInt64Size(7, j2) + CodedOutputStream.computeBoolSize(10, z);
        if (map != null) {
            for (Map.Entry<IdManager.DeviceIdentifierType, String> entry : map.entrySet()) {
                int deviceIdentifierSize = getDeviceIdentifierSize(entry.getKey(), entry.getValue());
                iComputeBytesSize += CodedOutputStream.computeTagSize(11) + CodedOutputStream.computeRawVarint32Size(deviceIdentifierSize) + deviceIdentifierSize;
            }
        }
        return iComputeBytesSize + CodedOutputStream.computeUInt32Size(12, i3) + (byteString3 == null ? 0 : CodedOutputStream.computeBytesSize(13, byteString3)) + (byteString4 == null ? 0 : CodedOutputStream.computeBytesSize(14, byteString4));
    }

    private static int getSessionEventSize(long j, String str, TrimmedThrowableData trimmedThrowableData, Thread thread, StackTraceElement[] stackTraceElementArr, Thread[] threadArr, List<StackTraceElement[]> list, int i, Map<String, String> map, ActivityManager.RunningAppProcessInfo runningAppProcessInfo, int i2, ByteString byteString, ByteString byteString2, Float f, int i3, boolean z, long j2, long j3, ByteString byteString3) {
        int iComputeUInt64Size = 0 + CodedOutputStream.computeUInt64Size(1, j) + CodedOutputStream.computeBytesSize(2, ByteString.copyFromUtf8(str));
        int eventAppSize = getEventAppSize(trimmedThrowableData, thread, stackTraceElementArr, threadArr, list, i, byteString, byteString2, map, runningAppProcessInfo, i2);
        int iComputeTagSize = iComputeUInt64Size + CodedOutputStream.computeTagSize(3) + CodedOutputStream.computeRawVarint32Size(eventAppSize) + eventAppSize;
        int eventDeviceSize = getEventDeviceSize(f, i3, z, i2, j2, j3);
        int iComputeTagSize2 = iComputeTagSize + CodedOutputStream.computeTagSize(5) + CodedOutputStream.computeRawVarint32Size(eventDeviceSize) + eventDeviceSize;
        if (byteString3 == null) {
            return iComputeTagSize2;
        }
        int eventLogSize = getEventLogSize(byteString3);
        return iComputeTagSize2 + CodedOutputStream.computeTagSize(6) + CodedOutputStream.computeRawVarint32Size(eventLogSize) + eventLogSize;
    }

    private static int getSessionOSSize(ByteString byteString, ByteString byteString2, boolean z) {
        return 0 + CodedOutputStream.computeEnumSize(1, 3) + CodedOutputStream.computeBytesSize(2, byteString) + CodedOutputStream.computeBytesSize(3, byteString2) + CodedOutputStream.computeBoolSize(4, z);
    }

    private static int getThreadSize(Thread thread, StackTraceElement[] stackTraceElementArr, int i, boolean z) {
        int iComputeBytesSize = CodedOutputStream.computeBytesSize(1, ByteString.copyFromUtf8(thread.getName())) + CodedOutputStream.computeUInt32Size(2, i);
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            int frameSize = getFrameSize(stackTraceElement, z);
            iComputeBytesSize += CodedOutputStream.computeTagSize(3) + CodedOutputStream.computeRawVarint32Size(frameSize) + frameSize;
        }
        return iComputeBytesSize;
    }

    private static ByteString stringToByteString(String str) {
        if (str == null) {
            return null;
        }
        return ByteString.copyFromUtf8(str);
    }

    public static void writeBeginSession(CodedOutputStream codedOutputStream, String str, String str2, long j) throws Exception {
        codedOutputStream.writeBytes(1, ByteString.copyFromUtf8(str2));
        codedOutputStream.writeBytes(2, ByteString.copyFromUtf8(str));
        codedOutputStream.writeUInt64(3, j);
    }

    private static void writeFrame(CodedOutputStream codedOutputStream, int i, StackTraceElement stackTraceElement, boolean z) throws Exception {
        codedOutputStream.writeTag(i, 2);
        codedOutputStream.writeRawVarint32(getFrameSize(stackTraceElement, z));
        if (stackTraceElement.isNativeMethod()) {
            codedOutputStream.writeUInt64(1, Math.max(stackTraceElement.getLineNumber(), 0));
        } else {
            codedOutputStream.writeUInt64(1, 0L);
        }
        codedOutputStream.writeBytes(2, ByteString.copyFromUtf8(stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName()));
        if (stackTraceElement.getFileName() != null) {
            codedOutputStream.writeBytes(3, ByteString.copyFromUtf8(stackTraceElement.getFileName()));
        }
        if (!stackTraceElement.isNativeMethod() && stackTraceElement.getLineNumber() > 0) {
            codedOutputStream.writeUInt64(4, stackTraceElement.getLineNumber());
        }
        codedOutputStream.writeUInt32(5, z ? 4 : 0);
    }

    public static void writeSessionApp(CodedOutputStream codedOutputStream, String str, String str2, String str3, String str4, String str5, int i, String str6) throws Exception {
        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8(str);
        ByteString byteStringCopyFromUtf9 = ByteString.copyFromUtf8(str2);
        ByteString byteStringCopyFromUtf10 = ByteString.copyFromUtf8(str3);
        ByteString byteStringCopyFromUtf11 = ByteString.copyFromUtf8(str4);
        ByteString byteStringCopyFromUtf12 = ByteString.copyFromUtf8(str5);
        ByteString byteStringCopyFromUtf13 = str6 != null ? ByteString.copyFromUtf8(str6) : null;
        codedOutputStream.writeTag(7, 2);
        codedOutputStream.writeRawVarint32(getSessionAppSize(byteStringCopyFromUtf8, byteStringCopyFromUtf9, byteStringCopyFromUtf10, byteStringCopyFromUtf11, byteStringCopyFromUtf12, i, byteStringCopyFromUtf13));
        codedOutputStream.writeBytes(1, byteStringCopyFromUtf8);
        codedOutputStream.writeBytes(2, byteStringCopyFromUtf10);
        codedOutputStream.writeBytes(3, byteStringCopyFromUtf11);
        codedOutputStream.writeTag(5, 2);
        codedOutputStream.writeRawVarint32(getSessionAppOrgSize(byteStringCopyFromUtf9));
        codedOutputStream.writeBytes(1, byteStringCopyFromUtf9);
        codedOutputStream.writeBytes(6, byteStringCopyFromUtf12);
        if (byteStringCopyFromUtf13 != null) {
            codedOutputStream.writeBytes(8, UNITY_PLATFORM_BYTE_STRING);
            codedOutputStream.writeBytes(9, byteStringCopyFromUtf13);
        }
        codedOutputStream.writeEnum(10, i);
    }

    public static void writeSessionDevice(CodedOutputStream codedOutputStream, String str, int i, String str2, int i2, long j, long j2, boolean z, Map<IdManager.DeviceIdentifierType, String> map, int i3, String str3, String str4) throws Exception {
        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8(str);
        ByteString byteStringStringToByteString = stringToByteString(str2);
        ByteString byteStringStringToByteString2 = stringToByteString(str4);
        ByteString byteStringStringToByteString3 = stringToByteString(str3);
        codedOutputStream.writeTag(9, 2);
        codedOutputStream.writeRawVarint32(getSessionDeviceSize(i, byteStringCopyFromUtf8, byteStringStringToByteString, i2, j, j2, z, map, i3, byteStringStringToByteString3, byteStringStringToByteString2));
        codedOutputStream.writeBytes(1, byteStringCopyFromUtf8);
        codedOutputStream.writeEnum(3, i);
        codedOutputStream.writeBytes(4, byteStringStringToByteString);
        codedOutputStream.writeUInt32(5, i2);
        codedOutputStream.writeUInt64(6, j);
        codedOutputStream.writeUInt64(7, j2);
        codedOutputStream.writeBool(10, z);
        for (Map.Entry<IdManager.DeviceIdentifierType, String> entry : map.entrySet()) {
            codedOutputStream.writeTag(11, 2);
            codedOutputStream.writeRawVarint32(getDeviceIdentifierSize(entry.getKey(), entry.getValue()));
            codedOutputStream.writeEnum(1, entry.getKey().protobufIndex);
            codedOutputStream.writeBytes(2, ByteString.copyFromUtf8(entry.getValue()));
        }
        codedOutputStream.writeUInt32(12, i3);
        if (byteStringStringToByteString3 != null) {
            codedOutputStream.writeBytes(13, byteStringStringToByteString3);
        }
        if (byteStringStringToByteString2 != null) {
            codedOutputStream.writeBytes(14, byteStringStringToByteString2);
        }
    }

    public static void writeSessionEvent(CodedOutputStream codedOutputStream, long j, String str, TrimmedThrowableData trimmedThrowableData, Thread thread, StackTraceElement[] stackTraceElementArr, Thread[] threadArr, List<StackTraceElement[]> list, Map<String, String> map, LogFileManager logFileManager, ActivityManager.RunningAppProcessInfo runningAppProcessInfo, int i, String str2, String str3, Float f, int i2, boolean z, long j2, long j3) throws Exception {
        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8(str2);
        ByteString byteStringCopyFromUtf9 = str3 == null ? null : ByteString.copyFromUtf8(str3.replace("-", ""));
        ByteString byteStringForLog = logFileManager.getByteStringForLog();
        if (byteStringForLog == null) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "No log data to include with this event.");
        }
        logFileManager.clearLog();
        codedOutputStream.writeTag(10, 2);
        codedOutputStream.writeRawVarint32(getSessionEventSize(j, str, trimmedThrowableData, thread, stackTraceElementArr, threadArr, list, 8, map, runningAppProcessInfo, i, byteStringCopyFromUtf8, byteStringCopyFromUtf9, f, i2, z, j2, j3, byteStringForLog));
        codedOutputStream.writeUInt64(1, j);
        codedOutputStream.writeBytes(2, ByteString.copyFromUtf8(str));
        writeSessionEventApp(codedOutputStream, trimmedThrowableData, thread, stackTraceElementArr, threadArr, list, 8, byteStringCopyFromUtf8, byteStringCopyFromUtf9, map, runningAppProcessInfo, i);
        writeSessionEventDevice(codedOutputStream, f, i2, z, i, j2, j3);
        writeSessionEventLog(codedOutputStream, byteStringForLog);
    }

    private static void writeSessionEventApp(CodedOutputStream codedOutputStream, TrimmedThrowableData trimmedThrowableData, Thread thread, StackTraceElement[] stackTraceElementArr, Thread[] threadArr, List<StackTraceElement[]> list, int i, ByteString byteString, ByteString byteString2, Map<String, String> map, ActivityManager.RunningAppProcessInfo runningAppProcessInfo, int i2) throws Exception {
        codedOutputStream.writeTag(3, 2);
        codedOutputStream.writeRawVarint32(getEventAppSize(trimmedThrowableData, thread, stackTraceElementArr, threadArr, list, i, byteString, byteString2, map, runningAppProcessInfo, i2));
        writeSessionEventAppExecution(codedOutputStream, trimmedThrowableData, thread, stackTraceElementArr, threadArr, list, i, byteString, byteString2);
        if (map != null && !map.isEmpty()) {
            writeSessionEventAppCustomAttributes(codedOutputStream, map);
        }
        if (runningAppProcessInfo != null) {
            codedOutputStream.writeBool(3, runningAppProcessInfo.importance != 100);
        }
        codedOutputStream.writeUInt32(4, i2);
    }

    private static void writeSessionEventAppCustomAttributes(CodedOutputStream codedOutputStream, Map<String, String> map) throws Exception {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            codedOutputStream.writeTag(2, 2);
            codedOutputStream.writeRawVarint32(getEventAppCustomAttributeSize(entry.getKey(), entry.getValue()));
            codedOutputStream.writeBytes(1, ByteString.copyFromUtf8(entry.getKey()));
            String value = entry.getValue();
            if (value == null) {
                value = "";
            }
            codedOutputStream.writeBytes(2, ByteString.copyFromUtf8(value));
        }
    }

    private static void writeSessionEventAppExecution(CodedOutputStream codedOutputStream, TrimmedThrowableData trimmedThrowableData, Thread thread, StackTraceElement[] stackTraceElementArr, Thread[] threadArr, List<StackTraceElement[]> list, int i, ByteString byteString, ByteString byteString2) throws Exception {
        codedOutputStream.writeTag(1, 2);
        codedOutputStream.writeRawVarint32(getEventAppExecutionSize(trimmedThrowableData, thread, stackTraceElementArr, threadArr, list, i, byteString, byteString2));
        writeThread(codedOutputStream, thread, stackTraceElementArr, 4, true);
        int length = threadArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            writeThread(codedOutputStream, threadArr[i2], list.get(i2), 0, false);
        }
        writeSessionEventAppExecutionException(codedOutputStream, trimmedThrowableData, 1, i, 2);
        codedOutputStream.writeTag(3, 2);
        codedOutputStream.writeRawVarint32(getEventAppExecutionSignalSize());
        codedOutputStream.writeBytes(1, SIGNAL_DEFAULT_BYTE_STRING);
        codedOutputStream.writeBytes(2, SIGNAL_DEFAULT_BYTE_STRING);
        codedOutputStream.writeUInt64(3, 0L);
        codedOutputStream.writeTag(4, 2);
        codedOutputStream.writeRawVarint32(getBinaryImageSize(byteString, byteString2));
        codedOutputStream.writeUInt64(1, 0L);
        codedOutputStream.writeUInt64(2, 0L);
        codedOutputStream.writeBytes(3, byteString);
        if (byteString2 != null) {
            codedOutputStream.writeBytes(4, byteString2);
        }
    }

    private static void writeSessionEventAppExecutionException(CodedOutputStream codedOutputStream, TrimmedThrowableData trimmedThrowableData, int i, int i2, int i3) throws Exception {
        codedOutputStream.writeTag(i3, 2);
        codedOutputStream.writeRawVarint32(getEventAppExecutionExceptionSize(trimmedThrowableData, 1, i2));
        codedOutputStream.writeBytes(1, ByteString.copyFromUtf8(trimmedThrowableData.className));
        String str = trimmedThrowableData.localizedMessage;
        if (str != null) {
            codedOutputStream.writeBytes(3, ByteString.copyFromUtf8(str));
        }
        for (StackTraceElement stackTraceElement : trimmedThrowableData.stacktrace) {
            writeFrame(codedOutputStream, 4, stackTraceElement, true);
        }
        TrimmedThrowableData trimmedThrowableData2 = trimmedThrowableData.cause;
        if (trimmedThrowableData2 != null) {
            if (i < i2) {
                writeSessionEventAppExecutionException(codedOutputStream, trimmedThrowableData2, i + 1, i2, 6);
                return;
            }
            int i4 = 0;
            while (trimmedThrowableData2 != null) {
                trimmedThrowableData2 = trimmedThrowableData2.cause;
                i4++;
            }
            codedOutputStream.writeUInt32(7, i4);
        }
    }

    private static void writeSessionEventDevice(CodedOutputStream codedOutputStream, Float f, int i, boolean z, int i2, long j, long j2) throws Exception {
        codedOutputStream.writeTag(5, 2);
        codedOutputStream.writeRawVarint32(getEventDeviceSize(f, i, z, i2, j, j2));
        if (f != null) {
            codedOutputStream.writeFloat(1, f.floatValue());
        }
        codedOutputStream.writeSInt32(2, i);
        codedOutputStream.writeBool(3, z);
        codedOutputStream.writeUInt32(4, i2);
        codedOutputStream.writeUInt64(5, j);
        codedOutputStream.writeUInt64(6, j2);
    }

    private static void writeSessionEventLog(CodedOutputStream codedOutputStream, ByteString byteString) throws Exception {
        if (byteString != null) {
            codedOutputStream.writeTag(6, 2);
            codedOutputStream.writeRawVarint32(getEventLogSize(byteString));
            codedOutputStream.writeBytes(1, byteString);
        }
    }

    public static void writeSessionOS(CodedOutputStream codedOutputStream, boolean z) throws Exception {
        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8(Build.VERSION.RELEASE);
        ByteString byteStringCopyFromUtf9 = ByteString.copyFromUtf8(Build.VERSION.CODENAME);
        codedOutputStream.writeTag(8, 2);
        codedOutputStream.writeRawVarint32(getSessionOSSize(byteStringCopyFromUtf8, byteStringCopyFromUtf9, z));
        codedOutputStream.writeEnum(1, 3);
        codedOutputStream.writeBytes(2, byteStringCopyFromUtf8);
        codedOutputStream.writeBytes(3, byteStringCopyFromUtf9);
        codedOutputStream.writeBool(4, z);
    }

    public static void writeSessionUser(CodedOutputStream codedOutputStream, String str, String str2, String str3) throws Exception {
        if (str == null) {
            str = "";
        }
        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8(str);
        ByteString byteStringStringToByteString = stringToByteString(str2);
        ByteString byteStringStringToByteString2 = stringToByteString(str3);
        int iComputeBytesSize = 0 + CodedOutputStream.computeBytesSize(1, byteStringCopyFromUtf8);
        if (str2 != null) {
            iComputeBytesSize += CodedOutputStream.computeBytesSize(2, byteStringStringToByteString);
        }
        if (str3 != null) {
            iComputeBytesSize += CodedOutputStream.computeBytesSize(3, byteStringStringToByteString2);
        }
        codedOutputStream.writeTag(6, 2);
        codedOutputStream.writeRawVarint32(iComputeBytesSize);
        codedOutputStream.writeBytes(1, byteStringCopyFromUtf8);
        if (str2 != null) {
            codedOutputStream.writeBytes(2, byteStringStringToByteString);
        }
        if (str3 != null) {
            codedOutputStream.writeBytes(3, byteStringStringToByteString2);
        }
    }

    private static void writeThread(CodedOutputStream codedOutputStream, Thread thread, StackTraceElement[] stackTraceElementArr, int i, boolean z) throws Exception {
        codedOutputStream.writeTag(1, 2);
        codedOutputStream.writeRawVarint32(getThreadSize(thread, stackTraceElementArr, i, z));
        codedOutputStream.writeBytes(1, ByteString.copyFromUtf8(thread.getName()));
        codedOutputStream.writeUInt32(2, i);
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            writeFrame(codedOutputStream, 3, stackTraceElement, z);
        }
    }
}
