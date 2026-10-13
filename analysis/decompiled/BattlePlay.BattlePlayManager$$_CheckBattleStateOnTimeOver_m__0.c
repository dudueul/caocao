// name: BattlePlay.BattlePlayManager$$<CheckBattleStateOnTimeOver>m__0
// entry: 0197b174

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__<CheckBattleStateOnTimeOver>m__0(long param_1)

{
  long lVar1;
  char cVar2;
  uint uVar3;
  int iVar4;
  undefined4 uVar5;
  long lVar6;
  long *plVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  ulong uVar10;
  code *pcVar11;
  long lVar12;
  long lVar13;
  undefined8 uVar14;
  
  if ((bRam00000000049fae1b & 1) == 0) {
    func_0x010c65d4(0x2071);
    bRam00000000049fae1b = 1;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar10 = Multiplay_MultiplayManager__get_isContinuedBattle(0,0);
  if ((uVar10 & 1) == 0) {
    return;
  }
  lVar12 = *(long *)(param_1 + 0x18);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  cVar2 = *(char *)(lVar12 + 0x30);
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  Multiplay_MultiplayManager__InitMode(0,0,cVar2 != '\0',0);
  if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar12 = Singleton<iTweenManager>__get_instance
                     (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  if ((bRam00000000049f7ae4 & 1) == 0) {
    func_0x010c65d4(0x8a22,0);
    bRam00000000049f7ae4 = 1;
  }
  if (*(char *)(lVar12 + 0x141) == '\0') {
    return;
  }
  *(undefined1 *)(lVar12 + 0x142) = 0;
  if ((*(long *)(lVar12 + 0x40) != 0) && (*(long *)(lVar12 + 0x48) != 0)) {
    if (((*(byte *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<BattlePlayManager>_get_instance__);
    lVar13 = *(long *)(lVar12 + 0x48);
    if (lVar13 == 0) {
      func_0x010eec64(0);
    }
    lVar13 = BattlePlay_BattlePlayData__get_host(lVar13,0);
    if (lVar13 == 0) {
      func_0x010eec64(0);
    }
    uVar14 = *(undefined8 *)(lVar13 + 0x10);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    BattlePlay_BattlePlayManager__ResetUnitTurnTime(lVar6,uVar14,1,0);
    lVar6 = *(long *)(lVar12 + 0x48);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = BattlePlay_BattlePlayData__get_host(lVar6,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    *(undefined1 *)(lVar6 + 0xa9) = 1;
    plVar7 = (long *)func_0x010fdda8(_class_LitJson_JsonWriter_TypeInfo);
    uVar14 = LitJson_JsonWriter___ctor(plVar7,0);
    Multiplay_Battle__Serialize(uVar14,plVar7);
    lVar6 = *(long *)(lVar12 + 0x48);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar14 = BattlePlay_BattlePlayData__get_battleKey(lVar6,0);
    if (plVar7 == (long *)0x0) {
      func_0x010eec64(0);
    }
    uVar8 = (**(code **)(*plVar7 + 0x160))(plVar7,*(undefined8 *)(*plVar7 + 0x168));
    plVar7 = *(long **)(lVar12 + 0x40);
    if (plVar7 == (long *)0x0) {
      uVar5 = 8;
    }
    else {
      uVar5 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
    }
    uVar9 = func_0x010fdda8(_class_<>__AnonType30<string,_string,_Step>_TypeInfo);
    <>__AnonType30<string,_string,_Step>___ctor
              (uVar9,uVar14,uVar8,uVar5,_mm_Method_<>__AnonType30<string,_string,_Step>__ctor__);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar8 = LitJson_JsonMapper__ToJson(0,uVar9,0);
    uVar14 = _str_continueBattle;
    goto Multiplay_MultiplayManager__EmitSocketMessage;
  }
  if ((bRam00000000049f7ad5 & 1) == 0) {
    func_0x010c65d4(0x8a8a);
    bRam00000000049f7ad5 = 1;
  }
  if (*(char *)(lVar12 + 0x141) == '\0') {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = Multiplay_MultiplayManager__get_isContinuedBattle();
  if ((uVar3 & 1) != 0) {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar3 & 1) != 0) {
    return;
  }
  plVar7 = *(long **)(lVar12 + 0x40);
  if (plVar7 == (long *)0x0) {
code_r0x013a22a0:
    uVar5 = 8;
code_r0x013a22a4:
    uVar14 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
    <>__AnonType19<Step,_bool,_bool>___ctor
              (uVar14,uVar5,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
  }
  else {
    iVar4 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
    if (iVar4 < 1) {
code_r0x013a2284:
      plVar7 = *(long **)(lVar12 + 0x40);
      if (plVar7 == (long *)0x0) goto code_r0x013a22a0;
      uVar5 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
      goto code_r0x013a22a4;
    }
    if (*(long *)(lVar12 + 0x40) == 0) goto code_r0x013a22a0;
    if ((*(long *)(lVar12 + 0x48) == 0) ||
       (lVar6 = BattlePlay_BattlePlayData__get_opponent(*(long *)(lVar12 + 0x48),0), lVar6 == 0))
    goto code_r0x013a2284;
    lVar6 = *(long *)(lVar12 + 0x48);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar8 = BattlePlay_BattlePlayData__get_battleKey(lVar6,0);
    plVar7 = *(long **)(lVar12 + 0x40);
    if (plVar7 == (long *)0x0) {
      uVar5 = 8;
    }
    else {
      uVar5 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
    }
    uVar14 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
    <>__AnonType18<string,_bool,_bool,_Step>___ctor
              (uVar14,uVar8,1,0,uVar5,_mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__);
  }
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar8 = LitJson_JsonMapper__ToJson(0,uVar14,0);
  uVar14 = _str_cancelMatchmaking;
Multiplay_MultiplayManager__EmitSocketMessage:
  uVar10 = MyUserData__get_hasInstance(0,0);
  if ((uVar10 & 1) != 0) {
    lVar6 = MyUserData__get_instance(0,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar6 + 0x268) != '\0') {
      lVar12 = *(long *)(lVar12 + 0x30);
      if (lVar12 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar14,uVar8,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar14 = System_String__Format(0,_str____0____1__,uVar14,uVar8,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar8 = LitJson_JsonMapper__ToObject(0,uVar14,0);
      lVar13 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar14 = _str__;
      System_Object___ctor(lVar13,0);
      *(undefined4 *)(lVar13 + 0x14) = 2;
      *(undefined4 *)(lVar13 + 0x18) = 0;
      *(undefined8 *)(lVar13 + 0x30) = uVar8;
      *(undefined8 *)(lVar13 + 0x20) = uVar14;
      *(undefined4 *)(lVar13 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar13 + 0x10) = 4;
      lVar6 = lVar12;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar6 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar12 + 0x48);
      if (*(long *)(lVar12 + 0x50) == 0) {
        lVar6 = func_0x010eec64(0);
      }
      uVar14 = SocketIO_Encoder__Encode(lVar6,lVar13);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar14,0);
      return;
    }
  }
  uVar10 = Multiplay_MultiplayManager__get_isConnected(lVar12);
  if ((uVar10 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(lVar12 + 0x70) = 0;
  *(undefined1 *)(lVar12 + 0x141) = 0;
  *(undefined1 *)(lVar12 + 0x142) = 0;
  uVar14 = *(undefined8 *)(lVar12 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar10 = UnityEngine_Object__op_Inequality(0,uVar14,0,0);
  if ((uVar10 & 1) != 0) {
    lVar6 = *(long *)(lVar12 + 0x30);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar6,0);
    uVar14 = *(undefined8 *)(lVar12 + 0x30);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    if ((bRam0000000004a04120 & 1) == 0) {
      func_0x010c65d4(0x906e,uVar14,0);
      bRam0000000004a04120 = 1;
    }
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    pcVar11 = pcRam0000000004a04118;
    if ((pcRam0000000004a04118 == (code *)0x0) &&
       (pcVar11 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar11 == (code *)0x0)) {
      uVar8 = func_0x010f000c(&UNK_039a20c7);
      func_0x010eebec(uVar8,0,0);
    }
    pcRam0000000004a04118 = pcVar11;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*pcRam0000000004a04118)(uVar14,0);
    return;
  }
  return;
}


