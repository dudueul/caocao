// name: Chat.Chatting$$OnChatRoomListRecieved
// entry: 01d30ad8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__OnChatRoomListRecieved(long param_1,long param_2)

{
  byte bVar1;
  uint uVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  long lVar5;
  long lVar6;
  long lVar7;
  long *plVar8;
  ulong uVar9;
  undefined8 unaff_x19;
  undefined8 unaff_x20;
  undefined8 unaff_x21;
  undefined8 unaff_x22;
  undefined8 uVar10;
  undefined8 unaff_x29;
  undefined8 unaff_x30;
  
  if ((bRam00000000049fcf55 & 1) == 0) {
    func_0x010c65d4(0x25df);
    bRam00000000049fcf55 = 1;
  }
  if (((*(byte *)(_class_HttpRequest_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_HttpRequest_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = HttpRequest__CommonCheck(0,param_2,0);
  if ((uVar2 & 1) == 0) {
    uVar10 = ___L(0,
                  _str_채팅_채널_정보를_가져오는데_실패하였습니다__다시_시도해_주세요_
                  ,0);
    uVar4 = func_0x01101ad4(_class_object___TypeInfo,0);
    if ((bRam00000000049fbacb & 1) == 0) {
      func_0x010c65d4(0x985e,uVar10,0,uVar4,0);
      bRam00000000049fbacb = 1;
    }
    lVar6 = **(long **)(_class_Overlay_TypeInfo + 0xb8);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar3 = Overlay__InstantiateCenterPopupText(lVar6,0);
    if (((*(byte *)(_class_NGUITools_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_NGUITools_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_NGUITools_TypeInfo);
    }
    plVar8 = (long *)NGUITools__AddMissingComponent<VipLevelUpEffect>
                               (0,uVar3,
                                _mm_Method_NGUITools_AddMissingComponent<PanelPopupCenterText>__);
    if (plVar8 == (long *)0x0) {
      func_0x010eec64(0);
      PanelPopupCenterText__UpdateUI(0x40000000,0,uVar10,0,uVar4);
      func_0x010eec64(0);
    }
    else {
      PanelPopupCenterText__UpdateUI(0x40000000,plVar8,uVar10,0,uVar4);
    }
    if ((bRam00000000049fe92f & 1) == 0) {
      func_0x010c65d4(0x243a,0);
      bRam00000000049fe92f = 1;
    }
    *(undefined1 *)((long)plVar8 + 0x19) = 0;
    if (((*(byte *)(_class_SceneSingleton<DepthManager>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<DepthManager>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<DepthManager>_get_instance__);
    uVar10 = UnityEngine_Component__get_gameObject(plVar8,0);
    lVar7 = plVar8[4];
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    DepthManager__PushPopup(lVar6,uVar10,lVar7,0);
    if ((bRam00000000049fe931 & 1) == 0) {
      func_0x010c65d4(0x2438);
      bRam00000000049fe931 = 1;
    }
    lVar6 = UnityEngine_Component__get_gameObject(plVar8,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    UnityEngine_GameObject__SetActive(lVar6,1,0);
    if (((*(byte *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<UIComponents>_get_instance__);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    UIComponents__UpdateButtonStatesForClosePanel(lVar6,0);
                    /* WARNING: Could not recover jumptable at 0x02021d74. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (**(code **)(*plVar8 + 0x2b0))(plVar8,*(undefined8 *)(*plVar8 + 0x2b8));
    return;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar6 = LitJson_JsonData__get_Item(param_2,_str_packet,0);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  lVar6 = LitJson_JsonData__get_Item(lVar6,_str_chatRoomList,0);
  if (lVar6 != 0) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    lVar7 = LitJson_JsonData__get_Item(param_2,_str_packet,0);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    plVar8 = (long *)LitJson_JsonData__get_Item(lVar7,_str_joinRoomID,0);
    if (plVar8 != (long *)0x0) {
      lVar7 = *(long *)(param_1 + 0x48);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = *(undefined8 *)(lVar7 + 0x30);
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar9 = System_String__IsNullOrEmpty(0,uVar10,0);
      if ((uVar9 & 1) != 0) {
        lVar7 = *(long *)(param_1 + 0x48);
        uVar10 = (**(code **)(*plVar8 + 0x160))(plVar8,*(undefined8 *)(*plVar8 + 0x168));
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        *(undefined8 *)(lVar7 + 0x30) = uVar10;
      }
    }
    Chat_Chatting__ChannelListRecieved(param_1,lVar6);
    if ((bRam00000000049fcf53 & 1) == 0) {
      func_0x010c65d4(0x25ce);
      bRam00000000049fcf53 = 1;
    }
    if (((*(byte *)(_class_GuildManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_GuildManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar9 = GuildManager__get_isGuildMember(0,0);
    if ((uVar9 & 1) != 0) {
      if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar6 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
      uVar10 = _mm_Method_Chat_Chatting_OnChatGuildSaveMessageRecieved__;
      uVar4 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
      HttpRequest_ResponseCallback___ctor(uVar4,param_1,uVar10,0);
      plVar8 = (long *)func_0x01101ad4(_class_object___TypeInfo,2);
      if (plVar8 == (long *)0x0) {
        func_0x010eec64(0);
      }
      if ((_str_room_id != 0) &&
         (lVar7 = func_0x010fdfcc(_str_room_id,*(undefined8 *)(*plVar8 + 0x40)), lVar7 == 0)) {
        uVar10 = func_0x010efe90();
        func_0x010eebec(uVar10,0,0);
      }
      lVar7 = _str_room_id;
      if ((int)plVar8[3] == 0) {
        uVar10 = func_0x010ef850();
        func_0x010eebec(uVar10,0,0);
      }
      plVar8[4] = lVar7;
      if (((*(byte *)(_class_GuildManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_GuildManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = GuildManager__get_myGuildId(0,0);
      lVar7 = Chat_Chatting__GetGuildRoomId(uVar10,uVar10);
      if ((lVar7 != 0) &&
         (lVar5 = func_0x010fdfcc(lVar7,*(undefined8 *)(*plVar8 + 0x40)), lVar5 == 0)) {
        uVar10 = func_0x010efe90();
        func_0x010eebec(uVar10,0,0);
      }
      if (*(uint *)(plVar8 + 3) < 2) {
        uVar10 = func_0x010ef850();
        func_0x010eebec(uVar10,0,0);
      }
      plVar8[5] = lVar7;
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = HttpRequest__Construct
                         (lVar6,_str__chat_getGuildSaveMessage,_str_POST,0,uVar4,0,0,plVar8,
                          unaff_x22,unaff_x21,unaff_x20,unaff_x19,unaff_x29,unaff_x30);
      lVar6 = *(long *)(lVar6 + 0x30);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a0137d & 1) == 0) {
        func_0x010c65d4(0x6b97,uVar10,0);
        bRam0000000004a0137d = 1;
      }
      uVar2 = System_Collections_Generic_Queue<string>__Contains
                        (lVar6,uVar10,
                         _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Contains__
                        );
      if ((uVar2 & 1) == 0) {
        System_Collections_Generic_Queue<MessageEventArgs>__Enqueue
                  (lVar6,uVar10,
                   _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Enqueue__
                  );
        bVar1 = HttpRequest_HttpRequestQueue__FindRequestNeed2ShowIndicator(lVar6);
        *(byte *)(lVar6 + 0x28) = bVar1 & 1;
      }
      return;
    }
    return;
  }
  return;
}


