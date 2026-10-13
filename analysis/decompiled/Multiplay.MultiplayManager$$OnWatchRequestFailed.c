// name: Multiplay.MultiplayManager$$OnWatchRequestFailed
// entry: 013aaf84

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnWatchRequestFailed(long param_1,long param_2)

{
  int iVar1;
  ushort uVar2;
  undefined4 uVar3;
  uint uVar4;
  code *UNRECOVERED_JUMPTABLE_00;
  ulong uVar5;
  ulong uVar6;
  long *plVar7;
  undefined8 uVar8;
  long lVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  long lVar12;
  long *plVar13;
  long lVar14;
  undefined8 *puVar15;
  long lVar16;
  int *piVar17;
  undefined1 auVar18 [16];
  undefined1 auVar19 [12];
  undefined8 uStack_e0;
  undefined8 uStack_d8;
  
  if ((bRam00000000049f7b1b & 1) == 0) {
    func_0x010c65d4(0x8a74);
    bRam00000000049f7b1b = 1;
  }
  if (((*(byte *)(_class_SceneSingleton<MultiplayIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<MultiplayIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<MultiplayIndicator>_get_instance__);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  MultiplayIndicator__Off(lVar9,0,0);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar10 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Util_TypeInfo);
  }
  uVar4 = Util__GetData<EnumData_MultiplayWatchUnableReason>
                    (0,uVar10,_str_reason,0,
                     _mm_Method_Util_GetData<EnumData_MultiplayWatchUnableReason>__);
  iVar1 = uVar4 + 7;
  if (6 < uVar4) {
    iVar1 = 0;
  }
  switch(iVar1) {
  case 0:
    uVar10 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
    System_ArgumentOutOfRangeException___ctor(uVar10,0);
    func_0x010eebec(uVar10,0,_mm_Method_Multiplay_MultiplayManager_OnWatchRequestFailed__);
    auVar18 = func_0x0112148c();
    lVar9 = auVar18._0_8_;
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
    if (auVar18._8_8_ == 0) {
      func_0x010eec64(0);
    }
    uVar10 = SocketIO_SocketIOEvent__get_data(auVar18._8_8_,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Util_TypeInfo);
    }
    uVar4 = Util__GetData<EnumData_MultiplayWatchEndReason>
                      (0,uVar10,_str_reason,0,
                       _mm_Method_Util_GetData<EnumData_MultiplayWatchEndReason>__);
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_SceneSingleton<ControlFlow>_TypeInfo);
    }
    lVar16 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) !=
         0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0
               )) {
      func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo);
    }
    auVar19 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,1,0);
    if (lVar16 == 0) {
      func_0x010eec64(0);
    }
    *(undefined1 (*) [12])(lVar16 + 0x128) = auVar19;
    ___L(0,_str_관전이_종료되었습니다_,0);
    iVar1 = uVar4 + 0xc;
    if (7 < uVar4) {
      iVar1 = 0;
    }
    switch(iVar1) {
    case 0:
      uVar10 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
      System_ArgumentOutOfRangeException___ctor(uVar10,0);
      func_0x010eebec(uVar10,0,_mm_Method_Multiplay_MultiplayManager_OnFinishWatch__);
      auVar18 = func_0x0112148c();
      if ((bRam00000000049f7b1d & 1) == 0) {
        func_0x010c65d4(0x8a72);
        bRam00000000049f7b1d = 1;
      }
      Multiplay_MultiplayManager__SetState(auVar18._0_8_,0);
      lVar9 = ___L(0,_str_관전_종료,0);
      uVar10 = _mm_Method_Multiplay_MultiplayManager_<OnWatchFinishedPostAction>m__0__;
      uVar11 = func_0x010fdda8(_class_System_Action_TypeInfo);
      System_Action___ctor(uVar11,auVar18._0_8_,uVar10,0);
      if ((bRam00000000049ff059 & 1) == 0) {
        func_0x010c65d4(0x96f9);
        bRam00000000049ff059 = 1;
      }
      lVar16 = func_0x010fdda8(_class_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_TypeInfo);
      System_Object___ctor(lVar16,0);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      *(undefined8 *)(lVar16 + 0x10) = uVar11;
      if (((*(byte *)(_class_PrefabsLib_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_PrefabsLib_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = PrefabsLib__get_m_common_goods_popupBtn2(0,0);
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_string_TypeInfo);
      }
      uVar11 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar12 = Util__InstantiateOnUIRoot(0,uVar10,0,uVar11,0,0);
      if (lVar12 == 0) {
        func_0x010eec64(0);
      }
      plVar7 = (long *)UnityEngine_GameObject__AddComponent<object>
                                 (lVar12,
                                  _mm_Method_UnityEngine_GameObject_AddComponent<PanelPopup1BtnConfirm>__
                                 );
      if (plVar7 == (long *)0x0) {
        func_0x010eec64(0);
        CaocaoUIModalPopup__set_parent(0,0,0);
        func_0x010eec64(0);
        plVar13 = (long *)0x0;
      }
      else {
        CaocaoUIModalPopup__set_parent(plVar7,0,0);
        plVar13 = plVar7;
      }
      CaocaoUIModalPopup__set_IgnoreTappingOutSide(plVar13,1,0);
      lVar14 = func_0x01101ad4(_class_object___TypeInfo,0);
      if (plVar7 == (long *)0x0) {
        func_0x010eec64(0);
      }
      plVar7[0x1d] = lVar9;
      plVar7[0x1f] = auVar18._8_8_;
      plVar7[0x20] = lVar14;
      uVar10 = _mm_Method_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_<>m__0__;
      uVar11 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
      System_Func<PanelPopupBase_PopupState>___ctor
                (uVar11,lVar16,uVar10,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
      if (plVar7 == (long *)0x0) {
        func_0x010eec64(0);
        PanelPopup2Buttons__add_OnConfirm(0,uVar11);
        func_0x010eec64(0);
      }
      else {
        PanelPopup2Buttons__add_OnConfirm(plVar7,uVar11);
      }
      (**(code **)(*plVar7 + 0x2c0))(plVar7,*(undefined8 *)(*plVar7 + 0x2c8));
      if ((bRam00000000049f7606 & 1) == 0) {
        func_0x010c65d4(0xf91f,lVar12,0);
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
      if (lVar12 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = UnityEngine_GameObject__get_transform(lVar12,0);
      if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_LoadingUI_TypeInfo);
      }
      uVar3 = LoadingUI__get_layer(0,0);
      LayerUtil__ChangeLayersRecursively(0,uVar10,uVar3,0);
      if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = *(undefined8 *)(lVar9 + 0x30);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar11 = _mm_Method_Util_OnTransitionEnded__;
      if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) == 0) {
        uVar8 = func_0x010fdda8(_class_System_Action_TypeInfo);
        System_Action___ctor(uVar8,0,uVar11,0);
        if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) = uVar8;
      }
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      plVar7 = (long *)System_Delegate__Remove
                                 (0,uVar10,*(undefined8 *)
                                            (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388),0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      if (plVar7 == (long *)0x0) {
code_r0x012ffbe0:
        plVar7 = (long *)0x0;
      }
      else if (*plVar7 != _class_System_Action_TypeInfo) {
        func_0x0111ef28(&uStack_d8,*(undefined8 *)(*plVar7 + 0x40));
        uVar10 = func_0x010ef81c(uStack_d8);
        func_0x010eebec(uVar10,0,0);
        func_0x036457e0(&uStack_d8);
        goto code_r0x012ffbe0;
      }
      *(long **)(lVar9 + 0x30) = plVar7;
      if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = _mm_Method_Util_OnTransitionEnded__;
      uVar11 = *(undefined8 *)(lVar9 + 0x30);
      if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) == 0) {
        uVar8 = func_0x010fdda8(_class_System_Action_TypeInfo);
        System_Action___ctor(uVar8,0,uVar10,0);
        if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) = uVar8;
      }
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      plVar7 = (long *)System_Delegate__Combine
                                 (0,uVar11,*(undefined8 *)
                                            (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390),0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      if (plVar7 != (long *)0x0) {
        if (*plVar7 == _class_System_Action_TypeInfo) goto code_r0x012ffd00;
        func_0x0111ef28(&uStack_e0,*(undefined8 *)(*plVar7 + 0x40));
        uVar10 = func_0x010ef81c(uStack_e0);
        func_0x010eebec(uVar10,0,0);
        func_0x036457e0(&uStack_e0);
      }
      plVar7 = (long *)0x0;
code_r0x012ffd00:
      *(long **)(lVar9 + 0x30) = plVar7;
      *(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x68) = lVar12;
      return;
    default:
      goto code_r0x013ab524;
    case 0xc:
      goto code_r0x013ab424;
    case 0xd:
      puVar15 = (undefined8 *)&str_대전자들의_접속이_종료되었습니다_;
      break;
    case 0xe:
      puVar15 = (undefined8 *)&str_관전이_비허용으로_변경되었습니다_;
      break;
    case 0xf:
      uVar10 = *(undefined8 *)(lVar9 + 0x158);
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar4 = System_String__IsNullOrEmpty(0,uVar10,0);
      if ((uVar4 & 1) != 0) goto code_r0x013ab424;
      puVar15 = (undefined8 *)&str__0_님의_접속이_종료되었습니다_;
code_r0x013ab5a0:
      uVar10 = ___L(0,*puVar15,0);
      if (((*(byte *)(_class_Localization_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Localization_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_Localization_TypeInfo);
      }
      uVar10 = Localization__Get(0,uVar10,0);
      uVar11 = *(undefined8 *)(lVar9 + 0x158);
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_string_TypeInfo);
      }
      uVar10 = System_String__Format(0,uVar10,uVar11,0);
      goto code_r0x013ab45c;
    case 0x10:
      uVar10 = *(undefined8 *)(lVar9 + 0x158);
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar4 = System_String__IsNullOrEmpty(0,uVar10,0);
      if ((uVar4 & 1) == 0) {
        puVar15 = (undefined8 *)&str__0_님이_경쟁전을_종료하였습니다_;
        goto code_r0x013ab5a0;
      }
      goto code_r0x013ab424;
    case 0x11:
      puVar15 = (undefined8 *)&DAT_049e6658;
      break;
    case 0x12:
      puVar15 = (undefined8 *)&DAT_049e6660;
      break;
    case 0x13:
      puVar15 = (undefined8 *)&DAT_049e6668;
    }
    uVar10 = *puVar15;
