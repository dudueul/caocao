// name: Chat.Chatting$$OnBroadcastDisconnectingTo
// entry: 01d2e5e8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

ulong Chat_Chatting__OnBroadcastDisconnectingTo(ulong param_1,long param_2)

{
  int iVar1;
  uint uVar2;
  undefined4 uVar3;
  int iVar4;
  uint uVar5;
  uint uVar6;
  ulong uVar7;
  undefined8 uVar8;
  long lVar9;
  undefined8 *puVar10;
  undefined8 *puVar11;
  long lVar12;
  int *piVar13;
  uint *puVar14;
  undefined8 *puVar15;
  long lVar16;
  long *plVar17;
  long lVar18;
  long lVar19;
  long lVar20;
  uint uVar21;
  uint uVar22;
  undefined1 auVar23 [16];
  
  if ((bRam00000000049fcf2d & 1) == 0) {
    func_0x010c65d4(0x25da);
    bRam00000000049fcf2d = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_LitJson_JsonMapper_TypeInfo);
  }
  uVar8 = LitJson_JsonMapper__ToJson(0,uVar8,0);
  lVar9 = LitJson_JsonMapper__ToObject<UserTechLocalInfos>
                    (0,uVar8,_mm_Method_LitJson_JsonMapper_ToObject<ChattingUser>__);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  *(undefined4 *)(lVar9 + 0x20) = 0;
  uVar7 = param_1;
  if ((bRam00000000049fcf2f & 1) == 0) {
    uVar7 = func_0x010c65d4(0x25e3);
    bRam00000000049fcf2f = 1;
  }
  if (lVar9 == 0) {
    return uVar7;
  }
  lVar16 = *(long *)(param_1 + 0x60);
  uVar8 = *(undefined8 *)(lVar9 + 0x10);
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  lVar18 = _mm_Method_System_Collections_Generic_Dictionary<string,_ChattingUser>_set_Item__;
  System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>__set_Item(lVar16,uVar8,lVar9);
  if (*(long *)(param_1 + 200) != 0) {
    lVar18 = _mm_Method_System_EventHandler<ChattingUser>_Invoke__;
    System_EventHandler<object>__Invoke(*(long *)(param_1 + 200),param_1,lVar9);
  }
  Chat_Chatting__CleanJoinedChannelUUIDs(param_1,lVar9);
  uVar7 = param_1;
  if ((bRam00000000049fcf3f & 1) == 0) {
    uVar7 = func_0x010c65d4(0x25b5);
    bRam00000000049fcf3f = 1;
  }
  if (lVar9 != 0) {
    lVar16 = *(long *)(param_1 + 0x58);
    uVar8 = *(undefined8 *)(lVar9 + 0x10);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = System_Collections_Generic_HashSet<UserUnit>__Contains
                      (lVar16,uVar8,_mm_Method_System_Collections_Generic_HashSet<string>_Contains__
                      );
    if ((uVar7 & 1) == 0) {
      if ((*(int *)(lVar9 + 0x20) != 0) &&
         (uVar7 = Chat_Chatting__IsThereAnySameChannel
                            (param_1,lVar9,*(undefined8 *)(param_1 + 0x48)), (uVar7 & 1) != 0)) {
        return uVar7;
      }
      lVar16 = *(long *)(param_1 + 0x60);
      puVar15 = *(undefined8 **)(lVar9 + 0x10);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      lVar9 = _mm_Method_System_Collections_Generic_Dictionary<string,_ChattingUser>_Remove__;
      if ((bRam0000000004a0818c & 1) == 0) {
        func_0x010c65d4(0x3ccf);
        bRam0000000004a0818c = 1;
      }
      if (puVar15 != (undefined8 *)0x0) {
        plVar17 = *(long **)(lVar16 + 0x40);
        if (plVar17 == (long *)0x0) {
          func_0x010eec64(0);
        }
        lVar19 = *(long *)(*(long *)(*(long *)(lVar9 + 0x18) + 0xc0) + 0x118);
        func_0x010bbb2c(lVar19);
        lVar12 = *plVar17;
        if ((ulong)*(ushort *)(lVar12 + 0x122) != 0) {
          uVar7 = 0;
          piVar13 = (int *)(*(long *)(lVar12 + 0xb0) + 8);
          do {
            if (*(long *)(piVar13 + -2) == lVar19) {
              puVar10 = (undefined8 *)(lVar12 + (ulong)(*piVar13 + 1) * 0x10 + 0x130);
              goto code_r0x0312e514;
            }
            uVar7 = uVar7 + 1;
            piVar13 = piVar13 + 4;
          } while (uVar7 < *(ushort *)(lVar12 + 0x122));
        }
        puVar10 = (undefined8 *)func_0x010bc584(plVar17,lVar19,1);
code_r0x0312e514:
        puVar11 = (undefined8 *)puVar10[1];
        uVar6 = (*(code *)*puVar10)(plVar17,puVar15);
        lVar12 = *(long *)(lVar16 + 0x10);
        if (lVar12 == 0) {
          func_0x010eec64(0);
          lVar12 = *(long *)(lVar16 + 0x10);
          if (lVar12 == 0) goto code_r0x0312e940;
          iVar1 = 0;
          if (iRam0000000000000018 != 0) {
            iVar1 = (int)(uVar6 & 0x7fffffff) / iRam0000000000000018;
          }
          uVar22 = (uVar6 & 0x7fffffff) - iVar1 * iRam0000000000000018;
        }
        else {
          iVar1 = *(int *)(lVar12 + 0x18);
          iVar4 = 0;
          if (iVar1 != 0) {
            iVar4 = (int)(uVar6 & 0x7fffffff) / iVar1;
          }
          uVar22 = (uVar6 & 0x7fffffff) - iVar4 * iVar1;
        }
        if (*(uint *)(lVar12 + 0x18) <= uVar22) {
          uVar8 = func_0x010ef850();
          func_0x010eebec(uVar8,0,0);
        }
        iVar1 = *(int *)(lVar12 + (ulong)uVar22 * 4 + 0x20);
        if (iVar1 != 0) {
          uVar2 = iVar1 - 1;
          uVar21 = 0xffffffff;
          do {
            uVar5 = uVar2;
            lVar18 = *(long *)(lVar16 + 0x18);
            if (lVar18 == 0) {
              func_0x010eec64(0);
            }
            lVar12 = (long)(int)uVar5;
            if (*(uint *)(lVar18 + 0x18) <= uVar5) {
              uVar8 = func_0x010ef850();
              func_0x010eebec(uVar8,0,0);
            }
            if (*(uint *)(lVar18 + lVar12 * 8 + 0x20) == (uVar6 | 0x80000000)) {
              plVar17 = *(long **)(lVar16 + 0x40);
              lVar18 = *(long *)(lVar16 + 0x20);
              if (lVar18 == 0) {
                func_0x010eec64(0);
              }
              if (*(uint *)(lVar18 + 0x18) <= uVar5) {
                uVar8 = func_0x010ef850();
                func_0x010eebec(uVar8,0,0);
              }
              uVar8 = *(undefined8 *)(lVar18 + lVar12 * 8 + 0x20);
              if (plVar17 == (long *)0x0) {
                func_0x010eec64(0);
              }
              lVar19 = *(long *)(*(long *)(*(long *)(lVar9 + 0x18) + 0xc0) + 0x118);
              func_0x010bbb2c(lVar19);
              lVar18 = *plVar17;
              if ((ulong)*(ushort *)(lVar18 + 0x122) != 0) {
                uVar7 = 0;
                puVar14 = (uint *)(*(long *)(lVar18 + 0xb0) + 8);
                do {
                  if (*(long *)(puVar14 + -2) == lVar19) {
                    puVar10 = (undefined8 *)(lVar18 + (ulong)*puVar14 * 0x10 + 0x130);
                    goto code_r0x0312e690;
                  }
                  uVar7 = uVar7 + 1;
                  puVar14 = puVar14 + 4;
                } while (uVar7 < *(ushort *)(lVar18 + 0x122));
              }
              puVar10 = (undefined8 *)func_0x010bc584(plVar17,lVar19,0);
code_r0x0312e690:
              lVar18 = puVar10[1];
              puVar11 = puVar15;
              uVar7 = (*(code *)*puVar10)(plVar17,uVar8);
              if ((uVar7 & 1) != 0) {
                if (uVar5 == 0xffffffff) {
                  return 0;
                }
                *(int *)(lVar16 + 0x38) = *(int *)(lVar16 + 0x38) + -1;
                if (uVar21 == 0xffffffff) {
                  lVar9 = *(long *)(lVar16 + 0x10);
                  lVar18 = *(long *)(lVar16 + 0x18);
                  if (lVar18 == 0) {
                    func_0x010eec64(0);
                  }
                  if (*(uint *)(lVar18 + 0x18) <= uVar5) {
                    uVar8 = func_0x010ef850();
                    func_0x010eebec(uVar8,0,0);
                  }
                  iVar1 = *(int *)(lVar18 + (long)(int)uVar5 * 8 + 0x24);
                  if (lVar9 == 0) {
                    func_0x010eec64(0);
                  }
                  if (*(uint *)(lVar9 + 0x18) <= uVar22) {
                    uVar8 = func_0x010ef850();
                    func_0x010eebec(uVar8,0,0);
                  }
                  *(int *)(lVar9 + (ulong)uVar22 * 4 + 0x20) = iVar1 + 1;
                }
                else {
                  lVar19 = *(long *)(lVar16 + 0x18);
                  lVar9 = lVar19;
                  if (lVar19 == 0) {
                    func_0x010eec64(0);
                    lVar9 = *(long *)(lVar16 + 0x18);
                    if (lVar9 == 0) goto code_r0x0312e940;
                  }
                  if (*(uint *)(lVar9 + 0x18) <= uVar5) {
                    uVar8 = func_0x010ef850();
                    func_0x010eebec(uVar8,0,0);
                  }
                  uVar3 = *(undefined4 *)(lVar9 + (long)(int)uVar5 * 8 + 0x24);
                  if (*(uint *)(lVar19 + 0x18) <= uVar21) {
                    uVar8 = func_0x010ef850();
                    func_0x010eebec(uVar8,0,0);
                  }
                  *(undefined4 *)(lVar19 + (long)(int)uVar21 * 8 + 0x24) = uVar3;
                }
                lVar9 = *(long *)(lVar16 + 0x18);
                if (lVar9 == 0) {
                  func_0x010eec64(0);
                }
                uVar3 = *(undefined4 *)(lVar16 + 0x34);
                if (*(uint *)(lVar9 + 0x18) <= uVar5) {
                  uVar8 = func_0x010ef850();
                  func_0x010eebec(uVar8,0,0);
                }
                *(undefined4 *)(lVar9 + lVar12 * 8 + 0x24) = uVar3;
                lVar9 = *(long *)(lVar16 + 0x18);
                *(uint *)(lVar16 + 0x34) = uVar5;
                if (lVar9 == 0) {
                  func_0x010eec64(0);
                }
                if (*(uint *)(lVar9 + 0x18) <= uVar5) {
                  uVar8 = func_0x010ef850();
                  func_0x010eebec(uVar8,0,0);
                }
                *(undefined4 *)(lVar9 + lVar12 * 8 + 0x20) = 0;
                lVar9 = *(long *)(lVar16 + 0x20);
                if (lVar9 == 0) {
                  func_0x010eec64(0);
                }
                if (*(uint *)(lVar9 + 0x18) <= uVar5) {
                  uVar8 = func_0x010ef850();
                  func_0x010eebec(uVar8,0,0);
                }
                *(undefined8 *)(lVar9 + lVar12 * 8 + 0x20) = 0;
                lVar9 = *(long *)(lVar16 + 0x28);
                if (lVar9 == 0) {
                  func_0x010eec64(0);
                }
                if (*(uint *)(lVar9 + 0x18) <= uVar5) {
                  uVar8 = func_0x010ef850();
                  func_0x010eebec(uVar8,0,0);
                }
                *(undefined8 *)(lVar9 + lVar12 * 8 + 0x20) = 0;
                *(int *)(lVar16 + 0x50) = *(int *)(lVar16 + 0x50) + 1;
                return 1;
              }
            }
            lVar18 = *(long *)(lVar16 + 0x18);
            if (lVar18 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar18 + 0x18) <= uVar5) {
              uVar8 = func_0x010ef850();
              func_0x010eebec(uVar8,0,0);
            }
            uVar2 = *(uint *)(lVar18 + lVar12 * 8 + 0x24);
            uVar21 = uVar5;
          } while (uVar2 != 0xffffffff);
        }
        return 0;
      }
      uVar8 = func_0x010fdda8(_class_System_ArgumentNullException_TypeInfo);
      System_ArgumentNullException___ctor(uVar8,_str_key,0);
      puVar11 = _mm_Method_System_Collections_Generic_Dictionary<object,_object>_Remove__;
      func_0x010eebec(uVar8,0);
      func_0x0112148c();
code_r0x0312e940:
      auVar23 = func_0x010eec64(0);
      lVar16 = auVar23._8_8_;
      lVar9 = auVar23._0_8_;
      if ((bRam0000000004a0818d & 1) == 0) {
        func_0x010c65d4(0x404b);
        bRam0000000004a0818d = 1;
      }
      if (lVar16 == 0) {
        uVar8 = func_0x010fdda8(_class_System_ArgumentNullException_TypeInfo);
        System_ArgumentNullException___ctor(uVar8,_str_key,0);
        func_0x010eebec(uVar8,0,
                        _mm_Method_System_Collections_Generic_Dictionary<object,_object>_TryGetValue__
                       );
        auVar23 = func_0x0112148c();
        uVar8 = *(undefined8 *)(*(long *)(*(long *)(auVar23._8_8_ + 0x18) + 0xc0) + 0x1a0);
        func_0x010bbb2c(uVar8);
        uVar7 = func_0x010fdda8(uVar8);
        (*(code *)**(undefined8 **)(*(long *)(*(long *)(auVar23._8_8_ + 0x18) + 0xc0) + 0x1a8))
                  (uVar7,auVar23._0_8_);
        return uVar7;
      }
      plVar17 = *(long **)(lVar9 + 0x40);
      if (plVar17 == (long *)0x0) {
        func_0x010eec64(0);
      }
      lVar19 = *(long *)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x118);
      func_0x010bbb2c(lVar19);
      lVar12 = *plVar17;
      if ((ulong)*(ushort *)(lVar12 + 0x122) != 0) {
        uVar7 = 0;
        piVar13 = (int *)(*(long *)(lVar12 + 0xb0) + 8);
        do {
          if (*(long *)(piVar13 + -2) == lVar19) {
            puVar15 = (undefined8 *)(lVar12 + (ulong)(*piVar13 + 1) * 0x10 + 0x130);
            goto code_r0x0312ea18;
          }
          uVar7 = uVar7 + 1;
          piVar13 = piVar13 + 4;
        } while (uVar7 < *(ushort *)(lVar12 + 0x122));
      }
      puVar15 = (undefined8 *)func_0x010bc584(plVar17,lVar19,1);
