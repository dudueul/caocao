// name: Multiplay.MultiplayManager$$OnCheckMultiplayState
// entry: 013ac624

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnCheckMultiplayState(long param_1,long param_2)

{
  undefined1 *puVar1;
  ushort uVar2;
  undefined4 uVar3;
  uint uVar4;
  uint uVar5;
  code *UNRECOVERED_JUMPTABLE_00;
  ulong uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  long lVar9;
  ulong uVar10;
  long lVar11;
  undefined8 uVar12;
  long lVar13;
  long lVar14;
  long *plVar15;
  long *plVar16;
  long lVar17;
  int iVar18;
  undefined8 *puVar19;
  int *piVar20;
  undefined8 unaff_x19;
  long unaff_x20;
  undefined8 unaff_x21;
  undefined8 unaff_x22;
  undefined1 *unaff_x29;
  code *unaff_x30;
  undefined1 auVar21 [16];
  undefined1 auVar22 [12];
  undefined8 uStack_70;
  undefined8 uStack_68;
  
  if ((bRam00000000049f7b25 & 1) == 0) {
    func_0x010c65d4(0x8a49);
    bRam00000000049f7b25 = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Util_TypeInfo);
  }
  uVar4 = Util__GetData<EnumData_MultiplayState>
                    (0,uVar8,_str_state,0,_mm_Method_Util_GetData<EnumData_MultiplayState>__);
  if (((*(byte *)(_class_SceneSingleton<MultiplayIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<MultiplayIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_SceneSingleton<MultiplayIndicator>_TypeInfo);
  }
  lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<MultiplayIndicator>_get_instance__);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  MultiplayIndicator__Off(lVar9,0,0);
  iVar18 = uVar4 + 7;
  if (3 < uVar4) {
    iVar18 = 0;
  }
  switch(iVar18) {
  case 7:
    if ((bRam00000000049f7b33 & 1) == 0) {
      func_0x010c65d4(0x8a8c);
      bRam00000000049f7b33 = 1;
    }
    if (((*(byte *)(_class_Singleton<MultiplayUserStatusPopup>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<MultiplayUserStatusPopup>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar9 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<MultiplayUserStatusPopup>_get_instance__);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    MultiplayUserStatusPopup__Off(lVar9,0);
    *(undefined8 *)(param_1 + 0x40) = 0;
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar10 = Multiplay_MultiplayManager__get_isMyActiveBattle();
    if ((uVar10 & 1) != 0) {
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                        (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      uVar8 = *(undefined8 *)(lVar9 + 0x128);
      uVar3 = *(undefined4 *)(lVar9 + 0x130);
      if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1)
           != 0) &&
         (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar8,uVar3,0);
      if ((uVar10 & 1) != 0) {
        if (((*(byte *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                          (0,_mm_Method_SceneSingleton<UIComponents>_get_instance__);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        uVar4 = UIComponents__IsActiveInHierarchy(lVar9,_str_VictoryPanel,0);
        if ((uVar4 & 1) != 0) {
          return;
        }
      }
    }
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar10 = Multiplay_MultiplayManager__get_isWatchingBattle();
    if ((uVar10 & 1) == 0) {
      puVar19 = (undefined8 *)&str_상대방의_연결이_끊어졌습니다_;
    }
    else {
      puVar19 = (undefined8 *)&str_전투가_종료되었습니다_;
    }
    lVar11 = ___L(0,*puVar19,0);
    lVar9 = ___L(0,_str_알림,0);
    uVar8 = _mm_Method_Multiplay_MultiplayManager_<SetBattleEndByOpponentDisconnected>m__5__;
    uVar12 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar12,param_1,uVar8,0);
    break;
  case 8:
    if ((bRam00000000049f7b34 & 1) == 0) {
      func_0x010c65d4(0x8a92);
      bRam00000000049f7b34 = 1;
    }
    if (((*(byte *)(_class_Singleton<MultiplayUserStatusPopup>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<MultiplayUserStatusPopup>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar9 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<MultiplayUserStatusPopup>_get_instance__);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    MultiplayUserStatusPopup__Off(lVar9,0);
    *(undefined8 *)(param_1 + 0x40) = 0;
    lVar9 = ___L(0,_str_알림,0);
    lVar11 = ___L(0,_str_서버와의_연결이_끊어졌습니다_,0);
    uVar8 = 
    _mm_Method_Multiplay_MultiplayManager_<SetMatchmakingCanceledByInvalidBattlePlayData>m__6__;
    uVar12 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar12,param_1,uVar8,0);
    break;
  case 9:
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar8 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Util_TypeInfo);
    }
    uVar4 = Util__GetData<EnumData_MultiplayBattleResult>
                      (0,uVar8,_str_data,0,_mm_Method_Util_GetData<EnumData_MultiplayBattleResult>__
                      );
    if ((bRam00000000049f7b36 & 1) == 0) {
      func_0x010c65d4(0x8a94);
      bRam00000000049f7b36 = 1;
    }
    if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar5 = LoadedSceneCheckUtil__get_isBattleMap(0,0);
    if ((uVar5 & 1) == 0) {
      Multiplay_MultiplayManager__SendCancelMatchmaking(param_1,1,0);
      lVar9 = *(long *)(param_1 + 0x90);
      if (lVar9 == 0) {
        return;
      }
code_r0x01092280:
      if (*(long *)(lVar9 + 0x58) != 0) {
        System_Action__Invoke(*(long *)(lVar9 + 0x58),0);
      }
      UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar9 + 0x10);
      plVar15 = *(long **)(lVar9 + 0x20);
      puVar19 = *(undefined8 **)(lVar9 + 0x28);
      func_0x010da364(puVar19);
      uVar4 = func_0x01101d6c(puVar19);
      if ((uVar4 & 1) == 0) {
        if (*(char *)((long)puVar19 + 0x4e) != '\0') {
                    /* WARNING: Could not recover jumptable at 0x0109237c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
          (*UNRECOVERED_JUMPTABLE_00)(0,plVar15,puVar19);
          return;
        }
        plVar15 = (long *)0x0;
      }
      else if ((*(short *)((long)puVar19 + 0x4c) != -1) &&
              (((plVar15 == (long *)0x0 || ((*(byte *)(*plVar15 + 0x10d) & 1) == 0)) &&
               (*(long *)(lVar9 + 0x18) != 0)))) {
        uVar10 = func_0x01101d58(puVar19);
        func_0x01101dd4(puVar19);
        uVar6 = func_0x010ba65c();
        if ((uVar10 & 1) == 0) {
          uVar2 = *(ushort *)((long)puVar19 + 0x4c);
          if ((uVar6 & 1) == 0) {
            lVar9 = *plVar15 + (ulong)uVar2 * 0x10;
            UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar9 + 0x130);
            puVar19 = *(undefined8 **)(lVar9 + 0x138);
          }
          else {
            lVar9 = func_0x01101dd4(puVar19);
            lVar11 = *plVar15;
            if ((ulong)*(ushort *)(lVar11 + 0x122) != 0) {
              uVar10 = 0;
              piVar20 = (int *)(*(long *)(lVar11 + 0xb0) + 8);
              do {
                if (*(long *)(piVar20 + -2) == lVar9) {
                  puVar19 = (undefined8 *)(lVar11 + (ulong)(*piVar20 + (uint)uVar2) * 0x10 + 0x130);
                  goto code_r0x0109246c;
                }
                uVar10 = uVar10 + 1;
                piVar20 = piVar20 + 4;
              } while (uVar10 < *(ushort *)(lVar11 + 0x122));
            }
            puVar19 = (undefined8 *)func_0x010bc584(plVar15,lVar9,(ulong)uVar2);
code_r0x0109246c:
            UNRECOVERED_JUMPTABLE_00 = (code *)*puVar19;
            puVar19 = (undefined8 *)puVar19[1];
          }
        }
        else {
          if ((uVar6 & 1) == 0) {
            uVar8 = *(undefined8 *)
                     (*plVar15 + (ulong)*(ushort *)((long)puVar19 + 0x4c) * 0x10 + 0x138);
          }
          else {
            lVar9 = *plVar15;
            if ((ulong)*(ushort *)(lVar9 + 0x122) != 0) {
              uVar10 = 0;
              piVar20 = (int *)(*(long *)(lVar9 + 0xb0) + 8);
              do {
                if (*(long *)(piVar20 + -2) == puVar19[3]) {
                  lVar9 = lVar9 + (ulong)(*piVar20 + (uint)*(ushort *)((long)puVar19 + 0x4c)) * 0x10
                          + 0x130;
                  goto code_r0x0109243c;
                }
                uVar10 = uVar10 + 1;
                piVar20 = piVar20 + 4;
              } while (uVar10 < *(ushort *)(lVar9 + 0x122));
            }
            lVar9 = func_0x010bc584(plVar15);
code_r0x0109243c:
            uVar8 = *(undefined8 *)(lVar9 + 8);
          }
          puVar19 = (undefined8 *)func_0x010da294(uVar8,puVar19);
          UNRECOVERED_JUMPTABLE_00 = (code *)*puVar19;
        }
      }
                    /* WARNING: Could not recover jumptable at 0x010923a4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE_00)(plVar15,puVar19);
      return;
    }
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    uVar8 = *(undefined8 *)(lVar9 + 0x128);
    uVar3 = *(undefined4 *)(lVar9 + 0x130);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) !=
         0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0
               )) {
      func_0x010da55c();
    }
    uVar10 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar8,uVar3,0);
    if ((uVar10 & 1) == 0) {
      if (5 < uVar4) {
code_r0x013acbd8:
        return;
      }
      switch(*(undefined4 *)(&UNK_03991a80 + (long)(int)uVar4 * 4)) {
      case 0xc:
      case 0xd:
        lVar9 = ___L(0,_str_알림,0);
        puVar19 = (undefined8 *)&str_오류가_발생하여_로비로_돌아갑니다_;
        break;
      case 0xe:
        goto Multiplay_MultiplayManager__SetWin;
      case 0xf:
        goto Multiplay_MultiplayManager__SetLose;
      case 0x10:
        if ((bRam00000000049f7b3e & 1) == 0) {
          func_0x010c65d4(0x8a90);
          bRam00000000049f7b3e = 1;
        }
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                          (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1)
             != 0) &&
           (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo);
        }
        auVar22 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,1,0);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        *(undefined1 (*) [12])(lVar9 + 0x128) = auVar22;
        if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar10 = Multiplay_MultiplayManager__get_isWatchingBattle();
        if ((uVar10 & 1) != 0) {
          lVar9 = *(long *)(param_1 + 0x48);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          lVar9 = BattlePlay_BattlePlayData__get_host(lVar9,0);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          *(undefined4 *)(lVar9 + 0x40) = 3;
          lVar9 = *(long *)(param_1 + 0x48);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          lVar9 = BattlePlay_BattlePlayData__get_opponent(lVar9,0);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          *(undefined4 *)(lVar9 + 0x40) = 2;
          uVar8 = _mm_Method_Multiplay_MultiplayManager_GoToCompetitionLobby__;
          uVar12 = func_0x010fdda8(_class_System_Action_TypeInfo);
          System_Action___ctor(uVar12,param_1,uVar8,0);
          goto Multiplay_MultiplayManager__ShowBattleEndPopupOnWatch;
        }
        Multiplay_MultiplayManager__ShowCompetitionFinish(uVar10,0);
        lVar9 = ___L(0,_str_전투_패배,0);
        lVar11 = ___L(0,_str_서버와의_연결이_끊어졌습니다_,0);
        uVar8 = _mm_Method_Multiplay_MultiplayManager_OnConfirmPopup__;
        uVar12 = func_0x010fdda8(_class_System_Action_TypeInfo);
        System_Action___ctor(uVar12,param_1,uVar8,0);
        goto PanelPopup1BtnConfirm__ShowPopup;
      default:
        goto code_r0x013acbd8;
      }
    }
    else {
      lVar9 = ___L(0,_str_알림,0);
      puVar19 = (undefined8 *)&str_전투_결과가_이미_처리_되었습니다_;
    }
    lVar11 = ___L(0,*puVar19,0);
    uVar8 = _mm_Method_Multiplay_MultiplayManager_GoToCompetitionLobby__;
    uVar12 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar12,param_1,uVar8,0);
    break;
  case 10:
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar8 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Util_TypeInfo);
    }
    uVar4 = Util__GetData<EnumData_MultiplayBattleResult>
                      (0,uVar8,_str_data,0,_mm_Method_Util_GetData<EnumData_MultiplayBattleResult>__
                      );
    if ((bRam00000000049f7b37 & 1) == 0) {
      func_0x010c65d4(0x8a96);
      bRam00000000049f7b37 = 1;
    }
    if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar5 = LoadedSceneCheckUtil__get_isBattleMap(0,0);
    if ((uVar5 & 1) == 0) {
      Multiplay_MultiplayManager__SendCancelMatchmaking(param_1,1,0);
      lVar9 = *(long *)(param_1 + 0x90);
      if (lVar9 == 0) {
code_r0x013ace50:
        return;
      }
      goto code_r0x01092280;
    }
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    uVar8 = *(undefined8 *)(lVar9 + 0x128);
    uVar3 = *(undefined4 *)(lVar9 + 0x130);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) !=
         0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0
               )) {
      func_0x010da55c();
    }
    uVar10 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar8,uVar3,0);
    if ((uVar10 & 1) != 0) {
      lVar9 = ___L(0,_str_알림,0);
      puVar19 = (undefined8 *)&str_전투_결과가_이미_처리_되었습니다_;
code_r0x013acdd4:
      lVar11 = ___L(0,*puVar19,0);
      uVar8 = _mm_Method_Multiplay_MultiplayManager_GoToCompetitionLobby__;
      uVar12 = func_0x010fdda8(_class_System_Action_TypeInfo);
      System_Action___ctor(uVar12,param_1,uVar8,0);
      break;
    }
    iVar18 = uVar4 + 0xc;
    if (5 < uVar4) {
      iVar18 = 0;
    }
    switch(iVar18) {
    case 0xc:
    case 0xd:
      lVar9 = ___L(0,_str_알림,0);
      puVar19 = (undefined8 *)&str_오류가_발생하여_로비로_돌아갑니다_;
      goto code_r0x013acdd4;
    case 0xe:
Multiplay_MultiplayManager__SetLose:
      if ((bRam00000000049f7b3d & 1) == 0) {
        func_0x010c65d4(0x8a91);
        bRam00000000049f7b3d = 1;
      }
      lVar9 = func_0x010fdda8(_class_Multiplay_MultiplayManager_<SetLose>c__AnonStorey9_TypeInfo);
      Multiplay_MultiplayManager_<SetLose>c__AnonStorey9___ctor(lVar9,0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
        uRam0000000000000010 = 0;
        func_0x010eec64(0);
      }
      else {
        *(undefined8 *)(lVar9 + 0x10) = 0;
      }
      *(long *)(lVar9 + 0x18) = param_1;
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar11 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1)
           != 0) &&
         (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo);
      }
      auVar22 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,1,0);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      *(undefined1 (*) [12])(lVar11 + 0x128) = auVar22;
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = Multiplay_MultiplayManager__get_isWatchingBattle();
      if ((uVar10 & 1) != 0) {
        lVar11 = *(long *)(param_1 + 0x48);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        lVar11 = BattlePlay_BattlePlayData__get_host(lVar11,0);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        *(undefined4 *)(lVar11 + 0x40) = 3;
        lVar11 = *(long *)(param_1 + 0x48);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        lVar11 = BattlePlay_BattlePlayData__get_opponent(lVar11,0);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        *(undefined4 *)(lVar11 + 0x40) = 2;
        uVar8 = _mm_Method_Multiplay_MultiplayManager_<SetLose>c__AnonStorey9_<>m__0__;
        uVar12 = func_0x010fdda8(_class_System_Action_TypeInfo);
        System_Action___ctor(uVar12,lVar9,uVar8,0);
        goto Multiplay_MultiplayManager__ShowBattleEndPopupOnWatch;
      }
      iVar18 = 0;
      goto code_r0x013af02c;
    case 0xf:
Multiplay_MultiplayManager__SetWin:
      if ((bRam00000000049f7b3c & 1) == 0) {
        func_0x010c65d4(0x8a9c);
        bRam00000000049f7b3c = 1;
      }
      lVar9 = func_0x010fdda8(_class_Multiplay_MultiplayManager_<SetWin>c__AnonStorey8_TypeInfo);
      Multiplay_MultiplayManager_<SetWin>c__AnonStorey8___ctor(lVar9,0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
        uRam0000000000000010 = 0;
        func_0x010eec64(0);
      }
      else {
        *(undefined8 *)(lVar9 + 0x10) = 0;
      }
      *(long *)(lVar9 + 0x18) = param_1;
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar11 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1)
           != 0) &&
         (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo);
      }
      auVar22 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,1,0);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      *(undefined1 (*) [12])(lVar11 + 0x128) = auVar22;
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = Multiplay_MultiplayManager__get_isWatchingBattle();
      if ((uVar10 & 1) != 0) {
        lVar11 = *(long *)(param_1 + 0x48);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        lVar11 = BattlePlay_BattlePlayData__get_host(lVar11,0);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        *(undefined4 *)(lVar11 + 0x40) = 2;
        lVar11 = *(long *)(param_1 + 0x48);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        lVar11 = BattlePlay_BattlePlayData__get_opponent(lVar11,0);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        *(undefined4 *)(lVar11 + 0x40) = 3;
        uVar8 = _mm_Method_Multiplay_MultiplayManager_<SetWin>c__AnonStorey8_<>m__0__;
        uVar12 = func_0x010fdda8(_class_System_Action_TypeInfo);
        System_Action___ctor(uVar12,lVar9,uVar8,0);
Multiplay_MultiplayManager__ShowBattleEndPopupOnWatch:
        if ((bRam00000000049f7b43 & 1) == 0) {
          func_0x010c65d4(0x8a9e);
          bRam00000000049f7b43 = 1;
        }
        if ((*(long *)(param_1 + 0x48) == 0) ||
           (lVar9 = BattlePlay_BattlePlayData__GetWinner(*(long *)(param_1 + 0x48),0), lVar9 == 0))
        {
          uVar8 = ___L(0,_str_경쟁전_로비로_이동합니다_,0);
        }
        else {
          uVar8 = ___L(0,_str__0__국이_승리하였습니다_,0);
          if (((*(byte *)(_class_Localization_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Localization_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c(_class_Localization_TypeInfo);
          }
          uVar8 = Localization__Get(0,uVar8,0);
          uVar7 = *(undefined8 *)(lVar9 + 0x18);
          if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c(_class_string_TypeInfo);
          }
          uVar8 = System_String__Format(0,uVar8,uVar7,0);
        }
        uVar7 = ___L(0,_str_관전_종료,0);
        lVar11 = Multiplay_MultiplayManager__GoToLobbyWithDelay(param_1,uVar7,uVar8,uVar12);
        puVar1 = &stack0xfffffffffffffff0;
        lVar9 = param_1;
        if ((bRam0000000004a0404a & 1) == 0) {
          lVar9 = func_0x010c65d4(0x8979,lVar11,0);
          bRam0000000004a0404a = 1;
        }
        if (lVar11 == 0) {
          unaff_x19 = func_0x010fdda8(_class_System_NullReferenceException_TypeInfo);
          System_NullReferenceException___ctor(unaff_x19,_str_routine_is_null,0);
        }
        else {
          uVar4 = UnityEngine_MonoBehaviour__IsObjectMonoBehaviour(lVar9,param_1);
          lVar9 = param_1;
          if ((uVar4 & 1) != 0) goto code_r0x02abd390;
          unaff_x19 = func_0x010fdda8(_class_System_ArgumentException_TypeInfo);
          System_ArgumentException___ctor
                    (unaff_x19,_str_Coroutines_can_only_be_stopped_on_a_MonoBehaviour,0);
        }
        func_0x010eebec(unaff_x19,0,_mm_Method_UnityEngine_MonoBehaviour_StartCoroutine__);
        unaff_x30 = UnityEngine_MonoBehaviour__StartCoroutineManaged2;
        auVar21 = func_0x0112148c();
        lVar11 = auVar21._8_8_;
        register0x00000008 = (BADSPACEBASE *)&stack0xffffffffffffffd0;
        lVar9 = auVar21._0_8_;
        unaff_x20 = param_1;
        unaff_x21 = 0x4a04000;
        unaff_x29 = puVar1;
code_r0x02abd390:
        *(undefined8 *)((long)register0x00000008 + -0x30) = unaff_x22;
        *(undefined8 *)((long)register0x00000008 + -0x28) = unaff_x21;
        *(long *)((long)register0x00000008 + -0x20) = unaff_x20;
        *(undefined8 *)((long)register0x00000008 + -0x18) = unaff_x19;
        *(undefined1 **)((long)register0x00000008 + -0x10) = unaff_x29;
        *(code **)((long)register0x00000008 + -8) = unaff_x30;
        UNRECOVERED_JUMPTABLE_00 = pcRam0000000004a040b0;
        if ((pcRam0000000004a040b0 == (code *)0x0) &&
           (UNRECOVERED_JUMPTABLE_00 = (code *)func_0x0111e49c(&UNK_039a1f22),
           UNRECOVERED_JUMPTABLE_00 == (code *)0x0)) {
          *(undefined8 *)((long)register0x00000008 + -0x38) = 0;
          uVar8 = func_0x010f000c(&UNK_039a1f22);
          func_0x010eebec(uVar8,0,0);
          UNRECOVERED_JUMPTABLE_00 = *(code **)((long)register0x00000008 + -0x38);
        }
        pcRam0000000004a040b0 = UNRECOVERED_JUMPTABLE_00;
                    /* WARNING: Could not recover jumptable at 0x02abd408. Too many branches */
                    /* WARNING: Treating indirect jump as call */
        (*pcRam0000000004a040b0)(lVar9,lVar11);
        return;
      }
      iVar18 = 1;
code_r0x013af02c:
      if ((bRam00000000049f7b42 & 1) == 0) {
        func_0x010c65d4(0x8a9f);
        bRam00000000049f7b42 = 1;
      }
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar4 = Multiplay_MultiplayManager__get_isWatchingBattle();
      if ((uVar4 & 1) != 0) {
        return;
      }
      if (((*(byte *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                        (0,_mm_Method_SceneSingleton<UIComponents>_get_instance__);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      lVar9 = UIComponents__get_Item(lVar9,_str_VictoryPanel,0);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
      }
      uVar10 = UnityEngine_Object__op_Implicit(0,lVar9,0);
      if ((uVar10 & 1) == 0) {
        return;
      }
      if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar11 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      *(undefined1 *)(lVar11 + 0xa0) = 1;
      if (((*(byte *)(_class_SceneSingleton<TimeController>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<TimeController>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar11 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<TimeController>_get_instance__);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      TimeController__SetSpeed(lVar11,0,0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      lVar9 = UnityEngine_GameObject__GetComponent<object>
                        (lVar9,_mm_Method_UnityEngine_GameObject_GetComponent<PopupFinish>__);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam00000000049f776f & 1) == 0) {
        func_0x010c65d4(0xa2d6,iVar18,0);
        bRam00000000049f776f = 1;
      }
      lVar11 = UnityEngine_Camera__get_main(0,0);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      lVar11 = UnityEngine_Component__get_gameObject(lVar11,0);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
      }
      uVar10 = UnityEngine_Object__op_Inequality(0,0,lVar11,0);
      if ((uVar10 & 1) != 0) {
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        lVar11 = UnityEngine_GameObject__GetComponent<object>
                           (lVar11,
                            _mm_Method_UnityEngine_GameObject_GetComponent<BattleCameraController>__
                           );
        if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
        }
        uVar10 = UnityEngine_Object__op_Inequality(0,0,lVar11,0);
        if ((uVar10 & 1) != 0) {
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          BattleCameraController__SetZoomSize(lVar11,0);
        }
      }
      if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar11 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
      uVar3 = 1;
      if (iVar18 == 0) {
        uVar3 = 2;
      }
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      *(undefined4 *)(lVar11 + 0x84) = uVar3;
      if (((*(byte *)(_class_SceneSingleton<DepthManager>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<DepthManager>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar11 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<DepthManager>_get_instance__);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      DepthManager__PopAllNormalPopups(lVar11,0);
      PopupFinish__SetVictoryObject(lVar9);
      CaocaoUIModalPopup__ShowPanel(lVar9,0);
      if (((*(byte *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar11 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<UIComponents>_get_instance__);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      lVar11 = UIComponents__get_Item(lVar11,_str_Chat,0);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      plVar15 = (long *)UnityEngine_GameObject__GetComponent<object>
                                  (lVar11,
                                   _mm_Method_UnityEngine_GameObject_GetComponent<PanelChat>__);
      if (plVar15 == (long *)0x0) {
        func_0x010eec64(0);
      }
      (**(code **)(*plVar15 + 0x2e0))(plVar15,*(undefined8 *)(*plVar15 + 0x2e8));
      if (((*(byte *)(_class_Singleton<BGMPlayer>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<BGMPlayer>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar11 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<BGMPlayer>_get_instance__);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      BGMPlayer__StopImmediately(lVar11,0);
      PopupFinish__UpdatePanelUI(lVar9);
      if (((*(byte *)(_class_Singleton<CommonMissionsDistribution>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<CommonMissionsDistribution>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar11 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<CommonMissionsDistribution>_get_instance__);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      CommonMissionsDistribution__CheckDefeatUnits(lVar11,0);
      if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar11 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      uVar4 = SceneDataPipe__get_battleMode(lVar11,0);
      if (uVar4 < 0x14) {
        if (0x22 < *(uint *)(&UNK_03991570 + (long)(int)uVar4 * 4)) {
          return;
        }
        switch(uVar4) {
        default:
          goto code_r0x01331640;
        case 1:
          lVar9 = *(long *)(lVar9 + 0x50);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          puVar19 = (undefined8 *)&mm_Method_UnityEngine_GameObject_GetComponent<FinishSiege>__;
          break;
        case 2:
        case 5:
          lVar9 = *(long *)(lVar9 + 0x50);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          puVar19 = (undefined8 *)&mm_Method_UnityEngine_GameObject_GetComponent<FinishInstance>__;
          break;
        case 3:
          lVar9 = *(long *)(lVar9 + 0x50);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          puVar19 = (undefined8 *)&mm_Method_UnityEngine_GameObject_GetComponent<FinishSham>__;
          break;
        case 6:
        case 0xb:
        case 0xd:
          lVar9 = *(long *)(lVar9 + 0x50);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          puVar19 = (undefined8 *)
                    &mm_Method_UnityEngine_GameObject_GetComponent<FinishAnnihilationWar>__;
          break;
        case 7:
          lVar9 = *(long *)(lVar9 + 0x50);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          puVar19 = (undefined8 *)
                    &mm_Method_UnityEngine_GameObject_GetComponent<FinishThousandBattle>__;
          break;
        case 8:
          lVar9 = *(long *)(lVar9 + 0x50);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          puVar19 = (undefined8 *)
                    &mm_Method_UnityEngine_GameObject_GetComponent<FinishBattleField>__;
          break;
        case 10:
          lVar9 = *(long *)(lVar9 + 0x50);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          puVar19 = (undefined8 *)&mm_Method_UnityEngine_GameObject_GetComponent<FinishRaid>__;
          break;
        case 0xe:
        case 0xf:
          lVar9 = *(long *)(lVar9 + 0x50);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          puVar19 = (undefined8 *)
                    &mm_Method_UnityEngine_GameObject_GetComponent<FinishCompetition>__;
          break;
        case 0x10:
          lVar9 = *(long *)(lVar9 + 0x50);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          puVar19 = (undefined8 *)&mm_Method_UnityEngine_GameObject_GetComponent<FinishWorldBoss>__;
          break;
        case 0x11:
          lVar11 = *(long *)(lVar9 + 0x40);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          lVar11 = UnityEngine_Component__get_gameObject(lVar11,0);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          UnityEngine_GameObject__SetActive(lVar11,0,0);
          lVar11 = *(long *)(lVar9 + 0x48);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          lVar11 = UnityEngine_Component__get_gameObject(lVar11,0);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          UnityEngine_GameObject__SetActive(lVar11,0,0);
          lVar11 = UnityEngine_Component__get_transform(lVar9,0);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          lVar11 = UnityEngine_Transform__Find(lVar11,_str_bg,0);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          lVar11 = UnityEngine_Component__get_gameObject(lVar11,0);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          UnityEngine_GameObject__SetActive(lVar11,0,0);
          lVar11 = UnityEngine_Component__get_transform(lVar9,0);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          lVar11 = UnityEngine_Transform__Find(lVar11,_str_SmT,0);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          lVar11 = UnityEngine_Component__get_gameObject(lVar11,0);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          UnityEngine_GameObject__SetActive(lVar11,0,0);
          lVar9 = *(long *)(lVar9 + 0x50);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          puVar19 = (undefined8 *)
                    &mm_Method_UnityEngine_GameObject_GetComponent<FinishRelationBattle>__;
          break;
        case 0x12:
          lVar11 = UnityEngine_Component__get_transform(lVar9,0);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          lVar11 = UnityEngine_Transform__Find(lVar11,_str_SmT,0);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          lVar11 = UnityEngine_Component__get_gameObject(lVar11,0);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          UnityEngine_GameObject__SetActive(lVar11,0,0);
          lVar9 = *(long *)(lVar9 + 0x50);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          puVar19 = (undefined8 *)&mm_Method_UnityEngine_GameObject_GetComponent<FinishGuildWar>__;
          break;
        case 0x13:
          lVar9 = *(long *)(lVar9 + 0x50);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          puVar19 = (undefined8 *)&mm_Method_UnityEngine_GameObject_GetComponent<FinishArena>__;
        }
      }
      else {
code_r0x01331640:
        lVar9 = *(long *)(lVar9 + 0x50);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        puVar19 = (undefined8 *)&mm_Method_UnityEngine_GameObject_GetComponent<FinishScenario>__;
      }
      plVar15 = (long *)UnityEngine_GameObject__GetComponent<object>(lVar9,*puVar19);
      if (plVar15 == (long *)0x0) {
        func_0x010eec64(0);
      }
      (**(code **)(*plVar15 + 0x350))(plVar15,*(undefined8 *)(*plVar15 + 0x358));
      lVar9 = MyGuildData__get_instance(0,0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      lVar9 = MyGuildData__get_missions(lVar9,0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      GuildMissions__Flush(lVar9,0);
      if (iVar18 != 0) {
        PopupFinish__CheckAchievementNCommonMission();
      }
      if (((*(byte *)(_class_Singleton<CommonMissionsDistribution>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<CommonMissionsDistribution>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<CommonMissionsDistribution>_get_instance__);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      CommonMissionsDistribution__CheckMissions(lVar9,0);
      if (iVar18 != 0) {
        if (((*(byte *)(_class_PluginManager_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_PluginManager_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        PluginManager__SteamAchievementAdvance(0,0,1,0,0);
      }
      uVar8 = SoundsLib__GetEndPopupSoundEffect(0,iVar18,0);
      if (((*(byte *)(_class_SoundManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SoundManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_SoundManager_TypeInfo);
      }
      SoundManager__PlaySE(0,0x7f7fffff,0x7f7fffff,0,uVar8,0,0);
      if ((bRam00000000049fdbe9 & 1) == 0) {
        func_0x010c65d4(0xfe0e,0);
        bRam00000000049fdbe9 = 1;
      }
      if (((*(byte *)(_class_Singleton<UserOptions>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<UserOptions>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<UserOptions>_get_instance__);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = UserOptions__get_vibration(lVar9,0);
      if ((uVar10 & 1) != 0) {
        UNRECOVERED_JUMPTABLE_00 = pcRam0000000004a05990;
        if ((pcRam0000000004a05990 == (code *)0x0) &&
           (UNRECOVERED_JUMPTABLE_00 = (code *)func_0x0111e49c(&UNK_039ac914,0),
           UNRECOVERED_JUMPTABLE_00 == (code *)0x0)) {
          uVar8 = func_0x010f000c(&UNK_039ac914);
          func_0x010eebec(uVar8,0,0);
        }
        pcRam0000000004a05990 = UNRECOVERED_JUMPTABLE_00;
                    /* WARNING: Could not recover jumptable at 0x02b41a34. Too many branches */
                    /* WARNING: Treating indirect jump as call */
        (*pcRam0000000004a05990)();
        return;
      }
      return;
    case 0x10:
      if ((bRam00000000049f7b3f & 1) == 0) {
        func_0x010c65d4(0x8a9b);
        bRam00000000049f7b3f = 1;
      }
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                        (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1)
           != 0) &&
         (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo);
      }
      auVar22 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,1,0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      *(undefined1 (*) [12])(lVar9 + 0x128) = auVar22;
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = Multiplay_MultiplayManager__get_isWatchingBattle();
      if ((uVar10 & 1) != 0) {
        lVar9 = *(long *)(param_1 + 0x48);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        lVar9 = BattlePlay_BattlePlayData__get_host(lVar9,0);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        *(undefined4 *)(lVar9 + 0x40) = 2;
        lVar9 = *(long *)(param_1 + 0x48);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        lVar9 = BattlePlay_BattlePlayData__get_opponent(lVar9,0);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        *(undefined4 *)(lVar9 + 0x40) = 3;
        uVar8 = _mm_Method_Multiplay_MultiplayManager_GoToCompetitionLobby__;
        uVar12 = func_0x010fdda8(_class_System_Action_TypeInfo);
        System_Action___ctor(uVar12,param_1,uVar8,0);
        goto Multiplay_MultiplayManager__ShowBattleEndPopupOnWatch;
      }
      Multiplay_MultiplayManager__ShowCompetitionFinish(uVar10,1);
      lVar9 = ___L(0,_str_전투_승리,0);
      lVar11 = ___L(0,_str_상대방이_전투를_포기하였습니다_,0);
      uVar8 = _mm_Method_Multiplay_MultiplayManager_OnConfirmPopup__;
      uVar12 = func_0x010fdda8(_class_System_Action_TypeInfo);
      System_Action___ctor(uVar12,param_1,uVar8,0);
      break;
    case 0x11:
      if ((bRam00000000049f7b40 & 1) == 0) {
        func_0x010c65d4(0x8a9a);
        bRam00000000049f7b40 = 1;
      }
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                        (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1)
           != 0) &&
         (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo);
      }
      auVar22 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,1,0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      *(undefined1 (*) [12])(lVar9 + 0x128) = auVar22;
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = Multiplay_MultiplayManager__get_isWatchingBattle();
      if ((uVar10 & 1) != 0) {
        lVar9 = *(long *)(param_1 + 0x48);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        lVar9 = BattlePlay_BattlePlayData__get_host(lVar9,0);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        *(undefined4 *)(lVar9 + 0x40) = 2;
        lVar9 = *(long *)(param_1 + 0x48);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        lVar9 = BattlePlay_BattlePlayData__get_opponent(lVar9,0);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        *(undefined4 *)(lVar9 + 0x40) = 3;
        uVar8 = _mm_Method_Multiplay_MultiplayManager_GoToCompetitionLobby__;
        uVar12 = func_0x010fdda8(_class_System_Action_TypeInfo);
        System_Action___ctor(uVar12,param_1,uVar8,0);
        goto Multiplay_MultiplayManager__ShowBattleEndPopupOnWatch;
      }
      Multiplay_MultiplayManager__ShowCompetitionFinish(uVar10,1);
      lVar9 = ___L(0,_str_전투_승리,0);
      lVar11 = ___L(0,_str_상대방의_연결이_끊어졌습니다_,0);
      uVar8 = _mm_Method_Multiplay_MultiplayManager_OnConfirmPopup__;
      uVar12 = func_0x010fdda8(_class_System_Action_TypeInfo);
      System_Action___ctor(uVar12,param_1,uVar8,0);
      break;
    default:
      goto code_r0x013ace50;
    }
    break;
  default:
    return;
  }
PanelPopup1BtnConfirm__ShowPopup:
  if ((bRam00000000049ff059 & 1) == 0) {
    func_0x010c65d4(0x96f9);
    bRam00000000049ff059 = 1;
  }
  lVar13 = func_0x010fdda8(_class_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_TypeInfo);
  System_Object___ctor(lVar13,0);
  if (lVar13 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar13 + 0x10) = uVar12;
  if (((*(byte *)(_class_PrefabsLib_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_PrefabsLib_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar8 = PrefabsLib__get_m_common_goods_popupBtn2(0,0);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar12 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar14 = Util__InstantiateOnUIRoot(0,uVar8,0,uVar12,0,0);
  if (lVar14 == 0) {
    func_0x010eec64(0);
  }
  plVar15 = (long *)UnityEngine_GameObject__AddComponent<object>
                              (lVar14,
                               _mm_Method_UnityEngine_GameObject_AddComponent<PanelPopup1BtnConfirm>__
                              );
  if (plVar15 == (long *)0x0) {
    func_0x010eec64(0);
    CaocaoUIModalPopup__set_parent(0,0,0);
    func_0x010eec64(0);
    plVar16 = (long *)0x0;
  }
  else {
    CaocaoUIModalPopup__set_parent(plVar15,0,0);
    plVar16 = plVar15;
  }
  CaocaoUIModalPopup__set_IgnoreTappingOutSide(plVar16,1,0);
  lVar17 = func_0x01101ad4(_class_object___TypeInfo,0);
  if (plVar15 == (long *)0x0) {
    func_0x010eec64(0);
  }
  plVar15[0x1d] = lVar9;
  plVar15[0x1f] = lVar11;
  plVar15[0x20] = lVar17;
  uVar8 = _mm_Method_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_<>m__0__;
  uVar12 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
  System_Func<PanelPopupBase_PopupState>___ctor
            (uVar12,lVar13,uVar8,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
  if (plVar15 == (long *)0x0) {
    func_0x010eec64(0);
    PanelPopup2Buttons__add_OnConfirm(0,uVar12);
    func_0x010eec64(0);
  }
  else {
    PanelPopup2Buttons__add_OnConfirm(plVar15,uVar12);
  }
  (**(code **)(*plVar15 + 0x2c0))(plVar15,*(undefined8 *)(*plVar15 + 0x2c8));
  if ((bRam00000000049f7606 & 1) == 0) {
    func_0x010c65d4(0xf91f,lVar14,0);
    bRam00000000049f7606 = 1;
  }
  if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar10 = LoadingUI__IsExistInstance(0,0);
  if ((uVar10 & 1) == 0) {
    return;
  }
  if (lVar14 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = UnityEngine_GameObject__get_transform(lVar14,0);
  if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_LoadingUI_TypeInfo);
  }
  uVar3 = LoadingUI__get_layer(0,0);
  LayerUtil__ChangeLayersRecursively(0,uVar8,uVar3,0);
  if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar9 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = *(undefined8 *)(lVar9 + 0x30);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar12 = _mm_Method_Util_OnTransitionEnded__;
  if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) == 0) {
    uVar7 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar7,0,uVar12,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) = uVar7;
  }
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  plVar15 = (long *)System_Delegate__Remove
                              (0,uVar8,*(undefined8 *)
                                        (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388),0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  if (plVar15 == (long *)0x0) {
code_r0x012ffbe0:
    plVar15 = (long *)0x0;
  }
  else if (*plVar15 != _class_System_Action_TypeInfo) {
    func_0x0111ef28(&uStack_68,*(undefined8 *)(*plVar15 + 0x40));
    uVar8 = func_0x010ef81c(uStack_68);
    func_0x010eebec(uVar8,0,0);
    func_0x036457e0(&uStack_68);
    goto code_r0x012ffbe0;
  }
  *(long **)(lVar9 + 0x30) = plVar15;
  if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar9 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = _mm_Method_Util_OnTransitionEnded__;
  uVar12 = *(undefined8 *)(lVar9 + 0x30);
  if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) == 0) {
    uVar7 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar7,0,uVar8,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) = uVar7;
  }
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  plVar15 = (long *)System_Delegate__Combine
                              (0,uVar12,*(undefined8 *)
                                         (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390),0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  if (plVar15 != (long *)0x0) {
    if (*plVar15 == _class_System_Action_TypeInfo) goto code_r0x012ffd00;
    func_0x0111ef28(&uStack_70,*(undefined8 *)(*plVar15 + 0x40));
    uVar8 = func_0x010ef81c(uStack_70);
    func_0x010eebec(uVar8,0,0);
    func_0x036457e0(&uStack_70);
  }
  plVar15 = (long *)0x0;
code_r0x012ffd00:
  *(long **)(lVar9 + 0x30) = plVar15;
  *(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x68) = lVar14;
  return;
}


