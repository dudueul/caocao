// name: Chat.Chatting$$get_ChattingUsers
// entry: 01d27d58

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 Chat_Chatting__get_ChattingUsers(long param_1)

{
  long lVar1;
  long lVar2;
  undefined8 uVar3;
  
  if ((bRam00000000049fcef6 & 1) == 0) {
    func_0x010c65d4(0x260e);
    bRam00000000049fcef6 = 1;
  }
  lVar2 = *(long *)(param_1 + 0x60);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  lVar1 = _mm_Method_System_Collections_Generic_Dictionary<string,_ChattingUser>_get_Values__;
  uVar3 = *(undefined8 *)
           (*(long *)(*(long *)(
                               _mm_Method_System_Collections_Generic_Dictionary<string,_ChattingUser>_get_Values__
                               + 0x18) + 0xc0) + 0x1b0);
  func_0x010bbb2c(uVar3);
  uVar3 = func_0x010fdda8(uVar3);
  (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar1 + 0x18) + 0xc0) + 0x1b8))(uVar3,lVar2);
  return uVar3;
}


