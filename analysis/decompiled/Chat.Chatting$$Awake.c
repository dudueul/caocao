// name: Chat.Chatting$$Awake
// entry: 01d299c0

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__Awake(long param_1)

{
  bool bVar1;
  byte bVar2;
  undefined4 uVar3;
  uint uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  ulong uVar7;
  undefined8 uVar8;
  long *plVar9;
  long lVar10;
  long *plVar11;
  long lVar12;
  undefined8 unaff_x25;
  
  if ((bRam00000000049fcf14 & 1) == 0) {
    func_0x010c65d4(0x25af);
    bRam00000000049fcf14 = 1;
  }
  Singleton<iTweenManager>__Awake(param_1,_mm_Method_Singleton<Chatting>_Awake__);
  uVar5 = func_0x010fdda8(_class_Chat_ChattingUser_TypeInfo);
  Chat_ChattingUser___ctor();
  *(undefined8 *)(param_1 + 0x48) = uVar5;
  uVar5 = func_0x010fdda8(_class_System_Collections_Generic_List<ChatBlockUser>_TypeInfo);
  System_Collections_Generic_List<tk2dUILayoutItem>___ctor
            (uVar5,_mm_Method_System_Collections_Generic_List<ChatBlockUser>__ctor__);
  *(undefined8 *)(param_1 + 0x50) = uVar5;
  uVar5 = func_0x010fdda8(_class_System_Collections_Generic_HashSet<string>_TypeInfo);
  System_Collections_Generic_HashSet<UserUnit>___ctor
            (uVar5,_mm_Method_System_Collections_Generic_HashSet<string>__ctor__);
  *(undefined8 *)(param_1 + 0x58) = uVar5;
  if (((*(byte *)(_class_System_StringComparer_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_System_StringComparer_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = System_StringComparer__get_Ordinal(0,0);
  uVar6 = func_0x010fdda8(
                         _class_System_Collections_Generic_Dictionary<string,_ChattingUser>_TypeInfo
                         );
  System_Collections_Generic_Dictionary<string,_HttpHeaderInfo>___ctor
            (uVar6,uVar5,
             _mm_Method_System_Collections_Generic_Dictionary<string,_ChattingUser>__ctor__);
  *(undefined8 *)(param_1 + 0x60) = uVar6;
  uVar5 = System_StringComparer__get_Ordinal(0,0);
  uVar6 = func_0x010fdda8(
                         _class_System_Collections_Generic_Dictionary<string,_HashSet<string>>_TypeInfo
                         );
  System_Collections_Generic_Dictionary<string,_HttpHeaderInfo>___ctor
            (uVar6,uVar5,
             _mm_Method_System_Collections_Generic_Dictionary<string,_HashSet<string>>__ctor__);
  *(undefined8 *)(param_1 + 0x68) = uVar6;
  uVar5 = func_0x010fdda8(_class_System_Collections_Generic_List<Notification>_TypeInfo);
  System_Collections_Generic_List<tk2dUILayoutItem>___ctor
            (uVar5,_mm_Method_System_Collections_Generic_List<Notification>__ctor__);
  *(undefined8 *)(param_1 + 0x78) = uVar5;
  uVar5 = func_0x010fdda8(
                         _class_System_Collections_Generic_Dictionary<string,_List<EventArgs>>_TypeInfo
                         );
  System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>___ctor
            (uVar5,_mm_Method_System_Collections_Generic_Dictionary<string,_List<EventArgs>>__ctor__
            );
  *(undefined8 *)(param_1 + 0x70) = uVar5;
  uVar5 = func_0x010fdda8(_class_System_Collections_Generic_List<double>_TypeInfo);
  System_Collections_Generic_List<double>___ctor
            (uVar5,_mm_Method_System_Collections_Generic_List<double>__ctor__);
  lVar12 = *(long *)(param_1 + 0x48);
  *(undefined8 *)(param_1 + 0x88) = uVar5;
  uVar3 = Chat_Chatting__get_GetChatRoomLocale();
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  *(undefined4 *)(lVar12 + 0x38) = uVar3;
  if (((*(byte *)(_class_Singleton<UserOptions>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<UserOptions>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar12 = Singleton<iTweenManager>__get_instance
                     (0,_mm_Method_Singleton<UserOptions>_get_instance__);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = UserOptions__get_chatEnabled(lVar12,0);
  if ((uVar7 & 1) == 0) {
    return;
  }
  if (((*(byte *)(_class_Singleton<CentralTimer>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CentralTimer>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar12 = Singleton<iTweenManager>__get_instance
                     (0,_mm_Method_Singleton<CentralTimer>_get_instance__);
  uVar6 = UnityEngine_Component__get_gameObject(param_1,0);
  uVar5 = _mm_Method_Chat_Chatting_SendGetChatAuthKey__;
  uVar8 = func_0x010fdda8(_class_System_Action_TypeInfo);
  System_Action___ctor(uVar8,param_1,uVar5,0);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  CentralTimer__AddTimer(0x45610000,lVar12,uVar6,uVar8,0xffffffff);
  uVar7 = Chat_Chatting__get_IsConnected(param_1);
  if ((uVar7 & 1) == 0) {
    if ((bRam00000000049fcf50 & 1) == 0) {
      func_0x010c65d4(0x25d1);
      bRam00000000049fcf50 = 1;
    }
    Chat_Chatting__RemoveGuildInfoChangeEventHandler(param_1);
    Chat_Chatting__AddGuildInfoChangeEventHandler(param_1);
    if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar12 = Singleton<iTweenManager>__get_instance
                       (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
    uVar5 = _mm_Method_Chat_Chatting_OnChatServerInfoRecieved__;
    uVar6 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
    HttpRequest_ResponseCallback___ctor(uVar6,param_1,uVar5,0);
    uVar5 = func_0x01101ad4(_class_object___TypeInfo,0);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    uVar5 = HttpRequest__Construct(lVar12,_str__chat_getServerInfo,_str_POST,0,uVar6,0,0,uVar5);
    lVar12 = *(long *)(lVar12 + 0x30);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    if ((bRam0000000004a0137d & 1) == 0) {
      func_0x010c65d4(0x6b97,uVar5,0);
      bRam0000000004a0137d = 1;
    }
    uVar4 = System_Collections_Generic_Queue<string>__Contains
                      (lVar12,uVar5,
                       _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Contains__
                      );
    if ((uVar4 & 1) == 0) {
      System_Collections_Generic_Queue<MessageEventArgs>__Enqueue
                (lVar12,uVar5,
                 _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Enqueue__
                );
      bVar2 = HttpRequest_HttpRequestQueue__FindRequestNeed2ShowIndicator(lVar12);
      *(byte *)(lVar12 + 0x28) = bVar2 & 1;
    }
    return;
  }
  Chat_Chatting__RemoveGuildInfoChangeEventHandler(param_1);
  if ((bRam00000000049fcf5a & 1) == 0) {
    func_0x010c65d4(0x25ae);
    bRam00000000049fcf5a = 1;
  }
  if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar12 = Singleton<iTweenManager>__get_instance
                     (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
  uVar5 = _mm_Method_Chat_Chatting_OnGuildJoin__;
  uVar6 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
  HttpRequest_ResponseCallback___ctor(uVar6,param_1,uVar5,0);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  HttpRequest__AddResponseHandler(lVar12,_str__guild_apply,uVar6,0,0);
  lVar12 = Singleton<iTweenManager>__get_instance
                     (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
  uVar5 = _mm_Method_Chat_Chatting_OnGuildJoin__;
  uVar6 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
  HttpRequest_ResponseCallback___ctor(uVar6,param_1,uVar5,0);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  HttpRequest__AddResponseHandler(lVar12,_str__guild_create,uVar6,0,0);
  lVar12 = Singleton<iTweenManager>__get_instance
                     (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
  uVar5 = _mm_Method_Chat_Chatting_OnGuildGet__;
  uVar6 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
  HttpRequest_ResponseCallback___ctor(uVar6,param_1,uVar5,0);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  HttpRequest__AddResponseHandler(lVar12,_str__guild_get,uVar6,0,0);
  lVar12 = Singleton<iTweenManager>__get_instance
                     (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
  uVar5 = _mm_Method_Chat_Chatting_OnGuildLeave__;
  uVar6 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
  HttpRequest_ResponseCallback___ctor(uVar6,param_1,uVar5,0);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  HttpRequest__AddResponseHandler(lVar12,_str__guild_leave,uVar6,0,0);
  lVar12 = Singleton<iTweenManager>__get_instance
                     (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
  uVar5 = _mm_Method_Chat_Chatting_OnGuildLeave__;
  uVar6 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
  HttpRequest_ResponseCallback___ctor(uVar6,param_1,uVar5,0);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  HttpRequest__AddResponseHandler(lVar12,_str__guild_break,uVar6,0,0);
  uVar5 = _mm_Method_Chat_Chatting_OnGuildError__;
  uVar6 = func_0x010fdda8(_class_GuildManager_GuildErrorEvent_TypeInfo);
  GuildManager_GuildErrorEvent___ctor(uVar6,param_1,uVar5,0);
  if (((*(byte *)(_class_GuildManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_GuildManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if ((bRam00000000049fed94 & 1) == 0) {
    func_0x010c65d4(0x6170,uVar6,0);
    bRam00000000049fed94 = 1;
  }
  if (((*(byte *)(_class_GuildManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_GuildManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar12 = **(long **)(_class_GuildManager_TypeInfo + 0xb8);
  do {
    if (((*(byte *)(_class_GuildManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_GuildManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    plVar9 = (long *)System_Delegate__Combine(0,lVar12,uVar6,0);
    uVar5 = *(undefined8 *)(_class_GuildManager_TypeInfo + 0xb8);
    plVar11 = (long *)0x0;
    if ((plVar9 != (long *)0x0) &&
       (plVar11 = plVar9, *plVar9 != _class_GuildManager_GuildErrorEvent_TypeInfo)) {
      func_0x0111ef28(&stack0xffffffffffffffb8,*(undefined8 *)(*plVar9 + 0x40));
      uVar8 = func_0x010ef81c(unaff_x25);
      func_0x010eebec(uVar8,0,0);
      func_0x036457e0(&stack0xffffffffffffffb8);
      plVar11 = (long *)0x0;
    }
    lVar10 = func_0x0110bbb4(uVar5,plVar11,lVar12);
    bVar1 = lVar10 != lVar12;
    lVar12 = lVar10;
  } while (bVar1);
  return;
}