code_r0x0312ea18:
      uVar6 = (*(code *)*puVar15)(plVar17,lVar16,puVar15[1]);
      lVar12 = *(long *)(lVar9 + 0x10);
      if (lVar12 == 0) {
        func_0x010eec64(0);
        func_0x010eec64(0);
      }
      uVar22 = *(uint *)(lVar12 + 0x18);
      iVar1 = 0;
      if (uVar22 != 0) {
        iVar1 = (int)(uVar6 & 0x7fffffff) / (int)uVar22;
      }
      uVar2 = (uVar6 & 0x7fffffff) - iVar1 * uVar22;
      if (uVar22 <= uVar2) {
        uVar8 = func_0x010ef850();
        func_0x010eebec(uVar8,0,0);
      }
      iVar1 = *(int *)(lVar12 + (ulong)uVar2 * 4 + 0x20);
      if (iVar1 != 0) {
        uVar22 = iVar1 - 1;
        do {
          lVar12 = *(long *)(lVar9 + 0x18);
          if (lVar12 == 0) {
            func_0x010eec64(0);
          }
          lVar19 = (long)(int)uVar22;
          if (*(uint *)(lVar12 + 0x18) <= uVar22) {
            uVar8 = func_0x010ef850();
            func_0x010eebec(uVar8,0,0);
          }
          if (*(uint *)(lVar12 + lVar19 * 8 + 0x20) == (uVar6 | 0x80000000)) {
            plVar17 = *(long **)(lVar9 + 0x40);
            lVar12 = *(long *)(lVar9 + 0x20);
            if (lVar12 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar12 + 0x18) <= uVar22) {
              uVar8 = func_0x010ef850();
              func_0x010eebec(uVar8,0,0);
            }
            uVar8 = *(undefined8 *)(lVar12 + lVar19 * 8 + 0x20);
            if (plVar17 == (long *)0x0) {
              func_0x010eec64(0);
            }
            lVar20 = *(long *)(*(long *)(*(long *)(lVar18 + 0x18) + 0xc0) + 0x118);
            func_0x010bbb2c(lVar20);
            lVar12 = *plVar17;
            if ((ulong)*(ushort *)(lVar12 + 0x122) != 0) {
              uVar7 = 0;
              puVar14 = (uint *)(*(long *)(lVar12 + 0xb0) + 8);
              do {
                if (*(long *)(puVar14 + -2) == lVar20) {
                  puVar15 = (undefined8 *)(lVar12 + (ulong)*puVar14 * 0x10 + 0x130);
                  goto code_r0x0312eb6c;
                }
                uVar7 = uVar7 + 1;
                puVar14 = puVar14 + 4;
              } while (uVar7 < *(ushort *)(lVar12 + 0x122));
            }
            puVar15 = (undefined8 *)func_0x010bc584(plVar17,lVar20,0);
code_r0x0312eb6c:
            uVar7 = (*(code *)*puVar15)(plVar17,uVar8,lVar16,puVar15[1]);
            if ((uVar7 & 1) != 0) {
              lVar9 = *(long *)(lVar9 + 0x28);
              if (lVar9 == 0) {
                func_0x010eec64(0);
              }
              if (*(uint *)(lVar9 + 0x18) <= uVar22) {
                uVar8 = func_0x010ef850();
                func_0x010eebec(uVar8,0,0);
              }
              *puVar11 = *(undefined8 *)(lVar9 + lVar19 * 8 + 0x20);
              return 1;
            }
          }
          lVar12 = *(long *)(lVar9 + 0x18);
          if (lVar12 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar12 + 0x18) <= uVar22) {
            uVar8 = func_0x010ef850();
            func_0x010eebec(uVar8,0,0);
          }
          uVar22 = *(uint *)(lVar12 + lVar19 * 8 + 0x24);
        } while (uVar22 != 0xffffffff);
      }
      *puVar11 = 0;
      return 0;
    }
  }
  return uVar7;
}


