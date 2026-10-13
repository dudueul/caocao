// name: BattlePlay.BattlePlayManager$$CheckStatus
// entry: 0197a3dc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

bool BattlePlay_BattlePlayManager__CheckStatus(long param_1)

{
  undefined4 uVar1;
  byte bVar2;
  int iVar3;
  uint uVar4;
  int iVar5;
  long lVar6;
  ulong uVar7;
  undefined8 uVar8;
  long lVar9;
  long lVar10;
  
  if ((bRam00000000049fae14 & 1) == 0) {
    func_0x010c65d4(0x2062);
    bRam00000000049fae14 = 1;
  }
  if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = *(undefined8 *)(lVar6 + 0x11c);
  uVar1 = *(undefined4 *)(lVar6 + 0x124);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) != 0)
     && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar7 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar8,uVar1,0);
  if ((uVar7 & 1) == 0) {
    return false;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar7 = Multiplay_MultiplayManager__get_isReplayBattle(0,0);
  if ((uVar7 & 1) != 0) {
    lVar6 = *(long *)(param_1 + 0x30);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    iVar5 = *(int *)(lVar6 + 0x50);
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = ControlFlow__get_remainedSeconds(lVar6,0);
    if (iVar5 < iVar3) {
      return false;
    }
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar4 = ControlFlow__get_isControllingUnitInProgress(lVar6,0);
    if ((uVar4 & 1) != 0) {
      return false;
    }
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar4 = Multiplay_MultiplayManager__get_isContinuedBattle(0,0);
  if ((uVar4 & 1) == 0) {
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar7 = Multiplay_MultiplayManager__get_isLoadingBattleForWatching(0,0);
    if ((uVar7 & 1) != 0) goto code_r0x0197a5cc;
  }
  else {
code_r0x0197a5cc:
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar4 = ControlFlow__get_isControllingUnitInProgress(lVar6,0);
    if ((uVar4 & 1) != 0) {
      return false;
    }
  }
  if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  iVar5 = ControlFlow__get_currentUnitTurnIdx(lVar6,0);
  lVar6 = *(long *)(param_1 + 0x30);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  if (iVar5 == *(int *)(lVar6 + 0x4c)) {
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = ControlFlow__DidAllMoveCompleted(lVar6,0);
    if ((uVar7 & 1) != 0) {
      lVar6 = *(long *)(param_1 + 0x30);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      if (*(int *)(lVar6 + 0x18) != 0xe) {
        if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar7 = Multiplay_MultiplayManager__get_isMyActiveBattle(0,0);
        if ((uVar7 & 1) != 0) {
          lVar6 = *(long *)(param_1 + 0x18);
          if (lVar6 == 0) {
            func_0x010eec64(0);
          }
          lVar6 = *(long *)(lVar6 + 0x40);
          if (lVar6 == 0) {
            func_0x010eec64(0);
          }
          lVar6 = *(long *)(lVar6 + 0x10);
          lVar9 = *(long *)(param_1 + 0x30);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          if (lVar6 == *(long *)(lVar9 + 0x10)) {
            return true;
          }
        }
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                          (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        iVar5 = ControlFlow__get_controlStatus(lVar6,0);
        lVar6 = *(long *)(param_1 + 0x30);
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        return iVar5 == *(int *)(lVar6 + 0x24);
      }
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                        (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      iVar5 = ControlFlow__get_controlStatus(lVar6,0);
      if (iVar5 != 0xf) {
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                          (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        iVar5 = ControlFlow__get_controlStatus(lVar6,0);
        if (iVar5 != 1) {
          if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                            (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
          if (lVar6 == 0) {
            func_0x010eec64(0);
          }
          iVar5 = ControlFlow__get_controlStatus(lVar6,0);
          if (iVar5 != 10) {
            if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                              (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
            if (lVar6 == 0) {
              func_0x010eec64(0);
            }
            iVar5 = ControlFlow__get_controlStatus(lVar6,0);
            if (iVar5 != 0xd) {
              if (((*(byte *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_SceneSingleton<TroopMan>_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c();
              }
              lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                                (0,_mm_Method_SceneSingleton<TroopMan>_get_instance__);
              lVar9 = *(long *)(param_1 + 0x30);
              if (lVar9 == 0) {
                func_0x010eec64(0);
              }
              uVar1 = *(undefined4 *)(lVar9 + 0x2c);
              if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c();
              }
              lVar10 = *(long *)(param_1 + 0x18);
              lVar9 = *(long *)(param_1 + 0x30);
              uVar8 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
              if (lVar9 == 0) {
                func_0x010eec64(0);
              }
              iVar5 = *(int *)(lVar9 + 0x28);
              if (lVar10 == 0) {
                func_0x010eec64(0);
              }
              lVar9 = *(long *)(lVar10 + 0x40);
              if (lVar9 == 0) {
                func_0x010eec64(0);
              }
              iVar3 = *(int *)(lVar9 + 0x38);
              if (lVar6 == 0) {
                func_0x010eec64(0);
              }
              lVar6 = TroopMan__GetUnitID2Unit(lVar6,uVar1,uVar8,(ulong)(iVar3 != iVar5) << 1,0);
              if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c(_class_SceneSingleton<ControlFlow>_TypeInfo);
              }
              lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                                (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
              if (lVar9 == 0) {
                func_0x010eec64(0);
              }
              lVar9 = ControlFlow__get_unitTurnOwner(lVar9,0);
              if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
              }
              if ((bRam0000000004a04154 & 1) == 0) {
                func_0x010c65d4(0x9081,lVar9,lVar6,0);
                bRam0000000004a04154 = 1;
              }
              if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c();
              }
              if ((bRam0000000004a040fb & 1) == 0) {
                func_0x010c65d4(0x906d);
                bRam0000000004a040fb = 1;
              }
              if (lVar9 == 0 && lVar6 == 0) {
                bVar2 = 1;
              }
              else {
                lVar10 = _class_UnityEngine_Object_TypeInfo;
                if (lVar6 == 0) {
                  lVar6 = lVar9;
                  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
                     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
                    lVar10 = func_0x010da55c();
                  }
                }
                else {
                  if (lVar9 != 0) {
                    bVar2 = lVar9 == lVar6;
                    goto code_r0x02abfd50;
                  }
                  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
                     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
                    lVar10 = func_0x010da55c();
                  }
                }
                bVar2 = UnityEngine_Object__IsNativeObjectAlive(lVar10,lVar6);
                bVar2 = bVar2 ^ 1;
              }
code_r0x02abfd50:
              return (bool)(bVar2 & 1);
            }
          }
        }
      }
    }
  }
  return false;
}


