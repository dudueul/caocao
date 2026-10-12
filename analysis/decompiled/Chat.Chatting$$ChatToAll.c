// name: Chat.Chatting$$ChatToAll
// entry: 01d2bfdc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__ChatToAll
               (long param_1,undefined8 param_2,ulong param_3,ulong param_4,long param_5,
               undefined4 param_6,undefined4 param_7,undefined4 param_8)

{
  long lVar1;
  int iVar2;
  undefined4 uVar3;
  long *plVar4;
  long lVar5;
  long lVar6;
  ulong uVar7;
  ulong uVar8;
  long lVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  undefined1 auVar12 [16];
  
  uVar7 = param_4 & 0xffffffff;
  uVar8 = param_3 & 0xffffffff;
  if ((bRam00000000049fcf1c & 1) == 0) {
    func_0x010c65d4(0x25b1);
    bRam00000000049fcf1c = 1;
  }
  if (*(char *)(param_1 + 0x80) == '\0') {
    return;
  }
  lVar9 = *(long *)(param_1 + 0x48);
  lVar6 = lVar9;
  if (lVar9 == 0) {
    func_0x010eec64(0);
    lVar6 = *(long *)(param_1 + 0x48);
    if (lVar6 == 0) {
      auVar12 = func_0x010eec64(0);
      plVar4 = auVar12._0_8_;
      if ((bRam00000000049fcf6c & 1) == 0) {
        func_0x010c65d4(0x25a4);
        bRam00000000049fcf6c = 1;
      }
      if (((*(byte *)(_class_System_EventArgs_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_System_EventArgs_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      System_EventArgs___ctor(plVar4,0);
      (**(code **)(*plVar4 + 0x180))(plVar4,auVar12._8_8_,*(undefined8 *)(*plVar4 + 0x188));
      plVar4[3] = param_3;
      plVar4[4] = param_4;
      plVar4[5] = param_5;
      *(undefined4 *)(plVar4 + 6) = param_6;
      *(undefined4 *)((long)plVar4 + 0x34) = param_7;
      *(undefined4 *)(plVar4 + 7) = param_8;
      return;
    }
  }
  iVar2 = *(int *)(param_1 + 0x84);
  uVar11 = *(undefined8 *)(lVar6 + 0x18);
  uVar10 = *(undefined8 *)(lVar9 + 0x10);
  *(int *)(param_1 + 0x84) = iVar2 + 1;
  lVar6 = func_0x010fdda8(_class_Chat_ChattingMessage_TypeInfo);
  Chat_ChattingMessage___ctor(lVar6,param_2,uVar10,uVar11,_str_all,iVar2,uVar8,uVar7);
  if (*(char *)(param_1 + 0x80) != '\0') {
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    lVar9 = (long)(*(int *)(lVar6 + 0x34) + 2);
    if (2 < *(int *)(lVar6 + 0x34) - 1U) {
      lVar9 = 0;
    }
    uVar3 = *(undefined4 *)(&UNK_03997e00 + lVar9 * 4);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar10 = *(undefined8 *)(lVar6 + 0x28);
    if ((bRam00000000049fcf28 & 1) == 0) {
      func_0x010c65d4(0x25bc);
      bRam00000000049fcf28 = 1;
    }
    if (*(char *)(param_1 + 0x80) != '\0') {
      lVar9 = *(long *)(param_1 + 0x20);
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar11 = LitJson_JsonMapper__ToJson(0,lVar6,0);
      lVar6 = func_0x010fdda8(_class_Chat_Broadcast_TypeInfo);
      System_Object___ctor(lVar6,0);
      *(undefined4 *)(lVar6 + 0x10) = uVar3;
      *(undefined8 *)(lVar6 + 0x18) = uVar11;
      *(undefined8 *)(lVar6 + 0x20) = uVar10;
      *(undefined4 *)(lVar6 + 0x28) = 0xffffffff;
      uVar10 = LitJson_JsonMapper__ToJson(0,lVar6,0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      uVar11 = _str_broadcastTo;
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,_str_broadcastTo,uVar10,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = System_String__Format(0,_str____0____1__,uVar11,uVar10,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar11 = LitJson_JsonMapper__ToObject(0,uVar10,0);
      lVar5 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar10 = _str__;
      System_Object___ctor(lVar5,0);
      *(undefined4 *)(lVar5 + 0x14) = 2;
      *(undefined4 *)(lVar5 + 0x18) = 0;
      *(undefined8 *)(lVar5 + 0x30) = uVar11;
      *(undefined8 *)(lVar5 + 0x20) = uVar10;
      *(undefined4 *)(lVar5 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar5 + 0x10) = 4;
      lVar6 = lVar9;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar6 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar9 + 0x48);
      if (*(long *)(lVar9 + 0x50) == 0) {
        lVar6 = func_0x010eec64(0);
      }
      uVar10 = SocketIO_Encoder__Encode(lVar6,lVar5);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar10,0);
      return;
    }
    return;
  }
  return;
}


