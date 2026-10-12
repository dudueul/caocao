package com.nexon.ngsm;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NgsmByte extends NgsmSecuData<Byte> {
    public NgsmByte() {
        super(NgsmSecuData.EJavaDataType.J_NORMAL);
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean addData(Byte b) {
        return Ngsm.getInst().ngsmNativeAddSecuData(this.m_shandle, b.byteValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.nexon.ngsm.NgsmSecuData
    public Byte getData() {
        return Byte.valueOf((byte) Ngsm.getInst().ngsmNativeGetSecuData(this.m_shandle));
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean setData(Byte b) {
        return Ngsm.getInst().ngsmNativeSetSecuData(this.m_shandle, b.byteValue());
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean subData(Byte b) {
        return Ngsm.getInst().ngsmNativeSubSecuData(this.m_shandle, b.byteValue());
    }
}
