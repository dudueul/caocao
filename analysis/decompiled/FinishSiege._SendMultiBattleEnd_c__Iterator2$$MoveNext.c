// name: FinishSiege.<SendMultiBattleEnd>c__Iterator2$$MoveNext
// entry: 0204ec1c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 FinishSiege_<SendMultiBattleEnd>c__Iterator2__MoveNext(long param_1)

{
  int iVar1;
  int iVar2;
  char cVar3;
  undefined4 uVar4;
  undefined8 uVar5;
  long lVar6;
  undefined8 uVar7;
  long lVar8;
  long lVar9;
  long *plVar10;
  long lVar11;
  undefined8 uVar12;
  undefined8 uStack_98;
  undefined8 uStack_90;
  undefined4 uStack_84;
  undefined4 uStack_80;
  int iStack_7c;
  undefined1 uStack_75;
  undefined4 uStack_74;
  undefined8 uStack_70;
  undefined4 uStack_64;
  
  if ((bRam00000000049fea62 & 1) == 0) {
    func_0x010c65d4(0xdfca);
    bRam00000000049fea62 = 1;
  }
  iVar2 = *(int *)(param_1 + 100);
  *(undefined4 *)(param_1 + 100) = 0xffffffff;
  iVar1 = 3;
  if (iVar2 != 0) {
    iVar1 = (uint)(iVar2 == 1) << 2;
  }
  if (iVar1 == 4) {
    uVar5 = 0;
    *(undefined4 *)(param_1 + 100) = 0xffffffff;
  }
  else {
    uVar5 = 0;
    if (iVar1 == 3) {
      plVar10 = *(long **)(param_1 + 0x50);
      cVar3 = *(char *)(param_1 + 0x10);
      if (plVar10 == (long *)0x0) {
        func_0x010eec64(0);
      }
      uVar5 = (**(code **)(*plVar10 + 0x390))
                        (plVar10,cVar3 != '\0',1,1,*(undefined8 *)(*plVar10 + 0x398));
      *(undefined8 *)(param_1 + 0x18) = uVar5;
      lVar6 = MyUserData__get_instance(0,0);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      lVar6 = UserData__get_defenseDecks(lVar6,0);
      uVar4 = *(undefined4 *)(param_1 + 0x20);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      lVar6 = UserDefenseDecks__GetUnit(lVar6,uVar4,0);
      *(long *)(param_1 + 0x28) = lVar6;
      if (lVar6 != 0) {
        lVar11 = *(long *)(param_1 + 0x50);
        uVar4 = DefenseDeckUnit__get_unitID(lVar6,0);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        *(undefined4 *)(lVar11 + 0x84) = uVar4;
        lVar11 = *(long *)(param_1 + 0x50);
        lVar6 = *(long *)(param_1 + 0x28);
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        uVar4 = DefenseDeckUnit__get_townID(lVar6,0);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        *(undefined4 *)(lVar11 + 0x88) = uVar4;
        lVar11 = *(long *)(param_1 + 0x50);
        lVar6 = *(long *)(param_1 + 0x28);
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        uVar4 = DefenseDeckUnit__get_BFIndex(lVar6,0);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        *(undefined4 *)(lVar11 + 0x8c) = uVar4;
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
      uVar4 = *(undefined4 *)(lVar6 + 0x250);
      if (((*(byte *)(_class_UnityEngine_Mathf_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Mathf_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar4 = UnityEngine_Mathf__RoundToInt(uVar4,0,0);
      *(undefined4 *)(param_1 + 0x30) = uVar4;
      lVar6 = *(long *)(param_1 + 0x50);
      if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar11 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
      uVar5 = _mm_Method_FinishSiege_OnProcessBattleEnd__;
      uVar12 = *(undefined8 *)(param_1 + 0x50);
      uVar7 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
      HttpRequest_ResponseCallback___ctor(uVar7,uVar12,uVar5,0);
      plVar10 = (long *)func_0x01101ad4(_class_object___TypeInfo,0x16);
      if (plVar10 == (long *)0x0) {
        func_0x010eec64(0);
      }
      if ((_str_townId != 0) &&
         (lVar8 = func_0x010fdfcc(_str_townId,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      lVar8 = _str_townId;
      if ((int)plVar10[3] == 0) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[4] = lVar8;
      uStack_64 = *(undefined4 *)(param_1 + 0x34);
      lVar8 = func_0x010fdd08(_class_int_TypeInfo,&uStack_64);
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 2) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[5] = lVar8;
      if ((_str_opponentId != 0) &&
         (lVar8 = func_0x010fdfcc(_str_opponentId,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      lVar8 = _str_opponentId;
      if (*(uint *)(plVar10 + 3) < 3) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[6] = lVar8;
      uStack_70 = *(undefined8 *)(param_1 + 0x38);
      lVar8 = func_0x010fdd08(_class_ulong_TypeInfo,&uStack_70);
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 4) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[7] = lVar8;
      if ((_str_unitId != 0) &&
         (lVar8 = func_0x010fdfcc(_str_unitId,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      lVar8 = _str_unitId;
      if (*(uint *)(plVar10 + 3) < 5) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[8] = lVar8;
      uStack_74 = *(undefined4 *)(param_1 + 0x20);
      lVar8 = func_0x010fdd08(_class_int_TypeInfo,&uStack_74);
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 6) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[9] = lVar8;
      if ((_str_isWin != 0) &&
         (lVar8 = func_0x010fdfcc(_str_isWin,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      lVar8 = _str_isWin;
      if (*(uint *)(plVar10 + 3) < 7) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[10] = lVar8;
      uStack_75 = *(undefined1 *)(param_1 + 0x10);
      lVar8 = func_0x010fdd08(_class_bool_TypeInfo,&uStack_75);
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 8) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[0xb] = lVar8;
      if ((_str_occupationPct != 0) &&
         (lVar8 = func_0x010fdfcc(_str_occupationPct,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0))
      {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      lVar8 = _str_occupationPct;
      if (*(uint *)(plVar10 + 3) < 9) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[0xc] = lVar8;
      iStack_7c = (int)*(float *)(param_1 + 0x40);
      lVar8 = func_0x010fdd08(_class_int_TypeInfo,&iStack_7c);
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 10) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[0xd] = lVar8;
      if ((_str_rankID != 0) &&
         (lVar8 = func_0x010fdfcc(_str_rankID,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      lVar8 = _str_rankID;
      if (*(uint *)(plVar10 + 3) < 0xb) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[0xe] = lVar8;
      uStack_80 = *(undefined4 *)(param_1 + 0x44);
      lVar8 = func_0x010fdd08(_class_int_TypeInfo,&uStack_80);
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 0xc) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[0xf] = lVar8;
      if ((_str_battlePlayTime != 0) &&
         (lVar8 = func_0x010fdfcc(_str_battlePlayTime,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0)
         ) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      lVar8 = _str_battlePlayTime;
      if (*(uint *)(plVar10 + 3) < 0xd) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[0x10] = lVar8;
      uStack_84 = *(undefined4 *)(param_1 + 0x30);
      lVar8 = func_0x010fdd08(_class_int_TypeInfo,&uStack_84);
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 0xe) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[0x11] = lVar8;
      if ((_str_battleStartDateTime != 0) &&
         (lVar8 = func_0x010fdfcc(_str_battleStartDateTime,*(undefined8 *)(*plVar10 + 0x40)),
         lVar8 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      lVar8 = _str_battleStartDateTime;
      if (*(uint *)(plVar10 + 3) < 0xf) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[0x12] = lVar8;
      if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar8 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      uStack_98 = *(undefined8 *)(lVar8 + 0x88);
      uStack_90 = *(undefined8 *)(lVar8 + 0x90);
      lVar8 = func_0x010fdd08(_class_System_DateTime_TypeInfo,&uStack_98);
      if (plVar10 == (long *)0x0) {
        func_0x010eec64(0);
      }
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 0x10) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[0x13] = lVar8;
      if ((_str_data != 0) &&
         (lVar8 = func_0x010fdfcc(_str_data,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      lVar8 = _str_data;
      if (*(uint *)(plVar10 + 3) < 0x11) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[0x14] = lVar8;
      lVar8 = *(long *)(param_1 + 0x18);
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 0x12) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[0x15] = lVar8;
      if ((_str_message != 0) &&
         (lVar8 = func_0x010fdfcc(_str_message,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      lVar8 = _str_message;
      if (*(uint *)(plVar10 + 3) < 0x13) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[0x16] = lVar8;
      lVar8 = *(long *)(param_1 + 0x48);
      if ((lVar8 != 0) &&
         (lVar9 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar10 + 0x40)), lVar9 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 0x14) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[0x17] = lVar8;
      if ((_str_gainedItems != 0) &&
         (lVar8 = func_0x010fdfcc(_str_gainedItems,*(undefined8 *)(*plVar10 + 0x40)), lVar8 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      lVar8 = _str_gainedItems;
      if (*(uint *)(plVar10 + 3) < 0x15) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[0x18] = lVar8;
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
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 0x16) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[0x19] = lVar8;
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      uVar5 = HttpRequest__SendAndWait
                        (lVar11,_str__battle_multiBattleEnd,_str_POST,1,uVar7,plVar10,0);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      uVar5 = UnityEngine_MonoBehaviour__StartCoroutine(lVar6,uVar5,0);
      *(undefined8 *)(param_1 + 0x58) = uVar5;
      uVar5 = 1;
      if (*(char *)(param_1 + 0x60) == '\0') {
        *(undefined4 *)(param_1 + 100) = 1;
      }
    }
  }
  return uVar5;
}


