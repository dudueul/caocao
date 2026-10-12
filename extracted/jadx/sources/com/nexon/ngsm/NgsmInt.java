package com.nexon.ngsm;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NgsmInt extends NgsmSecuData<Integer> {
    public NgsmInt() {
        super(NgsmSecuData.EJavaDataType.J_NORMAL);
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean addData(Integer num) {
        return Ngsm.getInst().ngsmNativeAddSecuData(this.m_shandle, num.intValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.nexon.ngsm.NgsmSecuData
    public Integer getData() {
        return Integer.valueOf((int) Ngsm.getInst().ngsmNativeGetSecuData(this.m_shandle));
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean setData(Integer num) {
        return Ngsm.getInst().ngsmNativeSetSecuData(this.m_shandle, num.intValue());
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean subData(Integer num) {
        return Ngsm.getInst().ngsmNativeSubSecuData(this.m_shandle, num.intValue());
    }
}
