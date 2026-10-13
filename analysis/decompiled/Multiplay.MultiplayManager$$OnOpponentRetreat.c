// name: Multiplay.MultiplayManager$$OnOpponentRetreat
// entry: 013aa230

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnOpponentRetreat(long param_1,long param_2)

{
  undefined4 uVar1;
  ushort uVar2;
  uint uVar3;
  undefined8 uVar4;
  long lVar5;
  long lVar6;
  code *UNRECOVERED_JUMPTABLE_00;
  ulong uVar7;
  ulong uVar8;
  long *plVar9;
  undefined8 *puVar10;
  long lVar11;
  int *piVar12;
  undefined1 auVar13 [12];
  
  if ((bRam00000000049f7b19 & 1) == 0) {
    func_0x010c65d4(0x8a63);
    bRam00000000049f7b19 = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Util_TypeInfo);
  }
  lVar5 = Util__GetData(0,uVar4,_str_retreat,0,0);
  if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_LoadedSceneCheckUtil_TypeInfo);
  }
  uVar3 = LoadedSceneCheckUtil__get_isBattleMap(0,0);
  if ((uVar3 & 1) == 0) {
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) !=
         0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0
               )) {
      func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo);
    }
    auVar13 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,1,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    *(undefined1 (*) [12])(lVar6 + 0x128) = auVar13;
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar7 = Multiplay_MultiplayManager__get_isWatchingBattle();
    if ((uVar7 & 1) != 0) {
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      *(undefined1 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x20) = 1;
    }
  }
  else if (*(long *)(param_1 + 0x48) != 0) {
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar4 = *(undefined8 *)(lVar6 + 0x128);
    uVar1 = *(undefined4 *)(lVar6 + 0x130);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) !=
         0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0
               )) {
      func_0x010da55c();
    }
    uVar3 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar4,uVar1,0);
    if ((uVar3 & 1) == 0) {
      lVar6 = *(long *)(param_1 + 0x48);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      lVar6 = BattlePlay_BattlePlayData__get_host(lVar6,0);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      if (*(long *)(lVar6 + 0x10) == lVar5) {
        lVar6 = *(long *)(param_1 + 0x48);
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        lVar6 = BattlePlay_BattlePlayData__get_opponent(lVar6,0);
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        if (*(long *)(lVar6 + 0x10) == lVar5) {
          return;
        }
        Multiplay_MultiplayManager__SetLose(param_1,0);
      }
      else {
        Multiplay_MultiplayManager__SetWinByOpponentRetreat(param_1);
      }
    }
  }
  lVar6 = *(long *)(param_1 + 0x138);
  if (lVar6 == 0) {
    return;
  }
  if (*(long *)(lVar6 + 0x58) != 0) {
    System_Action<ulong>__Invoke
              (*(long *)(lVar6 + 0x58),lVar5,_mm_Method_System_Action<ulong>_Invoke__);
  }
  UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar6 + 0x10);
  plVar9 = *(long **)(lVar6 + 0x20);
  puVar10 = *(undefined8 **)(lVar6 + 0x28);
  func_0x010da364(puVar10);
  uVar3 = func_0x01101d6c(puVar10);
  if ((uVar3 & 1) == 0) {
    if (*(char *)((long)puVar10 + 0x4e) != '\x01') {
                    /* WARNING: Could not recover jumptable at 0x029a44c8. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE_00)(0,plVar9,lVar5,puVar10);
      return;
    }
    plVar9 = (long *)0x0;
  }
  else if ((*(short *)((long)puVar10 + 0x4c) != -1) &&
          (((plVar9 == (long *)0x0 || ((*(byte *)(*plVar9 + 0x10d) & 1) == 0)) &&
           (*(long *)(lVar6 + 0x18) != 0)))) {
    uVar7 = func_0x01101d58(puVar10);
    func_0x01101dd4(puVar10);
    uVar8 = func_0x010ba65c();
    if ((uVar7 & 1) == 0) {
      uVar2 = *(ushort *)((long)puVar10 + 0x4c);
      if ((uVar8 & 1) == 0) {
        lVar6 = *plVar9 + (ulong)uVar2 * 0x10;
        UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar6 + 0x130);
        puVar10 = *(undefined8 **)(lVar6 + 0x138);
      }
      else {
        lVar6 = func_0x01101dd4(puVar10);
        lVar11 = *plVar9;
        if ((ulong)*(ushort *)(lVar11 + 0x122) != 0) {
          uVar7 = 0;
          piVar12 = (int *)(*(long *)(lVar11 + 0xb0) + 8);
          do {
            if (*(long *)(piVar12 + -2) == lVar6) {
              puVar10 = (undefined8 *)(lVar11 + (ulong)(*piVar12 + (uint)uVar2) * 0x10 + 0x130);
              goto code_r0x029a4590;
            }
            uVar7 = uVar7 + 1;
            piVar12 = piVar12 + 4;
          } while (uVar7 < *(ushort *)(lVar11 + 0x122));
        }
        puVar10 = (undefined8 *)func_0x010bc584(plVar9,lVar6,(ulong)uVar2);
code_r0x029a4590:
        UNRECOVERED_JUMPTABLE_00 = (code *)*puVar10;
        puVar10 = (undefined8 *)puVar10[1];
      }
    }
    else {
      if ((uVar8 & 1) == 0) {
        uVar4 = *(undefined8 *)(*plVar9 + (ulong)*(ushort *)((long)puVar10 + 0x4c) * 0x10 + 0x138);
      }
      else {
        lVar6 = *plVar9;
        if ((ulong)*(ushort *)(lVar6 + 0x122) != 0) {
          uVar7 = 0;
          piVar12 = (int *)(*(long *)(lVar6 + 0xb0) + 8);
          do {
            if (*(long *)(piVar12 + -2) == puVar10[3]) {
              lVar6 = lVar6 + (ulong)(*piVar12 + (uint)*(ushort *)((long)puVar10 + 0x4c)) * 0x10 +
                      0x130;
              goto code_r0x029a455c;
            }
            uVar7 = uVar7 + 1;
            piVar12 = piVar12 + 4;
          } while (uVar7 < *(ushort *)(lVar6 + 0x122));
        }
        lVar6 = func_0x010bc584(plVar9);
code_r0x029a455c:
        uVar4 = *(undefined8 *)(lVar6 + 8);
      }
      puVar10 = (undefined8 *)func_0x010da294(uVar4,puVar10);
      UNRECOVERED_JUMPTABLE_00 = (code *)*puVar10;
    }
  }
                    /* WARNING: Could not recover jumptable at 0x029a44a0. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*UNRECOVERED_JUMPTABLE_00)(plVar9,lVar5,puVar10);
  return;
}


