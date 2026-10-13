// name: FairRuleManager$$GetFairRuleValue
// entry: 0174a38c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

ulong FairRuleManager__GetFairRuleValue
                (undefined8 param_1,uint param_2,undefined8 param_3,ulong param_4)

{
  undefined4 uVar1;
  int iVar2;
  uint uVar3;
  ulong uVar4;
  long lVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 *puVar8;
  ulong uVar9;
  undefined4 uVar10;
  int iVar11;
  long lVar12;
  int iVar13;
  ulong uVar14;
  int *piVar15;
  uint *puVar16;
  ulong uVar17;
  uint uVar18;
  long lVar19;
  long *plVar20;
  long lVar21;
  long lVar22;
  uint uVar23;
  uint uVar24;
  undefined1 auVar25 [16];
  undefined1 auVar26 [12];
  uint uStack_c0;
  ulong uStack_b8;
  uint uStack_44;
  
  param_4 = param_4 & 0xffffffff;
  if ((bRam00000000049f9c1c & 1) == 0) {
    func_0x010c65d4(0x566f);
    bRam00000000049f9c1c = 1;
  }
  uVar4 = FairRuleManager__WillApplyFairRule(param_1,param_2,param_3);
  if (((uVar4 & 1) != 0) && (lVar5 = FairRuleManager__GetFairRule(param_1), lVar5 != 0)) {
    iVar11 = param_2 + 5;
    if (4 < param_2) {
      iVar11 = 0;
    }
    switch(iVar11) {
    case 0:
      uStack_44 = param_2;
      uVar6 = func_0x010fdd08(_class_EnumData_FairRuleType_TypeInfo,&uStack_44);
      uVar7 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
      System_ArgumentOutOfRangeException___ctor(uVar7,_str_ruleType,uVar6,0,0);
      uVar10 = (undefined4)_mm_Method_FairRuleManager_GetFairRuleValue__;
      func_0x010eebec(uVar7,0);
      auVar25 = func_0x0112148c();
      uVar4 = auVar25._8_8_ & 0xffffffff;
      iVar11 = auVar25._8_4_;
      if ((bRam00000000049f9c1d & 1) == 0) {
        func_0x010c65d4(0x5677);
        bRam00000000049f9c1d = 1;
      }
      lVar5 = *(long *)(auVar25._0_8_ + 0x30);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      uVar3 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_bool>__ContainsKey
                        (lVar5,uVar4,
                         _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_bool>_ContainsKey__
                        );
      lVar5 = *(long *)(auVar25._0_8_ + 0x30);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      lVar19 = 
      _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_bool>_set_Item__;
      lVar21 = _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_bool>_Add__;
      if ((uVar3 & 1) == 0) {
        if ((bRam0000000004a08218 & 1) == 0) {
          func_0x010c65d4(0x380f);
          bRam0000000004a08218 = 1;
        }
        plVar20 = *(long **)(lVar5 + 0x40);
        if (plVar20 == (long *)0x0) {
          func_0x010eec64(0);
        }
        lVar12 = *(long *)(*(long *)(*(long *)(lVar21 + 0x18) + 0xc0) + 0x118);
        func_0x010bbb2c(lVar12);
        lVar19 = *plVar20;
        if ((ulong)*(ushort *)(lVar19 + 0x122) != 0) {
          uVar17 = 0;
          piVar15 = (int *)(*(long *)(lVar19 + 0xb0) + 8);
          do {
            if (*(long *)(piVar15 + -2) == lVar12) {
              puVar8 = (undefined8 *)(lVar19 + (ulong)(*piVar15 + 1) * 0x10 + 0x130);
              goto code_r0x0314e7e4;
            }
            uVar17 = uVar17 + 1;
            piVar15 = piVar15 + 4;
          } while (uVar17 < *(ushort *)(lVar19 + 0x122));
        }
        puVar8 = (undefined8 *)func_0x010bc584(plVar20,lVar12,1);
code_r0x0314e7e4:
        uVar17 = (*(code *)*puVar8)(plVar20,uVar4,puVar8[1]);
        lVar19 = *(long *)(lVar5 + 0x10);
        uVar3 = (uint)uVar17;
        if (lVar19 == 0) {
          uVar17 = func_0x010eec64(0);
          lVar19 = *(long *)(lVar5 + 0x10);
          if (lVar19 == 0) {
code_r0x0314ec24:
            lVar5 = func_0x010eec64(0);
            lVar21 = *(long *)(lVar5 + 0x10);
            *(undefined4 *)(lVar5 + 0x38) = 0;
            if (lVar21 == 0) {
              func_0x010eec64(0);
            }
            System_Array__Clear(0,lVar21,0,*(undefined4 *)(lVar21 + 0x18),0);
            lVar21 = *(long *)(lVar5 + 0x20);
            if (lVar21 == 0) {
              func_0x010eec64(0);
            }
            System_Array__Clear(0,lVar21,0,*(undefined4 *)(lVar21 + 0x18),0);
            lVar21 = *(long *)(lVar5 + 0x28);
            if (lVar21 == 0) {
              func_0x010eec64(0);
            }
            System_Array__Clear(0,lVar21,0,*(undefined4 *)(lVar21 + 0x18),0);
            lVar21 = *(long *)(lVar5 + 0x18);
            if (lVar21 == 0) {
              func_0x010eec64(0);
            }
            uVar4 = System_Array__Clear(0,lVar21,0,*(undefined4 *)(lVar21 + 0x18),0);
            *(undefined8 *)(lVar5 + 0x30) = 0xffffffff00000000;
            *(int *)(lVar5 + 0x50) = *(int *)(lVar5 + 0x50) + 1;
            return uVar4;
          }
          iVar13 = 0;
          if (iRam0000000000000018 != 0) {
            iVar13 = (int)(uVar3 & 0x7fffffff) / iRam0000000000000018;
          }
          uVar24 = (uVar3 & 0x7fffffff) - iVar13 * iRam0000000000000018;
        }
        else {
          iVar13 = *(int *)(lVar19 + 0x18);
          iVar2 = 0;
          if (iVar13 != 0) {
            iVar2 = (int)(uVar3 & 0x7fffffff) / iVar13;
          }
          uVar24 = (uVar3 & 0x7fffffff) - iVar2 * iVar13;
        }
        uStack_b8 = CONCAT44(uVar10,uVar3) & 0x17fffffff;
        if (*(uint *)(lVar19 + 0x18) <= uVar24) {
          uVar6 = func_0x010ef850();
          uVar17 = func_0x010eebec(uVar6,0,0);
        }
        iVar13 = *(int *)(lVar19 + (ulong)uVar24 * 4 + 0x20);
        if (iVar13 != 0) {
          uVar18 = iVar13 - 1;
          do {
            lVar19 = *(long *)(lVar5 + 0x18);
            if (lVar19 == 0) {
              uVar17 = func_0x010eec64(0);
            }
            lVar12 = (long)(int)uVar18;
            if (*(uint *)(lVar19 + 0x18) <= uVar18) {
              uVar6 = func_0x010ef850();
              uVar17 = func_0x010eebec(uVar6,0,0);
            }
            if (*(uint *)(lVar19 + lVar12 * 8 + 0x20) == (uVar3 | 0x80000000)) {
              plVar20 = *(long **)(lVar5 + 0x40);
              lVar19 = *(long *)(lVar5 + 0x20);
              if (lVar19 == 0) {
                func_0x010eec64(0);
              }
              if (*(uint *)(lVar19 + 0x18) <= uVar18) {
                uVar6 = func_0x010ef850();
                func_0x010eebec(uVar6,0,0);
              }
              uVar10 = *(undefined4 *)(lVar19 + lVar12 * 4 + 0x20);
              if (plVar20 == (long *)0x0) {
                func_0x010eec64(0);
              }
              lVar22 = *(long *)(*(long *)(*(long *)(lVar21 + 0x18) + 0xc0) + 0x118);
              func_0x010bbb2c(lVar22);
              lVar19 = *plVar20;
              if ((ulong)*(ushort *)(lVar19 + 0x122) != 0) {
                uVar17 = 0;
                puVar16 = (uint *)(*(long *)(lVar19 + 0xb0) + 8);
                do {
                  if (*(long *)(puVar16 + -2) == lVar22) {
                    puVar8 = (undefined8 *)(lVar19 + (ulong)*puVar16 * 0x10 + 0x130);
                    goto code_r0x0314e964;
                  }
                  uVar17 = uVar17 + 1;
                  puVar16 = puVar16 + 4;
                } while (uVar17 < *(ushort *)(lVar19 + 0x122));
              }
              puVar8 = (undefined8 *)func_0x010bc584(plVar20,lVar22,0);
code_r0x0314e964:
              uVar17 = (*(code *)*puVar8)(plVar20,uVar10,uVar4,puVar8[1]);
              if ((uVar17 & 1) != 0) {
                uVar6 = func_0x010fdda8(_class_System_ArgumentException_TypeInfo);
                System_ArgumentException___ctor
                          (uVar6,_str_An_element_with_the_same_key_already_exists_in_the_dictionary_
                           ,0);
                func_0x010eebec(uVar6,0,
                                _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_bool>_Add__
                               );
                func_0x0112148c();
                goto code_r0x0314ec24;
              }
            }
            lVar19 = *(long *)(lVar5 + 0x18);
            if (lVar19 == 0) {
              uVar17 = func_0x010eec64(0);
            }
            if (*(uint *)(lVar19 + 0x18) <= uVar18) {
              uVar6 = func_0x010ef850();
              uVar17 = func_0x010eebec(uVar6,0,0);
            }
            uVar18 = *(uint *)(lVar19 + lVar12 * 8 + 0x24);
          } while (uVar18 != 0xffffffff);
        }
        iVar13 = *(int *)(lVar5 + 0x38) + 1;
        *(int *)(lVar5 + 0x38) = iVar13;
        if (*(int *)(lVar5 + 0x3c) < iVar13) {
          uVar17 = (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar21 + 0x18) + 0xc0) + 0x120))
                             (lVar5);
          lVar21 = *(long *)(lVar5 + 0x10);
          if (lVar21 == 0) {
            uVar17 = func_0x010eec64(0);
          }
          iVar13 = *(int *)(lVar21 + 0x18);
          iVar2 = 0;
          if (iVar13 != 0) {
            iVar2 = (int)(uint)uStack_b8 / iVar13;
          }
          uVar24 = (uint)uStack_b8 - iVar2 * iVar13;
        }
        uVar18 = *(uint *)(lVar5 + 0x34);
        if (uVar18 == 0xffffffff) {
          uVar18 = *(uint *)(lVar5 + 0x30);
          *(uint *)(lVar5 + 0x30) = uVar18 + 1;
        }
        else {
          lVar21 = *(long *)(lVar5 + 0x18);
          if (lVar21 == 0) {
            uVar17 = func_0x010eec64(0);
          }
          if (*(uint *)(lVar21 + 0x18) <= uVar18) {
            uVar6 = func_0x010ef850();
            uVar17 = func_0x010eebec(uVar6,0,0);
          }
          *(undefined4 *)(lVar5 + 0x34) = *(undefined4 *)(lVar21 + (long)(int)uVar18 * 8 + 0x24);
        }
        lVar21 = *(long *)(lVar5 + 0x18);
        if (lVar21 == 0) {
          uVar17 = func_0x010eec64(0);
        }
        lVar19 = (long)(int)uVar18;
        if (*(uint *)(lVar21 + 0x18) <= uVar18) {
          uVar6 = func_0x010ef850();
          uVar17 = func_0x010eebec(uVar6,0,0);
        }
        *(uint *)(lVar21 + lVar19 * 8 + 0x20) = uVar3 | 0x80000000;
        lVar21 = *(long *)(lVar5 + 0x18);
        if (lVar21 == 0) {
          uVar17 = func_0x010eec64(0);
        }
        lVar12 = *(long *)(lVar5 + 0x10);
        if (lVar12 == 0) {
          uVar17 = func_0x010eec64(0);
        }
        if (*(uint *)(lVar12 + 0x18) <= uVar24) {
          uVar6 = func_0x010ef850();
          uVar17 = func_0x010eebec(uVar6,0,0);
        }
        iVar13 = *(int *)(lVar12 + (long)(int)uVar24 * 4 + 0x20);
        if (*(uint *)(lVar21 + 0x18) <= uVar18) {
          uVar6 = func_0x010ef850();
          uVar17 = func_0x010eebec(uVar6,0,0);
        }
        *(int *)(lVar21 + lVar19 * 8 + 0x24) = iVar13 + -1;
        lVar21 = *(long *)(lVar5 + 0x10);
        if (lVar21 == 0) {
          uVar17 = func_0x010eec64(0);
        }
        if (*(uint *)(lVar21 + 0x18) <= uVar24) {
          uVar6 = func_0x010ef850();
          uVar17 = func_0x010eebec(uVar6,0,0);
        }
        *(uint *)(lVar21 + (long)(int)uVar24 * 4 + 0x20) = uVar18 + 1;
        lVar21 = *(long *)(lVar5 + 0x20);
        if (lVar21 == 0) {
          uVar17 = func_0x010eec64(0);
        }
        if (*(uint *)(lVar21 + 0x18) <= uVar18) {
          uVar6 = func_0x010ef850();
          uVar17 = func_0x010eebec(uVar6,0,0);
        }
        *(int *)(lVar21 + lVar19 * 4 + 0x20) = iVar11;
        lVar21 = *(long *)(lVar5 + 0x28);
        if (lVar21 == 0) {
          uVar17 = func_0x010eec64(0);
        }
        if (*(uint *)(lVar21 + 0x18) <= uVar18) {
          uVar6 = func_0x010ef850();
          uVar17 = func_0x010eebec(uVar6,0,0);
        }
        *(char *)(lVar21 + lVar19 + 0x20) = (char)(uStack_b8 >> 0x20);
        *(int *)(lVar5 + 0x50) = *(int *)(lVar5 + 0x50) + 1;
        return uVar17;
      }
      lVar21 = 
      _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_bool>_set_Item__;
      if ((bRam0000000004a08213 & 1) == 0) {
        func_0x010c65d4(0x4173);
        bRam0000000004a08213 = 1;
      }
      plVar20 = *(long **)(lVar5 + 0x40);
      if (plVar20 == (long *)0x0) {
        func_0x010eec64(0);
      }
      lVar22 = *(long *)(*(long *)(*(long *)(lVar19 + 0x18) + 0xc0) + 0x118);
      func_0x010bbb2c(lVar22);
      lVar12 = *plVar20;
      if ((ulong)*(ushort *)(lVar12 + 0x122) != 0) {
        uVar17 = 0;
        piVar15 = (int *)(*(long *)(lVar12 + 0xb0) + 8);
        do {
          if (*(long *)(piVar15 + -2) == lVar22) {
            puVar8 = (undefined8 *)(lVar12 + (ulong)(*piVar15 + 1) * 0x10 + 0x130);
            goto code_r0x0314d894;
          }
          uVar17 = uVar17 + 1;
          piVar15 = piVar15 + 4;
        } while (uVar17 < *(ushort *)(lVar12 + 0x122));
      }
      puVar8 = (undefined8 *)func_0x010bc584(plVar20,lVar22,1);
