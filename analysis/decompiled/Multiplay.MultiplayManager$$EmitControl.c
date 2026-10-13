// name: Multiplay.MultiplayManager$$EmitControl
// entry: 013a449c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 Multiplay_MultiplayManager__EmitControl(long param_1,long *param_2,ulong param_3)

{
  int iVar1;
  uint uVar2;
  undefined4 uVar3;
  long *plVar4;
  ulong uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  long lVar9;
  
  if ((bRam00000000049f7aec & 1) == 0) {
    func_0x010c65d4(0x8a29);
    bRam00000000049f7aec = 1;
  }
  if ((((*(char *)(param_1 + 0x141) != '\0') && (*(char *)(param_1 + 0x142) == '\0')) &&
      (plVar4 = *(long **)(param_1 + 0x40), plVar4 != (long *)0x0)) &&
     (iVar1 = (**(code **)(*plVar4 + 0x170))(plVar4,*(undefined8 *)(*plVar4 + 0x178)), iVar1 == 6))
  {
    lVar9 = *(long *)(param_1 + 0x48);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    uVar5 = BattlePlay_BattlePlayData__get_didCompleteProcessControls(lVar9,0);
    if ((uVar5 & 1) != 0) {
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                        (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      uVar2 = ControlFlow__get_isControllingUnitInProgress(lVar9,0);
      if ((uVar2 & 1) == 0) {
        if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar5 = Multiplay_MultiplayManager__get_isMyActiveBattle();
        if ((uVar5 & 1) != 0) {
          if ((param_3 & 1) == 0) {
            if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                              (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
            if (lVar9 == 0) {
              func_0x010eec64(0);
            }
            uVar2 = ControlFlow__IsMultiplayPendingState(lVar9,0);
            if ((uVar2 & 1) != 0) {
              return 0;
            }
          }
          if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
            *(undefined1 *)(param_1 + 0x142) = 1;
            plVar4 = (long *)func_0x010fdda8(_class_LitJson_JsonWriter_TypeInfo);
            LitJson_JsonWriter___ctor(plVar4,0);
            if (param_2 == (long *)0x0) {
              func_0x010eec64(0);
            }
            (**(code **)(*param_2 + 0x170))(param_2,plVar4,*(undefined8 *)(*param_2 + 0x178));
            lVar9 = *(long *)(param_1 + 0x48);
            if (lVar9 == 0) {
              func_0x010eec64(0);
            }
            uVar6 = BattlePlay_BattlePlayData__get_battleKey(lVar9,0);
            if (plVar4 == (long *)0x0) {
              func_0x010eec64(0);
            }
            uVar7 = (**(code **)(*plVar4 + 0x160))(plVar4,*(undefined8 *)(*plVar4 + 0x168));
            plVar4 = *(long **)(param_1 + 0x40);
            if (plVar4 == (long *)0x0) {
              uVar3 = 8;
            }
            else {
              uVar3 = (**(code **)(*plVar4 + 0x170))(plVar4,*(undefined8 *)(*plVar4 + 0x178));
            }
            uVar8 = func_0x010fdda8(_class_<>__AnonType36<string,_string,_Step>_TypeInfo);
            <>__AnonType36<string,_string,_Step>___ctor
                      (uVar8,uVar6,uVar7,uVar3,
                       _mm_Method_<>__AnonType36<string,_string,_Step>__ctor__);
            if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            uVar6 = LitJson_JsonMapper__ToJson(0,uVar8,0);
            Multiplay_MultiplayManager__EmitSocketMessage(param_1,_str_control,uVar6);
            return 1;
          }
          Multiplay_MultiplayManager__SendCancelMatchmaking(param_1,1,0);
        }
      }
    }
  }
  return 0;
}


