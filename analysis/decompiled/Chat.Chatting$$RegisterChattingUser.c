// name: Chat.Chatting$$RegisterChattingUser
// entry: 01d2a9c8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__RegisterChattingUser(long param_1,undefined8 param_2,undefined8 param_3)

{
  long lVar1;
  undefined8 uVar2;
  long lVar3;
  long lVar4;
  long lVar5;
  undefined8 uVar6;
  
  if ((bRam00000000049fcf17 & 1) == 0) {
    func_0x010c65d4(0x25ef);
    bRam00000000049fcf17 = 1;
  }
  lVar5 = *(long *)(param_1 + 0x48);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar5 + 0x10) = param_2;
  lVar5 = *(long *)(param_1 + 0x48);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar5 + 0x18) = param_3;
  lVar5 = *(long *)(param_1 + 0x48);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  *(undefined4 *)(lVar5 + 0x20) = 1;
  uVar6 = *(undefined8 *)(param_1 + 0x40);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1) !=
       0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0
             )) {
    func_0x010da55c();
  }
  uVar6 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Implicit(0,uVar6,0);
  if ((bRam00000000049fcf24 & 1) == 0) {
    func_0x010c65d4(0x25c1);
    bRam00000000049fcf24 = 1;
  }
  lVar5 = *(long *)(param_1 + 0x20);
  uVar2 = func_0x010fdda8(_class_<>__AnonType1<string,_string,_string>_TypeInfo);
  <>__AnonType1<string,_string,_string>___ctor
            (uVar2,param_2,param_3,uVar6,_mm_Method_<>__AnonType1<string,_string,_string>__ctor__);
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = LitJson_JsonMapper__ToJson(0,uVar2,0);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  uVar2 = _str_registerUser;
  if ((bRam0000000004a00249 & 1) == 0) {
    func_0x010c65d4(0xc5d1,_str_registerUser,uVar6,0);
    bRam0000000004a00249 = 1;
  }
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = System_String__Format(0,_str____0____1__,uVar2,uVar6,0);
  if ((bRam0000000004a0024d & 1) == 0) {
    func_0x010c65d4(0xc5ce);
    bRam0000000004a0024d = 1;
  }
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = LitJson_JsonMapper__ToObject(0,uVar6,0);
  lVar3 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
  uVar6 = _str__;
  System_Object___ctor(lVar3,0);
  *(undefined4 *)(lVar3 + 0x14) = 2;
  *(undefined4 *)(lVar3 + 0x18) = 0;
  *(undefined8 *)(lVar3 + 0x30) = uVar2;
  *(undefined8 *)(lVar3 + 0x20) = uVar6;
  *(undefined4 *)(lVar3 + 0x28) = 0xffffffff;
  *(undefined4 *)(lVar3 + 0x10) = 4;
  lVar4 = lVar5;
  if ((bRam0000000004a0024f & 1) == 0) {
    lVar4 = func_0x010c65d4(0xc5cf);
    bRam0000000004a0024f = 1;
  }
  lVar1 = *(long *)(lVar5 + 0x48);
  if (*(long *)(lVar5 + 0x50) == 0) {
    lVar4 = func_0x010eec64(0);
  }
  uVar6 = SocketIO_Encoder__Encode(lVar4,lVar3);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  WebSocketSharp_WebSocket__Send(lVar1,uVar6,0);
  return;
}


