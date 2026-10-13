// name: Multiplay.MultiplayManager$$ConnectToMultiplayServer
// entry: 0139f9e8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__ConnectToMultiplayServer(undefined8 param_1)

{
  byte bVar1;
  uint uVar2;
  long lVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  
  if ((bRam00000000049f7b2b & 1) == 0) {
    func_0x010c65d4(0x8a33);
    bRam00000000049f7b2b = 1;
  }
  Multiplay_MultiplayManager__RemoveResponseHandler(param_1);
  Multiplay_MultiplayManager__AddResponseHandler(param_1);
  if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar3 = Singleton<iTweenManager>__get_instance(0,_mm_Method_Singleton<HttpRequest>_get_instance__)
  ;
  uVar5 = _mm_Method_Multiplay_MultiplayManager_OnMultiplayServerInfoRecieved__;
  uVar4 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
  HttpRequest_ResponseCallback___ctor(uVar4,param_1,uVar5,0);
  uVar5 = func_0x01101ad4(_class_object___TypeInfo,0);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  uVar5 = HttpRequest__Construct(lVar3,_str__multiplay_getServerInfo,_str_POST,0,uVar4,0,0,uVar5);
  lVar3 = *(long *)(lVar3 + 0x30);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  if ((bRam0000000004a0137d & 1) == 0) {
    func_0x010c65d4(0x6b97,uVar5,0);
    bRam0000000004a0137d = 1;
  }
  uVar2 = System_Collections_Generic_Queue<string>__Contains
                    (lVar3,uVar5,
                     _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Contains__
                    );
  if ((uVar2 & 1) == 0) {
    System_Collections_Generic_Queue<MessageEventArgs>__Enqueue
              (lVar3,uVar5,
               _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Enqueue__
              );
    bVar1 = HttpRequest_HttpRequestQueue__FindRequestNeed2ShowIndicator(lVar3);
    *(byte *)(lVar3 + 0x28) = bVar1 & 1;
  }
  return;
}


