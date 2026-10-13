// name: Multiplay.MultiplayManager$$OnMultiplayAuthKeyRecieved
// entry: 013ae2bc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnMultiplayAuthKeyRecieved(long param_1,long param_2)

{
  undefined4 uVar1;
  uint uVar2;
  undefined8 uVar3;
  long *plVar4;
  long lVar5;
  long *plVar6;
  undefined8 uVar7;
  undefined8 uVar8;
  long lVar9;
  undefined8 uStack_48;
  
  if ((bRam00000000049f7b2f & 1) == 0) {
    func_0x010c65d4(0x8a5f);
    bRam00000000049f7b2f = 1;
  }
  if (((*(byte *)(_class_HttpRequest_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_HttpRequest_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = HttpRequest__CommonCheck(0,param_2,0);
  if ((uVar2 & 1) == 0) {
    if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar5 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    CaocaoLoadIndicator__DestroyLoadingUI(lVar5,0,0);
    if ((bRam00000000049f7b32 & 1) == 0) {
      func_0x010c65d4(0x8aa0);
      bRam00000000049f7b32 = 1;
    }
    lVar5 = func_0x010fdda8(
                           _class_Multiplay_MultiplayManager_<ShowRetryConnectPopup>c__AnonStorey7_TypeInfo
                           );
    Multiplay_MultiplayManager_<ShowRetryConnectPopup>c__AnonStorey7___ctor(lVar5,0);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    *(long *)(lVar5 + 0x18) = param_1;
    if (((*(byte *)(_class_PanelPopup2Buttons_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_PanelPopup2Buttons_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar7 = PanelPopup2Buttons__CreateInstance(0,0);
    *(undefined8 *)(lVar5 + 0x10) = uVar7;
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = UnityEngine_Object__op_Equality(0,uVar7,0,0);
    if ((uVar2 & 1) != 0) {
      return;
    }
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = _mm_Method_Multiplay_MultiplayManager_<ShowRetryConnectPopup>c__AnonStorey7_<>m__0__;
    lVar9 = *(long *)(lVar5 + 0x10);
    uVar3 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar3,lVar5,uVar7,0);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    *(undefined8 *)(lVar9 + 0xe0) = uVar3;
    uVar7 = _mm_Method_Multiplay_MultiplayManager_<ShowRetryConnectPopup>c__AnonStorey7_<>m__1__;
    lVar9 = *(long *)(lVar5 + 0x10);
    uVar3 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
    System_Func<PanelPopupBase_PopupState>___ctor
              (uVar3,lVar5,uVar7,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    PanelPopup2Buttons__add_OnYes(lVar9,uVar3,0);
    uVar7 = _mm_Method_Multiplay_MultiplayManager_<ShowRetryConnectPopup>c__AnonStorey7_<>m__2__;
    lVar9 = *(long *)(lVar5 + 0x10);
    uVar3 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
    System_Func<PanelPopupBase_PopupState>___ctor
              (uVar3,lVar5,uVar7,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    PanelPopup2Buttons__add_OnCancel(lVar9,uVar3,0);
    lVar9 = *(long *)(lVar5 + 0x10);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    CaocaoUIModalPopup__set_DepthGrade(lVar9,1000,0);
    plVar6 = *(long **)(lVar5 + 0x10);
    if (plVar6 == (long *)0x0) {
      func_0x010eec64(0);
    }
                    /* WARNING: Could not recover jumptable at 0x013ae2b8. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (**(code **)(*plVar6 + 0x2c0))(plVar6,*(undefined8 *)(*plVar6 + 0x2c8));
    return;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar5 = LitJson_JsonData__get_Item(param_2,_str_packet,0);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  plVar6 = (long *)LitJson_JsonData__get_Item(lVar5,_str_authKey,0);
  if (plVar6 != (long *)0x0) {
    uVar7 = (**(code **)(*plVar6 + 0x160))(plVar6,*(undefined8 *)(*plVar6 + 0x168));
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1)
         != 0) &&
       (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo);
    }
    uVar7 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Implicit(0,uVar7,0);
    *(undefined8 *)(param_1 + 0x38) = uVar7;
    if ((bRam00000000049f7aca & 1) == 0) {
      func_0x010c65d4(0x8a21);
      bRam00000000049f7aca = 1;
    }
    Multiplay_MultiplayManager__Disconnect(param_1);
    uVar7 = _mm_Method_Multiplay_MultiplayManager_ConnectToMultiplayServer__;
    uVar8 = *(undefined8 *)(param_1 + 0x70);
    uVar3 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar3,param_1,uVar7,0);
    plVar4 = (long *)System_Delegate__Combine(0,uVar8,uVar3,0);
    plVar6 = (long *)0x0;
    if (plVar4 != (long *)0x0) {
      plVar6 = plVar4;
      if (*plVar4 != _class_System_Action_TypeInfo) {
        func_0x0111ef28(&uStack_48,*(undefined8 *)(*plVar4 + 0x40));
        uVar7 = func_0x010ef81c(uStack_48);
        func_0x010eebec(uVar7,0,0);
        func_0x036457e0(&uStack_48);
        plVar6 = (long *)0x0;
      }
    }
    *(long **)(param_1 + 0x70) = plVar6;
    lVar5 = UnityEngine_Component__get_gameObject(param_1,0);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    lVar5 = UnityEngine_GameObject__AddComponent<object>
                      (lVar5,_mm_Method_UnityEngine_GameObject_AddComponent<SocketIOComponent>__);
    *(long *)(param_1 + 0x30) = lVar5;
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__set_reconnectDelay(lVar5,3,0);
    lVar5 = *(long *)(param_1 + 0x30);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    *(undefined4 *)(lVar5 + 0x2c) = 5;
    lVar5 = *(long *)(param_1 + 0x30);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    *(undefined8 *)(lVar5 + 0x30) = 0x4014000000000000;
    lVar5 = *(long *)(param_1 + 0x30);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    *(undefined1 *)(lVar5 + 0x20) = 0;
    Multiplay_MultiplayManager__RegisterCallback(param_1);
    lVar5 = *(long *)(param_1 + 0x28);
    lVar9 = *(long *)(param_1 + 0x30);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = Multiplay_MultiplayManager_ServerInfo__get_Uri(lVar5,0);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__set_URL(lVar9,uVar7,0);
    lVar5 = *(long *)(param_1 + 0x30);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Connect(lVar5,0);
    uVar1 = UnityEngine_Application__get_internetReachability(0,0);
    *(undefined4 *)(param_1 + 0x1c) = uVar1;
    uVar1 = UnityEngine_Time__get_realtimeSinceStartup(0,0);
    *(undefined4 *)(param_1 + 0x20) = uVar1;
    return;
  }
  return;
}