code_r0x0314d894:
      uVar17 = puVar8[1];
      uVar9 = (*(code *)*puVar8)(plVar20,uVar4);
      lVar12 = *(long *)(lVar5 + 0x10);
      uVar3 = (uint)uVar9;
      if (lVar12 == 0) {
        uVar9 = func_0x010eec64(0);
        lVar12 = *(long *)(lVar5 + 0x10);
        if (lVar12 == 0) {
code_r0x0314dde4:
          auVar26 = func_0x010eec64(0);
          iVar11 = auVar26._8_4_;
          lVar5 = auVar26._0_8_;
          if ((bRam0000000004a08214 & 1) == 0) {
            func_0x010c65d4(0x3bd6);
            bRam0000000004a08214 = 1;
          }
          if (-1 < iVar11) {
            if (uVar17 == 0) {
              lVar19 = *(long *)(*(long *)(*(long *)(lVar21 + 0x18) + 0xc0) + 0x130);
              func_0x010bbb2c(lVar19);
              if (((*(byte *)(lVar19 + 0x12a) & 1) != 0) &&
                 (lVar19 = *(long *)(*(long *)(*(long *)(lVar21 + 0x18) + 0xc0) + 0x130),
                 func_0x010bbb2c(lVar19), *(int *)(lVar19 + 0xd8) == 0)) {
                uVar6 = *(undefined8 *)(*(long *)(*(long *)(lVar21 + 0x18) + 0xc0) + 0x130);
                func_0x010bbb2c(uVar6);
                func_0x010da55c(uVar6);
              }
              uVar17 = (*(code *)**(undefined8 **)
                                   (*(long *)(*(long *)(lVar21 + 0x18) + 0xc0) + 0x128))(0);
            }
            if (lVar5 == 0) {
              func_0x010eec64(0);
            }
            iVar13 = 0xc;
            if (iVar11 != 0) {
              iVar13 = (int)((float)iVar11 / 0.9) + 1;
            }
            *(ulong *)(lVar5 + 0x40) = uVar17;
            if (lVar5 == 0) {
              func_0x010eec64(0);
            }
            uVar4 = (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar21 + 0x18) + 0xc0) + 0x138))
                              (lVar5,iVar13);
            *(undefined4 *)(lVar5 + 0x50) = 0;
            return uVar4;
          }
          uVar6 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
          System_ArgumentOutOfRangeException___ctor(uVar6,_str_capacity,0);
          lVar5 = _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_bool>_Init__
          ;
          func_0x010eebec(uVar6,0);
          auVar25 = func_0x0112148c();
          lVar21 = auVar25._0_8_;
          uVar4 = auVar25._8_8_ & 0xffffffff;
          if ((bRam0000000004a08215 & 1) == 0) {
            func_0x010c65d4(0x3b80);
            bRam0000000004a08215 = 1;
          }
          uVar6 = func_0x01101ad4(_class_int___TypeInfo,uVar4);
          *(undefined8 *)(lVar21 + 0x10) = uVar6;
          uVar6 = func_0x01101ad4(_class_System_Collections_Generic_Link___TypeInfo,uVar4);
          *(undefined4 *)(lVar21 + 0x34) = 0xffffffff;
          *(undefined8 *)(lVar21 + 0x18) = uVar6;
          uVar6 = *(undefined8 *)(*(long *)(*(long *)(lVar5 + 0x18) + 0xc0) + 0x140);
          func_0x010bbb2c(uVar6);
          uVar6 = func_0x01101ad4(uVar6,uVar4);
          *(undefined8 *)(lVar21 + 0x20) = uVar6;
          uVar6 = *(undefined8 *)(*(long *)(*(long *)(lVar5 + 0x18) + 0xc0) + 0x148);
          func_0x010bbb2c(uVar6);
          uVar4 = func_0x01101ad4(uVar6,uVar4);
          lVar5 = *(long *)(lVar21 + 0x10);
          *(ulong *)(lVar21 + 0x28) = uVar4;
          *(undefined4 *)(lVar21 + 0x30) = 0;
          if (lVar5 == 0) {
            uVar4 = func_0x010eec64(0);
          }
          iVar11 = (int)((float)*(int *)(lVar5 + 0x18) * 0.9);
          *(int *)(lVar21 + 0x3c) = iVar11;
          if (iVar11 == 0) {
            lVar5 = *(long *)(lVar21 + 0x10);
            if (lVar5 == 0) {
              uVar4 = func_0x010eec64(0);
            }
            if (0 < *(int *)(lVar5 + 0x18)) {
              *(undefined4 *)(lVar21 + 0x3c) = 1;
            }
          }
          return uVar4;
        }
        iVar13 = 0;
        if (iRam0000000000000018 != 0) {
          iVar13 = (int)(uVar3 & 0x7fffffff) / iRam0000000000000018;
        }
        uStack_b8._0_4_ = (uVar3 & 0x7fffffff) - iVar13 * iRam0000000000000018;
      }
      else {
        iVar13 = *(int *)(lVar12 + 0x18);
        iVar2 = 0;
        if (iVar13 != 0) {
          iVar2 = (int)(uVar3 & 0x7fffffff) / iVar13;
        }
        uStack_b8._0_4_ = (uVar3 & 0x7fffffff) - iVar2 * iVar13;
      }
      uStack_c0 = uVar3 & 0x7fffffff;
      uVar14 = (ulong)(uint)uStack_b8;
      if (*(uint *)(lVar12 + 0x18) <= (uint)uStack_b8) {
        uVar6 = func_0x010ef850();
        uVar9 = func_0x010eebec(uVar6,0,0);
      }
      iVar13 = *(int *)(lVar12 + uVar14 * 4 + 0x20);
      if (iVar13 != 0) {
        uVar24 = iVar13 - 1;
        uVar18 = 0xffffffff;
        do {
          uVar23 = uVar24;
          lVar21 = *(long *)(lVar5 + 0x18);
          if (lVar21 == 0) {
            uVar9 = func_0x010eec64(0);
          }
          lVar12 = (long)(int)uVar23;
          if (*(uint *)(lVar21 + 0x18) <= uVar23) {
            uVar6 = func_0x010ef850();
            uVar9 = func_0x010eebec(uVar6,0,0);
          }
          if (*(uint *)(lVar21 + lVar12 * 8 + 0x20) == (uVar3 | 0x80000000)) {
            plVar20 = *(long **)(lVar5 + 0x40);
            lVar21 = *(long *)(lVar5 + 0x20);
            if (lVar21 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar21 + 0x18) <= uVar23) {
              uVar6 = func_0x010ef850();
              func_0x010eebec(uVar6,0,0);
            }
            uVar1 = *(undefined4 *)(lVar21 + lVar12 * 4 + 0x20);
            if (plVar20 == (long *)0x0) {
              func_0x010eec64(0);
            }
            lVar22 = *(long *)(*(long *)(*(long *)(lVar19 + 0x18) + 0xc0) + 0x118);
            func_0x010bbb2c(lVar22);
            lVar21 = *plVar20;
            if ((ulong)*(ushort *)(lVar21 + 0x122) != 0) {
              uVar17 = 0;
              puVar16 = (uint *)(*(long *)(lVar21 + 0xb0) + 8);
              do {
                if (*(long *)(puVar16 + -2) == lVar22) {
                  puVar8 = (undefined8 *)(lVar21 + (ulong)*puVar16 * 0x10 + 0x130);
                  goto code_r0x0314da20;
                }
                uVar17 = uVar17 + 1;
                puVar16 = puVar16 + 4;
              } while (uVar17 < *(ushort *)(lVar21 + 0x122));
            }
            puVar8 = (undefined8 *)func_0x010bc584(plVar20,lVar22,0);
code_r0x0314da20:
            lVar21 = puVar8[1];
            uVar17 = uVar4;
            uVar9 = (*(code *)*puVar8)(plVar20,uVar1);
            if ((uVar9 & 1) != 0) {
              if (uVar23 != 0xffffffff) {
                if (uVar18 == 0xffffffff) goto code_r0x0314dc4c;
                lVar22 = *(long *)(lVar5 + 0x18);
                lVar19 = lVar22;
                if (lVar22 == 0) {
                  uVar9 = func_0x010eec64(0);
                  lVar19 = *(long *)(lVar5 + 0x18);
                  if (lVar19 == 0) goto code_r0x0314dde4;
                }
                if (*(uint *)(lVar19 + 0x18) <= uVar23) {
                  uVar6 = func_0x010ef850();
                  uVar9 = func_0x010eebec(uVar6,0,0);
                }
                uVar1 = *(undefined4 *)(lVar19 + lVar12 * 8 + 0x24);
                if (*(uint *)(lVar22 + 0x18) <= uVar18) {
                  uVar6 = func_0x010ef850();
                  uVar9 = func_0x010eebec(uVar6,0,0);
                }
                *(undefined4 *)(lVar22 + (long)(int)uVar18 * 8 + 0x24) = uVar1;
                lVar21 = *(long *)(lVar5 + 0x18);
                if (lVar21 == 0) {
                  uVar9 = func_0x010eec64(0);
                }
                lVar19 = *(long *)(lVar5 + 0x10);
                if (lVar19 == 0) {
                  uVar9 = func_0x010eec64(0);
                }
                if (*(uint *)(lVar19 + 0x18) <= (uint)uStack_b8) {
                  uVar6 = func_0x010ef850();
                  uVar9 = func_0x010eebec(uVar6,0,0);
                }
                iVar11 = *(int *)(lVar19 + uVar14 * 4 + 0x20);
                if (*(uint *)(lVar21 + 0x18) <= uVar23) {
                  uVar6 = func_0x010ef850();
                  uVar9 = func_0x010eebec(uVar6,0,0);
                }
                *(int *)(lVar21 + lVar12 * 8 + 0x24) = iVar11 + -1;
                lVar21 = *(long *)(lVar5 + 0x10);
                if (lVar21 == 0) {
                  uVar9 = func_0x010eec64(0);
                }
                iVar11 = uVar23 + 1;
                if (*(uint *)(lVar21 + 0x18) <= (uint)uStack_b8) {
                  uVar6 = func_0x010ef850();
                  uVar9 = func_0x010eebec(uVar6,0,0);
                }
                lVar21 = lVar21 + uVar14 * 4;
                goto code_r0x0314dc48;
              }
              break;
            }
          }
          lVar21 = *(long *)(lVar5 + 0x18);
          if (lVar21 == 0) {
            uVar9 = func_0x010eec64(0);
          }
          if (*(uint *)(lVar21 + 0x18) <= uVar23) {
            uVar6 = func_0x010ef850();
            uVar9 = func_0x010eebec(uVar6,0,0);
          }
          uVar24 = *(uint *)(lVar21 + lVar12 * 8 + 0x24);
          uVar18 = uVar23;
        } while (uVar24 != 0xffffffff);
      }
      iVar13 = *(int *)(lVar5 + 0x38) + 1;
      *(int *)(lVar5 + 0x38) = iVar13;
      if (*(int *)(lVar5 + 0x3c) < iVar13) {
        uVar9 = (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar19 + 0x18) + 0xc0) + 0x120))
                          (lVar5);
        lVar21 = *(long *)(lVar5 + 0x10);
        if (lVar21 == 0) {
          uVar9 = func_0x010eec64(0);
        }
        iVar13 = *(int *)(lVar21 + 0x18);
        iVar2 = 0;
        if (iVar13 != 0) {
          iVar2 = (int)uStack_c0 / iVar13;
        }
        uStack_b8._0_4_ = uStack_c0 - iVar2 * iVar13;
      }
      uVar23 = *(uint *)(lVar5 + 0x34);
      if (uVar23 == 0xffffffff) {
        uVar23 = *(uint *)(lVar5 + 0x30);
        *(uint *)(lVar5 + 0x30) = uVar23 + 1;
      }
      else {
        lVar21 = *(long *)(lVar5 + 0x18);
        if (lVar21 == 0) {
          uVar9 = func_0x010eec64(0);
        }
        if (*(uint *)(lVar21 + 0x18) <= uVar23) {
          uVar6 = func_0x010ef850();
          uVar9 = func_0x010eebec(uVar6,0,0);
        }
        *(undefined4 *)(lVar5 + 0x34) = *(undefined4 *)(lVar21 + (long)(int)uVar23 * 8 + 0x24);
      }
      lVar21 = *(long *)(lVar5 + 0x18);
      if (lVar21 == 0) {
        uVar9 = func_0x010eec64(0);
      }
      lVar19 = *(long *)(lVar5 + 0x10);
      if (lVar19 == 0) {
        uVar9 = func_0x010eec64(0);
      }
      if (*(uint *)(lVar19 + 0x18) <= (uint)uStack_b8) {
        uVar6 = func_0x010ef850();
        uVar9 = func_0x010eebec(uVar6,0,0);
      }
      iVar13 = *(int *)(lVar19 + (long)(int)(uint)uStack_b8 * 4 + 0x20);
      lVar19 = (long)(int)uVar23;
      if (*(uint *)(lVar21 + 0x18) <= uVar23) {
        uVar6 = func_0x010ef850();
        uVar9 = func_0x010eebec(uVar6,0,0);
      }
      *(int *)(lVar21 + lVar19 * 8 + 0x24) = iVar13 + -1;
      lVar21 = *(long *)(lVar5 + 0x10);
      if (lVar21 == 0) {
        uVar9 = func_0x010eec64(0);
      }
      if (*(uint *)(lVar21 + 0x18) <= (uint)uStack_b8) {
        uVar6 = func_0x010ef850();
        uVar9 = func_0x010eebec(uVar6,0,0);
      }
      *(uint *)(lVar21 + (long)(int)(uint)uStack_b8 * 4 + 0x20) = uVar23 + 1;
      lVar21 = *(long *)(lVar5 + 0x18);
      if (lVar21 == 0) {
        uVar9 = func_0x010eec64(0);
      }
      if (*(uint *)(lVar21 + 0x18) <= uVar23) {
        uVar6 = func_0x010ef850();
        uVar9 = func_0x010eebec(uVar6,0,0);
      }
      *(uint *)(lVar21 + lVar19 * 8 + 0x20) = uVar3 | 0x80000000;
      lVar21 = *(long *)(lVar5 + 0x20);
      if (lVar21 == 0) {
        uVar9 = func_0x010eec64(0);
      }
      if (*(uint *)(lVar21 + 0x18) <= uVar23) {
        uVar6 = func_0x010ef850();
        uVar9 = func_0x010eebec(uVar6,0,0);
      }
      lVar21 = lVar21 + lVar19 * 4;
code_r0x0314dc48:
      *(int *)(lVar21 + 0x20) = iVar11;
code_r0x0314dc4c:
      lVar21 = *(long *)(lVar5 + 0x28);
      if (lVar21 == 0) {
        uVar9 = func_0x010eec64(0);
      }
      if (*(uint *)(lVar21 + 0x18) <= uVar23) {
        uVar6 = func_0x010ef850();
        uVar9 = func_0x010eebec(uVar6,0,0);
      }
      *(byte *)(lVar21 + (int)uVar23 + 0x20) = (byte)uVar10 & 1;
      *(int *)(lVar5 + 0x50) = *(int *)(lVar5 + 0x50) + 1;
      return uVar9;
    case 5:
      param_4 = (ulong)*(uint *)(lVar5 + 0x14);
      break;
    case 6:
      param_4 = (ulong)*(uint *)(lVar5 + 0x18);
      break;
    case 7:
      return (ulong)*(byte *)(lVar5 + 0x1c);
    case 8:
      param_4 = (ulong)*(byte *)(lVar5 + 0x1d);
      break;
    case 9:
      param_4 = (ulong)*(uint *)(lVar5 + 0x20);
    }
  }
  return param_4;
}


