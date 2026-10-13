// name: FairRuleManager$$GetFairRule
// entry: 017493d4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 FairRuleManager__GetFairRule(long param_1,undefined4 param_2)

{
  undefined4 uVar1;
  uint uVar2;
  uint uVar3;
  int iVar4;
  undefined4 uVar5;
  uint uVar6;
  long lVar7;
  ulong uVar8;
  undefined8 uVar9;
  undefined8 *puVar10;
  int *piVar11;
  uint *puVar12;
  long lVar13;
  long *plVar14;
  long lVar15;
  long lVar16;
  long unaff_x25;
  uint unaff_w27;
  undefined1 auVar17 [16];
  
  lVar7 = param_1;
  if ((bRam00000000049f9c12 & 1) == 0) {
    lVar7 = func_0x010c65d4(0x5670);
    bRam00000000049f9c12 = 1;
  }
  uVar5 = FairRuleManager__GetTierGrade(lVar7,param_2);
  uVar6 = FairRuleManager__WillApplyFairRule(param_1,param_2);
  if ((uVar6 & 1) == 0) {
    if ((bRam00000000049f9c1e & 1) == 0) {
      func_0x010c65d4(0x566c);
      bRam00000000049f9c1e = 1;
    }
    lVar7 = *(long *)(param_1 + 0x38);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    uVar6 = System_Collections_Generic_Dictionary<int,_tk2dFontChar>__ContainsKey
                      (lVar7,uVar5,
                       _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_ContainsKey__
                      );
    if ((uVar6 & 1) == 0) {
      lVar13 = *(long *)(param_1 + 0x38);
      lVar7 = func_0x010fdda8(_class_FairRule_TypeInfo);
      System_Object___ctor(lVar7,0);
      if (lVar7 == 0) {
        func_0x010eec64(0);
        uRam0000000000000010 = uVar5;
        func_0x010eec64(0);
      }
      else {
        *(undefined4 *)(lVar7 + 0x10) = uVar5;
      }
      *(undefined4 *)(lVar7 + 0x14) = 0xc;
      if (((*(byte *)(_class_ClientConst_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_ClientConst_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar15 = *(long *)(*(long *)(_class_ClientConst_TypeInfo + 0xb8) + 0x1c0);
      if (lVar15 == 0) {
        func_0x010eec64(0);
        func_0x010eec64(0);
      }
      lVar16 = (*(long *)(lVar15 + 0x18) << 0x20) + -0x100000000;
      if ((uint)*(long *)(lVar15 + 0x18) <= (uint)((ulong)lVar16 >> 0x20)) {
        uVar9 = func_0x010ef850();
        func_0x010eebec(uVar9,0,0);
      }
      uVar1 = *(undefined4 *)(lVar15 + (lVar16 >> 0x20) * 4 + 0x20);
      if (lVar7 == 0) {
        func_0x010eec64(0);
        uRam0000000000000018 = uVar1;
        func_0x010eec64(0);
        uRam000000000000001c = 1;
        func_0x010eec64(0);
        uRam000000000000001d = 1;
        func_0x010eec64(0);
      }
      else {
        *(undefined4 *)(lVar7 + 0x18) = uVar1;
        *(undefined2 *)(lVar7 + 0x1c) = 0x101;
      }
      *(undefined4 *)(lVar7 + 0x20) = 5;
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      *(undefined8 *)(lVar7 + 0x28) = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
      if (lVar13 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_Dictionary<int,_UserUnitRelation>__Add
                (lVar13,uVar5,lVar7,
                 _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_Add__);
    }
    lVar7 = *(long *)(param_1 + 0x38);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    lVar13 = _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_get_Item__;
    if ((bRam0000000004a082c7 & 1) == 0) {
      func_0x010c65d4(0x40f3);
      bRam0000000004a082c7 = 1;
    }
    plVar14 = *(long **)(lVar7 + 0x40);
    if (plVar14 == (long *)0x0) {
      func_0x010eec64(0);
    }
    lVar16 = *(long *)(*(long *)(*(long *)(lVar13 + 0x18) + 0xc0) + 0x118);
    func_0x010bbb2c(lVar16);
    lVar15 = *plVar14;
    if ((ulong)*(ushort *)(lVar15 + 0x122) != 0) {
      uVar8 = 0;
      piVar11 = (int *)(*(long *)(lVar15 + 0xb0) + 8);
      do {
        if (*(long *)(piVar11 + -2) == lVar16) {
          puVar10 = (undefined8 *)(lVar15 + (ulong)(*piVar11 + 1) * 0x10 + 0x130);
          goto code_r0x03188408;
        }
        uVar8 = uVar8 + 1;
        piVar11 = piVar11 + 4;
      } while (uVar8 < *(ushort *)(lVar15 + 0x122));
    }
    puVar10 = (undefined8 *)func_0x010bc584(plVar14,lVar16,1);
code_r0x03188408:
    uVar6 = (*(code *)*puVar10)(plVar14,uVar5,puVar10[1]);
    lVar15 = *(long *)(lVar7 + 0x10);
    if (lVar15 == 0) {
      func_0x010eec64(0);
      func_0x010eec64(0);
    }
    uVar2 = *(uint *)(lVar15 + 0x18);
    iVar4 = 0;
    if (uVar2 != 0) {
      iVar4 = (int)(uVar6 & 0x7fffffff) / (int)uVar2;
    }
    uVar3 = (uVar6 & 0x7fffffff) - iVar4 * uVar2;
    if (uVar2 <= uVar3) {
      uVar9 = func_0x010ef850();
      func_0x010eebec(uVar9,0,0);
    }
    iVar4 = *(int *)(lVar15 + (ulong)uVar3 * 4 + 0x20);
    if (iVar4 != 0) {
      unaff_w27 = iVar4 - 1;
      do {
        lVar15 = *(long *)(lVar7 + 0x18);
        if (lVar15 == 0) {
          func_0x010eec64(0);
        }
        unaff_x25 = (long)(int)unaff_w27;
        if (*(uint *)(lVar15 + 0x18) <= unaff_w27) {
          uVar9 = func_0x010ef850();
          func_0x010eebec(uVar9,0,0);
        }
        if (*(uint *)(lVar15 + unaff_x25 * 8 + 0x20) == (uVar6 | 0x80000000)) {
          plVar14 = *(long **)(lVar7 + 0x40);
          lVar15 = *(long *)(lVar7 + 0x20);
          if (lVar15 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar15 + 0x18) <= unaff_w27) {
            uVar9 = func_0x010ef850();
            func_0x010eebec(uVar9,0,0);
          }
          uVar1 = *(undefined4 *)(lVar15 + unaff_x25 * 4 + 0x20);
          if (plVar14 == (long *)0x0) {
            func_0x010eec64(0);
          }
          lVar16 = *(long *)(*(long *)(*(long *)(lVar13 + 0x18) + 0xc0) + 0x118);
          func_0x010bbb2c(lVar16);
          lVar15 = *plVar14;
          if ((ulong)*(ushort *)(lVar15 + 0x122) != 0) {
            uVar8 = 0;
            puVar12 = (uint *)(*(long *)(lVar15 + 0xb0) + 8);
            do {
              if (*(long *)(puVar12 + -2) == lVar16) {
                puVar10 = (undefined8 *)(lVar15 + (ulong)*puVar12 * 0x10 + 0x130);
                goto code_r0x0318855c;
              }
              uVar8 = uVar8 + 1;
              puVar12 = puVar12 + 4;
            } while (uVar8 < *(ushort *)(lVar15 + 0x122));
          }
          puVar10 = (undefined8 *)func_0x010bc584(plVar14,lVar16,0);
code_r0x0318855c:
          uVar8 = (*(code *)*puVar10)(plVar14,uVar1,uVar5,puVar10[1]);
          if ((uVar8 & 1) != 0) goto code_r0x031885e8;
        }
        lVar15 = *(long *)(lVar7 + 0x18);
        if (lVar15 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar15 + 0x18) <= unaff_w27) {
          uVar9 = func_0x010ef850();
          func_0x010eebec(uVar9,0,0);
        }
        unaff_w27 = *(uint *)(lVar15 + unaff_x25 * 8 + 0x24);
      } while (unaff_w27 != 0xffffffff);
    }
    uVar9 = func_0x010fdda8(_class_System_Collections_Generic_KeyNotFoundException_TypeInfo);
    System_Collections_Generic_KeyNotFoundException___ctor(uVar9,0);
    func_0x010eebec(uVar9,0,_mm_Method_System_Collections_Generic_Dictionary<int,_object>_get_Item__
                   );
    func_0x0112148c();
code_r0x031885e8:
    lVar7 = *(long *)(lVar7 + 0x28);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar7 + 0x18) <= unaff_w27) {
      uVar9 = func_0x010ef850();
      func_0x010eebec(uVar9,0,0);
    }
    return *(undefined8 *)(lVar7 + unaff_x25 * 8 + 0x20);
  }
  lVar7 = *(long *)(param_1 + 0x28);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__ContainsKey
                    (lVar7,param_2,
                     _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_ContainsKey__
                    );
  if ((uVar8 & 1) != 0) {
    lVar7 = *(long *)(param_1 + 0x28);
    goto joined_r0x01749628;
  }
  lVar7 = *(long *)(param_1 + 0x20);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar6 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__ContainsKey
                    (lVar7,param_2,
                     _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_ContainsKey__
                    );
  if ((uVar6 & 1) == 0) {
    lVar7 = PermanentData__get_instance(0,0);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    lVar7 = PermanentData__get_gameModeRules(lVar7,0);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0x12a) & 1) != 0
        ) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0xd8) == 0))
    {
      func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo);
    }
    auVar17 = CodeStage_AntiCheat_ObscuredTypes_ObscuredInt__op_Implicit(0,param_2,0);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    lVar7 = GameModeRules__get_Item(lVar7,auVar17._0_8_,auVar17._8_8_ & 0xffffffff,0);
    if (lVar7 == 0) {
      return 0;
    }
    lVar13 = *(long *)(param_1 + 0x20);
    uVar9 = func_0x010fdda8(_class_FairRule_TypeInfo);
    FairRule___ctor(uVar9,lVar7,uVar5);
    if (lVar13 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__set_Item
              (lVar13,param_2,uVar9,
               _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_set_Item__
              );
  }
  lVar7 = *(long *)(param_1 + 0x20);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__ContainsKey
                    (lVar7,param_2,
                     _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_ContainsKey__
                    );
  if ((uVar8 & 1) == 0) {
code_r0x017495e8:
    lVar7 = *(long *)(param_1 + 0x20);
    uVar9 = FairRuleManager__GetDefaultFairRule(param_1,uVar5);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__set_Item
              (lVar7,param_2,uVar9,
               _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_set_Item__
              );
  }
  else {
    lVar7 = *(long *)(param_1 + 0x20);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    lVar7 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__get_Item
                      (lVar7,param_2,
                       _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_get_Item__
                      );
    if (lVar7 == 0) goto code_r0x017495e8;
  }
  lVar7 = *(long *)(param_1 + 0x20);
joined_r0x01749628:
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar9 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__get_Item
                    (lVar7,param_2,
                     _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_get_Item__
                    );
  return uVar9;
}


