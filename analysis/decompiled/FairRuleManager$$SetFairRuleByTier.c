// name: FairRuleManager$$SetFairRuleByTier
// entry: 01748f20

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void FairRuleManager__SetFairRuleByTier
               (long param_1,undefined4 param_2,uint param_3,undefined8 param_4)

{
  undefined4 uVar1;
  uint uVar2;
  undefined8 uVar3;
  ulong uVar4;
  undefined8 *puVar5;
  ulong uVar6;
  int iVar7;
  long lVar8;
  int iVar9;
  ulong uVar10;
  int *piVar11;
  uint *puVar12;
  uint uVar13;
  ulong uVar14;
  long lVar15;
  long lVar16;
  long *plVar17;
  long lVar18;
  long lVar19;
  uint uVar20;
  uint uVar21;
  undefined1 auVar22 [16];
  undefined1 auVar23 [12];
  uint uStack_74;
  uint uStack_6c;
  uint uStack_68;
  
  uVar14 = (ulong)param_3;
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
    uVar3 = func_0x010fdda8(_class_System_Collections_Generic_Dictionary<int,_FairRule>_TypeInfo);
    System_Collections_Generic_Dictionary<int,_WorldBossShopHistory>___ctor
              (uVar3,_mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>__ctor__);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__Add
              (lVar16,param_2,uVar3,
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
  uVar4 = System_Collections_Generic_Dictionary<int,_tk2dFontChar>__ContainsKey
                    (lVar16,uVar14,
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
  lVar15 = _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_set_Item__;
  lVar18 = _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_Add__;
  if ((uVar4 & 1) == 0) {
    if ((bRam0000000004a082cd & 1) == 0) {
      func_0x010c65d4(0x3827);
      bRam0000000004a082cd = 1;
    }
    plVar17 = *(long **)(lVar16 + 0x40);
    if (plVar17 == (long *)0x0) {
      func_0x010eec64(0);
    }
    lVar8 = *(long *)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x118);
    func_0x010bbb2c(lVar8);
    lVar15 = *plVar17;
    if ((ulong)*(ushort *)(lVar15 + 0x122) != 0) {
      uVar4 = 0;
      piVar11 = (int *)(*(long *)(lVar15 + 0xb0) + 8);
      do {
        if (*(long *)(piVar11 + -2) == lVar8) {
          puVar5 = (undefined8 *)(lVar15 + (ulong)(*piVar11 + 1) * 0x10 + 0x130);
          goto code_r0x03189650;
        }
        uVar4 = uVar4 + 1;
        piVar11 = piVar11 + 4;
      } while (uVar4 < *(ushort *)(lVar15 + 0x122));
    }
    puVar5 = (undefined8 *)func_0x010bc584(plVar17,lVar8,1);
code_r0x03189650:
    uVar2 = (*(code *)*puVar5)(plVar17,uVar14,puVar5[1]);
    lVar15 = *(long *)(lVar16 + 0x10);
    if (lVar15 == 0) {
      func_0x010eec64(0);
      lVar15 = *(long *)(lVar16 + 0x10);
      if (lVar15 == 0) {
code_r0x03189a8c:
        lVar16 = func_0x010eec64(0);
        lVar18 = *(long *)(lVar16 + 0x10);
        *(undefined4 *)(lVar16 + 0x38) = 0;
        if (lVar18 == 0) {
          func_0x010eec64(0);
        }
        System_Array__Clear(0,lVar18,0,*(undefined4 *)(lVar18 + 0x18),0);
        lVar18 = *(long *)(lVar16 + 0x20);
        if (lVar18 == 0) {
          func_0x010eec64(0);
        }
        System_Array__Clear(0,lVar18,0,*(undefined4 *)(lVar18 + 0x18),0);
        lVar18 = *(long *)(lVar16 + 0x28);
        if (lVar18 == 0) {
          func_0x010eec64(0);
        }
        System_Array__Clear(0,lVar18,0,*(undefined4 *)(lVar18 + 0x18),0);
        lVar18 = *(long *)(lVar16 + 0x18);
        if (lVar18 == 0) {
          func_0x010eec64(0);
        }
        System_Array__Clear(0,lVar18,0,*(undefined4 *)(lVar18 + 0x18),0);
        *(undefined8 *)(lVar16 + 0x30) = 0xffffffff00000000;
        *(int *)(lVar16 + 0x50) = *(int *)(lVar16 + 0x50) + 1;
        return;
      }
      iVar7 = 0;
      if (iRam0000000000000018 != 0) {
        iVar7 = (int)(uVar2 & 0x7fffffff) / iRam0000000000000018;
      }
      uVar21 = (uVar2 & 0x7fffffff) - iVar7 * iRam0000000000000018;
    }
    else {
      iVar7 = *(int *)(lVar15 + 0x18);
      iVar9 = 0;
      if (iVar7 != 0) {
        iVar9 = (int)(uVar2 & 0x7fffffff) / iVar7;
      }
      uVar21 = (uVar2 & 0x7fffffff) - iVar9 * iVar7;
    }
    uStack_6c = uVar2 & 0x7fffffff;
    if (*(uint *)(lVar15 + 0x18) <= uVar21) {
      uVar3 = func_0x010ef850();
      func_0x010eebec(uVar3,0,0);
    }
    iVar7 = *(int *)(lVar15 + (ulong)uVar21 * 4 + 0x20);
    if (iVar7 != 0) {
      uVar13 = iVar7 - 1;
      do {
        lVar15 = *(long *)(lVar16 + 0x18);
        if (lVar15 == 0) {
          func_0x010eec64(0);
        }
        lVar8 = (long)(int)uVar13;
        if (*(uint *)(lVar15 + 0x18) <= uVar13) {
          uVar3 = func_0x010ef850();
          func_0x010eebec(uVar3,0,0);
        }
        if (*(uint *)(lVar15 + lVar8 * 8 + 0x20) == (uVar2 | 0x80000000)) {
          plVar17 = *(long **)(lVar16 + 0x40);
          lVar15 = *(long *)(lVar16 + 0x20);
          if (lVar15 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar15 + 0x18) <= uVar13) {
            uVar3 = func_0x010ef850();
            func_0x010eebec(uVar3,0,0);
          }
          uVar1 = *(undefined4 *)(lVar15 + lVar8 * 4 + 0x20);
          if (plVar17 == (long *)0x0) {
            func_0x010eec64(0);
          }
          lVar19 = *(long *)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x118);
          func_0x010bbb2c(lVar19);
          lVar15 = *plVar17;
          if ((ulong)*(ushort *)(lVar15 + 0x122) != 0) {
            uVar4 = 0;
            puVar12 = (uint *)(*(long *)(lVar15 + 0xb0) + 8);
            do {
              if (*(long *)(puVar12 + -2) == lVar19) {
                puVar5 = (undefined8 *)(lVar15 + (ulong)*puVar12 * 0x10 + 0x130);
                goto code_r0x031897d0;
              }
              uVar4 = uVar4 + 1;
              puVar12 = puVar12 + 4;
            } while (uVar4 < *(ushort *)(lVar15 + 0x122));
          }
          puVar5 = (undefined8 *)func_0x010bc584(plVar17,lVar19,0);
code_r0x031897d0:
          uVar4 = (*(code *)*puVar5)(plVar17,uVar1,uVar14,puVar5[1]);
          if ((uVar4 & 1) != 0) {
            uVar3 = func_0x010fdda8(_class_System_ArgumentException_TypeInfo);
            System_ArgumentException___ctor
                      (uVar3,_str_An_element_with_the_same_key_already_exists_in_the_dictionary_,0);
            func_0x010eebec(uVar3,0,
                            _mm_Method_System_Collections_Generic_Dictionary<int,_object>_Add__);
            func_0x0112148c();
            goto code_r0x03189a8c;
          }
        }
        lVar15 = *(long *)(lVar16 + 0x18);
        if (lVar15 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar15 + 0x18) <= uVar13) {
          uVar3 = func_0x010ef850();
          func_0x010eebec(uVar3,0,0);
        }
        uVar13 = *(uint *)(lVar15 + lVar8 * 8 + 0x24);
      } while (uVar13 != 0xffffffff);
    }
    iVar7 = *(int *)(lVar16 + 0x38) + 1;
    *(int *)(lVar16 + 0x38) = iVar7;
    if (*(int *)(lVar16 + 0x3c) < iVar7) {
      (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x120))(lVar16);
      lVar18 = *(long *)(lVar16 + 0x10);
      if (lVar18 == 0) {
        func_0x010eec64(0);
      }
      iVar7 = *(int *)(lVar18 + 0x18);
      iVar9 = 0;
      if (iVar7 != 0) {
        iVar9 = (int)uStack_6c / iVar7;
      }
      uVar21 = uStack_6c - iVar9 * iVar7;
    }
    uVar13 = *(uint *)(lVar16 + 0x34);
    if (uVar13 == 0xffffffff) {
      uVar13 = *(uint *)(lVar16 + 0x30);
      *(uint *)(lVar16 + 0x30) = uVar13 + 1;
    }
    else {
      lVar18 = *(long *)(lVar16 + 0x18);
      if (lVar18 == 0) {
        func_0x010eec64(0);
      }
      if (*(uint *)(lVar18 + 0x18) <= uVar13) {
        uVar3 = func_0x010ef850();
        func_0x010eebec(uVar3,0,0);
      }
      *(undefined4 *)(lVar16 + 0x34) = *(undefined4 *)(lVar18 + (long)(int)uVar13 * 8 + 0x24);
    }
    lVar18 = *(long *)(lVar16 + 0x18);
    if (lVar18 == 0) {
      func_0x010eec64(0);
    }
    lVar15 = (long)(int)uVar13;
    if (*(uint *)(lVar18 + 0x18) <= uVar13) {
      uVar3 = func_0x010ef850();
      func_0x010eebec(uVar3,0,0);
    }
    *(uint *)(lVar18 + lVar15 * 8 + 0x20) = uVar2 | 0x80000000;
    lVar18 = *(long *)(lVar16 + 0x18);
    if (lVar18 == 0) {
      func_0x010eec64(0);
    }
    lVar8 = *(long *)(lVar16 + 0x10);
    if (lVar8 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar8 + 0x18) <= uVar21) {
      uVar3 = func_0x010ef850();
      func_0x010eebec(uVar3,0,0);
    }
    iVar7 = *(int *)(lVar8 + (long)(int)uVar21 * 4 + 0x20);
    if (*(uint *)(lVar18 + 0x18) <= uVar13) {
      uVar3 = func_0x010ef850();
      func_0x010eebec(uVar3,0,0);
    }
    *(int *)(lVar18 + lVar15 * 8 + 0x24) = iVar7 + -1;
    lVar18 = *(long *)(lVar16 + 0x10);
    if (lVar18 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar18 + 0x18) <= uVar21) {
      uVar3 = func_0x010ef850();
      func_0x010eebec(uVar3,0,0);
    }
    *(uint *)(lVar18 + (long)(int)uVar21 * 4 + 0x20) = uVar13 + 1;
    lVar18 = *(long *)(lVar16 + 0x20);
    if (lVar18 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar18 + 0x18) <= uVar13) {
      uVar3 = func_0x010ef850();
      func_0x010eebec(uVar3,0,0);
    }
    *(uint *)(lVar18 + lVar15 * 4 + 0x20) = param_3;
    lVar18 = *(long *)(lVar16 + 0x28);
    if (lVar18 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar18 + 0x18) <= uVar13) {
      uVar3 = func_0x010ef850();
      func_0x010eebec(uVar3,0,0);
    }
    *(undefined8 *)(lVar18 + lVar15 * 8 + 0x20) = param_4;
    *(int *)(lVar16 + 0x50) = *(int *)(lVar16 + 0x50) + 1;
    return;
  }
  lVar18 = _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_set_Item__;
  if ((bRam0000000004a082c8 & 1) == 0) {
    func_0x010c65d4(0x41ad);
    bRam0000000004a082c8 = 1;
  }
  plVar17 = *(long **)(lVar16 + 0x40);
  if (plVar17 == (long *)0x0) {
    func_0x010eec64(0);
  }
  lVar19 = *(long *)(*(long *)(*(long *)(lVar15 + 0x18) + 0xc0) + 0x118);
  func_0x010bbb2c(lVar19);
  lVar8 = *plVar17;
  if ((ulong)*(ushort *)(lVar8 + 0x122) != 0) {
    uVar4 = 0;
    piVar11 = (int *)(*(long *)(lVar8 + 0xb0) + 8);
    do {
      if (*(long *)(piVar11 + -2) == lVar19) {
        puVar5 = (undefined8 *)(lVar8 + (ulong)(*piVar11 + 1) * 0x10 + 0x130);
        goto code_r0x03188708;
      }
      uVar4 = uVar4 + 1;
      piVar11 = piVar11 + 4;
    } while (uVar4 < *(ushort *)(lVar8 + 0x122));
  }
  puVar5 = (undefined8 *)func_0x010bc584(plVar17,lVar19,1);
