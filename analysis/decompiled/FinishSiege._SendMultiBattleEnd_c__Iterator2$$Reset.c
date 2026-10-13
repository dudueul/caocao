// name: FinishSiege.<SendMultiBattleEnd>c__Iterator2$$Reset
// entry: 0204f7d4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

bool FinishSiege_<SendMultiBattleEnd>c__Iterator2__Reset(void)

{
  int iVar1;
  undefined8 uVar2;
  ulong uVar3;
  long lVar4;
  undefined1 auVar5 [16];
  
  if ((bRam00000000049fea63 & 1) == 0) {
    func_0x010c65d4(0xdfcb);
    bRam00000000049fea63 = 1;
  }
  uVar2 = func_0x010fdda8(_class_System_NotSupportedException_TypeInfo);
  System_NotSupportedException___ctor(uVar2,0);
  uVar3 = _mm_Method_FinishSiege_<SendMultiBattleEnd>c__Iterator2_Reset__;
  func_0x010eebec(uVar2,0,_mm_Method_FinishSiege_<SendMultiBattleEnd>c__Iterator2_Reset__);
  auVar5 = func_0x0112148c();
  lVar4 = *(long *)(auVar5._0_8_ + 0x10);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  lVar4 = AutoTexts__get_Item(lVar4,auVar5._8_8_,uVar3 & 0xffffffff,0);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  iVar1 = AutoText__get_type(lVar4,0);
  return iVar1 == 0;
}


