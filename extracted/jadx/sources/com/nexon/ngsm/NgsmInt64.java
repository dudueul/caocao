package com.nexon.ngsm;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NgsmInt64 extends NgsmSecuData<Long> {
    public NgsmInt64() {
        super(NgsmSecuData.EJavaDataType.J_NORMAL);
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean addData(Long l) {
        return Ngsm.getInst().ngsmNativeAddSecuData(this.m_shandle, l.longValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.nexon.ngsm.NgsmSecuData
    public Long getData() {
        return Long.valueOf(Ngsm.getInst().ngsmNativeGetSecuData(this.m_shandle));
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean setData(Long l) {
        return Ngsm.getInst().ngsmNativeSetSecuData(this.m_shandle, l.longValue());
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean subData(Long l) {
        return Ngsm.getInst().ngsmNativeSubSecuData(this.m_shandle, l.longValue());
    }
}
