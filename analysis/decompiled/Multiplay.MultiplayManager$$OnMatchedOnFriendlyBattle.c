// name: Multiplay.MultiplayManager$$OnMatchedOnFriendlyBattle
// entry: 013a7808

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnMatchedOnFriendlyBattle(long param_1,long param_2)

{
  long lVar1;
  ushort uVar2;
  undefined4 uVar3;
  undefined4 uVar4;
  undefined4 uVar5;
  uint uVar6;
  undefined4 uVar7;
  ulong uVar8;
  long *plVar9;
  undefined8 *puVar10;
  undefined8 uVar11;
  ulong uVar12;
  undefined8 uVar13;
  long lVar14;
  long lVar15;
  undefined8 uVar16;
  long lVar17;
  code *UNRECOVERED_JUMPTABLE_00;
  undefined8 uVar18;
  int *piVar19;
  undefined1 auVar20 [16];
  
  if ((bRam00000000049f7b06 & 1) == 0) {
    func_0x010c65d4(0x8a5a);
    bRam00000000049f7b06 = 1;
  }
  lVar14 = CompetitionManager__get_instance(0,0);
  if (lVar14 == 0) {
    func_0x010eec64(0);
  }
  lVar14 = *(long *)(lVar14 + 0xd8);
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = Multiplay_MultiplayManager__get_isFriendly();
  if ((lVar14 == 0) || (((uVar6 ^ 1) & 1) != 0)) {
code_r0x013a79e8:
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    lVar14 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    lVar14 = LitJson_JsonData__get_Item(lVar14,_str_roomInfo,0);
    if (lVar14 == 0) {
      return;
    }
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    lVar14 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    lVar14 = LitJson_JsonData__get_Item(lVar14,_str_roomInfo,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    lVar14 = LitJson_JsonData__get_Item(lVar14,_str_roomId,0);
    if (lVar14 == 0) {
      return;
    }
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    lVar14 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    lVar14 = LitJson_JsonData__get_Item(lVar14,_str_roomInfo,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    uVar16 = LitJson_JsonData__get_Item(lVar14,_str_roomId,0);
    uVar16 = LitJson_JsonData__op_Explicit(0,uVar16,0);
  }
  else {
    if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar6 = LoadedSceneCheckUtil__get_isCompetitionScene(0,0);
    if ((uVar6 & 1) == 0) goto code_r0x013a79e8;
    lVar15 = FriendlyMatchingRoom__get_me(lVar14,0);
    if (((lVar15 != 0) && (lVar15 = FriendlyMatchingRoom__get_player1(lVar14,0), lVar15 != 0)) &&
       (lVar15 = FriendlyMatchingRoom__get_player2(lVar14,0), lVar15 != 0)) {
      if (param_2 == 0) {
        func_0x010eec64(0);
      }
      lVar15 = SocketIO_SocketIOEvent__get_data(param_2,0);
      if (lVar15 == 0) {
        func_0x010eec64(0);
      }
      uVar16 = LitJson_JsonData__get_Item(lVar15,_str_roomInfo,0);
      FriendlyMatchingRoom__Deserialize(lVar14,uVar16,0);
      lVar15 = FriendlyMatchingRoom__get_me(lVar14,0);
      if (lVar15 == 0) {
        func_0x010eec64(0);
      }
      if (*(int *)(lVar15 + 0x30) != 2) {
        lVar14 = CompetitionManager__get_instance(0,0);
        if (lVar14 == 0) {
          func_0x010eec64(0);
        }
        CompetitionManager__SetBattleMapInfoForFriendlyMatch(lVar14,0);
        lVar14 = CompetitionManager__get_instance(0,0);
        if (lVar14 == 0) {
          func_0x010eec64(0);
        }
        auVar20 = CompetitionManager__get_competitionId(lVar14,0);
        if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0x12a) & 1)
             != 0) &&
           (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar7 = CodeStage_AntiCheat_ObscuredTypes_ObscuredInt__op_Implicit
                          (0,auVar20._0_8_,auVar20._8_8_ & 0xffffffff,0);
        Multiplay_MultiplayManager__InitFriendlyBattlePlayData(param_1,uVar7);
        Multiplay_MultiplayManager__GoToStep(param_1,0);
        if (param_2 == 0) {
          func_0x010eec64(0);
        }
        lVar14 = SocketIO_SocketIOEvent__get_data(param_2,0);
        if (lVar14 == 0) {
          func_0x010eec64(0);
        }
        uVar16 = LitJson_JsonData__get_Item(lVar14,_str_battlePlayData,0);
        Multiplay_Match__Deserialize(uVar16,uVar16);
        lVar14 = *(long *)(param_1 + 0x88);
        if (lVar14 == 0) {
          return;
        }
        if (*(long *)(lVar14 + 0x58) != 0) {
          System_Action__Invoke(*(long *)(lVar14 + 0x58),0);
        }
        UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar14 + 0x10);
        plVar9 = *(long **)(lVar14 + 0x20);
        puVar10 = *(undefined8 **)(lVar14 + 0x28);
        func_0x010da364(puVar10);
        uVar6 = func_0x01101d6c(puVar10);
        if ((uVar6 & 1) == 0) {
          if (*(char *)((long)puVar10 + 0x4e) != '\0') {
                    /* WARNING: Could not recover jumptable at 0x0109237c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
            (*UNRECOVERED_JUMPTABLE_00)(0,plVar9,puVar10);
            return;
          }
          plVar9 = (long *)0x0;
        }
        else if (((*(short *)((long)puVar10 + 0x4c) != -1) &&
                 ((plVar9 == (long *)0x0 || ((*(byte *)(*plVar9 + 0x10d) & 1) == 0)))) &&
                (*(long *)(lVar14 + 0x18) != 0)) {
          uVar12 = func_0x01101d58(puVar10);
          func_0x01101dd4(puVar10);
          uVar8 = func_0x010ba65c();
          if ((uVar12 & 1) == 0) {
            uVar2 = *(ushort *)((long)puVar10 + 0x4c);
            if ((uVar8 & 1) == 0) {
              lVar14 = *plVar9 + (ulong)uVar2 * 0x10;
              UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar14 + 0x130);
              puVar10 = *(undefined8 **)(lVar14 + 0x138);
            }
            else {
              lVar14 = func_0x01101dd4(puVar10);
              lVar15 = *plVar9;
              if ((ulong)*(ushort *)(lVar15 + 0x122) != 0) {
                uVar12 = 0;
                piVar19 = (int *)(*(long *)(lVar15 + 0xb0) + 8);
                do {
                  if (*(long *)(piVar19 + -2) == lVar14) {
                    puVar10 = (undefined8 *)
                              (lVar15 + (ulong)(*piVar19 + (uint)uVar2) * 0x10 + 0x130);
                    goto code_r0x0109246c;
                  }
                  uVar12 = uVar12 + 1;
                  piVar19 = piVar19 + 4;
                } while (uVar12 < *(ushort *)(lVar15 + 0x122));
              }
              puVar10 = (undefined8 *)func_0x010bc584(plVar9,lVar14,(ulong)uVar2);
code_r0x0109246c:
              UNRECOVERED_JUMPTABLE_00 = (code *)*puVar10;
              puVar10 = (undefined8 *)puVar10[1];
            }
          }
          else {
            if ((uVar8 & 1) == 0) {
              uVar16 = *(undefined8 *)
                        (*plVar9 + (ulong)*(ushort *)((long)puVar10 + 0x4c) * 0x10 + 0x138);
            }
            else {
              lVar14 = *plVar9;
              if ((ulong)*(ushort *)(lVar14 + 0x122) != 0) {
                uVar12 = 0;
                piVar19 = (int *)(*(long *)(lVar14 + 0xb0) + 8);
                do {
                  if (*(long *)(piVar19 + -2) == puVar10[3]) {
                    lVar14 = lVar14 + (ulong)(*piVar19 + (uint)*(ushort *)((long)puVar10 + 0x4c)) *
                                      0x10 + 0x130;
                    goto code_r0x0109243c;
                  }
                  uVar12 = uVar12 + 1;
                  piVar19 = piVar19 + 4;
                } while (uVar12 < *(ushort *)(lVar14 + 0x122));
              }
              lVar14 = func_0x010bc584(plVar9);
code_r0x0109243c:
              uVar16 = *(undefined8 *)(lVar14 + 8);
            }
            puVar10 = (undefined8 *)func_0x010da294(uVar16,puVar10);
            UNRECOVERED_JUMPTABLE_00 = (code *)*puVar10;
          }
        }
                    /* WARNING: Could not recover jumptable at 0x010923a4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
        (*UNRECOVERED_JUMPTABLE_00)(plVar9,puVar10);
        return;
      }
      lVar14 = FriendlyMatchingRoom__GetWatchHost(lVar14,0);
      if (lVar14 == 0) {
        func_0x010eec64(0);
      }
      uVar16 = *(undefined8 *)(lVar14 + 0x10);
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar18 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
      if ((bRam00000000049f7af0 & 1) == 0) {
        func_0x010c65d4(0x8a84);
        bRam00000000049f7af0 = 1;
      }
      if (*(char *)(param_1 + 0x141) == '\0') {
        return;
      }
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar6 = Multiplay_MultiplayManager__get_isFriendly();
      if ((uVar6 & 1) == 0) {
        if (((*(byte *)(_class_Singleton<VoiceChatController>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<VoiceChatController>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar14 = Singleton<iTweenManager>__get_instance
                           (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
        if (lVar14 == 0) {
          func_0x010eec64(0);
        }
        uVar12 = VoiceChatController__get_IsConnected(lVar14,0);
        if ((uVar12 & 1) != 0) {
          if (((*(byte *)(_class_Singleton<VoiceChatController>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Singleton<VoiceChatController>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar14 = Singleton<iTweenManager>__get_instance
                             (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
          if (lVar14 == 0) {
            func_0x010eec64(0);
          }
          VoiceChatController__LeaveRoom(lVar14,0);
        }
      }
      *(undefined8 *)(param_1 + 0x148) = uVar16;
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      *(undefined8 *)(param_1 + 0x158) = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
      uVar13 = func_0x010fdda8(_class_<>__AnonType40<ulong,_string,_bool,_bool>_TypeInfo);
      <>__AnonType40<ulong,_string,_bool,_bool>___ctor
                (uVar13,uVar16,uVar18,0,1,
                 _mm_Method_<>__AnonType40<ulong,_string,_bool,_bool>__ctor__);
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar18 = LitJson_JsonMapper__ToJson(0,uVar13,0);
      uVar16 = _str_requestWatch;
      goto Multiplay_MultiplayManager__EmitSocketMessage;
    }
    uVar16 = *(undefined8 *)(lVar14 + 0x10);
  }
  if ((bRam00000000049f7ad9 & 1) == 0) {
    func_0x010c65d4(0x8a41);
    bRam00000000049f7ad9 = 1;
  }
  if (((*(byte *)(_class_Singleton<VoiceChatController>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<VoiceChatController>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar14 = Singleton<iTweenManager>__get_instance
                     (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
  if (lVar14 == 0) {
    func_0x010eec64(0);
  }
  VoiceChatController__LeaveRoom(lVar14,0);
  if (*(char *)(param_1 + 0x141) == '\0') {
    if ((bRam00000000049f7ace & 1) == 0) {
      func_0x010c65d4(0x8a7a);
      bRam00000000049f7ace = 1;
    }
    lVar14 = MyUserData__get_instance(0,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    plVar9 = (long *)UserData__get_info(lVar14,0);
    if (plVar9 == (long *)0x0) {
      func_0x010eec64(0);
    }
    uVar16 = (**(code **)(*plVar9 + 0x1a0))(plVar9,*(undefined8 *)(*plVar9 + 0x1a8));
    lVar14 = MyUserData__get_instance(0,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    lVar14 = UserData__get_info(lVar14,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    uVar18 = UserInfo__get_nationName(lVar14,0);
    lVar14 = MyUserData__get_instance(0,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    lVar14 = UserData__get_info(lVar14,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = UserInfo__get_VisibleFaceUnitID(lVar14,0);
    lVar14 = MyUserData__get_instance(0,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    plVar9 = (long *)UserData__get_info(lVar14,0);
    if (plVar9 == (long *)0x0) {
      func_0x010eec64(0);
    }
    uVar3 = (**(code **)(*plVar9 + 0x1e0))(plVar9,*(undefined8 *)(*plVar9 + 0x1e8));
    lVar14 = MyUserData__get_instance(0,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    lVar14 = UserData__get_info(lVar14,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    uVar4 = UserInfo__get_level(lVar14,0);
    uVar13 = *(undefined8 *)(param_1 + 0x38);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Util_TypeInfo);
    }
    uVar5 = Util__GetRevisionNumber(0,0);
    uVar11 = func_0x010fdda8(
                            _class_<>__AnonType13<ulong,_string,_int,_int,_int,_ObscuredString,_int>_TypeInfo
                            );
    <>__AnonType13<ulong,_string,_int,_int,_int,_ObscuredString,_int>___ctor
              (uVar11,uVar16,uVar18,uVar7,uVar3,uVar4,uVar13,uVar5,
               _mm_Method_<>__AnonType13<ulong,_string,_int,_int,_int,_ObscuredString,_int>__ctor__)
    ;
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar18 = LitJson_JsonMapper__ToJson(0,uVar11,0);
    uVar16 = _str_register;
  }
  else {
    lVar14 = _class_Multiplay_MultiplayManager_TypeInfo;
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      lVar14 = func_0x010da55c();
    }
    Multiplay_MultiplayManager__set_isFriendly(lVar14,0);
    lVar14 = CompetitionManager__get_instance(0,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    *(undefined8 *)(lVar14 + 0xd8) = 0;
    plVar9 = *(long **)(param_1 + 0x40);
    if (plVar9 == (long *)0x0) {
      uVar7 = 8;
    }
    else {
      uVar7 = (**(code **)(*plVar9 + 0x170))(plVar9,*(undefined8 *)(*plVar9 + 0x178));
    }
    uVar18 = func_0x010fdda8(_class_<>__AnonType21<string,_Step>_TypeInfo);
    <>__AnonType21<string,_Step>___ctor
              (uVar18,uVar16,uVar7,_mm_Method_<>__AnonType21<string,_Step>__ctor__);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar18 = LitJson_JsonMapper__ToJson(0,uVar18,0);
    uVar16 = _str_leaveFriendlyMatchingRoom;
  }
Multiplay_MultiplayManager__EmitSocketMessage:
  uVar12 = MyUserData__get_hasInstance(0,0);
  if ((uVar12 & 1) != 0) {
    lVar14 = MyUserData__get_instance(0,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar14 + 0x268) != '\0') {
      lVar14 = *(long *)(param_1 + 0x30);
      if (lVar14 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar16,uVar18,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar16 = System_String__Format(0,_str____0____1__,uVar16,uVar18,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar18 = LitJson_JsonMapper__ToObject(0,uVar16,0);
      lVar17 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar16 = _str__;
      System_Object___ctor(lVar17,0);
      *(undefined4 *)(lVar17 + 0x14) = 2;
      *(undefined4 *)(lVar17 + 0x18) = 0;
      *(undefined8 *)(lVar17 + 0x30) = uVar18;
      *(undefined8 *)(lVar17 + 0x20) = uVar16;
      *(undefined4 *)(lVar17 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar17 + 0x10) = 4;
      lVar15 = lVar14;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar15 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar14 + 0x48);
      if (*(long *)(lVar14 + 0x50) == 0) {
        lVar15 = func_0x010eec64(0);
      }
      uVar16 = SocketIO_Encoder__Encode(lVar15,lVar17);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar16,0);
      return;
    }
  }
  uVar12 = Multiplay_MultiplayManager__get_isConnected(param_1);
  if ((uVar12 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(param_1 + 0x70) = 0;
  *(undefined1 *)(param_1 + 0x141) = 0;
  *(undefined1 *)(param_1 + 0x142) = 0;
  uVar16 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar12 = UnityEngine_Object__op_Inequality(0,uVar16,0,0);
  if ((uVar12 & 1) != 0) {
    lVar14 = *(long *)(param_1 + 0x30);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar14,0);
    uVar16 = *(undefined8 *)(param_1 + 0x30);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    if ((bRam0000000004a04120 & 1) == 0) {
      func_0x010c65d4(0x906e,uVar16,0);
      bRam0000000004a04120 = 1;
    }
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    UNRECOVERED_JUMPTABLE_00 = pcRam0000000004a04118;
    if ((pcRam0000000004a04118 == (code *)0x0) &&
       (UNRECOVERED_JUMPTABLE_00 = (code *)func_0x0111e49c(&UNK_039a20c7),
       UNRECOVERED_JUMPTABLE_00 == (code *)0x0)) {
      uVar18 = func_0x010f000c(&UNK_039a20c7);
      func_0x010eebec(uVar18,0,0);
    }
    pcRam0000000004a04118 = UNRECOVERED_JUMPTABLE_00;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*pcRam0000000004a04118)(uVar16,0);
    return;
  }
  return;
}


