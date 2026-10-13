// name: FairRuleManager$$OverrideFairRule
// entry: 01749300

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void FairRuleManager__OverrideFairRule(long param_1,uint param_2,undefined8 param_3)

{
  undefined4 uVar1;
  uint uVar2;
  undefined8 *puVar3;
  undefined8 uVar4;
  ulong uVar5;
  int iVar6;
  long lVar7;
  int iVar8;
  ulong uVar9;
  int *piVar10;
  uint *puVar11;
  ulong uVar12;
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
  
  uVar14 = (ulong)param_2;
  if ((bRam00000000049f9c10 & 1) == 0) {
    func_0x010c65d4(0x5676);
    bRam00000000049f9c10 = 1;
  }
  lVar15 = *(long *)(param_1 + 0x28);
  if (lVar15 == 0) {
    func_0x010eec64(0);
  }
  uVar2 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__ContainsKey
                    (lVar15,uVar14,
                     _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_ContainsKey__
                    );
  lVar15 = *(long *)(param_1 + 0x28);
  if (lVar15 == 0) {
    func_0x010eec64(0);
  }
  lVar16 = 
  _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_set_Item__;
  lVar18 = _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_Add__;
  if ((uVar2 & 1) == 0) {
    if ((bRam0000000004a0822a & 1) == 0) {
      func_0x010c65d4(0x3876);
      bRam0000000004a0822a = 1;
    }
    plVar17 = *(long **)(lVar15 + 0x40);
    if (plVar17 == (long *)0x0) {
      func_0x010eec64(0);
    }
    lVar7 = *(long *)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x118);
    func_0x010bbb2c(lVar7);
    lVar16 = *plVar17;
    if ((ulong)*(ushort *)(lVar16 + 0x122) != 0) {
      uVar12 = 0;
      piVar10 = (int *)(*(long *)(lVar16 + 0xb0) + 8);
      do {
        if (*(long *)(piVar10 + -2) == lVar7) {
          puVar3 = (undefined8 *)(lVar16 + (ulong)(*piVar10 + 1) * 0x10 + 0x130);
          goto code_r0x03152a04;
        }
        uVar12 = uVar12 + 1;
        piVar10 = piVar10 + 4;
      } while (uVar12 < *(ushort *)(lVar16 + 0x122));
    }
    puVar3 = (undefined8 *)func_0x010bc584(plVar17,lVar7,1);
code_r0x03152a04:
    uVar2 = (*(code *)*puVar3)(plVar17,uVar14,puVar3[1]);
    lVar16 = *(long *)(lVar15 + 0x10);
    if (lVar16 == 0) {
      func_0x010eec64(0);
      lVar16 = *(long *)(lVar15 + 0x10);
      if (lVar16 == 0) {
code_r0x03152e40:
        lVar15 = func_0x010eec64(0);
        lVar18 = *(long *)(lVar15 + 0x10);
        *(undefined4 *)(lVar15 + 0x38) = 0;
        if (lVar18 == 0) {
          func_0x010eec64(0);
        }
        System_Array__Clear(0,lVar18,0,*(undefined4 *)(lVar18 + 0x18),0);
        lVar18 = *(long *)(lVar15 + 0x20);
        if (lVar18 == 0) {
          func_0x010eec64(0);
        }
        System_Array__Clear(0,lVar18,0,*(undefined4 *)(lVar18 + 0x18),0);
        lVar18 = *(long *)(lVar15 + 0x28);
        if (lVar18 == 0) {
          func_0x010eec64(0);
        }
        System_Array__Clear(0,lVar18,0,*(undefined4 *)(lVar18 + 0x18),0);
        lVar18 = *(long *)(lVar15 + 0x18);
        if (lVar18 == 0) {
          func_0x010eec64(0);
        }
        System_Array__Clear(0,lVar18,0,*(undefined4 *)(lVar18 + 0x18),0);
        *(undefined8 *)(lVar15 + 0x30) = 0xffffffff00000000;
        *(int *)(lVar15 + 0x50) = *(int *)(lVar15 + 0x50) + 1;
        return;
      }
      iVar6 = 0;
      if (iRam0000000000000018 != 0) {
        iVar6 = (int)(uVar2 & 0x7fffffff) / iRam0000000000000018;
      }
      uVar21 = (uVar2 & 0x7fffffff) - iVar6 * iRam0000000000000018;
    }
    else {
      iVar6 = *(int *)(lVar16 + 0x18);
      iVar8 = 0;
      if (iVar6 != 0) {
        iVar8 = (int)(uVar2 & 0x7fffffff) / iVar6;
      }
      uVar21 = (uVar2 & 0x7fffffff) - iVar8 * iVar6;
    }
    uStack_6c = uVar2 & 0x7fffffff;
    if (*(uint *)(lVar16 + 0x18) <= uVar21) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    iVar6 = *(int *)(lVar16 + (ulong)uVar21 * 4 + 0x20);
    if (iVar6 != 0) {
      uVar13 = iVar6 - 1;
      do {
        lVar16 = *(long *)(lVar15 + 0x18);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        lVar7 = (long)(int)uVar13;
        if (*(uint *)(lVar16 + 0x18) <= uVar13) {
          uVar4 = func_0x010ef850();
          func_0x010eebec(uVar4,0,0);
        }
        if (*(uint *)(lVar16 + lVar7 * 8 + 0x20) == (uVar2 | 0x80000000)) {
          plVar17 = *(long **)(lVar15 + 0x40);
          lVar16 = *(long *)(lVar15 + 0x20);
          if (lVar16 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar16 + 0x18) <= uVar13) {
            uVar4 = func_0x010ef850();
            func_0x010eebec(uVar4,0,0);
          }
          uVar1 = *(undefined4 *)(lVar16 + lVar7 * 4 + 0x20);
          if (plVar17 == (long *)0x0) {
            func_0x010eec64(0);
          }
          lVar19 = *(long *)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x118);
          func_0x010bbb2c(lVar19);
          lVar16 = *plVar17;
          if ((ulong)*(ushort *)(lVar16 + 0x122) != 0) {
            uVar12 = 0;
            puVar11 = (uint *)(*(long *)(lVar16 + 0xb0) + 8);
            do {
              if (*(long *)(puVar11 + -2) == lVar19) {
                puVar3 = (undefined8 *)(lVar16 + (ulong)*puVar11 * 0x10 + 0x130);
                goto code_r0x03152b84;
              }
              uVar12 = uVar12 + 1;
              puVar11 = puVar11 + 4;
            } while (uVar12 < *(ushort *)(lVar16 + 0x122));
          }
          puVar3 = (undefined8 *)func_0x010bc584(plVar17,lVar19,0);
code_r0x03152b84:
          uVar12 = (*(code *)*puVar3)(plVar17,uVar1,uVar14,puVar3[1]);
          if ((uVar12 & 1) != 0) {
            uVar4 = func_0x010fdda8(_class_System_ArgumentException_TypeInfo);
            System_ArgumentException___ctor
                      (uVar4,_str_An_element_with_the_same_key_already_exists_in_the_dictionary_,0);
            func_0x010eebec(uVar4,0,
                            _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>_Add__
                           );
            func_0x0112148c();
            goto code_r0x03152e40;
          }
        }
        lVar16 = *(long *)(lVar15 + 0x18);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar16 + 0x18) <= uVar13) {
          uVar4 = func_0x010ef850();
          func_0x010eebec(uVar4,0,0);
        }
        uVar13 = *(uint *)(lVar16 + lVar7 * 8 + 0x24);
      } while (uVar13 != 0xffffffff);
    }
    iVar6 = *(int *)(lVar15 + 0x38) + 1;
    *(int *)(lVar15 + 0x38) = iVar6;
    if (*(int *)(lVar15 + 0x3c) < iVar6) {
      (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x120))(lVar15);
      lVar18 = *(long *)(lVar15 + 0x10);
      if (lVar18 == 0) {
        func_0x010eec64(0);
      }
      iVar6 = *(int *)(lVar18 + 0x18);
      iVar8 = 0;
      if (iVar6 != 0) {
        iVar8 = (int)uStack_6c / iVar6;
      }
      uVar21 = uStack_6c - iVar8 * iVar6;
    }
    uVar13 = *(uint *)(lVar15 + 0x34);
    if (uVar13 == 0xffffffff) {
      uVar13 = *(uint *)(lVar15 + 0x30);
      *(uint *)(lVar15 + 0x30) = uVar13 + 1;
    }
    else {
      lVar18 = *(long *)(lVar15 + 0x18);
      if (lVar18 == 0) {
        func_0x010eec64(0);
      }
      if (*(uint *)(lVar18 + 0x18) <= uVar13) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      *(undefined4 *)(lVar15 + 0x34) = *(undefined4 *)(lVar18 + (long)(int)uVar13 * 8 + 0x24);
    }
    lVar18 = *(long *)(lVar15 + 0x18);
    if (lVar18 == 0) {
      func_0x010eec64(0);
    }
    lVar16 = (long)(int)uVar13;
    if (*(uint *)(lVar18 + 0x18) <= uVar13) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    *(uint *)(lVar18 + lVar16 * 8 + 0x20) = uVar2 | 0x80000000;
    lVar18 = *(long *)(lVar15 + 0x18);
    if (lVar18 == 0) {
      func_0x010eec64(0);
    }
    lVar7 = *(long *)(lVar15 + 0x10);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar7 + 0x18) <= uVar21) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    iVar6 = *(int *)(lVar7 + (long)(int)uVar21 * 4 + 0x20);
    if (*(uint *)(lVar18 + 0x18) <= uVar13) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    *(int *)(lVar18 + lVar16 * 8 + 0x24) = iVar6 + -1;
    lVar18 = *(long *)(lVar15 + 0x10);
    if (lVar18 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar18 + 0x18) <= uVar21) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    *(uint *)(lVar18 + (long)(int)uVar21 * 4 + 0x20) = uVar13 + 1;
    lVar18 = *(long *)(lVar15 + 0x20);
    if (lVar18 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar18 + 0x18) <= uVar13) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    *(uint *)(lVar18 + lVar16 * 4 + 0x20) = param_2;
    lVar18 = *(long *)(lVar15 + 0x28);
    if (lVar18 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar18 + 0x18) <= uVar13) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    *(undefined8 *)(lVar18 + lVar16 * 8 + 0x20) = param_3;
    *(int *)(lVar15 + 0x50) = *(int *)(lVar15 + 0x50) + 1;
    return;
  }
  lVar18 = 
  _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_set_Item__;
  if ((bRam0000000004a08225 & 1) == 0) {
    func_0x010c65d4(0x41d8);
    bRam0000000004a08225 = 1;
  }
  plVar17 = *(long **)(lVar15 + 0x40);
  if (plVar17 == (long *)0x0) {
    func_0x010eec64(0);
  }
  lVar19 = *(long *)(*(long *)(*(long *)(lVar16 + 0x18) + 0xc0) + 0x118);
  func_0x010bbb2c(lVar19);
  lVar7 = *plVar17;
  if ((ulong)*(ushort *)(lVar7 + 0x122) != 0) {
    uVar12 = 0;
    piVar10 = (int *)(*(long *)(lVar7 + 0xb0) + 8);
    do {
      if (*(long *)(piVar10 + -2) == lVar19) {
        puVar3 = (undefined8 *)(lVar7 + (ulong)(*piVar10 + 1) * 0x10 + 0x130);
        goto code_r0x03151abc;
      }
      uVar12 = uVar12 + 1;
      piVar10 = piVar10 + 4;
    } while (uVar12 < *(ushort *)(lVar7 + 0x122));
  }
  puVar3 = (undefined8 *)func_0x010bc584(plVar17,lVar19,1);
