package com.facebook.places.internal;

import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface BleScanner {
    int getErrorCode();

    List<BluetoothScanResult> getScanResults();

    void initAndCheckEligibility() throws ScannerException;

    void startScanning() throws ScannerException;

    void stopScanning() throws ScannerException;
}
