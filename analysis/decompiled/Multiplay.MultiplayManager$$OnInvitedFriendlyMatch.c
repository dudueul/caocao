// name: Multiplay.MultiplayManager$$OnInvitedFriendlyMatch
// entry: 013a5408

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnInvitedFriendlyMatch(long param_1,long param_2)

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
  long lVar12;
  ulong uVar13;
  long *plVar14;
  undefined8 *puVar15;
  code *UNRECOVERED_JUMPTABLE;
  undefined8 uVar16;
  int *piVar17;
  
  if ((bRam00000000049f7af4 & 1) == 0) {
    func_0x010c65d4(0x8a57);
    bRam00000000049f7af4 = 1;
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
  uVar6 = Util__GetData(0,uVar6,_str_userId,0,0);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = SocketIO_SocketIOEvent__get_data(param_2,0);
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
        uVar16 = 4;
        goto code_r0x013a5624;
      }
      goto code_r0x013a559c;
    }
  }
  else {
code_r0x013a559c:
    lVar5 = *(long *)(param_1 + 0x178);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    uVar3 = Multiplay_MultiplayManager_DeniedInvitingInfo__CheckDeniedInvitingTime(lVar5,uVar6,0);
    if ((uVar3 & 1) == 0) {
      uVar16 = 5;
      goto code_r0x013a5624;
    }
    if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar3 = LoadedSceneCheckUtil__get_isCompetitionScene(0,0);
    if ((uVar3 & 1) == 0) {
      uVar16 = 2;
      goto code_r0x013a5624;
    }
    plVar9 = *(long **)(param_1 + 0x40);
    if ((plVar9 == (long *)0x0) ||
       (iVar4 = (**(code **)(*plVar9 + 0x170))(plVar9,*(undefined8 *)(*plVar9 + 0x178)), iVar4 < 0))
    {
      *(undefined8 *)(param_1 + 0x150) = uVar6;
      uVar16 = func_0x010fdda8(_class_<>__AnonType43<ulong>_TypeInfo);
      <>__AnonType43<ulong>___ctor(uVar16,uVar6,_mm_Method_<>__AnonType43<ulong>__ctor__);
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar6 = LitJson_JsonMapper__ToJson(0,uVar16,0);
      Multiplay_MultiplayManager__EmitSocketMessage(param_1,_str_responseInvitedFriendlyMatch,uVar6)
      ;
      if (*(long *)(param_1 + 0x120) == 0) {
        return;
      }
      if (param_2 == 0) {
        func_0x010eec64(0);
      }
      uVar6 = SocketIO_SocketIOEvent__get_data(param_2,0);
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_string_TypeInfo);
      }
      uVar16 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      plVar9 = (long *)Util__GetData(0,uVar6,_str_nationName,uVar16,0);
      lVar5 = *(long *)(param_1 + 0x120);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      if (*(long *)(lVar5 + 0x58) != 0) {
        System_Action<tk2dSpriteAnimator,_tk2dSpriteAnimationClip>__Invoke
                  (*(long *)(lVar5 + 0x58),plVar9,uVar7,
                   _mm_Method_System_Action<string,_string>_Invoke__);
      }
      UNRECOVERED_JUMPTABLE = *(code **)(lVar5 + 0x10);
      plVar14 = *(long **)(lVar5 + 0x20);
      puVar15 = *(undefined8 **)(lVar5 + 0x28);
      func_0x010da364(puVar15);
      uVar3 = func_0x01101d6c(puVar15);
      if ((uVar3 & 1) == 0) {
        if (*(char *)((long)puVar15 + 0x4e) != '\x02') {
                    /* WARNING: Could not recover jumptable at 0x029b1cb4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
          (*UNRECOVERED_JUMPTABLE)(0,plVar14,plVar9,uVar7,puVar15);
          return;
        }
        plVar14 = (long *)0x0;
      }
      else {
        if (*(char *)((long)puVar15 + 0x4e) != '\x02') {
          if ((*(short *)((long)puVar15 + 0x4c) != -1) &&
             (((plVar14 == (long *)0x0 || ((*(byte *)(*plVar14 + 0x10d) & 1) == 0)) &&
              (*(long *)(lVar5 + 0x18) != 0)))) {
            uVar8 = func_0x01101d58(puVar15);
            func_0x01101dd4(puVar15);
            uVar13 = func_0x010ba65c();
            if ((uVar8 & 1) == 0) {
              uVar2 = *(ushort *)((long)puVar15 + 0x4c);
              if ((uVar13 & 1) == 0) {
                lVar5 = *plVar9 + (ulong)uVar2 * 0x10;
                UNRECOVERED_JUMPTABLE = *(code **)(lVar5 + 0x130);
                puVar15 = *(undefined8 **)(lVar5 + 0x138);
              }
              else {
                lVar5 = func_0x01101dd4(puVar15);
                lVar12 = *plVar9;
                if ((ulong)*(ushort *)(lVar12 + 0x122) != 0) {
                  uVar8 = 0;
                  piVar17 = (int *)(*(long *)(lVar12 + 0xb0) + 8);
                  do {
                    if (*(long *)(piVar17 + -2) == lVar5) {
                      puVar15 = (undefined8 *)
                                (lVar12 + (ulong)(*piVar17 + (uint)uVar2) * 0x10 + 0x130);
                      goto code_r0x029b1eac;
                    }
                    uVar8 = uVar8 + 1;
                    piVar17 = piVar17 + 4;
                  } while (uVar8 < *(ushort *)(lVar12 + 0x122));
                }
                puVar15 = (undefined8 *)func_0x010bc584(plVar9,lVar5,(ulong)uVar2);
code_r0x029b1eac:
                UNRECOVERED_JUMPTABLE = (code *)*puVar15;
                puVar15 = (undefined8 *)puVar15[1];
              }
            }
            else {
              if ((uVar13 & 1) == 0) {
                uVar6 = *(undefined8 *)
                         (*plVar9 + (ulong)*(ushort *)((long)puVar15 + 0x4c) * 0x10 + 0x138);
              }
              else {
                lVar5 = *plVar9;
                if ((ulong)*(ushort *)(lVar5 + 0x122) != 0) {
                  uVar8 = 0;
                  piVar17 = (int *)(*(long *)(lVar5 + 0xb0) + 8);
                  do {
                    if (*(long *)(piVar17 + -2) == puVar15[3]) {
                      lVar5 = lVar5 + (ulong)(*piVar17 + (uint)*(ushort *)((long)puVar15 + 0x4c)) *
                                      0x10 + 0x130;
                      goto code_r0x029b1e54;
                    }
                    uVar8 = uVar8 + 1;
                    piVar17 = piVar17 + 4;
                  } while (uVar8 < *(ushort *)(lVar5 + 0x122));
                }
                lVar5 = func_0x010bc584(plVar9);
code_r0x029b1e54:
                uVar6 = *(undefined8 *)(lVar5 + 8);
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
            ((plVar14 == (long *)0x0 || ((*(byte *)(*plVar14 + 0x10d) & 1) == 0)))) &&
           (*(long *)(lVar5 + 0x18) != 0)) {
          uVar8 = func_0x01101d58(puVar15);
          func_0x01101dd4(puVar15);
          uVar13 = func_0x010ba65c();
          if ((uVar8 & 1) == 0) {
            uVar2 = *(ushort *)((long)puVar15 + 0x4c);
            if ((uVar13 & 1) == 0) {
              lVar5 = *plVar14 + (ulong)uVar2 * 0x10;
              UNRECOVERED_JUMPTABLE = *(code **)(lVar5 + 0x130);
              puVar15 = *(undefined8 **)(lVar5 + 0x138);
            }
            else {
              lVar5 = func_0x01101dd4(puVar15);
              lVar12 = *plVar14;
              if ((ulong)*(ushort *)(lVar12 + 0x122) != 0) {
                uVar8 = 0;
                piVar17 = (int *)(*(long *)(lVar12 + 0xb0) + 8);
                do {
                  if (*(long *)(piVar17 + -2) == lVar5) {
                    puVar15 = (undefined8 *)
                              (lVar12 + (ulong)(*piVar17 + (uint)uVar2) * 0x10 + 0x130);
                    goto code_r0x029b1e88;
                  }
                  uVar8 = uVar8 + 1;
                  piVar17 = piVar17 + 4;
                } while (uVar8 < *(ushort *)(lVar12 + 0x122));
              }
              puVar15 = (undefined8 *)func_0x010bc584(plVar14,lVar5,(ulong)uVar2);
code_r0x029b1e88:
              UNRECOVERED_JUMPTABLE = (code *)*puVar15;
              puVar15 = (undefined8 *)puVar15[1];
            }
          }
          else {
            if ((uVar13 & 1) == 0) {
              uVar6 = *(undefined8 *)
                       (*plVar14 + (ulong)*(ushort *)((long)puVar15 + 0x4c) * 0x10 + 0x138);
            }
            else {
              lVar5 = *plVar14;
              if ((ulong)*(ushort *)(lVar5 + 0x122) != 0) {
                uVar8 = 0;
                piVar17 = (int *)(*(long *)(lVar5 + 0xb0) + 8);
                do {
                  if (*(long *)(piVar17 + -2) == puVar15[3]) {
                    lVar5 = lVar5 + (ulong)(*piVar17 + (uint)*(ushort *)((long)puVar15 + 0x4c)) *
                                    0x10 + 0x130;
                    goto code_r0x029b1e1c;
                  }
                  uVar8 = uVar8 + 1;
                  piVar17 = piVar17 + 4;
                } while (uVar8 < *(ushort *)(lVar5 + 0x122));
              }
              lVar5 = func_0x010bc584(plVar14);
code_r0x029b1e1c:
              uVar6 = *(undefined8 *)(lVar5 + 8);
            }
            puVar15 = (undefined8 *)func_0x010da294(uVar6,puVar15);
            UNRECOVERED_JUMPTABLE = (code *)*puVar15;
          }
        }
      }
                    /* WARNING: Could not recover jumptable at 0x029b1ce0. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE)(plVar14,plVar9,uVar7,puVar15);
      return;
    }
  }
  uVar16 = 1;
code_r0x013a5624:
  if ((bRam00000000049f7af7 & 1) == 0) {
    func_0x010c65d4(0x8a24,uVar6,uVar16);
    bRam00000000049f7af7 = 1;
  }
  if (*(char *)(param_1 + 0x141) == '\0') {
    return;
  }
  uVar10 = func_0x010fdda8(
                          _class_<>__AnonType44<ulong,_EnumData_MultiplayInviteUnableReason,_string>_TypeInfo
                          );
  <>__AnonType44<ulong,_EnumData_MultiplayInviteUnableReason,_string>___ctor
            (uVar10,uVar6,uVar16,uVar7,
             _mm_Method_<>__AnonType44<ulong,_EnumData_MultiplayInviteUnableReason,_string>__ctor__)
  ;
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
      lVar5 = *(long *)(param_1 + 0x30);
      if (lVar5 == 0) {
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
      lVar12 = lVar5;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar12 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar5 + 0x48);
      if (*(long *)(lVar5 + 0x50) == 0) {
        lVar12 = func_0x010eec64(0);
      }
      uVar6 = SocketIO_Encoder__Encode(lVar12,lVar11);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar6,0);
      return;
    }
  }
  uVar8 = Multiplay_MultiplayManager__get_isConnected(param_1);
  if ((uVar8 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(param_1 + 0x70) = 0;
  *(undefined1 *)(param_1 + 0x141) = 0;
  *(undefined1 *)(param_1 + 0x142) = 0;
  uVar6 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar8 = UnityEngine_Object__op_Inequality(0,uVar6,0,0);
  if ((uVar8 & 1) != 0) {
    lVar5 = *(long *)(param_1 + 0x30);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar5,0);
    uVar6 = *(undefined8 *)(param_1 + 0x30);
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
}


