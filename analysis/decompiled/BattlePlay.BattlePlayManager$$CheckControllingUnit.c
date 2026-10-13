// name: BattlePlay.BattlePlayManager$$CheckControllingUnit
// entry: 01979e9c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

bool BattlePlay_BattlePlayManager__CheckControllingUnit(long param_1,long param_2)

{
  int iVar1;
  int iVar2;
  uint uVar3;
  int iVar4;
  long lVar5;
  undefined8 uVar6;
  long *plVar7;
  
  if ((bRam00000000049fae15 & 1) == 0) {
    func_0x010c65d4(0x2061);
    bRam00000000049fae15 = 1;
  }
  if (param_2 != 0) {
    iVar1 = *(int *)(param_2 + 0x4c);
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar5 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    iVar2 = ControlFlow__get_currentUnitTurnIdx(lVar5,0);
    if (iVar1 != iVar2) {
      return false;
    }
    if (*(int *)(param_2 + 0x18) != 0) {
      if ((*(int *)(param_2 + 0x18) == 1) && (*(int *)(param_2 + 0x2c) != 0)) {
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar5 = SceneSingleton<WorldBossBattleController>__get_instance
                          (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        uVar6 = ControlFlow__get_controllingUnit(lVar5,0);
        if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
        }
        uVar3 = UnityEngine_Object__op_Equality(0,uVar6,0,0);
        if ((uVar3 & 1) != 0) {
          return false;
        }
      }
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar5 = SceneSingleton<WorldBossBattleController>__get_instance
                        (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      uVar6 = ControlFlow__get_controllingUnit(lVar5,0);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
      }
      uVar3 = UnityEngine_Object__op_Equality(0,uVar6,0,0);
      if ((uVar3 & 1) == 0) {
        iVar1 = *(int *)(param_2 + 0x2c);
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar5 = SceneSingleton<WorldBossBattleController>__get_instance
                          (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        plVar7 = (long *)ControlFlow__get_controllingUnit(lVar5,0);
        if (plVar7 == (long *)0x0) {
          func_0x010eec64(0);
        }
        iVar2 = (**(code **)(*plVar7 + 0x1c0))(plVar7,*(undefined8 *)(*plVar7 + 0x1c8));
        if (iVar1 == iVar2) {
          lVar5 = *(long *)(param_1 + 0x18);
          iVar1 = *(int *)(param_2 + 0x28);
          if (lVar5 == 0) {
            func_0x010eec64(0);
          }
          lVar5 = *(long *)(lVar5 + 0x40);
          if (lVar5 == 0) {
            func_0x010eec64(0);
          }
          iVar2 = *(int *)(lVar5 + 0x38);
          if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar5 = SceneSingleton<WorldBossBattleController>__get_instance
                            (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
          if (lVar5 == 0) {
            func_0x010eec64(0);
          }
          plVar7 = (long *)ControlFlow__get_controllingUnit(lVar5,0);
          if (plVar7 == (long *)0x0) {
            func_0x010eec64(0);
          }
          iVar4 = (**(code **)(*plVar7 + 0x260))(plVar7,*(undefined8 *)(*plVar7 + 0x268));
          return (uint)(iVar2 != iVar1) << 1 == iVar4;
        }
        if (*(int *)(param_2 + 0x18) != 0xe) {
          return false;
        }
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar5 = SceneSingleton<WorldBossBattleController>__get_instance
                          (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        plVar7 = (long *)ControlFlow__get_controllingUnit(lVar5,0);
        if (plVar7 == (long *)0x0) {
          func_0x010eec64(0);
        }
        uVar3 = (**(code **)(*plVar7 + 0x1a0))(plVar7,*(undefined8 *)(*plVar7 + 0x1a8));
        if ((uVar3 & 1) != 0) {
          return false;
        }
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar5 = SceneSingleton<WorldBossBattleController>__get_instance
                          (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        ControlFlow__CancelAllAction(lVar5,1,0);
        return true;
      }
    }
  }
  return true;
}


