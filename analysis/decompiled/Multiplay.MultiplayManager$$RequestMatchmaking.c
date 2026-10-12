// name: Multiplay.MultiplayManager$$RequestMatchmaking
// entry: 013a196c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__RequestMatchmaking
               (long param_1,undefined4 param_2,undefined4 param_3,undefined4 param_4,
               undefined4 param_5,undefined4 param_6)

{
  long lVar1;
  undefined4 uVar2;
  undefined4 uVar3;
  uint uVar4;
  undefined4 uVar5;
  undefined4 uVar6;
  ulong uVar7;
  long lVar8;
  long *plVar9;
  long *plVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  long lVar14;
  undefined8 uVar15;
  code *pcVar16;
  long lVar17;
  undefined4 uVar18;
  undefined4 uVar19;
  undefined4 *puVar20;
  undefined4 *puVar21;
  undefined4 *puVar22;
  undefined8 uStack_70;
  undefined8 uStack_68;
  
  if ((bRam00000000049f7ad2 & 1) == 0) {
    func_0x010c65d4(0x8a7d);
    bRam00000000049f7ad2 = 1;
  }
  lVar8 = func_0x010fdda8(
                         _class_Multiplay_MultiplayManager_<RequestMatchmaking>c__AnonStorey3_TypeInfo
                         );
  Multiplay_MultiplayManager_<RequestMatchmaking>c__AnonStorey3___ctor(lVar8,0);
  if (lVar8 == 0) {
    func_0x010eec64(0);
    puVar21 = (undefined4 *)0x10;
    uRam0000000000000010 = param_2;
    func_0x010eec64(0);
    uRam0000000000000014 = param_3;
    func_0x010eec64(0);
    puVar22 = (undefined4 *)0x18;
    uRam0000000000000018 = param_4;
    func_0x010eec64(0);
    uRam000000000000001c = param_5;
    func_0x010eec64(0);
    puVar20 = (undefined4 *)0x20;
    uRam0000000000000020 = param_6;
    func_0x010eec64(0);
  }
  else {
    puVar21 = (undefined4 *)(lVar8 + 0x10);
    *puVar21 = param_2;
    *(undefined4 *)(lVar8 + 0x14) = param_3;
    puVar22 = (undefined4 *)(lVar8 + 0x18);
    *puVar22 = param_4;
    *(undefined4 *)(lVar8 + 0x1c) = param_5;
    puVar20 = (undefined4 *)(lVar8 + 0x20);
    *puVar20 = param_6;
  }
  *(long *)(lVar8 + 0x28) = param_1;
  uVar4 = Multiplay_MultiplayManager__get_isConnected(param_1);
  uVar11 = _mm_Method_Multiplay_MultiplayManager_<RequestMatchmaking>c__AnonStorey3_<>m__1__;
  uVar15 = _mm_Method_Multiplay_MultiplayManager_<RequestMatchmaking>c__AnonStorey3_<>m__0__;
  if ((uVar4 & 1) == 0) {
    uVar12 = *(undefined8 *)(param_1 + 0x78);
    uVar11 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar11,lVar8,uVar15,0);
    plVar9 = (long *)System_Delegate__Combine(0,uVar12,uVar11,0);
    plVar10 = (long *)0x0;
    if ((plVar9 != (long *)0x0) && (plVar10 = plVar9, *plVar9 != _class_System_Action_TypeInfo)) {
      func_0x0111ef28(&uStack_70,*(undefined8 *)(*plVar9 + 0x40));
      uVar15 = func_0x010ef81c(uStack_70);
      func_0x010eebec(uVar15,0,0);
      func_0x036457e0(&uStack_70);
      plVar10 = (long *)0x0;
    }
    *(long **)(param_1 + 0x78) = plVar10;
    Multiplay_MultiplayManager__Connect(param_1);
  }
  else {
    if (*(char *)(param_1 + 0x141) != '\0') {
      Multiplay_MultiplayManager__InitBattlePlayData(param_1,*puVar21);
      lVar17 = *(long *)(param_1 + 0x48);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      lVar17 = BattlePlay_BattlePlayData__get_host(lVar17,0);
      uVar5 = *(undefined4 *)(lVar8 + 0x14);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      *(undefined4 *)(lVar17 + 0x24) = uVar5;
      Multiplay_MultiplayManager__GoToStep(param_1,0);
      lVar17 = *(long *)(param_1 + 0x48);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      uVar5 = BattlePlay_BattlePlayData__get_battleId(lVar17,0);
      lVar17 = *(long *)(param_1 + 0x48);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      lVar17 = BattlePlay_BattlePlayData__get_host(lVar17,0);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      uVar2 = *(undefined4 *)(lVar17 + 0x24);
      if (lVar8 == 0) {
        func_0x010eec64(0);
        uVar18 = *puVar22;
        func_0x010eec64(0);
        uVar19 = uRam000000000000001c;
        func_0x010eec64(0);
      }
      else {
        uVar18 = *puVar22;
        uVar19 = *(undefined4 *)(lVar8 + 0x1c);
      }
      uVar3 = *puVar20;
      plVar10 = *(long **)(param_1 + 0x40);
      if (plVar10 == (long *)0x0) {
        uVar6 = 8;
      }
      else {
        uVar6 = (**(code **)(*plVar10 + 0x170))(plVar10,*(undefined8 *)(*plVar10 + 0x178));
      }
      lVar8 = *(long *)(param_1 + 0x48);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      lVar8 = BattlePlay_BattlePlayData__get_host(lVar8,0);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      uVar15 = _str_matchmaking;
      if (*(long *)(lVar8 + 0xb8) == 0) {
        uVar11 = 0;
      }
      else {
        lVar8 = *(long *)(param_1 + 0x48);
        if (lVar8 == 0) {
          func_0x010eec64(0);
        }
        lVar8 = BattlePlay_BattlePlayData__get_host(lVar8,0);
        if (lVar8 == 0) {
          func_0x010eec64(0);
        }
        plVar10 = *(long **)(lVar8 + 0xb8);
        if (plVar10 == (long *)0x0) {
          func_0x010eec64(0);
        }
        uVar11 = (**(code **)(*plVar10 + 0x160))(plVar10,*(undefined8 *)(*plVar10 + 0x168));
      }
      lVar8 = CompetitionManager__get_instance(0,0);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      uVar12 = CompetitionManager__get_prevOpponents(lVar8,0);
      uVar13 = func_0x010fdda8(
                              _class_<>__AnonType16<int,_int,_int,_int,_int,_Step,_string,_ulong[]>_TypeInfo
                              );
      <>__AnonType16<int,_int,_int,_int,_int,_Step,_string,_ulong[]>___ctor
                (uVar13,uVar5,uVar2,uVar18,uVar19,uVar3,uVar6,uVar11,uVar12,
                 _mm_Method_<>__AnonType16<int,_int,_int,_int,_int,_Step,_string,_ulong[]>__ctor__);
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar11 = LitJson_JsonMapper__ToJson(0,uVar13,0);
      if (param_1 == 0) {
        func_0x010eec64(0);
      }
      uVar7 = MyUserData__get_hasInstance(0,0);
      if ((uVar7 & 1) != 0) {
        lVar8 = MyUserData__get_instance(0,0);
        if (lVar8 == 0) {
          func_0x010eec64(0);
        }
        if (*(char *)(lVar8 + 0x268) != '\0') {
          lVar8 = *(long *)(param_1 + 0x30);
          if (lVar8 == 0) {
            func_0x010eec64(0);
          }
          if ((bRam0000000004a00249 & 1) == 0) {
            func_0x010c65d4(0xc5d1,uVar15,uVar11,0);
            bRam0000000004a00249 = 1;
          }
          if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar15 = System_String__Format(0,_str____0____1__,uVar15,uVar11,0);
          if ((bRam0000000004a0024d & 1) == 0) {
            func_0x010c65d4(0xc5ce);
            bRam0000000004a0024d = 1;
          }
          if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar11 = LitJson_JsonMapper__ToObject(0,uVar15,0);
          lVar14 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
          uVar15 = _str__;
          System_Object___ctor(lVar14,0);
          *(undefined4 *)(lVar14 + 0x14) = 2;
          *(undefined4 *)(lVar14 + 0x18) = 0;
          *(undefined8 *)(lVar14 + 0x30) = uVar11;
          *(undefined8 *)(lVar14 + 0x20) = uVar15;
          *(undefined4 *)(lVar14 + 0x28) = 0xffffffff;
          *(undefined4 *)(lVar14 + 0x10) = 4;
          lVar17 = lVar8;
          if ((bRam0000000004a0024f & 1) == 0) {
            lVar17 = func_0x010c65d4(0xc5cf);
            bRam0000000004a0024f = 1;
          }
          lVar1 = *(long *)(lVar8 + 0x48);
          if (*(long *)(lVar8 + 0x50) == 0) {
            lVar17 = func_0x010eec64(0);
          }
          uVar15 = SocketIO_Encoder__Encode(lVar17,lVar14);
          if (lVar1 == 0) {
            func_0x010eec64(0);
          }
          WebSocketSharp_WebSocket__Send(lVar1,uVar15,0);
          return;
        }
      }
      uVar7 = Multiplay_MultiplayManager__get_isConnected(param_1);
      if ((uVar7 & 1) == 0) {
        return;
      }
      if ((bRam00000000049f7acc & 1) == 0) {
        func_0x010c65d4(0x8a26);
        bRam00000000049f7acc = 1;
      }
      *(undefined8 *)(param_1 + 0x70) = 0;
      *(undefined1 *)(param_1 + 0x141) = 0;
      *(undefined1 *)(param_1 + 0x142) = 0;
      uVar15 = *(undefined8 *)(param_1 + 0x30);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar7 = UnityEngine_Object__op_Inequality(0,uVar15,0,0);
      if ((uVar7 & 1) == 0) {
        return;
      }
      lVar8 = *(long *)(param_1 + 0x30);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__Close(lVar8,0);
      uVar15 = *(undefined8 *)(param_1 + 0x30);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      if ((bRam0000000004a04120 & 1) == 0) {
        func_0x010c65d4(0x906e,uVar15,0);
        bRam0000000004a04120 = 1;
      }
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      pcVar16 = pcRam0000000004a04118;
      if ((pcRam0000000004a04118 == (code *)0x0) &&
         (pcVar16 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar16 == (code *)0x0)) {
        uVar11 = func_0x010f000c(&UNK_039a20c7);
        func_0x010eebec(uVar11,0,0);
      }
      pcRam0000000004a04118 = pcVar16;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*pcRam0000000004a04118)(uVar15,0);
      return;
    }
    uVar12 = *(undefined8 *)(param_1 + 0x78);
    uVar15 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar15,lVar8,uVar11,0);
    plVar9 = (long *)System_Delegate__Combine(0,uVar12,uVar15,0);
    plVar10 = (long *)0x0;
    if ((plVar9 != (long *)0x0) && (plVar10 = plVar9, *plVar9 != _class_System_Action_TypeInfo)) {
      func_0x0111ef28(&uStack_68,*(undefined8 *)(*plVar9 + 0x40));
      uVar15 = func_0x010ef81c(uStack_68);
      func_0x010eebec(uVar15,0,0);
      func_0x036457e0(&uStack_68);
      plVar10 = (long *)0x0;
    }
    *(long **)(param_1 + 0x78) = plVar10;
    Multiplay_MultiplayManager__Register(param_1);
  }
  return;
}