code_r0x013ab450:
    uVar10 = ___L(0,uVar10,0);
code_r0x013ab45c:
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    *(undefined1 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x20) = 1;
    uVar4 = Multiplay_MultiplayManager__IsStepComplete(lVar9,4);
    if ((uVar4 & 1) == 0) {
      if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar16 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
      if (lVar16 == 0) {
        func_0x010eec64(0);
      }
      uVar4 = CaocaoLoadIndicator__get_isLoading(lVar16,0);
      if ((uVar4 & 1) == 0) {
        Multiplay_MultiplayManager__OnWatchFinishedPostAction(lVar9,uVar10);
        if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar4 = Multiplay_MultiplayManager__get_isFriendly();
        if ((uVar4 & 1) == 0) {
          if (((*(byte *)(_class_Singleton<VoiceChatController>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Singleton<VoiceChatController>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar9 = Singleton<iTweenManager>__get_instance
                            (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          if ((bRam00000000049fdc23 & 1) == 0) {
            func_0x010c65d4(0xfe9b,0);
            bRam00000000049fdc23 = 1;
          }
          uVar5 = VoiceChatController__get_IsConnected(lVar9);
          if ((uVar5 & 1) != 0) {
            if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            lVar16 = *(long *)(lVar9 + 0x40);
            *(undefined8 *)(lVar9 + 0x18) = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
            if (lVar16 == 0) {
              func_0x010eec64(0);
            }
            *(undefined4 *)(lVar16 + 0x10) = 0;
          }
          return;
        }
      }
    }
code_r0x013ab524:
    return;
  default:
    goto code_r0x013ab1a0;
  case 7:
    puVar15 = (undefined8 *)&str_비허용으로_변경되었습니다_;
    break;
  case 8:
    uVar10 = *(undefined8 *)(param_1 + 0x158);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar4 = System_String__IsNullOrEmpty(0,uVar10,0);
    if ((uVar4 & 1) == 0) {
      puVar15 = (undefined8 *)&str_관전이_종료되었습니다_;
    }
    else {
      puVar15 = (undefined8 *)&str_관전할_수_없는_상태입니다_;
    }
    break;
  case 9:
    puVar15 = (undefined8 *)&str_이미_종료된_대전입니다_;
    break;
  case 10:
    puVar15 = (undefined8 *)&str_현재_관전자_수가_최대치입니다_;
    break;
  case 0xb:
    puVar15 = (undefined8 *)&str_현재_관전_중입니다_;
    break;
  case 0xc:
    puVar15 = (undefined8 *)&str_현재_친선전_중입니다_;
    break;
  case 0xd:
    puVar15 = (undefined8 *)&str_대상이_현재_다른_국가를_관전_중입니다_;
  }
  uVar10 = ___L(0,*puVar15,0);
  uVar11 = func_0x01101ad4(_class_object___TypeInfo,0);
  PanelPopupCenterText__Show(0x40000000,0,uVar10,0,uVar11,0);
  lVar9 = *(long *)(param_1 + 0x108);
  if (lVar9 != 0) {
    if (*(long *)(lVar9 + 0x58) != 0) {
      System_Action__Invoke(*(long *)(lVar9 + 0x58),0);
    }
    UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar9 + 0x10);
    plVar7 = *(long **)(lVar9 + 0x20);
    puVar15 = *(undefined8 **)(lVar9 + 0x28);
    func_0x010da364(puVar15);
    uVar4 = func_0x01101d6c(puVar15);
    if ((uVar4 & 1) == 0) {
      if (*(char *)((long)puVar15 + 0x4e) != '\0') {
                    /* WARNING: Could not recover jumptable at 0x0109237c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
        (*UNRECOVERED_JUMPTABLE_00)(0,plVar7,puVar15);
        return;
      }
      plVar7 = (long *)0x0;
    }
    else if ((*(short *)((long)puVar15 + 0x4c) != -1) &&
            (((plVar7 == (long *)0x0 || ((*(byte *)(*plVar7 + 0x10d) & 1) == 0)) &&
             (*(long *)(lVar9 + 0x18) != 0)))) {
      uVar5 = func_0x01101d58(puVar15);
      func_0x01101dd4(puVar15);
      uVar6 = func_0x010ba65c();
      if ((uVar5 & 1) == 0) {
        uVar2 = *(ushort *)((long)puVar15 + 0x4c);
        if ((uVar6 & 1) == 0) {
          lVar9 = *plVar7 + (ulong)uVar2 * 0x10;
          UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar9 + 0x130);
          puVar15 = *(undefined8 **)(lVar9 + 0x138);
        }
        else {
          lVar9 = func_0x01101dd4(puVar15);
          lVar16 = *plVar7;
          if ((ulong)*(ushort *)(lVar16 + 0x122) != 0) {
            uVar5 = 0;
            piVar17 = (int *)(*(long *)(lVar16 + 0xb0) + 8);
            do {
              if (*(long *)(piVar17 + -2) == lVar9) {
                puVar15 = (undefined8 *)(lVar16 + (ulong)(*piVar17 + (uint)uVar2) * 0x10 + 0x130);
                goto code_r0x0109246c;
              }
              uVar5 = uVar5 + 1;
              piVar17 = piVar17 + 4;
            } while (uVar5 < *(ushort *)(lVar16 + 0x122));
          }
          puVar15 = (undefined8 *)func_0x010bc584(plVar7,lVar9,(ulong)uVar2);
code_r0x0109246c:
          UNRECOVERED_JUMPTABLE_00 = (code *)*puVar15;
          puVar15 = (undefined8 *)puVar15[1];
        }
      }
      else {
        if ((uVar6 & 1) == 0) {
          uVar10 = *(undefined8 *)
                    (*plVar7 + (ulong)*(ushort *)((long)puVar15 + 0x4c) * 0x10 + 0x138);
        }
        else {
          lVar9 = *plVar7;
          if ((ulong)*(ushort *)(lVar9 + 0x122) != 0) {
            uVar5 = 0;
            piVar17 = (int *)(*(long *)(lVar9 + 0xb0) + 8);
            do {
              if (*(long *)(piVar17 + -2) == puVar15[3]) {
                lVar9 = lVar9 + (ulong)(*piVar17 + (uint)*(ushort *)((long)puVar15 + 0x4c)) * 0x10 +
                        0x130;
                goto code_r0x0109243c;
              }
              uVar5 = uVar5 + 1;
              piVar17 = piVar17 + 4;
            } while (uVar5 < *(ushort *)(lVar9 + 0x122));
          }
          lVar9 = func_0x010bc584(plVar7);
code_r0x0109243c:
          uVar10 = *(undefined8 *)(lVar9 + 8);
        }
        puVar15 = (undefined8 *)func_0x010da294(uVar10,puVar15);
        UNRECOVERED_JUMPTABLE_00 = (code *)*puVar15;
      }
    }
                    /* WARNING: Could not recover jumptable at 0x010923a4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*UNRECOVERED_JUMPTABLE_00)(plVar7,puVar15);
    return;
  }
code_r0x013ab1a0:
  return;
code_r0x013ab424:
  uVar10 = _str_관전이_종료되었습니다_;
  goto code_r0x013ab450;
}


