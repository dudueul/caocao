// name: Multiplay.MultiplayManager$$OnInviteFailed
// entry: 013a5238

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnInviteFailed(long param_1,long param_2)

{
  long lVar1;
  ushort uVar2;
  uint uVar3;
  int iVar4;
  long lVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  ulong uVar8;
  long *plVar9;
  undefined8 uVar10;
  long lVar11;
  ulong uVar12;
  long *plVar13;
  code *UNRECOVERED_JUMPTABLE;
  undefined8 uVar14;
  undefined8 *puVar15;
  int *piVar16;
  long lVar17;
  undefined1 auVar18 [16];
  
  if ((bRam00000000049f7af3 & 1) == 0) {
    func_0x010c65d4(0x8a56);
    bRam00000000049f7af3 = 1;
  }
  if (*(char *)(param_1 + 0x141) == '\0') {
    return;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar6 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Util_TypeInfo);
  }
  uVar3 = Util__GetData<EnumData_MultiplayInviteUnableReason>
                    (0,uVar6,_str_reason,1,
                     _mm_Method_Util_GetData<EnumData_MultiplayInviteUnableReason>__);
  iVar4 = uVar3 + 6;
  if (7 < uVar3) {
    iVar4 = 0;
  }
  switch(iVar4) {
  case 0:
    uVar6 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
    System_ArgumentOutOfRangeException___ctor(uVar6,0);
    func_0x010eebec(uVar6,0,_mm_Method_Multiplay_MultiplayManager_OnInviteFailed__);
    auVar18 = func_0x0112148c();
    lVar5 = auVar18._8_8_;
    lVar17 = auVar18._0_8_;
    if ((bRam00000000049f7af4 & 1) == 0) {
      func_0x010c65d4(0x8a57);
      bRam00000000049f7af4 = 1;
    }
    if (*(char *)(lVar17 + 0x141) == '\0') {
      return;
    }
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    uVar6 = SocketIO_SocketIOEvent__get_data(lVar5,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Util_TypeInfo);
    }
    uVar6 = Util__GetData(0,uVar6,_str_userId,0,0);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = SocketIO_SocketIOEvent__get_data(lVar5,0);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar7 = Util__GetData(0,uVar7,_str_roomId,**(undefined8 **)(_class_string_TypeInfo + 0xb8),0);
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Multiplay_MultiplayManager_TypeInfo);
    }
    uVar3 = Multiplay_MultiplayManager__get_mode();
    if ((uVar3 < 4) && (iVar4 = *(int *)(&UNK_03991958 + (long)(int)uVar3 * 4), iVar4 != 0)) {
      if (iVar4 != 0xb) {
        if (iVar4 != 10) {
          return;
        }
        if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar8 = Multiplay_MultiplayManager__get_isFriendly();
        if ((uVar8 & 1) != 0) {
          uVar14 = 4;
          goto code_r0x013a5624;
        }
        goto code_r0x013a559c;
      }
    }
    else {
code_r0x013a559c:
      lVar11 = *(long *)(lVar17 + 0x178);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      uVar3 = Multiplay_MultiplayManager_DeniedInvitingInfo__CheckDeniedInvitingTime(lVar11,uVar6,0)
      ;
      if ((uVar3 & 1) == 0) {
        uVar14 = 5;
        goto code_r0x013a5624;
      }
      if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar3 = LoadedSceneCheckUtil__get_isCompetitionScene(0,0);
      if ((uVar3 & 1) == 0) {
        uVar14 = 2;
        goto code_r0x013a5624;
      }
      plVar9 = *(long **)(lVar17 + 0x40);
      if ((plVar9 == (long *)0x0) ||
         (iVar4 = (**(code **)(*plVar9 + 0x170))(plVar9,*(undefined8 *)(*plVar9 + 0x178)), iVar4 < 0
         )) {
        *(undefined8 *)(lVar17 + 0x150) = uVar6;
        uVar14 = func_0x010fdda8(_class_<>__AnonType43<ulong>_TypeInfo);
        <>__AnonType43<ulong>___ctor(uVar14,uVar6,_mm_Method_<>__AnonType43<ulong>__ctor__);
        if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar6 = LitJson_JsonMapper__ToJson(0,uVar14,0);
        Multiplay_MultiplayManager__EmitSocketMessage
                  (lVar17,_str_responseInvitedFriendlyMatch,uVar6);
        if (*(long *)(lVar17 + 0x120) == 0) {
          return;
        }
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        uVar6 = SocketIO_SocketIOEvent__get_data(lVar5,0);
        if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_string_TypeInfo);
        }
        uVar14 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
        if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        plVar9 = (long *)Util__GetData(0,uVar6,_str_nationName,uVar14,0);
        lVar17 = *(long *)(lVar17 + 0x120);
        if (lVar17 == 0) {
          func_0x010eec64(0);
        }
        if (*(long *)(lVar17 + 0x58) != 0) {
          System_Action<tk2dSpriteAnimator,_tk2dSpriteAnimationClip>__Invoke
                    (*(long *)(lVar17 + 0x58),plVar9,uVar7,
                     _mm_Method_System_Action<string,_string>_Invoke__);
        }
        UNRECOVERED_JUMPTABLE = *(code **)(lVar17 + 0x10);
        plVar13 = *(long **)(lVar17 + 0x20);
        puVar15 = *(undefined8 **)(lVar17 + 0x28);
        func_0x010da364(puVar15);
        uVar3 = func_0x01101d6c(puVar15);
        if ((uVar3 & 1) == 0) {
          if (*(char *)((long)puVar15 + 0x4e) != '\x02') {
                    /* WARNING: Could not recover jumptable at 0x029b1cb4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
            (*UNRECOVERED_JUMPTABLE)(0,plVar13,plVar9,uVar7,puVar15);
            return;
          }
          plVar13 = (long *)0x0;
        }
        else {
          if (*(char *)((long)puVar15 + 0x4e) != '\x02') {
            if ((*(short *)((long)puVar15 + 0x4c) != -1) &&
               (((plVar13 == (long *)0x0 || ((*(byte *)(*plVar13 + 0x10d) & 1) == 0)) &&
                (*(long *)(lVar17 + 0x18) != 0)))) {
              uVar8 = func_0x01101d58(puVar15);
              func_0x01101dd4(puVar15);
              uVar12 = func_0x010ba65c();
              if ((uVar8 & 1) == 0) {
                uVar2 = *(ushort *)((long)puVar15 + 0x4c);
                if ((uVar12 & 1) == 0) {
                  lVar17 = *plVar9 + (ulong)uVar2 * 0x10;
                  UNRECOVERED_JUMPTABLE = *(code **)(lVar17 + 0x130);
                  puVar15 = *(undefined8 **)(lVar17 + 0x138);
                }
                else {
                  lVar17 = func_0x01101dd4(puVar15);
                  lVar5 = *plVar9;
                  if ((ulong)*(ushort *)(lVar5 + 0x122) != 0) {
                    uVar8 = 0;
                    piVar16 = (int *)(*(long *)(lVar5 + 0xb0) + 8);
                    do {
                      if (*(long *)(piVar16 + -2) == lVar17) {
                        puVar15 = (undefined8 *)
                                  (lVar5 + (ulong)(*piVar16 + (uint)uVar2) * 0x10 + 0x130);
                        goto code_r0x029b1eac;
                      }
                      uVar8 = uVar8 + 1;
                      piVar16 = piVar16 + 4;
                    } while (uVar8 < *(ushort *)(lVar5 + 0x122));
                  }
                  puVar15 = (undefined8 *)func_0x010bc584(plVar9,lVar17,(ulong)uVar2);
code_r0x029b1eac:
                  UNRECOVERED_JUMPTABLE = (code *)*puVar15;
                  puVar15 = (undefined8 *)puVar15[1];
                }
              }
              else {
                if ((uVar12 & 1) == 0) {
                  uVar6 = *(undefined8 *)
                           (*plVar9 + (ulong)*(ushort *)((long)puVar15 + 0x4c) * 0x10 + 0x138);
                }
                else {
                  lVar17 = *plVar9;
                  if ((ulong)*(ushort *)(lVar17 + 0x122) != 0) {
                    uVar8 = 0;
                    piVar16 = (int *)(*(long *)(lVar17 + 0xb0) + 8);
                    do {
                      if (*(long *)(piVar16 + -2) == puVar15[3]) {
                        lVar17 = lVar17 + (ulong)(*piVar16 + (uint)*(ushort *)((long)puVar15 + 0x4c)
                                                 ) * 0x10 + 0x130;
                        goto code_r0x029b1e54;
                      }
                      uVar8 = uVar8 + 1;
                      piVar16 = piVar16 + 4;
                    } while (uVar8 < *(ushort *)(lVar17 + 0x122));
                  }
                  lVar17 = func_0x010bc584(plVar9);
code_r0x029b1e54:
                  uVar6 = *(undefined8 *)(lVar17 + 8);
                }
                puVar15 = (undefined8 *)func_0x010da294(uVar6,puVar15);
                UNRECOVERED_JUMPTABLE = (code *)*puVar15;
              }
            }
                    /* WARNING: Could not recover jumptable at 0x029b1d08. Too many branches */
                    /* WARNING: Treating indirect jump as call */
            (*UNRECOVERED_JUMPTABLE)(plVar9,uVar7,puVar15);
            return;
          }
          if (((*(short *)((long)puVar15 + 0x4c) != -1) &&
              ((plVar13 == (long *)0x0 || ((*(byte *)(*plVar13 + 0x10d) & 1) == 0)))) &&
             (*(long *)(lVar17 + 0x18) != 0)) {
            uVar8 = func_0x01101d58(puVar15);
            func_0x01101dd4(puVar15);
            uVar12 = func_0x010ba65c();
            if ((uVar8 & 1) == 0) {
              uVar2 = *(ushort *)((long)puVar15 + 0x4c);
              if ((uVar12 & 1) == 0) {
                lVar17 = *plVar13 + (ulong)uVar2 * 0x10;
                UNRECOVERED_JUMPTABLE = *(code **)(lVar17 + 0x130);
                puVar15 = *(undefined8 **)(lVar17 + 0x138);
              }
              else {
                lVar17 = func_0x01101dd4(puVar15);
                lVar5 = *plVar13;
                if ((ulong)*(ushort *)(lVar5 + 0x122) != 0) {
                  uVar8 = 0;
                  piVar16 = (int *)(*(long *)(lVar5 + 0xb0) + 8);
                  do {
                    if (*(long *)(piVar16 + -2) == lVar17) {
                      puVar15 = (undefined8 *)
                                (lVar5 + (ulong)(*piVar16 + (uint)uVar2) * 0x10 + 0x130);
                      goto code_r0x029b1e88;
                    }
                    uVar8 = uVar8 + 1;
                    piVar16 = piVar16 + 4;
                  } while (uVar8 < *(ushort *)(lVar5 + 0x122));
                }
                puVar15 = (undefined8 *)func_0x010bc584(plVar13,lVar17,(ulong)uVar2);
code_r0x029b1e88:
                UNRECOVERED_JUMPTABLE = (code *)*puVar15;
                puVar15 = (undefined8 *)puVar15[1];
              }
            }
            else {
              if ((uVar12 & 1) == 0) {
                uVar6 = *(undefined8 *)
                         (*plVar13 + (ulong)*(ushort *)((long)puVar15 + 0x4c) * 0x10 + 0x138);
              }
              else {
                lVar17 = *plVar13;
                if ((ulong)*(ushort *)(lVar17 + 0x122) != 0) {
                  uVar8 = 0;
                  piVar16 = (int *)(*(long *)(lVar17 + 0xb0) + 8);
                  do {
                    if (*(long *)(piVar16 + -2) == puVar15[3]) {
                      lVar17 = lVar17 + (ulong)(*piVar16 + (uint)*(ushort *)((long)puVar15 + 0x4c))
                                        * 0x10 + 0x130;
                      goto code_r0x029b1e1c;
                    }
                    uVar8 = uVar8 + 1;
                    piVar16 = piVar16 + 4;
                  } while (uVar8 < *(ushort *)(lVar17 + 0x122));
                }
                lVar17 = func_0x010bc584(plVar13);
code_r0x029b1e1c:
                uVar6 = *(undefined8 *)(lVar17 + 8);
              }
              puVar15 = (undefined8 *)func_0x010da294(uVar6,puVar15);
              UNRECOVERED_JUMPTABLE = (code *)*puVar15;
            }
          }
        }
                    /* WARNING: Could not recover jumptable at 0x029b1ce0. Too many branches */
                    /* WARNING: Treating indirect jump as call */
        (*UNRECOVERED_JUMPTABLE)(plVar13,plVar9,uVar7,puVar15);
        return;
      }
    }
    uVar14 = 1;
