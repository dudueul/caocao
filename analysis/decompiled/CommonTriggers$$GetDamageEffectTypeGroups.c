// name: CommonTriggers$$GetDamageEffectTypeGroups
// entry: 01c34078

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 CommonTriggers__GetDamageEffectTypeGroups(long param_1,int param_2)

{
  int iVar1;
  int iVar2;
  undefined8 uVar3;
  long lVar4;
  
  if ((bRam00000000049fc718 & 1) == 0) {
    func_0x010c65d4(0x2c14);
    bRam00000000049fc718 = 1;
  }
  iVar2 = 0;
  uVar3 = 0;
  while( true ) {
    lVar4 = *(long *)(param_1 + 0x38);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    iVar1 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                      (lVar4,_mm_Method_System_Collections_Generic_List<TriggerGroups>_get_Count__);
    if (iVar1 <= iVar2) break;
    lVar4 = *(long *)(param_1 + 0x38);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    lVar4 = System_Collections_Generic_List<object>__get_Item
                      (lVar4,iVar2,
                       _mm_Method_System_Collections_Generic_List<TriggerGroups>_get_Item__);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    if (*(int *)(lVar4 + 0x18) == param_2) {
      lVar4 = *(long *)(param_1 + 0x38);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      uVar3 = System_Collections_Generic_List<object>__get_Item
                        (lVar4,iVar2,
                         _mm_Method_System_Collections_Generic_List<TriggerGroups>_get_Item__);
    }
    iVar2 = iVar2 + 1;
  }
  return uVar3;
}


