// name: FairRuleManager$$SetFairRule
// entry: 01749200

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void FairRuleManager__SetFairRule(long param_1,undefined4 param_2,long param_3)

{
  undefined4 uVar1;
  uint uVar2;
  uint uVar3;
  undefined8 uVar4;
  ulong uVar5;
  undefined8 *puVar6;
  ulong uVar7;
  ulong uVar8;
  int iVar9;
  long lVar10;
  int iVar11;
  ulong uVar12;
  int *piVar13;
  uint *puVar14;
  uint uVar15;
  long lVar16;
  long lVar17;
  long *plVar18;
  long lVar19;
  long lVar20;
  uint uVar21;
  uint uVar22;
  undefined1 auVar23 [16];
  undefined1 auVar24 [12];
  uint uStack_74;
  uint uStack_6c;
  uint uStack_68;
  
  if ((bRam00000000049f9c0e & 1) == 0) {
    func_0x010c65d4(0x567a);
    bRam00000000049f9c0e = 1;
  }
  FairRuleManager__ClearOverriden(param_1,param_2);
  lVar16 = *(long *)(param_1 + 0x20);
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  uVar3 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__ContainsKey
                    (lVar16,param_2,
                     _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_ContainsKey__
                    );
  lVar16 = *(long *)(param_1 + 0x20);
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  if ((uVar3 & 1) == 0) {
    System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__Add
              (lVar16,param_2,param_3,
               _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_Add__
              );
  }
  else {
    System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__set_Item
              (lVar16,param_2,param_3,
               _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_set_Item__
              );
  }
  if (param_3 == 0) {
    func_0x010eec64(0);
  }
  uVar3 = *(uint *)(param_3 + 0x10);
  uVar8 = (ulong)uVar3;
  if ((bRam00000000049f9c0f & 1) == 0) {
    func_0x010c65d4(0x5678);
    bRam00000000049f9c0f = 1;
  }
  lVar16 = *(long *)(param_1 + 0x18);
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  uVar2 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__ContainsKey
                    (lVar16,param_2,
                     _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_Dictionary<int,_FairRule>>_ContainsKey__
                    );
  if ((uVar2 & 1) == 0) {
    lVar16 = *(long *)(param_1 + 0x18);
    uVar4 = func_0x010fdda8(_class_System_Collections_Generic_Dictionary<int,_FairRule>_TypeInfo);
    System_Collections_Generic_Dictionary<int,_WorldBossShopHistory>___ctor
              (uVar4,_mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>__ctor__);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__Add
              (lVar16,param_2,uVar4,
               _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_Dictionary<int,_FairRule>>_Add__
              );
  }
  lVar16 = *(long *)(param_1 + 0x18);
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  lVar16 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__get_Item
                     (lVar16,param_2,
                      _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_Dictionary<int,_FairRule>>_get_Item__
                     );
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  uVar5 = System_Collections_Generic_Dictionary<int,_tk2dFontChar>__ContainsKey
                    (lVar16,uVar8,
                     _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_ContainsKey__);
  lVar16 = *(long *)(param_1 + 0x18);
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  lVar16 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__get_Item
                     (lVar16,param_2,
                      _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_Dictionary<int,_FairRule>>_get_Item__
                     );
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  lVar17 = _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_set_Item__;
  lVar19 = _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_Add__;
  if ((uVar5 & 1) == 0) {
    if ((bRam0000000004a082cd & 1) == 0) {
      func_0x010c65d4(0x3827);
      bRam0000000004a082cd = 1;
    }
    plVar18 = *(long **)(lVar16 + 0x40);
    if (plVar18 == (long *)0x0) {
      func_0x010eec64(0);
    }
    lVar10 = *(long *)(*(long *)(*(long *)(lVar19 + 0x18) + 0xc0) + 0x118);
    func_0x010bbb2c(lVar10);
    lVar17 = *plVar18;
    if ((ulong)*(ushort *)(lVar17 + 0x122) != 0) {
      uVar5 = 0;
      piVar13 = (int *)(*(long *)(lVar17 + 0xb0) + 8);
      do {
        if (*(long *)(piVar13 + -2) == lVar10) {
          puVar6 = (undefined8 *)(lVar17 + (ulong)(*piVar13 + 1) * 0x10 + 0x130);
          goto code_r0x03189650;
        }
        uVar5 = uVar5 + 1;
        piVar13 = piVar13 + 4;
      } while (uVar5 < *(ushort *)(lVar17 + 0x122));
    }
    puVar6 = (undefined8 *)func_0x010bc584(plVar18,lVar10,1);
code_r0x03189650:
    uVar2 = (*(code *)*puVar6)(plVar18,uVar8,puVar6[1]);
    lVar17 = *(long *)(lVar16 + 0x10);
    if (lVar17 == 0) {
      func_0x010eec64(0);
      lVar17 = *(long *)(lVar16 + 0x10);
      if (lVar17 == 0) {
code_r0x03189a8c:
        lVar16 = func_0x010eec64(0);
        lVar19 = *(long *)(lVar16 + 0x10);
        *(undefined4 *)(lVar16 + 0x38) = 0;
        if (lVar19 == 0) {
          func_0x010eec64(0);
        }
        System_Array__Clear(0,lVar19,0,*(undefined4 *)(lVar19 + 0x18),0);
        lVar19 = *(long *)(lVar16 + 0x20);
        if (lVar19 == 0) {
          func_0x010eec64(0);
        }
        System_Array__Clear(0,lVar19,0,*(undefined4 *)(lVar19 + 0x18),0);
        lVar19 = *(long *)(lVar16 + 0x28);
        if (lVar19 == 0) {
          func_0x010eec64(0);
        }
        System_Array__Clear(0,lVar19,0,*(undefined4 *)(lVar19 + 0x18),0);
        lVar19 = *(long *)(lVar16 + 0x18);
        if (lVar19 == 0) {
          func_0x010eec64(0);
        }
        System_Array__Clear(0,lVar19,0,*(undefined4 *)(lVar19 + 0x18),0);
        *(undefined8 *)(lVar16 + 0x30) = 0xffffffff00000000;
        *(int *)(lVar16 + 0x50) = *(int *)(lVar16 + 0x50) + 1;
        return;
      }
      iVar9 = 0;
      if (iRam0000000000000018 != 0) {
        iVar9 = (int)(uVar2 & 0x7fffffff) / iRam0000000000000018;
      }
      uVar22 = (uVar2 & 0x7fffffff) - iVar9 * iRam0000000000000018;
    }
    else {
      iVar9 = *(int *)(lVar17 + 0x18);
      iVar11 = 0;
      if (iVar9 != 0) {
        iVar11 = (int)(uVar2 & 0x7fffffff) / iVar9;
      }
      uVar22 = (uVar2 & 0x7fffffff) - iVar11 * iVar9;
    }
    uStack_6c = uVar2 & 0x7fffffff;
    if (*(uint *)(lVar17 + 0x18) <= uVar22) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    iVar9 = *(int *)(lVar17 + (ulong)uVar22 * 4 + 0x20);
    if (iVar9 != 0) {
      uVar15 = iVar9 - 1;
      do {
        lVar17 = *(long *)(lVar16 + 0x18);
        if (lVar17 == 0) {
          func_0x010eec64(0);
        }
        lVar10 = (long)(int)uVar15;
        if (*(uint *)(lVar17 + 0x18) <= uVar15) {
          uVar4 = func_0x010ef850();
          func_0x010eebec(uVar4,0,0);
        }
        if (*(uint *)(lVar17 + lVar10 * 8 + 0x20) == (uVar2 | 0x80000000)) {
          plVar18 = *(long **)(lVar16 + 0x40);
          lVar17 = *(long *)(lVar16 + 0x20);
          if (lVar17 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar17 + 0x18) <= uVar15) {
            uVar4 = func_0x010ef850();
            func_0x010eebec(uVar4,0,0);
          }
          uVar1 = *(undefined4 *)(lVar17 + lVar10 * 4 + 0x20);
          if (plVar18 == (long *)0x0) {
            func_0x010eec64(0);
          }
          lVar20 = *(long *)(*(long *)(*(long *)(lVar19 + 0x18) + 0xc0) + 0x118);
          func_0x010bbb2c(lVar20);
          lVar17 = *plVar18;
          if ((ulong)*(ushort *)(lVar17 + 0x122) != 0) {
            uVar5 = 0;
            puVar14 = (uint *)(*(long *)(lVar17 + 0xb0) + 8);
            do {
              if (*(long *)(puVar14 + -2) == lVar20) {
                puVar6 = (undefined8 *)(lVar17 + (ulong)*puVar14 * 0x10 + 0x130);
                goto code_r0x031897d0;
              }
              uVar5 = uVar5 + 1;
              puVar14 = puVar14 + 4;
            } while (uVar5 < *(ushort *)(lVar17 + 0x122));
          }
          puVar6 = (undefined8 *)func_0x010bc584(plVar18,lVar20,0);
code_r0x031897d0:
          uVar5 = (*(code *)*puVar6)(plVar18,uVar1,uVar8,puVar6[1]);
          if ((uVar5 & 1) != 0) {
            uVar4 = func_0x010fdda8(_class_System_ArgumentException_TypeInfo);
            System_ArgumentException___ctor
                      (uVar4,_str_An_element_with_the_same_key_already_exists_in_the_dictionary_,0);
            func_0x010eebec(uVar4,0,
                            _mm_Method_System_Collections_Generic_Dictionary<int,_object>_Add__);
            func_0x0112148c();
            goto code_r0x03189a8c;
          }
        }
        lVar17 = *(long *)(lVar16 + 0x18);
        if (lVar17 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar17 + 0x18) <= uVar15) {
          uVar4 = func_0x010ef850();
          func_0x010eebec(uVar4,0,0);
        }
        uVar15 = *(uint *)(lVar17 + lVar10 * 8 + 0x24);
      } while (uVar15 != 0xffffffff);
    }
    iVar9 = *(int *)(lVar16 + 0x38) + 1;
    *(int *)(lVar16 + 0x38) = iVar9;
    if (*(int *)(lVar16 + 0x3c) < iVar9) {
      (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar19 + 0x18) + 0xc0) + 0x120))(lVar16);
      lVar19 = *(long *)(lVar16 + 0x10);
      if (lVar19 == 0) {
        func_0x010eec64(0);
      }
      iVar9 = *(int *)(lVar19 + 0x18);
      iVar11 = 0;
      if (iVar9 != 0) {
        iVar11 = (int)uStack_6c / iVar9;
      }
      uVar22 = uStack_6c - iVar11 * iVar9;
    }
    uVar15 = *(uint *)(lVar16 + 0x34);
    if (uVar15 == 0xffffffff) {
      uVar15 = *(uint *)(lVar16 + 0x30);
      *(uint *)(lVar16 + 0x30) = uVar15 + 1;
    }
    else {
      lVar19 = *(long *)(lVar16 + 0x18);
      if (lVar19 == 0) {
        func_0x010eec64(0);
      }
      if (*(uint *)(lVar19 + 0x18) <= uVar15) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      *(undefined4 *)(lVar16 + 0x34) = *(undefined4 *)(lVar19 + (long)(int)uVar15 * 8 + 0x24);
    }
    lVar19 = *(long *)(lVar16 + 0x18);
    if (lVar19 == 0) {
      func_0x010eec64(0);
    }
    lVar17 = (long)(int)uVar15;
    if (*(uint *)(lVar19 + 0x18) <= uVar15) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    *(uint *)(lVar19 + lVar17 * 8 + 0x20) = uVar2 | 0x80000000;
    lVar19 = *(long *)(lVar16 + 0x18);
    if (lVar19 == 0) {
      func_0x010eec64(0);
    }
    lVar10 = *(long *)(lVar16 + 0x10);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar10 + 0x18) <= uVar22) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    iVar9 = *(int *)(lVar10 + (long)(int)uVar22 * 4 + 0x20);
    if (*(uint *)(lVar19 + 0x18) <= uVar15) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    *(int *)(lVar19 + lVar17 * 8 + 0x24) = iVar9 + -1;
    lVar19 = *(long *)(lVar16 + 0x10);
    if (lVar19 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar19 + 0x18) <= uVar22) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    *(uint *)(lVar19 + (long)(int)uVar22 * 4 + 0x20) = uVar15 + 1;
    lVar19 = *(long *)(lVar16 + 0x20);
    if (lVar19 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar19 + 0x18) <= uVar15) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    *(uint *)(lVar19 + lVar17 * 4 + 0x20) = uVar3;
    lVar19 = *(long *)(lVar16 + 0x28);
    if (lVar19 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar19 + 0x18) <= uVar15) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    *(long *)(lVar19 + lVar17 * 8 + 0x20) = param_3;
    *(int *)(lVar16 + 0x50) = *(int *)(lVar16 + 0x50) + 1;
    return;
  }
  lVar19 = _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_set_Item__;
  if ((bRam0000000004a082c8 & 1) == 0) {
    func_0x010c65d4(0x41ad);
    bRam0000000004a082c8 = 1;
  }
  plVar18 = *(long **)(lVar16 + 0x40);
  if (plVar18 == (long *)0x0) {
    func_0x010eec64(0);
  }
  lVar20 = *(long *)(*(long *)(*(long *)(lVar17 + 0x18) + 0xc0) + 0x118);
  func_0x010bbb2c(lVar20);
  lVar10 = *plVar18;
  if ((ulong)*(ushort *)(lVar10 + 0x122) != 0) {
    uVar5 = 0;
    piVar13 = (int *)(*(long *)(lVar10 + 0xb0) + 8);
    do {
      if (*(long *)(piVar13 + -2) == lVar20) {
        puVar6 = (undefined8 *)(lVar10 + (ulong)(*piVar13 + 1) * 0x10 + 0x130);
        goto code_r0x03188708;
      }
      uVar5 = uVar5 + 1;
      piVar13 = piVar13 + 4;
    } while (uVar5 < *(ushort *)(lVar10 + 0x122));
  }
  puVar6 = (undefined8 *)func_0x010bc584(plVar18,lVar20,1);
