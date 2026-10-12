// name: Chat.Chatting$$GetFirstCoolTime
// entry: 01d2c718

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

int Chat_Chatting__GetFirstCoolTime(double param_1,long param_2,int param_3)

{
  int iVar1;
  long lVar2;
  double dVar3;
  
  if ((bRam00000000049fcf4c & 1) == 0) {
    func_0x010c65d4(0x25cc);
    bRam00000000049fcf4c = 1;
  }
  if (param_3 == 0) {
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
    if (0 < iVar1) {
      lVar2 = *(long *)(param_2 + 0x88);
      if (lVar2 == 0) {
        func_0x010eec64(0);
      }
      dVar3 = (double)System_Collections_Generic_List<double>__get_Item
                                (lVar2,0,
                                 _mm_Method_System_Collections_Generic_List<double>_get_Item__);
      if (((*(byte *)(_class_UnityEngine_Mathf_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Mathf_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      iVar1 = UnityEngine_Mathf__Max(0,(int)((dVar3 - param_1) + 0.5),0,0);
      return iVar1 / 1000;
    }
  }
  return 0;
}


