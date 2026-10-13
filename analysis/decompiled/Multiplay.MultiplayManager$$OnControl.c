// name: Multiplay.MultiplayManager$$OnControl
// entry: 013a90f4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnControl(long param_1,long param_2)

{
  long lVar1;
  uint uVar2;
  undefined4 uVar3;
  int iVar4;
  ulong uVar5;
  undefined8 uVar6;
  long *plVar7;
  undefined8 uVar8;
  long lVar9;
  long lVar10;
  code *pcVar11;
  long lVar12;
  
  if ((bRam00000000049f7b10 & 1) == 0) {
    func_0x010c65d4(0x8a4c);
    bRam00000000049f7b10 = 1;
  }
  *(undefined1 *)(param_1 + 0x142) = 0;
  if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar8 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Util_TypeInfo);
    }
    lVar9 = Util__GetData(0,uVar8,_str_uid,0,0);
    lVar12 = *(long *)(param_1 + 0x48);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    lVar12 = BattlePlay_BattlePlayData__get_host(lVar12,0);
    if (lVar12 != 0) {
      lVar12 = *(long *)(param_1 + 0x48);
      if (lVar12 == 0) {
        func_0x010eec64(0);
      }
      lVar12 = BattlePlay_BattlePlayData__get_opponent(lVar12,0);
      if (lVar12 != 0) {
        lVar12 = *(long *)(param_1 + 0x48);
        if (lVar12 == 0) {
          func_0x010eec64(0);
        }
        lVar12 = BattlePlay_BattlePlayData__get_host(lVar12,0);
        if (lVar12 == 0) {
          func_0x010eec64(0);
        }
        if (*(long *)(lVar12 + 0x10) != lVar9) {
          lVar12 = *(long *)(param_1 + 0x48);
          if (lVar12 == 0) {
            func_0x010eec64(0);
          }
          lVar12 = BattlePlay_BattlePlayData__get_opponent(lVar12,0);
          if (lVar12 == 0) {
            func_0x010eec64(0);
          }
          if (*(long *)(lVar12 + 0x10) != lVar9) {
            return;
          }
        }
        if (param_2 == 0) {
          func_0x010eec64(0);
        }
        uVar8 = SocketIO_SocketIOEvent__get_data(param_2,0);
        if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_Util_TypeInfo);
        }
        iVar4 = Util__GetData(0,uVar8,_str_type,0,0);
        if (param_2 == 0) {
          func_0x010eec64(0);
        }
        uVar8 = SocketIO_SocketIOEvent__get_data(param_2,0);
        if (iVar4 == 0xb) {
          lVar12 = func_0x010fdda8(_class_BattlePlay_UserSpellControl_TypeInfo);
          BattlePlay_UserSpellControl___ctor(lVar12,uVar8,0);
        }
        else {
          lVar12 = func_0x010fdda8(_class_BattlePlay_BattleControl_TypeInfo);
          BattlePlay_BattleControl___ctor(lVar12,uVar8,0);
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
        BattlePlay_BattlePlayManager__AddControl(lVar10,lVar12,0);
        if (lVar12 == 0) {
          func_0x010eec64(0);
        }
        if (*(int *)(lVar12 + 0x18) == 0xe) {
          return;
        }
        lVar12 = *(long *)(param_1 + 0x48);
        if (lVar12 == 0) {
          func_0x010eec64(0);
        }
        lVar12 = BattlePlay_BattlePlayData__get_opponent(lVar12,0);
        if (lVar12 == 0) {
          func_0x010eec64(0);
        }
        if (*(long *)(lVar12 + 0x10) != lVar9) {
          return;
        }
        lVar12 = *(long *)(param_1 + 0x48);
        if (lVar12 == 0) {
          func_0x010eec64(0);
        }
        lVar12 = BattlePlay_BattlePlayData__get_opponent(lVar12,0);
        if (lVar12 == 0) {
          func_0x010eec64(0);
        }
        if (*(int *)(lVar12 + 0x44) == 3) {
          return;
        }
        if ((bRam00000000049f7acf & 1) == 0) {
          func_0x010c65d4(0x8a7f);
          bRam00000000049f7acf = 1;
        }
        if (*(char *)(param_1 + 0x141) == '\0') {
          return;
        }
        uVar8 = func_0x010fdda8(_class_<>__AnonType12<ulong>_TypeInfo);
        <>__AnonType12<ulong>___ctor(uVar8,lVar9,_mm_Method_<>__AnonType12<ulong>__ctor__);
        if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar6 = LitJson_JsonMapper__ToJson(0,uVar8,0);
        uVar8 = _str_roomInfo;
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
    uVar3 = 8;
code_r0x013a22a4:
    uVar8 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
    <>__AnonType19<Step,_bool,_bool>___ctor
              (uVar8,uVar3,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
  }
  else {
    iVar4 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
    if (iVar4 < 1) {
code_r0x013a2284:
      plVar7 = *(long **)(param_1 + 0x40);
      if (plVar7 == (long *)0x0) goto code_r0x013a22a0;
      uVar3 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
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
    uVar6 = BattlePlay_BattlePlayData__get_battleKey(lVar9,0);
    plVar7 = *(long **)(param_1 + 0x40);
    if (plVar7 == (long *)0x0) {
      uVar3 = 8;
    }
    else {
      uVar3 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
    }
    uVar8 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
    <>__AnonType18<string,_bool,_bool,_Step>___ctor
              (uVar8,uVar6,1,0,uVar3,_mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__);
  }
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = LitJson_JsonMapper__ToJson(0,uVar8,0);
  uVar8 = _str_cancelMatchmaking;
Multiplay_MultiplayManager__EmitSocketMessage:
  uVar5 = MyUserData__get_hasInstance(0,0);
  if ((uVar5 & 1) != 0) {
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
        func_0x010c65d4(0xc5d1,uVar8,uVar6,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar8 = System_String__Format(0,_str____0____1__,uVar8,uVar6,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar6 = LitJson_JsonMapper__ToObject(0,uVar8,0);
      lVar10 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar8 = _str__;
      System_Object___ctor(lVar10,0);
      *(undefined4 *)(lVar10 + 0x14) = 2;
      *(undefined4 *)(lVar10 + 0x18) = 0;
      *(undefined8 *)(lVar10 + 0x30) = uVar6;
      *(undefined8 *)(lVar10 + 0x20) = uVar8;
      *(undefined4 *)(lVar10 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar10 + 0x10) = 4;
      lVar12 = lVar9;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar12 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar9 + 0x48);
      if (*(long *)(lVar9 + 0x50) == 0) {
        lVar12 = func_0x010eec64(0);
      }
      uVar8 = SocketIO_Encoder__Encode(lVar12,lVar10);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar8,0);
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
  uVar8 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = UnityEngine_Object__op_Inequality(0,uVar8,0,0);
  if ((uVar5 & 1) != 0) {
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
    pcVar11 = pcRam0000000004a04118;
    if ((pcRam0000000004a04118 == (code *)0x0) &&
       (pcVar11 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar11 == (code *)0x0)) {
      uVar6 = func_0x010f000c(&UNK_039a20c7);
      func_0x010eebec(uVar6,0,0);
    }
    pcRam0000000004a04118 = pcVar11;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*pcRam0000000004a04118)(uVar8,0);
    return;
  }
  return;
}


