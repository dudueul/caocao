// name: Chat.Chatting$$get_IsConnecting
// entry: 01d29744

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined1 Chat_Chatting__get_IsConnecting(long param_1)

{
  ulong uVar1;
  long lVar2;
  undefined8 uVar3;
  
  if ((bRam00000000049fcf13 & 1) == 0) {
    func_0x010c65d4(0x2612);
    bRam00000000049fcf13 = 1;
  }
  uVar3 = *(undefined8 *)(param_1 + 0x20);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar1 = UnityEngine_Object__op_Inequality(0,0,uVar3,0);
  if ((uVar1 & 1) != 0) {
    lVar2 = *(long *)(param_1 + 0x20);
    if (lVar2 == 0) {
      func_0x010eec64(0);
    }
    return *(undefined1 *)(lVar2 + 0x40);
  }
  return 0;
}


