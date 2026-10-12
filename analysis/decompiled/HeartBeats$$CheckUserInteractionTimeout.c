// name: HeartBeats$$CheckUserInteractionTimeout
// entry: 016cb270

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void HeartBeats__CheckUserInteractionTimeout(long param_1)

{
  undefined8 uVar1;
  long *plVar2;
  long *plVar3;
  ulong uVar4;
  long lVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined4 uVar8;
  float fVar9;
  
  if ((bRam00000000049f97a6 & 1) == 0) {
    func_0x010c65d4(0x6a20);
    bRam00000000049f97a6 = 1;
  }
  uVar4 = MyUserData__get_hasInstance(0,0);
  if ((uVar4 & 1) != 0) {
    lVar5 = MyUserData__get_instance(0,0);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar5 + 0x268) != '\0') {
      if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar4 = LoadedSceneCheckUtil__get_isBattleMap(0,0);
      if ((uVar4 & 1) != 0) {
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar5 = SceneSingleton<WorldBossBattleController>__get_instance
                          (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        ControlFlow__GetEntrustState(lVar5,(char *)(param_1 + 0x30),(char *)(param_1 + 0x31),0);
        if ((*(char *)(param_1 + 0x31) != '\0') || (*(char *)(param_1 + 0x30) != '\0')) {
          uVar8 = UnityEngine_Time__get_realtimeSinceStartup(0,0);
          *(undefined4 *)(param_1 + 0x20) = uVar8;
        }
      }
      fVar9 = (float)UnityEngine_Time__get_realtimeSinceStartup(0,0);
      if (*(int *)(param_1 + 0x1c) <= (int)(fVar9 - *(float *)(param_1 + 0x20))) {
        lVar5 = MyUserData__get_instance(0,0);
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        *(undefined1 *)(lVar5 + 0x268) = 0;
        if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar6 = 0;
        if ((bRam00000000049f7603 & 1) == 0) {
          func_0x010c65d4(0xfab4,0,0);
          bRam00000000049f7603 = 1;
        }
        lVar5 = MyUserData__get_instance(0,0);
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        *(undefined1 *)(lVar5 + 0x268) = 0;
        if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar5 = Singleton<iTweenManager>__get_instance
                          (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        HttpRequest__Clear(lVar5,0);
        if (((*(byte *)(_class_System_Net_Http_HTTPHelper_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_System_Net_Http_HTTPHelper_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        System_Net_Http_HTTPHelper__ExpireSession(0,0);
        if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar4 = Singleton<WorldMapControlFlow>__get_HasInstance
                          (0,_mm_Method_Singleton<MultiplayManager>_get_HasInstance__);
        if ((uVar4 & 1) != 0) {
          if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar5 = Singleton<iTweenManager>__get_instance
                            (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
          if (lVar5 == 0) {
            func_0x010eec64(0);
          }
          uVar4 = Multiplay_MultiplayManager__get_isConnected(lVar5,0);
          if ((uVar4 & 1) != 0) {
            if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            lVar5 = Singleton<iTweenManager>__get_instance
                              (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
            if (lVar5 == 0) {
              func_0x010eec64(0);
            }
            Multiplay_MultiplayManager__DisconnectByQuit(lVar5,0);
            lVar5 = Singleton<iTweenManager>__get_instance
                              (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
            if (lVar5 == 0) {
              func_0x010eec64(0);
            }
            Multiplay_MultiplayManager__Disconnect(lVar5,0);
          }
        }
        if (((*(byte *)(_class_PrefabsLib_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_PrefabsLib_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar1 = PrefabsLib__get_m_common_goods_popupBtn2(0,0);
        if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_string_TypeInfo);
        }
        uVar7 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
        lVar5 = _class_Util_TypeInfo;
        if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
          lVar5 = func_0x010da55c();
        }
        lVar5 = Util__InstantiateOnUIRoot(lVar5,uVar1,0,uVar7,0);
        if (lVar5 == 0) {
          func_0x010eec64(0);
        }
        plVar2 = (long *)UnityEngine_GameObject__AddComponent<object>
                                   (lVar5,
                                    _mm_Method_UnityEngine_GameObject_AddComponent<PanelPopup1BtnConfirm>__
                                   );
        if (plVar2 == (long *)0x0) {
          func_0x010eec64(0);
          CaocaoUIModalPopup__set_DepthGrade(0,1000,0);
          func_0x010eec64(0);
          plVar3 = (long *)0x0;
        }
        else {
          CaocaoUIModalPopup__set_DepthGrade(plVar2,1000,0);
          plVar3 = plVar2;
        }
        CaocaoUIModalPopup__set_IgnoreTappingOutSide(plVar3,1,0);
        uVar1 = ___L(0,_str_서버_접속_종료,0);
        uVar4 = System_String__IsNullOrEmpty(0,0,0);
        if ((uVar4 & 1) != 0) {
          uVar6 = ___L(0,
                       _str_서버와의_접속이_종료되었습니다__앱을_재시작합니다_
                       ,0);
        }
        uVar7 = func_0x01101ad4(_class_object___TypeInfo,0);
        if (plVar2 == (long *)0x0) {
          func_0x010eec64(0);
        }
        PanelPopup1BtnConfirm__Apply(plVar2,uVar1,uVar6,uVar7,0);
        if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar6 = _mm_Method_Util_<ShowDisconnectedErrorPopup>m__6__;
        if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x380) == 0) {
          uVar1 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
          System_Func<PanelPopupBase_PopupState>___ctor
                    (uVar1,0,uVar6,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
          if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x380) = uVar1;
        }
        if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar6 = *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x380);
        if (plVar2 == (long *)0x0) {
          func_0x010eec64(0);
          PanelPopup2Buttons__add_OnConfirm(0,uVar6,0);
          func_0x010eec64(0);
        }
        else {
          PanelPopup2Buttons__add_OnConfirm(plVar2,uVar6,0);
        }
                    /* WARNING: Could not recover jumptable at 0x012ff828. Too many branches */
                    /* WARNING: Treating indirect jump as call */
        (**(code **)(*plVar2 + 0x2c0))(plVar2,*(undefined8 *)(*plVar2 + 0x2c8));
        return;
      }
    }
  }
  return;
}


