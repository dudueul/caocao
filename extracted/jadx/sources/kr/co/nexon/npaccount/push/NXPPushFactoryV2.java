package kr.co.nexon.npaccount.push;

import kr.co.nexon.npaccount.push.interfaces.NXPPushFactory;
import kr.co.nexon.npaccount.push.interfaces.NXPPushMenu;
import kr.co.nexon.npaccount.push.interfaces.NXPPushService;
import kr.co.nexon.npaccount.push.interfaces.NXPPushSetting;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPushFactoryV2 implements NXPPushFactory {
    @Override // kr.co.nexon.npaccount.push.interfaces.NXPPushFactory
    public NXPPushMenu createMenu() {
        return new NXPPushMenuImplV2();
    }

    @Override // kr.co.nexon.npaccount.push.interfaces.NXPPushFactory
    public NXPPushService createService() {
        return new NXPPushServiceImplV2();
    }

    @Override // kr.co.nexon.npaccount.push.interfaces.NXPPushFactory
    public NXPPushSetting createSetting() {
        return new NXPPushSettingImplV2();
    }
}
