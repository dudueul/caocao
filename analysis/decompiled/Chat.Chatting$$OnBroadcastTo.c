// name: Chat.Chatting$$OnBroadcastTo
// entry: 01d2d4cc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long * Chat_Chatting__OnBroadcastTo(long *param_1,long param_2,undefined8 param_3,long param_4)

{
  undefined1 *puVar1;
  int iVar2;
  uint uVar3;
  undefined4 uVar4;
  ushort uVar5;
  int iVar6;
  uint uVar7;
  uint uVar8;
  undefined8 uVar9;
  long lVar10;
  long *plVar11;
  long lVar12;
  long *plVar13;
  undefined8 uVar14;
  code *UNRECOVERED_JUMPTABLE;
  ulong uVar15;
  undefined8 *puVar16;
  undefined8 *puVar17;
  undefined8 *puVar18;
  long lVar19;
  int *piVar20;
  uint *puVar21;
  undefined8 unaff_x19;
  undefined8 unaff_x20;
  long unaff_x21;
  ulong uVar22;
  undefined *unaff_x22;
  long lVar23;
  undefined8 uVar24;
  undefined8 unaff_x23;
  undefined8 uVar25;
  long lVar26;
  undefined8 unaff_x24;
  undefined8 unaff_x25;
  long lVar27;
  undefined8 unaff_x26;
  uint uVar28;
  uint uVar29;
  undefined1 *unaff_x29;
  code *unaff_x30;
  undefined1 auVar30 [16];
  
  puVar1 = &stack0xfffffffffffffff0;
  if ((bRam00000000049fcf2b & 1) == 0) {
    func_0x010c65d4(0x25db);
    bRam00000000049fcf2b = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar9 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_LitJson_JsonMapper_TypeInfo);
  }
  uVar9 = LitJson_JsonMapper__ToJson(0,uVar9,0);
  lVar10 = LitJson_JsonMapper__ToObject<UserTechLocalInfos>
                     (0,uVar9,_mm_Method_LitJson_JsonMapper_ToObject<Broadcast>__);
  if (lVar10 == 0) {
    func_0x010eec64(0);
  }
  plVar11 = (long *)Chat_Broadcast__get_isMine(lVar10);
  if (((ulong)plVar11 & 1) == 0) {
    return plVar11;
  }
  if (lVar10 == 0) {
    plVar11 = (long *)func_0x010eec64(0);
  }
  uVar8 = *(uint *)(lVar10 + 0x10);
  if (8 < uVar8) {
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    unaff_x19 = System_String__Concat(0,_str_Invalid_broadcastType__data__,uVar9,0);
    unaff_x20 = func_0x010fdda8(_class_System_Exception_TypeInfo);
    System_Exception___ctor(unaff_x20,unaff_x19,0);
    func_0x010eebec(unaff_x20,0,_mm_Method_Chat_Chatting_OnBroadcastTo__);
    unaff_x30 = Chat_Chatting__OnChattingMessage;
    auVar30 = func_0x0112148c();
    plVar11 = auVar30._8_8_;
    param_1 = auVar30._0_8_;
    register0x00000008 = (BADSPACEBASE *)&stack0xffffffffffffffc0;
    unaff_x21 = lVar10;
    unaff_x22 = &class_LitJson_JsonMapper_TypeInfo;
    unaff_x29 = puVar1;
    goto code_r0x01d2dcc4;
  }
  if (0xc < *(uint *)(&UNK_03997e20 + (long)(int)uVar8 * 4)) {
    return plVar11;
  }
  switch(uVar8) {
  case 0:
    uVar9 = *(undefined8 *)(lVar10 + 0x18);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar10 = LitJson_JsonMapper__ToObject<UserTechLocalInfos>
                       (0,uVar9,_mm_Method_LitJson_JsonMapper_ToObject<ChattingUser>__);
    plVar11 = param_1;
    if ((bRam00000000049fcf2f & 1) == 0) {
      plVar11 = (long *)func_0x010c65d4(0x25e3);
      bRam00000000049fcf2f = 1;
    }
    if (lVar10 == 0) {
      return plVar11;
    }
    lVar23 = param_1[0xc];
    uVar9 = *(undefined8 *)(lVar10 + 0x10);
    if (lVar23 == 0) {
      func_0x010eec64(0);
    }
    lVar12 = _mm_Method_System_Collections_Generic_Dictionary<string,_ChattingUser>_set_Item__;
    System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>__set_Item
              (lVar23,uVar9,lVar10);
    if (param_1[0x19] != 0) {
      lVar12 = _mm_Method_System_EventHandler<ChattingUser>_Invoke__;
      System_EventHandler<object>__Invoke(param_1[0x19],param_1,lVar10);
    }
    Chat_Chatting__CleanJoinedChannelUUIDs(param_1,lVar10);
    plVar11 = param_1;
    if ((bRam00000000049fcf3f & 1) == 0) {
      plVar11 = (long *)func_0x010c65d4(0x25b5);
      bRam00000000049fcf3f = 1;
    }
    if (lVar10 != 0) {
      lVar23 = param_1[0xb];
      uVar9 = *(undefined8 *)(lVar10 + 0x10);
      if (lVar23 == 0) {
        func_0x010eec64(0);
      }
      plVar11 = (long *)System_Collections_Generic_HashSet<UserUnit>__Contains
                                  (lVar23,uVar9,
                                   _mm_Method_System_Collections_Generic_HashSet<string>_Contains__)
      ;
      if ((((ulong)plVar11 & 1) == 0) &&
         ((*(int *)(lVar10 + 0x20) == 0 ||
          (plVar11 = (long *)Chat_Chatting__IsThereAnySameChannel(param_1,lVar10,param_1[9]),
          ((ulong)plVar11 & 1) == 0)))) {
        lVar23 = param_1[0xc];
        puVar16 = *(undefined8 **)(lVar10 + 0x10);
        if (lVar23 == 0) {
          func_0x010eec64(0);
        }
        lVar10 = _mm_Method_System_Collections_Generic_Dictionary<string,_ChattingUser>_Remove__;
        if ((bRam0000000004a0818c & 1) == 0) {
          func_0x010c65d4(0x3ccf);
          bRam0000000004a0818c = 1;
        }
        if (puVar16 != (undefined8 *)0x0) {
          plVar11 = *(long **)(lVar23 + 0x40);
          if (plVar11 == (long *)0x0) {
            func_0x010eec64(0);
          }
          lVar26 = *(long *)(*(long *)(*(long *)(lVar10 + 0x18) + 0xc0) + 0x118);
          func_0x010bbb2c(lVar26);
          lVar19 = *plVar11;
          if ((ulong)*(ushort *)(lVar19 + 0x122) != 0) {
            uVar15 = 0;
            piVar20 = (int *)(*(long *)(lVar19 + 0xb0) + 8);
            do {
              if (*(long *)(piVar20 + -2) == lVar26) {
                puVar17 = (undefined8 *)(lVar19 + (ulong)(*piVar20 + 1) * 0x10 + 0x130);
                goto code_r0x0312e514;
              }
              uVar15 = uVar15 + 1;
              piVar20 = piVar20 + 4;
            } while (uVar15 < *(ushort *)(lVar19 + 0x122));
          }
          puVar17 = (undefined8 *)func_0x010bc584(plVar11,lVar26,1);
code_r0x0312e514:
          puVar18 = (undefined8 *)puVar17[1];
          uVar8 = (*(code *)*puVar17)(plVar11,puVar16);
          lVar19 = *(long *)(lVar23 + 0x10);
          if (lVar19 == 0) {
            func_0x010eec64(0);
            lVar19 = *(long *)(lVar23 + 0x10);
            if (lVar19 == 0) goto code_r0x0312e940;
            iVar2 = 0;
            if (iRam0000000000000018 != 0) {
              iVar2 = (int)(uVar8 & 0x7fffffff) / iRam0000000000000018;
            }
            uVar29 = (uVar8 & 0x7fffffff) - iVar2 * iRam0000000000000018;
          }
          else {
            iVar2 = *(int *)(lVar19 + 0x18);
            iVar6 = 0;
            if (iVar2 != 0) {
              iVar6 = (int)(uVar8 & 0x7fffffff) / iVar2;
            }
            uVar29 = (uVar8 & 0x7fffffff) - iVar6 * iVar2;
          }
          if (*(uint *)(lVar19 + 0x18) <= uVar29) {
            uVar9 = func_0x010ef850();
            func_0x010eebec(uVar9,0,0);
          }
          iVar2 = *(int *)(lVar19 + (ulong)uVar29 * 4 + 0x20);
          if (iVar2 != 0) {
            uVar3 = iVar2 - 1;
            uVar28 = 0xffffffff;
            do {
              uVar7 = uVar3;
              lVar12 = *(long *)(lVar23 + 0x18);
              if (lVar12 == 0) {
                func_0x010eec64(0);
              }
              lVar19 = (long)(int)uVar7;
              if (*(uint *)(lVar12 + 0x18) <= uVar7) {
                uVar9 = func_0x010ef850();
                func_0x010eebec(uVar9,0,0);
              }
              if (*(uint *)(lVar12 + lVar19 * 8 + 0x20) == (uVar8 | 0x80000000)) {
                plVar11 = *(long **)(lVar23 + 0x40);
                lVar12 = *(long *)(lVar23 + 0x20);
                if (lVar12 == 0) {
                  func_0x010eec64(0);
                }
                if (*(uint *)(lVar12 + 0x18) <= uVar7) {
                  uVar9 = func_0x010ef850();
                  func_0x010eebec(uVar9,0,0);
                }
                uVar9 = *(undefined8 *)(lVar12 + lVar19 * 8 + 0x20);
                if (plVar11 == (long *)0x0) {
                  func_0x010eec64(0);
                }
                lVar26 = *(long *)(*(long *)(*(long *)(lVar10 + 0x18) + 0xc0) + 0x118);
                func_0x010bbb2c(lVar26);
                lVar12 = *plVar11;
                if ((ulong)*(ushort *)(lVar12 + 0x122) != 0) {
                  uVar15 = 0;
                  puVar21 = (uint *)(*(long *)(lVar12 + 0xb0) + 8);
                  do {
                    if (*(long *)(puVar21 + -2) == lVar26) {
                      puVar17 = (undefined8 *)(lVar12 + (ulong)*puVar21 * 0x10 + 0x130);
                      goto code_r0x0312e690;
                    }
                    uVar15 = uVar15 + 1;
                    puVar21 = puVar21 + 4;
                  } while (uVar15 < *(ushort *)(lVar12 + 0x122));
                }
                puVar17 = (undefined8 *)func_0x010bc584(plVar11,lVar26,0);
code_r0x0312e690:
                lVar12 = puVar17[1];
                puVar18 = puVar16;
                uVar15 = (*(code *)*puVar17)(plVar11,uVar9);
                if ((uVar15 & 1) != 0) {
                  if (uVar7 == 0xffffffff) {
                    return (long *)0x0;
                  }
                  *(int *)(lVar23 + 0x38) = *(int *)(lVar23 + 0x38) + -1;
                  if (uVar28 == 0xffffffff) {
                    lVar10 = *(long *)(lVar23 + 0x10);
                    lVar12 = *(long *)(lVar23 + 0x18);
                    if (lVar12 == 0) {
                      func_0x010eec64(0);
                    }
                    if (*(uint *)(lVar12 + 0x18) <= uVar7) {
                      uVar9 = func_0x010ef850();
                      func_0x010eebec(uVar9,0,0);
                    }
                    iVar2 = *(int *)(lVar12 + (long)(int)uVar7 * 8 + 0x24);
                    if (lVar10 == 0) {
                      func_0x010eec64(0);
                    }
                    if (*(uint *)(lVar10 + 0x18) <= uVar29) {
                      uVar9 = func_0x010ef850();
                      func_0x010eebec(uVar9,0,0);
                    }
                    *(int *)(lVar10 + (ulong)uVar29 * 4 + 0x20) = iVar2 + 1;
                  }
                  else {
                    lVar26 = *(long *)(lVar23 + 0x18);
                    lVar10 = lVar26;
                    if (lVar26 == 0) {
                      func_0x010eec64(0);
                      lVar10 = *(long *)(lVar23 + 0x18);
                      if (lVar10 == 0) goto code_r0x0312e940;
                    }
                    if (*(uint *)(lVar10 + 0x18) <= uVar7) {
                      uVar9 = func_0x010ef850();
                      func_0x010eebec(uVar9,0,0);
                    }
                    uVar4 = *(undefined4 *)(lVar10 + (long)(int)uVar7 * 8 + 0x24);
                    if (*(uint *)(lVar26 + 0x18) <= uVar28) {
                      uVar9 = func_0x010ef850();
                      func_0x010eebec(uVar9,0,0);
                    }
                    *(undefined4 *)(lVar26 + (long)(int)uVar28 * 8 + 0x24) = uVar4;
                  }
                  lVar10 = *(long *)(lVar23 + 0x18);
                  if (lVar10 == 0) {
                    func_0x010eec64(0);
                  }
                  uVar4 = *(undefined4 *)(lVar23 + 0x34);
                  if (*(uint *)(lVar10 + 0x18) <= uVar7) {
                    uVar9 = func_0x010ef850();
                    func_0x010eebec(uVar9,0,0);
                  }
                  *(undefined4 *)(lVar10 + lVar19 * 8 + 0x24) = uVar4;
                  lVar10 = *(long *)(lVar23 + 0x18);
                  *(uint *)(lVar23 + 0x34) = uVar7;
                  if (lVar10 == 0) {
                    func_0x010eec64(0);
                  }
                  if (*(uint *)(lVar10 + 0x18) <= uVar7) {
                    uVar9 = func_0x010ef850();
                    func_0x010eebec(uVar9,0,0);
                  }
                  *(undefined4 *)(lVar10 + lVar19 * 8 + 0x20) = 0;
                  lVar10 = *(long *)(lVar23 + 0x20);
                  if (lVar10 == 0) {
                    func_0x010eec64(0);
                  }
                  if (*(uint *)(lVar10 + 0x18) <= uVar7) {
                    uVar9 = func_0x010ef850();
                    func_0x010eebec(uVar9,0,0);
                  }
                  *(undefined8 *)(lVar10 + lVar19 * 8 + 0x20) = 0;
                  lVar10 = *(long *)(lVar23 + 0x28);
                  if (lVar10 == 0) {
                    func_0x010eec64(0);
                  }
                  if (*(uint *)(lVar10 + 0x18) <= uVar7) {
                    uVar9 = func_0x010ef850();
                    func_0x010eebec(uVar9,0,0);
                  }
                  *(undefined8 *)(lVar10 + lVar19 * 8 + 0x20) = 0;
                  *(int *)(lVar23 + 0x50) = *(int *)(lVar23 + 0x50) + 1;
                  return (long *)0x1;
                }
              }
              lVar12 = *(long *)(lVar23 + 0x18);
              if (lVar12 == 0) {
                func_0x010eec64(0);
              }
              if (*(uint *)(lVar12 + 0x18) <= uVar7) {
                uVar9 = func_0x010ef850();
                func_0x010eebec(uVar9,0,0);
              }
              uVar3 = *(uint *)(lVar12 + lVar19 * 8 + 0x24);
              uVar28 = uVar7;
            } while (uVar3 != 0xffffffff);
          }
          return (long *)0x0;
        }
        uVar9 = func_0x010fdda8(_class_System_ArgumentNullException_TypeInfo);
        System_ArgumentNullException___ctor(uVar9,_str_key,0);
        puVar18 = _mm_Method_System_Collections_Generic_Dictionary<object,_object>_Remove__;
        func_0x010eebec(uVar9,0);
        func_0x0112148c();
code_r0x0312e940:
        auVar30 = func_0x010eec64(0);
        lVar23 = auVar30._8_8_;
        lVar10 = auVar30._0_8_;
        if ((bRam0000000004a0818d & 1) == 0) {
          func_0x010c65d4(0x404b);
          bRam0000000004a0818d = 1;
        }
        if (lVar23 == 0) {
          uVar9 = func_0x010fdda8(_class_System_ArgumentNullException_TypeInfo);
          System_ArgumentNullException___ctor(uVar9,_str_key,0);
          func_0x010eebec(uVar9,0,
                          _mm_Method_System_Collections_Generic_Dictionary<object,_object>_TryGetValue__
                         );
          auVar30 = func_0x0112148c();
          uVar9 = *(undefined8 *)(*(long *)(*(long *)(auVar30._8_8_ + 0x18) + 0xc0) + 0x1a0);
          func_0x010bbb2c(uVar9);
          plVar11 = (long *)func_0x010fdda8(uVar9);
          (*(code *)**(undefined8 **)(*(long *)(*(long *)(auVar30._8_8_ + 0x18) + 0xc0) + 0x1a8))
                    (plVar11,auVar30._0_8_);
          return plVar11;
        }
        plVar11 = *(long **)(lVar10 + 0x40);
        if (plVar11 == (long *)0x0) {
          func_0x010eec64(0);
        }
        lVar26 = *(long *)(*(long *)(*(long *)(lVar12 + 0x18) + 0xc0) + 0x118);
        func_0x010bbb2c(lVar26);
        lVar19 = *plVar11;
        if ((ulong)*(ushort *)(lVar19 + 0x122) != 0) {
          uVar15 = 0;
          piVar20 = (int *)(*(long *)(lVar19 + 0xb0) + 8);
          do {
            if (*(long *)(piVar20 + -2) == lVar26) {
              puVar16 = (undefined8 *)(lVar19 + (ulong)(*piVar20 + 1) * 0x10 + 0x130);
              goto code_r0x0312ea18;
            }
            uVar15 = uVar15 + 1;
            piVar20 = piVar20 + 4;
          } while (uVar15 < *(ushort *)(lVar19 + 0x122));
        }
        puVar16 = (undefined8 *)func_0x010bc584(plVar11,lVar26,1);
code_r0x0312ea18:
        uVar8 = (*(code *)*puVar16)(plVar11,lVar23,puVar16[1]);
        lVar19 = *(long *)(lVar10 + 0x10);
        if (lVar19 == 0) {
          func_0x010eec64(0);
          func_0x010eec64(0);
        }
        uVar29 = *(uint *)(lVar19 + 0x18);
        iVar2 = 0;
        if (uVar29 != 0) {
          iVar2 = (int)(uVar8 & 0x7fffffff) / (int)uVar29;
        }
        uVar3 = (uVar8 & 0x7fffffff) - iVar2 * uVar29;
        if (uVar29 <= uVar3) {
          uVar9 = func_0x010ef850();
          func_0x010eebec(uVar9,0,0);
        }
        iVar2 = *(int *)(lVar19 + (ulong)uVar3 * 4 + 0x20);
        if (iVar2 != 0) {
          uVar29 = iVar2 - 1;
          do {
            lVar19 = *(long *)(lVar10 + 0x18);
            if (lVar19 == 0) {
              func_0x010eec64(0);
            }
            lVar26 = (long)(int)uVar29;
            if (*(uint *)(lVar19 + 0x18) <= uVar29) {
              uVar9 = func_0x010ef850();
              func_0x010eebec(uVar9,0,0);
            }
            if (*(uint *)(lVar19 + lVar26 * 8 + 0x20) == (uVar8 | 0x80000000)) {
              plVar11 = *(long **)(lVar10 + 0x40);
              lVar19 = *(long *)(lVar10 + 0x20);
              if (lVar19 == 0) {
                func_0x010eec64(0);
              }
              if (*(uint *)(lVar19 + 0x18) <= uVar29) {
                uVar9 = func_0x010ef850();
                func_0x010eebec(uVar9,0,0);
              }
              uVar9 = *(undefined8 *)(lVar19 + lVar26 * 8 + 0x20);
              if (plVar11 == (long *)0x0) {
                func_0x010eec64(0);
              }
              lVar27 = *(long *)(*(long *)(*(long *)(lVar12 + 0x18) + 0xc0) + 0x118);
              func_0x010bbb2c(lVar27);
              lVar19 = *plVar11;
              if ((ulong)*(ushort *)(lVar19 + 0x122) != 0) {
                uVar15 = 0;
                puVar21 = (uint *)(*(long *)(lVar19 + 0xb0) + 8);
                do {
                  if (*(long *)(puVar21 + -2) == lVar27) {
                    puVar16 = (undefined8 *)(lVar19 + (ulong)*puVar21 * 0x10 + 0x130);
                    goto code_r0x0312eb6c;
                  }
                  uVar15 = uVar15 + 1;
                  puVar21 = puVar21 + 4;
                } while (uVar15 < *(ushort *)(lVar19 + 0x122));
              }
              puVar16 = (undefined8 *)func_0x010bc584(plVar11,lVar27,0);
code_r0x0312eb6c:
              uVar15 = (*(code *)*puVar16)(plVar11,uVar9,lVar23,puVar16[1]);
              if ((uVar15 & 1) != 0) {
                lVar10 = *(long *)(lVar10 + 0x28);
                if (lVar10 == 0) {
                  func_0x010eec64(0);
                }
                if (*(uint *)(lVar10 + 0x18) <= uVar29) {
                  uVar9 = func_0x010ef850();
                  func_0x010eebec(uVar9,0,0);
                }
                *puVar18 = *(undefined8 *)(lVar10 + lVar26 * 8 + 0x20);
                return (long *)0x1;
              }
            }
            lVar19 = *(long *)(lVar10 + 0x18);
            if (lVar19 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar19 + 0x18) <= uVar29) {
              uVar9 = func_0x010ef850();
              func_0x010eebec(uVar9,0,0);
            }
            uVar29 = *(uint *)(lVar19 + lVar26 * 8 + 0x24);
          } while (uVar29 != 0xffffffff);
        }
        *puVar18 = 0;
        return (long *)0x0;
      }
    }
    return plVar11;
  default:
    uVar9 = *(undefined8 *)(lVar10 + 0x18);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    plVar11 = (long *)LitJson_JsonMapper__ToObject<UserTechLocalInfos>
                                (0,uVar9,_mm_Method_LitJson_JsonMapper_ToObject<ChattingMessage>__);
    break;
  case 2:
    uVar9 = *(undefined8 *)(lVar10 + 0x18);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar10 = LitJson_JsonMapper__ToObject<UserTechLocalInfos>
                       (0,uVar9,_mm_Method_LitJson_JsonMapper_ToObject<ChattingUserRequest>__);
    if ((bRam00000000049fcf31 & 1) == 0) {
      func_0x010c65d4(0x25e2);
      bRam00000000049fcf31 = 1;
    }
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    uVar9 = *(undefined8 *)(lVar10 + 0x10);
    lVar23 = param_1[9];
    if (lVar23 == 0) {
      func_0x010eec64(0);
    }
    uVar14 = *(undefined8 *)(lVar23 + 0x18);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    plVar11 = (long *)System_String__op_Equality(0,uVar9,uVar14,0);
    if (((ulong)plVar11 & 1) != 0) {
      return plVar11;
    }
    lVar23 = param_1[9];
    if (lVar10 == 0) {
      plVar11 = (long *)func_0x010eec64(0);
    }
    uVar9 = Chat_Chatting__GetSubRoomId(plVar11,*(undefined8 *)(lVar10 + 0x10));
    plVar11 = param_1;
    if ((bRam00000000049fcf28 & 1) == 0) {
      plVar11 = (long *)func_0x010c65d4(0x25bc);
      bRam00000000049fcf28 = 1;
    }
    if ((char)param_1[0x10] != '\0') {
      lVar10 = param_1[4];
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar14 = LitJson_JsonMapper__ToJson(0,lVar23,0);
      lVar23 = func_0x010fdda8(_class_Chat_Broadcast_TypeInfo);
      System_Object___ctor(lVar23,0);
      *(undefined4 *)(lVar23 + 0x10) = 0;
      *(undefined8 *)(lVar23 + 0x18) = uVar14;
      *(undefined8 *)(lVar23 + 0x20) = uVar9;
      *(undefined4 *)(lVar23 + 0x28) = 0xffffffff;
      uVar9 = LitJson_JsonMapper__ToJson(0,lVar23,0);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      uVar14 = _str_broadcastTo;
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,_str_broadcastTo,uVar9,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar9 = System_String__Format(0,_str____0____1__,uVar14,uVar9,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar14 = LitJson_JsonMapper__ToObject(0,uVar9,0);
      lVar12 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar9 = _str__;
      System_Object___ctor(lVar12,0);
      *(undefined4 *)(lVar12 + 0x14) = 2;
      *(undefined4 *)(lVar12 + 0x18) = 0;
      *(undefined8 *)(lVar12 + 0x30) = uVar14;
      *(undefined8 *)(lVar12 + 0x20) = uVar9;
      *(undefined4 *)(lVar12 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar12 + 0x10) = 4;
      lVar23 = lVar10;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar23 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar19 = *(long *)(lVar10 + 0x48);
      if (*(long *)(lVar10 + 0x50) == 0) {
        lVar23 = func_0x010eec64(0);
      }
      uVar9 = SocketIO_Encoder__Encode(lVar23,lVar12);
      if (lVar19 == 0) {
        func_0x010eec64(0);
      }
      plVar11 = (long *)WebSocketSharp_WebSocket__Send(lVar19,uVar9,0);
      return plVar11;
    }
    return plVar11;
  case 3:
  case 7:
    uVar9 = *(undefined8 *)(lVar10 + 0x18);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    plVar11 = (long *)LitJson_JsonMapper__ToObject<UserTechLocalInfos>
                                (0,uVar9,_mm_Method_LitJson_JsonMapper_ToObject<ChattingMessage>__);
    if (plVar11 == (long *)0x0) {
      func_0x010eec64(0);
    }
    Chat_ChattingMessage__DeserializeSubType(plVar11);
    break;
  case 4:
    uVar9 = *(undefined8 *)(lVar10 + 0x18);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    plVar11 = (long *)LitJson_JsonMapper__ToObject<UserTechLocalInfos>
                                (0,uVar9,_mm_Method_LitJson_JsonMapper_ToObject<WhisperResponse>__);
    plVar13 = param_1;
    if ((bRam00000000049fcf32 & 1) == 0) {
      plVar13 = (long *)func_0x010c65d4(0x25ee);
      bRam00000000049fcf32 = 1;
    }
    if (plVar11 == (long *)0x0) {
      return plVar13;
    }
    Chat_WhisperResponse__DeserializeSubType(plVar11);
    if (param_1[0x1a] != 0) {
      param_4 = _mm_Method_System_EventHandler<WhisperResponse>_Invoke__;
      System_EventHandler<object>__Invoke(param_1[0x1a],param_1,plVar11);
    }
    lVar23 = param_1[0x12];
    lVar10 = plVar11[5];
    if (lVar23 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_List<int>__Remove
              (lVar23,(int)lVar10,_mm_Method_System_Collections_Generic_List<int>_Remove__);
    goto Chat_Chatting__AddChatMsg;
  case 5:
    uVar9 = *(undefined8 *)(lVar10 + 0x18);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar10 = LitJson_JsonMapper__ToObject<UserTechLocalInfos>
                       (0,uVar9,_mm_Method_LitJson_JsonMapper_ToObject<TypeValuePair>__);
    uVar9 = _class_Chat_Chatting_var;
    if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_System_Type_TypeInfo);
    }
    lVar23 = System_Type__GetTypeFromHandle(0,uVar9,0);
    if (lVar23 == 0) {
      func_0x010eec64(0);
    }
    plVar11 = (long *)System_Type__GetMethod(lVar23,_str_ToObjectForGenericReflection,0);
    plVar13 = (long *)func_0x01101ad4(_class_System_Type___TypeInfo,1);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    uVar14 = *(undefined8 *)(lVar10 + 0x10);
    uVar9 = func_0x010e75f0(uVar14,&UNK_039933a8);
    lVar23 = System_Type__GetType(0,uVar9,0);
    if (lVar23 == 0) {
      lVar23 = System_Type__GetType(0,uVar14,0);
    }
    if (plVar13 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((lVar23 != 0) &&
       (lVar12 = func_0x010fdfcc(lVar23,*(undefined8 *)(*plVar13 + 0x40)), lVar12 == 0)) {
      uVar9 = func_0x010efe90();
      func_0x010eebec(uVar9,0,0);
    }
    if ((int)plVar13[3] == 0) {
      uVar9 = func_0x010ef850();
      func_0x010eebec(uVar9,0,0);
    }
    plVar13[4] = lVar23;
    if (plVar11 == (long *)0x0) {
      func_0x010eec64(0);
    }
    lVar23 = (**(code **)(*plVar11 + 0x330))(plVar11,plVar13,*(undefined8 *)(*plVar11 + 0x338));
    plVar11 = (long *)func_0x01101ad4(_class_object___TypeInfo,1);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    lVar10 = *(long *)(lVar10 + 0x18);
    if (plVar11 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((lVar10 != 0) &&
       (lVar12 = func_0x010fdfcc(lVar10,*(undefined8 *)(*plVar11 + 0x40)), lVar12 == 0)) {
      uVar9 = func_0x010efe90();
      func_0x010eebec(uVar9,0,0);
    }
    if ((int)plVar11[3] == 0) {
      uVar9 = func_0x010ef850();
      func_0x010eebec(uVar9,0,0);
    }
    plVar11[4] = lVar10;
    if (lVar23 == 0) {
      func_0x010eec64(0);
    }
    param_4 = 0;
    uVar9 = System_Reflection_MethodBase__Invoke(lVar23,0,plVar11);
    plVar11 = (long *)func_0x010fdda8(_class_Chat_Notification_TypeInfo);
    Chat_Notification___ctor(plVar11,0,uVar9);
    plVar13 = param_1;
    if ((bRam00000000049fcf33 & 1) == 0) {
      plVar13 = (long *)func_0x010c65d4(0x25ed);
      bRam00000000049fcf33 = 1;
    }
    if (plVar11 == (long *)0x0) {
      return plVar13;
    }
    if (param_1[0x1c] != 0) {
      param_4 = _mm_Method_System_EventHandler<Notification>_Invoke__;
      System_EventHandler<object>__Invoke(param_1[0x1c],param_1,plVar11);
    }
    lVar10 = param_1[0xf];
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_List<tk2dUILayoutItem>__Add
              (lVar10,plVar11,_mm_Method_System_Collections_Generic_List<Notification>_Add__);
    goto Chat_Chatting__AddChatMsg;
  case 6:
    uVar9 = *(undefined8 *)(lVar10 + 0x18);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar10 = LitJson_JsonMapper__ToObject<UserTechLocalInfos>
                       (0,uVar9,_mm_Method_LitJson_JsonMapper_ToObject<TypeValuePair>__);
    uVar9 = _class_Chat_Chatting_var;
    if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_System_Type_TypeInfo);
    }
    lVar23 = System_Type__GetTypeFromHandle(0,uVar9,0);
    if (lVar23 == 0) {
      func_0x010eec64(0);
    }
    plVar11 = (long *)System_Type__GetMethod(lVar23,_str_ToObjectForGenericReflection,0);
    plVar13 = (long *)func_0x01101ad4(_class_System_Type___TypeInfo,1);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    uVar14 = *(undefined8 *)(lVar10 + 0x10);
    uVar9 = func_0x010e75f0(uVar14,&UNK_039933a8);
    lVar23 = System_Type__GetType(0,uVar9,0);
    if (lVar23 == 0) {
      lVar23 = System_Type__GetType(0,uVar14,0);
    }
    if (plVar13 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((lVar23 != 0) &&
       (lVar12 = func_0x010fdfcc(lVar23,*(undefined8 *)(*plVar13 + 0x40)), lVar12 == 0)) {
      uVar9 = func_0x010efe90();
      func_0x010eebec(uVar9,0,0);
    }
    if ((int)plVar13[3] == 0) {
      uVar9 = func_0x010ef850();
      func_0x010eebec(uVar9,0,0);
    }
    plVar13[4] = lVar23;
    if (plVar11 == (long *)0x0) {
      func_0x010eec64(0);
    }
    lVar23 = (**(code **)(*plVar11 + 0x330))(plVar11,plVar13,*(undefined8 *)(*plVar11 + 0x338));
    plVar11 = (long *)func_0x01101ad4(_class_object___TypeInfo,1);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    lVar10 = *(long *)(lVar10 + 0x18);
    if (plVar11 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((lVar10 != 0) &&
       (lVar12 = func_0x010fdfcc(lVar10,*(undefined8 *)(*plVar11 + 0x40)), lVar12 == 0)) {
      uVar9 = func_0x010efe90();
      func_0x010eebec(uVar9,0,0);
    }
    if ((int)plVar11[3] == 0) {
      uVar9 = func_0x010ef850();
      func_0x010eebec(uVar9,0,0);
    }
    plVar11[4] = lVar10;
    if (lVar23 == 0) {
      func_0x010eec64(0);
    }
    uVar9 = System_Reflection_MethodBase__Invoke(lVar23,0,plVar11,0);
    lVar10 = func_0x010fdda8(_class_Chat_CustomMessage_TypeInfo);
    Chat_CustomMessage___ctor(lVar10,uVar9);
    plVar11 = param_1;
    if ((bRam00000000049fcf34 & 1) == 0) {
      plVar11 = (long *)func_0x010c65d4(0x25e5);
      bRam00000000049fcf34 = 1;
    }
    if (lVar10 != 0) {
      lVar23 = param_1[0x1d];
      plVar11 = (long *)0x0;
      if (lVar23 != 0) {
        if (*(long *)(lVar23 + 0x58) != 0) {
          System_EventHandler<object>__Invoke
                    (*(long *)(lVar23 + 0x58),param_1,lVar10,
                     _mm_Method_System_EventHandler<CustomMessage>_Invoke__);
        }
        UNRECOVERED_JUMPTABLE = *(code **)(lVar23 + 0x10);
        plVar11 = *(long **)(lVar23 + 0x20);
        puVar16 = *(undefined8 **)(lVar23 + 0x28);
        func_0x010da364(puVar16);
        uVar8 = func_0x01101d6c(puVar16);
        if ((uVar8 & 1) == 0) {
          if (*(char *)((long)puVar16 + 0x4e) != '\x02') {
                    /* WARNING: Could not recover jumptable at 0x02d619e0. Too many branches */
                    /* WARNING: Treating indirect jump as call */
            plVar11 = (long *)(*UNRECOVERED_JUMPTABLE)(0,plVar11,param_1,lVar10,puVar16);
            return plVar11;
          }
          plVar11 = (long *)0x0;
        }
        else {
          if (*(char *)((long)puVar16 + 0x4e) != '\x02') {
            if ((*(short *)((long)puVar16 + 0x4c) != -1) &&
               (((plVar11 == (long *)0x0 || ((*(byte *)(*plVar11 + 0x10d) & 1) == 0)) &&
                (*(long *)(lVar23 + 0x18) != 0)))) {
              uVar15 = func_0x01101d58(puVar16);
              func_0x01101dd4(puVar16);
              uVar22 = func_0x010ba65c();
              if ((uVar15 & 1) == 0) {
                uVar5 = *(ushort *)((long)puVar16 + 0x4c);
                if ((uVar22 & 1) == 0) {
                  lVar23 = *param_1 + (ulong)uVar5 * 0x10;
                  UNRECOVERED_JUMPTABLE = *(code **)(lVar23 + 0x130);
                  puVar16 = *(undefined8 **)(lVar23 + 0x138);
                }
                else {
                  lVar23 = func_0x01101dd4(puVar16);
                  lVar12 = *param_1;
                  if ((ulong)*(ushort *)(lVar12 + 0x122) != 0) {
                    uVar15 = 0;
                    piVar20 = (int *)(*(long *)(lVar12 + 0xb0) + 8);
                    do {
                      if (*(long *)(piVar20 + -2) == lVar23) {
                        puVar16 = (undefined8 *)
                                  (lVar12 + (ulong)(*piVar20 + (uint)uVar5) * 0x10 + 0x130);
                        goto code_r0x02d61bd8;
                      }
                      uVar15 = uVar15 + 1;
                      piVar20 = piVar20 + 4;
                    } while (uVar15 < *(ushort *)(lVar12 + 0x122));
                  }
                  puVar16 = (undefined8 *)func_0x010bc584(param_1,lVar23,(ulong)uVar5);
code_r0x02d61bd8:
                  UNRECOVERED_JUMPTABLE = (code *)*puVar16;
                  puVar16 = (undefined8 *)puVar16[1];
                }
              }
              else {
                if ((uVar22 & 1) == 0) {
                  uVar9 = *(undefined8 *)
                           (*param_1 + (ulong)*(ushort *)((long)puVar16 + 0x4c) * 0x10 + 0x138);
                }
                else {
                  lVar23 = *param_1;
                  if ((ulong)*(ushort *)(lVar23 + 0x122) != 0) {
                    uVar15 = 0;
                    piVar20 = (int *)(*(long *)(lVar23 + 0xb0) + 8);
                    do {
                      if (*(long *)(piVar20 + -2) == puVar16[3]) {
                        lVar23 = lVar23 + (ulong)(*piVar20 + (uint)*(ushort *)((long)puVar16 + 0x4c)
                                                 ) * 0x10 + 0x130;
                        goto code_r0x02d61b80;
                      }
                      uVar15 = uVar15 + 1;
                      piVar20 = piVar20 + 4;
                    } while (uVar15 < *(ushort *)(lVar23 + 0x122));
                  }
                  lVar23 = func_0x010bc584(param_1);
code_r0x02d61b80:
                  uVar9 = *(undefined8 *)(lVar23 + 8);
                }
                puVar16 = (undefined8 *)func_0x010da294(uVar9,puVar16);
                UNRECOVERED_JUMPTABLE = (code *)*puVar16;
              }
            }
                    /* WARNING: Could not recover jumptable at 0x02d61a34. Too many branches */
                    /* WARNING: Treating indirect jump as call */
            plVar11 = (long *)(*UNRECOVERED_JUMPTABLE)(param_1,lVar10,puVar16);
            return plVar11;
          }
          if (((*(short *)((long)puVar16 + 0x4c) != -1) &&
              ((plVar11 == (long *)0x0 || ((*(byte *)(*plVar11 + 0x10d) & 1) == 0)))) &&
             (*(long *)(lVar23 + 0x18) != 0)) {
            uVar15 = func_0x01101d58(puVar16);
            func_0x01101dd4(puVar16);
            uVar22 = func_0x010ba65c();
            if ((uVar15 & 1) == 0) {
              uVar5 = *(ushort *)((long)puVar16 + 0x4c);
              if ((uVar22 & 1) == 0) {
                lVar23 = *plVar11 + (ulong)uVar5 * 0x10;
                UNRECOVERED_JUMPTABLE = *(code **)(lVar23 + 0x130);
                puVar16 = *(undefined8 **)(lVar23 + 0x138);
              }
              else {
                lVar23 = func_0x01101dd4(puVar16);
                lVar12 = *plVar11;
                if ((ulong)*(ushort *)(lVar12 + 0x122) != 0) {
                  uVar15 = 0;
                  piVar20 = (int *)(*(long *)(lVar12 + 0xb0) + 8);
                  do {
                    if (*(long *)(piVar20 + -2) == lVar23) {
                      puVar16 = (undefined8 *)
                                (lVar12 + (ulong)(*piVar20 + (uint)uVar5) * 0x10 + 0x130);
                      goto code_r0x02d61bb4;
                    }
                    uVar15 = uVar15 + 1;
                    piVar20 = piVar20 + 4;
                  } while (uVar15 < *(ushort *)(lVar12 + 0x122));
                }
                puVar16 = (undefined8 *)func_0x010bc584(plVar11,lVar23,(ulong)uVar5);
code_r0x02d61bb4:
                UNRECOVERED_JUMPTABLE = (code *)*puVar16;
                puVar16 = (undefined8 *)puVar16[1];
              }
            }
            else {
              if ((uVar22 & 1) == 0) {
                uVar9 = *(undefined8 *)
                         (*plVar11 + (ulong)*(ushort *)((long)puVar16 + 0x4c) * 0x10 + 0x138);
              }
              else {
                lVar23 = *plVar11;
                if ((ulong)*(ushort *)(lVar23 + 0x122) != 0) {
                  uVar15 = 0;
                  piVar20 = (int *)(*(long *)(lVar23 + 0xb0) + 8);
                  do {
                    if (*(long *)(piVar20 + -2) == puVar16[3]) {
                      lVar23 = lVar23 + (ulong)(*piVar20 + (uint)*(ushort *)((long)puVar16 + 0x4c))
                                        * 0x10 + 0x130;
                      goto code_r0x02d61b48;
                    }
                    uVar15 = uVar15 + 1;
                    piVar20 = piVar20 + 4;
                  } while (uVar15 < *(ushort *)(lVar23 + 0x122));
                }
                lVar23 = func_0x010bc584(plVar11);
code_r0x02d61b48:
                uVar9 = *(undefined8 *)(lVar23 + 8);
              }
              puVar16 = (undefined8 *)func_0x010da294(uVar9,puVar16);
              UNRECOVERED_JUMPTABLE = (code *)*puVar16;
            }
          }
        }
                    /* WARNING: Could not recover jumptable at 0x02d61a0c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
        plVar11 = (long *)(*UNRECOVERED_JUMPTABLE)(plVar11,param_1,lVar10,puVar16);
        return plVar11;
      }
    }
    return plVar11;
  }
code_r0x01d2dcc4:
  *(undefined8 *)((long)register0x00000008 + -0x50) = unaff_x26;
  *(undefined8 *)((long)register0x00000008 + -0x48) = unaff_x25;
  *(undefined8 *)((long)register0x00000008 + -0x40) = unaff_x24;
  *(undefined8 *)((long)register0x00000008 + -0x38) = unaff_x23;
  *(undefined **)((long)register0x00000008 + -0x30) = unaff_x22;
  *(long *)((long)register0x00000008 + -0x28) = unaff_x21;
  *(undefined8 *)((long)register0x00000008 + -0x20) = unaff_x20;
  *(undefined8 *)((long)register0x00000008 + -0x18) = unaff_x19;
  *(undefined1 **)((long)register0x00000008 + -0x10) = unaff_x29;
  *(code **)((long)register0x00000008 + -8) = unaff_x30;
  plVar13 = param_1;
  if ((bRam00000000049fcf30 & 1) == 0) {
    plVar13 = (long *)func_0x010c65d4(0x25e1);
    bRam00000000049fcf30 = 1;
  }
  if (plVar11 != (long *)0x0) {
    lVar10 = plVar11[3];
    if (((*(byte *)(_class_System_Convert_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_System_Convert_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar9 = System_Convert__ToUInt64(0,lVar10,0);
    plVar13 = (long *)Chat_Chatting__IsBlockUser(param_1,uVar9);
    if (((ulong)plVar13 & 1) == 0) {
      if (*(int *)((long)plVar11 + 0x34) == 1) {
        uVar9 = (**(code **)(*plVar11 + 0x170))(plVar11,*(undefined8 *)(*plVar11 + 0x178));
        lVar23 = param_1[9];
        lVar10 = lVar23;
        if (lVar23 == 0) {
          func_0x010eec64(0);
          lVar10 = param_1[9];
          if (lVar10 == 0) {
            plVar13 = (long *)func_0x010eec64(0);
            *(long **)((long)register0x00000008 + -0x70) = param_1;
            *(long **)((long)register0x00000008 + -0x68) = plVar11;
            *(undefined1 **)((long)register0x00000008 + -0x60) =
                 (undefined1 *)((long)register0x00000008 + -0x10);
            *(code **)((long)register0x00000008 + -0x58) = Chat_ChattingMessage__DeserializeSubType;
            if ((bRam00000000049fcf6f & 1) == 0) {
              func_0x010c65d4(0x25a1);
              bRam00000000049fcf6f = 1;
            }
            uVar9 = (**(code **)(*plVar13 + 0x170))(plVar13,*(undefined8 *)(*plVar13 + 0x178));
            if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            lVar10 = LitJson_JsonMapper__ToObject(0,uVar9,0);
            plVar11 = (long *)0x0;
            if (lVar10 != 0) {
              if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c();
              }
              plVar11 = (long *)Util__GetData(0,lVar10,_str_type,4,0);
              *(int *)((long)plVar13 + 0x3c) = (int)plVar11;
            }
            return plVar11;
          }
        }
        uVar25 = *(undefined8 *)(lVar10 + 0x18);
        uVar24 = *(undefined8 *)(lVar23 + 0x10);
        lVar10 = plVar11[6];
        lVar23 = plVar11[7];
        uVar14 = func_0x010fdda8(_class_Chat_WhisperResponse_TypeInfo);
        uVar9 = Chat_WhisperResponse___ctor(uVar14,uVar9,uVar24,uVar25,(int)lVar10,0,(int)lVar23);
        uVar9 = Chat_Chatting__GetSubRoomId(uVar9,plVar11[4]);
        Chat_Chatting__EmitBroadcast(param_1,4,uVar14,uVar9);
      }
      else {
        iVar2 = *(int *)((long)param_1 + 0x84);
        *(int *)((long)param_1 + 0x84) = iVar2 + 1;
        *(int *)(plVar11 + 6) = iVar2;
      }
      plVar13 = (long *)0x0;
      if (param_1[0x18] != 0) {
        param_4 = _mm_Method_System_EventHandler<ChattingMessage>_Invoke__;
        System_EventHandler<object>__Invoke(param_1[0x18],param_1,plVar11);
        unaff_x29 = *(undefined1 **)((long)register0x00000008 + -0x10);
        unaff_x30 = *(code **)((long)register0x00000008 + -8);
        unaff_x20 = *(undefined8 *)((long)register0x00000008 + -0x20);
        unaff_x19 = *(undefined8 *)((long)register0x00000008 + -0x18);
        unaff_x22 = *(undefined **)((long)register0x00000008 + -0x30);
        unaff_x21 = *(long *)((long)register0x00000008 + -0x28);
Chat_Chatting__AddChatMsg:
        *(undefined **)((long)register0x00000008 + -0x30) = unaff_x22;
        *(long *)((long)register0x00000008 + -0x28) = unaff_x21;
        *(undefined8 *)((long)register0x00000008 + -0x20) = unaff_x20;
        *(undefined8 *)((long)register0x00000008 + -0x18) = unaff_x19;
        *(undefined1 **)((long)register0x00000008 + -0x10) = unaff_x29;
        *(code **)((long)register0x00000008 + -8) = unaff_x30;
        if ((bRam00000000049fcf42 & 1) == 0) {
          func_0x010c65d4(0x25ab);
          bRam00000000049fcf42 = 1;
        }
        uVar9 = Chat_Chatting__GetDestRoomId(param_1,plVar11);
        if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_string_TypeInfo);
        }
        plVar13 = (long *)System_String__IsNullOrEmpty(0,uVar9,0);
        if (((ulong)plVar13 & 1) != 0) {
          return plVar13;
        }
        Chat_Chatting__AddChat(param_1,_str_all,plVar11);
        Chat_Chatting__TrimChat(param_1,_str_all);
        Chat_Chatting__AddChat(param_1,uVar9,plVar11);
        *(undefined8 *)((long)register0x00000008 + -0x30) =
             *(undefined8 *)((long)register0x00000008 + -0x30);
        *(undefined8 *)((long)register0x00000008 + -0x28) =
             *(undefined8 *)((long)register0x00000008 + -0x28);
        *(undefined8 *)((long)register0x00000008 + -0x20) =
             *(undefined8 *)((long)register0x00000008 + -0x20);
        *(undefined8 *)((long)register0x00000008 + -0x18) =
             *(undefined8 *)((long)register0x00000008 + -0x18);
        *(undefined8 *)((long)register0x00000008 + -0x10) =
             *(undefined8 *)((long)register0x00000008 + -0x10);
        *(undefined8 *)((long)register0x00000008 + -8) =
             *(undefined8 *)((long)register0x00000008 + -8);
        if ((bRam00000000049fcf44 & 1) == 0) {
          func_0x010c65d4(0x25f7);
          bRam00000000049fcf44 = 1;
        }
        lVar10 = param_1[0xe];
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        plVar11 = (long *)System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>__ContainsKey
                                    (lVar10,uVar9,
                                     _mm_Method_System_Collections_Generic_Dictionary<string,_List<EventArgs>>_ContainsKey__
                                    );
        if (((ulong)plVar11 & 1) != 0) {
          lVar10 = param_1[0xe];
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          lVar10 = System_Collections_Generic_Dictionary<object,_object>__get_Item
                             (lVar10,uVar9,
                              _mm_Method_System_Collections_Generic_Dictionary<string,_List<EventArgs>>_get_Item__
                             );
          plVar11 = (long *)0x0;
          if ((lVar10 != 0) &&
             (plVar11 = (long *)System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                                          (lVar10,
                                           _mm_Method_System_Collections_Generic_List<EventArgs>_get_Count__
                                          ),
             lVar23 = _mm_Method_System_Collections_Generic_List<EventArgs>_RemoveAt__,
             199 < (int)plVar11)) {
            *(undefined8 *)((long)register0x00000008 + -0x30) =
                 *(undefined8 *)((long)register0x00000008 + -0x30);
            *(undefined8 *)((long)register0x00000008 + -0x28) =
                 *(undefined8 *)((long)register0x00000008 + -0x28);
            *(undefined8 *)((long)register0x00000008 + -0x20) =
                 *(undefined8 *)((long)register0x00000008 + -0x20);
            *(undefined8 *)((long)register0x00000008 + -0x18) =
                 *(undefined8 *)((long)register0x00000008 + -0x18);
            *(undefined8 *)((long)register0x00000008 + -0x10) =
                 *(undefined8 *)((long)register0x00000008 + -0x10);
            *(undefined8 *)((long)register0x00000008 + -8) =
                 *(undefined8 *)((long)register0x00000008 + -8);
            if ((bRam0000000004a09250 & 1) == 0) {
              func_0x010c65d4(0x7e85);
              bRam0000000004a09250 = 1;
            }
            if (*(int *)(lVar10 + 0x18) != 0) {
              (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar23 + 0x18) + 0xc0) + 0x120))
                        (lVar10,0,0xffffffff);
              plVar11 = (long *)System_Array__Clear(0,*(undefined8 *)(lVar10 + 0x10),
                                                    *(undefined4 *)(lVar10 + 0x18),1,0);
              *(int *)(lVar10 + 0x1c) = *(int *)(lVar10 + 0x1c) + 1;
              return plVar11;
            }
            uVar9 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
            System_ArgumentOutOfRangeException___ctor(uVar9,_str_index,0);
            uVar15 = _mm_Method_System_Collections_Generic_List<object>_RemoveAt__;
            func_0x010eebec(uVar9,0);
            auVar30 = func_0x0112148c();
            lVar10 = auVar30._0_8_;
            *(undefined8 *)((long)register0x00000008 + -0x60) = 0x4a09000;
            *(long *)((long)register0x00000008 + -0x58) = lVar23;
            *(undefined8 *)((long)register0x00000008 + -0x50) = 0;
            *(undefined8 *)((long)register0x00000008 + -0x48) = uVar9;
            *(undefined1 **)((long)register0x00000008 + -0x40) =
                 (undefined1 *)((long)register0x00000008 + -0x10);
            *(code **)((long)register0x00000008 + -0x38) =
                 System_Collections_Generic_List<UserItem>__RemoveRange;
            uVar22 = auVar30._8_8_ & 0xffffffff;
            if (lVar10 == 0) {
              func_0x010eec64(0);
            }
            plVar11 = (long *)(*(code *)**(undefined8 **)
                                          (*(long *)(*(long *)(param_4 + 0x18) + 0xc0) + 0x118))
                                        (lVar10,uVar22,uVar15 & 0xffffffff);
            if (0 < (int)uVar15) {
              if (lVar10 == 0) {
                func_0x010eec64(0);
              }
              (*(code *)**(undefined8 **)(*(long *)(*(long *)(param_4 + 0x18) + 0xc0) + 0x120))
                        (lVar10,uVar22,-(int)uVar15);
              plVar11 = (long *)System_Array__Clear(0,*(undefined8 *)(lVar10 + 0x10),
                                                    *(undefined4 *)(lVar10 + 0x18),
                                                    uVar15 & 0xffffffff,0);
              *(int *)(lVar10 + 0x1c) = *(int *)(lVar10 + 0x1c) + 1;
            }
            return plVar11;
          }
        }
        return plVar11;
      }
    }
  }
  return plVar13;
}


