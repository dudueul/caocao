// name: Multiplay.MultiplayManager$$CancelMatchmaking
// entry: 013a1fa0

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 Multiplay_MultiplayManager__CancelMatchmaking(undefined8 param_1)

{
  uint uVar1;
  long lVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  long *plVar5;
  long lVar6;
  
  if ((bRam00000000049f7ad4 & 1) == 0) {
    func_0x010c65d4(0x8a1c);
    bRam00000000049f7ad4 = 1;
  }
  lVar2 = func_0x010fdda8(
                         _class_Multiplay_MultiplayManager_<CancelMatchmaking>c__AnonStorey4_TypeInfo
                         );
  Multiplay_MultiplayManager_<CancelMatchmaking>c__AnonStorey4___ctor(lVar2,0);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar2 + 0x18) = param_1;
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
  uVar3 = 0;
  if ((uVar1 & 1) == 0) {
    if (lVar2 == 0) {
      func_0x010eec64(0);
    }
    uVar3 = _mm_Method_Multiplay_MultiplayManager_<CancelMatchmaking>c__AnonStorey4_<>m__0__;
    lVar6 = *(long *)(lVar2 + 0x10);
    uVar4 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar4,lVar2,uVar3,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    *(undefined8 *)(lVar6 + 0xe0) = uVar4;
    uVar3 = _mm_Method_Multiplay_MultiplayManager_<CancelMatchmaking>c__AnonStorey4_<>m__1__;
    lVar6 = *(long *)(lVar2 + 0x10);
    uVar4 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
    System_Func<PanelPopupBase_PopupState>___ctor
              (uVar4,lVar2,uVar3,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    PanelPopup2Buttons__add_OnYes(lVar6,uVar4,0);
    plVar5 = *(long **)(lVar2 + 0x10);
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
    }
    (**(code **)(*plVar5 + 0x2c0))(plVar5,*(undefined8 *)(*plVar5 + 0x2c8));
    uVar3 = *(undefined8 *)(lVar2 + 0x10);
  }
  return uVar3;
}


