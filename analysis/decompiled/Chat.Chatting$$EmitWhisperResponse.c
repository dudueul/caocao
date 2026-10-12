// name: Chat.Chatting$$EmitWhisperResponse
// entry: 01d2d0d8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__EmitWhisperResponse(long param_1,undefined8 param_2,undefined8 param_3)

{
  long lVar1;
  undefined8 uVar2;
  long lVar3;
  undefined8 uVar4;
  long lVar5;
  long lVar6;
  
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
    uVar2 = LitJson_JsonMapper__ToJson(0,param_2,0);
    lVar3 = func_0x010fdda8(_class_Chat_Broadcast_TypeInfo);
    System_Object___ctor(lVar3,0);
    *(undefined4 *)(lVar3 + 0x10) = 4;
    *(undefined8 *)(lVar3 + 0x18) = uVar2;
    *(undefined8 *)(lVar3 + 0x20) = param_3;
    *(undefined4 *)(lVar3 + 0x28) = 0xffffffff;
    uVar2 = LitJson_JsonMapper__ToJson(0,lVar3,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar4 = _str_broadcastTo;
    if ((bRam0000000004a00249 & 1) == 0) {
      func_0x010c65d4(0xc5d1,_str_broadcastTo,uVar2,0);
      bRam0000000004a00249 = 1;
    }
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = System_String__Format(0,_str____0____1__,uVar4,uVar2,0);
    if ((bRam0000000004a0024d & 1) == 0) {
      func_0x010c65d4(0xc5ce);
      bRam0000000004a0024d = 1;
    }
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar4 = LitJson_JsonMapper__ToObject(0,uVar2,0);
    lVar5 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
    uVar2 = _str__;
    System_Object___ctor(lVar5,0);
    *(undefined4 *)(lVar5 + 0x14) = 2;
    *(undefined4 *)(lVar5 + 0x18) = 0;
    *(undefined8 *)(lVar5 + 0x30) = uVar4;
    *(undefined8 *)(lVar5 + 0x20) = uVar2;
    *(undefined4 *)(lVar5 + 0x28) = 0xffffffff;
    *(undefined4 *)(lVar5 + 0x10) = 4;
    lVar3 = lVar6;
    if ((bRam0000000004a0024f & 1) == 0) {
      lVar3 = func_0x010c65d4(0xc5cf);
      bRam0000000004a0024f = 1;
    }
    lVar1 = *(long *)(lVar6 + 0x48);
    if (*(long *)(lVar6 + 0x50) == 0) {
      lVar3 = func_0x010eec64(0);
    }
    uVar2 = SocketIO_Encoder__Encode(lVar3,lVar5);
    if (lVar1 == 0) {
      func_0x010eec64(0);
    }
    WebSocketSharp_WebSocket__Send(lVar1,uVar2,0);
    return;
  }
  return;
}


