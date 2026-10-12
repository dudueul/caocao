// name: Chat.Chatting$$EmitChattingMessage
// entry: 01d2c1b4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__EmitChattingMessage(long param_1,long param_2)

{
  long lVar1;
  undefined4 uVar2;
  undefined8 uVar3;
  long lVar4;
  long lVar5;
  undefined8 uVar6;
  long lVar7;
  
  if (*(char *)(param_1 + 0x80) == '\0') {
    return;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar7 = (long)(*(int *)(param_2 + 0x34) + 2);
  if (2 < *(int *)(param_2 + 0x34) - 1U) {
    lVar7 = 0;
  }
  uVar2 = *(undefined4 *)(&UNK_03997e00 + lVar7 * 4);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar6 = *(undefined8 *)(param_2 + 0x28);
  if ((bRam00000000049fcf28 & 1) == 0) {
    func_0x010c65d4(0x25bc);
    bRam00000000049fcf28 = 1;
  }
  if (*(char *)(param_1 + 0x80) != '\0') {
    lVar7 = *(long *)(param_1 + 0x20);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar3 = LitJson_JsonMapper__ToJson(0,param_2,0);
    lVar4 = func_0x010fdda8(_class_Chat_Broadcast_TypeInfo);
    System_Object___ctor(lVar4,0);
    *(undefined4 *)(lVar4 + 0x10) = uVar2;
    *(undefined8 *)(lVar4 + 0x18) = uVar3;
    *(undefined8 *)(lVar4 + 0x20) = uVar6;
    *(undefined4 *)(lVar4 + 0x28) = 0xffffffff;
    uVar6 = LitJson_JsonMapper__ToJson(0,lVar4,0);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    uVar3 = _str_broadcastTo;
    if ((bRam0000000004a00249 & 1) == 0) {
      func_0x010c65d4(0xc5d1,_str_broadcastTo,uVar6,0);
      bRam0000000004a00249 = 1;
    }
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar6 = System_String__Format(0,_str____0____1__,uVar3,uVar6,0);
    if ((bRam0000000004a0024d & 1) == 0) {
      func_0x010c65d4(0xc5ce);
      bRam0000000004a0024d = 1;
    }
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar3 = LitJson_JsonMapper__ToObject(0,uVar6,0);
    lVar5 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
    uVar6 = _str__;
    System_Object___ctor(lVar5,0);
    *(undefined4 *)(lVar5 + 0x14) = 2;
    *(undefined4 *)(lVar5 + 0x18) = 0;
    *(undefined8 *)(lVar5 + 0x30) = uVar3;
    *(undefined8 *)(lVar5 + 0x20) = uVar6;
    *(undefined4 *)(lVar5 + 0x28) = 0xffffffff;
    *(undefined4 *)(lVar5 + 0x10) = 4;
    lVar4 = lVar7;
    if ((bRam0000000004a0024f & 1) == 0) {
      lVar4 = func_0x010c65d4(0xc5cf);
      bRam0000000004a0024f = 1;
    }
    lVar1 = *(long *)(lVar7 + 0x48);
    if (*(long *)(lVar7 + 0x50) == 0) {
      lVar4 = func_0x010eec64(0);
    }
    uVar6 = SocketIO_Encoder__Encode(lVar4,lVar5);
    if (lVar1 == 0) {
      func_0x010eec64(0);
    }
    WebSocketSharp_WebSocket__Send(lVar1,uVar6,0);
    return;
  }
  return;
}


