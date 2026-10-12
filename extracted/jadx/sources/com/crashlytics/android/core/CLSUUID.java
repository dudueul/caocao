package com.crashlytics.android.core;

import android.os.Process;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.IdManager;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class CLSUUID {
    private static String _clsId;
    private static final AtomicLong _sequenceNumber = new AtomicLong(0);

    public CLSUUID(IdManager idManager) {
        byte[] bArr = new byte[10];
        populateTime(bArr);
        populateSequenceNumber(bArr);
        populatePID(bArr);
        String strSha1 = CommonUtils.sha1(idManager.getAppInstallIdentifier());
        String strHexify = CommonUtils.hexify(bArr);
        _clsId = String.format(Locale.US, "%s-%s-%s-%s", strHexify.substring(0, 12), strHexify.substring(12, 16), strHexify.subSequence(16, 20), strSha1.substring(0, 12)).toUpperCase(Locale.US);
    }

    private static byte[] convertLongToFourByteBuffer(long j) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.putInt((int) j);
        byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
        byteBufferAllocate.position(0);
        return byteBufferAllocate.array();
    }

    private static byte[] convertLongToTwoByteBuffer(long j) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(2);
        byteBufferAllocate.putShort((short) j);
        byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
        byteBufferAllocate.position(0);
        return byteBufferAllocate.array();
    }

    private void populatePID(byte[] bArr) {
        byte[] bArrConvertLongToTwoByteBuffer = convertLongToTwoByteBuffer(Integer.valueOf(Process.myPid()).shortValue());
        bArr[8] = bArrConvertLongToTwoByteBuffer[0];
        bArr[9] = bArrConvertLongToTwoByteBuffer[1];
    }

    private void populateSequenceNumber(byte[] bArr) {
        byte[] bArrConvertLongToTwoByteBuffer = convertLongToTwoByteBuffer(_sequenceNumber.incrementAndGet());
        bArr[6] = bArrConvertLongToTwoByteBuffer[0];
        bArr[7] = bArrConvertLongToTwoByteBuffer[1];
    }

    private void populateTime(byte[] bArr) {
        long time = new Date().getTime();
        byte[] bArrConvertLongToFourByteBuffer = convertLongToFourByteBuffer(time / 1000);
        bArr[0] = bArrConvertLongToFourByteBuffer[0];
        bArr[1] = bArrConvertLongToFourByteBuffer[1];
        bArr[2] = bArrConvertLongToFourByteBuffer[2];
        bArr[3] = bArrConvertLongToFourByteBuffer[3];
        byte[] bArrConvertLongToTwoByteBuffer = convertLongToTwoByteBuffer(time % 1000);
        bArr[4] = bArrConvertLongToTwoByteBuffer[0];
        bArr[5] = bArrConvertLongToTwoByteBuffer[1];
    }

    public String toString() {
        return _clsId;
    }
}
