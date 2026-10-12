// name: Chat.Chatting$$CleanChattingUser
// entry: 01d2eca8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

ulong Chat_Chatting__CleanChattingUser(ulong param_1,long param_2,undefined8 param_3,long param_4)

{
  int iVar1;
  uint uVar2;
  undefined4 uVar3;
  int iVar4;
  uint uVar5;
  uint uVar6;
  ulong uVar7;
  undefined8 *puVar8;
  undefined8 *puVar9;
  long lVar10;
  int *piVar11;
  uint *puVar12;
  undefined8 *puVar13;
  long lVar14;
  long lVar15;
  undefined8 uVar16;
  long *plVar17;
  long lVar18;
  long lVar19;
  uint uVar20;
  uint uVar21;
  undefined1 auVar22 [16];
  
  uVar7 = param_1;
  if ((bRam00000000049fcf3f & 1) == 0) {
    uVar7 = func_0x010c65d4(0x25b5);
    bRam00000000049fcf3f = 1;
  }
  if (param_2 != 0) {
    lVar14 = *(long *)(param_1 + 0x58);
    uVar16 = *(undefined8 *)(param_2 + 0x10);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = System_Collections_Generic_HashSet<UserUnit>__Contains
                      (lVar14,uVar16,
                       _mm_Method_System_Collections_Generic_HashSet<string>_Contains__);
    if ((uVar7 & 1) == 0) {
      if ((*(int *)(param_2 + 0x20) != 0) &&
         (uVar7 = Chat_Chatting__IsThereAnySameChannel
                            (param_1,param_2,*(undefined8 *)(param_1 + 0x48)), (uVar7 & 1) != 0)) {
        return uVar7;
      }
      lVar14 = *(long *)(param_1 + 0x60);
      puVar13 = *(undefined8 **)(param_2 + 0x10);
      if (lVar14 == 0) {
        func_0x010eec64(0);
      }
      lVar15 = _mm_Method_System_Collections_Generic_Dictionary<string,_ChattingUser>_Remove__;
      if ((bRam0000000004a0818c & 1) == 0) {
        func_0x010c65d4(0x3ccf);
        bRam0000000004a0818c = 1;
      }
      if (puVar13 != (undefined8 *)0x0) {
        plVar17 = *(long **)(lVar14 + 0x40);
        if (plVar17 == (long *)0x0) {
          func_0x010eec64(0);
        }
        lVar18 = *(long *)(*(long *)(*(long *)(lVar15 + 0x18) + 0xc0) + 0x118);
        func_0x010bbb2c(lVar18);
        lVar10 = *plVar17;
        if ((ulong)*(ushort *)(lVar10 + 0x122) != 0) {
          uVar7 = 0;
          piVar11 = (int *)(*(long *)(lVar10 + 0xb0) + 8);
          do {
            if (*(long *)(piVar11 + -2) == lVar18) {
              puVar8 = (undefined8 *)(lVar10 + (ulong)(*piVar11 + 1) * 0x10 + 0x130);
              goto code_r0x0312e514;
            }
            uVar7 = uVar7 + 1;
            piVar11 = piVar11 + 4;
          } while (uVar7 < *(ushort *)(lVar10 + 0x122));
        }
        puVar8 = (undefined8 *)func_0x010bc584(plVar17,lVar18,1);
code_r0x0312e514:
        puVar9 = (undefined8 *)puVar8[1];
        uVar6 = (*(code *)*puVar8)(plVar17,puVar13);
        lVar10 = *(long *)(lVar14 + 0x10);
        if (lVar10 == 0) {
          func_0x010eec64(0);
          lVar10 = *(long *)(lVar14 + 0x10);
          if (lVar10 == 0) goto code_r0x0312e940;
          iVar1 = 0;
          if (iRam0000000000000018 != 0) {
            iVar1 = (int)(uVar6 & 0x7fffffff) / iRam0000000000000018;
          }
          uVar21 = (uVar6 & 0x7fffffff) - iVar1 * iRam0000000000000018;
        }
        else {
          iVar1 = *(int *)(lVar10 + 0x18);
          iVar4 = 0;
          if (iVar1 != 0) {
            iVar4 = (int)(uVar6 & 0x7fffffff) / iVar1;
          }
          uVar21 = (uVar6 & 0x7fffffff) - iVar4 * iVar1;
        }
        if (*(uint *)(lVar10 + 0x18) <= uVar21) {
          uVar16 = func_0x010ef850();
          func_0x010eebec(uVar16,0,0);
        }
        iVar1 = *(int *)(lVar10 + (ulong)uVar21 * 4 + 0x20);
        if (iVar1 != 0) {
          uVar2 = iVar1 - 1;
          uVar20 = 0xffffffff;
          do {
            uVar5 = uVar2;
            lVar10 = *(long *)(lVar14 + 0x18);
            if (lVar10 == 0) {
              func_0x010eec64(0);
            }
            lVar18 = (long)(int)uVar5;
            if (*(uint *)(lVar10 + 0x18) <= uVar5) {
              uVar16 = func_0x010ef850();
              func_0x010eebec(uVar16,0,0);
            }
            if (*(uint *)(lVar10 + lVar18 * 8 + 0x20) == (uVar6 | 0x80000000)) {
              plVar17 = *(long **)(lVar14 + 0x40);
              lVar10 = *(long *)(lVar14 + 0x20);
              if (lVar10 == 0) {
                func_0x010eec64(0);
              }
              if (*(uint *)(lVar10 + 0x18) <= uVar5) {
                uVar16 = func_0x010ef850();
                func_0x010eebec(uVar16,0,0);
              }
              uVar16 = *(undefined8 *)(lVar10 + lVar18 * 8 + 0x20);
              if (plVar17 == (long *)0x0) {
                func_0x010eec64(0);
              }
              lVar19 = *(long *)(*(long *)(*(long *)(lVar15 + 0x18) + 0xc0) + 0x118);
              func_0x010bbb2c(lVar19);
              lVar10 = *plVar17;
              if ((ulong)*(ushort *)(lVar10 + 0x122) != 0) {
                uVar7 = 0;
                puVar12 = (uint *)(*(long *)(lVar10 + 0xb0) + 8);
                do {
                  if (*(long *)(puVar12 + -2) == lVar19) {
                    puVar8 = (undefined8 *)(lVar10 + (ulong)*puVar12 * 0x10 + 0x130);
                    goto code_r0x0312e690;
                  }
                  uVar7 = uVar7 + 1;
                  puVar12 = puVar12 + 4;
                } while (uVar7 < *(ushort *)(lVar10 + 0x122));
              }
              puVar8 = (undefined8 *)func_0x010bc584(plVar17,lVar19,0);
code_r0x0312e690:
              param_4 = puVar8[1];
              puVar9 = puVar13;
              uVar7 = (*(code *)*puVar8)(plVar17,uVar16);
              if ((uVar7 & 1) != 0) {
                if (uVar5 == 0xffffffff) {
                  return 0;
                }
                *(int *)(lVar14 + 0x38) = *(int *)(lVar14 + 0x38) + -1;
                if (uVar20 == 0xffffffff) {
                  lVar15 = *(long *)(lVar14 + 0x10);
                  lVar10 = *(long *)(lVar14 + 0x18);
                  if (lVar10 == 0) {
                    func_0x010eec64(0);
                  }
                  if (*(uint *)(lVar10 + 0x18) <= uVar5) {
                    uVar16 = func_0x010ef850();
                    func_0x010eebec(uVar16,0,0);
                  }
                  iVar1 = *(int *)(lVar10 + (long)(int)uVar5 * 8 + 0x24);
                  if (lVar15 == 0) {
                    func_0x010eec64(0);
                  }
                  if (*(uint *)(lVar15 + 0x18) <= uVar21) {
                    uVar16 = func_0x010ef850();
                    func_0x010eebec(uVar16,0,0);
                  }
                  *(int *)(lVar15 + (ulong)uVar21 * 4 + 0x20) = iVar1 + 1;
                }
                else {
                  lVar10 = *(long *)(lVar14 + 0x18);
                  lVar15 = lVar10;
                  if (lVar10 == 0) {
                    func_0x010eec64(0);
                    lVar15 = *(long *)(lVar14 + 0x18);
                    if (lVar15 == 0) goto code_r0x0312e940;
                  }
                  if (*(uint *)(lVar15 + 0x18) <= uVar5) {
                    uVar16 = func_0x010ef850();
                    func_0x010eebec(uVar16,0,0);
                  }
                  uVar3 = *(undefined4 *)(lVar15 + (long)(int)uVar5 * 8 + 0x24);
                  if (*(uint *)(lVar10 + 0x18) <= uVar20) {
                    uVar16 = func_0x010ef850();
                    func_0x010eebec(uVar16,0,0);
                  }
                  *(undefined4 *)(lVar10 + (long)(int)uVar20 * 8 + 0x24) = uVar3;
                }
                lVar15 = *(long *)(lVar14 + 0x18);
                if (lVar15 == 0) {
                  func_0x010eec64(0);
                }
                uVar3 = *(undefined4 *)(lVar14 + 0x34);
                if (*(uint *)(lVar15 + 0x18) <= uVar5) {
                  uVar16 = func_0x010ef850();
                  func_0x010eebec(uVar16,0,0);
                }
                *(undefined4 *)(lVar15 + lVar18 * 8 + 0x24) = uVar3;
                lVar15 = *(long *)(lVar14 + 0x18);
                *(uint *)(lVar14 + 0x34) = uVar5;
                if (lVar15 == 0) {
                  func_0x010eec64(0);
                }
                if (*(uint *)(lVar15 + 0x18) <= uVar5) {
                  uVar16 = func_0x010ef850();
                  func_0x010eebec(uVar16,0,0);
                }
                *(undefined4 *)(lVar15 + lVar18 * 8 + 0x20) = 0;
                lVar15 = *(long *)(lVar14 + 0x20);
                if (lVar15 == 0) {
                  func_0x010eec64(0);
                }
                if (*(uint *)(lVar15 + 0x18) <= uVar5) {
                  uVar16 = func_0x010ef850();
                  func_0x010eebec(uVar16,0,0);
                }
                *(undefined8 *)(lVar15 + lVar18 * 8 + 0x20) = 0;
                lVar15 = *(long *)(lVar14 + 0x28);
                if (lVar15 == 0) {
                  func_0x010eec64(0);
                }
                if (*(uint *)(lVar15 + 0x18) <= uVar5) {
                  uVar16 = func_0x010ef850();
                  func_0x010eebec(uVar16,0,0);
                }
                *(undefined8 *)(lVar15 + lVar18 * 8 + 0x20) = 0;
                *(int *)(lVar14 + 0x50) = *(int *)(lVar14 + 0x50) + 1;
                return 1;
              }
            }
            lVar10 = *(long *)(lVar14 + 0x18);
            if (lVar10 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar10 + 0x18) <= uVar5) {
              uVar16 = func_0x010ef850();
              func_0x010eebec(uVar16,0,0);
            }
            uVar2 = *(uint *)(lVar10 + lVar18 * 8 + 0x24);
            uVar20 = uVar5;
          } while (uVar2 != 0xffffffff);
        }
        return 0;
      }
      uVar16 = func_0x010fdda8(_class_System_ArgumentNullException_TypeInfo);
      System_ArgumentNullException___ctor(uVar16,_str_key,0);
      puVar9 = _mm_Method_System_Collections_Generic_Dictionary<object,_object>_Remove__;
      func_0x010eebec(uVar16,0);
      func_0x0112148c();
code_r0x0312e940:
      auVar22 = func_0x010eec64(0);
      lVar15 = auVar22._8_8_;
      lVar14 = auVar22._0_8_;
      if ((bRam0000000004a0818d & 1) == 0) {
        func_0x010c65d4(0x404b);
        bRam0000000004a0818d = 1;
      }
      if (lVar15 == 0) {
        uVar16 = func_0x010fdda8(_class_System_ArgumentNullException_TypeInfo);
        System_ArgumentNullException___ctor(uVar16,_str_key,0);
        func_0x010eebec(uVar16,0,
                        _mm_Method_System_Collections_Generic_Dictionary<object,_object>_TryGetValue__
                       );
        auVar22 = func_0x0112148c();
        uVar16 = *(undefined8 *)(*(long *)(*(long *)(auVar22._8_8_ + 0x18) + 0xc0) + 0x1a0);
        func_0x010bbb2c(uVar16);
        uVar7 = func_0x010fdda8(uVar16);
        (*(code *)**(undefined8 **)(*(long *)(*(long *)(auVar22._8_8_ + 0x18) + 0xc0) + 0x1a8))
                  (uVar7,auVar22._0_8_);
        return uVar7;
      }
      plVar17 = *(long **)(lVar14 + 0x40);
      if (plVar17 == (long *)0x0) {
        func_0x010eec64(0);
      }
      lVar18 = *(long *)(*(long *)(*(long *)(param_4 + 0x18) + 0xc0) + 0x118);
      func_0x010bbb2c(lVar18);
      lVar10 = *plVar17;
      if ((ulong)*(ushort *)(lVar10 + 0x122) != 0) {
        uVar7 = 0;
        piVar11 = (int *)(*(long *)(lVar10 + 0xb0) + 8);
        do {
          if (*(long *)(piVar11 + -2) == lVar18) {
            puVar13 = (undefined8 *)(lVar10 + (ulong)(*piVar11 + 1) * 0x10 + 0x130);
            goto code_r0x0312ea18;
          }
          uVar7 = uVar7 + 1;
          piVar11 = piVar11 + 4;
        } while (uVar7 < *(ushort *)(lVar10 + 0x122));
      }
      puVar13 = (undefined8 *)func_0x010bc584(plVar17,lVar18,1);
code_r0x0312ea18:
      uVar6 = (*(code *)*puVar13)(plVar17,lVar15,puVar13[1]);
      lVar10 = *(long *)(lVar14 + 0x10);
      if (lVar10 == 0) {
        func_0x010eec64(0);
        func_0x010eec64(0);
      }
      uVar21 = *(uint *)(lVar10 + 0x18);
      iVar1 = 0;
      if (uVar21 != 0) {
        iVar1 = (int)(uVar6 & 0x7fffffff) / (int)uVar21;
      }
      uVar2 = (uVar6 & 0x7fffffff) - iVar1 * uVar21;
      if (uVar21 <= uVar2) {
        uVar16 = func_0x010ef850();
        func_0x010eebec(uVar16,0,0);
      }
      iVar1 = *(int *)(lVar10 + (ulong)uVar2 * 4 + 0x20);
      if (iVar1 != 0) {
        uVar21 = iVar1 - 1;
        do {
          lVar10 = *(long *)(lVar14 + 0x18);
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          lVar18 = (long)(int)uVar21;
          if (*(uint *)(lVar10 + 0x18) <= uVar21) {
            uVar16 = func_0x010ef850();
            func_0x010eebec(uVar16,0,0);
          }
          if (*(uint *)(lVar10 + lVar18 * 8 + 0x20) == (uVar6 | 0x80000000)) {
            plVar17 = *(long **)(lVar14 + 0x40);
            lVar10 = *(long *)(lVar14 + 0x20);
            if (lVar10 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar10 + 0x18) <= uVar21) {
              uVar16 = func_0x010ef850();
              func_0x010eebec(uVar16,0,0);
            }
            uVar16 = *(undefined8 *)(lVar10 + lVar18 * 8 + 0x20);
            if (plVar17 == (long *)0x0) {
              func_0x010eec64(0);
            }
            lVar19 = *(long *)(*(long *)(*(long *)(param_4 + 0x18) + 0xc0) + 0x118);
            func_0x010bbb2c(lVar19);
            lVar10 = *plVar17;
            if ((ulong)*(ushort *)(lVar10 + 0x122) != 0) {
              uVar7 = 0;
              puVar12 = (uint *)(*(long *)(lVar10 + 0xb0) + 8);
              do {
                if (*(long *)(puVar12 + -2) == lVar19) {
                  puVar13 = (undefined8 *)(lVar10 + (ulong)*puVar12 * 0x10 + 0x130);
                  goto code_r0x0312eb6c;
                }
                uVar7 = uVar7 + 1;
                puVar12 = puVar12 + 4;
              } while (uVar7 < *(ushort *)(lVar10 + 0x122));
            }
            puVar13 = (undefined8 *)func_0x010bc584(plVar17,lVar19,0);
code_r0x0312eb6c:
            uVar7 = (*(code *)*puVar13)(plVar17,uVar16,lVar15,puVar13[1]);
            if ((uVar7 & 1) != 0) {
              lVar14 = *(long *)(lVar14 + 0x28);
              if (lVar14 == 0) {
                func_0x010eec64(0);
              }
              if (*(uint *)(lVar14 + 0x18) <= uVar21) {
                uVar16 = func_0x010ef850();
                func_0x010eebec(uVar16,0,0);
              }
              *puVar9 = *(undefined8 *)(lVar14 + lVar18 * 8 + 0x20);
              return 1;
            }
          }
          lVar10 = *(long *)(lVar14 + 0x18);
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar10 + 0x18) <= uVar21) {
            uVar16 = func_0x010ef850();
            func_0x010eebec(uVar16,0,0);
          }
          uVar21 = *(uint *)(lVar10 + lVar18 * 8 + 0x24);
        } while (uVar21 != 0xffffffff);
      }
      *puVar9 = 0;
      return 0;
    }
  }
  return uVar7;
}


