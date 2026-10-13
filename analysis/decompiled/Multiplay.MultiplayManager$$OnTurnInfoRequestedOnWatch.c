// name: Multiplay.MultiplayManager$$OnTurnInfoRequestedOnWatch
// entry: 013a9dd4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnTurnInfoRequestedOnWatch(long param_1,long param_2)

{
  long lVar1;
  uint uVar2;
  int iVar3;
  undefined4 uVar4;
  undefined4 uVar5;
  ulong uVar6;
  long *plVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  long lVar11;
  long lVar12;
  code *pcVar13;
  long lVar14;
  undefined1 auVar15 [16];
  
  if ((bRam00000000049f7b16 & 1) == 0) {
    func_0x010c65d4(0x8a6b);
    bRam00000000049f7b16 = 1;
  }
  if ((*(long *)(param_1 + 0x40) == 0) || (*(long *)(param_1 + 0x48) == 0)) {
    return;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar10 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Util_TypeInfo);
  }
  uVar10 = Util__GetData(0,uVar10,_str_uid,0,0);
  if ((bRam00000000049f7ae7 & 1) == 0) {
    func_0x010c65d4(0x8a2c);
    bRam00000000049f7ae7 = 1;
  }
  if (*(char *)(param_1 + 0x141) == '\0') {
    return;
  }
  *(undefined1 *)(param_1 + 0x142) = 0;
  if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
    plVar7 = (long *)func_0x010fdda8(_class_LitJson_JsonWriter_TypeInfo);
    LitJson_JsonWriter___ctor(plVar7,0);
    lVar14 = *(long *)(param_1 + 0x48);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    BattlePlay_BattlePlayData__SerializeLastControlTurn(lVar14,plVar7,0);
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar14 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    uVar4 = ControlFlow__get_currentUnitTurnIdx(lVar14,0);
    lVar14 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    auVar15 = ControlFlow__get_expireTime(lVar14,0);
    if (plVar7 == (long *)0x0) {
      func_0x010eec64(0);
    }
    uVar8 = (**(code **)(*plVar7 + 0x160))(plVar7,*(undefined8 *)(*plVar7 + 0x168));
    plVar7 = *(long **)(param_1 + 0x40);
    if (plVar7 == (long *)0x0) {
      uVar5 = 8;
    }
    else {
      uVar5 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
    }
    uVar9 = func_0x010fdda8(_class_<>__AnonType33<ulong,_int,_DateTime,_string,_Step>_TypeInfo);
    <>__AnonType33<ulong,_int,_DateTime,_string,_Step>___ctor
              (uVar9,uVar10,uVar4,auVar15._0_8_,auVar15._8_8_,uVar8,uVar5,
               _mm_Method_<>__AnonType33<ulong,_int,_DateTime,_string,_Step>__ctor__);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar8 = LitJson_JsonMapper__ToJson(0,uVar9,0);
    uVar10 = _str_turnInfo;
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
  plVar7 = *(long **)(param_1 + 0x40);
  if (plVar7 == (long *)0x0) {
code_r0x013a22a0:
    uVar4 = 8;
code_r0x013a22a4:
    uVar10 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
    <>__AnonType19<Step,_bool,_bool>___ctor
              (uVar10,uVar4,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
  }
  else {
    iVar3 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
    if (iVar3 < 1) {
code_r0x013a2284:
      plVar7 = *(long **)(param_1 + 0x40);
      if (plVar7 == (long *)0x0) goto code_r0x013a22a0;
      uVar4 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
      goto code_r0x013a22a4;
    }
    if (*(long *)(param_1 + 0x40) == 0) goto code_r0x013a22a0;
    if ((*(long *)(param_1 + 0x48) == 0) ||
       (lVar14 = BattlePlay_BattlePlayData__get_opponent(*(long *)(param_1 + 0x48),0), lVar14 == 0))
    goto code_r0x013a2284;
    lVar14 = *(long *)(param_1 + 0x48);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    uVar8 = BattlePlay_BattlePlayData__get_battleKey(lVar14,0);
    plVar7 = *(long **)(param_1 + 0x40);
    if (plVar7 == (long *)0x0) {
      uVar4 = 8;
    }
    else {
      uVar4 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
    }
    uVar10 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
    <>__AnonType18<string,_bool,_bool,_Step>___ctor
              (uVar10,uVar8,1,0,uVar4,_mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__);
  }
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar8 = LitJson_JsonMapper__ToJson(0,uVar10,0);
  uVar10 = _str_cancelMatchmaking;
Multiplay_MultiplayManager__EmitSocketMessage:
  uVar6 = MyUserData__get_hasInstance(0,0);
  if ((uVar6 & 1) != 0) {
    lVar14 = MyUserData__get_instance(0,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar14 + 0x268) != '\0') {
      lVar14 = *(long *)(param_1 + 0x30);
      if (lVar14 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar10,uVar8,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = System_String__Format(0,_str____0____1__,uVar10,uVar8,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar8 = LitJson_JsonMapper__ToObject(0,uVar10,0);
      lVar11 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar10 = _str__;
      System_Object___ctor(lVar11,0);
      *(undefined4 *)(lVar11 + 0x14) = 2;
      *(undefined4 *)(lVar11 + 0x18) = 0;
      *(undefined8 *)(lVar11 + 0x30) = uVar8;
      *(undefined8 *)(lVar11 + 0x20) = uVar10;
      *(undefined4 *)(lVar11 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar11 + 0x10) = 4;
      lVar12 = lVar14;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar12 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar14 + 0x48);
      if (*(long *)(lVar14 + 0x50) == 0) {
        lVar12 = func_0x010eec64(0);
      }
      uVar10 = SocketIO_Encoder__Encode(lVar12,lVar11);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar10,0);
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
  uVar10 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = UnityEngine_Object__op_Inequality(0,uVar10,0,0);
  if ((uVar6 & 1) != 0) {
    lVar14 = *(long *)(param_1 + 0x30);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar14,0);
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
    pcVar13 = pcRam0000000004a04118;
    if ((pcRam0000000004a04118 == (code *)0x0) &&
       (pcVar13 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar13 == (code *)0x0)) {
      uVar8 = func_0x010f000c(&UNK_039a20c7);
      func_0x010eebec(uVar8,0,0);
    }
    pcRam0000000004a04118 = pcVar13;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*pcRam0000000004a04118)(uVar10,0);
    return;
  }
  return;
}


