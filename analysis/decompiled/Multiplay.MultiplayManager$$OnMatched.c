// name: Multiplay.MultiplayManager$$OnMatched
// entry: 013a73d8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnMatched(long param_1,long param_2)

{
  long lVar1;
  ushort uVar2;
  int iVar3;
  uint uVar4;
  undefined4 uVar5;
  code *UNRECOVERED_JUMPTABLE_00;
  ulong uVar6;
  long *plVar7;
  undefined8 *puVar8;
  undefined8 uVar9;
  ulong uVar10;
  long lVar11;
  undefined8 uVar12;
  long lVar13;
  long lVar14;
  int *piVar15;
  undefined1 auVar16 [16];
  
  if ((bRam00000000049f7b04 & 1) == 0) {
    func_0x010c65d4(0x8a5c);
    bRam00000000049f7b04 = 1;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar4 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar4 & 1) != 0) {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar10 = Multiplay_MultiplayManager__get_isFriendlyBattle();
  if ((uVar10 & 1) != 0) {
    lVar11 = CompetitionManager__get_instance(0,0);
    if (lVar11 == 0) {
      func_0x010eec64(0);
    }
    CompetitionManager__SetBattleMapInfoForFriendlyMatch(lVar11,0);
    lVar11 = CompetitionManager__get_instance(0,0);
    if (lVar11 == 0) {
      func_0x010eec64(0);
    }
    auVar16 = CompetitionManager__get_competitionId(lVar11,0);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0x12a) & 1) != 0
        ) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0xd8) == 0))
    {
      func_0x010da55c();
    }
    uVar5 = CodeStage_AntiCheat_ObscuredTypes_ObscuredInt__op_Implicit
                      (0,auVar16._0_8_,auVar16._8_8_ & 0xffffffff,0);
    Multiplay_MultiplayManager__InitFriendlyBattlePlayData(param_1,uVar5);
    Multiplay_MultiplayManager__GoToStep(param_1,0);
  }
  if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar12 = SocketIO_SocketIOEvent__get_data(param_2,0);
    Multiplay_Match__Deserialize(uVar12,uVar12);
    lVar11 = *(long *)(param_1 + 0x88);
    if (lVar11 == 0) {
      return;
    }
    if (*(long *)(lVar11 + 0x58) != 0) {
      System_Action__Invoke(*(long *)(lVar11 + 0x58),0);
    }
    UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar11 + 0x10);
    plVar7 = *(long **)(lVar11 + 0x20);
    puVar8 = *(undefined8 **)(lVar11 + 0x28);
    func_0x010da364(puVar8);
    uVar4 = func_0x01101d6c(puVar8);
    if ((uVar4 & 1) == 0) {
      if (*(char *)((long)puVar8 + 0x4e) != '\0') {
                    /* WARNING: Could not recover jumptable at 0x0109237c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
        (*UNRECOVERED_JUMPTABLE_00)(0,plVar7,puVar8);
        return;
      }
      plVar7 = (long *)0x0;
    }
    else if ((*(short *)((long)puVar8 + 0x4c) != -1) &&
            (((plVar7 == (long *)0x0 || ((*(byte *)(*plVar7 + 0x10d) & 1) == 0)) &&
             (*(long *)(lVar11 + 0x18) != 0)))) {
      uVar10 = func_0x01101d58(puVar8);
      func_0x01101dd4(puVar8);
      uVar6 = func_0x010ba65c();
      if ((uVar10 & 1) == 0) {
        uVar2 = *(ushort *)((long)puVar8 + 0x4c);
        if ((uVar6 & 1) == 0) {
          lVar11 = *plVar7 + (ulong)uVar2 * 0x10;
          UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar11 + 0x130);
          puVar8 = *(undefined8 **)(lVar11 + 0x138);
        }
        else {
          lVar11 = func_0x01101dd4(puVar8);
          lVar14 = *plVar7;
          if ((ulong)*(ushort *)(lVar14 + 0x122) != 0) {
            uVar10 = 0;
            piVar15 = (int *)(*(long *)(lVar14 + 0xb0) + 8);
            do {
              if (*(long *)(piVar15 + -2) == lVar11) {
                puVar8 = (undefined8 *)(lVar14 + (ulong)(*piVar15 + (uint)uVar2) * 0x10 + 0x130);
                goto code_r0x0109246c;
              }
              uVar10 = uVar10 + 1;
              piVar15 = piVar15 + 4;
            } while (uVar10 < *(ushort *)(lVar14 + 0x122));
          }
          puVar8 = (undefined8 *)func_0x010bc584(plVar7,lVar11,(ulong)uVar2);
code_r0x0109246c:
          UNRECOVERED_JUMPTABLE_00 = (code *)*puVar8;
          puVar8 = (undefined8 *)puVar8[1];
        }
      }
      else {
        if ((uVar6 & 1) == 0) {
          uVar12 = *(undefined8 *)(*plVar7 + (ulong)*(ushort *)((long)puVar8 + 0x4c) * 0x10 + 0x138)
          ;
        }
        else {
          lVar11 = *plVar7;
          if ((ulong)*(ushort *)(lVar11 + 0x122) != 0) {
            uVar10 = 0;
            piVar15 = (int *)(*(long *)(lVar11 + 0xb0) + 8);
            do {
              if (*(long *)(piVar15 + -2) == puVar8[3]) {
                lVar11 = lVar11 + (ulong)(*piVar15 + (uint)*(ushort *)((long)puVar8 + 0x4c)) * 0x10
                         + 0x130;
                goto code_r0x0109243c;
              }
              uVar10 = uVar10 + 1;
              piVar15 = piVar15 + 4;
            } while (uVar10 < *(ushort *)(lVar11 + 0x122));
          }
          lVar11 = func_0x010bc584(plVar7);
code_r0x0109243c:
          uVar12 = *(undefined8 *)(lVar11 + 8);
        }
        puVar8 = (undefined8 *)func_0x010da294(uVar12,puVar8);
        UNRECOVERED_JUMPTABLE_00 = (code *)*puVar8;
      }
    }
                    /* WARNING: Could not recover jumptable at 0x010923a4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*UNRECOVERED_JUMPTABLE_00)(plVar7,puVar8);
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
  plVar7 = *(long **)(param_1 + 0x40);
  if (plVar7 == (long *)0x0) {
code_r0x013a22a0:
    uVar5 = 8;
  }
  else {
    iVar3 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
    if (0 < iVar3) {
      if (*(long *)(param_1 + 0x40) == 0) goto code_r0x013a22a0;
      if ((*(long *)(param_1 + 0x48) != 0) &&
         (lVar11 = BattlePlay_BattlePlayData__get_opponent(*(long *)(param_1 + 0x48),0), lVar11 != 0
         )) {
        lVar11 = *(long *)(param_1 + 0x48);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        uVar12 = BattlePlay_BattlePlayData__get_battleKey(lVar11,0);
        plVar7 = *(long **)(param_1 + 0x40);
        if (plVar7 == (long *)0x0) {
          uVar5 = 8;
        }
        else {
          uVar5 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
        }
        uVar9 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
        <>__AnonType18<string,_bool,_bool,_Step>___ctor
                  (uVar9,uVar12,1,0,uVar5,
                   _mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__);
        goto code_r0x013a22d4;
      }
    }
    plVar7 = *(long **)(param_1 + 0x40);
    if (plVar7 == (long *)0x0) goto code_r0x013a22a0;
    uVar5 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
  }
  uVar9 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
  <>__AnonType19<Step,_bool,_bool>___ctor
            (uVar9,uVar5,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
code_r0x013a22d4:
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar9 = LitJson_JsonMapper__ToJson(0,uVar9,0);
  uVar12 = _str_cancelMatchmaking;
  uVar10 = MyUserData__get_hasInstance(0,0);
  if ((uVar10 & 1) != 0) {
    lVar11 = MyUserData__get_instance(0,0);
    if (lVar11 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar11 + 0x268) != '\0') {
      lVar11 = *(long *)(param_1 + 0x30);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar12,uVar9,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar12 = System_String__Format(0,_str____0____1__,uVar12,uVar9,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar9 = LitJson_JsonMapper__ToObject(0,uVar12,0);
      lVar13 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar12 = _str__;
      System_Object___ctor(lVar13,0);
      *(undefined4 *)(lVar13 + 0x14) = 2;
      *(undefined4 *)(lVar13 + 0x18) = 0;
      *(undefined8 *)(lVar13 + 0x30) = uVar9;
      *(undefined8 *)(lVar13 + 0x20) = uVar12;
      *(undefined4 *)(lVar13 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar13 + 0x10) = 4;
      lVar14 = lVar11;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar14 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar11 + 0x48);
      if (*(long *)(lVar11 + 0x50) == 0) {
        lVar14 = func_0x010eec64(0);
      }
      uVar12 = SocketIO_Encoder__Encode(lVar14,lVar13);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar12,0);
      return;
    }
  }
  uVar10 = Multiplay_MultiplayManager__get_isConnected(param_1);
  if ((uVar10 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(param_1 + 0x70) = 0;
  *(undefined1 *)(param_1 + 0x141) = 0;
  *(undefined1 *)(param_1 + 0x142) = 0;
  uVar12 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar10 = UnityEngine_Object__op_Inequality(0,uVar12,0,0);
  if ((uVar10 & 1) != 0) {
    lVar11 = *(long *)(param_1 + 0x30);
    if (lVar11 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar11,0);
    uVar12 = *(undefined8 *)(param_1 + 0x30);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    if ((bRam0000000004a04120 & 1) == 0) {
      func_0x010c65d4(0x906e,uVar12,0);
      bRam0000000004a04120 = 1;
    }
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    UNRECOVERED_JUMPTABLE_00 = pcRam0000000004a04118;
    if ((pcRam0000000004a04118 == (code *)0x0) &&
       (UNRECOVERED_JUMPTABLE_00 = (code *)func_0x0111e49c(&UNK_039a20c7),
       UNRECOVERED_JUMPTABLE_00 == (code *)0x0)) {
      uVar9 = func_0x010f000c(&UNK_039a20c7);
      func_0x010eebec(uVar9,0,0);
    }
    pcRam0000000004a04118 = UNRECOVERED_JUMPTABLE_00;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*pcRam0000000004a04118)(uVar12,0);
    return;
  }
  return;
}


