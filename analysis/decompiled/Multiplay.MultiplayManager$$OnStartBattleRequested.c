// name: Multiplay.MultiplayManager$$OnStartBattleRequested
// entry: 013a8fa8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnStartBattleRequested(long param_1,long param_2)

{
  long lVar1;
  int iVar2;
  uint uVar3;
  undefined4 uVar4;
  ulong uVar5;
  long *plVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  undefined8 uVar9;
  long lVar10;
  long lVar11;
  code *pcVar12;
  long lVar13;
  
  if ((bRam00000000049f7b0f & 1) == 0) {
    func_0x010c65d4(0x8a67);
    bRam00000000049f7b0f = 1;
  }
  *(undefined1 *)(param_1 + 0x142) = 0;
  if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar9 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Util_TypeInfo);
    }
    lVar10 = Util__GetData(0,uVar9,_str_uid,0,0);
    lVar13 = *(long *)(param_1 + 0x48);
    if (lVar13 == 0) {
      func_0x010eec64(0);
    }
    lVar13 = BattlePlay_BattlePlayData__get_opponent(lVar13,0);
    if (lVar13 != 0) {
      lVar13 = *(long *)(param_1 + 0x48);
      if (lVar13 == 0) {
        func_0x010eec64(0);
      }
      lVar13 = BattlePlay_BattlePlayData__get_opponent(lVar13,0);
      if (lVar13 == 0) {
        func_0x010eec64(0);
      }
      if (*(long *)(lVar13 + 0x10) != lVar10) {
        return;
      }
      if ((bRam00000000049f7ae2 & 1) == 0) {
        func_0x010c65d4(0x8aa1);
        bRam00000000049f7ae2 = 1;
      }
      if (*(char *)(param_1 + 0x141) == '\0') {
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
      *(undefined1 *)(param_1 + 0x142) = 0;
      if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
        lVar10 = BattlePlay_BattlePlayData__get_host(*(long *)(param_1 + 0x48),0);
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        *(undefined1 *)(lVar10 + 0xa9) = 1;
        plVar6 = (long *)func_0x010fdda8(_class_LitJson_JsonWriter_TypeInfo);
        uVar9 = LitJson_JsonWriter___ctor(plVar6,0);
        Multiplay_Battle__Serialize(uVar9,plVar6);
        lVar10 = *(long *)(param_1 + 0x48);
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        uVar9 = BattlePlay_BattlePlayData__get_battleKey(lVar10,0);
        if (plVar6 == (long *)0x0) {
          func_0x010eec64(0);
        }
        uVar7 = (**(code **)(*plVar6 + 0x160))(plVar6,*(undefined8 *)(*plVar6 + 0x168));
        plVar6 = *(long **)(param_1 + 0x40);
        if (plVar6 == (long *)0x0) {
          uVar4 = 8;
        }
        else {
          uVar4 = (**(code **)(*plVar6 + 0x170))(plVar6,*(undefined8 *)(*plVar6 + 0x178));
        }
        uVar3 = Multiplay_MultiplayManager__IsStepComplete(param_1,6);
        uVar8 = func_0x010fdda8(_class_<>__AnonType28<string,_string,_Step,_bool>_TypeInfo);
        <>__AnonType28<string,_string,_Step,_bool>___ctor
                  (uVar8,uVar9,uVar7,uVar4,uVar3 & 1,
                   _mm_Method_<>__AnonType28<string,_string,_Step,_bool>__ctor__);
        if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar7 = LitJson_JsonMapper__ToJson(0,uVar8,0);
        uVar9 = _str_startBattle;
        goto Multiplay_MultiplayManager__EmitSocketMessage;
      }
    }
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
  plVar6 = *(long **)(param_1 + 0x40);
  if (plVar6 == (long *)0x0) {
code_r0x013a22a0:
    uVar4 = 8;
code_r0x013a22a4:
    uVar9 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
    <>__AnonType19<Step,_bool,_bool>___ctor
              (uVar9,uVar4,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
  }
  else {
    iVar2 = (**(code **)(*plVar6 + 0x170))(plVar6,*(undefined8 *)(*plVar6 + 0x178));
    if (iVar2 < 1) {
code_r0x013a2284:
      plVar6 = *(long **)(param_1 + 0x40);
      if (plVar6 == (long *)0x0) goto code_r0x013a22a0;
      uVar4 = (**(code **)(*plVar6 + 0x170))(plVar6,*(undefined8 *)(*plVar6 + 0x178));
      goto code_r0x013a22a4;
    }
    if (*(long *)(param_1 + 0x40) == 0) goto code_r0x013a22a0;
    if ((*(long *)(param_1 + 0x48) == 0) ||
       (lVar10 = BattlePlay_BattlePlayData__get_opponent(*(long *)(param_1 + 0x48),0), lVar10 == 0))
    goto code_r0x013a2284;
    lVar10 = *(long *)(param_1 + 0x48);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = BattlePlay_BattlePlayData__get_battleKey(lVar10,0);
    plVar6 = *(long **)(param_1 + 0x40);
    if (plVar6 == (long *)0x0) {
      uVar4 = 8;
    }
    else {
      uVar4 = (**(code **)(*plVar6 + 0x170))(plVar6,*(undefined8 *)(*plVar6 + 0x178));
    }
    uVar9 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
    <>__AnonType18<string,_bool,_bool,_Step>___ctor
              (uVar9,uVar7,1,0,uVar4,_mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__);
  }
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar7 = LitJson_JsonMapper__ToJson(0,uVar9,0);
  uVar9 = _str_cancelMatchmaking;
Multiplay_MultiplayManager__EmitSocketMessage:
  uVar5 = MyUserData__get_hasInstance(0,0);
  if ((uVar5 & 1) != 0) {
    lVar10 = MyUserData__get_instance(0,0);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar10 + 0x268) != '\0') {
      lVar10 = *(long *)(param_1 + 0x30);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar9,uVar7,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar9 = System_String__Format(0,_str____0____1__,uVar9,uVar7,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar7 = LitJson_JsonMapper__ToObject(0,uVar9,0);
      lVar11 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar9 = _str__;
      System_Object___ctor(lVar11,0);
      *(undefined4 *)(lVar11 + 0x14) = 2;
      *(undefined4 *)(lVar11 + 0x18) = 0;
      *(undefined8 *)(lVar11 + 0x30) = uVar7;
      *(undefined8 *)(lVar11 + 0x20) = uVar9;
      *(undefined4 *)(lVar11 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar11 + 0x10) = 4;
      lVar13 = lVar10;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar13 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar10 + 0x48);
      if (*(long *)(lVar10 + 0x50) == 0) {
        lVar13 = func_0x010eec64(0);
      }
      uVar9 = SocketIO_Encoder__Encode(lVar13,lVar11);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar9,0);
      return;
    }
  }
  uVar5 = Multiplay_MultiplayManager__get_isConnected(param_1);
  if ((uVar5 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(param_1 + 0x70) = 0;
  *(undefined1 *)(param_1 + 0x141) = 0;
  *(undefined1 *)(param_1 + 0x142) = 0;
  uVar9 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = UnityEngine_Object__op_Inequality(0,uVar9,0,0);
  if ((uVar5 & 1) != 0) {
    lVar10 = *(long *)(param_1 + 0x30);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar10,0);
    uVar9 = *(undefined8 *)(param_1 + 0x30);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    if ((bRam0000000004a04120 & 1) == 0) {
      func_0x010c65d4(0x906e,uVar9,0);
      bRam0000000004a04120 = 1;
    }
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    pcVar12 = pcRam0000000004a04118;
    if ((pcRam0000000004a04118 == (code *)0x0) &&
       (pcVar12 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar12 == (code *)0x0)) {
      uVar7 = func_0x010f000c(&UNK_039a20c7);
      func_0x010eebec(uVar7,0,0);
    }
    pcRam0000000004a04118 = pcVar12;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*pcRam0000000004a04118)(uVar9,0);
    return;
  }
  return;
}


