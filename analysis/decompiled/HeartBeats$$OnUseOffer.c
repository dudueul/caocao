// name: HeartBeats$$OnUseOffer
// entry: 016cc364

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void HeartBeats__OnUseOffer(undefined8 param_1,long param_2)

{
  undefined4 uVar1;
  uint uVar2;
  undefined8 uVar3;
  long lVar4;
  undefined8 uVar5;
  ulong uVar6;
  long lVar7;
  long lVar8;
  long lVar9;
  long *plVar10;
  long *plVar11;
  long lVar12;
  undefined8 uVar13;
  undefined8 uStack_70;
  undefined8 uStack_68;
  
  if ((bRam00000000049f97aa & 1) == 0) {
    func_0x010c65d4(0x6a23);
    bRam00000000049f97aa = 1;
  }
  lVar4 = _class_HttpRequest_TypeInfo;
  if (((*(byte *)(_class_HttpRequest_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_HttpRequest_TypeInfo + 0xd8) == 0)) {
    lVar4 = func_0x010da55c();
  }
  uVar2 = HttpRequest__CommonCheck(lVar4,param_2);
  if ((uVar2 & 1) != 0) {
    return;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
    LitJson_JsonData__get_Item(0,_str_packet,0);
    func_0x010eec64(0);
  }
  else {
    LitJson_JsonData__get_Item(param_2,_str_packet,0);
  }
  uVar5 = LitJson_JsonData__get_Item(param_2,_str_success,0);
  uVar6 = LitJson_JsonData__op_Explicit(0,uVar5,0);
  if ((uVar6 & 1) == 0) {
    return;
  }
  lVar4 = ___L(0,_str_상품_지급_완료,0);
  uVar5 = ___L(0,_str_서신에서_보상을_확인하세요_,0);
  if (((*(byte *)(_class_Localization_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Localization_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Localization_TypeInfo);
  }
  lVar7 = Localization__Get(0,uVar5,0);
  if ((bRam00000000049ff059 & 1) == 0) {
    func_0x010c65d4(0x96f9);
    bRam00000000049ff059 = 1;
  }
  lVar8 = func_0x010fdda8(_class_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_TypeInfo);
  System_Object___ctor(lVar8,0);
  if (lVar8 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar8 + 0x10) = 0;
  if (((*(byte *)(_class_PrefabsLib_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_PrefabsLib_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = PrefabsLib__get_m_common_goods_popupBtn2(0,0);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar13 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar9 = Util__InstantiateOnUIRoot(0,uVar5,0,uVar13,0,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  plVar10 = (long *)UnityEngine_GameObject__AddComponent<object>
                              (lVar9,
                               _mm_Method_UnityEngine_GameObject_AddComponent<PanelPopup1BtnConfirm>__
                              );
  if (plVar10 == (long *)0x0) {
    func_0x010eec64(0);
    CaocaoUIModalPopup__set_parent(0,0,0);
    func_0x010eec64(0);
    plVar11 = (long *)0x0;
  }
  else {
    CaocaoUIModalPopup__set_parent(plVar10,0,0);
    plVar11 = plVar10;
  }
  CaocaoUIModalPopup__set_IgnoreTappingOutSide(plVar11,0,0);
  lVar12 = func_0x01101ad4(_class_object___TypeInfo,0);
  if (plVar10 == (long *)0x0) {
    func_0x010eec64(0);
  }
  plVar10[0x1d] = lVar4;
  plVar10[0x1f] = lVar7;
  plVar10[0x20] = lVar12;
  uVar5 = _mm_Method_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_<>m__0__;
  uVar13 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
  System_Func<PanelPopupBase_PopupState>___ctor
            (uVar13,lVar8,uVar5,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
  if (plVar10 == (long *)0x0) {
    func_0x010eec64(0);
    PanelPopup2Buttons__add_OnConfirm(0,uVar13);
    func_0x010eec64(0);
  }
  else {
    PanelPopup2Buttons__add_OnConfirm(plVar10,uVar13);
  }
  (**(code **)(*plVar10 + 0x2c0))(plVar10,*(undefined8 *)(*plVar10 + 0x2c8));
  if ((bRam00000000049f7606 & 1) == 0) {
    func_0x010c65d4(0xf91f,lVar9,0);
    bRam00000000049f7606 = 1;
  }
  if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = LoadingUI__IsExistInstance(0,0);
  if ((uVar6 & 1) == 0) {
    return;
  }
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  uVar5 = UnityEngine_GameObject__get_transform(lVar9,0);
  if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_LoadingUI_TypeInfo);
  }
  uVar1 = LoadingUI__get_layer(0,0);
  LayerUtil__ChangeLayersRecursively(0,uVar5,uVar1,0);
  if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar4 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  uVar5 = *(undefined8 *)(lVar4 + 0x30);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar13 = _mm_Method_Util_OnTransitionEnded__;
  if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) == 0) {
    uVar3 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar3,0,uVar13,0);
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
  plVar10 = (long *)System_Delegate__Remove
                              (0,uVar5,*(undefined8 *)
                                        (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388),0);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  if (plVar10 == (long *)0x0) {
code_r0x012ffbe0:
    plVar10 = (long *)0x0;
  }
  else if (*plVar10 != _class_System_Action_TypeInfo) {
    func_0x0111ef28(&uStack_68,*(undefined8 *)(*plVar10 + 0x40));
    uVar5 = func_0x010ef81c(uStack_68);
    func_0x010eebec(uVar5,0,0);
    func_0x036457e0(&uStack_68);
    goto code_r0x012ffbe0;
  }
  *(long **)(lVar4 + 0x30) = plVar10;
  if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar4 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  uVar5 = _mm_Method_Util_OnTransitionEnded__;
  uVar13 = *(undefined8 *)(lVar4 + 0x30);
  if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) == 0) {
    uVar3 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar3,0,uVar5,0);
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
  plVar10 = (long *)System_Delegate__Combine
                              (0,uVar13,*(undefined8 *)
                                         (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390),0);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  if (plVar10 != (long *)0x0) {
    if (*plVar10 == _class_System_Action_TypeInfo) goto code_r0x012ffd00;
    func_0x0111ef28(&uStack_70,*(undefined8 *)(*plVar10 + 0x40));
    uVar5 = func_0x010ef81c(uStack_70);
    func_0x010eebec(uVar5,0,0);
    func_0x036457e0(&uStack_70);
  }
  plVar10 = (long *)0x0;
code_r0x012ffd00:
  *(long **)(lVar4 + 0x30) = plVar10;
  *(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x68) = lVar9;
  return;
}


