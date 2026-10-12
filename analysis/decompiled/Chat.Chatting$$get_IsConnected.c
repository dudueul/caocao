// name: Chat.Chatting$$get_IsConnected
// entry: 01d295b8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

bool Chat_Chatting__get_IsConnected(long param_1)

{
  char cVar1;
  short sVar2;
  bool bVar3;
  ulong uVar4;
  long lVar5;
  undefined8 uVar6;
  
  if ((bRam00000000049fcf11 & 1) == 0) {
    func_0x010c65d4(0x2611);
    bRam00000000049fcf11 = 1;
  }
  uVar6 = *(undefined8 *)(param_1 + 0x20);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar4 = UnityEngine_Object__op_Inequality(0,0,uVar6,0);
  if ((uVar4 & 1) == 0) {
    return false;
  }
  lVar5 = *(long *)(param_1 + 0x20);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  cVar1 = *(char *)(lVar5 + 0x41);
  func_0x010eae48(lVar5,0);
  if (cVar1 != '\0') {
    lVar5 = *(long *)(lVar5 + 0x48);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    sVar2 = *(short *)(lVar5 + 200);
    func_0x010eae48(lVar5,0);
    if (sVar2 == 1) {
      bVar3 = true;
    }
    else {
      sVar2 = *(short *)(lVar5 + 200);
      func_0x010eae48();
      bVar3 = sVar2 == 2;
    }
    return bVar3;
  }
  return false;
}


