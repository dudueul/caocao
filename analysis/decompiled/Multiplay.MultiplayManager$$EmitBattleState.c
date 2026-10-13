// name: Multiplay.MultiplayManager$$EmitBattleState
// entry: 013a663c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__EmitBattleState(long param_1,uint param_2)

{
  long lVar1;
  uint uVar2;
  int iVar3;
  undefined4 uVar4;
  ulong uVar5;
  long lVar6;
  long *plVar7;
  long *plVar8;
  undefined8 uVar9;
  long lVar10;
  long lVar11;
  code *pcVar12;
  ushort uVar13;
  int iVar14;
  undefined8 uVar15;
  undefined8 uVar16;
  
  if ((bRam00000000049f7afd & 1) == 0) {
    func_0x010c65d4(0x8a27);
    bRam00000000049f7afd = 1;
  }
  if (*(char *)(param_1 + 0x141) == '\0') {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = Multiplay_MultiplayManager__get_isMyActiveBattle();
  if ((uVar5 & 1) == 0) {
    return;
  }
  if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  uVar15 = *(undefined8 *)(lVar6 + 0x128);
  uVar4 = *(undefined4 *)(lVar6 + 0x130);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) != 0)
     && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar15,uVar4,0);
  if ((uVar2 & 1) != 0) {
    return;
  }
  if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
    uVar15 = BattlePlay_BattlePlayData__get_battleKey(*(long *)(param_1 + 0x48),0);
    uVar16 = *(undefined8 *)(param_1 + 0x48);
    plVar7 = (long *)func_0x010fdda8(_class_BattlePlay_BattleState_TypeInfo);
    BattlePlay_BattleState___ctor(plVar7,uVar15,uVar16,0);
    if (plVar7 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((plVar7[6] != 0) &&
       (iVar3 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                          (plVar7[6],
                           _mm_Method_System_Collections_Generic_List<BattleUnitState>_get_Count__),
       iVar3 != 0)) {
      if ((*(ushort *)(param_1 + 100) & 0xff) == 0) {
        uVar13 = *(ushort *)(param_1 + 100) >> 8;
      }
      else {
        iVar3 = -1;
        if ((int)plVar7[7] < 1) {
          iVar3 = 1;
        }
        *(int *)(plVar7 + 7) = (int)plVar7[7] + iVar3;
        *(undefined1 *)(param_1 + 100) = 0;
        uVar13 = (ushort)*(byte *)(param_1 + 0x65);
      }
      if (uVar13 != 0) {
        lVar6 = plVar7[6];
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        iVar3 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                          (lVar6,
                           _mm_Method_System_Collections_Generic_List<BattleUnitState>_get_Count__);
        if (0 < iVar3) {
          iVar14 = 0;
          do {
            if (plVar7 == (long *)0x0) {
              func_0x010eec64(0);
            }
            lVar6 = plVar7[6];
            if (lVar6 == 0) {
              func_0x010eec64(0);
            }
            lVar6 = System_Collections_Generic_List<object>__get_Item
                              (lVar6,iVar14,
                               _mm_Method_System_Collections_Generic_List<BattleUnitState>_get_Item__
                              );
            if (lVar6 == 0) {
              func_0x010eec64(0);
            }
            if (0 < *(int *)(lVar6 + 0x24)) {
              if (plVar7 == (long *)0x0) {
                func_0x010eec64(0);
              }
              lVar6 = plVar7[6];
              if (lVar6 == 0) {
                func_0x010eec64(0);
              }
              lVar6 = System_Collections_Generic_List<object>__get_Item
                                (lVar6,iVar14,
                                 _mm_Method_System_Collections_Generic_List<BattleUnitState>_get_Item__
                                );
              if (lVar6 == 0) {
                func_0x010eec64(0);
                iVar3 = iRam0000000000000024;
                func_0x010eec64(0);
              }
              else {
                iVar3 = *(int *)(lVar6 + 0x24);
              }
              *(int *)(lVar6 + 0x24) = iVar3 + 10;
              break;
            }
            iVar14 = iVar14 + 1;
          } while (iVar14 < iVar3);
        }
        *(undefined1 *)(param_1 + 0x65) = 0;
      }
      plVar8 = (long *)func_0x010fdda8(_class_LitJson_JsonWriter_TypeInfo);
      LitJson_JsonWriter___ctor(plVar8,0);
      if (plVar7 == (long *)0x0) {
        func_0x010eec64(0);
      }
      (**(code **)(*plVar7 + 0x170))(plVar7,plVar8,*(undefined8 *)(*plVar7 + 0x178));
      lVar6 = *(long *)(param_1 + 0x48);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      uVar15 = BattlePlay_BattlePlayData__get_battleKey(lVar6,0);
      if (plVar8 == (long *)0x0) {
        func_0x010eec64(0);
      }
      uVar16 = (**(code **)(*plVar8 + 0x160))(plVar8,*(undefined8 *)(*plVar8 + 0x168));
      plVar7 = *(long **)(param_1 + 0x40);
      if (plVar7 == (long *)0x0) {
        uVar4 = 8;
      }
      else {
        uVar4 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
      }
      uVar9 = func_0x010fdda8(_class_<>__AnonType48<string,_string,_bool,_Step>_TypeInfo);
      <>__AnonType48<string,_string,_bool,_Step>___ctor
                (uVar9,uVar15,uVar16,param_2 & 1,uVar4,
                 _mm_Method_<>__AnonType48<string,_string,_bool,_Step>__ctor__);
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar16 = LitJson_JsonMapper__ToJson(0,uVar9,0);
      uVar15 = _str_checkBattleState;
      goto Multiplay_MultiplayManager__EmitSocketMessage;
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
    uVar4 = 8;
code_r0x013a22a4:
    uVar15 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
    <>__AnonType19<Step,_bool,_bool>___ctor
              (uVar15,uVar4,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
  }
  else {
    iVar3 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
    if (iVar3 < 1) {
code_r0x013a2284:
      plVar7 = *(long **)(param_1 + 0x40);
      if (plVar7 == (long *)0x0) goto code_r0x013a22a0;
      uVar4 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
      goto code_r0x013a22a4;
    }
    if (*(long *)(param_1 + 0x40) == 0) goto code_r0x013a22a0;
    if ((*(long *)(param_1 + 0x48) == 0) ||
       (lVar6 = BattlePlay_BattlePlayData__get_opponent(*(long *)(param_1 + 0x48),0), lVar6 == 0))
    goto code_r0x013a2284;
    lVar6 = *(long *)(param_1 + 0x48);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar16 = BattlePlay_BattlePlayData__get_battleKey(lVar6,0);
    plVar7 = *(long **)(param_1 + 0x40);
    if (plVar7 == (long *)0x0) {
      uVar4 = 8;
    }
    else {
      uVar4 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
    }
    uVar15 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
    <>__AnonType18<string,_bool,_bool,_Step>___ctor
              (uVar15,uVar16,1,0,uVar4,_mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__);
  }
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar16 = LitJson_JsonMapper__ToJson(0,uVar15,0);
  uVar15 = _str_cancelMatchmaking;
Multiplay_MultiplayManager__EmitSocketMessage:
  uVar5 = MyUserData__get_hasInstance(0,0);
  if ((uVar5 & 1) != 0) {
    lVar6 = MyUserData__get_instance(0,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar6 + 0x268) != '\0') {
      lVar6 = *(long *)(param_1 + 0x30);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar15,uVar16,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar15 = System_String__Format(0,_str____0____1__,uVar15,uVar16,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar16 = LitJson_JsonMapper__ToObject(0,uVar15,0);
      lVar10 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar15 = _str__;
      System_Object___ctor(lVar10,0);
      *(undefined4 *)(lVar10 + 0x14) = 2;
      *(undefined4 *)(lVar10 + 0x18) = 0;
      *(undefined8 *)(lVar10 + 0x30) = uVar16;
      *(undefined8 *)(lVar10 + 0x20) = uVar15;
      *(undefined4 *)(lVar10 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar10 + 0x10) = 4;
      lVar11 = lVar6;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar11 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar6 + 0x48);
      if (*(long *)(lVar6 + 0x50) == 0) {
        lVar11 = func_0x010eec64(0);
      }
      uVar15 = SocketIO_Encoder__Encode(lVar11,lVar10);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar15,0);
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
  uVar15 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = UnityEngine_Object__op_Inequality(0,uVar15,0,0);
  if ((uVar5 & 1) != 0) {
    lVar6 = *(long *)(param_1 + 0x30);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar6,0);
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
    pcVar12 = pcRam0000000004a04118;
    if ((pcRam0000000004a04118 == (code *)0x0) &&
       (pcVar12 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar12 == (code *)0x0)) {
      uVar16 = func_0x010f000c(&UNK_039a20c7);
      func_0x010eebec(uVar16,0,0);
    }
    pcRam0000000004a04118 = pcVar12;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*pcRam0000000004a04118)(uVar15,0);
    return;
  }
  return;
}


