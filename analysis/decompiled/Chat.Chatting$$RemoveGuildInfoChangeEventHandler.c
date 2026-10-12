// name: Chat.Chatting$$RemoveGuildInfoChangeEventHandler
// entry: 01d29d9c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__RemoveGuildInfoChangeEventHandler(undefined8 param_1)

{
  bool bVar1;
  long lVar2;
  undefined8 uVar3;
  long *plVar4;
  undefined8 uVar5;
  long lVar6;
  long *plVar7;
  undefined8 uVar8;
  undefined8 unaff_x25;
  
  if ((bRam00000000049fcf5b & 1) == 0) {
    func_0x010c65d4(0x25f2);
    bRam00000000049fcf5b = 1;
  }
  if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar2 = Singleton<iTweenManager>__get_instance(0,_mm_Method_Singleton<HttpRequest>_get_instance__)
  ;
  uVar8 = _mm_Method_Chat_Chatting_OnGuildJoin__;
  uVar3 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
  HttpRequest_ResponseCallback___ctor(uVar3,param_1,uVar8,0);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  HttpRequest__RemoveResponseHandler(lVar2,_str__guild_apply,uVar3,0);
  lVar2 = Singleton<iTweenManager>__get_instance(0,_mm_Method_Singleton<HttpRequest>_get_instance__)
  ;
  uVar8 = _mm_Method_Chat_Chatting_OnGuildJoin__;
  uVar3 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
  HttpRequest_ResponseCallback___ctor(uVar3,param_1,uVar8,0);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  HttpRequest__RemoveResponseHandler(lVar2,_str__guild_create,uVar3,0);
  lVar2 = Singleton<iTweenManager>__get_instance(0,_mm_Method_Singleton<HttpRequest>_get_instance__)
  ;
  uVar8 = _mm_Method_Chat_Chatting_OnGuildGet__;
  uVar3 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
  HttpRequest_ResponseCallback___ctor(uVar3,param_1,uVar8,0);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  HttpRequest__RemoveResponseHandler(lVar2,_str__guild_get,uVar3,0);
  lVar2 = Singleton<iTweenManager>__get_instance(0,_mm_Method_Singleton<HttpRequest>_get_instance__)
  ;
  uVar8 = _mm_Method_Chat_Chatting_OnGuildLeave__;
  uVar3 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
  HttpRequest_ResponseCallback___ctor(uVar3,param_1,uVar8,0);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  HttpRequest__RemoveResponseHandler(lVar2,_str__guild_leave,uVar3,0);
  lVar2 = Singleton<iTweenManager>__get_instance(0,_mm_Method_Singleton<HttpRequest>_get_instance__)
  ;
  uVar8 = _mm_Method_Chat_Chatting_OnGuildLeave__;
  uVar3 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
  HttpRequest_ResponseCallback___ctor(uVar3,param_1,uVar8,0);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  HttpRequest__RemoveResponseHandler(lVar2,_str__guild_break,uVar3,0);
  uVar8 = _mm_Method_Chat_Chatting_OnGuildError__;
  uVar3 = func_0x010fdda8(_class_GuildManager_GuildErrorEvent_TypeInfo);
  GuildManager_GuildErrorEvent___ctor(uVar3,param_1,uVar8,0);
  if (((*(byte *)(_class_GuildManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_GuildManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if ((bRam00000000049fed95 & 1) == 0) {
    func_0x010c65d4(0x617b,uVar3,0);
    bRam00000000049fed95 = 1;
  }
  if (((*(byte *)(_class_GuildManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_GuildManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar2 = **(long **)(_class_GuildManager_TypeInfo + 0xb8);
  do {
    if (((*(byte *)(_class_GuildManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_GuildManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    plVar4 = (long *)System_Delegate__Remove(0,lVar2,uVar3,0);
    uVar8 = *(undefined8 *)(_class_GuildManager_TypeInfo + 0xb8);
    plVar7 = (long *)0x0;
    if ((plVar4 != (long *)0x0) &&
       (plVar7 = plVar4, *plVar4 != _class_GuildManager_GuildErrorEvent_TypeInfo)) {
      func_0x0111ef28(&stack0xffffffffffffffb8,*(undefined8 *)(*plVar4 + 0x40));
      uVar5 = func_0x010ef81c(unaff_x25);
      func_0x010eebec(uVar5,0,0);
      func_0x036457e0(&stack0xffffffffffffffb8);
      plVar7 = (long *)0x0;
    }
    lVar6 = func_0x0110bbb4(uVar8,plVar7,lVar2);
    bVar1 = lVar6 != lVar2;
    lVar2 = lVar6;
  } while (bVar1);
  return;
}


