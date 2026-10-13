// name: FairRuleManager$$SetFairRulesByTier
// entry: 01748ccc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 FairRuleManager__SetFairRulesByTier(long param_1,uint param_2,long param_3,long param_4)

{
  uint uVar1;
  uint uVar2;
  int iVar3;
  undefined4 uVar4;
  uint uVar5;
  long lVar6;
  undefined8 uVar7;
  long lVar8;
  undefined8 *puVar9;
  undefined8 *puVar10;
  long lVar11;
  int *piVar12;
  uint *puVar13;
  ulong uVar14;
  ulong uVar15;
  undefined8 *puVar16;
  long *plVar17;
  int iVar18;
  long lVar19;
  long lVar20;
  uint uVar21;
  uint uVar22;
  undefined1 auVar23 [16];
  
  puVar16 = (undefined8 *)(ulong)param_2;
  if ((bRam00000000049f9c0d & 1) == 0) {
    func_0x010c65d4(0x567b);
    bRam00000000049f9c0d = 1;
  }
  if (param_3 == 0) {
    func_0x010eec64(0);
  }
  lVar6 = LitJson_JsonData__get_Item(param_3,_str_gameModeRule,0);
  if (lVar6 != 0) {
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar7 = LitJson_JsonMapper__ToJson(0,lVar6,0);
    lVar6 = LitJson_JsonMapper__ToObject<UserTechLocalInfos>
                      (0,uVar7,_mm_Method_LitJson_JsonMapper_ToObject<GameModeRule>__);
    uVar7 = 0;
    if ((lVar6 != 0) && (uVar7 = GameModeRule__get_id(lVar6,0), (uint)uVar7 == param_2)) {
      lVar8 = GameModeRule__get_fairTreasureEnhance(lVar6,0);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      iVar3 = System_Collections_Generic_List<ObscuredInt>__get_Count
                        (lVar8,_mm_Method_System_Collections_Generic_List<ObscuredInt>_get_Count__);
      iVar18 = 0;
      while (iVar18 < iVar3) {
        uVar7 = func_0x010fdda8(_class_FairRule_TypeInfo);
        iVar18 = iVar18 + 1;
        FairRule___ctor(uVar7,lVar6,iVar18);
        FairRuleManager__SetFairRuleByTier(param_1,puVar16,iVar18,uVar7);
      }
      uVar4 = FairRuleManager__GetTierGrade(param_1);
      uVar7 = FairRuleManager__GetFairRuleByTier(param_1,uVar4);
      uVar7 = FairRuleManager__SetFairRule(param_1,puVar16,uVar7);
    }
    return uVar7;
  }
  lVar6 = *(long *)(param_1 + 0x20);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  lVar8 = _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_Remove__;
  if ((bRam0000000004a0822e & 1) == 0) {
    func_0x010c65d4(0x3cec);
    bRam0000000004a0822e = 1;
  }
  plVar17 = *(long **)(lVar6 + 0x40);
  if (plVar17 == (long *)0x0) {
    func_0x010eec64(0);
  }
  lVar19 = *(long *)(*(long *)(*(long *)(lVar8 + 0x18) + 0xc0) + 0x118);
  func_0x010bbb2c(lVar19);
  lVar11 = *plVar17;
  if ((ulong)*(ushort *)(lVar11 + 0x122) != 0) {
    uVar14 = 0;
    piVar12 = (int *)(*(long *)(lVar11 + 0xb0) + 8);
    do {
      if (*(long *)(piVar12 + -2) == lVar19) {
        puVar9 = (undefined8 *)(lVar11 + (ulong)(*piVar12 + 1) * 0x10 + 0x130);
        goto code_r0x03153978;
      }
      uVar14 = uVar14 + 1;
      piVar12 = piVar12 + 4;
    } while (uVar14 < *(ushort *)(lVar11 + 0x122));
  }
  puVar9 = (undefined8 *)func_0x010bc584(plVar17,lVar19,1);
code_r0x03153978:
  puVar10 = (undefined8 *)puVar9[1];
  uVar5 = (*(code *)*puVar9)(plVar17,puVar16);
  lVar11 = *(long *)(lVar6 + 0x10);
  if (lVar11 == 0) {
    func_0x010eec64(0);
    lVar11 = *(long *)(lVar6 + 0x10);
    if (lVar11 == 0) {
code_r0x03153d60:
      auVar23 = func_0x010eec64(0);
      lVar6 = auVar23._0_8_;
      uVar14 = auVar23._8_8_ & 0xffffffff;
      if ((bRam0000000004a0822f & 1) == 0) {
        func_0x010c65d4(0x402f);
        bRam0000000004a0822f = 1;
      }
      plVar17 = *(long **)(lVar6 + 0x40);
      if (plVar17 == (long *)0x0) {
        func_0x010eec64(0);
      }
      lVar11 = *(long *)(*(long *)(*(long *)(param_4 + 0x18) + 0xc0) + 0x118);
      func_0x010bbb2c(lVar11);
      lVar8 = *plVar17;
      if ((ulong)*(ushort *)(lVar8 + 0x122) != 0) {
        uVar15 = 0;
        piVar12 = (int *)(*(long *)(lVar8 + 0xb0) + 8);
        do {
          if (*(long *)(piVar12 + -2) == lVar11) {
            puVar16 = (undefined8 *)(lVar8 + (ulong)(*piVar12 + 1) * 0x10 + 0x130);
            goto code_r0x03153e34;
          }
          uVar15 = uVar15 + 1;
          piVar12 = piVar12 + 4;
        } while (uVar15 < *(ushort *)(lVar8 + 0x122));
      }
      puVar16 = (undefined8 *)func_0x010bc584(plVar17,lVar11,1);
code_r0x03153e34:
      uVar5 = (*(code *)*puVar16)(plVar17,uVar14,puVar16[1]);
      lVar8 = *(long *)(lVar6 + 0x10);
      if (lVar8 == 0) {
        func_0x010eec64(0);
        func_0x010eec64(0);
      }
      uVar22 = *(uint *)(lVar8 + 0x18);
      iVar18 = 0;
      if (uVar22 != 0) {
        iVar18 = (int)(uVar5 & 0x7fffffff) / (int)uVar22;
      }
      uVar1 = (uVar5 & 0x7fffffff) - iVar18 * uVar22;
      if (uVar22 <= uVar1) {
        uVar7 = func_0x010ef850();
        func_0x010eebec(uVar7,0,0);
      }
      iVar18 = *(int *)(lVar8 + (ulong)uVar1 * 4 + 0x20);
      if (iVar18 != 0) {
        uVar22 = iVar18 - 1;
        do {
          lVar8 = *(long *)(lVar6 + 0x18);
          if (lVar8 == 0) {
            func_0x010eec64(0);
          }
          lVar11 = (long)(int)uVar22;
          if (*(uint *)(lVar8 + 0x18) <= uVar22) {
            uVar7 = func_0x010ef850();
            func_0x010eebec(uVar7,0,0);
          }
          if (*(uint *)(lVar8 + lVar11 * 8 + 0x20) == (uVar5 | 0x80000000)) {
            plVar17 = *(long **)(lVar6 + 0x40);
            lVar8 = *(long *)(lVar6 + 0x20);
            if (lVar8 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar8 + 0x18) <= uVar22) {
              uVar7 = func_0x010ef850();
              func_0x010eebec(uVar7,0,0);
            }
            uVar4 = *(undefined4 *)(lVar8 + lVar11 * 4 + 0x20);
            if (plVar17 == (long *)0x0) {
              func_0x010eec64(0);
            }
            lVar19 = *(long *)(*(long *)(*(long *)(param_4 + 0x18) + 0xc0) + 0x118);
            func_0x010bbb2c(lVar19);
            lVar8 = *plVar17;
            if ((ulong)*(ushort *)(lVar8 + 0x122) != 0) {
              uVar15 = 0;
              puVar13 = (uint *)(*(long *)(lVar8 + 0xb0) + 8);
              do {
                if (*(long *)(puVar13 + -2) == lVar19) {
                  puVar16 = (undefined8 *)(lVar8 + (ulong)*puVar13 * 0x10 + 0x130);
                  goto code_r0x03153f88;
                }
                uVar15 = uVar15 + 1;
                puVar13 = puVar13 + 4;
              } while (uVar15 < *(ushort *)(lVar8 + 0x122));
            }
            puVar16 = (undefined8 *)func_0x010bc584(plVar17,lVar19,0);
code_r0x03153f88:
            uVar15 = (*(code *)*puVar16)(plVar17,uVar4,uVar14,puVar16[1]);
            if ((uVar15 & 1) != 0) {
              lVar6 = *(long *)(lVar6 + 0x28);
              if (lVar6 == 0) {
                func_0x010eec64(0);
              }
              if (*(uint *)(lVar6 + 0x18) <= uVar22) {
                uVar7 = func_0x010ef850();
                func_0x010eebec(uVar7,0,0);
              }
              *puVar10 = *(undefined8 *)(lVar6 + lVar11 * 8 + 0x20);
              return 1;
            }
          }
          lVar8 = *(long *)(lVar6 + 0x18);
          if (lVar8 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar8 + 0x18) <= uVar22) {
            uVar7 = func_0x010ef850();
            func_0x010eebec(uVar7,0,0);
          }
          uVar22 = *(uint *)(lVar8 + lVar11 * 8 + 0x24);
        } while (uVar22 != 0xffffffff);
      }
      *puVar10 = 0;
      return 0;
    }
    iVar18 = 0;
    if (iRam0000000000000018 != 0) {
      iVar18 = (int)(uVar5 & 0x7fffffff) / iRam0000000000000018;
    }
    uVar22 = (uVar5 & 0x7fffffff) - iVar18 * iRam0000000000000018;
  }
  else {
    iVar18 = *(int *)(lVar11 + 0x18);
    iVar3 = 0;
    if (iVar18 != 0) {
      iVar3 = (int)(uVar5 & 0x7fffffff) / iVar18;
    }
    uVar22 = (uVar5 & 0x7fffffff) - iVar3 * iVar18;
  }
  if (*(uint *)(lVar11 + 0x18) <= uVar22) {
    uVar7 = func_0x010ef850();
    func_0x010eebec(uVar7,0,0);
  }
  iVar18 = *(int *)(lVar11 + (ulong)uVar22 * 4 + 0x20);
  if (iVar18 != 0) {
    uVar1 = iVar18 - 1;
    uVar21 = 0xffffffff;
    do {
      uVar2 = uVar1;
      lVar11 = *(long *)(lVar6 + 0x18);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      lVar19 = (long)(int)uVar2;
      if (*(uint *)(lVar11 + 0x18) <= uVar2) {
        uVar7 = func_0x010ef850();
        func_0x010eebec(uVar7,0,0);
      }
      if (*(uint *)(lVar11 + lVar19 * 8 + 0x20) == (uVar5 | 0x80000000)) {
        plVar17 = *(long **)(lVar6 + 0x40);
        lVar11 = *(long *)(lVar6 + 0x20);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar11 + 0x18) <= uVar2) {
          uVar7 = func_0x010ef850();
          func_0x010eebec(uVar7,0,0);
        }
        uVar4 = *(undefined4 *)(lVar11 + lVar19 * 4 + 0x20);
        if (plVar17 == (long *)0x0) {
          func_0x010eec64(0);
        }
        lVar20 = *(long *)(*(long *)(*(long *)(lVar8 + 0x18) + 0xc0) + 0x118);
        func_0x010bbb2c(lVar20);
        lVar11 = *plVar17;
        if ((ulong)*(ushort *)(lVar11 + 0x122) != 0) {
          uVar14 = 0;
          puVar13 = (uint *)(*(long *)(lVar11 + 0xb0) + 8);
          do {
            if (*(long *)(puVar13 + -2) == lVar20) {
              puVar9 = (undefined8 *)(lVar11 + (ulong)*puVar13 * 0x10 + 0x130);
              goto code_r0x03153af4;
            }
            uVar14 = uVar14 + 1;
            puVar13 = puVar13 + 4;
          } while (uVar14 < *(ushort *)(lVar11 + 0x122));
        }
        puVar9 = (undefined8 *)func_0x010bc584(plVar17,lVar20,0);
code_r0x03153af4:
        param_4 = puVar9[1];
        puVar10 = puVar16;
        uVar14 = (*(code *)*puVar9)(plVar17,uVar4);
        if ((uVar14 & 1) != 0) {
          if (uVar2 == 0xffffffff) {
            return 0;
          }
          *(int *)(lVar6 + 0x38) = *(int *)(lVar6 + 0x38) + -1;
          if (uVar21 == 0xffffffff) {
            lVar8 = *(long *)(lVar6 + 0x10);
            lVar11 = *(long *)(lVar6 + 0x18);
            if (lVar11 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar11 + 0x18) <= uVar2) {
              uVar7 = func_0x010ef850();
              func_0x010eebec(uVar7,0,0);
            }
            iVar18 = *(int *)(lVar11 + (long)(int)uVar2 * 8 + 0x24);
            if (lVar8 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar8 + 0x18) <= uVar22) {
              uVar7 = func_0x010ef850();
              func_0x010eebec(uVar7,0,0);
            }
            *(int *)(lVar8 + (ulong)uVar22 * 4 + 0x20) = iVar18 + 1;
          }
          else {
            lVar11 = *(long *)(lVar6 + 0x18);
            lVar8 = lVar11;
            if (lVar11 == 0) {
              func_0x010eec64(0);
              lVar8 = *(long *)(lVar6 + 0x18);
              if (lVar8 == 0) goto code_r0x03153d60;
            }
            if (*(uint *)(lVar8 + 0x18) <= uVar2) {
              uVar7 = func_0x010ef850();
              func_0x010eebec(uVar7,0,0);
            }
            uVar4 = *(undefined4 *)(lVar8 + (long)(int)uVar2 * 8 + 0x24);
            if (*(uint *)(lVar11 + 0x18) <= uVar21) {
              uVar7 = func_0x010ef850();
              func_0x010eebec(uVar7,0,0);
            }
            *(undefined4 *)(lVar11 + (long)(int)uVar21 * 8 + 0x24) = uVar4;
          }
          lVar8 = *(long *)(lVar6 + 0x18);
          if (lVar8 == 0) {
            func_0x010eec64(0);
          }
          uVar4 = *(undefined4 *)(lVar6 + 0x34);
          if (*(uint *)(lVar8 + 0x18) <= uVar2) {
            uVar7 = func_0x010ef850();
            func_0x010eebec(uVar7,0,0);
          }
          *(undefined4 *)(lVar8 + lVar19 * 8 + 0x24) = uVar4;
          lVar8 = *(long *)(lVar6 + 0x18);
          *(uint *)(lVar6 + 0x34) = uVar2;
          if (lVar8 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar8 + 0x18) <= uVar2) {
            uVar7 = func_0x010ef850();
            func_0x010eebec(uVar7,0,0);
          }
          *(undefined4 *)(lVar8 + lVar19 * 8 + 0x20) = 0;
          lVar8 = *(long *)(lVar6 + 0x20);
          if (lVar8 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar8 + 0x18) <= uVar2) {
            uVar7 = func_0x010ef850();
            func_0x010eebec(uVar7,0,0);
          }
          *(undefined4 *)(lVar8 + lVar19 * 4 + 0x20) = 0;
          lVar8 = *(long *)(lVar6 + 0x28);
          if (lVar8 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar8 + 0x18) <= uVar2) {
            uVar7 = func_0x010ef850();
            func_0x010eebec(uVar7,0,0);
          }
          *(undefined8 *)(lVar8 + lVar19 * 8 + 0x20) = 0;
          *(int *)(lVar6 + 0x50) = *(int *)(lVar6 + 0x50) + 1;
          return 1;
        }
      }
      lVar11 = *(long *)(lVar6 + 0x18);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      if (*(uint *)(lVar11 + 0x18) <= uVar2) {
        uVar7 = func_0x010ef850();
        func_0x010eebec(uVar7,0,0);
      }
      uVar1 = *(uint *)(lVar11 + lVar19 * 8 + 0x24);
      uVar21 = uVar2;
    } while (uVar1 != 0xffffffff);
  }
  return 0;
}