code_r0x03188708:
  uVar5 = puVar6[1];
  uVar2 = (*(code *)*puVar6)(plVar18,uVar8);
  lVar10 = *(long *)(lVar16 + 0x10);
  if (lVar10 == 0) {
    func_0x010eec64(0);
    lVar10 = *(long *)(lVar16 + 0x10);
    if (lVar10 == 0) {
code_r0x03188c54:
      auVar24 = func_0x010eec64(0);
      iVar9 = auVar24._8_4_;
      lVar16 = auVar24._0_8_;
      if ((bRam0000000004a082c9 & 1) == 0) {
        func_0x010c65d4(0x3be3);
        bRam0000000004a082c9 = 1;
      }
      if (-1 < iVar9) {
        if (uVar5 == 0) {
          lVar17 = *(long *)(*(long *)(*(long *)(lVar19 + 0x18) + 0xc0) + 0x130);
          func_0x010bbb2c(lVar17);
          if (((*(byte *)(lVar17 + 0x12a) & 1) != 0) &&
             (lVar17 = *(long *)(*(long *)(*(long *)(lVar19 + 0x18) + 0xc0) + 0x130),
             func_0x010bbb2c(lVar17), *(int *)(lVar17 + 0xd8) == 0)) {
            uVar4 = *(undefined8 *)(*(long *)(*(long *)(lVar19 + 0x18) + 0xc0) + 0x130);
            func_0x010bbb2c(uVar4);
            func_0x010da55c(uVar4);
          }
          uVar5 = (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar19 + 0x18) + 0xc0) + 0x128))
                            (0);
        }
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        iVar11 = 0xc;
        if (iVar9 != 0) {
          iVar11 = (int)((float)iVar9 / 0.9) + 1;
        }
        *(ulong *)(lVar16 + 0x40) = uVar5;
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar19 + 0x18) + 0xc0) + 0x138))
                  (lVar16,iVar11);
        *(undefined4 *)(lVar16 + 0x50) = 0;
        return;
      }
      uVar4 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
      System_ArgumentOutOfRangeException___ctor(uVar4,_str_capacity,0);
      lVar16 = _mm_Method_System_Collections_Generic_Dictionary<int,_object>_Init__;
      func_0x010eebec(uVar4,0);
      auVar23 = func_0x0112148c();
      lVar19 = auVar23._0_8_;
      uVar8 = auVar23._8_8_ & 0xffffffff;
      if ((bRam0000000004a082ca & 1) == 0) {
        func_0x010c65d4(0x3b62);
        bRam0000000004a082ca = 1;
      }
      uVar4 = func_0x01101ad4(_class_int___TypeInfo,uVar8);
      *(undefined8 *)(lVar19 + 0x10) = uVar4;
      uVar4 = func_0x01101ad4(_class_System_Collections_Generic_Link___TypeInfo,uVar8);
      *(undefined4 *)(lVar19 + 0x34) = 0xffffffff;
      *(undefined8 *)(lVar19 + 0x18) = uVar4;
      uVar4 = *(undefined8 *)(*(long *)(*(long *)(lVar16 + 0x18) + 0xc0) + 0x140);
      func_0x010bbb2c(uVar4);
      uVar4 = func_0x01101ad4(uVar4,uVar8);
      *(undefined8 *)(lVar19 + 0x20) = uVar4;
      uVar4 = *(undefined8 *)(*(long *)(*(long *)(lVar16 + 0x18) + 0xc0) + 0x148);
      func_0x010bbb2c(uVar4);
      uVar4 = func_0x01101ad4(uVar4,uVar8);
      lVar16 = *(long *)(lVar19 + 0x10);
      *(undefined8 *)(lVar19 + 0x28) = uVar4;
      *(undefined4 *)(lVar19 + 0x30) = 0;
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      iVar9 = (int)((float)*(int *)(lVar16 + 0x18) * 0.9);
      *(int *)(lVar19 + 0x3c) = iVar9;
      if (iVar9 == 0) {
        lVar16 = *(long *)(lVar19 + 0x10);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        if (0 < *(int *)(lVar16 + 0x18)) {
          *(undefined4 *)(lVar19 + 0x3c) = 1;
        }
      }
      return;
    }
    iVar9 = 0;
    if (iRam0000000000000018 != 0) {
      iVar9 = (int)(uVar2 & 0x7fffffff) / iRam0000000000000018;
    }
    uStack_68 = (uVar2 & 0x7fffffff) - iVar9 * iRam0000000000000018;
  }
  else {
    iVar9 = *(int *)(lVar10 + 0x18);
    iVar11 = 0;
    if (iVar9 != 0) {
      iVar11 = (int)(uVar2 & 0x7fffffff) / iVar9;
    }
    uStack_68 = (uVar2 & 0x7fffffff) - iVar11 * iVar9;
  }
  uStack_74 = uVar2 & 0x7fffffff;
  uVar12 = (ulong)uStack_68;
  if (*(uint *)(lVar10 + 0x18) <= uStack_68) {
    uVar4 = func_0x010ef850();
    func_0x010eebec(uVar4,0,0);
  }
  iVar9 = *(int *)(lVar10 + uVar12 * 4 + 0x20);
  if (iVar9 != 0) {
    uVar22 = iVar9 - 1;
    uVar15 = 0xffffffff;
    do {
      uVar21 = uVar22;
      lVar19 = *(long *)(lVar16 + 0x18);
      if (lVar19 == 0) {
        func_0x010eec64(0);
      }
      lVar10 = (long)(int)uVar21;
      if (*(uint *)(lVar19 + 0x18) <= uVar21) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      if (*(uint *)(lVar19 + lVar10 * 8 + 0x20) == (uVar2 | 0x80000000)) {
        plVar18 = *(long **)(lVar16 + 0x40);
        lVar19 = *(long *)(lVar16 + 0x20);
        if (lVar19 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar19 + 0x18) <= uVar21) {
          uVar4 = func_0x010ef850();
          func_0x010eebec(uVar4,0,0);
        }
        uVar1 = *(undefined4 *)(lVar19 + lVar10 * 4 + 0x20);
        if (plVar18 == (long *)0x0) {
          func_0x010eec64(0);
        }
        lVar20 = *(long *)(*(long *)(*(long *)(lVar17 + 0x18) + 0xc0) + 0x118);
        func_0x010bbb2c(lVar20);
        lVar19 = *plVar18;
        if ((ulong)*(ushort *)(lVar19 + 0x122) != 0) {
          uVar5 = 0;
          puVar14 = (uint *)(*(long *)(lVar19 + 0xb0) + 8);
          do {
            if (*(long *)(puVar14 + -2) == lVar20) {
              puVar6 = (undefined8 *)(lVar19 + (ulong)*puVar14 * 0x10 + 0x130);
              goto code_r0x03188894;
            }
            uVar5 = uVar5 + 1;
            puVar14 = puVar14 + 4;
          } while (uVar5 < *(ushort *)(lVar19 + 0x122));
        }
        puVar6 = (undefined8 *)func_0x010bc584(plVar18,lVar20,0);
code_r0x03188894:
        lVar19 = puVar6[1];
        uVar5 = uVar8;
        uVar7 = (*(code *)*puVar6)(plVar18,uVar1);
        if ((uVar7 & 1) != 0) {
          if (uVar21 != 0xffffffff) {
            if (uVar15 == 0xffffffff) goto code_r0x03188ac0;
            lVar20 = *(long *)(lVar16 + 0x18);
            lVar17 = lVar20;
            if (lVar20 == 0) {
              func_0x010eec64(0);
              lVar17 = *(long *)(lVar16 + 0x18);
              if (lVar17 == 0) goto code_r0x03188c54;
            }
            if (*(uint *)(lVar17 + 0x18) <= uVar21) {
              uVar4 = func_0x010ef850();
              func_0x010eebec(uVar4,0,0);
            }
            uVar1 = *(undefined4 *)(lVar17 + lVar10 * 8 + 0x24);
            if (*(uint *)(lVar20 + 0x18) <= uVar15) {
              uVar4 = func_0x010ef850();
              func_0x010eebec(uVar4,0,0);
            }
            *(undefined4 *)(lVar20 + (long)(int)uVar15 * 8 + 0x24) = uVar1;
            lVar19 = *(long *)(lVar16 + 0x18);
            if (lVar19 == 0) {
              func_0x010eec64(0);
            }
            lVar17 = *(long *)(lVar16 + 0x10);
            if (lVar17 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar17 + 0x18) <= uStack_68) {
              uVar4 = func_0x010ef850();
              func_0x010eebec(uVar4,0,0);
            }
            iVar9 = *(int *)(lVar17 + uVar12 * 4 + 0x20);
            if (*(uint *)(lVar19 + 0x18) <= uVar21) {
              uVar4 = func_0x010ef850();
              func_0x010eebec(uVar4,0,0);
            }
            *(int *)(lVar19 + lVar10 * 8 + 0x24) = iVar9 + -1;
            lVar19 = *(long *)(lVar16 + 0x10);
            if (lVar19 == 0) {
              func_0x010eec64(0);
            }
            uVar3 = uVar21 + 1;
            if (*(uint *)(lVar19 + 0x18) <= uStack_68) {
              uVar4 = func_0x010ef850();
              func_0x010eebec(uVar4,0,0);
            }
            lVar19 = lVar19 + uVar12 * 4;
            goto code_r0x03188abc;
          }
          break;
        }
      }
      lVar19 = *(long *)(lVar16 + 0x18);
      if (lVar19 == 0) {
        func_0x010eec64(0);
      }
      if (*(uint *)(lVar19 + 0x18) <= uVar21) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      uVar22 = *(uint *)(lVar19 + lVar10 * 8 + 0x24);
      uVar15 = uVar21;
    } while (uVar22 != 0xffffffff);
  }
  iVar9 = *(int *)(lVar16 + 0x38) + 1;
  *(int *)(lVar16 + 0x38) = iVar9;
  if (*(int *)(lVar16 + 0x3c) < iVar9) {
    (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar17 + 0x18) + 0xc0) + 0x120))(lVar16);
    lVar19 = *(long *)(lVar16 + 0x10);
    if (lVar19 == 0) {
      func_0x010eec64(0);
    }
    iVar9 = *(int *)(lVar19 + 0x18);
    iVar11 = 0;
    if (iVar9 != 0) {
      iVar11 = (int)uStack_74 / iVar9;
    }
    uStack_68 = uStack_74 - iVar11 * iVar9;
  }
  uVar21 = *(uint *)(lVar16 + 0x34);
  if (uVar21 == 0xffffffff) {
    uVar21 = *(uint *)(lVar16 + 0x30);
    *(uint *)(lVar16 + 0x30) = uVar21 + 1;
  }
  else {
    lVar19 = *(long *)(lVar16 + 0x18);
    if (lVar19 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar19 + 0x18) <= uVar21) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    *(undefined4 *)(lVar16 + 0x34) = *(undefined4 *)(lVar19 + (long)(int)uVar21 * 8 + 0x24);
  }
  lVar19 = *(long *)(lVar16 + 0x18);
  if (lVar19 == 0) {
    func_0x010eec64(0);
  }
  lVar17 = *(long *)(lVar16 + 0x10);
  if (lVar17 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar17 + 0x18) <= uStack_68) {
    uVar4 = func_0x010ef850();
    func_0x010eebec(uVar4,0,0);
  }
  iVar9 = *(int *)(lVar17 + (long)(int)uStack_68 * 4 + 0x20);
  lVar17 = (long)(int)uVar21;
  if (*(uint *)(lVar19 + 0x18) <= uVar21) {
    uVar4 = func_0x010ef850();
    func_0x010eebec(uVar4,0,0);
  }
  *(int *)(lVar19 + lVar17 * 8 + 0x24) = iVar9 + -1;
  lVar19 = *(long *)(lVar16 + 0x10);
  if (lVar19 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar19 + 0x18) <= uStack_68) {
    uVar4 = func_0x010ef850();
    func_0x010eebec(uVar4,0,0);
  }
  *(uint *)(lVar19 + (long)(int)uStack_68 * 4 + 0x20) = uVar21 + 1;
  lVar19 = *(long *)(lVar16 + 0x18);
  if (lVar19 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar19 + 0x18) <= uVar21) {
    uVar4 = func_0x010ef850();
    func_0x010eebec(uVar4,0,0);
  }
  *(uint *)(lVar19 + lVar17 * 8 + 0x20) = uVar2 | 0x80000000;
  lVar19 = *(long *)(lVar16 + 0x20);
  if (lVar19 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar19 + 0x18) <= uVar21) {
    uVar4 = func_0x010ef850();
    func_0x010eebec(uVar4,0,0);
  }
  lVar19 = lVar19 + lVar17 * 4;
code_r0x03188abc:
  *(uint *)(lVar19 + 0x20) = uVar3;
code_r0x03188ac0:
  lVar19 = *(long *)(lVar16 + 0x28);
  if (lVar19 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar19 + 0x18) <= uVar21) {
    uVar4 = func_0x010ef850();
    func_0x010eebec(uVar4,0,0);
  }
  *(long *)(lVar19 + (long)(int)uVar21 * 8 + 0x20) = param_3;
  *(int *)(lVar16 + 0x50) = *(int *)(lVar16 + 0x50) + 1;
  return;
}


