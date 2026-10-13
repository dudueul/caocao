// name: Multiplay.MultiplayManager$$OnWatchMultiplay
// entry: 013aa984

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnWatchMultiplay(long param_1,long param_2)

{
  int iVar1;
  ushort uVar2;
  uint uVar3;
  code *UNRECOVERED_JUMPTABLE_00;
  ulong uVar4;
  ulong uVar5;
  long *plVar6;
  undefined8 *puVar7;
  long lVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  long lVar11;
  undefined8 uVar12;
  int *piVar13;
  
  if ((bRam00000000049f7b1a & 1) == 0) {
    func_0x010c65d4(0x8a73);
    bRam00000000049f7b1a = 1;
  }
  if (((*(byte *)(_class_SceneSingleton<MultiplayIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<MultiplayIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar8 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<MultiplayIndicator>_get_instance__);
  if (lVar8 == 0) {
    func_0x010eec64(0);
  }
  MultiplayIndicator__Off(lVar8,0,0);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar9 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Util_TypeInfo);
  }
  uVar9 = Util__GetData(0,uVar9,_str_hostUid,0,0);
  *(undefined8 *)(param_1 + 0x148) = uVar9;
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar9 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar9 = Util__GetData(0,uVar9,_str_nationName,**(undefined8 **)(_class_string_TypeInfo + 0xb8),0);
  *(undefined8 *)(param_1 + 0x158) = uVar9;
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar9 = SocketIO_SocketIOEvent__get_data(param_2,0);
  uVar3 = Util__GetData<EnumData_MultiplayUserState>
                    (0,uVar9,_str_state,0,_mm_Method_Util_GetData<EnumData_MultiplayUserState>__);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar9 = SocketIO_SocketIOEvent__get_data(param_2,0);
  uVar9 = Util__GetData(0,uVar9,_str_battleKey,**(undefined8 **)(_class_string_TypeInfo + 0xb8),0);
  if (uVar3 < 8) {
    iVar1 = *(int *)(&UNK_03991a40 + (long)(int)uVar3 * 4);
    if (9 < iVar1) {
      if (iVar1 == 10) {
        lVar8 = _class_Multiplay_MultiplayManager_TypeInfo;
        if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
          lVar8 = func_0x010da55c();
        }
        Multiplay_MultiplayManager__InitMode(lVar8,0,0);
        uVar9 = ___L(0,_str_대상이_관전할_수_없는_상태입니다_,0);
        uVar10 = func_0x01101ad4(_class_object___TypeInfo,0);
        if ((bRam00000000049fbacb & 1) == 0) {
          func_0x010c65d4(0x985e,uVar9,0,uVar10,0);
          bRam00000000049fbacb = 1;
        }
        lVar8 = **(long **)(_class_Overlay_TypeInfo + 0xb8);
        if (lVar8 == 0) {
          func_0x010eec64(0);
        }
        uVar12 = Overlay__InstantiateCenterPopupText(lVar8,0);
        if (((*(byte *)(_class_NGUITools_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_NGUITools_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_NGUITools_TypeInfo);
        }
        plVar6 = (long *)NGUITools__AddMissingComponent<VipLevelUpEffect>
                                   (0,uVar12,
                                    _mm_Method_NGUITools_AddMissingComponent<PanelPopupCenterText>__
                                   );
        if (plVar6 == (long *)0x0) {
          func_0x010eec64(0);
          PanelPopupCenterText__UpdateUI(0x40400000,0,uVar9,0,uVar10);
          func_0x010eec64(0);
        }
        else {
          PanelPopupCenterText__UpdateUI(0x40400000,plVar6,uVar9,0,uVar10);
        }
        if ((bRam00000000049fe92f & 1) == 0) {
          func_0x010c65d4(0x243a,0);
          bRam00000000049fe92f = 1;
        }
        *(undefined1 *)((long)plVar6 + 0x19) = 0;
        if (((*(byte *)(_class_SceneSingleton<DepthManager>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<DepthManager>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar8 = SceneSingleton<WorldBossBattleController>__get_instance
                          (0,_mm_Method_SceneSingleton<DepthManager>_get_instance__);
        uVar9 = UnityEngine_Component__get_gameObject(plVar6,0);
        lVar11 = plVar6[4];
        if (lVar8 == 0) {
          func_0x010eec64(0);
        }
        DepthManager__PushPopup(lVar8,uVar9,lVar11,0);
        if ((bRam00000000049fe931 & 1) == 0) {
          func_0x010c65d4(0x2438);
          bRam00000000049fe931 = 1;
        }
        lVar8 = UnityEngine_Component__get_gameObject(plVar6,0);
        if (lVar8 == 0) {
          func_0x010eec64(0);
        }
        UnityEngine_GameObject__SetActive(lVar8,1,0);
        if (((*(byte *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar8 = SceneSingleton<WorldBossBattleController>__get_instance
                          (0,_mm_Method_SceneSingleton<UIComponents>_get_instance__);
        if (lVar8 == 0) {
          func_0x010eec64(0);
        }
        UIComponents__UpdateButtonStatesForClosePanel(lVar8,0);
                    /* WARNING: Could not recover jumptable at 0x02021d74. Too many branches */
                    /* WARNING: Treating indirect jump as call */
        (**(code **)(*plVar6 + 0x2b0))(plVar6,*(undefined8 *)(*plVar6 + 0x2b8));
        return;
      }
      if (iVar1 != 0xb) {
        return;
      }
      lVar8 = _class_Multiplay_MultiplayManager_TypeInfo;
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        lVar8 = func_0x010da55c();
      }
      Multiplay_MultiplayManager__set_mode(lVar8,3);
      *(undefined1 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x20) = 0;
      if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = LoadedSceneCheckUtil__get_currentScene(0,0);
      *(undefined8 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x18) = uVar10;
      if (((*(byte *)(_class_Singleton<GameManager>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<GameManager>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar8 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<GameManager>_get_instance__);
      lVar11 = CompetitionManager__get_instance(0,0);
      uVar10 = *(undefined8 *)(param_1 + 0x148);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      uVar9 = CompetitionManager__RequestBattlePlayDataForWatch(lVar11,uVar10,uVar9,0,0,0);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      UnityEngine_MonoBehaviour__StartCoroutine(lVar8,uVar9,0);
      goto code_r0x013aae04;
    }
    if (iVar1 != 9) {
      if (iVar1 != 0) {
        return;
      }
      goto code_r0x013aadc4;
    }
    lVar8 = _class_Multiplay_MultiplayManager_TypeInfo;
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      lVar8 = func_0x010da55c();
    }
    Multiplay_MultiplayManager__set_mode(lVar8,3);
    *(undefined1 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x20) = 0;
    if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar9 = LoadedSceneCheckUtil__get_currentScene(0,0);
    *(undefined8 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x18) = uVar9;
    uVar4 = LoadedSceneCheckUtil__get_isCompetitionScene(0,0);
    if ((uVar4 & 1) != 0) {
      PopupWatchWaiting__Show(0,0,0);
      goto code_r0x013aae04;
    }
  }
  else {
code_r0x013aadc4:
    param_1 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
    System_ArgumentOutOfRangeException___ctor(param_1,0);
    func_0x010eebec(param_1,0,_mm_Method_Multiplay_MultiplayManager_OnWatchMultiplay__);
    func_0x0112148c();
  }
  Multiplay_MultiplayManager__GoToCompetitionLobby(param_1);
code_r0x013aae04:
  lVar8 = *(long *)(param_1 + 0x100);
  if (lVar8 == 0) {
    return;
  }
  if (*(long *)(lVar8 + 0x58) != 0) {
    System_Action__Invoke(*(long *)(lVar8 + 0x58),0);
  }
  UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar8 + 0x10);
  plVar6 = *(long **)(lVar8 + 0x20);
  puVar7 = *(undefined8 **)(lVar8 + 0x28);
  func_0x010da364(puVar7);
  uVar3 = func_0x01101d6c(puVar7);
  if ((uVar3 & 1) == 0) {
    if (*(char *)((long)puVar7 + 0x4e) != '\0') {
                    /* WARNING: Could not recover jumptable at 0x0109237c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE_00)(0,plVar6,puVar7);
      return;
    }
    plVar6 = (long *)0x0;
  }
  else if ((*(short *)((long)puVar7 + 0x4c) != -1) &&
          (((plVar6 == (long *)0x0 || ((*(byte *)(*plVar6 + 0x10d) & 1) == 0)) &&
           (*(long *)(lVar8 + 0x18) != 0)))) {
    uVar4 = func_0x01101d58(puVar7);
    func_0x01101dd4(puVar7);
    uVar5 = func_0x010ba65c();
    if ((uVar4 & 1) == 0) {
      uVar2 = *(ushort *)((long)puVar7 + 0x4c);
      if ((uVar5 & 1) == 0) {
        lVar8 = *plVar6 + (ulong)uVar2 * 0x10;
        UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar8 + 0x130);
        puVar7 = *(undefined8 **)(lVar8 + 0x138);
      }
      else {
        lVar8 = func_0x01101dd4(puVar7);
        lVar11 = *plVar6;
        if ((ulong)*(ushort *)(lVar11 + 0x122) != 0) {
          uVar4 = 0;
          piVar13 = (int *)(*(long *)(lVar11 + 0xb0) + 8);
          do {
            if (*(long *)(piVar13 + -2) == lVar8) {
              puVar7 = (undefined8 *)(lVar11 + (ulong)(*piVar13 + (uint)uVar2) * 0x10 + 0x130);
              goto code_r0x0109246c;
            }
            uVar4 = uVar4 + 1;
            piVar13 = piVar13 + 4;
          } while (uVar4 < *(ushort *)(lVar11 + 0x122));
        }
        puVar7 = (undefined8 *)func_0x010bc584(plVar6,lVar8,(ulong)uVar2);
code_r0x0109246c:
        UNRECOVERED_JUMPTABLE_00 = (code *)*puVar7;
        puVar7 = (undefined8 *)puVar7[1];
      }
    }
    else {
      if ((uVar5 & 1) == 0) {
        uVar9 = *(undefined8 *)(*plVar6 + (ulong)*(ushort *)((long)puVar7 + 0x4c) * 0x10 + 0x138);
      }
      else {
        lVar8 = *plVar6;
        if ((ulong)*(ushort *)(lVar8 + 0x122) != 0) {
          uVar4 = 0;
          piVar13 = (int *)(*(long *)(lVar8 + 0xb0) + 8);
          do {
            if (*(long *)(piVar13 + -2) == puVar7[3]) {
              lVar8 = lVar8 + (ulong)(*piVar13 + (uint)*(ushort *)((long)puVar7 + 0x4c)) * 0x10 +
                      0x130;
              goto code_r0x0109243c;
            }
            uVar4 = uVar4 + 1;
            piVar13 = piVar13 + 4;
          } while (uVar4 < *(ushort *)(lVar8 + 0x122));
        }
        lVar8 = func_0x010bc584(plVar6);
code_r0x0109243c:
        uVar9 = *(undefined8 *)(lVar8 + 8);
      }
      puVar7 = (undefined8 *)func_0x010da294(uVar9,puVar7);
      UNRECOVERED_JUMPTABLE_00 = (code *)*puVar7;
    }
  }
                    /* WARNING: Could not recover jumptable at 0x010923a4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*UNRECOVERED_JUMPTABLE_00)(plVar6,puVar7);
  return;
}


