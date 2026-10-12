// name: Chat.Chatting$$GetChattingUser
// entry: 01d2ce28

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 Chat_Chatting__GetChattingUser(long param_1,undefined8 param_2)

{
  long lVar1;
  undefined8 uStack_38;
  
  if ((bRam00000000049fcf22 & 1) == 0) {
    func_0x010c65d4(0x25c8);
    bRam00000000049fcf22 = 1;
  }
  uStack_38 = 0;
  lVar1 = *(long *)(param_1 + 0x60);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_Dictionary<UserUnit,_Dictionary<Type,_SpActionBase>>__TryGetValue
            (lVar1,param_2,&uStack_38,
             _mm_Method_System_Collections_Generic_Dictionary<string,_ChattingUser>_TryGetValue__);
  return uStack_38;
}


