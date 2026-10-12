// name: CompetitionManager$$RequestMatchmaking
// entry: 01c3ab90

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void CompetitionManager__RequestMatchmaking(long param_1)

{
  undefined4 uVar1;
  long lVar2;
  uint uVar3;
  undefined4 uVar4;
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
  undefined1 auVar23 [16];
  undefined8 uStack_70;
  undefined8 uStack_68;
  
  if ((bRam00000000049fc74b & 1) == 0) {
    func_0x010c65d4(0x2e12);
    bRam00000000049fc74b = 1;
  }
  if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar14 = Singleton<iTweenManager>__get_instance
                     (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
  auVar23 = CompetitionManager__get_competitionId(param_1);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0x12a) & 1) != 0)
     && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = CodeStage_AntiCheat_ObscuredTypes_ObscuredInt__op_Implicit
                    (0,auVar23._0_8_,auVar23._8_8_ & 0xffffffff,0);
  uVar4 = *(undefined4 *)(param_1 + 0x48);
  uVar18 = *(undefined4 *)(param_1 + 0x4c);
  uVar1 = *(undefined4 *)(param_1 + 0x50);
  uVar19 = *(undefined4 *)(param_1 + 0x54);
  if (lVar14 == 0) {
    func_0x010eec64(0);
  }
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
    uRam0000000000000010 = uVar6;
    func_0x010eec64(0);
    uRam0000000000000014 = uVar4;
    func_0x010eec64(0);
    puVar22 = (undefined4 *)0x18;
    uRam0000000000000018 = uVar18;
    func_0x010eec64(0);
    uRam000000000000001c = uVar1;
    func_0x010eec64(0);
    puVar20 = (undefined4 *)0x20;
    uRam0000000000000020 = uVar19;
    func_0x010eec64(0);
  }
  else {
    puVar21 = (undefined4 *)(lVar8 + 0x10);
    *puVar21 = uVar6;
    *(undefined4 *)(lVar8 + 0x14) = uVar4;
    puVar22 = (undefined4 *)(lVar8 + 0x18);
    *puVar22 = uVar18;
    *(undefined4 *)(lVar8 + 0x1c) = uVar1;
    puVar20 = (undefined4 *)(lVar8 + 0x20);
    *puVar20 = uVar19;
  }
  *(long *)(lVar8 + 0x28) = lVar14;
  uVar3 = Multiplay_MultiplayManager__get_isConnected(lVar14);
  uVar11 = _mm_Method_Multiplay_MultiplayManager_<RequestMatchmaking>c__AnonStorey3_<>m__1__;
  uVar15 = _mm_Method_Multiplay_MultiplayManager_<RequestMatchmaking>c__AnonStorey3_<>m__0__;
  if ((uVar3 & 1) == 0) {
    uVar12 = *(undefined8 *)(lVar14 + 0x78);
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
    *(long **)(lVar14 + 0x78) = plVar10;
    Multiplay_MultiplayManager__Connect(lVar14);
  }
  else {
    if (*(char *)(lVar14 + 0x141) != '\0') {
      Multiplay_MultiplayManager__InitBattlePlayData(lVar14,*puVar21);
      lVar17 = *(long *)(lVar14 + 0x48);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      lVar17 = BattlePlay_BattlePlayData__get_host(lVar17,0);
      uVar4 = *(undefined4 *)(lVar8 + 0x14);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      *(undefined4 *)(lVar17 + 0x24) = uVar4;
      Multiplay_MultiplayManager__GoToStep(lVar14,0);
      lVar17 = *(long *)(lVar14 + 0x48);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      uVar4 = BattlePlay_BattlePlayData__get_battleId(lVar17,0);
      lVar17 = *(long *)(lVar14 + 0x48);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      lVar17 = BattlePlay_BattlePlayData__get_host(lVar17,0);
      if (lVar17 == 0) {
        func_0x010eec64(0);
      }
      uVar1 = *(undefined4 *)(lVar17 + 0x24);
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
      uVar6 = *puVar20;
      plVar10 = *(long **)(lVar14 + 0x40);
      if (plVar10 == (long *)0x0) {
        uVar5 = 8;
      }
      else {
        uVar5 = (**(code **)(*plVar10 + 0x170))(plVar10,*(undefined8 *)(*plVar10 + 0x178));
      }
      lVar8 = *(long *)(lVar14 + 0x48);
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
        lVar8 = *(long *)(lVar14 + 0x48);
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
                (uVar13,uVar4,uVar1,uVar18,uVar19,uVar6,uVar5,uVar11,uVar12,
                 _mm_Method_<>__AnonType16<int,_int,_int,_int,_int,_Step,_string,_ulong[]>__ctor__);
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar11 = LitJson_JsonMapper__ToJson(0,uVar13,0);
      if (lVar14 == 0) {
        func_0x010eec64(0);
      }
      uVar7 = MyUserData__get_hasInstance(0,0);
      if ((uVar7 & 1) != 0) {
        lVar8 = MyUserData__get_instance(0,0);
        if (lVar8 == 0) {
          func_0x010eec64(0);
        }
        if (*(char *)(lVar8 + 0x268) != '\0') {
          lVar14 = *(long *)(lVar14 + 0x30);
          if (lVar14 == 0) {
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
          lVar17 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
          uVar15 = _str__;
          System_Object___ctor(lVar17,0);
          *(undefined4 *)(lVar17 + 0x14) = 2;
          *(undefined4 *)(lVar17 + 0x18) = 0;
          *(undefined8 *)(lVar17 + 0x30) = uVar11;
          *(undefined8 *)(lVar17 + 0x20) = uVar15;
          *(undefined4 *)(lVar17 + 0x28) = 0xffffffff;
          *(undefined4 *)(lVar17 + 0x10) = 4;
          lVar8 = lVar14;
          if ((bRam0000000004a0024f & 1) == 0) {
            lVar8 = func_0x010c65d4(0xc5cf);
            bRam0000000004a0024f = 1;
          }
          lVar2 = *(long *)(lVar14 + 0x48);
          if (*(long *)(lVar14 + 0x50) == 0) {
            lVar8 = func_0x010eec64(0);
          }
          uVar15 = SocketIO_Encoder__Encode(lVar8,lVar17);
          if (lVar2 == 0) {
            func_0x010eec64(0);
          }
          WebSocketSharp_WebSocket__Send(lVar2,uVar15,0);
          return;
        }
      }
      uVar7 = Multiplay_MultiplayManager__get_isConnected(lVar14);
      if ((uVar7 & 1) == 0) {
        return;
      }
      if ((bRam00000000049f7acc & 1) == 0) {
        func_0x010c65d4(0x8a26);
        bRam00000000049f7acc = 1;
      }
      *(undefined8 *)(lVar14 + 0x70) = 0;
      *(undefined1 *)(lVar14 + 0x141) = 0;
      *(undefined1 *)(lVar14 + 0x142) = 0;
      uVar15 = *(undefined8 *)(lVar14 + 0x30);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar7 = UnityEngine_Object__op_Inequality(0,uVar15,0,0);
      if ((uVar7 & 1) == 0) {
        return;
      }
      lVar8 = *(long *)(lVar14 + 0x30);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__Close(lVar8,0);
      uVar15 = *(undefined8 *)(lVar14 + 0x30);
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
    uVar12 = *(undefined8 *)(lVar14 + 0x78);
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
    *(long **)(lVar14 + 0x78) = plVar10;
    Multiplay_MultiplayManager__Register(lVar14);
  }
  return;
}


