// name: FairRuleManager$$Awake
// entry: 01747e84

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void FairRuleManager__Awake(undefined8 param_1)

{
  long lVar1;
  undefined8 uVar2;
  long *plVar3;
  undefined8 uVar4;
  long *plVar5;
  undefined8 uVar6;
  undefined8 uStack_70;
  undefined8 uStack_68;
  undefined8 uStack_60;
  undefined8 uStack_58;
  
  if ((bRam00000000049f9c07 & 1) == 0) {
    func_0x010c65d4(0x5669);
    bRam00000000049f9c07 = 1;
  }
  Singleton<iTweenManager>__Awake(param_1,_mm_Method_Singleton<FairRuleManager>_Awake__);
  if ((bRam00000000049f9c08 & 1) == 0) {
    func_0x010c65d4(0x5674);
    bRam00000000049f9c08 = 1;
  }
  if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar1 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = _mm_Method_FairRuleManager_OnBattleModeChanged__;
  uVar6 = *(undefined8 *)(lVar1 + 0x20);
  uVar2 = func_0x010fdda8(_class_System_Action_TypeInfo);
  System_Action___ctor(uVar2,param_1,uVar4,0);
  plVar3 = (long *)System_Delegate__Remove(0,uVar6,uVar2,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  if (plVar3 == (long *)0x0) {
code_r0x01747ff8:
    plVar3 = (long *)0x0;
  }
  else if (*plVar3 != _class_System_Action_TypeInfo) {
    func_0x0111ef28(&uStack_68,*(undefined8 *)(*plVar3 + 0x40));
    uVar4 = func_0x010ef81c(uStack_68);
    func_0x010eebec(uVar4,0,0);
    func_0x036457e0(&uStack_68);
    goto code_r0x01747ff8;
  }
  *(long **)(lVar1 + 0x20) = plVar3;
  lVar1 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = _mm_Method_FairRuleManager_OnBattleModeChanged__;
  uVar6 = *(undefined8 *)(lVar1 + 0x20);
  uVar2 = func_0x010fdda8(_class_System_Action_TypeInfo);
  System_Action___ctor(uVar2,param_1,uVar4,0);
  plVar3 = (long *)System_Delegate__Combine(0,uVar6,uVar2,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  if (plVar3 != (long *)0x0) {
    if (*plVar3 == _class_System_Action_TypeInfo) goto code_r0x017480a4;
    func_0x0111ef28(&uStack_60,*(undefined8 *)(*plVar3 + 0x40));
    uVar4 = func_0x010ef81c(uStack_60);
    func_0x010eebec(uVar4,0,0);
    func_0x036457e0(&uStack_60);
  }
  plVar3 = (long *)0x0;
code_r0x017480a4:
  *(long **)(lVar1 + 0x20) = plVar3;
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar4 = _mm_Method_FairRuleManager_OnMultiplayModeChanged__;
  uVar6 = *(undefined8 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 8);
  uVar2 = func_0x010fdda8(_class_System_Action_TypeInfo);
  System_Action___ctor(uVar2,param_1,uVar4,0);
  plVar5 = (long *)System_Delegate__Remove(0,uVar6,uVar2,0);
  lVar1 = *(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8);
  plVar3 = (long *)0x0;
  if (plVar5 != (long *)0x0) {
    plVar3 = plVar5;
    if (*plVar5 != _class_System_Action_TypeInfo) {
      func_0x0111ef28(&uStack_58,*(undefined8 *)(*plVar5 + 0x40));
      uVar4 = func_0x010ef81c(uStack_58);
      func_0x010eebec(uVar4,0,0);
      func_0x036457e0(&uStack_58);
      plVar3 = (long *)0x0;
    }
  }
  *(long **)(lVar1 + 8) = plVar3;
  uVar4 = _mm_Method_FairRuleManager_OnMultiplayModeChanged__;
  uVar6 = *(undefined8 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 8);
  uVar2 = func_0x010fdda8(_class_System_Action_TypeInfo);
  System_Action___ctor(uVar2,param_1,uVar4,0);
  plVar5 = (long *)System_Delegate__Combine(0,uVar6,uVar2,0);
  lVar1 = *(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8);
  plVar3 = (long *)0x0;
  if (plVar5 != (long *)0x0) {
    plVar3 = plVar5;
    if (*plVar5 != _class_System_Action_TypeInfo) {
      func_0x0111ef28(&uStack_70,*(undefined8 *)(*plVar5 + 0x40));
      uVar4 = func_0x010ef81c(uStack_70);
      func_0x010eebec(uVar4,0,0);
      func_0x036457e0(&uStack_70);
      plVar3 = (long *)0x0;
    }
  }
  *(long **)(lVar1 + 8) = plVar3;
  return;
}


