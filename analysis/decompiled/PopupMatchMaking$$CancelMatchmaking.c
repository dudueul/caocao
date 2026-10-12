// name: PopupMatchMaking$$CancelMatchmaking
// entry: 01711684

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

ulong PopupMatchMaking__CancelMatchmaking(void)

{
  long lVar1;
  int iVar2;
  undefined4 uVar3;
  uint uVar4;
  long lVar5;
  undefined8 uVar6;
  ulong uVar7;
  ulong uVar8;
  long lVar9;
  code *pcVar10;
  undefined8 uVar11;
  long lVar12;
  long *plVar13;
  
  if ((bRam00000000049f9a04 & 1) == 0) {
    func_0x010c65d4(0xa468);
    bRam00000000049f9a04 = 1;
  }
  uVar11 = **(undefined8 **)(_class_BackButtonManager_TypeInfo + 0xb8);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar8 = UnityEngine_Object__op_Inequality(0,uVar11,0,0);
  if ((uVar8 & 1) != 0) {
    lVar12 = **(long **)(_class_BackButtonManager_TypeInfo + 0xb8);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    UnityEngine_Behaviour__set_enabled(lVar12,1,0);
  }
  if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar12 = Singleton<iTweenManager>__get_instance
                     (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = Multiplay_MultiplayManager__get_isConnected(lVar12,0);
  if ((uVar4 & 1) == 0) {
    if ((bRam00000000049f99fb & 1) == 0) {
      func_0x010c65d4(0xa469);
      bRam00000000049f99fb = 1;
    }
    uVar11 = **(undefined8 **)(_class_PopupMatchMaking_TypeInfo + 0xb8);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar8 = UnityEngine_Object__op_Inequality(0,uVar11,0,0);
    if ((uVar8 & 1) != 0) {
      plVar13 = (long *)**(undefined8 **)(_class_PopupMatchMaking_TypeInfo + 0xb8);
      if (plVar13 == (long *)0x0) {
        func_0x010eec64(0);
      }
      uVar8 = (**(code **)(*plVar13 + 0x2e0))(plVar13,*(undefined8 *)(*plVar13 + 0x2e8));
      **(undefined8 **)(_class_PopupMatchMaking_TypeInfo + 0xb8) = 0;
    }
    return uVar8;
  }
  if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar12 = Singleton<iTweenManager>__get_instance
                     (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = Multiplay_MultiplayManager__get_isStepInvalid(lVar12,0);
  if ((uVar4 & 1) == 0) {
    if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar12 = Singleton<iTweenManager>__get_instance
                       (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    uVar8 = Multiplay_MultiplayManager__IsStep(lVar12,0,0);
    if ((uVar8 & 1) == 0) {
code_r0x01711860:
      if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar12 = Singleton<iTweenManager>__get_instance
                         (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
      if (lVar12 == 0) {
        func_0x010eec64(0);
      }
      uVar8 = Multiplay_MultiplayManager__IsStepComplete(lVar12,0,0);
      if ((uVar8 & 1) == 0) {
        if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar12 = Singleton<iTweenManager>__get_instance
                           (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
        if (lVar12 == 0) {
          func_0x010eec64(0);
        }
        uVar8 = Multiplay_MultiplayManager__IsStepGTE(lVar12,2,0);
        if ((uVar8 & 1) == 0) {
          if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar12 = Singleton<iTweenManager>__get_instance
                             (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
          if (lVar12 == 0) {
            func_0x010eec64(0);
          }
          if ((bRam00000000049f7ad4 & 1) == 0) {
            func_0x010c65d4(0x8a1c,0);
            bRam00000000049f7ad4 = 1;
          }
          lVar5 = func_0x010fdda8(
                                 _class_Multiplay_MultiplayManager_<CancelMatchmaking>c__AnonStorey4_TypeInfo
                                 );
          Multiplay_MultiplayManager_<CancelMatchmaking>c__AnonStorey4___ctor(lVar5,0);
          if (lVar5 == 0) {
            func_0x010eec64(0);
          }
          *(long *)(lVar5 + 0x18) = lVar12;
          if (((*(byte *)(_class_PanelPopup2Buttons_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_PanelPopup2Buttons_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar11 = PanelPopup2Buttons__CreateInstance(0,0);
          *(undefined8 *)(lVar5 + 0x10) = uVar11;
          if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar4 = UnityEngine_Object__op_Equality(0,uVar11,0,0);
          uVar8 = 0;
          if ((uVar4 & 1) == 0) {
            if (lVar5 == 0) {
              func_0x010eec64(0);
            }
            uVar11 = 
            _mm_Method_Multiplay_MultiplayManager_<CancelMatchmaking>c__AnonStorey4_<>m__0__;
            lVar12 = *(long *)(lVar5 + 0x10);
            uVar6 = func_0x010fdda8(_class_System_Action_TypeInfo);
            System_Action___ctor(uVar6,lVar5,uVar11,0);
            if (lVar12 == 0) {
              func_0x010eec64(0);
            }
            *(undefined8 *)(lVar12 + 0xe0) = uVar6;
            uVar11 = 
            _mm_Method_Multiplay_MultiplayManager_<CancelMatchmaking>c__AnonStorey4_<>m__1__;
            lVar12 = *(long *)(lVar5 + 0x10);
            uVar6 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
            System_Func<PanelPopupBase_PopupState>___ctor
                      (uVar6,lVar5,uVar11,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__)
            ;
            if (lVar12 == 0) {
              func_0x010eec64(0);
            }
            PanelPopup2Buttons__add_OnYes(lVar12,uVar6,0);
            plVar13 = *(long **)(lVar5 + 0x10);
            if (plVar13 == (long *)0x0) {
              func_0x010eec64(0);
            }
            (**(code **)(*plVar13 + 0x2c0))(plVar13,*(undefined8 *)(*plVar13 + 0x2c8));
            uVar8 = *(ulong *)(lVar5 + 0x10);
          }
          return uVar8;
        }
      }
      return uVar8;
    }
    if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar12 = Singleton<iTweenManager>__get_instance
                       (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    uVar4 = Multiplay_MultiplayManager__IsStepComplete(lVar12,0,0);
    if ((uVar4 & 1) != 0) goto code_r0x01711860;
  }
  if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar8 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
  if (uVar8 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = uVar8;
  if ((bRam00000000049f7ad5 & 1) == 0) {
    uVar7 = func_0x010c65d4(0x8a8a,0,0,0);
    bRam00000000049f7ad5 = 1;
  }
  if (*(char *)(uVar8 + 0x141) == '\0') {
    return uVar7;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar7 = Multiplay_MultiplayManager__get_isContinuedBattle();
  if ((uVar7 & 1) != 0) {
    return uVar7;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar7 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar7 & 1) != 0) {
    return uVar7;
  }
  plVar13 = *(long **)(uVar8 + 0x40);
  if (plVar13 == (long *)0x0) {
code_r0x013a22a0:
    uVar3 = 8;
  }
  else {
    iVar2 = (**(code **)(*plVar13 + 0x170))(plVar13,*(undefined8 *)(*plVar13 + 0x178));
    if (0 < iVar2) {
      if (*(long *)(uVar8 + 0x40) == 0) goto code_r0x013a22a0;
      if ((*(long *)(uVar8 + 0x48) != 0) &&
         (lVar12 = BattlePlay_BattlePlayData__get_opponent(*(long *)(uVar8 + 0x48),0), lVar12 != 0))
      {
        lVar12 = *(long *)(uVar8 + 0x48);
        if (lVar12 == 0) {
          func_0x010eec64(0);
        }
        uVar11 = BattlePlay_BattlePlayData__get_battleKey(lVar12,0);
        plVar13 = *(long **)(uVar8 + 0x40);
        if (plVar13 == (long *)0x0) {
          uVar3 = 8;
        }
        else {
          uVar3 = (**(code **)(*plVar13 + 0x170))(plVar13,*(undefined8 *)(*plVar13 + 0x178));
        }
        uVar6 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
        <>__AnonType18<string,_bool,_bool,_Step>___ctor
                  (uVar6,uVar11,0,0,uVar3,
                   _mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__);
        goto code_r0x013a22d4;
      }
    }
    plVar13 = *(long **)(uVar8 + 0x40);
    if (plVar13 == (long *)0x0) goto code_r0x013a22a0;
    uVar3 = (**(code **)(*plVar13 + 0x170))(plVar13,*(undefined8 *)(*plVar13 + 0x178));
  }
  uVar6 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
  <>__AnonType19<Step,_bool,_bool>___ctor
            (uVar6,uVar3,0,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
code_r0x013a22d4:
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = LitJson_JsonMapper__ToJson(0,uVar6,0);
  uVar11 = _str_cancelMatchmaking;
  uVar7 = MyUserData__get_hasInstance(0,0);
  if ((uVar7 & 1) != 0) {
    lVar12 = MyUserData__get_instance(0,0);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar12 + 0x268) != '\0') {
      lVar12 = *(long *)(uVar8 + 0x30);
      if (lVar12 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar11,uVar6,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar11 = System_String__Format(0,_str____0____1__,uVar11,uVar6,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar6 = LitJson_JsonMapper__ToObject(0,uVar11,0);
      lVar9 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar11 = _str__;
      System_Object___ctor(lVar9,0);
      *(undefined4 *)(lVar9 + 0x14) = 2;
      *(undefined4 *)(lVar9 + 0x18) = 0;
      *(undefined8 *)(lVar9 + 0x30) = uVar6;
      *(undefined8 *)(lVar9 + 0x20) = uVar11;
      *(undefined4 *)(lVar9 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar9 + 0x10) = 4;
      lVar5 = lVar12;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar5 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar12 + 0x48);
      if (*(long *)(lVar12 + 0x50) == 0) {
        lVar5 = func_0x010eec64(0);
      }
      uVar11 = SocketIO_Encoder__Encode(lVar5,lVar9);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      uVar8 = WebSocketSharp_WebSocket__Send(lVar1,uVar11,0);
      return uVar8;
    }
  }
  uVar7 = Multiplay_MultiplayManager__get_isConnected(uVar8);
  if ((uVar7 & 1) == 0) {
    return uVar7;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(uVar8 + 0x70) = 0;
  *(undefined1 *)(uVar8 + 0x141) = 0;
  *(undefined1 *)(uVar8 + 0x142) = 0;
  uVar11 = *(undefined8 *)(uVar8 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar7 = UnityEngine_Object__op_Inequality(0,uVar11,0,0);
  if ((uVar7 & 1) != 0) {
    lVar12 = *(long *)(uVar8 + 0x30);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar12,0);
    uVar11 = *(undefined8 *)(uVar8 + 0x30);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    if ((bRam0000000004a04120 & 1) == 0) {
      func_0x010c65d4(0x906e,uVar11,0);
      bRam0000000004a04120 = 1;
    }
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    pcVar10 = pcRam0000000004a04118;
    if ((pcRam0000000004a04118 == (code *)0x0) &&
       (pcVar10 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar10 == (code *)0x0)) {
      uVar6 = func_0x010f000c(&UNK_039a20c7);
      func_0x010eebec(uVar6,0,0);
    }
    pcRam0000000004a04118 = pcVar10;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    uVar8 = (*pcRam0000000004a04118)(uVar11,0);
    return uVar8;
  }
  return uVar7;
}


