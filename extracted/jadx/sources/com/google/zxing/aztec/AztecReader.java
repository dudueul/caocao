package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.aztec.decoder.Decoder;
import com.google.zxing.aztec.detector.Detector;
import com.google.zxing.common.DecoderResult;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class AztecReader implements Reader {
    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x005b  */
    /* JADX WARN: Code duplicated, block: B:21:0x005c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:22:0x005e  */
    /* JADX WARN: Code duplicated, block: B:23:0x005f  */
    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws Throwable {
        ResultPointCallback resultPointCallback;
        Throwable th;
        NotFoundException e = null;
        FormatException e2 = null;
        Detector detector = new Detector(binaryBitmap.getBlackMatrix());
        ResultPoint[] points = null;
        DecoderResult decoderResultDecode = null;
        try {
            AztecDetectorResult aztecDetectorResultDetect = detector.detect(false);
            points = aztecDetectorResultDetect.getPoints();
            decoderResultDecode = new Decoder().decode(aztecDetectorResultDetect);
        } catch (FormatException e3) {
            e2 = e3;
        } catch (NotFoundException e4) {
            e = e4;
        }
        if (decoderResultDecode == null) {
            try {
                AztecDetectorResult aztecDetectorResultDetect2 = detector.detect(true);
                points = aztecDetectorResultDetect2.getPoints();
                decoderResultDecode = new Decoder().decode(aztecDetectorResultDetect2);
            } catch (FormatException e5) {
                th = e5;
                if (e != null) {
                    throw e;
                }
                if (e2 != null) {
                    throw e2;
                }
                throw th;
            } catch (NotFoundException e6) {
                th = e6;
                if (e != null) {
                    throw e;
                }
                if (e2 != null) {
                    throw e2;
                }
                throw th;
            }
        }
        if (map != null && (resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK)) != null) {
            for (ResultPoint resultPoint : points) {
                resultPointCallback.foundPossibleResultPoint(resultPoint);
            }
        }
        Result result = new Result(decoderResultDecode.getText(), decoderResultDecode.getRawBytes(), decoderResultDecode.getNumBits(), points, BarcodeFormat.AZTEC, System.currentTimeMillis());
        List<byte[]> byteSegments = decoderResultDecode.getByteSegments();
        if (byteSegments != null) {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, byteSegments);
        }
        String eCLevel = decoderResultDecode.getECLevel();
        if (eCLevel != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, eCLevel);
        }
        return result;
    }

    @Override // com.google.zxing.Reader
    public void reset() {
    }
}
