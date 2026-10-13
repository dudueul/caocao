// name: Multiplay.MultiplayManager$$OnWatchFinishedPostAction
// entry: 013ab65c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnWatchFinishedPostAction(undefined8 param_1,long param_2)

{
  undefined4 uVar1;
  ulong uVar2;
  undefined8 uVar3;
  long lVar4;
  undefined8 uVar5;
  long lVar6;
  undefined8 uVar7;
  long lVar8;
  long *plVar9;
  long *plVar10;
  long lVar11;
  undefined8 uStack_70;
  undefined8 uStack_68;
  
  if ((bRam00000000049f7b1d & 1) == 0) {
    func_0x010c65d4(0x8a72);
    bRam00000000049f7b1d = 1;
  }
  Multiplay_MultiplayManager__SetState(param_1,0);
  lVar4 = ___L(0,_str_관전_종료,0);
  uVar7 = _mm_Method_Multiplay_MultiplayManager_<OnWatchFinishedPostAction>m__0__;
  uVar5 = func_0x010fdda8(_class_System_Action_TypeInfo);
  System_Action___ctor(uVar5,param_1,uVar7,0);
  if ((bRam00000000049ff059 & 1) == 0) {
    func_0x010c65d4(0x96f9);
    bRam00000000049ff059 = 1;
  }
  lVar6 = func_0x010fdda8(_class_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_TypeInfo);
  System_Object___ctor(lVar6,0);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar6 + 0x10) = uVar5;
  if (((*(byte *)(_class_PrefabsLib_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_PrefabsLib_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar7 = PrefabsLib__get_m_common_goods_popupBtn2(0,0);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar5 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar8 = Util__InstantiateOnUIRoot(0,uVar7,0,uVar5,0,0);
  if (lVar8 == 0) {
    func_0x010eec64(0);
  }
  plVar9 = (long *)UnityEngine_GameObject__AddComponent<object>
                             (lVar8,
                              _mm_Method_UnityEngine_GameObject_AddComponent<PanelPopup1BtnConfirm>__
                             );
  if (plVar9 == (long *)0x0) {
    func_0x010eec64(0);
    CaocaoUIModalPopup__set_parent(0,0,0);
    func_0x010eec64(0);
    plVar10 = (long *)0x0;
  }
  else {
    CaocaoUIModalPopup__set_parent(plVar9,0,0);
    plVar10 = plVar9;
  }
  CaocaoUIModalPopup__set_IgnoreTappingOutSide(plVar10,1,0);
  lVar11 = func_0x01101ad4(_class_object___TypeInfo,0);
  if (plVar9 == (long *)0x0) {
    func_0x010eec64(0);
  }
  plVar9[0x1d] = lVar4;
  plVar9[0x1f] = param_2;
  plVar9[0x20] = lVar11;
  uVar7 = _mm_Method_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_<>m__0__;
  uVar5 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
  System_Func<PanelPopupBase_PopupState>___ctor
            (uVar5,lVar6,uVar7,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
  if (plVar9 == (long *)0x0) {
    func_0x010eec64(0);
    PanelPopup2Buttons__add_OnConfirm(0,uVar5);
    func_0x010eec64(0);
  }
  else {
    PanelPopup2Buttons__add_OnConfirm(plVar9,uVar5);
  }
  (**(code **)(*plVar9 + 0x2c0))(plVar9,*(undefined8 *)(*plVar9 + 0x2c8));
  if ((bRam00000000049f7606 & 1) == 0) {
    func_0x010c65d4(0xf91f,lVar8,0);
    bRam00000000049f7606 = 1;
  }
  if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = LoadingUI__IsExistInstance(0,0);
  if ((uVar2 & 1) == 0) {
    return;
  }
  if (lVar8 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = UnityEngine_GameObject__get_transform(lVar8,0);
  if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_LoadingUI_TypeInfo);
  }
  uVar1 = LoadingUI__get_layer(0,0);
  LayerUtil__ChangeLayersRecursively(0,uVar7,uVar1,0);
  if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar4 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = *(undefined8 *)(lVar4 + 0x30);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = _mm_Method_Util_OnTransitionEnded__;
  if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) == 0) {
    uVar3 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar3,0,uVar5,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) = uVar3;
  }
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  plVar9 = (long *)System_Delegate__Remove
                             (0,uVar7,*(undefined8 *)
                                       (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388),0);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  if (plVar9 == (long *)0x0) {
code_r0x012ffbe0:
    plVar9 = (long *)0x0;
  }
  else if (*plVar9 != _class_System_Action_TypeInfo) {
    func_0x0111ef28(&uStack_68,*(undefined8 *)(*plVar9 + 0x40));
    uVar7 = func_0x010ef81c(uStack_68);
    func_0x010eebec(uVar7,0,0);
    func_0x036457e0(&uStack_68);
    goto code_r0x012ffbe0;
  }
  *(long **)(lVar4 + 0x30) = plVar9;
  if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar4 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = _mm_Method_Util_OnTransitionEnded__;
  uVar5 = *(undefined8 *)(lVar4 + 0x30);
  if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) == 0) {
    uVar3 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar3,0,uVar7,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) = uVar3;
  }
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  plVar9 = (long *)System_Delegate__Combine
                             (0,uVar5,*(undefined8 *)
                                       (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390),0);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  if (plVar9 != (long *)0x0) {
    if (*plVar9 == _class_System_Action_TypeInfo) goto code_r0x012ffd00;
    func_0x0111ef28(&uStack_70,*(undefined8 *)(*plVar9 + 0x40));
    uVar7 = func_0x010ef81c(uStack_70);
    func_0x010eebec(uVar7,0,0);
    func_0x036457e0(&uStack_70);
  }
  plVar9 = (long *)0x0;
code_r0x012ffd00:
  *(long **)(lVar4 + 0x30) = plVar9;
  *(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x68) = lVar8;
  return;
}


