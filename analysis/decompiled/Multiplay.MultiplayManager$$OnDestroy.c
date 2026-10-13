// name: Multiplay.MultiplayManager$$OnDestroy
// entry: 013b03f4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined1  [16] Multiplay_MultiplayManager__OnDestroy(long param_1)

{
  undefined1 auVar1 [16];
  
  if ((bRam00000000049f7b4c & 1) == 0) {
    func_0x010c65d4(0x8a4d);
    bRam00000000049f7b4c = 1;
  }
  *(undefined8 *)(param_1 + 0x28) = 0;
  Multiplay_MultiplayManager__RemoveResponseHandler(param_1);
  *(undefined8 *)(param_1 + 0x30) = 0;
  auVar1._8_8_ = _mm_Method_Singleton<MultiplayManager>_OnDestroy__;
  auVar1._0_8_ = param_1;
  return auVar1;
}


