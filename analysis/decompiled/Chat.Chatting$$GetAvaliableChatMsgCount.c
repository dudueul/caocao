// name: Chat.Chatting$$GetAvaliableChatMsgCount
// entry: 01d2c664

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

int Chat_Chatting__GetAvaliableChatMsgCount(long param_1,int param_2)

{
  int iVar1;
  long lVar2;
  
  if ((bRam00000000049fcf4d & 1) == 0) {
    func_0x010c65d4(0x25c2);
    bRam00000000049fcf4d = 1;
  }
  if (param_2 == 0) {
    lVar2 = *(long *)(param_1 + 0x88);
    if (lVar2 == 0) {
      lVar2 = func_0x010fdda8(_class_System_Collections_Generic_List<double>_TypeInfo);
      System_Collections_Generic_List<double>___ctor
                (lVar2,_mm_Method_System_Collections_Generic_List<double>__ctor__);
      *(long *)(param_1 + 0x88) = lVar2;
      if (lVar2 == 0) {
        func_0x010eec64(0);
        lVar2 = 0;
      }
    }
    iVar1 = System_Collections_Generic_List<double>__get_Count
                      (lVar2,_mm_Method_System_Collections_Generic_List<double>_get_Count__);
    iVar1 = 5 - iVar1;
  }
  else {
    iVar1 = 0x7fffffff;
  }
  return iVar1;
}


