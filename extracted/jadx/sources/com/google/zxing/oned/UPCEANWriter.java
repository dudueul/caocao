package com.google.zxing.oned;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class UPCEANWriter extends OneDimensionalCodeWriter {
    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public int getDefaultMargin() {
        return UPCEANReader.START_END_PATTERN.length;
    }
}
