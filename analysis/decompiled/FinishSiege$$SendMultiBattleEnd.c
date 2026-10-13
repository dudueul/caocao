// name: FinishSiege$$SendMultiBattleEnd
// entry: 0204d0d8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long FinishSiege__SendMultiBattleEnd
               (undefined4 param_1,undefined8 param_2,undefined4 param_3,undefined8 param_4,
               undefined4 param_5,byte param_6,undefined4 param_7,undefined8 param_8)

{
  long lVar1;
  
  if ((bRam00000000049fea5f & 1) == 0) {
    func_0x010c65d4(0x57d4);
    bRam00000000049fea5f = 1;
  }
  lVar1 = func_0x010fdda8(_class_FinishSiege_<SendMultiBattleEnd>c__Iterator2_TypeInfo);
  System_Object___ctor(lVar1,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
    bRam0000000000000010 = param_6 & 1;
    func_0x010eec64(0);
    uRam0000000000000020 = param_5;
    func_0x010eec64(0);
    uRam0000000000000034 = param_3;
    func_0x010eec64(0);
    uRam0000000000000038 = param_4;
    func_0x010eec64(0);
    uRam0000000000000040 = param_1;
    func_0x010eec64(0);
    uRam0000000000000044 = param_7;
    func_0x010eec64(0);
    uRam0000000000000048 = param_8;
    func_0x010eec64(0);
  }
  else {
    *(undefined4 *)(lVar1 + 0x20) = param_5;
    *(undefined4 *)(lVar1 + 0x34) = param_3;
    *(undefined8 *)(lVar1 + 0x38) = param_4;
    *(undefined4 *)(lVar1 + 0x40) = param_1;
    *(undefined4 *)(lVar1 + 0x44) = param_7;
    *(byte *)(lVar1 + 0x10) = param_6 & 1;
    *(undefined8 *)(lVar1 + 0x48) = param_8;
  }
  *(undefined8 *)(lVar1 + 0x50) = param_2;
  return lVar1;
}


