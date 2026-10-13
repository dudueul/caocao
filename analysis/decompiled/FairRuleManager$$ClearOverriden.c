// name: FairRuleManager$$ClearOverriden
// entry: 01748b88

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

ulong FairRuleManager__ClearOverriden(long param_1,ulong param_2,undefined8 param_3,long param_4)

{
  int iVar1;
  undefined4 uVar2;
  uint uVar3;
  int iVar4;
  uint uVar5;
  uint uVar6;
  ulong uVar7;
  undefined8 *puVar8;
  undefined8 uVar9;
  undefined8 *puVar10;
  long lVar11;
  int *piVar12;
  uint *puVar13;
  ulong uVar14;
  undefined8 *puVar15;
  long lVar16;
  long lVar17;
  long *plVar18;
  long lVar19;
  long lVar20;
  uint uVar21;
  uint uVar22;
  undefined1 auVar23 [16];
  
  puVar15 = (undefined8 *)(param_2 & 0xffffffff);
  if ((bRam00000000049f9c0a & 1) == 0) {
    func_0x010c65d4(0x566b);
    bRam00000000049f9c0a = 1;
  }
  lVar16 = *(long *)(param_1 + 0x28);
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__ContainsKey
                    (lVar16,puVar15,
                     _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_ContainsKey__
                    );
  if ((uVar7 & 1) == 0) {
    return uVar7;
  }
  lVar16 = *(long *)(param_1 + 0x28);
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  lVar17 = _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_FairRule>_Remove__;
  if ((bRam0000000004a0822e & 1) == 0) {
    func_0x010c65d4(0x3cec);
    bRam0000000004a0822e = 1;
  }
  plVar18 = *(long **)(lVar16 + 0x40);
  if (plVar18 == (long *)0x0) {
    func_0x010eec64(0);
  }
  lVar19 = *(long *)(*(long *)(*(long *)(lVar17 + 0x18) + 0xc0) + 0x118);
  func_0x010bbb2c(lVar19);
  lVar11 = *plVar18;
  if ((ulong)*(ushort *)(lVar11 + 0x122) != 0) {
    uVar7 = 0;
    piVar12 = (int *)(*(long *)(lVar11 + 0xb0) + 8);
    do {
      if (*(long *)(piVar12 + -2) == lVar19) {
        puVar8 = (undefined8 *)(lVar11 + (ulong)(*piVar12 + 1) * 0x10 + 0x130);
        goto code_r0x03153978;
      }
      uVar7 = uVar7 + 1;
      piVar12 = piVar12 + 4;
    } while (uVar7 < *(ushort *)(lVar11 + 0x122));
  }
  puVar8 = (undefined8 *)func_0x010bc584(plVar18,lVar19,1);
code_r0x03153978:
  puVar10 = (undefined8 *)puVar8[1];
  uVar6 = (*(code *)*puVar8)(plVar18,puVar15);
  lVar11 = *(long *)(lVar16 + 0x10);
  if (lVar11 == 0) {
    func_0x010eec64(0);
    lVar11 = *(long *)(lVar16 + 0x10);
    if (lVar11 == 0) {
code_r0x03153d60:
      auVar23 = func_0x010eec64(0);
      lVar16 = auVar23._0_8_;
      uVar7 = auVar23._8_8_ & 0xffffffff;
      if ((bRam0000000004a0822f & 1) == 0) {
        func_0x010c65d4(0x402f);
        bRam0000000004a0822f = 1;
      }
      plVar18 = *(long **)(lVar16 + 0x40);
      if (plVar18 == (long *)0x0) {
        func_0x010eec64(0);
      }
      lVar11 = *(long *)(*(long *)(*(long *)(param_4 + 0x18) + 0xc0) + 0x118);
      func_0x010bbb2c(lVar11);
      lVar17 = *plVar18;
      if ((ulong)*(ushort *)(lVar17 + 0x122) != 0) {
        uVar14 = 0;
        piVar12 = (int *)(*(long *)(lVar17 + 0xb0) + 8);
        do {
          if (*(long *)(piVar12 + -2) == lVar11) {
            puVar15 = (undefined8 *)(lVar17 + (ulong)(*piVar12 + 1) * 0x10 + 0x130);
            goto code_r0x03153e34;
          }
          uVar14 = uVar14 + 1;
          piVar12 = piVar12 + 4;
        } while (uVar14 < *(ushort *)(lVar17 + 0x122));
      }
      puVar15 = (undefined8 *)func_0x010bc584(plVar18,lVar11,1);
code_r0x03153e34:
      uVar6 = (*(code *)*puVar15)(plVar18,uVar7,puVar15[1]);
      lVar17 = *(long *)(lVar16 + 0x10);
      if (lVar17 == 0) {
        func_0x010eec64(0);
        func_0x010eec64(0);
      }
      uVar22 = *(uint *)(lVar17 + 0x18);
      iVar1 = 0;
      if (uVar22 != 0) {
        iVar1 = (int)(uVar6 & 0x7fffffff) / (int)uVar22;
      }
      uVar3 = (uVar6 & 0x7fffffff) - iVar1 * uVar22;
      if (uVar22 <= uVar3) {
        uVar9 = func_0x010ef850();
        func_0x010eebec(uVar9,0,0);
      }
      iVar1 = *(int *)(lVar17 + (ulong)uVar3 * 4 + 0x20);
      if (iVar1 != 0) {
        uVar22 = iVar1 - 1;
        do {
          lVar17 = *(long *)(lVar16 + 0x18);
          if (lVar17 == 0) {
            func_0x010eec64(0);
          }
          lVar11 = (long)(int)uVar22;
          if (*(uint *)(lVar17 + 0x18) <= uVar22) {
            uVar9 = func_0x010ef850();
            func_0x010eebec(uVar9,0,0);
          }
          if (*(uint *)(lVar17 + lVar11 * 8 + 0x20) == (uVar6 | 0x80000000)) {
            plVar18 = *(long **)(lVar16 + 0x40);
            lVar17 = *(long *)(lVar16 + 0x20);
            if (lVar17 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar17 + 0x18) <= uVar22) {
              uVar9 = func_0x010ef850();
              func_0x010eebec(uVar9,0,0);
            }
            uVar2 = *(undefined4 *)(lVar17 + lVar11 * 4 + 0x20);
            if (plVar18 == (long *)0x0) {
              func_0x010eec64(0);
            }
            lVar19 = *(long *)(*(long *)(*(long *)(param_4 + 0x18) + 0xc0) + 0x118);
            func_0x010bbb2c(lVar19);
            lVar17 = *plVar18;
            if ((ulong)*(ushort *)(lVar17 + 0x122) != 0) {
              uVar14 = 0;
              puVar13 = (uint *)(*(long *)(lVar17 + 0xb0) + 8);
              do {
                if (*(long *)(puVar13 + -2) == lVar19) {
                  puVar15 = (undefined8 *)(lVar17 + (ulong)*puVar13 * 0x10 + 0x130);
                  goto code_r0x03153f88;
                }
                uVar14 = uVar14 + 1;
                puVar13 = puVar13 + 4;
              } while (uVar14 < *(ushort *)(lVar17 + 0x122));
            }
            puVar15 = (undefined8 *)func_0x010bc584(plVar18,lVar19,0);
code_r0x03153f88:
            uVar14 = (*(code *)*puVar15)(plVar18,uVar2,uVar7,puVar15[1]);
            if ((uVar14 & 1) != 0) {
              lVar16 = *(long *)(lVar16 + 0x28);
              if (lVar16 == 0) {
                func_0x010eec64(0);
              }
              if (*(uint *)(lVar16 + 0x18) <= uVar22) {
                uVar9 = func_0x010ef850();
                func_0x010eebec(uVar9,0,0);
              }
              *puVar10 = *(undefined8 *)(lVar16 + lVar11 * 8 + 0x20);
              return 1;
            }
          }
          lVar17 = *(long *)(lVar16 + 0x18);
          if (lVar17 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar17 + 0x18) <= uVar22) {
            uVar9 = func_0x010ef850();
            func_0x010eebec(uVar9,0,0);
          }
          uVar22 = *(uint *)(lVar17 + lVar11 * 8 + 0x24);
        } while (uVar22 != 0xffffffff);
      }
      *puVar10 = 0;
      return 0;
    }
    iVar1 = 0;
    if (iRam0000000000000018 != 0) {
      iVar1 = (int)(uVar6 & 0x7fffffff) / iRam0000000000000018;
    }
    uVar22 = (uVar6 & 0x7fffffff) - iVar1 * iRam0000000000000018;
  }
  else {
    iVar1 = *(int *)(lVar11 + 0x18);
    iVar4 = 0;
    if (iVar1 != 0) {
      iVar4 = (int)(uVar6 & 0x7fffffff) / iVar1;
    }
    uVar22 = (uVar6 & 0x7fffffff) - iVar4 * iVar1;
  }
  if (*(uint *)(lVar11 + 0x18) <= uVar22) {
    uVar9 = func_0x010ef850();
    func_0x010eebec(uVar9,0,0);
  }
  iVar1 = *(int *)(lVar11 + (ulong)uVar22 * 4 + 0x20);
  if (iVar1 != 0) {
    uVar3 = iVar1 - 1;
    uVar21 = 0xffffffff;
    do {
      uVar5 = uVar3;
      lVar11 = *(long *)(lVar16 + 0x18);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      lVar19 = (long)(int)uVar5;
      if (*(uint *)(lVar11 + 0x18) <= uVar5) {
        uVar9 = func_0x010ef850();
        func_0x010eebec(uVar9,0,0);
      }
      if (*(uint *)(lVar11 + lVar19 * 8 + 0x20) == (uVar6 | 0x80000000)) {
        plVar18 = *(long **)(lVar16 + 0x40);
        lVar11 = *(long *)(lVar16 + 0x20);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar11 + 0x18) <= uVar5) {
          uVar9 = func_0x010ef850();
          func_0x010eebec(uVar9,0,0);
        }
        uVar2 = *(undefined4 *)(lVar11 + lVar19 * 4 + 0x20);
        if (plVar18 == (long *)0x0) {
          func_0x010eec64(0);
        }
        lVar20 = *(long *)(*(long *)(*(long *)(lVar17 + 0x18) + 0xc0) + 0x118);
        func_0x010bbb2c(lVar20);
        lVar11 = *plVar18;
        if ((ulong)*(ushort *)(lVar11 + 0x122) != 0) {
          uVar7 = 0;
          puVar13 = (uint *)(*(long *)(lVar11 + 0xb0) + 8);
          do {
            if (*(long *)(puVar13 + -2) == lVar20) {
              puVar8 = (undefined8 *)(lVar11 + (ulong)*puVar13 * 0x10 + 0x130);
              goto code_r0x03153af4;
            }
            uVar7 = uVar7 + 1;
            puVar13 = puVar13 + 4;
          } while (uVar7 < *(ushort *)(lVar11 + 0x122));
        }
        puVar8 = (undefined8 *)func_0x010bc584(plVar18,lVar20,0);
code_r0x03153af4:
        param_4 = puVar8[1];
        puVar10 = puVar15;
        uVar7 = (*(code *)*puVar8)(plVar18,uVar2);
        if ((uVar7 & 1) != 0) {
          if (uVar5 == 0xffffffff) {
            return 0;
          }
          *(int *)(lVar16 + 0x38) = *(int *)(lVar16 + 0x38) + -1;
          if (uVar21 == 0xffffffff) {
            lVar17 = *(long *)(lVar16 + 0x10);
            lVar11 = *(long *)(lVar16 + 0x18);
            if (lVar11 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar11 + 0x18) <= uVar5) {
              uVar9 = func_0x010ef850();
              func_0x010eebec(uVar9,0,0);
            }
            iVar1 = *(int *)(lVar11 + (long)(int)uVar5 * 8 + 0x24);
            if (lVar17 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar17 + 0x18) <= uVar22) {
              uVar9 = func_0x010ef850();
              func_0x010eebec(uVar9,0,0);
            }
            *(int *)(lVar17 + (ulong)uVar22 * 4 + 0x20) = iVar1 + 1;
          }
          else {
            lVar11 = *(long *)(lVar16 + 0x18);
            lVar17 = lVar11;
            if (lVar11 == 0) {
              func_0x010eec64(0);
              lVar17 = *(long *)(lVar16 + 0x18);
              if (lVar17 == 0) goto code_r0x03153d60;
            }
            if (*(uint *)(lVar17 + 0x18) <= uVar5) {
              uVar9 = func_0x010ef850();
              func_0x010eebec(uVar9,0,0);
            }
            uVar2 = *(undefined4 *)(lVar17 + (long)(int)uVar5 * 8 + 0x24);
            if (*(uint *)(lVar11 + 0x18) <= uVar21) {
              uVar9 = func_0x010ef850();
              func_0x010eebec(uVar9,0,0);
            }
            *(undefined4 *)(lVar11 + (long)(int)uVar21 * 8 + 0x24) = uVar2;
          }
          lVar17 = *(long *)(lVar16 + 0x18);
          if (lVar17 == 0) {
            func_0x010eec64(0);
          }
          uVar2 = *(undefined4 *)(lVar16 + 0x34);
          if (*(uint *)(lVar17 + 0x18) <= uVar5) {
            uVar9 = func_0x010ef850();
            func_0x010eebec(uVar9,0,0);
          }
          *(undefined4 *)(lVar17 + lVar19 * 8 + 0x24) = uVar2;
          lVar17 = *(long *)(lVar16 + 0x18);
          *(uint *)(lVar16 + 0x34) = uVar5;
          if (lVar17 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar17 + 0x18) <= uVar5) {
            uVar9 = func_0x010ef850();
            func_0x010eebec(uVar9,0,0);
          }
          *(undefined4 *)(lVar17 + lVar19 * 8 + 0x20) = 0;
          lVar17 = *(long *)(lVar16 + 0x20);
          if (lVar17 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar17 + 0x18) <= uVar5) {
            uVar9 = func_0x010ef850();
            func_0x010eebec(uVar9,0,0);
          }
          *(undefined4 *)(lVar17 + lVar19 * 4 + 0x20) = 0;
          lVar17 = *(long *)(lVar16 + 0x28);
          if (lVar17 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar17 + 0x18) <= uVar5) {
            uVar9 = func_0x010ef850();
            func_0x010eebec(uVar9,0,0);
          }
          *(undefined8 *)(lVar17 + lVar19 * 8 + 0x20) = 0;
          *(int *)(lVar16 + 0x50) = *(int *)(lVar16 + 0x50) + 1;
          return 1;
        }
      }
      lVar11 = *(long *)(lVar16 + 0x18);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      if (*(uint *)(lVar11 + 0x18) <= uVar5) {
        uVar9 = func_0x010ef850();
        func_0x010eebec(uVar9,0,0);
      }
      uVar3 = *(uint *)(lVar11 + lVar19 * 8 + 0x24);
      uVar21 = uVar5;
    } while (uVar3 != 0xffffffff);
  }
  return 0;
}


