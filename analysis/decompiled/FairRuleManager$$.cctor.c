// name: FairRuleManager$$.cctor
// entry: 0174ad08

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void FairRuleManager___cctor(void)

{
  long lVar1;
  undefined8 uVar2;
  long lVar3;
  
  if ((bRam00000000049f9c20 & 1) == 0) {
    func_0x010c65d4(0x5682);
    bRam00000000049f9c20 = 1;
  }
  **(undefined4 **)(_class_FairRuleManager_TypeInfo + 0xb8) = 0xc;
  if (((*(byte *)(_class_ClientConst_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_ClientConst_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar3 = *(long *)(*(long *)(_class_ClientConst_TypeInfo + 0xb8) + 0x1c0);
  if (lVar3 == 0) {
    func_0x010eec64(0);
    func_0x010eec64(0);
  }
  lVar1 = (*(long *)(lVar3 + 0x18) << 0x20) + -0x100000000;
  if ((uint)*(long *)(lVar3 + 0x18) <= (uint)((ulong)lVar1 >> 0x20)) {
    uVar2 = func_0x010ef850();
    func_0x010eebec(uVar2,0,0);
  }
  *(undefined4 *)(*(long *)(_class_FairRuleManager_TypeInfo + 0xb8) + 4) =
       *(undefined4 *)(lVar3 + (lVar1 >> 0x20) * 4 + 0x20);
  *(undefined1 *)(*(long *)(_class_FairRuleManager_TypeInfo + 0xb8) + 8) = 1;
  *(undefined1 *)(*(long *)(_class_FairRuleManager_TypeInfo + 0xb8) + 9) = 1;
  *(undefined4 *)(*(long *)(_class_FairRuleManager_TypeInfo + 0xb8) + 0xc) = 5;
  return;
}


