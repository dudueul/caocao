// name: Chat.Chatting$$RemoveNotification
// entry: 01d2ceb0

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

bool Chat_Chatting__RemoveNotification(long param_1,undefined8 param_2)

{
  long lVar1;
  int iVar2;
  long lVar3;
  
  if ((bRam00000000049fcf23 & 1) == 0) {
    func_0x010c65d4(0x25f3);
    bRam00000000049fcf23 = 1;
  }
  lVar1 = _mm_Method_System_Collections_Generic_List<Notification>_Remove__;
  lVar3 = *(long *)(param_1 + 0x78);
  if (lVar3 != 0) {
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    iVar2 = (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar1 + 0x18) + 0xc0) + 0x58))
                      (lVar3,param_2);
    if (iVar2 != -1) {
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar1 + 0x18) + 0xc0) + 0x148))(lVar3,iVar2);
    }
    return iVar2 != -1;
  }
  return false;
}


