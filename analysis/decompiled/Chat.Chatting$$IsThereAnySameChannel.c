// name: Chat.Chatting$$IsThereAnySameChannel
// entry: 01d2f834

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 Chat_Chatting__IsThereAnySameChannel(long param_1,long param_2,long param_3)

{
  int iVar1;
  uint uVar2;
  undefined8 uVar3;
  ulong uVar4;
  long lVar5;
  int iVar6;
  long lVar7;
  long lStack_68;
  
  if ((bRam00000000049fcf40 & 1) == 0) {
    func_0x010c65d4(0x25d4);
    bRam00000000049fcf40 = 1;
  }
  lStack_68 = 0;
  if ((param_2 != 0) && (param_3 != 0)) {
    lVar5 = *(long *)(param_3 + 0x28);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    iVar1 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                      (lVar5,_mm_Method_System_Collections_Generic_List<string>_get_Count__);
    if (0 < iVar1) {
      iVar6 = 0;
      do {
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        uVar3 = System_Collections_Generic_List<object>__get_Item
                          (lVar5,iVar6,_mm_Method_System_Collections_Generic_List<string>_get_Item__
                          );
        lStack_68 = 0;
        lVar7 = *(long *)(param_1 + 0x68);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        uVar4 = System_Collections_Generic_Dictionary<UserUnit,_Dictionary<Type,_SpActionBase>>__TryGetValue
                          (lVar7,uVar3,&lStack_68,
                           _mm_Method_System_Collections_Generic_Dictionary<string,_HashSet<string>>_TryGetValue__
                          );
        lVar7 = lStack_68;
        if ((uVar4 & 1) != 0) {
          if (lStack_68 == 0) {
            func_0x010eec64(0);
          }
          uVar2 = System_Collections_Generic_HashSet<UserUnit>__Contains
                            (lVar7,uVar3,
                             _mm_Method_System_Collections_Generic_HashSet<string>_Contains__);
          if ((uVar2 & 1) != 0) {
            return 1;
          }
        }
        iVar6 = iVar6 + 1;
      } while (iVar6 < iVar1);
    }
  }
  return 0;
}


