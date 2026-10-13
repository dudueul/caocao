// name: Multiplay.MultiplayManager$$OnNeedToUpdate
// entry: 013ac354

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnNeedToUpdate(undefined8 param_1)

{
  uint uVar1;
  long lVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  long *plVar5;
  long lVar6;
  
  if ((bRam00000000049f7b24 & 1) == 0) {
    func_0x010c65d4(0x8a62);
    bRam00000000049f7b24 = 1;
  }
  lVar2 = func_0x010fdda8(_class_Multiplay_MultiplayManager_<OnNeedToUpdate>c__AnonStorey5_TypeInfo)
  ;
  Multiplay_MultiplayManager_<OnNeedToUpdate>c__AnonStorey5___ctor(lVar2,0);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar2 + 0x18) = param_1;
  PopupMatchMaking__Close(0,0);
  if (((*(byte *)(_class_PanelPopup2Buttons_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_PanelPopup2Buttons_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = PanelPopup2Buttons__CreateInstance(0,0);
  *(undefined8 *)(lVar2 + 0x10) = uVar3;
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar1 = UnityEngine_Object__op_Equality(0,uVar3,0,0);
  if ((uVar1 & 1) != 0) {
    return;
  }
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  uVar3 = _mm_Method_Multiplay_MultiplayManager_<OnNeedToUpdate>c__AnonStorey5_<>m__0__;
  lVar6 = *(long *)(lVar2 + 0x10);
  uVar4 = func_0x010fdda8(_class_System_Action_TypeInfo);
  System_Action___ctor(uVar4,lVar2,uVar3,0);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar6 + 0xe0) = uVar4;
  lVar6 = *(long *)(lVar2 + 0x10);
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = _mm_Method_Multiplay_MultiplayManager_<OnNeedToUpdate>m__2__;
  if (*(long *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x28) == 0) {
    uVar4 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
    System_Func<PanelPopupBase_PopupState>___ctor
              (uVar4,0,uVar3,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    *(undefined8 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x28) = uVar4;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = *(undefined8 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x28);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  PanelPopup2Buttons__add_OnYes(lVar6,uVar3,0);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  uVar3 = _mm_Method_Multiplay_MultiplayManager_<OnNeedToUpdate>c__AnonStorey5_<>m__1__;
  lVar6 = *(long *)(lVar2 + 0x10);
  uVar4 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
  System_Func<PanelPopupBase_PopupState>___ctor
            (uVar4,lVar2,uVar3,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  PanelPopup2Buttons__add_OnCancel(lVar6,uVar4,0);
  lVar6 = *(long *)(lVar2 + 0x10);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  CaocaoUIModalPopup__set_DepthGrade(lVar6,1000,0);
  plVar5 = *(long **)(lVar2 + 0x10);
  if (plVar5 == (long *)0x0) {
    func_0x010eec64(0);
  }
                    /* WARNING: Could not recover jumptable at 0x013ac620. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)(*plVar5 + 0x2c0))(plVar5,*(undefined8 *)(*plVar5 + 0x2c8));
  return;
}


