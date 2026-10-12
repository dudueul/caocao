// name: Chat.Chatting$$CleanCoolTime
// entry: 01d2c54c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__CleanCoolTime(double param_1,long param_2)

{
  int iVar1;
  long lVar2;
  double dVar3;
  
  if ((bRam00000000049fcf4b & 1) == 0) {
    func_0x010c65d4(0x25b6);
    bRam00000000049fcf4b = 1;
  }
  lVar2 = *(long *)(param_2 + 0x88);
  if (lVar2 == 0) {
    lVar2 = func_0x010fdda8(_class_System_Collections_Generic_List<double>_TypeInfo);
    System_Collections_Generic_List<double>___ctor
              (lVar2,_mm_Method_System_Collections_Generic_List<double>__ctor__);
    *(long *)(param_2 + 0x88) = lVar2;
    if (lVar2 == 0) {
      func_0x010eec64(0);
      lVar2 = 0;
    }
  }
  iVar1 = System_Collections_Generic_List<double>__get_Count
                    (lVar2,_mm_Method_System_Collections_Generic_List<double>_get_Count__);
  while (iVar1 = iVar1 + -1, -1 < iVar1) {
    lVar2 = *(long *)(param_2 + 0x88);
    if (lVar2 == 0) {
      func_0x010eec64(0);
    }
    dVar3 = (double)System_Collections_Generic_List<double>__get_Item
                              (lVar2,iVar1,
                               _mm_Method_System_Collections_Generic_List<double>_get_Item__);
    if (dVar3 < param_1) {
      lVar2 = *(long *)(param_2 + 0x88);
      if (lVar2 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_List<double>__RemoveAt
                (lVar2,iVar1,_mm_Method_System_Collections_Generic_List<double>_RemoveAt__);
    }
  }
  return;
}


