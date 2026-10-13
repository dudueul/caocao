// name: FairRuleManager$$OnDestroy
// entry: 0174824c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void FairRuleManager__OnDestroy(undefined8 param_1)

{
  long lVar1;
  undefined8 uVar2;
  long *plVar3;
  undefined8 uVar4;
  long *plVar5;
  undefined8 uVar6;
  undefined8 uStack_50;
  undefined8 uStack_48;
  
  if ((bRam00000000049f9c09 & 1) == 0) {
    func_0x010c65d4(0x5675);
    bRam00000000049f9c09 = 1;
  }
  Singleton<iTweenManager>__OnDestroy(param_1,_mm_Method_Singleton<FairRuleManager>_OnDestroy__);
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
  if (plVar3 != (long *)0x0) {
    if (*plVar3 == _class_System_Action_TypeInfo) goto code_r0x0174837c;
    func_0x0111ef28(&uStack_48,*(undefined8 *)(*plVar3 + 0x40));
    uVar4 = func_0x010ef81c(uStack_48);
    func_0x010eebec(uVar4,0,0);
    func_0x036457e0(&uStack_48);
  }
  plVar3 = (long *)0x0;
code_r0x0174837c:
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
      func_0x0111ef28(&uStack_50,*(undefined8 *)(*plVar5 + 0x40));
      uVar4 = func_0x010ef81c(uStack_50);
      func_0x010eebec(uVar4,0,0);
      func_0x036457e0(&uStack_50);
      plVar3 = (long *)0x0;
    }
  }
  *(long **)(lVar1 + 8) = plVar3;
  return;
}


