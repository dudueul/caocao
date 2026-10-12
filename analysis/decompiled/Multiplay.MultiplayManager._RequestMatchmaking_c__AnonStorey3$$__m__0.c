// name: Multiplay.MultiplayManager.<RequestMatchmaking>c__AnonStorey3$$<>m__0
// entry: 01f5fb30

void Multiplay_MultiplayManager_<RequestMatchmaking>c__AnonStorey3__<>m__0(long param_1)

{
  undefined4 uVar1;
  undefined4 uVar2;
  undefined4 uVar3;
  undefined4 uVar4;
  undefined4 uVar5;
  long lVar6;
  
  lVar6 = *(long *)(param_1 + 0x28);
  uVar1 = *(undefined4 *)(param_1 + 0x10);
  uVar3 = *(undefined4 *)(param_1 + 0x14);
  uVar2 = *(undefined4 *)(param_1 + 0x18);
  uVar4 = *(undefined4 *)(param_1 + 0x1c);
  uVar5 = *(undefined4 *)(param_1 + 0x20);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  Multiplay_MultiplayManager__RequestMatchmaking(lVar6,uVar1,uVar3,uVar2,uVar4,uVar5,0);
  lVar6 = *(long *)(param_1 + 0x28);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar6 + 0x78) = 0;
  return;
}


