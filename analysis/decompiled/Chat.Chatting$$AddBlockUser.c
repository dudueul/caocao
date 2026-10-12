// name: Chat.Chatting$$AddBlockUser
// entry: 01d2fd78

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__AddBlockUser(long param_1,long param_2)

{
  int iVar1;
  uint uVar2;
  undefined8 uVar3;
  long lVar4;
  long lVar5;
  long lVar6;
  
  if ((bRam00000000049fcf47 & 1) == 0) {
    func_0x010c65d4(0x25aa);
    bRam00000000049fcf47 = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar3 = Chat_ChatBlockUser__get_userId(param_2);
  uVar2 = Chat_Chatting__IsBlockUser(param_1,uVar3);
  if ((uVar2 & 1) != 0) {
    return;
  }
  lVar4 = *(long *)(param_1 + 0x50);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  lVar5 = _mm_Method_System_Collections_Generic_List<ChatBlockUser>_Add__;
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
  *(long *)(lVar5 + (long)(int)uVar2 * 8 + 0x20) = param_2;
  *(int *)(lVar4 + 0x1c) = *(int *)(lVar4 + 0x1c) + 1;
  return;
}


