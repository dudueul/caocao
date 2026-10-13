// name: Multiplay.MultiplayManager$$OnApplicationQuit
// entry: 013b0508

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnApplicationQuit(undefined8 param_1)

{
  ulong uVar1;
  long lVar2;
  long lVar3;
  undefined8 uVar4;
  
  if ((bRam00000000049f7b4d & 1) == 0) {
    func_0x010c65d4(0x8a44);
    bRam00000000049f7b4d = 1;
  }
  uVar1 = Multiplay_MultiplayManager__get_isConnected(param_1);
  if ((uVar1 & 1) != 0) {
    Multiplay_MultiplayManager__DisconnectByQuit(param_1);
  }
  lVar2 = _mm_Method_Singleton<MultiplayManager>_OnApplicationQuit__;
  lVar3 = **(long **)(*(long *)(_mm_Method_Singleton<MultiplayManager>_OnApplicationQuit__ + 0x18) +
                     0xc0);
  func_0x010bbb2c(lVar3);
  if (((*(byte *)(lVar3 + 0x12a) & 1) != 0) &&
     (lVar3 = **(long **)(*(long *)(lVar2 + 0x18) + 0xc0), func_0x010bbb2c(lVar3),
     *(int *)(lVar3 + 0xd8) == 0)) {
    uVar4 = **(undefined8 **)(*(long *)(lVar2 + 0x18) + 0xc0);
    func_0x010bbb2c(uVar4);
    func_0x010da55c(uVar4);
  }
  (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar2 + 0x18) + 0xc0) + 0x28))(0);
  lVar2 = **(long **)(*(long *)(lVar2 + 0x18) + 0xc0);
  func_0x010bbb2c(lVar2);
  *(undefined1 *)(*(long *)(lVar2 + 0xb8) + 0x10) = 1;
  return;
}


