// name: Multiplay.MultiplayManager$$EmitRequestMissedControl
// entry: 013a48f0

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__EmitRequestMissedControl(long param_1,undefined4 param_2)

{
  long lVar1;
  uint uVar2;
  int iVar3;
  undefined4 uVar4;
  long *plVar5;
  ulong uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  long lVar9;
  long lVar10;
  code *pcVar11;
  long lVar12;
  
  if ((bRam00000000049f7aee & 1) == 0) {
    func_0x010c65d4(0x8a2a);
    bRam00000000049f7aee = 1;
  }
  if (*(char *)(param_1 + 0x141) == '\0') {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = Multiplay_MultiplayManager__get_isMyActiveBattle();
  if ((uVar6 & 1) == 0) {
    return;
  }
  if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
    uVar6 = Multiplay_MultiplayManager__get_isConnected(param_1);
    if ((uVar6 & 1) == 0) {
      return;
    }
    lVar12 = *(long *)(param_1 + 0x48);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = BattlePlay_BattlePlayData__get_battleKey(lVar12,0);
    uVar8 = func_0x010fdda8(_class_<>__AnonType38<string,_int>_TypeInfo);
    <>__AnonType38<string,_int>___ctor
              (uVar8,uVar7,param_2,_mm_Method_<>__AnonType38<string,_int>__ctor__);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar8 = LitJson_JsonMapper__ToJson(0,uVar8,0);
    uVar7 = _str_requestMissedControl;
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
  plVar5 = *(long **)(param_1 + 0x40);
  if (plVar5 == (long *)0x0) {
code_r0x013a22a0:
    uVar4 = 8;
code_r0x013a22a4:
    uVar7 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
    <>__AnonType19<Step,_bool,_bool>___ctor
              (uVar7,uVar4,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
  }
  else {
    iVar3 = (**(code **)(*plVar5 + 0x170))(plVar5,*(undefined8 *)(*plVar5 + 0x178));
    if (iVar3 < 1) {
code_r0x013a2284:
      plVar5 = *(long **)(param_1 + 0x40);
      if (plVar5 == (long *)0x0) goto code_r0x013a22a0;
      uVar4 = (**(code **)(*plVar5 + 0x170))(plVar5,*(undefined8 *)(*plVar5 + 0x178));
      goto code_r0x013a22a4;
    }
    if (*(long *)(param_1 + 0x40) == 0) goto code_r0x013a22a0;
    if ((*(long *)(param_1 + 0x48) == 0) ||
       (lVar12 = BattlePlay_BattlePlayData__get_opponent(*(long *)(param_1 + 0x48),0), lVar12 == 0))
    goto code_r0x013a2284;
    lVar12 = *(long *)(param_1 + 0x48);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    uVar8 = BattlePlay_BattlePlayData__get_battleKey(lVar12,0);
    plVar5 = *(long **)(param_1 + 0x40);
    if (plVar5 == (long *)0x0) {
      uVar4 = 8;
    }
    else {
      uVar4 = (**(code **)(*plVar5 + 0x170))(plVar5,*(undefined8 *)(*plVar5 + 0x178));
    }
    uVar7 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
    <>__AnonType18<string,_bool,_bool,_Step>___ctor
              (uVar7,uVar8,1,0,uVar4,_mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__);
  }
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar8 = LitJson_JsonMapper__ToJson(0,uVar7,0);
  uVar7 = _str_cancelMatchmaking;
Multiplay_MultiplayManager__EmitSocketMessage:
  uVar6 = MyUserData__get_hasInstance(0,0);
  if ((uVar6 & 1) != 0) {
    lVar12 = MyUserData__get_instance(0,0);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar12 + 0x268) != '\0') {
      lVar12 = *(long *)(param_1 + 0x30);
      if (lVar12 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar7,uVar8,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar7 = System_String__Format(0,_str____0____1__,uVar7,uVar8,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar8 = LitJson_JsonMapper__ToObject(0,uVar7,0);
      lVar9 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar7 = _str__;
      System_Object___ctor(lVar9,0);
      *(undefined4 *)(lVar9 + 0x14) = 2;
      *(undefined4 *)(lVar9 + 0x18) = 0;
      *(undefined8 *)(lVar9 + 0x30) = uVar8;
      *(undefined8 *)(lVar9 + 0x20) = uVar7;
      *(undefined4 *)(lVar9 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar9 + 0x10) = 4;
      lVar10 = lVar12;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar10 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar12 + 0x48);
      if (*(long *)(lVar12 + 0x50) == 0) {
        lVar10 = func_0x010eec64(0);
      }
      uVar7 = SocketIO_Encoder__Encode(lVar10,lVar9);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar7,0);
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
  uVar7 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = UnityEngine_Object__op_Inequality(0,uVar7,0,0);
  if ((uVar6 & 1) != 0) {
    lVar12 = *(long *)(param_1 + 0x30);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar12,0);
    uVar7 = *(undefined8 *)(param_1 + 0x30);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    if ((bRam0000000004a04120 & 1) == 0) {
      func_0x010c65d4(0x906e,uVar7,0);
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
    (*pcRam0000000004a04118)(uVar7,0);
    return;
  }
  return;
}


