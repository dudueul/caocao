// name: Multiplay.MultiplayManager$$OnBattleDesynced
// entry: 013acecc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnBattleDesynced(long param_1,long param_2)

{
  undefined1 *puVar1;
  undefined4 uVar2;
  int iVar3;
  uint uVar4;
  undefined8 uVar5;
  long lVar6;
  ulong uVar7;
  long lVar8;
  undefined8 uVar9;
  long lVar10;
  long lVar11;
  long *plVar12;
  long *plVar13;
  long lVar14;
  code *pcVar15;
  undefined8 unaff_x19;
  long unaff_x20;
  undefined8 unaff_x21;
  undefined8 unaff_x22;
  undefined8 uVar16;
  undefined1 *unaff_x29;
  code *unaff_x30;
  undefined1 auVar17 [16];
  undefined1 auVar18 [12];
  undefined8 uStack_70;
  undefined8 uStack_68;
  
  if ((bRam00000000049f7b26 & 1) == 0) {
    func_0x010c65d4(0x8a46);
    bRam00000000049f7b26 = 1;
  }
  if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  ControlFlow__RemoveDesyncCheckAlarm(lVar6,0);
  if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar16 = *(undefined8 *)(lVar6 + 0x128);
    uVar2 = *(undefined4 *)(lVar6 + 0x130);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) !=
         0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0
               )) {
      func_0x010da55c();
    }
    uVar4 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar16,uVar2,0);
    if ((uVar4 & 1) != 0) {
      return;
    }
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar16 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Util_TypeInfo);
    }
    uVar7 = Util__GetData(0,uVar16,_str_noGame,1,0);
    if ((uVar7 & 1) == 0) {
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar4 = Multiplay_MultiplayManager__get_isWatchingBattle();
      if ((uVar4 & 1) != 0) {
        return;
      }
      if (param_2 == 0) {
        func_0x010eec64(0);
      }
      uVar16 = SocketIO_SocketIOEvent__get_data(param_2,0);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_Util_TypeInfo);
      }
      Util__GetData(0,uVar16,_str_count,0,0);
      if (param_2 == 0) {
        func_0x010eec64(0);
      }
      uVar16 = SocketIO_SocketIOEvent__get_data(param_2,0);
      uVar2 = Util__GetData(0,uVar16,_str_turnIdx,0,0);
      lVar8 = Multiplay_MultiplayManager__RollbackBattle(param_1,uVar2);
      puVar1 = &stack0xfffffffffffffff0;
      lVar6 = param_1;
      if ((bRam0000000004a0404a & 1) == 0) {
        lVar6 = func_0x010c65d4(0x8979,lVar8,0);
        bRam0000000004a0404a = 1;
      }
      if (lVar8 == 0) {
        unaff_x19 = func_0x010fdda8(_class_System_NullReferenceException_TypeInfo);
        System_NullReferenceException___ctor(unaff_x19,_str_routine_is_null,0);
      }
      else {
        uVar4 = UnityEngine_MonoBehaviour__IsObjectMonoBehaviour(lVar6,param_1);
        lVar6 = param_1;
        if ((uVar4 & 1) != 0) goto code_r0x02abd390;
        unaff_x19 = func_0x010fdda8(_class_System_ArgumentException_TypeInfo);
        System_ArgumentException___ctor
                  (unaff_x19,_str_Coroutines_can_only_be_stopped_on_a_MonoBehaviour,0);
      }
      func_0x010eebec(unaff_x19,0,_mm_Method_UnityEngine_MonoBehaviour_StartCoroutine__);
      unaff_x30 = UnityEngine_MonoBehaviour__StartCoroutineManaged2;
      auVar17 = func_0x0112148c();
      lVar8 = auVar17._8_8_;
      register0x00000008 = (BADSPACEBASE *)&stack0xffffffffffffffd0;
      lVar6 = auVar17._0_8_;
      unaff_x20 = param_1;
      unaff_x21 = 0x4a04000;
      unaff_x29 = puVar1;
