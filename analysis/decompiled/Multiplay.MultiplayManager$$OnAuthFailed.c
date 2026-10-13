// name: Multiplay.MultiplayManager$$OnAuthFailed
// entry: 013a6e84

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnAuthFailed(undefined8 param_1)

{
  long lVar1;
  undefined8 uVar2;
  long *plVar3;
  long *plVar4;
  ulong uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  
  if ((bRam00000000049f7b02 & 1) == 0) {
    func_0x010c65d4(0x8a45);
    bRam00000000049f7b02 = 1;
  }
  Multiplay_MultiplayManager__Disconnect(param_1);
  if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = LoadedSceneCheckUtil__get_isBattleMap(0,0);
  if ((uVar5 & 1) != 0) {
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar6 = 0;
    if ((bRam00000000049f7603 & 1) == 0) {
      func_0x010c65d4(0xfab4,0,0);
      bRam00000000049f7603 = 1;
    }
    lVar1 = MyUserData__get_instance(0,0);
    if (lVar1 == 0) {
      func_0x010eec64(0);
    }
    *(undefined1 *)(lVar1 + 0x268) = 0;
    if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar1 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
    if (lVar1 == 0) {
      func_0x010eec64(0);
    }
    HttpRequest__Clear(lVar1,0);
    if (((*(byte *)(_class_System_Net_Http_HTTPHelper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_System_Net_Http_HTTPHelper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    System_Net_Http_HTTPHelper__ExpireSession(0,0);
    if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar5 = Singleton<WorldMapControlFlow>__get_HasInstance
                      (0,_mm_Method_Singleton<MultiplayManager>_get_HasInstance__);
    if ((uVar5 & 1) != 0) {
      if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar1 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      uVar5 = Multiplay_MultiplayManager__get_isConnected(lVar1,0);
      if ((uVar5 & 1) != 0) {
        if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar1 = Singleton<iTweenManager>__get_instance
                          (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
        if (lVar1 == 0) {
          func_0x010eec64(0);
        }
        Multiplay_MultiplayManager__DisconnectByQuit(lVar1,0);
        lVar1 = Singleton<iTweenManager>__get_instance
                          (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
        if (lVar1 == 0) {
          func_0x010eec64(0);
        }
        Multiplay_MultiplayManager__Disconnect(lVar1,0);
      }
    }
    if (((*(byte *)(_class_PrefabsLib_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_PrefabsLib_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = PrefabsLib__get_m_common_goods_popupBtn2(0,0);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar7 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    lVar1 = _class_Util_TypeInfo;
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      lVar1 = func_0x010da55c();
    }
    lVar1 = Util__InstantiateOnUIRoot(lVar1,uVar2,0,uVar7,0);
    if (lVar1 == 0) {
      func_0x010eec64(0);
    }
    plVar3 = (long *)UnityEngine_GameObject__AddComponent<object>
                               (lVar1,
                                _mm_Method_UnityEngine_GameObject_AddComponent<PanelPopup1BtnConfirm>__
                               );
    if (plVar3 == (long *)0x0) {
      func_0x010eec64(0);
      CaocaoUIModalPopup__set_DepthGrade(0,1000,0);
      func_0x010eec64(0);
      plVar4 = (long *)0x0;
    }
    else {
      CaocaoUIModalPopup__set_DepthGrade(plVar3,1000,0);
      plVar4 = plVar3;
    }
    CaocaoUIModalPopup__set_IgnoreTappingOutSide(plVar4,1,0);
    uVar2 = ___L(0,_str_서버_접속_종료,0);
    uVar5 = System_String__IsNullOrEmpty(0,0,0);
    if ((uVar5 & 1) != 0) {
      uVar6 = ___L(0,_str_서버와의_접속이_종료되었습니다__앱을_재시작합니다_,0
                  );
    }
    uVar7 = func_0x01101ad4(_class_object___TypeInfo,0);
    if (plVar3 == (long *)0x0) {
      func_0x010eec64(0);
    }
    PanelPopup1BtnConfirm__Apply(plVar3,uVar2,uVar6,uVar7,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar6 = _mm_Method_Util_<ShowDisconnectedErrorPopup>m__6__;
    if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x380) == 0) {
      uVar2 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
      System_Func<PanelPopupBase_PopupState>___ctor
                (uVar2,0,uVar6,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x380) = uVar2;
    }
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar6 = *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x380);
    if (plVar3 == (long *)0x0) {
      func_0x010eec64(0);
      PanelPopup2Buttons__add_OnConfirm(0,uVar6,0);
      func_0x010eec64(0);
    }
    else {
      PanelPopup2Buttons__add_OnConfirm(plVar3,uVar6,0);
    }
                    /* WARNING: Could not recover jumptable at 0x012ff828. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (**(code **)(*plVar3 + 0x2c0))(plVar3,*(undefined8 *)(*plVar3 + 0x2c8));
    return;
  }
  return;
}


