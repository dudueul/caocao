// name: Chat.Chatting$$ConnectSocket
// entry: 01d2a4cc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__ConnectSocket(long param_1,undefined8 param_2,undefined8 param_3)

{
  ulong uVar1;
  undefined8 uVar2;
  undefined8 uVar3;
  long lVar4;
  
  if ((bRam00000000049fcf16 & 1) == 0) {
    func_0x010c65d4(0x25b9);
    bRam00000000049fcf16 = 1;
  }
  uVar3 = *(undefined8 *)(param_1 + 0x20);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar1 = UnityEngine_Object__op_Inequality(0,uVar3,0,0);
  if ((uVar1 & 1) != 0) {
    lVar4 = *(long *)(param_1 + 0x20);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar4,0);
    uVar3 = *(undefined8 *)(param_1 + 0x20);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    UnityEngine_Object__DestroyImmediate(0,uVar3,0);
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
  uVar3 = _mm_Method_Chat_Chatting_OnConnect__;
  lVar4 = *(long *)(param_1 + 0x20);
  uVar2 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar2,param_1,uVar3,_mm_Method_System_Action<SocketIOEvent>__ctor__);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__On(lVar4,_str_connect,uVar2,0);
  uVar3 = _mm_Method_Chat_Chatting_OnDisconnect__;
  lVar4 = *(long *)(param_1 + 0x20);
  uVar2 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar2,param_1,uVar3,_mm_Method_System_Action<SocketIOEvent>__ctor__);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__On(lVar4,_str_disconnect,uVar2,0);
  uVar3 = _mm_Method_Chat_Chatting_OnRegistered__;
  lVar4 = *(long *)(param_1 + 0x20);
  uVar2 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar2,param_1,uVar3,_mm_Method_System_Action<SocketIOEvent>__ctor__);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__On(lVar4,_str_userRegistered,uVar2,0);
  uVar3 = _mm_Method_Chat_Chatting_OnBroadcastTo__;
  lVar4 = *(long *)(param_1 + 0x20);
  uVar2 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar2,param_1,uVar3,_mm_Method_System_Action<SocketIOEvent>__ctor__);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__On(lVar4,_str_broadcastTo,uVar2,0);
  uVar3 = _mm_Method_Chat_Chatting_OnNotFoundRoomId__;
  lVar4 = *(long *)(param_1 + 0x20);
  uVar2 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar2,param_1,uVar3,_mm_Method_System_Action<SocketIOEvent>__ctor__);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__On(lVar4,_str_notFoundRoomId,uVar2,0);
  uVar3 = _mm_Method_Chat_Chatting_OnBroadcastDisconnectingTo__;
  lVar4 = *(long *)(param_1 + 0x20);
  uVar2 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar2,param_1,uVar3,_mm_Method_System_Action<SocketIOEvent>__ctor__);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__On(lVar4,_str_broadcastDisconnectingTo,uVar2,0);
  uVar3 = _mm_Method_Chat_Chatting_OnError__;
  lVar4 = *(long *)(param_1 + 0x20);
  uVar2 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar2,param_1,uVar3,_mm_Method_System_Action<SocketIOEvent>__ctor__);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__On(lVar4,_str_error,uVar2,0);
  uVar3 = _mm_Method_Chat_Chatting_OnCheckBanContent__;
  lVar4 = *(long *)(param_1 + 0x20);
  uVar2 = func_0x010fdda8(_class_System_Action<SocketIOEvent>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar2,param_1,uVar3,_mm_Method_System_Action<SocketIOEvent>__ctor__);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__On(lVar4,_str_checkBanContents,uVar2,0);
  lVar4 = *(long *)(param_1 + 0x20);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__set_URL(lVar4,param_2,0);
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
            (lVar4,_mm_Method_System_Collections_Generic_Dictionary<string,_ChattingUser>_Clear__);
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
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1) !=
       0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0
             )) {
    func_0x010da55c();
  }
  uVar3 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Implicit(0,param_3,0);
  *(undefined8 *)(param_1 + 0x40) = uVar3;
  return;
}


