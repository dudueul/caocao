// name: Multiplay.MultiplayManager$$OnRegistered
// entry: 013a6f34

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnRegistered(long param_1,long param_2)

{
  long lVar1;
  char cVar2;
  byte bVar3;
  uint uVar4;
  int iVar5;
  int iVar6;
  undefined4 uVar7;
  undefined8 uVar8;
  long lVar9;
  undefined8 uVar10;
  ulong uVar11;
  long *plVar12;
  long lVar13;
  long lVar14;
  code *pcVar15;
  undefined1 auVar16 [16];
  
  if ((bRam00000000049f7b03 & 1) == 0) {
    func_0x010c65d4(0x8a65);
    bRam00000000049f7b03 = 1;
  }
  *(undefined2 *)(param_1 + 0x141) = 1;
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar9 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  lVar9 = LitJson_JsonData__get_Item(lVar9,_str_dodgePaneltyTime,0);
  if (lVar9 != 0) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar10 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (((*(byte *)(_class_System_DateTime_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_System_DateTime_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_System_DateTime_TypeInfo);
    }
    auVar16 = System_DateTime__get_Now(0,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    auVar16 = Util__GetData(0,uVar10,_str_dodgePaneltyTime,auVar16._0_8_,auVar16._8_8_,0);
    *(undefined1 (*) [16])(param_1 + 0x168) = auVar16;
    PanelCompetitionLobby__CallUpdateDodgePaneltyTime(0,0);
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar9 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  lVar9 = LitJson_JsonData__get_Item(lVar9,_str_pingInterval,0);
  if (lVar9 != 0) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    lVar9 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    lVar9 = LitJson_JsonData__get_Item(lVar9,_str_pingTimeout,0);
    if (lVar9 != 0) {
      if (param_2 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = SocketIO_SocketIOEvent__get_data(param_2,0);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_Util_TypeInfo);
      }
      iVar5 = Util__GetData(0,uVar10,_str_pingInterval,5,0);
      if (param_2 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = SocketIO_SocketIOEvent__get_data(param_2,0);
      iVar6 = Util__GetData(0,uVar10,_str_pingTimeout,5,0);
      lVar9 = *(long *)(param_1 + 0x30);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__ResetPingTime((double)iVar6 / 1000.0,lVar9,iVar5 / 1000,0);
      PanelCompetitionLobby__CallUpdateDodgePaneltyTime(0,0);
    }
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar9 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  lVar9 = LitJson_JsonData__get_Item(lVar9,_str_allowWatch,0);
  if (lVar9 != 0) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar10 = SocketIO_SocketIOEvent__get_data(param_2,0);
    cVar2 = *(char *)(param_1 + 0x140);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Util_TypeInfo);
    }
    bVar3 = Util__GetData(0,uVar10,_str_allowWatch,cVar2 != '\0',0);
    *(byte *)(param_1 + 0x140) = bVar3 & 1;
    PanelCompetitionLobby__CallUpdateAllowWatch(0,0);
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar11 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar11 & 1) != 0) {
    Multiplay_MultiplayManager__LeaveWatch(param_1);
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar9 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  lVar9 = LitJson_JsonData__get_Item(lVar9,_str_TimeOverDesync,0);
  if (lVar9 != 0) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar10 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Util_TypeInfo);
    }
    uVar7 = Util__GetData(0,uVar10,_str_TimeOverDesync,0x3c,0);
    *(undefined4 *)(param_1 + 0x60) = uVar7;
  }
  if (*(long *)(param_1 + 0x78) != 0) {
    System_Action__Invoke(*(long *)(param_1 + 0x78),0);
  }
  plVar12 = *(long **)(param_1 + 0x40);
  if (((plVar12 == (long *)0x0) || (*(long *)(param_1 + 0x48) == 0)) ||
     (iVar5 = (**(code **)(*plVar12 + 0x170))(plVar12,*(undefined8 *)(*plVar12 + 0x178)), iVar5 != 6
     )) {
    return;
  }
  lVar9 = *(long *)(param_1 + 0x48);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = BattlePlay_BattlePlayData__get_controlIdx(lVar9,0);
  if ((bRam00000000049f7aee & 1) == 0) {
    func_0x010c65d4(0x8a2a,uVar7);
    bRam00000000049f7aee = 1;
  }
  if (*(char *)(param_1 + 0x141) == '\0') {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar11 = Multiplay_MultiplayManager__get_isMyActiveBattle();
  if ((uVar11 & 1) == 0) {
    return;
  }
  if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
    uVar11 = Multiplay_MultiplayManager__get_isConnected(param_1);
    if ((uVar11 & 1) == 0) {
      return;
    }
    lVar9 = *(long *)(param_1 + 0x48);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    uVar10 = BattlePlay_BattlePlayData__get_battleKey(lVar9,0);
    uVar8 = func_0x010fdda8(_class_<>__AnonType38<string,_int>_TypeInfo);
    <>__AnonType38<string,_int>___ctor
              (uVar8,uVar10,uVar7,_mm_Method_<>__AnonType38<string,_int>__ctor__);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar8 = LitJson_JsonMapper__ToJson(0,uVar8,0);
    uVar10 = _str_requestMissedControl;
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
  plVar12 = *(long **)(param_1 + 0x40);
  if (plVar12 == (long *)0x0) {
code_r0x013a22a0:
    uVar7 = 8;
code_r0x013a22a4:
    uVar10 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
    <>__AnonType19<Step,_bool,_bool>___ctor
              (uVar10,uVar7,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
  }
  else {
    iVar5 = (**(code **)(*plVar12 + 0x170))(plVar12,*(undefined8 *)(*plVar12 + 0x178));
    if (iVar5 < 1) {
code_r0x013a2284:
      plVar12 = *(long **)(param_1 + 0x40);
      if (plVar12 == (long *)0x0) goto code_r0x013a22a0;
      uVar7 = (**(code **)(*plVar12 + 0x170))(plVar12,*(undefined8 *)(*plVar12 + 0x178));
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
    uVar8 = BattlePlay_BattlePlayData__get_battleKey(lVar9,0);
    plVar12 = *(long **)(param_1 + 0x40);
    if (plVar12 == (long *)0x0) {
      uVar7 = 8;
    }
    else {
      uVar7 = (**(code **)(*plVar12 + 0x170))(plVar12,*(undefined8 *)(*plVar12 + 0x178));
    }
    uVar10 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
    <>__AnonType18<string,_bool,_bool,_Step>___ctor
              (uVar10,uVar8,1,0,uVar7,_mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__);
  }
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar8 = LitJson_JsonMapper__ToJson(0,uVar10,0);
  uVar10 = _str_cancelMatchmaking;
Multiplay_MultiplayManager__EmitSocketMessage:
  uVar11 = MyUserData__get_hasInstance(0,0);
  if ((uVar11 & 1) != 0) {
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
      lVar13 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar10 = _str__;
      System_Object___ctor(lVar13,0);
      *(undefined4 *)(lVar13 + 0x14) = 2;
      *(undefined4 *)(lVar13 + 0x18) = 0;
      *(undefined8 *)(lVar13 + 0x30) = uVar8;
      *(undefined8 *)(lVar13 + 0x20) = uVar10;
      *(undefined4 *)(lVar13 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar13 + 0x10) = 4;
      lVar14 = lVar9;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar14 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar9 + 0x48);
      if (*(long *)(lVar9 + 0x50) == 0) {
        lVar14 = func_0x010eec64(0);
      }
      uVar10 = SocketIO_Encoder__Encode(lVar14,lVar13);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar10,0);
      return;
    }
  }
  uVar11 = Multiplay_MultiplayManager__get_isConnected(param_1);
  if ((uVar11 & 1) == 0) {
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
  uVar11 = UnityEngine_Object__op_Inequality(0,uVar10,0,0);
  if ((uVar11 & 1) != 0) {
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
    pcVar15 = pcRam0000000004a04118;
    if ((pcRam0000000004a04118 == (code *)0x0) &&
       (pcVar15 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar15 == (code *)0x0)) {
      uVar8 = func_0x010f000c(&UNK_039a20c7);
      func_0x010eebec(uVar8,0,0);
    }
    pcRam0000000004a04118 = pcVar15;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*pcRam0000000004a04118)(uVar10,0);
    return;
  }
  return;
}


