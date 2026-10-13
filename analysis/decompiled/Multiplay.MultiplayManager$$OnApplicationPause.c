// name: Multiplay.MultiplayManager$$OnApplicationPause
// entry: 013b056c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnApplicationPause(long param_1,ulong param_2)

{
  undefined4 uVar1;
  uint uVar2;
  undefined8 uVar3;
  ulong uVar4;
  long lVar5;
  long lVar6;
  undefined8 uVar7;
  long lVar8;
  long lVar9;
  long *plVar10;
  long *plVar11;
  long lVar12;
  undefined8 uVar13;
  undefined8 uStack_70;
  undefined8 uStack_68;
  
  if ((bRam00000000049f7b4e & 1) == 0) {
    func_0x010c65d4(0x8a43);
    bRam00000000049f7b4e = 1;
  }
  if (((((param_2 & 1) != 0) ||
       (uVar2 = Multiplay_MultiplayManager__get_isConnected(param_1), (uVar2 & 1) != 0)) ||
      (*(long *)(param_1 + 0x40) == 0)) || (*(long *)(param_1 + 0x48) == 0)) {
    return;
  }
  if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar4 = LoadedSceneCheckUtil__get_isBattleMap(0,0);
  if ((uVar4 & 1) == 0) {
    return;
  }
  if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar5 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = SceneDataPipe__get_isMultiplayMode(lVar5,0);
  if ((uVar4 & 1) == 0) {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = Multiplay_MultiplayManager__get_isMyActiveBattle();
  if ((uVar2 & 1) == 0) {
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = Multiplay_MultiplayManager__get_isContinuedBattle();
    if ((uVar2 & 1) == 0) {
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar4 = Multiplay_MultiplayManager__get_isWatchingBattle();
      if ((uVar4 & 1) == 0) {
        return;
      }
    }
  }
  Multiplay_MultiplayManager__DisconnectByQuit(param_1);
  Multiplay_MultiplayManager__Disconnect(param_1);
  if (((*(byte *)(_class_SceneSingleton<TimeController>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<TimeController>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar5 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<TimeController>_get_instance__);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  TimeController__Pause(lVar5,0);
  lVar5 = ___L(0,_str_접속_불안정,0);
  lVar6 = ___L(0,_str_접속_상태가_불안정_하여_앱을_다시_시작합니다_,0);
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Multiplay_MultiplayManager_TypeInfo);
  }
  uVar13 = _mm_Method_Multiplay_MultiplayManager_<OnApplicationPause>m__A__;
  if (*(long *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x38) == 0) {
    uVar7 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar7,0,uVar13,0);
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    *(undefined8 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x38) = uVar7;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Multiplay_MultiplayManager_TypeInfo);
  }
  uVar13 = *(undefined8 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x38);
  if ((bRam00000000049ff059 & 1) == 0) {
    func_0x010c65d4(0x96f9);
    bRam00000000049ff059 = 1;
  }
  lVar8 = func_0x010fdda8(_class_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_TypeInfo);
  System_Object___ctor(lVar8,0);
  if (lVar8 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar8 + 0x10) = uVar13;
  if (((*(byte *)(_class_PrefabsLib_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_PrefabsLib_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar13 = PrefabsLib__get_m_common_goods_popupBtn2(0,0);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar7 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar9 = Util__InstantiateOnUIRoot(0,uVar13,0,uVar7,0,0);
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
  plVar10[0x1d] = lVar5;
  plVar10[0x1f] = lVar6;
  plVar10[0x20] = lVar12;
  uVar13 = _mm_Method_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_<>m__0__;
  uVar7 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
  System_Func<PanelPopupBase_PopupState>___ctor
            (uVar7,lVar8,uVar13,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
  if (plVar10 == (long *)0x0) {
    func_0x010eec64(0);
    PanelPopup2Buttons__add_OnConfirm(0,uVar7);
    func_0x010eec64(0);
  }
  else {
    PanelPopup2Buttons__add_OnConfirm(plVar10,uVar7);
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
  uVar4 = LoadingUI__IsExistInstance(0,0);
  if ((uVar4 & 1) == 0) {
    return;
  }
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  uVar13 = UnityEngine_GameObject__get_transform(lVar9,0);
  if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_LoadingUI_TypeInfo);
  }
  uVar1 = LoadingUI__get_layer(0,0);
  LayerUtil__ChangeLayersRecursively(0,uVar13,uVar1,0);
  if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar5 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  uVar13 = *(undefined8 *)(lVar5 + 0x30);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar7 = _mm_Method_Util_OnTransitionEnded__;
  if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) == 0) {
    uVar3 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar3,0,uVar7,0);
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
                              (0,uVar13,*(undefined8 *)
                                         (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388),0);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  if (plVar10 == (long *)0x0) {
code_r0x012ffbe0:
    plVar10 = (long *)0x0;
  }
  else if (*plVar10 != _class_System_Action_TypeInfo) {
    func_0x0111ef28(&uStack_68,*(undefined8 *)(*plVar10 + 0x40));
    uVar13 = func_0x010ef81c(uStack_68);
    func_0x010eebec(uVar13,0,0);
    func_0x036457e0(&uStack_68);
    goto code_r0x012ffbe0;
  }
  *(long **)(lVar5 + 0x30) = plVar10;
  if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar5 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  uVar13 = _mm_Method_Util_OnTransitionEnded__;
  uVar7 = *(undefined8 *)(lVar5 + 0x30);
  if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) == 0) {
    uVar3 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar3,0,uVar13,0);
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
                              (0,uVar7,*(undefined8 *)
                                        (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390),0);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  if (plVar10 != (long *)0x0) {
    if (*plVar10 == _class_System_Action_TypeInfo) goto code_r0x012ffd00;
    func_0x0111ef28(&uStack_70,*(undefined8 *)(*plVar10 + 0x40));
    uVar13 = func_0x010ef81c(uStack_70);
    func_0x010eebec(uVar13,0,0);
    func_0x036457e0(&uStack_70);
  }
  plVar10 = (long *)0x0;
code_r0x012ffd00:
  *(long **)(lVar5 + 0x30) = plVar10;
  *(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x68) = lVar9;
  return;
}


