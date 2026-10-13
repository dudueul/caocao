// name: CommonTriggers$$GetDamageEffectType
// entry: 01c34374

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined4 CommonTriggers__GetDamageEffectType(long param_1,int param_2,int param_3)

{
  int iVar1;
  int iVar2;
  long lVar3;
  
  if ((bRam00000000049fc71a & 1) == 0) {
    func_0x010c65d4(0x2c16);
    bRam00000000049fc71a = 1;
  }
  lVar3 = *(long *)(param_1 + 0x28);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  iVar1 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                    (lVar3,
                     _mm_Method_System_Collections_Generic_List<UnitID2DamageEffectType>_get_Count__
                    );
  if (0 < iVar1) {
    iVar2 = 0;
    do {
      lVar3 = *(long *)(param_1 + 0x28);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      lVar3 = System_Collections_Generic_List<object>__get_Item
                        (lVar3,iVar2,
                         _mm_Method_System_Collections_Generic_List<UnitID2DamageEffectType>_get_Item__
                        );
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      if (*(int *)(lVar3 + 0x10) == param_2) {
        lVar3 = *(long *)(param_1 + 0x28);
        if (lVar3 == 0) {
          func_0x010eec64(0);
        }
        lVar3 = System_Collections_Generic_List<object>__get_Item
                          (lVar3,iVar2,
                           _mm_Method_System_Collections_Generic_List<UnitID2DamageEffectType>_get_Item__
                          );
        if (lVar3 == 0) {
          func_0x010eec64(0);
        }
        return *(undefined4 *)(lVar3 + 0x14);
      }
      iVar2 = iVar2 + 1;
    } while (iVar2 < iVar1);
  }
  lVar3 = *(long *)(param_1 + 0x30);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  iVar2 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                    (lVar3,
                     _mm_Method_System_Collections_Generic_List<JobType2DamageEffectType>_get_Count__
                    );
  iVar1 = 0;
  if (0 < iVar2) {
    do {
      lVar3 = *(long *)(param_1 + 0x30);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      lVar3 = System_Collections_Generic_List<object>__get_Item
                        (lVar3,iVar1,
                         _mm_Method_System_Collections_Generic_List<JobType2DamageEffectType>_get_Item__
                        );
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      if (*(int *)(lVar3 + 0x18) == param_3) {
        lVar3 = *(long *)(param_1 + 0x30);
        if (lVar3 == 0) {
          func_0x010eec64(0);
        }
        lVar3 = System_Collections_Generic_List<object>__get_Item
                          (lVar3,iVar1,
                           _mm_Method_System_Collections_Generic_List<JobType2DamageEffectType>_get_Item__
                          );
        if (lVar3 == 0) {
          func_0x010eec64(0);
        }
        return *(undefined4 *)(lVar3 + 0x1c);
      }
      iVar1 = iVar1 + 1;
    } while (iVar1 < iVar2);
  }
  return 0;
}