code_r0x03151abc:
  uVar12 = puVar3[1];
  uVar2 = (*(code *)*puVar3)(plVar17,uVar14);
  lVar7 = *(long *)(lVar15 + 0x10);
  if (lVar7 == 0) {
    func_0x010eec64(0);
    lVar7 = *(long *)(lVar15 + 0x10);
    if (lVar7 == 0) {
code_r0x03152008:
      auVar23 = func_0x010eec64(0);
      iVar6 = auVar23._8_4_;
      lVar15 = auVar23._0_8_;
      if ((bRam0000000004a08226 & 1) == 0) {
        func_0x010c65d4(0x3bfe);
        bRam0000000004a08226 = 1;
      }
      if (-1 < iVar6) {
        if (uVar12 == 0) {
          lVar16 = *(long *)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x130);
          func_0x010bbb2c(lVar16);
          if (((*(byte *)(lVar16 + 0x12a) & 1) != 0) &&
             (lVar16 = *(long *)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x130),
             func_0x010bbb2c(lVar16), *(int *)(lVar16 + 0xd8) == 0)) {
            uVar4 = *(undefined8 *)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x130);
            func_0x010bbb2c(uVar4);
            func_0x010da55c(uVar4);
          }
          uVar12 = (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x128))
                             (0);
        }
        if (lVar15 == 0) {
          func_0x010eec64(0);
        }
        iVar8 = 0xc;
        if (iVar6 != 0) {
          iVar8 = (int)((float)iVar6 / 0.9) + 1;
        }
        *(ulong *)(lVar15 + 0x40) = uVar12;
        if (lVar15 == 0) {
          func_0x010eec64(0);
        }
        (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x138))
                  (lVar15,iVar8);
        *(undefined4 *)(lVar15 + 0x50) = 0;
        return;
      }
      uVar4 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
      System_ArgumentOutOfRangeException___ctor(uVar4,_str_capacity,0);
      lVar15 = _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>_Init__;
      func_0x010eebec(uVar4,0);
      auVar22 = func_0x0112148c();
      lVar18 = auVar22._0_8_;
      uVar14 = auVar22._8_8_ & 0xffffffff;
      if ((bRam0000000004a08227 & 1) == 0) {
        func_0x010c65d4(0x3baa);
        bRam0000000004a08227 = 1;
      }
      uVar4 = func_0x01101ad4(_class_int___TypeInfo,uVar14);
      *(undefined8 *)(lVar18 + 0x10) = uVar4;
      uVar4 = func_0x01101ad4(_class_System_Collections_Generic_Link___TypeInfo,uVar14);
      *(undefined4 *)(lVar18 + 0x34) = 0xffffffff;
      *(undefined8 *)(lVar18 + 0x18) = uVar4;
      uVar4 = *(undefined8 *)(*(long *)(*(long *)(lVar15 + 0x18) + 0xc0) + 0x140);
      func_0x010bbb2c(uVar4);
      uVar4 = func_0x01101ad4(uVar4,uVar14);
      *(undefined8 *)(lVar18 + 0x20) = uVar4;
      uVar4 = *(undefined8 *)(*(long *)(*(long *)(lVar15 + 0x18) + 0xc0) + 0x148);
      func_0x010bbb2c(uVar4);
      uVar4 = func_0x01101ad4(uVar4,uVar14);
      lVar15 = *(long *)(lVar18 + 0x10);
      *(undefined8 *)(lVar18 + 0x28) = uVar4;
      *(undefined4 *)(lVar18 + 0x30) = 0;
      if (lVar15 == 0) {
        func_0x010eec64(0);
      }
      iVar6 = (int)((float)*(int *)(lVar15 + 0x18) * 0.9);
      *(int *)(lVar18 + 0x3c) = iVar6;
      if (iVar6 == 0) {
        lVar15 = *(long *)(lVar18 + 0x10);
        if (lVar15 == 0) {
          func_0x010eec64(0);
        }
        if (0 < *(int *)(lVar15 + 0x18)) {
          *(undefined4 *)(lVar18 + 0x3c) = 1;
        }
      }
      return;
    }
    iVar6 = 0;
    if (iRam0000000000000018 != 0) {
      iVar6 = (int)(uVar2 & 0x7fffffff) / iRam0000000000000018;
    }
    uStack_68 = (uVar2 & 0x7fffffff) - iVar6 * iRam0000000000000018;
  }
  else {
    iVar6 = *(int *)(lVar7 + 0x18);
    iVar8 = 0;
    if (iVar6 != 0) {
      iVar8 = (int)(uVar2 & 0x7fffffff) / iVar6;
    }
    uStack_68 = (uVar2 & 0x7fffffff) - iVar8 * iVar6;
  }
  uStack_74 = uVar2 & 0x7fffffff;
  uVar9 = (ulong)uStack_68;
  if (*(uint *)(lVar7 + 0x18) <= uStack_68) {
    uVar4 = func_0x010ef850();
    func_0x010eebec(uVar4,0,0);
  }
  iVar6 = *(int *)(lVar7 + uVar9 * 4 + 0x20);
  if (iVar6 != 0) {
    uVar21 = iVar6 - 1;
    uVar13 = 0xffffffff;
    do {
      uVar20 = uVar21;
      lVar18 = *(long *)(lVar15 + 0x18);
      if (lVar18 == 0) {
        func_0x010eec64(0);
      }
      lVar7 = (long)(int)uVar20;
      if (*(uint *)(lVar18 + 0x18) <= uVar20) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      if (*(uint *)(lVar18 + lVar7 * 8 + 0x20) == (uVar2 | 0x80000000)) {
        plVar17 = *(long **)(lVar15 + 0x40);
        lVar18 = *(long *)(lVar15 + 0x20);
        if (lVar18 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar18 + 0x18) <= uVar20) {
          uVar4 = func_0x010ef850();
          func_0x010eebec(uVar4,0,0);
        }
        uVar1 = *(undefined4 *)(lVar18 + lVar7 * 4 + 0x20);
        if (plVar17 == (long *)0x0) {
          func_0x010eec64(0);
        }
        lVar19 = *(long *)(*(long *)(*(long *)(lVar16 + 0x18) + 0xc0) + 0x118);
        func_0x010bbb2c(lVar19);
        lVar18 = *plVar17;
        if ((ulong)*(ushort *)(lVar18 + 0x122) != 0) {
          uVar12 = 0;
          puVar11 = (uint *)(*(long *)(lVar18 + 0xb0) + 8);
          do {
            if (*(long *)(puVar11 + -2) == lVar19) {
              puVar3 = (undefined8 *)(lVar18 + (ulong)*puVar11 * 0x10 + 0x130);
              goto code_r0x03151c48;
            }
            uVar12 = uVar12 + 1;
            puVar11 = puVar11 + 4;
          } while (uVar12 < *(ushort *)(lVar18 + 0x122));
        }
        puVar3 = (undefined8 *)func_0x010bc584(plVar17,lVar19,0);
code_r0x03151c48:
        lVar18 = puVar3[1];
        uVar12 = uVar14;
        uVar5 = (*(code *)*puVar3)(plVar17,uVar1);
        if ((uVar5 & 1) != 0) {
          if (uVar20 != 0xffffffff) {
            if (uVar13 == 0xffffffff) goto code_r0x03151e74;
            lVar19 = *(long *)(lVar15 + 0x18);
            lVar16 = lVar19;
            if (lVar19 == 0) {
              func_0x010eec64(0);
              lVar16 = *(long *)(lVar15 + 0x18);
              if (lVar16 == 0) goto code_r0x03152008;
            }
            if (*(uint *)(lVar16 + 0x18) <= uVar20) {
              uVar4 = func_0x010ef850();
              func_0x010eebec(uVar4,0,0);
            }
            uVar1 = *(undefined4 *)(lVar16 + lVar7 * 8 + 0x24);
            if (*(uint *)(lVar19 + 0x18) <= uVar13) {
              uVar4 = func_0x010ef850();
              func_0x010eebec(uVar4,0,0);
            }
            *(undefined4 *)(lVar19 + (long)(int)uVar13 * 8 + 0x24) = uVar1;
            lVar18 = *(long *)(lVar15 + 0x18);
            if (lVar18 == 0) {
              func_0x010eec64(0);
            }
            lVar16 = *(long *)(lVar15 + 0x10);
            if (lVar16 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar16 + 0x18) <= uStack_68) {
              uVar4 = func_0x010ef850();
              func_0x010eebec(uVar4,0,0);
            }
            iVar6 = *(int *)(lVar16 + uVar9 * 4 + 0x20);
            if (*(uint *)(lVar18 + 0x18) <= uVar20) {
              uVar4 = func_0x010ef850();
              func_0x010eebec(uVar4,0,0);
            }
            *(int *)(lVar18 + lVar7 * 8 + 0x24) = iVar6 + -1;
            lVar18 = *(long *)(lVar15 + 0x10);
            if (lVar18 == 0) {
              func_0x010eec64(0);
            }
            param_2 = uVar20 + 1;
            if (*(uint *)(lVar18 + 0x18) <= uStack_68) {
              uVar4 = func_0x010ef850();
              func_0x010eebec(uVar4,0,0);
            }
            lVar18 = lVar18 + uVar9 * 4;
            goto code_r0x03151e70;
          }
          break;
        }
      }
      lVar18 = *(long *)(lVar15 + 0x18);
      if (lVar18 == 0) {
        func_0x010eec64(0);
      }
      if (*(uint *)(lVar18 + 0x18) <= uVar20) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      uVar21 = *(uint *)(lVar18 + lVar7 * 8 + 0x24);
      uVar13 = uVar20;
    } while (uVar21 != 0xffffffff);
  }
  iVar6 = *(int *)(lVar15 + 0x38) + 1;
  *(int *)(lVar15 + 0x38) = iVar6;
  if (*(int *)(lVar15 + 0x3c) < iVar6) {
    (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar16 + 0x18) + 0xc0) + 0x120))(lVar15);
    lVar18 = *(long *)(lVar15 + 0x10);
    if (lVar18 == 0) {
      func_0x010eec64(0);
    }
    iVar6 = *(int *)(lVar18 + 0x18);
    iVar8 = 0;
    if (iVar6 != 0) {
      iVar8 = (int)uStack_74 / iVar6;
    }
    uStack_68 = uStack_74 - iVar8 * iVar6;
  }
  uVar20 = *(uint *)(lVar15 + 0x34);
  if (uVar20 == 0xffffffff) {
    uVar20 = *(uint *)(lVar15 + 0x30);
    *(uint *)(lVar15 + 0x30) = uVar20 + 1;
  }
  else {
    lVar18 = *(long *)(lVar15 + 0x18);
    if (lVar18 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(lVar18 + 0x18) <= uVar20) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    *(undefined4 *)(lVar15 + 0x34) = *(undefined4 *)(lVar18 + (long)(int)uVar20 * 8 + 0x24);
  }
  lVar18 = *(long *)(lVar15 + 0x18);
  if (lVar18 == 0) {
    func_0x010eec64(0);
  }
  lVar16 = *(long *)(lVar15 + 0x10);
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar16 + 0x18) <= uStack_68) {
    uVar4 = func_0x010ef850();
    func_0x010eebec(uVar4,0,0);
  }
  iVar6 = *(int *)(lVar16 + (long)(int)uStack_68 * 4 + 0x20);
  lVar16 = (long)(int)uVar20;
  if (*(uint *)(lVar18 + 0x18) <= uVar20) {
    uVar4 = func_0x010ef850();
    func_0x010eebec(uVar4,0,0);
  }
  *(int *)(lVar18 + lVar16 * 8 + 0x24) = iVar6 + -1;
  lVar18 = *(long *)(lVar15 + 0x10);
  if (lVar18 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar18 + 0x18) <= uStack_68) {
    uVar4 = func_0x010ef850();
    func_0x010eebec(uVar4,0,0);
  }
  *(uint *)(lVar18 + (long)(int)uStack_68 * 4 + 0x20) = uVar20 + 1;
  lVar18 = *(long *)(lVar15 + 0x18);
  if (lVar18 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar18 + 0x18) <= uVar20) {
    uVar4 = func_0x010ef850();
    func_0x010eebec(uVar4,0,0);
  }
  *(uint *)(lVar18 + lVar16 * 8 + 0x20) = uVar2 | 0x80000000;
  lVar18 = *(long *)(lVar15 + 0x20);
  if (lVar18 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar18 + 0x18) <= uVar20) {
    uVar4 = func_0x010ef850();
    func_0x010eebec(uVar4,0,0);
  }
  lVar18 = lVar18 + lVar16 * 4;
code_r0x03151e70:
  *(uint *)(lVar18 + 0x20) = param_2;
code_r0x03151e74:
  lVar18 = *(long *)(lVar15 + 0x28);
  if (lVar18 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar18 + 0x18) <= uVar20) {
    uVar4 = func_0x010ef850();
    func_0x010eebec(uVar4,0,0);
  }
  *(undefined8 *)(lVar18 + (long)(int)uVar20 * 8 + 0x20) = param_3;
  *(int *)(lVar15 + 0x50) = *(int *)(lVar15 + 0x50) + 1;
  return;
}


