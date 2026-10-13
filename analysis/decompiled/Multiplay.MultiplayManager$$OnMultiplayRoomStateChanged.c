// name: Multiplay.MultiplayManager$$OnMultiplayRoomStateChanged
// entry: 013abadc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnMultiplayRoomStateChanged(long param_1,long param_2)

{
  ushort uVar1;
  uint uVar2;
  undefined4 uVar3;
  int iVar4;
  uint uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  long lVar9;
  ulong uVar10;
  long *plVar11;
  long lVar12;
  long lVar13;
  long *plVar14;
  long lVar15;
  code *UNRECOVERED_JUMPTABLE_00;
  ulong uVar16;
  undefined8 *puVar17;
  int *piVar18;
  long lVar19;
  undefined8 uStack_b0;
  undefined8 uStack_a8;
  
  if ((bRam00000000049f7b21 & 1) == 0) {
    func_0x010c65d4(0x8a60);
    bRam00000000049f7b21 = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Util_TypeInfo);
  }
  uVar7 = Util__GetData(0,uVar7,_str_uid,0,0);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = SocketIO_SocketIOEvent__get_data(param_2,0);
  uVar2 = Util__GetData<EnumData_MultiplayUserState>
                    (0,uVar8,_str_state,1,_mm_Method_Util_GetData<EnumData_MultiplayUserState>__);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = SocketIO_SocketIOEvent__get_data(param_2,0);
  uVar3 = Util__GetData(0,uVar8,_str_memberCnt,0,0);
  if (*(long *)(param_1 + 0x48) == 0) {
code_r0x013abeb0:
    lVar9 = *(long *)(param_1 + 0xf8);
    if (lVar9 == 0) {
      return;
    }
    if (*(long *)(lVar9 + 0x58) != 0) {
      System_Action<ulong,_EnumData_MultiplayUserState,_int>__Invoke
                (*(long *)(lVar9 + 0x58),uVar7,uVar2,uVar3,
                 _mm_Method_System_Action<ulong,_EnumData_MultiplayUserState,_int>_Invoke__);
    }
    UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar9 + 0x10);
    plVar11 = *(long **)(lVar9 + 0x20);
    puVar17 = *(undefined8 **)(lVar9 + 0x28);
    func_0x010da364(puVar17);
    uVar5 = func_0x01101d6c(puVar17);
    if ((uVar5 & 1) == 0) {
      if (*(char *)((long)puVar17 + 0x4e) != '\x03') {
                    /* WARNING: Could not recover jumptable at 0x029b7ad4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
        (*UNRECOVERED_JUMPTABLE_00)(0,plVar11,uVar7,uVar2,uVar3,puVar17);
        return;
      }
      plVar11 = (long *)0x0;
    }
    else if ((*(short *)((long)puVar17 + 0x4c) != -1) &&
            (((plVar11 == (long *)0x0 || ((*(byte *)(*plVar11 + 0x10d) & 1) == 0)) &&
             (*(long *)(lVar9 + 0x18) != 0)))) {
      uVar10 = func_0x01101d58(puVar17);
      func_0x01101dd4(puVar17);
      uVar16 = func_0x010ba65c();
      if ((uVar10 & 1) == 0) {
        uVar1 = *(ushort *)((long)puVar17 + 0x4c);
        if ((uVar16 & 1) == 0) {
          lVar9 = *plVar11 + (ulong)uVar1 * 0x10;
          UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar9 + 0x130);
          puVar17 = *(undefined8 **)(lVar9 + 0x138);
        }
        else {
          lVar9 = func_0x01101dd4(puVar17);
          lVar19 = *plVar11;
          if ((ulong)*(ushort *)(lVar19 + 0x122) != 0) {
            uVar10 = 0;
            piVar18 = (int *)(*(long *)(lVar19 + 0xb0) + 8);
            do {
              if (*(long *)(piVar18 + -2) == lVar9) {
                puVar17 = (undefined8 *)(lVar19 + (ulong)(*piVar18 + (uint)uVar1) * 0x10 + 0x130);
                goto code_r0x029b7ba4;
              }
              uVar10 = uVar10 + 1;
              piVar18 = piVar18 + 4;
            } while (uVar10 < *(ushort *)(lVar19 + 0x122));
          }
          puVar17 = (undefined8 *)func_0x010bc584(plVar11,lVar9,(ulong)uVar1);
code_r0x029b7ba4:
          UNRECOVERED_JUMPTABLE_00 = (code *)*puVar17;
          puVar17 = (undefined8 *)puVar17[1];
        }
      }
      else {
        if ((uVar16 & 1) == 0) {
          uVar8 = *(undefined8 *)
                   (*plVar11 + (ulong)*(ushort *)((long)puVar17 + 0x4c) * 0x10 + 0x138);
        }
        else {
          lVar9 = *plVar11;
          if ((ulong)*(ushort *)(lVar9 + 0x122) != 0) {
            uVar10 = 0;
            piVar18 = (int *)(*(long *)(lVar9 + 0xb0) + 8);
            do {
              if (*(long *)(piVar18 + -2) == puVar17[3]) {
                lVar9 = lVar9 + (ulong)(*piVar18 + (uint)*(ushort *)((long)puVar17 + 0x4c)) * 0x10 +
                        0x130;
                goto code_r0x029b7b68;
              }
              uVar10 = uVar10 + 1;
              piVar18 = piVar18 + 4;
            } while (uVar10 < *(ushort *)(lVar9 + 0x122));
          }
          lVar9 = func_0x010bc584(plVar11);
code_r0x029b7b68:
          uVar8 = *(undefined8 *)(lVar9 + 8);
        }
        puVar17 = (undefined8 *)func_0x010da294(uVar8,puVar17);
        UNRECOVERED_JUMPTABLE_00 = (code *)*puVar17;
      }
    }
                    /* WARNING: Could not recover jumptable at 0x029b7aa0. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*UNRECOVERED_JUMPTABLE_00)(plVar11,uVar7,uVar2,uVar3,puVar17);
    return;
  }
  lVar9 = BattlePlay_BattlePlayData__get_battleUserDic(*(long *)(param_1 + 0x48),0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  uVar10 = System_Collections_Generic_Dictionary<ulong,_UserUnit>__ContainsKey
                     (lVar9,uVar7,
                      _mm_Method_System_Collections_Generic_Dictionary<ulong,_BattleUser>_ContainsKey__
                     );
  if ((uVar10 & 1) == 0) goto code_r0x013abeb0;
  lVar9 = *(long *)(param_1 + 0x48);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  lVar9 = BattlePlay_BattlePlayData__get_battleUserDic(lVar9,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  lVar9 = System_Collections_Generic_Dictionary<ulong,_object>__get_Item
                    (lVar9,uVar7,
                     _mm_Method_System_Collections_Generic_Dictionary<ulong,_BattleUser>_get_Item__)
  ;
  lVar19 = *(long *)(param_1 + 0x48);
  if (lVar19 == 0) {
    func_0x010eec64(0);
  }
  lVar19 = BattlePlay_BattlePlayData__get_opponent(lVar19,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
    func_0x010eec64(0);
    uRam0000000000000044 = uVar2;
    func_0x010eec64(0);
  }
  else {
    *(uint *)(lVar9 + 0x44) = uVar2;
  }
  *(undefined4 *)(lVar9 + 0x48) = uVar3;
  plVar11 = *(long **)(param_1 + 0x40);
  if (((plVar11 == (long *)0x0) ||
      (iVar4 = (**(code **)(*plVar11 + 0x170))(plVar11,*(undefined8 *)(*plVar11 + 0x178)),
      lVar19 != lVar9)) || (iVar4 < 1)) goto code_r0x013abeb0;
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar10 = Multiplay_MultiplayManager__get_isMyActiveBattle();
  if ((uVar10 & 1) == 0) goto code_r0x013abeb0;
  if (uVar2 < 8) {
    iVar4 = *(int *)(&UNK_03991a60 + (long)(int)uVar2 * 4);
    if (8 < iVar4) {
      if (iVar4 == 10) {
        if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar10 = LoadedSceneCheckUtil__get_isCompetitionScene(0,0);
        if ((uVar10 & 1) != 0) {
          if (((*(byte *)(_class_Singleton<MultiplayUserStatusPopup>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Singleton<MultiplayUserStatusPopup>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar9 = Singleton<iTweenManager>__get_instance
                            (0,_mm_Method_Singleton<MultiplayUserStatusPopup>_get_instance__);
          if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c(_class_string_TypeInfo);
          }
          uVar8 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          MultiplayUserStatusPopup__On(lVar9,uVar8,0);
        }
      }
      else {
        if (iVar4 != 9) {
          return;
        }
        plVar11 = *(long **)(param_1 + 0x40);
        if (((plVar11 == (long *)0x0) ||
            (iVar4 = (**(code **)(*plVar11 + 0x170))(plVar11,*(undefined8 *)(*plVar11 + 0x178)),
            iVar4 < 4)) ||
           ((plVar11 = *(long **)(param_1 + 0x40), plVar11 != (long *)0x0 &&
            ((iVar4 = (**(code **)(*plVar11 + 0x170))(plVar11,*(undefined8 *)(*plVar11 + 0x178)),
             iVar4 == 4 &&
             (uVar5 = Multiplay_MultiplayManager__IsStepComplete(param_1,4), (uVar5 & 1) == 0))))))
        {
          Multiplay_MultiplayManager__SetBattleEndByOpponentDisconnected(param_1);
        }
      }
      goto code_r0x013abeb0;
    }
    if (iVar4 == 8) {
      if (((*(byte *)(_class_Singleton<MultiplayUserStatusPopup>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<MultiplayUserStatusPopup>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<MultiplayUserStatusPopup>_get_instance__);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      MultiplayUserStatusPopup__Off(lVar9,0);
      goto code_r0x013abeb0;
    }
    if (iVar4 != 0) {
      return;
    }
  }
  uVar7 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
  System_ArgumentOutOfRangeException___ctor(uVar7,0);
  func_0x010eebec(uVar7,0,_mm_Method_Multiplay_MultiplayManager_OnMultiplayRoomStateChanged__);
  lVar9 = func_0x0112148c();
  if ((bRam00000000049f7b33 & 1) == 0) {
    func_0x010c65d4(0x8a8c);
    bRam00000000049f7b33 = 1;
  }
  if (((*(byte *)(_class_Singleton<MultiplayUserStatusPopup>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<MultiplayUserStatusPopup>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar19 = Singleton<iTweenManager>__get_instance
                     (0,_mm_Method_Singleton<MultiplayUserStatusPopup>_get_instance__);
  if (lVar19 == 0) {
    func_0x010eec64(0);
  }
  MultiplayUserStatusPopup__Off(lVar19,0);
  *(undefined8 *)(lVar9 + 0x40) = 0;
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar10 = Multiplay_MultiplayManager__get_isMyActiveBattle();
  if ((uVar10 & 1) != 0) {
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar19 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar19 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = *(undefined8 *)(lVar19 + 0x128);
    uVar3 = *(undefined4 *)(lVar19 + 0x130);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) !=
         0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0
               )) {
      func_0x010da55c();
    }
    uVar10 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar7,uVar3,0);
    if ((uVar10 & 1) != 0) {
      if (((*(byte *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar19 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<UIComponents>_get_instance__);
      if (lVar19 == 0) {
        func_0x010eec64(0);
      }
      uVar2 = UIComponents__IsActiveInHierarchy(lVar19,_str_VictoryPanel,0);
      if ((uVar2 & 1) != 0) {
        return;
      }
    }
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar10 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar10 & 1) == 0) {
    puVar17 = (undefined8 *)&str_상대방의_연결이_끊어졌습니다_;
  }
  else {
    puVar17 = (undefined8 *)&str_전투가_종료되었습니다_;
  }
  lVar19 = ___L(0,*puVar17,0);
  lVar12 = ___L(0,_str_알림,0);
  uVar7 = _mm_Method_Multiplay_MultiplayManager_<SetBattleEndByOpponentDisconnected>m__5__;
  uVar8 = func_0x010fdda8(_class_System_Action_TypeInfo);
  System_Action___ctor(uVar8,lVar9,uVar7,0);
  if ((bRam00000000049ff059 & 1) == 0) {
    func_0x010c65d4(0x96f9);
    bRam00000000049ff059 = 1;
  }
  lVar9 = func_0x010fdda8(_class_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_TypeInfo);
  System_Object___ctor(lVar9,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar9 + 0x10) = uVar8;
  if (((*(byte *)(_class_PrefabsLib_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_PrefabsLib_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar7 = PrefabsLib__get_m_common_goods_popupBtn2(0,0);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar8 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar13 = Util__InstantiateOnUIRoot(0,uVar7,0,uVar8,0,0);
  if (lVar13 == 0) {
    func_0x010eec64(0);
  }
  plVar11 = (long *)UnityEngine_GameObject__AddComponent<object>
                              (lVar13,
                               _mm_Method_UnityEngine_GameObject_AddComponent<PanelPopup1BtnConfirm>__
                              );
  if (plVar11 == (long *)0x0) {
    func_0x010eec64(0);
    CaocaoUIModalPopup__set_parent(0,0,0);
    func_0x010eec64(0);
    plVar14 = (long *)0x0;
  }
  else {
    CaocaoUIModalPopup__set_parent(plVar11,0,0);
    plVar14 = plVar11;
  }
  CaocaoUIModalPopup__set_IgnoreTappingOutSide(plVar14,1,0);
  lVar15 = func_0x01101ad4(_class_object___TypeInfo,0);
  if (plVar11 == (long *)0x0) {
    func_0x010eec64(0);
  }
  plVar11[0x1d] = lVar12;
  plVar11[0x1f] = lVar19;
  plVar11[0x20] = lVar15;
  uVar7 = _mm_Method_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_<>m__0__;
  uVar8 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
  System_Func<PanelPopupBase_PopupState>___ctor
            (uVar8,lVar9,uVar7,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
  if (plVar11 == (long *)0x0) {
    func_0x010eec64(0);
    PanelPopup2Buttons__add_OnConfirm(0,uVar8);
    func_0x010eec64(0);
  }
  else {
    PanelPopup2Buttons__add_OnConfirm(plVar11,uVar8);
  }
  (**(code **)(*plVar11 + 0x2c0))(plVar11,*(undefined8 *)(*plVar11 + 0x2c8));
  if ((bRam00000000049f7606 & 1) == 0) {
    func_0x010c65d4(0xf91f,lVar13,0);
    bRam00000000049f7606 = 1;
  }
  if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar10 = LoadingUI__IsExistInstance(0,0);
  if ((uVar10 & 1) == 0) {
    return;
  }
  if (lVar13 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = UnityEngine_GameObject__get_transform(lVar13,0);
  if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_LoadingUI_TypeInfo);
  }
  uVar3 = LoadingUI__get_layer(0,0);
  LayerUtil__ChangeLayersRecursively(0,uVar7,uVar3,0);
  if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar9 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = *(undefined8 *)(lVar9 + 0x30);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar8 = _mm_Method_Util_OnTransitionEnded__;
  if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) == 0) {
    uVar6 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar6,0,uVar8,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) = uVar6;
  }
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  plVar11 = (long *)System_Delegate__Remove
                              (0,uVar7,*(undefined8 *)
                                        (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388),0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  if (plVar11 == (long *)0x0) {
code_r0x012ffbe0:
    plVar11 = (long *)0x0;
  }
  else if (*plVar11 != _class_System_Action_TypeInfo) {
    func_0x0111ef28(&uStack_a8,*(undefined8 *)(*plVar11 + 0x40));
    uVar7 = func_0x010ef81c(uStack_a8);
    func_0x010eebec(uVar7,0,0);
    func_0x036457e0(&uStack_a8);
    goto code_r0x012ffbe0;
  }
  *(long **)(lVar9 + 0x30) = plVar11;
  if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar9 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = _mm_Method_Util_OnTransitionEnded__;
  uVar8 = *(undefined8 *)(lVar9 + 0x30);
  if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) == 0) {
    uVar6 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar6,0,uVar7,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) = uVar6;
  }
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  plVar11 = (long *)System_Delegate__Combine
                              (0,uVar8,*(undefined8 *)
                                        (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390),0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  if (plVar11 != (long *)0x0) {
    if (*plVar11 == _class_System_Action_TypeInfo) goto code_r0x012ffd00;
    func_0x0111ef28(&uStack_b0,*(undefined8 *)(*plVar11 + 0x40));
    uVar7 = func_0x010ef81c(uStack_b0);
    func_0x010eebec(uVar7,0,0);
    func_0x036457e0(&uStack_b0);
  }
  plVar11 = (long *)0x0;
code_r0x012ffd00:
  *(long **)(lVar9 + 0x30) = plVar11;
  *(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x68) = lVar13;
  return;
}


