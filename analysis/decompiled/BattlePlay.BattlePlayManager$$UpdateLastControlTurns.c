// name: BattlePlay.BattlePlayManager$$UpdateLastControlTurns
// entry: 019775cc

/* WARNING: Removing unreachable block (ram,0x01977850) */
/* WARNING: Removing unreachable block (ram,0x01977854) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__UpdateLastControlTurns(long param_1,long param_2)

{
  uint uVar1;
  ulong uVar2;
  long lVar3;
  undefined8 *puVar4;
  long lVar5;
  int iVar6;
  long lVar7;
  undefined8 uStack_70;
  undefined8 uStack_68;
  undefined8 uStack_60;
  undefined8 uStack_58;
  undefined8 uStack_50;
  undefined8 uStack_48;
  
  puVar4 = &uStack_70;
  if ((bRam00000000049fae0c & 1) == 0) {
    func_0x010c65d4(0x2073);
    bRam00000000049fae0c = 1;
  }
  uStack_50 = 0;
  uStack_48 = 0;
  uStack_60 = 0;
  uStack_58 = 0;
  uStack_70 = 0;
  uStack_68 = 0;
  if (*(long *)(param_1 + 0x18) == 0) {
    return;
  }
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar1 = UnityEngine_Object__op_Equality(0,param_2,0,0);
  if ((uVar1 & 1) == 0) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar2 = UnitData__get_isDead(param_2,0);
    if ((uVar2 & 1) == 0) {
      lVar5 = *(long *)(param_1 + 0x18);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      lVar5 = *(long *)(lVar5 + 0x28);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_List<object>__GetEnumerator
                (&uStack_70,lVar5,
                 _mm_Method_System_Collections_Generic_List<BattleUser>_GetEnumerator__);
      while (uVar1 = func_0x027e6418(&uStack_70,
                                     _mm_Method_System_Collections_Generic_List_Enumerator<BattleUser>_MoveNext__
                                    ), (uVar1 & 1) != 0) {
        lVar5 = func_0x027e64f4(&uStack_70,
                                _mm_Method_System_Collections_Generic_List_Enumerator<BattleUser>_get_Current__
                               );
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        lVar7 = *(long *)(lVar5 + 0x10);
        if (param_2 == 0) {
          func_0x010eec64(0);
        }
        lVar3 = UnitData__get_uid(param_2,0);
        if (lVar7 != lVar3) {
          if (lVar5 == 0) {
            func_0x010eec64(0);
            iVar6 = iRam00000000000000ac;
            func_0x010eec64(0);
          }
          else {
            iVar6 = *(int *)(lVar5 + 0xac);
          }
          *(int *)(lVar5 + 0xac) = iVar6 + 1;
        }
      }
      goto code_r0x01977848;
    }
  }
  lVar5 = *(long *)(param_1 + 0x18);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  lVar5 = *(long *)(lVar5 + 0x28);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_List<object>__GetEnumerator
            (&uStack_58,lVar5,_mm_Method_System_Collections_Generic_List<BattleUser>_GetEnumerator__
            );
  while (uVar1 = func_0x027e6418(&uStack_58,
                                 _mm_Method_System_Collections_Generic_List_Enumerator<BattleUser>_MoveNext__
                                ), (uVar1 & 1) != 0) {
    lVar5 = func_0x027e64f4(&uStack_58,
                            _mm_Method_System_Collections_Generic_List_Enumerator<BattleUser>_get_Current__
                           );
    if (lVar5 == 0) {
      func_0x010eec64(0);
      iVar6 = iRam00000000000000ac;
      func_0x010eec64(0);
    }
    else {
      iVar6 = *(int *)(lVar5 + 0xac);
    }
    *(int *)(lVar5 + 0xac) = iVar6 + 1;
  }
  puVar4 = &uStack_58;
code_r0x01977848:
  func_0x027e6294(puVar4,_mm_Method_System_Collections_Generic_List_Enumerator<BattleUser>_Dispose__
                 );
  return;
}


