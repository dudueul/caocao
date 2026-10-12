// name: HeartBeats$$OnHeartBeats
// entry: 016cb8dc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void HeartBeats__OnHeartBeats(long param_1,long param_2)

{
  undefined4 uVar1;
  uint uVar2;
  undefined8 uVar3;
  ulong uVar4;
  long lVar5;
  long *plVar6;
  long lVar7;
  long *plVar8;
  long lVar9;
  long lVar10;
  long lVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined8 uStack_70;
  undefined8 uStack_68;
  
  if ((bRam00000000049f97a8 & 1) == 0) {
    func_0x010c65d4(0x6a22);
    bRam00000000049f97a8 = 1;
  }
  if (param_2 == 0) {
    return;
  }
  uVar3 = LitJson_JsonData__get_Item(param_2,_str_success,0);
  uVar4 = LitJson_JsonData__op_Explicit(0,uVar3,0);
  if ((uVar4 & 1) != 0) {
    lVar5 = LitJson_JsonData__get_Item(param_2,_str_packet,0);
    if (lVar5 == 0) {
      return;
    }
    uVar3 = LitJson_JsonData__get_Item(lVar5,_str_serverNoticeMsg,0);
    uVar3 = LitJson_JsonData__op_Explicit(0,uVar3,0);
    uVar12 = *(undefined8 *)(param_1 + 0x28);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar2 = System_String__op_Equality(0,uVar12,uVar3,0);
    if ((uVar2 & 1) != 0) {
      return;
    }
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = System_String__IsNullOrEmpty(0,uVar3,0);
    if ((uVar2 & 1) == 0) {
      if (((*(byte *)(_class_PrefabsLib_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_PrefabsLib_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar12 = PrefabsLib__get_centerMiddlePopupText(0,0);
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_string_TypeInfo);
      }
      uVar13 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar5 = Util__InstantiateOnUIRoot(0,uVar12,0,uVar13,0,0);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      plVar6 = (long *)UnityEngine_GameObject__GetComponent<object>
                                 (lVar5,
                                  _mm_Method_UnityEngine_GameObject_GetComponent<PanelPopupCenterText>__
                                 );
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
      }
      uVar4 = UnityEngine_Object__op_Inequality(0,plVar6,0,0);
      if ((uVar4 & 1) != 0) {
        if (plVar6 == (long *)0x0) {
          func_0x010eec64(0);
          CaocaoUIModalPopup__set_DepthGrade(0,1000,0);
          func_0x010eec64(0);
          plVar8 = (long *)0x0;
        }
        else {
          CaocaoUIModalPopup__set_DepthGrade(plVar6,1000,0);
          plVar8 = plVar6;
        }
        CaocaoUIModalPopup__set_HasVisibleBg(plVar8,0,0);
        uVar12 = func_0x01101ad4(_class_object___TypeInfo,0);
        if (plVar6 == (long *)0x0) {
          func_0x010eec64(0);
          PanelPopupCenterText__UpdateUI(0x3f800000,0,uVar3,0,uVar12,0);
          func_0x010eec64(0);
        }
        else {
          PanelPopupCenterText__UpdateUI(0x3f800000,plVar6,uVar3,0,uVar12,0);
        }
        (**(code **)(*plVar6 + 0x2c0))(plVar6,*(undefined8 *)(*plVar6 + 0x2c8));
      }
    }
    *(undefined8 *)(param_1 + 0x28) = uVar3;
    return;
  }
  lVar5 = LitJson_JsonData__get_Item(param_2,_str_errorType,0);
  uVar3 = LitJson_JsonData__op_Implicit(0,_str_invalid_asset_bundle,0);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = LitJson_JsonData__Equals(lVar5,uVar3,0);
  if ((uVar4 & 1) == 0) {
    return;
  }
  lVar5 = ___L(0,_str_추가_리소스_다운로드,0);
  lVar7 = ___L(0,_str_새로운_리소스를_다운로드_하기_위해_앱을_재시작합니다_,0
              );
  uVar3 = _mm_Method_Util_Restart__;
  lVar11 = **(long **)(_class_HeartBeats_TypeInfo + 0xb8);
  if (lVar11 == 0) {
    uVar12 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar12,0,uVar3,0);
    **(undefined8 **)(_class_HeartBeats_TypeInfo + 0xb8) = uVar12;
    lVar11 = **(long **)(_class_HeartBeats_TypeInfo + 0xb8);
  }
  if ((bRam00000000049ff059 & 1) == 0) {
    func_0x010c65d4(0x96f9);
    bRam00000000049ff059 = 1;
  }
  lVar9 = func_0x010fdda8(_class_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_TypeInfo);
  System_Object___ctor(lVar9,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  *(long *)(lVar9 + 0x10) = lVar11;
  if (((*(byte *)(_class_PrefabsLib_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_PrefabsLib_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = PrefabsLib__get_m_common_goods_popupBtn2(0,0);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar12 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar11 = Util__InstantiateOnUIRoot(0,uVar3,0,uVar12,0,0);
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  plVar6 = (long *)UnityEngine_GameObject__AddComponent<object>
                             (lVar11,
                              _mm_Method_UnityEngine_GameObject_AddComponent<PanelPopup1BtnConfirm>__
                             );
  if (plVar6 == (long *)0x0) {
    func_0x010eec64(0);
    CaocaoUIModalPopup__set_parent(0,0,0);
    func_0x010eec64(0);
    plVar8 = (long *)0x0;
  }
  else {
    CaocaoUIModalPopup__set_parent(plVar6,0,0);
    plVar8 = plVar6;
  }
  CaocaoUIModalPopup__set_IgnoreTappingOutSide(plVar8,1,0);
  lVar10 = func_0x01101ad4(_class_object___TypeInfo,0);
  if (plVar6 == (long *)0x0) {
    func_0x010eec64(0);
  }
  plVar6[0x1d] = lVar5;
  plVar6[0x1f] = lVar7;
  plVar6[0x20] = lVar10;
  uVar3 = _mm_Method_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_<>m__0__;
  uVar12 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
  System_Func<PanelPopupBase_PopupState>___ctor
            (uVar12,lVar9,uVar3,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
  if (plVar6 == (long *)0x0) {
    func_0x010eec64(0);
    PanelPopup2Buttons__add_OnConfirm(0,uVar12);
    func_0x010eec64(0);
  }
  else {
    PanelPopup2Buttons__add_OnConfirm(plVar6,uVar12);
  }
  (**(code **)(*plVar6 + 0x2c0))(plVar6,*(undefined8 *)(*plVar6 + 0x2c8));
  if ((bRam00000000049f7606 & 1) == 0) {
    func_0x010c65d4(0xf91f,lVar11,0);
    bRam00000000049f7606 = 1;
  }
  if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar4 = LoadingUI__IsExistInstance(0,0);
  if ((uVar4 & 1) == 0) {
    return;
  }
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  uVar3 = UnityEngine_GameObject__get_transform(lVar11,0);
  if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_LoadingUI_TypeInfo);
  }
  uVar1 = LoadingUI__get_layer(0,0);
  LayerUtil__ChangeLayersRecursively(0,uVar3,uVar1,0);
  if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar5 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  uVar3 = *(undefined8 *)(lVar5 + 0x30);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar12 = _mm_Method_Util_OnTransitionEnded__;
  if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) == 0) {
    uVar13 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar13,0,uVar12,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) = uVar13;
  }
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  plVar6 = (long *)System_Delegate__Remove
                             (0,uVar3,*(undefined8 *)
                                       (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388),0);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  if (plVar6 == (long *)0x0) {
code_r0x012ffbe0:
    plVar6 = (long *)0x0;
  }
  else if (*plVar6 != _class_System_Action_TypeInfo) {
    func_0x0111ef28(&uStack_68,*(undefined8 *)(*plVar6 + 0x40));
    uVar3 = func_0x010ef81c(uStack_68);
    func_0x010eebec(uVar3,0,0);
    func_0x036457e0(&uStack_68);
    goto code_r0x012ffbe0;
  }
  *(long **)(lVar5 + 0x30) = plVar6;
  if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar5 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  uVar3 = _mm_Method_Util_OnTransitionEnded__;
  uVar12 = *(undefined8 *)(lVar5 + 0x30);
  if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) == 0) {
    uVar13 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar13,0,uVar3,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) = uVar13;
  }
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  plVar6 = (long *)System_Delegate__Combine
                             (0,uVar12,*(undefined8 *)
                                        (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390),0);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  if (plVar6 != (long *)0x0) {
    if (*plVar6 == _class_System_Action_TypeInfo) goto code_r0x012ffd00;
    func_0x0111ef28(&uStack_70,*(undefined8 *)(*plVar6 + 0x40));
    uVar3 = func_0x010ef81c(uStack_70);
    func_0x010eebec(uVar3,0,0);
    func_0x036457e0(&uStack_70);
  }
  plVar6 = (long *)0x0;
code_r0x012ffd00:
  *(long **)(lVar5 + 0x30) = plVar6;
  *(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x68) = lVar11;
  return;
}


