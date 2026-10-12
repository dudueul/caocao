// name: Chat.Chatting$$GetGuildSaveMessage
// entry: 01d308b4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__GetGuildSaveMessage(undefined8 param_1)

{
  byte bVar1;
  uint uVar2;
  ulong uVar3;
  long lVar4;
  undefined8 uVar5;
  long *plVar6;
  long lVar7;
  undefined8 uVar8;
  long lVar9;
  
  if ((bRam00000000049fcf53 & 1) == 0) {
    func_0x010c65d4(0x25ce);
    bRam00000000049fcf53 = 1;
  }
  if (((*(byte *)(_class_GuildManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_GuildManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = GuildManager__get_isGuildMember(0,0);
  if ((uVar3 & 1) != 0) {
    if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar4 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
    uVar8 = _mm_Method_Chat_Chatting_OnChatGuildSaveMessageRecieved__;
    uVar5 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
    HttpRequest_ResponseCallback___ctor(uVar5,param_1,uVar8,0);
    plVar6 = (long *)func_0x01101ad4(_class_object___TypeInfo,2);
    if (plVar6 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((_str_room_id != 0) &&
       (lVar7 = func_0x010fdfcc(_str_room_id,*(undefined8 *)(*plVar6 + 0x40)), lVar7 == 0)) {
      uVar8 = func_0x010efe90();
      func_0x010eebec(uVar8,0,0);
    }
    lVar7 = _str_room_id;
    if ((int)plVar6[3] == 0) {
      uVar8 = func_0x010ef850();
      func_0x010eebec(uVar8,0,0);
    }
    plVar6[4] = lVar7;
    if (((*(byte *)(_class_GuildManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_GuildManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar8 = GuildManager__get_myGuildId(0,0);
    lVar7 = Chat_Chatting__GetGuildRoomId(uVar8,uVar8);
    if ((lVar7 != 0) && (lVar9 = func_0x010fdfcc(lVar7,*(undefined8 *)(*plVar6 + 0x40)), lVar9 == 0)
       ) {
      uVar8 = func_0x010efe90();
      func_0x010eebec(uVar8,0,0);
    }
    if (*(uint *)(plVar6 + 3) < 2) {
      uVar8 = func_0x010ef850();
      func_0x010eebec(uVar8,0,0);
    }
    plVar6[5] = lVar7;
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    uVar8 = HttpRequest__Construct
                      (lVar4,_str__chat_getGuildSaveMessage,_str_POST,0,uVar5,0,0,plVar6);
    lVar4 = *(long *)(lVar4 + 0x30);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    if ((bRam0000000004a0137d & 1) == 0) {
      func_0x010c65d4(0x6b97,uVar8,0);
      bRam0000000004a0137d = 1;
    }
    uVar2 = System_Collections_Generic_Queue<string>__Contains
                      (lVar4,uVar8,
                       _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Contains__
                      );
    if ((uVar2 & 1) == 0) {
      System_Collections_Generic_Queue<MessageEventArgs>__Enqueue
                (lVar4,uVar8,
                 _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Enqueue__
                );
      bVar1 = HttpRequest_HttpRequestQueue__FindRequestNeed2ShowIndicator(lVar4);
      *(byte *)(lVar4 + 0x28) = bVar1 & 1;
    }
    return;
  }
  return;
}


