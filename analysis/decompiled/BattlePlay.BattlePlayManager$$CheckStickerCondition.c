// name: BattlePlay.BattlePlayManager$$CheckStickerCondition
// entry: 0197aea4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 BattlePlay_BattlePlayManager__CheckStickerCondition(undefined8 param_1,long param_2)

{
  int iVar1;
  int iVar2;
  ulong uVar3;
  long lVar4;
  undefined8 uVar5;
  
  if ((bRam00000000049fae13 & 1) == 0) {
    func_0x010c65d4(0x2063);
    bRam00000000049fae13 = 1;
  }
  if (param_2 == 0) {
code_r0x0197b004:
    uVar5 = 0;
  }
  else {
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar3 = Multiplay_MultiplayManager__get_isReplayBattle(0,0);
    if ((uVar3 & 1) != 0) {
      iVar2 = *(int *)(param_2 + 0x1c);
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar4 = SceneSingleton<WorldBossBattleController>__get_instance
                        (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      iVar1 = ControlFlow__get_currentUnitTurnIdx(lVar4,0);
      if (iVar1 < iVar2) goto code_r0x0197b004;
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar4 = SceneSingleton<WorldBossBattleController>__get_instance
                        (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      iVar2 = ControlFlow__get_remainedSeconds(lVar4,0);
      if ((-1 < iVar2) && (iVar2 = *(int *)(param_2 + 0x20), -1 < iVar2)) {
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar4 = SceneSingleton<WorldBossBattleController>__get_instance
                          (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        iVar1 = ControlFlow__get_remainedSeconds(lVar4,0);
        if (iVar2 < iVar1) goto code_r0x0197b004;
      }
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar4 = SceneSingleton<WorldBossBattleController>__get_instance
                        (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      ControlFlow__get_remainedSeconds(lVar4,0);
    }
    uVar5 = 1;
  }
  return uVar5;
}


