// name: BattlePlay.BattlePlayManager$$AddControl
// entry: 01976a1c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__AddControl(long param_1,long param_2)

{
  long lVar1;
  int iVar2;
  int iVar3;
  undefined4 uVar4;
  uint uVar5;
  long *plVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  ulong uVar9;
  long lVar10;
  code *pcVar11;
  long lVar12;
  long lVar13;
  int *piVar14;
  
  if ((bRam00000000049fae09 & 1) == 0) {
    func_0x010c65d4(0x205b);
    bRam00000000049fae09 = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
    func_0x010eec64(0);
  }
  if ((int)*(undefined8 *)(param_2 + 0x18) == 0xe) {
    iVar2 = (int)((ulong)*(undefined8 *)(param_2 + 0x18) >> 0x20);
  }
  else {
    BattlePlay_BattlePlayManager__ResetUnitTurnTime(param_1,*(undefined8 *)(param_2 + 0x10),0);
    iVar2 = *(int *)(param_2 + 0x1c);
  }
  piVar14 = (int *)(param_2 + 0x1c);
  lVar12 = *(long *)(param_1 + 0x18);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  iVar3 = BattlePlay_BattlePlayData__get_controlIdx(lVar12);
  if (iVar2 == iVar3) {
    lVar12 = *(long *)(param_1 + 0x18);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    BattlePlay_BattlePlayData__AddControl(lVar12,param_2);
    lVar12 = *(long *)(param_1 + 0x18);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    uVar4 = BattlePlay_BattlePlayData__get_controlIdx(lVar12);
    while( true ) {
      if ((bRam00000000049fae0f & 1) == 0) {
        func_0x010c65d4(0x2060);
        bRam00000000049fae0f = 1;
      }
      lVar12 = *(long *)(param_1 + 0x20);
      if (lVar12 == 0) {
        func_0x010eec64(0);
      }
      uVar9 = System_Collections_Generic_Dictionary<int,_tk2dFontChar>__ContainsKey
                        (lVar12,uVar4,
                         _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_ContainsKey__
                        );
      if ((uVar9 & 1) == 0) break;
      lVar12 = *(long *)(param_1 + 0x20);
      if (lVar12 == 0) {
        func_0x010eec64(0);
      }
      lVar12 = System_Collections_Generic_Dictionary<int,_object>__get_Item
                         (lVar12,uVar4,
                          _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_get_Item__
                         );
      lVar13 = *(long *)(param_1 + 0x20);
      if (lVar12 == 0) {
        func_0x010eec64(0);
      }
      uVar4 = *(undefined4 *)(lVar12 + 0x1c);
      if (lVar13 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_Dictionary<int,_EndPointListener>__Remove
                (lVar13,uVar4,
                 _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_Remove__);
      iVar2 = *(int *)(lVar12 + 0x1c);
      lVar13 = *(long *)(param_1 + 0x18);
      if (lVar13 == 0) {
        func_0x010eec64(0);
      }
      iVar3 = BattlePlay_BattlePlayData__get_controlIdx(lVar13);
      if (iVar2 == iVar3) {
        lVar13 = *(long *)(param_1 + 0x18);
        if (lVar13 == 0) {
          func_0x010eec64(0);
        }
        BattlePlay_BattlePlayData__AddControl(lVar13,lVar12);
      }
      lVar12 = *(long *)(param_1 + 0x18);
      if (lVar12 == 0) {
        func_0x010eec64(0);
      }
      uVar4 = BattlePlay_BattlePlayData__get_controlIdx(lVar12);
    }
    return;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  iVar2 = *piVar14;
  lVar12 = *(long *)(param_1 + 0x18);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  iVar3 = BattlePlay_BattlePlayData__get_controlIdx(lVar12);
  if (iVar2 <= iVar3) {
    return;
  }
  lVar12 = *(long *)(param_1 + 0x20);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  iVar2 = *piVar14;
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  uVar5 = System_Collections_Generic_Dictionary<int,_tk2dFontChar>__ContainsKey
                    (lVar12,iVar2,
                     _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_ContainsKey__
                    );
  if ((uVar5 & 1) == 0) {
    lVar12 = *(long *)(param_1 + 0x20);
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    iVar2 = *piVar14;
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_Dictionary<int,_UserUnitRelation>__Add
              (lVar12,iVar2,param_2,
               _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_Add__);
  }
  if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar12 = Singleton<iTweenManager>__get_instance
                     (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
  lVar13 = *(long *)(param_1 + 0x18);
  if (lVar13 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = BattlePlay_BattlePlayData__get_controlIdx(lVar13);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  if ((bRam00000000049f7aee & 1) == 0) {
    func_0x010c65d4(0x8a2a,uVar4,0);
    bRam00000000049f7aee = 1;
  }
  if (*(char *)(lVar12 + 0x141) == '\0') {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar9 = Multiplay_MultiplayManager__get_isMyActiveBattle();
  if ((uVar9 & 1) == 0) {
    return;
  }
  if ((*(long *)(lVar12 + 0x40) != 0) && (*(long *)(lVar12 + 0x48) != 0)) {
    uVar9 = Multiplay_MultiplayManager__get_isConnected(lVar12);
    if ((uVar9 & 1) == 0) {
      return;
    }
    lVar13 = *(long *)(lVar12 + 0x48);
    if (lVar13 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = BattlePlay_BattlePlayData__get_battleKey(lVar13,0);
    uVar8 = func_0x010fdda8(_class_<>__AnonType38<string,_int>_TypeInfo);
    <>__AnonType38<string,_int>___ctor
              (uVar8,uVar7,uVar4,_mm_Method_<>__AnonType38<string,_int>__ctor__);
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
  if (*(char *)(lVar12 + 0x141) == '\0') {
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
  plVar6 = *(long **)(lVar12 + 0x40);
  if (plVar6 == (long *)0x0) {
code_r0x013a22a0:
    uVar4 = 8;
code_r0x013a22a4:
    uVar7 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
    <>__AnonType19<Step,_bool,_bool>___ctor
              (uVar7,uVar4,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
  }
  else {
    iVar2 = (**(code **)(*plVar6 + 0x170))(plVar6,*(undefined8 *)(*plVar6 + 0x178));
    if (iVar2 < 1) {
code_r0x013a2284:
      plVar6 = *(long **)(lVar12 + 0x40);
      if (plVar6 == (long *)0x0) goto code_r0x013a22a0;
      uVar4 = (**(code **)(*plVar6 + 0x170))(plVar6,*(undefined8 *)(*plVar6 + 0x178));
      goto code_r0x013a22a4;
    }
    if (*(long *)(lVar12 + 0x40) == 0) goto code_r0x013a22a0;
    if ((*(long *)(lVar12 + 0x48) == 0) ||
       (lVar13 = BattlePlay_BattlePlayData__get_opponent(*(long *)(lVar12 + 0x48),0), lVar13 == 0))
    goto code_r0x013a2284;
    lVar13 = *(long *)(lVar12 + 0x48);
    if (lVar13 == 0) {
      func_0x010eec64(0);
    }
    uVar8 = BattlePlay_BattlePlayData__get_battleKey(lVar13,0);
    plVar6 = *(long **)(lVar12 + 0x40);
    if (plVar6 == (long *)0x0) {
      uVar4 = 8;
    }
    else {
      uVar4 = (**(code **)(*plVar6 + 0x170))(plVar6,*(undefined8 *)(*plVar6 + 0x178));
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
  uVar9 = MyUserData__get_hasInstance(0,0);
  if ((uVar9 & 1) != 0) {
    lVar13 = MyUserData__get_instance(0,0);
    if (lVar13 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar13 + 0x268) != '\0') {
      lVar12 = *(long *)(lVar12 + 0x30);
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
      lVar10 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar7 = _str__;
      System_Object___ctor(lVar10,0);
      *(undefined4 *)(lVar10 + 0x14) = 2;
      *(undefined4 *)(lVar10 + 0x18) = 0;
      *(undefined8 *)(lVar10 + 0x30) = uVar8;
      *(undefined8 *)(lVar10 + 0x20) = uVar7;
      *(undefined4 *)(lVar10 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar10 + 0x10) = 4;
      lVar13 = lVar12;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar13 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar12 + 0x48);
      if (*(long *)(lVar12 + 0x50) == 0) {
        lVar13 = func_0x010eec64(0);
      }
      uVar7 = SocketIO_Encoder__Encode(lVar13,lVar10);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar7,0);
      return;
    }
  }
  uVar9 = Multiplay_MultiplayManager__get_isConnected(lVar12);
  if ((uVar9 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(lVar12 + 0x70) = 0;
  *(undefined1 *)(lVar12 + 0x141) = 0;
  *(undefined1 *)(lVar12 + 0x142) = 0;
  uVar7 = *(undefined8 *)(lVar12 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar9 = UnityEngine_Object__op_Inequality(0,uVar7,0,0);
  if ((uVar9 & 1) != 0) {
    lVar13 = *(long *)(lVar12 + 0x30);
    if (lVar13 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar13,0);
    uVar7 = *(undefined8 *)(lVar12 + 0x30);
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


