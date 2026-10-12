// name: Chat.Chatting$$EmitLeaveRoom
// entry: 01d2be64

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__EmitLeaveRoom(long param_1,undefined8 param_2)

{
  long lVar1;
  undefined8 uVar2;
  undefined8 uVar3;
  long lVar4;
  long lVar5;
  long lVar6;
  
  if ((bRam00000000049fcf27 & 1) == 0) {
    func_0x010c65d4(0x25bf);
    bRam00000000049fcf27 = 1;
  }
  if (*(char *)(param_1 + 0x80) != '\0') {
    lVar6 = *(long *)(param_1 + 0x20);
    uVar2 = func_0x010fdda8(_class_<>__AnonType3<string>_TypeInfo);
    <>__AnonType3<string>___ctor(uVar2,param_2,_mm_Method_<>__AnonType3<string>__ctor__);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = LitJson_JsonMapper__ToJson(0,uVar2,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar3 = _str_leaveRoom;
    if ((bRam0000000004a00249 & 1) == 0) {
      func_0x010c65d4(0xc5d1,_str_leaveRoom,uVar2,0);
      bRam0000000004a00249 = 1;
    }
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = System_String__Format(0,_str____0____1__,uVar3,uVar2,0);
    if ((bRam0000000004a0024d & 1) == 0) {
      func_0x010c65d4(0xc5ce);
      bRam0000000004a0024d = 1;
    }
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar3 = LitJson_JsonMapper__ToObject(0,uVar2,0);
    lVar4 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
    uVar2 = _str__;
    System_Object___ctor(lVar4,0);
    *(undefined4 *)(lVar4 + 0x14) = 2;
    *(undefined4 *)(lVar4 + 0x18) = 0;
    *(undefined8 *)(lVar4 + 0x30) = uVar3;
    *(undefined8 *)(lVar4 + 0x20) = uVar2;
    *(undefined4 *)(lVar4 + 0x28) = 0xffffffff;
    *(undefined4 *)(lVar4 + 0x10) = 4;
    lVar5 = lVar6;
    if ((bRam0000000004a0024f & 1) == 0) {
      lVar5 = func_0x010c65d4(0xc5cf);
      bRam0000000004a0024f = 1;
    }
    lVar1 = *(long *)(lVar6 + 0x48);
    if (*(long *)(lVar6 + 0x50) == 0) {
      lVar5 = func_0x010eec64(0);
    }
    uVar2 = SocketIO_Encoder__Encode(lVar5,lVar4);
    if (lVar1 == 0) {
      func_0x010eec64(0);
    }
    WebSocketSharp_WebSocket__Send(lVar1,uVar2,0);
    return;
  }
  return;
}


