// name: BattlePlay.BattlePlayManager$$ResetUnitTurnTimeByCheat
// entry: 0197787c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__ResetUnitTurnTimeByCheat(long param_1)

{
  undefined4 uVar1;
  int iVar2;
  int iVar3;
  long lVar4;
  long lVar5;
  int iVar6;
  
  if ((bRam00000000049fae0e & 1) == 0) {
    func_0x010c65d4(0x206d);
    bRam00000000049fae0e = 1;
  }
  lVar5 = *(long *)(param_1 + 0x18);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  lVar5 = *(long *)(lVar5 + 0x28);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  iVar2 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                    (lVar5,_mm_Method_System_Collections_Generic_List<BattleUser>_get_Count__);
  if (0 < iVar2) {
    iVar6 = 0;
    do {
      lVar5 = *(long *)(param_1 + 0x18);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      lVar5 = *(long *)(lVar5 + 0x28);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      lVar5 = System_Collections_Generic_List<object>__get_Item
                        (lVar5,iVar6,
                         _mm_Method_System_Collections_Generic_List<BattleUser>_get_Item__);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      uVar1 = *(undefined4 *)(lVar5 + 0xac);
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar4 = SceneSingleton<WorldBossBattleController>__get_instance
                        (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      iVar3 = ControlFlow__get_currentUnitTurn(lVar4,0);
      if (lVar5 == 0) {
        func_0x010eec64(0);
        iRam00000000000000ac = iVar3 + -2;
        func_0x010eec64(0);
        BattlePlay_BattlePlayManager__ResetUnitTurnTime(param_1,uRam0000000000000010,0);
        func_0x010eec64(0);
      }
      else {
        *(int *)(lVar5 + 0xac) = iVar3 + -2;
        BattlePlay_BattlePlayManager__ResetUnitTurnTime(param_1,*(undefined8 *)(lVar5 + 0x10),0);
      }
      iVar6 = iVar6 + 1;
      *(undefined4 *)(lVar5 + 0xac) = uVar1;
    } while (iVar2 != iVar6);
  }
  return;
}


