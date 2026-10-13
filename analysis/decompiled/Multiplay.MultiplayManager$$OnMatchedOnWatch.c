// name: Multiplay.MultiplayManager$$OnMatchedOnWatch
// entry: 013a7580

/* WARNING: Removing unreachable block (ram,0x0139f844) */
/* WARNING: Removing unreachable block (ram,0x0139f9b0) */
/* WARNING: Removing unreachable block (ram,0x0139fa0c) */
/* WARNING: Removing unreachable block (ram,0x0139fa24) */
/* WARNING: Removing unreachable block (ram,0x0139fa48) */
/* WARNING: Removing unreachable block (ram,0x0139fa50) */
/* WARNING: Removing unreachable block (ram,0x0139fa54) */
/* WARNING: Removing unreachable block (ram,0x0139fabc) */
/* WARNING: Removing unreachable block (ram,0x0139fac4) */
/* WARNING: Removing unreachable block (ram,0x016dd40c) */
/* WARNING: Removing unreachable block (ram,0x016dd414) */
/* WARNING: Removing unreachable block (ram,0x024fa398) */
/* WARNING: Removing unreachable block (ram,0x024fa3b0) */
/* WARNING: Removing unreachable block (ram,0x024fa3d0) */
/* WARNING: Removing unreachable block (ram,0x024fa3f8) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnMatchedOnWatch(long param_1,long param_2)

{
  long lVar1;
  undefined4 uVar2;
  int iVar3;
  uint uVar4;
  long *plVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  ulong uVar8;
  long lVar9;
  undefined8 uVar10;
  long lVar11;
  undefined8 uVar12;
  long lVar13;
  code *pcVar14;
  undefined8 *puVar15;
  
  if ((bRam00000000049f7b05 & 1) == 0) {
    func_0x010c65d4(0x8a5b);
    bRam00000000049f7b05 = 1;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar8 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar8 & 1) == 0) {
    return;
  }
  if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar4 = LoadedSceneCheckUtil__get_isCompetitionScene(0,0);
  if ((uVar4 & 1) == 0) {
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    *(undefined1 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x20) = 1;
    return;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar9 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  lVar9 = LitJson_JsonData__get_Item(lVar9,_str_battlePlayData,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  uVar10 = LitJson_JsonData__get_Item(lVar9,_str_key,0);
  uVar10 = LitJson_JsonData__op_Explicit(0,uVar10,0);
  lVar9 = CompetitionManager__get_instance(0,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  CompetitionManager__SetBattleMapInfoForWatch(lVar9,uVar10,0);
  if (((*(byte *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar9 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<BattlePlayManager>_get_instance__);
  uVar10 = *(undefined8 *)(param_1 + 0x148);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar11 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  uVar12 = LitJson_JsonData__get_Item(lVar11,_str_battlePlayData,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  BattlePlay_BattlePlayManager__InitBattleData(lVar9,uVar10,uVar12,0);
  lVar9 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<BattlePlayManager>_get_instance__);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  uVar10 = BattlePlay_BattlePlayManager__get_battlePlayData(lVar9,0);
  Multiplay_MultiplayManager__SetBattlePlayData(param_1,uVar10);
  lVar9 = *(long *)(param_1 + 0x48);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  uVar10 = BattlePlay_BattlePlayData__get_battleKey(lVar9,0);
  *(undefined8 *)(param_1 + 0x160) = uVar10;
  if ((bRam00000000049f7ac9 & 1) == 0) {
    func_0x010c65d4(0x8a36);
    bRam00000000049f7ac9 = 1;
  }
  plVar5 = *(long **)(param_1 + 0x40);
  if (plVar5 != (long *)0x0) {
    (**(code **)(*plVar5 + 0x180))(plVar5,*(undefined8 *)(*plVar5 + 0x188));
  }
                    /* WARNING: Could not find normalized switch variable to match jumptable */
  switch(0x139f8c0) {
  case 0:
                    /* WARNING: This code block may not be properly labeled as switch case */
    uVar12 = *(undefined8 *)(param_1 + 0x48);
    puVar15 = (undefined8 *)&class_Multiplay_Match_TypeInfo;
    break;
  case 1:
                    /* WARNING: This code block may not be properly labeled as switch case */
    uVar12 = *(undefined8 *)(param_1 + 0x48);
    uVar10 = func_0x010fdda8(_class_Multiplay_Pick_TypeInfo);
    Multiplay_Pick___ctor(uVar10,uVar12,0);
    *(undefined8 *)(param_1 + 0x40) = uVar10;
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar4 = Multiplay_MultiplayManager__get_isWatchingBattle();
    if ((uVar4 & 1) != 0) {
      return;
    }
    if ((bRam00000000049f7add & 1) == 0) {
      func_0x010c65d4(0x8a76);
      bRam00000000049f7add = 1;
    }
    if (*(char *)(param_1 + 0x141) == '\0') {
      return;
    }
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar4 = Multiplay_MultiplayManager__get_isWatchingBattle();
    if ((uVar4 & 1) != 0) {
      return;
    }
    if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
      plVar5 = (long *)func_0x010fdda8(_class_LitJson_JsonWriter_TypeInfo);
      LitJson_JsonWriter___ctor(plVar5,0);
      Multiplay_Pick__Serialize(0,plVar5,0);
      lVar9 = *(long *)(param_1 + 0x48);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = BattlePlay_BattlePlayData__get_battleKey(lVar9,0);
      lVar9 = *(long *)(param_1 + 0x48);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      lVar9 = BattlePlay_BattlePlayData__get_opponent(lVar9,0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      uVar12 = *(undefined8 *)(lVar9 + 0x10);
      if (plVar5 == (long *)0x0) {
        func_0x010eec64(0);
      }
      uVar6 = (**(code **)(*plVar5 + 0x160))(plVar5,*(undefined8 *)(*plVar5 + 0x168));
      plVar5 = *(long **)(param_1 + 0x40);
      if (plVar5 == (long *)0x0) {
        uVar2 = 8;
      }
      else {
        uVar2 = (**(code **)(*plVar5 + 0x170))(plVar5,*(undefined8 *)(*plVar5 + 0x178));
      }
      uVar4 = Multiplay_MultiplayManager__IsStepComplete(param_1,1);
      uVar7 = func_0x010fdda8(_class_<>__AnonType23<string,_ulong,_string,_Step,_bool>_TypeInfo);
      <>__AnonType23<string,_ulong,_string,_Step,_bool>___ctor
                (uVar7,uVar10,uVar12,uVar6,uVar2,uVar4 & 1,
                 _mm_Method_<>__AnonType23<string,_ulong,_string,_Step,_bool>__ctor__);
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar12 = LitJson_JsonMapper__ToJson(0,uVar7,0);
      uVar10 = _str_pickUnits;
      goto Multiplay_MultiplayManager__EmitSocketMessage;
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
    uVar4 = Multiplay_MultiplayManager__get_isContinuedBattle();
    if ((uVar4 & 1) != 0) {
      return;
    }
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar4 = Multiplay_MultiplayManager__get_isWatchingBattle();
    if ((uVar4 & 1) != 0) {
      return;
    }
    plVar5 = *(long **)(param_1 + 0x40);
    if (plVar5 == (long *)0x0) {
code_r0x013a22a0:
      uVar2 = 8;
code_r0x013a22a4:
      uVar10 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
      <>__AnonType19<Step,_bool,_bool>___ctor
                (uVar10,uVar2,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
    }
    else {
      iVar3 = (**(code **)(*plVar5 + 0x170))(plVar5,*(undefined8 *)(*plVar5 + 0x178));
      if (iVar3 < 1) {
code_r0x013a2284:
        plVar5 = *(long **)(param_1 + 0x40);
        if (plVar5 == (long *)0x0) goto code_r0x013a22a0;
        uVar2 = (**(code **)(*plVar5 + 0x170))(plVar5,*(undefined8 *)(*plVar5 + 0x178));
        goto code_r0x013a22a4;
      }
      if (*(long *)(param_1 + 0x40) == 0) goto code_r0x013a22a0;
      if ((*(long *)(param_1 + 0x48) == 0) ||
         (lVar9 = BattlePlay_BattlePlayData__get_opponent(*(long *)(param_1 + 0x48),0), lVar9 == 0))
      goto code_r0x013a2284;
      lVar9 = *(long *)(param_1 + 0x48);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      uVar12 = BattlePlay_BattlePlayData__get_battleKey(lVar9,0);
      plVar5 = *(long **)(param_1 + 0x40);
      if (plVar5 == (long *)0x0) {
        uVar2 = 8;
      }
      else {
        uVar2 = (**(code **)(*plVar5 + 0x170))(plVar5,*(undefined8 *)(*plVar5 + 0x178));
      }
      uVar10 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
      <>__AnonType18<string,_bool,_bool,_Step>___ctor
                (uVar10,uVar12,1,0,uVar2,_mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__
                );
    }
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar12 = LitJson_JsonMapper__ToJson(0,uVar10,0);
    uVar10 = _str_cancelMatchmaking;
Multiplay_MultiplayManager__EmitSocketMessage:
    uVar8 = MyUserData__get_hasInstance(0,0);
    if ((uVar8 & 1) != 0) {
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
        lVar13 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
        uVar10 = _str__;
        System_Object___ctor(lVar13,0);
        *(undefined4 *)(lVar13 + 0x14) = 2;
        *(undefined4 *)(lVar13 + 0x18) = 0;
        *(undefined8 *)(lVar13 + 0x30) = uVar12;
        *(undefined8 *)(lVar13 + 0x20) = uVar10;
        *(undefined4 *)(lVar13 + 0x28) = 0xffffffff;
        *(undefined4 *)(lVar13 + 0x10) = 4;
        lVar11 = lVar9;
        if ((bRam0000000004a0024f & 1) == 0) {
          lVar11 = func_0x010c65d4(0xc5cf);
          bRam0000000004a0024f = 1;
        }
        lVar1 = *(long *)(lVar9 + 0x48);
        if (*(long *)(lVar9 + 0x50) == 0) {
          lVar11 = func_0x010eec64(0);
        }
        uVar10 = SocketIO_Encoder__Encode(lVar11,lVar13);
        if (lVar1 == 0) {
          func_0x010eec64(0);
        }
        WebSocketSharp_WebSocket__Send(lVar1,uVar10,0);
        return;
      }
    }
    uVar8 = Multiplay_MultiplayManager__get_isConnected(param_1);
    if ((uVar8 & 1) == 0) {
      return;
    }
    if ((bRam00000000049f7acc & 1) == 0) {
      func_0x010c65d4(0x8a26);
      bRam00000000049f7acc = 1;
    }
    *(undefined8 *)(param_1 + 0x70) = 0;
    *(undefined1 *)(param_1 + 0x141) = 0;
    *(undefined1 *)(param_1 + 0x142) = 0;
    uVar10 = *(undefined8 *)(param_1 + 0x30);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar8 = UnityEngine_Object__op_Inequality(0,uVar10,0,0);
    if ((uVar8 & 1) != 0) {
      lVar9 = *(long *)(param_1 + 0x30);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__Close(lVar9,0);
      uVar10 = *(undefined8 *)(param_1 + 0x30);
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
      pcVar14 = pcRam0000000004a04118;
      if ((pcRam0000000004a04118 == (code *)0x0) &&
         (pcVar14 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar14 == (code *)0x0)) {
        uVar12 = func_0x010f000c(&UNK_039a20c7);
        func_0x010eebec(uVar12,0,0);
      }
      pcRam0000000004a04118 = pcVar14;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*pcRam0000000004a04118)(uVar10,0);
      return;
    }
    return;
  case 2:
                    /* WARNING: This code block may not be properly labeled as switch case */
    uVar12 = *(undefined8 *)(param_1 + 0x48);
    puVar15 = (undefined8 *)&class_Multiplay_Ban_TypeInfo;
    break;
  case 3:
                    /* WARNING: This code block may not be properly labeled as switch case */
    uVar12 = *(undefined8 *)(param_1 + 0x48);
    puVar15 = (undefined8 *)&class_Multiplay_Formation_TypeInfo;
    break;
  case 4:
                    /* WARNING: This code block may not be properly labeled as switch case */
    uVar12 = *(undefined8 *)(param_1 + 0x48);
    uVar10 = func_0x010fdda8(_class_Multiplay_Order_TypeInfo);
    Multiplay_Order___ctor(uVar10,uVar12,0);
    goto code_r0x0139f99c;
  case 5:
                    /* WARNING: This code block may not be properly labeled as switch case */
    uVar12 = *(undefined8 *)(param_1 + 0x48);
    uVar10 = func_0x010fdda8(_class_Multiplay_Ready_TypeInfo);
    Multiplay_Ready___ctor(uVar10,uVar12,0);
    goto code_r0x0139f99c;
  case 6:
                    /* WARNING: This code block may not be properly labeled as switch case */
    uVar12 = *(undefined8 *)(param_1 + 0x48);
    puVar15 = (undefined8 *)&class_Multiplay_Battle_TypeInfo;
    break;
  case 7:
                    /* WARNING: This code block may not be properly labeled as switch case */
    uVar12 = *(undefined8 *)(param_1 + 0x48);
    uVar10 = func_0x010fdda8(_class_Multiplay_Result_TypeInfo);
    Multiplay_Result___ctor(uVar10,uVar12,0);
    goto code_r0x0139f99c;
  }
  uVar10 = func_0x010fdda8(*puVar15);
  Multiplay_StepBase___ctor(uVar10,uVar12,0);
code_r0x0139f99c:
  *(undefined8 *)(param_1 + 0x40) = uVar10;
  return;
}


