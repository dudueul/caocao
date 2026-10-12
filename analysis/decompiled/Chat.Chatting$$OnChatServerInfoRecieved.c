// name: Chat.Chatting$$OnChatServerInfoRecieved
// entry: 01d30ce8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__OnChatServerInfoRecieved(long param_1,long param_2)

{
  byte bVar1;
  uint uVar2;
  undefined8 uVar3;
  long *plVar4;
  undefined8 uVar5;
  long lVar6;
  undefined8 uVar7;
  undefined8 unaff_x19;
  undefined8 unaff_x20;
  undefined8 unaff_x21;
  long lVar8;
  undefined8 unaff_x22;
  undefined8 unaff_x29;
  undefined8 unaff_x30;
  
  if ((bRam00000000049fcf56 & 1) == 0) {
    func_0x010c65d4(0x25e0);
    bRam00000000049fcf56 = 1;
  }
  if (((*(byte *)(_class_HttpRequest_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_HttpRequest_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = HttpRequest__CommonCheck(0,param_2,0);
  if ((uVar2 & 1) == 0) {
    uVar7 = ___L(0,
                 _str_채팅_서버_정보를_가져오는데_실패하였습니다__다시_시도해_주세요_
                 ,0);
    uVar5 = func_0x01101ad4(_class_object___TypeInfo,0);
    if ((bRam00000000049fbacb & 1) == 0) {
      func_0x010c65d4(0x985e,uVar7,0,uVar5,0);
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
    plVar4 = (long *)NGUITools__AddMissingComponent<VipLevelUpEffect>
                               (0,uVar3,
                                _mm_Method_NGUITools_AddMissingComponent<PanelPopupCenterText>__);
    if (plVar4 == (long *)0x0) {
      func_0x010eec64(0);
      PanelPopupCenterText__UpdateUI(0x40000000,0,uVar7,0,uVar5);
      func_0x010eec64(0);
    }
    else {
      PanelPopupCenterText__UpdateUI(0x40000000,plVar4,uVar7,0,uVar5);
    }
    if ((bRam00000000049fe92f & 1) == 0) {
      func_0x010c65d4(0x243a,0);
      bRam00000000049fe92f = 1;
    }
    *(undefined1 *)((long)plVar4 + 0x19) = 0;
    if (((*(byte *)(_class_SceneSingleton<DepthManager>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<DepthManager>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<DepthManager>_get_instance__);
    uVar7 = UnityEngine_Component__get_gameObject(plVar4,0);
    lVar8 = plVar4[4];
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    DepthManager__PushPopup(lVar6,uVar7,lVar8,0);
    if ((bRam00000000049fe931 & 1) == 0) {
      func_0x010c65d4(0x2438);
      bRam00000000049fe931 = 1;
    }
    lVar6 = UnityEngine_Component__get_gameObject(plVar4,0);
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
    (**(code **)(*plVar4 + 0x2b0))(plVar4,*(undefined8 *)(*plVar4 + 0x2b8));
    return;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar6 = LitJson_JsonData__get_Item(param_2,_str_packet,0);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  lVar6 = LitJson_JsonData__get_Item(lVar6,_str_chatServerInfo,0);
  if (lVar6 != 0) {
    uVar7 = LitJson_JsonData__ToJson(lVar6,0);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_LitJson_JsonMapper_TypeInfo);
    }
    uVar7 = LitJson_JsonMapper__ToObject<UserTechLocalInfos>
                      (0,uVar7,_mm_Method_LitJson_JsonMapper_ToObject<ServerInfo>__);
    *(undefined8 *)(param_1 + 0x28) = uVar7;
    if ((bRam00000000049fcf51 & 1) == 0) {
      func_0x010c65d4(0x25c5);
      bRam00000000049fcf51 = 1;
    }
    if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar6 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
    uVar7 = _mm_Method_Chat_Chatting_OnChatAuthKeyRecieved__;
    uVar5 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
    HttpRequest_ResponseCallback___ctor(uVar5,param_1,uVar7,0);
    uVar7 = func_0x01101ad4(_class_object___TypeInfo,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = HttpRequest__Construct
                      (lVar6,_str__chat_getAuthKey,_str_POST,0,uVar5,0,0,uVar7,unaff_x22,unaff_x21,
                       unaff_x20,unaff_x19,unaff_x29,unaff_x30);
    lVar6 = *(long *)(lVar6 + 0x30);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    if ((bRam0000000004a0137d & 1) == 0) {
      func_0x010c65d4(0x6b97,uVar7,0);
      bRam0000000004a0137d = 1;
    }
    uVar2 = System_Collections_Generic_Queue<string>__Contains
                      (lVar6,uVar7,
                       _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Contains__
                      );
    if ((uVar2 & 1) == 0) {
      System_Collections_Generic_Queue<MessageEventArgs>__Enqueue
                (lVar6,uVar7,
                 _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Enqueue__
                );
      bVar1 = HttpRequest_HttpRequestQueue__FindRequestNeed2ShowIndicator(lVar6);
      *(byte *)(lVar6 + 0x28) = bVar1 & 1;
    }
    return;
  }
  return;
}


