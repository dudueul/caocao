// name: FairRuleManager$$UpdateFairRule
// entry: 017484f8

/* WARNING: Removing unreachable block (ram,0x01748b34) */
/* WARNING: Removing unreachable block (ram,0x01748b38) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void FairRuleManager__UpdateFairRule(undefined8 param_1)

{
  uint uVar1;
  ulong uVar2;
  undefined8 uVar3;
  long lVar4;
  long lVar5;
  long lVar6;
  long lVar7;
  undefined8 uVar8;
  undefined1 auVar9 [16];
  undefined8 uStack_118;
  undefined8 uStack_110;
  undefined8 uStack_108;
  undefined8 uStack_100;
  undefined8 uStack_f8;
  undefined8 uStack_f0;
  undefined8 uStack_e8;
  undefined8 uStack_e0;
  undefined8 uStack_d8;
  undefined8 uStack_d0;
  undefined8 uStack_c8;
  undefined1 auStack_c0 [16];
  undefined8 uStack_b0;
  undefined8 uStack_a8;
  undefined8 uStack_a0;
  undefined8 uStack_98;
  undefined1 auStack_90 [16];
  undefined8 uStack_80;
  undefined8 uStack_78;
  undefined8 uStack_70;
  
  if ((bRam00000000049f9c0c & 1) == 0) {
    func_0x010c65d4(0x567c);
    bRam00000000049f9c0c = 1;
  }
  uStack_78 = 0;
  uStack_70 = 0;
  auStack_90._8_8_ = 0;
  uStack_80 = 0;
  auStack_90._0_8_ = 0;
  uStack_a0 = 0;
  uStack_98 = 0;
  uStack_b0 = 0;
  uStack_a8 = 0;
  auStack_c0._0_8_ = 0;
  auStack_c0._8_8_ = 0;
  uStack_d0 = 0;
  uStack_c8 = 0;
  uStack_e0 = 0;
  uStack_d8 = 0;
  uStack_f0 = 0;
  uStack_e8 = 0;
  uStack_f8 = 0;
  uVar2 = MyUserData__get_hasInstance(0,0);
  if ((uVar2 & 1) != 0) {
    uVar3 = FairRuleManager__GetFairRule(param_1);
    lVar4 = MyUserData__get_instance(0,0);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    lVar4 = UserData__get_units(lVar4,0);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    lVar4 = UserUnits__get_unitList(lVar4,0);
    lVar5 = func_0x010fdda8(_class_System_Collections_Generic_HashSet<ulong>_TypeInfo);
    System_Collections_Generic_HashSet<ulong>___ctor
              (lVar5,_mm_Method_System_Collections_Generic_HashSet<ulong>__ctor__);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_List<object>__GetEnumerator
              (&uStack_118,lVar4,
               _mm_Method_System_Collections_Generic_List<UserUnit>_GetEnumerator__);
    uStack_70 = uStack_108;
    uStack_78 = uStack_110;
    uStack_80 = uStack_118;
    while (uVar1 = func_0x027e6418(&uStack_80,
                                   _mm_Method_System_Collections_Generic_List_Enumerator<UserUnit>_MoveNext__
                                  ), (uVar1 & 1) != 0) {
      lVar6 = func_0x027e64f4(&uStack_80,
                              _mm_Method_System_Collections_Generic_List_Enumerator<UserUnit>_get_Current__
                             );
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      lVar7 = UserUnit__get_weaponTre(lVar6,0);
      if (lVar7 != 0) {
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        uVar8 = UserUnit__get_weaponTre(lVar6,0);
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        System_Collections_Generic_HashSet<ulong>__Add
                  (lVar5,uVar8,_mm_Method_System_Collections_Generic_HashSet<ulong>_Add__);
      }
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      lVar7 = UserUnit__get_armorTre(lVar6,0);
      if (lVar7 != 0) {
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        uVar8 = UserUnit__get_armorTre(lVar6,0);
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        System_Collections_Generic_HashSet<ulong>__Add
                  (lVar5,uVar8,_mm_Method_System_Collections_Generic_HashSet<ulong>_Add__);
      }
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      lVar7 = UserUnit__get_kitTre(lVar6,0);
      if (lVar7 != 0) {
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        uVar8 = UserUnit__get_kitTre(lVar6,0);
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        System_Collections_Generic_HashSet<ulong>__Add
                  (lVar5,uVar8,_mm_Method_System_Collections_Generic_HashSet<ulong>_Add__);
      }
    }
    func_0x027e6294(&uStack_80,
                    _mm_Method_System_Collections_Generic_List_Enumerator<UserUnit>_Dispose__);
    lVar6 = MyUserData__get_instance(0,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = UserData__get_items(lVar6,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = UserItems__get_srl2Item(lVar6,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_Dictionary<ulong,_object>__GetEnumerator
              (&uStack_118,lVar6,
               _mm_Method_System_Collections_Generic_Dictionary<ulong,_UserItem>_GetEnumerator__);
    uStack_a8 = uStack_110;
    uStack_b0 = uStack_118;
    uStack_98 = uStack_100;
    uStack_a0 = uStack_108;
    while (uVar1 = func_0x01e18c68(&uStack_b0,
                                   _mm_Method_System_Collections_Generic_Dictionary_Enumerator<ulong,_UserItem>_MoveNext__
                                  ), (uVar1 & 1) != 0) {
      auVar9 = func_0x01e18e0c(&uStack_b0,
                               _mm_Method_System_Collections_Generic_Dictionary_Enumerator<ulong,_UserItem>_get_Current__
                              );
      auStack_90 = auVar9;
      lVar6 = func_0x025b1250(auStack_90,
                              _mm_Method_System_Collections_Generic_KeyValuePair<ulong,_UserItem>_get_Value__
                             );
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      uVar1 = UserItem__get_isTreasure(lVar6,0);
      if ((uVar1 & 1) != 0) {
        lVar6 = func_0x025b1250(auStack_90,
                                _mm_Method_System_Collections_Generic_KeyValuePair<ulong,_UserItem>_get_Value__
                               );
        uVar8 = func_0x025b1238(auStack_90,
                                _mm_Method_System_Collections_Generic_KeyValuePair<ulong,_UserItem>_get_Key__
                               );
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        uVar1 = System_Collections_Generic_HashSet<ulong>__Contains
                          (lVar5,uVar8,
                           _mm_Method_System_Collections_Generic_HashSet<ulong>_Contains__);
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        UserItem__UpdateFairRule(lVar6,uVar1 & 1,uVar3,0);
      }
    }
    func_0x01e190dc(&uStack_b0,
                    _mm_Method_System_Collections_Generic_Dictionary_Enumerator<ulong,_UserItem>_Dispose__
                   );
    lVar5 = MyUserData__get_instance(0,0);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    lVar5 = UserData__get_userRuneSlots(lVar5,0);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    lVar5 = UserRuneSlots__get_srl2RuneSlots(lVar5,0);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_Dictionary<ulong,_object>__GetEnumerator
              (&uStack_e0,lVar5,
               _mm_Method_System_Collections_Generic_Dictionary<ulong,_UserRuneSlot>_GetEnumerator__
              );
    while (uVar1 = func_0x01e18c68(&uStack_e0,
                                   _mm_Method_System_Collections_Generic_Dictionary_Enumerator<ulong,_UserRuneSlot>_MoveNext__
                                  ), (uVar1 & 1) != 0) {
      auVar9 = func_0x01e18e0c(&uStack_e0,
                               _mm_Method_System_Collections_Generic_Dictionary_Enumerator<ulong,_UserRuneSlot>_get_Current__
                              );
      auStack_c0 = auVar9;
      lVar5 = func_0x025b1250(auStack_c0,
                              _mm_Method_System_Collections_Generic_KeyValuePair<ulong,_UserRuneSlot>_get_Value__
                             );
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      UserRuneSlot__UpdateFairRule(lVar5,uVar3,0);
    }
    func_0x01e190dc(&uStack_e0,
                    _mm_Method_System_Collections_Generic_Dictionary_Enumerator<ulong,_UserRuneSlot>_Dispose__
                   );
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_List<object>__GetEnumerator
              (&uStack_f8,lVar4,_mm_Method_System_Collections_Generic_List<UserUnit>_GetEnumerator__
              );
    while (uVar1 = func_0x027e6418(&uStack_f8,
                                   _mm_Method_System_Collections_Generic_List_Enumerator<UserUnit>_MoveNext__
                                  ), (uVar1 & 1) != 0) {
      lVar4 = func_0x027e64f4(&uStack_f8,
                              _mm_Method_System_Collections_Generic_List_Enumerator<UserUnit>_get_Current__
                             );
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      UserUnit__UpdateRuneStat(lVar4,0);
    }
    func_0x027e6294(&uStack_f8,
                    _mm_Method_System_Collections_Generic_List_Enumerator<UserUnit>_Dispose__);
  }
  return;
}


