// name: GuildWar.Backend.PImpl.SessionImpl$$Send
// entry: 01b2d8a8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void GuildWar_Backend_PImpl_SessionImpl__Send
               (long *param_1,undefined8 param_2,undefined8 param_3,uint param_4,undefined8 param_5,
               undefined8 param_6)

{
  byte bVar1;
  uint uVar2;
  long lVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  
  if ((bRam00000000049fbe02 & 1) == 0) {
    func_0x010c65d4(0xc02f);
    bRam00000000049fbe02 = 1;
  }
  lVar3 = (**(code **)(*param_1 + 0x170))(param_1,*(undefined8 *)(*param_1 + 0x178));
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = GuildWar_Backend_ConnectionInfo__get_logicServerUri(lVar3);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar4 = System_String__Concat(0,_str_http___,uVar4,param_2,0);
  uVar5 = GuildWar_Backend_PImpl_SessionImpl__addUserIdObjects(uVar4,param_6);
  if (((*(byte *)(_class_HttpRequest_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_HttpRequest_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_HttpRequest_TypeInfo);
  }
  uVar5 = HttpRequest__PacketArrayToPacketString(0,uVar5,0);
  uVar6 = func_0x010fdda8(_class_HttpRequest_HttpRequestContext_TypeInfo);
  HttpRequest_HttpRequestContext___ctor(uVar6,uVar4,uVar5,param_3,param_4 & 1,param_5,0,0,0);
  if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar3 = Singleton<iTweenManager>__get_instance(0,_mm_Method_Singleton<HttpRequest>_get_instance__)
  ;
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  lVar3 = *(long *)(lVar3 + 0x30);
  if (lVar3 == 0) {
    func_0x010eec64(0,uVar6,0);
  }
  if ((bRam0000000004a0137d & 1) == 0) {
    func_0x010c65d4(0x6b97,uVar6,0);
    bRam0000000004a0137d = 1;
  }
  uVar2 = System_Collections_Generic_Queue<string>__Contains
                    (lVar3,uVar6,
                     _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Contains__
                    );
  if ((uVar2 & 1) == 0) {
    System_Collections_Generic_Queue<MessageEventArgs>__Enqueue
              (lVar3,uVar6,
               _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Enqueue__
              );
    bVar1 = HttpRequest_HttpRequestQueue__FindRequestNeed2ShowIndicator(lVar3);
    *(byte *)(lVar3 + 0x28) = bVar1 & 1;
  }
  return;
}


