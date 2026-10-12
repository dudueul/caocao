// name: GuildWar.Backend.PImpl.SessionImpl$$UpdateSocketConnect
// entry: 01b31d08

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void GuildWar_Backend_PImpl_SessionImpl__UpdateSocketConnect(long *param_1)

{
  int iVar1;
  long lVar2;
  undefined8 uVar3;
  long lVar4;
  undefined8 uVar5;
  float fVar6;
  float fVar7;
  
  fVar7 = *(float *)(param_1 + 0x13);
  fVar6 = (float)UnityEngine_Time__get_deltaTime(0,0);
  *(float *)(param_1 + 0x13) = fVar7 + fVar6;
  if ((char)param_1[2] != '\0') {
    lVar2 = (**(code **)(*param_1 + 0x1a0))(param_1,*(undefined8 *)(*param_1 + 0x1a8));
    if (lVar2 == 0) {
      func_0x010eec64(0);
    }
    iVar1 = System_String__get_Length(lVar2,0);
    if ((((0 < iVar1) && (*(char *)((long)param_1 + 0x9d) == '\0')) &&
        (*(char *)((long)param_1 + 0x9c) == '\0')) && (5.0 <= *(float *)(param_1 + 0x13))) {
      *(undefined1 *)((long)param_1 + 0xa6) = 0;
      *(undefined1 *)((long)param_1 + 0x9c) = 1;
      *(undefined1 *)((long)param_1 + 0xa5) = 0;
      GuildWar_Backend_PImpl_SessionImpl__CreateSocket(param_1);
      lVar4 = param_1[0xf];
      lVar2 = (**(code **)(*param_1 + 0x170))(param_1,*(undefined8 *)(*param_1 + 0x178));
      if (lVar2 == 0) {
        func_0x010eec64(0);
      }
      uVar3 = GuildWar_Backend_ConnectionInfo__get_socketServerUri(lVar2);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      SocketIO_SocketIOComponent__set_URL(lVar4,uVar3,0);
      lVar2 = param_1[0xf];
      if (lVar2 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00243 & 1) == 0) {
        func_0x010c65d4(0xc5ca,0);
        bRam0000000004a00243 = 1;
      }
      uVar5 = *(undefined8 *)(lVar2 + 0x18);
      uVar3 = func_0x01101ad4(_class_string___TypeInfo,0);
      lVar4 = func_0x010fdda8(_class_WebSocketSharp_WebSocket_TypeInfo);
      WebSocketSharp_WebSocket___ctor(lVar4,uVar5,uVar3,0);
      *(long *)(lVar2 + 0x48) = lVar4;
      uVar3 = _mm_Method_SocketIO_SocketIOComponent_OnOpen__;
      uVar5 = func_0x010fdda8(_class_System_EventHandler_TypeInfo);
      System_EventHandler___ctor(uVar5,lVar2,uVar3,0);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__add_OnOpen(lVar4,uVar5,0);
      uVar3 = _mm_Method_SocketIO_SocketIOComponent_OnMessage__;
      lVar4 = *(long *)(lVar2 + 0x48);
      uVar5 = func_0x010fdda8(_class_System_EventHandler<MessageEventArgs>_TypeInfo);
      System_EventHandler<MessageEventArgs>___ctor
                (uVar5,lVar2,uVar3,_mm_Method_System_EventHandler<MessageEventArgs>__ctor__);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__add_OnMessage(lVar4,uVar5,0);
      uVar3 = _mm_Method_SocketIO_SocketIOComponent_OnError__;
      lVar4 = *(long *)(lVar2 + 0x48);
      uVar5 = func_0x010fdda8(_class_System_EventHandler<ErrorEventArgs>_TypeInfo);
      System_EventHandler<MessageEventArgs>___ctor
                (uVar5,lVar2,uVar3,_mm_Method_System_EventHandler<ErrorEventArgs>__ctor__);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__add_OnError(lVar4,uVar5,0);
      uVar3 = _mm_Method_SocketIO_SocketIOComponent_OnClose__;
      lVar4 = *(long *)(lVar2 + 0x48);
      uVar5 = func_0x010fdda8(_class_System_EventHandler<CloseEventArgs>_TypeInfo);
      System_EventHandler<MessageEventArgs>___ctor
                (uVar5,lVar2,uVar3,_mm_Method_System_EventHandler<CloseEventArgs>__ctor__);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__add_OnClose(lVar4,uVar5,0);
      func_0x010eae48();
      *(undefined1 *)(lVar2 + 0x44) = 0;
      uVar3 = _mm_Method_SocketIO_SocketIOComponent_ResetConnecting__;
      uVar5 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
      System_Action<tk2dUIUpDownHoverButton>___ctor
                (uVar5,lVar2,uVar3,_mm_Method_System_Action<SocketIOEvent>__ctor__);
      SocketIO_SocketIOComponent__On(lVar2,_str_open,uVar5);
      uVar3 = _mm_Method_SocketIO_SocketIOComponent_ResetConnecting__;
      uVar5 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
      System_Action<tk2dUIUpDownHoverButton>___ctor
                (uVar5,lVar2,uVar3,_mm_Method_System_Action<SocketIOEvent>__ctor__);
      SocketIO_SocketIOComponent__On(lVar2,_str_error,uVar5);
      func_0x010eae48();
      *(undefined1 *)(lVar2 + 0x41) = 1;
      uVar3 = SocketIO_SocketIOComponent__StayConnected(lVar2,*(undefined8 *)(lVar2 + 0x48));
      UnityEngine_MonoBehaviour__StartCoroutine(lVar2,uVar3,0);
      if (*(long *)(lVar2 + 0xa0) != 0) {
        UnityEngine_MonoBehaviour__StopCoroutine(lVar2,*(long *)(lVar2 + 0xa0),0);
      }
      uVar3 = SocketIO_SocketIOComponent__SendPing(lVar2,*(undefined8 *)(lVar2 + 0x48));
      uVar3 = UnityEngine_MonoBehaviour__StartCoroutine(lVar2,uVar3,0);
      *(undefined8 *)(lVar2 + 0xa0) = uVar3;
      return;
    }
  }
  return;
}


