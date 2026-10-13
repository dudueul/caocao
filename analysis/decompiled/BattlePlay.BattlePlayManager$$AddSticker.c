// name: BattlePlay.BattlePlayManager$$AddSticker
// entry: 01977384

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__AddSticker(long param_1,undefined8 param_2)

{
  int iVar1;
  uint uVar2;
  undefined8 uVar3;
  long lVar4;
  long lVar5;
  long lVar6;
  
  lVar4 = *(long *)(param_1 + 0x18);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  if ((bRam00000000049fadf0 & 1) == 0) {
    func_0x010c65d4(0x203d);
    bRam00000000049fadf0 = 1;
  }
  lVar4 = *(long *)(lVar4 + 0x68);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  lVar5 = _mm_Method_System_Collections_Generic_List<BattleSticker>_Add__;
  iVar1 = *(int *)(lVar4 + 0x18);
  lVar6 = *(long *)(lVar4 + 0x10);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  if (iVar1 == *(int *)(lVar6 + 0x18)) {
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar5 + 0x18) + 0xc0) + 0x88))(lVar4,1);
  }
  uVar2 = *(uint *)(lVar4 + 0x18);
  lVar5 = *(long *)(lVar4 + 0x10);
  *(uint *)(lVar4 + 0x18) = uVar2 + 1;
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar5 + 0x18) <= uVar2) {
    uVar3 = func_0x010ef850();
    func_0x010eebec(uVar3,0,0);
  }
  *(undefined8 *)(lVar5 + (long)(int)uVar2 * 8 + 0x20) = param_2;
  *(int *)(lVar4 + 0x1c) = *(int *)(lVar4 + 0x1c) + 1;
  return;
}


