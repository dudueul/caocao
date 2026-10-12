// name: Chat.Chatting$$OnChatAuthKeyRecieved
// entry: 01d30e78

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__OnChatAuthKeyRecieved(long param_1,long param_2)

{
  uint uVar1;
  ulong uVar2;
  undefined8 uVar3;
  long lVar4;
  long *plVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  long lVar8;
  undefined8 uVar9;
  
  if ((bRam00000000049fcf57 & 1) == 0) {
    func_0x010c65d4(0x25dc);
    bRam00000000049fcf57 = 1;
  }
  if (((*(byte *)(_class_HttpRequest_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_HttpRequest_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar1 = HttpRequest__CommonCheck(0,param_2,0);
  if ((uVar1 & 1) != 0) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    lVar4 = LitJson_JsonData__get_Item(param_2,_str_packet,0);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    plVar5 = (long *)LitJson_JsonData__get_Item(lVar4,_str_chatKey,0);
    if (plVar5 != (long *)0x0) {
      lVar4 = *(long *)(param_1 + 0x28);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      uVar6 = Chat_ServerInfo__get_Uri(lVar4);
      uVar7 = (**(code **)(*plVar5 + 0x160))(plVar5,*(undefined8 *)(*plVar5 + 0x168));
      if ((bRam00000000049fcf16 & 1) == 0) {
        func_0x010c65d4(0x25b9);
        bRam00000000049fcf16 = 1;
      }
      uVar9 = *(undefined8 *)(param_1 + 0x20);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar2 = UnityEngine_Object__op_Inequality(0,uVar9,0,0);
      if ((uVar2 & 1) != 0) {
        lVar4 = *(long *)(param_1 + 0x20);
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        SocketIO_SocketIOComponent__Close(lVar4,0);
        uVar9 = *(undefined8 *)(param_1 + 0x20);
        if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        UnityEngine_Object__DestroyImmediate(0,uVar9,0);
      }
      lVar4 = UnityEngine_Component__get_gameObject(param_1,0);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      lVar4 = UnityEngine_GameObject__AddComponent<object>
                        (lVar4,_mm_Method_UnityEngine_GameObject_AddComponent<SocketIOComponent>__);
      *(long *)(param_1 + 0x20) = lVar4;
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__set_reconnectDelay(lVar4,5,0);
      lVar4 = *(long *)(param_1 + 0x20);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      *(undefined1 *)(lVar4 + 0x20) = 0;
      uVar9 = _mm_Method_Chat_Chatting_OnConnect__;
      lVar4 = *(long *)(param_1 + 0x20);
      uVar3 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
      System_Action<tk2dUIUpDownHoverButton>___ctor
                (uVar3,param_1,uVar9,_mm_Method_System_Action<SocketIOEvent>__ctor__);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__On(lVar4,_str_connect,uVar3,0);
      uVar9 = _mm_Method_Chat_Chatting_OnDisconnect__;
      lVar4 = *(long *)(param_1 + 0x20);
      uVar3 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
      System_Action<tk2dUIUpDownHoverButton>___ctor
                (uVar3,param_1,uVar9,_mm_Method_System_Action<SocketIOEvent>__ctor__);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__On(lVar4,_str_disconnect,uVar3,0);
      uVar9 = _mm_Method_Chat_Chatting_OnRegistered__;
      lVar4 = *(long *)(param_1 + 0x20);
      uVar3 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
      System_Action<tk2dUIUpDownHoverButton>___ctor
                (uVar3,param_1,uVar9,_mm_Method_System_Action<SocketIOEvent>__ctor__);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__On(lVar4,_str_userRegistered,uVar3,0);
      uVar9 = _mm_Method_Chat_Chatting_OnBroadcastTo__;
      lVar4 = *(long *)(param_1 + 0x20);
      uVar3 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
      System_Action<tk2dUIUpDownHoverButton>___ctor
                (uVar3,param_1,uVar9,_mm_Method_System_Action<SocketIOEvent>__ctor__);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__On(lVar4,_str_broadcastTo,uVar3,0);
      uVar9 = _mm_Method_Chat_Chatting_OnNotFoundRoomId__;
      lVar4 = *(long *)(param_1 + 0x20);
      uVar3 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
      System_Action<tk2dUIUpDownHoverButton>___ctor
                (uVar3,param_1,uVar9,_mm_Method_System_Action<SocketIOEvent>__ctor__);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__On(lVar4,_str_notFoundRoomId,uVar3,0);
      uVar9 = _mm_Method_Chat_Chatting_OnBroadcastDisconnectingTo__;
      lVar4 = *(long *)(param_1 + 0x20);
      uVar3 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
      System_Action<tk2dUIUpDownHoverButton>___ctor
                (uVar3,param_1,uVar9,_mm_Method_System_Action<SocketIOEvent>__ctor__);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__On(lVar4,_str_broadcastDisconnectingTo,uVar3,0);
      uVar9 = _mm_Method_Chat_Chatting_OnError__;
      lVar4 = *(long *)(param_1 + 0x20);
      uVar3 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
      System_Action<tk2dUIUpDownHoverButton>___ctor
                (uVar3,param_1,uVar9,_mm_Method_System_Action<SocketIOEvent>__ctor__);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__On(lVar4,_str_error,uVar3,0);
      uVar9 = _mm_Method_Chat_Chatting_OnCheckBanContent__;
      lVar4 = *(long *)(param_1 + 0x20);
      uVar3 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
      System_Action<tk2dUIUpDownHoverButton>___ctor
                (uVar3,param_1,uVar9,_mm_Method_System_Action<SocketIOEvent>__ctor__);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__On(lVar4,_str_checkBanContents,uVar3,0);
      lVar4 = *(long *)(param_1 + 0x20);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__set_URL(lVar4,uVar6,0);
      lVar4 = *(long *)(param_1 + 0x20);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__Connect(lVar4,0);
      lVar4 = *(long *)(param_1 + 0x58);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_HashSet<IClippable>__Clear
                (lVar4,_mm_Method_System_Collections_Generic_HashSet<string>_Clear__);
      lVar4 = *(long *)(param_1 + 0x60);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_Dictionary<HttpListenerContext,_HttpListenerContext>__Clear
                (lVar4,
                 _mm_Method_System_Collections_Generic_Dictionary<string,_ChattingUser>_Clear__);
      lVar4 = *(long *)(param_1 + 0x78);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_List<tk2dUICamera>__Clear
                (lVar4,_mm_Method_System_Collections_Generic_List<Notification>_Clear__);
      lVar4 = *(long *)(param_1 + 0x90);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_List<int>__Clear
                (lVar4,_mm_Method_System_Collections_Generic_List<int>_Clear__);
      if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1)
           != 0) &&
         (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar6 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Implicit(0,uVar7,0);
      *(undefined8 *)(param_1 + 0x40) = uVar6;
      return;
    }
    return;
  }
  uVar6 = ___L(0,
               _str_채팅_서버_정보를_가져오는데_실패하였습니다__다시_시도해_주세요_
               ,0);
  uVar7 = func_0x01101ad4(_class_object___TypeInfo,0);
  if ((bRam00000000049fbacb & 1) == 0) {
    func_0x010c65d4(0x985e,uVar6,0,uVar7,0);
    bRam00000000049fbacb = 1;
  }
  lVar4 = **(long **)(_class_Overlay_TypeInfo + 0xb8);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  uVar9 = Overlay__InstantiateCenterPopupText(lVar4,0);
  if (((*(byte *)(_class_NGUITools_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_NGUITools_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_NGUITools_TypeInfo);
  }
  plVar5 = (long *)NGUITools__AddMissingComponent<VipLevelUpEffect>
                             (0,uVar9,
                              _mm_Method_NGUITools_AddMissingComponent<PanelPopupCenterText>__);
  if (plVar5 == (long *)0x0) {
    func_0x010eec64(0);
    PanelPopupCenterText__UpdateUI(0x40000000,0,uVar6,0,uVar7);
    func_0x010eec64(0);
  }
  else {
    PanelPopupCenterText__UpdateUI(0x40000000,plVar5,uVar6,0,uVar7);
  }
  if ((bRam00000000049fe92f & 1) == 0) {
    func_0x010c65d4(0x243a,0);
    bRam00000000049fe92f = 1;
  }
  *(undefined1 *)((long)plVar5 + 0x19) = 0;
  if (((*(byte *)(_class_SceneSingleton<DepthManager>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<DepthManager>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar4 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<DepthManager>_get_instance__);
  uVar6 = UnityEngine_Component__get_gameObject(plVar5,0);
  lVar8 = plVar5[4];
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  DepthManager__PushPopup(lVar4,uVar6,lVar8,0);
  if ((bRam00000000049fe931 & 1) == 0) {
    func_0x010c65d4(0x2438);
    bRam00000000049fe931 = 1;
  }
  lVar4 = UnityEngine_Component__get_gameObject(plVar5,0);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  UnityEngine_GameObject__SetActive(lVar4,1,0);
  if (((*(byte *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar4 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<UIComponents>_get_instance__);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  UIComponents__UpdateButtonStatesForClosePanel(lVar4,0);
                    /* WARNING: Could not recover jumptable at 0x02021d74. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)(*plVar5 + 0x2b0))(plVar5,*(undefined8 *)(*plVar5 + 0x2b8));
  return;
}


