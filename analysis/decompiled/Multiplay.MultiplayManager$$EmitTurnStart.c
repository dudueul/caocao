// name: Multiplay.MultiplayManager$$EmitTurnStart
// entry: 013a3a18

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__EmitTurnStart(long param_1)

{
  char cVar1;
  int iVar2;
  undefined4 uVar3;
  undefined4 uVar4;
  undefined8 uVar5;
  long *plVar6;
  undefined8 uVar7;
  ulong uVar8;
  undefined8 uVar9;
  long *plVar10;
  long lVar11;
  undefined8 uStack_60;
  undefined8 uStack_58;
  
  if ((bRam00000000049f7ae6 & 1) == 0) {
    func_0x010c65d4(0x8a2d);
    bRam00000000049f7ae6 = 1;
  }
  uVar7 = _mm_Method_Multiplay_MultiplayManager_EmitTurnStart__;
  cVar1 = *(char *)(param_1 + 0x141);
  uVar9 = *(undefined8 *)(param_1 + 0x78);
  uVar5 = func_0x010fdda8(_class_System_Action_TypeInfo);
  System_Action___ctor(uVar5,param_1,uVar7,0);
  plVar6 = (long *)System_Delegate__Remove(0,uVar9,uVar5,0);
  plVar10 = (long *)0x0;
  if (plVar6 != (long *)0x0) {
    plVar10 = plVar6;
    if (*plVar6 != _class_System_Action_TypeInfo) {
      func_0x0111ef28(&uStack_60,*(undefined8 *)(*plVar6 + 0x40));
      uVar7 = func_0x010ef81c(uStack_60);
      func_0x010eebec(uVar7,0,0);
      func_0x036457e0(&uStack_60);
      plVar10 = (long *)0x0;
    }
  }
  *(long **)(param_1 + 0x78) = plVar10;
  uVar7 = _mm_Method_Multiplay_MultiplayManager_EmitTurnStart__;
  if (cVar1 == '\0') {
    uVar5 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar5,param_1,uVar7,0);
    plVar6 = (long *)System_Delegate__Combine(0,plVar10,uVar5,0);
    plVar10 = (long *)0x0;
    if (plVar6 != (long *)0x0) {
      plVar10 = plVar6;
      if (*plVar6 != _class_System_Action_TypeInfo) {
        func_0x0111ef28(&uStack_58,*(undefined8 *)(*plVar6 + 0x40));
        uVar7 = func_0x010ef81c(uStack_58);
        func_0x010eebec(uVar7,0,0);
        func_0x036457e0(&uStack_58);
        plVar10 = (long *)0x0;
      }
    }
    *(long **)(param_1 + 0x78) = plVar10;
  }
  else {
    *(undefined1 *)(param_1 + 0x142) = 0;
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar8 = Multiplay_MultiplayManager__get_isMyActiveBattle();
    if (((uVar8 & 1) != 0) && (plVar10 = *(long **)(param_1 + 0x40), plVar10 != (long *)0x0)) {
      iVar2 = (**(code **)(*plVar10 + 0x170))(plVar10,*(undefined8 *)(*plVar10 + 0x178));
      if (iVar2 == 6) {
        if ((*(long *)(param_1 + 0x40) == 0) || (*(long *)(param_1 + 0x48) == 0)) {
          Multiplay_MultiplayManager__SendCancelMatchmaking(param_1,1,0);
        }
        else {
          uVar7 = func_0x010fdda8(_class_LitJson_JsonWriter_TypeInfo);
          LitJson_JsonWriter___ctor(uVar7,0);
          lVar11 = *(long *)(param_1 + 0x48);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          BattlePlay_BattlePlayData__SerializeLastControlTurn(lVar11,uVar7,0);
          lVar11 = *(long *)(param_1 + 0x48);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          uVar7 = BattlePlay_BattlePlayData__get_battleKey(lVar11,0);
          if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c(_class_SceneSingleton<ControlFlow>_TypeInfo);
          }
          lVar11 = SceneSingleton<WorldBossBattleController>__get_instance
                             (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          uVar3 = ControlFlow__get_currentUnitTurnIdx(lVar11,0);
          plVar10 = *(long **)(param_1 + 0x40);
          if (plVar10 == (long *)0x0) {
            uVar4 = 8;
          }
          else {
            uVar4 = (**(code **)(*plVar10 + 0x170))(plVar10,*(undefined8 *)(*plVar10 + 0x178));
          }
          uVar5 = func_0x010fdda8(_class_<>__AnonType32<string,_int,_Step>_TypeInfo);
          <>__AnonType32<string,_int,_Step>___ctor
                    (uVar5,uVar7,uVar3,uVar4,_mm_Method_<>__AnonType32<string,_int,_Step>__ctor__);
          if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar7 = LitJson_JsonMapper__ToJson(0,uVar5,0);
          Multiplay_MultiplayManager__EmitSocketMessage(param_1,_str_turnStart,uVar7);
        }
      }
    }
  }
  return;
}


