// name: Chat.Chatting$$OnChattingUserRequest
// entry: 01d2dfa4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__OnChattingUserRequest(long param_1,long param_2)

{
  long lVar1;
  long lVar2;
  ulong uVar3;
  long lVar4;
  undefined8 uVar5;
  long lVar6;
  undefined8 uVar7;
  
  if ((bRam00000000049fcf31 & 1) == 0) {
    func_0x010c65d4(0x25e2);
    bRam00000000049fcf31 = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar5 = *(undefined8 *)(param_2 + 0x10);
  lVar6 = *(long *)(param_1 + 0x48);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = *(undefined8 *)(lVar6 + 0x18);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = System_String__op_Equality(0,uVar5,uVar7,0);
  if ((uVar3 & 1) != 0) {
    return;
  }
  uVar5 = *(undefined8 *)(param_1 + 0x48);
  if (param_2 == 0) {
    uVar3 = func_0x010eec64(0);
  }
  uVar7 = Chat_Chatting__GetSubRoomId(uVar3,*(undefined8 *)(param_2 + 0x10));
  if ((bRam00000000049fcf28 & 1) == 0) {
    func_0x010c65d4(0x25bc);
    bRam00000000049fcf28 = 1;
  }
  if (*(char *)(param_1 + 0x80) != '\0') {
    lVar6 = *(long *)(param_1 + 0x20);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar5 = LitJson_JsonMapper__ToJson(0,uVar5,0);
    lVar2 = func_0x010fdda8(_class_Chat_Broadcast_TypeInfo);
    System_Object___ctor(lVar2,0);
    *(undefined4 *)(lVar2 + 0x10) = 0;
    *(undefined8 *)(lVar2 + 0x18) = uVar5;
    *(undefined8 *)(lVar2 + 0x20) = uVar7;
    *(undefined4 *)(lVar2 + 0x28) = 0xffffffff;
    uVar5 = LitJson_JsonMapper__ToJson(0,lVar2,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = _str_broadcastTo;
    if ((bRam0000000004a00249 & 1) == 0) {
      func_0x010c65d4(0xc5d1,_str_broadcastTo,uVar5,0);
      bRam0000000004a00249 = 1;
    }
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar5 = System_String__Format(0,_str____0____1__,uVar7,uVar5,0);
    if ((bRam0000000004a0024d & 1) == 0) {
      func_0x010c65d4(0xc5ce);
      bRam0000000004a0024d = 1;
    }
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar7 = LitJson_JsonMapper__ToObject(0,uVar5,0);
    lVar4 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
    uVar5 = _str__;
    System_Object___ctor(lVar4,0);
    *(undefined4 *)(lVar4 + 0x14) = 2;
    *(undefined4 *)(lVar4 + 0x18) = 0;
    *(undefined8 *)(lVar4 + 0x30) = uVar7;
    *(undefined8 *)(lVar4 + 0x20) = uVar5;
    *(undefined4 *)(lVar4 + 0x28) = 0xffffffff;
    *(undefined4 *)(lVar4 + 0x10) = 4;
    lVar2 = lVar6;
    if ((bRam0000000004a0024f & 1) == 0) {
      lVar2 = func_0x010c65d4(0xc5cf);
      bRam0000000004a0024f = 1;
    }
    lVar1 = *(long *)(lVar6 + 0x48);
    if (*(long *)(lVar6 + 0x50) == 0) {
      lVar2 = func_0x010eec64(0);
    }
    uVar5 = SocketIO_Encoder__Encode(lVar2,lVar4);
    if (lVar1 == 0) {
      func_0x010eec64(0);
    }
    WebSocketSharp_WebSocket__Send(lVar1,uVar5,0);
    return;
  }
  return;
}


