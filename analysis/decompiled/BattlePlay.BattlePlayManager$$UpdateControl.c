// name: BattlePlay.BattlePlayManager$$UpdateControl
// entry: 01977b0c

/* WARNING: Removing unreachable block (ram,0x01895e24) */
/* WARNING: Removing unreachable block (ram,0x01895e2c) */
/* WARNING: Removing unreachable block (ram,0x01895e34) */
/* WARNING: Removing unreachable block (ram,0x0215b9bc) */
/* WARNING: Removing unreachable block (ram,0x0215b9d4) */
/* WARNING: Removing unreachable block (ram,0x0215b9e8) */
/* WARNING: Removing unreachable block (ram,0x0215b9f0) */
/* WARNING: Removing unreachable block (ram,0x0215b9f4) */
/* WARNING: Removing unreachable block (ram,0x0215ba08) */
/* WARNING: Removing unreachable block (ram,0x0215ba0c) */
/* WARNING: Removing unreachable block (ram,0x0215ba10) */
/* WARNING: Removing unreachable block (ram,0x0215ba1c) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__UpdateControl(long param_1)

{
  char cVar1;
  uint uVar2;
  int iVar3;
  int iVar4;
  long *plVar5;
  long *plVar6;
  undefined8 uVar7;
  long lVar8;
  long lVar9;
  undefined8 uVar10;
  ulong uVar11;
  undefined8 uVar12;
  code *pcVar13;
  ushort uVar14;
  undefined8 *puVar15;
  long lVar16;
  long lVar17;
  float fVar18;
  float fVar19;
  undefined4 uVar20;
  undefined1 auVar21 [12];
  
  if ((bRam00000000049fae11 & 1) == 0) {
    func_0x010c65d4(0x2072);
    bRam00000000049fae11 = 1;
  }
  if ((*(long *)(param_1 + 0x30) == 0) || (*(int *)(*(long *)(param_1 + 0x30) + 0x18) == 0x11)) {
    lVar16 = *(long *)(param_1 + 0x18);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    uVar10 = BattlePlay_BattlePlayData__get_battleControl(lVar16);
    uVar11 = BattlePlay_BattlePlayManager__CheckControllingUnit(param_1,uVar10);
    if ((uVar11 & 1) != 0) {
      *(undefined8 *)(param_1 + 0x30) = uVar10;
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar2 = Multiplay_MultiplayManager__get_isContinuedBattle(0,0);
      if ((uVar2 & 1) == 0) {
        if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar11 = Multiplay_MultiplayManager__get_isLoadingBattleForWatching(0,0);
        if ((uVar11 & 1) == 0) goto code_r0x01977d68;
      }
      if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar11 = LoadingUI__IsExistInstance(0,0);
      if ((uVar11 & 1) != 0) {
        if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar16 = **(long **)(_class_LoadingUI_TypeInfo + 0xb8);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        lVar16 = *(long *)(lVar16 + 0x18);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        fVar19 = (float)UIBasicSprite__get_fillAmount(lVar16,0);
        if (fVar19 < 0.68) {
          return;
        }
        if (*(long *)(param_1 + 0x30) == 0) {
          BattlePlay_BattlePlayManager__SetFinishLoading(param_1,1);
        }
        else {
          uVar20 = *(undefined4 *)(param_1 + 0x48);
          iVar4 = *(int *)(*(long *)(param_1 + 0x30) + 0x1c);
          lVar16 = *(long *)(param_1 + 0x18);
          if (lVar16 == 0) {
            func_0x010eec64(0);
          }
          iVar3 = BattlePlay_BattlePlayData__get_controlIdx(lVar16);
          if (((*(byte *)(_class_UnityEngine_Mathf_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_UnityEngine_Mathf_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c(_class_UnityEngine_Mathf_TypeInfo);
          }
          fVar19 = (float)UnityEngine_Mathf__Max(0x3f800000,(float)(iVar3 + -1),0,0);
          fVar19 = (float)UnityEngine_Mathf__Max(uVar20,((float)iVar4 * 0.32) / fVar19,0,0);
          *(float *)(param_1 + 0x48) = fVar19;
          if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
            fVar19 = *(float *)(param_1 + 0x48);
          }
          lVar16 = **(long **)(_class_LoadingUI_TypeInfo + 0xb8);
          if (lVar16 == 0) {
            func_0x010eec64(0);
          }
          LoadingUI__SetLoadingPercentage(fVar19 + 0.68,lVar16,0,0);
        }
      }
    }
  }
code_r0x01977d68:
  plVar5 = (long *)(param_1 + 0x18);
  lVar16 = *plVar5;
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  lVar16 = BattlePlay_BattlePlayData__get_battleControl(lVar16);
  if (lVar16 == 0) {
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar11 = Multiplay_MultiplayManager__get_isContinuedBattle(0,0);
    if ((uVar11 & 1) == 0) {
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar2 = Multiplay_MultiplayManager__get_isLoadingBattleForWatching(0,0);
      if ((uVar2 & 1) == 0) {
code_r0x01978258:
        if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_Multiplay_MultiplayManager_TypeInfo);
        }
        uVar11 = Multiplay_MultiplayManager__get_isReplayBattle(0,0);
        if ((uVar11 & 1) != 0) {
          if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                             (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
          if (lVar16 == 0) {
            func_0x010eec64(0);
          }
          uVar10 = *(undefined8 *)(lVar16 + 0x128);
          uVar20 = *(undefined4 *)(lVar16 + 0x130);
          if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) &
               1) != 0) &&
             (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0))
          {
            func_0x010da55c();
          }
          uVar2 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar10,uVar20,0);
          if ((uVar2 & 1) == 0) {
            if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                               (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
            if (lVar16 == 0) {
              func_0x010eec64(0);
            }
            uVar2 = ControlFlow__get_isControllingUnitInProgress(lVar16,0);
            if ((uVar2 & 1) == 0) {
              if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c();
              }
              lVar16 = Singleton<iTweenManager>__get_instance
                                 (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
              if (lVar16 == 0) {
                func_0x010eec64(0);
              }
              Multiplay_MultiplayManager__GoToStep(lVar16,7,0);
              uVar10 = BattlePlay_BattlePlayManager__ShowReplayBattleEndPopup(param_1);
              goto code_r0x019783ac;
            }
          }
        }
      }
      else {
        if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar16 = Singleton<iTweenManager>__get_instance
                           (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        uVar2 = Multiplay_MultiplayManager__IsStepComplete(lVar16,6,0);
        if ((uVar2 & 1) == 0) {
          if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c(_class_Multiplay_MultiplayManager_TypeInfo);
          }
          if (*(char *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x20) == '\0'
             ) goto code_r0x01978258;
        }
        if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_Multiplay_MultiplayManager_TypeInfo);
        }
        *(undefined1 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x11) = 0;
        if (((*(byte *)(_class_SceneSingleton<TimeController>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<TimeController>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                           (0,_mm_Method_SceneSingleton<TimeController>_get_instance__);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        TimeController__SetSpeed(lVar16,3,0);
        BattlePlay_BattlePlayManager__SetFinishLoading(param_1,0);
        if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar16 = Singleton<iTweenManager>__get_instance
                           (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        Multiplay_MultiplayManager__JoinMyWatchingHostVoiceChatRoom(lVar16,0);
        if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar16 = Singleton<iTweenManager>__get_instance
                           (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        CaocaoLoadIndicator__DestroyLoadingUI(lVar16,0,0);
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                           (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        uVar10 = *(undefined8 *)(lVar16 + 0x128);
        uVar20 = *(undefined4 *)(lVar16 + 0x130);
        if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1)
             != 0) &&
           (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar2 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar10,uVar20,0);
        if ((uVar2 & 1) == 0) {
          if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c(_class_Multiplay_MultiplayManager_TypeInfo);
          }
          if (*(char *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x20) == '\0'
             ) {
            if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            lVar16 = Singleton<iTweenManager>__get_instance
                               (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
            if (lVar16 == 0) {
              func_0x010eec64(0);
            }
            Multiplay_MultiplayManager__RequestTurnInfoOnWatch(lVar16,0);
            goto code_r0x019783bc;
          }
        }
        if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_Multiplay_MultiplayManager_TypeInfo);
        }
        *(undefined1 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x20) = 0;
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                           (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1)
             != 0) &&
           (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo);
        }
        auVar21 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,1,0);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        *(undefined1 (*) [12])(lVar16 + 0x128) = auVar21;
        uVar10 = BattlePlay_BattlePlayManager__FinishLoadingAndLeaveWatch(param_1);
code_r0x019783ac:
        UnityEngine_MonoBehaviour__StartCoroutine(param_1,uVar10,0);
      }
    }
    else {
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = *(undefined8 *)(lVar16 + 0x128);
      uVar20 = *(undefined4 *)(lVar16 + 0x130);
      if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1)
           != 0) &&
         (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar2 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar10,uVar20,0);
      if ((uVar2 & 1) == 0) {
        lVar16 = *plVar5;
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        cVar1 = *(char *)(lVar16 + 0x30);
        if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        Multiplay_MultiplayManager__InitMode(0,0,cVar1 != '\0',0);
        if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar16 = Singleton<iTweenManager>__get_instance
                           (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        Multiplay_MultiplayManager__ContinueBattle(lVar16,0);
      }
      BattlePlay_BattlePlayManager__SetFinishLoading(param_1,0);
      if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar16 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      CaocaoLoadIndicator__DestroyLoadingUI(lVar16,0,0);
    }
  }
code_r0x019783bc:
  if (*(long *)(param_1 + 0x30) == 0) {
    return;
  }
  if (*(int *)(*(long *)(param_1 + 0x30) + 0x18) == 0x11) {
    return;
  }
  uVar2 = BattlePlay_BattlePlayManager__CheckStatus(param_1);
  if ((uVar2 & 1) == 0) {
    if ((bRam00000000049fae16 & 1) == 0) {
      func_0x010c65d4(0x205e);
      bRam00000000049fae16 = 1;
    }
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    uVar10 = *(undefined8 *)(lVar16 + 0x128);
    uVar20 = *(undefined4 *)(lVar16 + 0x130);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) !=
         0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0
               )) {
      func_0x010da55c();
    }
    uVar2 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar10,uVar20,0);
    if ((uVar2 & 1) == 0) {
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      iVar4 = ControlFlow__get_remainedSeconds(lVar16,0);
      if (0 < iVar4) {
code_r0x0197abf8:
        uVar20 = UnityEngine_Time__get_realtimeSinceStartup(0,0);
        *(undefined4 *)(param_1 + 0x40) = uVar20;
        return;
      }
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      iVar4 = ControlFlow__get_controlStatus(lVar16,0);
      if (iVar4 == 0xf) goto code_r0x0197abf8;
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      iVar4 = ControlFlow__get_controlStatus(lVar16,0);
      if (iVar4 == 1) goto code_r0x0197abf8;
    }
    fVar19 = (float)UnityEngine_Time__get_realtimeSinceStartup(0,0);
    if (fVar19 - *(float *)(param_1 + 0x40) < 5.0) {
      return;
    }
    uVar20 = UnityEngine_Time__get_realtimeSinceStartup(0,0);
    *(undefined4 *)(param_1 + 0x40) = uVar20;
    if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = Singleton<iTweenManager>__get_instance
                       (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    uVar11 = CaocaoLoadIndicator__get_isLoading(lVar16,0);
    if ((uVar11 & 1) != 0) {
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar11 = Multiplay_MultiplayManager__get_isWatchingBattle(0,0);
      if ((uVar11 & 1) != 0) {
        if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        *(undefined1 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x11) = 0;
        if (((*(byte *)(_class_SceneSingleton<TimeController>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<TimeController>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                           (0,_mm_Method_SceneSingleton<TimeController>_get_instance__);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        TimeController__SetSpeed(lVar16,3,0);
        if (*(char *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x20) != '\0')
        {
          if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar16 = Singleton<iTweenManager>__get_instance
                             (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
          if (lVar16 == 0) {
            func_0x010eec64(0);
          }
          Multiplay_MultiplayManager__OnWatchFinishedPostActionOnLoading(lVar16,0);
        }
      }
      if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar16 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
      uVar10 = _mm_Method_BattlePlay_BattlePlayManager_<CheckBattleStateOnTimeOver>m__0__;
      uVar12 = func_0x010fdda8(_class_System_Action_TypeInfo);
      System_Action___ctor(uVar12,param_1,uVar10,0);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      CaocaoLoadIndicator__DestroyLoadingUI(lVar16,uVar12,0);
      if ((bRam00000000049fae1a & 1) == 0) {
        func_0x010c65d4(0x206f);
        bRam00000000049fae1a = 1;
      }
      *(undefined4 *)(param_1 + 0x48) = 0x3ea3d70a;
      if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar11 = LoadingUI__IsExistInstance(0,0);
      if ((uVar11 & 1) == 0) {
        return;
      }
      if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      fVar19 = *(float *)(param_1 + 0x48);
      lVar16 = **(long **)(_class_LoadingUI_TypeInfo + 0xb8);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      fVar19 = fVar19 + 0.68;
      if ((bRam00000000049fa4f1 & 1) == 0) {
        func_0x010c65d4(0x84a2,0,0);
        bRam00000000049fa4f1 = 1;
      }
      if (fVar19 == 0.0) {
        lVar17 = *(long *)(lVar16 + 0x18);
        if (lVar17 == 0) {
          func_0x010eec64(0);
        }
        UIBasicSprite__set_fillAmount(0,lVar17,0);
        lVar16 = *(long *)(lVar16 + 0x30);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        uVar10 = _str_0_;
        if ((bRam00000000049ff6b6 & 1) == 0) {
          func_0x010c65d4(0xe6fe,_str_0_,0);
          bRam00000000049ff6b6 = 1;
        }
        lVar17 = *(long *)(lVar16 + 0x278);
        *(undefined1 *)(lVar16 + 0x29a) = 0;
        if (lVar17 == 0) {
          func_0x010eec64(0);
        }
        System_Collections_Generic_List<tk2dUICamera>__Clear
                  (lVar17,
                   _mm_Method_System_Collections_Generic_List<UILabelLocalized_AppendCtx>_Clear__);
        *(undefined1 *)(lVar16 + 0x288) = 1;
        *(undefined8 *)(lVar16 + 0x268) = uVar10;
        *(undefined8 *)(lVar16 + 0x270) = 0;
        *(undefined1 *)(lVar16 + 0x299) = 0;
        return;
      }
      lVar17 = *(long *)(lVar16 + 0x38);
      lVar16 = *(long *)(lVar16 + 0x18);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      fVar18 = (float)UIBasicSprite__get_fillAmount(lVar16,0);
      if (((*(byte *)(_class_UnityEngine_Mathf_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Mathf_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      iVar4 = UnityEngine_Mathf__CeilToInt(fVar18 * 100.0,0,0);
      iVar3 = UnityEngine_Mathf__CeilToInt(fVar19 * 100.0,0,0);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam00000000049fd7cf & 1) == 0) {
        func_0x010c65d4(0x5db8);
        bRam00000000049fd7cf = 1;
      }
      *(undefined8 *)(lVar17 + 0x38) = 0;
      *(int *)(lVar17 + 0x40) = iVar3;
      *(long *)(lVar17 + 0x20) = lVar16;
      *(float *)(lVar17 + 0x28) = (float)iVar4 / 100.0;
      *(float *)(lVar17 + 0x2c) = (float)iVar3 / 100.0;
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar2 = UnityEngine_Object__op_Equality(0,lVar16,0,0);
      if ((uVar2 & 1) != 0) {
        return;
      }
      uVar10 = UnityEngine_Component__get_gameObject(lVar17,0);
      uVar12 = *(undefined8 *)(lVar17 + 0x18);
      if (((*(byte *)(_class__iTween_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class__iTween_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class__iTween_TypeInfo);
      }
      _iTween__StopByName(0,uVar10,uVar12,0,0);
      if (((*(byte *)(_class_itDic_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_itDic_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar16 = itDic__Allocate(0,0);
      uVar10 = *(undefined8 *)(lVar17 + 0x18);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      itDic__Set<itDic>(lVar16,_str_name,uVar10,_mm_Method_itDic_Set<string>__);
      uVar20 = *(undefined4 *)(lVar17 + 0x28);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      itDic__Set<float>(uVar20,lVar16,_str_from,_mm_Method_itDic_Set<float>__);
      uVar20 = *(undefined4 *)(lVar17 + 0x2c);
      if (lVar16 == 0) {
        func_0x010eec64(0);
        itDic__Set<float>(uVar20,0,_str_to,_mm_Method_itDic_Set<float>__);
        func_0x010eec64(0);
        itDic__Set<float>(0x3e800000,0,_str_time,_mm_Method_itDic_Set<float>__);
        func_0x010eec64(0);
      }
      else {
        itDic__Set<float>(uVar20,lVar16,_str_to,_mm_Method_itDic_Set<float>__);
        itDic__Set<float>(0x3e800000,lVar16,_str_time,_mm_Method_itDic_Set<float>__);
      }
      itDic__Set<itDic>(lVar16,_str_easetype,_str_easeInSine,_mm_Method_itDic_Set<string>__);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      itDic__Set<itDic>(lVar16,_str_onupdate,_str_OnGuageChangeUpdated,
                        _mm_Method_itDic_Set<string>__);
      uVar10 = UnityEngine_Component__get_gameObject(lVar17,0);
      if (lVar16 == 0) {
        func_0x010eec64(0);
        itDic__Set<itDic>(0,_str_onupdatetarget,uVar10,_mm_Method_itDic_Set<GameObject>__);
        func_0x010eec64(0);
      }
      else {
        itDic__Set<itDic>(lVar16,_str_onupdatetarget,uVar10,_mm_Method_itDic_Set<GameObject>__);
      }
      itDic__Set<bool>(lVar16,_str_ignoretimescale,1,_mm_Method_itDic_Set<bool>__);
      uVar10 = UnityEngine_Component__get_gameObject(lVar17,0);
      _iTween__ValueTo(0,uVar10,lVar16,0);
      uVar10 = *(undefined8 *)(lVar17 + 0x38);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar11 = UnityEngine_Object__op_Inequality(0,uVar10,0,0);
      if ((uVar11 & 1) != 0) {
        if (((*(byte *)(_class_itDic_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_itDic_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar16 = itDic__Allocate(0,0);
        uVar10 = *(undefined8 *)(lVar17 + 0x30);
        if (lVar16 == 0) {
          func_0x010eec64(0);
          itDic__Set<itDic>(0,_str_name,uVar10,_mm_Method_itDic_Set<string>__);
          func_0x010eec64(0);
          itDic__Set<int>(0,_str_from,iVar4,_mm_Method_itDic_Set<int>__);
          func_0x010eec64(0);
          itDic__Set<int>(0,_str_to,iVar3,_mm_Method_itDic_Set<int>__);
          func_0x010eec64(0);
          itDic__Set<float>(0x3e800000,0,_str_time,_mm_Method_itDic_Set<float>__);
          func_0x010eec64(0);
        }
        else {
          itDic__Set<itDic>(lVar16,_str_name,uVar10,_mm_Method_itDic_Set<string>__);
          itDic__Set<int>(lVar16,_str_from,iVar4,_mm_Method_itDic_Set<int>__);
          itDic__Set<int>(lVar16,_str_to,iVar3,_mm_Method_itDic_Set<int>__);
          itDic__Set<float>(0x3e800000,lVar16,_str_time,_mm_Method_itDic_Set<float>__);
        }
        itDic__Set<itDic>(lVar16,_str_easetype,_str_easeOutQuart,_mm_Method_itDic_Set<string>__);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        itDic__Set<itDic>(lVar16,_str_onupdate,_str_OnLabelChangeUpdated,
                          _mm_Method_itDic_Set<string>__);
        uVar10 = UnityEngine_Component__get_gameObject(lVar17,0);
        if (lVar16 == 0) {
          func_0x010eec64(0);
          itDic__Set<itDic>(0,_str_onupdatetarget,uVar10,_mm_Method_itDic_Set<GameObject>__);
          func_0x010eec64(0);
        }
        else {
          itDic__Set<itDic>(lVar16,_str_onupdatetarget,uVar10,_mm_Method_itDic_Set<GameObject>__);
        }
        itDic__Set<itDic>(lVar16,_str_oncomplete,_str_OnLabelAnimCompleted,
                          _mm_Method_itDic_Set<string>__);
        uVar10 = UnityEngine_Component__get_gameObject(lVar17,0);
        if (lVar16 == 0) {
          func_0x010eec64(0);
          itDic__Set<itDic>(0,_str_oncompletetarget,uVar10,_mm_Method_itDic_Set<GameObject>__);
          func_0x010eec64(0);
        }
        else {
          itDic__Set<itDic>(lVar16,_str_oncompletetarget,uVar10,_mm_Method_itDic_Set<GameObject>__);
        }
        itDic__Set<bool>(lVar16,_str_ignoretimescale,1,_mm_Method_itDic_Set<bool>__);
        lVar17 = UnityEngine_Component__get_gameObject(lVar17,0);
        if (((*(byte *)(_class__iTween_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class__iTween_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class__iTween_TypeInfo);
        }
        if ((bRam00000000049fa218 & 1) == 0) {
          func_0x010c65d4(0x10e05,lVar17,lVar16,0);
          bRam00000000049fa218 = 1;
        }
        if (((*(byte *)(_class__iTween_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class__iTween_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        if (lVar16 == 0) {
          func_0x010eec64(0);
          itDic__CleanArgs(0,0);
          func_0x010eec64(0);
        }
        else {
          itDic__CleanArgs(lVar16,0);
        }
        lVar8 = itDic__get_callbacks(lVar16,0);
        if (lVar8 == 0) {
          if (lVar16 == 0) {
            func_0x010eec64(0);
          }
          uVar11 = itDic__Contains(lVar16,_str_onupdate,0);
          if ((uVar11 & 1) == 0) {
            return;
          }
        }
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        uVar11 = itDic__Contains(lVar16,_str_from,0);
        if ((uVar11 & 1) != 0) {
          if (lVar16 == 0) {
            func_0x010eec64(0);
          }
          uVar11 = itDic__Contains(lVar16,_str_to,0);
          if ((uVar11 & 1) != 0) {
            if (lVar16 == 0) {
              func_0x010eec64(0);
              itDic__Set<itDic>(0,_str_type,_str_value,_mm_Method_itDic_Set<string>__);
              func_0x010eec64(0);
            }
            else {
              itDic__Set<itDic>(lVar16,_str_type,_str_value,_mm_Method_itDic_Set<string>__);
            }
            lVar8 = itDic__GetType(lVar16,_str_from,0);
            uVar10 = _class_UnityEngine_Vector2_var;
            if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c(_class_System_Type_TypeInfo);
            }
            lVar9 = System_Type__GetTypeFromHandle(0,uVar10,0);
            if (lVar16 == 0) {
              func_0x010eec64(0);
            }
            if (lVar8 == lVar9) {
              puVar15 = (undefined8 *)&str_vector2;
            }
            else {
              lVar8 = itDic__GetType(lVar16,_str_from,0);
              uVar10 = _class_UnityEngine_Vector3_var;
              if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c(_class_System_Type_TypeInfo);
              }
              lVar9 = System_Type__GetTypeFromHandle(0,uVar10,0);
              if (lVar16 == 0) {
                func_0x010eec64(0);
              }
              if (lVar8 == lVar9) {
                puVar15 = (undefined8 *)&str_vector3;
              }
              else {
                lVar8 = itDic__GetType(lVar16,_str_from,0);
                uVar10 = _class_UnityEngine_Rect_var;
                if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
                   (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
                  func_0x010da55c(_class_System_Type_TypeInfo);
                }
                lVar9 = System_Type__GetTypeFromHandle(0,uVar10,0);
                if (lVar16 == 0) {
                  func_0x010eec64(0);
                }
                if (lVar8 == lVar9) {
                  puVar15 = (undefined8 *)&str_rect;
                }
                else {
                  lVar8 = itDic__GetType(lVar16,_str_from,0);
                  uVar10 = _class_float_var;
                  if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
                     (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
                    func_0x010da55c(_class_System_Type_TypeInfo);
                  }
                  lVar9 = System_Type__GetTypeFromHandle(0,uVar10,0);
                  if (lVar16 == 0) {
                    func_0x010eec64(0);
                  }
                  if (lVar8 == lVar9) {
                    puVar15 = (undefined8 *)&str_float;
                  }
                  else {
                    lVar8 = itDic__GetType(lVar16,_str_from,0);
                    uVar10 = _class_UnityEngine_Color_var;
                    if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
                       (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
                      func_0x010da55c(_class_System_Type_TypeInfo);
                    }
                    lVar9 = System_Type__GetTypeFromHandle(0,uVar10,0);
                    if (lVar8 != lVar9) {
                      return;
                    }
                    if (lVar16 == 0) {
                      func_0x010eec64(0);
                    }
                    puVar15 = (undefined8 *)&str_color;
                  }
                }
              }
            }
            itDic__Set<itDic>(lVar16,_str_method,*puVar15,_mm_Method_itDic_Set<string>__);
            if (lVar16 == 0) {
              func_0x010eec64(0);
            }
            uVar2 = itDic__Contains(lVar16,_str_easetype,0);
            if ((uVar2 & 1) == 0) {
              if (lVar16 == 0) {
                func_0x010eec64(0);
              }
              itDic__Set<iTween_EaseType>
                        (lVar16,_str_easetype,0x15,_mm_Method_itDic_Set<iTween_EaseType>__);
            }
            if (((*(byte *)(_class__iTween_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class__iTween_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            if ((bRam00000000049fa2e0 & 1) == 0) {
              func_0x010c65d4(0x10daa);
              bRam00000000049fa2e0 = 1;
            }
            if (lVar16 == 0) {
              func_0x010eec64(0);
            }
            uVar2 = itDic__Contains(lVar16,_str_id,0);
            if ((uVar2 & 1) == 0) {
              if (((*(byte *)(_class__iTween_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class__iTween_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c();
              }
              uVar10 = _iTween__GenerateID();
              if (lVar16 == 0) {
                func_0x010eec64(0);
              }
              itDic__Set<itDic>(lVar16,_str_id,uVar10,_mm_Method_itDic_Set<string>__);
            }
            if (lVar16 == 0) {
              func_0x010eec64(0);
            }
            uVar11 = itDic__Contains(lVar16,_str_target,0);
            if ((uVar11 & 1) == 0) {
              if (lVar16 == 0) {
                func_0x010eec64(0);
              }
              itDic__Set<itDic>(lVar16,_str_target,lVar17,_mm_Method_itDic_Set<GameObject>__);
            }
            lVar8 = func_0x010fdda8(_class__iTween_TypeInfo);
            *(undefined1 *)(lVar8 + 0x18) = 1;
            *(undefined8 *)(lVar8 + 0x1c) = 0x1ffffffff;
            System_Object___ctor(lVar8,0);
            *(long *)(lVar8 + 0x80) = lVar16;
            uVar10 = func_0x010fdda8(_class_WeakReference<GameObject>_TypeInfo);
            WeakReference<GameObject>___ctor
                      (uVar10,lVar17,_mm_Method_WeakReference<GameObject>__ctor__);
            if (lVar8 == 0) {
              func_0x010eec64(0);
            }
            *(undefined8 *)(lVar8 + 0x10) = uVar10;
            if (lVar17 == 0) {
              func_0x010eec64(0);
              uVar20 = UnityEngine_Object__GetInstanceID(0,0);
              *(undefined4 *)(lVar8 + 0x1c) = uVar20;
              func_0x010eec64(0);
            }
            else {
              uVar20 = UnityEngine_Object__GetInstanceID(lVar17,0);
              *(undefined4 *)(lVar8 + 0x1c) = uVar20;
            }
            uVar10 = UnityEngine_GameObject__get_transform(lVar17,0);
            lVar17 = *(long *)(lVar8 + 0x10);
            *(undefined8 *)(lVar8 + 0x100) = uVar10;
            if (lVar17 == 0) {
              func_0x010eec64(0);
            }
            lVar17 = WeakReference<Renderer>__get_target
                               (lVar17,_mm_Method_WeakReference<GameObject>_get_target__);
            if (lVar17 == 0) {
              func_0x010eec64(0);
            }
            uVar2 = UnityEngine_GameObject__get_activeInHierarchy(lVar17,0);
            if (lVar8 == 0) {
              func_0x010eec64(0);
              uRam0000000000000020 = uVar2 & 1;
              func_0x010eec64(0);
              lRam0000000000000080 = lVar16;
              func_0x010eec64(0);
              _iTween__RetrieveArgs(0);
              func_0x010eec64(0);
            }
            else {
              *(uint *)(lVar8 + 0x20) = uVar2 & 1;
              *(long *)(lVar8 + 0x80) = lVar16;
              _iTween__RetrieveArgs(lVar8);
            }
            *(undefined4 *)(lVar8 + 0x24) = *(undefined4 *)(lVar8 + 0x50);
            uVar20 = UnityEngine_Time__get_realtimeSinceStartup(0,0);
            if (lVar8 == 0) {
              func_0x010eec64(0);
            }
            *(undefined4 *)(lVar8 + 0xf4) = uVar20;
            if (((*(byte *)(_class_Singleton<iTweenManager>_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_Singleton<iTweenManager>_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            lVar16 = Singleton<iTweenManager>__get_instance
                               (0,_mm_Method_Singleton<iTweenManager>_get_instance__);
            if (lVar16 == 0) {
              func_0x010eec64(0);
            }
            if ((bRam00000000049ffef6 & 1) == 0) {
              func_0x010c65d4(0x10e8d,lVar8,0);
              bRam00000000049ffef6 = 1;
            }
            lVar17 = *(long *)(lVar16 + 0x28);
            if (lVar17 == 0) {
              func_0x010eec64(0);
            }
            System_Collections_Generic_LinkedList<object>__AddLast
                      (lVar17,lVar8,
                       _mm_Method_System_Collections_Generic_LinkedList<_iTween>_AddLast__);
            if ((bRam00000000049ffef7 & 1) == 0) {
              func_0x010c65d4(0x10e8c);
              bRam00000000049ffef7 = 1;
            }
            if (lVar8 == 0) {
              func_0x010eec64(0);
            }
            uVar20 = *(undefined4 *)(lVar8 + 0x1c);
            lVar17 = *(long *)(lVar16 + 0x20);
            if (lVar17 == 0) {
              func_0x010eec64(0);
            }
            System_Collections_Generic_Dictionary<int,_UserUnitRelation>__TryGetValue
                      (lVar17,uVar20,&stack0xffffffffffffffc8,
                       _mm_Method_System_Collections_Generic_Dictionary<int,_List<_iTween>>_TryGetValue__
                      );
            lVar17 = *(long *)(lVar16 + 0x20);
            lVar16 = func_0x010fdda8(_class_System_Collections_Generic_List<_iTween>_TypeInfo);
            System_Collections_Generic_List<tk2dUILayoutItem>___ctor
                      (lVar16,_mm_Method_System_Collections_Generic_List<_iTween>__ctor__);
            if (lVar17 == 0) {
              func_0x010eec64(0);
            }
            System_Collections_Generic_Dictionary<int,_tk2dFontChar>__set_Item
                      (lVar17,uVar20,lVar16,
                       _mm_Method_System_Collections_Generic_Dictionary<int,_List<_iTween>>_set_Item__
                      );
            if (lVar16 == 0) {
              func_0x010eec64(0);
              lVar16 = 0;
            }
            System_Collections_Generic_List<tk2dUILayoutItem>__Add
                      (lVar16,lVar8,_mm_Method_System_Collections_Generic_List<_iTween>_Add__);
            return;
          }
        }
        return;
      }
      return;
    }
    if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = Singleton<iTweenManager>__get_instance
                       (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    if ((bRam00000000049f7afd & 1) == 0) {
      func_0x010c65d4(0x8a27,0,0);
      bRam00000000049f7afd = 1;
    }
    if (*(char *)(lVar16 + 0x141) == '\0') {
      return;
    }
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar11 = Multiplay_MultiplayManager__get_isMyActiveBattle();
    if ((uVar11 & 1) == 0) {
      return;
    }
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar17 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    uVar10 = *(undefined8 *)(lVar17 + 0x128);
    uVar20 = *(undefined4 *)(lVar17 + 0x130);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) !=
         0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0
               )) {
      func_0x010da55c();
    }
    uVar2 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar10,uVar20,0);
    if ((uVar2 & 1) != 0) {
      return;
    }
    if ((*(long *)(lVar16 + 0x40) != 0) && (*(long *)(lVar16 + 0x48) != 0)) {
      uVar10 = BattlePlay_BattlePlayData__get_battleKey(*(long *)(lVar16 + 0x48),0);
      uVar12 = *(undefined8 *)(lVar16 + 0x48);
      plVar5 = (long *)func_0x010fdda8(_class_BattlePlay_BattleState_TypeInfo);
      BattlePlay_BattleState___ctor(plVar5,uVar10,uVar12,0);
      if (plVar5 == (long *)0x0) {
        func_0x010eec64(0);
      }
      if ((plVar5[6] != 0) &&
         (iVar4 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                            (plVar5[6],
                             _mm_Method_System_Collections_Generic_List<BattleUnitState>_get_Count__
                            ), iVar4 != 0)) {
        if ((*(ushort *)(lVar16 + 100) & 0xff) == 0) {
          uVar14 = *(ushort *)(lVar16 + 100) >> 8;
        }
        else {
          iVar4 = -1;
          if ((int)plVar5[7] < 1) {
            iVar4 = 1;
          }
          *(int *)(plVar5 + 7) = (int)plVar5[7] + iVar4;
          *(undefined1 *)(lVar16 + 100) = 0;
          uVar14 = (ushort)*(byte *)(lVar16 + 0x65);
        }
        if (uVar14 != 0) {
          lVar17 = plVar5[6];
          if (lVar17 == 0) {
            func_0x010eec64(0);
          }
          iVar4 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                            (lVar17,
                             _mm_Method_System_Collections_Generic_List<BattleUnitState>_get_Count__
                            );
          if (0 < iVar4) {
            iVar3 = 0;
            do {
              if (plVar5 == (long *)0x0) {
                func_0x010eec64(0);
              }
              lVar17 = plVar5[6];
              if (lVar17 == 0) {
                func_0x010eec64(0);
              }
              lVar17 = System_Collections_Generic_List<object>__get_Item
                                 (lVar17,iVar3,
                                  _mm_Method_System_Collections_Generic_List<BattleUnitState>_get_Item__
                                 );
              if (lVar17 == 0) {
                func_0x010eec64(0);
              }
              if (0 < *(int *)(lVar17 + 0x24)) {
                if (plVar5 == (long *)0x0) {
                  func_0x010eec64(0);
                }
                lVar17 = plVar5[6];
                if (lVar17 == 0) {
                  func_0x010eec64(0);
                }
                lVar17 = System_Collections_Generic_List<object>__get_Item
                                   (lVar17,iVar3,
                                    _mm_Method_System_Collections_Generic_List<BattleUnitState>_get_Item__
                                   );
                if (lVar17 == 0) {
                  func_0x010eec64(0);
                  iVar4 = iRam0000000000000024;
                  func_0x010eec64(0);
                }
                else {
                  iVar4 = *(int *)(lVar17 + 0x24);
                }
                *(int *)(lVar17 + 0x24) = iVar4 + 10;
                break;
              }
              iVar3 = iVar3 + 1;
            } while (iVar3 < iVar4);
          }
          *(undefined1 *)(lVar16 + 0x65) = 0;
        }
        plVar6 = (long *)func_0x010fdda8(_class_LitJson_JsonWriter_TypeInfo);
        LitJson_JsonWriter___ctor(plVar6,0);
        if (plVar5 == (long *)0x0) {
          func_0x010eec64(0);
        }
        (**(code **)(*plVar5 + 0x170))(plVar5,plVar6,*(undefined8 *)(*plVar5 + 0x178));
        lVar17 = *(long *)(lVar16 + 0x48);
        if (lVar17 == 0) {
          func_0x010eec64(0);
        }
        uVar10 = BattlePlay_BattlePlayData__get_battleKey(lVar17,0);
        if (plVar6 == (long *)0x0) {
          func_0x010eec64(0);
        }
        uVar12 = (**(code **)(*plVar6 + 0x160))(plVar6,*(undefined8 *)(*plVar6 + 0x168));
        plVar5 = *(long **)(lVar16 + 0x40);
        if (plVar5 == (long *)0x0) {
          uVar20 = 8;
        }
        else {
          uVar20 = (**(code **)(*plVar5 + 0x170))(plVar5,*(undefined8 *)(*plVar5 + 0x178));
        }
        uVar7 = func_0x010fdda8(_class_<>__AnonType48<string,_string,_bool,_Step>_TypeInfo);
        <>__AnonType48<string,_string,_bool,_Step>___ctor
                  (uVar7,uVar10,uVar12,0,uVar20,
                   _mm_Method_<>__AnonType48<string,_string,_bool,_Step>__ctor__);
        if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar12 = LitJson_JsonMapper__ToJson(0,uVar7,0);
        uVar10 = _str_checkBattleState;
        goto Multiplay_MultiplayManager__EmitSocketMessage;
      }
    }
    if ((bRam00000000049f7ad5 & 1) == 0) {
      func_0x010c65d4(0x8a8a);
      bRam00000000049f7ad5 = 1;
    }
    if (*(char *)(lVar16 + 0x141) == '\0') {
      return;
    }
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = Multiplay_MultiplayManager__get_isContinuedBattle();
    if ((uVar2 & 1) != 0) {
      return;
    }
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = Multiplay_MultiplayManager__get_isWatchingBattle();
    if ((uVar2 & 1) != 0) {
      return;
    }
    plVar5 = *(long **)(lVar16 + 0x40);
    if (plVar5 == (long *)0x0) {
code_r0x013a22a0:
      uVar20 = 8;
code_r0x013a22a4:
      uVar10 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
      <>__AnonType19<Step,_bool,_bool>___ctor
                (uVar10,uVar20,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
    }
    else {
      iVar4 = (**(code **)(*plVar5 + 0x170))(plVar5,*(undefined8 *)(*plVar5 + 0x178));
      if (iVar4 < 1) {
code_r0x013a2284:
        plVar5 = *(long **)(lVar16 + 0x40);
        if (plVar5 == (long *)0x0) goto code_r0x013a22a0;
        uVar20 = (**(code **)(*plVar5 + 0x170))(plVar5,*(undefined8 *)(*plVar5 + 0x178));
        goto code_r0x013a22a4;
      }
      if (*(long *)(lVar16 + 0x40) == 0) goto code_r0x013a22a0;
      if ((*(long *)(lVar16 + 0x48) == 0) ||
         (lVar17 = BattlePlay_BattlePlayData__get_opponent(*(long *)(lVar16 + 0x48),0), lVar17 == 0)
         ) goto code_r0x013a2284;
      lVar17 = *(long *)(lVar16 + 0x48);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      uVar12 = BattlePlay_BattlePlayData__get_battleKey(lVar17,0);
      plVar5 = *(long **)(lVar16 + 0x40);
      if (plVar5 == (long *)0x0) {
        uVar20 = 8;
      }
      else {
        uVar20 = (**(code **)(*plVar5 + 0x170))(plVar5,*(undefined8 *)(*plVar5 + 0x178));
      }
      uVar10 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
      <>__AnonType18<string,_bool,_bool,_Step>___ctor
                (uVar10,uVar12,1,0,uVar20,
                 _mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__);
    }
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar12 = LitJson_JsonMapper__ToJson(0,uVar10,0);
    uVar10 = _str_cancelMatchmaking;
Multiplay_MultiplayManager__EmitSocketMessage:
    uVar11 = MyUserData__get_hasInstance(0,0);
    if ((uVar11 & 1) != 0) {
      lVar17 = MyUserData__get_instance(0,0);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      if (*(char *)(lVar17 + 0x268) != '\0') {
        lVar16 = *(long *)(lVar16 + 0x30);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        if ((bRam0000000004a00249 & 1) == 0) {
          func_0x010c65d4(0xc5d1,uVar10,uVar12,0);
          bRam0000000004a00249 = 1;
        }
        if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar10 = System_String__Format(0,_str____0____1__,uVar10,uVar12,0);
        if ((bRam0000000004a0024d & 1) == 0) {
          func_0x010c65d4(0xc5ce);
          bRam0000000004a0024d = 1;
        }
        if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar12 = LitJson_JsonMapper__ToObject(0,uVar10,0);
        lVar8 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
        uVar10 = _str__;
        System_Object___ctor(lVar8,0);
        *(undefined4 *)(lVar8 + 0x14) = 2;
        *(undefined4 *)(lVar8 + 0x18) = 0;
        *(undefined8 *)(lVar8 + 0x30) = uVar12;
        *(undefined8 *)(lVar8 + 0x20) = uVar10;
        *(undefined4 *)(lVar8 + 0x28) = 0xffffffff;
        *(undefined4 *)(lVar8 + 0x10) = 4;
        lVar17 = lVar16;
        if ((bRam0000000004a0024f & 1) == 0) {
          lVar17 = func_0x010c65d4(0xc5cf);
          bRam0000000004a0024f = 1;
        }
        lVar9 = *(long *)(lVar16 + 0x48);
        if (*(long *)(lVar16 + 0x50) == 0) {
          lVar17 = func_0x010eec64(0);
        }
        uVar10 = SocketIO_Encoder__Encode(lVar17,lVar8);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        WebSocketSharp_WebSocket__Send(lVar9,uVar10,0);
        return;
      }
    }
    uVar11 = Multiplay_MultiplayManager__get_isConnected(lVar16);
    if ((uVar11 & 1) == 0) {
      return;
    }
    if ((bRam00000000049f7acc & 1) == 0) {
      func_0x010c65d4(0x8a26);
      bRam00000000049f7acc = 1;
    }
    *(undefined8 *)(lVar16 + 0x70) = 0;
    *(undefined1 *)(lVar16 + 0x141) = 0;
    *(undefined1 *)(lVar16 + 0x142) = 0;
    uVar10 = *(undefined8 *)(lVar16 + 0x30);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar11 = UnityEngine_Object__op_Inequality(0,uVar10,0,0);
    if ((uVar11 & 1) != 0) {
      lVar17 = *(long *)(lVar16 + 0x30);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__Close(lVar17,0);
      uVar10 = *(undefined8 *)(lVar16 + 0x30);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      if ((bRam0000000004a04120 & 1) == 0) {
        func_0x010c65d4(0x906e,uVar10,0);
        bRam0000000004a04120 = 1;
      }
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      pcVar13 = pcRam0000000004a04118;
      if ((pcRam0000000004a04118 == (code *)0x0) &&
         (pcVar13 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar13 == (code *)0x0)) {
        uVar12 = func_0x010f000c(&UNK_039a20c7);
        func_0x010eebec(uVar12,0,0);
      }
      pcRam0000000004a04118 = pcVar13;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*pcRam0000000004a04118)(uVar10,0);
      return;
    }
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar11 = Multiplay_MultiplayManager__get_isReplayBattle(0,0);
  if ((uVar11 & 1) != 0) {
    lVar16 = *(long *)(param_1 + 0x30);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    if (*(int *)(lVar16 + 0x18) != 0xe) {
      lVar16 = *(long *)(param_1 + 0x30);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      BattlePlay_BattlePlayManager__ResetUnitTurnTime(param_1,*(undefined8 *)(lVar16 + 0x10),0);
    }
  }
  uVar20 = UnityEngine_Time__get_realtimeSinceStartup(0,0);
  lVar16 = *(long *)(param_1 + 0x30);
  *(undefined4 *)(param_1 + 0x40) = uVar20;
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  uVar20 = *(undefined4 *)(lVar16 + 0x20);
  if (((*(byte *)(_class_RandomGenerater_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_RandomGenerater_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  RandomGenerater__SetRandomIndex(0,uVar20,0);
  if (*(long *)(param_1 + 0x30) == 0) {
    func_0x010eec64(0);
  }
  if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                     (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  ControlFlow__StopAllUnitFlickering(lVar16,0);
  lVar16 = *(long *)(param_1 + 0x30);
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  uVar2 = *(uint *)(lVar16 + 0x18);
  if (0x10 < uVar2) goto code_r0x019787a0;
  switch(*(int *)(&UNK_03995c20 + (long)(int)uVar2 * 4)) {
  case 0x57:
    if (((*(byte *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<TroopMan>_get_instance__);
    lVar17 = *(long *)(param_1 + 0x30);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    uVar20 = *(undefined4 *)(lVar17 + 0x2c);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar8 = *plVar5;
    lVar17 = *(long *)(param_1 + 0x30);
    uVar10 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar4 = *(int *)(lVar17 + 0x28);
    if (lVar8 == 0) {
      func_0x010eec64(0);
    }
    lVar17 = *(long *)(lVar8 + 0x40);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = *(int *)(lVar17 + 0x38);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    lVar16 = TroopMan__GetUnitID2Unit(lVar16,uVar20,uVar10,(ulong)(iVar3 != iVar4) << 1,0);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
    }
    uVar2 = UnityEngine_Object__op_Equality(0,lVar16,0,0);
    if ((uVar2 & 1) != 0) {
      return;
    }
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = Multiplay_MultiplayManager__get_isContinuedBattle(0,0);
    if ((uVar2 & 1) == 0) {
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar11 = Multiplay_MultiplayManager__get_isLoadingBattleForWatching(0,0);
      if ((uVar11 & 1) != 0) goto code_r0x019786c0;
    }
    else {
code_r0x019786c0:
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      lVar17 = *(long *)(lVar16 + 0x68);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      UnitController__InitPathFinder(lVar17,0);
    }
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    lVar16 = *(long *)(lVar16 + 0x68);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    uVar10 = UnitController__SelectUnit(lVar16,0);
    UnityEngine_MonoBehaviour__StartCoroutine(param_1,uVar10,0);
    break;
  case 0x58:
    goto code_r0x019787d8;
  case 0x59:
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    ControlFlow__set_controlStatus(lVar16,5,0);
    if (((*(byte *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<TroopMan>_get_instance__);
    lVar17 = *(long *)(param_1 + 0x30);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    uVar20 = *(undefined4 *)(lVar17 + 0x2c);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar8 = *plVar5;
    lVar17 = *(long *)(param_1 + 0x30);
    uVar10 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar4 = *(int *)(lVar17 + 0x28);
    if (lVar8 == 0) {
      func_0x010eec64(0);
    }
    lVar17 = *(long *)(lVar8 + 0x40);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = *(int *)(lVar17 + 0x38);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    lVar16 = TroopMan__GetUnitID2Unit(lVar16,uVar20,uVar10,(ulong)(iVar3 != iVar4) << 1,0);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
    }
    uVar2 = UnityEngine_Object__op_Equality(0,lVar16,0,0);
    if ((uVar2 & 1) != 0) break;
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    lVar17 = *(long *)(lVar16 + 0x68);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    UnitController__HideMoveSelection(lVar17,0);
    lVar16 = *(long *)(lVar16 + 0x68);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    UnitController__ShowAttackSelection(lVar16,0);
    goto code_r0x01978c20;
  case 0x5a:
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    ControlFlow__set_controlStatus(lVar16,9,0);
    if (((*(byte *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<TroopMan>_get_instance__);
    lVar17 = *(long *)(param_1 + 0x30);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    uVar20 = *(undefined4 *)(lVar17 + 0x2c);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar8 = *plVar5;
    lVar17 = *(long *)(param_1 + 0x30);
    uVar10 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar4 = *(int *)(lVar17 + 0x28);
    if (lVar8 == 0) {
      func_0x010eec64(0);
    }
    lVar17 = *(long *)(lVar8 + 0x40);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = *(int *)(lVar17 + 0x38);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    lVar16 = TroopMan__GetUnitID2Unit(lVar16,uVar20,uVar10,(ulong)(iVar3 != iVar4) << 1,0);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
    }
    uVar2 = UnityEngine_Object__op_Equality(0,lVar16,0,0);
    if ((uVar2 & 1) != 0) break;
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    lVar17 = *(long *)(lVar16 + 0x68);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    UnitController__HideMoveSelection(lVar17,0);
    lVar16 = *(long *)(lVar16 + 0x68);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    UnitController__ShowPincerSelection(lVar16,0);
code_r0x01978c20:
    if (((*(byte *)(_class_UnitCommonFunc_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnitCommonFunc_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    UnitCommonFunc__SetColliderAllUnit(0,0,0);
    break;
  case 0x5b:
    if (((*(byte *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<TroopMan>_get_instance__);
    lVar17 = *(long *)(param_1 + 0x30);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    uVar20 = *(undefined4 *)(lVar17 + 0x2c);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar8 = *plVar5;
    lVar17 = *(long *)(param_1 + 0x30);
    uVar10 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar4 = *(int *)(lVar17 + 0x28);
    if (lVar8 == 0) {
      func_0x010eec64(0);
    }
    lVar17 = *(long *)(lVar8 + 0x40);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = *(int *)(lVar17 + 0x38);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    lVar16 = TroopMan__GetUnitID2Unit(lVar16,uVar20,uVar10,(ulong)(iVar3 != iVar4) << 1,0);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
    }
    uVar2 = UnityEngine_Object__op_Equality(0,lVar16,0,0);
    if ((uVar2 & 1) == 0) {
      if (((*(byte *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar17 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<TroopMan>_get_instance__);
      lVar8 = *(long *)(param_1 + 0x30);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      uVar20 = *(undefined4 *)(lVar8 + 0x34);
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = *plVar5;
      lVar8 = *(long *)(param_1 + 0x30);
      uVar10 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      iVar4 = *(int *)(lVar8 + 0x30);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      lVar8 = *(long *)(lVar9 + 0x40);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      iVar3 = *(int *)(lVar8 + 0x38);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      plVar5 = (long *)TroopMan__GetUnitID2Unit(lVar17,uVar20,uVar10,(ulong)(iVar3 != iVar4) << 1,0)
      ;
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
      }
      uVar2 = UnityEngine_Object__op_Equality(0,plVar5,0,0);
      if ((uVar2 & 1) == 0) {
        if (((*(byte *)(_class_SceneSingleton<GeoMan>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<GeoMan>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar17 = SceneSingleton<WorldBossBattleController>__get_instance
                           (0,_mm_Method_SceneSingleton<GeoMan>_get_instance__);
        if (plVar5 == (long *)0x0) {
          func_0x010eec64(0);
        }
        uVar10 = (**(code **)(*plVar5 + 0x2d0))(plVar5,*(undefined8 *)(*plVar5 + 0x2d8));
        if (lVar17 == 0) {
          func_0x010eec64(0);
        }
        iVar4 = GeoMan__GetTileStatus(lVar17,uVar10,0);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        lVar16 = *(long *)(lVar16 + 0x68);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        UnitController__Attack(lVar16,plVar5,1,iVar4 == 4,0);
      }
    }
    break;
  case 0x5c:
    if (((*(byte *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<TroopMan>_get_instance__);
    lVar17 = *(long *)(param_1 + 0x30);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    uVar20 = *(undefined4 *)(lVar17 + 0x2c);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar8 = *plVar5;
    lVar17 = *(long *)(param_1 + 0x30);
    uVar10 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar4 = *(int *)(lVar17 + 0x28);
    if (lVar8 == 0) {
      func_0x010eec64(0);
    }
    lVar17 = *(long *)(lVar8 + 0x40);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = *(int *)(lVar17 + 0x38);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    lVar16 = TroopMan__GetUnitID2Unit(lVar16,uVar20,uVar10,(ulong)(iVar3 != iVar4) << 1,0);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
    }
    uVar2 = UnityEngine_Object__op_Equality(0,lVar16,0,0);
    if ((uVar2 & 1) == 0) {
      if (((*(byte *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar17 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<TroopMan>_get_instance__);
      lVar8 = *(long *)(param_1 + 0x30);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      uVar20 = *(undefined4 *)(lVar8 + 0x34);
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = *plVar5;
      lVar8 = *(long *)(param_1 + 0x30);
      uVar10 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      iVar4 = *(int *)(lVar8 + 0x30);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      lVar8 = *(long *)(lVar9 + 0x40);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      iVar3 = *(int *)(lVar8 + 0x38);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = TroopMan__GetUnitID2Unit(lVar17,uVar20,uVar10,(ulong)(iVar3 != iVar4) << 1,0);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
      }
      uVar2 = UnityEngine_Object__op_Equality(0,uVar10,0,0);
      if ((uVar2 & 1) == 0) {
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        lVar16 = *(long *)(lVar16 + 0x68);
        lVar17 = *(long *)(param_1 + 0x30);
        if (lVar17 == 0) {
          func_0x010eec64(0);
        }
        uVar20 = *(undefined4 *)(lVar17 + 0x3c);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        UnitController__OnSkillAttack(lVar16,uVar10,1,uVar20,0);
      }
    }
    break;
  case 0x5d:
    if (*(long *)(param_1 + 0x28) != 0) {
      return;
    }
    if (((*(byte *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<TroopMan>_get_instance__);
    lVar17 = *(long *)(param_1 + 0x30);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    uVar20 = *(undefined4 *)(lVar17 + 0x2c);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar8 = *plVar5;
    lVar17 = *(long *)(param_1 + 0x30);
    uVar10 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar4 = *(int *)(lVar17 + 0x28);
    if (lVar8 == 0) {
      func_0x010eec64(0);
    }
    lVar17 = *(long *)(lVar8 + 0x40);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = *(int *)(lVar17 + 0x38);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    uVar10 = TroopMan__GetUnitID2Unit(lVar16,uVar20,uVar10,(ulong)(iVar3 != iVar4) << 1,0);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
    }
    uVar2 = UnityEngine_Object__op_Equality(0,uVar10,0,0);
    if ((uVar2 & 1) == 0) {
      if (((*(byte *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<TroopMan>_get_instance__);
      lVar17 = *(long *)(param_1 + 0x30);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      uVar20 = *(undefined4 *)(lVar17 + 0x34);
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar8 = *plVar5;
      lVar17 = *(long *)(param_1 + 0x30);
      uVar12 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      iVar4 = *(int *)(lVar17 + 0x30);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      lVar17 = *(long *)(lVar8 + 0x40);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      iVar3 = *(int *)(lVar17 + 0x38);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      uVar12 = TroopMan__GetUnitID2Unit(lVar16,uVar20,uVar12,(ulong)(iVar3 != iVar4) << 1,0);
      lVar16 = func_0x010fdda8(_class_nAI_Action_TypeInfo);
      nAI_Action___ctor(lVar16,0);
      lVar17 = *(long *)(param_1 + 0x30);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      iVar4 = *(int *)(lVar17 + 0x18);
      if (lVar16 == 0) {
        func_0x010eec64(0);
        nAI_Action__set_type(0,iVar4 + -6,0);
        func_0x010eec64(0);
        nAI_Action__set_actor(0,uVar10,0);
        func_0x010eec64(0);
      }
      else {
        nAI_Action__set_type(lVar16,iVar4 + -6,0);
        nAI_Action__set_actor(lVar16,uVar10,0);
      }
      nAI_Action__set_target(lVar16,uVar12,0);
      lVar17 = *(long *)(param_1 + 0x30);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      uVar20 = *(undefined4 *)(lVar17 + 0x3c);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      nAI_Action__set_skillID(lVar16,uVar20,0);
      if (((*(byte *)(_class_TileNode_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_TileNode_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar17 = TileNode__Allocate(0,0);
      if (((*(byte *)(_class_SceneSingleton<GeoMan>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<GeoMan>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_SceneSingleton<GeoMan>_TypeInfo);
      }
      lVar8 = SceneSingleton<WorldBossBattleController>__get_instance
                        (0,_mm_Method_SceneSingleton<GeoMan>_get_instance__);
      lVar9 = *(long *)(param_1 + 0x30);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      uVar20 = *(undefined4 *)(lVar9 + 0x38);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      lVar8 = GeoMan__GetTileDataByIdx(lVar8,uVar20,0);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      uVar12 = TileData__get_gridPos(lVar8,0);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      *(undefined8 *)(lVar17 + 0x24) = uVar12;
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      lVar8 = *(long *)(lVar16 + 0x38);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_List<tk2dUILayoutItem>__Add
                (lVar8,lVar17,_mm_Method_System_Collections_Generic_List<TileNode>_Add__);
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar17 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      ControlFlow__set_controlStatus(lVar17,2,0);
      lVar17 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      ControlFlow__set_controllingUnit(lVar17,uVar10,0);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = nAI_Action__ProcessPvpAI(lVar16,0);
      uVar10 = UnityEngine_MonoBehaviour__StartCoroutine(param_1,uVar10,0);
      *(undefined8 *)(param_1 + 0x28) = uVar10;
    }
    break;
  case 0x5e:
    if (((*(byte *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<TroopMan>_get_instance__);
    lVar17 = *(long *)(param_1 + 0x30);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    uVar20 = *(undefined4 *)(lVar17 + 0x2c);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar8 = *plVar5;
    lVar17 = *(long *)(param_1 + 0x30);
    uVar10 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar4 = *(int *)(lVar17 + 0x28);
    if (lVar8 == 0) {
      func_0x010eec64(0);
    }
    lVar17 = *(long *)(lVar8 + 0x40);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = *(int *)(lVar17 + 0x38);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    lVar16 = TroopMan__GetUnitID2Unit(lVar16,uVar20,uVar10,(ulong)(iVar3 != iVar4) << 1,0);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
    }
    uVar2 = UnityEngine_Object__op_Equality(0,lVar16,0,0);
    if ((uVar2 & 1) == 0) {
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = UnityEngine_Component__GetComponent<GUILayer>
                         (lVar16,_mm_Method_UnityEngine_Component_GetComponent<BattleMove>__);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
      }
      uVar2 = UnityEngine_Object__op_Equality(0,uVar10,0,0);
      if ((uVar2 & 1) == 0) {
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        lVar16 = UnityEngine_Component__GetComponent<GUILayer>
                           (lVar16,_mm_Method_UnityEngine_Component_GetComponent<BattleMove>__);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        uVar11 = Move__get_isCompleted(lVar16,0);
        if ((uVar11 & 1) == 0) break;
      }
    }
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    lVar17 = *(long *)(param_1 + 0x30);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    uVar10 = *(undefined8 *)(lVar17 + 0x44);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    ControlFlow__OnCancelAction(lVar16,uVar10,0);
    break;
  case 0x5f:
    if (((*(byte *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<TroopMan>_get_instance__);
    lVar17 = *(long *)(param_1 + 0x30);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    uVar20 = *(undefined4 *)(lVar17 + 0x2c);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar8 = *plVar5;
    lVar17 = *(long *)(param_1 + 0x30);
    uVar10 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar4 = *(int *)(lVar17 + 0x28);
    if (lVar8 == 0) {
      func_0x010eec64(0);
    }
    lVar17 = *(long *)(lVar8 + 0x40);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = *(int *)(lVar17 + 0x38);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    lVar16 = TroopMan__GetUnitID2Unit(lVar16,uVar20,uVar10,(ulong)(iVar3 != iVar4) << 1,0);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
    }
    uVar2 = UnityEngine_Object__op_Equality(0,lVar16,0,0);
    if ((uVar2 & 1) == 0) {
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      lVar17 = *(long *)(lVar16 + 0x68);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      UnitController__HideMoveSelection(lVar17,0);
      lVar17 = *(long *)(lVar16 + 0x68);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      UnitController__HideAttackSelection(lVar17,0);
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar17 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      ControlFlow__UnitActionCompleted(lVar17,lVar16,0);
    }
    break;
  case 0x60:
    if (((*(byte *)(_class_SceneSingleton<UserSpellAction>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<UserSpellAction>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<UserSpellAction>_get_instance__);
    plVar5 = *(long **)(param_1 + 0x30);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    if (plVar5 == (long *)0x0) {
code_r0x0197983c:
      plVar5 = (long *)0x0;
    }
    else {
      if (*(byte *)(*plVar5 + 0x124) <
          *(byte *)(_class_BattlePlay_UserSpellControl_TypeInfo + 0x124)) goto code_r0x0197983c;
      if (*(long *)(*(long *)(*plVar5 + 200) +
                    (ulong)*(byte *)(_class_BattlePlay_UserSpellControl_TypeInfo + 0x124) * 8 + -8)
          != _class_BattlePlay_UserSpellControl_TypeInfo) {
        plVar5 = (long *)0x0;
      }
    }
    UserSpellAction__Action(lVar16,plVar5,0);
    break;
  case 0x61:
    goto code_r0x019787a0;
  case 0x62:
    if (((*(byte *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<TroopMan>_get_instance__);
    lVar17 = *(long *)(param_1 + 0x30);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    uVar20 = *(undefined4 *)(lVar17 + 0x2c);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar8 = *plVar5;
    lVar17 = *(long *)(param_1 + 0x30);
    uVar10 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar4 = *(int *)(lVar17 + 0x28);
    if (lVar8 == 0) {
      func_0x010eec64(0);
    }
    lVar17 = *(long *)(lVar8 + 0x40);
    if (lVar17 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = *(int *)(lVar17 + 0x38);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    lVar16 = TroopMan__GetUnitID2Unit(lVar16,uVar20,uVar10,(ulong)(iVar3 != iVar4) << 1,0);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
    }
    uVar2 = UnityEngine_Object__op_Equality(0,lVar16,0,0);
    if ((uVar2 & 1) == 0) {
      if (((*(byte *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar17 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<TroopMan>_get_instance__);
      lVar8 = *(long *)(param_1 + 0x30);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      uVar20 = *(undefined4 *)(lVar8 + 0x34);
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = *plVar5;
      lVar8 = *(long *)(param_1 + 0x30);
      uVar10 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      iVar4 = *(int *)(lVar8 + 0x30);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      lVar8 = *(long *)(lVar9 + 0x40);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      iVar3 = *(int *)(lVar8 + 0x38);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = TroopMan__GetUnitID2Unit(lVar17,uVar20,uVar10,(ulong)(iVar3 != iVar4) << 1,0);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
      }
      uVar2 = UnityEngine_Object__op_Equality(0,uVar10,0,0);
      if ((uVar2 & 1) == 0) {
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        lVar16 = *(long *)(lVar16 + 0x68);
        lVar17 = *(long *)(param_1 + 0x30);
        if (lVar17 == 0) {
          func_0x010eec64(0);
        }
        uVar20 = *(undefined4 *)(lVar17 + 0x40);
        if (lVar16 == 0) {
          func_0x010eec64(0);
        }
        UnitController__UseItem(lVar16,uVar10,uVar20,0);
      }
    }
    break;
  case 99:
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    ControlFlow__SkipTurn(lVar16,0,0);
    break;
  case 100:
    break;
  case 0x65:
    lVar16 = CompetitionManager__get_instance(0,0);
    uVar10 = *(undefined8 *)(param_1 + 0x30);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    CompetitionManager__UnitTagAction(lVar16,uVar10,0);
    break;
  default:
    if (*(int *)(&UNK_03995c20 + (long)(int)uVar2 * 4) != 0) {
      return;
    }
    goto code_r0x019787a0;
  }
code_r0x01979b50:
  lVar16 = *(long *)(param_1 + 0x30);
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  *(undefined1 *)(lVar16 + 0x54) = 1;
  *(undefined8 *)(param_1 + 0x30) = 0;
  return;
code_r0x019787a0:
  param_1 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
  System_ArgumentOutOfRangeException___ctor(param_1,0);
  func_0x010eebec(param_1,0,_mm_Method_BattlePlay_BattlePlayManager_UpdateControl__);
  func_0x0112148c();
code_r0x019787d8:
  if (((*(byte *)(_class_SceneSingleton<GeoMan>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<GeoMan>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                     (0,_mm_Method_SceneSingleton<GeoMan>_get_instance__);
  lVar17 = *(long *)(param_1 + 0x30);
  if (lVar17 == 0) {
    func_0x010eec64(0);
  }
  uVar20 = *(undefined4 *)(lVar17 + 0x38);
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  lVar16 = GeoMan__GetTileDataByIdx(lVar16,uVar20,0);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
  }
  uVar2 = UnityEngine_Object__op_Equality(0,lVar16,0,0);
  if ((uVar2 & 1) != 0) {
    return;
  }
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  lVar16 = UnityEngine_Component__GetComponent<GUILayer>
                     (lVar16,_mm_Method_UnityEngine_Component_GetComponent<TileClickHandler>__);
  if (lVar16 == 0) {
    func_0x010eec64(0);
  }
  TileClickHandler__SelectTile(lVar16,0);
  goto code_r0x01979b50;
}