code_r0x03188708:
  uVar4 = puVar5[1];
  uVar2 = (*(code *)*puVar5)(plVar17,uVar14);
  lVar8 = *(long *)(lVar16 + 0x10);
  if (lVar8 == 0) {
    func_0x010eec64(0);
    lVar8 = *(long *)(lVar16 + 0x10);
    if (lVar8 == 0) {
code_r0x03188c54:
      auVar23 = func_0x010eec64(0);
      iVar7 = auVar23._8_4_;
      lVar16 = auVar23._0_8_;
      if ((bRam0000000004a082c9 & 1) == 0) {
        func_0x010c65d4(0x3be3);
        bRam0000000004a082c9 = 1;
      }
      if (-1 < iVar7) {
        if (uVar4 == 0) {
          lVar15 = *(long *)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x130);
          func_0x010bbb2c(lVar15);
          if (((*(byte *)(lVar15 + 0x12a) & 1) != 0) &&
             (lVar15 = *(long *)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x130),
             func_0x010bbb2c(lVar15), *(int *)(lVar15 + 0xd8) == 0)) {
            uVar3 = *(undefined8 *)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x130);
            func_0x010bbb2c(uVar3);
            func_0x010da55c(uVar3);
          }
          uVar4 = (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x128))
                            (0);
        }
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        iVar9 = 0xc;
        if (iVar7 != 0) {
          iVar9 = (int)((float)iVar7 / 0.9) + 1;
        }
        *(ulong *)(lVar16 + 0x40) = uVar4;
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x138))
                  (lVar16,iVar9);
        *(undefined4 *)(lVar16 + 0x50) = 0;
        return;
      }
      uVar3 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
      System_ArgumentOutOfRangeException___ctor(uVar3,_str_capacity,0);
      lVar16 = _mm_Method_System_Collections_Generic_Dictionary<int,_object>_Init__;
      func_0x010eebec(uVar3,0);
      auVar22 = func_0x0112148c();
      lVar18 = auVar22._0_8_;
      uVar14 = auVar22._8_8_ & 0xffffffff;
      if ((bRam0000000004a082ca & 1) == 0) {
        func_0x010c65d4(0x3b62);
        bRam0000000004a082ca = 1;
      }
      uVar3 = func_0x01101ad4(_class_int___TypeInfo,uVar14);
      *(undefined8 *)(lVar18 + 0x10) = uVar3;
      uVar3 = func_0x01101ad4(_class_System_Collections_Generic_Link___TypeInfo,uVar14);
      *(undefined4 *)(lVar18 + 0x34) = 0xffffffff;
      *(undefined8 *)(lVar18 + 0x18) = uVar3;
      uVar3 = *(undefined8 *)(*(long *)(*(long *)(lVar16 + 0x18) + 0xc0) + 0x140);
      func_0x010bbb2c(uVar3);
      uVar3 = func_0x01101ad4(uVar3,uVar14);
      *(undefined8 *)(lVar18 + 0x20) = uVar3;
      uVar3 = *(undefined8 *)(*(long *)(*(long *)(lVar16 + 0x18) + 0xc0) + 0x148);
      func_0x010bbb2c(uVar3);
      uVar3 = func_0x01101ad4(uVar3,uVar14);
      lVar16 = *(long *)(lVar18 + 0x10);
      *(undefined8 *)(lVar18 + 0x28) = uVar3;
      *(undefined4 *)(lVar18 + 0x30) = 0;
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      iVar7 = (int)((float)*(int *)(lVar16 + 0x18) * 0.9);
      *(int *)(lVar18 + 0x3c) = iVar7;
      if (iVar7 == 0) {
        lVar16 = *(long *)(lVar18 + 0x10);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        if (0 < *(int *)(lVar16 + 0x18)) {
          *(undefined4 *)(lVar18 + 0x3c) = 1;
        }
      }
      return;
    }
    iVar7 = 0;
    if (iRam0000000000000018 != 0) {
      iVar7 = (int)(uVar2 & 0x7fffffff) / iRam0000000000000018;
    }
    uStack_68 = (uVar2 & 0x7fffffff) - iVar7 * iRam0000000000000018;
  }
  else {
    iVar7 = *(int *)(lVar8 + 0x18);
    iVar9 = 0;
    if (iVar7 != 0) {
      iVar9 = (int)(uVar2 & 0x7fffffff) / iVar7;
    }
    uStack_68 = (uVar2 & 0x7fffffff) - iVar9 * iVar7;
  }
  uStack_74 = uVar2 & 0x7fffffff;
  uVar10 = (ulong)uStack_68;
  if (*(uint *)(lVar8 + 0x18) <= uStack_68) {
    uVar3 = func_0x010ef850();
    func_0x010eebec(uVar3,0,0);
  }
  iVar7 = *(int *)(lVar8 + uVar10 * 4 + 0x20);
  if (iVar7 != 0) {
    uVar21 = iVar7 - 1;
    uVar13 = 0xffffffff;
    do {
      uVar20 = uVar21;
      lVar18 = *(long *)(lVar16 + 0x18);
      if (lVar18 == 0) {
        func_0x010eec64(0);
      }
      lVar8 = (long)(int)uVar20;
      if (*(uint *)(lVar18 + 0x18) <= uVar20) {
        uVar3 = func_0x010ef850();
        func_0x010eebec(uVar3,0,0);
      }
      if (*(uint *)(lVar18 + lVar8 * 8 + 0x20) == (uVar2 | 0x80000000)) {
        plVar17 = *(long **)(lVar16 + 0x40);
        lVar18 = *(long *)(lVar16 + 0x20);
        if (lVar18 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar18 + 0x18) <= uVar20) {
          uVar3 = func_0x010ef850();
          func_0x010eebec(uVar3,0,0);
        }
        uVar1 = *(undefined4 *)(lVar18 + lVar8 * 4 + 0x20);
        if (plVar17 == (long *)0x0) {
          func_0x010eec64(0);
        }
        lVar19 = *(long *)(*(long *)(*(long *)(lVar15 + 0x18) + 0xc0) + 0x118);
        func_0x010bbb2c(lVar19);
        lVar18 = *plVar17;
        if ((ulong)*(ushort *)(lVar18 + 0x122) != 0) {
          uVar4 = 0;
          puVar12 = (uint *)(*(long *)(lVar18 + 0xb0) + 8);
          do {
            if (*(long *)(puVar12 + -2) == lVar19) {
              puVar5 = (undefined8 *)(lVar18 + (ulong)*puVar12 * 0x10 + 0x130);
              goto code_r0x03188894;
            }
            uVar4 = uVar4 + 1;
            puVar12 = puVar12 + 4;
          } while (uVar4 < *(ushort *)(lVar18 + 0x122));
        }
        puVar5 = (undefined8 *)func_0x010bc584(plVar17,lVar19,0);
code_r0x03188894:
        lVar18 = puVar5[1];
        uVar4 = uVar14;
        uVar6 = (*(code *)*puVar5)(plVar17,uVar1);
        if ((uVar6 & 1) != 0) {
          if (uVar20 != 0xffffffff) {
            if (uVar13 == 0xffffffff) goto code_r0x03188ac0;
            lVar19 = *(long *)(lVar16 + 0x18);
            lVar15 = lVar19;
            if (lVar19 == 0) {
              func_0x010eec64(0);
              lVar15 = *(long *)(lVar16 + 0x18);
              if (lVar15 == 0) goto code_r0x03188c54;
            }
            if (*(uint *)(lVar15 + 0x18) <= uVar20) {
              uVar3 = func_0x010ef850();
              func_0x010eebec(uVar3,0,0);
            }
            uVar1 = *(undefined4 *)(lVar15 + lVar8 * 8 + 0x24);
            if (*(uint *)(lVar19 + 0x18) <= uVar13) {
              uVar3 = func_0x010ef850();
              func_0x010eebec(uVar3,0,0);
            }
            *(undefined4 *)(lVar19 + (long)(int)uVar13 * 8 + 0x24) = uVar1;
            lVar18 = *(long *)(lVar16 + 0x18);
            if (lVar18 == 0) {
              func_0x010eec64(0);
            }
            lVar15 = *(long *)(lVar16 + 0x10);
            if (lVar15 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar15 + 0x18) <= uStack_68) {
              uVar3 = func_0x010ef850();
              func_0x010eebec(uVar3,0,0);
            }
            iVar7 = *(int *)(lVar15 + uVar10 * 4 + 0x20);
            if (*(uint *)(lVar18 + 0x18) <= uVar20) {
              uVar3 = func_0x010ef850();
              func_0x010eebec(uVar3,0,0);
            }
            *(int *)(lVar18 + lVar8 * 8 + 0x24) = iVar7 + -1;
            lVar18 = *(long *)(lVar16 + 0x10);
            if (lVar18 == 0) {
              func_0x010eec64(0);
            }
            param_3 = uVar20 + 1;
            if (*(uint *)(lVar18 + 0x18) <= uStack_68) {
              uVar3 = func_0x010ef850();
              func_0x010eebec(uVar3,0,0);
            }
            lVar18 = lVar18 + uVar10 * 4;
            goto code_r0x03188abc;
          }
          break;
        }
      }
      lVar18 = *(long *)(lVar16 + 0x18);
      if (lVar18 == 0) {
        func_0x010eec64(0);
      }
      if (*(uint *)(lVar18 + 0x18) <= uVar20) {
        uVar3 = func_0x010ef850();
        func_0x010eebec(uVar3,0,0);
      }
      uVar21 = *(uint *)(lVar18 + lVar8 * 8 + 0x24);
      uVar13 = uVar20;
    } while (uVar21 != 0xffffffff);
  }
  iVar7 = *(int *)(lVar16 + 0x38) + 1;
  *(int *)(lVar16 + 0x38) = iVar7;
  if (*(int *)(lVar16 + 0x3c) < iVar7) {
    (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar15 + 0x18) + 0xc0) + 0x120))(lVar16);
    lVar18 = *(long *)(lVar16 + 0x10);
    if (lVar18 == 0) {
      func_0x010eec64(0);
    }
    iVar7 = *(int *)(lVar18 + 0x18);
    iVar9 = 0;
    if (iVar7 != 0) {
      iVar9 = (int)uStack_74 / iVar7;
    }
    uStack_68 = uStack_74 - iVar9 * iVar7;
  }
  uVar20 = *(uint *)(lVar16 + 0x34);
  if (uVar20 == 0xffffffff) {
    uVar20 = *(uint *)(lVar16 + 0x30);
    *(uint *)(lVar16 + 0x30) = uVar20 + 1;
  }
  else {
    lVar18 = *(long *)(lVar16 + 0x18);
    if (lVar18 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar18 + 0x18) <= uVar20) {
      uVar3 = func_0x010ef850();
      func_0x010eebec(uVar3,0,0);
    }
    *(undefined4 *)(lVar16 + 0x34) = *(undefined4 *)(lVar18 + (long)(int)uVar20 * 8 + 0x24);
  }
  lVar18 = *(long *)(lVar16 + 0x18);
  if (lVar18 == 0) {
    func_0x010eec64(0);
  }
  lVar15 = *(long *)(lVar16 + 0x10);
  if (lVar15 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar15 + 0x18) <= uStack_68) {
    uVar3 = func_0x010ef850();
    func_0x010eebec(uVar3,0,0);
  }
  iVar7 = *(int *)(lVar15 + (long)(int)uStack_68 * 4 + 0x20);
  lVar15 = (long)(int)uVar20;
  if (*(uint *)(lVar18 + 0x18) <= uVar20) {
    uVar3 = func_0x010ef850();
    func_0x010eebec(uVar3,0,0);
  }
  *(int *)(lVar18 + lVar15 * 8 + 0x24) = iVar7 + -1;
  lVar18 = *(long *)(lVar16 + 0x10);
  if (lVar18 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar18 + 0x18) <= uStack_68) {
    uVar3 = func_0x010ef850();
    func_0x010eebec(uVar3,0,0);
  }
  *(uint *)(lVar18 + (long)(int)uStack_68 * 4 + 0x20) = uVar20 + 1;
  lVar18 = *(long *)(lVar16 + 0x18);
  if (lVar18 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar18 + 0x18) <= uVar20) {
    uVar3 = func_0x010ef850();
    func_0x010eebec(uVar3,0,0);
  }
  *(uint *)(lVar18 + lVar15 * 8 + 0x20) = uVar2 | 0x80000000;
  lVar18 = *(long *)(lVar16 + 0x20);
  if (lVar18 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar18 + 0x18) <= uVar20) {
    uVar3 = func_0x010ef850();
    func_0x010eebec(uVar3,0,0);
  }
  lVar18 = lVar18 + lVar15 * 4;
code_r0x03188abc:
  *(uint *)(lVar18 + 0x20) = param_3;
code_r0x03188ac0:
  lVar18 = *(long *)(lVar16 + 0x28);
  if (lVar18 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar18 + 0x18) <= uVar20) {
    uVar3 = func_0x010ef850();
    func_0x010eebec(uVar3,0,0);
  }
  *(undefined8 *)(lVar18 + (long)(int)uVar20 * 8 + 0x20) = param_4;
  *(int *)(lVar16 + 0x50) = *(int *)(lVar16 + 0x50) + 1;
  return;
}


