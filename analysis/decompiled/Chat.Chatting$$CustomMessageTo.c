// name: Chat.Chatting$$CustomMessageTo
// entry: 01d2cce0

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__CustomMessageTo(long param_1,undefined8 param_2,long param_3)

{
  long lVar1;
  long *plVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  long lVar6;
  long lVar7;
  long lVar8;
  
  if ((bRam00000000049fcf21 & 1) == 0) {
    func_0x010c65d4(0x25ba);
    bRam00000000049fcf21 = 1;
  }
  if (param_3 == 0) {
    func_0x010eec64(0);
  }
  plVar2 = (long *)System_Object__GetType(param_3,0);
  if (plVar2 == (long *)0x0) {
    func_0x010eec64(0);
  }
  uVar3 = (**(code **)(*plVar2 + 0x160))(plVar2,*(undefined8 *)(*plVar2 + 0x168));
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_LitJson_JsonMapper_TypeInfo);
  }
  uVar4 = LitJson_JsonMapper__ToJson(0,param_3,0);
  uVar5 = func_0x010fdda8(_class_Chat_TypeValuePair_TypeInfo);
  Chat_TypeValuePair___ctor(uVar5,uVar3,uVar4);
  if ((bRam00000000049fcf28 & 1) == 0) {
    func_0x010c65d4(0x25bc);
    bRam00000000049fcf28 = 1;
  }
  if (*(char *)(param_1 + 0x80) != '\0') {
    lVar8 = *(long *)(param_1 + 0x20);
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar3 = LitJson_JsonMapper__ToJson(0,uVar5,0);
    lVar6 = func_0x010fdda8(_class_Chat_Broadcast_TypeInfo);
    System_Object___ctor(lVar6,0);
    *(undefined4 *)(lVar6 + 0x10) = 6;
    *(undefined8 *)(lVar6 + 0x18) = uVar3;
    *(undefined8 *)(lVar6 + 0x20) = param_2;
    *(undefined4 *)(lVar6 + 0x28) = 0xffffffff;
    uVar3 = LitJson_JsonMapper__ToJson(0,lVar6,0);
    if (lVar8 == 0) {
      func_0x010eec64(0);
    }
    uVar4 = _str_broadcastTo;
    if ((bRam0000000004a00249 & 1) == 0) {
      func_0x010c65d4(0xc5d1,_str_broadcastTo,uVar3,0);
      bRam0000000004a00249 = 1;
    }
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar3 = System_String__Format(0,_str____0____1__,uVar4,uVar3,0);
    if ((bRam0000000004a0024d & 1) == 0) {
      func_0x010c65d4(0xc5ce);
      bRam0000000004a0024d = 1;
    }
    if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar4 = LitJson_JsonMapper__ToObject(0,uVar3,0);
    lVar7 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
    uVar3 = _str__;
    System_Object___ctor(lVar7,0);
    *(undefined4 *)(lVar7 + 0x14) = 2;
    *(undefined4 *)(lVar7 + 0x18) = 0;
    *(undefined8 *)(lVar7 + 0x30) = uVar4;
    *(undefined8 *)(lVar7 + 0x20) = uVar3;
    *(undefined4 *)(lVar7 + 0x28) = 0xffffffff;
    *(undefined4 *)(lVar7 + 0x10) = 4;
    lVar6 = lVar8;
    if ((bRam0000000004a0024f & 1) == 0) {
      lVar6 = func_0x010c65d4(0xc5cf);
      bRam0000000004a0024f = 1;
    }
    lVar1 = *(long *)(lVar8 + 0x48);
    if (*(long *)(lVar8 + 0x50) == 0) {
      lVar6 = func_0x010eec64(0);
    }
    uVar3 = SocketIO_Encoder__Encode(lVar6,lVar7);
    if (lVar1 == 0) {
      func_0x010eec64(0);
    }
    WebSocketSharp_WebSocket__Send(lVar1,uVar3,0);
    return;
  }
  return;
}


