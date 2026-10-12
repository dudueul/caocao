package com.nexon.ngsm;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NgsmShort extends NgsmSecuData<Short> {
    public NgsmShort() {
        super(NgsmSecuData.EJavaDataType.J_NORMAL);
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean addData(Short sh) {
        return Ngsm.getInst().ngsmNativeAddSecuData(this.m_shandle, sh.shortValue());
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public Short getData() {
        return Short.valueOf((short) Ngsm.getInst().ngsmNativeGetSecuData(this.m_shandle));
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean setData(Short sh) {
        return Ngsm.getInst().ngsmNativeSetSecuData(this.m_shandle, sh.shortValue());
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean subData(Short sh) {
        return Ngsm.getInst().ngsmNativeSubSecuData(this.m_shandle, sh.shortValue());
    }
}