code_r0x02abd390:
      *(undefined8 *)((long)register0x00000008 + -0x30) = unaff_x22;
      *(undefined8 *)((long)register0x00000008 + -0x28) = unaff_x21;
      *(long *)((long)register0x00000008 + -0x20) = unaff_x20;
      *(undefined8 *)((long)register0x00000008 + -0x18) = unaff_x19;
      *(undefined1 **)((long)register0x00000008 + -0x10) = unaff_x29;
      *(code **)((long)register0x00000008 + -8) = unaff_x30;
      pcVar15 = pcRam0000000004a040b0;
      if ((pcRam0000000004a040b0 == (code *)0x0) &&
         (pcVar15 = (code *)func_0x0111e49c(&UNK_039a1f22), pcVar15 == (code *)0x0)) {
        *(undefined8 *)((long)register0x00000008 + -0x38) = 0;
        uVar16 = func_0x010f000c(&UNK_039a1f22);
        func_0x010eebec(uVar16,0,0);
        pcVar15 = *(code **)((long)register0x00000008 + -0x38);
      }
      pcRam0000000004a040b0 = pcVar15;
                    /* WARNING: Could not recover jumptable at 0x02abd408. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*pcRam0000000004a040b0)(lVar6,lVar8);
      return;
    }
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) !=
         0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0
               )) {
      func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo);
    }
    auVar18 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,1,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    *(undefined1 (*) [12])(lVar6 + 0x128) = auVar18;
    if (((*(byte *)(_class_Singleton<MultiplayUserStatusPopup>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<MultiplayUserStatusPopup>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<MultiplayUserStatusPopup>_get_instance__);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    MultiplayUserStatusPopup__Off(lVar6,0);
    *(undefined8 *)(param_1 + 0x40) = 0;
    lVar6 = ___L(0,_str_전투_정보_불일치,0);
    lVar8 = ___L(0,_DAT_049e66a0,0);
    uVar16 = _mm_Method_Multiplay_MultiplayManager_<OnBattleDesynced>m__3__;
    uVar9 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar9,param_1,uVar16,0);
    if ((bRam00000000049ff059 & 1) == 0) {
      func_0x010c65d4(0x96f9);
      bRam00000000049ff059 = 1;
    }
    lVar10 = func_0x010fdda8(_class_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_TypeInfo);
    System_Object___ctor(lVar10,0);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    *(undefined8 *)(lVar10 + 0x10) = uVar9;
    if (((*(byte *)(_class_PrefabsLib_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_PrefabsLib_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar16 = PrefabsLib__get_m_common_goods_popupBtn2(0,0);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar9 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar11 = Util__InstantiateOnUIRoot(0,uVar16,0,uVar9,0,0);
    if (lVar11 == 0) {
      func_0x010eec64(0);
    }
    plVar12 = (long *)UnityEngine_GameObject__AddComponent<object>
                                (lVar11,
                                 _mm_Method_UnityEngine_GameObject_AddComponent<PanelPopup1BtnConfirm>__
                                );
    if (plVar12 == (long *)0x0) {
      func_0x010eec64(0);
      CaocaoUIModalPopup__set_parent(0,0,0);
      func_0x010eec64(0);
      plVar13 = (long *)0x0;
    }
    else {
      CaocaoUIModalPopup__set_parent(plVar12,0,0);
      plVar13 = plVar12;
    }
    CaocaoUIModalPopup__set_IgnoreTappingOutSide(plVar13,1,0);
    lVar14 = func_0x01101ad4(_class_object___TypeInfo,0);
    if (plVar12 == (long *)0x0) {
      func_0x010eec64(0);
    }
    plVar12[0x1d] = lVar6;
    plVar12[0x1f] = lVar8;
    plVar12[0x20] = lVar14;
    uVar16 = _mm_Method_PanelPopup1BtnConfirm_<ShowPopup>c__AnonStorey0_<>m__0__;
    uVar9 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
    System_Func<PanelPopupBase_PopupState>___ctor
              (uVar9,lVar10,uVar16,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
    if (plVar12 == (long *)0x0) {
      func_0x010eec64(0);
      PanelPopup2Buttons__add_OnConfirm(0,uVar9);
      func_0x010eec64(0);
    }
    else {
      PanelPopup2Buttons__add_OnConfirm(plVar12,uVar9);
    }
    (**(code **)(*plVar12 + 0x2c0))(plVar12,*(undefined8 *)(*plVar12 + 0x2c8));
    if ((bRam00000000049f7606 & 1) == 0) {
      func_0x010c65d4(0xf91f,lVar11,0);
      bRam00000000049f7606 = 1;
    }
    if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar7 = LoadingUI__IsExistInstance(0,0);
    if ((uVar7 & 1) == 0) {
      return;
    }
    if (lVar11 == 0) {
      func_0x010eec64(0);
    }
    uVar16 = UnityEngine_GameObject__get_transform(lVar11,0);
    if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_LoadingUI_TypeInfo);
    }
    uVar2 = LoadingUI__get_layer(0,0);
    LayerUtil__ChangeLayersRecursively(0,uVar16,uVar2,0);
    if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar16 = *(undefined8 *)(lVar6 + 0x30);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar9 = _mm_Method_Util_OnTransitionEnded__;
    if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) == 0) {
      uVar5 = func_0x010fdda8(_class_System_Action_TypeInfo);
      System_Action___ctor(uVar5,0,uVar9,0);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388) = uVar5;
    }
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    plVar12 = (long *)System_Delegate__Remove
                                (0,uVar16,*(undefined8 *)
                                           (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x388),0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    if (plVar12 == (long *)0x0) {
code_r0x012ffbe0:
      plVar12 = (long *)0x0;
    }
    else if (*plVar12 != _class_System_Action_TypeInfo) {
      func_0x0111ef28(&uStack_68,*(undefined8 *)(*plVar12 + 0x40));
      uVar16 = func_0x010ef81c(uStack_68);
      func_0x010eebec(uVar16,0,0);
      func_0x036457e0(&uStack_68);
      goto code_r0x012ffbe0;
    }
    *(long **)(lVar6 + 0x30) = plVar12;
    if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar16 = _mm_Method_Util_OnTransitionEnded__;
    uVar9 = *(undefined8 *)(lVar6 + 0x30);
    if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) == 0) {
      uVar5 = func_0x010fdda8(_class_System_Action_TypeInfo);
      System_Action___ctor(uVar5,0,uVar16,0);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390) = uVar5;
    }
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    plVar12 = (long *)System_Delegate__Combine
                                (0,uVar9,*(undefined8 *)
                                          (*(long *)(_class_Util_TypeInfo + 0xb8) + 0x390),0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    if (plVar12 != (long *)0x0) {
      if (*plVar12 == _class_System_Action_TypeInfo) goto code_r0x012ffd00;
      func_0x0111ef28(&uStack_70,*(undefined8 *)(*plVar12 + 0x40));
      uVar16 = func_0x010ef81c(uStack_70);
      func_0x010eebec(uVar16,0,0);
      func_0x036457e0(&uStack_70);
    }
    plVar12 = (long *)0x0;
code_r0x012ffd00:
    *(long **)(lVar6 + 0x30) = plVar12;
    *(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x68) = lVar11;
    return;
  }
  if ((bRam00000000049f7ad5 & 1) == 0) {
    func_0x010c65d4(0x8a8a);
    bRam00000000049f7ad5 = 1;
  }
  if (*(char *)(param_1 + 0x141) == '\0') {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar4 = Multiplay_MultiplayManager__get_isContinuedBattle();
  if ((uVar4 & 1) != 0) {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar4 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar4 & 1) != 0) {
    return;
  }
  plVar12 = *(long **)(param_1 + 0x40);
  if (plVar12 == (long *)0x0) {
code_r0x013a22a0:
    uVar2 = 8;
  }
  else {
    iVar3 = (**(code **)(*plVar12 + 0x170))(plVar12,*(undefined8 *)(*plVar12 + 0x178));
    if (0 < iVar3) {
      if (*(long *)(param_1 + 0x40) == 0) goto code_r0x013a22a0;
      if ((*(long *)(param_1 + 0x48) != 0) &&
         (lVar6 = BattlePlay_BattlePlayData__get_opponent(*(long *)(param_1 + 0x48),0), lVar6 != 0))
      {
        lVar6 = *(long *)(param_1 + 0x48);
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        uVar16 = BattlePlay_BattlePlayData__get_battleKey(lVar6,0);
        plVar12 = *(long **)(param_1 + 0x40);
        if (plVar12 == (long *)0x0) {
          uVar2 = 8;
        }
        else {
          uVar2 = (**(code **)(*plVar12 + 0x170))(plVar12,*(undefined8 *)(*plVar12 + 0x178));
        }
        uVar9 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
        <>__AnonType18<string,_bool,_bool,_Step>___ctor
                  (uVar9,uVar16,1,0,uVar2,
                   _mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__);
        goto code_r0x013a22d4;
      }
    }
    plVar12 = *(long **)(param_1 + 0x40);
    if (plVar12 == (long *)0x0) goto code_r0x013a22a0;
    uVar2 = (**(code **)(*plVar12 + 0x170))(plVar12,*(undefined8 *)(*plVar12 + 0x178));
  }
  uVar9 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
  <>__AnonType19<Step,_bool,_bool>___ctor
            (uVar9,uVar2,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
code_r0x013a22d4:
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar9 = LitJson_JsonMapper__ToJson(0,uVar9,0);
  uVar16 = _str_cancelMatchmaking;
  uVar7 = MyUserData__get_hasInstance(0,0);
  if ((uVar7 & 1) != 0) {
    lVar6 = MyUserData__get_instance(0,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar6 + 0x268) != '\0') {
      lVar6 = *(long *)(param_1 + 0x30);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar16,uVar9,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar16 = System_String__Format(0,_str____0____1__,uVar16,uVar9,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar9 = LitJson_JsonMapper__ToObject(0,uVar16,0);
      lVar10 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar16 = _str__;
      System_Object___ctor(lVar10,0);
      *(undefined4 *)(lVar10 + 0x14) = 2;
      *(undefined4 *)(lVar10 + 0x18) = 0;
      *(undefined8 *)(lVar10 + 0x30) = uVar9;
      *(undefined8 *)(lVar10 + 0x20) = uVar16;
      *(undefined4 *)(lVar10 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar10 + 0x10) = 4;
      lVar8 = lVar6;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar8 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar11 = *(long *)(lVar6 + 0x48);
      if (*(long *)(lVar6 + 0x50) == 0) {
        lVar8 = func_0x010eec64(0);
      }
      uVar16 = SocketIO_Encoder__Encode(lVar8,lVar10);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar11,uVar16,0);
      return;
    }
  }
  uVar7 = Multiplay_MultiplayManager__get_isConnected(param_1);
  if ((uVar7 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(param_1 + 0x70) = 0;
  *(undefined1 *)(param_1 + 0x141) = 0;
  *(undefined1 *)(param_1 + 0x142) = 0;
  uVar16 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar7 = UnityEngine_Object__op_Inequality(0,uVar16,0,0);
  if ((uVar7 & 1) != 0) {
    lVar6 = *(long *)(param_1 + 0x30);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar6,0);
    uVar16 = *(undefined8 *)(param_1 + 0x30);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    if ((bRam0000000004a04120 & 1) == 0) {
      func_0x010c65d4(0x906e,uVar16,0);
      bRam0000000004a04120 = 1;
    }
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    pcVar15 = pcRam0000000004a04118;
    if ((pcRam0000000004a04118 == (code *)0x0) &&
       (pcVar15 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar15 == (code *)0x0)) {
      uVar9 = func_0x010f000c(&UNK_039a20c7);
      func_0x010eebec(uVar9,0,0);
    }
    pcRam0000000004a04118 = pcVar15;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*pcRam0000000004a04118)(uVar16,0);
    return;
  }
  return;
}


