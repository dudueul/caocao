// name: Chat.Chatting$$OnDestroy
// entry: 01d2a454

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined1  [16] Chat_Chatting__OnDestroy(long param_1)

{
  undefined1 auVar1 [16];
  
  if ((bRam00000000049fcf15 & 1) == 0) {
    func_0x010c65d4(0x25e6);
    bRam00000000049fcf15 = 1;
  }
  *(undefined8 *)(param_1 + 0x28) = 0;
  Chat_Chatting__RemoveGuildInfoChangeEventHandler(param_1);
  *(undefined8 *)(param_1 + 0x48) = 0;
  *(undefined8 *)(param_1 + 0x20) = 0;
  *(undefined8 *)(param_1 + 0x88) = 0;
  *(undefined8 *)(param_1 + 0x30) = 0;
  *(undefined8 *)(param_1 + 0x38) = 0;
  *(undefined8 *)(param_1 + 0x70) = 0;
  *(undefined8 *)(param_1 + 0x78) = 0;
  *(undefined8 *)(param_1 + 0x60) = 0;
  *(undefined8 *)(param_1 + 0x68) = 0;
  *(undefined8 *)(param_1 + 0x50) = 0;
  *(undefined8 *)(param_1 + 0x58) = 0;
  auVar1._8_8_ = _mm_Method_Singleton<Chatting>_OnDestroy__;
  auVar1._0_8_ = param_1;
  return auVar1;
}


