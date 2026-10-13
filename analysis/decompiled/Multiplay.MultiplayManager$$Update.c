// name: Multiplay.MultiplayManager$$Update
// entry: 013b0454

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__Update(long param_1)

{
  byte bVar1;
  int iVar2;
  uint uVar3;
  long lVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  ulong uVar7;
  float fVar8;
  undefined4 uVar9;
  
  uVar7 = Multiplay_MultiplayManager__get_isConnected();
  if ((uVar7 & 1) == 0) {
    return;
  }
  fVar8 = (float)UnityEngine_Time__get_realtimeSinceStartup(0,0);
  if (1.0 <= fVar8 - *(float *)(param_1 + 0x20)) {
    uVar9 = UnityEngine_Time__get_realtimeSinceStartup(0,0);
    *(undefined4 *)(param_1 + 0x20) = uVar9;
    iVar2 = UnityEngine_Application__get_internetReachability(0,0);
    if ((iVar2 != 0) && (*(int *)(param_1 + 0x1c) != iVar2)) {
      Multiplay_MultiplayManager__Disconnect(param_1);
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
      lVar4 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
      uVar6 = _mm_Method_Multiplay_MultiplayManager_OnMultiplayServerInfoRecieved__;
      uVar5 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
      HttpRequest_ResponseCallback___ctor(uVar5,param_1,uVar6,0);
      uVar6 = func_0x01101ad4(_class_object___TypeInfo,0);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      uVar6 = HttpRequest__Construct
                        (lVar4,_str__multiplay_getServerInfo,_str_POST,0,uVar5,0,0,uVar6);
      lVar4 = *(long *)(lVar4 + 0x30);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a0137d & 1) == 0) {
        func_0x010c65d4(0x6b97,uVar6,0);
        bRam0000000004a0137d = 1;
      }
      uVar3 = System_Collections_Generic_Queue<string>__Contains
                        (lVar4,uVar6,
                         _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Contains__
                        );
      if ((uVar3 & 1) == 0) {
        System_Collections_Generic_Queue<MessageEventArgs>__Enqueue
                  (lVar4,uVar6,
                   _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Enqueue__
                  );
        bVar1 = HttpRequest_HttpRequestQueue__FindRequestNeed2ShowIndicator(lVar4);
        *(byte *)(lVar4 + 0x28) = bVar1 & 1;
      }
      return;
    }
  }
  return;
}


