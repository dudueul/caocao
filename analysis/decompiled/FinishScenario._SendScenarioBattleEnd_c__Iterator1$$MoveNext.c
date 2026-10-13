// name: FinishScenario.<SendScenarioBattleEnd>c__Iterator1$$MoveNext
// entry: 02048920

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 FinishScenario_<SendScenarioBattleEnd>c__Iterator1__MoveNext(long param_1)

{
  int iVar1;
  int iVar2;
  uint uVar3;
  undefined8 uVar4;
  long lVar5;
  long lVar6;
  undefined8 uVar7;
  long lVar8;
  long lVar9;
  long *plVar10;
  undefined8 uVar11;
  long *plVar12;
  undefined4 uVar13;
  undefined8 uStack_88;
  undefined8 uStack_80;
  undefined4 uStack_74;
  undefined4 uStack_70;
  undefined4 uStack_6c;
  byte bStack_65;
  undefined4 uStack_64;
  
  if ((bRam00000000049fea3f & 1) == 0) {
    func_0x010c65d4(0xdfdc);
    bRam00000000049fea3f = 1;
  }
  iVar2 = *(int *)(param_1 + 0x3c);
  *(undefined4 *)(param_1 + 0x3c) = 0xffffffff;
  iVar1 = 3;
  if (iVar2 != 0) {
    iVar1 = (uint)(iVar2 == 1) << 2;
  }
  if (iVar1 == 4) {
    uVar4 = 0;
    *(undefined4 *)(param_1 + 0x3c) = 0xffffffff;
  }
  else {
    uVar4 = 0;
    if (iVar1 == 3) {
      plVar10 = *(long **)(param_1 + 0x28);
      if (plVar10 == (long *)0x0) {
        func_0x010eec64(0);
      }
      uVar3 = (**(code **)(*plVar10 + 0x2f0))(plVar10,*(undefined8 *)(*plVar10 + 0x2f8));
      if (plVar10 == (long *)0x0) {
        func_0x010eec64(0);
      }
      uVar4 = (**(code **)(*plVar10 + 0x390))
                        (plVar10,uVar3 & 1,0,0,*(undefined8 *)(*plVar10 + 0x398));
      *(undefined8 *)(param_1 + 0x10) = uVar4;
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar5 = SceneSingleton<WorldBossBattleController>__get_instance
                        (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      uVar13 = *(undefined4 *)(lVar5 + 0x250);
      if (((*(byte *)(_class_UnityEngine_Mathf_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Mathf_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar13 = UnityEngine_Mathf__RoundToInt(uVar13,0,0);
      *(undefined4 *)(param_1 + 0x18) = uVar13;
      lVar5 = *(long *)(param_1 + 0x28);
      if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar6 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
      uVar4 = _mm_Method_FinishScenario_OnProcessBattleEnd__;
      uVar11 = *(undefined8 *)(param_1 + 0x28);
      uVar7 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
      HttpRequest_ResponseCallback___ctor(uVar7,uVar11,uVar4,0);
      plVar10 = (long *)func_0x01101ad4(_class_object___TypeInfo,0x12);
      if (plVar10 == (long *)0x0) {
        func_0x010eec64(0);
      }
      if ((_str_scenarioId != 0) &&
         (lVar8 = func_0x010fdfcc(_str_scenarioId,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      lVar8 = _str_scenarioId;
      if ((int)plVar10[3] == 0) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[4] = lVar8;
      uStack_64 = *(undefined4 *)(param_1 + 0x1c);
      lVar8 = func_0x010fdd08(_class_int_TypeInfo,&uStack_64);
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 2) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[5] = lVar8;
      if ((_str_isWin != 0) &&
         (lVar8 = func_0x010fdfcc(_str_isWin,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      lVar8 = _str_isWin;
      if (*(uint *)(plVar10 + 3) < 3) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[6] = lVar8;
      plVar12 = *(long **)(param_1 + 0x28);
      if (plVar12 == (long *)0x0) {
        func_0x010eec64(0);
      }
      bStack_65 = (**(code **)(*plVar12 + 0x2f0))(plVar12,*(undefined8 *)(*plVar12 + 0x2f8));
      bStack_65 = bStack_65 & 1;
      lVar8 = func_0x010fdd08(_class_bool_TypeInfo,&bStack_65);
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 4) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[7] = lVar8;
      if ((_str_victoryGrade != 0) &&
         (lVar8 = func_0x010fdfcc(_str_victoryGrade,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0))
      {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      lVar8 = _str_victoryGrade;
      if (*(uint *)(plVar10 + 3) < 5) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[8] = lVar8;
      uStack_6c = *(undefined4 *)(param_1 + 0x20);
      lVar8 = func_0x010fdd08(_class_int_TypeInfo,&uStack_6c);
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 6) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[9] = lVar8;
      if ((_str_modeLevel != 0) &&
         (lVar8 = func_0x010fdfcc(_str_modeLevel,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      lVar8 = _str_modeLevel;
      if (*(uint *)(plVar10 + 3) < 7) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[10] = lVar8;
      if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar8 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      uStack_70 = *(undefined4 *)(lVar8 + 0x74);
      lVar8 = func_0x010fdd08(_class_int_TypeInfo,&uStack_70);
      if (plVar10 == (long *)0x0) {
        func_0x010eec64(0);
      }
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 8) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[0xb] = lVar8;
      if ((_str_battlePlayTime != 0) &&
         (lVar8 = func_0x010fdfcc(_str_battlePlayTime,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0)
         ) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      lVar8 = _str_battlePlayTime;
      if (*(uint *)(plVar10 + 3) < 9) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[0xc] = lVar8;
      uStack_74 = *(undefined4 *)(param_1 + 0x18);
      lVar8 = func_0x010fdd08(_class_int_TypeInfo,&uStack_74);
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 10) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[0xd] = lVar8;
      if ((_str_battleStartDateTime != 0) &&
         (lVar8 = func_0x010fdfcc(_str_battleStartDateTime,*(undefined8 *)(*plVar10 + 0x40)),
         lVar8 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      lVar8 = _str_battleStartDateTime;
      if (*(uint *)(plVar10 + 3) < 0xb) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[0xe] = lVar8;
      lVar8 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      uStack_88 = *(undefined8 *)(lVar8 + 0x88);
      uStack_80 = *(undefined8 *)(lVar8 + 0x90);
      lVar8 = func_0x010fdd08(_class_System_DateTime_TypeInfo,&uStack_88);
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 0xc) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[0xf] = lVar8;
      if ((_str_lockScenarioIds != 0) &&
         (lVar8 = func_0x010fdfcc(_str_lockScenarioIds,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0
         )) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      lVar8 = _str_lockScenarioIds;
      if (*(uint *)(plVar10 + 3) < 0xd) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[0x10] = lVar8;
      lVar8 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      lVar8 = *(long *)(lVar8 + 0x98);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      lVar8 = System_Collections_Generic_List<int>__ToArray
                        (lVar8,_mm_Method_System_Collections_Generic_List<int>_ToArray__);
      if (plVar10 == (long *)0x0) {
        func_0x010eec64(0);
      }
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 0xe) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[0x11] = lVar8;
      if ((_str_data != 0) &&
         (lVar8 = func_0x010fdfcc(_str_data,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      lVar8 = _str_data;
      if (*(uint *)(plVar10 + 3) < 0xf) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[0x12] = lVar8;
      lVar8 = *(long *)(param_1 + 0x10);
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 0x10) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[0x13] = lVar8;
      if ((_str_gainedItems != 0) &&
         (lVar8 = func_0x010fdfcc(_str_gainedItems,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      lVar8 = _str_gainedItems;
      if (*(uint *)(plVar10 + 3) < 0x11) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[0x14] = lVar8;
      if (((*(byte *)(_class_SceneSingleton<EventScenario>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<EventScenario>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar8 = SceneSingleton<WorldBossBattleController>__get_instance
                        (0,_mm_Method_SceneSingleton<EventScenario>_get_instance__);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      lVar8 = EventScenario__SerializeGainedRewardsInThisBattle(lVar8,0);
      if (plVar10 == (long *)0x0) {
        func_0x010eec64(0);
      }
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 0x12) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar10[0x15] = lVar8;
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      uVar4 = HttpRequest__SendAndWait
                        (lVar6,_str__battle_scenarioBattleEnd,_str_POST,1,uVar7,plVar10,0);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      uVar4 = UnityEngine_MonoBehaviour__StartCoroutine(lVar5,uVar4,0);
      *(undefined8 *)(param_1 + 0x30) = uVar4;
      uVar4 = 1;
      if (*(char *)(param_1 + 0x38) == '\0') {
        *(undefined4 *)(param_1 + 0x3c) = 1;
      }
    }
  }
  return uVar4;
}


