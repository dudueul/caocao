package com.google.zxing.oned.rss.expanded.decoders;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class BlockParsedResult {
    private final DecodedInformation decodedInformation;
    private final boolean finished;

    BlockParsedResult(DecodedInformation decodedInformation, boolean z) {
        this.finished = z;
        this.decodedInformation = decodedInformation;
    }

    BlockParsedResult(boolean z) {
        this(null, z);
    }

    DecodedInformation getDecodedInformation() {
        return this.decodedInformation;
    }

    boolean isFinished() {
        return this.finished;
    }
}
