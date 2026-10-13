// name: Multiplay.MultiplayManager$$OnFinishWatch
// entry: 013ab1e8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnFinishWatch(long param_1,long param_2)

{
  int iVar1;
  undefined4 uVar2;
  uint uVar3;
  undefined8 uVar4;
  ulong uVar5;
  undefined8 uVar6;
  long lVar7;
  long lVar8;
  long *plVar9;
  long *plVar10;
  long lVar11;
  undefined8 *puVar12;
  undefined8 uVar13;
  long lVar14;
  undefined1 auVar15 [16];
  undefined1 auVar16 [12];
  undefined8 uStack_b0;
  undefined8 uStack_a8;
  
  if ((bRam00000000049f7b1c & 1) == 0) {
    func_0x010c65d4(0x8a51);
    bRam00000000049f7b1c = 1;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar5 & 1) == 0) {
    return;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar6 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Util_TypeInfo);
  }
  uVar3 = Util__GetData<EnumData_MultiplayWatchEndReason>
                    (0,uVar6,_str_reason,0,
                     _mm_Method_Util_GetData<EnumData_MultiplayWatchEndReason>__);
  if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_SceneSingleton<ControlFlow>_TypeInfo);
  }
  lVar7 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) != 0)
     && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo);
  }
  auVar16 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,1,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  *(undefined1 (*) [12])(lVar7 + 0x128) = auVar16;
  ___L(0,_str_관전이_종료되었습니다_,0);
  iVar1 = uVar3 + 0xc;
  if (7 < uVar3) {
    iVar1 = 0;
  }
  switch(iVar1) {
  case 0:
    uVar6 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
    System_ArgumentOutOfRangeException___ctor(uVar6,0);
    func_0x010eebec(uVar6,0,_mm_Method_Multiplay_MultiplayManager_OnFinishWatch__);
    auVar15 = func_0x0112148c();
    if ((bRam00000000049f7b1d & 1) == 0) {
      func_0x010c65d4(0x8a72);
      bRam00000000049f7b1d = 1;
    }
    Multiplay_MultiplayManager__SetState(auVar15._0_8_,0);
    lVar7 = ___L(0,_str_관전_종료,0);
    uVar6 = _mm_Method_Multiplay_MultiplayManager_<OnWatchFinishedPostAction>m__0__;
    uVar13 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar13,auVar15._0_8_,uVar6,0);
    if ((bRam00000000049ff059 & 1) == 0) {
      func_0x010c65d4(0x96f9);
      bRam00000000049ff059 = 1;
    }
    lVar14 = func_0x010fdda8(_class_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_TypeInfo);
    System_Object___ctor(lVar14,0);
    if (lVar14 == 0) {
      func_0x010eec64(0);
    }
    *(undefined8 *)(lVar14 + 0x10) = uVar13;
    if (((*(byte *)(_class_PrefabsLib_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_PrefabsLib_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar6 = PrefabsLib__get_m_common_goods_popupBtn2(0,0);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar13 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar8 = Util__InstantiateOnUIRoot(0,uVar6,0,uVar13,0,0);
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
    plVar9[0x1d] = lVar7;
    plVar9[0x1f] = auVar15._8_8_;
    plVar9[0x20] = lVar11;
    uVar6 = _mm_Method_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_<>m__0__;
    uVar13 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
    System_Func<PanelPopupBase_PopupState>___ctor
              (uVar13,lVar14,uVar6,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
    if (plVar9 == (long *)0x0) {
      func_0x010eec64(0);
      PanelPopup2Buttons__add_OnConfirm(0,uVar13);
      func_0x010eec64(0);
    }
    else {
      PanelPopup2Buttons__add_OnConfirm(plVar9,uVar13);
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
    uVar5 = LoadingUI__IsExistInstance(0,0);
    if ((uVar5 & 1) == 0) {
      return;
    }
    if (lVar8 == 0) {
      func_0x010eec64(0);
    }
    uVar6 = UnityEngine_GameObject__get_transform(lVar8,0);
    if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_LoadingUI_TypeInfo);
    }
    uVar2 = LoadingUI__get_layer(0,0);
    LayerUtil__ChangeLayersRecursively(0,uVar6,uVar2,0);
    if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar7 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    uVar6 = *(undefined8 *)(lVar7 + 0x30);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar13 = _mm_Method_Util_OnTransitionEnded__;
    if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) == 0) {
      uVar4 = func_0x010fdda8(_class_System_Action_TypeInfo);
      System_Action___ctor(uVar4,0,uVar13,0);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) = uVar4;
    }
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    plVar9 = (long *)System_Delegate__Remove
                               (0,uVar6,*(undefined8 *)
                                         (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388),0);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    if (plVar9 == (long *)0x0) {
code_r0x012ffbe0:
      plVar9 = (long *)0x0;
    }
    else if (*plVar9 != _class_System_Action_TypeInfo) {
      func_0x0111ef28(&uStack_a8,*(undefined8 *)(*plVar9 + 0x40));
      uVar6 = func_0x010ef81c(uStack_a8);
      func_0x010eebec(uVar6,0,0);
      func_0x036457e0(&uStack_a8);
      goto code_r0x012ffbe0;
    }
    *(long **)(lVar7 + 0x30) = plVar9;
    if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar7 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    uVar6 = _mm_Method_Util_OnTransitionEnded__;
    uVar13 = *(undefined8 *)(lVar7 + 0x30);
    if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) == 0) {
      uVar4 = func_0x010fdda8(_class_System_Action_TypeInfo);
      System_Action___ctor(uVar4,0,uVar6,0);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) = uVar4;
    }
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    plVar9 = (long *)System_Delegate__Combine
                               (0,uVar13,*(undefined8 *)
                                          (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390),0);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    if (plVar9 != (long *)0x0) {
      if (*plVar9 == _class_System_Action_TypeInfo) goto code_r0x012ffd00;
      func_0x0111ef28(&uStack_b0,*(undefined8 *)(*plVar9 + 0x40));
      uVar6 = func_0x010ef81c(uStack_b0);
      func_0x010eebec(uVar6,0,0);
      func_0x036457e0(&uStack_b0);
    }
    plVar9 = (long *)0x0;