code_r0x013a5624:
    if ((bRam00000000049f7af7 & 1) == 0) {
      func_0x010c65d4(0x8a24,uVar6,uVar14);
      bRam00000000049f7af7 = 1;
    }
    if (*(char *)(lVar17 + 0x141) == '\0') {
      return;
    }
    uVar10 = func_0x010fdda8(
                            _class_<>__AnonType44<ulong,_EnumData_MultiplayInviteUnableReason,_string>_TypeInfo
                            );
    <>__AnonType44<ulong,_EnumData_MultiplayInviteUnableReason,_string>___ctor
              (uVar10,uVar6,uVar14,uVar7,
               _mm_Method_<>__AnonType44<ulong,_EnumData_MultiplayInviteUnableReason,_string>__ctor__
              );
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar7 = LitJson_JsonMapper__ToJson(0,uVar10,0);
    uVar6 = _str_denyInvitedFriendlyMatch;
    uVar8 = MyUserData__get_hasInstance(0,0);
    if ((uVar8 & 1) != 0) {
      lVar5 = MyUserData__get_instance(0,0);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      if (*(char *)(lVar5 + 0x268) != '\0') {
        lVar17 = *(long *)(lVar17 + 0x30);
        if (lVar17 == 0) {
          func_0x010eec64(0);
        }
        if ((bRam0000000004a00249 & 1) == 0) {
          func_0x010c65d4(0xc5d1,uVar6,uVar7,0);
          bRam0000000004a00249 = 1;
        }
        if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar6 = System_String__Format(0,_str____0____1__,uVar6,uVar7,0);
        if ((bRam0000000004a0024d & 1) == 0) {
          func_0x010c65d4(0xc5ce);
          bRam0000000004a0024d = 1;
        }
        if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar7 = LitJson_JsonMapper__ToObject(0,uVar6,0);
        lVar11 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
        uVar6 = _str__;
        System_Object___ctor(lVar11,0);
        *(undefined4 *)(lVar11 + 0x14) = 2;
        *(undefined4 *)(lVar11 + 0x18) = 0;
        *(undefined8 *)(lVar11 + 0x30) = uVar7;
        *(undefined8 *)(lVar11 + 0x20) = uVar6;
        *(undefined4 *)(lVar11 + 0x28) = 0xffffffff;
        *(undefined4 *)(lVar11 + 0x10) = 4;
        lVar5 = lVar17;
        if ((bRam0000000004a0024f & 1) == 0) {
          lVar5 = func_0x010c65d4(0xc5cf);
          bRam0000000004a0024f = 1;
        }
        lVar1 = *(long *)(lVar17 + 0x48);
        if (*(long *)(lVar17 + 0x50) == 0) {
          lVar5 = func_0x010eec64(0);
        }
        uVar6 = SocketIO_Encoder__Encode(lVar5,lVar11);
        if (lVar1 == 0) {
          func_0x010eec64(0);
        }
        WebSocketSharp_WebSocket__Send(lVar1,uVar6,0);
        return;
      }
    }
    uVar8 = Multiplay_MultiplayManager__get_isConnected(lVar17);
    if ((uVar8 & 1) == 0) {
      return;
    }
    if ((bRam00000000049f7acc & 1) == 0) {
      func_0x010c65d4(0x8a26);
      bRam00000000049f7acc = 1;
    }
    *(undefined8 *)(lVar17 + 0x70) = 0;
    *(undefined1 *)(lVar17 + 0x141) = 0;
    *(undefined1 *)(lVar17 + 0x142) = 0;
    uVar6 = *(undefined8 *)(lVar17 + 0x30);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar8 = UnityEngine_Object__op_Inequality(0,uVar6,0,0);
    if ((uVar8 & 1) != 0) {
      lVar5 = *(long *)(lVar17 + 0x30);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__Close(lVar5,0);
      uVar6 = *(undefined8 *)(lVar17 + 0x30);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      if ((bRam0000000004a04120 & 1) == 0) {
        func_0x010c65d4(0x906e,uVar6,0);
        bRam0000000004a04120 = 1;
      }
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      UNRECOVERED_JUMPTABLE = pcRam0000000004a04118;
      if ((pcRam0000000004a04118 == (code *)0x0) &&
         (UNRECOVERED_JUMPTABLE = (code *)func_0x0111e49c(&UNK_039a20c7),
         UNRECOVERED_JUMPTABLE == (code *)0x0)) {
        uVar7 = func_0x010f000c(&UNK_039a20c7);
        func_0x010eebec(uVar7,0,0);
      }
      pcRam0000000004a04118 = UNRECOVERED_JUMPTABLE;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*pcRam0000000004a04118)(uVar6,0);
      return;
    }
    return;
  default:
    return;
  case 7:
  case 8:
  case 10:
  case 0xb:
    puVar15 = (undefined8 *)&str_초대할_수_없는_상태입니다_;
    break;
  case 9:
    lVar17 = *(long *)(param_1 + 0x180);
    uVar6 = *(undefined8 *)(param_1 + 0x150);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    Multiplay_MultiplayManager_DeniedInvitingInfo__StoreDeniedInfo(lVar17,uVar6,0);
    puVar15 = (undefined8 *)&str_초대를_거절하였습니다_;
    break;
  case 0xc:
    puVar15 = (undefined8 *)&str_대기실_인원이_최대치_입니다_;
    break;
  case 0xd:
    goto code_r0x013a53a0;
  }
  uVar6 = ___L(0,*puVar15,0);
  uVar7 = func_0x01101ad4(_class_object___TypeInfo,0);
  PanelPopupCenterText__Show(0x3fcccccd,0,uVar6,0,uVar7,0);
code_r0x013a53a0:
  if (*(long *)(param_1 + 0x118) != 0) {
    System_Action<EnumData_MultiplayInviteUnableReason>__Invoke
              (*(long *)(param_1 + 0x118),uVar3,
               _mm_Method_System_Action<EnumData_MultiplayInviteUnableReason>_Invoke__);
  }
  *(undefined8 *)(param_1 + 0x150) = 0;
  return;
}


