// name: Multiplay.MultiplayManager$$OnTurnStart
// entry: 013a9894

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnTurnStart(long param_1,long param_2)

{
  long lVar1;
  int iVar2;
  int iVar3;
  undefined4 uVar4;
  uint uVar5;
  ulong uVar6;
  long *plVar7;
  undefined8 uVar8;
  long lVar9;
  long lVar10;
  long lVar11;
  undefined1 *puVar12;
  undefined8 uVar13;
  code *pcVar14;
  double dVar15;
  undefined1 auVar16 [16];
  undefined1 auVar17 [16];
  undefined1 auStack_b0 [16];
  undefined1 auStack_a0 [16];
  undefined1 auStack_90 [16];
  undefined1 auStack_80 [16];
  undefined1 auStack_70 [16];
  
  if ((bRam00000000049f7b13 & 1) == 0) {
    func_0x010c65d4(0x8a6e);
    bRam00000000049f7b13 = 1;
  }
  *(undefined1 *)(param_1 + 0x142) = 0;
  if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
    if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar6 = LoadedSceneCheckUtil__get_isBattleMap(0,0);
    if ((((uVar6 & 1) == 0) || (plVar7 = *(long **)(param_1 + 0x40), plVar7 == (long *)0x0)) ||
       (iVar2 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178)), iVar2 != 6)
       ) {
      return;
    }
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar8 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Util_TypeInfo);
    }
    Util__GetData(0,uVar8,_str_uid,0,0);
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar8 = SocketIO_SocketIOEvent__get_data(param_2,0);
    iVar2 = Util__GetData(0,uVar8,_str_turnIdx,0,0);
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_SceneSingleton<ControlFlow>_TypeInfo);
    }
    lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = ControlFlow__get_currentUnitTurnIdx(lVar9,0);
    if (iVar2 != iVar3) {
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
    if ((bRam00000000049f9e16 & 1) == 0) {
      func_0x010c65d4(0x3008,0,0);
      bRam00000000049f9e16 = 1;
    }
    auStack_70._0_8_ = 0;
    auStack_70._8_8_ = 0;
    auStack_80._0_8_ = 0;
    auStack_80._8_8_ = 0;
    auStack_90._0_8_ = 0;
    auStack_90._8_8_ = 0;
    auStack_a0._8_8_ = 0;
    auStack_b0._8_8_ = 0;
    auStack_a0._0_8_ = 0;
    auStack_b0._0_8_ = 0;
    if (((*(byte *)(_class_System_DateTime_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_System_DateTime_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    auVar16 = *(undefined1 (*) [16])(*(long *)(_class_System_DateTime_TypeInfo + 0xb8) + 0x10);
    if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar10 = Singleton<iTweenManager>__get_instance
                       (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    uVar6 = SceneDataPipe__get_isSiegeMode(lVar10,0);
    if ((uVar6 & 1) == 0) {
      if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar10 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      uVar6 = SceneDataPipe__get_isCompetitionMode(lVar10,0);
      if ((uVar6 & 1) != 0) {
        uVar8 = ControlFlow__get_unitTurnOwner(lVar9);
        if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
        }
        uVar6 = UnityEngine_Object__op_Inequality(0,uVar8,0,0);
        lVar10 = 0;
        if ((uVar6 & 1) != 0) {
          if (((*(byte *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar10 = Singleton<iTweenManager>__get_instance
                             (0,_mm_Method_Singleton<BattlePlayManager>_get_instance__);
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          lVar10 = BattlePlay_BattlePlayManager__get_battlePlayData(lVar10,0);
          lVar11 = ControlFlow__get_unitTurnOwner(lVar9);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          uVar8 = UnitData__get_uid(lVar11,0);
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          lVar10 = BattlePlay_BattlePlayData__get_Item(lVar10,uVar8,0);
          lVar11 = CompetitionManager__get_instance(0,0);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          uVar13 = *(undefined8 *)(lVar11 + 0xa8);
          uVar8 = ControlFlow__get_unitTurnOwner(lVar9);
          if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
          }
          uVar6 = UnityEngine_Object__op_Equality(0,uVar13,uVar8,0);
          if ((uVar6 & 1) != 0) {
            ControlFlow__SetBattleTopMiddleUIActive(uVar6,1);
            return;
          }
        }
        lVar11 = *(long *)(lVar9 + 0x268);
        uVar4 = *(undefined4 *)(lVar9 + 0x14c);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        uVar6 = System_Collections_Generic_Dictionary<int,_DateTime>__ContainsKey
                          (lVar11,uVar4,
                           _mm_Method_System_Collections_Generic_Dictionary<int,_DateTime>_ContainsKey__
                          );
        if ((uVar6 & 1) == 0) {
          if (lVar10 == 0) {
            iVar2 = 1;
          }
          else {
            iVar2 = ControlFlow__get_currentUnitTurn(lVar9);
            iVar2 = iVar2 - *(int *)(lVar10 + 0xac);
          }
          if (((*(byte *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar10 = Singleton<iTweenManager>__get_instance
                             (0,_mm_Method_Singleton<BattlePlayManager>_get_instance__);
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          lVar10 = BattlePlay_BattlePlayManager__get_battlePlayData(lVar10,0);
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          lVar10 = BattlePlay_BattlePlayData__get_turnTime(lVar10,0);
          if (lVar10 == 0) {
            return;
          }
          auStack_80 = ServerTime__get_Now(0,0);
          if (((*(byte *)(_class_UnityEngine_Mathf_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_UnityEngine_Mathf_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          iVar2 = UnityEngine_Mathf__Min(0,*(undefined4 *)(lVar10 + 0x18),iVar2,0);
          uVar5 = UnityEngine_Mathf__Max(0,0,iVar2 + -1,0);
          if (*(uint *)(lVar10 + 0x18) <= uVar5) {
            uVar8 = func_0x010ef850();
            func_0x010eebec(uVar8,0,0);
          }
          puVar12 = auStack_80;
          dVar15 = (double)*(int *)(lVar10 + (long)(int)uVar5 * 4 + 0x20);
code_r0x01788cc4:
          auVar16 = func_0x02de0134(dVar15,puVar12,0);
        }
        else {
          auVar16 = ServerTime__get_Now(0,0);
          lVar10 = *(long *)(lVar9 + 0x268);
          uVar4 = *(undefined4 *)(lVar9 + 0x14c);
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          auVar17 = System_Collections_Generic_Dictionary<int,_DateTime>__get_Item
                              (lVar10,uVar4,
                               _mm_Method_System_Collections_Generic_Dictionary<int,_DateTime>_get_Item__
                              );
          if (((*(byte *)(_class_System_DateTime_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_System_DateTime_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar6 = System_DateTime__op_LessThan
                            (0,auVar16._0_8_,auVar16._8_8_,auVar17._0_8_,auVar17._8_8_,0);
          if ((uVar6 & 1) == 0) {
            auStack_70 = ServerTime__get_Now(0,0);
            dVar15 = 1.0;
            puVar12 = auStack_70;
            goto code_r0x01788cc4;
          }
          lVar10 = *(long *)(lVar9 + 0x268);
          uVar4 = *(undefined4 *)(lVar9 + 0x14c);
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          auVar16 = System_Collections_Generic_Dictionary<int,_DateTime>__get_Item
                              (lVar10,uVar4,
                               _mm_Method_System_Collections_Generic_Dictionary<int,_DateTime>_get_Item__
                              );
        }
        uVar4 = 5;
        goto code_r0x01788cdc;
      }
      if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar10 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      uVar6 = SceneDataPipe__get_isAnnihilationWarMode(lVar10,0);
      if ((uVar6 & 1) != 0) {
        auStack_90 = ServerTime__get_Now(0,0);
        auVar16 = func_0x02ddff64(0x4034000000000000,auStack_90,0);
        uVar4 = 0xb4;
        goto code_r0x01788cdc;
      }
      if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar10 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      uVar6 = SceneDataPipe__get_isBattleFieldMode(lVar10,0);
      if ((uVar6 & 1) != 0) {
        auStack_a0 = ServerTime__get_Now(0,0);
        if (((*(byte *)(_class_ClientConst_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_ClientConst_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        auVar16 = func_0x02ddff64((double)*(int *)(*(long *)(_class_ClientConst_TypeInfo + 0xb8) +
                                                  500),auStack_a0,0);
        uVar4 = *(undefined4 *)(*(long *)(_class_ClientConst_TypeInfo + 0xb8) + 0x1f8);
        goto code_r0x01788cdc;
      }
      if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar10 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      uVar6 = SceneDataPipe__get_isGuildSiegeWarMode(lVar10,0);
      if ((uVar6 & 1) == 0) {
        uVar4 = 0;
        goto code_r0x01788cdc;
      }
      auStack_b0 = ServerTime__get_Now(0,0);
      auVar16 = func_0x02ddff64(0x403e000000000000,auStack_b0,0);
    }
    else {
      lVar10 = MyUserData__get_instance(0,0);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      lVar10 = UserData__get_towns(lVar10,0);
      if (((*(byte *)(_class_Singleton<WorldMapControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<WorldMapControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_Singleton<WorldMapControlFlow>_TypeInfo);
      }
      lVar11 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<WorldMapControlFlow>_get_instance__);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      uVar4 = WorldMapControlFlow__get_controlTownId(lVar11,0);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      lVar10 = UserTowns__get_Item(lVar10,uVar4,0);
      if (lVar10 == 0) {
        return;
      }
      auVar16 = UserTown__get_limitTime(lVar10,0);
    }
    uVar4 = 300;
code_r0x01788cdc:
    if (((*(byte *)(_class_System_DateTime_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_System_DateTime_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar5 = System_DateTime__op_Equality
                      (0,auVar16._0_8_,auVar16._8_8_,
                       *(undefined8 *)(*(long *)(_class_System_DateTime_TypeInfo + 0xb8) + 0x10),
                       *(undefined8 *)(*(long *)(_class_System_DateTime_TypeInfo + 0xb8) + 0x18),0);
    if ((uVar5 & 1) == 0) {
      if (((*(byte *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar10 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<UIComponents>_get_instance__);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      lVar10 = UIComponents__get_Item(lVar10,_str_BattleTimer,0);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
      }
      uVar6 = UnityEngine_Object__op_Implicit(0,lVar10,0);
      if ((uVar6 & 1) != 0) {
        if (lVar10 == 0) {
          func_0x010eec64(0);
          UnityEngine_GameObject__SetActive(0,1,0);
          func_0x010eec64(0);
        }
        else {
          UnityEngine_GameObject__SetActive(lVar10,1,0);
        }
        lVar10 = UnityEngine_GameObject__GetComponent<object>
                           (lVar10,_mm_Method_UnityEngine_GameObject_GetComponent<BattleTimer>__);
        *(long *)(lVar9 + 600) = lVar10;
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        BattleTimer__StartTimer(lVar10,auVar16._0_8_,auVar16._8_8_,1,0,0);
        uVar8 = _mm_Method_ControlFlow_OnAlarm__;
        lVar10 = *(long *)(lVar9 + 600);
        uVar13 = func_0x010fdda8(_class_System_Action<int>_TypeInfo);
        System_Action<int>___ctor(uVar13,lVar9,uVar8,_mm_Method_System_Action<int>__ctor__);
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        BattleTimer__AddAlarm(lVar10,uVar4,uVar13,0);
        uVar8 = _mm_Method_ControlFlow_OnTimeOver__;
        lVar10 = *(long *)(lVar9 + 600);
        uVar13 = func_0x010fdda8(_class_System_Action_TypeInfo);
        System_Action___ctor(uVar13,lVar9,uVar8,0);
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        *(undefined8 *)(lVar10 + 0x78) = uVar13;
      }
      if (((*(byte *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar10 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<UIComponents>_get_instance__);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      lVar10 = UIComponents__get_Item(lVar10,_str_TopUserSpellGauge,0);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
      }
      uVar6 = UnityEngine_Object__op_Implicit(0,lVar10,0);
      if ((uVar6 & 1) != 0) {
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        UnityEngine_GameObject__SetActive(lVar10,1,0);
      }
      if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar10 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      uVar5 = SceneDataPipe__get_isCompetitionMode(lVar10,0);
      uVar8 = _mm_Method_ControlFlow_<SetBattleTimer>m__8__;
      if ((uVar5 & 1) == 0) {
        uVar13 = func_0x010fdda8(_class_System_Action_TypeInfo);
        System_Action___ctor(uVar13,lVar9,uVar8,0);
        ControlFlow__add_OnTurnOwnerChanged(lVar9,uVar13);
      }
    }
    return;
  }
  if ((bRam00000000049f7ad5 & 1) == 0) {
    func_0x010c65d4(0x8a8a);
    bRam00000000049f7ad5 = 1;
  }
  if (*(char *)(param_1 + 0x141) == '\0') {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = Multiplay_MultiplayManager__get_isContinuedBattle();
  if ((uVar5 & 1) != 0) {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar5 & 1) != 0) {
    return;
  }
  plVar7 = *(long **)(param_1 + 0x40);
  if (plVar7 == (long *)0x0) {
code_r0x013a22a0:
    uVar4 = 8;
  }
  else {
    iVar2 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
    if (0 < iVar2) {
      if (*(long *)(param_1 + 0x40) == 0) goto code_r0x013a22a0;
      if ((*(long *)(param_1 + 0x48) != 0) &&
         (lVar9 = BattlePlay_BattlePlayData__get_opponent(*(long *)(param_1 + 0x48),0), lVar9 != 0))
      {
        lVar9 = *(long *)(param_1 + 0x48);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        uVar8 = BattlePlay_BattlePlayData__get_battleKey(lVar9,0);
        plVar7 = *(long **)(param_1 + 0x40);
        if (plVar7 == (long *)0x0) {
          uVar4 = 8;
        }
        else {
          uVar4 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
        }
        uVar13 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
        <>__AnonType18<string,_bool,_bool,_Step>___ctor
                  (uVar13,uVar8,1,0,uVar4,
                   _mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__);
        goto code_r0x013a22d4;
      }
    }
    plVar7 = *(long **)(param_1 + 0x40);
    if (plVar7 == (long *)0x0) goto code_r0x013a22a0;
    uVar4 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
  }
  uVar13 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
  <>__AnonType19<Step,_bool,_bool>___ctor
            (uVar13,uVar4,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
code_r0x013a22d4:
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar13 = LitJson_JsonMapper__ToJson(0,uVar13,0);
  uVar8 = _str_cancelMatchmaking;
  uVar6 = MyUserData__get_hasInstance(0,0);
  if ((uVar6 & 1) != 0) {
    lVar9 = MyUserData__get_instance(0,0);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar9 + 0x268) != '\0') {
      lVar9 = *(long *)(param_1 + 0x30);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar8,uVar13,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar8 = System_String__Format(0,_str____0____1__,uVar8,uVar13,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar13 = LitJson_JsonMapper__ToObject(0,uVar8,0);
      lVar11 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar8 = _str__;
      System_Object___ctor(lVar11,0);
      *(undefined4 *)(lVar11 + 0x14) = 2;
      *(undefined4 *)(lVar11 + 0x18) = 0;
      *(undefined8 *)(lVar11 + 0x30) = uVar13;
      *(undefined8 *)(lVar11 + 0x20) = uVar8;
      *(undefined4 *)(lVar11 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar11 + 0x10) = 4;
      lVar10 = lVar9;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar10 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar9 + 0x48);
      if (*(long *)(lVar9 + 0x50) == 0) {
        lVar10 = func_0x010eec64(0);
      }
      uVar8 = SocketIO_Encoder__Encode(lVar10,lVar11);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar8,0);
      return;
    }
  }
  uVar6 = Multiplay_MultiplayManager__get_isConnected(param_1);
  if ((uVar6 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(param_1 + 0x70) = 0;
  *(undefined1 *)(param_1 + 0x141) = 0;
  *(undefined1 *)(param_1 + 0x142) = 0;
  uVar8 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = UnityEngine_Object__op_Inequality(0,uVar8,0,0);
  if ((uVar6 & 1) != 0) {
    lVar9 = *(long *)(param_1 + 0x30);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar9,0);
    uVar8 = *(undefined8 *)(param_1 + 0x30);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    if ((bRam0000000004a04120 & 1) == 0) {
      func_0x010c65d4(0x906e,uVar8,0);
      bRam0000000004a04120 = 1;
    }
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    pcVar14 = pcRam0000000004a04118;
    if ((pcRam0000000004a04118 == (code *)0x0) &&
       (pcVar14 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar14 == (code *)0x0)) {
      uVar13 = func_0x010f000c(&UNK_039a20c7);
      func_0x010eebec(uVar13,0,0);
    }
    pcRam0000000004a04118 = pcVar14;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*pcRam0000000004a04118)(uVar8,0);
    return;
  }
  return;
}