code_r0x012ffd00:
    *(long **)(lVar7 + 0x30) = plVar9;
    *(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x68) = lVar8;
    return;
  default:
    goto code_r0x013ab524;
  case 0xc:
    goto code_r0x013ab424;
  case 0xd:
    puVar12 = (undefined8 *)&str_대전자들의_접속이_종료되었습니다_;
    break;
  case 0xe:
    puVar12 = (undefined8 *)&str_관전이_비허용으로_변경되었습니다_;
    break;
  case 0xf:
    uVar6 = *(undefined8 *)(param_1 + 0x158);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar3 = System_String__IsNullOrEmpty(0,uVar6,0);
    if ((uVar3 & 1) != 0) goto code_r0x013ab424;
    puVar12 = (undefined8 *)&str__0_님의_접속이_종료되었습니다_;
code_r0x013ab5a0:
    uVar6 = ___L(0,*puVar12,0);
    if (((*(byte *)(_class_Localization_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Localization_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Localization_TypeInfo);
    }
    uVar6 = Localization__Get(0,uVar6,0);
    uVar13 = *(undefined8 *)(param_1 + 0x158);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar6 = System_String__Format(0,uVar6,uVar13,0);
    goto code_r0x013ab45c;
  case 0x10:
    uVar6 = *(undefined8 *)(param_1 + 0x158);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar3 = System_String__IsNullOrEmpty(0,uVar6,0);
    if ((uVar3 & 1) == 0) {
      puVar12 = (undefined8 *)&str__0_님이_경쟁전을_종료하였습니다_;
      goto code_r0x013ab5a0;
    }
    goto code_r0x013ab424;
  case 0x11:
    puVar12 = (undefined8 *)&DAT_049e6658;
    break;
  case 0x12:
    puVar12 = (undefined8 *)&DAT_049e6660;
    break;
  case 0x13:
    puVar12 = (undefined8 *)&DAT_049e6668;
  }
  uVar6 = *puVar12;
code_r0x013ab450:
  uVar6 = ___L(0,uVar6,0);
code_r0x013ab45c:
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  *(undefined1 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x20) = 1;
  uVar3 = Multiplay_MultiplayManager__IsStepComplete(param_1,4);
  if ((uVar3 & 1) == 0) {
    if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar7 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    uVar3 = CaocaoLoadIndicator__get_isLoading(lVar7,0);
    if ((uVar3 & 1) == 0) {
      Multiplay_MultiplayManager__OnWatchFinishedPostAction(param_1,uVar6);
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar3 = Multiplay_MultiplayManager__get_isFriendly();
      if ((uVar3 & 1) == 0) {
        if (((*(byte *)(_class_Singleton<VoiceChatController>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<VoiceChatController>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar7 = Singleton<iTweenManager>__get_instance
                          (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        if ((bRam00000000049fdc23 & 1) == 0) {
          func_0x010c65d4(0xfe9b,0);
          bRam00000000049fdc23 = 1;
        }
        uVar5 = VoiceChatController__get_IsConnected(lVar7);
        if ((uVar5 & 1) != 0) {
          if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar14 = *(long *)(lVar7 + 0x40);
          *(undefined8 *)(lVar7 + 0x18) = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
          if (lVar14 == 0) {
            func_0x010eec64(0);
          }
          *(undefined4 *)(lVar14 + 0x10) = 0;
        }
        return;
      }
    }
  }
code_r0x013ab524:
  return;
code_r0x013ab424:
  uVar6 = _str_관전이_종료되었습니다_;
  goto code_r0x013ab450;
}


