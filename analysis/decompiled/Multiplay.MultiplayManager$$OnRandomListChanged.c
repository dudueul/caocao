// name: Multiplay.MultiplayManager$$OnRandomListChanged
// entry: 013aa140

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnRandomListChanged(long param_1,long param_2)

{
  long lVar1;
  uint uVar2;
  int iVar3;
  undefined4 uVar4;
  ulong uVar5;
  undefined8 uVar6;
  long lVar7;
  undefined8 uVar8;
  long lVar9;
  long lVar10;
  code *pcVar11;
  long *plVar12;
  
  if ((bRam00000000049f7b18 & 1) == 0) {
    func_0x010c65d4(0x8a64);
    bRam00000000049f7b18 = 1;
  }
  if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    lVar7 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    LitJson_JsonData__get_Item(lVar7,_str_randomList,0);
    plVar12 = *(long **)(param_1 + 0x48);
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar8 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (plVar12 == (long *)0x0) {
      func_0x010eec64(0);
    }
                    /* WARNING: Could not recover jumptable at 0x02226228. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (**(code **)(*plVar12 + 0x180))(plVar12,uVar8,*(undefined8 *)(*plVar12 + 0x188));
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
  plVar12 = *(long **)(param_1 + 0x40);
  if (plVar12 == (long *)0x0) {
code_r0x013a22a0:
    uVar4 = 8;
  }
  else {
    iVar3 = (**(code **)(*plVar12 + 0x170))(plVar12,*(undefined8 *)(*plVar12 + 0x178));
    if (0 < iVar3) {
      if (*(long *)(param_1 + 0x40) == 0) goto code_r0x013a22a0;
      if ((*(long *)(param_1 + 0x48) != 0) &&
         (lVar7 = BattlePlay_BattlePlayData__get_opponent(*(long *)(param_1 + 0x48),0), lVar7 != 0))
      {
        lVar7 = *(long *)(param_1 + 0x48);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        uVar8 = BattlePlay_BattlePlayData__get_battleKey(lVar7,0);
        plVar12 = *(long **)(param_1 + 0x40);
        if (plVar12 == (long *)0x0) {
          uVar4 = 8;
        }
        else {
          uVar4 = (**(code **)(*plVar12 + 0x170))(plVar12,*(undefined8 *)(*plVar12 + 0x178));
        }
        uVar6 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
        <>__AnonType18<string,_bool,_bool,_Step>___ctor
                  (uVar6,uVar8,1,0,uVar4,_mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__
                  );
        goto code_r0x013a22d4;
      }
    }
    plVar12 = *(long **)(param_1 + 0x40);
    if (plVar12 == (long *)0x0) goto code_r0x013a22a0;
    uVar4 = (**(code **)(*plVar12 + 0x170))(plVar12,*(undefined8 *)(*plVar12 + 0x178));
  }
  uVar6 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
  <>__AnonType19<Step,_bool,_bool>___ctor
            (uVar6,uVar4,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
code_r0x013a22d4:
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = LitJson_JsonMapper__ToJson(0,uVar6,0);
  uVar8 = _str_cancelMatchmaking;
  uVar5 = MyUserData__get_hasInstance(0,0);
  if ((uVar5 & 1) != 0) {
    lVar7 = MyUserData__get_instance(0,0);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar7 + 0x268) != '\0') {
      lVar7 = *(long *)(param_1 + 0x30);
      if (lVar7 == 0) {
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
      lVar9 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar8 = _str__;
      System_Object___ctor(lVar9,0);
      *(undefined4 *)(lVar9 + 0x14) = 2;
      *(undefined4 *)(lVar9 + 0x18) = 0;
      *(undefined8 *)(lVar9 + 0x30) = uVar6;
      *(undefined8 *)(lVar9 + 0x20) = uVar8;
      *(undefined4 *)(lVar9 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar9 + 0x10) = 4;
      lVar10 = lVar7;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar10 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar7 + 0x48);
      if (*(long *)(lVar7 + 0x50) == 0) {
        lVar10 = func_0x010eec64(0);
      }
      uVar8 = SocketIO_Encoder__Encode(lVar10,lVar9);
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
    lVar7 = *(long *)(param_1 + 0x30);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar7,0);
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


